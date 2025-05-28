package com.restaurante.reservaAvaliacao.infrastructure.controller;

import com.restaurante.reservaAvaliacao.application.dto.AvaliacaoRestaurantePaginadoDTO;
import com.restaurante.reservaAvaliacao.application.dto.CreateAvaliacaoRestauranteDTO;
import com.restaurante.reservaAvaliacao.application.useCase.CreateAvaliacaoUseCase;
import com.restaurante.reservaAvaliacao.application.useCase.GetAllAvaliacaoRestauranteUseCase;
import com.restaurante.reservaAvaliacao.domain.entity.AvaliacaoRestaurante;
import com.restaurante.reservaAvaliacao.domain.pagination.Pagination;
import com.restaurante.reservaAvaliacao.infrastructure.mapper.AvaliacaoRestauranteMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class AvaliacaoRestauranteControllerTest {

    @Mock
    private CreateAvaliacaoUseCase createAvaliacaoUseCase;

    @Mock
    private GetAllAvaliacaoRestauranteUseCase getAllAvaliacaoRestauranteUseCase;

    @Mock
    private AvaliacaoRestauranteMapper avaliacaoRestauranteMapper;

    @InjectMocks
    private AvaliacaoRestauranteController avaliacaoRestauranteController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);  // Inicializa os mocks
    }

    @Test
    void deveCriarAvaliacao() {
        // Dados de entrada
        CreateAvaliacaoRestauranteDTO avaliacaoDTO = new CreateAvaliacaoRestauranteDTO();
       avaliacaoDTO.setNotaAvaliacao(5l);
       avaliacaoDTO.setComentario("Excelente");
       avaliacaoDTO.setDocumentoCliente("11122233344");
       avaliacaoDTO.setSeqRestaurante(1l);        

        // Executa a chamada ao método
        ResponseEntity<Void> response = avaliacaoRestauranteController.criarAvaliacao(avaliacaoDTO);

        // Verifica se o caso de uso foi executado corretamente
        verify(createAvaliacaoUseCase, times(1)).execute(any());

        // Verifica se a resposta HTTP é 204 No Content
        assertEquals(204, response.getStatusCodeValue());
    }

    @Test
    void deveListarAvaliacoesComPagina() {
        // Simulando uma lista de avaliações
        AvaliacaoRestaurante avaliacao1 = new AvaliacaoRestaurante(1L, LocalDate.now(), 5L, "Excelente", "123456789", 1L);
        AvaliacaoRestaurante avaliacao2 = new AvaliacaoRestaurante(2L, LocalDate.now(), 4L, "Muito bom", "987654321", 1L);
        List<AvaliacaoRestaurante> avaliacoesList = Arrays.asList(avaliacao1, avaliacao2);
        Pagination<AvaliacaoRestaurante> pagination = new Pagination<>(1, 10, 100, 10, avaliacoesList);

        // Simulando o comportamento do caso de uso
        when(getAllAvaliacaoRestauranteUseCase.execute(1, 10, 1L));

        AvaliacaoRestaurantePaginadoDTO paginadoDTO = new AvaliacaoRestaurantePaginadoDTO();

        // Executa a chamada ao método
        ResponseEntity<AvaliacaoRestaurantePaginadoDTO> response = avaliacaoRestauranteController.listaAvaliacoes(1, 10, 1L);

        // Verifica se o caso de uso foi executado corretamente
        verify(getAllAvaliacaoRestauranteUseCase, times(1)).execute(1, 10, 1L);

        // Verifica se a resposta contém o DTO de avaliações
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(paginadoDTO, response.getBody());
    }
}
