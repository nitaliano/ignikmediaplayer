package ignik.song.box;

import java.io.File;
import java.util.ArrayList;
import java.util.Set;


public class MusicList {
	private MapList<String, File> mapList;
	
	public MusicList(){
		mapList = new MapList<String, File>();
	}
	
	protected void printMusicList(){
		Object[] keys = mapList.getKeys().toArray();
		
		for(int i = 0; i < keys.length; i++){
			System.out.println(keys[i]);
			ArrayList<File> f = mapList.getValuesByKey((String)keys[i]);
			for(File cf : f){
				System.out.println("\t"+cf.getName());
			}
		}
	}
	
	protected void addMusic(String musicString, File song){
		mapList.addValue(musicString, song);
	}
	
	protected String[] getMusicStrings(){
		Set<String> artists = mapList.getKeys();
		
		Object []obj = artists.toArray();
		String []s = new String[obj.length];
		
		for(int i = 0; i < obj.length; i++){
			s[i] = (String)obj[i];
		}
		
		return s;
	}
	
	protected ArrayList<File> getMusicFiles(String key){
		return mapList.getValuesByKey(key);
	}
	
	protected boolean checkKeys(String key){
		if(mapList.keyExists(key)){
			return true;
		}
		return false;
	}
}
