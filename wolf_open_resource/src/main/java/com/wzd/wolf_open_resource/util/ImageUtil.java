package com.wzd.wolf_open_resource.util;

import java.io.File;
import java.io.FileNotFoundException;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.wzd.wolf_open_resource.data.GlobalData;
import com.wzd.wolf_open_resource.http.BaseApi;

public class ImageUtil {

	public static void scanPhotos(String path) {
		Log4JUtil.debugInfo("*scan photos: " + path);
		File file = new File(path);
		if (file.exists()) {
			String targetFileName = file.getName();
			Log4JUtil.debugInfo("targetFileName: " + targetFileName);
			try {
				MediaStore.Images.Media.insertImage(
						GlobalData.globalContext.getContentResolver(),
						file.getAbsolutePath(), targetFileName, null);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			GlobalData.globalContext.sendBroadcast(new Intent(
					Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.fromFile(file)));
		} else {
			Log4JUtil.debugInfo("*scan photos file not exist...");
		}
	}

	public static void showImage(Context context, String url,
			ImageView imageView) {
		// Picasso.with(context).load(BaseApi.URL_BASE + url)
		// .placeholder(R.drawable.no_image_show)
		// .error(R.drawable.un_down_load_pic_icon).into(imageView);
		Picasso.with(context).load(BaseApi.URL_BASE + url).into(imageView);
	}

}
