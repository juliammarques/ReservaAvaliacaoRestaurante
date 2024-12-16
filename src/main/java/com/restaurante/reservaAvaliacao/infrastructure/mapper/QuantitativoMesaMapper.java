package com.restaurante.reservaAvaliacao.infrastructure.mapper;

import com.restaurante.reservaAvaliacao.application.dto.CreateUpdateQuantitativoMesaDTO;
import com.restaurante.reservaAvaliacao.application.useCase.CreateQuantativoMesaUseCase;
import com.restaurante.reservaAvaliacao.application.useCase.UpdateQuantitativoMesaUseCase;

public class QuantitativoMesaMapper {

	public CreateQuantativoMesaUseCase.Input from(final CreateUpdateQuantitativoMesaDTO dto) {
        return new CreateQuantativoMesaUseCase.Input(dto.getSeqRestaurante(),dto.getTamanhoMesa(),dto.getQuantidadeDisponivel());
    }

    public UpdateQuantitativoMesaUseCase.Input from(final Long id, final CreateUpdateQuantitativoMesaDTO dto) {
        return new UpdateQuantitativoMesaUseCase.Input(id,dto.getSeqRestaurante(),dto.getTamanhoMesa(),dto.getQuantidadeDisponivel());
    }
}
