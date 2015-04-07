package org.irri.breedingtool.rjava.manager.api;


public interface IRJavaSTARAnalysisManager {

	/**
	 * calls the R statements for performing Descriptive Statistics
	 * 
	 * @param dataFileName - path and name of active data file
	 * @param outFileName - path and name of text file where text output is going to be saved
	 * @param respvar - string representing R vector of response variables, e.g. c("Y1","Y2")
	 * @param grp - string representing R vector of grouping variables, e.g. c("Grp1","Grp2")
	 *            - use "NULL" if the by variable dialog box is empty
	 * @param statistics - string representing R vector of statistics to be computed
	 * 					 - Must be at least one of the following: "n" (no. of obs), "nnmiss" (no. of non missing obs), 
	 * 					   "nmiss" (no. of missing obs.), "sum" (sum of values), "css" (corrected sum of squares), 
	 *					   "ucss" (uncorrected sum of squares), "se.skew" (standard error of skewness), 
	 *					   "se.kurtosis" (standard error of kurtosis), "range" (range), "iqr" (interquartile range), 
	 *					   "var" (variance),  "sd" (standard deviation), "se.mean" (standard error of the mean), 
	 *					   "cv" (coefficient of variation), "mean" (mean), "median" (median), "min" (minimum), 
	 *					   "max" (maximum), "q1" (1st Quartile), "q3" (3rd Quartile), "skew" (skewness) and/or 
	 *					   "kurtosis" (kurtosis).
	 */
	
	public void doDescriptiveStatistics(String dataFileName, String outFileName,
			String[] respvar, String[] grp, String[] statistics);
	
	/**
	 * calls the R statements for performing Cross Tabulation
	 * 
	 * @param dataFileName - path and name of active data file
	 * @param outFileName - path and name of text file where text output is going to be saved
	 * @param rowvar - string representing R vector of response variables, e.g. c("Y1","Y2")
	 * @param colvar - string representing R vector of response variables, e.g. c("Y1","Y2")	 
	 * @param obsFreq - logical; whether observed frequency will be displayed
	 * @param expFreq - logical; whether expected frequency will be displayed
	 * @param stdresid - logical; whether standardize residuals will be displayed
	 * @param totPercent - logical; whether total percentage will be displayed
	 * @param rowPercent - logical; whether row percentage will be displayed
	 * @param colPercent - logical; whether column percentage will be displayed
	 * @param chisq - logical; whether chi-square test will be performed
	 * @param phi - logical; whether phi test will be performed
	 * @param cramersv - logical; whether cramersv will be performed
	 * @param contingency - logical; whether contingency will be performed  
	 */
	
	public void doCrossTab(String dataFileName, String outFileName, String[] rowvar, 
			String[] colvar, boolean expFreq, boolean stdresid, 
			boolean totPercent, boolean rowPercent, boolean colPercent);
	
	
		
	/**
	 * calls the R statements for performing Normality Test
	 * 
	 * @param dataFileName - path and name of active data file
	 * @param outFileName - path and name of text file where text output is going to be saved
	 * @param respvar - string representing R vector of response variables, e.g. c("Y1","Y2")
	 * @param grp - string representing R vector of grouping variables, e.g. c("Grp1","Grp2")
	 *             - use "NULL" if the by variable dialog box is empty
	 * @param method - string representing R vector of normality test to be performed
	 *				 - Must be at least one of the following: "swilk" (shapiro-wilk), 
	 *				   "sfrancia" (shapiro-francia), "ks" (lilliefors[kolmogorov-smirnov]), 
	 *				   "cramer" (cramer-von mises) and/or "anderson" (anderson-darling)
	 * @param outputBoxplot - use "TRUE" if Boxplot graph will be created, "FALSE" otherwise
	 * @param graphBoxFileName - path and name of graph file where the boxplot is going to be saved; do not include the extension
	 * @param outputHist - use "TRUE" if Histogram graph will be created, "FALSE" otherwise
	 * @param graphHistFileName - path and name of graph file where the histogram is going to be saved; do not include the extension
	 */
	
	
	public void doNormality(String dataFileName, String outFileName, String[] respvar,
			String[] grp, String[] method, String outputBoxplot,
			String graphBoxFileName, String outputHist, String graphHistFileName);

	/**
	 * calls the R statements for performing Heteroskedasticity Test
	 * 
	 * @param dataFileName - path and name of active data file
	 * @param outFileName - path and name of text file where text output is going to be saved
	 * @param respvar - string representing R vector of response variables, e.g. c("Y1","Y2")
	 * @param grp - string representing R vector of grouping variables, e.g. c("Grp1","Grp2")
	 *             - use "NULL" if the by variable dialog box is empty
	 * @param method - string representing R vector of normality test to be performed
	 *				 - Must be at least one of the following: "barlett" or "levene"
	 * @param outputBoxplot - use "TRUE" if Boxplot graph will be created, "FALSE" otherwise
	 * @param graphBoxFileName - path and name of graph file where the boxplot is going to be saved; do not include the extension
	 * @param outputHist - use "TRUE" if Histogram graph will be created, "FALSE" otherwise
	 * @param graphHistFileName - path and name of graph file where the histogram is going to be saved; do not include the extension
	 */
	
