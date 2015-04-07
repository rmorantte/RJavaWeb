package org.irri.breedingtool.rjava.datamanipulation;

import org.irri.breedingtool.rjava.manager.RJavaManager;

public class TestAggregate {

	private static String BSLASH = "\\";
	private static String FSLASH = "/";
	private static String PATH1 = System.getProperty("user.dir")+ System.getProperty("file.separator") + "sample_datasets" + System.getProperty("file.separator");
	public static String DATA_PATH = PATH1.replace(BSLASH, FSLASH);
	
	public static void main(String[] args) {
		
		// Test case 1 for add aggregated variable to the active data
		
//		//supply path and name of active data
//		String dataFileName = DATA_PATH + "data1.csv";
//		//supply path and name of file where created data is going to be saved
//		String newFileName = DATA_PATH + "data1.csv"; 
//
//		
//		String[]inputNumVariablesUser={"Y1","Y2"};
//		String[]inputFactorVariablesUser={"Site","Blk"};
//		String[]functionUser={"mean", "standard deviation"};
//		boolean toAppend = true;
		
		
		// Test case 2 for create aggregated variable to another file
		//supply path and name of active data
		String dataFileName = DATA_PATH + "data1.csv";
		//supply path and name of file where created data is going to be saved
		String newFileName = DATA_PATH + "TESTdata1_aggregate3.csv"; 

		
		String[]inputNumVariables={"Y1","Y2"};
		String[]inputFactorVariables={"Site","Blk"};
		String[]function={"mean", "standard deviation"};
		boolean toAppend = false;
		
		RJavaManager rJavaManager= new RJavaManager();
		rJavaManager.initStar();
		rJavaManager.getRJavaDataManipulationManager().aggregateData(dataFileName, newFileName, inputNumVariables, inputFactorVariables, function, toAppend);
		
	}

}
