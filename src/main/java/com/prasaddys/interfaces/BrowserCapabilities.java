package com.prasaddys.interfaces;

import org.openqa.selenium.remote.DesiredCapabilities;

public interface BrowserCapabilities {

    DesiredCapabilities getDesiredCapabilities();

    void setDesiredCapabilities(DesiredCapabilities desiredCapabilities);

}
