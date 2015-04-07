package org.irri.breedingtool.rjava.staranalysis;

import org.irri.breedingtool.rjava.manager.RJavaManager;

public class TestANOVACombinedRCBD {

	private static String BSLASH = "\\";
	private static String FSLASH = "/";
	private static String PATH1 = System.getProperty("user.dir")+ System.getProperty("file.separator") + "sample_datasets" + System.getProperty("file.separator");
	public static String DATA_PATH = PATH1.replace(BSLASH, FSLASH);
	
	public static void main(String[] args) {
		// Case: CRD
		//supply path and name of active data
		String dataFileName = DATA_PATH + "CropStatRCBDOverSite.csv"; 
		//supply path where the output will be placed
		String outputPath = DATA_PATH; 

		//specify parameters
		String design = "RCBD";
		String[] respvar = {"YIELD"};
		String[] factor1 = {"NTRT"};
		String[] factor2 = null;
		String[] factor3 = null;
		String[] factor4 = null;
		// if design is "CRD" rep1 should be equal to null
		String rep1 = "REP";
		String rep2 = null; 
		String set = "SITE";
				
		boolean descriptive = false;
		boolean normality = true;
		boolean homogeneity = false;

		String[] pwtest = null;
		String[] pwvar = null;
		boolean contrastOption = false;
		double sig = 0.05;
				
		RJavaManager rJavaManager= new RJavaManager();
		rJavaManager.initStar();
		rJavaManager.getSTARAnalysisManager().doANOVACombined(dataFileName, outputPath,
				design, respvar, factor1, factor2, factor3, factor4,
				rep1, rep2, set, descriptive, normality, homogeneity, pwtest, pwvar, 
				contrastOption, sig);
	}

}
