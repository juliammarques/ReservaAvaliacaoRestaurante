package com.restaurante.reservaAvaliacao.infrastructure.persistence.repository;

import com.restaurante.reservaAvaliacao.infrastructure.persistence.entity.QuantitativoMesaEntity;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@SpringBootTest
@AutoConfigureTestDatabase
@Transactional
class IQuantitativoMesaRepositoryTest {

    @Mock
    private IQuantitativoMesaRepository quantitativoMesaRepository;

    @InjectMocks
    private IQuantitativoMesaRepositoryTest service;

    private QuantitativoMesaEntity quantitativoMesa;

    @BeforeEach
    public void setUp() {
        // Criando um objeto de QuantitativoMesaEntity com valores de exemplo
        quantitativoMesa = new QuantitativoMesaEntity();
        quantitativoMesa.setSeqQuantitativoMesaEntity(1L);
        quantitativoMesa.setSeqRestaurante(1L);
        quantitativoMesa.setTamanhoMesa("4");
        quantitativoMesa.setQuantidadeDisponivel(10L);
    }

    @Test
    void deveSalvarQuantitativoMesa() {
        // Arrange: Quando o método save for chamado, retorna a entidade criada
        when(quantitativoMesaRepository.save(any(QuantitativoMesaEntity.class))).thenReturn(quantitativoMesa);

        // Act: Chamando o método save
        QuantitativoMesaEntity resultado = quantitativoMesaRepository.save(quantitativoMesa);

        // Assert: Verificando os valores retornados
        assertThat(resultado).isNotNull();
        assertThat(resultado.getSeqQuantitativoMesaEntity()).isEqualTo(quantitativoMesa.getSeqQuantitativoMesaEntity());
        assertThat(resultado.getSeqRestaurante()).isEqualTo(quantitativoMesa.getSeqRestaurante());
        assertThat(resultado.getTamanhoMesa()).isEqualTo(quantitativoMesa.getTamanhoMesa());
        assertThat(resultado.getQuantidadeDisponivel()).isEqualTo(quantitativoMesa.getQuantidadeDisponivel());

        // Verificando se o método save foi chamado uma vez
        verify(quantitativoMesaRepository, times(1)).save(any(QuantitativoMesaEntity.class));
    }

    @Test
    void deveBuscarQuantitativoMesaPorId() {
        // Arrange: Quando o método findById for chamado com o ID 1, retorna a entidade
        when(quantitativoMesaRepository.findById(1L)).thenReturn(Optional.of(quantitativoMesa));

        // Act: Chamando o método findById
        Optional<QuantitativoMesaEntity> resultado = quantitativoMesaRepository.findById(1L);

        // Assert: Verificando se o resultado está presente e seus valores
        assertThat(resultado).isPresent();
        assertThat(resultado.get().getSeqQuantitativoMesaEntity()).isEqualTo(quantitativoMesa.getSeqQuantitativoMesaEntity());
        assertThat(resultado.get().getSeqRestaurante()).isEqualTo(quantitativoMesa.getSeqRestaurante());
        assertThat(resultado.get().getTamanhoMesa()).isEqualTo(quantitativoMesa.getTamanhoMesa());
        assertThat(resultado.get().getQuantidadeDisponivel()).isEqualTo(quantitativoMesa.getQuantidadeDisponivel());

        // Verificando se o método findById foi chamado uma vez
        verify(quantitativoMesaRepository, times(1)).findById(1L);
    }

    @Test
    void deveBuscarTodosQuantitativosMesa() {
        // Arrange: Simulando o retorno de uma lista de objetos QuantitativoMesaEntity
        List<QuantitativoMesaEntity> lista = Collections.singletonList(quantitativoMesa);
        when(quantitativoMesaRepository.findAll()).thenReturn(lista);

        // Act: Chamando o método findAll
        List<QuantitativoMesaEntity> resultado = quantitativoMesaRepository.findAll();

        // Assert: Verificando se a lista não está vazia e se contém os valores esperados
        assertThat(resultado).isNotNull();
        assertThat(resultado).isNotEmpty();
        assertThat(resultado).hasSize(1);
        assertThat(resultado.get(0).getSeqQuantitativoMesaEntity()).isEqualTo(quantitativoMesa.getSeqQuantitativoMesaEntity());
        assertThat(resultado.get(0).getSeqRestaurante()).isEqualTo(quantitativoMesa.getSeqRestaurante());

        // Verificando se o método findAll foi chamado uma vez
        verify(quantitativoMesaRepository, times(1)).findAll();
    }

    @Test
    void deveAtualizarQuantitativoMesa() {
        // Arrange: Criando uma nova entidade com dados atualizados
        QuantitativoMesaEntity quantitativoMesaAtualizada = new QuantitativoMesaEntity();
        quantitativoMesaAtualizada.setSeqQuantitativoMesaEntity(1L);
        quantitativoMesaAtualizada.setSeqRestaurante(1L);
        quantitativoMesaAtualizada.setTamanhoMesa("6");
        quantitativoMesaAtualizada.setQuantidadeDisponivel(8L);

        // Simulando o comportamento do repositório
        when(quantitativoMesaRepository.save(any(QuantitativoMesaEntity.class))).thenReturn(quantitativoMesaAtualizada);

        // Act: Chamando o método save para atualizar
        QuantitativoMesaEntity resultado = quantitativoMesaRepository.save(quantitativoMesaAtualizada);

        // Assert: Verificando se os dados foram atualizados corretamente
        assertThat(resultado).isNotNull();
        assertThat(resultado.getTamanhoMesa()).isEqualTo(quantitativoMesaAtualizada.getTamanhoMesa());
        assertThat(resultado.getQuantidadeDisponivel()).isEqualTo(quantitativoMesaAtualizada.getQuantidadeDisponivel());

        // Verificando se o método save foi chamado uma vez
        verify(quantitativoMesaRepository, times(1)).save(any(QuantitativoMesaEntity.class));
    }

    @Test
    void deveDeletarQuantitativoMesa() {
        // Arrange: Simulando que o método deleteById não retorna nada
        doNothing().when(quantitativoMesaRepository).deleteById(1L);

        // Act: Chamando o método deleteById para excluir a entidade
        quantitativoMesaRepository.deleteById(1L);

        // Assert: Verificando se o método deleteById foi chamado uma vez com o ID 1
        verify(quantitativoMesaRepository, times(1)).deleteById(1L);
    }
}
