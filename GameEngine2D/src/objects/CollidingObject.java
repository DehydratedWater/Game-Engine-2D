package objects;

import collizions.BoundingBox;
import managers.textureManager;

public class CollidingObject extends StaticObject
{
	public boolean isStatic = false;
	public BoundingBox BB;
	public float speedX, speedY;
	public CollidingObject(float posX, float posY, String name, String path, textureManager texManager, BoundingBox BB) {
		super(posX, posY, name, path, texManager);
		this.BB = BB;
		BB.moveBBTo(posX, posY);
		super.colliding = true;
	}

	public CollidingObject(float posX, float posY, String[] name, String[] path, textureManager texManager,
			int animationTime, BoundingBox BB) {
		super(posX, posY, name, path, texManager, animationTime);
		this.BB = BB;
		BB.moveBBTo(posX, posY);
		super.colliding = true;
	}

	public void moveToPos(float X, float Y)
	{
		super.x = X;
		super.y = Y;
		BB.moveBBTo(X, Y);
	}
	public void refreshMovement()
	{
		moveToPos(super.x+speedX, super.y+speedY);
	}
	public void scale(float s)
	{
		super.scaleX = s;
		super.scaleY = s;
		
		BB.scaleBB(s);
	}
}
