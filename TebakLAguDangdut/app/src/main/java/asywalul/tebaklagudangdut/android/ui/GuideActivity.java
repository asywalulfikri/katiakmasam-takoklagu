package asywalul.tebaklagudangdut.android.ui;

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
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.BounceInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import asywalul.tebaklagudangdut.android.R;
import asywalul.tebaklagudangdut.android.data.ConstJudul;
import asywalul.tebaklagudangdut.android.data.ConstPenyanyi;
import asywalul.tebaklagudangdut.android.model.DialogButtonClickListener;
import asywalul.tebaklagudangdut.android.util.ViewUtil;


public class GuideActivity extends Activity {

    private TextView tvPercent;
    private TextView tvFooter;
    private AdView mAdView;
    private SharedPreferences sp1;
    private SharedPreferences sp2;
    private MediaPlayer mMediaPlayer = null;
    private int mCurrentIndex = 0;
    private ImageView logo;
    private RelativeLayout Frame;
    private ImageView ivPanbar;
    private Button btnTJ,btnTPeny,btnTpenc,btnTLirik;
    private ImageView ivVolumeon,ivVolumeoff;
    private LinearLayout rate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);

        sound();
        init();

        logo          = (ImageView)findViewById(R.id.iv_logo);
        Frame         = (RelativeLayout) findViewById(R.id.frame);
        ivPanbar      = (ImageView)findViewById(R.id.iv_pan_bar);
        rate          = (LinearLayout)findViewById(R.id.rate);

        btnTJ         = (Button)findViewById(R.id.btn_tebak_judul);
        btnTLirik     = (Button)findViewById(R.id.btn_tebak_lirik);
        btnTpenc      = (Button)findViewById(R.id.btn_tebak_pencipta);
        btnTPeny      = (Button)findViewById(R.id.btn_tebak_penyanyi);

        ivVolumeon    = (ImageView)findViewById(R.id.iv_volume_on);
        ivVolumeoff   = (ImageView)findViewById(R.id.iv_volume_off);

        ivVolumeon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMediaPlayer.stop();
                ivVolumeon.setVisibility(View.GONE);
                ivVolumeoff.setVisibility(View.VISIBLE);
            }
        });

        ivVolumeoff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMediaPlayer.start();
                ivVolumeon.setVisibility(View.VISIBLE);
                ivVolumeoff.setVisibility(View.GONE);
                sound();
            }
        });


        btnTJ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMediaPlayer.stop();
                sp1 = getSharedPreferences("config", MODE_PRIVATE);
                if(!ConstJudul.hasMoreMusic(mCurrentIndex)) {
                    mCurrentIndex = Integer.parseInt(sp1.getString("undone", "0"));
                    Intent i = new Intent(GuideActivity.this,PassActivityJudul.class);
                    startActivity(i);
                }else {
                    mCurrentIndex = Integer.parseInt(sp1.getString("done", "0"));
                    Intent i = new Intent(GuideActivity.this,TebakJudul.class);
                    i.putExtra("tebak","TEBAK JUDUL");
                    startActivity(i);
                }
            }
        });

        btnTLirik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMediaPlayer.stop();
                Intent i =  new Intent(GuideActivity.this,Cara_Main.class);
                startActivity(i);
            }
        });

        btnTpenc.setOnClickListener(new View.OnClickListener() {
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
                            Uri.parse("http://play.google.com/store/apps/details?id=asywalul.tebaklagudangdut.android"
                                    + appName)));
                }


            }
        });

        btnTPeny.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMediaPlayer.stop();
                sp2 = getSharedPreferences("configpenyanyi", MODE_PRIVATE);
                if(!ConstPenyanyi.hasMoreMusic(mCurrentIndex)) {
                    mCurrentIndex = Integer.parseInt(sp2.getString("undonepenyanyi", "0"));
                    Intent i = new Intent(GuideActivity.this,PassActivityPenyanyi.class);
                    startActivity(i);
                }else {
                    mCurrentIndex = Integer.parseInt(sp2.getString("donepenyanyi", "0"));
                    Intent i = new Intent(GuideActivity.this,TebakPenyanyi.class);
                    i.putExtra("tebak","TEBAK PENYANYI");
                    startActivity(i);
                }
            }
        });



        rate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                final String appName = getPackageName();//your application package name i.e play store application url
                        try {
                            startActivity(new Intent(Intent.ACTION_VIEW,
                                    Uri.parse("market://details?id="
                                            + appName)));
                        } catch (android.content.ActivityNotFoundException anfe) {
                            startActivity(new Intent(
                                    Intent.ACTION_VIEW,
                                    Uri.parse("http://play.google.com/store/apps/details?id=asywalul.tebaklagudangdut.android"
                                            + appName)));
                        }


                    }

        });





        mAdView = (AdView)findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder()
                .build();
        mAdView.loadAd(adRequest);


        startTootipAnimation();
        startTootipAnimation();

    }

    private void init() {
       // sp = getSharedPreferences("config", MODE_PRIVATE);
        tvPercent = (TextView) findViewById(R.id.tv_percent);
       // tvFooter = (TextView) findViewById(R.id.tv_footer);

      //  String text = sp.getString("undonepenyanyi", "0") + "/" + ConstJudul.SONG_INFO.length;*/
        tvPercent.setText("RATE APPS");
       // tvFooter.setText(text);
    }

    public void sound(){

        mMediaPlayer = new MediaPlayer();
        mMediaPlayer = MediaPlayer.create(this, R.raw.viva_dangdut);
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
/*
    public void startGame(View view){
        mMediaPlayer.stop();
        if(!ConstJudul.hasMoreMusic(mCurrentIndex)) {
            mCurrentIndex = Integer.parseInt(sp.getString("undone", "0"));
            Intent i = new Intent(GuideActivity.this,PassActivityJudul.class);
            startActivity(i);
        }else {
            mCurrentIndex = Integer.parseInt(sp.getString("done", "0"));
            Intent i = new Intent(GuideActivity.this,TebakJudul.class);
            startActivity(i);
        }


    }*/

    public void about(View view){
        //TODO
    }

    public void clearProgress(View view) {
        ViewUtil.showDialog(GuideActivity.this, "PASTIKAN MEMBERSIAHKAN？", new DialogButtonClickListener() {
            @Override
            public void onClick() {    //Pastikan untuk membersihkan permainan kemajuan itu?
                SharedPreferences.Editor et = sp1.edit();
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




    private void startTootipAnimation() {
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(logo, "scaleY", 0.8f);
        scaleY.setDuration(200);

        ObjectAnimator scaleYBack = ObjectAnimator.ofFloat(logo, "scaleY", 1f);
        scaleYBack.setDuration(500);

        ObjectAnimator scaleY2 = ObjectAnimator.ofFloat(ivPanbar, "scaleY2", 0.8f);
        scaleY2.setDuration(200);

        ObjectAnimator scaleYBack2 = ObjectAnimator.ofFloat(ivPanbar, "scaleY2", 1f);
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
        logo.setLayerType(View.LAYER_TYPE_HARDWARE, null);
        ivPanbar.setLayerType(View.LAYER_TYPE_HARDWARE,null);
        animatorSet.start();
    }

    private void startTootipAnimationn() {
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(ivPanbar, "scaleY", 0.8f);
        scaleY.setDuration(200);

        ObjectAnimator scaleYBack = ObjectAnimator.ofFloat(ivPanbar, "scaleY", 1f);
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
        ivPanbar.setLayerType(View.LAYER_TYPE_HARDWARE,null);
        animatorSet.start();
    }



}
