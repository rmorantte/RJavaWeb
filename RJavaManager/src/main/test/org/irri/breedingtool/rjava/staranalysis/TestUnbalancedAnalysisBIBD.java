package org.irri.breedingtool.rjava.staranalysis;

import org.irri.breedingtool.rjava.manager.RJavaManager;

public class TestUnbalancedAnalysisBIBD {

	private static String BSLASH = "\\";
	private static String FSLASH = "/";
	private static String PATH1 = System.getProperty("user.dir")+ System.getProperty("file.separator") + "sample_datasets" + System.getProperty("file.separator");
	public static String DATA_PATH = PATH1.replace(BSLASH, FSLASH);
	
	public static void main(String[] args) {
		
		//supply path and name of active data
		String dataFileName = DATA_PATH + "BIB_TIME.csv";
		//supply path where the all output will be saved
		String resultFolderPath = DATA_PATH;
		
		//specify parameters
		String design = "BIBD";
		String[] respvars = {"time"};
		String environment = null;
		String factor = "catalyst";
		String block = "batch";
		String rep = null;
		String row = null;
		String column = null;
		boolean descriptiveStat = true; 
		boolean varianceComponents = true;
		// possible values: "none" (pairwise will not be performed)
		//                  "control" (if compare with control is performed)
		//				    "all" (if perform all comparison)
		String performPairwise = "all";
		double levelSig = 0.05;
		// if performPairwise = "none" or "all", control = null
		String[] control = null;
		
		boolean boxplotRawData = true;
		boolean histogramRawData = true;
		boolean heatmapResiduals = true;
		String heatmapRow = "plotx";
		String heatmapColumn = "ploty";
		boolean diagnosticPlot = true;
		
		RJavaManager rJavaManager= new RJavaManager();
		rJavaManager.initStar();
		rJavaManager.getSTARAnalysisManager().doUnbalancedAnalysis(dataFileName, resultFolderPath, design, respvars, environment, factor, block, rep, row, column, descriptiveStat, 
				varianceComponents, performPairwise, levelSig, control, boxplotRawData, histogramRawData, 
				heatmapResiduals, heatmapRow, heatmapColumn, diagnosticPlot);
	}

}