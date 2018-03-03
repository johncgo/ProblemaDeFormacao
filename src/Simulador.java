import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PVector;


//FieldPerception
public class Simulador {
	
	PApplet p;	
	private ArrayList<Vehicle>vehicles;	
	
	public Simulador(PApplet p){
		this.p = p;		
		vehicles = new ArrayList<Vehicle>();
	}	
	
	public void vehicleInstantiate(int number){
		for(int i=0;i<number;i++){
			Vehicle v = new Vehicle(p.random(700), p.random(500), p);
			vehicles.add(v);
		}
	}
	
	public void runAgents(PVector target){
		for(Vehicle v:vehicles){
			v.run(vehicles, target.x, target.y);
			
		}
	}	
}