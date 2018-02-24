package neu.lab.autoexe.entrance;

import java.io.IOException;

import neu.lab.autoexe.AutoUpVer;

public class AutoExeEntrance {

	public static final String staProjectDir = "D:\\ws\\gitHub_old\\";
	public static final String detectProjectDir = "D:\\ws\\gitHub_new\\";
	public static final String upVerProjectDir = "D:\\ws\\solvebug\\ignite-master";
	public static final String findVerDir = "D:\\ws\\solvebug\\ignite-master";

	public static void main(String[] args) throws IOException {
		new AutoUpVer().autoExe();
	}

}
