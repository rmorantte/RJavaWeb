package org.irri.breedingtool.rjava.manager.api;


public interface IRJavaWebToolManager {
	
	
	/**
	 * calls the R statements for performing QTL analysis
	 * 
	 * @param dataFileName - path and name of active data file
	 * @param isInputRawData - true if input is raw data
	 * @param outFileName - path and name of text file where text output is going to be saved
	 * @param resultFolderPath - path where the generated .csv file and graphs will be saved
	 * @param genotypicDataFileName - path and name of genotypic file
	 * @param mapFileName - path and name of map file
	 * @param designIndex - experimental design used: 0 if RCB; 1 if AugRCB; 2 if AugLatinSquare; 3 if AlphaLattice; 4 if Row-Column
	 * @param respvar - string array representing R vector of response variables, e.g. c("Y1","Y2")
	 * @param block - name of the blocking factor; "NULL" if input is NOT raw data
	 * @param rep - name of the replicate factor; "NULL" if input is NOT raw data 
	 * @param row - name of the row factor; "NULL" if input is NOT raw data
	 * @param column - name of the column factor; "NULL" if input is NOT raw data
	 * @param genotype - name of the genotype factor
	 * @param environment - variable name of the environment variable
	 * @param environmentLevels - levels of the environment variable
	 * @param selectedEnvironmentLevel - [ALL] if all levels are selected; or the actual environment level otherwise
	 * @param heterozygousPresent - true if selected 
	 * @param crossType - value is what is selected in GUI
	 * @param step - value is what is selected in GUI
	 * @param windowSize - value is what is selected in GUI
	 * @param minDistance - value is what is selected in GUI
	 * @param qtlMethod - "SIM" or "CIM"
	 * @param thresholdLiJi - true if selected
	 * @param thresholdNumericValue - "NULL" if Li&Ji is selected
	 * @param estimatePlotMarkerMap - true if selected
	 * @param allelicDiffThreshold - value is what is selected in GUI
	 * @param cutOffMissingData - value is what is selected in GUI
	 * @param significanceLevelChiSquare - value is what is selected in GUI
	 */
	
	public void doCreateQTLData(String resultFolderPath, String dataFormat, String format1,
			String crossType, String file1, String format2, String file2,
			String format3, String file3, String P_geno, int bcNum, int fNum);
	
	public void doCheckQTLData(String dataCheckOutFileName, String outFileName, String resultFolderPath, 
			String dataFormat, String format1,
			String crossType, 
			String file1, String format2, String file2, String format3, String file3, String P_geno, 
			int bcNum, int fNum,
			boolean doMissing, boolean deleteMiss, double cutOff,
			boolean doDistortionTest, double pvalCutOff, boolean doCompareGeno,
			double cutoffP, boolean doCheckMarkerOrder, double lodThreshold,
			boolean doCheckGenoErrors, double lodCutOff, double errorProb
//			,
//			String traitType, String[] yVars, String mMethod, double stepCalc,
//			double errCalc, String mapCalc, double lodCutoffM,
//			String phenoModel, String alMethod, int nPermutations,
//			int numCovar, double winSize, String genoName, boolean threshLiJi,
//			double thresholdNumericalValue, double minDist, double stepSize,
//			boolean addModel, int numCofac, boolean mlAlgo, boolean setupModel,
//			boolean includeEpistasis, boolean useDepPrior, int priorMain,
//			int priorAll, String maxQTLs, double priorProb
			);


//	public void doQtl(String outFileName, String resultFolderPath, String dataFormat, String format1, String crossType, String file1, 
//	String format2, String file2, String format3, String file3, String P_geno, int bcNum, int fNum, Boolean doMissing, Boolean deleteMiss, 
//	double cutOff, Boolean doDistortionTest, double pvalCutOff, Boolean doCompareGeno, double cutoffP,
//	Boolean doCheckMarkerOrder, double lodThreshold, Boolean doCheckGenoErrors, double lodCutOff, double errorProb);

	public void doQtl(
			String dataCheckOutFileName, 
			String outFileName, String resultFolderPath, 
			String dataFormat, String format1, 
			String crossType, 
			String file1, String format2, String file2, String format3, String file3, String P_geno, 
			int bcNum, int fNum,
//			boolean doMissing, boolean deleteMiss, double cutOff, boolean doDistortionTest, double pvalCutOff, boolean doCompareGeno, double cutoffP, 
//			boolean doCheckMarkerOrder, double lodThreshold, boolean doCheckGenoErrors, double lodCutOff, double errorProb,
			String traitType, String[] yVars, String mMethod, double stepCalc, double errCalc, String mapCalc, double lodCutoffM, String phenoModel, 
			String alMethod, int nPermutations, int numCovar, double winSize, String genoName, boolean threshLiJi, double thresholdNumericalValue,  
			double minDist, double stepSize, boolean addModel, int numCofac, boolean mlAlgo, boolean setupModel, boolean includeEpistasis, boolean useDepPrior,
	        int priorMain, int priorAll, String maxQTLs, double priorProb);

	public void doGenSim(String resultFolderPath, String doPedigree, String fileFormat, String fileName,
			String relType, String outFileName, int markerFormat);

	public void doGSDataPrep(String resultFolderPath, String pheno_file, String geno_file,  String map_file, String rel_file, String pFormat, 
			String gFormat, String mFormat, String rFormat);

	public void doGSDataCheck(String resultFolderPath, String geno_file, int markerFormat, double maxMissingP, double minMAF, double maxCorr);
	
	public void doGSDataImputation(String resultFolderPath, String geno_file, String impType, String pheno_file, String familyTrait, String packageFormat);

	public void doGBLUP(String resultFolderPath, String pheno_file, String geno_file, 
//			int markerFormat, 
			String importRel, String rel_file, String rMatType, 
			String map_file, String[] traitNames, String[] covariates, String doCV, 
//			String varCompEst, 
			String samplingStrat, String popStruc_file, int nfolds, int nrep);
}