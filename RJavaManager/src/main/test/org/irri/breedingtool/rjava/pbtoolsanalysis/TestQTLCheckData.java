package org.irri.breedingtool.rjava.pbtoolsanalysis;

import org.irri.breedingtool.rjava.manager.RJavaManager;

public class TestQTLCheckData {

	private static String BSLASH = "\\";
	private static String FSLASH = "/";
	private static String PATH1 = System.getProperty("user.dir")+ System.getProperty("file.separator") + "sample_datasets" + System.getProperty("file.separator");
	public static String DATA_PATH = PATH1.replace(BSLASH, FSLASH);
	
	public static void main(String[] args) {
		
		//supply path and name of text file where text output for data quality check is going to be saved
		String dataCheckOutFileName = DATA_PATH + "QTL_dataCheck.txt";
		//supply path and name of text file where text output for analysis is going to be saved
		String outFileName = DATA_PATH + "QTL_output.txt";
		//supply path where the graphs will be saved
		String resultFolderPath = DATA_PATH;  // outputPath = "E:/App Files/workspace_Juno/RJavaManager/sample_datasets"
		//supply data format used - "default", "R", Map Maker", "Map Manager", "QTL Cartographer" 
		String dataFormat = "default";
		//supply format of the first input file, whether "csv", "ctxt", "stxt", "ttxt", or "sctxt" 
		String format1 = "csv";
		//supply type of cross used, whether "f2", "bc", "risib", "riself", "bcsft"
		String crossType = "f2"; 
		//supply path and name of first input file
		String file1 = DATA_PATH + "listeria1_pheno_mv.csv";
		//supply format of the second input file, whether "csv", "ctxt", "stxt", "ttxt", or "sctxt" 
		String format2 = "csv";
		//supply path and name of second input file
		String file2 = DATA_PATH + "listeria1_geno.csv";
		//supply format of the third input file, whether "csv", "ctxt", "stxt", "ttxt", or "sctxt" 
		String format3 = "csv";
		//supply path and name of third input file
		String file3 = DATA_PATH + "listeria1_map.csv";
		//supply name of genotype variable
		String P_geno = "Geno";
		//supply bc generation, if crosstype is "bcsft" 
		int bcNum = 0;
		//supply filial generation, if crosstype is "bcsft"
		int fNum = 0;

//		QTLdata <- createQTLdata(outputPath, dataFormat, format1, crossType, file1, format2, file2, format3, file3, 
//		              P_geno, bcNum, fNum)

		//specify parameters
		boolean doMissing = true; //
		boolean deleteMiss = true;//
		double cutOff= 0.15;
		boolean doDistortionTest = true;//
		double pvalCutOff = 0.01;
		boolean doCompareGeno = true;//
		double cutoffP = 0.70;
		boolean doCheckMarkerOrder = true;
		double lodThreshold = 3;
		boolean doCheckGenoErrors = true;
		double lodCutOff = 5;
		double errorProb = 0.01; 
//	              
//		//IM
//		String traitType = "Binary"; //"Ordinal"; //"Continuous"; //c("Continuous", "Binary", "Ordinal"), yVars, mMethod = c("IM", "CIM", "MQM", "BM")	//reqd
//		String[] yVars = {"T264b", "T264b2"}; //{"T264o", "T264o2", "T264o3"}; //{"T264", "T264d", "T264sd"};																								//reqd
//		String mMethod = "IM"; 																								//reqd
//		double stepCalc = 0; 																									
//		double errCalc = 0.01;																									
//		String mapCalc = "haldane"; //c("haldane","kosambi","c-f","morgan")														
//		double lodCutoffM = 3;																									//reqd																									
//		String phenoModel = "np"; // "binary"; //"normal";//c("normal","binary","2part","np")														
//		String alMethod = "em";//c("em","imp","hk","ehk","mr","mr-imp","mr-argmax")												//reqd
//		int nPermutations = 100;//100																							//reqd
//		int numCovar = 1; 					//3  
//		double winSize = 10;				//10
//		String genoName = "NULL";			//"Geno"
//		boolean threshLiJi = false;			//true
//		double thresholdNumericalValue = 0; //0 
//		double minDist = 10;				//10
//		double stepSize = 5.0;				//5.0
//		boolean addModel = false;			//true
//		int numCofac = 1;					//1
//		boolean mlAlgo = true;				//true
//		boolean setupModel = true; //TRUE																						
//		boolean includeEpistasis = false; //FALSE																				
//		boolean useDepPrior = false; //FALSE,																					
//        int priorMain = 3;																										
//		int priorAll = priorMain + 3;																							
//		String maxQTLs  = "NULL";																								
//		double priorProb = 0.5;																									

		//CIM
//		String traitType = "Continuous"; //c("Continuous", "Binary", "Ordinal"), yVars, mMethod = c("IM", "CIM", "MQM", "BM")	//reqd
//		String[] yVars = {"T264"};																								//reqd
//		String mMethod = "CIM";  																								//reqd
//		double stepCalc = 0; 																									//reqd
//		double errCalc = 0.01;																									//reqd
//		String mapCalc = "haldane"; //c("haldane","kosambi","c-f","morgan")														//reqd
//		double lodCutoffM = 3;																									//reqd
//		String phenoModel = "normal";//c("normal","binary","2part","np")														
//		String alMethod = "em";//c("em","imp","hk","ehk","mr","mr-imp","mr-argmax")												//reqd
//		int nPermutations = 100;//100																							//reqd
//		int numCovar = 3; 					  																					//reqd
//		double winSize = 10;																									//reqd
//		String genoName = "NULL";			//"Geno"
//		boolean threshLiJi = false;			//true
//		double thresholdNumericalValue = 0; //0 
//		double minDist = 10;				//10
//		double stepSize = 5.0;				//5.0
//		boolean addModel = false;			//true
//		int numCofac = 1;					//1
//		boolean mlAlgo = true;				//true
//		boolean setupModel = true; //TRUE																						
//		boolean includeEpistasis = false; //FALSE																				
//		boolean useDepPrior = false; //FALSE,																					
//        int priorMain = 3;																										
//		int priorAll = priorMain + 3;																							
//		String maxQTLs  = "NULL";																								
//		double priorProb = 0.5;																									
		
		//CIM2
//		String traitType = "Continuous"; //c("Continuous", "Binary", "Ordinal"), yVars, mMethod = c("IM", "CIM", "MQM", "BM")	//reqd
//		String[] yVars = {"T264"};																								//reqd
//		String mMethod = "CIM2"; 																								//reqd
//		double stepCalc = 0; 																									
//		double errCalc = 0.01;																									
//		String mapCalc = "haldane"; //c("haldane","kosambi","c-f","morgan")														
//		double lodCutoffM = 3;																									
//		String phenoModel = "normal";//c("normal","binary","2part","np")														
//		String alMethod = "em";//c("em","imp","hk","ehk","mr","mr-imp","mr-argmax")												
//		int nPermutations = 100;//100																							
//		int numCovar = 1; 					//3  																				
//		double winSize = 10;				//10																				//reqd
//		String genoName = "Geno";			//"Geno"																			//reqd
//		boolean threshLiJi = false;			//true																				//reqd
//		double thresholdNumericalValue = 3; //0 																				//reqd
//		double minDist = 10;				//10																				//reqd
//		double stepSize = 5.0;				//5.0																				//reqd
//		boolean addModel = false;			//true
//		int numCofac = 1;					//1
//		boolean mlAlgo = true;				//true
//		boolean setupModel = true; //TRUE																						
//		boolean includeEpistasis = false; //FALSE																				
//		boolean useDepPrior = false; //FALSE,																					
//        int priorMain = 3;																										
//		int priorAll = priorMain + 3;																							
//		String maxQTLs  = "NULL";																								
//		double priorProb = 0.5;																									

		
		//MQM
//		String traitType = "Continuous"; //c("Continuous", "Binary", "Ordinal"), yVars, mMethod = c("IM", "CIM", "MQM", "BM")	//reqd
//		String[] yVars = {"T264"};																								//reqd
//		String mMethod = "MQM"; 																								//reqd
//		double stepCalc = 0; 																									
//		double errCalc = 0.01;																									
//		String mapCalc = "haldane"; //c("haldane","kosambi","c-f","morgan")														
//		double lodCutoffM = 3;																									//reqd																									
//		String phenoModel = "normal";//c("normal","binary","2part","np")														
//		String alMethod = "em";//c("em","imp","hk","ehk","mr","mr-imp","mr-argmax")												
//		int nPermutations = 100;//100																							
//		int numCovar = 1; 					//3  																				
//		double winSize = 10;				//10																				//reqd
//		String genoName = "Geno";			//"Geno"																			
//		boolean threshLiJi = false;			//true																				
//		double thresholdNumericalValue = 3; //0 																				
//		double minDist = 10;				//10																				
//		double stepSize = 5.0;				//5.0																				//reqd
//		boolean addModel = false;			//true																				//reqd
//		int numCofac = 1;					//1																					//reqd
//		boolean mlAlgo = true;				//true																				//reqd
//		boolean setupModel = true; //TRUE																						
//		boolean includeEpistasis = false; //FALSE																				
//		boolean useDepPrior = false; //FALSE,																					
//        int priorMain = 3;																										
//		int priorAll = priorMain + 3;																							
//		String maxQTLs  = "NULL";																								
//		double priorProb = 0.5;																									
	
		//BIM
//		String traitType = "Ordinal"; //"Binary"; //"Continuous"; //c("Continuous", "Binary", "Ordinal"), yVars, mMethod = c("IM", "CIM", "MQM", "BM")	//reqd
//		String[] yVars = {"T264o", "T264o2"}; //{"T264b", "T264b2"}; //{"T264", "T264d", "T264sd"};																								//reqd
//		String mMethod = "BIM"; 																								//reqd
//		double stepCalc = 0; 																									
//		double errCalc = 0.01;																									
//		String mapCalc = "haldane"; //c("haldane","kosambi","c-f","morgan")														
//		double lodCutoffM = 3;																									//reqd																									
//		String phenoModel = "np"; // "binary"; //"normal";//c("normal","binary","2part","np")														
//		String alMethod = "em";//c("em","imp","hk","ehk","mr","mr-imp","mr-argmax")												
//		int nPermutations = 100;//100																							
//		int numCovar = 1; 					//3  																				
//		double winSize = 10;				//10																				//reqd
//		String genoName = "Geno";			//"Geno"																			
//		boolean threshLiJi = false;			//true																				
//		double thresholdNumericalValue = 3; //0 																				
//		double minDist = 10;				//10																				
//		double stepSize = 5.0;				//5.0																				//reqd
//		boolean addModel = false;			//true																				//reqd
//		int numCofac = 1;					//1																					//reqd
//		boolean mlAlgo = true;				//true																				//reqd
//		boolean setupModel = true; //TRUE																						//reqd
//		boolean includeEpistasis = false; //FALSE																				//reqd
//		boolean useDepPrior = false; //FALSE,																					//reqd
//      int priorMain = 3;																										//reqd but has default
//		int priorAll = priorMain + 3;																							//reqd but has default
//		String maxQTLs  = "NULL";																								//reqd but can be NULL
//		double priorProb = 0.5;																									//reqd but has default
				
		RJavaManager rJavaManager= new RJavaManager();
		rJavaManager.initPBtool();
//		rJavaManager.initStar();

//		rJavaManager.getWebToolManager().doQtl(outFileName, resultFolderPath, dataFormat, format1, crossType, file1, 
//				format2, file2, format3, file3, P_geno, bcNum, fNum, doMissing, deleteMiss, cutOff, doDistortionTest, pvalCutOff, doCompareGeno, cutoffP,
//				doCheckMarkerOrder, lodThreshold, doCheckGenoErrors, lodCutOff, errorProb);

//		rJavaManager.getWebToolManager().doQtl(dataCheckOutFileName, outFileName, resultFolderPath, dataFormat, format1, crossType, file1, 
//				format2, file2, format3, file3, P_geno, bcNum, fNum, doMissing, deleteMiss, cutOff, doDistortionTest, pvalCutOff, doCompareGeno, cutoffP,
//				doCheckMarkerOrder, lodThreshold, doCheckGenoErrors, lodCutOff, errorProb, traitType, yVars, mMethod, stepCalc, errCalc, mapCalc, lodCutoffM,
//				phenoModel, alMethod, nPermutations, numCovar, winSize, genoName, threshLiJi, thresholdNumericalValue,  
//				minDist, stepSize, addModel, numCofac, mlAlgo, setupModel, includeEpistasis, useDepPrior, priorMain, priorAll, maxQTLs, priorProb);

		rJavaManager.getWebToolManager().doCheckQTLData(dataCheckOutFileName, outFileName, resultFolderPath, 
				dataFormat, format1, 
				crossType, 
				file1, format2, file2, format3, file3, P_geno, 
				bcNum, fNum, doMissing, deleteMiss, cutOff, doDistortionTest, pvalCutOff, doCompareGeno, cutoffP,
				doCheckMarkerOrder, lodThreshold, doCheckGenoErrors, lodCutOff, errorProb
//		,
//		traitType, yVars, mMethod, stepCalc, errCalc, mapCalc, lodCutoffM,
//		phenoModel, alMethod, nPermutations, numCovar, winSize, genoName, threshLiJi, thresholdNumericalValue,  
//		minDist, stepSize, addModel, numCofac, mlAlgo, setupModel, includeEpistasis, useDepPrior, priorMain, priorAll, maxQTLs, priorProb
		);

		System.out.println("TestQTLCheckData here");

//		(String outFileName, String resultFolderPath, String dataFormat, String format1, String crossType, String file1, 
//		String format2, String file2, String format3, String file3, String P_geno, int bcNum, int fNum)


	}

}