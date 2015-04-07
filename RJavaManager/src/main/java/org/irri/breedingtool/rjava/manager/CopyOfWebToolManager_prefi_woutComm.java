//package org.irri.breedingtool.rjava.manager;
//
//import org.irri.breedingtool.rjava.manager.api.IRJavaPBToolsAnalysisManager;
//import org.irri.breedingtool.rjava.manager.api.IRJavaWebToolManager;
//import org.irri.breedingtool.rjava.utilities.InputTransform;
////import org.irri.breedingtool.rjava.utilities.InputTransform;
//import org.rosuda.JRI.Rengine;
//
//public class WebToolManager implements IRJavaWebToolManager {
//
//	private Rengine rEngine;
//	private InputTransform inputTransform;
//	
//	public WebToolManager(Rengine rEngine) {
//		this.rEngine = rEngine;
//		this.inputTransform= new InputTransform();
//	}
//	
//	private void rEngineEnd(){
//		String rm = "rm(list=ls(all=TRUE))";
//		rEngine.eval(rm);
//		rEngine.end();
//	}
//
//	
//		
//	@Override
//	public void doCreateQTLData(String resultFolderPath, String dataFormat, String format1, String crossType, String file1, 
//		String format2, String file2, String format3, String file3, String P_geno, int bcNum, int fNum) {
//
//		System.out.println("start QTL");
//		System.out.println("resultFolderPath: " + resultFolderPath);
//		
//		try {
//
//			rEngine.eval("library(qtl)");
//			System.out.println("library(qtl)");
//			rEngine.eval("library(lattice)");
//			System.out.println("library(lattice)");
//			rEngine.eval("library(qtlbim)");
//			System.out.println("library(qtlbim)");
//			rEngine.eval("library(PBTools)");
//			System.out.println("library(PBTools)");
//
//			
//			String readData = "QTLdata <- tryCatch(createQTLdata(\"" + resultFolderPath + "\", \"" + dataFormat + "\", \"" + format1 + "\", \"" + crossType + "\", \"" + file1 + "\", \"" +
//					format2 + "\", \"" + file2 + "\", \"" + format3 + "\", \"" + file3 + "\", \"" + P_geno + "\", " + bcNum + ", " + fNum + "))";
//
//			System.out.println(readData);
//			
//			rEngine.eval(readData);
//
//			rEngineEnd();
//			
//			System.out.println("reached end.");
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//	
//	@Override
//	public void doCheckQTLData(String dataCheckOutFileName, String outFileName, String resultFolderPath, 
//		String dataFormat, String format1, String crossType, 
//		String file1, String format2, String file2, String format3, String file3, String P_geno, 
//		int bcNum, int fNum, boolean doMissing, boolean deleteMiss, double cutOff, boolean doDistortionTest, double pvalCutOff, boolean doCompareGeno, double cutoffP, 
//		boolean doCheckMarkerOrder, double lodThreshold, boolean doCheckGenoErrors, double lodCutOff, double errorProb
//        ) {
//
//		System.out.println("start QTL");
//		System.out.println("resultFolderPath: " + resultFolderPath);
//		System.out.println("outFileName: " + outFileName);
//		
//		try {
//
//			rEngine.eval("library(qtl)");
//			System.out.println("library(qtl)");
//			rEngine.eval("library(lattice)");
//			System.out.println("library(lattice)");
//			rEngine.eval("library(qtlbim)");
//			System.out.println("library(qtlbim)");
//			rEngine.eval("library(PBTools)");
//			System.out.println("library(PBTools)");
//
//			
//			String readData = "QTLdata <- tryCatch(createQTLdata(\"" + resultFolderPath + "\", \"" + dataFormat + "\", \"" + format1 + "\", \"" + crossType + "\", \"" + file1 + "\", \"" +
//					format2 + "\", \"" + file2 + "\", \"" + format3 + "\", \"" + file3 + "\", \"" + P_geno + "\", " + bcNum + ", " + fNum + "))";
//			String getData = "crossData = QTLdata$crossObj";
//			
//			String sinkCheckData = "sink(\"" + dataCheckOutFileName + "\")";
//			String checkData = "chkQTLdata <- checkQTLdata(\"" + resultFolderPath + "\", crossData, \"" + crossType + "\", " + bcNum + ", " + fNum + ", " +
//					String.valueOf(doMissing).toUpperCase() + ", " + String.valueOf(deleteMiss).toUpperCase() + ", " + cutOff + ", " + String.valueOf(doDistortionTest).toUpperCase() +
//					", " + pvalCutOff + ", " + String.valueOf(doCompareGeno).toUpperCase() + ", " + cutoffP + ", " + String.valueOf(doCheckMarkerOrder).toUpperCase() + ", " + lodThreshold + ", " + 
//					String.valueOf(doCheckGenoErrors).toUpperCase() + ", " + lodCutOff + ", " + errorProb + ")";
//			
//			System.out.println(readData);
//			System.out.println(getData);
//			System.out.println(sinkCheckData);
//			System.out.println(checkData);
//			System.out.println("sink()");
//			
//			rEngine.eval(readData);
//			rEngine.eval(getData);
//			rEngine.eval(sinkCheckData);
//			rEngine.eval(checkData);
//			rEngine.eval("sink()");
//
//			rEngineEnd();
//			
//			System.out.println("reached end.");
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//	
//	
//	@Override
//	public void doQtl(
//			String dataCheckOutFileName, 
//			String outFileName, String resultFolderPath, 
//			String dataFormat, String format1, 
//			String crossType, 
//			String file1, String format2, String file2, String format3, String file3, String P_geno, 
//			int bcNum, int fNum,
//		String traitType, String[] yVars, String mMethod, double stepCalc, double errCalc, String mapCalc, double lodCutoffM, String phenoModel, 
//		String alMethod, int nPermutations, int numCovar, double winSize, String genoName, boolean threshLiJi, double thresholdNumericalValue,  
//		double minDist, double stepSize, boolean addModel, int numCofac, boolean mlAlgo, boolean setupModel, boolean includeEpistasis, boolean useDepPrior,
//        int priorMain, int priorAll, String maxQTLs, double priorProb) {
//
//		String yVarsVector= inputTransform.createRVector(yVars);
//
//		System.out.println("start QTL");
//		System.out.println("resultFolderPath: " + resultFolderPath);
//		System.out.println("outFileName: " + outFileName);
//		
//		try {
//
//			rEngine.eval("library(qtl)");
//			System.out.println("library(qtl)");
//			rEngine.eval("library(lattice)");
//			System.out.println("library(lattice)");
//			rEngine.eval("library(qtlbim)");
//			System.out.println("library(qtlbim)");
//			rEngine.eval("library(PBTools)");
//			System.out.println("library(PBTools)");
//
//			
//			String readData = "QTLdata <- tryCatch(createQTLdata(\"" + resultFolderPath + "\", \"" + dataFormat + "\", \"" + format1 + "\", \"" + crossType + "\", \"" + file1 + "\", \"" +
//					format2 + "\", \"" + file2 + "\", \"" + format3 + "\", \"" + file3 + "\", \"" + P_geno + "\", " + bcNum + ", " + fNum + "))";
//			String getData = "crossData = QTLdata$crossObj";
//
//			String sinkQTL = "sink(\"" + outFileName + "\")";
//
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
//			System.out.println(readData);
//			System.out.println(getData);
//			
//			if (mMethod == "BIM") System.out.println(getBIMdata);				
//			System.out.println(sinkQTL);
//			System.out.println(doQTL);
//			System.out.println("sink()");
//			
//			rEngine.eval(readData);
//			rEngine.eval(getData);
//			if (mMethod == "BIM") rEngine.eval(getBIMdata);				
//			rEngine.eval(sinkQTL);
//			rEngine.eval(doQTL);
//			rEngine.eval("sink()");
//
//			rEngineEnd();
//			
//			System.out.println("reached end.");
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//	
//	
//
//}
//
