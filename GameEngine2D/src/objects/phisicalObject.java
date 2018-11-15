package objects;

import collizions.BoundingBox;
import managers.textureManager;

public class phisicalObject extends CollidingObject{

	public float accelerationX, accelerationY;
	public float gravityX, gravityY;
	public float waste = 0.99f;
	public phisicalObject(float posX, float posY, String name, String path, textureManager texManager, BoundingBox BB, float mass) {
		super(posX, posY, name, path, texManager, BB);
		super.isPhisical = true;
		super.mass = mass;
	}


	public phisicalObject(float posX, float posY, String[] name, String[] path, textureManager texManager,
			int animationTime, BoundingBox BB) {
		super(posX, posY, name, path, texManager, animationTime, BB);
		super.isPhisical = true;
		super.mass = mass;
	}

	@Override
	public void refreshMovement() 
	{
		super.speedX+=(accelerationX+gravityX);
		super.speedY+=(accelerationY+gravityY);

		speedX*=waste;
		speedY*=waste;
		//System.out.println("SX "+speedX+" SY "+speedY);
		super.refreshMovement();
	}
	
}
