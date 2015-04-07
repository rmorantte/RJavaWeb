package org.irri.breedingtool.rjava.pbtoolsanalysis;

import org.irri.breedingtool.rjava.manager.RJavaManager;

public class TestGSDataCheck {

	private static String BSLASH = "\\";
	private static String FSLASH = "/";
	private static String PATH1 = System.getProperty("user.dir")+ System.getProperty("file.separator") + "sample_datasets" + System.getProperty("file.separator");
	public static String DATA_PATH = PATH1.replace(BSLASH, FSLASH);
	
	public static void main(String[] args) {

		//supply path where output will be saved
		String resultFolderPath = DATA_PATH;  // outputPath = "E:/App Files/workspace_Juno/RJavaManager/sample_datasets"
		String geno_file = resultFolderPath + "genoData.csv"; //fixed filename from GSDataPrep
		int markerFormat = 5;
		double maxCorr = 0.9;
		double maxMissingP = 0.1;
		double minMAF = 0.05;

		RJavaManager rJavaManager= new RJavaManager();
		rJavaManager.initPBtool();

		rJavaManager.getWebToolManager().doGSDataCheck(resultFolderPath, geno_file, markerFormat, maxMissingP, minMAF, maxCorr);
//		geno <- GSDataCheck(outputPath, gsData$genoData, markerFormat, nmiss = maxMissingP, maf = minMAF, cor_threshold = maxCorr)
//		GSDataCheck <- function(outputPath, 
//		                      ori_data=gsData$genoData 
//		                      type =markerFormat
//		                      nmiss = 0.1;maf = 0.05; cor_threshold = 0.90

		System.out.println("TestGSDataCheck end");



	}

}