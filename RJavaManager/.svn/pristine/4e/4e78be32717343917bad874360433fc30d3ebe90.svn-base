package org.irri.breedingtool.rjava.datamanipulation;

import org.irri.breedingtool.rjava.manager.RJavaManager;

public class TestGetGenindProperties {

	private static String BSLASH = "\\";
	private static String FSLASH = "/";
	private static String PATH1 = System.getProperty("user.dir")+ System.getProperty("file.separator") + "sample_datasets" + System.getProperty("file.separator");
	public static String DATA_PATH = PATH1.replace(BSLASH, FSLASH);
	
	public static void main(String[] args) {
		
		//supply path and name of rda data
		String dataFileName = DATA_PATH + "trialImport6.RData";
//		String dataFileName = DATA_PATH + "trialImport2.RData";
		
		String outFileName = DATA_PATH + "trialTab.csv";
		
		String[] result;
		int result2;
		String result3;
		
		RJavaManager rJavaManager= new RJavaManager();
		rJavaManager.initPBtool();
//		result=rJavaManager.getRJavaDataManipulationManager().getGenindPopNames(dataFileName);
//		result=rJavaManager.getRJavaDataManipulationManager().getGenindIndNames(dataFileName);
//		result=rJavaManager.getRJavaDataManipulationManager().getGenindLocNames(dataFileName);
//		result=rJavaManager.getRJavaDataManipulationManager().getGenindAlleleNames(dataFileName);
//		result=rJavaManager.getRJavaDataManipulationManager().getGenindNumberAllelesPerMarker(dataFileName);
		result2=rJavaManager.getRJavaDataManipulationManager().getGenindPloidy(dataFileName);
//		result3=rJavaManager.getRJavaDataManipulationManager().getGenindType(dataFileName);
//		rJavaManager.getRJavaDataManipulationManager().getGenindTab(dataFileName, outFileName);
		
	}

}
