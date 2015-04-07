package org.irri.breedingtool.rjava.stardesign;

import org.irri.breedingtool.rjava.manager.RJavaManager;

public class TestDesignLatinizedAlpha {

	private static String BSLASH = "\\";
	private static String FSLASH = "/";
	private static String PATH1 = System.getProperty("user.dir")+ System.getProperty("file.separator") + "sample_datasets" + System.getProperty("file.separator");
	public static String DATA_PATH = PATH1.replace(BSLASH, FSLASH);
	
	public static void main(String[] args) {
		
		//supply path where the output will be saved
		String path = DATA_PATH; 
		//supply the fieldbook name specified by the user
		String fieldBookName = "fieldbookLatinizedAlphaLattice"; 
		
		//specify parameters
		Integer numTrmt = 15;
		Integer blkSize = 5;
		Integer rep = 3;
		Integer trial = 1;
		Integer numFieldRow = 15;
		String fieldOrder = "Serpentine";
		
		RJavaManager rJavaManager= new RJavaManager();
		rJavaManager.initStar();
		rJavaManager.getSTARDesignManager().doDesignLatinizedAlpha(path, fieldBookName, numTrmt, blkSize, 
				rep, trial, numFieldRow, fieldOrder);
		
		StringBuilder s = rJavaManager.getSTARDesignManager().getRscriptCommand();
		System.out.println("Script Command:\n" +s.toString());
	}

}
