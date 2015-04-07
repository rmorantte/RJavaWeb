package org.irri.breedingtool.rjava.pbtoolsanalysis;

import org.irri.breedingtool.rjava.manager.RJavaManager;

public class TestGeneticDistance {

	private static String BSLASH = "\\";
	private static String FSLASH = "/";
	private static String PATH1 = System.getProperty("user.dir")+ System.getProperty("file.separator") + "sample_datasets" + System.getProperty("file.separator");
	public static String DATA_PATH = PATH1.replace(BSLASH, FSLASH);
	
	public static void main(String[] args) {
		
		//supply path and name of active data
//		String dataFileName = DATA_PATH + "popgenTestData.csv";
		//supply path and name of text file where text output is going to be saved
		String outFilename = DATA_PATH + "GenDistance_output.txt";
		String resultFolderPath = DATA_PATH;
		
		//specify parameters
		String rDataFilename = DATA_PATH + "trialImport.RData";
		int method = 3;
		Boolean displayDiag=true;
		Boolean displayUpper=false;
		
		
		RJavaManager rJavaManager= new RJavaManager();
		rJavaManager.initPBtool();
		rJavaManager.getPbToolAnalysisManager().dogenDistance(rDataFilename, outFilename, resultFolderPath, method, displayDiag, displayUpper);
	}

}