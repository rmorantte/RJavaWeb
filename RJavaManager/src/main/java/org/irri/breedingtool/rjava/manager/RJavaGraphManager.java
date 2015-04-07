package org.irri.breedingtool.rjava.manager;

import org.irri.breedingtool.rjava.manager.api.IRJavaGraphManager;
import org.irri.breedingtool.rjava.utilities.InputTransform;
import org.rosuda.JRI.Rengine;

public class RJavaGraphManager implements IRJavaGraphManager{
	
	private Rengine rEngine;
	private InputTransform inputTransform;

	public RJavaGraphManager(Rengine rEngine) {
		this.rEngine = rEngine;
		this.inputTransform= new InputTransform();

	}

	private void rEngineEnd(){
		String rm = "rm(list=ls(all=TRUE))";
		rEngine.eval(rm);
		rEngine.end();
	}

	@Override
	public void createGraphHistogram1(String plotPath,String dataFileName, String respvar) {

		String graphHist = "g <- tryCatch(CreateGraphHistogram(\"" + plotPath + "\", dataName = \"" + dataFileName + "\", respvar = \"" + respvar + "\"), error = function(err) \"notRun\")";
		rEngine.eval(graphHist);

		System.out.println(plotPath);
		System.out.println("graphHist: " + graphHist);

		//for checking if the R function CreateGraphHistogram encountered an error (use of "tryCatch" in the statement above)
		//will display "notRun" if an error is encountered, "null" otherwise  
		String runSuccessG = rEngine.eval("g").asString();
		System.out.println("runSuccessG: " + runSuccessG);
	}

	@Override
	public void createGraphBarplot(String outputPath, String dataFileName, String[] nVar, String cVar, String[] clustVars, String mTitle, String[] yAxisLab, 
			String xAxisLab, String[] minValue, String[] maxValue, String typeData, String descStat, String barsHoriz, String barsClust,
			String byVar, String errBars, String typeErrBar, int errMult, double confLev, int axisLabelStyle, String[] barColor, String showLeg,
			String legPos, String legTitle, int legCol, String boxed, String multGraphs, int numRowsGraphs, int numColsGraphs, 
			String orientGraphs, String showCatVarLevels, int[] barDensity, int[] barLineAngle){

		String nVarVector= inputTransform.createRVector(nVar);
		String barDensityVector= inputTransform.createRNumVector(barDensity);
		String barLineAngleVector= inputTransform.createRNumVector(barLineAngle);

		String yAxisLabVector = inputTransform.createRVector(yAxisLab);
		String minValueVector = inputTransform.createRNumVector(minValue);
		String maxValueVector = inputTransform.createRNumVector(maxValue);

		String clustVarsVector;  
		if (clustVars == null) clustVarsVector = "NULL"; else clustVarsVector = inputTransform.createRVector(clustVars);
		if (mTitle == null) mTitle = "NULL"; else mTitle = "\"" + mTitle + "\"";
		if (xAxisLab == null) xAxisLab = "NULL"; else xAxisLab = "\"" + xAxisLab + "\"";
		if (descStat == null) descStat = "NULL"; else descStat = "\"" + descStat + "\"";
		if (byVar == null) byVar = "NULL"; else byVar = "\"" + byVar + "\"";
		if (typeErrBar == null) typeErrBar = "NULL"; else typeErrBar = "\"" + typeErrBar + "\"";
		if (legTitle == null) legTitle = "NULL"; else legTitle = "\"" + legTitle + "\"";
		
		//barColor is an array of "R vectors" representing RGB values - will have default color
		//for example: barColor = {rgb(0,0,255,max = 255),rgb(0,255,150,max=255)}
		String barColorVector = null;
		if (!barColor[0].equals("NULL")) barColorVector = inputTransform.createRColorRGBVector(barColor);
		else barColorVector = barColor[0];
		
//	  	String sourceRun = "source(\"E:/App Files/workspace_Juno/RJavaManager/rscript/Graph_bar.R\")";
		String graphBar = "g <- try(GraphBar(data, \"" + outputPath + "\", " + nVarVector + ", \"" + cVar + "\", " + clustVarsVector + ", " + mTitle + ", " + 
				yAxisLabVector + ", " + xAxisLab + ", " + minValueVector + ", " + maxValueVector + ", \"" + typeData + "\", " + descStat + ", " + 
				barsHoriz + ", " + barsClust + ", " + byVar + ", " + 
				errBars + ", " + typeErrBar + ", " + errMult + ", " + confLev + ", " + axisLabelStyle + ", " + barColorVector + ", " +
				showLeg + ", \"" + legPos + "\", " + legTitle + ", " + legCol + ", " + boxed + ", " + multGraphs + 
				", " + numRowsGraphs + ", " + numColsGraphs + ", \"" + orientGraphs + "\" , " + 
				showCatVarLevels + ", " + barDensityVector + ", " + barLineAngleVector + "), silent = TRUE)";

//		System.out.println(sourceRun);
		System.out.println(graphBar);

		//R statements passed on to the R engine
//		rEngine.eval(sourceRun);
		readData(dataFileName);
		rEngine.eval(graphBar);

		String runSuccessCommand = rEngine.eval("class(g)").asString();
		if (runSuccessCommand.equals("try-error")) {
		  	String outFilePath = outputPath + "GraphBar_error.txt";
			String sinkIn = "sink(\"" + outFilePath + "\")";
			String sinkOut = "sink()";
			String checkError = "if (class(g) == \"try-error\") {\n";
			checkError = checkError + "    msg <- trim.strings(strsplit(g, \":\")[[1]])\n";
			checkError = checkError + "    msg <- trim.strings(paste(strsplit(msg, \"\\n\")[[length(msg)]], collapse = \" \"))\n";
			checkError = checkError + "    msg <- gsub(\"\\\"\", \"\", msg)\n";
			checkError = checkError + "    cat(\"\n*** \nERROR in GraphBar function:\\n  \",msg, \"\n***\", sep = \"\")\n";
			checkError = checkError + "}";
			
			System.out.println(sinkIn);
			System.out.println(checkError);
			System.out.println(sinkOut);
				
			rEngine.eval(sinkIn);
			rEngine.eval(checkError);
			rEngine.eval(sinkOut);
		}

		rEngineEnd();
	}

