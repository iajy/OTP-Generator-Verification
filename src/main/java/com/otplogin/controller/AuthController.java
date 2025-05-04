package com.otplogin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.otplogin.entity.OtpRequest;
import com.otplogin.entity.VerifyRequest;
import com.otplogin.service.OtpService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private OtpService otpService;

    @PostMapping("/send-otp")
    public ResponseEntity<?> sendOtp(@RequestBody OtpRequest request) {
        otpService.sendOtp(request.getEmail());
        return ResponseEntity.ok("OTP sent to your email");
    }

    @PostMapping("/verify-otp")
    public ResponseEntity<?> verifyOtp(@RequestBody VerifyRequest request) {
        boolean valid = otpService.verifyOtp(request.getEmail(), request.getOtp());
        return valid ? ResponseEntity.ok("OTP verified successfully")
                     : ResponseEntity.status(401).body("Invalid OTP");
    }
}
