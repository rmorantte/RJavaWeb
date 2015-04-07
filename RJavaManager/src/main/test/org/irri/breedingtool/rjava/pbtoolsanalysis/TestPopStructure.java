package org.irri.breedingtool.rjava.pbtoolsanalysis;

import org.irri.breedingtool.rjava.manager.RJavaManager;

public class TestPopStructure {

	private static String BSLASH = "\\";
	private static String FSLASH = "/";
	private static String PATH1 = System.getProperty("user.dir")+ System.getProperty("file.separator") + "sample_datasets" + System.getProperty("file.separator");
	public static String DATA_PATH = PATH1.replace(BSLASH, FSLASH);
	
	public static void main(String[] args) {
		
		//supply path and name of active data
//		String dataFileName = DATA_PATH + "popgenTestData.csv";
		//supply path and name of text file where text output is going to be saved
		String outFileName = DATA_PATH + "PopStructure_output.txt";
	
		//specify parameters
		String rDataFileName = DATA_PATH + "trialImport.RData";
		Boolean pairwiseFst=true;
		
		RJavaManager rJavaManager= new RJavaManager();
		rJavaManager.initPBtool();
		rJavaManager.getPbToolAnalysisManager().dopopStructure(rDataFileName, outFileName, pairwiseFst);
	}

}