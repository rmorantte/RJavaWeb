package org.irri.breedingtool.rjava.graph;


import org.irri.breedingtool.rjava.manager.RJavaManager;

public class TestGraphScatter {

	private static String BSLASH = "\\";
	private static String FSLASH = "/";
	private static String PATH1 = System.getProperty("user.dir")+ System.getProperty("file.separator") + "sample_datasets" + System.getProperty("file.separator");
	public static String DATA_PATH = PATH1.replace(BSLASH, FSLASH);
	
	public static void main(String[] args) {

		//supply path and name of active data
		String dataFileName = DATA_PATH + "gerua_na.csv";
		String[] xVar = {"PLHT","DFF"}; 
		String[] yVar = {"GYLDL500", "yldSp"}; //{"GYLDL500", "yldSp3", "yldNA"}; 
		String mTitle = null; //"The Main Title";
		String[] xAxisLab = {"PLHT","DFF"}; //{"", ""};  //
		String[] yAxisLab = {"GYLDL500", "yldNA"}; //{"", ""};  // 
		String[] xMinValue = {"50", "0"}; //null;//"
		String[] xMaxValue = {"NA", "200"}; //null;//"
		String[] yMinValue = {"0", "0"}; //null;//"
		String[] yMaxValue = {"3500", "NA"}; //null;//"
		int axisLabelStyle = 3;
		String byVar = null; // "TPL"; //
		String pointCol = "rgb(0,255,0, max = 255)"; 
		int pointChar = 1; 
		double pointCharSize = 1; 
		String dispLine = "TRUE";
		int lineType = 1; 
		String lineCol = "rgb(0,0,0, max = 255)"; 
		double lineWidth = 1;
		String dispR2P = "TRUE";
		String r2PPos = "bottomright";
		String boxed = "TRUE"; //"FALSE"; //
		String multGraphs = "TRUE"; //"FALSE";
		int numRowsGraphs = 1;
		int numColsGraphs = 3;
		String orientGraphs = "top-bottom";

//		String path = System.getProperty("user.dir")+ System.getProperty("file.separator") + "sample_datasets";
		String outputPath = DATA_PATH;
		
//		System.out.println(dataFileName);
//		System.out.println(outputPath);
		
		RJavaManager rJavaManager= new RJavaManager();
		rJavaManager.initStar();
//		rJavaManager.initPBtool();

		rJavaManager.getRJavaGraphManager().createGraphScatterplot(outputPath, dataFileName, xVar, yVar, mTitle, xAxisLab, yAxisLab,  
				xMinValue, xMaxValue, yMinValue, yMaxValue, axisLabelStyle, 
				byVar, pointCol, pointChar, pointCharSize,
				dispLine, lineType, lineCol, lineWidth, dispR2P, r2PPos, boxed,
				multGraphs, numRowsGraphs, numColsGraphs, orientGraphs);

	}

}