	public void doHeteroskedasticity(String dataFileName, String outFileName, String[] respvar,
			String[] grp, String[] method, String outputBoxplot,
			String graphBoxFileName, String outputHist, String graphHistFileName);

	/**
	 * calls the R statements for performing One Sample t-test
	 * 
	 * @param dataFileName - path and name of active data file
	 * @param outFileName - path and name of text file where text output is going to be saved
	 * @param respvar - string representing R vector of response variables, e.g. c("Y1","Y2")
	 * @param testValue - a numeric value which represent the hypothesize value
	 * @param altHypo - string representing the alternative hypothesis
	 * 				  - possible value: "less", "greater" or "two.sided"
	 * @param statistics - logical; determines whether summary statistics will be display
	 * @param confInt - logical; determines whether confidence interval will be computed
	 * @param confLevel - a numeric value which determines the level of confidence for computing the interval
	 * 					- valid value is between 90 to 99
	 * @param method - NULL or string representing R vector of normality test to perform
	 *				 - if a string, must be at least one of the following: "swilk" (shapiro-wilk), 
	 *				   "sfrancia" (shapiro-francia), "ks" (lilliefors[kolmogorov-smirnov]), 
	 *				   "cramer" (cramer-von mises) and/or "anderson" (anderson-darling)
	 */
	
	public void doOneMean(String dataFileName, String outFileName,
			String[] respvar, Integer testValue, String altHypo, boolean statistics, 
			boolean confInt, float confLevel, String[] method);
	
	/**
	 * calls the R statements for performing Paired Sample t-test
	 * 
	 * @param dataFileName - path and name of active data file
	 * @param outFileName - path and name of text file where text output is going to be saved
	 * @param varX - string representing R vector of response variables, e.g. c("Y1","Y2")
	 * @param varY - string representing R vector of response variables, e.g. c("Y1","Y2")
	 * @param altHypo - string representing the alternative hypothesis
	 * 				  - possible value: "less", "greater" or "two.sided" (default value)
	 * @param statistics - logical; determines whether summary statistics will be display
	 * @param confInt - logical; determines whether confidence interval will be computed
	 * @param confLevel - a numeric value which determines the level of confidence for computing the interval
	 * 					- valid value is between 90 to 99; default value is 95
	 * @param method - NULL or string representing R vector of normality test to perform
	 *				 - if string, must be at least one of the following: "swilk" (shapiro-wilk), 
	 *				   "sfrancia" (shapiro-francia), "ks" (lilliefors[kolmogorov-smirnov]), 
	 *				   "cramer" (cramer-von mises) and/or "anderson" (anderson-darling)
	 */
	
	public void doPairedMean(String dataFileName, String outFileName,
			String[] varX, String[] varY, String altHypo, boolean statistics, 
			boolean confInt, float confLevel, String[] method);

	/**
	 * calls the R statements for performing Two Independent Sample t-test
	 * 
	 * @param dataFileName - path and name of active data file
	 * @param outFileName - path and name of text file where text output is going to be saved
	 * @param respvar - string representing R vector of response variables, e.g. c("Y1","Y2")
	 * @param grp - string representing R vector of grouping variables, e.g. c("Grp1","Grp2")
	 * @param altHypo - string representing the alternative hypothesis
	 * 				  - possible value: "less", "greater" or "two.sided" (default value)
	 * @param levelSig - a numeric value which determines the level of significance to be used when 
	 * 				     performing test for equality of variance
	 * 				   - valid value is between 0.01 to 0.10; 
	 * @param statistics - logical; determines whether summary statistics will be display
	 * @param confInt - logical; determines whether confidence interval will be computed
	 * @param confLevel - a numeric value which determines the level of confidence for computing the interval
	 * 					- valid value is between 90 to 99
	 * @param method - string representing R vector of normality test to perform
	 *				 - Must be at least one of the following: "swilk" (shapiro-wilk), 
	 *				   "sfrancia" (shapiro-francia), "ks" (lilliefors[kolmogorov-smirnov]), 
	 *				   "cramer" (cramer-von mises) and/or "anderson" (anderson-darling)
	 */
	
	public void doIndependentMean(String dataFileName, String outFileName,
			String[] respvar, String grp, String altHypo, double levelSig, 
			boolean statistics, boolean confInt, float confLevel, 
			String[] method);
	
	/**
	 * calls the R statements for performing Chi-Square Goodness of Fit Test
	 * 
	 * @param dataFileName - path and name of active data file
	 * @param resultFolderPath - path where text output is going to be saved
	 * @param varX - character vector representing the variable of intereset (for procedure == "one" || procedure == "independent") or variable1 for procedure == "paired"
	 * @param varY - NULL (for procedure == "one" && procedure == "independent") or character vector representing the variable2 for procedure == "paired"
	 * @param grp - NULL (for procedure == "one" && procedure == "paired") or a character string representing the grouping variable for procedure == "independent"
	 * @param testVal - a integer vector representing the test value for each variable; default = 0.05
	 * @param categoryEqual - logical; whether all categories have equal expected frequency
	 * @param procedure - type of test procedure to perform
	 * @param alternative - string representing the alternative hypothesis to used
	 */
	
