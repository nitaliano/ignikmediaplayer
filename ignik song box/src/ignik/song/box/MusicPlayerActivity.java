package ignik.song.box;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.app.Activity;
import android.content.Intent;

public class MusicPlayerActivity extends Activity {
	private ArrayList<File> fList;
	
	private String fileToPlayName;
	private String musicKey;
	private String filePath;
	
	private Button playBtn;
	private Button pauseBtn;
	
	private MediaPlayer mp;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_player);
        
        playBtn = (Button)findViewById(R.id.playbtn);
        pauseBtn = (Button)findViewById(R.id.pausebtn);
        
        Intent in = getIntent();
        
        Bundle bun = in.getExtras();
        
        fileToPlayName = bun.getString("fileToPlayName");
        musicKey = bun.getString("keyName");
        
        fList = GlobalVars.globalList.getMusicFiles(musicKey);
        
        for(int i = 0; i < fList.size(); i++){
        	File tmp = fList.get(i);
        	if(musicKey.equals(tmp.getName())){
        		filePath = tmp.getPath();
        	}
        }
        
        mp = new MediaPlayer();
        
        playBtn.setOnClickListener(new View.OnClickListener(){

			@Override
			public void onClick(View v) {
				try {
					mp.reset();
					mp.setDataSource(filePath);
					mp.prepare();
					mp.start();
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SecurityException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalStateException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
        	
        });
        
        pauseBtn.setOnClickListener(new View.OnClickListener(){

			@Override
			public void onClick(View v) {
				mp.stop();
			}
        	
        });
        
    }
    
}
