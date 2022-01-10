package com.ds.nofication;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import android.content.Context;

import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.Test;
import org.junit.runner.RunWith;

public class ConfigLoaderTest {

    @Test
    public void loadConfigTest(){
        ConfigLoader configLoader = new ConfigLoader();
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();

        String configValue = configLoader.getConfigValue(appContext, "api_url");

        assertNotNull(configValue);
    }

    @Test
    public void loadConfigFailTest(){
        ConfigLoader configLoader = new ConfigLoader();
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();

        // If the key does not exist, it will be error logged and method will return null
        String configValue = configLoader.getConfigValue(appContext, "key that does not exist");

        assertEquals(null, configValue);
    }
}
