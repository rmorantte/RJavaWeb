package org.irri.breedingtool.rjava.graph;


import org.irri.breedingtool.rjava.manager.RJavaManager;

public class TestGraphBoxplot {

	private static String BSLASH = "\\";
	private static String FSLASH = "/";
	private static String PATH1 = System.getProperty("user.dir")+ System.getProperty("file.separator") + "sample_datasets" + System.getProperty("file.separator");
	public static String DATA_PATH = PATH1.replace(BSLASH, FSLASH);
	
	public static void main(String[] args) {
		
		//supply path and name of active data
		String dataFileName = DATA_PATH + "gerua_na.csv";
		String[] nVar = {"GYLDL500", "yldSp3", "yldSp"}; //"DFF", "PLHT", "PNCLE", "GRNYLD"};
		String[] cVars = {"TPL", "REP"}; //"CULT"; //"REP"; //null; //
		String mTitle = null; // "The Main Title";
		String[] nAxisLab = nVar; // null; // "y-axis label";
		String cAxisLab = null; // ""; //cVar;// "Rep"; // null; // "x-axis label";
		String[] yMinValue = {"NA", "NA", "NA"}; //{"100", "NA", "100","0"};  
		String[] yMaxValue = {"NA", "NA", "NA"}; //{"160", "180", "NA","7000"};  
		int axisLabelStyle = 3;
		String plotHoriz = "FALSE"; // "TRUE" ; //
		String byVar = null; //"TPL"; // NULL
        double boxSize = 0.8;
        String boxColor = "rgb(255,0,255, max = 255)";
        String boxFillColor = "rgb(255,0,255, max = 255)";
        int medLineType = 1;
        double medLineWidth = 3;
        String medColor = "rgb(255,255,255, max = 255)"; 
        int whiskLineType = 2;
        double whiskLineWidth = 1;
        String whiskColor = "rgb(255,0,255, max = 255)";
        int outChar = 1;
        double outCharSize = 1;
        String outColor = "rgb(255,0,255, max = 255)";
		String boxed = "TRUE"; //"FALSE"; //
		String multGraphs = "FALSE"; //"TRUE"; //
		int numRowsGraphs = 3;//2;
		int numColsGraphs = 1;//4;
		String orientGraphs = "left-right"; //"top-bottom";

//		String path = System.getProperty("user.dir")+ System.getProperty("file.separator") + "sample_datasets";
		String outputPath = DATA_PATH;
		
//		System.out.println(dataFileName);
//		System.out.println(outputPath);
		
		RJavaManager rJavaManager= new RJavaManager();
		rJavaManager.initStar();
//		rJavaManager.initPBtool();

		rJavaManager.getRJavaGraphManager().createGraphBoxplot(outputPath, dataFileName, nVar, cVars, 
				mTitle, nAxisLab, cAxisLab, yMinValue, yMaxValue, axisLabelStyle, plotHoriz, byVar, 
				boxSize, boxColor, boxFillColor,
	            medLineType, medLineWidth, medColor, whiskLineType, whiskLineWidth,
	            whiskColor, outChar, outCharSize, outColor,
	            boxed, multGraphs, numRowsGraphs, numColsGraphs, orientGraphs);
				
	}

}

