package ru.tusur.server.util;

import ru.tusur.server.data.DrmData;

import java.util.HashMap;
import java.util.Map;

public class DrmHandler {


    private static final Map<String, DrmData> usersData;
    private static final Map<String, Boolean> licenseData;

    static {
        usersData = new HashMap<>();
        DrmData drmData = new DrmData();
        drmData.setPassword("0000");
        drmData.setHardware("hard");
        drmData.setLicense("XXXX-XXXX-XXXX-XXXX");
        usersData.put("den1", drmData);

        licenseData = new HashMap<>();
        licenseData.put("XXXX-XXXX-XXXX-XXXX", true);
        licenseData.put("AAAA-XXXX-XXXX-XXXX", false);
        licenseData.put("BBBB-XXXX-XXXX-XXXX", false);
    }

    public static boolean isCorrectPassword(String username, String password) {
        return isExist(username) && usersData.get(username).getPassword().equals(password);
    }

    public static boolean addUser(String username, String password) {
        if (!isExist(username)) {
            DrmData drmData = new DrmData();
            drmData.setPassword(password);
            usersData.put(username, drmData);
            return true;
        }
        return false;
    }

    public static boolean checkHardware(String username, String hardware) {
        return isExist(username) && usersData.get(username).getHardware().equals(hardware);
    }

    public static boolean addHardware(String username, String hardware) {
        if (!isExist(username) && !checkHardware(username, hardware)) {
            DrmData drmData = usersData.get(username);
            drmData.setHardware(hardware);
            usersData.put(username, drmData);
            return true;
        }
        return false;
    }

    public static boolean checkLicense(String username, String key) {
        return isExist(username) && usersData.get(username).getLicense().equals(key);
    }

    public static boolean addLicense(String username, String key) {
        if (isExist(username) && !checkLicense(username, key) && isExistLicense(key)) {
            licenseData.put(key, !licenseData.get(key));
            DrmData drmData = usersData.get(username);
            drmData.setLicense(key);
            usersData.put(username, drmData);
            return true;
        }
        return false;
    }

    private static boolean isExistLicense(String license) {
        return licenseData.containsKey(license) && !licenseData.get(license);
    }

    private static boolean isExist(String username) {
        return usersData.containsKey(username);
    }

}
