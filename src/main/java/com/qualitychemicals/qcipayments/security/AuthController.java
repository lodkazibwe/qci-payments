package com.qualitychemicals.qcipayments.security;

import com.qualitychemicals.qcipayments.exceptions.InvalidValuesException;
import com.qualitychemicals.qcipayments.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/authenticate")
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    MyUserDetailsService myUserDetailsService;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    UserService userService;

    @PostMapping("get/token")
    public ResponseEntity<?> createAuthToken(@Valid @RequestBody AuthRequest authRequest){

            try {
                authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(authRequest.getUserName(), authRequest.getPassword())
                );
            } catch (InvalidValuesException e) {
                throw new InvalidValuesException("Incorrect profile name or password");
            }
            UserDetails userDetails = myUserDetailsService.loadUserByUsername(authRequest.getUserName());

            final String jwt = jwtUtil.generateToken(userDetails);

            return new ResponseEntity<>(new AuthResponse(jwt), HttpStatus.OK);
        }



    @PutMapping("/requestPin/{contact}")//admin
    public ResponseEntity<?> requestPin(@PathVariable String contact){
        userService.requestPin(contact);
        return new ResponseEntity<>("success", HttpStatus.OK);
    }

    @PutMapping("/createPass")//authenticated
    public ResponseEntity<?> createPass(@Valid @RequestBody AuthRequest authRequest){
        userService.createPass(authRequest.getPassword());
        return new ResponseEntity<>("success", HttpStatus.OK);

    }
}
