package com.ericsson.csp.tsc.admin.util;

import java.net.URL;
import java.util.List;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.FileConfiguration;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.configuration.reloading.FileChangedReloadingStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class AppConfiguration {

    private static final Logger      LOG    = LoggerFactory.getLogger(AppConfiguration.class);

    private static FileConfiguration config = null;

    static {
        try {
            URL url = AppConfiguration.class.getClassLoader().getResource("app.properties");
            if (url == null) {
                throw new RuntimeException("unable to find resource: app.properties");
            }
            config = new PropertiesConfiguration(url);
            config.setEncoding("UTF-8");
            config.setReloadingStrategy(new FileChangedReloadingStrategy());
        } catch (final Exception e) {
            // LOG.error("Exception occured while loading app.properties.", e);
            throw new RuntimeException(e);
        }
    }

    public static String[] getStringArray(final String propertyName) {
        return config.getStringArray(propertyName);
    }

    public static String getString(final String propertyName) {
        return config.getString(propertyName);
    }

    public static List<Object> getList(final String propertyName) {
        return config.getList(propertyName);
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

    public static float getFloat(final String propertyName) {
        return config.getFloat(propertyName);
    }

    public static float getFloat(final String propertyName, final int defaultValue) {
        return config.getFloat(propertyName, defaultValue);
    }

    public static double getDouble(final String propertyName) {
        return config.getDouble(propertyName);
    }

    public static double getDouble(final String propertyName, final int defaultValue) {
        return config.getDouble(propertyName, defaultValue);
    }

    public static boolean getBoolean(final String propertyName) {
        return config.getBoolean(propertyName);
    }

    public static boolean getBoolean(final String propertyName, final boolean defaultValue) {
        return config.getBoolean(propertyName, defaultValue);
    }

    public static synchronized void setProperty(String propertyName, Object propertyValue) {
        try {
            config.setProperty(propertyName, propertyValue);
            config.save();
        } catch (ConfigurationException e) {
            LOG.error("Exception occured while writing app.properties.", e);
        }
    }

}
