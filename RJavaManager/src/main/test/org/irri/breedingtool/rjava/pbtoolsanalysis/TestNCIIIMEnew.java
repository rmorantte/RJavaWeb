package org.irri.breedingtool.rjava.pbtoolsanalysis;

import org.irri.breedingtool.rjava.manager.RJavaManager;

public class TestNCIIIMEnew {

	private static String BSLASH = "\\";
	private static String FSLASH = "/";
	private static String PATH1 = System.getProperty("user.dir")+ System.getProperty("file.separator") + "sample_datasets" + System.getProperty("file.separator");
	public static String DATA_PATH = PATH1.replace(BSLASH, FSLASH);
	
	public static void main(String[] args) {
		
		//supply path and name of active data
		String dataFileName = DATA_PATH + "NC3Alpha.csv";
		//supply path and name of text file where text output is going to be saved
		String outFileName = DATA_PATH + "NCIII_ME_output.txt";
		
		//specify parameters
		String design = "CRD";
		String[] respvars = {"Y"};
		String tester = "fem";
		String f2lines = "male";
		String rep = "replication";
		String block = "NULL";
		String row = "NULL";
		String column = "NULL";
		String environment = "Trial";
		String individual = "NULL";
		
		//specify parameters
//		String design = "RCB";
//		String[] respvars = {"Y2", "Y"};
//		String tester = "fem";
//		String f2lines = "male";
//		String rep = "NULL";
//		String block = "replication";
//		String row = "NULL";
//		String column = "NULL";
//		String environment = "Trial";
//		String individual = "NULL";
		
		//specify parameters
//		String design = "Alpha";
//		String[] respvars = {"Y"};
//		String tester = "fem";
//		String f2lines = "male";
//		String rep = "replication";
//		String block = "block";
//		String row = "NULL";
//		String column = "NULL";
//		String environment = "Trial";
//		String individual = "NULL";
		
		//specify parameters
//		String dataFileName = DATA_PATH + "NC3RowCol.csv";
//		
//		String design = "RowColumn";
//		String[] respvars = {"Y"};
//		String tester = "fem";
//		String f2lines = "male";
//		String rep = "rep";
//		String block = "NULL";
//		String row = "row";
//		String column = "col";
//		String environment = "Trial";
//		String individual = "NULL";
		
		//specify parameters
//		String dataFileName = DATA_PATH + "NCIII_ME_missing.csv";
//		
//		String design = "RCB";
//		String[] respvars = {"Y"};
//		String tester = "Female";
//		String f2lines = "Male";
//		String rep = "NULL";
//		String block = "Block";
//		String row = "NULL";
//		String column = "NULL";
//		String environment = "Env";
//		String individual = "NULL";
		
		RJavaManager rJavaManager= new RJavaManager();
		rJavaManager.initPBtool();
		rJavaManager.getPbToolAnalysisManager().doNC3METest(dataFileName, outFileName, design, respvars, tester, f2lines, rep, block, row, column, individual, environment);
	}

}