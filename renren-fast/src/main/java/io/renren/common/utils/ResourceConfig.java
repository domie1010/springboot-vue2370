package io.renren.common.utils;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Created by 李杨勇 on 2021/7/5 0005.
 */
@Configuration
public class ResourceConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        /** 图片传路径 */
        registry.addResourceHandler("/virtuel/**").addResourceLocations("file:" + MyFileConfig.getFilePath());

    }
}
