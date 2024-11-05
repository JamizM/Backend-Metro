package com.maua.backendMetro.Service.Impl;

import com.maua.backendMetro.Service.QRCodeService;
import com.maua.backendMetro.domain.entity.Extinguisher;
import com.maua.backendMetro.domain.entity.HistoricManutention;
import com.maua.backendMetro.domain.repository.Extinguishers;
import com.maua.backendMetro.domain.repository.HistoricManutentions;
import com.maua.backendMetro.domain.repository.Localizations;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
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
    @DisplayName("Should return a message when extinguisher is within the expiration date or has expired")
    void testVerifyExpirationDateExtinguisherAndAlert_Success() {
        Extinguisher expiredExtinguisher = new Extinguisher();
        expiredExtinguisher.setId("1");
        expiredExtinguisher.setExpirationDate(LocalDate.now().minusDays(1));

        Extinguisher validExtinguisher = new Extinguisher();
        validExtinguisher.setId("2");
        validExtinguisher.setExpirationDate(LocalDate.now().plusMonths(4));

        when(extinguishers.findAll()).thenReturn(List.of(expiredExtinguisher, validExtinguisher));

        List<String> messages = extinguisherService.verifyExpirationDateExtinguisherAndAlert();

        assertEquals(2, messages.size());
        assertEquals("O extintor com identificador: 1 está expirado.", messages.get(0));
        assertEquals("O extintor com identificador: 2 está próximo de vencer: " + validExtinguisher.getExpirationDate(), messages.get(1));
    }

    @Test
    @DisplayName("Should return a message when no extinguishers are found")
    void testVerifyExpirationDateExtinguisherAndAlert_Fail() {
        when(extinguishers.findAll()).thenReturn(List.of());

        List<String> messages = extinguisherService.verifyExpirationDateExtinguisherAndAlert();

        assertEquals(1, messages.size());
        assertEquals("Nenhum extintor encontrado.", messages.get(0));
    }

    @Test
    @DisplayName("Should return a message when extinguisher is scheduled for inspection")
    void testScheduleRegularInspectionsOfExtinguishers_Success() {
        Extinguisher extinguisher = new Extinguisher();
        extinguisher.setId("1");
        extinguisher.setNextInspection(LocalDate.now().plusMonths(1));

        when(extinguishers.findById("1")).thenReturn(Optional.of(extinguisher));

        int months = 12;
        List<String> messages = extinguisherService.scheduleRegularInspectionsOfExtinguishers("1", months);

        assertEquals(1, messages.size());
        assertEquals("Próxima inspeção agendada para o extintor com identificador: " + extinguisher.getId() + " está marcada para dia "
                + LocalDate.now().plusMonths(13), messages.get(0));
        assertEquals(LocalDate.now().plusMonths(13), extinguisher.getNextInspection());
    }

    @Test
    @DisplayName("Should return a message when extinguisher is not due for inspection")
    void testScheduleRegularInspectionsOfExtinguishers_Fail() {
        Extinguisher extinguisher = new Extinguisher();
        extinguisher.setId("1");
        extinguisher.setNextInspection(LocalDate.now().minusMonths(1));

        when(extinguishers.findById("1")).thenReturn(Optional.of(extinguisher));

        int months = 12;
        List<String> messages = extinguisherService.scheduleRegularInspectionsOfExtinguishers("1", months);

        assertEquals(1, messages.size());
        assertEquals("Extintor com o identificador: " + extinguisher.getId() + " não é devido para inspeção.", messages.get(0));
    }

    @Test
    @DisplayName("Should return a qrcode for the extinguisher to see historic manutentions")
    void testGenerateQRCodeForExtinguisher_Success() throws Exception {
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

    @Test
    @DisplayName("Should not return a qrcode for the extinguisher when the extinguisher does not exist")
    void testGenerateQRCodeForExtinguisher_Fail() throws Exception {
        String extinguisherId = "1";

        when(extinguishers.findById(extinguisherId)).thenReturn(Optional.empty());

        Exception exception = assertThrows(RuntimeException.class, () -> {
            extinguisherService.generateQRCodeForExtinguisher(extinguisherId);
        });

        assertEquals("Extintor não encontrado com ID: " + extinguisherId, exception.getMessage());
        verify(extinguishers).findById(extinguisherId);
    }
}