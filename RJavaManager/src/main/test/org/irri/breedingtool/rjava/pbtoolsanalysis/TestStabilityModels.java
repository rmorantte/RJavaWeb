package org.irri.breedingtool.rjava.pbtoolsanalysis;

import org.irri.breedingtool.rjava.manager.RJavaManager;

public class TestStabilityModels {

	private static String BSLASH = "\\";
	private static String FSLASH = "/";
	private static String PATH1 = System.getProperty("user.dir")+ System.getProperty("file.separator") + "sample_datasets" + System.getProperty("file.separator");
	public static String DATA_PATH = PATH1.replace(BSLASH, FSLASH);
	
	public static void main(String[] args) {
		
		//supply path and name of output file
		String outFileName = DATA_PATH + "StabilityModels_output.txt";

		//specify parameters
		String dataFileName = DATA_PATH + "RCB_ME.csv";
		String[] respvar = {"Yield", "Y2"};
		String environment = "Env";
		String genotype = "Genotype";
		boolean stabilityFinlay = true;
		boolean stabilityShukla = true;
		
		//specify parameters
//		String dataFileName = DATA_PATH + "summaryStats2_b.csv";
//		String[] respvar = {"Y2_Mean", "Y3_Mean"};
//		String environment = "Set";
//		String genotype = "EntryNo";
//		boolean stabilityFinlay = true;
//		boolean stabilityShukla = false;
		
		RJavaManager rJavaManager= new RJavaManager();
		rJavaManager.initPBtool();
		rJavaManager.getPbToolAnalysisManager().doStabilityModels(dataFileName, outFileName, respvar, environment, genotype, stabilityFinlay, stabilityShukla);
	}

}