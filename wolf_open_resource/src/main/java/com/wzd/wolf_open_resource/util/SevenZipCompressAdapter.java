package com.wzd.wolf_open_resource.util;

import java.io.IOException;

import SevenZip.SevenZipHelper;

/**
 * 压缩写入文件适配器，在写入压缩文件时被调用
 * 
 * @author tian
 * 
 */
public class SevenZipCompressAdapter implements ICompress {
	
	@Override
	public byte[] compress(byte[] bytes) throws IOException {
		return SevenZipHelper.Compress(bytes);
	}

}
