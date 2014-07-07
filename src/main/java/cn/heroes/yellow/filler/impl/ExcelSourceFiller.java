package cn.heroes.yellow.filler.impl;

import java.io.IOException;
import java.io.OutputStream;

import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.heroes.yellow.filler.SourceFiller;

/**
 * Fill the modified excel source into a new excel file.
 * 
 * @see {@link cn.heroes.yellow.entity.TDRow#setValue(int, Object)}
 * @author Leon Kidd
 * @version 1.00, 2014-7-7
 */
public class ExcelSourceFiller implements SourceFiller<Workbook> {

	private static final Logger logger = LoggerFactory
			.getLogger(ExcelSourceFiller.class);

	@Override
	public void init() {

	}

	@Override
	public void destroy() {

	}

	@Override
	public void fill(Workbook data, OutputStream os) {
		try {
			data.write(os);
		} catch (IOException e) {
			logger.error("", e);
		}
	}

}
