import java.util.ArrayList;

import processing.core.PApplet;


//FieldPerception
public class Simulador {
	
	PApplet p;
	private ArrayList<PerceptionAgente> posicoes;
	
	public ArrayList<PerceptionAgente> getPosicoes() {
		return posicoes;
	}
	public void setPosicoes(ArrayList<PerceptionAgente> posicoes) {
		this.posicoes = posicoes;
	}
	public Simulador(PApplet p, ArrayList<PerceptionAgente> posicoes) {
		super();
		this.p = p;
		this.posicoes = posicoes;
	}
	
	public Simulador copy(){
		return new Simulador(p, posicoes);
	}

}
