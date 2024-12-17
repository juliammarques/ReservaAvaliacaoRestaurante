package com.restaurante.reservaAvaliacao.infrastructure.gateway;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.restaurante.reservaAvaliacao.domain.entity.AvaliacaoRestaurante;
import com.restaurante.reservaAvaliacao.infrastructure.gateway.AvaliacaoRestauranteGatewayImpl;
import com.restaurante.reservaAvaliacao.infrastructure.persistence.entity.AvaliacaoRestauranteEntity;
import com.restaurante.reservaAvaliacao.infrastructure.persistence.repository.IAvaliacaoRestauranteRepository;

class AvaliacaoRestauranteGatewayImplTest {

    @Mock
    private IAvaliacaoRestauranteRepository repository; // Mock do repositório

    @InjectMocks
    private AvaliacaoRestauranteGatewayImpl avaliacaoRestauranteGateway; // Classe que será testada

    private AvaliacaoRestaurante avaliacaoRestaurante;

    @BeforeEach
    void setUp() {
        // Inicializa os mocks do Mockito
        MockitoAnnotations.openMocks(this);

        // Setup de um objeto AvaliacaoRestaurante para os testes
        avaliacaoRestaurante = new AvaliacaoRestaurante();
        avaliacaoRestaurante.setSeqAvaliacao(1L);
        avaliacaoRestaurante.setDataAvaliacao(LocalDate.now());
        avaliacaoRestaurante.setNotaAvaliacao(5L);
        avaliacaoRestaurante.setComentario("Excelente restaurante!");
    }

    @Test
    void testCreateAvaliacaoRestaurante() {
        // Arrange
        //when(repository.save(any(AvaliacaoRestauranteEntity.class))).thenReturn(avaliacaoRestaurante);

        // Act
        //avaliacaoRestauranteGateway.createAvaliacaoRestaurante(avaliacaoRestaurante);

        // Assert
        verify(repository, times(1)).save(any(AvaliacaoRestauranteEntity.class)); // Verifica se o save foi chamado
    }

    @Test
    void testReadAvaliacaoRestaurante() {
        // Arrange
        //when(repository.findById(1L)).thenReturn(Optional.of(avaliacaoRestaurante));

        // Act
        //Optional<AvaliacaoRestauranteEntity> resultado = avaliacaoRestauranteGateway.readAvaliacaoRestaurante(1L);

        // Assert
        //assertTrue(resultado.isPresent()); // Verifica se a avaliação foi encontrada
        //assertEquals(avaliacaoRestaurante.getSeqAvaliacao(), resultado.get().getSeqAvaliacao()); // Verifica se o seqAvaliacao corresponde
    }

    @Test
    void testUpdateAvaliacaoRestaurante() {
        // Arrange
        AvaliacaoRestauranteEntity updatedAvaliacao = new AvaliacaoRestauranteEntity();
        updatedAvaliacao.setSeqAvaliacao(1L);
        updatedAvaliacao.setDataAvaliacao(LocalDate.now());
        updatedAvaliacao.setNotaAvaliacao(4L); // Mudou a nota
        updatedAvaliacao.setComentario("Bom, mas pode melhorar.");

        when(repository.save(any(AvaliacaoRestauranteEntity.class))).thenReturn(updatedAvaliacao);

        // Act
        //avaliacaoRestauranteGateway.updateAvaliacaoRestaurante(updatedAvaliacao);

        // Assert
        verify(repository, times(1)).save(any(AvaliacaoRestauranteEntity.class)); // Verifica se o save foi chamado
    }

    @Test
    void testDeleteAvaliacaoRestaurante() {
        // Arrange
        doNothing().when(repository).deleteById(1L);

        // Act
        //avaliacaoRestauranteGateway.deleteAvaliacaoRestaurante(1L);

        // Assert
        verify(repository, times(1)).deleteById(1L); // Verifica se o deleteById foi chamado
    }
}
