package cn.yhhh.fairy.yellow.core;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.ho.yaml.Yaml;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.yhhh.fairy.yellow.filler.Filler;
import cn.yhhh.fairy.yellow.interceptor.Interceptor;
import cn.yhhh.fairy.yellow.parser.Parser;

/**
 * Configuration.
 * 
 * @author Leon Kidd
 * @version 1.00, 2011-9-5
 * @since 1.0
 */
public class Configuration {
	private static final Logger logger = LoggerFactory
			.getLogger(Configuration.class);
	/** Parser */
	private Parser parser;
	/** Interceptors */
	private List<Interceptor> interceptors = new ArrayList<Interceptor>();
	/** Filler */
	private Set<Filler> fillers = new HashSet<Filler>();
	/** Data file or directory */
	private File data;

	public Configuration() {

	}

	public static Configuration configure() {
		return configure("yellow.yml");
	}

	/**
	 * Load module and initialize them.
	 * 
	 * @return
	 */
	public static Configuration configure(String resource) {
		return configure(new File(resource));
	}

	public static Configuration configure(File resource) {
		Configuration config = new Configuration();
		try {
			logger.info("Load configuration file {}.", resource.getName());
			config = Yaml.loadType(resource, Configuration.class);
		} catch (FileNotFoundException e) {
			logger.error("Configuration file {} not found.", resource.getName());
		}

		// TODO Use XML instead
		// config.parser = new SimpleDelParser();
		// config.interceptors = new ArrayList<Interceptor>();
		// config.fillers = new HashSet<Filler>();
		// end of Test

		return config;
	}

	public List<Interceptor> getInterceptors() {
		return interceptors;
	}

	public Parser getParser() {
		return parser;
	}

	public Set<Filler> getFillers() {
		return fillers;
	}

	// ============== Delete where use xml configuration

	public void setParser(Parser parser) {
		this.parser = parser;
	}

	public void setInterceptors(List<Interceptor> interceptors) {
		this.interceptors = interceptors;
	}

	public void addInterceptor(Interceptor interceptor) {
		this.interceptors.add(interceptor);
	}

	public void setFillers(Set<Filler> fillers) {
		this.fillers = fillers;
	}

	public File getData() {
		return data;
	}

	/**
	 * Data file or directory
	 * 
	 * @param data
	 */
	public void setData(File data) {
		this.data = data;
	}

}
