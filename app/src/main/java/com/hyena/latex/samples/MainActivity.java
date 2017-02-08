package com.hyena.latex.samples;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import maximsblog.blogspot.com.jlatexmath.core.AjLatexMath;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //初始化
        AjLatexMath.init(this);

    }
}
