/*
 * Copyright (C) 2017 The AndroidLatexExt Project
 */

package maximsblog.blogspot.com.jlatexmath.core;

import android.graphics.Typeface;

/**
 * Created by yangzc on 17/2/8.
 */
public class Text {

    private String c;
    private final Typeface font;
    private final Metrics m;
    private final int fontCode;

    public Text(String c, Typeface f, int fc, Metrics m) {
        if (c == null) {
            c = "";
        }
        font = f;
        fontCode = fc;
        this.c = c;
        this.m = m;
    }

    public TextFont getTextFont() {
        return new TextFont(c, fontCode);
    }

    public String getText() {
        return c;
    }

    public Typeface getFont() {
        return font;
    }

    public int getFontCode() {
        return fontCode;
    }

    public float getWidth() {
        return m.getWidth() * c.toCharArray().length;
    }

    public float getItalic() {
        return m.getItalic();
    }

    public float getHeight() {
        return m.getHeight();
    }

    public float getDepth() {
        return m.getDepth();
    }

    public Metrics getMetrics() {
        return m;
    }

    public void setText(String text){
        this.c = text;
    }
}
