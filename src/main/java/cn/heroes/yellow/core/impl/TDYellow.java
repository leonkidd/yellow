package cn.heroes.yellow.core.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import cn.heroes.yellow.core.Yellow;
import cn.heroes.yellow.entity.Info;
import cn.heroes.yellow.entity.TDRow;
import cn.heroes.yellow.entity.impl.FileInfo;
import cn.heroes.yellow.exception.ParsingException;
import cn.heroes.yellow.filler.Filler;
import cn.heroes.yellow.intercepter.TDIntercepter;
import cn.heroes.yellow.parser.TDParser;

/**
 * 二维表的Yellow实现
 * 
 * @author Leon Kidd
 * @version 1.00, 2014-2-2
 */
public class TDYellow extends Yellow {

	/** 解析器对象 */
	private TDParser p;
	/** 拦截器对象 */
	private TDIntercepter i;
	/** 填充器对象 */
	private Filler f;

	public TDYellow(TDParser parser, TDIntercepter intercepter, Filler filler) {
		super(parser, intercepter, filler);
		this.p = parser;
		this.i = intercepter;
		this.f = filler;
	}

	@Override
	public void yellow(InputStream is, Info info) {
		FileInfo fi = (FileInfo) info;
		
		// 调用解析器去解析InputStream
		try {
			p.parse(is);
		} catch(ParsingException e) {
			throw new ParsingException("分析文件[" + fi.file.getAbsolutePath() + "]出错", e);
		}

		// 是否已真正开始的标识
		boolean isBegin = false;

		// 迭代row
		TDRow row = null;

		i.info(info);
		while ((row = p.next()) != null) {

			// 是否已真正开始
			if (!isBegin) {
				if (i.begin(row)) {
					// 判断是否真正开始, 真正开始后不再判断
					isBegin = true;
				} else {
					continue;
				}
			}

			// 已开始
			if (i.end(row)) {
				// 结束
				break;
			} else if (i.ignore(row)) {
				// 该行忽略
				continue;
			} else {
				// Business Code
				i.row(row);
			}
		}
		// 分析结束
		i.over();
	}

	@Override
	public void yellow(File file) {
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(file);

			FileInfo info = new FileInfo();
			info.file = file;

			yellow(fis, info);
		} catch (FileNotFoundException e) {
			// Throw exception
			e.printStackTrace();
		} finally {
			if (fis != null) {
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
