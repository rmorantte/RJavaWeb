package org.irri.breedingtool.rjava.staranalysis;

import org.irri.breedingtool.rjava.manager.RJavaManager;

public class TestUnbalancedAnalysisRowColumn {

	private static String BSLASH = "\\";
	private static String FSLASH = "/";
	private static String PATH1 = System.getProperty("user.dir")+ System.getProperty("file.separator") + "sample_datasets" + System.getProperty("file.separator");
	public static String DATA_PATH = PATH1.replace(BSLASH, FSLASH);
	
	public static void main(String[] args) {
		
		//supply path and name of active data
		//String dataFileName = DATA_PATH + "durban.rowcol.csv";
		String dataFileName = DATA_PATH + "rowcol_generated.csv";
		//supply path where the all output will be saved
		String resultFolderPath = DATA_PATH;
		
		//specify parameters
		String design = "RowCol";
		String[] respvars = {"yield"};
		String environment = "env";
		String factor = "gen";
		String block = null;
		String rep = "rep";
		String row = "row";
		String column = "bed";
		boolean descriptiveStat = true; 
		boolean varianceComponents = true;
		// possible values: "none" (pairwise will not be performed)
		//                  "control" (if compare with control is performed)
		//				    "all" (if perform all comparison)
		String performPairwise = "none";
		double levelSig = 0.05;
		// if performPairwise = "none" or "all", control = null
		String[] control = null;
		
		boolean boxplotRawData = true;
		boolean histogramRawData = true;
		boolean heatmapResiduals = true;
		String heatmapRow = "Row";
		String heatmapColumn = "Column";
		boolean diagnosticPlot = true;
		
		RJavaManager rJavaManager= new RJavaManager();
		rJavaManager.initStar();
		rJavaManager.getSTARAnalysisManager().doUnbalancedAnalysis(dataFileName, resultFolderPath, design, respvars, environment, factor, block, rep, row, column, descriptiveStat, 
				varianceComponents, performPairwise, levelSig, control, boxplotRawData, histogramRawData, 
				heatmapResiduals, heatmapRow, heatmapColumn, diagnosticPlot);
	}

}