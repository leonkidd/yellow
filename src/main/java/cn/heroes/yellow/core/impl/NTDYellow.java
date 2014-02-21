package cn.heroes.yellow.core.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

import cn.heroes.yellow.core.Yellow;
import cn.heroes.yellow.entity.FillObject;
import cn.heroes.yellow.entity.Info;
import cn.heroes.yellow.entity.TDPage;
import cn.heroes.yellow.entity.TDRow;
import cn.heroes.yellow.entity.impl.FileInfo;
import cn.heroes.yellow.filler.Filler;
import cn.heroes.yellow.intercepter.NTDIntercepter;
import cn.heroes.yellow.parser.NTDParser;

/**
 * TODO
 * 
 * @author Leon Kidd
 * @version 1.00, 2014-2-15
 * @since 1.0
 */
public class NTDYellow extends Yellow {

	/** 解析器对象 */
	private NTDParser p;
	/** 拦截器对象 */
	private NTDIntercepter i;
	/** 填充器对象 */
	private Filler f;

	public NTDYellow(NTDParser parser, NTDIntercepter intercepter, Filler filler) {
		super(parser, intercepter, filler);
		this.p = parser;
		this.i = intercepter;
		this.f = filler;
	}

	@Override
	public void yellow(InputStream is, Info info) {
		// TODO <?>
		i.info(info);
		
		Iterator<TDPage> pages = p.parse(is);
		int index = 1;
		while (pages.hasNext()) {
			TDPage page = pages.next();
			
			boolean need = i.sheet(index++, page.getName());
			if(!need) {
				continue;
			}

			// 是否已真正开始的标识
			boolean isBegin = false;

			// 迭代row
			TDRow row = null;

			//i.inputInfo(info);
			while ((row = page.next()) != null) {

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

		// 分析结束, 获取需要填充的数据
		FillObject fo = i.over();
		if (f != null) {
			f.fill(fo.getData(), fo.getOutputStream());
		}
	}

	@Override
	public void yellow(File file) {
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(file);

			yellow(fis, new FileInfo(file));
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
