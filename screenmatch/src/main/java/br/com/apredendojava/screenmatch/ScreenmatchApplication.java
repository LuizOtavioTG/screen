package br.com.apredendojava.screenmatch;

import br.com.apredendojava.screenmatch.model.DadosEpisodios;
import br.com.apredendojava.screenmatch.model.DadosSerie;
import br.com.apredendojava.screenmatch.service.ConsumoApi;
import br.com.apredendojava.screenmatch.service.ConverteDados;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ScreenmatchApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ScreenmatchApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		var consumoApi = new ConsumoApi();
		var json = consumoApi.obterDados("https://www.omdbapi.com/?t=gilmore+girls&apikey=6585022c");
		System.out.println(json);

		ConverteDados coversor = new ConverteDados();
		DadosSerie dadosSerie = coversor.obterDados(json, DadosSerie.class);
		System.out.println(dadosSerie);

		json = consumoApi.obterDados("https://www.omdbapi.com/?t=gilmore+girls&season=1&episode=2&apikey=6585022c");
		DadosEpisodios dadosEpisodios = coversor.obterDados(json, DadosEpisodios.class);
		System.out.println(dadosEpisodios);
	}
}
