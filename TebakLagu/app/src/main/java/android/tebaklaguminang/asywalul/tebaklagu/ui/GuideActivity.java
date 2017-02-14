package android.tebaklaguminang.asywalul.tebaklagu.ui;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.tebaklaguminang.asywalul.tebaklagu.R;
import android.tebaklaguminang.asywalul.tebaklagu.data.Const;
import android.tebaklaguminang.asywalul.tebaklagu.model.DialogButtonClickListener;
import android.tebaklaguminang.asywalul.tebaklagu.util.ViewUtil;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.BounceInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

public class GuideActivity extends AppCompatActivity{

    private TextView tvPercent,ket;
    private TextView tvFooter;
    private AdView mAdView;
    private ImageView keluar,musikoff,musikon;
    private Button caro;
    private RelativeLayout ivPan;
    private MediaPlayer mMediaPlayer = null;
    private boolean isPlayMusic = true;
    private Activity activity;
    private SharedPreferences sp;
    private int mCurrentIndex = 0;
    private Button start;
    private Button rate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide2);
        caro = (Button) findViewById(R.id.btn_tebak_lirik);
        start = (Button)findViewById(R.id.btn_tebak_judul);
        rate  = (Button)findViewById(R.id.btn_tebak_pencipta);

        rate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMediaPlayer.stop();
                final String appName = getPackageName();//your application package name i.e play store application url
                try {
                    startActivity(new Intent(Intent.ACTION_VIEW,
                            Uri.parse("market://details?id="
                                    + appName)));
                } catch (android.content.ActivityNotFoundException anfe) {
                    startActivity(new Intent(
                            Intent.ACTION_VIEW,
                            Uri.parse("http://play.google.com/store/apps/details?id=minang.laguminang.padangsumbar"
                                    + appName)));
                }


            }
        });

        ket  = (TextView)findViewById(R.id.marque);
        ket.setEllipsize(TextUtils.TruncateAt.MARQUEE);
        ket.setText("Tebak lagu minang merupakan game khusus bagi pecinta lagu minang dimana pun berada, kok nan di rantau ingek jo lah kampuang, danga jo lah saluang");
        ket.setSelected(true);
        ket.setSingleLine(true);
        ket.setAllCaps(true);
        sp = getSharedPreferences("config", MODE_PRIVATE);




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

        musikon  = (ImageView)findViewById(R.id.iv_volume_on);
        musikoff = (ImageView)findViewById(R.id.iv_volume_off);
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

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startGame();
            }
        });


    }


    private void startTootipAnimation() {

        ObjectAnimator scaleY2 = ObjectAnimator.ofFloat(ivPan, "scaleY2", 0.8f);
        scaleY2.setDuration(200);

        ObjectAnimator scaleYBack2 = ObjectAnimator.ofFloat(ivPan, "scaleY2", 1f);
        scaleYBack2.setDuration(500);


        scaleYBack2.setInterpolator(new BounceInterpolator());
        final AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.setStartDelay(1000);
        animatorSet.playSequentially(scaleY2,scaleYBack2);
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
        int finis = sp.getInt("finis",0);
        tvPercent.setText(String.valueOf(finis)+"dari"+text);
        tvFooter.setText(text);
    }

    public void s(){

        mMediaPlayer = new MediaPlayer();
        mMediaPlayer = MediaPlayer.create(this, R.raw.saluang);
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

    public void startGame(){
        mMediaPlayer.stop();
        int finis = sp.getInt("finis",0);

        if(finis==4) {
            Intent i = new Intent(GuideActivity.this,PassActivity.class);
            startActivity(i);
        }else {
            mCurrentIndex = Integer.parseInt(sp.getString("done", "0"));
            Intent i = new Intent(GuideActivity.this,MainActivity2.class);
            startActivity(i);
            Toast.makeText(GuideActivity.this,String.valueOf(finis),Toast.LENGTH_LONG).show();
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
        startActivity(i);
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
