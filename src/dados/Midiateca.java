package dados;

import java.util.ArrayList;

public class Midiateca implements Iterador {

	private int contador;
	private ArrayList<Midia> midias;

	public Midiateca() {
		this.contador = 0;
		this.midias = new ArrayList<>();
	}

	public boolean cadastraMidia(Midia midia) {
		if (midias.isEmpty()) {
			midias.add(midia);
			return true;
		}
		for (Midia m : midias) {
			if (m.getCodigo() == midia.getCodigo()) {
				return false;
			}
		}
		midias.add(midia);
		return true;
	}

	public Midia consultaPorCodigo(int codigo) {
		for (Midia m : midias) {
			if (m.getCodigo() == codigo) {
				return m;
			}
		}
		return null;
	}

	public ArrayList<Midia> consultaPorCategoria(Categoria categoria) {
		ArrayList<Midia> resultado = new ArrayList<>();
		for (Midia m : midias) {
			if (m.getCategoria() == categoria) {
				resultado.add(m);
			}
		}
		return resultado;
	}

	// Método novo
	public ArrayList<Midia> consultaPorQualidade(int qualidade) {
		ArrayList<Midia> resultado = new ArrayList<>();
		for (Midia m : midias) {
			if (m instanceof Video && ((Video) m).getQualidade() == qualidade) {
				resultado.add(m);
			}
		}
		return resultado;
	}

	// Método novo
	public Midia musicaComMaiorDuracao() {
		Midia aux = null;
		if (midias.isEmpty()) {
			return null; // Nenhuma musica
		}
		for (Midia m : midias) {
			if ((m instanceof Musica) && (aux == null || ((Musica) m).getDuracao() > ((Musica) aux).getDuracao())) {
				aux = m;
			}
		}
		return aux;
	}

	// Método novo
	public double somatorioLocacoes() {
		double somatorio = 0;
		for (Midia m : midias) {
			somatorio += m.calculaLocacao();
		}
		return somatorio;
	}

	// Método novo
	public Midia removeMidia(int codigo) {
		for (Midia m : midias) {
			if (m.getCodigo() == codigo) {
				midias.remove(m);
				return m;
			}
		}
		return null;
	}

	// Método extra
	public Midia musicaComLocacaoProximaMedia() {
		if (midias.isEmpty()) {
			return null; // Vazio
		}
		double aux = this.mediaLocacao();
		double menorDiferenca = Double.MAX_VALUE;
		Musica musicaProxima = null;
		double diferenca;

		for (Midia m : midias) {
			if (m instanceof Musica) {
				diferenca = Math.abs(m.calculaLocacao() - aux);
				if (diferenca < menorDiferenca) {
					menorDiferenca = diferenca;
					musicaProxima = (Musica) m;
				}
			}
		}

		return musicaProxima;
	}

	// Auxiliar
	public double mediaLocacao() {
		double soma = 0;
		int count = 0;
		for (Midia m : midias) {
			if (m instanceof Musica) {
				soma += m.calculaLocacao();
				count++;
			}
		}
		return soma / count;
	}

	// Método extra
	public Midia midiaNova() {
		if (midias.isEmpty()) {
			return null;
		}

		Midia maisNova = midias.get(0);
		for (Midia m : midias) {
			if (m.getAno() > maisNova.getAno()) { // maior = mais nova
				maisNova = m;
			}
		}

		return maisNova;
	}

	@Override
	public void reset() {
		contador = 0;
	}

	@Override
	public boolean hasNext() {
		if (contador < midias.size()) {
			return true;
		}
		return false;
	}

	@Override
	public Object next() {
		if (this.hasNext()) {
			return midias.get(contador++);
		}
		return null;
	}

}
