from py4j.java_gateway import JavaGateway

# Connect to JVM
gateway = JavaGateway() 
annotation_app = gateway.entry_point

# Annotate
l = annotation_app.annotate("Example with ConceptMapper for Streptomyces albospinus")
[print(i, i.getBegin(), i.getEnd(), i.getCoveredText()) for i in l.iterator() if str(i.getClass()).endswith("DictTerm")]