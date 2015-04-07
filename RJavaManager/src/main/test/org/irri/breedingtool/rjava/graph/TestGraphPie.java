package org.irri.breedingtool.rjava.graph;


import org.irri.breedingtool.rjava.manager.RJavaManager;

public class TestGraphPie {

	private static String BSLASH = "\\";
	private static String FSLASH = "/";
	private static String PATH1 = System.getProperty("user.dir")+ System.getProperty("file.separator") + "sample_datasets" + System.getProperty("file.separator");
	public static String DATA_PATH = PATH1.replace(BSLASH, FSLASH);
	
	public static void main(String[] args) {
		
		//supply path and name of active data
//		String dataFileName = DATA_PATH + "gerua_na.csv";
//		String cVar = "CULT2"; //null; //
//		String mTitle = "The Main Title";
//		String wVar = null; // "Count"; //
//		String byVar = null; //"TPL2"; // NULL
//		String[] sliceColors = {"rgb(255,0,255, max = 255)","rgb(0,255,0, max = 255)"}; //,"rgb(0,0,255, max = 255)"}; //c(rgb(255,0,255, max = 255),rgb(0,255,0, max = 255),rgb(255,0,0, max = 255));
////		String dispLabels = "TRUE"; //"FALSE"; - 
//		String useLabels = "pctcats"; // "none"; "counts"; //"pcts"; // "cats"; //"countcats";
//		String showLeg = "TRUE";
//		String legPos = "bottomright";
//		String legTitle = "The Title";
//		int legCol = 1; // "FALSE";
//		int[] pieDensity = {10, 100};
//		int[] pieLineAngle = {45, 0};
		
//		String dataFileName = DATA_PATH + "icecream2.csv"; 
		String dataFileName = DATA_PATH + "icecreamSummary2.csv";
//		String dataFileName = DATA_PATH + "gerua_na.csv";
		String cVar = "numScoops"; //null; //
		String mTitle = "The Main Title";
		String wVar = "Count"; //null; // NULL
		String byVar = "brand"; //null; // NULL
		String[] sliceColors = {"rgb(255,0,255, max = 255)","rgb(0,255,0, max = 255)","rgb(0,0,255, max = 255)"}; //c(rgb(255,0,255, max = 255),rgb(0,255,0, max = 255),rgb(255,0,0, max = 255));
//		String dispLabels = "TRUE"; //"FALSE"; - 
		String useLabels = "pctcats"; // "none"; "counts"; //"pcts"; // "cats"; //"countcats";
		String showLeg = "TRUE";
		String legPos = "bottomright";
		String legTitle = "The Title";
		int legCol = 3;
		int[] pieDensity = {10, 100, 10};
		int[] pieLineAngle = {45, 0, 90};
		String boxed = "TRUE"; //"FALSE"; //
		String multGraphs = "TRUE"; //"FALSE";
		int numRowsGraphs = 2;
		int numColsGraphs = 2;
		String orientGraphs = "top-bottom";

//		String path = System.getProperty("user.dir")+ System.getProperty("file.separator") + "sample_datasets";
		String outputPath = DATA_PATH;
		
//		System.out.println(dataFileName);
//		System.out.println(outputPath);
		
		RJavaManager rJavaManager= new RJavaManager();
		rJavaManager.initStar();
//		rJavaManager.initPBtool();

		rJavaManager.getRJavaGraphManager().createGraphPie(outputPath, dataFileName, cVar, mTitle, wVar,
                     byVar, sliceColors, useLabels, showLeg, legPos, legTitle, legCol, pieDensity, pieLineAngle, boxed,
                     multGraphs, numRowsGraphs, numColsGraphs, orientGraphs);
	}

}

