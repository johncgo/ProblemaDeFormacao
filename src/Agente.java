import processing.core.PApplet;

public class Agente{
	
	private PApplet p;
	private Simulador s;
	
	private int number;
	private Vector2D initialPosition;
	private Vector2D position;
	private float width = 2;
	private float height = 2;
	
	public Agente(PApplet p, Simulador s) {			
		this.initialPosition = new Vector2D(p.random(700), p.random(500));
		this.position = initialPosition;		
		this.p = p;
		this.s = s;
	}	
	
	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public Vector2D getPosition() {
		return position;
	}

	public void setPosition(Vector2D position) {
		this.position = position;
	}
	
	public void goToPoint(){
		p.ellipse(position.getX(),position.getY(),width,height);
		if(number != 0){
			if(s.isNearZero(this)){				
				if(position.getX() > s.getZero().position.getX()){
					position.setX(position.getX()-1);
				}else if (position.getX() < s.getZero().position.getX()){
					position.setX(position.getX()+1);
				}
				if(position.getY() > s.getZero().position.getY()){
					position.setY(position.getY()-1);
				}else if (position.getY() < s.getZero().position.getY()){
					position.setY(position.getY()+1);
				}		
			}
		}			
	}
	
	public void goToPoint(Vector2D point){
		if(position.getX() > s.getZero().position.getX()){
			position.setX(position.getX()-1);
		}else if (position.getX() < s.getZero().position.getX()){
			position.setX(position.getX()+1);
		}
		if(position.getY() > s.getZero().position.getY()){
			position.setY(position.getY()-1);
		}else if (position.getY() < s.getZero().position.getY()){
			position.setY(position.getY()+1);
		}		
	}

	public void run(int side, float velocity){
		p.ellipse(position.getX(),position.getY(),width,height);
		if(side == 1){			
			position.setX(position.getX()+1);
			position.setY(position.getY()+1);
		}else{
			position.setX(position.getX()-1);
			position.setY(position.getY()-1);
		}
	}
	
	
}