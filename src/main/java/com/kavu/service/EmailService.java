package com.kavu.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String fromEmail;

    public void sendProjectAssignedEmail(String toEmail, String assignee, String projectName, String deadline) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            helper.setFrom(fromEmail);
            helper.setTo(toEmail);
            helper.setSubject("📢 New Project Assigned: " + projectName);

            String html = """
                    <div style="font-family: Arial, sans-serif; color: #333;">
                        <h2 style="color: #2a9d8f;">Hello %s,</h2>
                        <p>You have been assigned a new project:</p>
                        <p><strong>%s</strong></p>
                        <p><b>Deadline:</b> %s</p>
                        <p>Please check your <a href="http://localhost:3000" style="color:#1d3557;">Kavu Proman Dashboard</a> for details.</p>
                        <br>
                        <p>Best Regards,<br><b>Kavu Proman Team</b></p>
                    </div>
                    """.formatted(assignee, projectName, deadline);

            helper.setText(html, true);
            mailSender.send(message);

            System.out.println("✅ HTML Email sent successfully to " + toEmail);

        } catch (MessagingException e) {
            throw new RuntimeException("❌ Failed to send email", e);
        }
    }
}
