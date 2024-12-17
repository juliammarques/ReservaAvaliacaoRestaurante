package com.restaurante.reservaAvaliacao.infrastructure.gateway;

import com.restaurante.reservaAvaliacao.domain.entity.ReservaMesa;
import com.restaurante.reservaAvaliacao.infrastructure.gateway.ReservaMesaGatewayImpl;
import com.restaurante.reservaAvaliacao.infrastructure.persistence.entity.ReservaMesaEntity;
import com.restaurante.reservaAvaliacao.infrastructure.persistence.repository.IReservaMesaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Example;

import java.time.LocalDate;
import java.util.List;

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
                "12345678901" // documentoCliente
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
    void testBuscaReservaMesaPorCliente() {
        // Arrange
        ReservaMesaEntity reservaMesaEntity = new ReservaMesaEntity(
                1L, // seqReservaMesa
                LocalDate.now(), // dataReserva
                1L, // seqRestaurante
                "Cliente Teste", // nomeCliente
                "12345678901" // documentoCliente
        );

        // Simula o retorno de uma lista de reservas para o cliente com documento "12345678901"
        when(repository.findAll((Example<ReservaMesaEntity>) any())).thenReturn(List.of(reservaMesaEntity));

        // Act
        List<ReservaMesa> reservas = reservaMesaGateway.buscaReservaMesaPorCliente("12345678901");

        // Assert
        assertNotNull(reservas);
        assertFalse(reservas.isEmpty()); // Verifica se a lista não está vazia
        assertEquals(1, reservas.size()); // Verifica que há uma reserva na lista
    }

    @Test
    void testUpdateReserva() {
        // Arrange
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
}
