package com.maua.backendMetro.Service;

import com.maua.backendMetro.domain.entity.QRCode;
import org.springframework.stereotype.Service;

@Service
public interface QRCodeService {

    QRCode generateAndSaveQRCodeWithExtintorId(String qrText);

}