	@Override
	public void createGraphLineplot(String outputPath, String dataFileName, 
				String[] yVars, String xVar, String[] lineVars, String mTitle, String[] yAxisLab, String xAxisLab, 
				String[] yMinValue, String[] yMaxValue, int axisLabelStyle, String byVar, 
				String errBars, String typeErrBar, int errMult, double confLev, 
				String[] plotCol, String showLineLabels, String showLeg, String legPos, String legTitle, int legCol, String boxed,
				String[] linePtTypes, int[] lineTypes, double[] lineWidths, String[] pointChars, double[] pointCharSizes, 
				String multGraphs, int numRowsGraphs, int numColsGraphs, String orientGraphs) {

		String yVarsVector= inputTransform.createRVector(yVars);
		String yAxisLabVector = inputTransform.createRVector(yAxisLab);
		String yMinValueVector = inputTransform.createRNumVector(yMinValue);
		String yMaxValueVector = inputTransform.createRNumVector(yMaxValue);
		String linePtTypesVector= inputTransform.createRVector(linePtTypes);
		String lineTypesVector= inputTransform.createRNumVector(lineTypes);
		String lineWidthsVector= inputTransform.createRNumVector(lineWidths);
		String pointCharsVector= inputTransform.createRNumVector(pointChars);
		String pointCharSizesVector= inputTransform.createRNumVector(pointCharSizes);
		
		String lineVarsVector;  
		if (lineVars == null) lineVarsVector = "NULL"; else lineVarsVector = inputTransform.createRVector(lineVars);
		if (mTitle == null) mTitle = "NULL"; else mTitle = "\"" + mTitle + "\"";
		if (xAxisLab == null) xAxisLab = "NULL"; else xAxisLab = "\"" + xAxisLab + "\"";
		if (xVar == null) xVar = "NULL"; else xVar = "\"" + xVar + "\"";
		if (byVar == null) byVar = "NULL"; else byVar = "\"" + byVar + "\"";
		if (typeErrBar == null) typeErrBar = "NULL"; else typeErrBar = "\"" + typeErrBar + "\"";
		if (legTitle == null) legTitle = "NULL"; else legTitle = "\"" + legTitle + "\"";
		
		//plotCol is an array of "R vectors" representing RGB values - will have default color
		//for example: plotColor = {rgb(0,0,255,max = 255),rgb(0,255,150,max=255)}
		String plotColVector = null;
		if (!plotCol[0].equals("NULL")) plotColVector = inputTransform.createRColorRGBVector(plotCol);
		else plotColVector = plotCol[0];
		
//	  	String sourceRun = "source(\"E:/App Files/workspace_Juno/RJavaManager/rscript/Graph_line.R\")";
		String graphLine = "g <- try(GraphLine(data, \"" + outputPath + "\", " + yVarsVector + ", " + xVar + ", " + lineVarsVector + ", " +
				mTitle + ", " + yAxisLabVector + ", " + xAxisLab + ", " + yMinValueVector + ", " + yMaxValueVector + ", " + axisLabelStyle + ", " + 
				byVar + ", " + errBars + ", " + typeErrBar + ", " + errMult + ", " + confLev + ", " + plotColVector + ", " + 
				showLineLabels + ", " + showLeg + ", \"" + legPos + "\", " + legTitle + ", " + legCol + ", " + boxed + ", " + 
				linePtTypesVector + ", " + lineTypesVector + ", " + lineWidthsVector + ", " +  
				pointCharsVector + ", " + pointCharSizesVector + ", " + 
				multGraphs + ", " + numRowsGraphs + ", " + numColsGraphs + ", \"" + orientGraphs + "\"), silent = TRUE)";
		
//		System.out.println(sourceRun);
		System.out.println(graphLine);

		//R statements passed on to the R engine
//		rEngine.eval(sourceRun);
		readData(dataFileName);
		rEngine.eval(graphLine);


		String runSuccessCommand = rEngine.eval("class(g)").asString();
		if (runSuccessCommand.equals("try-error")) {
		  	String outFilePath = outputPath + "GraphLine_error.txt";
			String sinkIn = "sink(\"" + outFilePath + "\")";
			String sinkOut = "sink()";
			String checkError = "if (class(g) == \"try-error\") {\n";
			checkError = checkError + "    msg <- trim.strings(strsplit(g, \":\")[[1]])\n";
			checkError = checkError + "    msg <- trim.strings(paste(strsplit(msg, \"\\n\")[[length(msg)]], collapse = \" \"))\n";
			checkError = checkError + "    msg <- gsub(\"\\\"\", \"\", msg)\n";
			checkError = checkError + "    cat(\"\n*** \nERROR in GraphLine function:\\n  \",msg, \"\n***\", sep = \"\")\n";
			checkError = checkError + "}";
			
			System.out.println(sinkIn);
			System.out.println(checkError);
			System.out.println(sinkOut);
				
			rEngine.eval(sinkIn);
			rEngine.eval(checkError);
			rEngine.eval(sinkOut);
		}

		rEngineEnd();
	}

