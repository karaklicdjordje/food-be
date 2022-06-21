package com.example.demo.controllers;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.demo.dto.LoginDTO;
import com.example.demo.dto.RegistrationDTO;
import com.example.demo.dto.UserDTO;
import com.example.demo.dto.UserTokenStateDTO;
import com.example.demo.entities.User;
import com.example.demo.factories.UserFactory;
import com.example.demo.security.TokenUtils;
import com.example.demo.services.UserService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping(value = "/auth", produces = MediaType.APPLICATION_JSON_VALUE)
public class AuthController {

	@Autowired
	private TokenUtils tokenUtils;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UserFactory userFactory;
	
	@GetMapping("/get")
	public String get() {
		return "Prolazi";
	}

	@PostMapping("/login")
	@Operation(summary = "Login for users", description = "Login for users by email and password")
	public ResponseEntity<UserTokenStateDTO> createAuthenticationToken(@RequestBody LoginDTO loginDTOreq,
			HttpServletResponse response) {

		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(loginDTOreq.getEmail(),
						loginDTOreq.getPassword()));

		// Ubaci korisnika u trenutni security kontekst
		SecurityContextHolder.getContext().setAuthentication(authentication);

		// Kreiraj token za tog korisnika
		User user = (User) authentication.getPrincipal();
		System.out.println( user.getEmail() + "-----------------");
		String jwt = tokenUtils.generateToken(user.getUsername(), user.getRole().toString());
		Long expiresIn = tokenUtils.getExpiredIn();

		// Vrati token kao odgovor na uspesnu autentifikaciju
		return ResponseEntity.ok(new UserTokenStateDTO(jwt, expiresIn));
	}
	
	
	@PostMapping("/signup")
	@Operation(summary = "Registration for users", description = "Registration for users")
	public ResponseEntity<HttpStatus> addUser(@RequestBody RegistrationDTO registrationDTO) {
		
		System.out.println(registrationDTO);
		
		userFactory.createUser(registrationDTO);
		
		return new ResponseEntity<HttpStatus>(HttpStatus.CREATED);
	}
//	//@PreAuthorize("hasRole('USER')")
//	@PostMapping("/change-password")
//	public ResponseEntity<?> changePassword(@RequestBody PasswordChanger passwordChanger, Principal p, Authentication auths) {
//		System.out.println(auths.getAuthorities() + "----------------");
//		System.out.println(p.getName() + "TEST ---------");
//		System.out.println(SecurityContextHolder.getContext());
//
//		// Kreiraj token za tog korisnika 
//	
//		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//		String userEmail = auth.getPrincipal().toString();
//		System.out.println("email: " + userEmail);
//		userService.changePassword(passwordChanger.oldPassword, passwordChanger.newPassword);
//
//		Map<String, String> result = new HashMap();
//		result.put("result", "success");
//		return ResponseEntity.accepted().body(result);
//	}
//
//	static class PasswordChanger {
//		public String oldPassword;
//		public String newPassword;
//	}
	
}
