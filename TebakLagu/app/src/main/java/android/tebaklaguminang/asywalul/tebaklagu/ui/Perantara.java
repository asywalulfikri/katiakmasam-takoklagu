package android.tebaklaguminang.asywalul.tebaklagu.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.tebaklaguminang.asywalul.tebaklagu.R;

/**
 * Created by asywalulfikri on 10/31/16.
 */

public class Perantara extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kosong);



        Intent i = new Intent(Perantara.this, MainActivity.class);
        startActivity(i);
    }
}