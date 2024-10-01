package br.com.apredendojava.screenmatch.Principal;

import br.com.apredendojava.screenmatch.model.DadosEpisodios;
import br.com.apredendojava.screenmatch.model.DadosSerie;
import br.com.apredendojava.screenmatch.model.DadosTemporadas;
import br.com.apredendojava.screenmatch.service.ConsumoApi;
import br.com.apredendojava.screenmatch.service.ConverteDados;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Principal {

    private static final String ENDERECO = "https://www.omdbapi.com/?t=";
    private static final String APIKEY = "&apikey=6585022c";
    private static ConsumoApi consumoApi = new ConsumoApi();
    private static ConverteDados conversor = new ConverteDados();
    private Scanner scan = new Scanner(System.in);
    public void exibemenu(){
        System.out.println("Digite o nome da série");
        String nomeSerie= scan.nextLine();

        String json = consumoApi.obterDados(ENDERECO + nomeSerie.replace(" ", "+")+ APIKEY);
        DadosSerie dadosSerie = conversor.obterDados(json, DadosSerie.class);
        System.out.println(dadosSerie);

        List<DadosTemporadas> listaTemporadas = new ArrayList<>();
        for (int i = 1; i <= dadosSerie.totalTemporadas(); i++) {
			json = consumoApi.obterDados(ENDERECO + nomeSerie.replace(" ", "+") +"&season=" + i + "&apikey=6585022c");
			DadosTemporadas dadosTemporadas = conversor.obterDados(json, DadosTemporadas.class);
			listaTemporadas.add(dadosTemporadas);
		}
		listaTemporadas.forEach(System.out::println);

//        for (int i = 0; i < dadosSerie.totalTemporadas(); i++) {
//            List<DadosEpisodios> listEpTemp = listaTemporadas.get(i).episodiosList();
//            for (int j = 0; j < listEpTemp.size(); j++) {
//                System.out.println(listEpTemp.get(j).titulo());
//            }
//        }
        listaTemporadas.forEach(t ->t.episodiosList().forEach(e -> System.out.println(e.titulo())));
    }
}
