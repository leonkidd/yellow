package cn.yhhh.fairy.yellow.core;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.heroes.jkit.utils.Callback;
import cn.heroes.jkit.utils.FileUtils;
import cn.yhhh.fairy.yellow.entity.Row;
import cn.yhhh.fairy.yellow.filler.Filler;
import cn.yhhh.fairy.yellow.interceptor.Interceptor;
import cn.yhhh.fairy.yellow.parser.Parser;

/**
 * Yellow框架入口调用程序
 * 
 * @author Leon Kidd
 * @version 1.00, 2011-9-5
 * @since 1.0
 */
public class Yellow {

	private static final Logger logger = LoggerFactory.getLogger(Yellow.class);

	// TODO 可以不配置Filler
	// TODO Interceptor中begin等方案要和directory等方法一起吗？end是否是全部结束。
	// TODO 不同文件进来,在读他们时每个重新开始Interceptor周期
	public static void yellow(final Configuration config) {

		FileUtils.recursion(config.getData(), null, new Callback() {

			@Override
			public void invoke(Object... o) {
				core(config, (File) o[0]);
			}
		});

	}

	/**
	 * 
	 * @param config
	 *            配置
	 * @param file
	 *            非目录文件
	 */
	public static void core(Configuration config, File file) {

		// Configuration Modules
		Parser parser = config.getParser();
		parser.init();
		parser.parser(config.getData());

		// initialize
		int ii = 0;
		for (Interceptor interceptor : config.getInterceptors()) {
			interceptor.init(config);
			interceptor.file(file);
			ii++;
		}
		logger.info("Load {} interceptors", ii);

		ii = 0;
		for (Filler filler : config.getFillers()) {
			filler.init();
			ii++;
		}
		logger.info("Load {} fillers", ii);

		List<Interceptor> is = config.getInterceptors();
		Interceptor[] interceptors = is.toArray(new Interceptor[] {});

		// sign
		Map<Interceptor, Object> begins = new HashMap<Interceptor, Object>();
		Map<Interceptor, Object> ends = new HashMap<Interceptor, Object>();
		Object value = new Object();

		// Iterate
		Row row;
		while ((row = parser.next()) != null) {
			interceptor: for (Interceptor interceptor : interceptors) {
				if (ends.get(interceptor) != null)
					break interceptor;
				// TODO 一边迭代一边fill
				if (begins.get(interceptor) != null) {
					if (interceptor.end(row)) {
						ends.put(interceptor, value);
						break interceptor;
					} else if (interceptor.ignore(row)) {
						continue interceptor;
					} else {
						interceptor.row(row);
					}
				} else if (interceptor.begin(row)) {
					begins.put(interceptor, value);
				}

			}
		}

		// Destroy
		for (Interceptor interceptor : interceptors) {
			interceptor.destroy();
		}

		parser.destroy();
	}
}
