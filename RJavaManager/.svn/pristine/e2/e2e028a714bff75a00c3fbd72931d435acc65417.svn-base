package org.irri.breedingtool.rjava.pbtoolsanalysis;

import org.irri.breedingtool.rjava.manager.RJavaManager;

public class TestMultiEnvironmentSecondStage {

	private static String BSLASH = "\\";
	private static String FSLASH = "/";
	private static String PATH1 = System.getProperty("user.dir")+ System.getProperty("file.separator") + "sample_datasets" + System.getProperty("file.separator");
	public static String DATA_PATH = PATH1.replace(BSLASH, FSLASH);
	
	public static void main(String[] args) {
		
		//supply path and name of active data
//		String dataFileName = DATA_PATH + "summaryStats2_b.csv";
		//supply path and name of text file where text output is going to be saved
		String outFileName = DATA_PATH + "MEASecondStage_output.txt";
		//supply path where the graphs will be saved
		String resultFolderPath = DATA_PATH;
//		
		//specify parameters
//		String weightOption = "none";
////		String[] respvar = {"Plot.Yield_Mean", "Y3_Mean"};
////		String[] standardErrors = {"Plot.Yield_StdErrMean", "Y3_StdErrMean"};
////		String[] residualVariances = {"Plot.Yield_sigma2", "Y3_sigma2"};
////		String[] numberOfReps = {"Plot.Yield_No.rep", "Y3_No.rep"};
//		String[] respvar = {"Y3_Mean", "Plot.Yield_Mean"};
//		String[] standardErrors = {"Y3_StdErrMean", "Plot.Yield_StdErrMean"};
//		String[] residualVariances = {"Y3_sigma2", "Plot.Yield_sigma2"};
//		String[] numberOfReps = {"Y3_No.rep", "Plot.Yield_No.rep"};
////		String[] respvar = {"Y3_Mean"};
////		String[] standardErrors = {"Y3_StdErrMean"};
////		String[] residualVariances = {"Y3_sigma2"};
////		String[] numberOfReps = {"Y3_No.rep"};
//		String environment = "Set";
//		String[] environmentLevels = {"10", "6", "7", "8", "9"};
//		String genotype = "EntryNo";
//		boolean descriptiveStat = true; 
//		boolean varianceComponents = true;
//		boolean boxplotRawData = true;
//		boolean histogramRawData = true;
//		boolean diagnosticPlot = true;
//		boolean genotypeFixed = true;
//		boolean performPairwise = false;
//		String pairwiseAlpha = "0.05";
//		String[] genotypeLevels = {"1",   "2",   "3",   "4",   "5",   "6",   "7",   "8",   "9",   "10",  "11",  "12",  "13",  "14",  "15",  "16",  "17",  "18",  "19",  "20",  "21",  "22",  "23",  "24",  "25",  "26",  "27",  "28",  "29",  "30",  "31",  "32",  "33",  "34",  "35",  "36",  "37",  "38",  "39",  "40",  "41",  "42",  "43",  "44",  "45",  "46",  "47",  "48",  "49",  "50",  "51",  "52",  "53",  "54",  "55",  "56",  "57",  "58",  "59",  "60",  "61",  "62",  "63",  "64",  "65",  "66",  "67",  "68",  "69",  "70",  "71",  "72",  "73",  "74",  "75",  "76",  "77",  "78",  "79",  "80",  "81",  "82",  "83",  "84",  "85",  "86",  "87",  "88",  "89",  "90",  "91",  "92",  "93",  "94",  "95",  "96",  "97",  "98",  "99", "100"};
//		String[] controlLevels = {"4",   "5",   "6"};
//		boolean compareControl = true;
//		boolean performAllPairwise = false;
//		boolean genotypeRandom = false;
		
				
		//specify parameters
		String dataFileName = DATA_PATH + "Two_stage.csv";
		String weightOption = "stderr";
		String[] respvar = {"Yield_Mean"};
		String[] standardErrors = {"Yield_StdErrMean"};
		String[] residualVariances = {"Yield_sigma2"};
		String[] numberOfReps = {"Yield_No.rep"};
		String environment = "Env";
		String[] environmentLevels = {"E1", "E2", "E3", "E4", "E5", "E6", "E7", "E8", "E9", "E10"};
		String genotype = "Geno";
		boolean descriptiveStat = true; 
		boolean varianceComponents = true;
		boolean boxplotRawData = false;
		boolean histogramRawData = false;
		boolean diagnosticPlot = false;
		boolean genotypeFixed = true;
		boolean performPairwise = true;
		String pairwiseAlpha = "0.05";
		String[] genotypeLevels = {"G1",   "G2",   "G3",   "G4",   "G5",   "G6",   "G7",   "G8",   "G9",   "G10",  "G11",  "G12"};
		String[] controlLevels = {"G1", "G3"};
		boolean compareControl = false;
		boolean performAllPairwise = true;
		boolean genotypeRandom = true;
		
//		String dataFileName = DATA_PATH + "summaryStats.csv";
////		String weightOption = "stderr";
//		String weightOption = "stderr";
//		String[] respvar = {"Yield_Mean"};
//		String[] standardErrors = {"Yield_StdErrMean"};
//		String[] residualVariances = {"Yield_sigma2"};
//		String[] numberOfReps = {"Yield_No.rep"};
//		String environment = "Env";
//		String[] environmentLevels = {"E1", "E2", "E3", "E4", "E5", "E6", "E7", "E8", "E9", "E10", "E11"};
////		String[] environmentLevels = {"E1", "E2"};
//		String genotype = "Genotype";
//		boolean descriptiveStat = true; 
//		boolean varianceComponents = true;
//		boolean testGenotypicEffect = true;
//		boolean testGxEEffect = true;
//		boolean boxplotRawData = false;
//		boolean histogramRawData = false;
//		boolean diagnosticPlot = false;
//		boolean genotypeFixed = true;
//		boolean performPairwise = true;
//		String pairwiseAlpha = "0.5";
//		String[] genotypeLevels = {"GEN1",   "GEN2",   "GEN3",   "GEN4",   "GEN5",   "GEN6",   "GEN7",   "GEN8",   "GEN9",   "GEN10",  "GEN11",  "GEN12", "GEN13",  "GEN14",  "GEN15", "GEN16"};
//		String[] controlLevels = {"GEN1",   "GEN2",   "GEN3",   "GEN4",   "GEN5",   "GEN6"};
//		boolean compareControl = false;
//		boolean performAllPairwise = true;
//		boolean genotypeRandom = true;
		
		RJavaManager rJavaManager= new RJavaManager();
		rJavaManager.initPBtool();
		rJavaManager.getPbToolAnalysisManager().doMultiEnvironmentSecondStage(dataFileName, outFileName,resultFolderPath, weightOption, respvar, standardErrors, residualVariances, numberOfReps, 
				environment, environmentLevels, genotype, descriptiveStat, varianceComponents, boxplotRawData, histogramRawData, diagnosticPlot, genotypeFixed, performPairwise, pairwiseAlpha, genotypeLevels, 
				controlLevels, compareControl, performAllPairwise, genotypeRandom);
	}

}