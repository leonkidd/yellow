package cn.heroes.yellow.entity.impl;

import java.io.File;

import cn.heroes.yellow.entity.Info;

/**
 * 拦截文件的一些标志信息
 * 
 * @author Leon Kidd
 * @version 1.00, 2014-2-2
 */
public class FileInfo implements Info<File> {
	private File file;

	public FileInfo(File file) {
		this.file = file;
	}

	@Override
	public String id() {
		return file.getName();
	}

	@Override
	public File body() {
		return file;
	}
}
