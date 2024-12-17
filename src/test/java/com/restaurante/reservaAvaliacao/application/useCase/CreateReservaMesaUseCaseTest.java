package com.restaurante.reservaAvaliacao.application.useCase;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.restaurante.reservaAvaliacao.domain.entity.ReservaMesa;
import com.restaurante.reservaAvaliacao.domain.service.ReservaMesaDomainService;
import com.restaurante.reservaAvaliacao.infrastructure.persistence.entity.ReservaMesaEntity.StatusReserva;

class CreateReservaMesaUseCaseTest {

    @Mock
    private ReservaMesaDomainService service;

    @InjectMocks
    private CreateReservaMesaUseCase useCase;

    @BeforeEach
    public void setUp() {
        // Initialize the mocks and inject them into the use case
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testExecute_ShouldCallCriaReservaRestaurante() {
        // Arrange: Create the input data for the use case
        String nomeCliente = "João Silva";
        String documentoCliente = "123456789";
        LocalDate dataReserva = LocalDate.of(2024, 12, 17);
        Long seqRestaurante = 101L;

        CreateReservaMesaUseCase.Input input = new CreateReservaMesaUseCase.Input(
                nomeCliente, documentoCliente, dataReserva, seqRestaurante);

        // Act: Execute the use case
        useCase.execute(input);

        // Assert: Verify that the domain service method was called exactly once with the correct argument
        ArgumentCaptor<ReservaMesa> captor = ArgumentCaptor.forClass(ReservaMesa.class);
        verify(service, times(1)).criaReservaRestaurante(captor.capture());

        // Validate the captured argument
        ReservaMesa capturedReserva = captor.getValue();
        assert capturedReserva.getNomeCliente().equals(nomeCliente);
        assert capturedReserva.getDocumentoCliente().equals(documentoCliente);
        assert capturedReserva.getDataReserva().equals(dataReserva);
        assert capturedReserva.getSeqRestaurante().equals(seqRestaurante);
        assert capturedReserva.getStatus().equals(StatusReserva.CONFIRMADA);
    }

    // Optionally, you can add more tests to cover different scenarios like invalid inputs or edge cases.
}
