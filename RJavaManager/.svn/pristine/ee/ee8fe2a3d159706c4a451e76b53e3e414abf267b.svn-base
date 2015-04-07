package org.irri.breedingtool.rjava.staranalysis;

import org.irri.breedingtool.rjava.manager.RJavaManager;

public class TestDiscriminant {

	private static String BSLASH = "\\";
	private static String FSLASH = "/";
	private static String PATH1 = System.getProperty("user.dir")+ System.getProperty("file.separator") + "sample_datasets" + System.getProperty("file.separator");
	public static String DATA_PATH = PATH1.replace(BSLASH, FSLASH);
	
	public static void main(String[] args) {

		//supply path and name of active data
		String dataFileName = DATA_PATH + "fishSAS_sorted.csv";
		String dataFileName2 = DATA_PATH + "fishTest2.csv";
		//supply path where the output will be placed
		String outputPath = DATA_PATH; 

		//specify parameters
		String[] vars = {"Weight", "Length1", "Length2", "Length3", "Height", "Width"};
		String grpVar = "Species";

		boolean descriptive = true; // false; // 
		boolean doNormalTest = true; // false; //
		boolean doBoxM = true; // false; //
		String priorProb = "c(1,1,1,1,1,1,1)/7"; // NULL 
		boolean classifyNew = true; // false; //
		
		RJavaManager rJavaManager= new RJavaManager();
		rJavaManager.initStar();
		rJavaManager.getSTARAnalysisManager().doDiscriminant(dataFileName, outputPath, vars, grpVar,  
		descriptive, doNormalTest, doBoxM, priorProb, classifyNew, dataFileName2);
		
		//Check: names of variables in "vars" should be in colnames of data2
	}

}
