package com.tdm.server.application.problem.service;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.stereotype.Service;

import com.tdm.domain.model.problem.ProblemId;

@Service
public class DefaultInvitationService implements InvitationService {

	public void inviteExperts(ProblemId problemId, List<String> emails,
			String msgBody) {

		Properties props = new Properties();
		Session session = Session.getDefaultInstance(props, null);
		for (String address : emails) {

			try {
				Message msg = new MimeMessage(session);
				msg.setFrom(new InternetAddress("pawel@dolecinski.pl",
						"TDMTool"));
				msg.addRecipient(Message.RecipientType.TO, new InternetAddress(
						address));
				msg.setSubject("We need your help to make decision");
				msg.setText(msgBody);
				Transport.send(msg);

			} catch (AddressException e) {
				e.printStackTrace();
			} catch (MessagingException e) {
				e.printStackTrace();
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
	}
}