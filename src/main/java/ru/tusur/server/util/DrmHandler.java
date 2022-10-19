package ru.tusur.server.util;

import ru.tusur.server.data.DrmData;
import ru.tusur.server.data.StatusCode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class DrmHandler {

    private static final Map<String, DrmData> usersData;
    private static final Map<String, Boolean> licenseData;
    private static final Set<String> hardwareSet;

    static {
        hardwareSet = new HashSet<>();
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

    public static int isCorrectPassword(String username, String password) {
        if (!isExist(username)) {
            return StatusCode.LOGIN_FAILED_201.getCode();
        } else if (!usersData.get(username).getPassword().equals(password)) {
            return StatusCode.LOGIN_FAILED_202.getCode();
        } else {
            return StatusCode.LOGIN_SUCCESS_101.getCode();
        }
    }

    public static int addUser(String username, String password) {
        if (!isExist(username)) {
            DrmData drmData = new DrmData();
            drmData.setPassword(password);
            usersData.put(username, drmData);
            return StatusCode.REGISTRATION_SUCCESS_110.getCode();
        }
        return StatusCode.REGISTRATION_FAILED_210.getCode();
    }

    public static int checkHardware(String username, String hardware) {
        if (!isExist(username)) {
            return StatusCode.LOGIN_FAILED_201.getCode();
        } else if (usersData.get(username).getHardware() == null || !usersData.get(username).getHardware().equals(hardware)) {
            return StatusCode.HARDWARE_FAILED_230.getCode();
        } else if (!licenseData.containsKey(usersData.get(username).getLicense()) || !licenseData.get(usersData.get(username).getLicense())) {
            return StatusCode.LICENSE_FAILED_221.getCode();
        } else {
            return StatusCode.HARDWARE_SUCCESS_130.getCode();
        }
    }

    public static int addHardware(String username, String hardware) {

        if (!isExist(username)) {
            return StatusCode.LOGIN_FAILED_201.getCode();
        } else if (hardwareSet.contains(hardware)) {
            return StatusCode.HARDWARE_FAILED_231.getCode();
        } else {
            hardwareSet.add(hardware);
            DrmData drmData = usersData.get(username);
            drmData.setHardware(hardware);
            usersData.put(username, drmData);
            return StatusCode.HARDWARE_SUCCESS_130.getCode();
        }
    }

    public static int addLicense(String username, String key) {
        if (!isExist(username)) {
            return StatusCode.LOGIN_FAILED_201.getCode();
        } else if (!licenseData.containsKey(key)) {
            return StatusCode.LICENSE_FAILED_221.getCode();
        } else if (licenseData.get(key)) {
            return StatusCode.LICENSE_FAILED_220.getCode();
        } else {
            licenseData.put(key, !licenseData.get(key));
            DrmData drmData = usersData.get(username);
            drmData.setLicense(key);
            usersData.put(username, drmData);
            return StatusCode.LICENSE_SUCCESS_120.getCode();
        }
    }

    private static boolean isExist(String username) {
        return usersData.containsKey(username);
    }
}