	@Override
	public void createGraphScatterplot(String outputPath, String dataFileName, 
				String[] xVar, String[] yVar, String mTitle, String[] xAxisLab, String[] yAxisLab, 
				String[] xMinValue, String[] xMaxValue, String[] yMinValue, String[] yMaxValue, int axisLabelStyle,
				String byVar, String pointCol, int pointChar, double pointCharSize,
				String dispLine, int lineType, String lineCol, double lineWidth, String dispR2P, String r2PPos, String boxed,
				String multGraphs, int numRowsGraphs, int numColsGraphs, String orientGraphs) {
		
		String xVarVector = inputTransform.createRVector(xVar);
		String yVarVector = inputTransform.createRVector(yVar);
		String xAxisLabVector = inputTransform.createRVector(xAxisLab);
		String yAxisLabVector = inputTransform.createRVector(yAxisLab);
		String xMinValueVector = inputTransform.createRNumVector(xMinValue);
		String xMaxValueVector = inputTransform.createRNumVector(xMaxValue);
		String yMinValueVector = inputTransform.createRNumVector(yMinValue);
		String yMaxValueVector = inputTransform.createRNumVector(yMaxValue);
		
		if (mTitle == null) mTitle = "NULL"; else mTitle = "\"" + mTitle + "\"";
		if (byVar == null) byVar = "NULL"; else byVar = "\"" + byVar + "\"";
		
//	  	String sourceRun = "source(\"E:/App Files/workspace_Juno/RJavaManager/rscript/Graph_scatter.R\")";
		String graphScatter = "g <- try(GraphScatter(data, \"" + outputPath + "\", " + xVarVector + ", " + yVarVector + ", " + mTitle + ", " + 
				xAxisLabVector + ", " + yAxisLabVector + ", " + xMinValueVector + ", " + xMaxValueVector + ", " + yMinValueVector + ", " + yMaxValueVector + ", " + 
				axisLabelStyle + ", " + byVar + ", " + boxed + ", " +  pointCol + ", " +  pointChar + ", " + pointCharSize + ", " + 
				dispLine + ", " + lineType + ", " + lineCol + ", " + lineWidth + ", " +
				dispR2P + ", \"" + r2PPos + "\", " + multGraphs + ", " + numRowsGraphs + ", " + numColsGraphs + ", \"" + orientGraphs + "\"), silent = TRUE)";
		
//		System.out.println(sourceRun);
		System.out.println(graphScatter);
	
		//R statements passed on to the R engine
//		rEngine.eval(sourceRun);
		readData(dataFileName);
		rEngine.eval(graphScatter);

		String runSuccessCommand = rEngine.eval("class(g)").asString();
		if (runSuccessCommand.equals("try-error")) {
			String outFilePath = outputPath + "GraphScatter_error.txt";
			String sinkIn = "sink(\"" + outFilePath + "\")";
			String sinkOut = "sink()";
			String checkError = "if (class(g) == \"try-error\") {\n";
			checkError = checkError + "    msg <- trim.strings(strsplit(g, \":\")[[1]])\n";
			checkError = checkError + "    msg <- trim.strings(paste(strsplit(msg, \"\\n\")[[length(msg)]], collapse = \" \"))\n";
			checkError = checkError + "    msg <- gsub(\"\\\"\", \"\", msg)\n";
			checkError = checkError + "    cat(\"\n*** \nERROR in GraphScatter function:\\n  \",msg, \"\n***\", sep = \"\")\n";
			checkError = checkError + "}";
			
			System.out.println(sinkIn);
			System.out.println(checkError);
			System.out.println(sinkOut);
				
			rEngine.eval(sinkIn);
			rEngine.eval(checkError);
			rEngine.eval(sinkOut);
		}
	  	
		rEngineEnd();
	}
	
