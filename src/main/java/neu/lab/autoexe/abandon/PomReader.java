package neu.lab.autoexe.abandon;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class PomReader {
	private String groupId;
	private String artifactId;
	private String versionId;

	public PomReader(String pomPath) throws DocumentException {
		SAXReader reader = new SAXReader();
		Document document = reader.read(pomPath);
		Element project = document.getRootElement();
		Element parent = project.element("parent");
		if(parent!=null)
			getCoordinate(parent);
		getCoordinate(project);
		System.out.println(groupId+":"+artifactId+":"+versionId);
		
	}
	private void getCoordinate(Element e) {
		Element groupE = e.element("groupId");
		if (null != groupE) {
			groupId = groupE.getStringValue();
		}
		Element artifactE = e.element("artifactId");
		if (null != artifactE) {
			artifactId = artifactE.getStringValue();
		}
		Element versionE = e.element("version");
		if (null != versionE) {
			versionId = versionE.getStringValue();
		}
	}

	public static void main(String[] args) throws DocumentException {
		new PomReader("D:\\ws\\pom.xml");
	}
}
