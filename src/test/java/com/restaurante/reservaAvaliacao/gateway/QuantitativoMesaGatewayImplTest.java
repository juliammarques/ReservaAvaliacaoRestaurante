package com.restaurante.reservaAvaliacao.gateway;

import com.restaurante.reservaAvaliacao.domain.entity.QuantitativoMesa;
import com.restaurante.reservaAvaliacao.infrastructure.gateway.QuantitativoMesaGatewayImpl;
import com.restaurante.reservaAvaliacao.infrastructure.persistence.entity.QuantitativoMesaEntity;
import com.restaurante.reservaAvaliacao.infrastructure.persistence.repository.IQuantitativoMesaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

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
        quantitativoMesa = new QuantitativoMesa();
        quantitativoMesa.setSeqQuantitativoMesaEntity(1L);
        quantitativoMesa.setSeqRestaurante(1L);
        quantitativoMesa.setTamanhoMesa(4L);
        quantitativoMesa.setQuantidadeDisponivel(10L);
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
        // Mock de qualquer ação de atualização que você precisar (se implementado)
        doNothing().when(repository).save(any(QuantitativoMesaEntity.class));

        // Act
        quantitativoMesaGateway.updateQuantitativoMesa(quantitativoMesa);

        // Assert
        verify(repository, times(1)).save(any(QuantitativoMesaEntity.class)); // Verifica se o save foi chamado para atualização
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
}
