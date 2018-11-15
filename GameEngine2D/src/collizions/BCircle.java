package collizions;

public class BCircle 
{
	private float SposX, SposY, SR;
	public float Scale;
	private float posX, posY, R;
	public float X, Y;
	public BCircle(float X, float Y, float R) 
	{
		this.posX = X;
		this.posY = Y;
		this.R = R;
		this.SposX = X;
		this.SposY = Y;
		this.SR = R;
	}
	public void moveTo(float X, float Y)
	{
		this.X = X;
		this.Y = Y;
	}
	
	public float[] getBBTab()
	{
		float[] tab = new float[3];
		tab[0] = X+posX;
		tab[1] = Y+posY;
		tab[2] = R;
		return tab;
	}
	public void scaleBB(float s)
	{
		this.Scale = s;
		posX=SposX*Scale;
		posY=SposY*Scale;

		R = SR*Scale;
	}
}
