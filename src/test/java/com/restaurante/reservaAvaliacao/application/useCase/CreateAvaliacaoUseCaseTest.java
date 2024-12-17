package com.restaurante.reservaAvaliacao.application.useCase;

import com.restaurante.reservaAvaliacao.application.useCase.CreateAvaliacaoUseCase;
import com.restaurante.reservaAvaliacao.domain.entity.AvaliacaoRestaurante;
import com.restaurante.reservaAvaliacao.domain.service.AvaliacaoRestauranteDomainService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class CreateAvaliacaoUseCaseTest {

    @Mock
    private AvaliacaoRestauranteDomainService service;

    @InjectMocks
    private CreateAvaliacaoUseCase useCase;

    @BeforeEach
    public void setUp() {
        // Initialize the mocks and inject them into the use case
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testExecute_ShouldCallAdicionaAvaliacaoRestaurante() {
        // Arrange: Create the input data for the use case
        LocalDate dataAvaliacao = LocalDate.of(2024, 12, 17);
        Long notaAvaliacao = 4L;
        String comentario = "Ótima experiência!";
        String documentoCliente = "123456789";
        Long seqRestaurante = 101L;

        CreateAvaliacaoUseCase.Input input = new CreateAvaliacaoUseCase.Input(
                dataAvaliacao, notaAvaliacao, comentario, documentoCliente, seqRestaurante);

        // Act: Execute the use case
        useCase.execute(input);

        // Assert: Verify that the domain service method was called exactly once with the correct argument
        ArgumentCaptor<AvaliacaoRestaurante> captor = ArgumentCaptor.forClass(AvaliacaoRestaurante.class);
        verify(service, times(1)).adicionaAvaliacaoRestaurante(captor.capture());

        // Validate the captured argument
        AvaliacaoRestaurante capturedAvaliacao = captor.getValue();
        assert capturedAvaliacao.getDataAvaliacao().equals(dataAvaliacao);
        assert capturedAvaliacao.getNotaAvaliacao().equals(notaAvaliacao);
        assert capturedAvaliacao.getComentario().equals(comentario);
        assert capturedAvaliacao.getDocumentoCliente().equals(documentoCliente);
        assert capturedAvaliacao.getSeqRestaurante().equals(seqRestaurante);
    }

    // Optionally, you can add more tests to cover different scenarios like invalid inputs.
}
