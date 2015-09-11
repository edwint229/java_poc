package org.edwin.vote.util;

import java.security.GeneralSecurityException;
import java.util.Properties;

import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sun.mail.util.MailSSLSocketFactory;

public class MailSender {
	private static Logger logger = LoggerFactory.getLogger(MailSender.class);
	private static Session session;

	static {
		try {
			Properties props = System.getProperties();
			props.put("mail.smtp.host", "smtp.qq.com");
			props.put("mail.smtp.port", "465");
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.connectiontimeout", "10000");
			props.put("mail.smtp.timeout", "10000");

			MailSSLSocketFactory sf = new MailSSLSocketFactory();
			sf.setTrustAllHosts(true);
			props.put("mail.smtp.ssl.enable", "true");
			props.put("mail.smtp.ssl.socketFactory", sf);

			session = Session.getInstance(props, new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					try {
//						return new PasswordAuthentication("jinsheng.tang@qq.com", SecurityUtil.decrypt("6aOKhu7PfbvWJYPgVG1bd3=="));
						return new PasswordAuthentication("jinsheng.tang@qq.com", "xxxx");
					} catch (Exception e1) {
						logger.error(e1.getMessage(), e1);
					}

					return null;
				}
			});

		} catch (GeneralSecurityException e) {
			logger.error(e.getMessage(), e);
		}
	}

	/**
	 * Sent Email by String Content
	 * 
	 * @param mailTo
	 * @param subject
	 * @param bodyContent
	 *            html format
	 */
	public static void send(String mailTo, String subject, String bodyContent) {
		try {
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress("jinsheng.tang@qq.com"));
			message.setSubject(subject);
			message.addRecipients(Message.RecipientType.TO, InternetAddress.parse(mailTo));
			message.setContent(getMailBody(bodyContent));

			Transport.send(message);
		} catch (Exception e) {
			logger.error("Sent Email Failed!!!");
			logger.error(e.getMessage(), e);
		}
	}

	/**
	 * get body content
	 * 
	 * @param bodyContent
	 * @return
	 * @throws Exception
	 */
	private static Multipart getMailBody(String bodyContent) throws Exception {
		Multipart multipart = new MimeMultipart();
		multipart.addBodyPart(getBodyPart(bodyContent));
		return multipart;
	}

	/**
	 * Get Body Content
	 * 
	 * @param bodyContent
	 * @return
	 * @throws Exception
	 */
	private static BodyPart getBodyPart(String bodyContent) throws Exception {
		BodyPart bodyPart = new MimeBodyPart();
		bodyPart.setContent(bodyContent, "text/html;charset=utf8");
		return bodyPart;
	}

}
