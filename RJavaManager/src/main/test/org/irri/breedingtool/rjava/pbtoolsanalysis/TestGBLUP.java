package org.irri.breedingtool.rjava.pbtoolsanalysis;

import org.irri.breedingtool.rjava.manager.RJavaManager;

public class TestGBLUP {

	private static String BSLASH = "\\";
	private static String FSLASH = "/";
	private static String PATH1 = System.getProperty("user.dir")+ System.getProperty("file.separator") + "sample_datasets" + System.getProperty("file.separator");
	public static String DATA_PATH = PATH1.replace(BSLASH, FSLASH);
	
	public static void main(String[] args) {

		//supply path where output will be saved
		String resultFolderPath = DATA_PATH;  // outputPath = "E:/App Files/workspace_Juno/RJavaManager/sample_datasets"

//				String pheno_file = resultFolderPath + "phenoData2V.csv"; //fixed filename, output of GSDataCheck 
//				String geno_file =  resultFolderPath + "synbreed.csv"; //fixed filename, output of GSDataImputation
//				int markerFormat = 3;//c(1, 2, 3), ,
//				String importRel = "FALSE"; 
//				String rel_file = "NULL"; 
//				String rMatType = "t1"; //c("t1", "t2", "t3", "t4"), 
//                String map_file = "NULL"; //# ped_file = NULL, #peFormat = NULL, #data quality check options, ...,
////                String[] traitNames = {"Trait.1"}; //
//                String[] traitNames = {"Trait.1", "Trait2"};
//                String[] covariates = {"NULL"}; 
//                String doCV = "TRUE"; //"FALSE"; //
////                String varCompEst = "BL"; //c("BL", "BRR"), 
//                String samplingStrat = "random"; //c("random","within popStruc"),
//                String popStruc_file = "NULL";
//                int nfolds = 5; 
//                int nrep = 2;

		
		String pheno_file = resultFolderPath + "phenoData2V.csv"; //fixed filename, output of GSDataCheck 
		String geno_file =  resultFolderPath + "synbreed.csv"; //fixed filename, output of GSDataImputation
//		int markerFormat = 3;//c(1, 2, 3), ,
		String importRel = "FALSE"; 
		String rel_file = "NULL"; 
		String rMatType = "t1"; //c("t1", "t2", "t3", "t4"), 
        String map_file = "NULL"; //# ped_file = NULL, #peFormat = NULL, #data quality check options, ...,
//        String[] traitNames = {"Trait.1"}; //
        String[] traitNames = {"Trait.1", "Trait2"};
        String[] covariates = {"NULL"}; 
        String doCV = "TRUE"; //"FALSE"; //
//        String varCompEst = "BL"; //c("BL", "BRR"), 
        String samplingStrat = "random"; //c("random","within popStruc"),
        String popStruc_file = "NULL";
        int nfolds = 5; 
        int nrep = 2;
		
		
//		//Pedigree-based
//		String doPedigree = "TRUE";
//		//supply format of input file, whether "csv", "ctxt", "stxt", "ttxt", or "sctxt" 
//		String fileFormat = "csv";
//		//supply path and name of input file
//		String fileName = DATA_PATH + "pedFile.csv";
//		//supply type of relationship matrix to create: "dom", "add", "sm-smin","realized","realizedAB","sm","additive"
//		String relType = "dom";

//		//Marker-based 
//		String doPedigree = "FALSE";		
//		//supply format of input file, whether "csv", "ctxt", "stxt", "ttxt", or "sctxt" 
//		String fileFormat = "csv";
//		//supply path and name of input file
//		String fileName = DATA_PATH + "ori_file_type3_ed.csv";
//		//supply type of relationship matrix to create: "dom", "add", "sm-smin","realized","realizedAB","sm","additive"
//		String relType = "realized"; //sm-smin";
//		int markerFormat = 3;
//		
//		//supply path and name of text file where relationship matrix is going to be saved
//		String outFileName = "NULL";
//		if (doPedigree == "TRUE") outFileName = "pedigreeRelation_" + relType; //DATA_PATH + 
//		else outFileName = "markerRelation_" + relType; //DATA_PATH + 

		RJavaManager rJavaManager= new RJavaManager();
		rJavaManager.initPBtool();

//		rJavaManager.getWebToolManager().doGBLUP(
//				resultFolderPath, doPedigree, fileFormat, fileName,
//				relType, outFileName, markerFormat);
		
		rJavaManager.getWebToolManager().doGBLUP(
				resultFolderPath, pheno_file, geno_file, 
//				markerFormat, 
				importRel, rel_file, rMatType, 
                map_file, traitNames, covariates, doCV, 
//                varCompEst, 
                samplingStrat, popStruc_file, nfolds, nrep);

//			doGBLUP <- function(outputPath, pheno_file, geno_file = NULL, markerFormat = c(1, 2, 3), , 
//	                importRel = FALSE, rel_file = NULL, rMatType = c("t1", "t2", "t3", "t4"), 
//	                map_file = NULL, # ped_file = NULL, #peFormat = NULL, #data quality check options, ...,
//	                traitNames, covariates = NULL, doCV = FALSE, varCompEst = c("BL", "BRR"), samplingStrat = c("random","within popStruc"), nfolds = 2, nrep = 1) {

			System.out.println("TestGBLUP end");

	}

}