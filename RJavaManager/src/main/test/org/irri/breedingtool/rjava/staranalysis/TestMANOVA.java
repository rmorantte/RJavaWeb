package org.irri.breedingtool.rjava.staranalysis;

import org.irri.breedingtool.rjava.manager.RJavaManager;

public class TestMANOVA {

	private static String BSLASH = "\\";
	private static String FSLASH = "/";
	private static String PATH1 = System.getProperty("user.dir")+ System.getProperty("file.separator") + "sample_datasets" + System.getProperty("file.separator");
	public static String DATA_PATH = PATH1.replace(BSLASH, FSLASH);
	
	public static void main(String[] args) {
		
//		// Case: CRD
//		//supply path and name of active data
//		String dataFileName = DATA_PATH + "grp_pssMulti.csv"; 
//		//supply path where the output will be placed
//		String outputPath = DATA_PATH; 
//
//		//specify parameters
//		String[] yVars = {"YIELD", "Yld2", "Yld3"};
//		String factorVar = "VARIETY";
//
//		String repVar = null;
//		String testStat = "Pillai";
//		String descriptive = "FALSE"; //"TRUE"; //
//		String correlate = "FALSE"; //"TRUE"; //
//		String doNormalTest = "FALSE"; //"TRUE"; //
//		String doBoxM = "FALSE"; //"TRUE"; //
//		String doSphericity = "FALSE"; //"TRUE"; //
//		String saveSSH = "FALSE"; //"TRUE"; //
//		String saveSSE = "FALSE"; //"TRUE"; //
		
		// Case: RCBD
		//supply path and name of active data
		String dataFileName = DATA_PATH + "RCB_Multi.csv"; 
		//supply path where the output will be placed
		String outputPath = DATA_PATH; 

		//specify parameters
		String[] yVars = {"Y1", "Y2"};
		String factorVar = "Gen";

		String repVar = "Blk"; //null;//
		String testStat = "Pillai";
		String descriptive = "TRUE";//"FALSE"; // 
		String correlate = "TRUE";// "FALSE"; //
		String doNormalTest = "TRUE";//"FALSE"; //
		String doBoxM = "TRUE";//"FALSE"; //
		String doSphericity = "TRUE";//"FALSE"; //
		String saveSSH = "TRUE";//"FALSE"; //
		String saveSSE = "TRUE";//"FALSE"; //
		
		
		RJavaManager rJavaManager= new RJavaManager();
		rJavaManager.initStar();
		rJavaManager.getSTARAnalysisManager().doManova(dataFileName, outputPath, yVars, factorVar, repVar, testStat, 
		descriptive, correlate, doNormalTest, doBoxM, doSphericity, saveSSH, saveSSE);
		
	}

}
