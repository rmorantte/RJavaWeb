package org.irri.breedingtool.rjava.pbtoolsanalysis;

import org.irri.breedingtool.rjava.manager.RJavaManager;

public class TestMultiplicativeModels {

	private static String BSLASH = "\\";
	private static String FSLASH = "/";
	private static String PATH1 = System.getProperty("user.dir")+ System.getProperty("file.separator") + "sample_datasets" + System.getProperty("file.separator");
	public static String DATA_PATH = PATH1.replace(BSLASH, FSLASH);
	
	public static void main(String[] args) {
		
		//supply path and name of output file
		String outFileName = DATA_PATH + "MultiplicativeModels_output.txt";
		String resultFolderPath = DATA_PATH;

		//specify parameters
//		String dataFileName = DATA_PATH + "summaryStats2_b.csv";
//		String[] respvar = {"Y3_Mean", "Plot.Yield_Mean"};
//		String[] residualVariances = {"Y3_sigma2", "Plot.Yield_sigma2"};
//		String[] numberOfReps = {"Y3_No.rep", "Plot.Yield_No.rep"};
//		String environment = "Set";
//		String genotype = "EntryNo";
//		boolean responsePlot = false; 
//		boolean doAMMI = false;
//		boolean biplotPC12 = true;
//		boolean biplotPC13 = true;
//		boolean biplotPC23 = true;
//		boolean ammi1Biplot = false;
//		boolean adaptationMap = true;
//		boolean doGGE = true;
//		boolean graphSymmetricView = true;
//		boolean graphEnvironmentView = true;
//		boolean graphGenotypicView = true;
		
//		String dataFileName = DATA_PATH + "Two_stage.csv";
//		String[] respvar = {"Yield_Mean"};
//		String[] residualVariances = {"Yield_sigma2"};
//		String[] numberOfReps = {"Yield_No.rep"};
//		String environment = "Env";
//		String genotype = "Geno";
//		boolean responsePlot = true; 
//		boolean doAMMI = true;
//		boolean biplotPC12 = true;
//		boolean biplotPC13 = true;
//		boolean biplotPC23 = true;
//		boolean ammi1Biplot = true;
//		boolean adaptationMap = true;
//		boolean doGGE = true;
//		boolean graphSymmetricView = true;
//		boolean graphEnvironmentView = true;
//		boolean graphGenotypicView = true;
		
		String dataFileName = DATA_PATH + "summaryStats_Yield94.csv";
		String[] respvar = {"Yield_Mean"};
		String[] residualVariances = {"Yield_sigma2"};
		String[] numberOfReps = {"Yield_No.rep"};
		String environment = "Env";
		String genotype = "Geno";
		boolean responsePlot = true; 
		boolean doAMMI = false;
		boolean biplotPC12 = true;
		boolean biplotPC13 = true;
		boolean biplotPC23 = true;
		boolean ammi1Biplot = true;
		boolean adaptationMap = true;
		boolean doGGE = true;
		boolean graphSymmetricView = true;
		boolean graphEnvironmentView = true;
		boolean graphGenotypicView = true;
		
		RJavaManager rJavaManager= new RJavaManager();
		rJavaManager.initPBtool();
		rJavaManager.getPbToolAnalysisManager().doMultiplicativeModels (dataFileName, outFileName, resultFolderPath, respvar, environment, genotype, numberOfReps, residualVariances, responsePlot, 
				doAMMI, biplotPC12, biplotPC13, biplotPC23, ammi1Biplot, adaptationMap, doGGE, graphSymmetricView, graphEnvironmentView, graphGenotypicView);
	}

}