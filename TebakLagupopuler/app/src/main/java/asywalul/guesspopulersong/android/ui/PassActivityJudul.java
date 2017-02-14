package asywalul.guesspopulersong.android.ui;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import asywalul.guesspopulersong.android.R;


public class PassActivityJudul extends Activity {

    private Button ulang;
    private SharedPreferences sp;
    private AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pass);
        mAdView = (AdView)findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder()
                .build();
        mAdView.loadAd(adRequest);

        ulang = (Button)findViewById(R.id.ulang);
        ulang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogOut();
               // Toast.makeText(PassActivityJudul.this,"HAPUS DATA UNTUAK MULAI BALIAK",Toast.LENGTH_LONG).show();

            }
        });

    }

    private void LogOut() {
              SharedPreferences sharedAt = getApplicationContext()
                      .getSharedPreferences("config", 0);
             SharedPreferences.Editor editor = sharedAt.edit();
             editor.clear();
             editor.commit();
              Intent i = new Intent(PassActivityJudul.this, GuideActivity.class);
               i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_TASK_ON_HOME);
              startActivity(i);
                    // getActivity().finish();

                  }

    public void clear(){

        SharedPreferences.Editor editor = sp.edit();
        editor.putString("config","0");
        editor.commit();


        // After logout redirect user to Login Activity
        Intent i = new Intent(PassActivityJudul.this, GuideActivity.class);

        // Closing all the Activities
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        // Add new Flag to start new Activity
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        // Staring Login Activity
        startActivity(i);
    }


    @Override
    public void onBackPressed() {
        Intent i = new Intent(PassActivityJudul.this,GuideActivity.class);
        startActivity(i);
    }


    //////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_pass, menu);
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
