package com.restaurante.reservaAvaliacao.application.useCase;

import com.restaurante.reservaAvaliacao.domain.entity.Restaurante;
import com.restaurante.reservaAvaliacao.domain.service.RestauranteDomainService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class UpdateRestauranteUseCaseTest {

    @Mock
    private RestauranteDomainService service;

    @InjectMocks
    private UpdateRestauranteUseCase useCase;

    private UpdateRestauranteUseCase.Input input;

    @BeforeEach
    public void setUp() {
        // Inicializa os mocks e injeta-os no use case
        MockitoAnnotations.openMocks(this);

        // Prepara o objeto Input para os testes
        input = new UpdateRestauranteUseCase.Input(1L, "Restaurante Teste", "Rua Teste, 123", "Comida Italiana");
    }

    @Test
    void testExecute_ShouldCallAtualizaRestaurante() {
        // Act: Executa o método do caso de uso
        useCase.execute(input);

        // Assert: Verifica se o método 'atualizaRestaurante' foi chamado com o objeto correto
        verify(service).atualizaRestaurante(any(Restaurante.class));

        // Asserção adicional: Verifica se o método foi chamado com os dados corretos
        Restaurante capturado = captureRestauranteArgument();
        assertNotNull(capturado);
        assertEquals(input.id(), capturado.getSeqRestaurante());
        assertEquals(input.nomeRestaurante(), capturado.getNomeRestaurante());
        assertEquals(input.endereco(), capturado.getEndereco());
        assertEquals(input.tipoCozinha(), capturado.getTipoCozinha());
    }

    @Test
    void testExecute_ShouldNotCallAtualizaRestauranteWhenInputIsNull() {
        // Arrange: Passa um input nulo
        UpdateRestauranteUseCase.Input nullInput = null;

        // Act: Executa o método do caso de uso com input nulo
        useCase.execute(nullInput);

        // Assert: Verifica se o serviço não foi chamado
        verify(service, never()).atualizaRestaurante(any(Restaurante.class));
    }

    // Método auxiliar para capturar o argumento passado ao método atualizaRestaurante
    private Restaurante captureRestauranteArgument() {
        // Usando ArgumentCaptor para capturar o argumento do método 'atualizaRestaurante'
        ArgumentCaptor<Restaurante> captor = ArgumentCaptor.forClass(Restaurante.class);
        verify(service).atualizaRestaurante(captor.capture());
        return captor.getValue();
    }
}
