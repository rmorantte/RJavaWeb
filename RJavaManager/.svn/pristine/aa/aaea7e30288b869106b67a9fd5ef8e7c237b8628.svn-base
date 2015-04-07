package org.irri.breedingtool.rjava.pbtoolsanalysis;

import org.irri.breedingtool.rjava.manager.RJavaManager;

public class TestGenerationMeanRawData {

	private static String BSLASH = "\\";
	private static String FSLASH = "/";
	private static String PATH1 = System.getProperty("user.dir")+ System.getProperty("file.separator") + "sample_datasets" + System.getProperty("file.separator");
	public static String DATA_PATH = PATH1.replace(BSLASH, FSLASH);
	
	public static void main(String[] args) {
		
		//supply path and name of active data
		String dataFileName = DATA_PATH + "GenMean(raw).csv";
		//supply path and name of text file where text output is going to be saved
		String outFileName = DATA_PATH + "GMA_raw_output.txt";
		
		//specify parameters
		String[] usersNotation = {"P1", "P2", "F1", "F2", "F3", "BC1", "BC2"}; 
		String[] generalNotation={"B0F0P1", "B0F0P2", "B0F1P0", "B0F2P0", "B0F3P0", "B1F1P1", "B1F1P2"}; 
		String alpha = "0.05";
		
		RJavaManager rJavaManager= new RJavaManager();
		rJavaManager.initPBtool();
		rJavaManager.getPbToolAnalysisManager().doGenerationMeanRawData(dataFileName, outFileName, usersNotation, generalNotation, alpha);
	}

}