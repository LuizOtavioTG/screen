package br.com.apredendojava.screenmatch.service;

import br.com.apredendojava.screenmatch.dto.EpisodioDTO;
import br.com.apredendojava.screenmatch.dto.SerieDTO;
import br.com.apredendojava.screenmatch.model.Categoria;
import br.com.apredendojava.screenmatch.model.Episodio;
import br.com.apredendojava.screenmatch.model.Serie;
import br.com.apredendojava.screenmatch.repository.SerieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SerieService {
    @Autowired
    private SerieRepository repositorio;

    private List<SerieDTO> converteDados (List<Serie> series){
        return series.stream()
                .map(s -> new SerieDTO(s.getId(), s.getTitulo(), s.getTotalTemporadas(), s.getAvaliacaoImdb(), s.getGenero(), s.getAtores(), s.getPoster(), s.getSinopse()))
                .collect(Collectors.toList());
    }

    public List<SerieDTO> obterTodasAsSeries(){
        return converteDados(repositorio.findAll());

    }

    public List<SerieDTO> obterTop5Series() {
        return converteDados(repositorio.findTop5ByOrderByAvaliacaoImdbDesc());

    }
    public List<SerieDTO> obterEpisodiosLacamentos() {
        return converteDados(repositorio.lancamentosMaisRecentes());

    }

    public SerieDTO obterPorId(Long id) {
        Optional<Serie> serieOptional = repositorio.findById(id);
        if(serieOptional.isPresent()){
            Serie s = serieOptional.get();
            return new SerieDTO(s.getId(), s.getTitulo(), s.getTotalTemporadas(), s.getAvaliacaoImdb(), s.getGenero(), s.getAtores(), s.getPoster(), s.getSinopse());
        }
        return null;

    }

    public List<EpisodioDTO> obterTodasTemporadas(Long id) {
        Optional<Serie> serieOptional = repositorio.findById(id);
        if(serieOptional.isPresent()){
            Serie s = serieOptional.get();
            return s.getEpisodioList().stream()
                    .map(e -> new EpisodioDTO(e.getTemporada(),e.getTitulo(),e.getNumero(),e.getAvaliacao(),e.getDataLacamento()))
                    .collect(Collectors.toList());
        }
        return null;

    }

    public List<EpisodioDTO> obterTemporadasEspecificas(Long id, Long numero) {
        return repositorio.obterEpisodiosPorTemporada(id, numero).stream()
                .map(e -> new EpisodioDTO(e.getTemporada(),e.getTitulo(),e.getNumero(),e.getAvaliacao(),e.getDataLacamento()))
                .collect(Collectors.toList());
    }

    public List<SerieDTO> obterSeriesPorCategoria(String nomeGenero) {
        Categoria categoria = Categoria.fromPortugues(nomeGenero);
        return converteDados(repositorio.findAllByGenero(categoria));
    }
}
