package br.com.apredendojava.screenmatch.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosSerie(@JsonProperty ("Title") String titulo,
                         @JsonProperty ("totalSeasons")Integer totalTemporadas,
                         @JsonProperty("imdbRating") String avaliacaoImdb,
                         @JsonProperty("Genre") String genero,
                         @JsonProperty("Actors") String atores,
                         @JsonProperty("Poster") String poster,
                         @JsonProperty("Plot") String sinopse) {
}
