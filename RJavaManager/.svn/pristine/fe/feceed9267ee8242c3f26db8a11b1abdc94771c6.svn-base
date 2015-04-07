package org.irri.breedingtool.rjava.manager;

import org.irri.breedingtool.rjava.manager.api.IRJavaSTARDesignManager;
import org.irri.breedingtool.rjava.utilities.InputTransform;
import org.rosuda.JRI.Rengine;

public class STARDesignManager implements IRJavaSTARDesignManager {

	private Rengine rEngine;
	private InputTransform inputTransform;
	private StringBuilder rscriptCommand;
	
	public STARDesignManager(Rengine rEngine) {
		this.rEngine = rEngine;
		this.inputTransform= new InputTransform();
	}
	
	private void rEngineEnd(){
		String rm = "rm(list=ls(all=TRUE))";
		rEngine.eval(rm);
		rEngine.end();
	}
	
	@Override
	public void doDesignCRD(String path, String fieldBookName, String[] factorName, String[] factorID, Integer[] factorLevel,
			Integer rep, Integer trial, Integer numFieldRow, String fieldOrder){

		String inputList = inputTransform.createRList(factorName, factorLevel, factorID);

		//defining the R statements for randomization of completely randomized design
		rscriptCommand = new StringBuilder();
		String CSVOutput = path + fieldBookName + ".csv";
		String TxtOuptut = path + fieldBookName + ".txt";
		
		String sinkIn = "sink(\"" + TxtOuptut + "\")";
		String pkgIntro = "cat(\"Statistical Tool for Agricultural Research (STAR)\\nResult of Randomization\\n\",date(),\"\\n\\n\\n\", sep = \"\")";
		String funcRandomize = "result <- try(";
		String command = "designCRD("+inputList+", r = "+ rep +", trial = "+ trial +", numFieldRow = "+ numFieldRow;
		if (fieldOrder == "Plot Order") {
			command = command + ", serpentine = FALSE, file = \""+ CSVOutput +"\")";
		} else {
			command = command + ", serpentine = TRUE, file = \""+ CSVOutput +"\")";
		}
		funcRandomize = funcRandomize + command + ", silent = TRUE)";
		
		System.out.println(sinkIn);
		System.out.println(pkgIntro);
		System.out.println(funcRandomize);

		rEngine.eval(sinkIn);
		rEngine.eval(pkgIntro);
		rEngine.eval(funcRandomize);
		//save sorted to csv file
//		String sortFile = "write.csv(result$fieldbook[order(result$fieldbook$Trial, result$fieldbook$PlotNum),], file = \""+ CSVOutput +"\", row.names = FALSE)";
//		System.out.println(sortFile);
//		rEngine.eval(sortFile);
		
		String runSuccessCommand = rEngine.eval("class(result)").asString();
		if (runSuccessCommand.equals("try-error")) {
			String errorMsg1 = "msg <- trimStrings(strsplit(result, \":\")[[1]])";
			String errorMsg2 = "msg <- trimStrings(paste(strsplit(msg, \"\\n\")[[length(msg)]], collapse = \" \"))";
			String errorMsg3 = "msg <- gsub(\"\\\"\", \"\", msg)";
			String errorMsg4 = "cat(\"Error in designCRD:\\n\",msg, sep = \"\")";

			System.out.println(errorMsg1);
			System.out.println(errorMsg2);
			System.out.println(errorMsg3);
			System.out.println(errorMsg4);
			
			rEngine.eval(errorMsg1);
			rEngine.eval(errorMsg2);
			rEngine.eval(errorMsg3);
			rEngine.eval(errorMsg4);
		} 
		else {
			String checkOutput = "if (nrow(result$fieldbook) != 0) {\n";
			checkOutput = checkOutput + "    cat(\"\\nLayout for Completely Randomized Design:\",\"\\n\\n\")\n";
			checkOutput = checkOutput + "    for (i in (1:length(result$layout))) { \n";
			checkOutput = checkOutput + "          printLayout(result$layout[[i]], result$plotNum[[i]], RowLabel = NULL, ColLabel = NULL, title = paste(\"Trial = \", i, sep = \"\"))\n";
			checkOutput = checkOutput + "          cat(\"\\n\")\n";
			checkOutput = checkOutput + "    }\n";
			checkOutput = checkOutput + "    cat(\"\\n\",\"**Note: Cells contain plot numbers on top, treatments/entries below\")\n";
			checkOutput = checkOutput + "}";
	
			System.out.println(checkOutput);
			rEngine.eval(checkOutput);
		}
		String sinkOut = "sink()";
		System.out.println(sinkOut);
		rEngine.eval(sinkOut);
		
		rscriptCommand.append(command+"\n");
		rEngineEnd();
	}
	
	@Override
	public void doDesignRCBD(String path, String fieldBookName, String[] factorName, String[] factorID, Integer[] factorLevel,
			Integer blk, Integer trial, Integer numFieldRow, Integer rowPerBlk, String fieldOrder){

		String inputList = inputTransform.createRList(factorName, factorLevel, factorID);

		//defining the R statements for randomization of randomized complete block design
		rscriptCommand = new StringBuilder();
		String CSVOutput = path + fieldBookName + ".csv";
		String TxtOuptut = path + fieldBookName + ".txt";
		
		String sinkIn = "sink(\"" + TxtOuptut + "\")";
		String pkgIntro = "cat(\"Statistical Tool for Agricultural Research (STAR)\\nResult of Randomization\\n\",date(),\"\\n\\n\\n\", sep = \"\")";
		String funcRandomize = "result <- try(";
		String command = "designRCBD(generate = "+ inputList +", r = "+ blk +", trial = "+ trial +", numFieldRow = "+ numFieldRow + ", rowPerBlk = "+ rowPerBlk;
		if (fieldOrder == "Plot Order") {
			command = command + ", serpentine = FALSE";
		} else {
			command = command + ", serpentine = TRUE";
		}
		command = command + ", display = TRUE, file = \""+ CSVOutput +"\")";
		funcRandomize = funcRandomize + command + ", silent = TRUE)";
		
		System.out.println(sinkIn);
		System.out.println(pkgIntro);
		System.out.println(funcRandomize);
		
		rEngine.eval(sinkIn);
		rEngine.eval(pkgIntro);
		rEngine.eval(funcRandomize);

		//save sorted to csv file
//		String sortFile = "write.csv(result$fieldbook[order(result$fieldbook$Trial, result$fieldbook$PlotNum),], file = \""+ CSVOutput +"\", row.names = FALSE)";
//		System.out.println(sortFile);
//		rEngine.eval(sortFile);

		
		String runSuccessCommand = rEngine.eval("class(result)").asString();
		if (runSuccessCommand.equals("try-error")) {
			String errorMsg1 = "msg <- trimStrings(strsplit(result, \":\")[[1]])";
			String errorMsg2 = "msg <- trimStrings(paste(strsplit(msg, \"\\n\")[[length(msg)]], collapse = \" \"))";
			String errorMsg3 = "msg <- gsub(\"\\\"\", \"\", msg)";
			String errorMsg4 = "cat(\"Error in designRCBD:\\n\",msg, sep = \"\")";

			System.out.println(errorMsg1);
			System.out.println(errorMsg2);
			System.out.println(errorMsg3);
			System.out.println(errorMsg4);
			
			rEngine.eval(errorMsg1);
			rEngine.eval(errorMsg2);
			rEngine.eval(errorMsg3);
			rEngine.eval(errorMsg4);
		} 
		else {
			String checkOutput = "if (nrow(result$fieldbook) != 0) {\n";
			checkOutput = checkOutput + "    cat(\"\\nLayout for Randomized Complete Block Design:\",\"\\n\\n\")\n";
			checkOutput = checkOutput + "    for (i in (1:length(result$layout))) { \n";
			checkOutput = checkOutput + "          printLayout(result$layout[[i]], result$plotNum, RowLabel = rownames(result$layout[[i]]), ColLabel = colnames(result$layout[[i]]), title = paste(\"Trial = \", i, sep = \"\"))\n";
			checkOutput = checkOutput + "          cat(\"\\n\")\n";
			checkOutput = checkOutput + "    }\n";
			checkOutput = checkOutput + "    cat(\"\\n\",\"**Note: Cells contain plot numbers on top, treatments/entries below\")\n";
			checkOutput = checkOutput + "}";
	
			System.out.println(checkOutput);
			rEngine.eval(checkOutput);
		}

		String sinkOut = "sink()";
		System.out.println(sinkOut);
		rEngine.eval(sinkOut);

		rscriptCommand.append(command+"\n");
		rEngineEnd();
	}
	
