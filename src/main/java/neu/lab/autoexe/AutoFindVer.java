package neu.lab.autoexe;

import neu.lab.autoexe.entrance.AutoExeEntrance;

public class AutoFindVer extends AutoExe{

	@Override
	protected String getStateDir() {
		return "D:\\ws\\state_findVer\\";
	}

	@Override
	protected String getProjectDir() {
		return AutoExeEntrance.findVerDir;
	}

	@Override
	public String getBatPath() {
		return "D:\\ws\\findVer.bat";
	}

	@Override
	public String getCommand() {
		return "mvn neu.lab:conflict:1.0:findVersion -e";
	}

}
