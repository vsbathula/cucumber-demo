package com.prasaddys.config.platforms;

import com.prasaddys.interfaces.Platform;
import enums.PlatformType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PlatformBase implements Platform {

    protected String deviceName;
    protected enums.PlatformType platformType;
    protected String runOnGrid;
    protected String osName;
    protected String osVersion;
    protected String browserName;
    protected String browserVersion;
    protected URL remoteUrl;
    protected DesiredCapabilities desiredCapabilities;

}
