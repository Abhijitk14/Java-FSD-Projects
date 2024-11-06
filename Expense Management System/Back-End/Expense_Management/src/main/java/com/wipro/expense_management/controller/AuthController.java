package com.wipro.expense_management.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.wipro.expense_management.services.MyUserDetailService;
import com.wipro.expense_management.webtokan.AuthResponse;
import com.wipro.expense_management.webtokan.JwtService;
import com.wipro.expense_management.webtokan.LoginForm;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class AuthController {
	
	@Autowired
    private AuthenticationManager authenticationManager;
	
    @Autowired
    private JwtService jwtService;
    
    @Autowired
    private MyUserDetailService myUserDetailService;

    /**
     * authenticateAndGetToken controller method is used to authenticate user
     * @param loginForm
     * @return
     */
    @PostMapping("/authenticate")
    public ResponseEntity<?> authenticateAndGetToken(@RequestBody LoginForm loginForm) {
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    loginForm.username(), loginForm.password()
            ));
            if (authentication.isAuthenticated()) {
                String token = jwtService.generateToken(myUserDetailService.loadUserByUsername(loginForm.username()));
                System.out.println(token);
                return ResponseEntity.ok(new AuthResponse(token)); // Return JSON with token
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Authentication failed");
        }
    }
}
