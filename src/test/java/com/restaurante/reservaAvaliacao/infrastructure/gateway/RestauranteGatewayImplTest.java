package com.restaurante.reservaAvaliacao.infrastructure.gateway;

import com.restaurante.reservaAvaliacao.domain.entity.Restaurante;
import com.restaurante.reservaAvaliacao.infrastructure.persistence.entity.RestauranteEntity;
import com.restaurante.reservaAvaliacao.infrastructure.persistence.repository.IRestauranteRepository;
import com.restaurante.reservaAvaliacao.domain.pagination.Pagination;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RestauranteGatewayImplTest {

    @Mock
    private IRestauranteRepository repository; // Mock do repositório

    @InjectMocks
    private RestauranteGatewayImpl restauranteGateway; // Classe que será testada

    private Restaurante restaurante;
    private RestauranteEntity restauranteEntity;

    @BeforeEach
    void setUp() {
        // Inicializa os mocks do Mockito
        MockitoAnnotations.openMocks(this);

        // Setup de um objeto Restaurante para os testes
        restaurante = new Restaurante(
                "Restaurante Teste", // nomeRestaurante
                "Rua Teste, 123",    // endereco
                "Italiana"           // tipoCozinha
        );
        restaurante.setSeqRestaurante(1L);
        restaurante.setDataCadastro(LocalDate.now());

        // Setup de RestauranteEntity correspondente
        restauranteEntity = RestauranteEntity.of(restaurante);
    }

    @Test
    void testCreateRestaurante() {
        // Arrange
        when(repository.save(any(RestauranteEntity.class))).thenReturn(restauranteEntity);

        // Act
        restauranteGateway.createRestaurante(restaurante);

        // Assert
        verify(repository, times(1)).save(any(RestauranteEntity.class)); // Verifica se o save foi chamado
    }

    @Test
    void testFindAll() {
        // Arrange
        int page = 0;
        int size = 10;
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<RestauranteEntity> pageResponse = new PageImpl<>(List.of(restauranteEntity), pageRequest, 1);

        when(repository.findAll(pageRequest)).thenReturn(pageResponse);

        // Act
        Pagination<Restaurante> result = restauranteGateway.findAll(page, size);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.total()); // Total de elementos
        assertEquals(1, result.totalPages()); // Total de páginas
        assertEquals(1, result.items().size()); // Verifica se há 1 item na página
        assertEquals("Restaurante Teste", result.items().get(0).getNomeRestaurante()); // Verifica o nome do restaurante
    }

    @Test
    void testUpdateRestaurante() {
        // Arrange
        when(repository.save(any(RestauranteEntity.class))).thenReturn(restauranteEntity);

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
    void testGetRestauranteByIdFound() {
        // Arrange
        when(repository.findById(1L)).thenReturn(Optional.of(restauranteEntity));

        // Act
        Optional<Restaurante> result = restauranteGateway.getRestauranteById(1L);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(restaurante.getNomeRestaurante(), result.get().getNomeRestaurante());
    }

    @Test
    void testGetRestauranteByIdNotFound() {
        // Arrange
        when(repository.findById(1L)).thenReturn(Optional.empty());

        // Act
        Optional<Restaurante> result = restauranteGateway.getRestauranteById(1L);

        // Assert
        assertFalse(result.isPresent()); // Verifica se não foi encontrado
    }
}
