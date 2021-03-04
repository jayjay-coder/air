package org.jayjay.air.security.config;

import org.jayjay.air.security.evaluator.UserPermissionEvaluator;
import org.jayjay.air.security.filter.JwtAuthenticationFilter;
import org.jayjay.air.security.filter.SysUsernamePasswordAuthenticationFilter;
import org.jayjay.air.security.handler.*;
import org.jayjay.air.security.provider.UserAuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @Author: JayJay
 * @Date: 25/2/2021
 * @ClassName: SecurityConfig
 * @Description:
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true) // 开启方法权限注解
public class SecurityConfig  extends WebSecurityConfigurerAdapter {


    /**
     * 无权限处理类
     */
    @Autowired
    private UserAccessDeniedHandler userAccessDeniedHandler;

    /**
     * 用户未登录处理类
     */
    @Autowired
    private UserNotLoginHandler userNotLoginHandler;

    /**
     * 用户登录成功处理类
     */
    @Autowired
    private UserLoginSuccessHandler userLoginSuccessHandler;

    /**
     * 用户登录失败处理类
     */
    @Autowired
    private UserLoginFailureHandler userLoginFailureHandler;

    /**
     * 用户登出成功处理类
     */
    @Autowired
    private UserLogoutSuccessHandler userLogoutSuccessHandler;

    /**
     * 用户登录验证
     */
    @Autowired
    private UserAuthenticationProvider userAuthenticationProvider;

    /**
     * 用户权限注解
     */
    @Autowired
    private UserPermissionEvaluator userPermissionEvaluator;

    /**
     * 加密方式
     *
     * @return
     */
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * 注入自定义PermissionEvaluator
     *
     * @return
     */
    @Bean
    public DefaultWebSecurityExpressionHandler userSecurityExpressionHandler() {
        DefaultWebSecurityExpressionHandler handler = new DefaultWebSecurityExpressionHandler();
        handler.setPermissionEvaluator(userPermissionEvaluator);
        return handler;
    }

    /**
     * 用户登录验证
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(userAuthenticationProvider);
    }

    /**
     * 安全权限配置
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.anonymous().principal("Guest")
                .and().authorizeRequests() // 权限配置
                .antMatchers(JwtConfig.antMatchers.split(",")).permitAll()// 获取白名单（不进行权限验证）
                .anyRequest().authenticated() // 其他的需要登陆后才能访问
                .and().httpBasic().authenticationEntryPoint(userNotLoginHandler) // 配置未登录处理类
//                .and().addFilterAt(new SysUsernamePasswordAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class).formLogin()
//                .and()
//                .formLogin().loginProcessingUrl("/login/s")// 配置登录URL
//                .successHandler(userLoginSuccessHandler) // 配置登录成功处理类
//                .failureHandler(userLoginFailureHandler) // 配置登录失败处理类
                .and().logout().logoutUrl("/logout")// 配置登出地址
                .logoutSuccessHandler(userLogoutSuccessHandler) // 配置用户登出处理类
                .and().exceptionHandling().accessDeniedHandler(userAccessDeniedHandler)// 配置没有权限处理类
                .and().cors()// 开启跨域
                .and().csrf().disable(); // 禁用跨站请求伪造防护
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS); // 禁用session（使用Token认证）
        http.headers().cacheControl(); // 禁用缓存
        http.addFilter(new JwtAuthenticationFilter(authenticationManager())); //// 添加JWT过滤器
    }


    @Bean
    public SysUsernamePasswordAuthenticationFilter loginFilter() throws Exception {
        SysUsernamePasswordAuthenticationFilter loginFilter = new SysUsernamePasswordAuthenticationFilter();
        loginFilter.setAuthenticationSuccessHandler(userLoginSuccessHandler);
        loginFilter.setAuthenticationFailureHandler(userLoginFailureHandler);
        loginFilter.setAuthenticationManager(authenticationManagerBean());
        loginFilter.setFilterProcessesUrl("/login");
        return loginFilter;
    }

}
