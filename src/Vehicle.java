import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PVector;

public class Vehicle {

	PApplet p;
	PVector location;
	PVector velocity;
	PVector acceleration;
	float r; // size
	float maxforce;
	float maxspeed;
	float PI = 3.1415f;

	Vehicle(float x, float y, PApplet p) {
		acceleration = new PVector(0, 0);
		velocity = new PVector(0, 0);
		location = new PVector(x, y);
		r = (float) 3.0;
		maxspeed = 4;
		maxforce = 0.09f;
		this.p = p;
	}

	void update() {
		velocity.add(acceleration);
		velocity.limit(maxspeed);
		location.add(velocity);
		acceleration.mult(0);
	}

	void applyForce(PVector force) {
		acceleration.add(force);
	}

	void applyBehaviors(ArrayList<Vehicle> vehicles, float x, float y) {
		PVector separate = separate(vehicles);
		PVector seek = seek(new PVector(x, y));

		separate.mult(1.5f);
		seek.mult(0.5f);

		applyForce(separate);
		applyForce(seek);
	}

	PVector separate(ArrayList<Vehicle> vehicles) {
		float desiredseparation = r * 6;
		PVector sum = new PVector();
		int count = 0;
		for (Vehicle other : vehicles) {
			float d = PVector.dist(location, other.location);
			if ((d > 0) && (d < desiredseparation)) {
				PVector diff = PVector.sub(location, other.location);
				diff.normalize();
				diff.div(d);
				sum.add(diff);
				count++;

			}
		}
		if (count > 0) {
			sum.div(count);
			sum.normalize();
			sum.mult(maxspeed);
			PVector steer = PVector.sub(sum, velocity);
			steer.limit(maxforce);
			// applyForce(steer);
			return steer;
		} else {
			return new PVector(0, 0);
		}

	}

	PVector seek(PVector target, float minDistance) {
		// comente ou descomente apenas a linha abaixo (comentário alternado
		// mágico)
		PVector desired = PVector.sub(target,location); /*
							  PVector desired = PVector.sub(location, * target); //
							 */

		// só aplica o seek/flee se estiver dentro dessa distância
		if (this.location.dist(target) > minDistance) {
			desired = new PVector(0, 0);
		}

		desired.normalize();
		desired.mult(maxspeed);
		PVector steer = PVector.sub(desired, velocity);
		steer.limit(maxforce);
		// applyForce(steer);
		return steer;
	}

	PVector seek(PVector target) {

		PVector desired = PVector.sub(target, location);
		
		if (squareVerify(target)) {
			desired = new PVector(0, 0);
		}
		desired.normalize();
		desired.mult(maxspeed);
		PVector steer = PVector.sub(desired, velocity);
		steer.limit(maxforce);
		
		return steer;
	}

	public void run(ArrayList<Vehicle> boids, float x, float y) {
		update();
		flock(boids);
		applyBehaviors(boids, x, y);
		display();
	}

	boolean squareVerify(PVector target) {
		float x = target.x - 75;
		float y = target.y - 75;
		if (location.x > x && location.x < x + 150 && location.y > y && location.y < y + 150) {
			return true;
		}
		return false;
	}

	PVector align(ArrayList<Vehicle> boids) {
		float neighbordist = 100;
		PVector sum = new PVector(0, 0);
		int count = 0;
		for (Vehicle other : boids) {
			float d = PVector.dist(location, other.location);
			if ((d > 0) && (d < neighbordist)) {
				sum.add(other.velocity);
				count++;
			}
		}
		if (count > 0) {
			sum.div(count);
			sum.normalize();
			sum.mult(maxspeed);
			PVector steer = PVector.sub(sum, velocity);
			steer.limit(maxforce);
			return steer;
		} else {
			return new PVector(0, 0);
		}
	}

	PVector cohesion(ArrayList<Vehicle> boids) {
		float neighbordist = 50;
		PVector sum = new PVector(0, 0);
		int count = 0;
		for (Vehicle other : boids) {
			float d = PVector.dist(location, other.location);
			if ((d > 0) && (d < neighbordist)) {
				sum.add(other.location);
				count++;
			}
		}
		if (count > 0) {
			sum.div(count);
			return seek(sum);
		} else {
			return new PVector(0, 0);
		}
	}

	void flock(ArrayList<Vehicle> boids) {
		PVector sep = separate(boids);
		PVector ali = align(boids);
		PVector coh = cohesion(boids);

		sep.mult(1.5f);
		ali.mult(1.0f);
		coh.mult(1.0f);

		applyForce(sep);
		applyForce(ali);
		applyForce(coh);
	}

	void display() {
		// float theta = velocity.heading() + PI/2;
		// p.fill(175);
		// p.stroke(0);
		// p.pushMatrix();
		// p.translate(location.x,location.y);
		// p.rotate(theta);
		// p.beginShape();
		// p.vertex(0, -r*2);
		// p.vertex(-r, r*2);
		// p.vertex(r, r*2);
		// p.endShape(p.CLOSE);
		// p.popMatrix();
		p.ellipse(location.x, location.y, r, r);
	}

}
