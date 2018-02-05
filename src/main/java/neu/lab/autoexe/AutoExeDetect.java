package neu.lab.autoexe;

public class AutoExeDetect extends AutoExe {
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

}
