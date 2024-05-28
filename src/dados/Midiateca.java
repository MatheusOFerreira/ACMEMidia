package dados;

import java.util.ArrayList;

public class Midiateca implements Iterador {

	private int contador;
	private ArrayList<Midia> midias;

	public Midiateca() {
	}

	public Midiateca(int contador, ArrayList<Midia> midias) {
		this.contador = contador;
		this.midias = midias;
	}

	public boolean cadastraMidia(Midia midia) {
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
		ArrayList<Midia> result = new ArrayList<>();
		for (Midia m : midias) {
			if (m.getCategoria() == categoria) {
				result.add(m);
			}
		}
		return result;
	}

	public boolean removeMidia(int codigo) {
		for (Midia m : midias) {
			if (m.getCodigo() == codigo) {
				midias.remove(m);
				return true;
			}
		}
		return false;
	}

	@Override
	public void reset() {
		contador = 0;
	}

	@Override
	public boolean hasNext() {
		return contador < midias.size();
	}

	@Override
	public Object next() {
		if (this.hasNext()) {
			return midias.get(contador++);
		}
		return null;
	}

}
