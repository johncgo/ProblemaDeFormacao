import java.lang.reflect.Array;
import java.util.ArrayList;

import javax.sound.sampled.FloatControl;

import processing.core.PApplet;
import processing.core.PVector;


//FieldPerception
public class Simulador {
	
	PApplet p;
	private ArrayList<PerceptionAgente> posicoes;
	private ArrayList<Agente>agentes;
	private ArrayList<Vehicle>vehicles;
	private float range = 100;
	
	public Simulador(PApplet p, ArrayList<PerceptionAgente> posicoes) {
		super();
		this.p = p;
		this.posicoes = posicoes;
	}
	
	public Simulador(PApplet p){
		this.p = p;
		agentes = new ArrayList<Agente>();
		vehicles = new ArrayList<Vehicle>();
	}
	
	public void agentInstantiate(int numberOfAgents){
		for(int i=0;i<numberOfAgents;i++){
			Agente a = new Agente(p, this);
			a.setNumber(i);
			agentes.add(a);
		}
	}
	
	public void vehicleInstantiate(int number){
		for(int i=0;i<number;i++){
			Vehicle v = new Vehicle(p.random(700), p.random(500), p);
			vehicles.add(v);
		}
	}
	
	public void runAgents(PVector target){
		for(Vehicle v:vehicles){
			v.update();
			v.flock(vehicles); 
			v.applyBehaviors(vehicles, target.x, target.y);
			v.display();
			
		}
	}
	
	public void runAgents(float x, float y){
		agentes.get(0).goToPoint(new PVector(x,y));
		for(int i=1;i<agentes.size();i++){
			//a.run(2, (float)0.5);
			//a.goToPoint(new PVector(150,150));
			agentes.get(i).goToPoint();
			//System.out.println(agentes.get(i).getNumber());
			//System.out.println(agentes.get(i).getPosition().toString());
			//agentes.get(i).tree();
			//agentes.get(i).run();
		}
	}
	
	public boolean isNearZero(Agente a){
		float distance = PVector.dist(a.getPosition(), agentes.get(0).getPosition());
		if(distance < range){
			return true;
		}
		return false;
	}
	
	public boolean isNear(Agente a, Agente b){
		float distance = PVector.dist(a.getPosition(), b.getPosition());
		if(distance < range){
			return true;
		}
		return false;
	}
	
	public PVector getAgentPosition(int agentNumber, Agente a){
		Agente aux = agentes.get(agentNumber);
		if(isNear(a, aux)){
			return aux.getPosition();
		}
		else return new PVector(-1,-1);
	}
	
	public PVector getZeroPosition(Agente a){
		Agente aux = agentes.get(0);
		if(isNear(a, aux)){
			return aux.getPosition();
		}
		else return new PVector(-1,-1);
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
