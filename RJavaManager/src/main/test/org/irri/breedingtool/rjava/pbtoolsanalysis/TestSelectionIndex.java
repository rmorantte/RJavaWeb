package org.irri.breedingtool.rjava.pbtoolsanalysis;

import org.irri.breedingtool.rjava.manager.RJavaManager;

public class TestSelectionIndex {

	private static String BSLASH = "\\";
	private static String FSLASH = "/";
	private static String PATH1 = System.getProperty("user.dir")+ System.getProperty("file.separator") + "sample_datasets" + System.getProperty("file.separator");
	public static String DATA_PATH = PATH1.replace(BSLASH, FSLASH);
	
	public static void main(String[] args) {
		
		//supply path and name of active data
		String dataFileName = DATA_PATH + "traits.csv";
		//supply path and name of text file where text output is going to be saved
		String outFileName = DATA_PATH + "SI_output.txt";
		//supply path where the graphs will be saved
		String resultFolderPath = DATA_PATH;
		//supply path and name of weights file
		String weightsFileName = DATA_PATH + "weights.csv";
		//supply path and name of markers file
//		String markersFileName = "NULL";
		String markersFileName = DATA_PATH + "markers.csv";
		//supply path and name of qtl file
//		String qtlFileName = "NULL";
		String qtlFileName = DATA_PATH + "qtl.csv";
		
		//specify parameters
		int selectionIndex = 4;
		int designIndex = 0;
		boolean basisCorrelation=true;
		int percentSelected = 5;
		
		RJavaManager rJavaManager= new RJavaManager();
		rJavaManager.initPBtool();
		rJavaManager.getPbToolAnalysisManager().doSelectionIndex(dataFileName, outFileName, resultFolderPath, selectionIndex, designIndex, basisCorrelation, weightsFileName, markersFileName,
				qtlFileName, percentSelected);
	}

}