package com.qa.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

	private static Properties props = new Properties();

	static {
		try {
			FileInputStream fis = new FileInputStream("src/main/resources/config/config.properties");
			props = new Properties();
			props.load(fis);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String getProperty(String key) {
		return props.getProperty(key);
	}
	
	public String emailHost() {
		return props.getProperty("EmailHost");
	}
	
	public String mailPort() {
		return props.getProperty("MailPort");
	}
	
	public String mailUserName() {
		return props.getProperty("MailUserName");
	}
	
	public String mailPassword() {
		return props.getProperty("MailPassword");
	}
	
	
	public String mailTo() {
		return props.getProperty("MailTo");
	}
	
	public String mailCc() {
		return props.getProperty("MailCC");
	}
}
