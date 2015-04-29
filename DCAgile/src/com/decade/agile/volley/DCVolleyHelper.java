package com.decade.agile.volley;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.widget.ImageView;

import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageLoader.ImageContainer;
import com.android.volley.toolbox.ImageLoader.ImageListener;
import com.android.volley.toolbox.Volley;
import com.decade.agile.kit.DCImageUtils;

/**
 * @description: Volley辅助类
 * @author: Decade
 * @date: 2013-11-4
 * 
 */
public class DCVolleyHelper {
	private RequestQueue mRequestQueue;
	private ImageLoader mImageLoader;
	private static DCVolleyHelper mInstance = null;

	private DCVolleyHelper(Context context) {
		this(context, new DCVolleyBitmapCache());
	}

	private DCVolleyHelper(Context context, DCVolleyBitmapCache bitmapCache) {
		mRequestQueue = Volley.newRequestQueue(context);
		mImageLoader = new ImageLoader(mRequestQueue, bitmapCache);
	}

	public static DCVolleyHelper getInstance(Context context) {
		if (mInstance == null) {
			mInstance = new DCVolleyHelper(context);
		}
		return mInstance;
	}

	public static DCVolleyHelper getInstance(Context context,
			DCVolleyBitmapCache bitmapCache) {
		if (mInstance == null) {
			mInstance = new DCVolleyHelper(context, bitmapCache);
		}
		return mInstance;
	}

	public static ImageListener getImageListener(final ImageView view,
			final int defaultImageResId, final int errorImageResId,
			final boolean isRound, final int pixels) {
		return new ImageListener() {
			@Override
			public void onErrorResponse(VolleyError error) {
				if (errorImageResId != 0) {
					view.setImageResource(errorImageResId);
				}
			}

			@Override
			public void onResponse(ImageContainer response, boolean isImmediate) {
				if (response.getBitmap() != null) {
					if (isRound) {
						if (pixels == -1) {
							view.setImageBitmap(DCImageUtils
									.toRoundBitmap(response.getBitmap()));
						} else {
							view.setImageBitmap(DCImageUtils.toRoundCorner(
									response.getBitmap(), pixels));
						}
					} else {
						view.setImageBitmap(response.getBitmap());
					}
				} else if (defaultImageResId != 0) {
					view.setImageResource(defaultImageResId);
				}
			}
		};
	}

	public static ImageListener getImageBackgroundListener(
			final ImageView view, final int defaultImageResId,
			final int errorImageResId, final boolean isRound, final int pixels) {
		return new ImageListener() {
			@Override
			public void onErrorResponse(VolleyError error) {
				if (errorImageResId != 0) {
					view.setBackgroundResource(errorImageResId);
				}
			}

			@Override
			public void onResponse(ImageContainer response, boolean isImmediate) {
				if (response.getBitmap() != null) {
					if (isRound) {
						if (pixels == -1) {
							view.setBackgroundDrawable(new BitmapDrawable(
									DCImageUtils.toRoundBitmap(response
											.getBitmap())));
						} else {
							view.setBackgroundDrawable(new BitmapDrawable(
									DCImageUtils.toRoundCorner(
											response.getBitmap(), pixels)));
						}
					} else {
						view.setBackgroundDrawable(new BitmapDrawable(response
								.getBitmap()));
					}
				} else if (defaultImageResId != 0) {
					view.setBackgroundResource(defaultImageResId);
				}
			}
		};
	}

	public RequestQueue getmRequestQueue() {
		return mRequestQueue;
	}

	public ImageLoader getmImageLoader() {
		return mImageLoader;
	}

	public void release() {
		this.mImageLoader = null;
		this.mRequestQueue = null;
		mInstance = null;
	}
}
