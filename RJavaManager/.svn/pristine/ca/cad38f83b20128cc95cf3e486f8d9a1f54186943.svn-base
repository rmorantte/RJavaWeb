package org.irri.breedingtool.rjava.graph;


import org.irri.breedingtool.rjava.manager.RJavaManager;

public class TestGraphHistogram {

	private static String BSLASH = "\\";
	private static String FSLASH = "/";
	private static String PATH1 = System.getProperty("user.dir")+ System.getProperty("file.separator") + "sample_datasets" + System.getProperty("file.separator");
	public static String DATA_PATH = PATH1.replace(BSLASH, FSLASH);
	
	public static void main(String[] args) {
		
		//supply path and name of active data
 		String dataFileName = DATA_PATH + "gerua_na.csv";
		String[] nVar = {"GYLDL500", "PNCLE"}; //"DFF", "PLHT", "PNCLE", "GRNYLD"};

//		String dataFileName = DATA_PATH + "Year_Parallel.csv";
//		String[] nVar = {"GRNYLD.95", "GRNYLD.96"}; 
//		String cVar = "Nitrogen"; //null; //
		String mTitle = "The Main Title";//"The Main Title";
		String[] yAxisLabs = {"", ""};
		String[] xAxisLabs = {"GYLDL500", "PNCLE"};
		String[] yMinValues = {"NA", "NA"}; 
		String[] yMaxValues = {"NA", "NA"};
		String[] xMinValues = {"NA", "NA"}; 
		String[] xMaxValues = {"NA", "NA"};
		String useFreq = "FALSE"; //"TRUE"; // 
		String numBins = "Auto"; //for "Sturges" or integer
		int axisLabelStyle = 3;
		String byVar = "TPL"; // NULL
		String barColor = "rgb(255,0,255, max = 255, alpha = 127)"; 
		String dispCurve = "TRUE"; //"FALSE"; //
		int lineType = 1; 
		String lineColor = "rgb(0,0,0, max = 255)";
		double lineWidth = 2; //c(1,1,1)
//		String showLeg = "TRUE";
//		String legPos = "topright"; //"bottomright";
//		String legTitle = "The Title";
//		String legHoriz = "FALSE";
		String boxed = "TRUE"; //"FALSE"; //
		String multGraphs = "FALSE"; // "TRUE"; //
		int numRowsGraphs = 1;
		int numColsGraphs = 1;
		String orientGraphs = "top-bottom";

//		String path = System.getProperty("user.dir")+ System.getProperty("file.separator") + "sample_datasets";
		String outputPath = DATA_PATH;
		
//		System.out.println(dataFileName);
//		System.out.println(outputPath);
		
		RJavaManager rJavaManager= new RJavaManager();
		rJavaManager.initStar();
//		rJavaManager.initPBtool();

		rJavaManager.getRJavaGraphManager().createGraphHist(outputPath, dataFileName, nVar, mTitle,  
				yAxisLabs, xAxisLabs, yMinValues, yMaxValues, xMinValues, xMaxValues, useFreq, numBins, axisLabelStyle,  
				byVar, barColor, dispCurve, lineType, lineColor, lineWidth, 
		        boxed, multGraphs, numRowsGraphs, numColsGraphs, orientGraphs);
		
	}

}
