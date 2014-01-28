package cn.yhhh.fairy.yellow.parser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.heroes.jkit.utils.TextUtils;
import cn.yhhh.fairy.yellow.entity.DelRow;
import cn.yhhh.fairy.yellow.entity.Row;

/**
 * DEL格式文件分析器 DEL格式: 逗号隔开，引号在字段两段，金额字段没有引号，而是以+00000000001300.00格式.很多字段头尾有空格 <br/>
 * 读入处理单线程,已略过无效行.
 * 
 * @author Leon Kidd
 * @version 1.00, 2011-9-3
 * @since 1.0
 */
public class SimpleDelParser implements Parser {
	private static final Logger logger = LoggerFactory
			.getLogger(SimpleDelParser.class);

	/** 当前有效行数,有1行则为1 */
	private long rows = 0L;
	// TODO 列头验证
	/** 正常的列数,以列头为准 */
	// private int column;

	/** Buffer IO Reader */
	private BufferedReader br = null;

	/**
	 * 无论如何要在最后调用一次close方法.
	 */
	@Override
	public void parser(File file) {
		FileInputStream fis = null;
		InputStreamReader isr = null;
		try {
			fis = new FileInputStream(file);
			isr = new InputStreamReader(fis, TextUtils.getCharset(file));
			br = new BufferedReader(isr);
		} catch (Exception e) {
			logger.error("Del parse error.", e);
			try {
				if (isr != null)
					isr.close();
			} catch (IOException e1) {
				logger.error("", e1);
			}
			try {
				if (fis != null)
					fis.close();
			} catch (IOException e2) {
				logger.error("", e2);
			}

		}
	}

	@Override
	public Row next() {
		DelRow row = null;
		String temp;
		try {
			if ((temp = br.readLine()) != null) {
				if ("".equals(temp.trim())) {
					// 空行，直接跳过，到下一行
					logger.warn("Empty row show up after {} row.", rows);
					return next();
				}

				// 有效行+1
				rows++;

				String[] item = temp.split(",");
				Object[] values = new Object[item.length];
				String str;
				for (int i = 0; i < item.length; i++) {
					str = item[i].trim();
					if (str.startsWith("\"") && str.endsWith("\"")
							&& str.length() > 2) {
						// 去引号,去空格
						values[i] = str.substring(1, str.length() - 1).trim();
					} else {
						// 数值
						try {
							values[i] = Double.parseDouble(str);
						} catch (NumberFormatException e) {
							values[i] = str;
							logger.error(
									"Format error when attempt to format {} to double in {} row.",
									str, rows);
						}
					}
				}

				row = new DelRow();
				row.setRowNum(rows);
				row.setValues(values);
			}
		} catch (IOException e) {
			logger.error("", e);
		}
		return row;
	}

	@Override
	public void init() {
	}

	@Override
	public void destroy() {

	}

	@Override
	public void parser(File file, Object id) {
		parser(file);
	}

}
