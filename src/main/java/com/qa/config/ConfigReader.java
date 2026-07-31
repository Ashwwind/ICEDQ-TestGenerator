package com.qa.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

    private static final Properties props = new Properties();

    static {
        // FIX: Use try-with-resources so FileInputStream is always closed.
        // The original code opened the stream but never closed it (resource leak).
        try (FileInputStream fis = new FileInputStream("src/main/resources/config/config.properties")) {
            props.load(fis);
        } catch (IOException e) {
            throw new ExceptionInInitializerError(
                    STR."Cannot load config.properties: \{e.getMessage()}");
        }
    }

    public static String getProperty(String key) {
        return props.getProperty(key);
    }

    // ── Email config helpers ────────────────────────────────────────────────────────

    public String emailHost()    { return props.getProperty("EmailHost");    }
    public String mailPort()     { return props.getProperty("MailPort");     }
    public String mailUserName() { return props.getProperty("MailUserName"); }
    public String mailPassword() { return props.getProperty("MailPassword"); }
    public String mailTo()       { return props.getProperty("MailTo");       }
    public String mailCc()       { return props.getProperty("MailCC");       }
}
