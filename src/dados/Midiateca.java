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
	public Midia musicaComMaiorDuracao(){
		Midia aux = null;
		if(midias.isEmpty()) {
			return null; // Nenhuma musica
		}
		for(Midia m : midias) {
			if((m instanceof Musica) && (aux == null || ((Musica) m).getDuracao() > ((Musica) aux).getDuracao())) {
				aux = m;
			}
		}
		return aux;
	}
	
	// Método novo
	public double somatorioLocacoes() {
		double somatorio = 0;
		for(Midia m : midias) {
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
