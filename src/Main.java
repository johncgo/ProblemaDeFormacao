import processing.core.PApplet;

public class Main extends PApplet{

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PApplet.main("Main");
	}	
	
	Simulador simulador;
	float squareX = 250;
	float squareY = 200;
	
	public void settings(){
		 size(700,500);	
		 simulador = new Simulador(this);
		 simulador.agentInstantiate(50);
	}
	
	public void setup(){
		fill(120,50,240);
	}
	
	public void draw(){
		background(255);	
		simulador.runAgents();
		rect(squareX, squareY, 150,150);
		noFill();		
	}
	    
}