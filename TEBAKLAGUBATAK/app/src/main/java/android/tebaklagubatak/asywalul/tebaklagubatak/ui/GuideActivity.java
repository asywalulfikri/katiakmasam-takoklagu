package android.tebaklagubatak.asywalul.tebaklagubatak.ui;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.tebaklagubatak.asywalul.tebaklagubatak.R;
import android.tebaklagubatak.asywalul.tebaklagubatak.data.Const;
import android.tebaklagubatak.asywalul.tebaklagubatak.model.DialogButtonClickListener;
import android.tebaklagubatak.asywalul.tebaklagubatak.util.ViewUtil;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.BounceInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;


public class GuideActivity extends AppCompatActivity {

    private TextView tvPercent,ket;
    private TextView tvFooter;
    private AdView mAdView;
    private ImageView keluar,caro,musikoff,musikon;
    private RelativeLayout ivPan;
    private MediaPlayer mMediaPlayer = null;
    private boolean isPlayMusic = true;
    private Activity activity;
    private LinearLayout ivStar;
    private SharedPreferences sp;
    private int mCurrentIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        caro = (ImageView) findViewById(R.id.iv_about);

        keluar = (ImageView) findViewById(R.id.iv_exit);
        ket  = (TextView)findViewById(R.id.marque);
        ket.setEllipsize(TextUtils.TruncateAt.MARQUEE);
        ket.setText("HORAS!!!!. Tebak lagu batak merupakan game khusus bagi pecinta lagu Batak dimana pun berada, selamat menikmati :)");
        ket.setSelected(true);
        ket.setSingleLine(true);
        ket.setAllCaps(true);
        sp = getSharedPreferences("config", MODE_PRIVATE);

        ivStar = (LinearLayout)findViewById(R.id.ll_star);
        ivPan = (RelativeLayout)findViewById(R.id.frame);
        startTootipAnimation();
        startTootipAnimation2();




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
        musikon  = (ImageView)findViewById(R.id.iv_sound_on);
        musikoff = (ImageView)findViewById(R.id.iv_sound);
        musikoff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMediaPlayer.stop();
                musikon.setVisibility(View.VISIBLE);
                musikoff.setVisibility(View.GONE);
            }
        });

        musikon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                s();
                musikon.setVisibility(View.GONE);
                musikoff.setVisibility(View.VISIBLE);
            }
        });


    }


    private void startTootipAnimation() {
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(ivStar, "scaleY", 0.8f);
        scaleY.setDuration(200);

        ObjectAnimator scaleYBack = ObjectAnimator.ofFloat(ivStar, "scaleY", 1f);
        scaleYBack.setDuration(500);

        ObjectAnimator scaleY2 = ObjectAnimator.ofFloat(ivPan, "scaleY2", 0.8f);
        scaleY2.setDuration(200);

        ObjectAnimator scaleYBack2 = ObjectAnimator.ofFloat(ivPan, "scaleY2", 1f);
        scaleYBack2.setDuration(500);


        scaleYBack.setInterpolator(new BounceInterpolator());
        scaleYBack2.setInterpolator(new BounceInterpolator());
        final AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.setStartDelay(1000);
        animatorSet.playSequentially(scaleY, scaleYBack);
        animatorSet.playSequentially(scaleY2,scaleYBack2);
        animatorSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                animatorSet.setStartDelay(2000);
                animatorSet.start();
            }
        });
        ivStar.setLayerType(View.LAYER_TYPE_HARDWARE, null);
        ivPan.setLayerType(View.LAYER_TYPE_HARDWARE,null);
        animatorSet.start();
    }


    private void startTootipAnimation2() {

        ObjectAnimator scaleY = ObjectAnimator.ofFloat(ivPan, "scaleY", 0.8f);
        scaleY.setDuration(200);

        ObjectAnimator scaleYBack = ObjectAnimator.ofFloat(ivPan, "scaleY", 1f);
        scaleYBack.setDuration(500);


        scaleYBack.setInterpolator(new BounceInterpolator());
        final AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.setStartDelay(1000);
        animatorSet.playSequentially(scaleY, scaleYBack);
        animatorSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                animatorSet.setStartDelay(2000);
                animatorSet.start();
            }
        });
        ivPan.setLayerType(View.LAYER_TYPE_HARDWARE,null);
        animatorSet.start();
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
        mMediaPlayer = MediaPlayer.create(this, R.raw.gondang_batak);
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
        finish();
        mMediaPlayer.stop();
        finish();
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
