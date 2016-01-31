package com.example.ux21a.rulerpad;

import android.graphics.Paint;

import java.math.BigDecimal;

/**
 * Created by kuniatus on 16/01/15.
 * 画面上に表示させる測定値のクラス
 */
public class ValueLog {
    private ColorPattern colorPattern;
    private float value;
    private float positionX;
    private float positionY;


    private Paint paintStr;//大きさと色を設定


//    public ValueLog(int strsize,int color,String valustr,float positionx,float positiony){
    public ValueLog(int strsize,int color,float value,float positionx,float positiony){
        this.paintStr = new Paint();
        this.paintStr.setColor(color);
        this.paintStr.setTextSize(strsize);
        this.value = value;
        this.positionX = positionx;
        this.positionY = positiony;
    }

    public ValueLog(Paint paintstr,float value,float positionx,float positiony){
        this.paintStr = paintstr;
        this.value = value;
        this.positionX = positionx;
        this.positionY = positiony;
    }


    public String getValueStr() {
        BigDecimal bd = new BigDecimal(value);
        BigDecimal bd2 = bd.setScale(2, BigDecimal.ROUND_HALF_UP);  //小数第２位
        return "≒"+String.valueOf(bd2)+"cm";
    }

    public float getPositionX() {
        return positionX;
    }

    public float getPositionY() {
        return positionY;
    }

    public Paint getPaintStr() {
        return paintStr;
    }

}