	public void doProportionTest(String dataFileName, String resultFolderPath,
			String procedure, String[] varX, String[] varY, String grp, double[] testVal, 
			String alternative, boolean confInterval, int confLevel);
	
	
	/**
	 * calls the R statements for performing Chi-Square Goodness of Fit Test
	 * 
	 * @param dataFileName - path and name of active data file
	 * @param resultFolderPath - path where text output is going to be saved
	 * @param inputType - type of data input; possible value "raw" (for raw data) or "summary" (for summary data)
	 * @param testvar - a character string representing the test variable	 
	 * @param freqVar - null (if inputType == "raw") or a character string representing the freq variable
	 * @param categoryEqual - logical; whether all categories have equal expected frequency
	 * @param specifiedCategory - a character vector representing the categories of the testvar
	 * @param specifiedExpFreq - a numeric vector representing the expected frequency of each category of the test variable
	 */
	
	public void doChiSqGoodnessOfFit(String dataFileName, 
			String resultFolderPath, String inputType, 
			String testvar, String freqVar,
			boolean categoryEqual, String[] specifiedCategory, 
			int[] specifiedExpFreq);
	
	/**
	 * calls the R statements for performing Chi-Square Test of Independence
	 * 
	 * @param dataFileName - path and name of active data file
	 * @param resultFolderPath - path text output is going to be saved
	 * @param rowvar - string representing R vector of response variables, e.g. c("Y1","Y2")
	 * @param colvar - string representing R vector of response variables, e.g. c("Y1","Y2")	 
	 * @param obsFreq - logical; whether observed frequency will be displayed
	 * @param expFreq - logical; whether expected frequency will be displayed
	 * @param stdresid - logical; whether standardize residuals will be displayed
	 * @param totPercent - logical; whether total percentage will be displayed
	 * @param rowPercent - logical; whether row percentage will be displayed
	 * @param colPercent - logical; whether column percentage will be displayed
	 * @param phi - logical; whether phi test will be performed
	 * @param cramersv - logical; whether cramersv will be performed
	 * @param contingency - logical; whether contingency will be performed  
	 */
	
	public void doChiSqTestOfIndependence(String dataFileName, 
			String resultFolderPath, String inputType, String[] rowvar, String[] colvar, String freqvar, 
			boolean obsFreq, boolean expFreq, boolean stdresid, 
			boolean totPercent, boolean rowPercent, boolean colPercent,
			boolean phi, boolean cramersv, boolean contingency);
	
	/**
	 * calls the R statements for performing Analysis of Variance
	 * (Perform Analysis of Variance per level of the set variable)
	 * 
	 * @param dataFileName - path and name of active data file
	 * @param outputPath - path where the output file (graph, text file, csv) will be saved
	 * @param respvar - string representing R vector of response variables, e.g. c("Y1","Y2")
	 * @param design - design code;
	 * @param factor1 - string representing R vector of factors (design code = CRD or RCBD or LSD) or 
	 * 				    main plot factor (design code = Split Plot Family of Design) 
	 * 					or vertical factor (design code = Strip Plot Family of Design), e.g. c("Grp1","Grp2")
	 * @param factor2 - NULL (if design code = CRD or RCBD or LSD); else  
	 *                - string representing R vector of subplot factor (design code = Split Plot Family of Design) 
	 *                  or horizontal factor (design code = Strip Plot Family of Design), e.g. c("Grp3","Grp4")
	 * @param factor3 - NULL if design code = CRD or RCBD or LSD or Split Plot or Strip Plot; else 
	 *                - string representing R vector of sub-subplot factor (design code = Split-Split Plot Family of Design) 
	 *                  or subplot factor (design code = Strip-Split Plot Family of Design), e.g. c("Grp5","Grp6")
	 * @param factor4 - NULL if design code = CRD or RCBD or LSD or Split Plot or Strip Plot or Split-Split Plot or Strip-Split Plot; else 
	 *                - string representing R vector of sub-sub-subplot factor (design code = Split-Split-Split Plot) 
	 *                  or sub-subplot factor (design code = Strip-Split-Split Plot), e.g. c("Grp7","Grp8")
	 * @param rep1 - NULL (if design == CRD) or a character string representing the replicate or block or the Row block
	 * @param rep2 - NULL (if design == CRD || RCBD) or a character string representing the Column Block
	 * @param set - NULL or a string representing the set variable
	 * @param descriptive - logical
	 * @param normality - logical
	 * @param homogeneity - logical
	 * @param pwtest - string representing the R vector of pairwise mean comparison to be performed
	 * @param pwvar - string representing the R vector of response variable with significant effect
	 * @param contrastOption
	 * @param sig - level of significance
	 */
	
	public String[] doANOVA(String dataFileName, String outputPath, 
			String design, String[] respvar, String[] factor1, 
			String[] factor2, String[] factor3, String[] factor4, 
			String rep1, String rep2, String set, boolean descriptive,
			boolean normality, boolean homogeneity, String[] pwtest, 
			String[] pwvar, boolean contrastOption, double sig);
	
