import processing.core.PApplet;
import processing.core.PVector;

public class Agente{
	
	private PApplet p;
	private Simulador s;
	
	private enum State { SEARCHING, ALIGNING };
	
	private State state;
	private int number;
	private PVector initialPosition;
	private PVector position;
	private float width = 2;
	private float height = 2;
	private float range = 2;
	
	public Agente(PApplet p, Simulador s) {			
		this.initialPosition = new PVector(p.random(700), p.random(500));
		this.position = initialPosition;		
		this.p = p;
		this.s = s;
		this.state = State.SEARCHING;
	}	
	
	public void run(float x, float y){
		
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
			goToPoint(new PVector(x,y));
		}
	}
	public boolean flag = true;
	public PVector pt;
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
			pt = new PVector(x, y);
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
		//p.ellipse(position.x,position.y,width+10,height+10);
		PVector aux = s.getZeroPosition(this);
		aux.y = aux.y+10;
		goToPoint(aux);
	}
	
	public boolean pointReached(PVector point){
		if((position.x + range >= point.x || position.x - range >= point.x)
				&& (position.y + range >= point.y || position.x - range >= point.y)){
			return true;
		}
		return false;
	}
	
	public void goToPoint(){
		//p.tint(204, 102, 0);
		p.ellipse(position.x,position.y,width,height);
		if(number != 0){
			if(s.isNearZero(this)){				
				if(position.x > s.getZero().position.x){
					position.x = position.x-1;
				}else if (position.x < s.getZero().position.x){
					position.x = position.x+1;
				}
				if(position.y > s.getZero().position.y){
					position.y = position.y-1;
				}else if (position.y < s.getZero().position.y){
					position.y = position.y+1;
				}		
			}
		}			
	}
	
	public void goToPoint(PVector point){		
		p.ellipse(position.x,position.y,width,height);
		if(position.x > point.x){
			position.x = position.x-1;
		}else if (position.x < point.x){
			position.x = position.x+1;
		}
		if(position.y > point.y){
			position.y = position.y-1;
		}else if (position.y < point.y){
			position.y = position.y+1;
		}		
	}

	public void walk (String direction){
		p.ellipse(position.x,position.y,width,height);
		switch (direction){
		case "right":
			position.x = position.x++;
			break;
		case "left":
			position.x = position.x++;
			break;
		case "up":
			position.y = position.y--;
			break;
		case "down":
			position.y = position.y++;
			break;
		default:
			System.out.println("Entrada inválida");
		}			
	}
	
	public void tree(){
		PVector screenLimit = new PVector(700,500);
		PVector aux = new PVector(0,0);
		//boolean edgeReached = false;
		String direction="right";
		
		while(position != screenLimit){
			walk(direction);
			if(position.x >= screenLimit.x){
				direction = "down";
				aux = position;
			}
			if(position.y >= aux.x+50){
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

	public PVector getPosition() {
		return position;
	}

	public void setPosition(PVector position) {
		this.position = position;
	}
	
	
}