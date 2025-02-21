package com.prasaddys.config.platforms;

import com.prasaddys.interfaces.OperatingSystem;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class OperatingSystemBase implements OperatingSystem {

    protected String osName;
    protected String osVersion;

}
