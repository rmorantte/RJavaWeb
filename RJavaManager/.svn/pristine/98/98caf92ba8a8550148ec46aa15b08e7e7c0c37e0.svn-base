package org.irri.breedingtool.rjava.pbtoolsanalysis;

import org.irri.breedingtool.rjava.manager.RJavaManager;

public class TestFactorialRegression {

	private static String BSLASH = "\\";
	private static String FSLASH = "/";
	private static String PATH1 = System.getProperty("user.dir")+ System.getProperty("file.separator") + "sample_datasets" + System.getProperty("file.separator");
	public static String DATA_PATH = PATH1.replace(BSLASH, FSLASH);
	
	public static void main(String[] args) {
		
		//supply path and name of output file
		String outFileName = DATA_PATH + "FactorialRegression_output.txt";
		String resultFolderPath = DATA_PATH;

		//Covariate: environment characteristics
		String dataFileName= DATA_PATH + "ENVGENO DATA.csv";
		String[] respvar= {"YLD"};
		String environment = "ENV";
		String genotype = "GENOTYPE";
		String covariateData = DATA_PATH + "ENVIRONMENTAL DATA FOR PLS.csv";
		String covariateEnvironment="ENVIRONMENT";
		String covariateGenotype="NULL";
		
		//Covariate: genotype characteristics
//		String dataFileName= DATA_PATH + "durum means yield2.csv";
//		String[] respvar= {"Yield"};
//		String environment = "Env";
//		String genotype = "Geno";
//		String covariateData = DATA_PATH + "durum genotypic covariables2.csv";
//		String covariateEnvironment="NULL";
//		String covariateGenotype="GENOTYPE";

		
		RJavaManager rJavaManager= new RJavaManager();
		rJavaManager.initPBtool();
		rJavaManager.getPbToolAnalysisManager().doFactorialRegression(dataFileName, outFileName, resultFolderPath, respvar, environment, genotype, covariateData, covariateEnvironment, covariateGenotype);
	}

}