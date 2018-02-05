package neu.lab.autoexe;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecutor;

public abstract class AutoExe {
	public FileSyn donePjct;// project has done;
	public FileSyn mvnExpPjt;// project that throws exception when executes maven command
	public FileSyn noCnflictPjct;// record project that hasn't conflict

	protected void readState() throws IOException {
		donePjct = new FileSyn(getStateDir(), "Project_done.txt");
		mvnExpPjt = new FileSyn(getStateDir(), "Project_throw_error.txt");
		noCnflictPjct = new FileSyn(getStateDir(), "Project_no_conflict.txt");
	}

	protected abstract String getStateDir();

	private void writeState() {
		donePjct.closeOut();
		mvnExpPjt.closeOut();
		noCnflictPjct.closeOut();
	}

	public void autoExe() throws IOException {
		readState();
		File baseFile = new File(getProjectDir());
		List<String> pomDirs = findPomPaths(baseFile);
		for (String pomPath : pomDirs) {
			String projectName = path2name(pomPath);
			if (!donePjct.contains(projectName) && !mvnExpPjt.contains(projectName)
					&& !noCnflictPjct.contains(projectName)) {
				execOnce(pomPath);
				donePjct.add(path2name(pomPath));
			} else {
				System.out.println("skip project:" + pomPath);
			}
		}
		writeState();
	}

	protected abstract String getProjectDir();

	private void execOnce(String pomPath) {
		try {
			System.out.println("exec mvn for:" + pomPath);
			writeBat(pomPath);
			String line = "cmd.exe /C " + getBatPath();
			CommandLine cmdLine = CommandLine.parse(line);
			DefaultExecutor executor = new DefaultExecutor();
			executor.execute(cmdLine);
		} catch (Exception e) {
			e.printStackTrace();
			mvnExpPjt.add(pomPath);
		}
	}

	private String path2name(String path) {
		// D:\test_apache\simple\commons-logging-1.2-src
		return path.replace(Conf.staProjectDir, "");
	}

	private boolean isSingle(File pomFile) {
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(pomFile));
			String line = reader.readLine();
			while (line != null) {
				if (line.contains("<modules>")) {
					reader.close();
					return false;
				}
				line = reader.readLine();
			}
			reader.close();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	private List<String> findPomPaths(File father) {
		File[] children = father.listFiles();
		List<String> pomPaths = new ArrayList<String>();
		for (File child : children) {
			if (child.getName().equals("pom.xml")) {
				if (isSingle(child))
					pomPaths.add(father.getAbsolutePath());
			}
			if (child.isDirectory()) {
				pomPaths.addAll(findPomPaths(child));
			}
		}
		return pomPaths;
	}

	private void writeBat(String pomPath) throws IOException {
		PrintWriter printer = new PrintWriter(new BufferedWriter(new FileWriter(getBatPath())));
		printer.println("cd " + pomPath);
		// printer.println("mvn -Dmaven.test.skip=true package
		// neu.lab:conflict:1.0:detect -e");
		printer.println(getCommand());
		printer.close();
	}

	public abstract String getBatPath();

	public abstract String getCommand();
}