	/**
	 * calls the R statements for performing Combined Analysis of Variance
	 * (Performed Combined ANOVA)
	 * 
	 * @param dataFileName - path and name of active data file
	 * @param outputPath - path where the output file (graph, text file, csv) will be saved
	 * @param respvar - string representing R vector of response variables, e.g. c("Y1","Y2")
	 * @param design - design code;
	 * @param factor1 - string representing R vector of factors (design code = CRD or RCBD or LSD) or 
	 * 				    main plot factor (design code = Split Plot Family of Design) 
	 * 					or vertical factor (design code = Strip Plot Family of Design), e.g. c("Grp1","Grp2")
	 * @param factor2 - NULL (if design code = CRD or RCBD or LSD); else  
	 *                - string representing R vector of subplot factor (design code = Split Plot Family of Design) 
	 *                  or horizontal factor (design code = Strip Plot Family of Design), e.g. c("Grp3","Grp4")
	 * @param factor3 - NULL if design code = CRD or RCBD or LSD or Split Plot or Strip Plot; else 
	 *                - string representing R vector of sub-subplot factor (design code = Split-Split Plot Family of Design) 
	 *                  or subplot factor (design code = Strip-Split Plot Family of Design), e.g. c("Grp5","Grp6")
	 * @param factor4 - NULL if design code = CRD or RCBD or LSD or Split Plot or Strip Plot or Split-Split Plot or Strip-Split Plot; else 
	 *                - string representing R vector of sub-sub-subplot factor (design code = Split-Split-Split Plot) 
	 *                  or sub-subplot factor (design code = Strip-Split-Split Plot), e.g. c("Grp7","Grp8")
	 * @param rep1 - NULL (if design == CRD) or a character string representing the replicate or block or the Row block
	 * @param rep2 - NULL (if design == CRD || RCBD) or a character string representing the Column Block
	 * @param set - string representing the set variable
	 * @param descriptive - logical
	 * @param normality - logical
	 * @param homogeneity - logical
	 * @param pwtest - string representing the R vector of pairwise mean comparison to be performed
	 * @param pwvar - string representing the R vector of response variable with significant effect
	 * @param contrastOption
	 * @param sig - level of significance
	 */
	
	public String[] doANOVACombined(String dataFileName, String outputPath, 
			String design, String[] respvar, String[] factor1, 
			String[] factor2, String[] factor3, String[] factor4, 
			String rep1, String rep2, String set, boolean descriptive,
			boolean normality, boolean homogeneity, String[] pwtest, 
			String[] pwvar, boolean contrastOption, double sig);
	
	/**
	 * calls the R statements for performing Pairwise Mean Comparison
	 * 
	 * @param filePath - path where the tempANOVAWorkspace.Rda was save
	 * @param respvar - string representing R vector of response variables where pairwise mean comparison will be performed, e.g. c("Y1","Y2")
	 * @param method - string representing the R vector of pairwise mean comparison to be performed
	 * @param sig - level of significance
	 * @param combined - logical; true if Combined ANOVA was performed; false - if ANOVA per level of the set variable was performed 
	 */
	
	
	public void doPairwiseANOVA(String filePath, 
			String[] respvar, String[] method, String design, 
			double sig, boolean combined);
	
	/**
	 * calls the R statements for performing Contrast Analysis
	 * 
	 * @param filePath - path where the tempANOVAWorkspace.Rda was save
	 * @param respvar - string representing R vector of response variables where pairwise mean comparison will be performed, e.g. c("Y1","Y2")
	 * @param method - string representing the R vector of pairwise mean comparison to be performed
	 * @param sig - level of significance
	 * @param combined - logical; true if Combined ANOVA was performed; false - if ANOVA per level of the set variable was performed
	 */
	
	public void doContrast(String filePath, String[] respvar,
			String[] factor, String[] type, String[] level, 
			String[] coef, boolean combined);
	
	
	/**
	 * calls the R statements for performing Balanced Incomplete Block Design
	 * 
	 * @param dataFileName - path and name of active data file
	 * @param outputPath - path and name of text file where text output is going to be saved
	 * @param respvar - string representing R vector of response variables, e.g. c("Y1","Y2")
	 * @param treatment - a string representing the name of the treatment
	 * @param block - a string representing the name of the block
	 * @param method - string representing R vector of pairwise mean comparison method to be used;
	 *                 possible values: c("LSD", "HSD", "SNK", "duncan")
	 * @param descriptive - logical; indicate if descriptive statistics will be displayed
	 * @param normality - logical; indicate if normality test will be performed
	 * @param homogeneity - logical; indicate if test for homogeneity will be performed
	 * @param sig - level of significance
	 */
	
	public String[] doBIBD(String dataFileName, String outputPath, 
			String[] respvar, String treatment, String block, String set, String[] method, 
			boolean descriptive, boolean normality, boolean homogeneity, double sig
			);	
	
	
	/**
	 * calls the R statements for printing pairwise mean comparison of Balanced Incomplete Block Design
	 * 
	 * @param filePath - path where the R object (result of the BIBDTest) is saved
	 * @param respvar - string representing R vector of response variables, e.g. c("Y1","Y2")
	 * @param method - string representing R vector of pairwise mean comparison methods;
	 *                 possible values: c("LSD", "HSD", "SNK", "duncan")
	 */
	
	public void doPairwiseBIBD(String filePath, String[] respvar, String[] method);
		
