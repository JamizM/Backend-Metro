package com.maua.backendMetro.Service.Impl;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.maua.backendMetro.Service.QRCodeService;
import com.maua.backendMetro.domain.entity.QRCode;
import com.maua.backendMetro.domain.repository.QRCodeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

@RequiredArgsConstructor
@Service
public class QRCodeServiceImpl implements QRCodeService {

    @Override
    public byte[] generateAndSaveQRCodeWithExtintorId(String text, int width, int height) throws IOException, WriterException {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, width, height);

        ByteArrayOutputStream pngOutputStream = new ByteArrayOutputStream();
        MatrixToImageWriter.writeToStream(bitMatrix, "PNG", pngOutputStream);
        return pngOutputStream.toByteArray();
    }
}
