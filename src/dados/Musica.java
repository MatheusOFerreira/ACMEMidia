package dados;

public class Musica extends Midia {

	private double duracao;

	public Musica(int codigo, String titulo, int ano, Categoria categoria, double duracao) {
		super(codigo, titulo, ano, categoria);
		this.duracao = duracao;
	}

	public double getDuracao() {
		return duracao;
	}

	@Override
	public double calculaLocacao() {
		double valorPorMinuto = 0.0;

		switch (getCategoria()) {
		case ACA:
			valorPorMinuto = 0.9;
			break;
		case DRA:
			valorPorMinuto = 0.7;
			break;
		case FIC:
			valorPorMinuto = 0.5;
			break;
		case ROM:
			valorPorMinuto = 0.3;
			break;
		default:
			throw new IllegalArgumentException("Categoria desconhecida: " + getCategoria());
		}

		return valorPorMinuto * duracao;
	}
	
	//Sobreescrita do toString da Midia com o atributo duracao
	@Override
    public String toString() {
        return super.toString() + "," + duracao + "," + calculaLocacao();
    }

}
