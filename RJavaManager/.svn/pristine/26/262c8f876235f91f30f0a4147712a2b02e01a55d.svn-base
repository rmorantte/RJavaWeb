package org.irri.breedingtool.rjava.datamanipulation;

import org.irri.breedingtool.rjava.manager.RJavaManager;

public class TestGetColumnHeaders {

	private static String BSLASH = "\\";
	private static String FSLASH = "/";
	private static String PATH1 = System.getProperty("user.dir")+ System.getProperty("file.separator") + "sample_datasets" + System.getProperty("file.separator");
	public static String DATA_PATH = PATH1.replace(BSLASH, FSLASH);
	
	public static void main(String[] args) {
		
		//supply path and name of rda data
		String dataFileName = DATA_PATH + "nancycats_geno.csv";
		
		String[] result;
		
		RJavaManager rJavaManager= new RJavaManager();
		rJavaManager.initPBtool();

		result=rJavaManager.getRJavaDataManipulationManager().getColumnHeaders(dataFileName);
		
	}

}
