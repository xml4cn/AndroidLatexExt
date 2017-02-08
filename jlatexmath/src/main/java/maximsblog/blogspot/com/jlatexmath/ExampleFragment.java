//package maximsblog.blogspot.com.jlatexmath;
//
//import java.io.File;
//import java.io.FileOutputStream;
//import java.util.Random;
//
//import maximsblog.blogspot.com.jlatexmath.core.Insets;
//import maximsblog.blogspot.com.jlatexmath.core.TeXConstants;
//import maximsblog.blogspot.com.jlatexmath.core.TeXFormula;
//import maximsblog.blogspot.com.jlatexmath.core.TeXIcon;
//import maximsblog.blogspot.com.jlatexmath.core.AjLatexMath;
//import android.graphics.Bitmap;
//import android.graphics.Bitmap.Config;
//import android.graphics.BitmapFactory;
//import android.graphics.Canvas;
//import android.graphics.Color;
//import android.graphics.Matrix;
//import android.graphics.RectF;
//import android.os.Bundle;
//import android.os.Environment;
//import android.support.v4.app.Fragment;
//import android.util.TypedValue;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.View.OnClickListener;
//import android.view.ViewGroup;
//import android.widget.EditText;
//import android.widget.ImageView;
//import android.widget.LinearLayout;
//import android.widget.RelativeLayout;
//import android.widget.Toast;
//
//public class ExampleFragment extends Fragment implements OnClickListener {
//
//	public static android.support.v4.app.Fragment newInstance(String latex,
//			int tag) {
//		ExampleFragment fragment = new ExampleFragment();
//		fragment.setTag(tag);
//		fragment.setFormula(latex);
//		return fragment;
//	}
//
//	private ImageView mImageView;
//	private String mLatex;
//	private float mTextSize = 16;
//	private int mTag;
//	private EditText mSizeText;
//
//	private void setFormula(String latex) {
//		mLatex = latex;
//	}
//
//	private void setTag(int tag) {
//		mTag = tag;
//	}
//
//	@Override
//	public void onSaveInstanceState(Bundle outState) {
//		outState.putString("latex", mLatex);
//		outState.putFloat("textsize", mTextSize);
//		outState.putInt("tag", mTag);
//		super.onSaveInstanceState(outState);
//	};
//
//	@Override
//	public void onCreate(Bundle savedInstanceState) {
//		super.onCreate(savedInstanceState);
//		if (savedInstanceState != null) {
//			mLatex = savedInstanceState.getString("latex");
//			mTextSize = savedInstanceState.getFloat("textsize");
//			mTag = savedInstanceState.getInt("tag");
//		}
//	};
//
//	@Override
//	public View onCreateView(LayoutInflater inflater, ViewGroup container,
//			Bundle savedInstanceState) {
//
//		LinearLayout layout = (LinearLayout) inflater.inflate(
//				R.layout.fragment_example, container, false);
//		mImageView = (ImageView) layout.findViewById(R.id.logo);
//		mSizeText = (EditText) layout.findViewById(R.id.size);
//		layout.findViewById(R.id.set_textsize).setOnClickListener(this);
//		setformula();
//
//		return layout;
//	}
//
//	private void setformula() {
//		int w = getResources().getDisplayMetrics().widthPixels;
//		int h = getResources().getDisplayMetrics().heightPixels;
//		TeXFormula formula = new TeXFormula(mLatex);
//		TeXIcon icon = formula.new TeXIconBuilder()
//				.setStyle(TeXConstants.STYLE_DISPLAY)
//				.setSize(mTextSize)
//				.setWidth(TeXConstants.UNIT_PIXEL, w, TeXConstants.ALIGN_LEFT)
//				.setIsMaxWidth(true)
//				.setInterLineSpacing(TeXConstants.UNIT_PIXEL,
//						AjLatexMath.getLeading(mTextSize)).build();
//		icon.setInsets(new Insets(5, 5, 5, 5));
//
//		Bitmap image = Bitmap.createBitmap(icon.getIconWidth(), icon.getIconHeight(),
//				Config.ARGB_8888);
//
//		Canvas g2 = new Canvas(image);
//		g2.drawColor(Color.WHITE);
//		icon.paintIcon(g2, 0, 0);
//
//		Bitmap scaleimage = scaleBitmapAndKeepRation(image, h, w);
//
//		mImageView.setImageBitmap(scaleimage);
//	}
//
//	public Bitmap scaleBitmapAndKeepRation(Bitmap targetBmp,
//			int reqHeightInPixels, int reqWidthInPixels) {
//		Bitmap bitmap = Bitmap.createBitmap(reqWidthInPixels,
//				reqHeightInPixels, Config.ARGB_8888);
//		Canvas g = new Canvas(bitmap);
//		g.drawBitmap(targetBmp, 0, 0, null);
//		targetBmp.recycle();
//		return bitmap;
//	}
//
//	@Override
//	public void onDestroy() {
//		super.onDestroy();
//	};
//
//	@Override
//	public void onClick(View v) {
//		if (v.getId() == R.id.set_textsize) {
//			mTextSize = Integer.valueOf(mSizeText.getText().toString());
//			setformula();
//		}
//	}
//}
