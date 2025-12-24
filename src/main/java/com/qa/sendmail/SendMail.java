package com.qa.sendmail;

import java.io.File;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Properties;

import com.qa.base.Base;
import com.qa.config.ConfigReader;
import com.qa.extentreportlistener.ExtentManager;
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

	private static String fromEmail;
	private static String toEmail;
	private static String ccEmail;
	

	private static String getMailBody() {
		return "<html><body style='font-family: Arial;'>" + "<p>Hi Team,</p>"
				+ "<p>Automation execution has completed. Please find the Extent Report attached.</p>"
				+ "<table border='1' cellpadding='8' cellspacing='0' style='border-collapse: collapse; width: 100%;'>"

				// Header row with blue background and white text
				+ "<tr style='background-color: #2a4d69; color: white; text-align: center;'>"
				+ "<th>Start Time (IST)</th>" + "<th>End Time (IST)</th>" + "<th>Test Suite Name</th>"
				+ "<th>Workspace</th>" + "<th>Passed</th>" + "<th>Failed</th>" + "<th>Skipped</th>" + "</tr>"

				// Data row with colored text in Passed, Failed, Skipped columns
				+ "<tr style='text-align: center;'>" + "<td>" + Base.timesp + "</td>" + "<td>" + Base.endTime + "</td>"
				+ "<td>Test Execution</td>" + "<td>TestGenerator_Sanity</td>"
				+ "<td style='color: green; font-weight: bold;'>" + ExtentReportListener.passedCount + "</td>"
				+ "<td style='color: red; font-weight: bold;'>" + ExtentReportListener.failedCount + "</td>"
				+ "<td style='color: blue; font-weight: bold;'>" + ExtentReportListener.skippedCount + "</td>" + "</tr>"

				+ "</table>" + "<br/>" + "<p>Regards,<br/>Ashwin Doye</p>" + "</body></html>";
	}


	public static void sendExecutionReport(String reportPath) {

		if (reportPath == null || reportPath.isEmpty()) {
			System.out.println("❌ Report path is null or empty, email not sent.");
			return;
		}

		File reportFile = new File(reportPath);
		if (!reportFile.exists() || !reportFile.isFile()) {
			System.out.println("❌ Report file does not exist: " + reportPath);
			return;
		}

		ConfigReader mailconfig = new ConfigReader();

		fromEmail = mailconfig.mailUserName();
		toEmail = mailconfig.mailTo();
		ccEmail = mailconfig.mailCc();

		Properties props = new Properties();
		props.put("mail.smtp.host", mailconfig.emailHost());
		props.put("mail.smtp.port", mailconfig.mailPort());
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");

		Session session = Session.getInstance(props, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(mailconfig.mailUserName(), mailconfig.mailPassword());
			}
		});

		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(fromEmail));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
			message.setRecipients(Message.RecipientType.CC, InternetAddress.parse(ccEmail));
			message.setSubject("Automation Execution Report");

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
			e.printStackTrace();
		}
	}

	public static String getLatestExtentReportPath() {
		String reportsFolder = System.getProperty("user.dir") + File.separator + "reports" + File.separator + "BUILD";
		File folder = new File(reportsFolder);
		if (!folder.exists() || !folder.isDirectory()) {
			System.out.println("❌ Reports folder does not exist: " + reportsFolder);
			return null;
		}

		// List all files starting with "ExtentReport" and ending with ".html"
		File[] files = folder.listFiles((dir, name) -> name.startsWith("ExtentReport") && name.endsWith(".html"));

		if (files == null || files.length == 0) {
			System.out.println("❌ No ExtentReport HTML files found in: " + reportsFolder);
			return null;
		}

		// Sort files by last modified date descending (newest first)
		Arrays.sort(files, Comparator.comparingLong(File::lastModified).reversed());

		// Return the absolute path of the newest file
		return files[0].getAbsolutePath();
	}
}
