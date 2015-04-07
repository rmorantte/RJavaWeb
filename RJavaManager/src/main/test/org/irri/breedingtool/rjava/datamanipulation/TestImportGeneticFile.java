package org.irri.breedingtool.rjava.datamanipulation;

import org.irri.breedingtool.rjava.manager.RJavaManager;

public class TestImportGeneticFile {

	private static String BSLASH = "\\";
	private static String FSLASH = "/";
	private static String PATH1 = System.getProperty("user.dir")+ System.getProperty("file.separator") + "sample_datasets" + System.getProperty("file.separator");
	public static String DATA_PATH = PATH1.replace(BSLASH, FSLASH);
	
	public static void main(String[] args) {
		
		//supply path and name of rda data
		//specify parameters
//		String dataFileName = DATA_PATH + "nancycats_geno.csv";
//		String outFileName = DATA_PATH + "trialImport.RData";
//		String extension = "csv"; 
//		String population = "pop"; 
//		String individual = "ind";
//		String ploidy = "2";
//		String sep = "/";
		
//		String dataFileName = DATA_PATH + "nancycats.gtx";
//		String outFileName = DATA_PATH + "trialImport4.RData";
//		String extension = "gtx"; 
//		String population = "NULL"; 
//		String individual = "NULL";
//		String ploidy = "NULL";
//		String sep = "NULL";
		
//		String dataFileName = DATA_PATH + "nancycats.gen";
//		String outFileName = DATA_PATH + "trialImport5.RData";
//		String extension = "gen"; 
//		String population = "NULL"; 
//		String individual = "NULL";
//		String ploidy = "NULL";
//		String sep = "NULL";
		
		String dataFileName = DATA_PATH + "nancycats.dat";
		String outFileName = DATA_PATH + "trialImport6.RData";
		String extension = "dat"; 
		String population = "NULL"; 
		String individual = "NULL";
		String ploidy = "NULL";
		String sep = "NULL";
		
		RJavaManager rJavaManager= new RJavaManager();
		rJavaManager.initPBtool();
		rJavaManager.getRJavaDataManipulationManager().importGeneticFile(dataFileName, outFileName, extension, population, individual, ploidy, sep);
	}

}
