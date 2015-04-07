package org.irri.breedingtool.rjava.graph;


import org.irri.breedingtool.rjava.manager.RJavaManager;

public class TestGraphLine {

	private static String BSLASH = "\\";
	private static String FSLASH = "/";
	private static String PATH1 = System.getProperty("user.dir")+ System.getProperty("file.separator") + "sample_datasets" + System.getProperty("file.separator");
	public static String DATA_PATH = PATH1.replace(BSLASH, FSLASH);
	
	public static void main(String[] args) {
		
		//supply path and name of active data
//		String dataFileName = DATA_PATH + "gdata2.csv"; 
//		String dataFileName = DATA_PATH + "gerua_aggregate.csv";
		String dataFileName = DATA_PATH + "gerua_na.csv";
		String[] yVars = {"GRNYLD", "GYLDL500", "yldNA3"}; //{"C463", "IR5", "IR8"}; //nVar = c("C463", "IR8")
//		String[] yVars = {"C463", "IR5", "IR8"}; //nVar = c("C463", "IR8")
		String xVar = "CULT"; //"Nitrogen"; //"TPL"; // null; //
		String[] lineVars = {"TPL", "REP"};
//		String xNumeric = "FALSE"; // "TRUE"; //
		String mTitle = "ABCDEFGHIJKLMNOPQRSTUVWXYZABCDEFGHIJKLMNOPQRS"; // "The Main Title";
		String[] yAxisLab = yVars; //"y-axis label";
		String xAxisLab = "x-axis label";
		String[] yMinValue = {"NA", "NA", "NA"}; //null;//"0"; // #/NULL
		String[] yMaxValue = {"NA", "NA", "NA"}; //null;//"10000"; // #/NULL
		int axisLabelStyle = 3; 
		String byVar = null; // NULL
		String errBars = "TRUE"; //"FALSE"; //
		String typeErrBar = "stdErr"; //null; // #c(NULL, "stdErr", "confInt")
		int errMult = 2;
		double confLev = 0.95;
		String[] plotCol = {"rgb(127,0,255, max = 255)","rgb(255,0,127, max = 255)","rgb(255,127,255, max = 255)","rgb(255,0,255, max = 255)","rgb(0,255,0, max = 255)","rgb(255,0,0, max = 255)"}; //c(rgb(255,0,255, max = 255),rgb(0,255,0, max = 255),rgb(255,0,0, max = 255));
		String showLineLabels = "TRUE"; //"FALSE";
		String showLeg = "TRUE";
		String legPos = "bottomright";
		String legTitle = "The Title";
		int legCol = 1;
		String boxed = "TRUE"; //"FALSE"; //
		String[] linePtTypes = {"o", "l", "b", "o", "o", "o"}; //c("o", "o", "b")
		int[] lineTypes = {1, 1, 1, 2, 2, 2}; 
		double[] lineWidths = {1,1,1,1,1,1}; //c(1,1,1)
		String[] pointChars = {"3", "NA", "2","3","3","3"}; //{"NA", "1", "NA"}; //c(NA,1,NA)
		double[] pointCharSizes = {1,1,1,1,1,1}; //c(1,1,1)
		String multGraphs = "TRUE"; // "FALSE";
		int numRowsGraphs = 2;
		int numColsGraphs = 2;
		String orientGraphs = "top-bottom";

//		//EXAMPLE 2
//		//supply path and name of active data
//		String dataFileName = DATA_PATH + "gdata2.csv"; 
//		String[] yVars = {"C463", "IR5", "IR8"}; //nVar = c("C463", "IR8")
//		String xVar = "Nitrogen"; //"TPL"; // null; //
//		String[] lineVars = null;
////		String xNumeric = "FALSE"; // "TRUE"; //
//		String mTitle = "The Main Title";
//		String[] yAxisLab = yVars; //"y-axis label";
//		String xAxisLab = "x-axis label";
//		String[] yMinValue = {"NA", "NA", "NA"}; //null;//"0"; // #/NULL
//		String[] yMaxValue = {"NA", "NA", "NA"}; //null;//"10000"; // #/NULL
//		int axisLabelStyle = 1; 
//		String byVar = null; // NULL
//		String errBars = "FALSE"; //"TRUE"; //
//		String typeErrBar = "stdErr"; //null; // #c(NULL, "stdErr", "confInt")
//		int errMult = 2;
//		double confLev = 0.95;
//		String[] plotCol = {"rgb(127,0,255, max = 255)"}; //,"rgb(255,0,127, max = 255)","rgb(255,127,255, max = 255)","rgb(255,0,255, max = 255)","rgb(0,255,0, max = 255)","rgb(255,0,0, max = 255)"}; //c(rgb(255,0,255, max = 255),rgb(0,255,0, max = 255),rgb(255,0,0, max = 255));
//		String showLineLabels = "FALSE"; //"TRUE"; //
//		String showLeg = "FALSE";
//		String legPos = "bottomright";
//		String legTitle = "The Title";
//		int legCol = 1;
//		String boxed = "TRUE"; //"FALSE"; //
//		String[] linePtTypes = {"o"}; //, "l", "b", "o", "o", "o"}; //c("o", "o", "b")
//		int[] lineTypes = {1}; //, 1, 1, 2, 2, 2}; 
//		double[] lineWidths = {1}; //,1,1,1,1,1}; //c(1,1,1)
//		String[] pointChars = {"3"}; //, "NA", "2","3","3","3"}; //{"NA", "1", "NA"}; //c(NA,1,NA)
//		double[] pointCharSizes = {1}; //,1,1,1,1,1}; //c(1,1,1)
//		String multGraphs = "TRUE"; //"FALSE";
//		int numRowsGraphs = 3;
//		int numColsGraphs = 2;
//		String orientGraphs = "top-bottom";
		
//		//supply path and name of active data
//		String mTitle = "The Main Title";
//		String yAxisLab = "y-axis label";
//		String xAxisLab = "x-axis label";
//		String yMinValue = null;//"0"; // #/NULL
//		String yMaxValue = null;//"10000"; // #/NULL
//		String plotData = "rawData"; //"means"; // 
//		String byVar = null; //"TPL"; // NULL
//		String errBars = "FALSE"; // "TRUE"; //"FALSE"; //
//		String typeErrBar = "stdErr"; //null; // #c(NULL, "stdErr", "confInt")
//		int errMult = 2;
//		double confLev = 0.95;
//		String[] plotCol = {"rgb(255,0,0, max = 255)"}; //c(rgb(255,0,255, max = 255),rgb(0,255,0, max = 255),rgb(255,0,0, max = 255));
//		String showLineLabels = "TRUE"; //"FALSE";
//		String showLeg = "TRUE";
//		String legPos = "bottomright";
//		String legTitle = "The Title";
//		int legCol = 1;
//		String boxed = "TRUE"; //"FALSE"; //
//		String[] linePtTypes = {"b"}; //c("o", "o", "b")
//		int[] lineTypes = {1}; 
//		double[] lineWidths = {1}; //c(1,1,1)
//		String[] pointChars = {"2"}; //{"NA", "1", "NA"}; //c(NA,1,NA)
//		double[] pointCharSizes = {1}; //c(1,1,1)
//		String multGraphs = "FALSE";
//		int numRowsGraphs = 3;
//		int numColsGraphs = 1;
//		String orientGraphs = "top-bottom";
		
////		String path = System.getProperty("user.dir")+ System.getProperty("file.separator") + "sample_datasets";
		String outputPath = DATA_PATH;
		
//		System.out.println(dataFileName);
//		System.out.println(outputPath);
		
		RJavaManager rJavaManager= new RJavaManager();
		rJavaManager.initStar();
//		rJavaManager.initPBtool();

		rJavaManager.getRJavaGraphManager().createGraphLineplot(outputPath, dataFileName, yVars, xVar, lineVars,  
				mTitle, yAxisLab, xAxisLab, yMinValue, yMaxValue, axisLabelStyle, 
				byVar, errBars, typeErrBar, errMult, confLev, plotCol, showLineLabels, showLeg, legPos, legTitle, legCol, boxed,
				linePtTypes, lineTypes, lineWidths, pointChars, pointCharSizes, 
				multGraphs, numRowsGraphs, numColsGraphs, orientGraphs);
	}

}

