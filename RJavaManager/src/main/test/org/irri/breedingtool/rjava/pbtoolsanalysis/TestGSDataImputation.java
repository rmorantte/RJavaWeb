package org.irri.breedingtool.rjava.pbtoolsanalysis;

import org.irri.breedingtool.rjava.manager.RJavaManager;

public class TestGSDataImputation {

	private static String BSLASH = "\\";
	private static String FSLASH = "/";
	private static String PATH1 = System.getProperty("user.dir")+ System.getProperty("file.separator") + "sample_datasets" + System.getProperty("file.separator");
	public static String DATA_PATH = PATH1.replace(BSLASH, FSLASH);
	
	public static void main(String[] args) {

		//supply path where output will be saved
		String resultFolderPath = DATA_PATH;  // outputPath = "E:/App Files/workspace_Juno/RJavaManager/sample_datasets"
//		String geno_file = resultFolderPath + "genoData.csv"; //from GSDataPrep
//		int markerFormat = 5;
//		double maxCorr = 0.9;
//		double maxMissingP = 0.1;
//		double minMAF = 0.05;

		String geno_file = resultFolderPath + "genoData_qc.csv"; //fixed filename, output of GSDataCheck
		String impType = "random"; //c("random", "family"), 
		String pheno_file = "NULL"; //, 
		String familyTrait = "NULL";
		String packageFormat = "synbreed"; //c("synbreed", "rrBLUP", "BGLR")) 
//		GSDataImputation <- function(outputPath, geno_file, impType = c("random", "family"), pheno_file = NULL, familyTrait = NULL, packageFormat = c("synbreed", "rrBLUP", "BGLR")) {

		RJavaManager rJavaManager= new RJavaManager();
		rJavaManager.initPBtool();

		rJavaManager.getWebToolManager().doGSDataImputation(resultFolderPath, geno_file, impType, pheno_file, familyTrait, packageFormat);
//		geno <- GSDataCheck(outputPath, gsData$genoData, markerFormat, nmiss = maxMissingP, maf = minMAF, cor_threshold = maxCorr)
//		GSDataCheck <- function(outputPath, 
//		                      ori_data=gsData$genoData 
//		                      type =markerFormat
//		                      nmiss = 0.1;maf = 0.05; cor_threshold = 0.90

		System.out.println("TestGSDataImputation end");



	}

}