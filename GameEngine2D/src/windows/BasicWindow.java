package windows;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import collizions.BCircle;
import collizions.BoundingBox;
import collizions.collizionChecker;
import managers.ControlsManager;
import managers.textureManager;
import objects.CollidingObject;
import objects.StaticObject;
import objects.phisicalObject;

public class BasicWindow extends Window implements MouseListener, MouseMotionListener, MouseWheelListener, KeyListener{


	private static final long serialVersionUID = 1L;
	private textureManager texManager;
	private ControlsManager cm;
	StaticObject so1,so2,so3, so4;
	CollidingObject co, co1,co2;
	phisicalObject co3;
	float a = 0;
	
	public BasicWindow(int resx, int resy, int locx, int locy, String title) {
		super(resx, resy, locx, locy, title);
		Window.antialiasing = true;
		Window.clearLastFrame = true;
		Window.lossyScale = true;
		Window.showFPS = true;
		Window.FPSrate = 60;
		this.addKeyListener(this);
		this.addMouseListener(this);
		this.addMouseWheelListener(this);
		this.addMouseMotionListener(this);
	}
	
	public void initializeData()
	{
		cm = new ControlsManager();
		texManager = new textureManager();
		//deklaruje obiekty static czyli takie bez kolizji i bez fizyki
		so1 = new StaticObject(224, 45, "test", "res/wow1.png", texManager);
		so2 = new StaticObject(24, 435, "test", "res/wow1.png", texManager);
		so3 = new StaticObject(566, 145, "test", "res/wow1.png", texManager);
		//Animowany obiekt podajesz tablicê nazw i tablicê pathy a na koniec czas co ile milisekund ma zmieniaæ klatkê
		so4 = new StaticObject(0, 0, new String[]{"test", "kok", "rect"}, new String[]{"res/wow1.png", "res/kolo.png", "res/rect.png"}, texManager, 500);
		
		co = new CollidingObject(50, 50, "kolo", "res/kolo.png", texManager, new BoundingBox(new BCircle(69, 69, 69))); //<--- To wygl¹da dziwnie ale to czysty przyapadek tu musi byæ podany punkt œrodka ko³a x y a póŸniej promieñ
		co1  = new phisicalObject(0, 50, "kolo", "res/kolo.png", texManager, new BoundingBox(new BCircle(69, 69, 69)), 1f);
		co2  = new phisicalObject(500, 250, "kolo", "res/kolo.png", texManager, new BoundingBox(new BCircle(69, 69, 69)), 1f);
		co3  = new phisicalObject(750, 400, "kolo", "res/kolo.png", texManager, new BoundingBox(new BCircle(69, 69, 69)), 1f);
		co.scale(2);
		co.isStatic = false;
		co1.isStatic=false;
		co2.isStatic=false;
		co3.isStatic=false;
	}
	
	public void refreshFrame()
	{
		super.refresh(texManager);
	}
	
	
	
