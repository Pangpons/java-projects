package com.example.spring_email;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.time.Year;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import reactor.core.publisher.Mono;

@SpringBootApplication
@RestController
public class SpringEmailApplication {

	@Autowired
	private final JavaMailSender javaMailSender;

	public SpringEmailApplication(JavaMailSender javaMailSender) {
		this.javaMailSender = javaMailSender;
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringEmailApplication.class, args);
	}

	@RequestMapping("/send-email")
	public Mono<String> sendEmail() throws IOException, MessagingException {
		String html = loadTemplate("templates/otp_template.html");

		String userName = "ppopokjf1223@gmail.com";
		String otp = String.format("%06d", new Random().nextInt(999999));
		String expiryMinutes = "5";

		html = html.replace("{{USER_NAME}}", userName)
                   .replace("{{OTP}}", otp)
                   .replace("{{EXPIRY_MINUTES}}", String.valueOf(expiryMinutes))
                   .replace("{{YEAR}}", String.valueOf(Year.now().getValue()));

		MimeMessage message = javaMailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, "utf-8");
		helper.setTo(userName);
		helper.setSubject("รหัสยืนยัน (OTP)");
		helper.setText(html, true);
        helper.setFrom("no-reply@example.com");

		javaMailSender.send(message);
		

		return Mono.just("Success");
	}

	private String loadTemplate(String path) throws IOException {
        ClassPathResource resource = new ClassPathResource(path);
        return Files.readString(resource.getFile().toPath(), StandardCharsets.UTF_8);
    }

}
