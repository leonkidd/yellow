package demo;

import java.io.File;
import java.util.List;

import cn.heroes.yellow.entity.FillObject;
import cn.heroes.yellow.entity.Info;
import cn.heroes.yellow.entity.TDRow;
import cn.heroes.yellow.intercepter.TDIntercepter;

public class InnerDetailIntercepter implements TDIntercepter<File> {

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

	@Override
	public void row(TDRow row) {
		String accno = row.getString(1);
		System.out.println(accno);
	}

}
