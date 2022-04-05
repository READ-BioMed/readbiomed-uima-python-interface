# readbiomed-uima-python-interface

Documentation under development.

Given an AnalysisEngine, this package can help set the analysis engine as a server that can be accessed from Python.


From Java, you can start the server from the AnnotationApplication starServer function:

```
(new AnnotationApplication(ConceptMapperFactory.create(dictionaryFileName), 1))
				.startServer(Integer.parseInt(portNumber));
```

From Python, you can call the server, annotate text using the annotation engine and access the list of annotation.

```
from py4j.java_gateway import JavaGateway

# Connect to JVM
gateway = JavaGateway() 
annotation_app = gateway.entry_point

# Annotate
l = annotation_app.annotate("Example with ConceptMapper for Streptomyces albospinus")
[print(i, i.getBegin(), i.getEnd(), i.getCoveredText()) for i in l.iterator() if str(i.getClass()).endswith("DictTerm")]
```

## Installation

This package has been tested with Java 11 and Maven 3.6.3 and Python 3.9

There is a dependency with our ConceptMapper wrapper, that needs to be installed first, please visit:

[https://github.com/READ-BioMed/readbiomed-conceptmapper](https://github.com/READ-BioMed/readbiomed-conceptmapper)

To install readbiomed-uima-python-interface, first clone it and then run `mvn install`

Py4j needs to be installed

`pip install py4j`