package managers;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.imageio.ImageIO;

public class textureManager 
{
	private ArrayList<Texture> textureList;
	private HashMap<String, Integer> serchMap;
	
	public textureManager()
	{
		textureList= new ArrayList<Texture>();
		serchMap = new HashMap<>();
	}
	
	public int addNewTextureAndGetTextureID(String path, String name)
	{
		if(serchMap.containsKey(name))
		{
			System.out.println("Tekstura jest ju� w pami�ci");
			return serchMap.get(name);
		}
		else
		{
			BufferedImage tex = null;
			
			try {
				tex = ImageIO.read(new File(path));
			} catch (IOException e) {
				e.printStackTrace();
				System.out.println("B��d �adowania textury");
				return -10;
			}
			serchMap.put(name, textureList.size());
			textureList.add(new Texture(path, name, tex));
			System.out.println("Pomy�lnie za�adowano textur� "+name+" "+path+" "+(textureList.size()-1));
			return textureList.size()-1;
		}
	}
	
	public Texture getTexture(int i)
	{
		return textureList.get(i);
	}
}