	@Override
	public void createGraphBoxplot(String outputPath, String dataFileName, String[] nVar, String[] cVars, 
				String mTitle, String[] nAxisLab, String cAxisLab, String[] yMinValue, String[] yMaxValue, 
				int axisLabelStyle, String plotHoriz, String byVar, double boxSize, String boxColor, String boxFillColor,
	            int medLineType, double medLineWidth, String medColor, int whiskLineType, double whiskLineWidth,
	            String whiskColor, int outChar, double outCharSize, String outColor,
	            String boxed, String multGraphs, int numRowsGraphs, int numColsGraphs, String orientGraphs) {
		
		if (mTitle == null) mTitle = "NULL"; else mTitle = "\"" + mTitle + "\"";
		if (cAxisLab == null) cAxisLab = "NULL"; else cAxisLab = "\"" + cAxisLab + "\"";
		if (byVar == null) byVar = "NULL"; else byVar = "\"" + byVar + "\"";
		
		String cVarsVector;  
		if (cVars == null) cVarsVector = "NULL"; else cVarsVector = inputTransform.createRVector(cVars);
		String nVarVector = inputTransform.createRVector(nVar);
		String nAxisLabVector = inputTransform.createRVector(nAxisLab);
		String yMinValueVector = inputTransform.createRNumVector(yMinValue);
		String yMaxValueVector = inputTransform.createRNumVector(yMaxValue);

//	  	String sourceRun = "source(\"E:/App Files/workspace_Juno/RJavaManager/rscript/Graph_boxplot.R\")";
		String graphBox = "g <- try(GraphBoxplot(data, \"" + outputPath + "\", " + nVarVector + ", " + cVarsVector + ", " + mTitle + ", " + 
				nAxisLabVector + ", " + cAxisLab + ", " + yMinValueVector + ", " + yMaxValueVector + ", " + axisLabelStyle + ", " + 
				plotHoriz + ", " + byVar + ", " + boxSize + ", " + boxColor + ", " + 
				boxFillColor + ", " + medLineType + ", " + medLineWidth + ", " + medColor + ", " + whiskLineType + ", " + whiskLineWidth + ", " + 
	            whiskColor + ", " + outChar + ", " + outCharSize + ", " + outColor + ", " + 
				boxed + ", " + multGraphs + ", " + numRowsGraphs + ", " + numColsGraphs + ", \"" + orientGraphs + "\"), silent = TRUE)";
		
//		System.out.println(sourceRun);
		System.out.println(graphBox);

		//R statements passed on to the R engine
//		rEngine.eval(sourceRun);
		readData(dataFileName);
		rEngine.eval(graphBox);

		String runSuccessCommand = rEngine.eval("class(g)").asString();
		if (runSuccessCommand.equals("try-error")) {
		  	String outFilePath = outputPath + "GraphBoxplot_error.txt";
			String sinkIn = "sink(\"" + outFilePath + "\")";
			String sinkOut = "sink()";
			String checkError = "if (class(g) == \"try-error\") {\n";
			checkError = checkError + "    msg <- trim.strings(strsplit(g, \":\")[[1]])\n";
			checkError = checkError + "    msg <- trim.strings(paste(strsplit(msg, \"\\n\")[[length(msg)]], collapse = \" \"))\n";
			checkError = checkError + "    msg <- gsub(\"\\\"\", \"\", msg)\n";
			checkError = checkError + "    cat(\"\n*** \nERROR in GraphBoxplot function:\\n  \",msg, \"\n***\", sep = \"\")\n";
			checkError = checkError + "}";
			
			System.out.println(sinkIn);
			System.out.println(checkError);
			System.out.println(sinkOut);
				
			rEngine.eval(sinkIn);
			rEngine.eval(checkError);
			rEngine.eval(sinkOut);
		}

		rEngineEnd();
	}
	
