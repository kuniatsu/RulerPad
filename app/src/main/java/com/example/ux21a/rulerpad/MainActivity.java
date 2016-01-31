package com.example.ux21a.rulerpad;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new CanvasTest2View(this,this));

    }

    public static void toast(MainActivity main,String mess){
        Toast.makeText(main, mess, Toast.LENGTH_SHORT).show();
    }


}
