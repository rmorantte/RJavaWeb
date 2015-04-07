package org.irri.breedingtool.rjava.staranalysis;

import org.irri.breedingtool.rjava.manager.RJavaManager;

public class TestPairwise {

	private static String BSLASH = "\\";
	private static String FSLASH = "/";
	private static String PATH1 = System.getProperty("user.dir")+ System.getProperty("file.separator") + "sample_datasets" + System.getProperty("file.separator");
	public static String DATA_PATH = PATH1.replace(BSLASH, FSLASH);
	
	public static void main(String[] args) {
		//supply path where the tempANOVAWorkspace.Rda is save
		String inputPath = DATA_PATH; 

		//specify parameters
		String design = "RCBD";
		String[] respvar = {"GrainYield"};
		String[] method = {"LSD", "HSD"};
		double sig = 0.05;
		boolean combined = false;
				
		RJavaManager rJavaManager= new RJavaManager();
		rJavaManager.initStar();
		rJavaManager.getSTARAnalysisManager().doPairwiseANOVA(inputPath, 
				respvar, method, design, sig, combined);
	}

}
