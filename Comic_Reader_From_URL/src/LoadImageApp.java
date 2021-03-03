import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class LoadImageApp extends Component
{
	private static final long serialVersionUID = 523276030756839284L;
	BufferedImage img;
	
	@Override
	public void paint(Graphics g) 
	{
		g.drawImage(img, 0, 0,null);
	}
	
	
	public LoadImageApp(String direc)
	{
		try
		{
			img = ImageIO.read(new File(direc));
		}catch(IOException e)
		{
			e.printStackTrace();
		}
		
		img = scaleImage(img, 700);
	}
	
	public BufferedImage scaleImage(BufferedImage image, int maxSideLength)
	{
		assert(maxSideLength > 0);
		double originalHeigth = image.getHeight();
		double originalWidth = image.getWidth();
		double scaleFactor = 0.0;
		
		if(originalWidth > originalHeigth)
		{
			scaleFactor = ((double) maxSideLength / originalWidth);
		}else
		{
			scaleFactor = ((double) maxSideLength / originalHeigth);
		}
		
		BufferedImage img = new BufferedImage((int) (originalWidth * scaleFactor), (int) (originalHeigth * scaleFactor), BufferedImage.TYPE_INT_RGB);
		
		Graphics g  = img.getGraphics();
		g.drawImage(image, 0, 0, img.getWidth(), img.getHeight(),null);
		return img;
	}
	
	public Dimension getPreferredSize()
	{
		if(img == null)
		{
			return new Dimension(100,100);
		}else
		{
			return new Dimension(img.getWidth(null), img.getHeight(null));
		}
	}
	
}
