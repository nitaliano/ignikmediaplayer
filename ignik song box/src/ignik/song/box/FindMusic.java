package ignik.song.box;
import java.io.File;
import java.util.ArrayList;


public class FindMusic {
	private ArrayList<File> fList;
	
	public FindMusic(){
		this.fList = new ArrayList<File>();
	}
	
	protected ArrayList<File> getMusicFiles(String path){		
		File files = new File(path);
		
		if(files.listFiles().length > 0){
			for(File curFile : files.listFiles()){
				if(curFile.isFile() && curFile.getName().endsWith(".mp3")){
					this.fList.add(curFile);
				}
				else if(curFile.isDirectory()){
					getMusicFiles(path + curFile.getName() + '/');
				}
			}
		}
		
		return fList;
	}
}
