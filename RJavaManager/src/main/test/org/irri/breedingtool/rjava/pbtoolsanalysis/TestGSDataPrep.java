package org.irri.breedingtool.rjava.pbtoolsanalysis;

import org.irri.breedingtool.rjava.manager.RJavaManager;

public class TestGSDataPrep {

	private static String BSLASH = "\\";
	private static String FSLASH = "/";
	private static String PATH1 = System.getProperty("user.dir")+ System.getProperty("file.separator") + "sample_datasets" + System.getProperty("file.separator");
	public static String DATA_PATH = PATH1.replace(BSLASH, FSLASH);
	
	public static void main(String[] args) {
		
		//supply path where output will be saved
		String resultFolderPath = DATA_PATH;  // outputPath = "E:/App Files/workspace_Juno/RJavaManager/sample_datasets"

		String pheno_file = "E:/StarPbtools/GS/data/wheat_Y.csv";
		String geno_file = "E:/StarPbtools/GS/data/wheat_X.csv"; //					# geno_file = "E:/StarPbtools/GS/data/maize_geno2b.csv"
//		String cov_file = "E:/StarPbtools/GS/data/maize_cov.csv";
		String map_file = "NULL"; //"E:/StarPbtools/GS/data/maize_map2.csv";
//					# #create rel_file
		String rel_file = "E:/StarPbtools/GS/data/wheat_A.p";

		
//		String pheno_file = "E:/StarPbtools/GS/data/maize_phenoCov2.csv";
//		String geno_file = "E:/StarPbtools/GS/data/maize_geno2.csv"; //					# geno_file = "E:/StarPbtools/GS/data/maize_geno2b.csv"
////		String cov_file = "E:/StarPbtools/GS/data/maize_cov.csv";
//		String map_file = "E:/StarPbtools/GS/data/maize_map2.csv"; //"NULL"; //
////					# #create rel_file
//		String rel_file = "E:/StarPbtools/GS/data/relMatFile2.m"; //"NULL"; //
//		String # ped_file = "E:/StarPbtools/GS/data/maize_ped.csv" # NULL
		String pFormat = "csv";
		String gFormat = "csv"; 
//		String cFormat = "csv"; 
		String mFormat = "NULL"; //"csv"; // //NULL if no map_file declared 
		String rFormat = "ttxt";// "NULL"; //
//		String #peFormat = "csv";
					
		RJavaManager rJavaManager= new RJavaManager();
		rJavaManager.initPBtool();
//		rJavaManager.initStar();

		rJavaManager.getWebToolManager().doGSDataPrep(
				resultFolderPath, pheno_file, geno_file,  map_file, rel_file, pFormat, gFormat, mFormat, rFormat); 

		System.out.println("TestGSDataPrep here");

	}

}