package core;

import windows.BasicWindow;

public class MainGame {

	public static void main(String[] args) 
	{
		BasicWindow w = new BasicWindow(1280, 720, 10, 10, "NAME");
		w.initializeData();
		while(w.isVisible())
		{
			w.refreshFrame();
		}
		w.clearUp();
	}
}
