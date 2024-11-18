package br.com.apredendojava.screenmatch.Principal;

import br.com.apredendojava.screenmatch.model.*;
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
    private  Scanner scan = new Scanner(System.in);
    private List<DadosSerie> dadosSeries = new ArrayList<>();
    public void exibemenu(){

        //***EXIBE OS DADOS DE UMA SÉRIE
//        System.out.println("Digite o nome da série");
//        String nomeSerie= scan.nextLine();
//
//        String json = consumoApi.obterDados(ENDERECO + nomeSerie.replace(" ", "+")+ APIKEY);
//        DadosSerie dadosSerie = conversor.obterDados(json, DadosSerie.class);
//        System.out.println(dadosSerie);

        //***CRIA UMA LIST COM OS DADOS DE TODAS AS TEMPORADAS E DEPOIS IMPRIME NO TERMINAL
//        List<DadosTemporadas> listaTemporadas = new ArrayList<>();
//        for (int i = 1; i <= dadosSerie.totalTemporadas(); i++) {
//			json = consumoApi.obterDados(ENDERECO + nomeSerie.replace(" ", "+")
//                    + "&season=" + i + "&apikey=6585022c");
//			DadosTemporadas dadosTemporadas = conversor.obterDados(json, DadosTemporadas.class);
//			listaTemporadas.add(dadosTemporadas);
//		}
//		listaTemporadas.forEach(System.out::println);

        //***DUAS FORMAS DE ITERAR TEMPORADAS E EPISÓDIOS (UMA DA FORMA TRADICIONAL A OUTRA USANDO FUNÇÕES LAMBDA)
////        for (int i = 0; i < dadosSerie.totalTemporadas(); i++) {
////            List<DadosEpisodios> listEpTemp = listaTemporadas.get(i).episodiosList();
////            for (int j = 0; j < listEpTemp.size(); j++) {
////                System.out.println(listEpTemp.get(j).titulo());
////            }
////        }
//        listaTemporadas.forEach(t ->t.episodiosList().forEach(e -> System.out.println(e.titulo())));

        //***CRIANDO LIST COM TODOS OS EPISÓDIOS
//        List<DadosEpisodios> dadosEpisodiosList = listaTemporadas.stream()
//                .flatMap(t -> t.episodiosList().stream())
//                .collect(Collectors.toList());

        //***LISTANDO TOP 5 EPISÓDIOS
//        System.out.println("\n Lista top 5 episódios:");
//        dadosEpisodiosList.stream()
//                .filter(e -> !e.avaliacao().equalsIgnoreCase("N/A"))
//                .sorted(Comparator.comparing(DadosEpisodios::avaliacao).reversed())
//                .limit(5)
//                .forEach(System.out::println);

        //***TRANFORMANDO LISTA DE TEMPORADAS EM LISTA DE EPISÓDIOS
//        List<Episodio> episodios = listaTemporadas.stream()
//                .flatMap(t -> t.episodiosList().stream()
//                .map(d -> new Episodio(t.numero(), d))
//                ).collect(Collectors.toList());
//
//        episodios.forEach(System.out::println);

        //***ENCONTRAR UM EPISÓDIO PELO NOME
//        System.out.println("Digite o nome do episódio que você deseja buscar:");
//        var trechoEpisodio = scan.nextLine();
//
//        Optional<Episodio> episodioOptional = episodios.stream()
//                .filter(e -> e.getTitulo().toUpperCase().contains(trechoEpisodio.toUpperCase()))
//                .findFirst();
//        if (episodioOptional.isPresent()){
//            System.out.println("Episódio encontrado");
//            System.out.println("Temporada: " + episodioOptional.get());
//        } else{
//            System.out.println("Episódio não encontrado");
//        }

        //***LISTA DE EPISÓDIOS A PARTIR DE UM DETERMINADO ANO
//        System.out.println("A partir de qual ano você deseja ver os episódios?");
//        var ano = scan.nextInt();
//        scan.nextLine();
//
//        LocalDate dataBusca = LocalDate.of(ano,1,1);
//
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
//
//        episodios.stream()
//                .filter(e -> e.getDataLacamento() != null && e.getDataLacamento().isAfter(dataBusca))
//                .forEach(e ->System.out.println(
//                        "Temporada: " + e.getTemporada() +
//                                " Episódio: " + e.getTitulo() +
//                                " Data de Lançamento: " + e.getDataLacamento().format(formatter)
//                ));

        //***AVALIAÇÃO POR TEMPORADA
//        Map<Integer, Double> avaliacoesPorTemporada = episodios.stream()
//                .filter(e -> e.getAvaliacao() > 0.0)
//                .collect(Collectors.groupingBy(Episodio::getTemporada,
//                        Collectors.averagingDouble(Episodio::getAvaliacao)));
//        System.out.println("Avaliações por Temporada: "+ avaliacoesPorTemporada);

        //***ESTATÍSTICA DE EPISÓDIOS
//        DoubleSummaryStatistics est = episodios.stream()
//                .filter(e -> e.getAvaliacao() > 0.0)
//                .collect(Collectors.summarizingDouble(Episodio::getAvaliacao));
//        System.out.println("Média: " + est.getAverage());
//        System.out.println("Melhor nota:" + est.getMax());
//        System.out.println("Pior nota:" + est.getMin());
//        System.out.println("Quantidade de ep.:" + est.getCount());
        int opcao;
        do {
            var menu = """
                    1 - Buscar séries
                    2 - Buscar episódios
                    3 - Listar séries buscadas
                                    
                    0 - Sair                                 
                    """;

            System.out.println(menu);
            opcao = scan.nextInt();
            scan.nextLine();

            switch (opcao) {
                case 1:
                    buscarSerieWeb();
                    System.out.println("\n");
                    break;
                case 2:
                    buscarEpisodioPorSerie();
                    System.out.println("\n");
                    break;
                case 3:
                    listarSeriesBuscadas();
                    System.out.println("\n");
                    break;
                case 0:
                    System.out.println("Saindo...");
                    System.out.println("\n");
                    break;
                default:
                    System.out.println("Opção inválida");
                    System.out.println("\n");
            }
        }while (opcao !=0);
    }
    private void buscarSerieWeb() {
        DadosSerie dados = getDadosSerie();
        dadosSeries.add(dados);
        System.out.println(dados);
    }

    private DadosSerie getDadosSerie() {
        System.out.println("Digite o nome da série para busca");
        var nomeSerie = scan.nextLine();
        var json = consumoApi.obterDados(ENDERECO + nomeSerie.replace(" ", "+") + APIKEY);
        DadosSerie dados = conversor.obterDados(json, DadosSerie.class);
        return dados;
    }

    private void buscarEpisodioPorSerie(){
        DadosSerie dadosSerie = getDadosSerie();
        List<DadosTemporadas> temporadas = new ArrayList<>();

        for (int i = 1; i <= dadosSerie.totalTemporadas(); i++) {
            var json = consumoApi.obterDados(ENDERECO + dadosSerie.titulo().replace(" ", "+") + "&season=" + i + APIKEY);
            DadosTemporadas dadosTemporada = conversor.obterDados(json, DadosTemporadas.class);
            temporadas.add(dadosTemporada);
        }
        temporadas.forEach(System.out::println);
    }
    private void listarSeriesBuscadas(){
        List<Serie> serieList = new ArrayList<>();
        serieList = dadosSeries.stream()
                        .map(d -> new Serie(d))
                                .collect(Collectors.toList());
        serieList.stream()
                .sorted(Comparator.comparing(Serie::getGenero))
                .forEach(System.out::println);
    }
}
