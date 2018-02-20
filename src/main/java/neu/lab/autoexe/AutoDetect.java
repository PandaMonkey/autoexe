package neu.lab.autoexe;

import neu.lab.autoexe.entrance.AutoExeEntrance;

public class AutoDetect extends AutoExe {
	public String getBatPath() {
		return "D:\\ws\\detect.bat";
	}

	public String getCommand() {
		return "mvn neu.lab:conflict:1.0:detect -e";
	}

	@Override
	protected String getStateDir() {
		return "D:\\ws\\state_detect\\";
	}

	@Override
	protected String getProjectDir() {
		return AutoExeEntrance.detectProjectDir;
	}

}
