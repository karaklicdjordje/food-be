package com.example.demo.controllers;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.services.EmailService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/email")
@Tag(name = "Email controller", description = "Manage for sending email to users")
public class EmailController {

	@Autowired
	EmailService emailService;
	
	@PostMapping
	@Operation(summary = "Send email all users", description = "Sending email with daily many to users.",
	responses = {
			@ApiResponse(responseCode = "200", description = "Email succesfully sent.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = String.class))),
			@ApiResponse(responseCode = "404", description = "Email doesn't exist.")
	})
	public ResponseEntity<String> sendMail() throws MessagingException {
		emailService.sendMail();
		return new ResponseEntity<String>("Uspesno poslat mail", HttpStatus.OK);
	}
}
