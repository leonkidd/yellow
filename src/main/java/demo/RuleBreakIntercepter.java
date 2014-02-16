package demo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.builder.HashCodeBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.heroes.yellow.entity.FillObject;
import cn.heroes.yellow.entity.Info;
import cn.heroes.yellow.entity.TDRow;
import cn.heroes.yellow.intercepter.NTDIntercepter;

public class RuleBreakIntercepter extends NTDIntercepter<File> {

	private static final Logger logger = LoggerFactory
			.getLogger(RuleBreakIntercepter.class);

	class Teller {
		String name;
		String organ;
		String happen;

		@Override
		public int hashCode() {
			return new HashCodeBuilder().append(name).append(organ)
					.append(happen).toHashCode();
		}

		@Override
		public boolean equals(Object obj) {
			Teller s = (Teller) obj;
			return name.equals(s.name) && organ.equals(s.organ)
					&& happen.equals(s.happen);
		}
	}

	class Mistake {
		String content;
		float score;
		String comment;
	}

	private Map<Teller, List<Mistake>> map = new HashMap<Teller, List<Mistake>>();

	private boolean begin = false;

	@Override
	public boolean begin(TDRow row) {
		String name = row.getString(2);
		if (begin) {
			return true;
		} else if (name != null && name.contains("姓名")) {
			// 匹配后下一行再开始
			begin = true;
		}
		return false;
	}

	@Override
	public boolean end(TDRow row) {
		// TODO error ?
		//String name = row.getString(2);
		//return name == null || "".equals(name);
		return false;
	}

	@Override
	public boolean ignore(TDRow row) {
		return false;
	}

	@Override
	public void row(TDRow row) {
		Teller t = new Teller();
		t.name = row.getString(2);
		t.organ = row.getString(8);
		t.happen = row.getString(7);

		Mistake m = new Mistake();
		m.content = row.getString(3);
//		String num = row.getString(5);
		m.score = row.getFloat(5);
		m.comment = row.getString(6);
		/*try {
			m.score = Float.parseFloat(num);
		} catch (NumberFormatException e) {
			logger.error(sheetName + "," + num, e);
		}*/

		List<Mistake> value = map.get(t);
		if (value == null) {
			value = new ArrayList<Mistake>();
			map.put(t, value);
		}
		value.add(m);
	}

	//private String sheetName;

	@Override
	public void sheet(int index, String name) {
		begin = false;
		//sheetName = name;
		logger.info("开始分析第{}个Sheet [{}]", index, name);
	}

	@Override
	public FillObject<List<Object[]>> over() {
		FillObject<List<Object[]>> fo = new FillObject<List<Object[]>>();
		List<Object[]> data = new ArrayList<Object[]>();
		Set<Teller> keys = map.keySet();
		Iterator<Teller> iter = keys.iterator();
		while(iter.hasNext()) {
			Teller t = iter.next();
			// list won't be null or empty.
			List<Mistake> mises = map.get(t);
			Object[] os = new Object[mises.size() * 3 + 3];
			
			os[0] = t.organ;
			os[1] = t.happen;
			os[2] = t.name;
			
			int index = 3;
			for(Mistake mis : mises) {
				os[index++] = mis.content;
				os[index++] = mis.score;
				os[index++] = mis.comment;
			}
			data.add(os);
		}
		fo.setData(data);
		fo.setOutputStream(fos);
		return fo;
	}
	
	private FileOutputStream fos = null;

	@Override
	public void init() {
		try {
			fos = new FileOutputStream("$.xls");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void destroy() {
		if(fos != null) {
			try {
				fos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void info(Info<File> info) {
		File file = info.body();
		logger.info("开始分析文件[{}]", file.getAbsolutePath());
	}

}
