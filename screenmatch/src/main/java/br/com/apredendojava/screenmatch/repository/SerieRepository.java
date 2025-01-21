package br.com.apredendojava.screenmatch.repository;

import br.com.apredendojava.screenmatch.model.Categoria;
import br.com.apredendojava.screenmatch.model.Episodio;
import br.com.apredendojava.screenmatch.model.Serie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface SerieRepository extends JpaRepository<Serie, Long> {
    Optional<Serie> findAllByTituloContainingIgnoreCase(String nomeSerie);

    List<Serie> findByAtoresContainingIgnoreCaseAndAvaliacaoImdbGreaterThanEqual(String nomeAtor, Double avaliacao);

    List<Serie> findTop5ByOrderByAvaliacaoImdbDesc();

    List<Serie> findAllByGenero(Categoria categoria);


    // 3 forma de  fazer a busca, com derivedQueries, nativeQuery e com JPQL
    List<Serie> findAllByTotalTemporadasLessThanEqualAndAvaliacaoImdbGreaterThanEqual(Integer totalTemporadas, Double avalicao);
    //@Query(value = "select * from series WHERE series.totalTemporadas <= 5 AND series.avaliacaoImdb >= 7.5", nativeQuery = true)
    //List<Serie> seriesPorTemporadaEAValiacao();
    @Query("select s from Serie s WHERE s.totalTemporadas <= :totalTemporadas AND s.avaliacaoImdb >= :avaliacao")// os dois pontos indica que são os valores dos parametros
    List<Serie> seriesPorTemporadaEAValiacao(int totalTemporadas, double avaliacao);

    @Query("SELECT e FROM Serie s JOIN s.episodioList e WHERE e.titulo ILIKE %:trechoEp%")
    List<Episodio> episodioPorTrecho(String trechoEp);

    @Query("SELECT e FROM Serie s JOIN s.episodioList e WHERE s = :serie ORDER BY e.avaliacao DESC LIMIT 5")
    List<Episodio> topEpisodiosPorSerie(Serie serie);

    @Query("SELECT e FROM Serie s JOIN s.episodioList e WHERE s = :serie AND YEAR(e.dataLacamento) >= :ano")
    List<Episodio> episodioDepoisDeUmaData(Serie serie,int ano);

    @Query("SELECT s FROM Serie s " +
            "JOIN s.episodioList e " +
            "GROUP BY s " +
            "ORDER BY MAX(e.dataLacamento) DESC LIMIT 5")
    List<Serie> lancamentosMaisRecentes();

    Optional<Serie> findById (Long id);

    @Query("SELECT e FROM Serie s JOIN s.episodioList e WHERE s.id = :id AND e.temporada = :numero")
    List<Episodio> obterEpisodiosPorTemporada(Long id, Long numero);


}
