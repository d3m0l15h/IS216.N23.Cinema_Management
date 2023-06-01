package uit.utils;

public class Env {
    public static final String DB_HOST = "localhost";
    public static final String DB_USERNAME = "root";
    public static final String DB_PASSWORD = "";

    public static String getEnv(String key) {
        return System.getenv(key);
    }
}
