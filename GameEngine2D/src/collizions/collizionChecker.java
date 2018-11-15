package collizions;

import maths.TB;
import maths.Vector2D;
import objects.CollidingObject;
import objects.phisicalObject;

public class collizionChecker 
{
	public boolean isCollig(CollidingObject o1, CollidingObject o2)
	{
		if(o1.BB.type==1&&o2.BB.type==1)
		{
			return collisionOfTwoCircles(o1, o2);
		}
		else if(o1.BB.type==2&&o2.BB.type==2)
		{
			return collisionOfTwoBoundingBoxes(o1, o2);
		}
		return false;
	}
	
	public boolean checkIfCollig(CollidingObject o1, CollidingObject o2)
	{
		if(o1.BB.type==1&&o2.BB.type==1)
		{
			float tab1[] = o1.BB.bCircle.getBBTab();
			float tab2[] = o2.BB.bCircle.getBBTab();
			
			if(TB.distanceF(tab1[0], tab1[1], tab2[0], tab2[1])<(tab1[2]+tab2[2]))
			{
				return true;
			}
			return false;
		}
		else if(o1.BB.type==2&&o2.BB.type==2)
		{
			float tab1[] = o1.BB.bBox.getBBTab();
			float tab2[] = o2.BB.bBox.getBBTab();
			
			if(!(tab1[0]>=tab2[2]||tab1[2]<=tab2[0]||tab1[1]>=tab2[3]||tab1[3]<=tab2[1]))
			{
				return true;
			}
			return false;
		}
		
		return false;
	}
	
	
	public void simulatePhisics(phisicalObject o1, phisicalObject o2)
	{
		if(o1.BB.type==1&&o2.BB.type==1)
		{
			simulatePhisicForTwoBoxes(o1, o2);
		}
		else if(o1.BB.type==2&&o2.BB.type==2)
		{
			
		}
	}

