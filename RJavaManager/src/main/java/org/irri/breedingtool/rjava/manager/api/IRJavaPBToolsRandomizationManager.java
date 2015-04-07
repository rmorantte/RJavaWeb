package org.irri.breedingtool.rjava.manager.api;


public interface IRJavaPBToolsRandomizationManager {
	
	/**
	 * calls the R statements for designCRD
	 * 
	 * @param dataFileName - path and name where the field book will be save
	 * @param outFileName - path and name of text file where text output is going to be saved
	 * @param factorName - string representing R vector of factor names, 
	 * @param factorLevel - integer representing R vector of factor levels
	 * @param rep - integer representing the number of replicates; 
	 * @param trial - integer representing the number of trials
	 */
	
	public void doDesignCRD(String dataFileName, String outFileName, String[] factorName, Integer[] factorLevel,
			Integer rep, Integer trial);

	/**
	 * calls the R statements for designRCBD
	 * 
	 * @param dataFileName - path and name where the field book will be save
	 * @param outFileName - path and name of text file where text output is going to be saved
	 * @param factorName - string representing R vector of factor names
	 * @param factorLevel - integer representing R vector of factor levels
	 * @param rep - integer representing the number of replicates; 
	 * @param trial - integer representing the number of trials
	 */
	
	public void doDesignRCBD(String dataFileName, String outFileName, String[] factorName, Integer[] factorLevel,
			Integer rep, Integer trial);
	
	/**
	 * calls the R statements for designLSD
	 * 
	 * @param dataFileName - path and name where the field book will be save
	 * @param outFileName - path and name of text file where text output is going to be saved
	 * @param factorName - string representing R vector of factor names
	 * @param factorLevel - integer representing R vector of factor levels
	 * @param trial - integer representing the number of trials
	 */
	
	public void doDesignLSD(String dataFileName, String outFileName, String[] factorName, Integer[] factorLevel,
			Integer trial);
	
	/**
	 * calls the R statements for designSplit
	 * 
	 * @param dataFileName - path and name where the field book will be save
	 * @param outFileName - path and name of text file where text output is going to be saved
	 * @param main - string representing the name of the main plot factor
	 * @param sub - string representing the name of the sub plot factor
	 * @param ssub - NULL or string representing the name of the sub-sub plot factor
	 * @param sssub - NULL or string representing the name of the sub-sub-sub plot factor
	 * @param factorLevel - integer representing R vector of factor levels
	 * @param rep - NULL (if design = "lsd") or an integer representing the number of replicates/block
	 * @param trial - integer representing the number of trials
	 * @param design - a string representing the design structure used
	 * 				 - possible value: "crd", "rcbd" or "lsd"
	 */
	
	public void doDesignSplit(String dataFileName, String outFileName, 
			String main, String sub, String ssub, String sssub,
			Integer[] factorLevel, Integer rep, Integer trial, String design);
	
	/**
	 * calls the R statements for designStrip
	 * 
	 * @param dataFileName - path and name where the field book will be save
	 * @param outFileName - path and name of text file where text output is going to be saved
	 * @param vertical - string representing the name of the vertical factor
	 * @param horizontal - string representing the name of the horizontal factor
	 * @param sub - NULL or string representing the name of the sub plot factor
	 * @param ssub - NULL or string representing the name of the sub-sub plot factor
	 * @param factorLevel - integer representing R vector of factor levels
	 * @param rep - an integer representing the number of replicates/block
	 * @param trial - integer representing the number of trials
	 */
	
	public void doDesignStrip(String dataFileName, String outFileName, 
			String vertical, String horizontal, String sub, String ssub, 
			Integer[] factorLevel, Integer rep, Integer trial);
	
	/**
	 * calls the R statements for designBIBD
	 * 
	 * @param dataFileName - path and name where the field book will be save
	 * @param outFileName - path and name of text file where text output is going to be saved
	 * @param factorName - a string representing the name of the factor
	 * @param factorLevel - an integer representing levels of the factor
	 * @param blkSize - an integer representing the number of plots per block
	 * @param trial - integer representing the number of trials
	 */
	
	public void doDesignBIBD(String dataFileName, String outFileName, 
			Integer numTrmt, Integer blkSize, Integer trial);
	
	/**
	 * calls the R statements for designAugmented
	 * 
	 * @param dataFileName - path and name where the field book will be save
	 * @param outFileName - path and name of text file where text output is going to be saved
	 * @param repTrmt - an integer representing number of levels of the replicated treatment
	 * @param unrepTrmt - an integer representing number of levels of the unreplicated treatment
	 * @param rep - NULL (if design = "lsd") or an integer representing the number of replicates/block
	 * @param trial - integer representing the number of trials
	 * @param design - experimental design used; possible values "rcbd" or "lsd"
	 */
	
	public void doDesignAug(String dataFileName, String outFileName, 
			Integer repTrmt, Integer unrepTrmt, Integer rep, Integer trial,
			String design);
	
	/**
	 * calls the R statements for designLattice
	 * 
	 * @param dataFileName - path and name where the field book will be save
	 * @param outFileName - path and name of text file where text output is going to be saved
	 * @param numTrmt - an integer representing levels of the factor
	 * @param rep - an integer representing the number of replicates
	 * @param trial - integer representing the number of trials
	 */
	
	public void doDesignLattice(String dataFileName, String outFileName, 
			Integer numTrmt, Integer rep, Integer trial);
	
	/**
	 * calls the R statements for designAlphaLattice
	 * 
	 * @param dataFileName - path and name where the field book will be save
	 * @param outFileName - path and name of text file where text output is going to be saved
	 * @param numTrmt - an integer representing levels of the factor
	 * @param blkSize - an integer representing the number of plots per block
	 * @param rep - integer representing the number of replicates
	 * @param trial - integer representing the number of trials
	 */
	
	public void doDesignAlpha(String dataFileName, String outFileName, 
			Integer numTrmt, Integer blkSize, Integer rep, Integer trial);
	
	
}
