package readbiomed.python;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.concurrent.Callable;

import org.apache.uima.UIMAException;
import org.xml.sax.SAXException;

import picocli.CommandLine;
import picocli.CommandLine.Parameters;
import readbiomed.nlp.dictionary.ConceptMapperFactory;

/**
 * 
 * @author Antonio Jimeno Yepes (antonio.jimeno@gmail.com)
 * 
 *         Interface using ConceptMapper as example
 */
public class AnnotationApplicationConceptMapper implements Callable<Integer> {

	@Parameters(index = "0", description = "Dictionary file name.")
	private String dictionaryFileName;

	// Default value uses the default port number
	@Parameters(index = "1", description = "Port number [optional].", defaultValue = "-1")
	private String portNumber;

	@Override
	public Integer call() throws Exception {
		(new AnnotationApplication(ConceptMapperFactory.create(dictionaryFileName), 1))
				.startServer(Integer.parseInt(portNumber));
		return 0;
	}

	public static void main(String[] argc) throws UIMAException, IOException, URISyntaxException, SAXException {
		new CommandLine(new AnnotationApplicationConceptMapper()).execute(argc);
	}
}