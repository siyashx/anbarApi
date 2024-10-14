package com.codesupreme.anbarapi.api.satici.controller;

import com.codesupreme.anbarapi.dto.satici.SaticiDto;
import com.codesupreme.anbarapi.service.impl.satici.SaticiServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v4/satici")
public class SaticiController {
    
    private final SaticiServiceImpl saticiServiceImpl;


    public SaticiController(SaticiServiceImpl saticiServiceImpl) {
        this.saticiServiceImpl = saticiServiceImpl;
    }

    // List
    @GetMapping
    public List<SaticiDto> getAllSatici() {
        return saticiServiceImpl.getAllSatici();
    }

    // Id
    @GetMapping("/{saticiId}")
    public ResponseEntity<?> getSaticiById(@PathVariable("saticiId") Long saticiId) {
        SaticiDto saticiDto = saticiServiceImpl.getSaticiById(saticiId);
        if (saticiDto != null) {
            return ResponseEntity.ok(saticiDto);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Satici doesn't exist with given id..");
    }

    // Update
    @PutMapping("/{saticiId}")
    public ResponseEntity<?> updateSatici(
            @PathVariable("saticiId") Long id,
            @RequestBody SaticiDto saticiDto) {
        SaticiDto updatedSatici = saticiServiceImpl.updateSatici(id, saticiDto);
        if (updatedSatici != null) {
            return ResponseEntity.ok(updatedSatici);
        }
        return ResponseEntity.notFound().build();
    }

    // Delete
    @DeleteMapping("/{saticiId}")
    public ResponseEntity<?> deleteSaticiById(@PathVariable("saticiId") Long saticiId) {
        saticiServiceImpl.deleteSatici(saticiId);
        return ResponseEntity.ok().build();
    }
}
