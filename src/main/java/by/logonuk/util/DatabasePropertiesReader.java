package by.logonuk.util;

import java.util.ResourceBundle;

public class DatabasePropertiesReader {
    public static final String POSTGRES_DRIVER_NAME = "POSTGRES_DRIVER_NAME";
    public static final String DATABASE_URL = "DATABASE_URL";
    public static final String DATABASE_LOGIN = "DATABASE_LOGIN";
    public static final String DATABASE_PASSWORD = "DATABASE_PASSWORD";

    public static String getProperty(String key) {
        return ResourceBundle.getBundle("database").getString(key);
    }
}
