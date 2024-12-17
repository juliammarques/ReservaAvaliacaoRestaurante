package com.restaurante.reservaAvaliacao.domain.pagination;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.restaurante.reservaAvaliacao.domain.entity.AvaliacaoRestaurante;
import com.restaurante.reservaAvaliacao.domain.entity.ReservaMesa;
import com.restaurante.reservaAvaliacao.domain.entity.Restaurante;

public record Pagination<T>(int page, int size, int total, int totalPages, List<T> items) {

    public <R> Pagination<R> map(final Function<T, R> mapper) {
        final var mappedItems = this.items.stream().map(mapper).collect(Collectors.toList());
        final var totalPages = (total + size - 1) / size;
        return new Pagination<>(page, size, total, totalPages, mappedItems);
    }
    

    public static Pagination<Restaurante> from(int page, int size, int total, int totalPages, List<Restaurante> items) {
        return new Pagination<>(page, size, total, totalPages, items);
    }
    
    public static Pagination<ReservaMesa> from(List<ReservaMesa> items,int page, int size, int total, int totalPages) {
        return new Pagination<>(page, size, total, totalPages, items);
    }
    
    public static Pagination<AvaliacaoRestaurante> fromAvaliacao(List<AvaliacaoRestaurante> items,int page, int size, int total, int totalPages) {
        return new Pagination<>(page, size, total, totalPages, items);
    }

}
