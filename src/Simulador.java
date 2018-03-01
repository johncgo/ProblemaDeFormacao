import java.util.ArrayList;

import processing.core.PApplet;


//FieldPerception
public class Simulador {
	
	PApplet p;
	private ArrayList<PerceptionAgente> posicoes;
	private ArrayList<Agente>agentes;
	private float range = 100;
	
	public Simulador(PApplet p, ArrayList<PerceptionAgente> posicoes) {
		super();
		this.p = p;
		this.posicoes = posicoes;
	}
	
	public Simulador(PApplet p){
		this.p = p;
		agentes = new ArrayList<Agente>();
	}
	
	public void agentInstantiate(int numberOfAgents){
		for(int i=0;i<numberOfAgents;i++){
			Agente a = new Agente(p, this);
			a.setNumber(i);
			agentes.add(a);
		}
	}
	
	public void runAgents(){
		agentes.get(0).goToPoint(new Vector2D(250,200));
		for(int i=1;i<agentes.size();i++){
			//a.run(2, (float)0.5);
			//a.goToPoint(new Vector2D(150,150));
			agentes.get(i).goToPoint();
			//System.out.println(agentes.get(i).getNumber());
			//System.out.println(agentes.get(i).getPosition().toString());
			//agentes.get(i).tree();
			//agentes.get(i).run();
		}
	}
	
	public boolean isNearZero(Agente a){
		float distance = agentes.get(0).getPosition().distance(a.getPosition());
		if(distance < range){
			return true;
		}
		return false;
	}
	
	public boolean isNear(Agente a, Agente b){
		float distance = a.getPosition().distance(b.getPosition());
		if(distance < range){
			return true;
		}
		return false;
	}
	
	public Vector2D getAgentPosition(int agentNumber, Agente a){
		Agente aux = agentes.get(agentNumber);
		if(isNear(a, aux)){
			return aux.getPosition();
		}
		else return new Vector2D(-1,-1);
	}
	
	public Vector2D getZeroPosition(Agente a){
		Agente aux = agentes.get(0);
		if(isNear(a, aux)){
			return aux.getPosition();
		}
		else return new Vector2D(-1,-1);
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
}
