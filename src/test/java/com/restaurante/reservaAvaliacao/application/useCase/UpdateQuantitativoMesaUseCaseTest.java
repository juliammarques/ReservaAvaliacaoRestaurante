package com.restaurante.reservaAvaliacao.application.useCase;

import com.restaurante.reservaAvaliacao.domain.entity.QuantitativoMesa;
import com.restaurante.reservaAvaliacao.domain.service.QuantitativoMesaDomainService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class UpdateQuantitativoMesaUseCaseTest {

    @Mock
    private QuantitativoMesaDomainService service;

    @InjectMocks
    private UpdateQuantitativoMesaUseCase useCase;

    private QuantitativoMesa quantitativoMesa;

    private UpdateQuantitativoMesaUseCase.Input input;

    @BeforeEach
    public void setUp() {
        // Inicializa os mocks e injeta-os no use case
        MockitoAnnotations.openMocks(this);

        // Prepara o objeto QuantitativoMesa e o input para os testes
        quantitativoMesa = new QuantitativoMesa(1L, 101L, "Grande", 10L);
        input = new UpdateQuantitativoMesaUseCase.Input(1L, 101L, "Grande", 10L);
    }

    @Test
    void testExecute_ShouldCallAtualizaQuantitativoMesa() {
        // Arrange: Nenhuma configuração adicional é necessária, pois o comportamento será verificado após a execução do método.

        // Act: Executa o método do caso de uso
        useCase.execute(input);

        // Assert: Verifica se o método 'atualizaQuantitativoMesa' foi chamado no serviço com o objeto correto
        verify(service).atualizaQuantitativoMesa(any(QuantitativoMesa.class));

        // Asserção adicional: Verifica se o método foi chamado com os dados corretos
        QuantitativoMesa capturado = captureQuantitativoMesaArgument();
        assertNotNull(capturado);
        assertEquals(input.seqQuantitativoMesaEntity(), capturado.getSeqQuantitativoMesaEntity());
        assertEquals(input.seqRestaurante(), capturado.getSeqRestaurante());
        assertEquals(input.tamanhoMesa(), capturado.getTamanhoMesa());
        assertEquals(input.quantidadeDisponivel(), capturado.getQuantidadeDisponivel());
    }

    @Test
    void testExecute_ShouldNotCallAtualizaQuantitativoMesaWhenInputIsNull() {
        // Arrange: Passa um input nulo
        UpdateQuantitativoMesaUseCase.Input nullInput = null;

        // Act: Executa o método do caso de uso com input nulo
        useCase.execute(nullInput);

        // Assert: Verifica se o serviço não foi chamado
        verify(service, never()).atualizaQuantitativoMesa(any(QuantitativoMesa.class));
    }

    // Método auxiliar para capturar o argumento passado ao método atualizaQuantitativoMesa
    private QuantitativoMesa captureQuantitativoMesaArgument() {
        // Usando ArgumentCaptor para capturar o argumento do método 'atualizaQuantitativoMesa'
        ArgumentCaptor<QuantitativoMesa> captor = ArgumentCaptor.forClass(QuantitativoMesa.class);
        verify(service).atualizaQuantitativoMesa(captor.capture());
        return captor.getValue();
    }
}

