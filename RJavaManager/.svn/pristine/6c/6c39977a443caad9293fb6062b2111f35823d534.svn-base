package org.irri.breedingtool.rjava.datamanipulation;

import java.util.ArrayList;

import org.irri.breedingtool.rjava.manager.RJavaManager;

public class TestRJava {

	/**
	 * @param args
	 */

	private static String BSLASH = "\\";
	private static String FSLASH = "/";
	private static String PATH1 = System.getProperty("user.dir")+ System.getProperty("file.separator") + "sample_datasets" + System.getProperty("file.separator");
	public static String DATA_PATH = PATH1.replace(BSLASH, FSLASH);

	public static void main(String[] args) {

		// Sort Test

//		String dataFileName = DATA_PATH + "data1Sort.csv"; 
//		String newFileName = DATA_PATH + "data1SortTest.csv";
//
//		String[]sortChoices={"Site","Gen","Blk"};
//		String[]sortOrder={"ascending","ascending","descending"};
//
//		CreateRObjects createRObject= new CreateRObjects();
//
//		String varString = createRObject.createRVector(sortChoices); //"c(\"Site\",\"Y1\")";
//		String sortBy = createRObject.createRVector(sortOrder); //"c(\"ascending\",\"descending\")";
//
//		System.out.println("varString: " + varString);
//		System.out.println("sortBy: " + sortBy);
//
		RJavaManager rJavaManager=  new RJavaManager();
		rJavaManager.initPBtool();
//		rJavaManager.getRJavaDataManipulationManager().sortCases(dataFileName, newFileName, varString, sortBy);
//		rJavaManager.clean();
		
		
		// Subset Test		
		
		
		String dataFileName2 = DATA_PATH + "data1.csv";
		String newFileName2 = DATA_PATH + "data1_subsetTest.csv"; 

		ArrayList<String> subsetConditions= new ArrayList<String>();
		subsetConditions.add("Gen:==:7,8:Numeric");
		subsetConditions.add("Site:!=:Env1:Factor");
		subsetConditions.add("Blk:!=:4:Numeric");

		rJavaManager.getRJavaDataManipulationManager().subSet(dataFileName2, newFileName2, subsetConditions);

	}

}
