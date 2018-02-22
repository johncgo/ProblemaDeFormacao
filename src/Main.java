import processing.core.PApplet;

public class Main extends PApplet{

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PApplet.main("Main");
	}	
	
	Agente a1, a2, a3;
	
	public void settings(){
		 size(700,500);
		 a1 = new Agente(this);
		 a2 = new Agente(this);
		 a3 = new Agente(this);
	}
	
	public void setup(){
		fill(120,50,240);
	}
	
	public void draw(){
		background(255);
		a1.run(1, 1);
		a2.run(1, (float)0.3);
		a3.run(1, (float)0.5);
		rect(250,200, 150,150);
		noFill();
		delay(100);
	}
	    
}
