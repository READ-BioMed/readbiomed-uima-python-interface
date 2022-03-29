package readbiomed.python;

import java.util.Collection;

import org.apache.uima.UIMAException;
import org.apache.uima.analysis_engine.AnalysisEngine;
import org.apache.uima.fit.factory.JCasFactory;
import org.apache.uima.fit.util.JCasUtil;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.tcas.Annotation;

import py4j.GatewayServer;

public class AnnotationApplication {

	private AnalysisEngine ae = null;

	private AnnotationApplication(AnalysisEngine ae) {
		this.ae = ae;
	}

	public Collection<Annotation> annotate(String text) throws UIMAException {
		JCas jCas = JCasFactory.createText(text);
		ae.process(jCas);
		return JCasUtil.select(jCas, Annotation.class);
	}

	public static void startServer(AnalysisEngine ae) {
		startServer(ae, -1);
	}

	public static void startServer(AnalysisEngine ae, int port) {
		AnnotationApplication app = new AnnotationApplication(ae);
		if (port > 0) {
			(new GatewayServer(app, port)).start();
		} else {
			(new GatewayServer(app)).start();
		}
		System.out.println("Gateway Server Started");
	}
}