	/**
	 * calls the R statements for Bivariate Correlation Test
	 * 
	 * @param dataFileName - path and name of active data file
	 * @param outFileName - path and name of text file where text output is going to be saved
	 * @param respvar - string representing R vector of response variables, e.g. c("Y1","Y2")
	 * @param method - string representing R vector of correlation coefficient used
	 * 			     - possible values: "pearson", "kendall" and/or "spearman"
	 * @param alternative - string representing the alternative hypothesis used; 
	 * 					  - possible values: "less", "greater" or "two.sided" (not equal)
	 * @param statistics - use "TRUE" if summary statistics will be displayed, "FALSE" otherwise
	 * @param outputPlot - use "TRUE" if a scatterplot will be produced, "FALSE" otherwise
	 * @param graphFileName - path and name of graph file where the graph is going to be saved; do not include the extension
	 */
	
	public void doBivariateCorr(String dataFileName, String outFileName, String[] respvar, String[] method, String alternative, 
			String statistics, String outputPlot);
	
	/**
	 * calls the R statements for Linear Regression Analysis
	 * 
	 * @param dataFileName - path and name of active data file
	 * @param outputPath - path where the output (text, graph and data) will be saved
	 * @param depvar - string representing R vector of dependent variables, e.g. c("Y1","Y2")
	 * @param indepvar - string representing R vector of independent variables, e.g. c("X1","X2", "X3")
	 * @param constant - logical; use "true" if a constant will be included in the equation
	 * @param confInt - logical; use "true" if confidence interval for the regression coefficients will be displayed
	 * @param confLevel - level of confidence; valid value is between 90 to 99; default is 95 (in terms of percent)
	 * @param covMatrix - logical; use "true" if the covariance matrix of the regression coefficient will be displayed 
	 * @param VIF - logical; use "true" if the variance inflation factors will be displayed
	 * @param autoCorr - logical; use "true" Durbin-Watson statistics will be computed
	 * @param heteroskedasticity - null or string representing R vector of test for heteroskedasticity
	 * 							 - if string, must be at least one of the followng: 
	 * 							   "bpagan" (Breusch-Pagar) and/or "gqtest" (Goldfeld-Quandt)
	 * @param normality - null or string representing R vector of normality test to be performed
	 *				    - if string Must be at least one of the following: "swilk" (shapiro-wilk), 
	 *				      "sfrancia" (shapiro-francia), "ks" (lilliefors[kolmogorov-smirnov]), 
	 *				      "cramer" (cramer-von mises) and/or "anderson" (anderson-darling)
	 * @param COOKS - logical; use "true" if Cook's Distance will be saved
	 * @param leverage - logical; use "true" if Leverage Values will be saved
	 */
	
	public void doLinearReg(String dataFileName, String outputPath, 
			String[] depvar, String[] indepvar, boolean constant, 
			boolean confInt, double confLevel, boolean covMatrix, 
			boolean VIF, boolean autoCorr, String[] heteroskedasticity, 
			String[] normality, boolean COOKS, boolean leverage);
	
	
	/**
	 * calls the R statements for Linear Regression Analysis with Selection Method
	 * 
	 * @param dataFileName - path and name of active data file
	 * @param outputPath - path where the output (text, graph and data) will be saved
	 * @param depvar - string representing R vector of dependent variables, e.g. c("Y1","Y2")
	 * @param indepvar - string representing R vector of independent variables, e.g. c("X1","X2", "X3")
	 * @param constant - logical; use "true" if a constant will be included in the equation
 	 * @param correlate - logical; use "true" if a correlation matrix will be displayed
 	 * @param selection - string representing selection method, if any
 	 * 					- must be "none" or one of the following: "allposs" (All possible selection), "forward" (Forward selection),
 	 * 					  "backward" (Backward selection), or "stepwise" (Stepwise regression)
 	 * @param selectionStat - null or string representing selection statistic use, if selection method used is "allposs"
 	 * 					- if string, must be one of the following: "mallow" (Mallow's C(p)), "rsquare" (R-Square), or 
 	 * 					  "adjrsq" (Adjusted R-Square)  
	 * @param confInt - logical; use "true" if confidence interval for the regression coefficients will be displayed
	 * @param confLevel - level of confidence; valid value is between 90 to 99; default is 95 (in terms of percent)
	 * @param covMatrix - logical; use "true" if the covariance matrix of the regression coefficient will be displayed 
	 * @param normality - null or string representing R vector of normality test to be performed
	 *				    - if string Must be at least one of the following: "swilk" (shapiro-wilk), 
	 *				      "sfrancia" (shapiro-francia), "ks" (lilliefors[kolmogorov-smirnov]), 
	 *				      "cramer" (cramer-von mises) and/or "anderson" (anderson-darling)
	 * @param heteroskedasticity - null or string representing R vector of test for heteroskedasticity
	 * 							 - if string, must be at least one of the followng: 
	 * 							   "bpagan" (Breusch-Pagar) and/or "gqtest" (Goldfeld-Quandt)
 	 * @param autoCorr - logical; use "true" Durbin-Watson statistics will be computed
 	 * @param VIF - logical; use "true" if the variance inflation factors will be displayed
	 * @param COOKS - logical; use "true" if Cook's Distance will be saved
	 * @param leverage - logical; use "true" if Leverage Values will be saved
	 * @param scatterplot - logical; use "true" if scatterplots will be created
	 */
	
	public void doLinearRegSelection(String dataFileName, String outputPath, 
			String[] depvar, String[] indepvar, boolean constant, boolean correlate,
			String selection, String selectionStat, boolean confInt, double confLevel, 
			boolean covMatrix, String[] normality, String[] heteroskedasticity,   
			boolean autoCorr, boolean VIF, boolean COOKS, boolean leverage, boolean scatterplot);
	
