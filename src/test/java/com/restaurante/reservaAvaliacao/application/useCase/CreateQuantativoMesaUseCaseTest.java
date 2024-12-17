package com.restaurante.reservaAvaliacao.application.useCase;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.restaurante.reservaAvaliacao.domain.entity.QuantitativoMesa;
import com.restaurante.reservaAvaliacao.domain.service.QuantitativoMesaDomainService;

class CreateQuantativoMesaUseCaseTest {

    @Mock
    private QuantitativoMesaDomainService service;

    @InjectMocks
    private CreateQuantativoMesaUseCase useCase;

    @BeforeEach
    public void setUp() {
        // Initialize the mocks and inject them into the use case
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testExecute_ShouldCallRegistraQuantitativoInicial() {
        // Arrange: Create the input data for the use case
        Long seqRestaurante = 101L;
        String tamanhoMesa = "Grande";
        Long quantidadeDisponivel = 10L;

        CreateQuantativoMesaUseCase.Input input = new CreateQuantativoMesaUseCase.Input(
                seqRestaurante, tamanhoMesa, quantidadeDisponivel);

        // Act: Execute the use case
        useCase.execute(input);

        // Assert: Verify that the domain service method was called exactly once with the correct argument
        ArgumentCaptor<QuantitativoMesa> captor = ArgumentCaptor.forClass(QuantitativoMesa.class);
        verify(service, times(1)).registraQuantitativoInicial(captor.capture());

        // Validate the captured argument
        QuantitativoMesa capturedQuantitativoMesa = captor.getValue();
        assert capturedQuantitativoMesa.getSeqRestaurante().equals(seqRestaurante);
        assert capturedQuantitativoMesa.getTamanhoMesa().equals(tamanhoMesa);
        assert capturedQuantitativoMesa.getQuantidadeDisponivel().equals(quantidadeDisponivel);
    }

    // Optionally, you can add more tests to cover different scenarios like invalid inputs.
}