	@Override
	public void useControls(Graphics2D g, int delta) 
	{
		super.useControls(g, delta);
		//Ustalam k¹t obrotu obiektów
		so1.angle = a;
		so2.angle = a;
		so3.angle = a;
			
		//ustawiam skale obiektu
		so1.scaleX = 2;
		so2.scaleY = 3;
				
		//zmieniam po³o¿enie objektów
		so3.x = a*2;

		//Ustanawiam po³o¿enie x y obiektu, jednak chce by myszka by³a na œrodku mordy, dlatego odejmujê
		// od pozycji myszy po³owy wysokoœci i szerokoœci tekstury*skala tekstury, po³o¿enie myszy mX, mY jest jeszcze dzielone przez skalê okna
		//by dzia³a³o równie¿ po przeskalowaniu okna

		
		if(co1.x<-100)
			co1.x = 1200;
		if(co1.x>1300)
			co1.x = 0;
		
		if(co2.x<-100)
			co2.x = 1200;
		if(co2.x>1300)
			co2.x = 0;
		
		if(co3.x<-100)
			co3.x = 1200;
		if(co3.x>1300)
			co3.x = 0;
		
		if(co1.y<-100)
			co1.y = 700;
		if(co1.y>800)
			co1.y = 0;
		
		if(co2.y<-100)
			co2.y = 700;
		if(co2.y>800)
			co2.y = 0;
		
		if(co3.y<-100)
			co3.y = 700;
		if(co3.y>800)
			co3.y = 0;
		co.speedX = cm.mX/super.getScale()-co.width/2*co.scaleX-co.x;
		co.speedY = cm.mY/super.getScale()-co.height/2*co.scaleY-co.y;
		co.refreshMovement();
		co1.refreshMovement();	
		co2.refreshMovement();	
		co3.refreshMovement();
		//co.moveToPos(, cm.mY/super.getScale()-co.height/2*co.scaleY);

				
		if(!cm.SPACE)
		so4.angle = -a;
				
		so4.scaleX+=cm.mouseWeel/50;
		so4.scaleY+=cm.mouseWeel/50;
		cm.reset();//konieczne przy wykorzystaniu ko³a myszy
				

				//zmieniam k¹t obrotu
				a+=1;
				if(a>360)
					a=0;
		
	}
	@Override
	public void drawFrame(Graphics2D g, int delta, textureManager texManager) 
	{
		super.drawFrame(g,delta,texManager);
		
		
		//nakazuje narysowaæ objekty
		so1.drawObject(g, texManager);
		so2.drawObject(g, texManager);
		so3.drawObject(g, texManager);
		so4.drawObject(g, texManager);
		
		co.drawObject(g, texManager);
		co1.drawObject(g, texManager);
		co2.drawObject(g, texManager);
		co3.drawObject(g, texManager);
	}
	
	@Override
	public void checkCollisions(Graphics2D g, int delta) 
	{
		super.checkCollisions(g, delta);
		//Objekt do sprawdzania kolizji
		collizionChecker cc = new collizionChecker();
		//Sprawdzanie kolizji pomiêdzy objektami
		//Kolejnoœæ ma znaczenie najpierw to co siê porusza z reszt¹ póŸniej reszta z reszt¹
		//Kiedyœ dopracujê to tak by dzia³a³o w dowolnej kolejnoœci
		System.out.println(cc.checkIfCollig(co, co1)); //<-- zwraca prawdê w razie kolizji
		cc.isCollig(co, co1);

		cc.isCollig(co, co2);
		cc.isCollig(co, co3);
		cc.isCollig(co1, co2);
		cc.isCollig(co1, co3);
		cc.isCollig(co2, co3);

	}
	public void clearUp() 
	{
		
		
	}


	public void keyPressed(KeyEvent e) {
		cm.keyPressed(e);
	}
	public void keyReleased(KeyEvent e) {
		cm.keyReleased(e);
	}
	public void keyTyped(KeyEvent e) {
		cm.keyTyped(e);
	}
	public void mouseWheelMoved(MouseWheelEvent arg0) {
		cm.mouseWheelMoved(arg0);
	}
	public void mouseDragged(MouseEvent arg0) {
		cm.mouseDragged(arg0);
	}
	public void mouseMoved(MouseEvent arg0) {
		cm.mouseMoved(arg0);
	}
	public void mouseClicked(MouseEvent arg0) {
		cm.mouseClicked(arg0);
	}
	public void mouseEntered(MouseEvent arg0) {
		cm.mouseEntered(arg0);
	}
	public void mouseExited(MouseEvent arg0) {
		cm.mouseExited(arg0);
	}
	public void mousePressed(MouseEvent arg0) {
		cm.mousePressed(arg0);
	}
	public void mouseReleased(MouseEvent arg0) {
		cm.mouseReleased(arg0);
	}
}
