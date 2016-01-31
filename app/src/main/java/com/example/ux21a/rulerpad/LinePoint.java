package com.example.ux21a.rulerpad;

import java.util.ArrayList;



/**
 * Created by kuniatus on 16/01/09.
 * 書かれた線の情報を計算したり保存してたりするクラス
 */
public class LinePoint {

    private ArrayList<Float> xArray = new ArrayList<Float>();
    private ArrayList<Float> yArray = new ArrayList<Float>();

    public ArrayList<Float> getxArray(){
        return xArray;
    }
    public ArrayList<Float> getyArray(){
        return yArray;
    }
    public void setxArray(ArrayList<Float> xArray){
        this.xArray = xArray;
    }
    public void setyArray(ArrayList<Float> yArray){
        this.yArray = yArray;
    }





    public void lineAdd(float x,float y){
        //二つの配列に値を入れる
        xArray.add(x);
        yArray.add(y);
    }
    public void lineClear(){
        //配列を初期化
        xArray.clear();
        yArray.clear();
    }
    public int[] lineSize(){
        //配列数を返す
        int xy[] = new int[2];
        xy[0] = xArray.size();
        xy[1] = yArray.size();
        return xy;
    }

    public String xStringChange(){
        String xStr="";
        for(float x: xArray){
            xStr += String.valueOf(x)+":";
        }
        return xStr;
    }

    public String yStringChange(){
        //テスト用
        String yStr="";
        for(float y: yArray){
            yStr += String.valueOf(y)+":";
        }
        return yStr;
    }


    public float calcLine(float ax,float ay,float bx,float by){
        //三平方の定理
        Double teihen;
        Double takasa;
        teihen = Math.pow((double)bx - (double)ax, 2);
        takasa = Math.pow((double)by - (double)ay, 2);
        return (float)Math.sqrt(teihen + takasa);
    }

    public Double calcLine(Double ax,Double ay,Double bx,Double by){
        //三平方の定理
        Double teihen;
        Double takasa;
        teihen = Math.pow(bx - ax, 2);
        takasa = Math.pow(by - ay, 2);
        return Math.sqrt(teihen + takasa);
    }

    public float calcLineInch(float ax,float ay,float bx,float by){
        //三平方の定理＆戻り値をインチに
        //三平方
        Double teihen;
        Double takasa;
        teihen = Math.pow((double)bx - (double)ax, 2);
        takasa = Math.pow((double)by - (double)ay, 2);
        float pxNum = (float)Math.sqrt(teihen + takasa);
        return pxNum;

    }






}
