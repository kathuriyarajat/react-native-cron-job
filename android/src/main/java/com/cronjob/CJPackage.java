package com.cronjob;

import com.facebook.react.ReactPackage;

import com.facebook.react.bridge.NativeModule;

import com.facebook.react.bridge.ReactApplicationContext;

import com.facebook.react.uimanager.ViewManager;

import java.util.Arrays;

import java.util.Collections;

import java.util.List;

public class CJPackage implements ReactPackage {

    @Override

    public List<NativeModule> createNativeModules(ReactApplicationContext reactContext) {

        return Arrays.<NativeModule>asList(
                new CJModule(reactContext)
        );

    }

    @Override

    public List<ViewManager> createViewManagers(ReactApplicationContext reactContext) {

        return Collections.emptyList();

    }

}