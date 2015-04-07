package org.irri.breedingtool.rjava.stardesign;

import org.irri.breedingtool.rjava.manager.RJavaManager;

public class TestDesignAugmented {

	private static String BSLASH = "\\";
	private static String FSLASH = "/";
	private static String PATH1 = System.getProperty("user.dir")+ System.getProperty("file.separator") + "sample_datasets" + System.getProperty("file.separator");
	public static String DATA_PATH = PATH1.replace(BSLASH, FSLASH);
	
	public static void main(String[] args) {
		
		//supply path and name of active data
		String dataFileName = DATA_PATH + "Augmented.csv"; 
		//supply path and name of text file where text output is going to be saved
		String outFileName = DATA_PATH + "AugmentedRandomiztion.txt"; 
		//supply path and name of graph file
		
		//specify parameters
		Integer repTrmt = 6;
		Integer unrepTrmt = 60;
		Integer rep = 5;
		Integer trial = 2;
		String design = "rcbd";
		
		RJavaManager rJavaManager= new RJavaManager();
		rJavaManager.initStar();
		rJavaManager.getSTARDesignManager().doDesignAug(dataFileName, outFileName, 
				repTrmt, unrepTrmt, rep, trial, design);
		
		StringBuilder s = rJavaManager.getSTARDesignManager().getRscriptCommand();
		System.out.println("Script Command:\n" +s.toString());
	}

}
