import java.io.File;
import java.util.ArrayList;

public class LocalManga implements Comparable<LocalManga>
{
	private String mangaDirectory;
	private ArrayList<LocalChapter> allLocalChapter;
	private String mangaID;
	private String slash = System.getProperty("file.separator");
	
	//getter and setter
	public String getMangaDirectory() 
	{
		return mangaDirectory;
	}
	public void setMangaDirectory(String mangaDirectory) 
	{
		this.mangaDirectory = mangaDirectory;
	}
	public ArrayList<LocalChapter> getAllLocalChapter() 
	{
		return allLocalChapter;
	}
	public void setAllLocalChapter(ArrayList<LocalChapter> allLocalChapter) 
	{
		this.allLocalChapter = allLocalChapter;
	}
	public String getMangaID() 
	{
		return mangaID;
	}
	public void setMangaID(String mangaID) 
	{
		this.mangaID = mangaID;
	}
	
	//contructor
	public LocalManga(String websiteDirector, String mangaID) 
	{
		
		allLocalChapter = new ArrayList<LocalChapter>();
		
		this.mangaID = mangaID;
		this.mangaDirectory = websiteDirector + slash + mangaID;
		
		File folder = new File(this.mangaDirectory);
		
		if(!folder.exists())
		{
			folder.mkdirs();
		}else
		{
			File[] listOfFiles = folder.listFiles();
			
			if(listOfFiles != null)
			{
				for(int i = 0; i < listOfFiles.length; i++)
				{
					if(listOfFiles[i].isDirectory())
					{
						String chapterNumber = listOfFiles[i].getName();
						LocalChapter newChapter = new LocalChapter(this, mangaDirectory, chapterNumber, true);
						allLocalChapter.add(newChapter);
					}
				}
			}
		}
	}
	
	//find truyen theo numberChapter
	
	public LocalChapter findChapter(String chapterNumber)
	{
		for (int i = 0; i < this.allLocalChapter.size(); i++) 
		{
			if((this.allLocalChapter.get(i)).getChapterNumber().equals(chapterNumber))
			{
				return allLocalChapter.get(i);
			}
			
		}
		
		LocalChapter newChapter = new LocalChapter(this, mangaDirectory, chapterNumber, false);
		this.allLocalChapter.add(newChapter);
		return newChapter;
	}
	// sap xep
	@Override
	public int compareTo(LocalManga o) 
	{
		return this.getMangaID().compareTo(o.getMangaID());
	}
	@Override
	public String toString() 
	{
		return this.getMangaID();
	}
	
	
	
	
	
	
	
	
	
}
