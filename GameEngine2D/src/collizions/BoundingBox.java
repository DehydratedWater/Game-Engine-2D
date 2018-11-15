package collizions;

public class BoundingBox 
{
	int type;
	
	float posX, posY;
	float scaleX = 1;
	float scaleY = 1;
	public BCircle bCircle;
	public BBox bBox;
	public OBB bObb;
	public BoundingBox(BCircle circle) 
	{
		type = 1;
		bCircle = circle;
	}
	public BoundingBox(BBox box) 
	{
		type = 2;
		bBox = box;
	}
	public BoundingBox(OBB obb) 
	{
		type = 3;
		bObb = obb;
	}
	
	public void moveBBTo(float x, float y)
	{
		if(type==1)
		{
			bCircle.X = x;
			bCircle.Y = y;
		}
		else if(type==2)
		{
			bBox.X = x;
			bBox.Y = y;
		}
		else if(type==3)
		{
			bObb.posX = x;
			bObb.posY = y;
		}
	}
	public void scaleBB(float s)
	{
		if(type==1)
		{
			bCircle.scaleBB(s);
		}
		
	}
	
}
