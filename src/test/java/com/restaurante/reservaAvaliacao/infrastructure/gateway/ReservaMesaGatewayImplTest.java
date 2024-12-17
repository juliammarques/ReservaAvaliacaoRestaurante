package com.restaurante.reservaAvaliacao.infrastructure.gateway;

import com.restaurante.reservaAvaliacao.domain.entity.ReservaMesa;
import com.restaurante.reservaAvaliacao.infrastructure.persistence.entity.ReservaMesaEntity;
import com.restaurante.reservaAvaliacao.infrastructure.persistence.repository.IReservaMesaRepository;
import com.restaurante.reservaAvaliacao.domain.pagination.Pagination;
import com.restaurante.reservaAvaliacao.infrastructure.persistence.entity.ReservaMesaEntity.StatusReserva;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ReservaMesaGatewayImplTest {

    @Mock
    private IReservaMesaRepository repository; // Mock do repositório

    @InjectMocks
    private ReservaMesaGatewayImpl reservaMesaGateway; // Classe que será testada

    private ReservaMesa reservaMesa;

    @BeforeEach
    void setUp() {
        // Inicializa os mocks do Mockito
        MockitoAnnotations.openMocks(this);

        // Setup de um objeto ReservaMesa para os testes
        reservaMesa = new ReservaMesa(
                1L, // seqReservaMesa
                LocalDate.now(), // dataReserva
                1L, // seqRestaurante
                "Cliente Teste", // nomeCliente
                "12345678901", // documentoCliente
                StatusReserva.CONFIRMADA // status
        );
    }

    @Test
    void testCreateReservaMesa() {
        // Arrange
        when(repository.save(any(ReservaMesaEntity.class))).thenReturn(ReservaMesaEntity.of(reservaMesa));

        // Act
        reservaMesaGateway.createReservaMesa(reservaMesa);

        // Assert
        verify(repository, times(1)).save(any(ReservaMesaEntity.class)); // Verifica se o save foi chamado
    }

    @Test
    void testFindAll() {
        // Arrange
        int page = 0;
        int size = 10;
        long seqRestaurante = 1L;
        PageRequest pageRequest = PageRequest.of(page, size);

        // Simula o retorno de um Page com uma reserva
        Page<ReservaMesaEntity> pageResponse = new PageImpl<>(
                List.of(ReservaMesaEntity.of(reservaMesa)),
                pageRequest, 1);

        when(repository.findAll((Example<ReservaMesaEntity>) any(), eq(pageRequest))).thenReturn(pageResponse);

        // Act
        Pagination<ReservaMesa> result = reservaMesaGateway.findAll(page, size, seqRestaurante);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.total()); // Total de elementos
        assertEquals(1, result.totalPages()); // Total de páginas
        assertEquals(1, result.items().size()); // Verifica que há 1 item na lista
        assertEquals("Cliente Teste", result.items().get(0).getNomeCliente()); // Verifica o nome do cliente
        assertEquals(StatusReserva.CONFIRMADA, result.items().get(0).getStatus()); // Verifica o status da reserva
    }

    @Test
    void testUpdateReserva() {
        // Arrange
        reservaMesa.setStatus(StatusReserva.CONFIRMADA); // Atualiza o status
        when(repository.save(any(ReservaMesaEntity.class))).thenReturn(ReservaMesaEntity.of(reservaMesa));

        // Act
        reservaMesaGateway.updateReserva(reservaMesa);

        // Assert
        verify(repository, times(1)).save(any(ReservaMesaEntity.class)); // Verifica se o save foi chamado
    }

    @Test
    void testDeleteReserva() {
        // Arrange
        doNothing().when(repository).deleteById(1L);

        // Act
        reservaMesaGateway.deleteReserva(1L);

        // Assert
        verify(repository, times(1)).deleteById(1L); // Verifica se o deleteById foi chamado
    }

    @Test
    void testGetReservaMesaByIdFound() {
        // Arrange
        when(repository.findById(1L)).thenReturn(Optional.of(ReservaMesaEntity.of(reservaMesa)));

        // Act
        Optional<ReservaMesa> result = reservaMesaGateway.getReservaMesaById(1L);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(reservaMesa.getNomeCliente(), result.get().getNomeCliente()); // Verifica o nome do cliente
        assertEquals(reservaMesa.getStatus(), result.get().getStatus()); // Verifica o status da reserva
    }

    @Test
    void testGetReservaMesaByIdNotFound() {
        // Arrange
        when(repository.findById(1L)).thenReturn(Optional.empty());

        // Act
        Optional<ReservaMesa> result = reservaMesaGateway.getReservaMesaById(1L);

        // Assert
        assertFalse(result.isPresent()); // Verifica se não foi encontrado
    }
}
