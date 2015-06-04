package org.irri.breedingtool.rjava.pbtoolsanalysis;

import org.irri.breedingtool.rjava.manager.RJavaManager;

public class TestGS_PGD {

	private static String BSLASH = "\\";
	private static String FSLASH = "/";
	private static String PATH1 = System.getProperty("user.dir")+ System.getProperty("file.separator") + "sample_datasets" + System.getProperty("file.separator");
	public static String DATA_PATH = PATH1.replace(BSLASH, FSLASH);
	
	public static void main(String[] args) {

// from GSDataPrep
		//supply path where output will be saved
		String resultFolderPath = DATA_PATH;  // outputPath = "E:/App Files/workspace_Juno/RJavaManager/sample_datasets"

		String pheno_file = "E:/StarPbtools/GS/data/pg_pheno.csv";
		String geno_file = "E:/StarPbtools/GS/data/pg_geno.csv"; //					# geno_file = "E:/StarPbtools/GS/data/maize_geno2b.csv"
//		String cov_file = "E:/StarPbtools/GS/data/maize_cov.csv";
		String map_file = "NULL"; //"E:/StarPbtools/GS/data/maize_map2.csv";
//					# #create rel_file
		String rel_file = "NULL"; //"E:/StarPbtools/GS/data/wheat_A.p";
////		String # ped_file = "E:/StarPbtools/GS/data/maize_ped.csv" # NULL

		String pFormat = "csv";
		String gFormat = "csv"; 
//		String cFormat = "csv"; 
		String mFormat = "NULL"; //"csv"; // //NULL if no map_file declared 
		String rFormat = "NULL"; //"ttxt";// 
////		String #peFormat = "csv";
					
		RJavaManager rJavaManager= new RJavaManager();
		rJavaManager.initPBtool();
//		rJavaManager.initStar();

		rJavaManager.getWebToolManager().doGSDataPrep(
				resultFolderPath, pheno_file, geno_file,  map_file, rel_file, pFormat, gFormat, mFormat, rFormat); 

		System.out.println("TestGSDataPrep here");
		
// end from GSDataPrep
		
// from GSDataCheck

		//supply path where output will be saved
//		String resultFolderPath = DATA_PATH;  // outputPath = "E:/App Files/workspace_Juno/RJavaManager/sample_datasets"
		String geno_file_DC = resultFolderPath + "genoData.csv"; //fixed filename from GSDataPrep
		int markerFormat = 2;// 5;
		double maxCorr = 0.9;
		double maxMissingP = 0.1;
		double minMAF = 0.05;

//		RJavaManager rJavaManager= new RJavaManager();
//		rJavaManager.initPBtool();

		rJavaManager.getWebToolManager().doGSDataCheck(resultFolderPath, geno_file_DC, markerFormat, maxMissingP, minMAF, maxCorr);
//		geno <- GSDataCheck(outputPath, gsData$genoData, markerFormat, nmiss = maxMissingP, maf = minMAF, cor_threshold = maxCorr)
//		GSDataCheck <- function(outputPath, 
//		                      ori_data=gsData$genoData 
//		                      type =markerFormat
//		                      nmiss = 0.1;maf = 0.05; cor_threshold = 0.90

		System.out.println("TestGSDataCheck end");
		
// end from GSDataCheck
		
// from GSDataImputation
		String geno_file_DI = resultFolderPath + "genoData_qc.csv"; //fixed filename, output of GSDataCheck
		String impType = "random"; //c("random", "family"), 
		String pheno_file_DI = "NULL"; //, 
		String familyTrait = "NULL";
		String packageFormat = "synbreed"; //c("synbreed", "rrBLUP", "BGLR")) 
//		GSDataImputation <- function(outputPath, geno_file, impType = c("random", "family"), pheno_file = NULL, familyTrait = NULL, packageFormat = c("synbreed", "rrBLUP", "BGLR")) {

//		RJavaManager rJavaManager= new RJavaManager();
//		rJavaManager.initPBtool();

		rJavaManager.getWebToolManager().doGSDataImputation(resultFolderPath, geno_file_DI, impType, pheno_file_DI, familyTrait, packageFormat);

		System.out.println("TestGSDataImputation end");

// end from GSDataImputation
		
		
//		//supply path where output will be saved
////		String resultFolderPath = DATA_PATH;  // outputPath = "E:/App Files/workspace_Juno/RJavaManager/sample_datasets"
//
////				String pheno_file = resultFolderPath + "phenoData2V.csv"; //fixed filename, output of GSDataCheck 
////				String geno_file =  resultFolderPath + "synbreed.csv"; //fixed filename, output of GSDataImputation
////				int markerFormat = 3;//c(1, 2, 3), ,
////				String importRel = "FALSE"; 
////				String rel_file = "NULL"; 
////				String rMatType = "t1"; //c("t1", "t2", "t3", "t4"), 
////                String map_file = "NULL"; //# ped_file = NULL, #peFormat = NULL, #data quality check options, ...,
//////                String[] traitNames = {"Trait.1"}; //
////                String[] traitNames = {"Trait.1", "Trait2"};
////                String[] covariates = {"NULL"}; 
////                String doCV = "TRUE"; //"FALSE"; //
//////                String varCompEst = "BL"; //c("BL", "BRR"), 
////                String samplingStrat = "random"; //c("random","within popStruc"),
////                String popStruc_file = "NULL";
////                int nfolds = 5; 
////                int nrep = 2;
//
//		
//		String pheno_file = resultFolderPath + "phenoData.csv"; //fixed filename, output of GSDataCheck 
//		String geno_file =  resultFolderPath + "synbreed.csv"; //fixed filename, output of GSDataImputation
////		int markerFormat = 3;//c(1, 2, 3), ,
//		String importRel = "FALSE"; 
//		String rel_file = "NULL"; 
//		String rMatType = "t1"; //c("t1", "t2", "t3", "t4"), 
//        String map_file = "NULL"; //# ped_file = NULL, #peFormat = NULL, #data quality check options, ...,
////        String[] traitNames = {"Trait.1"}; //
//        String[] traitNames = {"X1", "X2", "X5"}; //{"Trait.1", "Trait2"};
//        String[] covariates = {"NULL"}; 
//        String doCV = "TRUE"; //"FALSE"; //
////        String varCompEst = "BL"; //c("BL", "BRR"), 
//        String samplingStrat = "random"; //c("random","within popStruc"),
//        String popStruc_file = "NULL";
//        int nfolds = 5; 
//        int nrep = 2;
//		
//		
////		//Pedigree-based
////		String doPedigree = "TRUE";
////		//supply format of input file, whether "csv", "ctxt", "stxt", "ttxt", or "sctxt" 
////		String fileFormat = "csv";
////		//supply path and name of input file
////		String fileName = DATA_PATH + "pedFile.csv";
////		//supply type of relationship matrix to create: "dom", "add", "sm-smin","realized","realizedAB","sm","additive"
////		String relType = "dom";
//
////		//Marker-based 
////		String doPedigree = "FALSE";		
////		//supply format of input file, whether "csv", "ctxt", "stxt", "ttxt", or "sctxt" 
////		String fileFormat = "csv";
////		//supply path and name of input file
////		String fileName = DATA_PATH + "ori_file_type3_ed.csv";
////		//supply type of relationship matrix to create: "dom", "add", "sm-smin","realized","realizedAB","sm","additive"
////		String relType = "realized"; //sm-smin";
////		int markerFormat = 3;
////		
////		//supply path and name of text file where relationship matrix is going to be saved
////		String outFileName = "NULL";
////		if (doPedigree == "TRUE") outFileName = "pedigreeRelation_" + relType; //DATA_PATH + 
////		else outFileName = "markerRelation_" + relType; //DATA_PATH + 
//
//		RJavaManager rJavaManager= new RJavaManager();
//		rJavaManager.initPBtool();
//
////		rJavaManager.getWebToolManager().doGBLUP(
////				resultFolderPath, doPedigree, fileFormat, fileName,
////				relType, outFileName, markerFormat);
//		
//		rJavaManager.getWebToolManager().doGBLUP(
//				resultFolderPath, pheno_file, geno_file, 
////				markerFormat, 
//				importRel, rel_file, rMatType, 
//                map_file, traitNames, covariates, doCV, 
////                varCompEst, 
//                samplingStrat, popStruc_file, nfolds, nrep);
//
////			doGBLUP <- function(outputPath, pheno_file, geno_file = NULL, markerFormat = c(1, 2, 3), , 
////	                importRel = FALSE, rel_file = NULL, rMatType = c("t1", "t2", "t3", "t4"), 
////	                map_file = NULL, # ped_file = NULL, #peFormat = NULL, #data quality check options, ...,
////	                traitNames, covariates = NULL, doCV = FALSE, varCompEst = c("BL", "BRR"), samplingStrat = c("random","within popStruc"), nfolds = 2, nrep = 1) {
//
//			System.out.println("TestGBLUP end");

	}

}