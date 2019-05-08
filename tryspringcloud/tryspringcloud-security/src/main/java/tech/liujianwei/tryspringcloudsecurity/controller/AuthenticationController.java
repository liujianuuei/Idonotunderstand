package tech.liujianwei.tryspringcloudsecurity.controller;

import tech.liujianwei.tryspringcloudsecurity.exception.EntityNotFoundException;
import tech.liujianwei.tryspringcloudsecurity.request.AuthenticationRequest;
import tech.liujianwei.tryspringcloudsecurity.response.JWTTokenResponse;
import tech.liujianwei.tryspringcloudsecurity.auth.AuthenticationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class AuthenticationController {

    private AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/login")
    public ResponseEntity<JWTTokenResponse> createCustomer(@RequestBody AuthenticationRequest request) {
        return new ResponseEntity<>(authenticationService.generateJWTToken(request.getUsername(), request.getPassword()), HttpStatus.OK);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<String> handleEntityNotFoundException(EntityNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }
}
