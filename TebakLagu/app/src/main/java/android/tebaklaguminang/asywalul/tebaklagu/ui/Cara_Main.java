package android.tebaklaguminang.asywalul.tebaklagu.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.tebaklaguminang.asywalul.tebaklagu.R;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

/**
 * Created by asywalulfikri on 10/18/16.
 */

public class Cara_Main extends AppCompatActivity {

    private AdView mAdView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cara_main);
        mAdView = (AdView)findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder()
                .build();
        mAdView.loadAd(adRequest);
    }
}