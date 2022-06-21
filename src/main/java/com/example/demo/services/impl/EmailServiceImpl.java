package com.example.demo.services.impl;



import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.example.demo.repositories.UserRepository;
import com.example.demo.services.EmailService;

@Service
public class EmailServiceImpl implements EmailService{

	@Autowired
    private JavaMailSender javaMailSender;
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public void sendMail() throws MessagingException {
		
		
        List<String> mails = userRepository.findAllEmail();
        for (String mail : mails) {
        	MimeMessage msg = javaMailSender.createMimeMessage();

            // true = multipart message
            MimeMessageHelper helper = new MimeMessageHelper(msg, true);
            
        	helper.setTo(mail);
        	
        	 helper.setSubject("Food Distribution");

             // default = text/plain
             //helper.setText("Check attachment for image!");

             // true = text/html
             helper.setText("<b>Poruka!<b> <br/> <h1>Vaša lozinka je: <b>d</b></h1>", true);

             //helper.addAttachment("my_photo.png", new ClassPathResource("android.png"));

             javaMailSender.send(msg);
        }
        
        
       
	}

}
