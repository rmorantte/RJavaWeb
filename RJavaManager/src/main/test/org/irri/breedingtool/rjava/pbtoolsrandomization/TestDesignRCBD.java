package org.irri.breedingtool.rjava.pbtoolsrandomization;

import org.irri.breedingtool.rjava.manager.RJavaManager;

public class TestDesignRCBD {

	private static String BSLASH = "\\";
	private static String FSLASH = "/";
	private static String PATH1 = System.getProperty("user.dir")+ System.getProperty("file.separator") + "sample_datasets" + System.getProperty("file.separator");
	public static String DATA_PATH = PATH1.replace(BSLASH, FSLASH);
	
	public static void main(String[] args) {
		
		String path = DATA_PATH;
		String fieldBookName = "fieldbookRCBD";
		String[] factorName = {"Variety", "Nitrogen"};
		String[] factorID = {"V", "N"};
		Integer[] factorLevel = {3, 3};
		Integer blk = 9;
		Integer trial = 2;
		Integer numFieldRow = 9;
		Integer rowPerBlk = 3;
		String fieldOrder = "Serpentine";
			
	
		RJavaManager rJavaManager= new RJavaManager();
		rJavaManager.initPBtool();
		rJavaManager.getPbToolRandomizationManager().doDesignRCBD(path, fieldBookName, factorName, factorID, factorLevel,
				blk, trial, numFieldRow, rowPerBlk, fieldOrder);
	}

}
