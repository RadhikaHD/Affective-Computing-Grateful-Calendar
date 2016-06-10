
package com.natasha.grateful.util;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;

public final class DrawUtils {
	// Make dest a resized copy of src. This maintains the aspect ratio. and cuts
	// along edges of the image if src and dest have a different aspect ratio.
	public static void convertSizeClip(Bitmap src, Bitmap dest) {
		Canvas canvas = new Canvas(dest);
		RectF srcRect = new RectF(0, 0, src.getWidth(), src.getHeight());
		RectF destRect = new RectF(0, 0, dest.getWidth(), dest.getHeight());

		// Because the current SDK does not directly support the "dest fits
		// inside src" mode, we calculate the reverse matrix and invert to
		// get what we want.
		Matrix mDestSrc = new Matrix();
		mDestSrc.setRectToRect(destRect, srcRect, Matrix.ScaleToFit.CENTER);
		Matrix mSrcDest = new Matrix();
		mDestSrc.invert(mSrcDest);

		canvas.drawBitmap(src, mSrcDest, new Paint(Paint.DITHER_FLAG));
	}

	public static void convertSizeFill(Bitmap src, Bitmap dest) {
		Canvas canvas = new Canvas(dest);
		RectF srcRect = new RectF(0, 0, src.getWidth(), src.getHeight());
		RectF destRect = new RectF(0, 0, dest.getWidth(), dest.getHeight());

		Matrix m = new Matrix();
		m.setRectToRect(srcRect, destRect, Matrix.ScaleToFit.CENTER);

		canvas.drawBitmap(src, m, new Paint(Paint.DITHER_FLAG));
	}

	public static int brightness(int color) {
		// Because this should be fast, we cheat: simply return the green channel.
		return (color >> 16) & 0xff;
	}
}
