import java.util.ArrayList;

public interface OnlineWebsite 
{
	public ArrayList<OnlineManga> getAllOnlineManga();
	public void setAllOnlineManga(ArrayList<OnlineManga> allOnlineManga);
	public LocalWebsite getLocalWebsite();
	public void setLocalWebsite(LocalWebsite localWebsite);
	
	public void fetchAllOnlineManga();
	
	public ArrayList<OnlineManga> SearchAllOnlineManga(String condition);
}
