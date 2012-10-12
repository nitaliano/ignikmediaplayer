package ignik.song.box;

import android.os.Bundle;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.app.TabActivity;
import android.content.Intent;

@SuppressWarnings("deprecation")
public class MainActivity extends TabActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        TabHost tabHost = getTabHost();
        
        TabSpec artistSpec, albumSpec;
        
        artistSpec = tabHost.newTabSpec("Artist");
        albumSpec = tabHost.newTabSpec("Album");
        
        artistSpec.setIndicator("Artist", getResources().getDrawable(R.drawable.icon_artist_tab));
        albumSpec.setIndicator("Album", getResources().getDrawable(R.drawable.icon_album_tab));
        
        Intent artistIntent = new Intent(this, ArtistActivity.class);
        Intent albumIntent = new Intent(this, AlbumActivity.class);
        
        artistSpec.setContent(artistIntent);
        albumSpec.setContent(albumIntent);
        
        tabHost.addTab(artistSpec);
        tabHost.addTab(albumSpec);
        
    }
}
