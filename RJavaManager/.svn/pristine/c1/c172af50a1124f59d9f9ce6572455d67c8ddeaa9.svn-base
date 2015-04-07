package org.irri.breedingtool.rjava.datamanipulation;

import org.irri.breedingtool.rjava.manager.RJavaManager;

public class TestTransform {

	private static String BSLASH = "\\";
	private static String FSLASH = "/";
	private static String PATH1 = System.getProperty("user.dir")+ System.getProperty("file.separator") + "sample_datasets" + System.getProperty("file.separator");
	public static String DATA_PATH = PATH1.replace(BSLASH, FSLASH);
	
	public static void main(String[] args) {
		
		//supply path and name of active data
		String dataFileName = DATA_PATH + "data1.csv";
		//supply path and name of file where created data is going to be saved
		String newFileName = DATA_PATH + "data_transform.csv"; 

		String newVariableName="NewVarname";
		String inputVariable="Y1";
		String formula="log";
		
		RJavaManager rJavaManager= new RJavaManager();
		rJavaManager.initStar();
		rJavaManager.getRJavaDataManipulationManager().dataTransformation(dataFileName, newFileName, newVariableName, inputVariable,formula);
		

		

	}

}