	@Override
	public void createGraphHist(String outputPath, String dataFileName, 
				String[] nVar, String mTitle, String[] yAxisLabs, String[] xAxisLabs, 
				String[] yMinValues, String[] yMaxValues, String[] xMinValues, String[] xMaxValues,
				String useFreq, String numBins, int axisLabelStyle, String byVar, String barColor, 
				String dispCurve, int lineType, String lineColor, double lineWidth, String boxed,
				String multGraphs, int numRowsGraphs, int numColsGraphs, String orientGraphs) {
		
		String nVarVector= inputTransform.createRVector(nVar);
		String yAxisLabsVector = inputTransform.createRVector(yAxisLabs);
		String xAxisLabsVector = inputTransform.createRVector(xAxisLabs);
		String yMinValuesVector = inputTransform.createRNumVector(yMinValues);
		String yMaxValuesVector = inputTransform.createRNumVector(yMaxValues);
		String xMinValuesVector = inputTransform.createRNumVector(xMinValues);
		String xMaxValuesVector = inputTransform.createRNumVector(xMaxValues);

		if (mTitle == null) mTitle = "NULL"; else mTitle = "\"" + mTitle + "\"";
		if (byVar == null) byVar = "NULL"; else byVar = "\"" + byVar + "\"";
		if (numBins.equals("Auto")) numBins = "\"Sturges\"";
		
		//barColor contains RGB values
		//for example: barColor = {rgb(0,0,255,max = 255)}

//	  	String sourceRun = "source(\"E:/App Files/workspace_Juno/RJavaManager/rscript/Graph_hist.R\")";
		String graphHist = "g <- try(GraphHist(data, \"" + outputPath + "\", " + nVarVector + ", " + mTitle + ", " + 
				yAxisLabsVector + ", " + xAxisLabsVector + ", " + yMinValuesVector + ", " + yMaxValuesVector + ", " + xMinValuesVector + ", " + xMaxValuesVector + ", " + 
				useFreq + ", " + numBins + ", " + axisLabelStyle + ", " + byVar + ", " + barColor + ", " + dispCurve + ", " + 
				lineType + ", " + lineColor + ", " + lineWidth + ", " +	boxed + ", " +   
				multGraphs + ", " + numRowsGraphs + ", " + numColsGraphs + ", \"" + orientGraphs + "\"), silent = TRUE)";
		
//		System.out.println(sourceRun);
		System.out.println(graphHist);

		//R statements passed on to the R engine
//		rEngine.eval(sourceRun);
		readData(dataFileName);
		rEngine.eval(graphHist);

		String runSuccessCommand = rEngine.eval("class(g)").asString();
		if (runSuccessCommand.equals("try-error")) {
		  	String outFilePath = outputPath + "GraphHist_error.txt";
			String sinkIn = "sink(\"" + outFilePath + "\")";
			String sinkOut = "sink()";
			String checkError = "if (class(g) == \"try-error\") {\n";
			checkError = checkError + "    msg <- trim.strings(strsplit(g, \":\")[[1]])\n";
			checkError = checkError + "    msg <- trim.strings(paste(strsplit(msg, \"\\n\")[[length(msg)]], collapse = \" \"))\n";
			checkError = checkError + "    msg <- gsub(\"\\\"\", \"\", msg)\n";
			checkError = checkError + "    cat(\"\n*** \nERROR in GraphHist function:\\n  \",msg, \"\n***\", sep = \"\")\n";
			checkError = checkError + "}";
			
			System.out.println(sinkIn);
			System.out.println(checkError);
			System.out.println(sinkOut);
				
			rEngine.eval(sinkIn);
			rEngine.eval(checkError);
			rEngine.eval(sinkOut);
		}

		rEngineEnd();
	}

