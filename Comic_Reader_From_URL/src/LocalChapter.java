import java.io.File;
import java.util.ArrayList;

public class LocalChapter implements Comparable<LocalChapter>
{
	private LocalManga localManga;
	private boolean isDownload;
	private String chapterDirectory;
	private ArrayList<String> allPageAddress;
	private String chapterNumber;
	private String slash = System.getProperty("file.separator");
	
	//getter and setter
	public LocalManga getLocalManga() 
	{
		return localManga;
	}
	public void setLocalManga(LocalManga localManga) 
	{
		this.localManga = localManga;
	}
	
	public boolean isDownload() 
	{
		return isDownload;
	}
	public void setDownload(boolean isDownload) 
	{
		this.isDownload = isDownload;
	}
	
	public String getChapterDirectory() 
	{
		return chapterDirectory;
	}
	public void setChapterDirectory(String chapterDirectory) 
	{
		this.chapterDirectory = chapterDirectory;
	}
	
	public String getChapterNumber() 
	{
		return chapterNumber;
	}
	public void setChapterNumber(String chapterNumber) 
	{
		this.chapterNumber = chapterNumber;
	}
	
	
	public ArrayList<String> getAllPageAddress() {
		return allPageAddress;
	}
	public void setAllPageAddress(ArrayList<String> allPageAddress) {
		this.allPageAddress = allPageAddress;
	}
	//contructor
	public LocalChapter(LocalManga localManga, String mangaDirectory, String chapterNumber, boolean isDownload) 
	{
		this.allPageAddress = new ArrayList<String>();
		this.localManga = localManga;
		this.chapterDirectory = chapterDirectory + slash + chapterNumber;
		this.chapterNumber = chapterNumber;
		this.isDownload = isDownload;
		
		if(isDownload)
		{
			readData();
		}
	
	}
	
	//them trang vao tap dang xem
	public void addPage(String pageAddress)
	{
		allPageAddress.add(pageAddress);
	}
	
	private void readData()
	{
		File folder = new File(chapterDirectory);
		
		if(!folder.exists())
		{
			folder.mkdirs();
		}
		
		File[] listOfFiles = folder.listFiles();
		
		if(listOfFiles != null)
		{
			for(int i = 0; i < listOfFiles.length; i++)
			{
				if(listOfFiles[i].isFile())
				{
					String pageAddress = this.chapterDirectory + slash + listOfFiles[i].getName();
					this.addPage(pageAddress);
				}
			}
		}
	}
	//sap xep
	@Override
	public int compareTo(LocalChapter o) 
	{
		return this.chapterNumber.compareTo(((LocalChapter)o).chapterNumber);
	}
	// hien thi
	@Override
	public String toString() 
	{
		return this.chapterNumber;
	}
	
	
	
	
	
	
	
	
}
