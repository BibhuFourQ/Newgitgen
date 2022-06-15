package com.example.servingwebcontent;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.invoke.MethodHandles;
import java.util.Properties;

@ControllerAdvice
public class AirBrakeControllerAdvice {

    private static final Logger LOGGER = LogManager.getLogger(MethodHandles.lookup().lookupClass());

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public void genericException(Exception e) {
        test_AWS_Email(e);
    }

    public void test_AWS_Email(Exception e) {
        final String username = "AKIATEBKNH2WOWMBCEPN";
        final String password = "BB684RUrhf+h1XNTrpxMnlexvzi5fcqPBFOxE45AM6AT";
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);

        Properties prop = new Properties();
        prop.put("mail.smtp.host", "email-smtp.us-east-1.amazonaws.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true"); //TLS

        Session session = Session.getInstance(prop, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("OneBiller.unmonitored@four-q.com"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("ben.contreras@four-q.com,Daniel.Vasquez@four-q.com"));
            message.setSubject(e.getMessage());
            e.printStackTrace(pw);
            message.setText(sw.toString());

            Transport.send(message);

            System.out.println("Done");

        } catch (MessagingException ex) {
            LOGGER.error(ex);
        }
    }
}