	@Override
	public void doDesignLSD(String path, String fieldBookName, String[] factorName, String factorID[], Integer[] factorLevel, 
			Integer trial, String fieldOrder) {

		String inputList = inputTransform.createRList(factorName, factorLevel, factorID);

		//defining the R statements for randomization of latin square design
		rscriptCommand = new StringBuilder();
		String CSVOutput = path + fieldBookName + ".csv";
		String TxtOuptut = path + fieldBookName + ".txt";
		
		String sinkIn = "sink(\"" + TxtOuptut + "\")";
		String pkgIntro = "cat(\"Statistical Tool for Agricultural Research (STAR)\\nResult of Randomization\\n\",date(),\"\\n\\n\\n\", sep = \"\")";
		String funcRandomize = "result <- try(";
		String command = "designLSD("+ inputList +", trial = "+ trial;
		if (fieldOrder == "Plot Order") {
			command = command + ", serpentine = FALSE, file = \""+ CSVOutput +"\")";
		} else {
			command = command + ", serpentine = TRUE, file = \""+ CSVOutput +"\")";
		}
		funcRandomize = funcRandomize + command + ", silent = TRUE)";
		
		
		System.out.println(sinkIn);
		System.out.println(pkgIntro);
		System.out.println(funcRandomize);

		rEngine.eval(sinkIn);
		rEngine.eval(pkgIntro);
		rEngine.eval(funcRandomize);
		//save sorted to csv file
//		String sortFile = "write.csv(result$fieldbook[order(result$fieldbook$Trial, result$fieldbook$PlotNum),], file = \""+ CSVOutput +"\", row.names = FALSE)";
//		System.out.println(sortFile);
//		rEngine.eval(sortFile);

		String runSuccessCommand = rEngine.eval("class(result)").asString();
		if (runSuccessCommand.equals("try-error")) {
			String errorMsg1 = "msg <- trimStrings(strsplit(result, \":\")[[1]])";
			String errorMsg2 = "msg <- trimStrings(paste(strsplit(msg, \"\\n\")[[length(msg)]], collapse = \" \"))";
			String errorMsg3 = "msg <- gsub(\"\\\"\", \"\", msg)";
			String errorMsg4 = "cat(\"Error in designLSD:\\n\",msg, sep = \"\")";

			System.out.println(errorMsg1);
			System.out.println(errorMsg2);
			System.out.println(errorMsg3);
			System.out.println(errorMsg4);
			
			rEngine.eval(errorMsg1);
			rEngine.eval(errorMsg2);
			rEngine.eval(errorMsg3);
			rEngine.eval(errorMsg4);
		} 
		else {
			String checkOutput = "if (nrow(result$fieldbook) != 0) {\n";
			checkOutput = checkOutput + "    cat(\"\\nLayout for Latin Square Design:\",\"\\n\\n\")\n";
			checkOutput = checkOutput + "    for (i in (1:length(result$layout))) { \n";
			checkOutput = checkOutput + "          printLayout(result$layout[[i]], result$plotNum[[i]], RowLabel = rownames(result$layout[[i]]), ColLabel = colnames(result$layout[[i]]), title = paste(\"Trial = \", i, sep = \"\"))\n";
			checkOutput = checkOutput + "          cat(\"\\n\")\n";
			checkOutput = checkOutput + "    }\n";
			checkOutput = checkOutput + "    cat(\"\\n\",\"**Note: Cells contain plot numbers on top, treatments/entries below\")\n";
			checkOutput = checkOutput + "}";
	
			System.out.println(checkOutput);
			rEngine.eval(checkOutput);
		}
		String sinkOut = "sink()";
		System.out.println(sinkOut);
		rEngine.eval(sinkOut);

		rscriptCommand.append(command+"\n");
		rEngineEnd();
	}
	
