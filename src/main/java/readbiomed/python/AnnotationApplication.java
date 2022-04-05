package readbiomed.python;

import java.util.Collection;

import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.uima.UIMAException;
import org.apache.uima.analysis_engine.AnalysisEngine;
import org.apache.uima.analysis_engine.AnalysisEngineDescription;
import org.apache.uima.fit.factory.JCasFactory;
import org.apache.uima.fit.util.JCasUtil;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.tcas.Annotation;

import py4j.GatewayServer;

public class AnnotationApplication {
	GenericObjectPool<AnalysisEngine> pool;

	private void createApplication(AnalysisEngineDescription aed, int max) {
		this.pool = new GenericObjectPool<>(new AnalysisEnginePoolFactory(aed));
		this.pool.setMaxTotal(max);
	}

	public AnnotationApplication(AnalysisEngineDescription aed, int max) {
		createApplication(aed, max);
	}

	public AnnotationApplication(AnalysisEngineDescription aed) {
		createApplication(aed, 1);
	}

	public Collection<Annotation> annotate(String text) throws UIMAException {
		JCas jCas = JCasFactory.createText(text);

		AnalysisEngine ae = null;

		try {
			ae = pool.borrowObject();
			ae.process(jCas);
		} catch (Exception e) {
			throw new UIMAException(e);
		} finally {
			if (ae != null) {
				pool.returnObject(ae);
			}
		}
		return JCasUtil.select(jCas, Annotation.class);
	}

	public void startServer() {
		startServer(-1);
	}

	public void startServer(int port) {
		if (port > 0) {
			(new GatewayServer(this, port)).start();
		} else {
			(new GatewayServer(this)).start();
		}
		System.out.println("Gateway Server Started");
	}
}