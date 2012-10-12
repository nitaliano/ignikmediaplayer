package ignik.song.box;

import java.io.File;
import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ListMusicActivity extends ListActivity {
	private ArrayList<File> musicFiles;
	private String musicKey;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_music);
        
        if(GlobalVars.globalList != null){
        	
        	Intent intent = getIntent();
        	
        	Bundle bundle = intent.getExtras();
        	
        	musicKey = bundle.getString("keyName");
        	
        	musicFiles = GlobalVars.globalList.getMusicFiles(musicKey);
        	
        	ArrayList<String> musicTitles = new ArrayList<String>();
        	
        	for(int i = 0; i < musicFiles.size(); i++){
        		File mFile = musicFiles.get(i);
        		musicTitles.add(mFile.getName());
        	}
        	
        	ArrayAdapter<String> adapt = new ArrayAdapter<String>(this, android.R.layout.simple_selectable_list_item, musicTitles);
            
            setListAdapter(adapt);
        }
        
        ListView listView = getListView();
        
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> av, View view, int pos,
					long id) {
				Intent intent = new Intent(getApplicationContext(), MusicPlayerActivity.class);
				
				File m = musicFiles.get(pos);
				String s = m.getPath();
				
				Bundle bundle = new Bundle();
				
				bundle.putString("keyName", musicKey);
				bundle.putString("filePath", s);
				
				intent.putExtras(bundle);
				
				startActivityForResult(intent, 100);
			}

        });	
    }
}
