package mcq;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.coode.owlapi.manchesterowlsyntax.ManchesterOWLSyntaxEditorParser;
import org.semanticweb.HermiT.Reasoner;
import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.expression.OWLEntityChecker;
import org.semanticweb.owlapi.expression.ParserException;
import org.semanticweb.owlapi.expression.ShortFormEntityChecker;
import org.semanticweb.owlapi.model.AddAxiom;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLClassAssertionAxiom;
import org.semanticweb.owlapi.model.OWLClassExpression;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLDataProperty;
import org.semanticweb.owlapi.model.OWLEntity;
import org.semanticweb.owlapi.model.OWLIndividual;
import org.semanticweb.owlapi.model.OWLLiteral;
import org.semanticweb.owlapi.model.OWLNamedIndividual;
import org.semanticweb.owlapi.model.OWLObjectProperty;
import org.semanticweb.owlapi.model.OWLObjectPropertyAssertionAxiom;
import org.semanticweb.owlapi.model.OWLObjectPropertyExpression;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyManager;
import org.semanticweb.owlapi.model.OWLOntologyStorageException;
import org.semanticweb.owlapi.reasoner.Node;
import org.semanticweb.owlapi.reasoner.NodeSet;
import org.semanticweb.owlapi.reasoner.OWLReasoner;
import org.semanticweb.owlapi.reasoner.OWLReasonerFactory;
import org.semanticweb.owlapi.util.BidirectionalShortFormProvider;
import org.semanticweb.owlapi.util.BidirectionalShortFormProviderAdapter;
import org.semanticweb.owlapi.util.ShortFormProvider;
import org.semanticweb.owlapi.util.SimpleShortFormProvider;




