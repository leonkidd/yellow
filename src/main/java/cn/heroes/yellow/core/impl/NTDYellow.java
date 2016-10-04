package cn.heroes.yellow.core.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import cn.heroes.yellow.core.Yellow;
import cn.heroes.yellow.entity.TDRow;
import cn.heroes.yellow.entity.TDSheet;
import cn.heroes.yellow.exception.ParsingException;
import cn.heroes.yellow.filler.Filler;
import cn.heroes.yellow.filler.SourceFiller;
import cn.heroes.yellow.filler.TDFiller;
import cn.heroes.yellow.intercepter.NTDIntercepter;
import cn.heroes.yellow.parser.NTDParser;

/**
 * 
 * 多页式二维表的Yellow实现
 * 
 * @author Leon Kidd
 * @version 1.00, 2016-10-4
 */
public class NTDYellow extends Yellow {

	/** 解析器对象 */
	private NTDParser<?> p;
	/** 拦截器对象 */
	private NTDIntercepter i;
	/** 填充器对象 */
	private Filler<?> f;

	public NTDYellow(NTDParser<?> parser, NTDIntercepter intercepter, Filler<?> filler) {
		super(parser, intercepter, filler);
		this.p = parser;
		this.i = intercepter;
		this.f = filler;
	}

	@Override
	public void yellow(InputStream is, Object info) {
		try {
			// 调用解析器去解析InputStream
			Object source = p.parse(is);

			// 是否已真正开始的标识
			boolean isBegin = false;

			TDRow row = null;
			TDSheet sheet = null;

			i.info(info);

			// 迭代sheet and row
			for (int sheetIndex = 1; (sheet = p.sheet()) != null; sheetIndex++) {
				if (i.sheet(sheetIndex, sheet.getName())) {
					while ((row = p.next()) != null) {

						// 是否已真正开始
						if (!isBegin) {
							// 由拦截器来确认是否要真正开始
							if (i.begin(row)) {
								// 真正开始后不再判断
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
				}
			}

			// 分析结束, 获取需要填充的数据
			// FillObject<List<Object[]>> fo = i.over();
			OutputStream os = i.outputStream();
			List<Object[]> data = i.data();

			if (f != null && os != null && data != null) {
				if (f instanceof SourceFiller && source != null) {
					SourceFiller sf = (SourceFiller) f;
					sf.fill(source, os);
				} else if (f instanceof TDFiller) {
					TDFiller tf = (TDFiller) f;
					tf.fill(data, os);
				} else {
					// TODO !!!
				}
			}
		} catch (ParsingException e) {
			throw new ParsingException("分析文件[" + (info instanceof File ? ((File) info).getName() : info) + "]出错", e);
		}
	}

	@Override
	public void yellow(File file) {
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(file);

			yellow(fis, file);
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
