/*
 * Copyright (C) 2017 The AndroidLatexExt Project
 */

package maximsblog.blogspot.com.jlatexmath.core;

/**
 * Created by yangzc on 17/2/8.
 */
public class TextFont {

    public String c;
    public int fontId;
    public int boldFontId;

    public TextFont(String ch, int f) {
        this(ch, f, f);
    }

    public TextFont(String ch, int f, int bf) {
        c = ch;
        fontId = f;
        boldFontId = bf;
    }
}
