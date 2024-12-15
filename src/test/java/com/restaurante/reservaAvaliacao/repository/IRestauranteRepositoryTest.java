package com.restaurante.reservaAvaliacao.repository;

import com.restaurante.reservaAvaliacao.infrastructure.persistence.entity.RestauranteEntity;
import com.restaurante.reservaAvaliacao.infrastructure.persistence.repository.IRestauranteRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@SpringBootTest
@AutoConfigureTestDatabase
@Transactional
class IRestauranteRepositoryTest {

    @Mock
    private IRestauranteRepository restauranteRepository;

    @InjectMocks
    private IRestauranteRepositoryTest service;

    private RestauranteEntity restauranteEntity;

    @BeforeEach
    public void setUp() {
        // Criando um objeto RestauranteEntity com valores de exemplo
        restauranteEntity = new RestauranteEntity();
        restauranteEntity.setSeqRestaurante(1L);
        restauranteEntity.setNomeRestaurante("Restaurante Teste");
        restauranteEntity.setDataCadastro(LocalDate.of(2024, 12, 10));
        restauranteEntity.setEndereco("Rua Exemplo, 123");
        restauranteEntity.setTipoCozinha("Italiana");
    }

    @Test
    void testSalvarRestaurante() {
        // Arrange: Quando o método save for chamado, retorna a entidade criada
        when(restauranteRepository.save(any(RestauranteEntity.class))).thenReturn(restauranteEntity);

        // Act: Chamando o método save
        RestauranteEntity resultado = restauranteRepository.save(restauranteEntity);

        // Assert: Verificando os valores retornados
        assertThat(resultado).isNotNull();
        assertThat(resultado.getSeqRestaurante()).isEqualTo(restauranteEntity.getSeqRestaurante());
        assertThat(resultado.getNomeRestaurante()).isEqualTo(restauranteEntity.getNomeRestaurante());
        assertThat(resultado.getDataCadastro()).isEqualTo(restauranteEntity.getDataCadastro());
        assertThat(resultado.getEndereco()).isEqualTo(restauranteEntity.getEndereco());
        assertThat(resultado.getTipoCozinha()).isEqualTo(restauranteEntity.getTipoCozinha());

        // Verificando se o método save foi chamado uma vez
        verify(restauranteRepository, times(1)).save(any(RestauranteEntity.class));
    }

    @Test
    void testBuscarRestaurantePorId() {
        // Arrange: Quando o método findById for chamado com o ID 1, retorna a entidade
        when(restauranteRepository.findById(1L)).thenReturn(Optional.of(restauranteEntity));

        // Act: Chamando o método findById
        Optional<RestauranteEntity> resultado = restauranteRepository.findById(1L);

        // Assert: Verificando se o resultado está presente e seus valores
        assertThat(resultado).isPresent();
        assertThat(resultado.get().getSeqRestaurante()).isEqualTo(restauranteEntity.getSeqRestaurante());
        assertThat(resultado.get().getNomeRestaurante()).isEqualTo(restauranteEntity.getNomeRestaurante());
        assertThat(resultado.get().getDataCadastro()).isEqualTo(restauranteEntity.getDataCadastro());
        assertThat(resultado.get().getEndereco()).isEqualTo(restauranteEntity.getEndereco());
        assertThat(resultado.get().getTipoCozinha()).isEqualTo(restauranteEntity.getTipoCozinha());

        // Verificando se o método findById foi chamado uma vez
        verify(restauranteRepository, times(1)).findById(1L);
    }

    @Test
    void testBuscarTodosRestaurantes() {
        // Arrange: Simulando o retorno de uma lista de objetos RestauranteEntity
        List<RestauranteEntity> lista = Collections.singletonList(restauranteEntity);
        when(restauranteRepository.findAll()).thenReturn(lista);

        // Act: Chamando o método findAll
        List<RestauranteEntity> resultado = restauranteRepository.findAll();

        // Assert: Verificando se a lista não está vazia e se contém os valores esperados
        assertThat(resultado).isNotNull();
        assertThat(resultado).isNotEmpty();
        assertThat(resultado).hasSize(1);
        assertThat(resultado.get(0).getSeqRestaurante()).isEqualTo(restauranteEntity.getSeqRestaurante());
        assertThat(resultado.get(0).getNomeRestaurante()).isEqualTo(restauranteEntity.getNomeRestaurante());
        assertThat(resultado.get(0).getDataCadastro()).isEqualTo(restauranteEntity.getDataCadastro());

        // Verificando se o método findAll foi chamado uma vez
        verify(restauranteRepository, times(1)).findAll();
    }

    @Test
    void testAtualizarRestaurante() {
        // Arrange: Criando uma nova entidade com dados atualizados
        RestauranteEntity restauranteAtualizado = new RestauranteEntity();
        restauranteAtualizado.setSeqRestaurante(1L);
        restauranteAtualizado.setNomeRestaurante("Restaurante Atualizado");
        restauranteAtualizado.setDataCadastro(LocalDate.of(2024, 12, 15)); // Data alterada
        restauranteAtualizado.setEndereco("Rua Atualizada, 456");
        restauranteAtualizado.setTipoCozinha("Francesa");

        // Simulando o comportamento do repositório
        when(restauranteRepository.save(any(RestauranteEntity.class))).thenReturn(restauranteAtualizado);

        // Act: Chamando o método save para atualizar
        RestauranteEntity resultado = restauranteRepository.save(restauranteAtualizado);

        // Assert: Verificando se os dados foram atualizados corretamente
        assertThat(resultado).isNotNull();
        assertThat(resultado.getNomeRestaurante()).isEqualTo(restauranteAtualizado.getNomeRestaurante());
        assertThat(resultado.getDataCadastro()).isEqualTo(restauranteAtualizado.getDataCadastro());
        assertThat(resultado.getEndereco()).isEqualTo(restauranteAtualizado.getEndereco());
        assertThat(resultado.getTipoCozinha()).isEqualTo(restauranteAtualizado.getTipoCozinha());

        // Verificando se o método save foi chamado uma vez
        verify(restauranteRepository, times(1)).save(any(RestauranteEntity.class));
    }

    @Test
    void testDeletarRestaurante() {
        // Arrange: Simulando que o método deleteById não retorna nada
        doNothing().when(restauranteRepository).deleteById(1L);

        // Act: Chamando o método deleteById para excluir a entidade
        restauranteRepository.deleteById(1L);

        // Assert: Verificando se o método deleteById foi chamado uma vez com o ID 1
        verify(restauranteRepository, times(1)).deleteById(1L);
    }
}
