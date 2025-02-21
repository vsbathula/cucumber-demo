package com.prasaddys.config.environment;

import com.prasaddys.config.properties.PropertiesConfigReader;
import com.prasaddys.util.CommonConstants;
import lombok.Getter;
import lombok.Setter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Setter
@Getter
public class EnvironmentReader {

    private final Logger log = LogManager.getLogger(EnvironmentReader.class);
    private final PropertiesConfigReader propertiesConfigReader;
    private String envUrl;

    public EnvironmentReader(String envName) {
        this.propertiesConfigReader = new PropertiesConfigReader(CommonConstants.CONFIG_FILE_PATH);
        this.envUrl = this.propertiesConfigReader.getProperty(envName + ".url");
        log.info("Environment url: {}", this.envUrl);
    }

}

