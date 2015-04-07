package org.irri.breedingtool.rjava.datamanipulation;


import org.irri.breedingtool.rjava.manager.RJavaManager;

public class TestMerge {

	private static String BSLASH = "\\";
	private static String FSLASH = "/";
	private static String PATH1 = System.getProperty("user.dir")+ System.getProperty("file.separator") + "sample_datasets" + System.getProperty("file.separator");
	public static String DATA_PATH = PATH1.replace(BSLASH, FSLASH);
	
	public static void main(String[] args) {
		
		//supply path and name of active data
		String dataFileName = DATA_PATH + "data1_merge.csv";
		//supply path and name of transaction data
		String transactionFileName = DATA_PATH + "data2_merge.csv";
		//supply path and name of file where created data is going to be saved
		String newFileName = DATA_PATH + "data1__data2_merge.csv"; 

		String[] keyVariablesMaster={"ID","Env", "Rep"};
		String[] keyVariablesTransact={"Num","Envt", "Plot"};
		String[] otherVariablesMaster={};
		String[] otherVariablesTransact={};
		int option=3;
		
		RJavaManager rJavaManager= new RJavaManager();
		rJavaManager.initStar();
		rJavaManager.getRJavaDataManipulationManager().mergeData(dataFileName, transactionFileName, newFileName, keyVariablesMaster, keyVariablesTransact, otherVariablesMaster, otherVariablesTransact, option);

	}

}
