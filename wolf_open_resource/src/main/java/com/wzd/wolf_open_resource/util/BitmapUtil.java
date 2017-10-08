package com.wzd.wolf_open_resource.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PaintFlagsDrawFilter;
import android.graphics.Path;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Region.Op;
import android.media.ExifInterface;

import com.wzd.wolf_open_resource.data.GlobalData;

public class BitmapUtil extends CommonBitmapUtil {
	/** 发送图片的最大大小 */
	public static final int MAX_IMAGE_SIZE = 10 * 1024 * 1024;
	/** 最大高度 自定义表情显示 */
	public static final int MAX_IMAGE_HEIGHT_DIP = 120;
	/** 最大宽度 */
	public static final int MAX_IMAGE_WIDTH_DIP = 120;
	/** 最大高度 会话窗口的图片小图显示 */
	public static final int MAX_IMAGE_HEIGHT_DIP_200 = 200;
	/** 最大宽度 */
	public static final int MAX_IMAGE_WIDTH_DIP_200 = 200;
	/** 头像的缩放 */
	private static final int HEAD_IMAGE_HALFWIDTH = 58;
	/** 圆形头像的缩放 */
	private static final int CIRCLE_IMAGE_HALFWIDTH = 57;
	/** 圆形头像背景的缩放 */
	public static final int CIRCLE_IMAGE_BG_HALFWIDTH = 180;
	/** 图片压缩至960像素以内 */
	public static final int COMPRESS_IMAGE_HEIGHT_PX = 960;
	public static final int COMPRESS_IMAGE_WIDTH_PX = 960;

