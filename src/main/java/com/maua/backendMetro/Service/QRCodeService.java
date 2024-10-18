package com.maua.backendMetro.Service;

import com.google.zxing.WriterException;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public interface QRCodeService {

    byte[] generateAndSaveQRCodeWithExtintorId(String text, int width, int height) throws IOException, WriterException;

}
