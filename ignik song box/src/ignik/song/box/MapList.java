package ignik.song.box;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;


public class MapList<S,T>{
	private Map<S, ArrayList<T>> map;
	
	public MapList(){
		this.map = new HashMap<S,ArrayList<T>>();
	}
	
	protected void addValue(S key, T val){
		if(!keyExists(key)){
			ArrayList<T> l = new ArrayList<T>();
			l.add(val);
			this.map.put(key, l);
		} else {
			ArrayList<T> l = this.map.get(key);
			l.add(val);
			this.map.put(key, l);
		}
	}
	
	protected Set<S> getKeys(){
		if(!this.map.isEmpty()){
			return this.map.keySet();
		}
		
		return null;
	}
	
	protected ArrayList<T> getValuesByKey(S key){
		if(keyExists(key)){
			return map.get(key);
		}
		
		return null;
	}
	
	protected boolean keyExists(S key){
		
		if(!this.map.isEmpty()){
			if(this.map.get(key) != null){
				return true;
			}
		}
		
		return false;
	}
}
