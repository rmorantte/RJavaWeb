package org.irri.breedingtool.rjava.datamanipulation;

import org.irri.breedingtool.rjava.manager.RJavaManager;

public class TestConvertRdaToCsv {

	private static String BSLASH = "\\";
	private static String FSLASH = "/";
	private static String PATH1 = System.getProperty("user.dir")+ System.getProperty("file.separator") + "sample_datasets" + System.getProperty("file.separator");
	public static String DATA_PATH = PATH1.replace(BSLASH, FSLASH);
	
	public static void main(String[] args) {
		
		//supply path and name of rda data
		String dataFileName = "C:\\Documents and Settings\\NSales\\My Documents\\sample_datasets\\sample.rda";
		String dataFileName2 = dataFileName.replace(BSLASH, FSLASH); 
		//supply path and name of file where created data is going to be saved
		String newFileName = DATA_PATH + "sample.csv";
		//supply path and name of the temp file containing var info
		String tempFileName = DATA_PATH + "sample_varInfo.temp";
		
		System.out.println(dataFileName);
		System.out.println(dataFileName2);
		
		RJavaManager rJavaManager= new RJavaManager();
		rJavaManager.initStar();
		rJavaManager.getRJavaDataManipulationManager().convertRdaToCsv(dataFileName2, newFileName);
		rJavaManager.getRJavaDataManipulationManager().getVariableInfo(dataFileName2, tempFileName, 3, "NULL");

	}

}
