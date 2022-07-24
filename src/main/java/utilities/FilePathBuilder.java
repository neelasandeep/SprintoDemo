package utilities;

import environmentinfo.EnvironementInformation;

import java.io.File;

public class FilePathBuilder {

	private String resourceName;
	private String parentDirectory;

	public String getParentDirectory() {
		return parentDirectory;
	}

	public void setParentDirectory(String parentDirectory) {
		this.parentDirectory = parentDirectory;
	}

	public FilePathBuilder(String resourceName) {
		this.resourceName = resourceName;
	}

	public String getFilePath() {
		StringBuilder builder = new StringBuilder();
		builder.append(EnvironementInformation.getResourcePath()).append(File.separator);
		if (parentDirectory != null)
			builder.append(parentDirectory).append(File.separator);
		builder.append(resourceName);
		return builder.toString();
	}
}
