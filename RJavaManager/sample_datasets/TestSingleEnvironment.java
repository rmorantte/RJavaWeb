package org.irri.breedingtool.rjava.pbtoolsanalysis;

import org.irri.breedingtool.rjava.manager.RJavaManager;

public class TestSingleEnvironment {

	private static String BSLASH = "\\";
	private static String FSLASH = "/";
	private static String PATH1 = System.getProperty("user.dir")+ System.getProperty("file.separator") + "sample_datasets" + System.getProperty("file.separator");
	public static String DATA_PATH = PATH1.replace(BSLASH, FSLASH);
	
	public static void main(String[] args) {
		
		//supply path and name of active data
		//supply path and name of text file where text output is going to be saved
		String outFileName = DATA_PATH + "SEA_output.txt";
		//supply path where the graphs will be saved
		String resultFolderPath = DATA_PATH;
		
		//specify parameters
		String dataFileName = DATA_PATH + "RCB_Multi.csv";
		int design = 0;
		String[] respvars = {"Y1", "Y2"};
		String environment = "Site";
		String[] environmentLevels = {"Env1", "Env2"};
//		String environment = "NULL";
//		String[] environmentLevels = {};
		String genotype = "Gen";
		String block = "Blk";
		String rep = "NULL";
		String row = "NULL";
		String column = "NULL";
		boolean descriptiveStat = false; 
		boolean varianceComponents = false;
		boolean boxplotRawData = true; // false; //
		boolean histogramRawData = true; // false; //
		boolean heatmapResiduals = false;
		String heatmapRow = "fieldRow";
		String heatmapColumn = "fieldColumn";
		boolean diagnosticPlot = true; // false; //
		boolean genotypeFixed = true;
		boolean performPairwise = false;
		String pairwiseAlpha = "0.05";
		String[] genotypeLevels = {"1",   "2",   "3",   "4",   "5",   "6",   "7",   "8"};
		String[] controlLevels = {"1", "2", "3"};
		boolean compareControl = false;
		boolean performAllPairwise = false;
		boolean genotypeRandom = true; // false; //
		boolean excludeControls = false;
		boolean genoPhenoCorrelation = true; // false; //
		
		
		RJavaManager rJavaManager= new RJavaManager();
		rJavaManager.initPBtool();
		rJavaManager.getPbToolAnalysisManager().doSingleEnvironmentAnalysis(dataFileName, outFileName, resultFolderPath, design, respvars, environment, environmentLevels,
				genotype, block, rep, row, column, descriptiveStat, varianceComponents, boxplotRawData, histogramRawData, heatmapResiduals, heatmapRow, 
				heatmapColumn, diagnosticPlot, genotypeFixed, performPairwise, pairwiseAlpha, genotypeLevels, controlLevels, compareControl, performAllPairwise,
				genotypeRandom, excludeControls, genoPhenoCorrelation);
	}

}