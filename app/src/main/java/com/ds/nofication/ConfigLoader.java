package com.ds.nofication;

import android.content.Context;
import android.content.res.Resources;
import android.nfc.Tag;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigLoader {
    private static final String TAG = "ConfigLoader";

    /**
     * Loads value from config file
     * @param context Activity context
     * @param name Name of the config key/name
     * @return return value from the config or null
     */
    public String getConfigValue(Context context, String name) {
        Resources resources = context.getResources();

        try {
            InputStream rawResource = resources.openRawResource(R.raw.config);
            Properties properties = new Properties();
            properties.load(rawResource);
            return properties.getProperty(name);
        } catch (Resources.NotFoundException e) {
            Log.e(TAG, "Unable to find the config file: " + e.getMessage());
        } catch (IOException e) {
            Log.e(TAG, "Failed to open config file.");
        } catch (Exception e){
            Log.e(TAG, e.getMessage());
        }

        return null;
    }
}
