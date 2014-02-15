package demo;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import cn.heroes.yellow.entity.FillObject;
import cn.heroes.yellow.entity.Info;
import cn.heroes.yellow.entity.TDRow;
import cn.heroes.yellow.intercepter.TDIntercepter;

public class OpenScanIntercepter implements TDIntercepter {

	private List<Object[]> data = new ArrayList<Object[]>();
	private OutputStream os;
	public StringBuffer log = new StringBuffer();

	public OpenScanIntercepter(OutputStream os) {
		this.os = os;
	}

	@Override
	public void inputInfo(Info info) {
		// do nothing..
	}

	@Override
	public FillObject<List<Object[]>> over() {
		FillObject<List<Object[]>> fo = new FillObject<List<Object[]>>();
		fo.setData(data);
		fo.setOutputStream(os);
		return fo;
	}

	/** 身份证档案类型 */
	private int sno = 1;

	@Override
	public void row(TDRow row) {
		String[] os = new String[5];

		// Get data from row
		String name = row.getString(2);
		String accno = row.getString(3);
		String certno = row.getString(4);

		// Query data
		String[] ss = new String[5]; // queryByAccno(accno);

		// picture no.
		os[0] = sno + ".JPG";
		// doc type
		os[1] = "0201010000";
		// accno
		os[2] = accno;
		// certype + certno
		os[3] = ss[2] + ss[3];
		// inner no.
		os[4] = ss[0];

		data.add(os);

		sno++;
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
	public void init() {

	}

	@Override
	public void destroy() {

	}

}
