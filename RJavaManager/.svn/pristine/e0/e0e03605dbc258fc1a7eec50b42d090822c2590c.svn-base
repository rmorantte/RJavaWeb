package org.irri.breedingtool.rjava.pbtoolsanalysis;

import org.irri.breedingtool.rjava.manager.RJavaManager;

public class TestMultiEnvironmentSecondStageVersion2 {

	private static String BSLASH = "\\";
	private static String FSLASH = "/";
	private static String PATH1 = System.getProperty("user.dir")+ System.getProperty("file.separator") + "sample_datasets" + System.getProperty("file.separator");
	public static String DATA_PATH = PATH1.replace(BSLASH, FSLASH);
	
	public static void main(String[] args) {
		
		//supply path and name of active data
//		String dataFileName = DATA_PATH + "summaryStats2.csv";
		//supply path and name of text file where text output is going to be saved
		String outFileName = DATA_PATH + "MEASecondStageV2_output.txt";
		//supply path where the graphs will be saved
		String resultFolderPath = DATA_PATH;
		
		//specify parameters
//		String[] respvar = {"Plot.Yield_Mean", "Y2_Mean"};
//		String[] mseValue = {"0","0"};
//		String[] repValue = {"1","1"};
//		String environment = "Set";
//		String genotype = "EntryNo";
		
		
//		String dataFileName = DATA_PATH + "Two_stage.csv";
//		String[] respvar = {"Yield_Mean"};
//		String[] mseValue = {"0","0"};
//		String[] repValue = {"1","1"};
//		String environment = "Env";
//		String[] environmentLevels = {"E1", "E2", "E3", "E4", "E5", "E6", "E7", "E8", "E9", "E10"};
////		String[] environmentLevels = {"E1", "E2"};
//		String genotype = "Geno";
		
//		String dataFileName = DATA_PATH + "MET_example2.csv";
//		String[] respvar = {"Yield"};
//		String[] mseValue = {"0"};
//		String[] repValue = {"1"};
//		String environment = "Env";
//		String[] environmentLevels = {"Env1", "Env2", "Env3", "Env4", "Env5", "Env6", "Env7", "Env8", "Env9", "Env10", "Env11", "Env12", "Env13", "Env14", "Env15", "Env16", "Env17", "Env18", "Env19", "Env20", "Env21", "Env22", "Env23", "Env24"};
//		String genotype = "Genotype";
//		boolean stabilityFinlay=true;
//		boolean stabilityShukla=true;
//		boolean ammi=false;
//		boolean gge=true;
		
		String dataFileName = DATA_PATH + "MET_example.csv";
		String[] respvar = {"Yield"};
		String[] mseValue = {"0"};
		String[] repValue = {"1"};
		String environment = "Env";
		String[] environmentLevels = {"E1", "E2", "E3", "E4", "E5", "E6", "E7", "E8", "E9", "E10", "E11", "E12", "E13", "E14", "E15", "E16", "E17", "E18", "E19", "E20", "E21", "E22", "E23", "E24"};
		String genotype = "Genotype";
		boolean stabilityFinlay=true;
		boolean stabilityShukla=true;
		boolean ammi=false;
		boolean gge=true;
		
		RJavaManager rJavaManager= new RJavaManager();
		rJavaManager.initPBtool();
		rJavaManager.getPbToolAnalysisManager().doMultiEnvironmentSecondStageVersion2(dataFileName, outFileName, resultFolderPath, respvar, environment, environmentLevels, genotype, mseValue, repValue, stabilityFinlay, stabilityShukla, ammi, gge);
			
	}

}