	@Override
	public void createGraphPie(String outputPath, String dataFileName, 
				String cVar, String mTitle, String wVar, String byVar, String[] sliceColors, 
				String useLabels, String showLeg, String legPos, String legTitle, int legCol,  
				int[] pieDensity, int[] pieLineAngle, String boxed,
				String multGraphs, int numRowsGraphs, int numColsGraphs, String orientGraphs) {

		if (mTitle == null) mTitle = "NULL"; else mTitle = "\"" + mTitle + "\"";
		if (wVar == null) wVar = "NULL"; else wVar = "\"" + wVar + "\"";
		if (byVar == null) byVar = "NULL"; else byVar = "\"" + byVar + "\"";
		if (legTitle == null) legTitle = "NULL"; else legTitle = "\"" + legTitle + "\"";

		String pieDensityVector= inputTransform.createRNumVector(pieDensity);
		String pieLineAngleVector= inputTransform.createRNumVector(pieLineAngle);
		
		String dispLabels;
		
		if (useLabels.equals("none")) dispLabels = "FALSE"; else dispLabels = "TRUE";
		
		//sliceColors is an array of "R vectors" representing RGB values - will have default color
		//for example: sliceColors = {rgb(0,0,255,max = 255),rgb(0,255,150,max=255)}
		String sliceColorsVector = null;
		if (!sliceColors[0].equals("NULL")) sliceColorsVector = inputTransform.createRColorRGBVector(sliceColors);
		else sliceColorsVector = sliceColors[0];

//	  	String sourceRun = "source(\"E:/App Files/workspace_Juno/RJavaManager/rscript/Graph_pie.R\")";
		String graphPie = "g <- try(GraphPie(data, \"" + outputPath + "\", \"" + cVar + "\", " + mTitle + ", " + 
				wVar + ", " + byVar + ", " + sliceColorsVector + ", " + dispLabels + ", \"" + useLabels + "\", " +  
				showLeg + ", \"" + legPos + "\", " + legTitle + ", " + legCol + ", " + pieDensityVector + ", " + pieLineAngleVector + ", " + 
				boxed + ", " + multGraphs + ", " + numRowsGraphs + ", " + numColsGraphs + ", \"" + orientGraphs + "\"), silent = TRUE)";
		
//		System.out.println(sourceRun);
		System.out.println(graphPie);

		//R statements passed on to the R engine
//		rEngine.eval(sourceRun);
		readData(dataFileName);
		rEngine.eval(graphPie);

		String runSuccessCommand = rEngine.eval("class(g)").asString();
		if (runSuccessCommand.equals("try-error")) {
			String outFilePath = outputPath + "GraphPie_error.txt";
			String sinkIn = "sink(\"" + outFilePath + "\")";
			String sinkOut = "sink()";
			String checkError = "if (class(g) == \"try-error\") {\n";
			checkError = checkError + "    msg <- trim.strings(strsplit(g, \":\")[[1]])\n";
			checkError = checkError + "    msg <- trim.strings(paste(strsplit(msg, \"\\n\")[[length(msg)]], collapse = \" \"))\n";
			checkError = checkError + "    msg <- gsub(\"\\\"\", \"\", msg)\n";
			checkError = checkError + "    cat(\"\n*** \nERROR in GraphPie function:\\n  \",msg, \"\n***\", sep = \"\")\n";
			checkError = checkError + "}";
			
			System.out.println(sinkIn);
			System.out.println(checkError);
			System.out.println(sinkOut);
				
			rEngine.eval(sinkIn);
			rEngine.eval(checkError);
			rEngine.eval(sinkOut);
		}
	  	
		rEngineEnd();
	}
	
	private void readData(String dataFileName){
		String readData = "data <- read.csv(\"" + dataFileName + "\", header = TRUE, na.strings = c(\"NA\",\".\",\"\"), blank.lines.skip=TRUE, sep = \",\")";
		System.out.println(readData);
		rEngine.eval(readData);
	}
	
}