	/**
	 * calls the R statements for Nonlinear Regression Analysis
	 * 
	 * @param dataFileName - path and name of active data file
	 * @param outputPath - path where the output (text, graph and data) will be saved
	 * @param depvar - string representing R vector of dependent variables, e.g. c("Y1","Y2")
	 * @param indepvar - string representing R independent variable, e.g. "X1"
	 * @param statistics - logical; use "true" if descriptive statistics will be displayed
	 * @param model - string representing type of model to fit
	 * 				- must be one of the following: "quadratic" (Quadratic Polynomial, "cubic" (Cubic Polynomial), 
	 * 				  "logis" (Logistic model), "fpl" (Four-parameter logistic model), "gompertz" ("Gompertz Growth Model), 
	 * 				  "weibull" (Weibull Growth Curve Model), or "custom" for user-defined model
	 * @param modelExp - string representing the model right-hand side expression
	 * @param startValues - string representing the R vector of starting values for the parameters (if a custom model is specified)
	 * @param covMatrix - logical; use "true" if the covariance matrix of the regression coefficient will be displayed 
	 * @param normality - logical; use "true" if the Wilk-Shapiro Test for Normality will be displayed
	 * @param confInterval - logical; use "true" if confidence interval for the regression coefficients will be displayed
	 * @param confLevel - level of confidence; valid value is between 90 to 99; default is 95 (in terms of percent)
	 */
	
	public void doNonlinearRegression(String dataFileName, String outputPath, 
			String[] depvar, String indepvar, boolean statistics, String model, String modelExp, 
			String startValues, boolean covMatrix, boolean normality, boolean confInterval, double confLevel);
	
	/**
	 * calls the R statements for performing signed rank test
	 * (one population)
	 * 
	 * @param dataFileName - path and name of active data file
	 * @param outFileName - path and name of text file where text output is going to be saved
	 * @param respvar - string representing R vector of response variables, e.g. c("Y1","Y2")
	 * @param testValue - a numeric value which represent the hypothesize value
	 * @param altHypo - string representing the alternative hypothesis
	 * 				  - possible value: "less", "greater" or "two.sided"
	 * @param confInt - logical; determines whether confidence interval will be computed
	 * @param confLevel - a numeric value which determines the level of confidence for computing the interval
	 * 					- valid value is between 90 to 99
	 */
	
	public void doOneMedian(String dataFileName, String outFileName,
			String[] respvar, Integer testValue, String altHypo, 
			boolean confInt, float confLevel);
	
	/**
	 * calls the R statements for performing Wilcoxon Signed Rank Test
	 * (two related population non-parametric)
	 * 
	 * @param dataFileName - path and name of active data file
	 * @param outFileName - path and name of text file where text output is going to be saved
	 * @param varX - string representing R vector of response variables, e.g. c("Y1","Y2")
	 * @param varY - string representing R vector of response variables, e.g. c("Y1","Y2")
	 * @param altHypo - string representing the alternative hypothesis
	 * 				  - possible value: "less", "greater" or "two.sided" (default value)
	 * @param confInt - logical; determines whether confidence interval will be computed
	 * @param confLevel - a numeric value which determines the level of confidence for computing the interval
	 * 					- valid value is between 90 to 99; default value is 95
	 */
	
	public void doPairedMedian(String dataFileName, String outFileName,
			String[] varX, String[] varY, String altHypo, 
			boolean confInt, float confLevel);

	/**
	 * calls the R statements for performing Mann-Whitney Test
	 * (two independent population non-parametric)
	 * 
	 * @param dataFileName - path and name of active data file
	 * @param outFileName - path and name of text file where text output is going to be saved
	 * @param respvar - string representing R vector of response variables, e.g. c("Y1","Y2")
	 * @param grp - string representing R vector of grouping variables, e.g. c("Grp1","Grp2")
	 * @param altHypo - string representing the alternative hypothesis
	 * 				  - possible value: "less", "greater" or "two.sided" (default value)
	 * @param confInt - logical; determines whether confidence interval will be computed
	 * @param confLevel - a numeric value which determines the level of confidence for computing the interval
	 * 					- valid value is between 90 to 99
	 */
	
	public void doIndependentMedian(String dataFileName, String outFileName,
			String[] respvar, String grp, String altHypo, boolean confInt, 
			float confLevel);
	
	/**
	 * calls the R statements for performing Kruskal Wallis Test
	 * (several independent population non-parametric)
	 * 
	 * @param dataFileName - path and name of active data file
	 * @param outFileName - path and name of text file where text output is going to be saved
	 * @param respvar - string representing R vector of response variables, e.g. c("Y1","Y2")
	 * @param grp - string representing R vector of grouping variables, e.g. c("Grp1","Grp2")
	 * @param altHypo - string representing the alternative hypothesis
	 * 				  - possible value: "less", "greater" or "two.sided" (default value)
	 * @param confInt - logical; determines whether confidence interval will be computed
	 * @param confLevel - a numeric value which determines the level of confidence for computing the interval
	 * 					- valid value is between 90 to 99
	 */
	
	public void doKruskal(String dataFileName, String outFileName,
			String[] respvar, String[] grp);
	
