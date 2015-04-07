package org.irri.breedingtool.rjava.pbtoolsanalysis;

import org.irri.breedingtool.rjava.manager.RJavaManager;

public class TestHWE {

	private static String BSLASH = "\\";
	private static String FSLASH = "/";
	private static String PATH1 = System.getProperty("user.dir")+ System.getProperty("file.separator") + "sample_datasets" + System.getProperty("file.separator");
	public static String DATA_PATH = PATH1.replace(BSLASH, FSLASH);
	
	public static void main(String[] args) {
		
		//supply path and name of active data
//		String dataFileName = DATA_PATH + "popgenTestData.csv";
		//supply path and name of text file where text output is going to be saved
		String outFileName = DATA_PATH + "HWE_output.txt";
		
		//specify parameters
//		String dataFileName = DATA_PATH + "popgenTestData.csv";
//		String extension = "csv"; 
//		String population = "pop"; 
//		String individual = "IND";
//		String ploidy = "2";
//		String sep = "NULL";
		
		//specify parameters
		String rDataFileName = DATA_PATH + "trialImport.RData";
		String display="matrix";
		
		RJavaManager rJavaManager= new RJavaManager();
		rJavaManager.initPBtool();
		rJavaManager.getPbToolAnalysisManager().doHWETest(rDataFileName, outFileName, display);
	}

}