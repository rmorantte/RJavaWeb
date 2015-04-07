package org.irri.breedingtool.rjava.datamanipulation;

import org.irri.breedingtool.rjava.manager.RJavaManager;

public class TestIsColumnNumeric {

	private static String BSLASH = "\\";
	private static String FSLASH = "/";
	private static String PATH1 = System.getProperty("user.dir")+ System.getProperty("file.separator") + "sample_datasets" + System.getProperty("file.separator");
	public static String DATA_PATH = PATH1.replace(BSLASH, FSLASH);
	
	public static void main(String[] args) {
		
		//supply path and name of rda data
		String dataFileName = DATA_PATH + "data1.csv";
		String column = "Y1";
		String result;
		
		RJavaManager rJavaManager= new RJavaManager();
		rJavaManager.initStar();
		result=rJavaManager.getRJavaDataManipulationManager().isColumnNumeric(dataFileName, column);
		System.out.println(result);
	}

}
