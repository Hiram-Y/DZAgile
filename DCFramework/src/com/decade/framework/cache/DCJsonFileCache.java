package com.decade.framework.cache;

import java.io.File;

import com.decade.framework.DCApplication;
import com.decade.framework.kit.DCSDCardOperate;

/**
 * @description: json缓存处理类
 * @author: Decade
 * @date: 2013-9-16
 * @preserve protected
 */
public class DCJsonFileCache extends DCBaseJsonFileCache {
	private static DCJsonFileCache jsonFileCache = null;
	private static File jsonCacheDir = null;
	private static long mTimeDiff = 7 * 24 * 60 * 60 * 1000; // 七天

	public DCJsonFileCache() {
		// Find the dir to save cached images
		if (DCSDCardOperate.isSDCardMounted()) {
			jsonCacheDir = new File(DCSDCardOperate.getFilePath(),
					"Android/Cache/"
							+ DCApplication.getAppContext().getPackageName()
							+ "/Json_Cache/");
			DCSDCardOperate.removeExpiredCache(jsonCacheDir, mTimeDiff);
		} else {
			jsonCacheDir = DCApplication.getAppContext().getCacheDir();
		}
		if (jsonCacheDir != null) {
			if (!jsonCacheDir.exists())
				jsonCacheDir.mkdirs();
		}
	}

	public static DCJsonFileCache getJsonFileCache() {
		if (jsonFileCache == null) {
			jsonFileCache = new DCJsonFileCache();
		}
		return jsonFileCache;
	}

	public File getFile(String url) {
		File f = getFile(jsonCacheDir, url);
		return f;
	}

	public void clear() {
		clear(jsonCacheDir);
	}

	public void saveCache(String response, String url) {
		saveCache(jsonCacheDir, response, url);
	}

	public boolean isExpiredCache(String url, long mTimeDiff) {
		return isExpiredCache(jsonCacheDir, url, mTimeDiff);
	}

	public String selectCache(String url) {
		return selectCache(jsonCacheDir, url);
	}

	public void setFileExpired(String url) {
		setFileExpired(jsonCacheDir, url);
	}

	/**
	 * 设置缓存自动清理间隔时间
	 * 
	 * @param time
	 */
	public static void setTimeDiff(int time) {
		mTimeDiff = time;
	}

	/**
	 * 设置json数据缓存目录
	 * 
	 * @param dir
	 */
	public static void setJsonCacheDir(File dir) {
		jsonCacheDir = dir;
	}
}