package com.example.clothes.utils;

import com.example.clothes.entities.ConfirmationToken;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.experimental.UtilityClass;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import java.io.UnsupportedEncodingException;

@UtilityClass
public class EmailUtility {

    public void sendVerificationEmail(ConfirmationToken confirmationToken, JavaMailSender mailSender, String applicationUrl)
            throws MessagingException, UnsupportedEncodingException {
        String toAddress = confirmationToken.getUser().getEmail();
        String fromAddress = "elif.clothing.shop@gmail.com";
        String senderName = "Elif Clothing Store";
        String subject = "Please complete registration";
        String content = "Dear [[name]],<br>"
                + "To confirm your account, please click here :<br>"
                + "<h3><a href=\"[[URL]]\" target=\"_self\">VERIFY</a></h3>"
                + "Thank you,<br>"
                + "Elif Clothing Store.";

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setFrom(fromAddress, senderName);
        helper.setTo(toAddress);
        helper.setSubject(subject);

        content = content.replace("[[name]]", confirmationToken.getUser().getFirstname());
        String verifyURL = applicationUrl + "/api/v1/auth/confirm-account/" + confirmationToken.getConfirmationToken();
        content = content.replace("[[URL]]", verifyURL);

        helper.setText(content, true);
        mailSender.send(message);

        System.out.println(content);

    }

}