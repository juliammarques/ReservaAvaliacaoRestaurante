package com.restaurante.reservaAvaliacao.domain.service;

import com.restaurante.reservaAvaliacao.domain.entity.QuantitativoMesa;
import com.restaurante.reservaAvaliacao.domain.exception.QuantitativoMesaNaoEncontrado;
import com.restaurante.reservaAvaliacao.domain.gateway.QuantitativoMesaGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class QuantitativoMesaDomainServiceTest {

    @Mock
    private QuantitativoMesaGateway gateway;

    @InjectMocks
    private QuantitativoMesaDomainService service;

    private QuantitativoMesa quantitativoMesa;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        // Criação do objeto QuantitativoMesa para ser usado nos testes
        quantitativoMesa = new QuantitativoMesa(1L, 1L, "Grande", 10L);
    }

    @Test
    void testRegistraQuantitativoInicial() {
        // Act: Chama o método para registrar o quantitativo inicial
        service.registraQuantitativoInicial(quantitativoMesa);

        // Assert: Verifica se o método 'createQuantitativoMesa' foi chamado no gateway
        verify(gateway, times(1)).createQuantitativoMesa(quantitativoMesa);
    }

    @Test
    void testAtualizaQuantitativoMesa() {
        // Arrange: Simula o comportamento do método getQuantitativoMesaById para retornar um quantitativo válido
        when(gateway.getQuantitativoMesaById(quantitativoMesa.getSeqQuantitativoMesaEntity()))
                .thenReturn(Optional.of(quantitativoMesa));

        // Act: Chama o método para atualizar o quantitativo
        service.atualizaQuantitativoMesa(quantitativoMesa);

        // Assert: Verifica se o método 'updateQuantitativoMesa' foi chamado no gateway
        verify(gateway, times(1)).updateQuantitativoMesa(quantitativoMesa);
    }

    @Test
    void testAtualizaQuantitativoMesa_QuantitativoNaoEncontrado() {
        // Arrange: Simula o comportamento do método getQuantitativoMesaById para retornar Optional.empty()
        when(gateway.getQuantitativoMesaById(quantitativoMesa.getSeqQuantitativoMesaEntity()))
                .thenReturn(Optional.empty());

        // Act & Assert: Verifica se o método lança a exceção QuantitativoMesaNaoEncontrado quando o quantitativo não é encontrado
        assertThrows(QuantitativoMesaNaoEncontrado.class, () -> service.atualizaQuantitativoMesa(quantitativoMesa));

        // Verifica se o método 'updateQuantitativoMesa' não foi chamado no gateway
        verify(gateway, times(0)).updateQuantitativoMesa(quantitativoMesa);
    }

    @Test
    void testDeleteQuantitativo() {
        // Arrange: Simula o comportamento do método getQuantitativoMesaById para retornar um quantitativo válido
        when(gateway.getQuantitativoMesaById(quantitativoMesa.getSeqQuantitativoMesaEntity()))
                .thenReturn(Optional.of(quantitativoMesa));

        // Act: Chama o método para deletar o quantitativo
        service.deleteQuantitativo(quantitativoMesa.getSeqQuantitativoMesaEntity());

        // Assert: Verifica se o método 'deleteQuantitativoMesa' foi chamado no gateway
        verify(gateway, times(1)).deleteQuantitativoMesa(quantitativoMesa.getSeqQuantitativoMesaEntity());
    }

    @Test
    void testDeleteQuantitativo_QuantitativoNaoEncontrado() {
        // Arrange: Simula o comportamento do método getQuantitativoMesaById para retornar Optional.empty()
        when(gateway.getQuantitativoMesaById(quantitativoMesa.getSeqQuantitativoMesaEntity()))
                .thenReturn(Optional.empty());

        // Act & Assert: Verifica se o método lança a exceção QuantitativoMesaNaoEncontrado quando o quantitativo não é encontrado
        assertThrows(QuantitativoMesaNaoEncontrado.class, () -> service.deleteQuantitativo(quantitativoMesa.getSeqQuantitativoMesaEntity()));

        // Verifica se o método 'deleteQuantitativoMesa' não foi chamado no gateway
        verify(gateway, times(0)).deleteQuantitativoMesa(quantitativoMesa.getSeqQuantitativoMesaEntity());
    }
}
