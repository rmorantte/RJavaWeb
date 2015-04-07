package org.irri.breedingtool.rjava.staranalysis;


import org.irri.breedingtool.rjava.manager.RJavaManager;

public class TestFitDistribution {

	private static String BSLASH = "\\";
	private static String FSLASH = "/";
	private static String PATH1 = System.getProperty("user.dir")+ System.getProperty("file.separator") + "sample_datasets" + System.getProperty("file.separator");
	public static String DATA_PATH = PATH1.replace(BSLASH, FSLASH);
	
	public static void main(String[] args) {
		
		//supply path and name of active data
		String dataFileName = DATA_PATH + "beta.csv";
		String yVar = "Y"; 
		String byVar = null; //"TPL"; //
		String[] distrib = {"beta", "lnorm"}; //gamma"}; //{"norm"}; //
		String fitMethod = "mle";

		//TEST 1
//		String dataFileName = DATA_PATH + "Gerua.csv";
//		String yVar = "GRNYLD"; 
//		String byVar = null; //"TPL"; //
//		String[] distrib = {"norm", "lnorm"}; //gamma"}; //{"norm"}; //
//		String fitMethod = "mle";

////		TEST 2
//		String dataFileName = DATA_PATH + "Gerua.csv";
//		String yVar = "DFF"; 
//		String byVar = "TPL"; // null; //
//		String[] distrib = {"nbinom", "pois"}; //{"nbinom"}; //
//		String fitMethod = "mle";

		//TEST 3
//		String dataFileName = DATA_PATH + "Gerua_na.csv";
//		String yVar = "yldNA2"; //"GRNYLD"; // "yldSp"; // 
//		String byVar = "TPL"; //null; //
//		String[] distrib = { "norm", "gamma"}; //{"gamma", "exp", "norm"}; //
//		String fitMethod = "mle";

		String outputPath = DATA_PATH;
		
//		System.out.println(dataFileName);
//		System.out.println(outputPath);
		
		RJavaManager rJavaManager= new RJavaManager();
		rJavaManager.initStar();
		rJavaManager.getSTARAnalysisManager().doFitDistribution(dataFileName, 
				outputPath, yVar, byVar, distrib, fitMethod);
	}

}

