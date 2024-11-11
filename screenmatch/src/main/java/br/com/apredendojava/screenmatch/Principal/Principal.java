package br.com.apredendojava.screenmatch.Principal;

import br.com.apredendojava.screenmatch.model.DadosEpisodios;
import br.com.apredendojava.screenmatch.model.DadosSerie;
import br.com.apredendojava.screenmatch.model.DadosTemporadas;
import br.com.apredendojava.screenmatch.model.Episodio;
import br.com.apredendojava.screenmatch.service.ConsumoApi;
import br.com.apredendojava.screenmatch.service.ConverteDados;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

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

//
//        for (int i = 0; i < dadosSerie.totalTemporadas(); i++) {
//            List<DadosEpisodios> listEpTemp = listaTemporadas.get(i).episodiosList();
//            for (int j = 0; j < listEpTemp.size(); j++) {
//                System.out.println(listEpTemp.get(j).titulo());
//            }
//        }
        listaTemporadas.forEach(t ->t.episodiosList().forEach(e -> System.out.println(e.titulo())));

        List<DadosEpisodios> dadosEpisodiosList = listaTemporadas.stream()
                .flatMap(t -> t.episodiosList().stream())
                .collect(Collectors.toList());

        System.out.println("\n Lista top 5 episódios:");
        dadosEpisodiosList.stream()
                .filter(e -> !e.avaliacao().equalsIgnoreCase("N/A"))
                .sorted(Comparator.comparing(DadosEpisodios::avaliacao).reversed())
                .limit(5)
                .forEach(System.out::println);

        List<Episodio> episodios = listaTemporadas.stream()
                .flatMap(t -> t.episodiosList().stream()
                .map(d -> new Episodio(t.numero(), d))
                ).collect(Collectors.toList());

        episodios.forEach(System.out::println);

        System.out.println("Digite o nome do episódio que você deseja buscar:");
        var trechoEpisodio = scan.nextLine();

        Optional<Episodio> episodioOptional = episodios.stream()
                .filter(e -> e.getTitulo().toUpperCase().contains(trechoEpisodio.toUpperCase()))
                .findFirst();
        if (episodioOptional.isPresent()){
            System.out.println("Episódio encontrado");
            System.out.println("Temporada: " + episodioOptional.get());
        } else{
            System.out.println("Episódio não encontrado");
        }

        System.out.println("A partir de qual ano você deseja ver os episódios?");
        var ano = scan.nextInt();
        scan.nextLine();

        LocalDate dataBusca = LocalDate.of(ano,1,1);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        episodios.stream()
                .filter(e -> e.getDataLacamento() != null && e.getDataLacamento().isAfter(dataBusca))
                .forEach(e ->System.out.println(
                        "Temporada: " + e.getTemporada() +
                                " Episódio: " + e.getTitulo() +
                                " Data de Lançamento: " + e.getDataLacamento().format(formatter)
                ));
    }
}
