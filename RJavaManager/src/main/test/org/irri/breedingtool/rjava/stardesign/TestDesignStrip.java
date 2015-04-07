package org.irri.breedingtool.rjava.stardesign;

import org.irri.breedingtool.rjava.manager.RJavaManager;

public class TestDesignStrip {

	private static String BSLASH = "\\";
	private static String FSLASH = "/";
	private static String PATH1 = System.getProperty("user.dir")+ System.getProperty("file.separator") + "sample_datasets" + System.getProperty("file.separator");
	public static String DATA_PATH = PATH1.replace(BSLASH, FSLASH);
	
	public static void main(String[] args) {
		
		// supply the path where the output will be saved
		String path = DATA_PATH;
		String fieldBookName = "fieldbookStrip";
		String vertical = "Variety";
		String horizontal = "Nitrogen";
		String sub = null;
		String ssub = null;
		String[] factorID = {"V", "N"}; // factor ID for main plot and sub plot factor respectively
		Integer[] factorLevel = {5, 4}; // factor level for main plot and sub plot factor respectively
		Integer rep = 6;
		Integer rowPerMain = 1;			// for Strip Plot design, rowPerMain is equal to 1
		Integer rowPerSub = 1; 			// for Strip and Strip-Split Plot Design, rowPerSub is equal to 1
		Integer numFieldRow = 8;
		Integer trial = 2;
		String fieldOrder = "Serpentine";
		
		RJavaManager rJavaManager= new RJavaManager();
		rJavaManager.initStar();
		rJavaManager.getSTARDesignManager().doDesignStrip(path, fieldBookName, vertical, horizontal, sub, ssub, 
				factorID, factorLevel, rep, trial, numFieldRow, rowPerMain, rowPerSub, fieldOrder);
		
		StringBuilder s = rJavaManager.getSTARDesignManager().getRscriptCommand();
		System.out.println("Script Command:\n" +s.toString());

	}

}
