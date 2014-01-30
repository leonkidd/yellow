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
 * CSV格式文件分析器 CSV格式: 逗号隔开，没有涉及引号<br/>
 * 读入处理单线程,已略过无效行.
 * 
 * @author Leon Kidd
 * @version 1.00, 2011-9-3
 * @since 1.0
 */
public class SimpleCSVParser implements Parser {
	private static final Logger logger = LoggerFactory
			.getLogger(SimpleCSVParser.class);

	/** 当前有效行数,有1行则为1 */
	private long rows = 0L;
	
	/** Buffer IO Reader */
	private BufferedReader br = null;

	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void parser(File file) {
		FileInputStream fis = null;
		InputStreamReader isr = null;
		try {
			fis = new FileInputStream(file);
			isr = new InputStreamReader(fis, TextUtils.getCharset(file));
			br = new BufferedReader(isr);
		} catch (Exception e) {
			logger.error("csv parse error.", e);
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
				for (int i = 0; i < item.length; i++) {
					values[i] = item[i].trim();
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
	public void parser(File file, Object id) {
		parser(file);
	}

}
