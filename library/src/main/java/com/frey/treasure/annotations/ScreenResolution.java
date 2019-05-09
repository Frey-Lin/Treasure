package com.frey.treasure.annotations;


import android.support.annotation.IntDef;

import com.frey.treasure.device.DeviceInfo;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@IntDef({DeviceInfo.SCREEN_RESOLUTION_HEIGHT_FIRST, DeviceInfo.SCREEN_RESOLUTION_WIDTH_FIRST})
@Retention(RetentionPolicy.SOURCE)
@Target(ElementType.PARAMETER)
public @interface ScreenResolution {

}
