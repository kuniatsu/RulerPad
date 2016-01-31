package com.example.ux21a.rulerpad;

import android.graphics.Color;

/**
 * Created by kuniatus on 16/01/14.
 * 毎回色を変えるためのクラス。
 */
public class ColorPattern {

    private int color;


    public int colorMake(int index){
        switch (index%5){
            case 0:
                color = Color.RED;
                break;
            case 1:
                color = Color.GREEN;
                break;
            case 2:
                color = Color.BLACK;
                break;
            case 3:
                color = Color.BLUE;
                break;
            case 4:
                color = Color.GRAY;
                break;
        }
        return color;
    }




}
