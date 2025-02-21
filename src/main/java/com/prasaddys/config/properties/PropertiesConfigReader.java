package com.prasaddys.config.properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.Properties;

public class PropertiesConfigReader {

    private final Logger log = LogManager.getLogger(PropertiesConfigReader.class);
    protected String configFilePath;

    public PropertiesConfigReader(String configFilePath) {
        this.configFilePath = configFilePath;
    }

    public String getProperty(String propertyName) {
        log.info("Getting property value of: {}", propertyName);
        String property = System.getProperty(propertyName);

        if (property == null) {
            loadProperties();
            property = System.getProperty(propertyName);
        }

        return  property;
    }

    protected Properties loadProperties() {
        Properties properties = new Properties();
        InputStream input = null;

        try{
            input = new FileInputStream(this.configFilePath);
            properties.load(input);
        } catch (IOException ex) {
            log.error(ex.getMessage());
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException ex) {
                    log.error(ex.getMessage());
                }
            }
        }

        Properties systemProperties = System.getProperties();

        Enumeration<?> loadPropertyNames = properties.propertyNames();

        while (loadPropertyNames.hasMoreElements()) {
            String name = loadPropertyNames.nextElement().toString();

            systemProperties.setProperty(name, properties.getProperty(name));
        }
        return properties;
    }

}
