package br.com.apredendojava.screenmatch.dto;

import br.com.apredendojava.screenmatch.model.Categoria;


public record SerieDTO(Long id,
                       String titulo,
                       Integer totalTemporadas,
                       Double avaliacaoImdb,
                       Categoria genero,
                       String atores,
                       String poster,
                       String sinopse) {

}
