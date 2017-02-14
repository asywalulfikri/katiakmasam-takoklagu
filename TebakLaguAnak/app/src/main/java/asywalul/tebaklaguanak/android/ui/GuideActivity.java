package asywalul.tebaklaguanak.android.ui;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import asywalul.tebaklaguanak.android.R;
import asywalul.tebaklaguanak.android.data.Const;
import asywalul.tebaklaguanak.android.model.DialogButtonClickListener;
import asywalul.tebaklaguanak.android.util.ViewUtil;


public class GuideActivity extends Activity {

    private TextView tvPercent;
    private TextView tvFooter;
    private AdView mAdView;
    private SharedPreferences sp;
    private Button caro,keluar;
    private MediaPlayer mMediaPlayer = null;
    private boolean isPlayMusic = true;
    private Activity activity;
    private int mCurrentIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        caro = (Button)findViewById(R.id.about);
        sp = getSharedPreferences("config", MODE_PRIVATE);
        keluar = (Button)findViewById(R.id.vv);



        caro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(GuideActivity.this,Cara_Main.class);
                startActivity(i);
            }
        });
        mAdView = (AdView)findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder()
                .build();
        mAdView.loadAd(adRequest);
        init();
        s();
        keluar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(GuideActivity.this,InterestialLogout2.class);
                mMediaPlayer.stop();
                startActivity(i);
            }
        });

    }

    private void init() {
        sp = getSharedPreferences("config", MODE_PRIVATE);
        tvPercent = (TextView) findViewById(R.id.tv_percent);
        tvFooter = (TextView) findViewById(R.id.tv_footer);

        String text = sp.getString("done", "0") + "/" + Const.SONG_INFO.length;
        tvPercent.setText(text);
        tvFooter.setText(text);
    }

    public void s(){

        mMediaPlayer = new MediaPlayer();
        mMediaPlayer = MediaPlayer.create(this, R.raw.backroundmusuk);
        mMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        mMediaPlayer.setLooping(true);
        mMediaPlayer.start();
    }


    @Override
    protected void onPause() {
        super.onPause();
    }



    @Override
    protected void onResume() {
        init();
        // s();
        super.onResume();



    }

    @Override
    protected void onDestroy() {
        mMediaPlayer.stop();
        super.onDestroy();
    }

    public void startGame(View view){
        mMediaPlayer.stop();
        if(!Const.hasMoreMusic(mCurrentIndex)) {
            mCurrentIndex = Integer.parseInt(sp.getString("undone", "0"));
            Intent i = new Intent(GuideActivity.this,PassActivity.class);
            startActivity(i);
        }else {
            mCurrentIndex = Integer.parseInt(sp.getString("done", "0"));
            Intent i = new Intent(GuideActivity.this,MainActivity.class);
            startActivity(i);
        }


    }

    public void about(View view){
        //TODO
    }

    public void clearProgress(View view) {
        ViewUtil.showDialog(GuideActivity.this, "PASTIKAN MEMBERSIAHKAN？", new DialogButtonClickListener() {
            @Override
            public void onClick() {    //Pastikan untuk membersihkan permainan kemajuan itu?
                SharedPreferences.Editor et = sp.edit();
                et.putString("done", "0");
                et.commit();
                init();
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(GuideActivity.this,InterestialLogout2.class);
        mMediaPlayer.stop();
        startActivity(i);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_guide, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
