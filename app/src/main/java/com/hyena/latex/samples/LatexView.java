package com.hyena.latex.samples;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import maximsblog.blogspot.com.jlatexmath.core.AjLatexMath;
import maximsblog.blogspot.com.jlatexmath.core.Box;
import maximsblog.blogspot.com.jlatexmath.core.Insets;
import maximsblog.blogspot.com.jlatexmath.core.TeXConstants;
import maximsblog.blogspot.com.jlatexmath.core.TeXFormula;
import maximsblog.blogspot.com.jlatexmath.core.TeXIcon;

/**
 * Created by yangzc on 16/5/31.
 */
public class LatexView extends View {

    private TeXFormula mTexFormula;
    private TeXIcon mTexIcon;
    private TeXFormula.TeXIconBuilder mBuilder;
    private String mLatex;
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

    public LatexView(Context context) {
        super(context);
        init();
    }

    public LatexView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        mTexFormula = new TeXFormula();
        mBuilder = mTexFormula.new TeXIconBuilder()
                .setStyle(TeXConstants.STYLE_DISPLAY)
                .setSize(30).setTag("hello tag");
    }

    public void setFormula(String latex){
        this.mLatex = latex;
        mTexFormula.setLaTeX(latex);
    }

    public String getLatex() {
        return mLatex;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        relayout();
    }

//    private RectF mRectF = new RectF();
//    Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.WHITE);
        if (mTexIcon != null) {
//            mRectF.set(0, 0, mTexIcon.getTrueIconWidth(), mTexIcon.getTrueIconHeight());
//            mPaint.setStyle(Paint.Style.STROKE);
//            canvas.drawRect(mRectF, mPaint);
            mTexIcon.paintIcon(canvas, 0, 0, paint);
        }
    }

    public void relayout(){
        if (mBuilder != null) {
            mBuilder.setWidth(TeXConstants.UNIT_PIXEL, getWidth(), TeXConstants.ALIGN_LEFT);
            mBuilder.setIsMaxWidth(true);
            mBuilder.setInterLineSpacing(TeXConstants.UNIT_PIXEL, paint.getFontSpacing());
        }
        mTexIcon = mBuilder.build();
        mTexIcon.setInsets(new Insets(5, 5, 5, 5));
        Log.v("yangzc", "relayout-----> width:" + mTexIcon.getTrueIconWidth() + ", height:" + mTexIcon.getTrueIconHeight());
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (mTexIcon == null && mTexIcon.getSize() != 0)
            return false;

        float x = event.getX() / mTexIcon.getSize();
        float y = event.getY() / mTexIcon.getSize();

        int action = event.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
            {
                Log.v("yangzc", "width:" + mTexIcon.getTrueIconWidth() + ", height:" + mTexIcon.getTrueIconHeight());
                Log.v("yangzc", "x: " + x + ", y: " + y);
                FillInAtom.FillInBox fillInBox = getFillInBox(mTexIcon.getBox(), x, y);
                if (fillInBox != null) {
                    fillInBox.setFocus(true);
                }
                postInvalidate();
                break;
            }
        }
        return true;
    }

    public FillInAtom.FillInBox getFocusFillIn(){
        return getFocusFillIn(mTexIcon.getBox());
    }

    private FillInAtom.FillInBox getFillInBox(Box box, float x, float y) {
        if (box != null) {
            if (box instanceof FillInAtom.FillInBox) {
                if (((FillInAtom.FillInBox) box).getVisibleRect().contains(x, y)) {
                    return (FillInAtom.FillInBox) box;
                }
            } else {
                if (box.getChildren() != null && !box.getChildren().isEmpty()) {
                    for (int i = 0; i < box.getChildren().size(); i++) {
                        Box child = box.getChildren().get(i);
                        FillInAtom.FillInBox result = getFillInBox(child, x, y);
                        if (result != null) {
                            return result;
                        }
                    }
                }
            }
        }
        return null;
    }

    public FillInAtom.FillInBox getFocusFillIn(Box box) {
        if (box != null) {
            if (box instanceof FillInAtom.FillInBox) {
                if (((FillInAtom.FillInBox) box).hasFocus()) {
                    return (FillInAtom.FillInBox) box;
                }
            } else {
                if (box.getChildren() != null && !box.getChildren().isEmpty()) {
                    for (int i = 0; i < box.getChildren().size(); i++) {
                        Box child = box.getChildren().get(i);
                        FillInAtom.FillInBox result = getFocusFillIn(child);
                        if (result != null) {
                            return result;
                        }
                    }
                }
            }
        }
        return null;
    }
}
