package org.irri.breedingtool.rjava.staranalysis;

import org.irri.breedingtool.rjava.manager.RJavaManager;

public class TestChiSqGoodnessOfFitSummaryData {

	private static String BSLASH = "\\";
	private static String FSLASH = "/";
	private static String PATH1 = System.getProperty("user.dir")+ System.getProperty("file.separator") + "sample_datasets" + System.getProperty("file.separator");
	public static String DATA_PATH = PATH1.replace(BSLASH, FSLASH);
	
	public static void main(String[] args) {
		
		//supply path and name of active data
		String dataFileName = DATA_PATH + "icecreamSummary.csv"; 
		
		//supply path and name of text file where text output is going to be saved
		String resultFolderPath = DATA_PATH; 
		
		//specify parameters
		String inputType = "summary";
		String testvar = "numScoops";
		String freqVar = "Count";
		boolean categoryEqual = false;
		String[] specifiedCategory = {"one", "two", "three"};
		int[] specifiedExpFreq = {57, 32, 11};
		
		RJavaManager rJavaManager= new RJavaManager();
		rJavaManager.initStar();
		rJavaManager.getSTARAnalysisManager().doChiSqGoodnessOfFit(dataFileName, 
				resultFolderPath, inputType, testvar, freqVar, categoryEqual, 
				specifiedCategory, specifiedExpFreq);
	}

}
