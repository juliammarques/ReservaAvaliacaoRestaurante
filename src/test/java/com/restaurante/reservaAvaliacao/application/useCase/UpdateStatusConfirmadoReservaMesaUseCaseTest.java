package com.restaurante.reservaAvaliacao.application.useCase;

import com.restaurante.reservaAvaliacao.domain.entity.ReservaMesa;
import com.restaurante.reservaAvaliacao.domain.service.ReservaMesaDomainService;
import com.restaurante.reservaAvaliacao.infrastructure.persistence.entity.ReservaMesaEntity.StatusReserva;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class UpdateStatusConfirmadoReservaMesaUseCaseTest {

    @Mock
    private ReservaMesaDomainService service;

    @InjectMocks
    private UpdateStatusConfirmadoReservaMesaUseCase useCase;

    private UpdateStatusConfirmadoReservaMesaUseCase.Input input;

    @BeforeEach
    public void setUp() {
        // Inicializa os mocks e injeta-os no use case
        MockitoAnnotations.openMocks(this);

        // Prepara o objeto Input para os testes
        input = new UpdateStatusConfirmadoReservaMesaUseCase.Input(1L);
    }

    @Test
    void testExecute_ShouldCallAtualizaStatusEncerrado() {
        // Act: Executa o método do caso de uso
        useCase.execute(input);

        // Assert: Verifica se o método 'atualizaStatusEncerrado' foi chamado com o objeto correto
        verify(service).atualizaStatusEncerrado(any(ReservaMesa.class));

        // Asserção adicional: Verifica se o método foi chamado com os dados corretos
        ReservaMesa capturado = captureReservaMesaArgument();
        assertNotNull(capturado);
        assertEquals(input.id(), capturado.getSeqReservaMesa());
        assertEquals(StatusReserva.CONFIRMADA, capturado.getStatus());
    }

    @Test
    void testExecute_ShouldNotCallAtualizaStatusEncerradoWhenInputIsNull() {
        // Arrange: Passa um input nulo
        UpdateStatusConfirmadoReservaMesaUseCase.Input nullInput = null;

        // Act: Executa o método do caso de uso com input nulo
        useCase.execute(nullInput);

        // Assert: Verifica se o serviço não foi chamado
        verify(service, never()).atualizaStatusEncerrado(any(ReservaMesa.class));
    }

    // Método auxiliar para capturar o argumento passado ao método atualizaStatusEncerrado
    private ReservaMesa captureReservaMesaArgument() {
        // Usando ArgumentCaptor para capturar o argumento do método 'atualizaStatusEncerrado'
        ArgumentCaptor<ReservaMesa> captor = ArgumentCaptor.forClass(ReservaMesa.class);
        verify(service).atualizaStatusEncerrado(captor.capture());
        return captor.getValue();
    }
}
