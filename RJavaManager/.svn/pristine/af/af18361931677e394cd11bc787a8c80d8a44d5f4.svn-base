package org.irri.breedingtool.rjava.staranalysis;

import org.irri.breedingtool.rjava.manager.RJavaManager;

public class TestBivariateCorrelation {

	private static String BSLASH = "\\";
	private static String FSLASH = "/";
	private static String PATH1 = System.getProperty("user.dir")+ System.getProperty("file.separator") + "sample_datasets" + System.getProperty("file.separator");
	public static String DATA_PATH = PATH1.replace(BSLASH, FSLASH);
	
	public static void main(String[] args) {
		
		//supply path and name of active data
		String dataFileName = DATA_PATH + "Corr.csv"; 
		//supply path and name of text file where text output is going to be saved
		String outFileName = DATA_PATH; 
		//supply path and name of graph file
		//String graphFileName = DATA_PATH + "Scatterplot_"; 
		
		
		//specify parameters
		String[] respvars = {"GY14", "N", "P", "K"};
		String[] method = {"pearson", "spearman", "kendall"};
		//String[] method = {"pearson"};
		String alternative = "two.sided";
		String statistics = "TRUE";
		String outputPlot = "TRUE";
				
		RJavaManager rJavaManager= new RJavaManager();
		rJavaManager.initStar();
		rJavaManager.getSTARAnalysisManager().doBivariateCorr(dataFileName, outFileName, respvars, method, alternative, 
				statistics, outputPlot);
	}

}
