package org.irri.breedingtool.rjava.stardesign;

import org.irri.breedingtool.rjava.manager.RJavaManager;

public class TestDesignSplitLSD {

	private static String BSLASH = "\\";
	private static String FSLASH = "/";
	private static String PATH1 = System.getProperty("user.dir")+ System.getProperty("file.separator") + "sample_datasets" + System.getProperty("file.separator");
	public static String DATA_PATH = PATH1.replace(BSLASH, FSLASH);
	
	public static void main(String[] args) {
		
		// supply the path where the output will be saved
		String path = DATA_PATH;
		String fieldBookName = "fieldbookSplitLSD";
		String main = "Variety";
		String sub = "Nitrogen";
		String ssub = null;
		String sssub = null;
		String[] factorID = {"V", "N"}; // factor ID for main plot and sub plot factor respectively
		Integer[] factorLevel = {5, 4}; // factor level for main plot and sub plot factor respectively
		Integer rep = null;				// for Split Plot in LSD, rep is equal to null
		Integer rowPerBlk = 1; 			// for Split Plot in LSD, rowPerBlk is equal to 1
		Integer rowPerMain = 4;
		Integer rowPerSub = 1; 			// for Split Plot Design, rowPerSub is equal to 1
		Integer rowPerSubSub = 1; 		// for Split Plot Design, rowPerSubSub is equal to 1
		Integer numFieldRow = 1;		// for Split Plot in LSD, numFieldRow is equal to 1
		Integer trial = 2;
		String design = "lsd";
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
