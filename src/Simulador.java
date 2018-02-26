import java.util.ArrayList;

import processing.core.PApplet;


//FieldPerception
public class Simulador {
	
	PApplet p;
	private ArrayList<PerceptionAgente> posicoes;
	private ArrayList<Agente>agentes;
	private float range = 300;
	
	public Simulador(PApplet p, ArrayList<PerceptionAgente> posicoes) {
		super();
		this.p = p;
		this.posicoes = posicoes;
	}
	
	public Simulador(PApplet p){
		this.p = p;
		agentes = new ArrayList<Agente>();
	}
	
	public void runAgents(){
		agentes.get(0).goToPoint(new Vector2D(250,200));
		for(int i=1;i<agentes.size();i++){
			//a.run(2, (float)0.5);
			//a.goToPoint(new Vector2D(150,150));
			agentes.get(i).goToPoint();
		}
	}
	
	public boolean isNearZero(Agente a){
		float distance = agentes.get(0).getPosition().distance(a.getPosition());
		if(distance < range){
			return true;
		}
		return false;
	}
	
	public Agente getZero(){
		return agentes.get(0);
	}
	
	public void mouseWalk(){
		
	}
	
	public ArrayList<PerceptionAgente> getPosicoes() {
		return posicoes;
	}
	
	public void setPosicoes(ArrayList<PerceptionAgente> posicoes) {
		this.posicoes = posicoes;
	}	
	
	public ArrayList<Agente> getAgentes() {
		return agentes;
	}

	public void setAgentes(ArrayList<Agente> agentes) {
		this.agentes = agentes;
	}

	public Simulador copy(){
		return new Simulador(p, posicoes);
	}
	
	public void agentInstantiate(int numberOfAgents){
		for(int i=0;i<numberOfAgents;i++){
			Agente a = new Agente(p, this);
			a.setNumber(i);
			agentes.add(a);
		}
	}
}
