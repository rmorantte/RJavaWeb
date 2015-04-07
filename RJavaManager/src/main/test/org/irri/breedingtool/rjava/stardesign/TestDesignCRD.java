package org.irri.breedingtool.rjava.stardesign;

import org.irri.breedingtool.rjava.manager.RJavaManager;

public class TestDesignCRD {

	private static String BSLASH = "\\";
	private static String FSLASH = "/";
	private static String PATH1 = System.getProperty("user.dir")+ System.getProperty("file.separator") + "sample_datasets" + System.getProperty("file.separator");
	public static String DATA_PATH = PATH1.replace(BSLASH, FSLASH);
	
	public static void main(String[] args) {
		
		//supply path where the output will be saved
		String path = DATA_PATH; 
		//supply the fieldbook name specified by the user
		String fieldBookName = "fieldbookCRD"; 
		
		//specify parameters
		String[] factorName = {"VARIETY", "NITROGEN"};
		String[] factorID = {"V", "N"};
		Integer[] factorLevel = {4, 2};
		Integer rep = 2;
		Integer trial = 2;
		String fieldOrder = "Serpentine";
		Integer numFieldRow = 2;
		
		RJavaManager rJavaManager= new RJavaManager();
		rJavaManager.initStar();
		rJavaManager.getSTARDesignManager().doDesignCRD(path, fieldBookName, factorName, factorID, factorLevel, rep, trial, numFieldRow, fieldOrder);
		
		StringBuilder s = rJavaManager.getSTARDesignManager().getRscriptCommand();
		System.out.println("Script Command:\n" +s.toString());
	}

}
