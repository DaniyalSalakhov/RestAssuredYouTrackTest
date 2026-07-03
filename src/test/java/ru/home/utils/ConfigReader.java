package ru.home.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {
    private static final Properties properties = new Properties();

    static {
        try (InputStream inputStream= ConfigReader.class.getClassLoader()
                .getResourceAsStream("config.properties")){
            properties.load(inputStream);
        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }

    public static String get(String key){
        return properties.getProperty(key);
    }
}
