package com.maua.backendMetro.rest.controller;

import com.maua.backendMetro.Service.QRCodeService;
import com.maua.backendMetro.domain.entity.QRCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController("api/qrCode")
public class QRCodeController {

    private QRCodeService qrCodeService;

    @GetMapping("/saveQRCode")
    public ResponseEntity<QRCode> saveQRCode(@RequestParam String qrText) {
        QRCode savedQRCode = qrCodeService.generateAndSaveQRCodeWithExtintorId(qrText);
        return ResponseEntity.ok(savedQRCode);
    }
}
