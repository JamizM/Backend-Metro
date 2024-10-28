package com.maua.backendMetro.Service.Impl;

import com.maua.backendMetro.Service.QRCodeService;
import com.maua.backendMetro.domain.entity.Extinguisher;
import com.maua.backendMetro.domain.entity.HistoricManutention;
import com.maua.backendMetro.domain.repository.Extinguishers;
import com.maua.backendMetro.domain.repository.HistoricManutentions;
import com.maua.backendMetro.domain.repository.Localizations;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Qualifier;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class ExtinguisherServiceImplTest {

    @Mock
    private Extinguishers extinguishers;

    @Mock
    private Localizations localizations;

    @Mock
    private HistoricManutentions historicManutentions;

    @Qualifier("QRCodeServiceImpl")
    @Mock
    private QRCodeService qrCodeService;

    @InjectMocks
    private ExtinguisherServiceImpl extinguisherService;

    //testes ja feitos
    //findExtinguisherByLocalizationDetails
    //findExtinguisherByExtinguisherStatus
    //findExtinguisherByExtinguisherType - vem do repository e é aplicado no controller

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void verifyExpirationDateExtinguisherAndAlert() {
        Extinguisher expiredExtinguisher = new Extinguisher();

        expiredExtinguisher.setId("1");
        expiredExtinguisher.setExpirationDate(LocalDate.now().minusDays(1));

        Extinguisher validExtinguisher = new Extinguisher();
        validExtinguisher.setId("2");
        validExtinguisher.setExpirationDate(LocalDate.now().plusMonths(13));

        when(extinguishers.findAll()).thenReturn(List.of(expiredExtinguisher, validExtinguisher));

        List<String> messages = extinguisherService.verifyExpirationDateExtinguisherAndAlert();

        assertEquals(2, messages.size());
        assertEquals("The Extinguisher with ID 1 has expired.", messages.get(0));
        assertEquals("The Extinguisher with ID 2 is within the expiration date.", messages.get(1));
    }

    @Test
    void scheduleRegularInspectionsOfExtinguishers() {
        Extinguisher extinguisher = new Extinguisher();

        extinguisher.setId("1");
        extinguisher.setNextInspection(LocalDate.now());

        when(extinguishers.findById("1")).thenReturn(Optional.of(extinguisher));

        List<String> messages = extinguisherService.scheduleRegularInspectionsOfExtinguishers("1");

        assertEquals(1, messages.size());
        assertEquals("Scheduled next inspection for extinguisher ID: " + extinguisher.getId() + " on "
                + LocalDate.now().plusMonths(12), messages.getFirst());
        assertEquals(LocalDate.now().plusMonths(12), extinguisher.getNextInspection());
    }

    @Test
    void generateQRCodeForExtinguisher() throws Exception {
        String extinguisherId = "1";
        Extinguisher extinguisher = new Extinguisher();
        extinguisher.setId(extinguisherId);

        HistoricManutention manutention = new HistoricManutention();
        manutention.setMaintenanceDate(LocalDate.now());
        manutention.setDescription("Test Description");
        manutention.setResponsible("Test Responsible");

        when(extinguishers.findById(extinguisherId)).thenReturn(Optional.of(extinguisher));
        when(historicManutentions.findHistoricManutentionByExtinguisherId(extinguisherId)).thenReturn(List.of(manutention));
        when(qrCodeService.generateAndSaveQRCodeWithExtintorId(anyString(), anyInt(), anyInt())).thenReturn(new byte[0]);

        byte[] qrCode = extinguisherService.generateQRCodeForExtinguisher(extinguisherId);

        assertNotNull(qrCode);
        verify(extinguishers).findById(extinguisherId);
        verify(historicManutentions).findHistoricManutentionByExtinguisherId(extinguisherId);
        verify(qrCodeService).generateAndSaveQRCodeWithExtintorId(anyString(), eq(350), eq(350));
    }
}