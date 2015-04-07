package org.irri.breedingtool.rjava.graph;


import org.irri.breedingtool.rjava.manager.RJavaManager;

public class TestGraphBar {

	private static String BSLASH = "\\";
	private static String FSLASH = "/";
	private static String PATH1 = System.getProperty("user.dir")+ System.getProperty("file.separator") + "sample_datasets" + System.getProperty("file.separator");
	public static String DATA_PATH = PATH1.replace(BSLASH, FSLASH);
	
	public static void main(String[] args) {
		
		//supply path and name of active data
//		String dataFileName = DATA_PATH + "Gerua_na.csv"; 
//		String[] nVar = {"GYLDL500", "GRNYLD", "yldSp3"}; //, "PLHT",  "PNCC"};
//		String cVar = "REP"; //"TPL"; //"CULT"; // 
//		String[] clustVars = null;
////		String cVar = "REP";
//		String mTitle = ""; //"The Main Title"; //null; //
//		String[] yAxisLab = nVar; //"y-axis label"; //null; // 
//		String xAxisLab = cVar; //"x-axis label"; //null; //
//		String[] minValue = {"NA", "NA", "NA"}; //"20";//
//		String[] maxValue = {"NA", "NA", "NA"}; //null; //"300";//
//		String typeData = "raw"; //"sumStat";//
//		String descStat = "mean"; //#c(NULL, "mean", "median", "sum", "freq")
//		String barsHoriz = "FALSE"; //"TRUE"; //
//		String barsClust = "TRUE"; //"FALSE"; //
//		String byVar = "TPL"; //"REP"; //null; //"CULT"; // ;//#NULL; 
//		String errBars = "TRUE"; //"FALSE"; //
//		String typeErrBar = "stdDev"; //null; // #c(NULL, "stdDev", "stdErr", "confInt")
//		int errMult = 2;
//		double confLev = 0.95;
//		int axisLabelStyle = 2;
////		String[] barColor = {"rgb(255,0,255, max = 255)","rgb(0,255,0, max = 255)"}; //{"rgb(255,0,255, max = 255)","rgb(0,255,0, max = 255)", "rgb(255,0,0, max = 255)"}; //{"rgb(255,0,255, max = 255)"}; //{"(rgb(255,0,255, max = 255)","rgb(0,255,0, max = 255))"} //{"NULL"}; //{"lightblue", "mistyrose"};
////		String[] barColor = {"rgb(128, 255, 128, max = 255)", "rgb(0, 128, 255, max = 255)", "rgb(255, 0, 128, max = 255)"}; 
//		String[] barColor = {"rgb(255, 0, 0, max = 255)", "rgb(0, 255, 0, max = 255)", "rgb(0, 0, 255, max = 255)"};
//		String showLeg = "TRUE"; // "FALSE"; //
//		String legPos = "bottomright"; //null; //eight other choices
//		String legTitle = ""; //"The Title"; //null; //
//		int legCol = 1;
//		String boxed = "FALSE"; //"TRUE"; //
//		String multGraphs = "FALSE"; //"TRUE"; //
//		int numRowsGraphs = 1;// 3;
//		int numColsGraphs = 1;
//		String orientGraphs = "top-bottom"; // #c("left-right", "top-bottom"))
//		String showCatVarLevels = "TRUE"; //"FALSE"; //
//		int[] barDensity = {100, 100, 100}; //{0,10,10,10};
//		int[] barLineAngle = {0, 0, 0};//{0,0,90,135}; //{0,45,90,135};

		String dataFileName = DATA_PATH + "gerua_aggregate.csv"; 
		String[] nVar = {"Mean.DFF", "Median.DFF", "Mean.PLHT", "Median.PLHT"};
		String cVar = "REP"; //"TPL"; //"CULT"; // 
		String[] clustVars = null;
		String mTitle = ""; //"The Main Title"; //null; //
		String[] yAxisLab = nVar; //"y-axis label"; //null; // 
		String xAxisLab = cVar; //"x-axis label"; //null; //
		String[] minValue = {"NA"}; //"20";//
		String[] maxValue = {"NA"}; //null; //"300";//
		String typeData = "sumStat";// "raw"; //
		String descStat = "mean"; //#c(NULL, "mean", "median", "sum", "freq")
		String barsHoriz = "FALSE"; //"TRUE"; //
		String barsClust = "TRUE"; //"FALSE"; //
		String byVar = null; //"REP"; //"CULT"; // ; 
		String errBars = "FALSE"; //"TRUE"; //
		String typeErrBar = "stdErr"; //null; // #c(NULL, "stdDev", "stdErr", "confInt")
		int errMult = 1;
		double confLev = 0.95;
		int axisLabelStyle = 3;
//		String[] barColor = {"rgb(255,0,255, max = 255)","rgb(0,255,0, max = 255)"}; //{"rgb(255,0,255, max = 255)","rgb(0,255,0, max = 255)", "rgb(255,0,0, max = 255)"}; //{"rgb(255,0,255, max = 255)"}; //{"(rgb(255,0,255, max = 255)","rgb(0,255,0, max = 255))"} //{"NULL"}; //{"lightblue", "mistyrose"};
//		String[] barColor = {"rgb(128, 255, 128, max = 255)", "rgb(0, 128, 255, max = 255)", "rgb(255, 0, 128, max = 255)"}; 
		String[] barColor = {"rgb(255, 0, 0, max = 255)", "rgb(0, 255, 0, max = 255)", "rgb(0, 0, 255, max = 255)"};
		String showLeg = "FALSE"; // "TRUE"; //
		String legPos = "bottomright"; //null; //eight other choices
		String legTitle = ""; //"The Title"; //null; //
		int legCol = 1;
		String boxed = "FALSE"; //"TRUE"; //
		String multGraphs = "TRUE"; // "FALSE"; //
		int numRowsGraphs =  3; //1;//
		int numColsGraphs = 1;
		String orientGraphs = "top-bottom"; // #c("left-right", "top-bottom"))
		String showCatVarLevels = "TRUE"; //"FALSE"; //
		int[] barDensity = {100, 100, 100}; //{0,10,10,10};
		int[] barLineAngle = {0, 0, 0};//{0,0,90,135}; //{0,45,90,135};

		
//		String path = System.getProperty("user.dir")+ System.getProperty("file.separator") + "sample_datasets";
		String outputPath = DATA_PATH;
		
//		System.out.println(dataFileName);
//		System.out.println(outputPath);
		
		RJavaManager rJavaManager= new RJavaManager();
		rJavaManager.initStar();
//		rJavaManager.initPBtool();

		rJavaManager.getRJavaGraphManager().createGraphBarplot(outputPath, dataFileName, nVar, cVar, clustVars, 
				mTitle, yAxisLab, xAxisLab, minValue, maxValue, typeData, descStat, 
				barsHoriz, barsClust, byVar, errBars, typeErrBar, errMult, confLev, axisLabelStyle, 
				barColor, showLeg, legPos, legTitle, legCol, boxed, 
				multGraphs, numRowsGraphs, numColsGraphs, orientGraphs, showCatVarLevels, barDensity, barLineAngle);
	}

}

