package org.irri.breedingtool.rjava.pbtoolsanalysis;

import org.irri.breedingtool.rjava.manager.RJavaManager;

public class TestDiallel2new {

	private static String BSLASH = "\\";
	private static String FSLASH = "/";
	private static String PATH1 = System.getProperty("user.dir")+ System.getProperty("file.separator") + "sample_datasets" + System.getProperty("file.separator");
	public static String DATA_PATH = PATH1.replace(BSLASH, FSLASH);
	
	public static void main(String[] args) {
		
		//supply path and name of active data
//		String dataFileName = DATA_PATH + "Diallel2Alpha_missing.csv";
		//supply path and name of text file where text output is going to be saved
		String outFileName = DATA_PATH + "Diallel2_output.txt";
		String resultFolderPath = DATA_PATH;
		
		//specify parameters
//		String dataFileName = DATA_PATH + "Diallel2Alpha_missing2.csv";
//		String design = "CRD";
//		String[] respvar = {"Y"};
//		String p1 = "par1";
//		String p2 = "par2";
//		String rep = "replication";
//		String block = "NULL";
//		String row = "NULL";
//		String column = "NULL";
//		String cross = "TRUE";
//		String environment = "Trial";
////		String environment = "NULL";
//		String individual = "NULL";
//		String alpha = "0.75";
		
		//specify parameters
//		String dataFileName = DATA_PATH + "Diallel2Alpha_missing2.csv";
//		String design = "RCB";
//		String[] respvar = {"Y2", "Y"};
//		String p1 = "p1";
//		String p2 = "p2";
//		String rep = "NULL";
//		String block = "replication";
//		String row = "NULL";
//		String column = "NULL";
//		String cross = "TRUE";
//		String environment = "Trial";
////		String environment = "NULL";
//		String individual = "NULL";
//		String alpha = "0.80";
		
		//specify parameters
//		String dataFileName = DATA_PATH + "Diallel2Alpha_missing2.csv";
//		String design = "Alpha";
//		String[] respvar = {"Y"};
//		String p1 = "par1";
//		String p2 = "par2";
//		String rep = "replication";
//		String block = "block";
//		String row = "NULL";
//		String column = "NULL";
//		String cross = "TRUE";
//		String environment = "Trial";
//		String individual = "NULL";
//		String alpha = "0.80";
		
		//specify parameters
//		String dataFileName = DATA_PATH + "Diallel2RowCol_missing.csv";
//		
//		String design = "RowColumn";
//		String[] respvar = {"Y"};
//		String p1 = "p1";
//		String p2 = "p2";
//		String rep = "rep";
//		String block = "NULL";
//		String row = "row";
//		String column = "col";
//		String cross = "TRUE";
//		String environment = "Trial";
//		String individual = "NULL";
//		String alpha = "0.80";
		
		//specify parameters
//		String dataFileName = DATA_PATH + "Diallel_M2_missing.csv";
//
//		String design = "CRD";
//		String[] respvar = {"respvar"};
//		String p1 = "p1";
//		String p2 = "p2";
//		String rep = "NULL";
//		String block = "block";
//		String row = "NULL";
//		String column = "NULL";
//		String cross = "TRUE";
//		String environment = "environment";
//		String individual = "NULL";
//		String alpha = "0.80";
		
		//specify parameters
		String dataFileName = DATA_PATH + "Diallel_M2.csv";
		
		String design = "RCB";
		String[] respvar = {"Plant_height", "Grain_yield"};
		String p1 = "P1";
		String p2 = "P2";
		String rep = "NULL";
		String block = "Block";
		String row = "NULL";
		String column = "NULL";
		String cross = "TRUE";
		String environment = "Env";
		String individual = "NULL";
		String alpha = "0.05";
		
		RJavaManager rJavaManager= new RJavaManager();
		rJavaManager.initPBtool();
		rJavaManager.getPbToolAnalysisManager().doDiallel2Test(dataFileName, outFileName, resultFolderPath, design, respvar, p1, p2, rep, block, row, column, cross, individual, environment, alpha);
	}

}