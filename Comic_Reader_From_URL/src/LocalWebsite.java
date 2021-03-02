import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;

public class LocalWebsite 
{
	private String websiteDirector;
	private ArrayList<LocalManga> allLocalManga;
	
	//getter setter
	
	public String getWebsiteDirector() 
	{
		return websiteDirector;
	}
	public void setWebsiteDirector(String websiteDirector) 
	{
		this.websiteDirector = websiteDirector;
	}
	public ArrayList<LocalManga> getAllLocalManga() 
	{
		return allLocalManga;
	}
	public void setAllLocalManga(ArrayList<LocalManga> allLocalManga) 
	{
		this.allLocalManga = allLocalManga;
	}
	
	//contructor
	public LocalWebsite(String websiteDirectory) 
	{
		allLocalManga = new ArrayList<LocalManga>();
		this.websiteDirector = websiteDirectory;
		File folder = new File(this.websiteDirector);
		
		if(!folder.exists())
		{
			folder.mkdirs();
		}
		
		File[] listOfFiles = folder.listFiles();
		
		if(listOfFiles != null)
		{
			for (int i = 0; i < listOfFiles.length; i++) 
			{
				if(listOfFiles[i].isDirectory())
				{
					String mangaID = listOfFiles[i].getName();
					LocalManga newManga = new LocalManga(websiteDirectory, mangaID);
					allLocalManga.add(newManga);
				}
			}
		}
	}
	
	// tim kiem manga voi mangaId
	public LocalManga findID(String mangaID)
	{
		for (int i = 0; i < this.allLocalManga.size(); i++) 
		{
			return this.allLocalManga.get(i);
		}
		LocalManga newManga = new LocalManga(websiteDirector, mangaID);
		this.allLocalManga.add(newManga);
		return newManga;
	}
	
	
	
	

	
	
	
}
