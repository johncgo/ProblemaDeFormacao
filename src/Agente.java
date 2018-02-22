import processing.core.PApplet;

public class Agente{
	
	float initialPointX;
	float initialPointY;
	float widht;
	float height;
	float pointX;
	float pointY;
	PApplet p;
	
	public Agente(PApplet p) {			
		this.initialPointX = p.random(0,300);
		this.initialPointY = p.random(0, 300);
		this.widht = 10;
		this.height = 10;
		pointX = initialPointX;
		pointY = initialPointY;
		this.p = p;
	}
	
	public void run(int side, float velocity){
		if(side == 1){
			p.ellipse(pointX, pointY, widht, height);
			pointX+=velocity;
			pointY+=velocity;
		}
	}

}
