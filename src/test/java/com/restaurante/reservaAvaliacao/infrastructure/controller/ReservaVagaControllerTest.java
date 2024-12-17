package com.restaurante.reservaAvaliacao.infrastructure.controller;

import com.restaurante.reservaAvaliacao.application.dto.CreateUpdateReservaMesaDTO;
import com.restaurante.reservaAvaliacao.application.dto.ReservaMesaDTO;
import com.restaurante.reservaAvaliacao.application.dto.ReservaMesaPaginadoDTO;
import com.restaurante.reservaAvaliacao.application.useCase.*;
import com.restaurante.reservaAvaliacao.domain.entity.ReservaMesa;
import com.restaurante.reservaAvaliacao.infrastructure.mapper.ReservaVagaMapper;
import com.restaurante.reservaAvaliacao.infrastructure.persistence.entity.ReservaMesaEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ReservaVagaControllerTest {

    @Mock
    private CreateReservaMesaUseCase createReservaMesa;

    @Mock
    private UpdateReservaMesaUseCase updateReservaMesa;

    @Mock
    private UpdateStatusConfirmadoReservaMesaUseCase updateStatusConfirmadoReservaMesaUseCase;

    @Mock
    private UpdateStatusEncerradoReservaMesaUseCase updateStatusEncerradoReservaMesaUseCase;

    @Mock
    private GetAllReservaMesaUseCase getAllReservaMesaUseCase;

    @Mock
    private GetReservaMesaByIdUseCase getReservaMesaById;

    @Mock
    private ReservaVagaMapper reservaVagaMapper;

    @InjectMocks
    private ReservaVagaController reservaVagaController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);  // Inicializa os mocks
    }

    @Test
    void deveCriarReserva() {
        CreateUpdateReservaMesaDTO reservaDTO = new CreateUpdateReservaMesaDTO(LocalDate.now(), 1L, "Cliente Teste", "1234567890", ReservaMesaEntity.StatusReserva.PENDENTE);

        // Executa a chamada ao método
        ResponseEntity<Void> response = reservaVagaController.criarReserva(reservaDTO);

        // Verifica se o caso de uso foi executado corretamente
        verify(createReservaMesa, times(1)).execute(any());

        // Verifica se a resposta HTTP é 204 No Content
        assertEquals(204, response.getStatusCodeValue());
    }

    @Test
    void deveAtualizarReserva() {
        Long id = 1L;
        CreateUpdateReservaMesaDTO reservaDTO = new CreateUpdateReservaMesaDTO(LocalDate.now(), 1L, "Cliente Atualizado", "0987654321", ReservaMesaEntity.StatusReserva.PENDENTE);

        // Executa a chamada ao método
        ResponseEntity<Void> response = reservaVagaController.atualizaRestaurantePeloId(id, reservaDTO);

        // Verifica se o caso de uso foi executado corretamente
        verify(updateReservaMesa, times(1)).execute(anyLong(), any());

        // Verifica se a resposta HTTP é 204 No Content
        assertEquals(204, response.getStatusCodeValue());
    }

    @Test
    void deveAtualizarStatusConfirmado() {
        Long id = 1L;

        // Executa a chamada ao método
        ResponseEntity<Void> response = reservaVagaController.atualisaStatusConfirmado(id);

        // Verifica se o caso de uso foi executado corretamente
        verify(updateStatusConfirmadoReservaMesaUseCase, times(1)).execute(any());

        // Verifica se a resposta HTTP é 204 No Content
        assertEquals(204, response.getStatusCodeValue());
    }

    @Test
    void deveAtualizarStatusEncerrado() {
        Long id = 1L;

        // Executa a chamada ao método
        ResponseEntity<Void> response = reservaVagaController.atualizaRestaurantePeloId(id);

        // Verifica se o caso de uso foi executado corretamente
        verify(updateStatusEncerradoReservaMesaUseCase, times(1)).execute(any());

        // Verifica se a resposta HTTP é 204 No Content
        assertEquals(204, response.getStatusCodeValue());
    }

    @Test
    void deveListarReservasComPagina() {
        ReservaMesa reserva1 = new ReservaMesa(1L, LocalDate.now(), 1L, "Cliente 1", "1234567890", ReservaMesaEntity.StatusReserva.PENDENTE);
        ReservaMesa reserva2 = new ReservaMesa(2L, LocalDate.now(), 1L, "Cliente 2", "0987654321", ReservaMesaEntity.StatusReserva.PENDENTE);

        // Simula o retorno da lista de reservas
        List<ReservaMesa> reservas = List.of(reserva1, reserva2);
        ReservaMesaPaginadoDTO reservaMesaPaginadoDTO = new ReservaMesaPaginadoDTO();
        when(getAllReservaMesaUseCase.execute(1, 10, 1L)).thenReturn(reservas);
        when(reservaVagaMapper.toDTO(reservas)).thenReturn(reservaMesaPaginadoDTO);

        // Executa a chamada ao método
        ResponseEntity<ReservaMesaPaginadoDTO> response = reservaVagaController.listaReserva(1, 10, 1L);

        // Verifica se o caso de uso foi executado corretamente
        verify(getAllReservaMesaUseCase, times(1)).execute(1, 10, 1L);

        // Verifica se a resposta contém o DTO de reservas
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(reservaMesaPaginadoDTO, response.getBody());
    }

    @Test
    void deveConsultarReservaPorId() {
        Long id = 1L;
        ReservaMesa reserva = new ReservaMesa(id, LocalDate.now(), 1L, "Cliente 1", "1234567890", ReservaMesaEntity.StatusReserva.PENDENTE);
        ReservaMesaDTO reservaDTO = new ReservaMesaDTO(id, LocalDate.now(), 1L, "Cliente 1", "1234567890", ReservaMesaEntity.StatusReserva.PENDENTE);

        // Simula a consulta da reserva
        when(getReservaMesaById.execute(id)).thenReturn(reserva);
        when(reservaVagaMapper.toDTO(reserva)).thenReturn(reservaDTO);

        // Executa a chamada ao método
        ResponseEntity<ReservaMesaDTO> response = reservaVagaController.reservaMesaId(id);

        // Verifica se o caso de uso foi executado corretamente
        verify(getReservaMesaById, times(1)).execute(id);

        // Verifica se a resposta contém o DTO da reserva
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(reservaDTO, response.getBody());
    }
}
