package layout;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public interface BPMNLayoutCreator {
    String createLayout(String process) throws Exception;
}
