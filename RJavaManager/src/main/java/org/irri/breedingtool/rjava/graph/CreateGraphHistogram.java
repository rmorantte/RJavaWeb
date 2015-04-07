package org.irri.breedingtool.rjava.graph;


import org.irri.breedingtool.rjava.manager.RScriptLibraryInit;
import org.rosuda.JRI.Rengine;

public class CreateGraphHistogram {

	private Rengine rEngine;
	
	public CreateGraphHistogram(Rengine rEngine, RScriptLibraryInit rScriptLibraryInit) {
		this.rEngine = rEngine;
		rScriptLibraryInit.initLibrary();

	}
	
	public void createHistogram(String dataFileName, String respvar){
		
		//specify the path where the "plots" folder will be created
		String bslash = "\\";
		String fslash = "/";
		String path = System.getProperty("user.dir")+ System.getProperty("file.separator") + "sample_datasets";
		String plot_path = path.replace(bslash, fslash);
		
		String graphHist = "g <- tryCatch(CreateGraphHistogram(\"" + plot_path + "\", dataName = \"" + dataFileName + "\", respvar = \"" + respvar + "\"), error = function(err) \"notRun\")";
		
		rEngine.eval(graphHist);
		
		System.out.println(plot_path);
		System.out.println("graphHist: " + graphHist);

		//for checking if the R function CreateGraphHistogram encountered an error (use of "tryCatch" in the statement above)
		//will display "notRun" if an error is encountered, "null" otherwise  
		String runSuccessG = rEngine.eval("g").asString();
		System.out.println("runSuccessG: " + runSuccessG);
	}
	


}
