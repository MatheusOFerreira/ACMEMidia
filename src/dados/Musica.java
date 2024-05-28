package dados;

public class Musica extends Midia {

	private double duracao;

	public Musica(int codigo, String titulo, int ano, Categoria categoria, double duracao) {
		super(codigo, titulo, ano, categoria);
		this.duracao = duracao;
	}

	@Override
	public double calculaLocacao() {
		double valorPorMinuto = 0.0;

		switch (getCategoria()) {
		case ACA:
			valorPorMinuto = 1.0;
			break;
		case DRA:
			valorPorMinuto = 0.8;
			break;
		case FIC:
			valorPorMinuto = 1.2;
			break;
		case ROM:
			valorPorMinuto = 0.9;
			break;
		default:
			throw new IllegalArgumentException("Categoria desconhecida: " + getCategoria());
		}

		return valorPorMinuto * duracao;
	}

}
