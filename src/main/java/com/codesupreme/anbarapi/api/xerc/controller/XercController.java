package com.codesupreme.anbarapi.api.xerc.controller;

import com.codesupreme.anbarapi.dto.xerc.XercDto;
import com.codesupreme.anbarapi.service.impl.xerc.XercServiceImpl;
import jakarta.persistence.OptimisticLockException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v4/xerc")
public class XercController {

    private final XercServiceImpl xercServiceImpl;


    public XercController(XercServiceImpl xercServiceImpl) {
        this.xercServiceImpl = xercServiceImpl;
    }

    // List
    @GetMapping
    public ResponseEntity<List<XercDto>> getAllXercs() {
        List<XercDto> xercDtoList = xercServiceImpl.getAllXerc();
        return ResponseEntity.ok(xercDtoList);
    }

    // Create
    @PostMapping
    public ResponseEntity<XercDto> createXerc(@RequestBody XercDto xercDto) {
        XercDto createdXerc = xercServiceImpl.createXerc(xercDto);
        return ResponseEntity.ok(createdXerc);
    }

    // Id
    @GetMapping("/{xercId}")
    public ResponseEntity<XercDto> findXercById(@PathVariable Long xercId) {
        XercDto xercDto = xercServiceImpl.getXercById(xercId);
        if (xercDto != null) {
            return ResponseEntity.ok(xercDto);
        }
        return ResponseEntity.notFound().build();
    }

    // Update
    @PutMapping("/{xercId}")
    public ResponseEntity<?> updateXerc(
            @PathVariable Long xercId,
            @RequestBody XercDto xercDto) {

        try {
            XercDto updatedXerc = xercServiceImpl.updateXerc(xercId, xercDto);
            if (updatedXerc != null) {
                return ResponseEntity.ok(updatedXerc);
            }
            return ResponseEntity.notFound().build();
        } catch (OptimisticLockException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("Xerc has been modified by another user. Please try again.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Delete
    @DeleteMapping("/{xercId}")
    public ResponseEntity<String> deleteXerc(@PathVariable Long xercId) {
        boolean deleted = xercServiceImpl.removeById(xercId);
        if (deleted) {
            return ResponseEntity.ok("Xerc deleted successfully");
        }
        return ResponseEntity.notFound().build();
    }
}
