
package com.ericsson.csp.tsc.admin.util;

import java.io.File;
import java.util.List;

import org.apache.commons.configuration.XMLConfiguration;
import org.apache.commons.configuration.reloading.FileChangedReloadingStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class AppXmlConfiguration {

    private static final Logger     LOG = LoggerFactory.getLogger(AppXmlConfiguration.class);

    private static XMLConfiguration config;

    static {
        try {
            LOG.info("load configuration from ../config/app.xml");
            System.out.println("load configuration from ../config/app.xml");
            String path = AppXmlConfiguration.class.getClassLoader().getResource("app.xml").getPath();
            System.out.println(path);
            File file = new File(path);

            if (file.exists()) {
                System.out.println("file exists");
            } else {
                System.out.println("file not exists");
            }

            config = new XMLConfiguration(new File(path));
            config.setReloadingStrategy(new FileChangedReloadingStrategy());
        } catch (final Exception e) {
            LOG.error("Exception happened while loading parameters.", e);
        }
    }

    public static <T> List<T> getList(final String propertyName) {
        return (List<T>) config.getList(propertyName);
    }
    
    public static String getString(final String propertyName) {
        return config.getString(propertyName);
    }

    public static String getString(final String propertyName, final String defaultValue) {
        return config.getString(propertyName, defaultValue);
    }

    public static int getInt(final String propertyName) {
        return config.getInt(propertyName);
    }

    public static int getInt(final String propertyName, final int defaultValue) {
        return config.getInt(propertyName, defaultValue);
    }

}
