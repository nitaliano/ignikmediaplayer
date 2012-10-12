package ignik.song.box;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import android.os.Bundle;
import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class ArtistActivity extends ListActivity {
	private MusicList artistMusicList;
	private ArrayList<File> musicFiles;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artist);
        
        artistMusicList = new MusicList();
        
        FindMusic fMusic = new FindMusic();
        
        musicFiles = fMusic.getMusicFiles("mnt/sdcard/Music/");
        
        for(File mFile : musicFiles){
			try {
				mp3Data mData = new mp3Data(mFile);
				artistMusicList.addMusic(mData.getArtist(), mFile);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
        
        final String []artists = artistMusicList.getMusicStrings();
        
        ArrayList<String> songTitle = new ArrayList<String>();
        
        for(int i=0; i < artists.length; i++){
        	songTitle.add(artists[i].trim());
        }
        
        ArrayAdapter<String> adapt = new ArrayAdapter<String>(this, android.R.layout.simple_selectable_list_item, songTitle);
        
        setListAdapter(adapt);
        
        ListView artistList = getListView();
        
        artistList.setOnItemClickListener(new AdapterView.OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> av, View view, int pos,
					long id) {
				
				GlobalVars.globalList = artistMusicList;
				
				Intent in = new Intent(getApplicationContext(),ListMusicActivity.class);
				Bundle bun = new Bundle();
				
				bun.putString("keyName", artists[pos]);
				in.putExtras(bun);
				startActivityForResult(in, 100);
			}
        	
        });	
    }

}
