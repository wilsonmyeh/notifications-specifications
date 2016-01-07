package woofsonmeowry.notificationspecification;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {
    private MediaPlayer player;
    private AudioManager am = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        // Permission to read notifications
        startActivityForResult(new Intent(android.provider.Settings.ACTION_NOTIFICATION_LISTENER_SETTINGS), 0);

        try
        {
            player = MediaPlayer.create(this, R.raw.trust_me);
        }
        catch(Exception e)
        {
            System.out.println("Media Error");
        }

        Intent intent = new Intent(this, NotificationListenerServ.class);
        startService(intent);
    }

    // handler for received Intents for the "mary-event" event
    @SuppressWarnings("deprecation")
    private BroadcastReceiver maryReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            System.out.println("Mary Message!");
            if(am.isWiredHeadsetOn()) // Set alarm to max volume if headphones aren't plugged in (no hearing loss!)
                am.setStreamVolume(AudioManager.STREAM_MUSIC, am.getStreamMaxVolume(AudioManager.STREAM_MUSIC), 0);
            player.start();
        }
    };
    @Override
     public void onResume() {
        super.onResume();
        LocalBroadcastManager.getInstance(this).registerReceiver(maryReceiver, new IntentFilter("mary-event"));
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