	@Override
	public void doDesignSplit(String path, String fieldBookName, String main, String sub, String ssub, 
			String sssub, String[]  factorID, Integer[] factorLevel, Integer rep, Integer trial, String design, 
			Integer numFieldRow, Integer rowPerBlk, Integer rowPerMain, Integer rowPerSub, Integer rowPerSubSub, 
			String fieldOrder){

		//defining the R statements for randomization of Family of Split plot design
		rscriptCommand = new StringBuilder();
		String CSVOutput = path + fieldBookName + ".csv";
		String TxtOuptut = path + fieldBookName + ".txt";
		
		String sinkIn = "sink(\"" + TxtOuptut + "\")";
		String pkgIntro = "cat(\"Statistical Tool for Agricultural Research (STAR)\\nResult of Randomization\\n\",date(),\"\\n\\n\\n\", sep = \"\")";
		String funcRandomize = "result <- try(";
		String command = "designSplit(main = list("+ main +" = paste(\""+ factorID[0] +"\", paste(1:"+ factorLevel[0] +"), sep = \"\"))";
		command = command + ", sub = list("+ sub +" = paste(\""+ factorID[1] +"\", paste(1:"+ factorLevel[1] +"), sep = \"\"))";
		if (ssub != null) {
			command = command + ", ssub = list("+ ssub +" = paste(\""+ factorID[2] +"\", paste(1:"+ factorLevel[2] +"), sep = \"\"))";
		}
		if (sssub != null) {
			command = command + ", sssub = list("+ sssub +" = paste(\""+ factorID[3] +"\", paste(1:"+ factorLevel[3] +"), sep = \"\"))";
		}
		if (design != "lsd") {
			command = command + ", r = "+ rep ;
		}
		command = command + ", trial = "+ trial + ", design = \""+ design +"\"";
		if (design != "lsd") {
			command = command + ", numFieldRow = "+ numFieldRow;
		}
		if (design == "rcbd") {
			command = command + ", rowPerBlk = "+ rowPerBlk;
		}
		command = command + ", rowPerMain = "+ rowPerMain;
		if (ssub != null) {
			command = command + ", rowPerSub = "+ rowPerSub;
		}
		if (sssub != null) {
			command = command + ", rowPerSubSub = "+ rowPerSubSub;
		}
		if (fieldOrder == "Plot Order") {
			command = command + ", serpentine = FALSE";
		} else {
			command = command + ", serpentine = TRUE";
		}
		command = command + ", file = \"" + CSVOutput +"\")";
		funcRandomize = funcRandomize + command + ", silent = TRUE)";
				
		System.out.println(sinkIn);
		System.out.println(pkgIntro);
		System.out.println(funcRandomize);

		rEngine.eval(sinkIn);
		rEngine.eval(pkgIntro);
		rEngine.eval(funcRandomize);

//		String sortFile = "write.csv(result$fieldbook, file = \""+ CSVOutput +"\", row.names = FALSE)";
//		System.out.println(sortFile);
//		rEngine.eval(sortFile);
		
	String runSuccessCommand = rEngine.eval("class(result)").asString();
		if (runSuccessCommand.equals("try-error")) {
			String errorMsg1 = "msg <- trimStrings(strsplit(result, \":\")[[1]])";
			String errorMsg2 = "msg <- trimStrings(paste(strsplit(msg, \"\\n\")[[length(msg)]], collapse = \" \"))";
			String errorMsg3 = "msg <- gsub(\"\\\"\", \"\", msg)";
			String errorMsg4 = "cat(\"Error in designSplit:\\n\",msg, sep = \"\")";

			System.out.println(errorMsg1);
			System.out.println(errorMsg2);
			System.out.println(errorMsg3);
			System.out.println(errorMsg4);
			
			rEngine.eval(errorMsg1);
			rEngine.eval(errorMsg2);
			rEngine.eval(errorMsg3);
			rEngine.eval(errorMsg4);
		} 
		else {
			String checkOutput = "if (nrow(result$fieldbook) != 0) {\n";
			checkOutput = checkOutput + "    cat(\"\\nLayout for \")\n";
			if (ssub == null && sssub == null) checkOutput = checkOutput + "    cat(\"Split Plot in \")\n";
			if (ssub != null && sssub == null) checkOutput = checkOutput + "    cat(\"Split-Split Plot in \")\n";
			if (ssub != null && sssub != null) checkOutput = checkOutput + "    cat(\"Split-Split-Split Plot in \")\n";
			if (design == "crd") checkOutput = checkOutput + "    cat(\"Completely Randomized Design: \",\"\\n\\n\")\n";
			if (design == "rcbd") checkOutput = checkOutput + "    cat(\"Randomized Complete Block Design: \",\"\\n\\n\")\n";
			if (design == "lsd") checkOutput = checkOutput + "    cat(\"Latin Square Design: \",\"\\n\\n\")\n";
			checkOutput = checkOutput + "    for (i in (1:length(result$layout))) { \n";
			checkOutput = checkOutput + "          printLayout(result$layout[[i]], result$plotNum, RowLabel = rownames(result$layout[[i]]), ColLabel = colnames(result$layout[[i]]), title = paste(\"Trial = \", i, sep = \"\"))\n";
			checkOutput = checkOutput + "          cat(\"\\n\")\n";
			checkOutput = checkOutput + "    }\n";
			checkOutput = checkOutput + "    cat(\"\\n\",\"**Note: Cells contain plot numbers on top, treatments/entries below\")\n";
			checkOutput = checkOutput + "}";
	
			System.out.println(checkOutput);
			rEngine.eval(checkOutput);
		}

		String sinkOut = "sink()";
		System.out.println(sinkOut);
		rEngine.eval(sinkOut);
	
		rscriptCommand.append(command+"\n");
		rEngineEnd();
	}
	
	@Override
	public void doDesignStrip(String path, String fieldBookName, String vertical, String horizontal, String sub, String ssub, 
			String[] factorID, Integer[] factorLevel, Integer rep, Integer trial, Integer numFieldRow, Integer rowPerMain, 
			Integer rowPerSub, String fieldOrder){

		//defining the R statements for randomization for Family of Strip Design
		rscriptCommand = new StringBuilder();
		String CSVOutput = path + fieldBookName + ".csv";
		String TxtOuptut = path + fieldBookName + ".txt";
		
		String sinkIn = "sink(\"" + TxtOuptut + "\")";
		String pkgIntro = "cat(\"Statistical Tool for Agricultural Research (STAR)\\nResult of Randomization\\n\",date(),\"\\n\\n\\n\", sep = \"\")";		
		String funcRandomize = "result <- try(";
		String command = "designStrip(vertical = list("+ vertical +" = paste(\""+ factorID[0] +"\", paste(1:"+ factorLevel[0] +"), sep = \"\"))";
		command = command + ", horizontal = list("+ horizontal +" = paste(\""+ factorID[1] +"\", paste(1:"+ factorLevel[1] +"), sep = \"\"))";
		if (sub != null) {
			command = command + ", sub = list("+ sub +" = paste(\""+ factorID[2] +"\", paste(1:"+ factorLevel[2] +"), sep = \"\"))";
		}
		if (ssub != null) {
			command = command + ", ssub = list("+ ssub +" = paste(\""+ factorID[3] +"\", paste(1:"+ factorLevel[3] +"), sep = \"\"))";
		}
		
		command = command + ", r = "+ rep +", trial = "+ trial +", numFieldRow = "+ numFieldRow;
		if (sub != null) {
			command = command + ", rowPerMain = "+ rowPerMain;
		} 
		if (ssub != null) {
			command = command + ", rowPerSub = "+ rowPerSub;
		}
		if (fieldOrder == "Plot Order") {
			command = command + ", serpentine = FALSE";
		} else {
			command = command + ", serpentine = TRUE";
		}
		command = command + ", file = \"" + CSVOutput +"\")";
		funcRandomize = funcRandomize + command + ", silent = TRUE)";
				
		System.out.println(sinkIn);
		System.out.println(pkgIntro);
		System.out.println(funcRandomize);

		//R statements passed on to the R engine
		rEngine.eval(sinkIn);
		rEngine.eval(pkgIntro);
		rEngine.eval(funcRandomize);

//		String sortFile = "write.csv(result$fieldbook, file = \""+ CSVOutput +"\", row.names = FALSE)";
//		System.out.println(sortFile);
//		rEngine.eval(sortFile);
		
		String runSuccessCommand = rEngine.eval("class(result)").asString();
		if (runSuccessCommand.equals("try-error")) {
			String errorMsg1 = "msg <- trimStrings(strsplit(result, \":\")[[1]])";
			String errorMsg2 = "msg <- trimStrings(paste(strsplit(msg, \"\\n\")[[length(msg)]], collapse = \" \"))";
			String errorMsg3 = "msg <- gsub(\"\\\"\", \"\", msg)";
			String errorMsg4 = "cat(\"Error in designStrip:\\n\",msg, sep = \"\")";

			System.out.println(errorMsg1);
			System.out.println(errorMsg2);
			System.out.println(errorMsg3);
			System.out.println(errorMsg4);
			
			rEngine.eval(errorMsg1);
			rEngine.eval(errorMsg2);
			rEngine.eval(errorMsg3);
			rEngine.eval(errorMsg4);
		} 
		else {
			String checkOutput = "if (nrow(result$fieldbook) != 0) {\n";
			if (sub == null && ssub == null) checkOutput = checkOutput + "    cat(\"\\nLayout for Strip Plot:\",\"\\n\\n\")\n";
			if (sub != null && ssub == null) checkOutput = checkOutput + "    cat(\"\\nLayout for Strip-Split Plot:\",\"\\n\\n\")\n";
			if (sub != null && ssub != null) checkOutput = checkOutput + "    cat(\"\\nLayout for Strip-Split-Split Plot:\",\"\\n\\n\")\n";
			checkOutput = checkOutput + "    for (i in (1:length(result$layout))) { \n";
			checkOutput = checkOutput + "          printLayout(result$layout[[i]], result$plotNum, RowLabel = rownames(result$layout[[i]]), ColLabel = colnames(result$layout[[i]]), title = paste(\"Trial = \", i, sep = \"\"))\n";
			checkOutput = checkOutput + "          cat(\"\\n\")\n";
			checkOutput = checkOutput + "    }\n";
			checkOutput = checkOutput + "    cat(\"\\n\",\"**Note: Cells contain plot numbers on top, treatments/entries below\")\n";
			checkOutput = checkOutput + "}";
	
			System.out.println(checkOutput);
			rEngine.eval(checkOutput);
		}
		
		String sinkOut = "sink()";
		System.out.println(sinkOut);
		rEngine.eval(sinkOut);

		rscriptCommand.append(command+"\n");
		rEngineEnd();
	}
	
