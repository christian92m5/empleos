package com.course.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Value("${empleosapp.ruta.imagenes}")
    private String rutaImagenes;

    @Value("${empleosapp.ruta.cv}")
    private String rutaCV;

    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/logos/**").addResourceLocations("file:"+rutaImagenes); // Linux
        registry.addResourceHandler("/cv/**").addResourceLocations("file:"+rutaCV); // Linux

        //registry.addResourceHandler("/logos/**").addResourceLocations("file:/Users/christianguaman/Desktop/empleos/img-vacantes/"); // Linux
        //registry.addResourceHandler("/logos/**").addResourceLocations("file:c:/empleos/img-vacantes/"); // Windows
    }
}
