package com.example.ux21a.rulerpad;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.math.BigDecimal;
import java.util.ArrayList;


/**
 * Created by ux21a on 2015/06/14.
 * 実際に画面上に現れる部分
 */
public class CanvasTest2View extends View {
    private Paint paint;
    private Path path;
    private MainActivity main;
    private Paint paintStr = new Paint();
    private Paint paintborder = new Paint();
    private LinePoint lp;
    private float lineLen;
    private DisplayParameters disp;
    private float leng=0;
    private int paintColor = Color.RED;
    private int borderColor = Color.BLACK;
    private int colorIndex;
    private ValueLog val;
    private ArrayList<ValueLog> valulogList = new ArrayList<ValueLog>();
    private ArrayList<Rect> rectList = new ArrayList<Rect>();//四角を書くためのもの


    public CanvasTest2View(Context context,MainActivity main) {
        super(context);
        path = new Path();
        paint = new Paint();
        paint.setColor(paintColor);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeJoin(Paint.Join.ROUND);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setStrokeWidth(10);
        this.main = main;
        lp = new LinePoint();
        disp = new DisplayParameters(main);


        //背景を作成
        int dpi = (int)disp.getDpi();
        int cmpx = (int)(new CalcPx(disp)).oneCm();
        paintborder.setColor(borderColor);
        paintborder.setStyle(Paint.Style.STROKE);

        float dispHeight=disp.getHeightPixels();
        float dispwidth=disp.getWidthPixels();
        int y=0;
        for(int i=0;i<dispHeight;i+=cmpx) {
            int x=0;
            for (int j = 0; j < dispwidth; j += cmpx) {
                rectList.add(new Rect(x, y, x + cmpx, y + cmpx));
                x += cmpx;
            }
            y+=cmpx;
        }


        //桁数の繰上げ
        //元データ
        double val2 = 42.195;

        //元データをBigDecimal型にする
        BigDecimal bd = new BigDecimal(val2);
        BigDecimal bd2 = bd.setScale(2, BigDecimal.ROUND_HALF_UP);  //小数第２位
        Log.v("第2四捨五入",String.valueOf(bd2));



    }




    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawPath(path, paint);


        //背景に四角を置く
        for(Rect rect:rectList) {
            canvas.drawRect(rect, paintborder);
        }



        int sizeNum = valulogList.size();
        int j = 1;
        for (int i = sizeNum; i > 0; i--) {
            ValueLog val = valulogList.get(i - 1);

            canvas.drawText(val.getValueStr(), 20, (j * 60), val.getPaintStr());//テキスト描画
            j++;

        }



    }




    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();



        switch(event.getAction()){
            case MotionEvent.ACTION_DOWN:

                if(valulogList.size()>10) {
                    //10回以上の書き込みは消してしまおう。
                    valulogList.clear();
                }

                paintColor = new ColorPattern().colorMake(valulogList.size());
                paint.setColor(paintColor);//色を設定

                leng = 0;
                lp.lineClear();
                path.reset();
                lp.lineAdd(x, y);
                path.moveTo(x, y);
                invalidate();

                break;
            case MotionEvent.ACTION_MOVE:
                path.lineTo(x, y);
                lp.lineAdd(x, y);
                path.moveTo(x, y);
                invalidate();
                break;
            case MotionEvent.ACTION_UP:
                path.lineTo(x, y);
                lp.lineAdd(x, y);
                path.moveTo(x, y);
                invalidate();

                ArrayList<Float> xarray = lp.getxArray();
                ArrayList<Float> yarray = lp.getyArray();
                int maxSize = xarray.size();
                float xa = xarray.get(maxSize - 1);




                for(int i=0;i<(maxSize-1);i++){
                    float sx = xarray.get(i);
                    float sy = yarray.get(i);
                    float ex = xarray.get(i + 1);
                    float ey = yarray.get(i+1);
                    float lengSum = lp.calcLine(sx, sy, ex, ey);//
                    leng += lengSum;

                }

                //cmに変更
                CalcPx calcpx = new CalcPx(disp);
                float cmValue = calcpx.calcCm(leng);


                this.val = new ValueLog(62,paintColor,cmValue,10,(60*(valulogList.size()+1)));
                valulogList.add(this.val);
                break;



//            Log.v("旧", "i:" + String.valueOf(i) + " x:" + String.valueOf(val.getPositionX()) + "-y:" + val.getPositionY());
//            Log.v("新", "i:" + String.valueOf(i) + " x:20.0-y:" + String.valueOf(j * 60));
//                    Log.v("計算ループ:","(sx:"+String.valueOf(sx)+"-sy:"+String.valueOf(sy)+")(ex:"+String.valueOf(ex)+"-ey:"+String.valueOf(ey)+")"+"　　結果＝"+String.valueOf(lengSum)+"合計:"+String.valueOf(leng));
//                Log.v("頭と結","(ax:"+String.valueOf(xarray.get(0))+"-ay:"+String.valueOf(yarray.get(0))+")(bx:"+String.valueOf(xarray.get(maxSize - 1))+"-by:"+String.valueOf(yarray.get(maxSize-1))+")    全体長さ＝"+String.valueOf(lp.calcLine(xarray.get(0), yarray.get(0), xarray.get(maxSize-1), yarray.get(maxSize-1))));
//            //インチに変更
//            float inchValue = calcpx.calcInch(leng);
//                float leng = lp.calcLine(xarray.get(0),xarray.get(maxSize-1),yarray.get(0),yarray.get(maxSize-1));
//                Log.v("x", lp.xStringChange());
//                Log.v("y",lp.yStringChange());
//                Log.v("x",String.valueOf(lp.lineSize()[0]));
//                main.toast(main,"x:");
//                Log.v("y",String.valueOf(lp.lineSize()[1]));


        }
            return true;
    }



}
