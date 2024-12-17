package com.restaurante.reservaAvaliacao.application.useCase;

import com.restaurante.reservaAvaliacao.domain.entity.ReservaMesa;
import com.restaurante.reservaAvaliacao.domain.service.ReservaMesaDomainService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class UpdateReservaMesaUseCaseTest {

    @Mock
    private ReservaMesaDomainService service;

    @InjectMocks
    private UpdateReservaMesaUseCase useCase;

    private UpdateReservaMesaUseCase.Input input;

    @BeforeEach
    public void setUp() {
        // Inicializa os mocks e injeta-os no use case
        MockitoAnnotations.openMocks(this);

        // Prepara o objeto Input para os testes
        input = new UpdateReservaMesaUseCase.Input(1L, LocalDate.of(2024, 12, 17));
    }

    @Test
    void testExecute_ShouldCallAtualizaReserva() {
        // Act: Executa o método do caso de uso
        useCase.execute(input);

        // Assert: Verifica se o método 'atualizaReserva' foi chamado com o objeto correto
        verify(service).atualizaReserva(any(ReservaMesa.class));

        // Asserção adicional: Verifica se o método foi chamado com os dados corretos
        ReservaMesa capturado = captureReservaMesaArgument();
        assertNotNull(capturado);
        assertEquals(input.id(), capturado.getSeqReservaMesa());
        assertEquals(input.dataReserva(), capturado.getDataReserva());
    }

    @Test
    void testExecute_ShouldNotCallAtualizaReservaWhenInputIsNull() {
        // Arrange: Passa um input nulo
        UpdateReservaMesaUseCase.Input nullInput = null;

        // Act: Executa o método do caso de uso com input nulo
        useCase.execute(nullInput);

        // Assert: Verifica se o serviço não foi chamado
        verify(service, never()).atualizaReserva(any(ReservaMesa.class));
    }

    // Método auxiliar para capturar o argumento passado ao método atualizaReserva
    private ReservaMesa captureReservaMesaArgument() {
        // Usando ArgumentCaptor para capturar o argumento do método 'atualizaReserva'
        ArgumentCaptor<ReservaMesa> captor = ArgumentCaptor.forClass(ReservaMesa.class);
        verify(service).atualizaReserva(captor.capture());
        return captor.getValue();
    }
}
