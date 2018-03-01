import processing.core.PApplet;

public class Agente{
	
	private PApplet p;
	private Simulador s;
	
	private enum State { SEARCHING, ALIGNING };
	
	private State state;
	private int number;
	private Vector2D initialPosition;
	private Vector2D position;
	private float width = 2;
	private float height = 2;
	private float range = 2;
	
	public Agente(PApplet p, Simulador s) {			
		this.initialPosition = new Vector2D(p.random(700), p.random(500));
		this.position = initialPosition;		
		this.p = p;
		this.s = s;
		this.state = State.SEARCHING;
	}	
	
	public void run(){
		
		if(number!=0){
			switch (state){
			case SEARCHING:
				searching();
				break;
			case ALIGNING:
				aligning();
				break;
			default:
				System.out.println("Estado inválido");
			}
		}else{
			goToPoint(new Vector2D(250,200));
		}
	}
	public boolean flag = true;
	public Vector2D pt;
	public void searching(){
		
		if(s.isNearZero(this)){
			state = State.ALIGNING;
			return;
		}
		if(flag){		
			//System.out.println(">>>>>>>>>>>chegou");
			float x = p.random(0,700);
			//System.out.print("x: "+x);
			float y = p.random(0,500);
			//System.out.println("  y:"+y);
			pt = new Vector2D(x, y);
			flag = false;			
		}		
		if(pointReached(pt)){
			flag = true;
		}	
		goToPoint(pt);
		
	}	
	
	public void aligning(){
		if(!s.isNearZero(this)){
			state = State.SEARCHING;
			return;
		}
		//p.ellipse(position.getX(),position.getY(),width+10,height+10);
		Vector2D aux = s.getZeroPosition(this);
		aux.setY(aux.getY()+10);
		goToPoint(aux);
	}
	
	public boolean pointReached(Vector2D point){
		if((position.getX() + range >= point.getX() || position.getX() - range >= point.getX())
				&& (position.getY() + range >= point.getY() || position.getY() - range >= point.getY())){
			return true;
		}
		return false;
	}
	
	public void goToPoint(){
		//p.tint(204, 102, 0);
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
		p.ellipse(position.getX(),position.getY(),width,height);
		if(position.getX() > point.getX()){
			position.setX(position.getX()-1);
		}else if (position.getX() < point.getX()){
			position.setX(position.getX()+1);
		}
		if(position.getY() > point.getY()){
			position.setY(position.getY()-1);
		}else if (position.getY() < point.getY()){
			position.setY(position.getY()+1);
		}		
	}

	public void walk (String direction){
		p.ellipse(position.getX(),position.getY(),width,height);
		switch (direction){
		case "right":
			position.setX(position.getX()+1);
			break;
		case "left":
			position.setX(position.getX()-1);
			break;
		case "up":
			position.setY(position.getY()-1);
			break;
		case "down":
			position.setY(position.getY()+1);
			break;
		default:
			System.out.println("Entrada inválida");
		}			
	}
	
	public void tree(){
		Vector2D screenLimit = new Vector2D(700,500);
		Vector2D aux = new Vector2D(0,0);
		//boolean edgeReached = false;
		String direction="right";
		
		while(position != screenLimit){
			walk(direction);
			if(position.getX() >= screenLimit.getX()){
				direction = "down";
				aux = position;
			}
			if(position.getY() >= aux.getX()+50){
				direction = "left";
			}
		}
		System.out.println("chegou haha");				
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
	
	
}