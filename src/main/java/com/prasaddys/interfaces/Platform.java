package com.prasaddys.interfaces;

import enums.PlatformType;

public interface Platform extends BrowserCapabilities, Browser, OperatingSystem {

    String getDeviceName();

    void setDeviceName(String deviceName);

    PlatformType getPlatformType();

    void setPlatformType(PlatformType platformType);

    String getRunOnGrid();

    void setRunOnGrid(String runOnGrid);

}
