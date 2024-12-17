package com.restaurante.reservaAvaliacao.application.useCase;

import com.restaurante.reservaAvaliacao.domain.service.QuantitativoMesaDomainService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class DeleteQuantitativoMesaUseCaseTest {

    @Mock
    private QuantitativoMesaDomainService service;

    @InjectMocks
    private DeleteQuantitativoMesaUseCase useCase;

    @BeforeEach
    public void setUp() {
        // Inicializa os mocks e injeta-os no use case
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testExecute_ShouldCallDeleteQuantitativo() {
        // Arrange: Cria o id do quantitativo que será deletado
        Long idQuantitativo = 1L;

        // Act: Executa o use case
        useCase.execute(idQuantitativo);

        // Assert: Verifica se o método de deleção foi chamado exatamente uma vez
        verify(service, times(1)).deleteQuantitativo(idQuantitativo);
    }

    // Adicione mais testes, se necessário, para verificar casos como id nulo ou valores inválidos.
}
