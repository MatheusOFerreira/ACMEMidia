package dados;

public class Video extends Midia {

	private int qualidade;

	public Video(int codigo, String titulo, int ano, Categoria categoria, int qualidade) {
		super(codigo, titulo, ano, categoria);
		this.qualidade = qualidade;
	}

	public int getQualidade() {
		return qualidade;
	}

	@Override
	public double calculaLocacao() {
		double valorLocacao = 0.0;
		if (super.getAno() == 2024) {
			valorLocacao = 20.00;
		} else if (super.getAno() >= 2000 && super.getAno() <= 2023) {
			valorLocacao = 15.00;
		} else
			valorLocacao = 10.00;
		return valorLocacao;
	}

}
