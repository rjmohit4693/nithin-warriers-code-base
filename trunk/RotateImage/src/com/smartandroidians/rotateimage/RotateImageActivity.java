package com.smartandroidians.rotateimage;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.os.Bundle;
import android.view.View;

public class RotateImageActivity extends Activity {
    
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.main);
        DrawImage img = new DrawImage(this);
        img.postInvalidate();
        setContentView(img); 
    }
	
	class DrawImage extends View {
		
		private Bitmap image;
		private float degrees = 0;
		private Matrix matrix;
		
		public DrawImage(Context context) {
			super(context);
			image = BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_launcher);
			matrix = new Matrix();
		}
		
		public void onDraw(Canvas canvas) {
			super.onDraw(canvas);
			canvas.save();
			matrix.setRotate(degrees, image.getWidth()/2, image.getHeight()/2);
//			canvas.rotate(degrees);
			degrees += 5;
			canvas.drawBitmap(image, matrix, null);
			canvas.restore();
			invalidate();
		}
		
	}
}