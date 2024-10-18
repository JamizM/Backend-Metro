package com.maua.backendMetro.Service.Impl;

import com.maua.backendMetro.Service.QRCodeService;
import com.maua.backendMetro.domain.entity.Extinguisher;
import com.maua.backendMetro.domain.entity.QRCode;
import com.maua.backendMetro.domain.repository.QRCodeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class QRCodeServiceImpl implements QRCodeService {

    private QRCodeRepository qrCodeRepository;

    @Override
    public QRCode generateAndSaveQRCodeWithExtintorId(String qrText) {
        QRCode qrCodeEntity = new QRCode();
        qrCodeEntity.setQrData(qrText);
        return qrCodeRepository.save(qrCodeEntity);
    }
}
