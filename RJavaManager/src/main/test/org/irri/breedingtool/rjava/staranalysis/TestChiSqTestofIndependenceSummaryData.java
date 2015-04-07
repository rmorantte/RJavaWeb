package org.irri.breedingtool.rjava.staranalysis;

import org.irri.breedingtool.rjava.manager.RJavaManager;

public class TestChiSqTestofIndependenceSummaryData {

	private static String BSLASH = "\\";
	private static String FSLASH = "/";
	private static String PATH1 = System.getProperty("user.dir")+ System.getProperty("file.separator") + "sample_datasets" + System.getProperty("file.separator");
	public static String DATA_PATH = PATH1.replace(BSLASH, FSLASH);
	
	public static void main(String[] args) {
		// Chi-Square Test of Independence with Summary data as input
		
		//supply path and name of active data
		String dataFileName = DATA_PATH + "GomezTenureStatusSummary.csv"; 
		//supply path and name of text file where text output is going to be saved
		String resultFolderPath = DATA_PATH; 
		
		//specify parameters
		String inputType = "summary";
		String[] rowvar = {"TenureStatus"};
		String[] colvar = {"FarmerClassif"};
		String freqvar = "Freq";
		boolean obsFreq = true;
		boolean expFreq = true;
		boolean stdresid = false;
		boolean totPercent = false;
		boolean rowPercent = false;
		boolean colPercent = false;
		boolean phi = false;
		boolean cramersv = false;
		boolean contingency = false;
		
		RJavaManager rJavaManager= new RJavaManager();
		rJavaManager.initStar();
		rJavaManager.getSTARAnalysisManager().doChiSqTestOfIndependence(dataFileName, 
				resultFolderPath, inputType, rowvar, colvar, freqvar, obsFreq, expFreq, stdresid, 
				totPercent, rowPercent, colPercent, phi, cramersv, contingency);
	}

}