public class test {
	@SuppressWarnings("deprecation")
	public void testingMethod(String location) throws OWLOntologyCreationException, OWLOntologyStorageException, IOException {
		
			
		//}
	//public static void main(String [] args) throws OWLOntologyCreationException, OWLOntologyStorageException, IOException {
		
		OWLOntologyManager manager = OWLManager.createOWLOntologyManager();
		File file = new File(location);
		//File file = new File("maha.owl");
		//if(file.canRead())
		//	System.out.println("Hello World");
		StringBuilder sb = new StringBuilder();
		PrintWriter writer = new PrintWriter("mcq.txt", "UTF-8");
		ShortFormProvider shortFormProvider = new SimpleShortFormProvider();
		OWLOntology hp = manager.loadOntologyFromOntologyDocument(file);
		System.out.println("Loaded ontology: " + hp);
		IRI documentIRI = manager.getOntologyDocumentIRI(hp);
		System.out.println("    from: " + documentIRI);
		writer.println("from : "+ documentIRI);
		OWLReasoner reasoner = createReasoner(hp);
 	    //ShortFormProvider shortFormProvider = new SimpleShortFormProvider();
 	    DLQueryPrinter dlQueryPrinter = new DLQueryPrinter(
                 new DLQueryEngine(reasoner, shortFormProvider),
                 shortFormProvider);
		
		//assertNotNull(hp);
		// Named classes referenced by axioms in the ontology.
		
		//for (OWLClass cls :  hp.getClassesInSignature())			
		//	System.out.println(shortFormProvider.getShortForm(cls));
		
		
		//for (OWLIndividual indiv : hp.getIndividualsInSignature()) 
		 //   System.out.println(shortFormProvider.getShortForm((OWLEntity)indiv));
		 Set<OWLNamedIndividual> inds=hp.getIndividualsInSignature();
		    for (OWLNamedIndividual ind: inds){
		    	int rmv=0;
		    	sb.append("Q : choose the one who ");
		    	int and=0;
		    //	System.out.print("#################"+shortFormProvider.getShortForm((OWLEntity)ind));
		       
		    	
		    	for(java.util.Map.Entry<OWLObjectPropertyExpression, Set<OWLIndividual>> entry : ind.getObjectPropertyValues(hp).entrySet()) {
		    		OWLObjectPropertyExpression propertyValues = entry.getKey();
		    		int andd=0;
		    		if(and==0){
		    			sb.append(shortFormProvider.getShortForm((OWLEntity) propertyValues)+" ");
		    		}
		    		else{
		    			sb.append(" and "+shortFormProvider.getShortForm((OWLEntity) propertyValues)+" ");
		    		}
		    		and++;
		    		//System.out.println("#############"+shortFormProvider.getShortForm((OWLEntity) propertyValues));
		    		Set<OWLIndividual> pValues=ind.getObjectPropertyValues((OWLObjectPropertyExpression)propertyValues,hp);
		    		for(OWLIndividual pps : pValues ){
		    			if(andd==0){
			    			sb.append(shortFormProvider.getShortForm((OWLEntity)pps));

		    			}
		    			else{
			    			sb.append(","+ shortFormProvider.getShortForm((OWLEntity)pps));

		    			}
		    			andd++;
		    			
		    			//System.out.print(shortFormProvider.getShortForm((OWLEntity)pps)+"\t");
						System.out.print("Q : choose the one who  "+shortFormProvider.getShortForm((OWLEntity) propertyValues)+" "+shortFormProvider.getShortForm((OWLEntity)pps)+"\n");
						writer.println("Q : choose the one who  "+shortFormProvider.getShortForm((OWLEntity) propertyValues)+" "+shortFormProvider.getShortForm((OWLEntity)pps));
						System.out.println("Choices : key="+shortFormProvider.getShortForm((OWLEntity)ind));
						writer.println("Choices : key="+shortFormProvider.getShortForm((OWLEntity)ind));
						
				    	
						
				    
						
					//query
						
						
						//System.out.print(out);
						Set<OWLClassExpression> query;
						query=ind.getTypes(hp);
						//Set<OWLClassExpression> out = shortFormProvider.getShortForm((OWLEntity)query);
						int lenth_class = 0;
						
						for(OWLClassExpression qr : query ){
							lenth_class++;	
						}
						
						//System.out.println(lenth_class);
						String[] classSet = new String[lenth_class];
						int clength = 0;
						Random rand = new Random();
						int randomNum = rand.nextInt((lenth_class - 1) + 1);
						
						
						for(OWLClassExpression qr : query ){
							classSet[clength]=shortFormProvider.getShortForm((OWLEntity)qr);
							clength++;
							
						} 
						
						//for(String classname : classSet)
							System.out.println("###########"+classSet[randomNum]);
						String qstring = classSet[randomNum];
						Set<? extends OWLEntity> entities= doQueryLoop(dlQueryPrinter,qstring );
						
						 int i=0; 
						 int len_ch=0;
						 int len_chno=0;
						 int rpt=0;
						 for(OWLEntity entity : entities){
							 len_ch++;
						 }
						 System.out.println("%%%%%%%%%%%%%"+len_ch);
						
						
						 String[] ch_set = new String[len_ch-1];
						 
			            if (!entities.isEmpty()) {
			                for (OWLEntity entity : entities) {
			                	
			                	String out = shortFormProvider.getShortForm(entity);
			                	
			                   	if(out==shortFormProvider.getShortForm((OWLEntity)ind)|| out==shortFormProvider.getShortForm((OWLEntity)pps)){
			                		rpt++;
			                	}
			                	else{
			                		i++;
			                		ch_set[len_chno] = out;
			                		len_chno++;
			                		//System.out.println(out);
			                	}
			                	 
			                	 
			                }
			            }
			            
			            Collections.shuffle(Arrays.asList(ch_set)); 
			            int aln=0;
			            aln=ch_set.length;
			            writer.println("Distractors : " );
			            if(aln<3){
			            	
			            		switch(aln){
			            		case 1:
			            			writer.println(":none_of_these");
			            			writer.println(ch_set[0]);
			            			writer.println(":Inconsistent");
			            			break;
			            		case 2:
			            			writer.println(":none_of_these");
			            			writer.println(ch_set[0]);
			            			writer.println(ch_set[1]);
			            		}
			            	
			            	
			            }else{
			            
			            
			            for(i=0;i<3;i++){
			            	System.out.println(ch_set[i]);
			            	if(ch_set[i]==null)
			            		writer.println(":none_of_these");
			            	else
			            		writer.println((i+1)+":"+ch_set[i]);
			            }
			            }
			        }
			            System.out.println();
			            if(rmv!=0){
			            	
			            
			            writer.println(sb);
			            writer.println("Choices : key="+shortFormProvider.getShortForm((OWLEntity)ind));
				    	//System.out.println(sb);
			            Set<OWLNamedIndividual> indvs=hp.getIndividualsInSignature();
			            int indno=0;
					    for (OWLNamedIndividual indv: indvs){
					    	indno++;
					    }
					    String[] indvSet = new String[indno-1];
					    indno=0;
					    for (OWLNamedIndividual indv: indvs){
					    	if(shortFormProvider.getShortForm(indv)!=shortFormProvider.getShortForm((OWLEntity)ind)){
						    	indvSet[indno++] = shortFormProvider.getShortForm(indv);
					    	}
					    	
					    }
					    Collections.shuffle(Arrays.asList(indvSet));
					    writer.println("Distractors : " );
			            for(int d = 0;d<3;d++){
			            	System.out.println(indvSet[d]);
			            	if(indvSet[d]==null)
			            		writer.println(":none_of_these");
			            	else
			            		writer.println((d+1)+":"+indvSet[d]);
			            }
					    
					    
			         }  
					   rmv++; 
					    
			    }
		    	sb.setLength(0);
		    }
		    writer.close();
		    Output op = new Output();
		    op.outprinter();
		    
		 }
			            
