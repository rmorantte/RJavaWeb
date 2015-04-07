package org.irri.breedingtool.rjava.staranalysis;

import org.irri.breedingtool.rjava.manager.RJavaManager;

public class TestLinearRegressionSelection {

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
		String[] depvar = {"PLHT", "GRNYLD"};
		//String[] indepvar = {"PLHT", "PNCLE"};
		String[] indepvar = {"PNCLE", "DFF"};
		boolean constant = true;
		boolean correlate = true;
		String selection = "forward"; //OR: allposs, forward, backward, stepwise
		String selectionStat = "mallow"; //OR: mallow, rsquare, adjrsq
		boolean confInt = false; 
		double confLevel = 95;
		boolean covMatrix = false;  
		String[] normality = {"swilk", "sfrancia"}; //null;
		String[] heteroskedasticity = {"bpagan", "gquandt"} ; // null;
		boolean autoCorr = false; 
		boolean VIF = false;
		boolean COOKS = false;
		boolean leverage = false;
		boolean scatterplot = true;
		
		
		RJavaManager rJavaManager= new RJavaManager();
		rJavaManager.initStar();
		rJavaManager.getSTARAnalysisManager().doLinearRegSelection(dataFileName, 
				outputPath, depvar, indepvar, constant, correlate, selection, 
				selectionStat, confInt, confLevel, covMatrix, 
				normality, heteroskedasticity, autoCorr, VIF, COOKS, leverage, scatterplot);
	}

}
