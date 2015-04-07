package org.irri.breedingtool.rjava.staranalysis;

import org.irri.breedingtool.rjava.manager.RJavaManager;

public class TestClusterKmeans {

	private static String BSLASH = "\\";
	private static String FSLASH = "/";
	private static String PATH1 = System.getProperty("user.dir")+ System.getProperty("file.separator") + "sample_datasets" + System.getProperty("file.separator");
	public static String DATA_PATH = PATH1.replace(BSLASH, FSLASH);
	
	public static void main(String[] args) {
		//supply path and name of active data
		String dataFileName = DATA_PATH + "Ratio_agro.csv"; 
		//supply path where the output will be placed
		String outputPath = DATA_PATH; 

		//specify parameters
		String[] var = {"GWT", "GrainL", "GrainW", "Flower", "PlantH"};
		String idVar = "Entry";
		Integer clusterNum = 2;
		boolean clusterMem = true;
		boolean descriptiveStatRaw = true;
		boolean corMatx = true;
		boolean descriptiveStat = true;
		boolean kgraph = true;
		boolean saveMem = true;
		
		RJavaManager rJavaManager= new RJavaManager();
		rJavaManager.initStar();
		rJavaManager.getSTARAnalysisManager().doClusterKmeans(dataFileName, outputPath,
				var, idVar, clusterMem,descriptiveStatRaw,corMatx, descriptiveStat, clusterNum, kgraph, saveMem);
	}

}