	/**
	 * 获取的圆形头像 缩小至56*56
	 */
	public static Bitmap getCircleHeadPic(Bitmap bitmap) {
		PaintFlagsDrawFilter mPaintFlagsDrawFilter = new PaintFlagsDrawFilter(
				0, Paint.ANTI_ALIAS_FLAG | Paint.FILTER_BITMAP_FLAG);
		// 图片
		int width = bitmap.getWidth();
		int height = bitmap.getHeight();
		int dip3px = AndroidUtil.dip2px(GlobalData.globalContext, 3);
		int dip2px = AndroidUtil.dip2px(GlobalData.globalContext, 2);
		// 缩放图片
		Matrix m = new Matrix();
		float sx = (float) 2 * CIRCLE_IMAGE_HALFWIDTH / width;
		float sy = (float) 2 * CIRCLE_IMAGE_HALFWIDTH / height;
		m.setScale(sx, sy);
		// 重新构造一个60*60的图片
		bitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(),
				bitmap.getHeight(), m, false);
		// 图片
		width = bitmap.getWidth();
		height = bitmap.getHeight();
		Bitmap output = Bitmap.createBitmap(width, height, Config.ARGB_8888);
		Canvas canvas = new Canvas(output);
		final int transparentColor = 0x00000000;
		final int whiteColor = 0xffffffff;
		final int color = 0xffefeff4;
		final Paint paint = new Paint();
		paint.setAntiAlias(true);
		paint.setFilterBitmap(true);
		paint.setColor(whiteColor);
		Path path = new Path();
		path.addCircle(width / 2.0f, (float) (height / 2.0),
				(float) (Math.min(width / 2.0f, height / 2.0f)),
				Path.Direction.CCW);
		path.close();
		canvas.drawCircle(width / 2.0f, height / 2.0f,
				Math.min(width / 2.0f, height / 2.0f) - dip3px, paint);
		final Rect rect = new Rect(dip2px, dip2px, width - dip2px, width
				- dip2px);
		final RectF rectF = new RectF(rect);
		paint.setAntiAlias(true);
		canvas.drawARGB(0, 0, 0, 0);
		paint.setColor(color);
		canvas.drawRoundRect(rectF, width / 2 - dip2px, width / 2 - dip2px,
				paint);
		paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
		canvas.drawBitmap(bitmap, rect, rect, paint);
		int saveCount = canvas.getSaveCount();
		canvas.save();
		canvas.setDrawFilter(mPaintFlagsDrawFilter);
		canvas.clipPath(path, Op.REPLACE);
		canvas.setDrawFilter(mPaintFlagsDrawFilter);
		canvas.drawColor(transparentColor);
		canvas.restoreToCount(saveCount);
		return output;
	}

	/**
	 * 获取当前矩形形头像白色背景 缩小至58*58
	 */
	public static Bitmap getRectHeadPic(Bitmap bitmap) {
		// 缩放图片
		Matrix m = new Matrix();
		float sx = (float) 2 * HEAD_IMAGE_HALFWIDTH / bitmap.getWidth();
		float sy = (float) 2 * HEAD_IMAGE_HALFWIDTH / bitmap.getHeight();
		m.setScale(sx, sy);
		// 重新构造一个60*60的图片
		bitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(),
				bitmap.getHeight(), m, false);
		return bitmap;
	}

	/**
	 * 如果是静态图片，则进行压缩处理, 并旋转 传入前保证file不为空
	 * 
	 * @param rotate
	 *            TODO
	 */
	public static Bitmap CompressAndSaveImg(File file) throws IOException {
		int degree = 0;
		ExifInterface exifInterface = new ExifInterface(file.getAbsolutePath());
		int orc = exifInterface.getAttributeInt(ExifInterface.TAG_ORIENTATION,
				-1);
		// 然后旋转
		if (orc == ExifInterface.ORIENTATION_ROTATE_90) {
			degree = 90;
		} else if (orc == ExifInterface.ORIENTATION_ROTATE_180) {
			degree = 180;
		} else if (orc == ExifInterface.ORIENTATION_ROTATE_270) {
			degree = 270;
		}
		/** 用于压缩的原图Image */
		Bitmap bitmapSourceImg;
		if (degree == 0) {
			bitmapSourceImg = converBitmap(file, COMPRESS_IMAGE_HEIGHT_PX,
					COMPRESS_IMAGE_WIDTH_PX);
		} else {
			bitmapSourceImg = converBitmap(file, COMPRESS_IMAGE_HEIGHT_PX / 2,
					COMPRESS_IMAGE_WIDTH_PX / 2);
			bitmapSourceImg = rotate(file.getAbsolutePath(), bitmapSourceImg,
					degree, COMPRESS_IMAGE_HEIGHT_PX / 2,
					COMPRESS_IMAGE_WIDTH_PX / 2);
		}
		// 存储临时文件
		return bitmapSourceImg;
	}

	/**
	 * 下载URL文件并转化成PNG图片
	 * 
	 * @param url
	 * @param localPath
	 */
	public static boolean downloadHttpImageToPng(byte[] imageArray,
			String filename) {
		boolean result = false;
		Bitmap bm = null;
		bm = BitmapFactory.decodeByteArray(imageArray, 0, imageArray.length);
		String picPath = FileUtil.getPicPath(filename);
		if (bm != null) {
			File filetemp = new File(picPath);
			// 存储临时文件
			FileOutputStream out = null;
			try {
				out = new FileOutputStream(filetemp);
				bm.compress(Bitmap.CompressFormat.PNG, 100, out);
				result = true;
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} finally {
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			bm.recycle();
		}
		return result;
	}

	/**
	 * 根据压缩后往缓存中加入图片,用于相册
	 * 
	 * @param height
	 *            最大像素高
	 * @param width
	 *            最大像素宽
	 * @return
	 */
	public static Bitmap converThumbnailBitmap(File file, String key) {
		// 设置inJustDecodeBounds为true,这样decode的时候只计算高和宽，而不加载真正的图片，
		// 宽高具体在opts里。
		// 使用decodeFile方法得到图片的宽和高
		// 计算缩放比例，如果比最大高宽小则设为1
		BitmapFactory.Options opts = new BitmapFactory.Options();
		opts.inJustDecodeBounds = true;
		Bitmap bm = BitmapUtil.decodeFileNoFromCache(key,
				file.getAbsolutePath(), opts, CIRCLE_IMAGE_BG_HALFWIDTH,
				CIRCLE_IMAGE_BG_HALFWIDTH);
		int sampleHeight = opts.outHeight / CIRCLE_IMAGE_BG_HALFWIDTH;
		int sampleWidth = opts.outWidth / CIRCLE_IMAGE_BG_HALFWIDTH;
		int sampleSize = sampleHeight > sampleWidth ? sampleHeight
				: sampleWidth;
		if (sampleSize < 1) {
			sampleSize = 1;
		}
		// 设置图片属性
		opts.inSampleSize = sampleSize;
		opts.inJustDecodeBounds = false;
		// 未知下面两个属性的含义
		opts.inInputShareable = true;
		opts.inPurgeable = true;
		bm = BitmapUtil.decodeFileNoFromCache(key, file.getAbsolutePath(),
				opts, CIRCLE_IMAGE_BG_HALFWIDTH, CIRCLE_IMAGE_BG_HALFWIDTH);
		int degree = 0;
		try {
			ExifInterface exifInterface = new ExifInterface(
					file.getAbsolutePath());
			int orc = exifInterface.getAttributeInt(
					ExifInterface.TAG_ORIENTATION, -1);
			// 然后旋转
			if (orc == ExifInterface.ORIENTATION_ROTATE_90) {
				degree = 90;
			} else if (orc == ExifInterface.ORIENTATION_ROTATE_180) {
				degree = 180;
			} else if (orc == ExifInterface.ORIENTATION_ROTATE_270) {
				degree = 270;
			}
			/** 用于旋转图片 */
			if (degree != 0) {
				bm = BitmapUtil.rotate(file.getAbsolutePath(), bm, degree,
						CIRCLE_IMAGE_BG_HALFWIDTH, CIRCLE_IMAGE_BG_HALFWIDTH);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bm;
	}

	/**
	 * 生成bitmap,不从缓存找 用于相册
	 * 
	 * @param paramString
	 * @param paramOptions
	 * @return
	 */
	public static Bitmap decodeFileNoFromCache(String key, String paramString,
			BitmapFactory.Options paramOptions, int limitW, int limitH) {
		String pathStr = key + ","
				+ (paramOptions == null ? "_1" : "_" + limitW + "_" + limitH);
		Bitmap localBitmap = getFromCache(pathStr);
		if (localBitmap != null)
			return localBitmap;
		try {
			localBitmap = BitmapFactory.decodeFile(paramString, paramOptions);
			if (paramOptions == null || !paramOptions.inJustDecodeBounds)
				addBitmap(key, localBitmap);
			return localBitmap;
		} catch (OutOfMemoryError localOutOfMemoryError1) {
			System.gc();
			Thread.yield();
			try {
				localBitmap = BitmapFactory.decodeFile(paramString,
						paramOptions);
				if (paramOptions == null || !paramOptions.inJustDecodeBounds)
					addBitmap(key, localBitmap);
			} catch (OutOfMemoryError localOutOfMemoryError2) {
				clearMap();
			}
			return localBitmap;
		}
	}

	/**
	 * 读取SD卡图片
	 * 
	 * @param path
	 * @return
	 */
	public static Bitmap getSdCardImage(String path) {
		File mFile = new File(path);
		if (mFile.exists()) {
			Bitmap bitmap = BitmapFactory.decodeFile(path);
			return bitmap;
		}
		return null;
	}

	/**
	 * 压缩图片到300K
	 * 
	 * @param bitmapSourceImg
	 * @return
	 */
	public static ByteArrayOutputStream compressPic(Bitmap bitmapSourceImg) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		bitmapSourceImg.compress(Bitmap.CompressFormat.JPEG, 100, baos);
		int options = 100;
		while (baos.toByteArray().length / 1024 > 290) { // 循环判断如果压缩后图片是否大于100kb,大于继续压缩
			baos.reset();// 重置baos即清空baos
			options -= 10;// 每次都减少10
			bitmapSourceImg.compress(Bitmap.CompressFormat.JPEG, options, baos);// 这里压缩options%，把压缩后的数据存放到baos中
		}
		return baos;
	}
}
