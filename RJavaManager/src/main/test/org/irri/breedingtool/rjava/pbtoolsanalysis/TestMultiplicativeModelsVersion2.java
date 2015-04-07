package org.irri.breedingtool.rjava.pbtoolsanalysis;

import org.irri.breedingtool.rjava.manager.RJavaManager;

public class TestMultiplicativeModelsVersion2 {

	private static String BSLASH = "\\";
	private static String FSLASH = "/";
	private static String PATH1 = System.getProperty("user.dir")+ System.getProperty("file.separator") + "sample_datasets" + System.getProperty("file.separator");
	public static String DATA_PATH = PATH1.replace(BSLASH, FSLASH);
	
	public static void main(String[] args) {
		
		//supply path and name of output file
		String outFileName = DATA_PATH + "MultiplicativeModelsVersion2_output.txt";
		String resultFolderPath = DATA_PATH;

		//specify parameters
//		String dataFileName = DATA_PATH + "summaryStats2_b.csv";
//		String[] respvar = {"Y3_Mean", "Plot.Yield_Mean"};
//		String[] residualVariances = {"0","0"};
//		String[] numberOfReps = {"1","1"};
//		String environment = "Set";
//		String genotype = "EntryNo";
//		boolean responsePlot = false; 
//		boolean doAMMI = true;
//		boolean biplotPC12 = true;
//		boolean biplotPC13 = true;
//		boolean biplotPC23 = true;
//		boolean ammi1Biplot = false;
//		boolean adaptationMap = true;
//		boolean doGGE = true;
//		boolean graphSymmetricView = true;
//		boolean graphEnvironmentView = true;
//		boolean graphGenotypicView = true;
		
		String dataFileName = DATA_PATH + "Two_stage.csv";
		String[] respvar = {"Yield_Mean"};
		String[] residualVariances = {"0"};
		String[] numberOfReps = {"1"};
		String environment = "Env";
		String genotype = "Geno";
		boolean responsePlot = true; 
		boolean doAMMI = true;
		boolean biplotPC12 = true;
		boolean biplotPC13 = true;
		boolean biplotPC23 = true;
		boolean ammi1Biplot = true;
		boolean adaptationMap = true;
		boolean doGGE = true;
		boolean graphSymmetricView = true;
		boolean graphEnvironmentView = true;
		boolean graphGenotypicView = true;
		
//		String dataFileName = DATA_PATH + "RCB_ME.csv";
//		String[] respvar = {"Yield", "Y2"};
//		String environment = "Env";
//		String genotype = "Genotype";
//		String[] residualVariances = {"1","2"};
//		String[] numberOfReps = {"1","1"};
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
		
//		String dataFileName = DATA_PATH + "GenoEnvMeans_Yield94.csv";
//		String[] respvar = {"Yield_means"};
//		String environment = "Env";
//		String genotype = "Geno";
//		String[] residualVariances = {"0.1571"};
//		String[] numberOfReps = {"4"};
//		boolean responsePlot = true; 
//		boolean doAMMI = false;
//		boolean biplotPC12 = true;
//		boolean biplotPC13 = true;
//		boolean biplotPC23 = true;
//		boolean ammi1Biplot = true;
//		boolean adaptationMap = true;
//		boolean doGGE = true;
//		boolean graphSymmetricView = true;
//		boolean graphEnvironmentView = true;
//		boolean graphGenotypicView = true;
		
//		String dataFileName = DATA_PATH + "MET_data.csv";
//		String[] respvar = {"Mean_Yield"};
//		String environment = "Env";
//		String genotype = "Geno";
//		String[] residualVariances = {"0.5"};
//		String[] numberOfReps = {"1"};
//		boolean responsePlot = true; 
//		boolean doAMMI = false;
//		boolean biplotPC12 = true;
//		boolean biplotPC13 = true;
//		boolean biplotPC23 = true;
//		boolean ammi1Biplot = true;
//		boolean adaptationMap = true;
//		boolean doGGE = true;
//		boolean graphSymmetricView = true;
//		boolean graphEnvironmentView = true;
//		boolean graphGenotypicView = true;
		
//		String dataFileName = DATA_PATH + "MET_example.csv";
//		String[] respvar = {"Yield"};
//		String environment = "Env";
//		String genotype = "Genotype";
//		String[] residualVariances = {"0"};
//		String[] numberOfReps = {"1"};
//		boolean responsePlot = true; 
//		boolean doAMMI = false;
//		boolean biplotPC12 = true;
//		boolean biplotPC13 = true;
//		boolean biplotPC23 = true;
//		boolean ammi1Biplot = true;
//		boolean adaptationMap = true;
//		boolean doGGE = true;
//		boolean graphSymmetricView = true;
//		boolean graphEnvironmentView = true;
//		boolean graphGenotypicView = true;
		
//		String dataFileName = DATA_PATH + "Yan_Tinker_yield.csv";
//		String[] respvar = {"MeanYield"};
//		String environment = "Env";
//		String genotype = "Geno";
//		String[] residualVariances = {"0"};
//		String[] numberOfReps = {"1"};
//		boolean responsePlot = true; 
//		boolean doAMMI = false;
//		boolean biplotPC12 = true;
//		boolean biplotPC13 = true;
//		boolean biplotPC23 = true;
//		boolean ammi1Biplot = true;
//		boolean adaptationMap = true;
//		boolean doGGE = true;
//		boolean graphSymmetricView = true;
//		boolean graphEnvironmentView = true;
//		boolean graphGenotypicView = true;
		
		RJavaManager rJavaManager= new RJavaManager();
		rJavaManager.initPBtool();
		rJavaManager.getPbToolAnalysisManager().doMultiplicativeModelsVersion2 (dataFileName, outFileName, resultFolderPath, respvar, environment, genotype, numberOfReps, residualVariances, responsePlot, 
				doAMMI, biplotPC12, biplotPC13, biplotPC23, ammi1Biplot, adaptationMap, doGGE, graphSymmetricView, graphEnvironmentView, graphGenotypicView);
	}

}