package cn.heroes.yellow.intercepter;

import cn.heroes.yellow.entity.Info;

public abstract class NTDIntercepter<F extends Info> implements TDIntercepter<F> {
	
	/**
	 * Current handling sheet, invoke once before starting to parse every sheet.
	 * 
	 * @param index 1-based
	 * @param name
	 */
	public abstract void sheet(int index, String name);
	
}
