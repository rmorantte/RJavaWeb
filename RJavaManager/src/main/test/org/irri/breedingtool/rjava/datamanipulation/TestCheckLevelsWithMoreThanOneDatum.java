package org.irri.breedingtool.rjava.datamanipulation;

import org.irri.breedingtool.rjava.manager.RJavaManager;

public class TestCheckLevelsWithMoreThanOneDatum {

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

//		//EXAMPLE 2
//		//supply path and name of rda data
//		String dataFileName = DATA_PATH + "icecreamSummary2NA.csv";
//		String categoryName = "numScoops";
//		String responseColumnName = "Count2";
//		String groupingName = "brand"; //null; // NULL

		//EXAMPLE 3
		//supply path and name of rda data
		String dataFileName = DATA_PATH + "gerua_NA.csv";
		String categoryName = "CULT";
		String responseColumnName = "DFF";
		String groupingName = "TPL2"; //null; // NULL
		
		RJavaManager rJavaManager= new RJavaManager();
		rJavaManager.initStar();
		
		String[] result2;
		result2=rJavaManager.getRJavaDataManipulationManager().checkLevelsWithMoreThanOneDatum(dataFileName, categoryName, responseColumnName, groupingName);
		System.out.println("\ncheckLevelsWithMoreThanOneDatum:\nNo of cVar Levels With More Than One Datum:");
		for (int i=0; i<result2.length;i++) System.out.println(result2[i]);
	}
}
