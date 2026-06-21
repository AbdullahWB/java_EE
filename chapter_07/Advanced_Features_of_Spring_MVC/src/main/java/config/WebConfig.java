package config;

import handle.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        System.out.println("LoginInterceptor 执行了");
        registry.addInterceptor(new LoginInterceptor())
//        拦截所有请求。
                .addPathPatterns("/**")
//        放行登录页面和登录提交请求。
                .excludePathPatterns("/login", "/doLogin");
    }
}
