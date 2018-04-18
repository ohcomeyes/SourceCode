package com.boce.flcp;

import com.boce.flcp.interceptor.DemandInterceptor;
import com.boce.flcp.interceptor.DemandViewInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author xutang
 * @version V1.0
 * @Package PACKAGE_NAME
 * @Description: TODO(Api拦截)
 * @date 2017/12/4 11:42
 */
@Configuration
public class ApiInterceptor extends WebMvcConfigurerAdapter {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new DemandInterceptor()).addPathPatterns("/api/demands");
        registry.addInterceptor(new DemandViewInterceptor()).addPathPatterns("/api/demands/*/*");
        super.addInterceptors(registry);
    }
}
