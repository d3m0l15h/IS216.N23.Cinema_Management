package uit.utils;

public class Env {
    public static final String DB_HOST = "DB_HOST";
    public static final String DB_USERNAME = "DB_USERNAME";
    public static final String DB_PASSWORD = "DB_PASSWORD";

    public static String getEnv(String key) {
        return System.getenv(key);
    }
}
