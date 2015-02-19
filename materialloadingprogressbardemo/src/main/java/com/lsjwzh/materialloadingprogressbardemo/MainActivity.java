package com.lsjwzh.materialloadingprogressbardemo;

import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.lsjwzh.widget.materialloadingprogressbar.CircleProgressBar;


public class MainActivity extends ActionBarActivity {
    int progress = 0;
    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final CircleProgressBar progress2 = (CircleProgressBar) findViewById(R.id.progress2);

        handler = new Handler();
        for (int i = 0; i < 10; i++) {
            final int finalI = i;
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    if(finalI *10>=90){
                        progress2.setVisibility(View.INVISIBLE);
                    }else {
                        progress2.setProgress(finalI * 10);
                    }
                }
            },1000*(i+1));
        }

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
