package com.maua.backendMetro.Service;

import com.google.zxing.WriterException;
import com.maua.backendMetro.domain.entity.QRCode;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public interface QRCodeService {

    byte[] generateAndSaveQRCodeWithExtintorId(String text, int width, int height) throws IOException, WriterException;

    QRCode save(QRCode qrCode);
}
