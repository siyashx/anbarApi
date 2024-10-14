package com.codesupreme.anbarapi.api.sened.controller;

import com.codesupreme.anbarapi.dto.sened.SenedDto;
import com.codesupreme.anbarapi.service.impl.sened.SenedServiceImpl;
import jakarta.persistence.OptimisticLockException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v4/sened")
public class SenedController {

    private final SenedServiceImpl senedServiceImpl;


    public SenedController(SenedServiceImpl senedServiceImpl) {
        this.senedServiceImpl = senedServiceImpl;
    }

    // List
    @GetMapping
    public ResponseEntity<List<SenedDto>> getAllSeneds() {
        List<SenedDto> senedDtoList = senedServiceImpl.getAllSened();
        return ResponseEntity.ok(senedDtoList);
    }

    // Create
    @PostMapping
    public ResponseEntity<SenedDto> createSened(@RequestBody SenedDto senedDto) {
        SenedDto createdSened = senedServiceImpl.createSened(senedDto);
        return ResponseEntity.ok(createdSened);
    }

    // Id
    @GetMapping("/{senedId}")
    public ResponseEntity<SenedDto> findSenedById(@PathVariable Long senedId) {
        SenedDto senedDto = senedServiceImpl.getSenedById(senedId);
        if (senedDto != null) {
            return ResponseEntity.ok(senedDto);
        }
        return ResponseEntity.notFound().build();
    }

    // Update
    @PutMapping("/{senedId}")
    public ResponseEntity<?> updateSened(
            @PathVariable Long senedId,
            @RequestBody SenedDto senedDto) {

        try {
            SenedDto updatedSened = senedServiceImpl.updateSened(senedId, senedDto);
            if (updatedSened != null) {
                return ResponseEntity.ok(updatedSened);
            }
            return ResponseEntity.notFound().build();
        } catch (OptimisticLockException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("Sened has been modified by another user. Please try again.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Delete
    @DeleteMapping("/{senedId}")
    public ResponseEntity<String> deleteSened(@PathVariable Long senedId) {
        boolean deleted = senedServiceImpl.removeById(senedId);
        if (deleted) {
            return ResponseEntity.ok("Sened deleted successfully");
        }
        return ResponseEntity.notFound().build();
    }
}
