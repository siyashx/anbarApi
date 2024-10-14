package com.codesupreme.anbarapi.api.satici.controller;

import com.codesupreme.anbarapi.dto.satici.SaticiDto;
import com.codesupreme.anbarapi.model.LoginRequest;
import com.codesupreme.anbarapi.service.inter.satici.SaticiServiceInter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v3/login/satici")
public class SaticiLoginController {

    private final SaticiServiceInter saticiServiceInter;

    public SaticiLoginController(SaticiServiceInter saticiServiceInter) {
        this.saticiServiceInter = saticiServiceInter;
    }

    @PostMapping
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        String phoneNumber = loginRequest.getPhoneNumber();
        String password = loginRequest.getPassword();

        SaticiDto saticiDto = saticiServiceInter.findSaticiByPhoneNumber(phoneNumber);

        if (saticiDto != null && saticiDto.getPassword().equals(password)) {
            return ResponseEntity.ok(saticiDto);
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body("Invalid email, username or password");
    }
}
