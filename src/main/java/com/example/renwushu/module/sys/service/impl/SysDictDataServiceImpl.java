package com.example.renwushu.module.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.renwushu.common.QueryField;
import com.example.renwushu.module.sys.entity.SysDictData;
import com.example.renwushu.module.sys.dao.SysDictDataMapper;
import com.example.renwushu.module.sys.entity.SysMenu;
import com.example.renwushu.module.sys.service.SysDictDataService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 字典数据表 服务实现类
 * </p>
 *
 * @author RedStar
 * @since 2022-11-10
 */
@Service
public class SysDictDataServiceImpl extends ServiceImpl<SysDictDataMapper, SysDictData> implements SysDictDataService {

    /*
    * 翻译key值
    * dictId
    * key
    * */
    public String getValue(String dictId, String key){
        SysDictData sysDictData = new SysDictData();
        sysDictData.setDictId(dictId);

        SysDictData one = this.getOne(createQueryWrapper(sysDictData));
        if (one != null && StringUtils.isNotBlank(one.getDictValue())){
            return one.getDictValue();
        }

        return "";
    }

    @Override
    public LambdaQueryWrapper<SysDictData> createQueryWrapper(SysDictData param){
        LambdaQueryWrapper<SysDictData> queryWrapper = new LambdaQueryWrapper<>();

        if (StringUtils.isNotBlank(param.getDictKey())){
            queryWrapper.eq(SysDictData::getDictKey, param.getDictKey());
        }
        if (StringUtils.isNotBlank(param.getDictId())){
            queryWrapper.eq(SysDictData::getDictId, param.getDictId());
        }
        if (StringUtils.isNotBlank(param.getDictValue())){
            queryWrapper.eq(SysDictData::getDictValue, param.getDictValue());
        }

        if (param.getStatus() != null) {
            queryWrapper.eq(SysDictData::getStatus, param.getStatus());
        } else {
//            queryWrapper.eq(SysDictData::getStatus, QueryField.STATU_NOR);
        }
        if (StringUtils.isNotEmpty(param.getOrderBy())) {
            if (StringUtils.isNotEmpty(param.getOrderByType())
                    && QueryField.ASC.equals(param.getOrderByType())) {
                queryWrapper.orderByAsc(SysDictData::getStatus);
            } else {
                queryWrapper.orderByDesc(SysDictData::getOrderBy);
            }
        } else {
            queryWrapper.orderByAsc(SysDictData::getTreeSort);
        }
        return queryWrapper;
    }
}
