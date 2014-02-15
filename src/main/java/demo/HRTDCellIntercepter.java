package demo;

import java.io.File;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.heroes.yellow.entity.impl.FileInfo;
import cn.heroes.yellow.intercepter.TDCellIntercepter;

public class HRTDCellIntercepter extends TDCellIntercepter<FileInfo> {

	private static final Logger logger = LoggerFactory
			.getLogger(HRTDCellIntercepter.class);

	public HRTDCellIntercepter(Set<String> cellPoses) {
		super(cellPoses);
	}

	private Map<String, Object> cellDatas;

	public Map<String, Object> getCellDatas() {
		return cellDatas;
	}

	@Override
	public void callback(Map<String, Object> cellDatas) {
		this.cellDatas = cellDatas;
	}

	@Override
	public void info(FileInfo info) {
		File file = info.body();
		logger.info("The file [{}] is intercepting.", file.getAbsolutePath());
	}

}
