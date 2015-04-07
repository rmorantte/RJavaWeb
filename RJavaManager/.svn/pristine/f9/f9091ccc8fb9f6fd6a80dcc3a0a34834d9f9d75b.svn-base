package org.irri.breedingtool.rjava.staranalysis;

import org.irri.breedingtool.rjava.manager.RJavaManager;

public class TestPairedMean {

	private static String BSLASH = "\\";
	private static String FSLASH = "/";
	private static String PATH1 = System.getProperty("user.dir")+ System.getProperty("file.separator") + "sample_datasets" + System.getProperty("file.separator");
	public static String DATA_PATH = PATH1.replace(BSLASH, FSLASH);
	
	public static void main(String[] args) {
		
		//supply path and name of active data
		String dataFileName = DATA_PATH + "Paired_Yield.csv"; 
		//supply path and name of text file where text output is going to be saved
		String outFileName = DATA_PATH + "Output.txt"; 
		
		//specify parameters
		String[] varX = {"after"};
		String[] varY = {"before"};
		// default value for the alternative hypothesis
		String altHypo = "two.sided";
		boolean statistics = false;
		boolean confInt = false;
		float confLevel = 90;
		// default value for method (test for normality)
		 String[] method = null;
		// case when method is not null
		//String[] method = {"swilk"};
		RJavaManager rJavaManager= new RJavaManager();
		rJavaManager.initStar();
		rJavaManager.getSTARAnalysisManager().doPairedMean(dataFileName, outFileName, varX, varY, altHypo, statistics, confInt, confLevel, method);
	}

}
