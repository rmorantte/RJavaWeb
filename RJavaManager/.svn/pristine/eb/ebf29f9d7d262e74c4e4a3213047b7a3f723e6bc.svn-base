package org.irri.breedingtool.rjava.staranalysis;

import org.irri.breedingtool.rjava.manager.RJavaManager;

public class TestChiSqGoodnessOfFitRawData {

	private static String BSLASH = "\\";
	private static String FSLASH = "/";
	private static String PATH1 = System.getProperty("user.dir")+ System.getProperty("file.separator") + "sample_datasets" + System.getProperty("file.separator");
	public static String DATA_PATH = PATH1.replace(BSLASH, FSLASH);
	
	public static void main(String[] args) {
		
		//supply path and name of active data
		// use the dataset "icecream.csv" for type of input = "raw"
		String dataFileName = DATA_PATH + "icecream.csv"; 
		
		//supply path and name of text file where text output is going to be saved
		String resultFolderPath = DATA_PATH; 
		
		//specify parameters
		String inputType = "raw";
		String testvar = "numScoops";
		String freqVar = null;
		boolean categoryEqual = true;
		String[] specifiedCategory = null;
		int[] specifiedExpFreq = null;
		
		RJavaManager rJavaManager= new RJavaManager();
		rJavaManager.initStar();
		rJavaManager.getSTARAnalysisManager().doChiSqGoodnessOfFit(dataFileName, 
				resultFolderPath, inputType, testvar, freqVar, categoryEqual, 
				specifiedCategory, specifiedExpFreq);
	}

}
