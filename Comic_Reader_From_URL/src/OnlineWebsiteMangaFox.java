import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

public class OnlineWebsiteMangaFox implements OnlineWebsite {
	
	private ArrayList<OnlineManga> allOnlineManga;
	private LocalWebsite localWebsite;
	
	public ArrayList<OnlineManga> getAllOnlineManga() 
	{
		return allOnlineManga;
	}
	public void setAllOnlineManga(ArrayList<OnlineManga> allOnlineManga) 
	{
		this.allOnlineManga = allOnlineManga;
	}
	
	
	public LocalWebsite getLocalWebsite() 
	{
		return localWebsite;
	}
	public void setLocalWebsite(LocalWebsite localWebsite) 
	{
		this.localWebsite = localWebsite;
	}
	
	
	public OnlineWebsiteMangaFox(LocalWebsite localWebsite) 
	{
			allOnlineManga = new ArrayList<OnlineManga>();
			this.localWebsite = localWebsite;
	}
	
	public void fetchAllOnlineManga()
	{
		BufferedReader in = null;
		
		try
		{
			URL url = new URL("http://fanfox.net/manga/");
			in = new BufferedReader(new InputStreamReader(url.openStream()));
			String line;
			
			while((line = in.readLine()) != null)
			{
				int begin = line.indexOf("<li><a href=\"fanfox.net/manga/");
				if( begin > 0)
				{
					int start = line.indexOf("href=\"",begin) + 6;
					int end = line.indexOf("\"", start + 6);
					String link = line.substring(start,end);
					int startName = line.indexOf(">",end) + 1;
					int endName = line.indexOf("</", startName);
					String id = line.substring(startName,endName);
					
					OnlineManga fetchedManga = new OnlineMangaMangaFox(localWebsite,line,id);
					this.allOnlineManga.add(fetchedManga);
				}
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		} finally
		{
			if(in != null)
			{
				try
				{
					in.close();
				}catch(IOException e)
				{
					e.printStackTrace();
				}
			}
		}
	}
	
	public ArrayList<OnlineManga> SearchAllOnlineManga(String condition)
	{
		ArrayList<OnlineManga> result = new ArrayList<OnlineManga>();
		for (int i = 0; i < this.allOnlineManga.size(); i++) 
		{
			if((this.allOnlineManga.get(i).getMangaID().toUpperCase().indexOf(condition.toUpperCase())) >= 0)
			{
				result.add(this.allOnlineManga.get(i));
			}
		}
		return result;
	}
		
	


}
