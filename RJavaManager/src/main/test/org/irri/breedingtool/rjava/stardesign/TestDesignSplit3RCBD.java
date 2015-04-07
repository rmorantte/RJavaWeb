package org.irri.breedingtool.rjava.stardesign;

import org.irri.breedingtool.rjava.manager.RJavaManager;

public class TestDesignSplit3RCBD {

	private static String BSLASH = "\\";
	private static String FSLASH = "/";
	private static String PATH1 = System.getProperty("user.dir")+ System.getProperty("file.separator") + "sample_datasets" + System.getProperty("file.separator");
	public static String DATA_PATH = PATH1.replace(BSLASH, FSLASH);
	
	public static void main(String[] args) {
		
		// supply the path where the output will be saved
		String path = DATA_PATH;
		String fieldBookName = "fieldbookSplit3RCBD";
		String main = "Fertilizer";
		String sub = "Nitrogen";
		String ssub = "Variety";
		String sssub = "Management";
		String[] factorID = {"F", "N", "V", "M"}; // factor ID for main plot and sub plot factor respectively
		Integer[] factorLevel = {5, 4, 4, 2}; // factor level for main plot and sub plot factor respectively
		Integer rep = 6;
		Integer rowPerBlk = 5; 			
		Integer rowPerMain = 2;
		Integer rowPerSub = 2; 	
		Integer rowPerSubSub = 1;
		Integer numFieldRow = 40;
		Integer trial = 2;
		String design = "rcbd";
		String fieldOrder = "Serpentine";
			
		RJavaManager rJavaManager= new RJavaManager();
		rJavaManager.initStar();
		rJavaManager.getSTARDesignManager().doDesignSplit(path, fieldBookName, 
				main, sub, ssub, sssub, factorID, factorLevel, rep, trial, design, 
				numFieldRow, rowPerBlk, rowPerMain, rowPerSub, rowPerSubSub, fieldOrder);
		
		StringBuilder s = rJavaManager.getSTARDesignManager().getRscriptCommand();
		System.out.println("Script Command:\n" +s.toString());

	}

}
