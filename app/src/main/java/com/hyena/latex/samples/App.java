/*
 * Copyright (C) 2017 The AndroidLatexExt Project
 */

package com.hyena.latex.samples;

import android.app.Application;

import maximsblog.blogspot.com.jlatexmath.core.AjLatexMath;

/**
 * Created by yangzc on 17/2/8.
 */
public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        //初始化
        AjLatexMath.init(this);

    }
}
