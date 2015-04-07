package org.irri.breedingtool.rjava.pbtoolsanalysis;

import org.irri.breedingtool.rjava.manager.RJavaManager;

public class TestGenSim {

	private static String BSLASH = "\\";
	private static String FSLASH = "/";
	private static String PATH1 = System.getProperty("user.dir")+ System.getProperty("file.separator") + "sample_datasets" + System.getProperty("file.separator");
	public static String DATA_PATH = PATH1.replace(BSLASH, FSLASH);
	
	public static void main(String[] args) {

		//supply path where output will be saved
		String resultFolderPath = DATA_PATH;  // outputPath = "E:/App Files/workspace_Juno/RJavaManager/sample_datasets"

		//Pedigree-based
		String doPedigree = "TRUE";
		//supply format of input file, whether "csv", "ctxt", "stxt", "ttxt", or "sctxt" 
		String fileFormat = "csv";
		//supply path and name of input file
		String fileName = DATA_PATH + "pedFile.csv";
		//supply type of relationship matrix to create: "dom", "add", "sm-smin","realized","realizedAB","sm","additive"
		String relType = "dom";

//		//Marker-based 
//		String doPedigree = "FALSE";		
//		//supply format of input file, whether "csv", "ctxt", "stxt", "ttxt", or "sctxt" 
//		String fileFormat = "csv";
//		//supply path and name of input file
//		String fileName = DATA_PATH + "ori_file_type3_ed.csv";
//		//supply type of relationship matrix to create: "dom", "add", "sm-smin","realized","realizedAB","sm","additive"
//		String relType = "realized"; //sm-smin";
		int markerFormat = 3;
		
		//supply path and name of text file where relationship matrix is going to be saved
		String outFileName = "NULL";
		if (doPedigree == "TRUE") outFileName = "pedigreeRelation_" + relType; //DATA_PATH + 
		else outFileName = "markerRelation_" + relType; //DATA_PATH + 

		
//		fileName <- "E:/StarPbtools/GS/data/pedFile.csv"
//		fileFormat <- "csv" #"ctxt", "stxt", "ttxt", "sctxt"
//		doPedigree <- TRUE #FALSE
//		relType <- "dom" #"add", "sm-smin","realized","realizedAB","sm","additive"
//		outFileName <- paste("pedigreeRelation_", relType, sep = "")
//
//		fileName <- "E:/StarPbtools/GS/data/ori_file_type3_ed.csv"
//		fileFormat <- "csv" #"ctxt", "stxt", "ttxt", "sctxt"
//		doPedigree <- FALSE #TRUE #
//		relType <- "sm-smin" #,"realized","realizedAB","sm","additive", "dom", "add"
//		outFileName <- paste("markerRelation_", relType, sep = "")
//		markerFormat <- 3
	
		RJavaManager rJavaManager= new RJavaManager();
		rJavaManager.initPBtool();

		rJavaManager.getWebToolManager().doGenSim(
				resultFolderPath, doPedigree, fileFormat, fileName,
				relType, outFileName, markerFormat);
		
		System.out.println("TestGenSim end");



	}

}