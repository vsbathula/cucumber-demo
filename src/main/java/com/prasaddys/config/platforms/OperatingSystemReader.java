package com.prasaddys.config.platforms;


import com.prasaddys.interfaces.OperatingSystem;
import com.prasaddys.interfaces.Reader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collection;

public class OperatingSystemReader implements Reader<OperatingSystem> {

    private final Logger log = LoggerFactory.getLogger(OperatingSystemReader.class);

    @Override
    public Collection<OperatingSystem> read() {
        Collection<OperatingSystem> operatingSystems = new ArrayList<>();
        String os = System.getProperty("os-name");
        String osVersion = System.getProperty("os-version");
        String localOs = System.getProperty("os.name");
        String localOsVersion = System.getProperty("os.version");

        OperatingSystem operatingSystem = new OperatingSystemBase();

        if (os != null && !os.isEmpty()) {
            operatingSystem.setOsName(os);
        } else {
            operatingSystem.setOsName(localOs);
        }

        if (osVersion != null && !osVersion.isEmpty()) {
                operatingSystem.setOsVersion(osVersion);
        } else {
            operatingSystem.setOsVersion(localOsVersion);
        }

        log.info("Running on os: {}", operatingSystem.getOsName());
        log.info("Running on os version: {}", operatingSystem.getOsVersion());
        operatingSystems.add(operatingSystem);
//        if (os != null && !os.equals("")) {
//            OperatingSystem operatingSystem = new OperatingSystemBase();
//            operatingSystem.setOsName(os);
//            if (osVersion != null && !osVersion.equals("")) {
//                operatingSystem.setOsVersion(osVersion);
//            }
//            operatingSystems.add(operatingSystem);
//        }
        return operatingSystems;
    }

}