			            private static Set<? extends OWLEntity> doQueryLoop(DLQueryPrinter dlQueryPrinter, String classExpression) throws IOException {
			            	 //       while (true) {
			            	           // System.out.println("Enter DL Query");
			            	        	//System.out.println("");
			            	            //String classExpression = "inverse hasDisease value " + Name;
			            	            		//readInput();
			            	            // Check for exit condition
			            	        /*    if (classExpression.equalsIgnoreCase("x")) {
			            	                break;
			            	            }
			            	            */
			            	        	Set<? extends OWLEntity> entities = dlQueryPrinter.askQuery(classExpression.trim());
			            	        	return entities;
			            	        	//  System.out.println();
			            	          //  System.out.println();
			            	   //     }
			            	    }

			            	    private static String readInput() throws IOException {
			            	        InputStream is = System.in;
			            	        InputStreamReader reader;
			            	        reader = new InputStreamReader(is, "UTF-8");
			            	        BufferedReader br = new BufferedReader(reader);
			            	        return br.readLine();
			            	    }

			            	    private static OWLReasoner createReasoner(final OWLOntology rootOntology) {
			            	        // We need to create an instance of OWLReasoner. An OWLReasoner provides
			            	        // the basic query functionality that we need, for example the ability
			            	        // obtain the subclasses of a class etc. To do this we use a reasoner
			            	        // factory.
			            	        // Create a reasoner factory.
			            	        //OWLReasonerFactory reasonerFactory = new StructuralReasonerFactory();
			            	    	OWLReasonerFactory reasonerFactory = new Reasoner.ReasonerFactory();
			            	    	return reasonerFactory.createReasoner(rootOntology);
			            	    }
			            	}

			            	/**
			            	 * This example shows how to perform a "dlquery". The DLQuery view/tab in
			            	 * Protege 4 works like this.
			            	 */

			            	class DLQueryEngine {
			            	    private final OWLReasoner reasoner;
			            	    private final DLQueryParser parser;
			            	    /**
			            	     * Constructs a DLQueryEngine. This will answer "DL queries" using the
			            	     * specified reasoner. A short form provider specifies how entities are
			            	     * rendered.
			            	     * 
			            	     * @param reasoner
			            	     *        The reasoner to be used for answering the queries.
			            	     * @param shortFormProvider
			            	     *        A short form provider.
			            	     */
			            	    public DLQueryEngine(OWLReasoner reasoner,
			            	            ShortFormProvider shortFormProvider) {
			            	        this.reasoner = reasoner;
			            	        OWLOntology rootOntology = reasoner.getRootOntology();
			            	        parser = new DLQueryParser(rootOntology, shortFormProvider);
			            	    }

			            	    /**
			            	     * Gets the superclasses of a class expression parsed from a string.
			            	     * 
			            	     * @param classExpressionString
			            	     *        The string from which the class expression will be parsed.
			            	     * @param direct
			            	     *        Specifies whether direct superclasses should be returned or not.
			            	     * @return The superclasses of the specified class expression If there was a
			            	     *         problem parsing the class expression.
			            	     * @throws ParserException 
			            	     */
			            	    public Set<OWLClass> getSuperClasses(String classExpressionString,
			            	            boolean direct) throws ParserException {
			            	        if (classExpressionString.trim().length() == 0) {
			            	            return Collections.emptySet();
			            	        }
			            	        OWLClassExpression classExpression = parser.parseClassExpression(classExpressionString);
			            	        NodeSet<OWLClass> superClasses = reasoner.getSuperClasses(
			            	                classExpression, direct);
			            	        return superClasses.getFlattened();
			            	    }

			            	    /**
			            	     * Gets the equivalent classes of a class expression parsed from a string.
			            	     * 
			            	     * @param classExpressionString
			            	     *        The string from which the class expression will be parsed.
			            	     * @return The equivalent classes of the specified class expression If there
			            	     *         was a problem parsing the class expression.
			            	     * @throws ParserException 
			            	     */
			            	    public Set<OWLClass> getEquivalentClasses(String classExpressionString) throws ParserException {
			            	        if (classExpressionString.trim().length() == 0) {
			            	            return Collections.emptySet();
			            	        }
			            	        OWLClassExpression classExpression = parser.parseClassExpression(classExpressionString);
			            	        Node<OWLClass> equivalentClasses = reasoner
			            	                .getEquivalentClasses(classExpression);
			            	        Set<OWLClass> result;
			            	        if (classExpression.isAnonymous()) {
			            	            result = equivalentClasses.getEntities();
			            	        } else {
			            	            result = equivalentClasses.getEntitiesMinus(classExpression
			            	                    .asOWLClass());
			            	        }
			            	        return result;
			            	    }

			            	    /**
			            	     * Gets the subclasses of a class expression parsed from a string.
			            	     * 
			            	     * @param classExpressionString
			            	     *        The string from which the class expression will be parsed.
			            	     * @param direct
			            	     *        Specifies whether direct subclasses should be returned or not.
			            	     * @return The subclasses of the specified class expression If there was a
			            	     *         problem parsing the class expression.
			            	     * @throws  
			            	     */
			            	    public Set<OWLClass> getSubClasses(String classExpressionString,
			            	            boolean direct) throws ParserException {
			            	        if (classExpressionString.trim().length() == 0) {
			            	            return Collections.emptySet();
			            	        }
			            	        OWLClassExpression classExpression = parser.parseClassExpression(classExpressionString);
			            	        NodeSet<OWLClass> subClasses = reasoner.getSubClasses(classExpression,
			            	                direct);
			            	        return subClasses.getFlattened();
			            	    }

			            	    /**
			            	     * Gets the instances of a class expression parsed from a string.
			            	     * 
			            	     * @param classExpressionString
			            	     *        The string from which the class expression will be parsed.
			            	     * @param direct
			            	     *        Specifies whether direct instances should be returned or not.
			            	     * @return The instances of the specified class expression If there was a
			            	     *         problem parsing the class expression.
			            	     * @throws ParserException 
			            	     */
			            	    public Set<OWLNamedIndividual> getInstances(String classExpressionString,
			            	            boolean direct) throws ParserException {
			            	        if (classExpressionString.trim().length() == 0) {
			            	            return Collections.emptySet();
			            	        }
			            	        OWLClassExpression classExpression = parser.parseClassExpression(classExpressionString);
			            	        NodeSet<OWLNamedIndividual> individuals = reasoner.getInstances(
			            	                classExpression, direct);
			            	        return individuals.getFlattened();        
			            	    }
			            	}

			            	class DLQueryParser {

			            	    private final OWLOntology rootOntology;
			            	    private final BidirectionalShortFormProvider bidiShortFormProvider;

			            	    /**
			            	     * Constructs a DLQueryParser using the specified ontology and short form
			            	     * provider to map entity IRIs to short names.
			            	     * 
			            	     * @param rootOntology
			            	     *        The root ontology. This essentially provides the domain vocabulary
			            	     *        for the query.
			            	     * @param shortFormProvider
			            	     *        A short form provider to be used for mapping back and forth
			            	     *        between entities and their short names (renderings).
			            	     */
			            	    public DLQueryParser(OWLOntology rootOntology,
			            	            ShortFormProvider shortFormProvider) {
			            	        this.rootOntology = rootOntology;
			            	        OWLOntologyManager manager = rootOntology.getOWLOntologyManager();
			            	        Set<OWLOntology> importsClosure = rootOntology.getImportsClosure();
			            	        // Create a bidirectional short form provider to do the actual mapping.
			            	        // It will generate names using the input
			            	        // short form provider.
			            	        bidiShortFormProvider = new BidirectionalShortFormProviderAdapter(
			            	                manager, importsClosure, shortFormProvider);
			            	    }

			            	    /**
			            	     * Parses a class expression string to obtain a class expression.
			            	     * 
			            	     * @param classExpressionString
			            	     *        The class expression string
			            	     * @return The corresponding class expression if the class expression string
			            	     *         is malformed or contains unknown entity names.
			            	     * @throws ParserException 
			            	     */
			            	    public OWLClassExpression
			            	            parseClassExpression(String classExpressionString) throws ParserException {
			            	        OWLDataFactory dataFactory = rootOntology.getOWLOntologyManager().getOWLDataFactory();
			            	        // Set up the real parser
			            	        ManchesterOWLSyntaxEditorParser parser = new ManchesterOWLSyntaxEditorParser(
			            	                dataFactory, classExpressionString);
			            	        parser.setDefaultOntology(rootOntology);
			            	        // Specify an entity checker that will be used to check a class
			            	        // expression contains the correct names.
			            	        OWLEntityChecker entityChecker = new ShortFormEntityChecker(
			            	                bidiShortFormProvider);
			            	        parser.setOWLEntityChecker(entityChecker);
			            	        // Do the actual parsing
			            	        return parser.parseClassExpression();
			            	    }
			            	}

