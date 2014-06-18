package cn.yhhh.test;

import java.io.File;
import java.util.List;

import org.junit.Test;

import cn.heroes.yellow.core.Yellow;
import cn.heroes.yellow.core.impl.TDYellow;
import cn.heroes.yellow.entity.FillObject;
import cn.heroes.yellow.entity.Info;
import cn.heroes.yellow.entity.TDRow;
import cn.heroes.yellow.intercepter.TDIntercepter;
import cn.heroes.yellow.parser.TDParser;
import cn.heroes.yellow.parser.impl.DelParser;

public class DelParserTest implements TDIntercepter<File> {
	@Test
	public void delparse() {
		TDParser parser = new DelParser();
		DelParserTest i = new DelParserTest();
		Yellow yellow = new TDYellow(parser, i, null);
		yellow.yellow(new File("test/whole.csv"));
		yellow.destroy();
	}

	@Override
	public void row(TDRow row) {
		String accno = row.getString(1);
		int organ = row.getInt(4);
		String chinese = row.getString(5);
		System.out.println(accno + "," + organ + "," + chinese);
	}

	@Override
	public void info(Info<File> info) {

	}

	@Override
	public FillObject<List<Object[]>> over() {
		return null;
	}

	@Override
	public void init() {

	}

	@Override
	public void destroy() {

	}

	@Override
	public boolean begin(TDRow row) {
		return true;
	}

	@Override
	public boolean end(TDRow row) {
		return false;
	}

	@Override
	public boolean ignore(TDRow row) {
		return false;
	}
}
