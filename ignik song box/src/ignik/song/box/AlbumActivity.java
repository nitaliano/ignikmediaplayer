package ignik.song.box;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;

public class AlbumActivity extends ListActivity {
	private MusicList albumMusicList;
	private ArrayList<File> musicFiles;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album);
        
        albumMusicList = new MusicList();
        
        FindMusic fMusic = new FindMusic();
        
        musicFiles = fMusic.getMusicFiles("mnt/sdcard/Music/");
        
        for(File mFile : musicFiles){
			try {
				mp3Data mData = new mp3Data(mFile);
				albumMusicList.addMusic(mData.getAlbum(), mFile);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
        
        final String []albums = albumMusicList.getMusicStrings();
        
        ArrayList<String> albumTitle = new ArrayList<String>();
        
        for(int i=0; i < albums.length; i++){
        	albumTitle.add(albums[i].trim());
        }
        
        ArrayAdapter<String> adapt = new ArrayAdapter<String>(this, android.R.layout.simple_selectable_list_item, albumTitle);
        
        setListAdapter(adapt);
        
        ListView albumList = getListView();
        
        albumList.setOnItemClickListener(new AdapterView.OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> av, View view, int pos,
					long id) {
				
				GlobalVars.globalList = albumMusicList;
				
				Intent in = new Intent(getApplicationContext(),ListMusicActivity.class);
				Bundle bun = new Bundle();
				
				bun.putString("keyName", albums[pos]);
				in.putExtras(bun);
				startActivityForResult(in, 100);
			}
        	
        });	
    }
}