	/**
	 * calls the R statements for performing Friedman Test
	 * (several related population non-parametric)
	 * 
	 * @param dataFileName - path and name of active data file
	 * @param resultFolderPath - path where text output is going to be saved
	 * @param respvar - string representing R vector of response variables, e.g. c("Y1","Y2")
	 * @param treatment - a character string representing name of the treatment
	 * @param block - a character string representing name of the block
	 */
	
	public void doFriedman(String dataFileName, String resultFolderPath,
			String[] respvar, String treatment, String block);
	
	/**
	 * calls the R statements for performing Single Environment Analysis
	 * 
	 * @param dataFileName - path and name of active data file
	 * @param outFileName - path and name of text file where text output is going to be saved
	 * @param resultFolderPath - path where the generated .csv file and graphs will be saved
	 * @param designIndex - experimental design used: 0 if RCB; 1 if AugRCB; 2 if AugLatinSquare; 3 if AlphaLattice; 4 if Row-Column 
	 * @param respvar - string representing R vector of response variables, e.g. c("Y1","Y2")
	 * @param environment - variable name of the environment variable
	 * @param environmentLevels - levels of the environment variable
	 * @param genotype - name of the genotype factor
	 * @param block - name of the blocking factor
	 * @param rep - name of the replicate factor
	 * @param row - name of the row factor
	 * @param column - name of the column factor
	 * @param descriptiveStat - true if selected 
	 * @param varianceComponents - true if selected
	 * @param boxplotRawData - true if selected
	 * @param histogramRawData - true if selected
	 * @param heatmapResiduals - true if selected
	 * @param heatmapRow - name of the variable assigned as row in the heatmap
	 * @param heatmapColumn - name of the variable assigned as column in the heatmap
	 * @param diagnosticPlot - true if selected
	 * @param genotypeFixed - true if selected
	 * @param performPairwise - true if selected
	 * @param pairwiseAlpha - string, value of level of significance
	 * @param genotypeLevels - levels of genotype factor
	 * @param controlLevels - genotype levels that are set as controls
	 * @param compareControl - true if selected
	 * @param performAllPairwise - true if selected
	 * @param genotypeRandom - true if selected
	 * @param excludeControls - true if selected
	 * @param controlLevels2 - contol levels to exclude
	 * @param genoPhenoCorrelation - true if selected
	 */
	
	public void doUnbalancedAnalysis(String dataFileName, String resultFolderPath, 
			String design, String[] respvars, String environment, String factor,
			String block, String rep, String row, String column, boolean descriptiveStat, 
			boolean varianceComponents, String performPairwise, double levelSig, 
			String[] control, boolean boxplotRawData, boolean histogramRawData, 
			boolean heatmapResiduals, String heatmapRow, String heatmapColumn, 
			boolean diagnosticPlot);
	
	/**
	 * calls the R functions for performing Principal Component Analysis
	 * 
	 * @param dataFileName - path and name of active data file
	 * @param outputPath - path and name of text file where text output is going to be saved
	 * @param var - string representing R vector of selected variables, e.g. c("Y1","Y2")
	 * @param idVar - string represanting the grouping variable
	 * @param descriptiveStat - logical value indicating whether the descriptive statistics will be displayed
	 * @param matx - string representing the matrix to be used whether correlation or covariance.
	 * @param transform - string representing the data transformation to be used e.g. c("zero- centered")
	 * @param saveScore - logical value indicating whether the pc scores will be displayed
	 * @param scree - logical value indicating whether to display scree plot
	 * @param biplot - logical value indicating whether to display biplot
	 * @param pcaplot - logical value indicating whether to display pcaplot
	 * @param useIdVar - logical value indicating whether to use the levels of the ID variable as points in the PCA plot
	 */
		
	public void doPCA( String dataFileName, 
			String outputPath, String[] var, String idVar, boolean descriptiveStat, boolean corMatx, boolean covMatx,
			String matx,String transform, boolean saveScore, boolean scatterMatx, boolean scree, boolean biplot, boolean pcaplot, boolean useIdVar,
			int[] pChars, double[] pSizes, String[] pCol, boolean showLeg,
			String legTitle, String legPos, int legNcol, int axesNum);
	
	/**
	 * calls the R functions for performing Cluster Analysis (agglomerative)
	 * 
	 * @param dataFileName - path and name of active data file
	 * @param outputPath - path and name of text file where text output is going to be saved
	 * @param var - string representing R vector of selected variables
	 * @param idVar - grouping variable.
	 * @param sbinVar - symmetric binary variable(s).
	 * @param abinVar - asymmetric binary variable(s).
	 * @param ofactorVar - ordered factor(s).
	 * @param factorVar - factor(s).
	 * @param stand - logical value indicating whether variables are to be standardized or not
	 * @param distance - string which specifies the distance measure to be used
	 * 				   - Must be one of the following: "Euclidean", "Maximum", "Manhattan", "Minkowski", "Canberra", "Binary", 
	 * 					"Simple Matching","Sokal & Sneath", "Hamann coefficient","Jaccard", "Dice", "Gower"
	 * @param clusmethod - string which specifies the agglomeration method to be used
	 * 					 - Must be one of the following: "Single", "Complete", "Average", "Ward",
	 * 					   and "Centroid"
	 * @param distMatrix - logical value indicating whether distance matrix will be displayed.
	 * @param clusterMem - logical value indicating whether cluster membership will be displayed.
	 * @param descriptiveStat - logical value indicating whether descriptive statistics will be displayed.
	 * @param clusterNum - integer which specifies the desired number of groups or clusters.
	 * 					 - the default value is 2.
	 * @param dendrogram - if true graph of the analysis will be displayed
	 * @param clusterBox - logical value indicating whether clusters will be highlighted in the dendrogram.
	 * @param saveMem - logical value indicating whether membership will be saved to a file.
	 */

