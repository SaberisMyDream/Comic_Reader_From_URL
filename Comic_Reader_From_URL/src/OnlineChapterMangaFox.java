import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

import javax.swing.JTextArea;

public class OnlineChapterMangaFox implements OnlineChapter 
{
	private LocalManga localManga;
	private LocalChapter localChapter;
	private String chapterURL;
	private ArrayList<String> allPageURL;
	private String chapterNumber,mangaID;
	private JTextArea downloadStatus;
	
	public LocalManga getLocalManga() 
	{
		return localManga;
	}
	public void setLocalManga(LocalManga localManga)
	{
		this.localManga = localManga;
	}
	
	
	public LocalChapter getLocalChapter() 
	{
		return localChapter;
	}
	public void setLocalChapter(LocalChapter localChapter) 
	{
		this.localChapter = localChapter;
	}
	
	
	public String getChapterURL() 
	{
		return chapterURL;
	}
	public void setChapterURL(String chapterURL) 
	{
		this.chapterURL = chapterURL;
	}
	
	
	public ArrayList<String> getAllPageURL() 
	{
		return allPageURL;
	}
	public void setAllPageURL(ArrayList<String> allPageURL)
	{
		this.allPageURL = allPageURL;
	}
	
	
	public String getChapterNumber() 
	{
		return chapterNumber;
	}
	public void setChapterNumber(String chapterNumber) 
	{
		this.chapterNumber = chapterNumber;
	}
	
	
	public String getMangaID() 
	{
		return mangaID;
	}
	public void setMangaID(String mangaID)
	{
		this.mangaID = mangaID;
	}
	
	
	public JTextArea getDownloadStatus() 
	{
		return downloadStatus;
	}
	public void setDownloadStatus(JTextArea downloadStatus) 
	{
		this.downloadStatus = downloadStatus;
	}
	
	
	public OnlineChapterMangaFox(LocalManga localManga, String chapterURL, String chapterNumber) 
	{
		allPageURL = new ArrayList<String>();
		this.localManga = localManga;
		this.chapterURL = chapterURL;
		this.chapterNumber = chapterNumber;
		
		localChapter = localManga.findChapter(chapterNumber);
	}
	
	private String extractURL(String urlText)
	{
		BufferedReader in = null;
		try
		{
			URL url = new URL(urlText);
			in = new BufferedReader(new InputStreamReader(url.openStream()));
			String line;
			while((line = in.readLine()) != null)
			{
				int before = line.indexOf("onclick=\"return enlarge()\">");
				if(before >= 0)
				{
					int begin = line.indexOf("img src",before) + 9;
					int end = line.indexOf("\"", begin  + 1);
					String extractedURL = line.substring(begin,end);
					return extractedURL;
				}
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}finally 
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
		return "";
				
	}
	
	public void fetchAllPageURL()
	{
		allPageURL = new ArrayList<String>();
		int lastPage = findLastPage();
		
		for (int i = 1; i < lastPage; i++) 
		{
			String urlText= chapterURL + i + ".html";
			downloadStatus.setText("Currently Downloading: " + this.toString() + "\nFetching page" + i);
			String extractedURL = extractURL(urlText);
			allPageURL.add(extractedURL);
		}
	}
	
	private int findLastPage()
	{
		BufferedReader in = null;
		try
		{
			URL url = new URL(chapterURL + 1 + ".html");
			in = new BufferedReader(new InputStreamReader(url.openStream()));
			String line;
			while((line = in.readLine()) != null)
			{
				int before = line.indexOf("var total_pages=");
				if(before > 0)
				{
					return Integer.parseInt(line.substring(before + 16, line.length() - 1));
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
		return 0;
	}
	
	public boolean isDownload()
	{
		return this.getLocalChapter().isDownload();
	}
	
	public String saveTo()
	{
		return this.getLocalChapter().getChapterDirectory();
	}
	public void setDownload(boolean isDownload)
	{
		this.localChapter.setDownload(isDownload);
	}
	@Override
	public String toString() 
	{
		return this.getChapterNumber() + " / " + this.getLocalManga().getMangaID();
	}
	

}
