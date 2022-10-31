### springSecurity框架理解
* 请求接口为 `/login`，此为默认接口，重写此接口不会起作用。此项目重写只是能再 `swagger` 中看到。
* 配置说明：此处说明对应 `SecurityConfiguration` 
  * 登录接口使用到的配置
    * `.addFilterBefore()` 配置校验用户名密码前的逻辑处理，此处校验验证码
    * `.userDetailsService()` 检验用户名密码的配置
    * `.successHandler()`校验用户名密码成功进入此配置。上面返回非null
    * `.failureHandler()`检验用户名密码失败进入此配置
  * 其他接口使用到的配置
    * `.addFilter()` 其他接口会进入此配置。进行jwt校验。注意这个类中无法注入。使用到的`authenticationManager`需要关闭2.6之后的循环依赖校验。
    * `.authenticationEntryPoint()` 认证失败进入此配置。返回提示
    * `.accessDeniedHandler()` 鉴权失败会进入此配置
  * 退出登录接口使用到的配置
    * `.logoutSuccessHandler()` 请求`/loginout`接口后会进入此方法
  * 其他配置
  * Security内置的权限注解：直接加载 `controller`方法上`@PreAuthorize("hasRole('admin')")/@PreAuthorize("hasAuthority('sys:user:save')")`
    * `@PreAuthorize`：方法执行前进行权限检查
    * `@PostAuthorize`：方法执行后进行权限检查
  