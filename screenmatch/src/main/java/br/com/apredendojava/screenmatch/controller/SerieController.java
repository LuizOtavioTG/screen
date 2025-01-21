package br.com.apredendojava.screenmatch.controller;

import br.com.apredendojava.screenmatch.dto.EpisodioDTO;
import br.com.apredendojava.screenmatch.dto.SerieDTO;
import br.com.apredendojava.screenmatch.service.SerieService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/series")
public class SerieController {

    private final SerieService servico;

    public SerieController(SerieService servico) {
        this.servico = servico;
    }


    @GetMapping()
    public List<SerieDTO> obterSeries(){
        return servico.obterTodasAsSeries();
    }

    @GetMapping("/top5")
    public List<SerieDTO> obterTop5Series(){
        return servico.obterTop5Series();
    }

    @GetMapping("/lancamentos")
    public List<SerieDTO> obterEpisodiosLacamentos(){
        return servico.obterEpisodiosLacamentos();
    }

    @GetMapping("/{id}")
    public  SerieDTO obterPorId (@PathVariable Long id){
        return servico.obterPorId(id);
    }
    @GetMapping("/{id}/temporadas/todas")
    public List<EpisodioDTO> obterTodasTemporadas (@PathVariable Long id){
        return servico.obterTodasTemporadas(id);
    }

    @GetMapping("/{id}/temporadas/{numero}")
    public List<EpisodioDTO> obterTemporadasEspecificas (@PathVariable Long id, @PathVariable Long numero){
        return servico.obterTemporadasEspecificas(id, numero);
    }
    @RequestMapping(value = "/categoria/{nomeGenero}", method = RequestMethod.GET)
    public List<SerieDTO> obterSeriesPorCategoria (@PathVariable String nomeGenero){
        return servico.obterSeriesPorCategoria(nomeGenero);
    }


}
