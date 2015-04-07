package org.irri.breedingtool.rjava.pbtoolsanalysis;

import org.irri.breedingtool.rjava.manager.RJavaManager;

public class TestDiallel3new {

	private static String BSLASH = "\\";
	private static String FSLASH = "/";
	private static String PATH1 = System.getProperty("user.dir")+ System.getProperty("file.separator") + "sample_datasets" + System.getProperty("file.separator");
	public static String DATA_PATH = PATH1.replace(BSLASH, FSLASH);
	
	public static void main(String[] args) {
		
		//supply path and name of active data
		
		//supply path and name of text file where text output is going to be saved
		String outFileName = DATA_PATH + "Diallel3_output.txt";
		String resultFolderPath = DATA_PATH;
		
		//specify parameters
//		String dataFileName = DATA_PATH + "Diallel3Alpha_missing.csv";
//		String design = "CRD";
//		String[] respvar = {"Y"};
//		String p1 = "p1";
//		String p2 = "p2";
//		String rep = "replication";
//		String block = "NULL";
//		String row = "NULL";
//		String column = "NULL";
//		String cross = "TRUE";
//		String environment = "Trial";
//		String individual = "NULL";
//		String alpha = "0.80";
		
		//specify parameters
//		String dataFileName = DATA_PATH + "Diallel3Alpha_missing.csv";
//		String design = "RCB";
//		String[] respvar = {"Y", "Y2"};
//		String p1 = "p1";
//		String p2 = "p2";
//		String rep = "NULL";
//		String block = "replication";
//		String row = "NULL";
//		String column = "NULL";
//		String cross = "TRUE";
//		String environment = "Trial";
//		String individual = "NULL";
//		String alpha = "0.80";
		
		//specify parameters
//		String dataFileName = DATA_PATH + "Diallel3Alpha_missing.csv";
//		String design = "Alpha";
//		String[] respvar = {"Y"};
//		String p1 = "p1";
//		String p2 = "p2";
//		String rep = "replication";
//		String block = "block";
//		String row = "NULL";
//		String column = "NULL";
//		String cross = "TRUE";
//		String environment = "Trial";
//		String individual = "NULL";
//		String alpha = "0.80";
		
		//specify parameters
//		String dataFileName = DATA_PATH + "Diallel3RowCol_missing.csv";
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
//		String dataFileName = DATA_PATH + "Diallel_M3_missing.csv";
//
//		String design = "RCB";
//		String[] respvar = {"Grain_yield"};
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
//		String dataFileName = DATA_PATH + "Reza_M3_test2.csv";
//		
//		String design = "RCB";
//		String[] respvar = {"Th_grain_weight", "Grain_yield"};
//		String p1 = "P1";
//		String p2 = "P2";
//		String rep = "NULL";
//		String block = "Rep";
//		String row = "NULL";
//		String column = "NULL";
//		String cross = "TRUE";
//		String environment = "Env";
//		String individual = "NULL";
//		String alpha = "0.05";
		
		//specify parameters
		String dataFileName = DATA_PATH + "Diallel_M3.csv";
		
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
		rJavaManager.getPbToolAnalysisManager().doDiallel3Test(dataFileName, outFileName, resultFolderPath, design, respvar, p1, p2, rep, block, row, column, cross, individual, environment, alpha);
	}

}