package org.irri.breedingtool.rjava.staranalysis;

import org.irri.breedingtool.rjava.manager.RJavaManager;

public class TestContrast {

	private static String BSLASH = "\\";
	private static String FSLASH = "/";
	private static String PATH1 = System.getProperty("user.dir")+ System.getProperty("file.separator") + "sample_datasets" + System.getProperty("file.separator");
	public static String DATA_PATH = PATH1.replace(BSLASH, FSLASH);
	
	public static void main(String[] args) {
		//supply path where the tempANOVAWorkspace.Rda is save
		String inputPath = DATA_PATH; 
		String table1 = DATA_PATH + "das.csv";
		//String table2 = DATA_PATH + "depth.csv";
		
		//specify parameters
		String[] respvar = {"wexr", "rld"};
		String[] factor = {"das", "depth"};
		String[] type = {"user", "control"};
		String[] level = {"2", "5"};
		String[] coef = {table1};
		boolean combined = false;
		
				
		RJavaManager rJavaManager= new RJavaManager();
		rJavaManager.initStar();
		rJavaManager.getSTARAnalysisManager().doContrast(inputPath, 
				respvar, factor, type, level, coef, combined);
	}

}
