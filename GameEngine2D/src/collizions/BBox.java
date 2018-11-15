package collizions;

public class BBox 
{
	private float maxX, maxY;
	public float X, Y;
	
	public BBox(float maxX, float maxY) 
	{
		this.maxX = maxX;
		this.maxY = maxY;
	}
	public void moveTo(float X, float Y)
	{
		this.X = X;
		this.Y = Y;
	}
	
	public float[] getBBTab()
	{
		float[] tab = new float[6];
		tab[0] = X;
		tab[1] = Y;
		tab[2] = X+maxX;
		tab[3] = Y+maxY;
		tab[4] = (X+X+maxX)/2;
		tab[5] = (Y+Y+maxY)/2;
		return tab;
	}
}
