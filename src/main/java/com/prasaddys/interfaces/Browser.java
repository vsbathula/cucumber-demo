package com.prasaddys.interfaces;

import java.net.URL;

public interface Browser {

    String getBrowserName();

    void setBrowserName(String browserName);

    String getBrowserVersion();

    void setBrowserVersion(String browserVersion);

    URL getRemoteUrl();

    void setRemoteUrl(URL remoteUrl);

}
