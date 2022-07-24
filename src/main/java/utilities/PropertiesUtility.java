package utilities;

import lombok.extern.slf4j.Slf4j;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
@Slf4j
public class PropertiesUtility {
	static Properties properties = new Properties();
	private PropertiesUtility() {}


	public static String getProperty(String key) {
		File src = new File("./src/test/resources/Config.properties");
		try {
			loadSourceFile(src);

		} catch (Exception e) {
			log.info("Unable to load config File", e);
		}
		return properties.getProperty(key);
	}



	private static void loadSourceFile(File src) throws  IOException {
		FileInputStream fis = new FileInputStream(src);

		properties.load(fis);
		log.info("report properties file reading is done");
	}

}
