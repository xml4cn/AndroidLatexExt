package com.hyena.latex.samples;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import maximsblog.blogspot.com.jlatexmath.ExampleFormula;
import maximsblog.blogspot.com.jlatexmath.core.AjLatexMath;
import maximsblog.blogspot.com.jlatexmath.core.MacroInfo;
import maximsblog.blogspot.com.jlatexmath.core.NewCommandMacro;
import maximsblog.blogspot.com.jlatexmath.core.ParseException;
import maximsblog.blogspot.com.jlatexmath.core.TeXParser;

public class MainActivity extends AppCompatActivity {

    private LatexView mLatexView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MacroInfo.Commands.put("fillin", new MacroInfo(2) {
            @Override
            public Object invoke(TeXParser tp, String[] args) throws ParseException {
                //return custom atom
                return new FillInAtom(args[1], args[2]);
            }
        });

        findViewById(R.id.latex_keyboard_1).setOnClickListener(mClickListener);
        findViewById(R.id.latex_keyboard_2).setOnClickListener(mClickListener);
        findViewById(R.id.latex_keyboard_3).setOnClickListener(mClickListener);
        findViewById(R.id.latex_keyboard_4).setOnClickListener(mClickListener);
        findViewById(R.id.latex_keyboard_5).setOnClickListener(mClickListener);
        findViewById(R.id.latex_keyboard_6).setOnClickListener(mClickListener);
        findViewById(R.id.latex_keyboard_7).setOnClickListener(mClickListener);
        findViewById(R.id.latex_keyboard_8).setOnClickListener(mClickListener);
        findViewById(R.id.latex_keyboard_9).setOnClickListener(mClickListener);
        findViewById(R.id.latex_keyboard_star).setOnClickListener(mClickListener);
        findViewById(R.id.latex_keyboard_del).setOnClickListener(mClickListener);
        findViewById(R.id.latex_keyboard_w).setOnClickListener(mClickListener);

        this.mLatexView = (LatexView) findViewById(R.id.latexview);
        mLatexView.setFormula(ExampleFormula.mExample8);

    }

    private View.OnClickListener mClickListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            if (v != null && v instanceof TextView) {
                TextView textView = (TextView) v;
                FillInAtom.FillInBox fillInBox = mLatexView.getFocusFillIn();
                if (fillInBox == null)
                    return;

                String latex = mLatexView.getLatex();
                String index = fillInBox.getIndex();
                String currentText = fillInBox.getText();

                String text = textView.getText().toString();
                if ("删除".equals(text)) {
                    if (TextUtils.isEmpty(currentText))
                        return;

                    mLatexView.setFormula(latex.replace("fillin{" + index +"}{" + currentText + "}",
                            "fillin{" + index +"}{" + currentText.substring(0, currentText.length() -1) + "}"));
//                    fillInBox.setText(currentText.substring(0, currentText.length() -1));
                } else {
//                    fillInBox.setText(currentText + text);
                    mLatexView.setFormula(latex.replace("fillin{" + index +"}{" + currentText + "}",
                            "fillin{" + index +"}{" + currentText + text + "}"));
                }
                mLatexView.relayout();
                mLatexView.postInvalidate();
            }
        }
    };
}
