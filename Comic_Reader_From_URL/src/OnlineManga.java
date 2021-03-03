import java.util.ArrayList;

public interface OnlineManga
{
	public LocalWebsite getLocalWebsite();
	public void setLocalWebsite(LocalWebsite localWebsite);
	
	public LocalManga getLocalManga();
	public void setLocalManga(LocalManga localManga);
	
	public String getMangaURL();
	public void setMangaURL(String mangaURL);
	
	public ArrayList<OnlineChapter> getAllOnlineChapter();
	public void setAllOnlineChapter(ArrayList<OnlineChapter> allOnlineChapter);
	
	public String getMangaID();
	public void setMangID(String mangaID);
	
	public void fetchAllOnlineChapter();
}
