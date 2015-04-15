package org.irri.breedingtool.rjava.manager;

import org.irri.breedingtool.rjava.manager.api.IRJavaPBToolsAnalysisManager;
import org.irri.breedingtool.rjava.manager.api.IRJavaWebToolManager;
import org.irri.breedingtool.rjava.utilities.InputTransform;
//import org.irri.breedingtool.rjava.utilities.InputTransform;
import org.rosuda.JRI.Rengine;

public class WebToolManager implements IRJavaWebToolManager {

	private Rengine rEngine;
	private InputTransform inputTransform;
	
	public WebToolManager(Rengine rEngine) {
		this.rEngine = rEngine;
		this.inputTransform= new InputTransform();
	}
	
	private void rEngineEnd(){
		String rm = "rm(list=ls(all=TRUE))";
		rEngine.eval(rm);
		rEngine.end();
	}
	
	
	@Override
	public void doCreateQTLData(String resultFolderPath, String dataFormat, String format1, String crossType, String file1, 
		String format2, String file2, String format3, String file3, String P_geno, int bcNum, int fNum) {

//		String yVarsVector= inputTransform.createRVector(yVars);
////		String [] respVarMean = new String[respvar.length];
//		
////		String sThresh;
////		double thresholdNumericValue = 0;
////		if (!thresholdLiJi) thresholdNumericValue =  ;

		System.out.println("start QTL");
		System.out.println("resultFolderPath: " + resultFolderPath);
//		System.out.println("outFileName: " + outFileName);
		
		try {
//			String source1 = "source(\'E:/StarPbtools/QTL/irri_new/trimStrings.R\')";
//			String source2 = "source(\'E:/StarPbtools/QTL/irri_new/QTL_dataprep.R\')";
//			String source3 = "source(\'E:/StarPbtools/QTL/irri_new/createQTLdata.R\')";
//			String source4 = "source(\'E:/StarPbtools/QTL/irri_new/checkQTLdata.R\')";
//			String source5 = "source(\'E:/StarPbtools/QTL/irri_new/DA_IWN_PDF.R\')";
//			String source6 = "source(\'E:/StarPbtools/QTL/irri_new/manageQTLmissing.R\')";
//			String source7 = "source(\'E:/StarPbtools/QTL/irri_new/testQTLsegregation.R\')";
//			String source8 = "source(\'E:/StarPbtools/QTL/irri_new/compareGenotypes.R\')";
//			String source9 = "source(\'E:/StarPbtools/QTL/irri_new/checkMarkerOrder.R\')";
//			String source10 = "source(\'E:/StarPbtools/QTL/irri_new/checkGenoError.R\')";
//			String source11 = "source(\'E:/StarPbtools/QTL/irri_new/checkQTLdata.R\')";
//			String source12 = "source(\'E:/StarPbtools/QTL/irri_new/doQTL_IM.R\')";
//			String source13 = "source(\'E:/StarPbtools/QTL/irri_new/doQTL_CIM.R\')";
//			String source14 = "source(\'E:/StarPbtools/QTL/irri_new/doQTL_MQM.R\')";
//			String source15 = "source(\'E:/StarPbtools/QTL/irri_new/doQTL_BIM.R\')";
//			String source16 = "source(\'E:/StarPbtools/QTL/irri_new/doQTLanalysis.R\')"; 
//			String source17 = "source(\'E:/StarPbtools/QTL/irri_new/qtl_cimwur.R\')";
//			
//			System.out.println(source1);
//			System.out.println(source2);
//			System.out.println(source3);
//			System.out.println(source4);
//			System.out.println(source5);
//			System.out.println(source6);
//			System.out.println(source7);
//			System.out.println(source8);
//			System.out.println(source9);
//			System.out.println(source10);
//			System.out.println(source11);
//			System.out.println(source12);
//			System.out.println(source13);
//			System.out.println(source14);
//			System.out.println(source15);
//			System.out.println(source16);
//			System.out.println(source17);
//			
//			rEngine.eval(source1); //rEngine.eval("source(\'E:/StarPbtools/QTL/irri_new/trimStrings.R\')");
//			rEngine.eval(source2);
//			rEngine.eval(source3);
//			rEngine.eval(source4);
//			rEngine.eval(source5);
//			rEngine.eval(source6);
//			rEngine.eval(source7);
//			rEngine.eval(source8);
//			rEngine.eval(source9);
//			rEngine.eval(source10);
//			rEngine.eval(source11);
//			rEngine.eval(source12);
//			rEngine.eval(source13);
//			rEngine.eval(source14);
//			rEngine.eval(source15);
//			rEngine.eval(source16);
//			rEngine.eval(source17);
			
//			rEngine.eval("source(\'E:/StarPbtools/QTL/irri_new/QTL_dataprep.R\')");
//			rEngine.eval("source(\'E:/StarPbtools/QTL/irri_new/createQTLdata.R\')");
//			rEngine.eval("source(\'E:/StarPbtools/QTL/irri_new/checkQTLdata.R\')");
//			rEngine.eval("source(\'E:/StarPbtools/QTL/irri_new/DA_IWN_PDF.R\')");
//			rEngine.eval("source(\'E:/StarPbtools/QTL/irri_new/manageQTLmissing.R\')");
//			rEngine.eval("source(\'E:/StarPbtools/QTL/irri_new/testQTLsegregation.R\')");
//			rEngine.eval("source(\'E:/StarPbtools/QTL/irri_new/compareGenotypes.R\')");
//			rEngine.eval("source(\'E:/StarPbtools/QTL/irri_new/checkMarkerOrder.R\')");
//			rEngine.eval("source(\'E:/StarPbtools/QTL/irri_new/checkGenoError.R\')");
//			rEngine.eval("source(\'E:/StarPbtools/QTL/irri_new/checkQTLdata.R\')");

			rEngine.eval("library(qtl)");
			System.out.println("library(qtl)");
			rEngine.eval("library(lattice)");
			System.out.println("library(lattice)");
			rEngine.eval("library(qtlbim)");
			System.out.println("library(qtlbim)");
			rEngine.eval("library(PBTools)");
			System.out.println("library(PBTools)");

			
			String readData = "QTLdata <- tryCatch(createQTLdata(\"" + resultFolderPath + "\", \"" + dataFormat + "\", \"" + format1 + "\", \"" + crossType + "\", \"" + file1 + "\", \"" +
					format2 + "\", \"" + file2 + "\", \"" + format3 + "\", \"" + file3 + "\", \"" + P_geno + "\", " + bcNum + ", " + fNum + "))";
//			String getData = "crossData = QTLdata$crossObj";
////			String sinkCheckData = "sink(paste(\"" + resultFolderPath + "\",\"markerQC.txt\", sep = \"\"))";
//			String sinkCheckData = "sink(\"" + dataCheckOutFileName + "\")";
//			String checkData = "chkQTLdata <- checkQTLdata(\"" + resultFolderPath + "\", crossData, \"" + crossType + "\", " + bcNum + ", " + fNum + ", " +
//					String.valueOf(doMissing).toUpperCase() + ", " + String.valueOf(deleteMiss).toUpperCase() + ", " + cutOff + ", " + String.valueOf(doDistortionTest).toUpperCase() +
//					", " + pvalCutOff + ", " + String.valueOf(doCompareGeno).toUpperCase() + ", " + cutoffP + ", " + String.valueOf(doCheckMarkerOrder).toUpperCase() + ", " + lodThreshold + ", " + 
//					String.valueOf(doCheckGenoErrors).toUpperCase() + ", " + lodCutOff + ", " + errorProb + ")";
//			//used String.valueOf(deleteMiss).toUpperCase() instead of deleteMiss.toString().toUpperCase() , etc
			
////			String sinkQTL = "sink(paste(\"" + resultFolderPath + "\",\"QTLout_" + mMethod + ".txt\", sep = \"\"))";
//			String sinkQTL = "sink(\"" + outFileName + "\")";

//			String doQTL = null;
//
//			String getBIMdata = null;
//			if (mMethod == "BIM") getBIMdata = "crossData <- qb.genoprob(crossData, map.function = \"" + mapCalc + "\", step = " + stepCalc + ")";
//
//			if (threshLiJi) {
//				doQTL = "runQTL <- doQTLanalysis(\"" + resultFolderPath + "\", crossData, \"" + traitType + "\", " + yVarsVector + ", \"" + mMethod + "\", " + stepCalc + ", " + errCalc + " , \"" +
//						mapCalc + "\", " + lodCutoffM + ", \"" + phenoModel + "\", \"" + alMethod + "\", " + nPermutations + ", " + numCovar + ", " + winSize + ", \"" + genoName + "\", \"thresholdWUR = \"Li&Ji\", " +  
//						minDist + ", " + stepSize + ", " + String.valueOf(addModel).toUpperCase() + ", " + numCofac + ", " + String.valueOf(mlAlgo).toUpperCase() + ", " + String.valueOf(setupModel).toUpperCase() + ", " +  
//						String.valueOf(includeEpistasis).toUpperCase() + ", " + String.valueOf(useDepPrior).toUpperCase() + ", " +
//						priorMain + ", " + priorAll + ", " + maxQTLs + ", " + priorProb + ")";
//			} else {
//				doQTL = "runQTL <- doQTLanalysis(\"" + resultFolderPath + "\", crossData, \"" + traitType + "\", " + yVarsVector + ", \"" + mMethod + "\", " + stepCalc + ", " + errCalc + " , \"" +
//						mapCalc + "\", " + lodCutoffM + ", \"" + phenoModel + "\", \"" + alMethod + "\", " + nPermutations + ", " + numCovar + ", " + winSize + ", \"" + genoName + "\", " + thresholdNumericalValue + ", " +  
//						minDist + ", " + stepSize + ", " + String.valueOf(addModel).toUpperCase() + ", " + numCofac + ", " + String.valueOf(mlAlgo).toUpperCase() + ", " + String.valueOf(setupModel).toUpperCase() + ", " +  
//						String.valueOf(includeEpistasis).toUpperCase() + ", " + String.valueOf(useDepPrior).toUpperCase() + ", " +
//						priorMain + ", " + priorAll + ", " + maxQTLs + ", " + priorProb + ")";
//			}
//			
////			String doQTL = "doQTLanalysis(" + resultFolderPath + "\", crossData, \"" + traitType  + = c("Continuous", "Binary", "Ordinal"), yVars, mMethod = c("IM", "CIM", "MQM", "BM"), 
////                    stepCalc = 0, errCalc = 0.01, mapCalc = c("haldane","kosambi","c-f","morgan"), lodCutoff = 3,
////                    phenoModel = c("normal","binary","2part","np"), alMethod = c("em","imp","hk","ehk"), nPermutations = 100, 
////                    numCovar = 1, winSize = 10, genoName, thresholdWUR =  "Li&Ji", minDist = 10, stepSize = 5.0,
////                    addModel = TRUE, numCofac = 1, mlAlgo = TRUE);
			
			System.out.println(readData);
//			System.out.println(getData);
//			System.out.println(sinkCheckData);
//			System.out.println(checkData);
//			System.out.println("sink()");
//			if (mMethod == "BIM") System.out.println(getBIMdata);				
//			System.out.println(sinkQTL);
//			System.out.println(doQTL);
//			System.out.println("sink()");
			
			rEngine.eval(readData);
//			rEngine.eval(getData);
//			rEngine.eval(sinkCheckData);
//			rEngine.eval(checkData);
//			rEngine.eval("sink()");
//			if (mMethod == "BIM") rEngine.eval(getBIMdata);				
//			rEngine.eval(sinkQTL);
//			rEngine.eval(doQTL);
//			rEngine.eval("sink()");

			rEngineEnd();
			
			System.out.println("reached end.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	@Override
	public void doCheckQTLData(String dataCheckOutFileName, String outFileName, String resultFolderPath, 
			String dataFormat, String format1, 
		String crossType, 
			String file1, String format2, String file2, String format3, String file3, String P_geno, 
		int bcNum, int fNum, boolean doMissing, boolean deleteMiss, double cutOff, boolean doDistortionTest, double pvalCutOff, boolean doCompareGeno, double cutoffP, 
		boolean doCheckMarkerOrder, double lodThreshold, boolean doCheckGenoErrors, double lodCutOff, double errorProb
//		,
//		String traitType, String[] yVars, String mMethod, double stepCalc, double errCalc, String mapCalc, double lodCutoffM, String phenoModel, 
//		String alMethod, int nPermutations, int numCovar, double winSize, String genoName, boolean threshLiJi, double thresholdNumericalValue,  
//		double minDist, double stepSize, boolean addModel, int numCofac, boolean mlAlgo, boolean setupModel, boolean includeEpistasis, boolean useDepPrior,
//        int priorMain, int priorAll, String maxQTLs, double priorProb
        ) {

//		String yVarsVector= inputTransform.createRVector(yVars);
////		String [] respVarMean = new String[respvar.length];
////		String sThresh;
////		double thresholdNumericValue = 0;
////		if (!thresholdLiJi) thresholdNumericValue =  ;

		System.out.println("start QTL");
		System.out.println("resultFolderPath: " + resultFolderPath);
		System.out.println("outFileName: " + outFileName);
		
		try {
//			String source1 = "source(\'E:/StarPbtools/QTL/irri_new/trimStrings.R\')";
//			String source2 = "source(\'E:/StarPbtools/QTL/irri_new/QTL_dataprep.R\')";
//			String source3 = "source(\'E:/StarPbtools/QTL/irri_new/createQTLdata.R\')";
//			String source4 = "source(\'E:/StarPbtools/QTL/irri_new/checkQTLdata.R\')";
//			String source5 = "source(\'E:/StarPbtools/QTL/irri_new/DA_IWN_PDF.R\')";
//			String source6 = "source(\'E:/StarPbtools/QTL/irri_new/manageQTLmissing.R\')";
//			String source7 = "source(\'E:/StarPbtools/QTL/irri_new/testQTLsegregation.R\')";
//			String source8 = "source(\'E:/StarPbtools/QTL/irri_new/compareGenotypes.R\')";
//			String source9 = "source(\'E:/StarPbtools/QTL/irri_new/checkMarkerOrder.R\')";
//			String source10 = "source(\'E:/StarPbtools/QTL/irri_new/checkGenoError.R\')";
//			String source11 = "source(\'E:/StarPbtools/QTL/irri_new/checkQTLdata.R\')";
//			String source12 = "source(\'E:/StarPbtools/QTL/irri_new/doQTL_IM.R\')";
//			String source13 = "source(\'E:/StarPbtools/QTL/irri_new/doQTL_CIM.R\')";
//			String source14 = "source(\'E:/StarPbtools/QTL/irri_new/doQTL_MQM.R\')";
//			String source15 = "source(\'E:/StarPbtools/QTL/irri_new/doQTL_BIM.R\')";
//			String source16 = "source(\'E:/StarPbtools/QTL/irri_new/doQTLanalysis.R\')"; 
//			String source17 = "source(\'E:/StarPbtools/QTL/irri_new/qtl_cimwur.R\')";
//			
//			System.out.println(source1);
//			System.out.println(source2);
//			System.out.println(source3);
//			System.out.println(source4);
//			System.out.println(source5);
//			System.out.println(source6);
//			System.out.println(source7);
//			System.out.println(source8);
//			System.out.println(source9);
//			System.out.println(source10);
//			System.out.println(source11);
//			System.out.println(source12);
//			System.out.println(source13);
//			System.out.println(source14);
//			System.out.println(source15);
//			System.out.println(source16);
//			System.out.println(source17);
//			
//			rEngine.eval(source1); //rEngine.eval("source(\'E:/StarPbtools/QTL/irri_new/trimStrings.R\')");
//			rEngine.eval(source2);
//			rEngine.eval(source3);
//			rEngine.eval(source4);
//			rEngine.eval(source5);
//			rEngine.eval(source6);
//			rEngine.eval(source7);
//			rEngine.eval(source8);
//			rEngine.eval(source9);
//			rEngine.eval(source10);
//			rEngine.eval(source11);
//			rEngine.eval(source12);
//			rEngine.eval(source13);
//			rEngine.eval(source14);
//			rEngine.eval(source15);
//			rEngine.eval(source16);
//			rEngine.eval(source17);
			
//			rEngine.eval("source(\'E:/StarPbtools/QTL/irri_new/QTL_dataprep.R\')");
//			rEngine.eval("source(\'E:/StarPbtools/QTL/irri_new/createQTLdata.R\')");
//			rEngine.eval("source(\'E:/StarPbtools/QTL/irri_new/checkQTLdata.R\')");
//			rEngine.eval("source(\'E:/StarPbtools/QTL/irri_new/DA_IWN_PDF.R\')");
//			rEngine.eval("source(\'E:/StarPbtools/QTL/irri_new/manageQTLmissing.R\')");
//			rEngine.eval("source(\'E:/StarPbtools/QTL/irri_new/testQTLsegregation.R\')");
//			rEngine.eval("source(\'E:/StarPbtools/QTL/irri_new/compareGenotypes.R\')");
//			rEngine.eval("source(\'E:/StarPbtools/QTL/irri_new/checkMarkerOrder.R\')");
//			rEngine.eval("source(\'E:/StarPbtools/QTL/irri_new/checkGenoError.R\')");
//			rEngine.eval("source(\'E:/StarPbtools/QTL/irri_new/checkQTLdata.R\')");

			rEngine.eval("library(qtl)");
			System.out.println("library(qtl)");
			rEngine.eval("library(lattice)");
			System.out.println("library(lattice)");
			rEngine.eval("library(qtlbim)");
			System.out.println("library(qtlbim)");
			rEngine.eval("library(PBTools)");
			System.out.println("library(PBTools)");

			
			String readData = "QTLdata <- tryCatch(createQTLdata(\"" + resultFolderPath + "\", \"" + dataFormat + "\", \"" + format1 + "\", \"" + crossType + "\", \"" + file1 + "\", \"" +
					format2 + "\", \"" + file2 + "\", \"" + format3 + "\", \"" + file3 + "\", \"" + P_geno + "\", " + bcNum + ", " + fNum + "))";
			String getData = "crossData = QTLdata$crossObj";
//			String sinkCheckData = "sink(paste(\"" + resultFolderPath + "\",\"markerQC.txt\", sep = \"\"))";
			
//			String getQTLdata = null;
//			if (crossType == "bcsft") getQTLdata = "crossData <- read.cross(format = \"csv\", file = paste(\"" + resultFolderPath + "\", \"crossData.csv\", sep = \"\"), crosstype = \"" + crossType + "\", BC.gen = " + bcNum + ", F.gen = " + fNum + ")";
//			else getQTLdata = "crossData <- read.cross(format = \"csv\", file = paste(\"" + resultFolderPath + "\", \"crossData.csv\", sep = \"\"), crosstype = \"" + crossType + "\")";

			String sinkCheckData = "sink(\"" + dataCheckOutFileName + "\")";
			String checkData = "chkQTLdata <- checkQTLdata(\"" + resultFolderPath + "\", crossData, \"" + crossType + "\", " + bcNum + ", " + fNum + ", " +
					String.valueOf(doMissing).toUpperCase() + ", " + String.valueOf(deleteMiss).toUpperCase() + ", " + cutOff + ", " + String.valueOf(doDistortionTest).toUpperCase() +
					", " + pvalCutOff + ", " + String.valueOf(doCompareGeno).toUpperCase() + ", " + cutoffP + ", " + String.valueOf(doCheckMarkerOrder).toUpperCase() + ", " + lodThreshold + ", " + 
					String.valueOf(doCheckGenoErrors).toUpperCase() + ", " + lodCutOff + ", " + errorProb + ")";
			//used String.valueOf(deleteMiss).toUpperCase() instead of deleteMiss.toString().toUpperCase() , etc
			
////			String sinkQTL = "sink(paste(\"" + resultFolderPath + "\",\"QTLout_" + mMethod + ".txt\", sep = \"\"))";
//			String sinkQTL = "sink(\"" + outFileName + "\")";

//			String doQTL = null;
//
//			String getBIMdata = null;
//			if (mMethod == "BIM") getBIMdata = "crossData <- qb.genoprob(crossData, map.function = \"" + mapCalc + "\", step = " + stepCalc + ")";
//
//			if (threshLiJi) {
//				doQTL = "runQTL <- doQTLanalysis(\"" + resultFolderPath + "\", crossData, \"" + traitType + "\", " + yVarsVector + ", \"" + mMethod + "\", " + stepCalc + ", " + errCalc + " , \"" +
//						mapCalc + "\", " + lodCutoffM + ", \"" + phenoModel + "\", \"" + alMethod + "\", " + nPermutations + ", " + numCovar + ", " + winSize + ", \"" + genoName + "\", \"thresholdWUR = \"Li&Ji\", " +  
//						minDist + ", " + stepSize + ", " + String.valueOf(addModel).toUpperCase() + ", " + numCofac + ", " + String.valueOf(mlAlgo).toUpperCase() + ", " + String.valueOf(setupModel).toUpperCase() + ", " +  
//						String.valueOf(includeEpistasis).toUpperCase() + ", " + String.valueOf(useDepPrior).toUpperCase() + ", " +
//						priorMain + ", " + priorAll + ", " + maxQTLs + ", " + priorProb + ")";
//			} else {
//				doQTL = "runQTL <- doQTLanalysis(\"" + resultFolderPath + "\", crossData, \"" + traitType + "\", " + yVarsVector + ", \"" + mMethod + "\", " + stepCalc + ", " + errCalc + " , \"" +
//						mapCalc + "\", " + lodCutoffM + ", \"" + phenoModel + "\", \"" + alMethod + "\", " + nPermutations + ", " + numCovar + ", " + winSize + ", \"" + genoName + "\", " + thresholdNumericalValue + ", " +  
//						minDist + ", " + stepSize + ", " + String.valueOf(addModel).toUpperCase() + ", " + numCofac + ", " + String.valueOf(mlAlgo).toUpperCase() + ", " + String.valueOf(setupModel).toUpperCase() + ", " +  
//						String.valueOf(includeEpistasis).toUpperCase() + ", " + String.valueOf(useDepPrior).toUpperCase() + ", " +
//						priorMain + ", " + priorAll + ", " + maxQTLs + ", " + priorProb + ")";
//			}
//			
////			String doQTL = "doQTLanalysis(" + resultFolderPath + "\", crossData, \"" + traitType  + = c("Continuous", "Binary", "Ordinal"), yVars, mMethod = c("IM", "CIM", "MQM", "BM"), 
////                    stepCalc = 0, errCalc = 0.01, mapCalc = c("haldane","kosambi","c-f","morgan"), lodCutoff = 3,
////                    phenoModel = c("normal","binary","2part","np"), alMethod = c("em","imp","hk","ehk"), nPermutations = 100, 
////                    numCovar = 1, winSize = 10, genoName, thresholdWUR =  "Li&Ji", minDist = 10, stepSize = 5.0,
////                    addModel = TRUE, numCofac = 1, mlAlgo = TRUE);
			
			System.out.println(readData);
			System.out.println(getData);
//			System.out.println(getQTLdata);
			System.out.println(sinkCheckData);
			System.out.println(checkData);
			System.out.println("sink()");
//			if (mMethod == "BIM") System.out.println(getBIMdata);				
//			System.out.println(sinkQTL);
//			System.out.println(doQTL);
//			System.out.println("sink()");
			
			rEngine.eval(readData);
			rEngine.eval(getData);
//			rEngine.eval(getQTLdata);
			rEngine.eval(sinkCheckData);
			rEngine.eval(checkData);
			rEngine.eval("sink()");
//			if (mMethod == "BIM") rEngine.eval(getBIMdata);				
//			rEngine.eval(sinkQTL);
//			rEngine.eval(doQTL);
//			rEngine.eval("sink()");

			rEngineEnd();
			
			System.out.println("reached end.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	@Override
	public void doQtl(
			String dataCheckOutFileName, 
			String outFileName, String resultFolderPath, 
			String dataFormat, String format1, 
			String crossType, 
			String file1, String format2, String file2, String format3, String file3, String P_geno, 
			int bcNum, int fNum,
//		boolean doMissing, boolean deleteMiss, double cutOff, boolean doDistortionTest, double pvalCutOff, boolean doCompareGeno, double cutoffP, 
//		boolean doCheckMarkerOrder, double lodThreshold, boolean doCheckGenoErrors, double lodCutOff, double errorProb,
		String traitType, String[] yVars, String mMethod, double stepCalc, double errCalc, String mapCalc, double lodCutoffM, String phenoModel, 
		String alMethod, int nPermutations, int numCovar, double winSize, String genoName, boolean threshLiJi, double thresholdNumericalValue,  
		double minDist, double stepSize, boolean addModel, int numCofac, boolean mlAlgo, boolean setupModel, boolean includeEpistasis, boolean useDepPrior,
        int priorMain, int priorAll, String maxQTLs, double priorProb) {

		String yVarsVector= inputTransform.createRVector(yVars);
//		String [] respVarMean = new String[respvar.length];
		
//		String sThresh;
//		double thresholdNumericValue = 0;
//		if (!thresholdLiJi) thresholdNumericValue =  ;

		System.out.println("start QTL");
		System.out.println("resultFolderPath: " + resultFolderPath);
		System.out.println("outFileName: " + outFileName);
		
		try {
//			String source1 = "source(\'E:/StarPbtools/QTL/irri_new/trimStrings.R\')";
//			String source2 = "source(\'E:/StarPbtools/QTL/irri_new/QTL_dataprep.R\')";
//			String source3 = "source(\'E:/StarPbtools/QTL/irri_new/createQTLdata.R\')";
//			String source4 = "source(\'E:/StarPbtools/QTL/irri_new/checkQTLdata.R\')";
//			String source5 = "source(\'E:/StarPbtools/QTL/irri_new/DA_IWN_PDF.R\')";
//			String source6 = "source(\'E:/StarPbtools/QTL/irri_new/manageQTLmissing.R\')";
//			String source7 = "source(\'E:/StarPbtools/QTL/irri_new/testQTLsegregation.R\')";
//			String source8 = "source(\'E:/StarPbtools/QTL/irri_new/compareGenotypes.R\')";
//			String source9 = "source(\'E:/StarPbtools/QTL/irri_new/checkMarkerOrder.R\')";
//			String source10 = "source(\'E:/StarPbtools/QTL/irri_new/checkGenoError.R\')";
//			String source11 = "source(\'E:/StarPbtools/QTL/irri_new/checkQTLdata.R\')";
//			String source12 = "source(\'E:/StarPbtools/QTL/irri_new/doQTL_IM.R\')";
//			String source13 = "source(\'E:/StarPbtools/QTL/irri_new/doQTL_CIM.R\')";
//			String source14 = "source(\'E:/StarPbtools/QTL/irri_new/doQTL_MQM.R\')";
//			String source15 = "source(\'E:/StarPbtools/QTL/irri_new/doQTL_BIM.R\')";
//			String source16 = "source(\'E:/StarPbtools/QTL/irri_new/doQTLanalysis.R\')"; 
//			String source17 = "source(\'E:/StarPbtools/QTL/irri_new/qtl_cimwur.R\')";
//			
//			System.out.println(source1);
//			System.out.println(source2);
//			System.out.println(source3);
//			System.out.println(source4);
//			System.out.println(source5);
//			System.out.println(source6);
//			System.out.println(source7);
//			System.out.println(source8);
//			System.out.println(source9);
//			System.out.println(source10);
//			System.out.println(source11);
//			System.out.println(source12);
//			System.out.println(source13);
//			System.out.println(source14);
//			System.out.println(source15);
//			System.out.println(source16);
//			System.out.println(source17);
//			
//			rEngine.eval(source1); //rEngine.eval("source(\'E:/StarPbtools/QTL/irri_new/trimStrings.R\')");
//			rEngine.eval(source2);
//			rEngine.eval(source3);
//			rEngine.eval(source4);
//			rEngine.eval(source5);
//			rEngine.eval(source6);
//			rEngine.eval(source7);
//			rEngine.eval(source8);
//			rEngine.eval(source9);
//			rEngine.eval(source10);
//			rEngine.eval(source11);
//			rEngine.eval(source12);
//			rEngine.eval(source13);
//			rEngine.eval(source14);
//			rEngine.eval(source15);
//			rEngine.eval(source16);
//			rEngine.eval(source17);
			
//			rEngine.eval("source(\'E:/StarPbtools/QTL/irri_new/QTL_dataprep.R\')");
//			rEngine.eval("source(\'E:/StarPbtools/QTL/irri_new/createQTLdata.R\')");
//			rEngine.eval("source(\'E:/StarPbtools/QTL/irri_new/checkQTLdata.R\')");
//			rEngine.eval("source(\'E:/StarPbtools/QTL/irri_new/DA_IWN_PDF.R\')");
//			rEngine.eval("source(\'E:/StarPbtools/QTL/irri_new/manageQTLmissing.R\')");
//			rEngine.eval("source(\'E:/StarPbtools/QTL/irri_new/testQTLsegregation.R\')");
//			rEngine.eval("source(\'E:/StarPbtools/QTL/irri_new/compareGenotypes.R\')");
//			rEngine.eval("source(\'E:/StarPbtools/QTL/irri_new/checkMarkerOrder.R\')");
//			rEngine.eval("source(\'E:/StarPbtools/QTL/irri_new/checkGenoError.R\')");
//			rEngine.eval("source(\'E:/StarPbtools/QTL/irri_new/checkQTLdata.R\')");

			rEngine.eval("library(qtl)");
			System.out.println("library(qtl)");
			rEngine.eval("library(lattice)");
			System.out.println("library(lattice)");
			rEngine.eval("library(qtlbim)");
			System.out.println("library(qtlbim)");
			rEngine.eval("library(PBTools)");
			System.out.println("library(PBTools)");

			
			String readData = "QTLdata <- tryCatch(createQTLdata(\"" + resultFolderPath + "\", \"" + dataFormat + "\", \"" + format1 + "\", \"" + crossType + "\", \"" + file1 + "\", \"" +
					format2 + "\", \"" + file2 + "\", \"" + format3 + "\", \"" + file3 + "\", \"" + P_geno + "\", " + bcNum + ", " + fNum + "))";
			String getData = "crossData = QTLdata$crossObj";
////			String sinkCheckData = "sink(paste(\"" + resultFolderPath + "\",\"markerQC.txt\", sep = \"\"))";

//			String sinkCheckData = "sink(\"" + dataCheckOutFileName + "\")";
//			String checkData = "chkQTLdata <- checkQTLdata(\"" + resultFolderPath + "\", crossData, \"" + crossType + "\", " + bcNum + ", " + fNum + ", " +
//					String.valueOf(doMissing).toUpperCase() + ", " + String.valueOf(deleteMiss).toUpperCase() + ", " + cutOff + ", " + String.valueOf(doDistortionTest).toUpperCase() +
//					", " + pvalCutOff + ", " + String.valueOf(doCompareGeno).toUpperCase() + ", " + cutoffP + ", " + String.valueOf(doCheckMarkerOrder).toUpperCase() + ", " + lodThreshold + ", " + 
//					String.valueOf(doCheckGenoErrors).toUpperCase() + ", " + lodCutOff + ", " + errorProb + ")";
//			//used String.valueOf(deleteMiss).toUpperCase() instead of deleteMiss.toString().toUpperCase() , etc
//			
////			String sinkQTL = "sink(paste(\"" + resultFolderPath + "\",\"QTLout_" + mMethod + ".txt\", sep = \"\"))";

//			String getQTLdata = null;
//			if (crossType == "bcsft") getQTLdata = "crossData <- read.cross(format = \"csv\", file = paste(outputPath, \"/crossData.csv\", sep = \"\"), crosstype = " + crossType + ", BC.gen = " + bcNum + ", F.gen = " + fNum + ")";
//			else getQTLdata = "crossData <- read.cross(format = \"csv\", file = paste(outputPath, \"/crossData.csv\", sep = \"\"), crosstype = " + crossType + ")";
			
			String sinkQTL = "sink(\"" + outFileName + "\")";

			String doQTL = null;

			String getBIMdata = null;
			if (mMethod == "BIM") getBIMdata = "crossData <- qb.genoprob(crossData, map.function = \"" + mapCalc + "\", step = " + stepCalc + ")";

			if (threshLiJi) {
				doQTL = "runQTL <- doQTLanalysis(\"" + resultFolderPath + "\", crossData, \"" + traitType + "\", " + yVarsVector + ", \"" + mMethod + "\", " + stepCalc + ", " + errCalc + " , \"" +
						mapCalc + "\", " + lodCutoffM + ", \"" + phenoModel + "\", \"" + alMethod + "\", " + nPermutations + ", " + numCovar + ", " + winSize + ", \"" + genoName + "\", \"thresholdWUR = \"Li&Ji\", " +  
						minDist + ", " + stepSize + ", " + String.valueOf(addModel).toUpperCase() + ", " + numCofac + ", " + String.valueOf(mlAlgo).toUpperCase() + ", " + String.valueOf(setupModel).toUpperCase() + ", " +  
						String.valueOf(includeEpistasis).toUpperCase() + ", " + String.valueOf(useDepPrior).toUpperCase() + ", " +
						priorMain + ", " + priorAll + ", " + maxQTLs + ", " + priorProb + ")";
			} else {
				doQTL = "runQTL <- doQTLanalysis(\"" + resultFolderPath + "\", crossData, \"" + traitType + "\", " + yVarsVector + ", \"" + mMethod + "\", " + stepCalc + ", " + errCalc + " , \"" +
						mapCalc + "\", " + lodCutoffM + ", \"" + phenoModel + "\", \"" + alMethod + "\", " + nPermutations + ", " + numCovar + ", " + winSize + ", \"" + genoName + "\", " + thresholdNumericalValue + ", " +  
						minDist + ", " + stepSize + ", " + String.valueOf(addModel).toUpperCase() + ", " + numCofac + ", " + String.valueOf(mlAlgo).toUpperCase() + ", " + String.valueOf(setupModel).toUpperCase() + ", " +  
						String.valueOf(includeEpistasis).toUpperCase() + ", " + String.valueOf(useDepPrior).toUpperCase() + ", " +
						priorMain + ", " + priorAll + ", " + maxQTLs + ", " + priorProb + ")";
			}
			
//			String doQTL = "doQTLanalysis(" + resultFolderPath + "\", crossData, \"" + traitType  + = c("Continuous", "Binary", "Ordinal"), yVars, mMethod = c("IM", "CIM", "MQM", "BM"), 
//                    stepCalc = 0, errCalc = 0.01, mapCalc = c("haldane","kosambi","c-f","morgan"), lodCutoff = 3,
//                    phenoModel = c("normal","binary","2part","np"), alMethod = c("em","imp","hk","ehk"), nPermutations = 100, 
//                    numCovar = 1, winSize = 10, genoName, thresholdWUR =  "Li&Ji", minDist = 10, stepSize = 5.0,
//                    addModel = TRUE, numCofac = 1, mlAlgo = TRUE);
			
			System.out.println(readData);
			System.out.println(getData);
//			System.out.println(sinkCheckData);
//			System.out.println(checkData);
//			System.out.println("sink()");
			
//			System.out.println(getQTLdata);
			if (mMethod == "BIM") System.out.println(getBIMdata);				
			System.out.println(sinkQTL);
			System.out.println(doQTL);
			System.out.println("sink()");
			
			rEngine.eval(readData);
			rEngine.eval(getData);
//			rEngine.eval(sinkCheckData);
//			rEngine.eval(checkData);
//			rEngine.eval("sink()");
//			rEngine.eval(getQTLdata);
			if (mMethod == "BIM") rEngine.eval(getBIMdata);				
			rEngine.eval(sinkQTL);
			rEngine.eval(doQTL);
			rEngine.eval("sink()");

			//				String readData = "data <- tryCatch(read.csv(\"" + dataFileName + "\", header = TRUE, na.strings = c(\"NA\",\".\",\" \",\"\"), blank.lines.skip=TRUE, sep = \",\"), error=function(err) \"notRun\")";
//				System.out.println(readData);
//				rEngine.eval(readData);
//
			
//			String dire3 = null;
//			String environment_In = null;
//			if (!environment.equals("NULL")) {
//				environment_In = "\"" + environment + "\"";
//			} else environment_In = "NULL";
//			
//			String[] envts = null;
//			if (environmentLevels != null) {
//				if (environmentLevels.length > 0) {
//					envts = environmentLevels;
//				} else { 
//					envts = new String[1];
//					envts[0] = "1"; 
//				}
//			}
//			
//			String specEnv = "NULL";
//			int numEnvts = 1;
//			if (!environment.equals("NULL")) {
//				if (selectedEnvironmentLevel.equals("[ALL]")) numEnvts = envts.length;
//			} else {
//				numEnvts = 1;
//			}
			
			//if input is raw data
//			String designUsed = new String();
//			String design = new String();
//			boolean readingDataMeansSuccess=true;
			
//			if (isInputRawData) {
//				boolean printAllOutputFixed=true;
//				
//				String readData = "data <- tryCatch(read.csv(\"" + dataFileName + "\", header = TRUE, na.strings = c(\"NA\",\".\",\" \",\"\"), blank.lines.skip=TRUE, sep = \",\"), error=function(err) \"notRun\")";
//				System.out.println(readData);
//				rEngine.eval(readData);
//				
//				if (!selectedEnvironmentLevel.equals("[ALL]")) {
//					String dire2 = "data<-tryCatch(data[which(data[match(\"" + environment + "\", names(data))] == \"" + selectedEnvironmentLevel + "\"),], error=function(err) \"notRun\")";
//					System.out.println(dire2);
//					rEngine.eval(dire2);
//				}
//				
//				switch (designIndex) {
//					case 0: {
//						designUsed = "Randomized Complete Block (RCB)";
//						design = "RCB";
//						break;
//					}
//					case 1: {
//						designUsed = "Augmented RCB"; 
//						design = "AugRCB";
//						break;
//					}
//					case 2: {
//						designUsed = "Augmented Latin Square"; 
//						design = "AugLS";
//						break;
//					}
//					case 3: {
//						designUsed = "Alpha-Lattice"; 
//						design = "Alpha";
//						break;
//					}
//					case 4: {
//						designUsed = "Row-Column"; 
//						design = "RowCol";
//						break;
//					}
//					default: {
//						designUsed = "Randomized Complete Block (RCB)"; 
//						design = "RCB";
//						break;
//					}
//				}
//				
//				String usedData = "capture.output(cat(\"\nDATA FILE: " + dataFileName + "\n\",file=\"" + outFileName + "\"))";
//				String outFile = "capture.output(cat(\"\nSINGLE-ENVIRONMENT ANALYSIS\n\"),file=\"" + outFileName + "\",append = TRUE)";
//				String usedDesign = "capture.output(cat(\"\nDESIGN: " + designUsed + "\n\n\"),file=\"" + outFileName + "\",append = TRUE)";
//				
//				rEngine.eval(usedData);
//				rEngine.eval(outFile);
//				rEngine.eval(usedDesign);
//				
//				String runSuccessData = rEngine.eval("data").asString();
//				if (runSuccessData != null && runSuccessData.equals("notRun")) {	
//					System.out.println("error");
//					rEngine.eval("capture.output(cat(\"\n***Error reading data.***\n\"),file=\"" + outFileName + "\",append = FALSE)"); //append to output file?
//				}
//				
//				String funcSeaFixed = null;
//				String groupVars = null;
//				if (design == "RCB" || design == "AugRCB"){
//					funcSeaFixed = "ssa1 <- try(ssa.test(\"" + design + "\",data,"+ respvarVector + ",\"" + genotype + "\",\"" + block + "\",column=NULL, rep=NULL, env = " + environment_In+ ", is.random = FALSE), silent = TRUE)";
//					groupVars = "c(\"" + genotype + "\", \"" + block + "\")";
//				} else if (design == "AugLS") {
//					funcSeaFixed = "ssa1 <- try(ssa.test(\"" + design + "\",data,"+ respvarVector + ",\"" + genotype + "\",\"" + row + "\",\"" + column + "\", rep=NULL, env = " + environment_In+ ", is.random = FALSE), silent = TRUE)";
//					groupVars = "c(\"" + genotype + "\", \"" + row + "\", \"" + column + "\")";
//				} else if (design == "Alpha") {
//					funcSeaFixed = "ssa1 <- try(ssa.test(\"" + design + "\",data,"+ respvarVector + ",\"" + genotype + "\",\"" + block + "\",column=NULL,\"" + rep + "\", env = " + environment_In+ ", is.random = FALSE), silent = TRUE)";
//					groupVars = "c(\"" + genotype + "\", \"" + block + "\", \"" + rep + "\")";
//				} else if (design == "RowCol") {
//					funcSeaFixed = "ssa1 <- try(ssa.test(\"" + design + "\",data,"+ respvarVector + ",\"" + genotype + "\",\"" + row + "\",\"" + column + "\",\"" + rep + "\", env = " + environment_In+ ", is.random = FALSE), silent = TRUE)";
//					groupVars = "c(\"" + genotype + "\", \"" + rep + "\", \"" + row + "\", \"" + column + "\")";
//				}
//
//				System.out.println(funcSeaFixed);
//				rEngine.eval(funcSeaFixed);
//				
//				String runSuccess = rEngine.eval("class(ssa1)").asString();
//				if (runSuccess != null && runSuccess.equals("try-error")) {	
//					System.out.println("ssa.test: error");
//					String checkError = "msg <- trimStrings(strsplit(ssa1, \":\")[[1]])";
//					String checkError2 = "msg <- trimStrings(paste(strsplit(msg, \"\\n\")[[length(msg)]], collapse = \" \"))";
//					String checkError3 ="msg <- gsub(\"\\\"\", \"\", msg)";
//					String checkError4 = "capture.output(cat(\"*** \nERROR in ssa.test function:\\n  \",msg, \"\n***\n\n\", sep = \"\"), file=\"" + outFileName + "\",append = TRUE)";
//					rEngine.eval(checkError);
//					rEngine.eval(checkError2);
//					rEngine.eval(checkError3);
//					rEngine.eval(checkError4);
//				} else {
//					//capture dataMeans
//					dire3 = "dataMeans<-tryCatch(ssa1$meansse, error=function(err) \"notRun\")";
//					rEngine.eval(dire3);
//					System.out.println(dire3);
//					
//					String runSuccessDire3 = rEngine.eval("dataMeans").asString(); 
//					if (runSuccessDire3 != null && runSuccessDire3.equals("notRun")) {
//						System.out.println("error with capturing dataMeans");
//						readingDataMeansSuccess=false;
//					}
//					
//					for (int i = 0; i < respvar.length; i++) {
//						respVarMean[i] = respvar[i]+ "_Mean";
//					}
//					
//					//display results of SSA when input data is raw data
//					
//					
//					String sep = "capture.output(cat(\"------------------------------\"),file=\"" + outFileName + "\",append = TRUE)";
//					String sep2 = "capture.output(cat(\"==============================\n\"),file=\"" + outFileName + "\",append = TRUE)";
//					String outspace = "capture.output(cat(\"\n\"),file=\"" + outFileName + "\",append = TRUE)"; 
//					
//					
//					for (int k = 0; k < respvar.length; k++) {
//						int i = k + 1; // 1-relative index;
//						String respVarHead = "capture.output(cat(\"\nRESPONSE VARIABLE: " + respvar[k] + "\n\"),file=\"" + outFileName + "\",append = TRUE)";
//						rEngine.eval(sep);
//						rEngine.eval(respVarHead);
//						rEngine.eval(sep);
//						rEngine.eval(outspace);
//						
//						//default output: descriptive statistics			
//						String funcDesc = "outDesc <- try(DescriptiveStatistics(data, \"" + respvar[k] + "\", \"" + environment + "\"), silent=TRUE)";
//						System.out.println(funcDesc);
//						rEngine.eval(funcDesc);
//						
//						String outDescStat = "capture.output(cat(\"DESCRIPTIVE STATISTICS:\n\n\"),file=\"" + outFileName + "\",append = TRUE)"; 
//						String outDescStat2 = "capture.output(outDesc,file=\"" + outFileName + "\",append = TRUE)";
//						
//  						String runSuccessDescStat = rEngine.eval("class(outDesc)").asString();	
//						if (runSuccessDescStat != null && runSuccessDescStat.equals("try-error")) {	
//							System.out.println("desc stat: error");
//							String checkError = "msg <- trimStrings(strsplit(outDesc, \":\")[[1]])";
//							String checkError2 = "msg <- trimStrings(paste(strsplit(msg, \"\\n\")[[length(msg)]], collapse = \" \"))";
//							String checkError3 ="msg <- gsub(\"\\\"\", \"\", msg)";
//							String checkError4 = "capture.output(cat(\"*** \nERROR in DescriptiveStatistics function:\\n  \",msg, \"\n***\n\n\", sep = \"\"), file=\"" + outFileName + "\",append = TRUE)";
//							rEngine.eval(checkError);
//							rEngine.eval(checkError2);
//							rEngine.eval(checkError3);
//							rEngine.eval(checkError4);
//						} 
//						else {
//							rEngine.eval(outspace);
//							rEngine.eval(outDescStat);
//							rEngine.eval(outDescStat2);
//							rEngine.eval(outspace);
//						}
//
//						for (int m = 0; m < numEnvts; m++) { // no of envts or sites
//							printAllOutputFixed=true;
//							int j = m + 1; // 1-relative index;
//							String envtHead = "capture.output(cat(\"\nANALYSIS FOR: "+ environment + "\", \" = \" ,ssa1$output[[" + i	+ "]]$site[[" + j + "]]$env,\"\n\"),file=\""+ outFileName + "\",append = TRUE)";
//							rEngine.eval(sep);
//							rEngine.eval(envtHead);
//							rEngine.eval(sep);
//							rEngine.eval(outspace);
//							
//							//check if the data has too many missing observation
//							double nrowData=rEngine.eval("ssa1$output[[" + i + "]]$site[[" + j + "]]$responseRate").asDouble();
//							if (nrowData < 0.80) {
//								String allNAWarning = rEngine.eval("ssa1$output[[" + i + "]]$site[[" + j + "]]$manyNAWarning").asString();
//								String printError1 = "capture.output(cat(\"***\\n\"), file=\"" + outFileName + "\",append = TRUE)";
//								String printError2 = "capture.output(cat(\"ERROR:\\n\"), file=\"" + outFileName + "\",append = TRUE)";
//								String printError3 = "capture.output(cat(\"" + allNAWarning + "\\n\"), file=\"" + outFileName + "\",append = TRUE)";
//								
//								rEngine.eval(outspace);
//								rEngine.eval(printError1);
//								rEngine.eval(printError2);
//								rEngine.eval(printError3);
//								rEngine.eval(printError1);
//								rEngine.eval(outspace);
//								rEngine.eval(outspace);
//								printAllOutputFixed=false;
//							} // end of if (nrowData < 0.80)
//							
//							if (printAllOutputFixed) {
//								// default output: trial summary
//								String funcTrialSum = "funcTrialSum <- try(class.information(" + groupVars + ",ssa1$output[[" + i + "]]$site[[" + j + "]]$data), silent=TRUE)";
//								String trialSumHead = "capture.output(cat(\"\nDATA SUMMARY:\n\n\"),file=\"" + outFileName + "\",append = TRUE)";
//								String trialObsRead = "capture.output(cat(\"Number of observations read: \", ssa1$output[["	+ i	+ "]]$site[[" + j + "]]$obsread[[1]],\"\n\"),file=\"" + outFileName + "\",append = TRUE)";
//								String trialObsUsed = "capture.output(cat(\"Number of observations used: \", ssa1$output[["	+ i	+ "]]$site[[" + j + "]]$obsused[[1]],\"\n\n\"),file=\""	+ outFileName + "\",append = TRUE)";
//								String trialSum = "capture.output(funcTrialSum,file=\"" + outFileName + "\",append = TRUE)";
//
//								rEngine.eval(funcTrialSum);
//		
//								String runSuccessTS = rEngine.eval("class(funcTrialSum)").asString();
//								if (runSuccessTS != null && runSuccessTS.equals("try-error")) {	
//									System.out.println("class info: error");
//									String checkError = "msg <- trimStrings(strsplit(funcTrialSum, \":\")[[1]])";
//									String checkError2 = "msg <- trimStrings(paste(strsplit(msg, \"\\n\")[[length(msg)]], collapse = \" \"))";
//									String checkError3 ="msg <- gsub(\"\\\"\", \"\", msg)";
//									String checkError4 = "capture.output(cat(\"*** \nERROR in class.information function:\\n  \",msg, \"\n***\n\n\", sep = \"\"), file=\"" + outFileName + "\",append = TRUE)";
//									rEngine.eval(checkError);
//									rEngine.eval(checkError2);
//									rEngine.eval(checkError3);
//									rEngine.eval(checkError4);
//								}
//								else {
//									rEngine.eval(trialSumHead);
//									rEngine.eval(trialObsRead);
//									rEngine.eval(trialObsUsed);
//									rEngine.eval(trialSum);
//									rEngine.eval(outspace);
//								}
//								
//								
//								// default output: variance components
//								String outVarComp = "capture.output(cat(\"\nVARIANCE COMPONENTS TABLE:\n\n\"),file=\"" + outFileName + "\",append = TRUE)";
//								String outVarComp2 = "capture.output(ssa1$output[[" + i + "]]$site[[" + j + "]]$varcomp.table,file=\"" + outFileName + "\",append = TRUE)";
//	
//								rEngine.eval(outVarComp);
//								rEngine.eval(outVarComp2);
//								rEngine.eval(outspace);
//
//								
//								// default output: test for genotypic effect
////								String outAnovaTable1 = "capture.output(ssa1$output[[" + i + "]]$site[[" + j + "]]$model.comparison,file=\"" + outFileName + "\",append = TRUE)";
//								String outAnovaTable1 = "capture.output(cat(\"\nTESTING FOR THE SIGNIFICANCE OF GENOTYPIC EFFECT:\n\"),file=\"" + outFileName + "\",append = TRUE)";
//								String outAnovaTable2 = "library(lmerTest)";
//								String outAnovaTable3 = "model1b <- lmer(formula(ssa1$output[[" + i + "]]$site[[" + j + "]]$formula1), data = ssa1$output[[" + i + "]]$site[[" + j + "]]$data, REML = T)";
//								String outAnovaTable4 = "a.table <- anova(model1b)";
//								String outAnovaTable5 = "pvalue <- formatC(as.numeric(format(a.table[1,6], scientific=FALSE)), format=\"f\")";
//								String outAnovaTable6 = "a.table<-cbind(round(a.table[,1:5], digits=4),pvalue)";
//								String outAnovaTable7 = "colnames(a.table)<-c(\"Df\", \"Sum Sq\", \"Mean Sq\", \"F value\", \"Denom\", \"Pr(>F)\")";
//								String outAnovaTable8 = "capture.output(cat(\"Analysis of Variance Table with Satterthwaite Denominator Df\n\"),file=\"" + outFileName + "\",append = TRUE)";
//								String outAnovaTable9 = "capture.output(a.table,file=\"" + outFileName + "\",append = TRUE)";
//								String outAnovaTable10 = "detach(\"package:lmerTest\")";
//								
//								rEngine.eval(outAnovaTable1);
//								rEngine.eval(outAnovaTable2);
//								rEngine.eval(outAnovaTable3);
//								rEngine.eval(outAnovaTable4);
//								rEngine.eval(outAnovaTable5);
//								rEngine.eval(outAnovaTable6);
//								rEngine.eval(outAnovaTable7);
//								rEngine.eval(outspace);
//								rEngine.eval(outAnovaTable8);
//								rEngine.eval(outAnovaTable9);
//								rEngine.eval(outspace);
//								rEngine.eval(outAnovaTable10);
//		
//							
//								//default output: LSMeans
//								String outLSMeans1 = "capture.output(cat(\"\nGENOTYPE LSMEANS AND STANDARD ERRORS:\n\n\"),file=\"" + outFileName + "\",append = TRUE)";
//								String outLSMeans2 = "capture.output(ssa1$output[[" + i + "]]$site[[" + j + "]]$summary.statistic,file=\"" + outFileName + "\",append = TRUE)"; 
//		
//								rEngine.eval(outLSMeans1);
//								rEngine.eval(outLSMeans2);
//								rEngine.eval(outspace);
//								
//								
//								//default output: standard error of the differences
//								String outsedTable = "capture.output(cat(\"\nSTANDARD ERROR OF THE DIFFERENCE (SED):\n\n\"),file=\"" + outFileName + "\",append = TRUE)";
//								String outsedTable2 = "capture.output(ssa1$output[[" + i + "]]$site[[" + j + "]]$sedTable,file=\"" + outFileName + "\",append = TRUE)";
//
//								rEngine.eval(outsedTable);
//								rEngine.eval(outsedTable2);
//								rEngine.eval(outspace);
//
//							} //end of if (printAllOutputFixed)
//								
//						} // end of for loop for diff envts
//						
//					} //end of for (int k = 0; k < respvar.length; k++)
//					
//					rEngine.eval(outspace);
//					rEngine.eval(sep2);
//					
//				} //end of else of if runSuccess
//				
//			}// end of if (isInputRawData)

			
//			//using the predicted means data
//			if (isInputRawData == false) {
//				dire3 = "dataMeans<- tryCatch(read.csv(\"" + dataFileName + "\", header = TRUE, na.strings = c(\"NA\",\".\",\" \",\"\"), blank.lines.skip=TRUE, sep = \",\"), error=function(err) \"notRun\")";				
//				rEngine.eval(dire3);
//				System.out.println(dire3);
//				
//				String runSuccessDire3 = rEngine.eval("dataMeans").asString(); 
//				if (runSuccessDire3 != null && runSuccessDire3.equals("notRun")) {
//					System.out.println("error with capturing dataMeans");
//					readingDataMeansSuccess=false;
//				}
//				
//				for (int i = 0; i < respvar.length; i++) {
//					respVarMean[i] = respvar[i];
//				}
//			}
//			
//			if (readingDataMeansSuccess) {
//				//data compatibility check
//				String setWd = "setwd(\"" + resultFolderPath + "\")";
//				String dataPrepFunction = "dataPrepResult<-QTL.dataprep(dataMeans, \"" + genotypicDataFileName + "\", \"" + mapFileName + "\", \"" + genotype + "\")";
//				
//				rEngine.eval(setWd);
//				rEngine.eval(dataPrepFunction);
//				
//				System.out.println(setWd);
//				System.out.println(dataPrepFunction);
//				
//				String isNewPhenoCreated = rEngine.eval("dataPrepResult$isNewPhenoCreated").asBool().toString();
//				String isNewMapCreated = rEngine.eval("dataPrepResult$isNewMapCreated").asBool().toString();
//				String isNewGenoCreated = rEngine.eval("dataPrepResult$isNewGenoCreated").asBool().toString();
//				
//				System.out.println("is new phenotypic file created:" + isNewPhenoCreated);
//				System.out.println("is new map file created:" + isNewMapCreated);
//				System.out.println("is new genotypic file created:" + isNewGenoCreated);
//				
//				if (isNewPhenoCreated == "TRUE") {
//					String newPheno = "dataMeans<- tryCatch(read.csv(\"" + resultFolderPath + "newPhenoData.csv\", header = TRUE, na.strings = c(\"NA\",\".\",\" \",\"\"), blank.lines.skip=TRUE, sep = \",\"), error=function(err) \"notRun\")";
//					rEngine.eval(newPheno);
//					System.out.println(newPheno);
//				}
//				
//				String newMapFilePath=null;
//				if (isNewMapCreated == "TRUE") {
//					newMapFilePath = resultFolderPath + "newMapData.txt";
//				} else {
//					newMapFilePath = mapFileName;
//				}
//				
//				String newGenoFilePath=null;
//				if (isNewGenoCreated == "TRUE") {
//					newGenoFilePath = resultFolderPath + "newGenoData.txt";
//				} else {
//					newGenoFilePath = genotypicDataFileName;
//				}
//				
//				//start of QTL analysis
//				if (isInputRawData == false) {
//					String usedData = "capture.output(cat(\"\nDATA FILE: " + dataFileName + "\n\",file=\"" + outFileName + "\"))";
//					rEngine.eval(usedData);
//				}
//				
//				String heading2 = "capture.output(cat(\"\nQTL ANALYSIS\n\"),file=\"" + outFileName + "\",append = TRUE)";
//				String heading3 = "capture.output(cat(\"\nMETHOD: " +  qtlMethod + "\n\n\"),file=\"" + outFileName + "\",append = TRUE)";
//				String sep = "capture.output(cat(\"------------------------------\"),file=\"" + outFileName + "\",append = TRUE)";
//				String sep2 = "capture.output(cat(\"==============================\n\"),file=\"" + outFileName + "\",append = TRUE)";
//				String outspace = "capture.output(cat(\"\n\"),file=\"" + outFileName + "\",append = TRUE)";
//				
//				rEngine.eval(heading2);
//				rEngine.eval(heading3);
//
//				////////////////////////////////
////				rEngine.eval("source(\"E:/StarPbtools/QTL/QTL_irri2.R\")");
//				
//				////////////////////////////////
//				String bHet = "FALSE";
//				if (heterozygousPresent) bHet = "TRUE"; else bHet = "FALSE"; 
//				String bMarker = "FALSE";
//				if (estimatePlotMarkerMap) bMarker = "TRUE"; else bMarker = "FALSE";
//				String sThresh;
//				if (thresholdLiJi) sThresh = "Li&Ji"; else sThresh = thresholdNumericValue;
//
//				double dThreshMQ = allelicDiffThreshold;
//				double dChi2Sig = significanceLevelChiSquare;
//				double dCutoff = cutOffMissingData;
//									
//				for (int i = 0; i < respvar.length; i++) {
//					String qtlResultAll1 = "capture.output(cat(\"\nRESPONSE VARIABLE: " + respvar[i] + "\n\"),file=\"" + outFileName + "\",append = TRUE)";
//					rEngine.eval(sep);
//					rEngine.eval(qtlResultAll1);
//					rEngine.eval(sep);
//					rEngine.eval(outspace);
//					
//					for (int j = 0; j < numEnvts; j++) {
//						if (!selectedEnvironmentLevel.equals("[ALL]")) specEnv = selectedEnvironmentLevel;
//						else specEnv = envts[j];
//
//						if (environment != "NULL") {
//							String qtlResultAll1b = "capture.output(cat(\"\nANALYSIS FOR: " + environment + " = " + specEnv + "\n\"),file=\"" + outFileName + "\",append = TRUE)";
//							rEngine.eval(sep);
//							rEngine.eval(qtlResultAll1b);
//							rEngine.eval(sep);
//							rEngine.eval(outspace);
//						}
//
//						String funcDmMq1 = "dmMq1 <- tryCatch(cross.data<-load.cross.data(dataMeans, \"" + newGenoFilePath +
//						"\", \"" + newMapFilePath + "\", cross = \"" + crossType +
//						"\", heterozygotes = " + bHet + ", genotype = \"" + genotype + "\", env.label = " + environment_In + ", env = \"" + specEnv + "\", ST = NULL), error=function(err) \"notRun\")";
//
//						String funcDmMq2 = "dmMq2 <- tryCatch(crossobj <- cross.data, error=function(err) \"notRun\")";
//
//						rEngine.eval(funcDmMq1);
//						System.out.println(funcDmMq1);
//						
//						String runSuccessDmMq1 = rEngine.eval("dmMq1").asString();
//						if (runSuccessDmMq1 != null && runSuccessDmMq1.equals("notRun")) {	
//							System.out.println("error");
//							rEngine.eval("capture.output(cat(\"\n***Problem encountered during data reading/compilation.***\n\n\"),file=\"" + outFileName + "\",append = TRUE)"); //append to output file?
//						}
//
//						rEngine.eval(funcDmMq2);
//						System.out.println(funcDmMq2);
//						
//						String runSuccessDmMq2 = rEngine.eval("dmMq2").asString();
//						if (runSuccessDmMq2 != null && runSuccessDmMq2.equals("notRun")) {	
//							System.out.println("error");
//							rEngine.eval("capture.output(cat(\"\n***Problem encountered during data reading/compilation.***\n\n\"),file=\"" + outFileName + "\",append = TRUE)"); //append to output file?
//						}
//							
//						if (i == 0 && j == 0) {
//							//done once for all envts and traits
//							String funcDmMq3 = "dmMq3 <- tryCatch(MQ.marker.diag(crossobj, \"" + resultFolderPath + "\", estmarker = " + bMarker + ", thresholdMQ = " + dThreshMQ + ", p.val = " + dChi2Sig + ", na.cutoff = " + 
//												dCutoff + "), error=function(err) \"notRun\")";
//
//							rEngine.eval(funcDmMq3);
//							System.out.println(funcDmMq3);
//							
//							String runSuccessDmMq3 = rEngine.eval("dmMq3").asString();
//							if (runSuccessDmMq3 != null && runSuccessDmMq3.equals("notRun")) {	
//								System.out.println("error");
//								rEngine.eval("capture.output(cat(\"\n***Problem encountered during marker quality check.***\n\n\"),file=\"" + outFileName + "\",append = TRUE)"); //append to output file?
//							}
//						}
//						
//						String funcQtlSIM = null;
//						if (thresholdLiJi) {
//							funcQtlSIM = "qtlSIM <- tryCatch(QTL.result<-QTL.analysis(crossobj, \"" + resultFolderPath + "\", env.label = " + environment_In + ", env = \"" + specEnv + "\", trait = \"" + respVarMean[i] + "\", step = " + step + 
//									", method = \"SIM\", threshold = \"" + sThresh +
//									"\", distance = " + minDistance + ", window.size = " + windowSize + 
//						            "), error=function(err) \"notRun\")";
//						} else {
//							funcQtlSIM = "qtlSIM <- tryCatch(QTL.result<-QTL.analysis(crossobj, \"" + resultFolderPath + "\", env.label = " + environment_In + ", env = \"" + specEnv + "\", trait = \"" + respVarMean[i] + "\", step = " + step + 
//									", method = \"SIM\", threshold = " + sThresh +
//									", distance = " + minDistance + ", window.size = " + windowSize + 
//						            "), error=function(err) \"notRun\")";
//						}
//						
//						System.out.println(funcQtlSIM);
//						rEngine.eval(funcQtlSIM);
//						
////						String runSuccessQtlSim = rEngine.eval("qtlSIM").asString();
////						System.out.println("runSuccessQtlSim: " + runSuccessQtlSim);
////						//generate warning if error occurred	
////						if (runSuccessQtlSim != null && runSuccessQtlSim.equals("notRun")) {	
////							System.out.println("error");
////							rEngine.eval("capture.output(cat(\"\n***An error has occurred.***\n***QTL analysis not completed.***\n\"),file=\"" + outFileName + "\",append = TRUE)"); //append to output file?
////						}
//						
//						String runSuccessQtl = null;
//						
//						if (qtlMethod == "SIM") {
//							runSuccessQtl = rEngine.eval("qtlSIM").asString();
//						}
//						
//						if (qtlMethod == "CIM") {
//							String funcQtlCIM1 = "qtlCIM1 <- tryCatch(cofactors<-as.vector(QTL.result$selected$marker), error=function(err) \"notRun\")";
//							
//							String funcQtlCIM2=null;
//							if (thresholdLiJi) {
//								funcQtlCIM2 = "qtlCIM2 <- tryCatch(QTL.result<-QTL.analysis(crossobj, \"" + resultFolderPath + "\", env.label = " + environment_In + ", env = \"" + specEnv + "\", trait = \"" + respVarMean[i] + "\", step = " + step + 
//										", method = \"CIM\", threshold = \"" + sThresh +
//										"\", distance = " + minDistance + ", cofactors, window.size = " + windowSize + 
//							            "), error=function(err) \"notRun\")";
//							} else {
//								funcQtlCIM2 = "qtlCIM2 <- tryCatch(QTL.result<-QTL.analysis(crossobj, \"" + resultFolderPath + "\", env.label = " + environment_In + ", env = \"" + specEnv + "\", trait = \"" + respVarMean[i] + "\", step = " + step + 
//										", method = \"CIM\", threshold = " + sThresh +
//										", distance = " + minDistance + ", cofactors, window.size = " + windowSize + 
//							            "), error=function(err) \"notRun\")";
//							}
//							
//							System.out.println(funcQtlCIM1);
//							rEngine.eval(funcQtlCIM1);
//
//							String runSuccessQtlCIM1 = rEngine.eval("qtlCIM1").asString();
//							if (runSuccessQtlCIM1 != null && runSuccessQtlCIM1.equals("notRun")) {	
//								System.out.println("error");
//								rEngine.eval("capture.output(cat(\"\n***Cannot proceed with CIM (error on the cofactors).***\n***(Shown output for SIM).***\n\"),file=\"" + outFileName + "\",append = TRUE)"); //append to output file?
//							} else { //if cofactors were generated
//								System.out.println(funcQtlCIM2);
//								rEngine.eval(funcQtlCIM2);
//								
//								runSuccessQtl = rEngine.eval("qtlCIM2").asString();
//							}
//						}
//						
//						if (runSuccessQtl != null && runSuccessQtl.equals("notRun")) {	
//							System.out.println("error");
//							rEngine.eval("capture.output(cat(\"\n***QTL analysis not done.***\n\"),file=\"" + outFileName + "\",append = TRUE)"); //append to output file?
//						}
//						else {
//						
//							String qtlResultAll1a = "capture.output(cat(\"\nQTL RESULT (ALL):\n\"),file=\"" + outFileName + "\",append = TRUE)";
//							rEngine.eval(qtlResultAll1a);	
//		
//							String qtlResultAll2 = "capture.output(QTL.result$all,file=\"" + outFileName + "\",append = TRUE)";
//							rEngine.eval(qtlResultAll2);
//							
//							//QTL result selected
//							String qtlResultSel1 = "capture.output(cat(\"\nQTL RESULT (SELECTED):\n\"),file=\"" + outFileName + "\",append = TRUE)";
//							String qtlResultSel2 = "capture.output(QTL.result$selected,file=\"" + outFileName + "\",append = TRUE)";
//							
//							rEngine.eval(qtlResultSel1);
//							rEngine.eval(qtlResultSel2);
//							rEngine.eval(outspace);
//							rEngine.eval(outspace);
//						
//						}// end of else for r
//					}
//				} //end of for (int i = 0; i < respvar.length; i++)
//				
//				rEngine.eval(sep2);
//				
//			} //end of if (readingDataMeansSuccess)
			
			rEngineEnd();
			
			System.out.println("reached end.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	@Override
	public void doGenSim(String resultFolderPath, String doPedigree, String fileFormat, String fileName,
			String relType, String outFileName, int markerFormat) {
				
//			String dataCheckOutFileName, 
//			String outFileName, String resultFolderPath, 
//			String dataFormat, String format1, 
//			String crossType, 
//			String file1, String format2, String file2, String format3, String file3, String P_geno, 
//			int bcNum, int fNum,

//		rJavaManager.getWebToolManager().doGenSim(
//			outputPath, doPedigree, fileFormat, fileName,
//			relType, outFileName);
	
//		String yVarsVector= inputTransform.createRVector(yVars);
//		String [] respVarMean = new String[respvar.length];
		
//		String sThresh;
//		double thresholdNumericValue = 0;
//		if (!thresholdLiJi) thresholdNumericValue =  ;

		System.out.println("start GenSim");
		System.out.println("resultFolderPath: " + resultFolderPath);
		System.out.println("outFileName: " + outFileName);
		
		try {
			String source1 = "library(synbreed)";
			String source2 = "library(pedigreemm)";
			String source3 = "library(PBTools)";
			String source4 = "library(pbtgs)";
//			String source4 = "source(\'E:/StarPbtools/GS/script/trimStrings.R\')";
//			String source5 = "source(\"E:/StarPbtools/GS/script/GSDataCheck.R\")";
//			String source6 = "source(\"E:/StarPbtools/GS/script/marker_relationship.R\")";
//			String source7 = "source(\"E:/StarPbtools/GS/script/pedigree_relationship.R\")";
//			String source8 = "source(\"E:/StarPbtools/GS/script/doGenSim.R\")";
			String getGenSimOut = "genSimOut <- doGenSim(\"" + resultFolderPath + "\", \"" + fileName + "\", \"" + fileFormat + "\", " + doPedigree +
					", \"" + relType + "\", \"" + outFileName + "\", " + markerFormat + ")";

			System.out.println(source1);
			System.out.println(source2);
			System.out.println(source3);
			System.out.println(source4);
//			System.out.println(source5);
//			System.out.println(source6);
//			System.out.println(source7);
//			System.out.println(source8);
			System.out.println(getGenSimOut);
//			System.out.println(source9);
//			System.out.println(source10);
//			
			rEngine.eval(source1); //rEngine.eval("source(\'E:/StarPbtools/QTL/irri_new/trimStrings.R\')");
			rEngine.eval(source2);
			rEngine.eval(source3);
			rEngine.eval(source4);
//			rEngine.eval(source5);
//			rEngine.eval(source6);
//			rEngine.eval(source7);
//			rEngine.eval(source8);
			rEngine.eval(getGenSimOut);
//			rEngine.eval(source9);
//			rEngine.eval(source10);

//			rEngine.eval("sink()");

			rEngineEnd();
			
			System.out.println("reached end.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}	
	
	
	@Override
	public void doGSDataPrep(String resultFolderPath, String pheno_file, String geno_file,  String map_file, String rel_file, String pFormat, 
			String gFormat, String mFormat, String rFormat) {
				
		if (!geno_file.equals("NULL")) {
			geno_file = "\"" + geno_file + "\"";
			gFormat = "\"" + gFormat + "\"";
		}
		if (!map_file.equals("NULL")) {
			map_file = "\"" + map_file + "\"";
			mFormat = "\"" + mFormat + "\"";
		}
		System.out.println("mFormat: " + mFormat);
		if (!rel_file.equals("NULL")) {
			rel_file = "\"" + rel_file + "\"";
			rFormat = "\"" + rFormat + "\"";
		}

//		rJavaManager.getWebToolManager().doGSDataPrep(
//				resultFolderPath, pheno_file, geno_file,  map_file, rel_file, pFormat, gFormat, mFormat, rFormat); 

//		String yVarsVector= inputTransform.createRVector(yVars);
//		String [] respVarMean = new String[respvar.length];
		
		System.out.println("start GSDataPrep");
		System.out.println("resultFolderPath: " + resultFolderPath);
//		System.out.println("outFileName: " + outFileName);
		
		try {
//			String source1 = "library(synbreed)";
			String source1 = "library(PBTools)";
			String source2 = "library(pbtgs)";
//			String source2 = "library(pedigreemm)";
//			String source3 = "library()";
//			String source4 = "source(\'E:/StarPbtools/GS/script/trimStrings.R\')";
//			String source5 = "source(\"E:/StarPbtools/GS/script/GSDataCheck.R\")";
//			String source6 = "source(\"E:/StarPbtools/GS/script/marker_relationship.R\")";
//			String source7 = "source(\"E:/StarPbtools/GS/script/pedigree_relationship.R\")";
//			String source8 = "source(\"E:/StarPbtools/GS/script/doGenSim.R\")";
			
//			String source1 = "source(\"E:/StarPbtools/GS/script/GSDataPrep.R\")";
//			String source2 = "source(\'E:/StarPbtools/GS/script/trimStrings.R\')";
			String getGSDataPrepOut = "gsDataPrepOut <- GSDataPrep(\"" + resultFolderPath + "\", \"" + pheno_file + "\", " + geno_file + ", " + map_file +
					", " + rel_file + ", \"" + pFormat + "\", " + gFormat + ", " + mFormat + ", " + rFormat + ")";

			System.out.println(source1);
			System.out.println(source2);
//			System.out.println(source3);
//			System.out.println(source4);
//			System.out.println(source5);
//			System.out.println(source6);
//			System.out.println(source7);
//			System.out.println(source8);
			System.out.println(getGSDataPrepOut);
//			
			rEngine.eval(source1); //rEngine.eval("source(\'E:/StarPbtools/QTL/irri_new/trimStrings.R\')");
			rEngine.eval(source2);
//			rEngine.eval(source3);
//			rEngine.eval(source4);
//			rEngine.eval(source5);
//			rEngine.eval(source6);
//			rEngine.eval(source7);
//			rEngine.eval(source8);
			rEngine.eval(getGSDataPrepOut);

//			rEngine.eval("sink()");

			rEngineEnd();
			
			System.out.println("reached end.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}	
	

	@Override
	public void doGSDataCheck(String resultFolderPath, String geno_file, int markerFormat, double maxMissingP, double minMAF, double maxCorr) {
//	(String resultFolderPath, String pheno_file, String geno_file,  String map_file, String rel_file, String pFormat, 
//			String gFormat, String mFormat, String rFormat) {
				
//		String yVarsVector= inputTransform.createRVector(yVars);
//		String [] respVarMean = new String[respvar.length];
		
		System.out.println("start GSDataCheck");
		System.out.println("resultFolderPath: " + resultFolderPath);
//		System.out.println("outFileName: " + outFileName);
		
		try {
			String source1 = "library(pbtgs)";
//			String source1 = "library(synbreed)";
//			String source2 = "library(pedigreemm)";
//			String source3 = "library(GeneticsPed)";
//			String source4 = "source(\'E:/StarPbtools/GS/script/trimStrings.R\')";
//			String source5 = "source(\"E:/StarPbtools/GS/script/GSDataCheck.R\")";
//			String source6 = "source(\"E:/StarPbtools/GS/script/marker_relationship.R\")";
//			String source7 = "source(\"E:/StarPbtools/GS/script/pedigree_relationship.R\")";
//			String source8 = "source(\"E:/StarPbtools/GS/script/doGenSim.R\")";
			
//			String source1 = "source(\"E:/StarPbtools/GS/script/GSDataCheck.R\")";
//			String source2 = "source(\'E:/StarPbtools/GS/script/trimStrings.R\')";
//			String getGSDataPrepOut = "gsDataPrepOut <- GSDataPrep(\"" + resultFolderPath + "\", \"" + pheno_file + "\", \"" + geno_file + "\", \"" + map_file +
//					"\", \"" + rel_file + "\", \"" + pFormat + "\", \"" + gFormat + "\", \"" + mFormat + "\", \"" + rFormat + "\")";
			String getGSDataCheckOut = "gsDataCheckOut <- GSDataCheck(\"" + resultFolderPath + "\", \"" + geno_file + "\", " + markerFormat + ", " + maxMissingP +
					", " + minMAF + ", " + maxCorr + ")";
//			GSDataCheck <- function(outputPath, geno_file, type, nmiss = 0.1, maf = 0.05, cor_threshold = 0.90)
//			maxMissingP, maf = minMAF, cor_threshold = maxCorr)

			System.out.println(source1);
//			System.out.println(source2);
//			System.out.println(source3);
//			System.out.println(source4);
//			System.out.println(source5);
//			System.out.println(source6);
//			System.out.println(source7);
//			System.out.println(source8);
			System.out.println(getGSDataCheckOut);
//			
			rEngine.eval(source1); //rEngine.eval("source(\'E:/StarPbtools/QTL/irri_new/trimStrings.R\')");
//			rEngine.eval(source2);
//			rEngine.eval(source3);
//			rEngine.eval(source4);
//			rEngine.eval(source5);
//			rEngine.eval(source6);
//			rEngine.eval(source7);
//			rEngine.eval(source8);
			rEngine.eval(getGSDataCheckOut);

//			rEngine.eval("sink()");

			rEngineEnd();
			
			System.out.println("reached end.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}	

	@Override
	public void doGSDataImputation(String resultFolderPath, String geno_file, String impType, String pheno_file, String familyTrait, String packageFormat) {
		
//		String yVarsVector= inputTransform.createRVector(yVars);
//		String [] respVarMean = new String[respvar.length];
		
		System.out.println("start GSDataImputation");
		System.out.println("resultFolderPath: " + resultFolderPath);
//		System.out.println("outFileName: " + outFileName);
		
		try {
			String source1 = "library(synbreed)";
			String source2 = "library(pbtgs)";
			String source3 = "library(PBTools)";
//			String source2 = "library(pedigreemm)";
//			String source3 = "library(GeneticsPed)";
//			String source4 = "source(\'E:/StarPbtools/GS/script/trimStrings.R\')";
//			String source5 = "source(\"E:/StarPbtools/GS/script/GSDataCheck.R\")";
//			String source6 = "source(\"E:/StarPbtools/GS/script/marker_relationship.R\")";
//			String source7 = "source(\"E:/StarPbtools/GS/script/pedigree_relationship.R\")";
//			String source8 = "source(\"E:/StarPbtools/GS/script/doGenSim.R\")";
			
//			String source2 = "source(\"E:/StarPbtools/GS/script/GSDataImputation.R\")";
//			String source2 = "source(\'E:/StarPbtools/GS/script/trimStrings.R\')";
			String getGSDataImputation  = null;
			if (impType == "random") 
				getGSDataImputation = "gsDataImputation <- GSDataImputation(\"" + resultFolderPath + "\", \"" + geno_file + "\", \"" + impType + "\", NULL, NULL, \"" + packageFormat + "\")";
			else getGSDataImputation = "gsDataImputation <- GSDataImputation(\"" + resultFolderPath + "\", \"" + geno_file + "\", \"" + impType + "\", \"" + pheno_file +
					"\", \"" + familyTrait + "\", \"" + packageFormat + "\")";
//			
//			GSDataImputation <- function(outputPath, geno_file, impType = c("random", "family"), pheno_file = NULL, familyTrait = NULL, packageFormat = c("synbreed", "rrBLUP", "BGLR")) {
			

			System.out.println(source1);
			System.out.println(source2);
			System.out.println(source3);
//			System.out.println(source4);
//			System.out.println(source5);
//			System.out.println(source6);
//			System.out.println(source7);
//			System.out.println(source8);
			System.out.println(getGSDataImputation);
//			
			rEngine.eval(source1); //rEngine.eval("source(\'E:/StarPbtools/QTL/irri_new/trimStrings.R\')");
			rEngine.eval(source2);
			rEngine.eval(source3);
//			rEngine.eval(source4);
//			rEngine.eval(source5);
//			rEngine.eval(source6);
//			rEngine.eval(source7);
//			rEngine.eval(source8);
			rEngine.eval(getGSDataImputation);

//			rEngine.eval("sink()");

			rEngineEnd();
			
			System.out.println("reached end.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}	
	
	
	@Override
	public void doGBLUP(String resultFolderPath, String pheno_file, String geno_file, 
//			int markerFormat, 
			String importRel, String rel_file, String rMatType, 
			String map_file, String[] traitNames, String[] covariates, String doCV, 
//			String varCompEst, 
			String samplingStrat, String popStruc_file, int nfolds, int nrep) {
		
//		rJavaManager.getWebToolManager().doGBLUP(
//				resultFolderPath, pheno_file, geno_file, markerFormat, importRel, rel_file, rMatType, 
//                map_file, traitNames, covariates, doCV, varCompEst, samplingStrat, nfolds, nrep);

		String traitNamesVector = inputTransform.createRVector(traitNames);
		String covariatesVector = "NULL";
		if (covariates[0] != "NULL") covariatesVector = inputTransform.createRVector(covariates);
//		String [] respVarMean = new String[respvar.length];
		
		String genoFile = null;
		if (geno_file.equals("NULL")) genoFile = geno_file; else genoFile = "\"" + geno_file + "\"";
		
		String relFile = null;
		if (rel_file.equals("NULL")) relFile = rel_file; else relFile = "\"" + rel_file + "\"";
		
		String mapFile = null;
		if (map_file.equals("NULL")) mapFile = map_file; else mapFile = "\"" + map_file + "\"";
		
		String popStrucFile = null;
		if (popStruc_file.equals("NULL")) popStrucFile = popStruc_file; else popStrucFile = "\"" + popStruc_file + "\"";

		System.out.println("start GBLUP");
		System.out.println("resultFolderPath: " + resultFolderPath);
//		System.out.println("outFileName: " + outFileName);
		
		try {
			String source1 = "library(synbreed)";
//			String source2 = "library(pedigreemm)";
			String source2 = "library(PBTools)";
			String source3 = "library(Matrix)";
			String source4 = "library(pbtgs)";
////			String source4 = "source(\'E:/StarPbtools/GS/script/trimStrings.R\')";
////			String source5 = "source(\"E:/StarPbtools/GS/script/GSDataCheck.R\")";
//			String source6 = "source(\"E:/StarPbtools/GS/script/marker_relationship.R\")";
////			String source7 = "source(\"E:/StarPbtools/GS/script/pedigree_relationship.R\")";
////			String source8 = "source(\"E:/StarPbtools/GS/script/doGenSim.R\")";
//			
//			String source2 = "source(\"E:/StarPbtools/GS/script/BLUP_synbreed_gv.R\")";
//			String source4 = "source(\"E:/StarPbtools/GS/script/BLUP_synbreed_cv.R\")";
//			String source5 = "source(\'E:/StarPbtools/GS/script/doGBLUP.R\')";
//			String source7 = "source(\'E:/StarPbtools/GS/script/createGSPlots.R\')";
//			### add sourcing panel functions
			String source8 = "sink(paste(\"" + resultFolderPath + "GBLUPOut.txt\", sep = \"\"))";
//			String getGBLUPOut  = null;
			String getGBLUPOut = "gsGBLUP <- doGBLUP(\"" + resultFolderPath + "\", \"" + pheno_file + "\", " + genoFile + ", " + 
//					markerFormat + ", " + 
					importRel + ", " + relFile + ", \"" + rMatType + "\", " + mapFile + ", " + traitNamesVector + ", " + covariatesVector + ", " + doCV + ", \"" + 
//					varCompEst + "\", \"" +
					samplingStrat + "\", " + popStrucFile + ", " + nfolds + ", " + nrep + ")";
			String source9 = "sink()";
//			doGBLUP <- function(outputPath, pheno_file, geno_file = NULL, markerFormat = c(1, 2, 3), , 
//                    importRel = FALSE, rel_file = NULL, rMatType = c("t1", "t2", "t3", "t4"), 
//                    map_file = NULL, # ped_file = NULL, #peFormat = NULL, #data quality check options, ...,
//                    traitNames, covariates = NULL, doCV = FALSE, varCompEst = c("BL", "BRR"), samplingStrat = c("random","within popStruc"), nfolds = 2, nrep = 1) {
 
//			rJavaManager.getWebToolManager().doGBLUP(
//					resultFolderPath, pheno_file, geno_file, markerFormat, importRel, rel_file, rMatType, 
//	                map_file, traitNames, covariates, doCV, varCompEst, samplingStrat, nfolds, nrep);

			System.out.println(source1);
			System.out.println(source2);
			System.out.println(source3);
			System.out.println(source4);
//			System.out.println(source5);
//			System.out.println(source6);
//			System.out.println(source7);
			System.out.println(source8);
			System.out.println(getGBLUPOut);
			System.out.println(source9);

//			
			rEngine.eval(source1); //rEngine.eval("source(\'E:/StarPbtools/QTL/irri_new/trimStrings.R\')");
			rEngine.eval(source2);
			rEngine.eval(source3);
			rEngine.eval(source4);
//			rEngine.eval(source5);
//			rEngine.eval(source6);
//			rEngine.eval(source7);
			 if (doCV == "TRUE") rEngine.eval(source8);
			rEngine.eval(getGBLUPOut);
			 if (doCV == "TRUE") rEngine.eval(source9);

//			rEngine.eval("sink()");

			rEngineEnd();
			
			System.out.println("reached end.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}	
}

