package org.irri.breedingtool.rjava.staranalysis;

import org.irri.breedingtool.rjava.manager.RJavaManager;

public class TestDescriptiveStatistics {

	private static String BSLASH = "\\";
	private static String FSLASH = "/";
	private static String PATH1 = System.getProperty("user.dir")+ System.getProperty("file.separator") + "sample_datasets" + System.getProperty("file.separator");
	public static String DATA_PATH = PATH1.replace(BSLASH, FSLASH);
	
	public static void main(String[] args) {
		
		//supply path and name of active data
		String dataFileName = DATA_PATH + "Gerua.csv";
		//String dataFileName = DATA_PATH + "testData1.csv"; 
		//supply path and name of text file where text output is going to be saved
		String outFileName = DATA_PATH + "DescOutput.txt"; 
		
		//specify parameters
		String[] respvars = {"DFF", "PLHT", "PNCLE", "GRNYLD"};
		//String[] respvars = {"Y1", "Y2","Y3","Y4","Y5","Y6","Y7","Y8","Y9","Y11","Y12","Y13","Y14","Y15","Y16"};
		// if grp is null
		// String[] grp = null;
		// if grp is not null
		String[] grp = {"TPL", "CULT"};
		//String[] grp = {"var", "wregime", "das","depth"};
		String[] statistics = {"nmiss", "sum", "css", "ucss", "mean", "sd", "var",
				"se.skew", "se.kurtosis", "range", "iqr", "se.mean", "cv",
				"median", "min", "max", "q1", "q3", "skew", "kurtosis"};
		
				
		RJavaManager rJavaManager= new RJavaManager();
		rJavaManager.initStar();
		rJavaManager.getSTARAnalysisManager().doDescriptiveStatistics(dataFileName, outFileName, respvars, grp, statistics);
	}

}
