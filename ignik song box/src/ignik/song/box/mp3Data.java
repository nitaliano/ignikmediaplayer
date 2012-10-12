package ignik.song.box;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;


public class mp3Data {	
	private byte []b;
	private int fileBytes;
	private String id3;
	private String tag;
	
	private FileInputStream fis;
	
	private String artist;
	private String title;
	private String album;
	private String year;
	
	
	public mp3Data(File file) throws IOException{
		try {
			fis = new FileInputStream(file);
			fileBytes = (int)file.length();
			
			fis.skip(fileBytes - 128);
			
			b = new byte[128];
			
			fis.read(b);
			
			id3 = new String(b);
			
			tag = id3.substring(0, 3);
			
			setmp3Data();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	protected void setmp3Data(){
		if(tag.equals("TAG")){
			this.title = id3.substring(3, 32);
			this.artist  = id3.substring(33, 62);
			this.album  = id3.substring(63, 93);
			this.year   = id3.substring(93, 97);
		}
		
	}
	
	protected String getArtist(){
		return this.artist;
	}
	
	protected String getAlbum(){
		return this.album;
	}
	
	protected String[] getmp3Data(){
		String []s = new String[4];
		s[0] = this.artist;
		s[1] = this.title;
		s[2] = this.album;
		s[3] = this.year;
		
		return s;
	}
}