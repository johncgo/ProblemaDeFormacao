import processing.core.PApplet;
import processing.core.PVector;

public class Main extends PApplet{

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PApplet.main("Main");
	}	
	
	//Simulador simulador;
	Vehicle vehicle;
	float squareX = 250;
	float squareY = 200;	
	
	int nrAgentes = 50;
	
	public void settings(){
		 size(700,500);	
		 //simulador = new Simulador(this);
		 //simulador.agentInstantiate(nrAgentes);
		 vehicle = new Vehicle(50,50, this);
	}
	
	public void setup(){
		//fill(120,50,240);
	}
	
	public void draw(){
		background(255);			
		mousePose();
		//simulador.runAgents(squareX,squareY);
		vehicle.update();
		vehicle.seek(new PVector(mouseX, mouseY), 200);
		vehicle.display();
		noFill();	
		squareX = mouseX;
		squareY = mouseY;
	}	
	
	public void mousePose(){
		rect(mouseX, mouseY, 150,150);
	}  	
	
}