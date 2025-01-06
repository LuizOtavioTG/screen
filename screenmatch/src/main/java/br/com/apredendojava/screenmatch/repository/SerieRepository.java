package br.com.apredendojava.screenmatch.repository;

import br.com.apredendojava.screenmatch.model.Categoria;
import br.com.apredendojava.screenmatch.model.Serie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface    SerieRepository extends JpaRepository<Serie, Long> {
    Optional<List<Serie>> findAllByTituloContainingIgnoreCase(String nomeSerie);

    List<Serie> findByAtoresContainingIgnoreCaseAndAvaliacaoImdbGreaterThanEqual(String nomeAtor, Double avaliacao);
    List<Serie> findTop5ByOrderByAvaliacaoImdbDesc();
    List<Serie> findAllByGenero (Categoria categoria);
}
