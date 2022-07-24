package environmentinfo;

import java.io.File;

public class EnvironementInformation {
	private static String resourcePath;
    private EnvironementInformation() {}
	public static String getProjectMainPath() {
		return System.getProperty("user.dir");
	}

	public static String getResourcePath() {
		if (resourcePath == null) {
			String path = System.getProperty("user.dir").concat("/src/test/resources");
			File file = new File(path);
			if (file.exists())
				resourcePath = path;
			else
				resourcePath = getProjectMainPath();
		}
		return resourcePath;
	}

}
