package com.example.renwushu.module.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.renwushu.common.QueryField;
import com.example.renwushu.module.sys.entity.SysDictData;
import com.example.renwushu.module.sys.entity.SysDictType;
import com.example.renwushu.module.sys.dao.SysDictTypeMapper;
import com.example.renwushu.module.sys.entity.dto.SysDictDto;
import com.example.renwushu.module.sys.service.SysDictDataService;
import com.example.renwushu.module.sys.service.SysDictTypeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * <p>
 * 字典类型表 服务实现类
 * </p>
 *
 * @author RedStar
 * @since 2022-11-10
 */
@Service
public class SysDictTypeServiceImpl extends ServiceImpl<SysDictTypeMapper, SysDictType> implements SysDictTypeService {
    @Autowired
    SysDictDataService sysDictDataService;

    @Override
    public List<SysDictDto> getTreeDict() {
        LambdaQueryWrapper<SysDictType> queryWrapper = createQueryWrapper(new SysDictType().setStatus(QueryField.STATU_NOR));
        List<SysDictType> dictTypeList = this.list(queryWrapper);

        List<String> collect = dictTypeList.stream().map(SysDictType::getId).collect(Collectors.toList());

        LambdaQueryWrapper<SysDictData> lambdaQueryWrapper = new LambdaQueryWrapper();
        lambdaQueryWrapper.in(SysDictData::getDictId,collect);
        lambdaQueryWrapper.eq(SysDictData::getStatus,QueryField.STATU_NOR);
        lambdaQueryWrapper.orderByAsc(SysDictData::getTreeSort);
        List<SysDictData> dictDataList = sysDictDataService.list(lambdaQueryWrapper);

        return buildTreeDict(dictTypeList,dictDataList);
    }

    /*
    * 将字典转化为树形结构
    * */
    public List<SysDictDto> buildTreeDict(List<SysDictType> dictTypeList, List<SysDictData> dictDataList){
        List<SysDictDto> list = new ArrayList<>();

        dictTypeList.forEach(type -> {
            SysDictDto sysDictDto = new SysDictDto();
            sysDictDto.setId(type.getId());
            sysDictDto.setName(type.getDictName());
            sysDictDto.setValue(type.getDictType());
            sysDictDto.setSort(type.getTreeSort());
            sysDictDto.setRemarks(type.getRemarks());
            List<SysDictData> sysDictDatas = dictDataList.stream().filter(f -> f.getDictId().equals(type.getId())).collect(Collectors.toList());
            List<SysDictDto> dataList = new ArrayList<>();
            for (SysDictData dictData : sysDictDatas) {
                SysDictDto dictDto = new SysDictDto();
                dictDto.setId(dictData.getId());
                dictDto.setName(dictData.getDictValue());
                dictDto.setValue(dictData.getDictKey());
                dictDto.setSort(dictData.getTreeSort());
                dictDto.setRemarks(dictData.getRemarks());
                dataList.add(dictDto);
            };
            sysDictDto.setChildren(dataList);
            list.add(sysDictDto);
        });

        return list;
    }

    @Override
    public LambdaQueryWrapper<SysDictType> createQueryWrapper(SysDictType param){
        LambdaQueryWrapper<SysDictType> queryWrapper = new LambdaQueryWrapper<>();

        if (StringUtils.isNotBlank(param.getDictType())){
            queryWrapper.eq(SysDictType::getDictType, param.getDictType());
        }

        if (param.getStatus() != null) {
            queryWrapper.eq(SysDictType::getStatus, param.getStatus());
        } else {
            queryWrapper.eq(SysDictType::getStatus, QueryField.STATU_NOR);
        }
        if (StringUtils.isNotEmpty(param.getOrderBy())) {
            if (StringUtils.isNotEmpty(param.getOrderByType())
                    && QueryField.ASC.equals(param.getOrderByType())) {
                queryWrapper.orderByAsc(SysDictType::getStatus);
            } else {
                queryWrapper.orderByDesc(SysDictType::getOrderBy);
            }
        } else {
            queryWrapper.orderByDesc(SysDictType::getCreateDate);
        }
        return queryWrapper;
    }

}