	@Override
	public void doDesignBIBD(String path, String fieldBookName, Integer numTrmt, Integer blkSize, 
			Integer trial, Integer numFieldRow, Integer rowPerBlk, String fieldOrder){

		//defining the R statements for randomization for balanced incomplete block design
		rscriptCommand = new StringBuilder();
		String CSVOutput = path + fieldBookName + ".csv";
		String TxtOuptut = path + fieldBookName + ".txt";
		
		String sinkIn = "sink(\"" + TxtOuptut + "\")";
		String pkgIntro = "cat(\"Statistical Tool for Agricultural Research (STAR)\\nResult of Randomization\\n\",date(),\"\\n\\n\\n\", sep = \"\")";
		String funcRandomize = "result <- try(";
		String command = "designBIBD(generate = list(Treatment = paste(\"T\", paste(1:"+ numTrmt +"), sep = \"\"))";
		command = command + ", blkSize = "+ blkSize +", trial = "+ trial + ", numFieldRow = "+ numFieldRow +", rowPerBlk = "+ rowPerBlk;

		if (fieldOrder == "Plot Order") {
			command = command + ", serpentine = FALSE";
		} else {
			command = command + ", serpentine = TRUE";
		}
		command = command + ", display = TRUE, file = \""+ CSVOutput +"\")";
		funcRandomize = funcRandomize + command + ", silent = TRUE)";
				
		System.out.println(sinkIn);
		System.out.println(pkgIntro);
		System.out.println(funcRandomize);

		rEngine.eval(sinkIn);
		rEngine.eval(pkgIntro);
		rEngine.eval(funcRandomize);

		String runSuccessCommand = rEngine.eval("class(result)").asString();
		if (runSuccessCommand.equals("try-error")) {
			String errorMsg1 = "msg <- trimStrings(strsplit(result, \":\")[[1]])";
			String errorMsg2 = "msg <- trimStrings(paste(strsplit(msg, \"\\n\")[[length(msg)]], collapse = \" \"))";
			String errorMsg3 = "msg <- gsub(\"\\\"\", \"\", msg)";
			String errorMsg4 = "cat(\"Error in designBIBD:\\n\",msg, sep = \"\")";

			System.out.println(errorMsg1);
			System.out.println(errorMsg2);
			System.out.println(errorMsg3);
			System.out.println(errorMsg4);
			
			rEngine.eval(errorMsg1);
			rEngine.eval(errorMsg2);
			rEngine.eval(errorMsg3);
			rEngine.eval(errorMsg4);
		} 
		else {
			String checkOutput = "if (nrow(result$fieldbook) != 0) {\n";
			checkOutput = checkOutput + "    cat(\"\\nLayout for Balanced Incomplete Block Design:\",\"\\n\\n\")\n";
			checkOutput = checkOutput + "    for (i in (1:length(result$layout))) { \n";
			checkOutput = checkOutput + "          printLayout(result$layout[[i]], result$plotNum, RowLabel = rownames(result$layout[[i]]), ColLabel = colnames(result$layout[[i]]), title = paste(\"Trial = \", i, sep = \"\"))\n";
			checkOutput = checkOutput + "          cat(\"\\n\")\n";
			checkOutput = checkOutput + "    }\n";
			checkOutput = checkOutput + "    cat(\"\\n\",\"**Note: Cells contain plot numbers on top, treatments/entries below\")\n";
			checkOutput = checkOutput + "}";
	
			System.out.println(checkOutput);
			rEngine.eval(checkOutput);
		}
		String sinkOut = "sink()";
		System.out.println(sinkOut);
		rEngine.eval(sinkOut);
		
		rscriptCommand.append(command+"\n");
		rEngineEnd();
	}
	
	@Override
	public void doDesignAug(String dataFileName, String outFileName, 
			Integer repTrmt, Integer unrepTrmt, Integer rep, Integer trial,
			String design){

		//defining the R statements for randomization for augmented design
		rscriptCommand = new StringBuilder();
		String sinkIn = "sink(\"" + outFileName + "\")";
		String pkgIntro = "cat(\"Statistical Tool for Agricultural Research (STAR)\\nResult of Randomization\\n\",date(),\"\\n\\n\\n\", sep = \"\")";
		String funcRandomize = "result <- try(";
		String command = "designAugmented(check = "+ repTrmt +", newTrmt = "+ unrepTrmt;
		if (design == "rcbd") {
			command = command + ", r = "+ rep +", trial = "+ trial + ", design = \""+ design + "\", file = \""+ dataFileName +"\")";
		} else {
			command = command + ", trial = "+ trial + ", design = \""+ design + "\", file = \""+ dataFileName +"\")";
		}
		funcRandomize = funcRandomize + command + ", silent = TRUE)";
		
		System.out.println(sinkIn);
		System.out.println(pkgIntro);
		System.out.println(funcRandomize);

		rEngine.eval(sinkIn);
		rEngine.eval(pkgIntro);
		rEngine.eval(funcRandomize);

		String runSuccessCommand = rEngine.eval("class(result)").asString();
		if (runSuccessCommand.equals("try-error")) {
			String errorMsg1 = "msg <- trimStrings(strsplit(result, \":\")[[1]])";
			String errorMsg2 = "msg <- trimStrings(paste(strsplit(msg, \"\\n\")[[length(msg)]], collapse = \" \"))";
			String errorMsg3 = "msg <- gsub(\"\\\"\", \"\", msg)";
			String errorMsg4 = "cat(\"Error in designAugmented:\\n\",msg, sep = \"\")";

			System.out.println(errorMsg1);
			System.out.println(errorMsg2);
			System.out.println(errorMsg3);
			System.out.println(errorMsg4);
			
			rEngine.eval(errorMsg1);
			rEngine.eval(errorMsg2);
			rEngine.eval(errorMsg3);
			rEngine.eval(errorMsg4);
		} 

		String sinkOut = "sink()";
		System.out.println(sinkOut);
		rEngine.eval(sinkOut);
		
		rscriptCommand.append(command+"\n");
		rEngineEnd();
	}
	
