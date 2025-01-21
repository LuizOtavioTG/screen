package br.com.apredendojava.screenmatch.dto;

import br.com.apredendojava.screenmatch.model.Serie;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.time.LocalDate;

public record EpisodioDTO(Integer temporada,
        String titulo,
        Integer numero,
        Double avaliacao,
        LocalDate dataLacamento) {
}
