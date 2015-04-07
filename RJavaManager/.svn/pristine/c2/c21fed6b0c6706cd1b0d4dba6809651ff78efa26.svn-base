package org.irri.breedingtool.rjava.staranalysis;

import org.irri.breedingtool.rjava.manager.RJavaManager;

public class TestPairedProportion {

	private static String BSLASH = "\\";
	private static String FSLASH = "/";
	private static String PATH1 = System.getProperty("user.dir")+ System.getProperty("file.separator") + "sample_datasets" + System.getProperty("file.separator");
	public static String DATA_PATH = PATH1.replace(BSLASH, FSLASH);
	
	public static void main(String[] args) {
		
		//supply path and name of active data
		String dataFileName = DATA_PATH + "relatedPropData.csv"; 
		//supply path and name of text file where text output is going to be saved
		String resultFolderPath = DATA_PATH; 
		
		//specify parameters
		String[] varX = {"first"};
		String[] varY = {"second"}; 
		String grp = null; 
		double[] testVal = null;
		String procedure = "paired";
		String alternative = "not equal";
		boolean confInterval = false;
		int confLevel = 95;
				
		RJavaManager rJavaManager= new RJavaManager();
		rJavaManager.initStar();
		rJavaManager.getSTARAnalysisManager().doProportionTest(dataFileName, 
				resultFolderPath, procedure, varX, varY, grp, testVal, 
				alternative, confInterval, confLevel);
	}

}
