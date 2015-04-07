package org.irri.breedingtool.rjava.datamanipulation;

import org.irri.breedingtool.rjava.manager.RJavaManager;

public class TestGetVariableInfo {

	private static String BSLASH = "\\";
	private static String FSLASH = "/";
	private static String PATH1 = System.getProperty("user.dir")+ System.getProperty("file.separator") + "sample_datasets" + System.getProperty("file.separator");
	public static String DATA_PATH = PATH1.replace(BSLASH, FSLASH);
	
	public static void main(String[] args) {

		//EXAMPLE 1
		//supply path and name of imported data
		String fileName = DATA_PATH + "data1.csv";
		//supply path and name of temp data to be created
		String tempFileName = DATA_PATH + "data1_varInfo.temp";
		//supply format of imported data
		int fileFormat = 1;
		String separator = "NULL";

//		//EXAMPLE 2
//		//supply path and name of imported data
//		String fileName = DATA_PATH + "Mdata1.txt";
//		//supply format of imported data
//		int fileFormat = 2;
//		String separator = "\t";

		
		RJavaManager rJavaManager= new RJavaManager();
		rJavaManager.initStar();
		rJavaManager.getRJavaDataManipulationManager().getVariableInfo(fileName, tempFileName, fileFormat, separator);
	}

}