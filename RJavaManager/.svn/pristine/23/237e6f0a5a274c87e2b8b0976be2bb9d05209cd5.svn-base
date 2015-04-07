package org.irri.breedingtool.rjava.pbtoolsrandomization;

import org.irri.breedingtool.rjava.manager.RJavaManager;

public class TestDesignBIBD {

	private static String BSLASH = "\\";
	private static String FSLASH = "/";
	private static String PATH1 = System.getProperty("user.dir")+ System.getProperty("file.separator") + "sample_datasets" + System.getProperty("file.separator");
	public static String DATA_PATH = PATH1.replace(BSLASH, FSLASH);
	
	public static void main(String[] args) {
		
		
		// supply the path where the output will be saved
		String path = DATA_PATH;
		String fieldBookName = "fieldbookBIBD";
		Integer numTrmt = 5;
		Integer blkSize = 4;
		Integer trial = 2;
		Integer numFieldRow = 10;
		Integer rowPerBlk = 2;
		String fieldOrder = "Serpentine";
						
		RJavaManager rJavaManager= new RJavaManager();
		rJavaManager.initPBtool();
		rJavaManager.getPbToolRandomizationManager().doDesignBIBD(path, fieldBookName, numTrmt, blkSize, 
				trial, numFieldRow, rowPerBlk, fieldOrder);
		
	}

}
