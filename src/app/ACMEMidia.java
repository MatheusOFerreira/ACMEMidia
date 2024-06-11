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
		cadastraVideos(); // 1
		cadastraMusica(); // 2
		dadosMidia(); // 3
		dadosMidiaCategoria(); // 4
		dadosVideoQualidade(); // 5
		dadosMusicaDuracao(); // 6
		removeMidia(); // 7
		somatorioLocacoes(); // 8

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
				System.out.println("2: " + musica);
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
		Midia midia = midiateca.consultaPorCodigo(codigo);
		if (midia == null) {
			System.out.println("3:Codigo inexistente.");
		} else {
			System.out.println("3: " + midia);
		}
	}

	private void dadosMidiaCategoria() {
		String categoriaEntrada = sc.nextLine();
	    Categoria categoria;
	    try {
	        categoria = Categoria.valueOf(categoriaEntrada);
	    } catch (IllegalArgumentException e) {
	        System.out.println("4: Nenhuma midia encontrada");
	        return;
	    }

	    if (midiateca.consultaPorCategoria(categoria).isEmpty()) {
	        System.out.println("4: Nenhuma midia encontrada");
	    } else {
	        for (Midia m : midiateca.consultaPorCategoria(categoria)) {
	            System.out.println("4: " + m);
	        }
	    }
	}

	private void dadosVideoQualidade() {
		int qualidade = sc.nextInt();
		sc.nextLine();
		if (!midiateca.consultaPorQualidade(qualidade).isEmpty()) {
			for (Midia m : midiateca.consultaPorQualidade(qualidade)) {
				System.out.println("5: " + m);
			}
		} else {
			System.out.println("5: Qualidade inexistente");
		}
	}

	private void dadosMusicaDuracao() {
		Midia aux = midiateca.musicaComMaiorDuracao();
		if (aux != null) {
			Musica musica = (Musica) aux;
			System.out.println("6: " + musica.getTitulo() + ", " + musica.getDuracao());
		} else {
			System.out.println("6: Nenhuma música encontrada.");
		}
	}

	private void removeMidia() {
		int codigo = sc.nextInt();
		sc.nextLine();
		Midia aux = midiateca.removeMidia(codigo);
		if (aux != null) {
			System.out.println("7: " + aux);
		} else {
			System.out.println("7:Codigo inexistente.");
		}
	}

	private void somatorioLocacoes() {
		double somatorio = midiateca.somatorioLocacoes();
		if (somatorio == 0) {
			System.out.println("8: Nenhuma midia encontrada.");
		} else {
			System.out.println("8: " + somatorio);
		}
	}
	
	// Metodo adicional
	private void dadosMusicaComLocacaoProximoMedia() {
		Midia aux = midiateca.musicaComLocacaoProximaMedia();
		if(aux == null) {
			System.out.println("9: Nenhuma musica encontrada.");
		} else {
			double media = midiateca.mediaLocacao();
			System.out.println("9: " + String.format("%.2f", media) + ", " + aux);
		}
	}
	
	// Metodo adicional
	private void dadosMidiaMaisNova() {
		Midia aux = midiateca.midiaNova();
		if(aux == null) {
			System.out.println("10:Nenhuma midia encontrada.");
		} else {
			System.out.println("10: " + aux.getCodigo() + ", " + aux.getTitulo() + ", " + aux.getAno());
		}
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
