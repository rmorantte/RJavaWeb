package org.irri.breedingtool.rjava.staranalysis;

import org.irri.breedingtool.rjava.manager.RJavaManager;

public class TestCrossTab {

	private static String BSLASH = "\\";
	private static String FSLASH = "/";
	private static String PATH1 = System.getProperty("user.dir")+ System.getProperty("file.separator") + "sample_datasets" + System.getProperty("file.separator");
	public static String DATA_PATH = PATH1.replace(BSLASH, FSLASH);
	
	public static void main(String[] args) {
		// parameter was change as of 6Dec2012
		//supply path and name of active data
		String dataFileName = DATA_PATH + "StatusAndAdoption.csv"; 
		//supply path where text output is going to be saved
		String resultFolderPath = DATA_PATH; 
		
		//specify parameters
		String[] rowvar = {"TenureStatus"};
		String[] colvar = {"FarmerClassif"};
		boolean expFreq = true;
		boolean stdresid = false;
		boolean totPercent = false;
		boolean rowPercent = false;
		boolean colPercent = false;
		
		RJavaManager rJavaManager= new RJavaManager();
		rJavaManager.initStar();
		rJavaManager.getSTARAnalysisManager().doCrossTab(dataFileName, resultFolderPath, rowvar, colvar, expFreq, stdresid, 
														 totPercent, rowPercent, colPercent);
	}

}
