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
		return true;
	}

	public Midia consultaPorCodigo(int codigo) {
		return null;
	}

	public ArrayList<Midia> consultaPorCategoria(Categoria categoria) {
		return null;
	}

	public boolean removeMidia(int codigo) {
		return true;
	}

	@Override
	public void reset() {
	}

	@Override
	public boolean hasNext() {
		return false;
	}

	@Override
	public Object next() {
		return null;
	}

}