	@Override
	public void doDesignAugLSD(String path, String fieldBookName, Integer repTrmt, Integer unrepTrmt, Integer fieldRow, 
			Integer trial, String fieldOrder){

		//defining the R statements for randomization for augmented design in Latin Square Design
		rscriptCommand = new StringBuilder();
		String CSVOutput = path + fieldBookName + ".csv";
		String TxtOuptut = path + fieldBookName + ".txt";
		
		String sinkIn = "sink(\"" + TxtOuptut + "\")";
		String pkgIntro = "cat(\"Statistical Tool for Agricultural Research (STAR)\\nResult of Randomization\\n\",date(),\"\\n\\n\\n\", sep = \"\")";
		String funcRandomize = "result <- try(";
		String command = "designAugmentedLSD(check = "+ repTrmt +", newTrmt = "+ unrepTrmt;
		command = command + ", trial = "+ trial + ", numFieldRow = "+ fieldRow;
		if (fieldOrder == "Plot Order") {
			command = command + ", serpentine = FALSE, file = \""+ CSVOutput +"\")";
		} else {
			command = command + ", serpentine = TRUE, file = \""+ CSVOutput +"\")";
		}
		funcRandomize = funcRandomize + command + ", silent = TRUE)";
		
		System.out.println(sinkIn);
		System.out.println(pkgIntro);
		System.out.println(funcRandomize);


		rEngine.eval(sinkIn);
		rEngine.eval(pkgIntro);
		rEngine.eval(funcRandomize);

		//save sorted to csv file
//		String sortFile = "write.csv(result$fieldbook[order(result$fieldbook$Trial, result$fieldbook$PlotNum),], file = \""+ CSVOutput +"\", row.names = FALSE)";
//		System.out.println(sortFile);
//		rEngine.eval(sortFile);
		
		
		String runSuccessCommand = rEngine.eval("class(result)").asString();
		if (runSuccessCommand.equals("try-error")) {
			String errorMsg1 = "msg <- trimStrings(strsplit(result, \":\")[[1]])";
			String errorMsg2 = "msg <- trimStrings(paste(strsplit(msg, \"\\n\")[[length(msg)]], collapse = \" \"))";
			String errorMsg3 = "msg <- gsub(\"\\\"\", \"\", msg)";
			String errorMsg4 = "cat(\"Error in designAugmentedLSD:\\n\",msg, sep = \"\")";

			System.out.println(errorMsg1);
			System.out.println(errorMsg2);
			System.out.println(errorMsg3);
			System.out.println(errorMsg4);
			
			rEngine.eval(errorMsg1);
			rEngine.eval(errorMsg2);
			rEngine.eval(errorMsg3);
			rEngine.eval(errorMsg4);
		} 
		else {
			String checkOutput = "if (nrow(result$fieldbook) != 0) {\n";
			checkOutput = checkOutput + "    cat(\"\\nLayout for Augmented Latin Square Design:\",\"\\n\\n\")\n";
			checkOutput = checkOutput + "    for (i in (1:length(result$layout))) { \n";
			checkOutput = checkOutput + "          printLayout(result$layout[[i]], result$plotNum[[i]], RowLabel = rownames(result$layout[[i]]), ColLabel = colnames(result$layout[[i]]), title = paste(\"Trial = \", i, sep = \"\"))\n";
			checkOutput = checkOutput + "          cat(\"\\n\")\n";
			checkOutput = checkOutput + "    }\n";
			checkOutput = checkOutput + "    cat(\"\\n\",\"**Note: Cells contain plot numbers on top, treatments/entries below\")\n";
			checkOutput = checkOutput + "}";
			
			System.out.println(checkOutput);
			rEngine.eval(checkOutput);
		}

		String sinkOut = "sink()";
		System.out.println(sinkOut);
		rEngine.eval(sinkOut);
		
		rscriptCommand.append(command+"\n");
		rEngineEnd();
	}
	
	@Override
	public void doDesignAugRCB(String path, String fieldBookName, Integer repTrmt, Integer unrepTrmt, Integer Blk, Integer fieldRow, 
			Integer trial, String fieldOrder){

		//defining the R statements for randomization for augmented design in Latin Square Design
		rscriptCommand = new StringBuilder();
		String CSVOutput = path + fieldBookName + ".csv";
		String TxtOuptut = path + fieldBookName + ".txt";
		
		String sinkIn = "sink(\"" + TxtOuptut + "\")";
		String pkgIntro = "cat(\"Statistical Tool for Agricultural Research (STAR)\\nResult of Randomization\\n\",date(),\"\\n\\n\\n\", sep = \"\")";
		String funcRandomize = "result <- try(";
		String command = "designAugmentedRCB(checkTrmt = "+ repTrmt +", newTrmt = "+ unrepTrmt + ", r = "+ Blk;
		command = command + ", trial = "+ trial + ", numFieldRow = "+ fieldRow;
		if (fieldOrder == "Plot Order") {
			command = command + ", serpentine = FALSE, file = \""+ CSVOutput +"\")";
		} else {
			command = command + ", serpentine = TRUE, file = \""+ CSVOutput +"\")";
		}
		funcRandomize = funcRandomize + command + ", silent = TRUE)";
		
		System.out.println(sinkIn);
		System.out.println(pkgIntro);
		System.out.println(funcRandomize);


		rEngine.eval(sinkIn);
		rEngine.eval(pkgIntro);
		rEngine.eval(funcRandomize);

		//save sorted to csv file
//		String sortFile = "write.csv(result$fieldbook[order(result$fieldbook$Trial, result$fieldbook$PlotNum),], file = \""+ CSVOutput +"\", row.names = FALSE)";
//		System.out.println(sortFile);
//		rEngine.eval(sortFile);
		
		String runSuccessCommand = rEngine.eval("class(result)").asString();
		if (runSuccessCommand.equals("try-error")) {
			String errorMsg1 = "msg <- trimStrings(strsplit(result, \":\")[[1]])";
			String errorMsg2 = "msg <- trimStrings(paste(strsplit(msg, \"\\n\")[[length(msg)]], collapse = \" \"))";
			String errorMsg3 = "msg <- gsub(\"\\\"\", \"\", msg)";
			String errorMsg4 = "cat(\"Error in designAugmentedRCBD:\\n\",msg, sep = \"\")";

			System.out.println(errorMsg1);
			System.out.println(errorMsg2);
			System.out.println(errorMsg3);
			System.out.println(errorMsg4);
			
			rEngine.eval(errorMsg1);
			rEngine.eval(errorMsg2);
			rEngine.eval(errorMsg3);
			rEngine.eval(errorMsg4);
		} 
		else {
			String checkOutput = "if (nrow(result$fieldbook) != 0) {\n";
			checkOutput = checkOutput + "    cat(\"\\nLayout for Augmented Randomized Complete Block Design:\",\"\\n\\n\")\n";
			checkOutput = checkOutput + "    for (i in (1:length(result$layout))) { \n";
			checkOutput = checkOutput + "          printLayout(result$layout[[i]], result$plotNum[[i]], RowLabel = rownames(result$layout[[i]]), ColLabel = NULL, title = paste(\"Trial = \", i, sep = \"\"))\n";
			checkOutput = checkOutput + "          cat(\"\\n\")\n";
			checkOutput = checkOutput + "    }\n";
			checkOutput = checkOutput + "    cat(\"\\n\",\"**Note: Cells contain plot numbers on top, treatments/entries below\")\n";
			checkOutput = checkOutput + "}";
			
			System.out.println(checkOutput);
			rEngine.eval(checkOutput);
		}

		String sinkOut = "sink()";
		System.out.println(sinkOut);
		rEngine.eval(sinkOut);
		
		rscriptCommand.append(command+"\n");
		rEngineEnd();
	}
	
