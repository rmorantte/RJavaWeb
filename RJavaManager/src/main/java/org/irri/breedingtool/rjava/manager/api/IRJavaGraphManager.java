package org.irri.breedingtool.rjava.manager.api;

public interface IRJavaGraphManager {
	
	/**
	 * Create Graph Histogram
	 * 
	 * @param plotPath - path of the graph
	 * @param dataFileName - path and name of active data file
	 * @param respvar - 
	 */
	public void createGraphHistogram1(String plotPath,String dataFileName, String respvar);

	void createGraphBarplot(String outputPath, String dataFileName,
			String[] nVar, String cVar, String[] clustVars, String mTitle,
			String[] yAxisLab, String xAxisLab, String[] minValue,
			String[] maxValue, String typeData, String descStat,
			String barsHoriz, String barsClust, String byVar, String errBars,
			String typeErrBar, int errMult, double confLev, int axisLabelStyle,
			String[] barColor, String showLeg, String legPos, String legTitle,
			int legCol, String boxed, String multGraphs,
			int numRowsGraphs, int numColsGraphs, String orientGraphs,
			String showCatVarLevels, int[] barDensity, int[] barLineAngle);

	void createGraphLineplot(String outputPath, String dataFileName, 
			String[] yVars, String xVar, String[] lineVars, String mTitle, String[] yAxisLab, String xAxisLab, 
			String[] yMinValue, String[] yMaxValue, int axisLabelStyle, String byVar, 
			String errBars, String typeErrBar, int errMult, double confLev, 
			String[] plotCol, String showLineLabels, String showLeg, String legPos, String legTitle, int legCol, String boxed,
			String[] linePtTypes, int[] lineTypes, double[] lineWidths, String[] pointChars, double[] pointCharSizes, 
			String multGraphs, int numRowsGraphs, int numColsGraphs, String orientGraphs);
	
	void createGraphScatterplot(String outputPath, String dataFileName, 
			String[] xVar, String[] yVar, String mTitle, String[] xAxisLab, String[] yAxisLab, 
			String[] xMinValue, String[] xMaxValue, String[] yMinValue, String[] yMaxValue, int axisLabelStyle,
			String byVar, String pointCol, int pointChar, double pointCharSize,
			String dispLine, int lineType, String lineCol, double lineWidth, String dispR2P, String r2PPos, String boxed,
			String multGraphs, int numRowsGraphs, int numColsGraphs, String orientGraphs); 

	void createGraphBoxplot(String outputPath, String dataFileName, String[] nVar, String[] cVars, 
			String mTitle, String[] nAxisLab, String cAxisLab, String[] yMinValue, String[] yMaxValue, int axisLabelStyle,
			String plotHoriz, String byVar, double boxSize, String boxColor, String boxFillColor,
            int medLineType, double medLineWidth, String medColor, int whiskLineType, double whiskLineWidth,
            String whiskColor, int outChar, double outCharSize, String outColor,
            String boxed, String multGraphs, int numRowsGraphs, int numColsGraphs, String orientGraphs);

	void createGraphHist(String outputPath, String dataFileName, String[] nVar,
			String mTitle, String[] yAxisLabs, String[] xAxisLabs,
			String[] yMinValues, String[] yMaxValues, String[] xMinValues,
			String[] xMaxValues, String useFreq, String numBins,
			int axisLabelStyle, String byVar, String barColor,
			String dispCurve, int lineType, String lineColor, double lineWidth,
			String boxed, String multGraphs, int numRowsGraphs,
			int numColsGraphs, String orientGraphs);
	
	void createGraphPie(String outputPath, String dataFileName, 
			String cVar, String mTitle, String wVar, String byVar, String[] sliceColors, 
			String useLabels, String showLeg, String legPos, String legTitle, int legCol, 
			int[] pieDensity, int[] pieLineAngle, String boxed,
			String multGraphs, int numRowsGraphs, int numColsGraphs, String orientGraphs);





}
