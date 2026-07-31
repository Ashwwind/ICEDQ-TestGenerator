package com.qa.sendmail;

import java.io.File;
import java.net.InetAddress;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Base64;
import java.util.Comparator;
import java.util.Properties;

import com.qa.base.Base;
import com.qa.config.ConfigReader;
import com.qa.extentreportlistener.ExtentReportListener;
import jakarta.activation.DataHandler;
import jakarta.activation.DataSource;
import jakarta.activation.FileDataSource;
import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.Multipart;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;

public class SendMail {

    public static String getLogoBase64() throws Exception {
        byte[] fileContent = Files.readAllBytes(
                Paths.get("src/main/resources/images/app_logo.png")
        );

        return Base64.getEncoder().encodeToString(fileContent);
    }


    private static String getMailBody() throws Exception {

        // App version
        String jenkinsAppVersion = System.getProperty("AppVersion", "UNKNOWN_VERSION");

        // Total test cases
        int totalTestCases = ExtentReportListener.passedCount.get()
                + ExtentReportListener.failedCount.get()
                + ExtentReportListener.skippedCount.get();

        // Calculate execution time
        long executionMillis = Base.endTimeMillis - Base.startTimeMillis;

        long seconds = (executionMillis / 1000) % 60;
        long minutes = (executionMillis / (1000 * 60)) % 60;
        long hours = (executionMillis / (1000 * 60 * 60));

        String totalExecutionTime =
                STR."\{hours} hours, \{minutes} minutes, \{seconds} seconds";

        // Logo Base64
        String logoBase64 = getLogoBase64();

        // Blue Horizontal Line
        // logo
        // Header Row
        // Data Row
        // Signature Section
        // Logo
        // Right side text
        return STR."<html><body style='font-family: Arial;'><p>Hi <b>Team</b>,</p><p>This automation test suite has been executed on the machine: <b>\{getMachineName()}</b>. Please find the summary of the test results below:</p><table border='1' cellpadding='8' cellspacing='0' style='border-collapse: collapse; width: 100%;'><tr><hr style='border: 0; height: 3px; background-color: #138fed; margin-top: 10px; margin-bottom: 20px;'></tr><div style='text-align: center; padding-top: 10px; padding-bottom: 10px;'><img src='data:image/png;base64,\{logoBase64}' width='30' style='vertical-align: middle;'/>&nbsp;&nbsp;<span style='font-size: 20px; font-weight: bold; color: #333333; vertical-align: middle;'>Test Automation Results</span></div><tr style='background-color: #138fed; color: white; text-align: center;'><th>Start Time (IST)</th><th>End Time (IST)</th><th>Test Suite Name</th><th>Workspace</th><th>Passed</th><th>Failed</th><th>Skipped</th></tr><tr style='text-align: center;'><td>\{Base.timesp}</td><td>\{Base.endTime}</td><td>TestGenerator_Service_Sanity</td><td>workspaceName</td><td style='color: green; font-weight: bold;'>\{ExtentReportListener.passedCount}</td><td style='color: red; font-weight: bold;'>\{ExtentReportListener.failedCount}</td><td style='color: blue; font-weight: bold;'>\{ExtentReportListener.skippedCount}</td></tr></table><br/><p><b>Application URL:</b> \{Base.baseUrl}</p><p><b>Total number of test cases:</b> \{totalTestCases}</p><p><b>About Execution:</b> <I>This sanity suite execution performed on Latest build of # </I><b>\{jenkinsAppVersion}</b></p><p><b>Total Execution Time:</b> \{totalExecutionTime}</p><p><b>Note:</b> This email was sent automatically by <span style='color:#138fed; font-weight:bold;'>NextGen iceDQ system</span>. Please do not reply.</p><br/><br/><p style='font-family: Arial; color: #808080; font-size: 15px; font-weight: bold;'>Thanks & Regards,</p><table style='border-collapse: collapse; font-family: Arial;'><tr><td style='padding-right: 15px;'><img src='data:image/png;base64,\{logoBase64}' width='50' height='50'/></td><td style='border-left: 3px solid #f4c20d; padding-left: 15px;'><span style='font-size: 15px; color: #2F5FD0; font-weight: bold;'>QA <i>Automation</i></span><span style='font-size: 15px; font-weight: bold;'> Team</span><br/><span style='font-size: 9px; color: #555;'><i>Rethink Data Reliability!</i></span></td></tr></table></body></html>";
    }

    public static void sendExecutionReport(String reportPath) {

        if (reportPath == null || reportPath.isEmpty()) {
            System.out.println("❌ Report path is null or empty, email not sent.");
            return;
        }

        File reportFile = new File(reportPath);
        if (!reportFile.exists() || !reportFile.isFile()) {
            System.out.println(STR."❌ Report file does not exist: \{reportPath}");
            return;
        }

        ConfigReader mailConfig = new ConfigReader();

        String fromEmail = mailConfig.mailUserName();
        String toEmail = mailConfig.mailTo();
        String ccEmail = mailConfig.mailCc();

        Properties props = new Properties();
        props.put("mail.smtp.host", mailConfig.emailHost());
        props.put("mail.smtp.port", mailConfig.mailPort());
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(mailConfig.mailUserName(), mailConfig.mailPassword());
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(fromEmail));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
            message.setRecipients(Message.RecipientType.CC, InternetAddress.parse(ccEmail));
            message.setSubject("NextGen iceDQ Test Automation Report : " + "TEST GENERATOR Service : " + "Test Generator-Sanity Suite");

            // Email body (HTML)
            MimeBodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setContent(getMailBody(), "text/html");

            // Attachment
            MimeBodyPart attachmentPart = new MimeBodyPart();
            DataSource source = new FileDataSource(reportFile);
            attachmentPart.setDataHandler(new DataHandler(source));
            attachmentPart.setFileName(reportFile.getName()); // Use actual file name

            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart);
            multipart.addBodyPart(attachmentPart);

            message.setContent(multipart);

            Transport.send(message);
            System.out.println("📧 Email sent successfully!");

        } catch (Exception e) {
            System.err.println(STR."Error: \{e.getMessage()}");
        }
    }

    public static String getLatestExtentReportPath() {
        String reportsFolder = STR."\{System.getProperty("user.dir")}\{File.separator}reports\{File.separator}BUILD";
        File folder = new File(reportsFolder);
        if (!folder.exists() || !folder.isDirectory()) {
            System.out.println(STR."❌ Reports folder does not exist: \{reportsFolder}");
            return null;
        }

        File[] files = folder.listFiles((_, name) -> name.endsWith(".html"));

        if (files == null || files.length == 0) {
            System.out.println(STR."❌ No ExtentReport HTML files found in: \{reportsFolder}");
            return null;
        }

        Arrays.sort(files, Comparator.comparingLong(File::lastModified).reversed());
        return files[0].getAbsolutePath();
    }

    /** Returns the hostname of the current machine, or "UNKNOWN_HOST" on failure. */
    private static String getMachineName() {
        try {
            return InetAddress.getLocalHost().getHostName();
        } catch (Exception e) {
            return "UNKNOWN_HOST";
        }
    }
}
