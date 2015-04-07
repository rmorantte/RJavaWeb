package org.irri.breedingtool.rjava.pbtoolsanalysis;

import org.irri.breedingtool.rjava.manager.RJavaManager;

public class TestGenerationMeanSummaryStats {

	private static String BSLASH = "\\";
	private static String FSLASH = "/";
	private static String PATH1 = System.getProperty("user.dir")+ System.getProperty("file.separator") + "sample_datasets" + System.getProperty("file.separator");
	public static String DATA_PATH = PATH1.replace(BSLASH, FSLASH);
	
	public static void main(String[] args) {
		
		//supply path and name of active data
		String dataFileName = DATA_PATH + "GenMean(summaryStats).csv";
		//supply path and name of text file where text output is going to be saved
		String outFileName = DATA_PATH + "GMA_summarystats_output.txt";
		
		//specify parameters
		String generationMean = "mean"; 
		String weights = "NULL"; 
		String generationStandardDeviation = "stddev"; 
		String numberObservations = "n"; 
		String generation = "generation";
		String[] usersNotation = {"P1", "P2", "F1", "F2", "BC1", "BC2"}; 
		String[] generalNotation={"B0F0P1", "B0F0P2", "B0F1P0", "B0F2P0", "B1F1P1", "B1F1P2"}; 
		String alpha = "0.05";
		
		RJavaManager rJavaManager= new RJavaManager();
		rJavaManager.initPBtool();
		rJavaManager.getPbToolAnalysisManager().doGenerationMeanSummaryStats(dataFileName, outFileName, generationMean, weights, generationStandardDeviation, numberObservations, generation, usersNotation, generalNotation, alpha);
	}

}