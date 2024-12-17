package com.restaurante.reservaAvaliacao.infrastructure.persistence.repository;

import com.restaurante.reservaAvaliacao.infrastructure.persistence.entity.ReservaMesaEntity;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@SpringBootTest
@AutoConfigureTestDatabase
@Transactional
class IReservaMesaRepositoryTest {

    @Mock
    private IReservaMesaRepository reservaMesaRepository;

    @InjectMocks
    private IReservaMesaRepositoryTest service;

    private ReservaMesaEntity reservaMesa;

    @BeforeEach
    public void setUp() {
        // Criando um objeto de ReservaMesaEntity com valores de exemplo
        reservaMesa = new ReservaMesaEntity();
        reservaMesa.setSeqReservaMesa(1L);
        reservaMesa.setDataReserva(LocalDate.of(2024, 12, 10));
        reservaMesa.setSeqRestaurante(1L);
        reservaMesa.setNomeCliente("João da Silva");
        reservaMesa.setDocumentoCliente("12345678901");
    }

    @Test
    void testSalvarReservaMesa() {
        // Arrange: Quando o método save for chamado, retorna a entidade criada
        when(reservaMesaRepository.save(any(ReservaMesaEntity.class))).thenReturn(reservaMesa);

        // Act: Chamando o método save
        ReservaMesaEntity resultado = reservaMesaRepository.save(reservaMesa);

        // Assert: Verificando os valores retornados
        assertThat(resultado).isNotNull();
        assertThat(resultado.getSeqReservaMesa()).isEqualTo(reservaMesa.getSeqReservaMesa());
        assertThat(resultado.getDataReserva()).isEqualTo(reservaMesa.getDataReserva());
        assertThat(resultado.getSeqRestaurante()).isEqualTo(reservaMesa.getSeqRestaurante());
        assertThat(resultado.getNomeCliente()).isEqualTo(reservaMesa.getNomeCliente());
        assertThat(resultado.getDocumentoCliente()).isEqualTo(reservaMesa.getDocumentoCliente());

        // Verificando se o método save foi chamado uma vez
        verify(reservaMesaRepository, times(1)).save(any(ReservaMesaEntity.class));
    }

    @Test
    void testBuscarReservaMesaPorId() {
        // Arrange: Quando o método findById for chamado com o ID 1, retorna a entidade
        when(reservaMesaRepository.findById(1L)).thenReturn(Optional.of(reservaMesa));

        // Act: Chamando o método findById
        Optional<ReservaMesaEntity> resultado = reservaMesaRepository.findById(1L);

        // Assert: Verificando se o resultado está presente e seus valores
        assertThat(resultado).isPresent();
        assertThat(resultado.get().getSeqReservaMesa()).isEqualTo(reservaMesa.getSeqReservaMesa());
        assertThat(resultado.get().getDataReserva()).isEqualTo(reservaMesa.getDataReserva());
        assertThat(resultado.get().getSeqRestaurante()).isEqualTo(reservaMesa.getSeqRestaurante());
        assertThat(resultado.get().getNomeCliente()).isEqualTo(reservaMesa.getNomeCliente());
        assertThat(resultado.get().getDocumentoCliente()).isEqualTo(reservaMesa.getDocumentoCliente());

        // Verificando se o método findById foi chamado uma vez
        verify(reservaMesaRepository, times(1)).findById(1L);
    }

    @Test
    void testBuscarTodasReservasMesas() {
        // Arrange: Simulando o retorno de uma lista de objetos ReservaMesaEntity
        List<ReservaMesaEntity> lista = Collections.singletonList(reservaMesa);
        when(reservaMesaRepository.findAll()).thenReturn(lista);

        // Act: Chamando o método findAll
        List<ReservaMesaEntity> resultado = reservaMesaRepository.findAll();

        // Assert: Verificando se a lista não está vazia e se contém os valores esperados
        assertThat(resultado).isNotNull();
        assertThat(resultado).isNotEmpty();
        assertThat(resultado).hasSize(1);
        assertThat(resultado.get(0).getSeqReservaMesa()).isEqualTo(reservaMesa.getSeqReservaMesa());
        assertThat(resultado.get(0).getDataReserva()).isEqualTo(reservaMesa.getDataReserva());

        // Verificando se o método findAll foi chamado uma vez
        verify(reservaMesaRepository, times(1)).findAll();
    }

    @Test
    void testAtualizarReservaMesa() {
        // Arrange: Criando uma nova entidade com dados atualizados
        ReservaMesaEntity reservaMesaAtualizada = new ReservaMesaEntity();
        reservaMesaAtualizada.setSeqReservaMesa(1L);
        reservaMesaAtualizada.setDataReserva(LocalDate.of(2024, 12, 12)); // Data alterada
        reservaMesaAtualizada.setSeqRestaurante(1L);
        reservaMesaAtualizada.setNomeCliente("Maria Oliveira");
        reservaMesaAtualizada.setDocumentoCliente("98765432100");

        // Simulando o comportamento do repositório
        when(reservaMesaRepository.save(any(ReservaMesaEntity.class))).thenReturn(reservaMesaAtualizada);

        // Act: Chamando o método save para atualizar
        ReservaMesaEntity resultado = reservaMesaRepository.save(reservaMesaAtualizada);

        // Assert: Verificando se os dados foram atualizados corretamente
        assertThat(resultado).isNotNull();
        assertThat(resultado.getDataReserva()).isEqualTo(reservaMesaAtualizada.getDataReserva());
        assertThat(resultado.getNomeCliente()).isEqualTo(reservaMesaAtualizada.getNomeCliente());
        assertThat(resultado.getDocumentoCliente()).isEqualTo(reservaMesaAtualizada.getDocumentoCliente());

        // Verificando se o método save foi chamado uma vez
        verify(reservaMesaRepository, times(1)).save(any(ReservaMesaEntity.class));
    }

    @Test
    void testDeletarReservaMesa() {
        // Arrange: Simulando que o método deleteById não retorna nada
        doNothing().when(reservaMesaRepository).deleteById(1L);

        // Act: Chamando o método deleteById para excluir a entidade
        reservaMesaRepository.deleteById(1L);

        // Assert: Verificando se o método deleteById foi chamado uma vez com o ID 1
        verify(reservaMesaRepository, times(1)).deleteById(1L);
    }

    @Test
    void testBuscarComSpecification() {
        // Arrange: Simulando o comportamento do método findAll com especificação
        Specification<ReservaMesaEntity> specification = (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("seqRestaurante"), 1L);
        List<ReservaMesaEntity> lista = Collections.singletonList(reservaMesa);
        when(reservaMesaRepository.findAll(specification)).thenReturn(lista);

        // Act: Chamando o método findAll com Specification
        List<ReservaMesaEntity> resultado = reservaMesaRepository.findAll(specification);

        // Assert: Verificando se a lista contém a entidade filtrada
        assertThat(resultado).isNotNull();
        assertThat(resultado).isNotEmpty();
        assertThat(resultado.get(0).getSeqReservaMesa()).isEqualTo(reservaMesa.getSeqReservaMesa());

        // Verificando se o método findAll foi chamado uma vez com a especificação
        verify(reservaMesaRepository, times(1)).findAll(specification);
    }
}
