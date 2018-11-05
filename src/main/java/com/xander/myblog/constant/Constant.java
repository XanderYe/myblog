package com.xander.myblog.constant;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by Xander on 2018-11-05.
 */
public final class Constant {
    public static String SALT;

    static {
        Properties prop = new Properties();
        try {
            InputStream inputStream = new Object() {
                public InputStream getInputStream() {
                    return this.getClass().getResourceAsStream("/config.properties");
                }
            }.getInputStream();
            prop.load(inputStream);
            SALT = prop.getProperty("SALT");
        } catch (IOException e) {
            e.printStackTrace();
        }catch (Exception e1){
            e1.printStackTrace();
        }
    }
}
