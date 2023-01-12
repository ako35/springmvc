package com.tpe;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

// !!! web.xml yerine bu sinifimizi kullanacagiz.
public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    @Override
    // db ve hibernate ayarlarim icin kullanacagim
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{
                RootContextConfig.class
        };
    }

    @Override
    // Mvc config ayarlarim
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{
                WebMvcConfig.class
        };
    }

    @Override
    // hangi  url hangi servlet ile iliskilendirilecek
    protected String[] getServletMappings() {
        return new String[]{"/"}; // gelen butun request leri bu servlet tarafindan karsilanacagini soyluyorum
    }
}
