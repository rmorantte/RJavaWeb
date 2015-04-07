package org.irri.breedingtool.rjava.datamanipulation;

import org.irri.breedingtool.rjava.manager.RJavaManager;

public class TestCheckLevelsWithOneDatum {

	private static String BSLASH = "\\";
	private static String FSLASH = "/";
	private static String PATH1 = System.getProperty("user.dir")+ System.getProperty("file.separator") + "sample_datasets" + System.getProperty("file.separator");
	public static String DATA_PATH = PATH1.replace(BSLASH, FSLASH);
	
	public static void main(String[] args) {
		
//		//supply path and name of rda data
//		String dataFileName = DATA_PATH + "icecreamSummary2NA.csv";
//		String categoryName = "numScoops";
//		String responseColumnName = "Count3";
//		String groupingName = null; // NULL
//		String[] result;
//
//		//EXAMPLE 2
//		//supply path and name of rda data
//		String dataFileName = DATA_PATH + "icecreamSummary2NA.csv";
//		String categoryName = "numScoops";
//		String responseColumnName = "Count2";
//		String groupingName = "brand"; //null; // NULL
//		String[] result;

		//EXAMPLE 3
		//supply path and name of rda data
		String dataFileName = DATA_PATH + "gerua_NA.csv";
		String categoryName = "CULT";
		String responseColumnName = "yldSp2";
		String groupingName = "TPL"; //null; // NULL
		String[] result;

		RJavaManager rJavaManager= new RJavaManager();
		rJavaManager.initStar();
		result=rJavaManager.getRJavaDataManipulationManager().checkLevelsWithOneDatum(dataFileName, categoryName, responseColumnName, groupingName);
		System.out.println("\ncheckLevelsWithOneDatum:\nNo. of cVar Levels With One Datum in subgroup levels:");
		for (int i=0; i<result.length;i++) System.out.println(result[i]);
	}
}
