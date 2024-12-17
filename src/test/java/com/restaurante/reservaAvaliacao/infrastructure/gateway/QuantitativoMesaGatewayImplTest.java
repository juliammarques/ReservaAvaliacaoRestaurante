package com.restaurante.reservaAvaliacao.infrastructure.gateway;

import com.restaurante.reservaAvaliacao.domain.entity.QuantitativoMesa;
import com.restaurante.reservaAvaliacao.infrastructure.persistence.entity.QuantitativoMesaEntity;
import com.restaurante.reservaAvaliacao.infrastructure.persistence.repository.IQuantitativoMesaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class QuantitativoMesaGatewayImplTest {

    @Mock
    private IQuantitativoMesaRepository repository; // Mock do repositório

    @InjectMocks
    private QuantitativoMesaGatewayImpl quantitativoMesaGateway; // Classe que será testada

    private QuantitativoMesa quantitativoMesa;

    @BeforeEach
    void setUp() {
        // Inicializa os mocks do Mockito
        MockitoAnnotations.openMocks(this);

        // Setup de um objeto QuantitativoMesa para os testes
        quantitativoMesa = new QuantitativoMesa(1L, "Mesa para 4 pessoas", 10L);
    }

    @Test
    void testCreateQuantitativoMesa() {
        // Arrange
        when(repository.save(any(QuantitativoMesaEntity.class))).thenReturn(QuantitativoMesaEntity.of(quantitativoMesa));

        // Act
        quantitativoMesaGateway.createQuantitativoMesa(quantitativoMesa);

        // Assert
        verify(repository, times(1)).save(any(QuantitativoMesaEntity.class)); // Verifica se o save foi chamado
    }

    @Test
    void testUpdateQuantitativoMesa() {
        // Arrange
        when(repository.save(any(QuantitativoMesaEntity.class))).thenReturn(QuantitativoMesaEntity.of(quantitativoMesa));

        // Act
        quantitativoMesaGateway.updateQuantitativoMesa(quantitativoMesa);

        // Assert
        verify(repository, times(1)).save(any(QuantitativoMesaEntity.class)); // Verifica se o save foi chamado
    }

    @Test
    void testDeleteQuantitativoMesa() {
        // Arrange
        doNothing().when(repository).deleteById(1L);

        // Act
        quantitativoMesaGateway.deleteQuantitativoMesa(1L);

        // Assert
        verify(repository, times(1)).deleteById(1L); // Verifica se o deleteById foi chamado
    }

    @Test
    void testGetQuantitativoMesaByIdFound() {
        // Arrange
        when(repository.findById(1L)).thenReturn(Optional.of(QuantitativoMesaEntity.of(quantitativoMesa)));

        // Act
        Optional<QuantitativoMesa> result = quantitativoMesaGateway.getQuantitativoMesaById(1L);

        // Assert
        assertTrue(result.isPresent()); // Verifica que o resultado não é vazio
        assertEquals(quantitativoMesa.getSeqQuantitativoMesaEntity(), result.get().getSeqQuantitativoMesaEntity()); // Verifica o seqQuantitativoMesaEntity
        assertEquals(quantitativoMesa.getSeqRestaurante(), result.get().getSeqRestaurante()); // Verifica o seqRestaurante
        assertEquals(quantitativoMesa.getTamanhoMesa(), result.get().getTamanhoMesa()); // Verifica o tamanho da mesa
        assertEquals(quantitativoMesa.getQuantidadeDisponivel(), result.get().getQuantidadeDisponivel()); // Verifica a quantidade disponível
    }

    @Test
    void testGetQuantitativoMesaByIdNotFound() {
        // Arrange
        when(repository.findById(1L)).thenReturn(Optional.empty());

        // Act
        Optional<QuantitativoMesa> result = quantitativoMesaGateway.getQuantitativoMesaById(1L);

        // Assert
        assertFalse(result.isPresent()); // Verifica que o resultado está vazio, ou seja, não encontrou o item
    }
}
