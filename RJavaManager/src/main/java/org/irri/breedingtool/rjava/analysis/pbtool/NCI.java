package org.irri.breedingtool.rjava.analysis.pbtool;

import org.irri.breedingtool.rjava.manager.RScriptLibraryInit;
import org.rosuda.JRI.Rengine;

public class NCI {

	private Rengine rEngine;

	public NCI(Rengine rEngine, RScriptLibraryInit rScriptLibraryInit) {
		this.rEngine = rEngine;
		rScriptLibraryInit.initLibrary();
	}
	
	
	/**
	 * calls the R statements for performing NCI
	 * 
	 * @param dataFileName - path and name of active data file
	 * @param outFileName - path and name of text file where text output is going to be saved
	 * @param design - experimental design used: "CRD" or "RCBD"
	 * @param respvar - string representing R vector of response variables, e.g. c("Y1","Y2")
	 * @param female - variable name of female factor
	 * @param male - variable name of male factor
	 * @param rep - variable name of replicate factor
	 * @param block - variable name of blocking factor
	 * @param inbred - use TRUE if parents are inbred, FALSE otherwise
	 * @param individual - variable name of the individual variable, NULL if input data are plot means
	 * @param environment - variable name of the environment variable
	 */
	public void doNci(String dataFileName, String outFileName, String design, String respvar, String female, String male, 
			String rep, String block, String inbred, String individual, String environment){

		//defining the R statements
		String readData = "dataRead <- read.csv(\"" + dataFileName + "\", header = TRUE, na.strings = c(\"NA\",\".\"), blank.lines.skip=TRUE, sep = \",\")";
		String sinkIn = "sink(\"" + outFileName + "\")";
		
		String funcNci;
		if (design == "CRD") {
			funcNci = "result<-nc1Test(\"CRD\", \"dataRead\", " + respvar + ", \"" + female + "\", \"" + male + "\", rep = \"" 
			+ rep + "\", block = NULL, inbred = " + inbred + ", individual = NULL, environment = \"" + environment + "\")";
		} else {
			funcNci = "result<-nc1Test(\"RCB\", \"dataRead\", " + respvar + ", \"" + female + "\", \"" + male + "\", rep = NULL, " +
			"block = \"" + block + "\", inbred = " + inbred + ", individual = NULL, environment = \"" + environment + "\")";
		}
			
		String sinkOut = "sink()";
		
		System.out.println(readData);
		System.out.println(sinkIn);
		System.out.println(funcNci);
		System.out.println(sinkOut);
		
		//R statements passed on to the R engine
		rEngine.eval(readData);
		rEngine.eval(sinkIn);
		rEngine.eval(funcNci);
		rEngine.eval(sinkOut);
		
	}
}
