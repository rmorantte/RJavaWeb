package org.irri.breedingtool.rjava.staranalysis;

import org.irri.breedingtool.rjava.manager.RJavaManager;

public class TestLinearRegression {

	private static String BSLASH = "\\";
	private static String FSLASH = "/";
	private static String PATH1 = System.getProperty("user.dir")+ System.getProperty("file.separator") + "sample_datasets" + System.getProperty("file.separator");
	public static String DATA_PATH = PATH1.replace(BSLASH, FSLASH);
	
	public static void main(String[] args) {
		
		//supply path and name of active data
		String dataFileName = DATA_PATH + "Gerua.csv"; 
		//supply path and name of text file where text output is going to be saved
		String outputPath = DATA_PATH; 
		
		//specify parameters
		String[] depvar = {"DFF", "GRNYLD"};
		//String[] indepvar = {"PLHT", "PNCLE"};
		String[] indepvar = {"PNCLE"};
		boolean constant = false;
		boolean confInt = false; 
		double confLevel = 95;
		boolean covMatrix = false;  
		boolean VIF = false;
		boolean autoCorr = false; 
		String[] heteroskedasticity = null;
		String[] normality = null; 
		boolean COOKS = false;
		boolean leverage = false;
				
		RJavaManager rJavaManager= new RJavaManager();
		rJavaManager.initStar();
		rJavaManager.getSTARAnalysisManager().doLinearReg(dataFileName, 
				outputPath, depvar, indepvar, constant, confInt, 
				confLevel, covMatrix, VIF, autoCorr, heteroskedasticity, 
				normality, COOKS, leverage);
	}

}
