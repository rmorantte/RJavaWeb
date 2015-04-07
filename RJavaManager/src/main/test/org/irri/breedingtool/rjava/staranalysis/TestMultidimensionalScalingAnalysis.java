package org.irri.breedingtool.rjava.staranalysis;

import org.irri.breedingtool.rjava.manager.RJavaManager;

public class TestMultidimensionalScalingAnalysis {

	private static String BSLASH = "\\";
	private static String FSLASH = "/";
	private static String PATH1 = System.getProperty("user.dir")+ System.getProperty("file.separator") + "sample_datasets" + System.getProperty("file.separator");
	public static String DATA_PATH = PATH1.replace(BSLASH, FSLASH);
	
	public static void main(String[] args) {
		//Example 1 distance matrix
		//supply path and name of active data
		String dataFileName = DATA_PATH + "watervoles.csv"; 
		//supply path where the output will be placed
		String outputPath = DATA_PATH; 
		//specify parameters
		String inputType = "distMat"; // "raw"; //
		String[] vars = {"NULL"}; //{"SPKLT", "SPKWD", "SLLT", "SLWD", "AWLT", "AWWD", "ANLT", "STGLT", "STYLT", "FLLT", "FLWD", "LLT_2ND", "LWD_2ND", "LIGLT", "PANLT", "DIST", "PANBR", "CULT", "CUDI", "DAYS_TO_FLOWERING"};
		String idVar = "X"; //"SPECIES"; //CODE_ACCNO1";
		String type =  "Nonmetric"; // "Classical"; //
		String distClass = "Euclidean"; //"NULL";//
		String distNonmet = "Euclidean"; //"NULL";//
		int dimnum = 2;
		boolean useIdVar = false; // true; //
		int[] pChars = {1,2,3,4,5,6,7,8,9,10,11,12,13,14};
		double[] pSizes = {0.75}; //all the same
		String[] pCol = {"rgb(255,0,0,max = 255)"}; //all the same
		boolean showLeg = false; // true;
		String legTitle = "Species";
		String legPos = "bottomright";
		int legNcol = 1;
		boolean descriptive = false;
		boolean correlate = false;


		//Example 2 raw data
		//supply path and name of active data
//		String dataFileName = DATA_PATH + "PCAex.csv"; 
//		//supply path where the output will be placed
//		String outputPath = DATA_PATH; 
//		String inputType = "raw"; //"distMat"
//		String[] vars = {"SPKLT", "SPKWD", "SLLT", "SLWD", "AWLT", "AWWD", "ANLT", "STGLT", "STYLT", "FLLT", "FLWD", "LLT_2ND", "LWD_2ND", "LIGLT", "PANLT", "DIST", "PANBR", "CULT", "CUDI", "DAYS_TO_FLOWERING"};
//		String idVar = "SPECIES"; //CODE_ACCNO1";
//		String type =  "Classical"; //"Nonmetric"; //
//		String distClass = "Euclidean"; //"NULL";//
//		String distNonmet = "Euclidean"; //"NULL";//
//		int dimnum = 2;
//		boolean useIdVar = false;
//		int[] pChars = {1,2,3,4,5};
//		double[] pSizes = {0.75, 0.75, 0.75, 0.75, 0.75};
//		String[] pCol = {"rgb(255,0,0,max = 255)", "rgb(0,255,0,max = 255)", "rgb(0,0,255,max = 255)", "rgb(0,255,255,max = 255)", "rgb(127,127,127,max = 255)"};
//		boolean showLeg = true;
//		String legTitle = "Species";
//		String legPos = "bottomright";
//		int legNcol = 1;
//		boolean descriptive = true;
//		boolean correlate = true;
		
		RJavaManager rJavaManager= new RJavaManager();
		rJavaManager.initStar();
		rJavaManager.getSTARAnalysisManager().doMDS(dataFileName, outputPath,  
				inputType, vars, idVar, type,  distClass, distNonmet,  dimnum, useIdVar, pChars, pSizes, pCol, showLeg,
				legTitle, legPos, legNcol, descriptive, correlate);
	}
}
