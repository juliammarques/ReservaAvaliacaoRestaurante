package com.restaurante.reservaAvaliacao.infrastructure.controller;

import com.restaurante.reservaAvaliacao.application.dto.CreateUpdateQuantitativoMesaDTO;
import com.restaurante.reservaAvaliacao.application.dto.CreateUpdateRestauranteDTO;
import com.restaurante.reservaAvaliacao.application.dto.RestaurantePaginadoDTO;
import com.restaurante.reservaAvaliacao.application.useCase.*;
import com.restaurante.reservaAvaliacao.domain.entity.Restaurante;
import com.restaurante.reservaAvaliacao.infrastructure.mapper.QuantitativoMesaMapper;
import com.restaurante.reservaAvaliacao.infrastructure.mapper.RestauranteMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.List;

class CadastroRestauranteControllerTest {

    @Mock
    private CreateRestauranteUseCase createRestauranteUseCase;

    @Mock
    private UpdateRestauranteUseCase updateRestauranteUseCase;

    @Mock
    private GetAllRestauranteUseCase getAllRestauranteUseCase;

    @Mock
    private CreateQuantativoMesaUseCase createQuantativoMesaUseCase;

    @Mock
    private UpdateQuantitativoMesaUseCase updateQuantitativoMesaUseCase;

    @Mock
    private DeleteQuantitativoMesaUseCase deleteQuantitativoMesaUseCase;

    @Mock
    private RestauranteMapper restauranteMapper;

    @Mock
    private QuantitativoMesaMapper quantitativoMesaMapper;

    @InjectMocks
    private CadastroRestauranteControlle cadastroRestauranteController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);  // Inicializa os mocks
    }

    @Test
    void deveCriarRestaurante() {
        CreateUpdateRestauranteDTO restauranteDTO = new CreateUpdateRestauranteDTO("Restaurante Teste", "Rua A", "Italiana");

        // Executa a chamada ao método
        ResponseEntity<Void> response = cadastroRestauranteController.criarRestaurante(restauranteDTO);

        // Verifica se o caso de uso foi executado corretamente
        verify(createRestauranteUseCase, times(1)).execute(any());

        // Verifica se a resposta HTTP é 204 No Content
        assertEquals(204, response.getStatusCodeValue());
    }

    @Test
    void deveAtualizarRestaurantePeloId() {
        Long id = 1L;
        CreateUpdateRestauranteDTO restauranteDTO = new CreateUpdateRestauranteDTO("Restaurante Atualizado", "Rua B", "Francesa");

        // Executa a chamada ao método
        ResponseEntity<Void> response = cadastroRestauranteController.atualizaRestaurantePeloId(id, restauranteDTO);

        // Verifica se o caso de uso foi executado corretamente
        verify(updateRestauranteUseCase, times(1)).execute(anyLong(), any());

        // Verifica se a resposta HTTP é 204 No Content
        assertEquals(204, response.getStatusCodeValue());
    }

    @Test
    void deveListarRestaurantesComPagina() {
        Restaurante restaurante1 = new Restaurante(1L, "Restaurante 1", LocalDate.now(), "Rua A", "Italiana");
        Restaurante restaurante2 = new Restaurante(2L, "Restaurante 2", LocalDate.now(), "Rua B", "Francesa");

        // Simula o retorno da lista de restaurantes
        RestaurantePaginadoDTO restaurantePaginadoDTO = new RestaurantePaginadoDTO();
        when(getAllRestauranteUseCase.execute(1, 10)).thenReturn(List.of(restaurante1, restaurante2));
        when(restauranteMapper.toDTO(any())).thenReturn(restaurantePaginadoDTO);

        // Executa a chamada ao método
        ResponseEntity<RestaurantePaginadoDTO> response = cadastroRestauranteController.listaRestaurantes(1, 10);

        // Verifica se o caso de uso foi executado corretamente
        verify(getAllRestauranteUseCase, times(1)).execute(1, 10);

        // Verifica se a resposta contém o DTO de restaurantes
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(restaurantePaginadoDTO, response.getBody());
    }

    @Test
    void deveCriarQuantitativoMesa() {
        CreateUpdateQuantitativoMesaDTO quantitativoDTO = new CreateUpdateQuantitativoMesaDTO(10);

        // Executa a chamada ao método
        ResponseEntity<Void> response = cadastroRestauranteController.criarQauntitativoMesaDisponivel(quantitativoDTO);

        // Verifica se o caso de uso foi executado corretamente
        verify(createQuantativoMesaUseCase, times(1)).execute(any());

        // Verifica se a resposta HTTP é 204 No Content
        assertEquals(204, response.getStatusCodeValue());
    }

    @Test
    void deveAtualizarQuantitativoMesa() {
        Long id = 1L;
        CreateUpdateQuantitativoMesaDTO quantitativoDTO = new CreateUpdateQuantitativoMesaDTO(20);

        // Executa a chamada ao método
        ResponseEntity<Void> response = cadastroRestauranteController.atualizaRestaurantePeloId(id, quantitativoDTO);

        // Verifica se o caso de uso foi executado corretamente
        verify(updateQuantitativoMesaUseCase, times(1)).execute(anyLong(), any());

        // Verifica se a resposta HTTP é 204 No Content
        assertEquals(204, response.getStatusCodeValue());
    }

    @Test
    void deveExcluirQuantitativoMesa() {
        Long id = 1L;

        // Executa a chamada ao método
        ResponseEntity<Void> response = cadastroRestauranteController.excluiQuantitativoMesa(id);

        // Verifica se o caso de uso foi executado corretamente
        verify(deleteQuantitativoMesaUseCase, times(1)).execute(anyLong());

        // Verifica se a resposta HTTP é 204 No Content
        assertEquals(204, response.getStatusCodeValue());
    }
}
