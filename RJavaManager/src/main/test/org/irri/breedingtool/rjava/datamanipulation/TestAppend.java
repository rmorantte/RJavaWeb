package org.irri.breedingtool.rjava.datamanipulation;


import org.irri.breedingtool.rjava.manager.RJavaManager;

public class TestAppend {

	private static String BSLASH = "\\";
	private static String FSLASH = "/";
	private static String PATH1 = System.getProperty("user.dir")+ System.getProperty("file.separator") + "sample_datasets" + System.getProperty("file.separator");
	public static String DATA_PATH = PATH1.replace(BSLASH, FSLASH);
	
	public static void main(String[] args) {
		
		// supply the name of the active/master data
		String MName = "data1_merge.csv";
		// supply the name of the transaction data
		String TName = "data2_merge.csv";
		//supply path and name of active data
		String dataFileName = DATA_PATH + "data1_merge.csv";
		//supply path and name of transaction data
		String transactionFileName = DATA_PATH + "data2_merge.csv";
		//supply path and name of file where created data is going to be saved
		String newFileName = DATA_PATH + "data1__data2_append2.csv"; 

		String[] keyVariablesMaster={"ID"};
		String[] keyVariablesTransact={"Num"};
		String[] otherVariablesMaster={};
		String[] otherVariablesTransact={};
		
		RJavaManager rJavaManager= new RJavaManager();
		rJavaManager.initStar();
		rJavaManager.getRJavaDataManipulationManager().appendData(MName, TName, dataFileName, transactionFileName, newFileName, keyVariablesMaster, keyVariablesTransact, otherVariablesMaster, otherVariablesTransact);

	}

}
