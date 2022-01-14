package com.ds.nofication.Security;

import android.content.Context;
import android.content.SharedPreferences;

public class SecurityHandler {

    private final Context _context;

    public SecurityHandler(Context context) {
        _context = context;
    }

    /**
     * Write to the secure storage unit
     * @param key The key which represents the value
     */
    public void write(String key, String value) {
        SharedPreferences sp = getStorageUnit();
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(key, value);
        editor.apply();
    }

    /**
     * Read from the secure storage unit
     * @param key The key which represents the value we wanna read
     * @return The value in the storage unit
     */
    public String read(String key) {
        SharedPreferences sp = getStorageUnit();
        return sp.getString(key, "");
    }

    /**
     * @return A handle to the secure storage unit
     */
    private SharedPreferences getStorageUnit() {
        return _context.getSharedPreferences("MedicineApp_Storage", Context.MODE_PRIVATE);
    }
}
