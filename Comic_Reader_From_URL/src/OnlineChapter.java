import java.util.ArrayList;

import javax.swing.JTextArea;

public interface OnlineChapter 
{
	//Geter and Seter
		public LocalManga getLocalManga();
		public void setLocalManga(LocalManga localManga);
		public LocalChapter getLocalChapter();
		public void setLocalChapter(LocalChapter localChapter);
		public String getChapterURL();
		public void setChapterURL(String chapterURL);
		public ArrayList<String> getAllPageURL();
		public void setAllPageURL(ArrayList<String> allPageURL);
		public String getChapterNumber();
		public void setChapterNumber(String chapterNumber);
		public String getMangaID();
		public void setMangaID(String mangaName);
		public JTextArea getDownloadStatus();
		public void setDownloadStatus(JTextArea downloadStatus);

		//Vong lap url anh
		public void fetchAllPageURL();
		
		//kiem tra co download khong
		public boolean isDownload();
		
		//vi tri save
		public String saveTo();
		
		//local chapter
		public void setDownload(boolean isDownload);
		
		public String toString ();
}
