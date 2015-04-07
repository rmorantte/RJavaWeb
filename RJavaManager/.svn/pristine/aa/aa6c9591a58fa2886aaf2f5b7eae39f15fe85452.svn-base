package org.irri.breedingtool.rjava.staranalysis;

import org.irri.breedingtool.rjava.manager.RJavaManager;

public class TestBalancedIncompleteBlockDesign {

	private static String BSLASH = "\\";
	private static String FSLASH = "/";
	private static String PATH1 = System.getProperty("user.dir")+ System.getProperty("file.separator") + "sample_datasets" + System.getProperty("file.separator");
	public static String DATA_PATH = PATH1.replace(BSLASH, FSLASH);
	
	public static void main(String[] args) {
		
		//supply path and name of active data
		String dataFileName = DATA_PATH + "BIB_TIME.csv"; 
		//supply path where the output will be placed
		String outputPath = DATA_PATH; 

		//specify parameters
		String[] respvar = {"time"};
		String treatment = "catalyst";
		String block = "batch";
		boolean descriptive = true;
		boolean normality = true;
		boolean homogeneity = true; 
		String[] method = null;
		String set = null;
		double sig = 0.05;
				
		RJavaManager rJavaManager= new RJavaManager();
		rJavaManager.initStar();
		rJavaManager.getSTARAnalysisManager().doBIBD(dataFileName, outputPath, 
				respvar, treatment, block, set, method, 
				descriptive, normality, homogeneity, sig);
	}

}
