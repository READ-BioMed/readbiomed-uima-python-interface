package readbiomed.python;

import org.apache.commons.pool2.BasePooledObjectFactory;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.impl.DefaultPooledObject;
import org.apache.uima.analysis_engine.AnalysisEngine;
import org.apache.uima.analysis_engine.AnalysisEngineDescription;
import org.apache.uima.fit.factory.AnalysisEngineFactory;

public class AnalysisEnginePoolFactory extends BasePooledObjectFactory<AnalysisEngine> {

	private AnalysisEngineDescription description;

	public AnalysisEnginePoolFactory(AnalysisEngineDescription description) {
		this.description = description;
	}

	@Override
	public AnalysisEngine create() throws Exception {
		return AnalysisEngineFactory.createEngine(description);
	}

	@Override
	public PooledObject<AnalysisEngine> wrap(AnalysisEngine ae) {
		return new DefaultPooledObject<AnalysisEngine>(ae);
	}
}