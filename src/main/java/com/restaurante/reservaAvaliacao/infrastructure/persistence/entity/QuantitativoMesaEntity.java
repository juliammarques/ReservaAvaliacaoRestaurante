package com.restaurante.reservaAvaliacao.infrastructure.persistence.entity;

import java.util.ArrayList;
import java.util.List;

import com.restaurante.reservaAvaliacao.domain.entity.QuantitativoMesa;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="QuantitativoMesaEntity")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class QuantitativoMesaEntity {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long seqQuantitativoMesaEntity;
	
	@NotBlank
	private Long seqRestaurante;
	
	@NotBlank
	private String tamanhoMesa;
	
	@NotBlank
	private Long  quantidadeDisponivel;
	
	/*@NotBlank
	private String indDisponivel;*/
	
	public static QuantitativoMesaEntity of (QuantitativoMesa quantitativoMesa) {
		return new QuantitativoMesaEntity (quantitativoMesa.getSeqQuantitativoMesaEntity(),quantitativoMesa.getSeqRestaurante(),quantitativoMesa.getTamanhoMesa(), quantitativoMesa.getQuantidadeDisponivel());
	}
	
	public static List<QuantitativoMesaEntity> list (List<QuantitativoMesa> quantitativoMesa) {
		List<QuantitativoMesaEntity> quantitativoEntity = new ArrayList<QuantitativoMesaEntity>();
				quantitativoMesa.forEach(x -> quantitativoEntity.add(of(x)));
		return quantitativoEntity;
	}
	
	public QuantitativoMesa toQuantitativo() {
        return QuantitativoMesa.with(this.seqQuantitativoMesaEntity, this.seqRestaurante, this.tamanhoMesa, this.quantidadeDisponivel);
    }
	
}