	private void simulatePhisicForTwoBoxes(phisicalObject o1, phisicalObject o2) {
		if(o1.speedX==0&&o1.speedY==0)
		{
			Vector2D v = new Vector2D(o2.speedX, o2.speedY);
			v.normalise();
			v.multiply(o2.mass);
			
			v.multiply(1/o1.mass);
			o1.speedX = v.x;
			o1.speedY = v.y;
			
			o2.speedX = -v.x;
			o2.speedY = -v.y;
		}
		else if(o2.speedX==0&&o2.speedY==0)
		{
			Vector2D v = new Vector2D(o1.speedX, o1.speedY);
			v.normalise();
			v.multiply(o1.mass);
			
			v.multiply(1/o2.mass);
			o2.speedX = v.x;
			o2.speedY = v.y;
			
			o1.speedX = -v.x;
			o1.speedY = -v.y;
		}
		
		o1.speedX = (o1.speedX*(o1.mass-o2.mass)+2*o2.mass*o2.speedX)/(o1.mass+o2.mass);
		o1.speedY = (o1.speedY*(o1.mass-o2.mass)+2*o2.mass*o2.speedY)/(o1.mass+o2.mass);
		o2.speedX = (o2.speedX*(o2.mass-o1.mass)+2*o1.mass*o1.speedX)/(o1.mass+o2.mass);
		o2.speedY = (o2.speedY*(o2.mass-o1.mass)+2*o1.mass*o1.speedY)/(o1.mass+o2.mass);
	}
	private boolean collisionOfTwoBoundingBoxes(CollidingObject o1, CollidingObject o2) {
		float tab1[] = o1.BB.bBox.getBBTab();
		float tab2[] = o2.BB.bBox.getBBTab();
		
		if(!(tab1[0]>=tab2[2]||tab1[2]<=tab2[0]||tab1[1]>=tab2[3]||tab1[3]<=tab2[1]))
		{

//			if(tab1[0]>tab1[2])
//			{
//				System.out.println("1");
//			}
//			if((tab1[1]>=tab2[3]||tab1[3]<=tab2[1]))
//			{
//				System.out.println("2");
//			}
			float XPointer = tab1[4]-tab2[4];
			float YPointer = tab1[5]-tab2[5];
			float l1 = (tab1[3]-tab1[1])/2+(tab2[3]-tab2[1])/2;
			float l2 = (tab1[2]-tab1[0])/2+(tab2[2]-tab2[0])/2;
			//System.out.println("Kolizja dwóch prostok¹tów "+XPointer+" "+YPointer);
			boolean vertical = false;
			if(Math.abs(XPointer/l2)>=Math.abs(YPointer/l1))
				vertical = false;
			else
				vertical = true;
			//System.out.println("Kolizja dwóch prostok¹tów "+XPointer+" "+YPointer+" "+vertical);
			float L1, L2;
			if(!o1.isStatic&&!o2.isStatic)
			{
			if(vertical)
			{
				L1 = (tab1[3]-tab1[1])/2;
				L2 = (tab2[3]-tab2[1])/2;
				
				float dist = (L1+L2)-(Math.abs(tab1[5]-tab2[5]));
				//System.out.println("Zag³êbienie na "+dist);
				if(YPointer>0)
				{
					o1.moveToPos(o1.x, o1.y+dist/2);
					o2.moveToPos(o2.x, o2.y-dist/2);
				}
				else
				{
					o1.moveToPos(o1.x, o1.y-dist/2);
					o2.moveToPos(o2.x, o2.y+dist/2);
				}
			}
			else
			{
				
				L1 = (tab1[2]-tab1[0])/2;
				L2 = (tab2[2]-tab2[0])/2;
				
				float dist = (L1+L2)-(Math.abs(tab1[4]-tab2[4]));
				//System.out.println("Zag³êbienie na "+dist);
				if(XPointer>0)
				{
					o1.moveToPos(o1.x+dist/2, o1.y);
					o2.moveToPos(o2.x-dist/2, o2.y);
				}
				else
				{
					o1.moveToPos(o1.x-dist/2, o1.y);
					o2.moveToPos(o2.x+dist/2, o2.y);
				}
			}
			}
			else if(o1.isStatic&&!o2.isStatic)
			{
				if(vertical)
				{
					L1 = (tab1[3]-tab1[1])/2;
					L2 = (tab2[3]-tab2[1])/2;
					
					float dist = (L1+L2)-(Math.abs(tab1[5]-tab2[5]));
					//System.out.println("Zag³êbienie na "+dist);
					if(YPointer>0)
					{
						if(o2.isPhisical)
						o2.speedY*=-1;
						o2.moveToPos(o2.x, o2.y-dist);
					}
					else
					{
						if(o2.isPhisical)
						o2.speedY*=-1;
						o2.moveToPos(o2.x, o2.y+dist);
					}
				}
				else
				{
					
					L1 = (tab1[2]-tab1[0])/2;
					L2 = (tab2[2]-tab2[0])/2;
					
					float dist = (L1+L2)-(Math.abs(tab1[4]-tab2[4]));
					//System.out.println("Zag³êbienie na "+dist);
					if(XPointer>0)
					{
						if(o2.isPhisical)
						o2.speedX*=-1;
						o2.moveToPos(o2.x-dist, o2.y);
					}
					else
					{
						if(o2.isPhisical)
						o2.speedX*=-1;
						o2.moveToPos(o2.x+dist, o2.y);
					}
				}
			}
			else if(!o1.isStatic&&o2.isStatic)
			{
				if(vertical)
				{
					L1 = (tab1[3]-tab1[1])/2;
					L2 = (tab2[3]-tab2[1])/2;
					
					float dist = (L1+L2)-(Math.abs(tab1[5]-tab2[5]));
					//System.out.println("Zag³êbienie na "+dist);
					if(YPointer>0)
					{
						if(o1.isPhisical)
						o1.speedY*=-1;
						o1.moveToPos(o1.x, o1.y+dist);
					}
					else
					{
						if(o1.isPhisical)
						o1.speedY*=-1;
						o1.moveToPos(o1.x, o1.y-dist);
					}
				}
				else
				{
					
					L1 = (tab1[2]-tab1[0])/2;
					L2 = (tab2[2]-tab2[0])/2;
					
					float dist = (L1+L2)-(Math.abs(tab1[4]-tab2[4]));
					//System.out.println("Zag³êbienie na "+dist);
					if(XPointer>0)
					{
						if(o1.isPhisical)
						o1.speedX*=-1;
						o1.moveToPos(o1.x+dist, o1.y);
					}
					else
					{
						if(o1.isPhisical)
						o1.speedX*=-1;
						o1.moveToPos(o1.x-dist, o1.y);
					}
				}
			}
			return true;
		}
		return false;
	}
//	if(!(minMax[0]>=maxX||minMax[2]<=minX||minMax[1]>=maxY||minMax[3]<=minY))
//	{
//		sortedTabIndexX.add(new PointWithIndex(points.get(conections.get(i).getP1()).x, i));
//		sortedTabIndexX.add(new PointWithIndex(points.get(conections.get(i).getP2()).x, i));
//	}
	private boolean collisionOfTwoCircles(CollidingObject o1, CollidingObject o2) {
		float tab1[] = o1.BB.bCircle.getBBTab();
		float tab2[] = o2.BB.bCircle.getBBTab();
		if(tab1[0]==tab2[0]&&tab1[1]==tab2[1])
		{
			if(!o1.isStatic&&!o2.isStatic)
			{
				tab1[0]+=1;
			}
			else if(o1.isStatic&&!o2.isStatic)
			{
				tab2[0]+=1;
			}
			else if(!o1.isStatic&&o2.isStatic)
			{
				tab1[0]+=1;
			}
		}
		if(TB.distanceF(tab1[0], tab1[1], tab2[0], tab2[1])<(tab1[2]+tab2[2]))
		{
			
			Vector2D v1 = new Vector2D(tab1[0]-tab2[0], tab1[1]-tab2[1]);
			Vector2D v2 = new Vector2D(tab2[0]-tab1[0], tab2[1]-tab1[1]);
			v1.normalise();
			v2.normalise();
			float lenght = (tab1[2]+tab2[2])-TB.distanceF(tab1[0], tab1[1], tab2[0], tab2[1]);

			if(!o1.isStatic&&!o2.isStatic)
			{
				v1.multiply(lenght/2);
				v2.multiply(lenght/2);
				o1.moveToPos(o1.x+v1.x, o1.y+v1.y);
				o2.moveToPos(o2.x+v2.x, o2.y+v2.y);
				
				if(o1.colliding&&o2.isPhisical)
				{
					o2.speedX = o1.speedX;
					o2.speedY = o1.speedY;
				}
				else if(o2.colliding&&o1.isPhisical)
				{
					o1.speedX = o2.speedX;
					o1.speedY = o2.speedY;
				}
			}
			else if(o1.isStatic&&!o2.isStatic)
			{
				v2.multiply(lenght);
				if(o2.isPhisical)
					o2.speedX*=-1;
				o2.moveToPos(o2.x+v2.x, o2.y+v2.y);
			}
			else if(!o1.isStatic&&o2.isStatic)
			{
				v1.multiply(lenght);
				if(o1.isPhisical)
					o1.speedX*=-1;
				o1.moveToPos(o1.x+v1.x, o1.y+v1.y);
			}
			return true;
		}
		return false;
	}
	
	
}
