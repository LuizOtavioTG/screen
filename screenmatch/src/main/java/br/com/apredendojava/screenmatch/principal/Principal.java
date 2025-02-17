package br.com.apredendojava.screenmatch.principal;

import br.com.apredendojava.screenmatch.model.*;
import br.com.apredendojava.screenmatch.repository.SerieRepository;
import br.com.apredendojava.screenmatch.service.ConsumoApi;
import br.com.apredendojava.screenmatch.service.ConverteDados;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class Principal {

    private static final String ENDERECO = "https://www.omdbapi.com/?t=";
    private static final String APIKEY = System.getenv("IMDB_API_KEY").trim();
    private static ConsumoApi consumoApi = new ConsumoApi();
    private static ConverteDados conversor = new ConverteDados();
    private  Scanner scan = new Scanner(System.in);
    private List<DadosSerie> dadosSeries = new ArrayList<>();
    private SerieRepository repositorio;
    private List<Serie> serieList = new ArrayList<>();
    private Optional<Serie> serieBusca;

    public Principal(SerieRepository repositorio) {
        this.repositorio = repositorio;
    }

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
                    4 - Buscar série por título
                    5 - Buscar séries por ator
                    6 - Top 5 séries
                    7 - Buscar séries por categoria
                    8 - Buscar série com máximo temporadas e mínimo de avalição
                    9 - Buscar episódio por trecho
                    10 - Buscar Top 5 episódios por série
                    11 - Buscar episódios a partir de uma data
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
                case 4:
                    buscarSeriePorTitulo();
                    break;
                case 5:
                    buscarSeriesPorAtor();
                    break;
                case 6:
                    buscarTop5Series();
                    break;
                case 7 :
                    buscarSeriesPorCategoria();
                    break;
                case 8:
                    buscarSeriesPorMaxTemporadasEMinAvalicao();
                    break;
                case 9:
                    buscarEpisodioPorTrecho();
                    break;
                case 10:
                    buscarTop5EpisodiosPorSerie();
                    break;
                case 11:
                    buscarEpisodioDepoisDeUmaData();
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
        Serie serie = new Serie(dados);
        repositorio.save(serie);
        System.out.println(dados);
    }

    private DadosSerie getDadosSerie() {
        System.out.println("Digite o nome da série para busca");
        var nomeSerie = scan.nextLine();
        var json = consumoApi.obterDados(ENDERECO + nomeSerie.replace(" ", "+") + APIKEY);
        DadosSerie dados = conversor.obterDados(json, DadosSerie.class);
        return dados;
    }

    private void buscarEpisodioPorSerie() {
        listarSeriesBuscadas();
        System.out.println("Escolha uma serie pelo nome: ");
        var nomeSerie = scan.nextLine();

        Optional<Serie> serie = serieList.stream()
                .filter(s -> s.getTitulo().toLowerCase().contains(nomeSerie.toLowerCase()))
                .findFirst();

        if (serie.isPresent()) {
            var serieEncontrada = serie.get();
            List<DadosTemporadas> temporadas = new ArrayList<>();
            for (int i = 1; i <= serieEncontrada.getTotalTemporadas(); i++) {
                var json = consumoApi.obterDados(ENDERECO + serieEncontrada.getTitulo().replace(" ", "+") + "&season=" + i + APIKEY);
                DadosTemporadas dadosTemporada = conversor.obterDados(json, DadosTemporadas.class);
                temporadas.add(dadosTemporada);
            }
            temporadas.forEach(System.out::println);
            List<Episodio> episodios = temporadas.stream()
                    .flatMap(d -> d.episodiosList().stream()
                            .map(e -> new Episodio(d.numero(), e)))
                    .collect(Collectors.toList());

            serieEncontrada.setEpisodioList(episodios);
            repositorio.save(serieEncontrada);
        } else {
            System.out.println("Série não encontrada");
        }
    }
    private void listarSeriesBuscadas(){
        serieList = repositorio.findAll();
        serieList.stream()
                .sorted(Comparator.comparing(Serie::getGenero))
                .forEach(System.out::println);
    }
    private void buscarSeriePorTitulo() {
        System.out.println("Escolha uma serie pelo nome: ");
        var nomeSerie = scan.nextLine();
         serieBusca = repositorio.findAllByTituloContainingIgnoreCase(nomeSerie);

        if (serieBusca.isPresent()) {
                System.out.println("Dados da(s) série(s): \n" + serieBusca.get());
        } else {
            System.out.println("Série não encontrada!");
        }
    }

    private void buscarSeriesPorAtor() {
        System.out.println("Escolha uma serie pelo nome do ator: ");
        var nomeAtor = scan.nextLine();
        System.out.println("Avalição a partir de que valor: ");
        var avalicao = scan.nextDouble();

        List<Serie> seriesEncontradas = repositorio.findByAtoresContainingIgnoreCaseAndAvaliacaoImdbGreaterThanEqual(nomeAtor, avalicao);
        System.out.println("Séries em que " + nomeAtor + " trabalhou: ");
        seriesEncontradas.forEach( s ->
                System.out.println(s.getTitulo() + " avaliação: "+ s.getAvaliacaoImdb()));

    }
    private void buscarTop5Series() {
        List<Serie> series = repositorio.findTop5ByOrderByAvaliacaoImdbDesc();
        series.forEach( s ->
                System.out.println(s.getTitulo() + " avaliação: "+ s.getAvaliacaoImdb()));
    }
    private void buscarSeriesPorCategoria() {
        System.out.println("Digite o nome da categoria/gênero que você deseja buscar: ");
        var nomeGenero = scan.nextLine();
        Categoria categoria = Categoria.fromPortugues(nomeGenero);
        List<Serie> series = repositorio.findAllByGenero(categoria);
        System.out.println("Séries da categoria " + nomeGenero + " : ");
        series.forEach(s ->
                System.out.println(s.getTitulo()));

    }
    private void buscarSeriesPorMaxTemporadasEMinAvalicao() {
        System.out.println("Digite o número máximo de temporadas que você busca: ");
        var maxTemporadas = scan.nextInt();
        scan.nextLine();
        System.out.println("Digite o número mínimo de avaliação que você busca");
        var minAvaliacao = scan.nextDouble();
        scan.nextLine();
        List<Serie> series = repositorio
                .findAllByTotalTemporadasLessThanEqualAndAvaliacaoImdbGreaterThanEqual (maxTemporadas, minAvaliacao);
        System.out.println("Séries encontradas com no máximo "+ maxTemporadas
                + " temporadas e no mínimo "+ minAvaliacao +" de avaliação");
        series.forEach( s ->
                System.out.println(s.getTitulo() + "\n temporadas : "+s.getTotalTemporadas() +
                        " \n avaliação: "+ s.getAvaliacaoImdb()+ "\n\n"));
    }
    private void buscarEpisodioPorTrecho() {
        System.out.println("Qual trecho o nome/trecho do episódio que você deseja buscar: ");
        var trechoEp = scan.nextLine();
        List<Episodio> episodios = repositorio.episodioPorTrecho(trechoEp);
        episodios.forEach(e ->
                System.out.printf("Série: %s Temporada %s - Episódio %s - %s\n",
                        e.getSerie().getTitulo(), e.getTemporada(),
                        e.getNumero(), e.getTitulo()));
    }
    private void buscarTop5EpisodiosPorSerie(){
        buscarSeriePorTitulo();
        if(serieBusca.isPresent()){
           Serie serie =  serieBusca.get();

            List<Episodio> episodios = repositorio.topEpisodiosPorSerie(serie);
            episodios.forEach(e ->
                    System.out.printf("Série: %s Temporada: %s - Episódio: %s - %s Avaliação: %s\n",
                            e.getSerie().getTitulo(), e.getTemporada(),
                            e.getNumero(), e.getTitulo(), e.getAvaliacao()));
        }

    }
    private void buscarEpisodioDepoisDeUmaData() {
        buscarSeriePorTitulo();
        if(serieBusca.isPresent()){
            Serie serie =  serieBusca.get();
            System.out.println("Digite uma data no formato yyyy-MM-dd:");
            int ano = scan.nextInt();
            scan.nextLine();

            List<Episodio> episodios = repositorio.episodioDepoisDeUmaData(serie,ano);
            episodios.forEach(e ->
                    System.out.printf("Série: %s Temporada: %s - Episódio: %s - %s Data de Lançamento %s\n",
                            e.getSerie().getTitulo(), e.getTemporada(),
                            e.getNumero(), e.getTitulo(), e.getDataLacamento()));

        }



    }

}
