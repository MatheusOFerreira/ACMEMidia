package app;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintStream;
import java.nio.charset.Charset;
import java.util.Locale;
import java.util.Scanner;

import dados.*;

public class ACMEMidia {

	private Midiateca midiateca;
	private Scanner sc = new Scanner(System.in); // Atributo para entrada de dados
	private PrintStream saidaPadrao = System.out; // Guarda a saida padrao - tela (console)
	private final String nomeArquivoEntrada = "entrada.txt"; // Nome do arquivo de entrada de dados
	private final String nomeArquivoSaida = "saida.txt"; // Nome do arquivo de saida de dados

	public ACMEMidia() {
		midiateca = new Midiateca();
		redirecionaES(); // Redireciona E/S para arquivos
	}

	public void executar() {
		cadastraVideos();
		cadastraMusica();
		dadosMidia();
		dadosMidiaCategoria();

	}

	private void cadastraVideos() {
		int ano, codigo, qualidade;
		String titulo, categoriaEntrada;
		Categoria categoria;
		codigo = sc.nextInt();
		sc.nextLine();
		while (codigo != -1) {
			titulo = sc.nextLine();
			ano = sc.nextInt();
			sc.nextLine();
			categoriaEntrada = sc.nextLine();
			categoria = Categoria.valueOf(categoriaEntrada);
			qualidade = sc.nextInt();
			sc.nextLine();
			Video video = new Video(codigo, titulo, ano, categoria, qualidade);

			if (midiateca.cadastraMidia(video)) {
				System.out.println("1: " + video.getCodigo() + ", " + video.getTitulo() + ", " + video.getAno() + ", "
						+ video.getCategoria() + ", " + video.getQualidade());
			} else {
				System.out.println("1:Erro-video com codigo repetido: " + codigo);
			}
			codigo = sc.nextInt();
			sc.nextLine();
		}
	}

	private void cadastraMusica() {
		int codigo, ano;
		double duracao;
		String titulo, categoriaEntrada;
		Categoria categoria;
		codigo = sc.nextInt();
		sc.nextLine();
		while (codigo != -1) {
			titulo = sc.nextLine();
			ano = sc.nextInt();
			sc.nextLine();
			categoriaEntrada = sc.nextLine();
			categoria = Categoria.valueOf(categoriaEntrada);
			duracao = sc.nextDouble();
			Musica musica = new Musica(codigo, titulo, ano, categoria, duracao);
			if (midiateca.cadastraMidia(musica)) {
				System.out.println("2: " + musica.getCodigo() + ", " + musica.getTitulo() + ", " + musica.getAno()
						+ ", " + musica.getCategoria() + ", " + musica.getDuracao());
			} else {
				System.out.println("2:Erro-musica com codigo repetido: " + codigo);
			}
			codigo = sc.nextInt();
			sc.nextLine();
		}
	}

	private void dadosMidia() {
		int codigo = sc.nextInt();
		sc.nextLine();
		if (midiateca.consultaPorCodigo(codigo) == null) {
			System.out.println("3:Codigo inexistente.");
		} else {
			System.out.println("3: " + midiateca.consultaPorCodigo(codigo));
		}
	}

	private void dadosMidiaCategoria() {
		String categoriaEntrada = sc.nextLine();
		Categoria categoria = Categoria.valueOf(categoriaEntrada);
		if (midiateca.consultaPorCategoria(categoria).isEmpty()) {
			System.out.println("4: Nenhuma midia encontrada");
		} else {
			for (Midia m : midiateca.consultaPorCategoria(categoria)) {
				System.out.println("4: " + m);
			}	
		}
	}

	private void dadosVideoQualidade() {

	}

	private void dadosMusicaDuracao() {

	}

	private void removeMidia() {

	}

	private void somatorioLocacoes() {

	}

	// Redireciona E/S para arquivos
	// Chame este metodo para redirecionar a leitura e escrita de dados para
	// arquivos
	private void redirecionaES() {
		try {
			BufferedReader streamEntrada = new BufferedReader(new FileReader(nomeArquivoEntrada));
			sc = new Scanner(streamEntrada); // Usa como entrada um arquivo
			PrintStream streamSaida = new PrintStream(new File(nomeArquivoSaida), Charset.forName("UTF-8"));
			System.setOut(streamSaida); // Usa como saida um arquivo
		} catch (Exception e) {
			System.out.println(e);
		}
		Locale.setDefault(Locale.ENGLISH); // Ajusta para ponto decimal
		sc.useLocale(Locale.ENGLISH); // Ajusta para leitura para ponto decimal
	}

}
