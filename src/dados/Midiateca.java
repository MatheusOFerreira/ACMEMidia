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
