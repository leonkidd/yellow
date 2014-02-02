package cn.heroes.yellow.entity.impl;

import java.io.File;

import cn.heroes.yellow.entity.Info;

/**
 * 拦截文件的一些标志信息
 * 
 * @author Leon Kidd
 * @version 1.00, 2014-2-2
 */
public class FileInfo extends Info {
	/** 文件 */
	public File file;
	/** 文件所在目录 */
	public File directory;
}
