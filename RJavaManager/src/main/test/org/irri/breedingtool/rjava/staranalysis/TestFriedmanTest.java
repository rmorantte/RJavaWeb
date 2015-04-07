package org.irri.breedingtool.rjava.staranalysis;

import org.irri.breedingtool.rjava.manager.RJavaManager;

public class TestFriedmanTest {

	private static String BSLASH = "\\";
	private static String FSLASH = "/";
	private static String PATH1 = System.getProperty("user.dir")+ System.getProperty("file.separator") + "sample_datasets" + System.getProperty("file.separator");
	public static String DATA_PATH = PATH1.replace(BSLASH, FSLASH);
	
	public static void main(String[] args) {
		
		//supply path and name of active data
		String dataFileName = DATA_PATH + "SeedingRate.csv"; 
		//supply path and name of text file where text output is going to be saved
		String resultFolderPath = DATA_PATH ; 
		
		//specify parameters
		String[] respvar = {"GrainYield"};
		String treatment = "SeedingRate";
		String block = "Rep";
				
		RJavaManager rJavaManager= new RJavaManager();
		rJavaManager.initStar();
		rJavaManager.getSTARAnalysisManager().doFriedman(dataFileName, 
				resultFolderPath, respvar, treatment, block);
	}

}
