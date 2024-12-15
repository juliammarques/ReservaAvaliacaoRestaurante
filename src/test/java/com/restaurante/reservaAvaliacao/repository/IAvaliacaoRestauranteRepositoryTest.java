package com.restaurante.reservaAvaliacao.repository;

import com.restaurante.reservaAvaliacao.infrastructure.persistence.entity.AvaliacaoRestauranteEntity;
import com.restaurante.reservaAvaliacao.infrastructure.persistence.repository.IAvaliacaoRestauranteRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@SpringBootTest
@AutoConfigureTestDatabase
@Transactional
class IAvaliacaoRestauranteRepositoryTest {

    @Mock
    private IAvaliacaoRestauranteRepository avaliacaoRestauranteRepository;

    @InjectMocks
    private IAvaliacaoRestauranteRepositoryTest service;

    private AvaliacaoRestauranteEntity avaliacao;

    @BeforeEach
    public void setUp() {
        // Criando um objeto de AvaliacaoRestauranteEntity com valores de exemplo
        avaliacao = new AvaliacaoRestauranteEntity();
        avaliacao.setSeqAvaliacao(1L);
        avaliacao.setDataAvaliacao(LocalDate.now());
        avaliacao.setNotaAvaliacao(5L);
        avaliacao.setComentario("Excelente restaurante!");
    }

    @Test
    void deveSalvarAvaliacao() {
        // Arrange: Quando o método save for chamado, retorna a avaliação criada
        when(avaliacaoRestauranteRepository.save(any(AvaliacaoRestauranteEntity.class))).thenReturn(avaliacao);

        // Act: Chamando o método save
        AvaliacaoRestauranteEntity resultado = avaliacaoRestauranteRepository.save(avaliacao);

        // Assert: Verificando os valores retornados
        assertThat(resultado).isNotNull();
        assertThat(resultado.getSeqAvaliacao()).isEqualTo(avaliacao.getSeqAvaliacao());
        assertThat(resultado.getDataAvaliacao()).isEqualTo(avaliacao.getDataAvaliacao());
        assertThat(resultado.getNotaAvaliacao()).isEqualTo(avaliacao.getNotaAvaliacao());
        assertThat(resultado.getComentario()).isEqualTo(avaliacao.getComentario());

        // Verificando se o método save foi chamado uma vez
        verify(avaliacaoRestauranteRepository, times(1)).save(any(AvaliacaoRestauranteEntity.class));
    }

    @Test
    void deveBuscarAvaliacaoPorId() {
        // Arrange: Quando o método findById for chamado com o ID 1, retorna a avaliação
        when(avaliacaoRestauranteRepository.findById(1L)).thenReturn(Optional.of(avaliacao));

        // Act: Chamando o método findById
        Optional<AvaliacaoRestauranteEntity> resultado = avaliacaoRestauranteRepository.findById(1L);

        // Assert: Verificando se o resultado está presente e seus valores
        assertThat(resultado).isPresent();
        assertThat(resultado.get().getSeqAvaliacao()).isEqualTo(avaliacao.getSeqAvaliacao());
        assertThat(resultado.get().getDataAvaliacao()).isEqualTo(avaliacao.getDataAvaliacao());
        assertThat(resultado.get().getNotaAvaliacao()).isEqualTo(avaliacao.getNotaAvaliacao());
        assertThat(resultado.get().getComentario()).isEqualTo(avaliacao.getComentario());

        // Verificando se o método findById foi chamado uma vez
        verify(avaliacaoRestauranteRepository, times(1)).findById(1L);
    }

    @Test
    void deveAtualizarAvaliacao() {
        // Arrange: Criando uma nova avaliação com dados atualizados
        AvaliacaoRestauranteEntity avaliacaoAtualizada = new AvaliacaoRestauranteEntity();
        avaliacaoAtualizada.setSeqAvaliacao(1L);
        avaliacaoAtualizada.setDataAvaliacao(LocalDate.now());
        avaliacaoAtualizada.setNotaAvaliacao(4L);
        avaliacaoAtualizada.setComentario("Muito bom, mas pode melhorar!");

        // Simulando o comportamento do repositório
        when(avaliacaoRestauranteRepository.save(any(AvaliacaoRestauranteEntity.class))).thenReturn(avaliacaoAtualizada);

        // Act: Chamando o método save com os dados atualizados
        AvaliacaoRestauranteEntity resultado = avaliacaoRestauranteRepository.save(avaliacaoAtualizada);

        // Assert: Verificando se os dados foram atualizados corretamente
        assertThat(resultado).isNotNull();
        assertThat(resultado.getNotaAvaliacao()).isEqualTo(avaliacaoAtualizada.getNotaAvaliacao());
        assertThat(resultado.getComentario()).isEqualTo(avaliacaoAtualizada.getComentario());

        // Verificando se o método save foi chamado uma vez
        verify(avaliacaoRestauranteRepository, times(1)).save(any(AvaliacaoRestauranteEntity.class));
    }

    @Test
    void deveDeletarAvaliacao() {
        // Arrange: Simulando que o método deleteById não retorna nada
        doNothing().when(avaliacaoRestauranteRepository).deleteById(1L);

        // Act: Chamando o método deleteById para excluir a avaliação
        avaliacaoRestauranteRepository.deleteById(1L);

        // Assert: Verificando se o método deleteById foi chamado uma vez com o ID 1
        verify(avaliacaoRestauranteRepository, times(1)).deleteById(1L);
    }
}
