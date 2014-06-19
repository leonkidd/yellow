package cn.heroes.yellow.filler.impl;

import java.io.File;
import java.io.InputStream;

/**
 * Excel first sheet Filler with template. The data will be inserted after the
 * row with the <code>$</code> mark in the first cell(cell no. is 1). If there
 * is no <code>$</code> mark, the data will be filled after the end of sheet.
 * 
 * @author Leon Kidd
 * @version 1.00, 2014-2-8
 */
public class Sheet0Filler extends SheetNFiller {

	/**
	 * Create a blank Excel file with default sheet name.
	 */
	public Sheet0Filler() {
		super();
	}

	/**
	 * Create a blank Excel file with the specified sheet name.
	 * 
	 * @param sheetName
	 */
	public Sheet0Filler(String sheetName) {
		super(sheetName);
	}

	/**
	 * Choose a template to fill data.
	 * 
	 * @param template
	 *            the Excel template file. <code>NULL</code> if want a blank
	 *            Excel file.
	 */
	public Sheet0Filler(File template) {
		super(template, 0);
	}

	/**
	 * Choose a template to fill data.
	 * 
	 * @param template
	 *            the Excel template file. <code>NULL</code> if want a blank
	 *            Excel file.
	 */
	public Sheet0Filler(InputStream template) {
		super(template, 0);
	}

}
