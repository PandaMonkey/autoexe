package neu.lab.autoexe;

public class AutoExeSta extends AutoExe{
	public String getBatPath() {
		return "D:\\ws\\sta.bat";
	}

	public String getCommand() {
		return "mvn neu.lab:conflict:1.0:sta -e";
	}
	@Override
	protected String getStateDir() {
		return "D:\\ws\\state_sta\\";
	}
}
