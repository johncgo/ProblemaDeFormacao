import processing.core.PApplet;

public class PerceptionAgente {
	PApplet p;
	
	private Vector2D posicao;
	
	private Acoes acao;
	
	public Vector2D getPosicao() {
		return posicao;
	}

	public void setPosicao(Vector2D posicao) {
		this.posicao = posicao;
	}

	public Acoes getAcao() {
		return acao;
	}

	public void setAcao(Acoes acao) {
		this.acao = acao;
	}
	
	
}
