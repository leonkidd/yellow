package cn.heroes.yellow.filler.impl;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import au.com.bytecode.opencsv.CSVWriter;
import cn.heroes.yellow.filler.TDFiller;

/**
 * TDFiller's implementation about <i>DEL, CSV</i> file.
 * 
 * @author Leon Kidd
 * @version 1.00, 2014-6-19
 */
public class DelFiller implements TDFiller {

	private static final Logger logger = LoggerFactory
			.getLogger(DelFiller.class);

	private String charset = "GBK";

	public DelFiller() {
	}

	public DelFiller(String charset) {
		this.charset = charset;
	}

	@Override
	public void init() {

	}

	@Override
	public void destroy() {

	}

	@Override
	public void fill(List<Object[]> data, OutputStream os) {
		try {
			OutputStreamWriter osw = new OutputStreamWriter(os, charset);
			CSVWriter writer = new CSVWriter(osw);
			for (Object[] obs : data) {
				int length = obs.length;
				String[] ss = new String[length];
				System.arraycopy(obs, 0, ss, 0, length);
				writer.writeNext(ss);
			}
			writer.flush();
		} catch (UnsupportedEncodingException e) {
			logger.error("The charset name {} is unsupported.", charset);
		} catch (IOException e) {
			logger.error("", e);
		} finally {
			// Filler won't care about the I/O close
		}
	}

}
