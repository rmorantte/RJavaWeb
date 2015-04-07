package org.irri.breedingtool.rjava.datamanipulation;

import org.irri.breedingtool.rjava.manager.RJavaManager;

public class TestHowManyLevelsHaveOneDatum {

	private static String BSLASH = "\\";
	private static String FSLASH = "/";
	private static String PATH1 = System.getProperty("user.dir")+ System.getProperty("file.separator") + "sample_datasets" + System.getProperty("file.separator");
	public static String DATA_PATH = PATH1.replace(BSLASH, FSLASH);
	
	public static void main(String[] args) {
		
		//supply path and name of rda data
		String dataFileName = DATA_PATH + "icecreamSummary2NA.csv";
		String categoryName = "numScoops";
		String responseColumnName = "Count3";
		String groupingName = null; // NULL
		int groupingNameLevelsCount = 1; //3; //
		int[] result;

//		//EXAMPLE 2
//		//supply path and name of rda data
//		String dataFileName = DATA_PATH + "icecreamSummary2NA.csv";
//		String categoryName = "numScoops";
//		String responseColumnName = "Count2";
//		String groupingName = "brand"; //null; // NULL
//		int groupingNameLevelsCount = 4; //1; //
//		int[] result;

		RJavaManager rJavaManager= new RJavaManager();
		rJavaManager.initStar();
		result=rJavaManager.getRJavaDataManipulationManager().howManyLevelsHaveOneDatum(dataFileName, categoryName, responseColumnName, groupingName);
		for (int i=0; i<groupingNameLevelsCount;i++) System.out.println(i + ":" + result[i]);
	}

}