	@Override
	public void doDesignLattice(String path, String fieldBookName,  
			Integer numTrmt, Integer rep, Integer trial, Integer numFieldRow, String fieldOrder){

		//defining the R statements for randomization for Lattice
		rscriptCommand = new StringBuilder();
		String CSVOutput = path + fieldBookName + ".csv";
		String TxtOuptut = path + fieldBookName + ".txt";
		
		String sinkIn = "sink(\"" + TxtOuptut + "\")";
		String pkgIntro = "cat(\"Statistical Tool for Agricultural Research (STAR)\\nResult of Randomization\\n\",date(),\"\\n\\n\\n\", sep = \"\")";
		String funcRandomize = "result <- try(";
		String command = "designLattice(list(Treatment = paste(\"T\", paste(1:"+ numTrmt +"), sep = \"\"))";
		command = command + ", r = "+ rep +", trial = "+ trial + ", numFieldRow = "+ numFieldRow;
		if (fieldOrder == "Plot Order") {
			command = command + ", serpentine = FALSE, file = \""+ CSVOutput +"\")";
		} else {
			command = command + ", serpentine = TRUE, file = \""+ CSVOutput +"\")";
		}
		funcRandomize = funcRandomize + command + ", silent = TRUE)";
		
		System.out.println(sinkIn);
		System.out.println(pkgIntro);
		System.out.println(funcRandomize);


		rEngine.eval(sinkIn);
		rEngine.eval(pkgIntro);
		rEngine.eval(funcRandomize);
		//save sorted to csv file
//		String sortFile = "write.csv(result$fieldbook[order(result$fieldbook$Trial, result$fieldbook$PlotNum),], file = \""+ CSVOutput +"\", row.names = FALSE)";
//		System.out.println(sortFile);
//		rEngine.eval(sortFile);
		
		
		String runSuccessCommand = rEngine.eval("class(result)").asString();
		if (runSuccessCommand.equals("try-error")) {
			String errorMsg1 = "msg <- trimStrings(strsplit(result, \":\")[[1]])";
			String errorMsg2 = "msg <- trimStrings(paste(strsplit(msg, \"\\n\")[[length(msg)]], collapse = \" \"))";
			String errorMsg3 = "msg <- gsub(\"\\\"\", \"\", msg)";
			String errorMsg4 = "cat(\"Error in designLattice:\\n\",msg, sep = \"\")";

			System.out.println(errorMsg1);
			System.out.println(errorMsg2);
			System.out.println(errorMsg3);
			System.out.println(errorMsg4);
			
			rEngine.eval(errorMsg1);
			rEngine.eval(errorMsg2);
			rEngine.eval(errorMsg3);
			rEngine.eval(errorMsg4);
		} 
		else {
			String checkOutput = "if (nrow(result$fieldbook) != 0) {\n";
			checkOutput = checkOutput + "    cat(\"\\nLayout for Lattice Design:\",\"\\n\\n\")\n";
			checkOutput = checkOutput + "    for (i in (1:length(result$layout))) { \n";
			checkOutput = checkOutput + "          printLayout(result$layout[[i]], result$plotNum, RowLabel = rownames(result$layout[[i]]), ColLabel = colnames(result$layout[[i]]), title = paste(\"Trial = \", i, sep = \"\"))\n";
			checkOutput = checkOutput + "          cat(\"\\n\")\n";
			checkOutput = checkOutput + "    }\n";
			checkOutput = checkOutput + "    cat(\"\\n\",\"**Note: Cells contain plot numbers on top, treatments/entries below\")\n";
			checkOutput = checkOutput + "}";
	
			System.out.println(checkOutput);
			rEngine.eval(checkOutput);
		}
		
		String sinkOut = "sink()";
		System.out.println(sinkOut);
		rEngine.eval(sinkOut);

		rscriptCommand.append(command+"\n");
		rEngineEnd();
	}
	
