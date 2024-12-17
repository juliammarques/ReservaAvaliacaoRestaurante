package com.restaurante.reservaAvaliacao.infrastructure.gateway;

import com.restaurante.reservaAvaliacao.domain.entity.Restaurante;
import com.restaurante.reservaAvaliacao.infrastructure.gateway.RestauranteGatewayImpl;
import com.restaurante.reservaAvaliacao.infrastructure.persistence.entity.RestauranteEntity;
import com.restaurante.reservaAvaliacao.infrastructure.persistence.repository.IRestauranteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RestauranteGatewayImplTest {

    @Mock
    private IRestauranteRepository repository; // Mock do repositório

    @InjectMocks
    private RestauranteGatewayImpl restauranteGateway; // Classe que será testada

    private Restaurante restaurante;

    @BeforeEach
    void setUp() {
        // Inicializa os mocks do Mockito
        MockitoAnnotations.openMocks(this);

        // Setup de um objeto Restaurante para os testes com os novos atributos
        restaurante = new Restaurante(
                "Restaurante Teste", // nomeRestaurante
                "Rua Teste, 123", // endereco
                "Italiana" // tipoCozinha
        );
        restaurante.setSeqRestaurante(1L);
        restaurante.setDataCadastro(LocalDate.now());
    }

    @Test
    void testCreateRestaurante() {
        // Arrange
        when(repository.save(any(RestauranteEntity.class))).thenReturn(RestauranteEntity.of(restaurante));

        // Act
        restauranteGateway.createRestaurante(restaurante);

        // Assert
        verify(repository, times(1)).save(any(RestauranteEntity.class)); // Verifica se o save foi chamado
    }

    @Test
    void testUpdateRestaurante() {
        // Arrange
        when(repository.save(any(RestauranteEntity.class))).thenReturn(RestauranteEntity.of(restaurante));

        // Act
        restauranteGateway.updateRestaurante(restaurante);

        // Assert
        verify(repository, times(1)).save(any(RestauranteEntity.class)); // Verifica se o save foi chamado
    }

    @Test
    void testDeleteRestaurante() {
        // Arrange
        doNothing().when(repository).deleteById(1L);

        // Act
        restauranteGateway.deleteRestaurante(1L);

        // Assert
        verify(repository, times(1)).deleteById(1L); // Verifica se o deleteById foi chamado
    }

    @Test
    void testBuscaListaRestaurante() {
        // Arrange
        when(repository.findAll()).thenReturn(List.of(RestauranteEntity.of(restaurante)));

        // Act
        List<Restaurante> restaurantes = restauranteGateway.buscaListaRestaurante();

        // Assert
        assertNotNull(restaurantes);
        assertFalse(restaurantes.isEmpty()); // Verifica se a lista não está vazia
    }

    @Test
    void testBuscaListaRestauranteEmpty() {
        // Arrange
        when(repository.findAll()).thenReturn(List.of());

        // Act
        List<Restaurante> restaurantes = restauranteGateway.buscaListaRestaurante();

        // Assert
        assertNotNull(restaurantes);
        assertTrue(restaurantes.isEmpty()); // Verifica se a lista está vazia
    }
}