	public void doClusterAgglo(String dataFileName, String outputPath, String[] var, String idVar,
			String[] sbinVar, String[] abinVar, String[] ofactorVar, String[] factorVar,
			boolean stand, String distance,String clusmethod, boolean distMatrix,boolean copDistance, 
			boolean clusterMem, boolean descriptiveStatRaw, boolean corMatx, boolean scatterMatx, boolean descriptiveStat, Integer clusterNum, 
			boolean dendrogram, boolean clusterBox, boolean saveMem);


	/**
	 * calls the R functions for performing Cluster Analysis (kmeans)
	 * 
	 * @param dataFileName - path and name of active data file
	 * @param outputPath - path and name of text file where text output is going to be saved
	 * @param var - string representing R vector of selected numeric variables
	 * @param idVar - grouping variable.
	 * @param clusterMem - logical value indicating whether cluster membership will be displayed.
	 * @param descriptiveStat- logical value indicating whether descriptive statistics will be displayed.
	 * @param clusterNum - integer which specifies the desired number of groups or clusters.
	 * 					 - the default value is 2.
	 * @param kgraph - if true graph of the analysis will be displayed
	 * @param saveMem - logical value indicating whether membership will be saved to a file.
	 */	
	
	public void doClusterKmeans(String dataFileName, String outputPath, String[] var, String idVar, boolean clusterMem, boolean descriptiveStatRaw, boolean corMatx, boolean descriptiveStat, 
			Integer clusterNum, boolean kgraph, boolean saveMem);
	
	/**
	 * calls the R statement for performing Cluster Analysis (Divisive)
	 * @param dataFileName - path and name of active data file 
	 * @param outputPath - path and name of text file where text ouput is going to be saved
	 * @param var - string representing R vector of selected variables
	 * @param idVar - grouping variable.
	 * @param sbinVar - symmetric binary variable(s).
	 * @param abinVar - asymmetric binary variable(s).
	 * @param ofactorVar - ordered factor(s).
	 * @param factorVar - factor(s).
	 * @param distance - string which specifies the distance measure to be used
	 * 				   - Must be one of the following: "Euclidean", "Maximum", "Manhattan", "Minkowski", "Canberra", "Binary", 
	 * 					"Simple Matching","Sokal & Sneath", "Hamann coefficient","Jaccard", "Dice", "Gower"
	 * @param distMatrix - logical value indicating whether distance matrix will be displayed.
	 * @param clusterMem - logical value indicating whether cluster membership will be displayed.
	 * @param descriptiveStat - logical value indicating whether descriptive statistics will be displayed.
	 * @param clusterNum - integer which specifies the desired number of groups or clusters.
	 * @param dendrogram - if true dendrogram of the analysis will be displayed.
	 * @param clusterBox - logical value indicating whether clusters will be highlighted in the dendrogram.
	 * @param saveMem - logical value indicating whether membership will be saved to a file.
	 * 
	 */
	
	public void doClusterDivisive(String dataFileName, String outputPath, 
			  String[] var, String idVar, String[] sbinVar, String[] abinVar, String[] ofactorVar, String[] factorVar, 
			  boolean stand, String distance, boolean distMatrix,boolean copDistance,
			  boolean clusterMem, boolean descriptiveStatRaw, boolean corMatx, boolean scatterMatx, boolean descriptiveStat, Integer clusterNum, 
			  boolean dendrogram, boolean clusterBox, boolean saveMem);

	/**
	 * calls the R statement for fitting distribution(s)
	 * @param dataFileName - path and name of active data file 
	 * @param outputPath - path and name of text file where text ouput is going to be saved
	 * @param yVar - string representing selected variable
 	 * @param byVar - string representing grouping variable, if any
	 * @param distrib - string representing R vector of selected distributions
	 * 				   - Must be among the following: "beta", "exp", "gamma", "geom", "lnorm", "logis", "nbinom", "norm", "pois"
	 * @param fitMethod - string representing fitting method to be used
	 * 				   - Must be among the following: "mle", "mme", "mge"
	 * 
	 */

	public void doFitDistribution(String dataFileName, String outputPath, String yVar,
			String byVar, String[] distrib, String fitMethod);

	public void doManova(String dataFileName, String outputPath, String[] yVars,
			String factorVar, String repVar, String testStat,
			String descriptive, String correlate, String doNormalTest,
			String doBoxM, String doSphericity, String saveSSH, String saveSSE);

	public void doMDS(String dataFileName, String outputPath, String inputType,
			String[] vars, String idVar, String type, String distClass,
			String distNonmet, int dimnum, boolean useIdVar, int[] pChars,
			double[] pSizes, String[] pCol, boolean showLeg, String legTitle,
			String legPos, int legNcol, boolean descriptive, boolean correlate);

	
}