	@Override
	public void doDesignAlpha(String path, String fieldBookName, Integer numTrmt, Integer blkSize, 
			Integer rep, Integer trial, Integer rowPerBlk, Integer rowPerRep, Integer numFieldRow, String fieldOrder){

		//defining the R statements for randomization for Alpha Lattice
		rscriptCommand = new StringBuilder();
		String CSVOutput = path + fieldBookName + ".csv";
		String TxtOuptut = path + fieldBookName + ".txt";
		
		String sinkIn = "sink(\"" + TxtOuptut + "\")";
		String pkgIntro = "cat(\"Statistical Tool for Agricultural Research (STAR)\\nResult of Randomization\\n\",date(),\"\\n\\n\\n\", sep = \"\")";
		String funcRandomize = "result <- try(";
		String command = "designAlphaLattice(generate = list(Treatment = paste(\"T\", paste(1:"+ numTrmt +"), sep = \"\"))";
		command = command + ", blksize = "+ blkSize +", r = "+ rep +", trial = "+ trial;
		command = command + ", rowPerBlk = " + rowPerBlk +", rowPerRep = "+ rowPerRep +", numFieldRow = "+ numFieldRow;
		if (fieldOrder == "Plot Order") {
			command = command + ", serpentine = FALSE, file = \""+ CSVOutput +"\")";
		} else {
			command = command + ", serpentine = TRUE, file = \""+ CSVOutput +"\")";
		}
		funcRandomize = funcRandomize + command + ", silent = TRUE)";
		
		System.out.println(sinkIn);
		System.out.println(pkgIntro);
		System.out.println(funcRandomize);


		//R statements passed on to the R engine
		rEngine.eval(sinkIn);
		rEngine.eval(pkgIntro);
		rEngine.eval(funcRandomize);

		//save sorted to csv file
		//String sortFile = "write.csv(result$fieldbook, file = \""+ CSVOutput +"\", row.names = FALSE)";
		//System.out.println(sortFile);
		//rEngine.eval(sortFile);
		
		String runSuccessCommand = rEngine.eval("class(result)").asString();
		if (runSuccessCommand.equals("try-error")) {
			String errorMsg1 = "msg <- trimStrings(strsplit(result, \":\")[[1]])";
			String errorMsg2 = "msg <- trimStrings(paste(strsplit(msg, \"\\n\")[[length(msg)]], collapse = \" \"))";
			String errorMsg3 = "msg <- gsub(\"\\\"\", \"\", msg)";
			String errorMsg4 = "cat(\"Error in designAlphaLattice:\\n\",msg, sep = \"\")";

			System.out.println(errorMsg1);
			System.out.println(errorMsg2);
			System.out.println(errorMsg3);
			System.out.println(errorMsg4);
			
			rEngine.eval(errorMsg1);
			rEngine.eval(errorMsg2);
			rEngine.eval(errorMsg3);
			rEngine.eval(errorMsg4);
		} 
		else {
			String checkOutput = "if (nrow(result$fieldbook) != 0) {\n";
			checkOutput = checkOutput + "    cat(\"\\nLayout for Alpha Lattice Design:\",\"\\n\\n\")\n";
			checkOutput = checkOutput + "    for (i in (1:length(result$layout))) { \n";
			checkOutput = checkOutput + "          printLayout(result$layout[[i]], result$plotNum, RowLabel = rownames(result$layout[[i]]), ColLabel = colnames(result$layout[[i]]), title = paste(\"Trial = \", i, sep = \"\"))\n";
			checkOutput = checkOutput + "          cat(\"\\n\")\n";
			checkOutput = checkOutput + "    }\n";
			checkOutput = checkOutput + "    cat(\"\\n\",\"**Note: Cells contain plot numbers on top, treatments/entries below\")\n";
			checkOutput = checkOutput + "}";
	
			System.out.println(checkOutput);
			rEngine.eval(checkOutput);
		}

		String sinkOut = "sink()";
		System.out.println(sinkOut);
		rEngine.eval(sinkOut);
			
		rscriptCommand.append(command+"\n");
		rEngineEnd();
		//return msg;
	}
		
	@Override
	public void doDesignRowColumn(String path, String fieldBookName, Integer numTrmt, Integer rep, Integer trial, 
			Integer rowPerRep, Integer numFieldRow, String fieldOrder){

		//defining the R statements for randomization for Alpha Lattice
		rscriptCommand = new StringBuilder();
		String CSVOutput = path + fieldBookName + ".csv";
		String TxtOuptut = path + fieldBookName + ".txt";
		
		String sinkIn = "sink(\"" + TxtOuptut + "\")";
		String pkgIntro = "cat(\"Statistical Tool for Agricultural Research (STAR)\\nResult of Randomization\\n\",date(),\"\\n\\n\\n\", sep = \"\")";
		String funcRandomize = "result <- try(";
		String command = "designRowColumn(list(Treatment = paste(\"T\", paste(1:"+ numTrmt +"), sep = \"\"))";
		command = command + ", r = "+ rep +", trial = "+ trial;
		command = command + ", rowPerRep = "+ rowPerRep +", numFieldRow = "+ numFieldRow;
		if (fieldOrder == "Plot Order") {
			command = command + ", serpentine = FALSE, file = \""+ CSVOutput +"\")";
		} else {
			command = command + ", serpentine = TRUE, file = \""+ CSVOutput +"\")";
		}
		funcRandomize = funcRandomize + command + ", silent = TRUE)";
		
		System.out.println(sinkIn);
		System.out.println(pkgIntro);
		System.out.println(funcRandomize);


		//R statements passed on to the R engine
		rEngine.eval(sinkIn);
		rEngine.eval(pkgIntro);
		rEngine.eval(funcRandomize);

		String runSuccessCommand = rEngine.eval("class(result)").asString();
		if (runSuccessCommand.equals("try-error")) {
			String errorMsg1 = "msg <- trimStrings(strsplit(result, \":\")[[1]])";
			String errorMsg2 = "msg <- trimStrings(paste(strsplit(msg, \"\\n\")[[length(msg)]], collapse = \" \"))";
			String errorMsg3 = "msg <- gsub(\"\\\"\", \"\", msg)";
			String errorMsg4 = "cat(\"Error in designRowColumn:\\n\",msg, sep = \"\")";

			System.out.println(errorMsg1);
			System.out.println(errorMsg2);
			System.out.println(errorMsg3);
			System.out.println(errorMsg4);
			
			rEngine.eval(errorMsg1);
			rEngine.eval(errorMsg2);
			rEngine.eval(errorMsg3);
			rEngine.eval(errorMsg4);
		} 
		else {
			String checkOutput = "if (nrow(result$fieldbook) != 0) {\n";
			checkOutput = checkOutput + "    cat(\"\\nLayout for Row-Column Design:\",\"\\n\\n\")\n";
			checkOutput = checkOutput + "    for (i in (1:length(result$layout))) { \n";
			checkOutput = checkOutput + "          printLayout(result$layout[[i]], result$plotNum, RowLabel = rownames(result$layout[[i]]), ColLabel = colnames(result$layout[[i]]), title = paste(\"Trial = \", i, sep = \"\"))\n";
			checkOutput = checkOutput + "          cat(\"\\n\")\n";
			checkOutput = checkOutput + "    }\n";
			checkOutput = checkOutput + "    cat(\"\\n\",\"**Note: Cells contain plot numbers on top, treatments/entries below\")\n";
			checkOutput = checkOutput + "}";
	
			System.out.println(checkOutput);
			rEngine.eval(checkOutput);
		}

		String sinkOut = "sink()";
		System.out.println(sinkOut);
		rEngine.eval(sinkOut);
			
		rscriptCommand.append(command+"\n");
		rEngineEnd();
		//return msg;
	}
	