class DLQueryPrinter {

			            	    private final DLQueryEngine dlQueryEngine;
			            	    private final ShortFormProvider shortFormProvider;

			            	    /**
			            	     * @param engine
			            	     *        the engine
			            	     * @param shortFormProvider
			            	     *        the short form provider
			            	     */
			            	    public DLQueryPrinter(DLQueryEngine engine,
			            	            ShortFormProvider shortFormProvider) {
			            	        this.shortFormProvider = shortFormProvider;
			            	        dlQueryEngine = engine;
			            	    }

			            	    /**
			            	     * @param classExpression
			            	     *        the class expression to use for interrogation
			            	     */
			            	    
			            	    public Set<? extends OWLEntity> askQuery(String classExpression) {
			            	        if (classExpression.length() == 0) {
			            	            System.out.println("No class expression specified");
			            	           // return Collections.emptySet();
			            	        } else {
			            	            try {
			            	                StringBuilder sb = new StringBuilder();
			            	             /*
			            	                // Ask for the subclasses, superclasses etc. of the specified
			            	                // class expression. Print out the results.
			            	               
			            	                /*
			            	                Set<OWLClass> superClasses = dlQueryEngine.getSuperClasses(
			            	                        classExpression, true);
			            	                printEntities("SuperClasses", superClasses, sb);
			            	                Set<OWLClass> equivalentClasses = dlQueryEngine
			            	                        .getEquivalentClasses(classExpression);
			            	                printEntities("EquivalentClasses", equivalentClasses, sb);
			            	                Set<OWLClass> subClasses = dlQueryEngine.getSubClasses(
			            	                        classExpression, true);
			            	                //printEntities("SubClasses", subClasses, sb);
			            	                */
			            	                
			            	                Set<OWLNamedIndividual> individuals = dlQueryEngine
			            	                        .getInstances(classExpression, true);
			            	                printEntities("Instances", individuals, sb);
			            	                return individuals;

			            	                             //   System.out.println(sb.toString());
			            	            } catch (ParserException e) {
			            	                System.out.println(e.getMessage());
			            	            }
			            	        }
			            			return null;
			            	        
			            	    }

			            	    private void printEntities(String name, Set<? extends OWLEntity> entities,
			            	            StringBuilder sb) {
			            	        sb.append(name);
			            	        int length = 50 - name.length();
			            	        for (int i = 0; i < length; i++) {
			            	            sb.append(".");
			            	        }
			            	        sb.append("\n\n");
			            	        
			            	        if (!entities.isEmpty()) {
			            	            for (OWLEntity entity : entities) {
			            	            	String out = shortFormProvider.getShortForm(entity);
			            	            	// System.out.println(out);
			            	            }
			            	        }  
			            	        
			            	        if (!entities.isEmpty()) {
			            	            for (OWLEntity entity : entities) {
			            	                sb.append("\t");
			            	                sb.append(shortFormProvider.getShortForm(entity));
			            	                sb.append("\n");
			            	            }
			            	        } else {
			            	            sb.append("\t[NONE]\n");
			            	        }
			            	        sb.append("\n");
			            	    }
			            	
 
						
						
		    		}
		    	
		
		
		
		
		


	

	


