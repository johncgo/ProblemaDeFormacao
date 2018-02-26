
public class Vector2D {
	
	private float x;
	private float y;
	
	public Vector2D(float x, float y){
		this.x = x;
		this.y = y;
	}
	
	public float distance(Vector2D pontoA, Vector2D pontoB){
		float auxA = (pontoA.getX() - pontoB.getX());
		auxA = auxA * auxA;
		float auxB = (pontoA.getY() - pontoB.getY());
		auxB = auxB * auxB;
		return (float)Math.sqrt(auxA + auxB);
	}
	
	public float distance(Vector2D pontoB){
		float auxA = (x - pontoB.getX());
		auxA = auxA * auxA;
		float auxB = (y - pontoB.getY());
		auxB = auxB * auxB;
		return (float)Math.sqrt(auxA + auxB);
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}
	
	
	
}