	@Override
	public void doDesignLatinizedAlpha(String path, String fieldBookName, Integer numTrmt, Integer blkSize, 
			Integer rep, Integer trial, Integer numFieldRow, String fieldOrder){
		
		//defining the R statements for randomization for Alpha Lattice
		rscriptCommand = new StringBuilder();
		String CSVOutput = path + fieldBookName + ".csv";
		String TxtOuptut = path + fieldBookName + ".txt";
		
		String sinkIn = "sink(\"" + TxtOuptut + "\")";
		String pkgIntro = "cat(\"Statistical Tool for Agricultural Research (STAR)\\nResult of Randomization\\n\",date(),\"\\n\\n\\n\", sep = \"\")";
		String funcRandomize = "result <- try(";
		String command = "designLatinizedAlpha(generate = list(Treatment = paste(\"T\", paste(1:"+ numTrmt +"), sep = \"\"))";
		command = command + ", blksize = "+ blkSize +", r = "+ rep +", trial = "+ trial +", numFieldRow = "+ numFieldRow;
		if (fieldOrder == "Plot Order") {
			command = command + ", serpentine = FALSE, file = \""+ CSVOutput +"\")";
		} else {
			command = command + ", serpentine = TRUE, file = \""+ CSVOutput +"\")";
		}
		funcRandomize = funcRandomize + command + ", silent = TRUE)";
		
		System.out.println(sinkIn);
		System.out.println(pkgIntro);
		System.out.println(funcRandomize);


		//R statements passed on to the R engine
		rEngine.eval(sinkIn);
		rEngine.eval(pkgIntro);
		rEngine.eval(funcRandomize);

			
		String runSuccessCommand = rEngine.eval("class(result)").asString();
		if (runSuccessCommand.equals("try-error")) {
			String errorMsg1 = "msg <- trimStrings(strsplit(result, \":\")[[1]])";
			String errorMsg2 = "msg <- trimStrings(paste(strsplit(msg, \"\\n\")[[length(msg)]], collapse = \" \"))";
			String errorMsg3 = "msg <- gsub(\"\\\"\", \"\", msg)";
			String errorMsg4 = "cat(\"Error in designAlphaLattice:\\n\",msg, sep = \"\")";

			System.out.println(errorMsg1);
			System.out.println(errorMsg2);
			System.out.println(errorMsg3);
			System.out.println(errorMsg4);
			
			rEngine.eval(errorMsg1);
			rEngine.eval(errorMsg2);
			rEngine.eval(errorMsg3);
			rEngine.eval(errorMsg4);
		} 
		else {
			String checkOutput = "if (nrow(result$fieldbook) != 0) {\n";
			checkOutput = checkOutput + "    cat(\"\\nLayout for Latinized Alpha Lattice Design:\",\"\\n\\n\")\n";
			checkOutput = checkOutput + "    for (i in (1:length(result$layout))) { \n";
			checkOutput = checkOutput + "          printLayout(result$layout[[i]], result$plotNum, RowLabel = rownames(result$layout[[i]]), ColLabel = colnames(result$layout[[i]]), title = paste(\"Trial = \", i, sep = \"\"))\n";
			checkOutput = checkOutput + "          cat(\"\\n\")\n";
			checkOutput = checkOutput + "    }\n";
			checkOutput = checkOutput + "    cat(\"\\n\",\"**Note: Cells contain plot numbers on top, treatments/entries below\")\n";
			checkOutput = checkOutput + "}";
	
			System.out.println(checkOutput);
			rEngine.eval(checkOutput);
		}

		String sinkOut = "sink()";
		System.out.println(sinkOut);
		rEngine.eval(sinkOut);
			
		rscriptCommand.append(command+"\n");
		rEngineEnd();
		//return msg;

	}

	@Override
	public void doDesignLatinizedRowColumn(String path, String fieldBookName, Integer numTrmt, Integer rep, Integer trial, 
			Integer rowPerRep, Integer numFieldRow, String fieldOrder){

		//defining the R statements for randomization for Alpha Lattice
		rscriptCommand = new StringBuilder();
		String CSVOutput = path + fieldBookName + ".csv";
		String TxtOuptut = path + fieldBookName + ".txt";
		
		String sinkIn = "sink(\"" + TxtOuptut + "\")";
		String pkgIntro = "cat(\"Statistical Tool for Agricultural Research (STAR)\\nResult of Randomization\\n\",date(),\"\\n\\n\\n\", sep = \"\")";
		String funcRandomize = "result <- try(";
		String command = "designLatinizedRowCol(list(Treatment = paste(\"T\", paste(1:"+ numTrmt +"), sep = \"\"))";
		command = command + ", r = "+ rep +", trial = "+ trial;
		command = command + ", rowPerRep = "+ rowPerRep +", numFieldRow = "+ numFieldRow;
		if (fieldOrder == "Plot Order") {
			command = command + ", serpentine = FALSE, file = \""+ CSVOutput +"\")";
		} else {
			command = command + ", serpentine = TRUE, file = \""+ CSVOutput +"\")";
		}
		funcRandomize = funcRandomize + command + ", silent = TRUE)";
		
		System.out.println(sinkIn);
		System.out.println(pkgIntro);
		System.out.println(funcRandomize);


		//R statements passed on to the R engine
		rEngine.eval(sinkIn);
		rEngine.eval(pkgIntro);
		rEngine.eval(funcRandomize);

		String runSuccessCommand = rEngine.eval("class(result)").asString();
		if (runSuccessCommand.equals("try-error")) {
			String errorMsg1 = "msg <- trimStrings(strsplit(result, \":\")[[1]])";
			String errorMsg2 = "msg <- trimStrings(paste(strsplit(msg, \"\\n\")[[length(msg)]], collapse = \" \"))";
			String errorMsg3 = "msg <- gsub(\"\\\"\", \"\", msg)";
			String errorMsg4 = "cat(\"Error in designLatinizedRowCol:\\n\",msg, sep = \"\")";

			System.out.println(errorMsg1);
			System.out.println(errorMsg2);
			System.out.println(errorMsg3);
			System.out.println(errorMsg4);
			
			rEngine.eval(errorMsg1);
			rEngine.eval(errorMsg2);
			rEngine.eval(errorMsg3);
			rEngine.eval(errorMsg4);
		} 
		else {
			String checkOutput = "if (nrow(result$fieldbook) != 0) {\n";
			checkOutput = checkOutput + "    cat(\"\\nLayout for Latinized Row-Column Design:\",\"\\n\\n\")\n";
			checkOutput = checkOutput + "    for (i in (1:length(result$layout))) { \n";
			checkOutput = checkOutput + "          printLayout(result$layout[[i]], result$plotNum, RowLabel = rownames(result$layout[[i]]), ColLabel = colnames(result$layout[[i]]), title = paste(\"Trial = \", i, sep = \"\"))\n";
			checkOutput = checkOutput + "          cat(\"\\n\")\n";
			checkOutput = checkOutput + "    }\n";
			checkOutput = checkOutput + "    cat(\"\\n\",\"**Note: Cells contain plot numbers on top, treatments/entries below\")\n";
			checkOutput = checkOutput + "}";
	
			System.out.println(checkOutput);
			rEngine.eval(checkOutput);
		}

		String sinkOut = "sink()";
		System.out.println(sinkOut);
		rEngine.eval(sinkOut);
			
		rscriptCommand.append(command+"\n");
		rEngineEnd();
		//return msg;
	}
	
	public StringBuilder getRscriptCommand() {
		return rscriptCommand;
	}
	
	
}

