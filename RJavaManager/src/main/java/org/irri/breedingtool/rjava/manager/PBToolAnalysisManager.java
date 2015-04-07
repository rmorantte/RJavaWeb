package org.irri.breedingtool.rjava.manager;

import org.irri.breedingtool.rjava.manager.api.IRJavaPBToolsAnalysisManager;
import org.irri.breedingtool.rjava.utilities.InputTransform;
import org.rosuda.JRI.Rengine;

public class PBToolAnalysisManager implements IRJavaPBToolsAnalysisManager {

	private Rengine rEngine;
	private InputTransform inputTransform;
	
	public PBToolAnalysisManager(Rengine rEngine) {
		this.rEngine = rEngine;
		this.inputTransform= new InputTransform();
	}
	
	private void rEngineEnd(){
		String rm = "rm(list=ls(all=TRUE))";
		rEngine.eval(rm);
		rEngine.end();
	}

		
	@Override
	public void doNC1Test(String dataFileName, String outFileName, String design, String[] respvar, String female, String male, 
			String rep, String block, String row, String column, String inbred, String individual, String environment){

		String respvarVector= inputTransform.createRVector(respvar);
		
		//defining the R statements
		String readData = "dataRead <- read.csv(\"" + dataFileName + "\", header = TRUE, na.strings = c(\"NA\",\".\",\" \",\"\"), blank.lines.skip=TRUE, sep = \",\")";
		String sinkIn = "sink(\"" + outFileName + "\")";
		
		String usedData = "cat(\"\nDATA FILE: " + dataFileName + "\n\")";
		
		//run nc1test		
		String funcNc1Test = null;
		if (design == "CRD") {
			if (environment == "NULL") {
				funcNc1Test = "result<-try(nc1Test(\"CRD\", \"dataRead\", respvar = " + respvarVector + ", female = \"" + female + "\", male = \"" 
						+ male + "\", rep = NULL, block = NULL, row = NULL, column = NULL, inbred = " + inbred + ", individual = NULL, environment = " + environment + "), silent = TRUE)";
			} else {
				funcNc1Test = "result<-try(nc1Test(\"CRD\", \"dataRead\", respvar = " + respvarVector + ", female = \"" + female + "\", male = \"" 
						+ male + "\", rep = NULL, block = NULL, row = NULL, column = NULL, inbred = " + inbred + ", individual = NULL, environment = \"" + environment + "\"), silent = TRUE)";
			}
		} 
		if (design == "RCB") {
			if (environment == "NULL") {
				funcNc1Test = "result<-try(nc1Test(\"RCB\", \"dataRead\", respvar = " + respvarVector + ", female = \"" + female + "\", male = \"" + male + "\", rep = NULL, block = \"" 
						+ block + "\", row = NULL, column = NULL, inbred = " + inbred + ", individual = NULL, environment = " + environment + "), silent = TRUE)";
			} else {
				funcNc1Test = "result<-try(nc1Test(\"RCB\", \"dataRead\", respvar =" + respvarVector + ", female =\"" + female + "\", male = \"" + male + "\", rep = NULL, block = \"" 
						+ block + "\", row = NULL, column = NULL, inbred = " + inbred + ", individual = NULL, environment = \"" + environment + "\"), silent = TRUE)";
			}
		}
		if (design == "Alpha") {
			if (environment == "NULL") {
				funcNc1Test = "result<-try(nc1Test(\"Alpha\", \"dataRead\", respvar = " + respvarVector + ", female = \"" + female + "\", male = \"" + male + "\", rep = \"" 
						+ rep + "\", block = \"" + block + "\", row = NULL, column = NULL, inbred = " + inbred + ", individual = NULL, environment = " + environment + "), silent = TRUE)";
			} else {
				funcNc1Test = "result<-try(nc1Test(\"Alpha\", \"dataRead\", respvar = " + respvarVector + ", female = \"" + female + "\", male = \"" + male + "\", rep = \"" 
						+ rep + "\", block = \"" + block + "\", row = NULL, column = NULL, inbred = " + inbred + ", individual = NULL, environment = \"" + environment + "\"), silent = TRUE)";
			}
		}
		if (design == "RowColumn") {
			if (environment == "NULL") {
				funcNc1Test = "result<-try(nc1Test(\"RowColumn\", \"dataRead\", respvar = " + respvarVector + ", female = \"" + female + "\", male = \"" + male + "\", rep = \"" 
						+ rep + "\", block = NULL, row = \"" + row + "\", column = \"" + column + "\", inbred = " + inbred + ", individual = NULL, environment = " + environment + "), silent = TRUE)";
			} else {
				funcNc1Test = "result<-try(nc1Test(\"RowColumn\", \"dataRead\", respvar = " + respvarVector + ", female = \"" + female + "\", male = \"" + male + "\", rep = \"" 
						+ rep + "\", block = NULL, row = \"" + row + "\", column = \"" + column + "\", inbred = " + inbred + ", individual = NULL, environment = \"" + environment + "\"), silent = TRUE)";
			}
		}
		
		String checkError = "if (class(result) == \"try-error\") {\n";
		checkError = checkError + "    msg <- trimStrings(strsplit(result, \":\")[[1]])\n";
		checkError = checkError + "    msg <- trimStrings(paste(strsplit(msg, \"\\n\")[[length(msg)]], collapse = \" \"))\n";
		checkError = checkError + "    msg <- gsub(\"\\\"\", \"\", msg)\n";
		checkError = checkError + "    cat(\"\n*** \nERROR in nc1Test function:\\n  \",msg, \"\n***\", sep = \"\")\n";
		checkError = checkError + "}";
		String sinkOut = "sink()";
		
		System.out.println(readData);
		System.out.println(sinkIn);
		System.out.println(funcNc1Test);
		System.out.println(checkError);
		System.out.println(sinkOut);
		
		//R statements passed on to the R engine
		rEngine.eval(readData);
		rEngine.eval(sinkIn);
		rEngine.eval(usedData);
		rEngine.eval(funcNc1Test);
		rEngine.eval(checkError);
		rEngine.eval(sinkOut);
		rEngineEnd();
	}
	
	@Override
	public void doNC1METest(String dataFileName, String outFileName, String design, String[] respvar, String female, String male, 
			String rep, String block, String row, String column, String inbred, String individual, String environment){

		String respvarVector= inputTransform.createRVector(respvar);
		
		//defining the R statements
		String readData = "dataRead <- read.csv(\"" + dataFileName + "\", header = TRUE, na.strings = c(\"NA\",\".\",\" \",\"\"), blank.lines.skip=TRUE, sep = \",\")";
		String sinkIn = "sink(\"" + outFileName + "\")";
		
		String usedData = "cat(\"\nDATA FILE: " + dataFileName + "\n\")";
		
		String funcNc1TestME = null;
		if (design == "CRD") {
			funcNc1TestME = "result<-try(nc1TestME(\"CRD\", \"dataRead\", respvar = " + respvarVector + ", female = \"" + female + "\", male = \"" 
						+ male + "\", rep = NULL, block = NULL, row = NULL, column = NULL, inbred = " + inbred + ", individual = NULL, environment = \"" + environment + "\"), silent = TRUE)";
		} 
		if (design == "RCB") {
			funcNc1TestME = "result<-try(nc1TestME(\"RCB\", \"dataRead\", respvar =" + respvarVector + ", female =\"" + female + "\", male = \"" + male + "\", rep = NULL, block = \"" 
						+ block + "\", row = NULL, column = NULL, inbred = " + inbred + ", individual = NULL, environment = \"" + environment + "\"), silent = TRUE)";
		}
		if (design == "Alpha") {
			funcNc1TestME = "result<-try(nc1TestME(\"Alpha\", \"dataRead\", respvar = " + respvarVector + ", female = \"" + female + "\", male = \"" + male + "\", rep = \"" 
						+ rep + "\", block = \"" + block + "\", row = NULL, column = NULL, inbred = " + inbred + ", individual = NULL, environment = \"" + environment + "\"), silent = TRUE)";
		}
		if (design == "RowColumn") {
			funcNc1TestME = "result<-try(nc1TestME(\"RowColumn\", \"dataRead\", respvar = " + respvarVector + ", female = \"" + female + "\", male = \"" + male + "\", rep = \"" 
						+ rep + "\", block = NULL, row = \"" + row + "\", column = \"" + column + "\", inbred = " + inbred + ", individual = NULL, environment = \"" + environment + "\"), silent = TRUE)";
		}
		
		String checkError = "if (class(result) == \"try-error\") {\n";
		checkError = checkError + "    msg <- trimStrings(strsplit(result, \":\")[[1]])\n";
		checkError = checkError + "    msg <- trimStrings(paste(strsplit(msg, \"\\n\")[[length(msg)]], collapse = \" \"))\n";
		checkError = checkError + "    msg <- gsub(\"\\\"\", \"\", msg)\n";
		checkError = checkError + "    cat(\"\n*** \nERROR in nc1TestME function:\\n  \",msg, \"\n***\", sep = \"\")\n";
		checkError = checkError + "}";
	
		String sinkOut = "sink()";
		
		System.out.println(readData);
		System.out.println(sinkIn);
		System.out.println(funcNc1TestME);
		System.out.println(checkError);
		System.out.println(sinkOut);
		
		//R statements passed on to the R engine
		rEngine.eval(readData);
		rEngine.eval(sinkIn);
		rEngine.eval(usedData);
		rEngine.eval(funcNc1TestME);
		rEngine.eval(checkError);
		rEngine.eval(sinkOut);
		rEngineEnd();
	}
	
		
	@Override
	public void doNC2Test(String dataFileName, String outFileName, String design, String[] respvar, String female, String male, 
			String rep, String block, String row, String column, String inbred, String individual, String environment){

		String respvarVector= inputTransform.createRVector(respvar);
		
		//defining the R statements
		String readData = "dataRead <- read.csv(\"" + dataFileName + "\", header = TRUE, na.strings = c(\"NA\",\".\",\" \",\"\"), blank.lines.skip=TRUE, sep = \",\")";
		String sinkIn = "sink(\"" + outFileName + "\")";
		
		String usedData = "cat(\"\nDATA FILE: " + dataFileName + "\n\")";
		
		String funcNc2Test = null;
		if (design == "CRD") {
			if (environment == "NULL") {
				funcNc2Test = "result<-try(nc2Test(\"CRD\", \"dataRead\", respvar = " + respvarVector + ", female = \"" + female + "\", male = \"" + male + 
						"\", rep = NULL, block = NULL, row = NULL, column = NULL, inbred = " + inbred + ", individual = NULL, environment = " + environment + "), silent = TRUE)";
			} else {
				funcNc2Test = "result<-try(nc2Test(\"CRD\", \"dataRead\", respvar = " + respvarVector + ", female = \"" + female + "\", male = \"" + male + 
						"\", rep = NULL, block = NULL, row = NULL, column = NULL, inbred = " + inbred + ", individual = NULL, environment = \"" + environment + "\"), silent = TRUE)";
			}
		} 
		if (design == "RCB") {
			if (environment == "NULL") {
				funcNc2Test = "result<-try(nc2Test(\"RCB\", \"dataRead\", respvar = " + respvarVector + ", female = \"" + female + "\", male = \"" + male + "\", rep = NULL, block = \"" 
						+ block + "\", row = NULL, column = NULL, inbred = " + inbred + ", individual = NULL, environment = " + environment + "), silent = TRUE)";
			} else {
				funcNc2Test = "result<-try(nc2Test(\"RCB\", \"dataRead\", respvar =" + respvarVector + ", female =\"" + female + "\", male = \"" + male + "\", rep = NULL, block = \"" 
						+ block + "\", row = NULL, column = NULL, inbred = " + inbred + ", individual = NULL, environment = \"" + environment + "\"), silent = TRUE)";
			}
		}
		if (design == "Alpha") {
			if (environment == "NULL") {
				funcNc2Test = "result<-try(nc2Test(\"Alpha\", \"dataRead\", respvar = " + respvarVector + ", female = \"" + female + "\", male = \"" + male + "\", rep = \"" 
						+ rep + "\", block = \"" + block + "\", row = NULL, column = NULL, inbred = " + inbred + ", individual = NULL, environment = " + environment + "), silent = TRUE)";
			} else {
				funcNc2Test = "result<-try(nc2Test(\"Alpha\", \"dataRead\", respvar = " + respvarVector + ", female = \"" + female + "\", male = \"" + male + "\", rep = \"" 
						+ rep + "\", block = \"" + block + "\", row = NULL, column = NULL, inbred = " + inbred + ", individual = NULL, environment = \"" + environment + "\"), silent = TRUE)";
			}
		}
		if (design == "RowColumn") {
			if (environment == "NULL") {
				funcNc2Test = "result<-try(nc2Test(\"RowColumn\", \"dataRead\", respvar = " + respvarVector + ", female = \"" + female + "\", male = \"" + male + "\", rep = \"" 
						+ rep + "\", block = NULL, row = \"" + row + "\", column = \"" + column + "\", inbred = " + inbred + ", individual = NULL, environment = " + environment + "), silent = TRUE)";
			} else {
				funcNc2Test = "result<-try(nc2Test(\"RowColumn\", \"dataRead\", respvar = " + respvarVector + ", female = \"" + female + "\", male = \"" + male + "\", rep = \"" 
						+ rep + "\", block = NULL, row = \"" + row + "\", column = \"" + column + "\", inbred = " + inbred + ", individual = NULL, environment = \"" + environment + "\"), silent = TRUE)";
			}
		}
		
		String checkError = "if (class(result) == \"try-error\") {\n";
		checkError = checkError + "    msg <- trimStrings(strsplit(result, \":\")[[1]])\n";
		checkError = checkError + "    msg <- trimStrings(paste(strsplit(msg, \"\\n\")[[length(msg)]], collapse = \" \"))\n";
		checkError = checkError + "    msg <- gsub(\"\\\"\", \"\", msg)\n";
		checkError = checkError + "    cat(\"\n*** \nERROR in nc2Test function:\\n  \",msg, \"\n***\", sep = \"\")\n";
		checkError = checkError + "}";
		String sinkOut = "sink()";
		
		System.out.println(readData);
		System.out.println(sinkIn);
		System.out.println(funcNc2Test);
		System.out.println(checkError);
		System.out.println(sinkOut);
		
		//R statements passed on to the R engine
		rEngine.eval(readData);
		rEngine.eval(sinkIn);
		rEngine.eval(usedData);
		rEngine.eval(funcNc2Test);
		rEngine.eval(checkError);
		rEngine.eval(sinkOut);
		rEngineEnd();
	}
	
	@Override
	public void doNC2METest(String dataFileName, String outFileName, String design, String[] respvar, String female, String male, 
			String rep, String block, String row, String column, String inbred, String individual, String environment){

		String respvarVector= inputTransform.createRVector(respvar);
		
		//defining the R statements
		String readData = "dataRead <- read.csv(\"" + dataFileName + "\", header = TRUE, na.strings = c(\"NA\",\".\",\" \",\"\"), blank.lines.skip=TRUE, sep = \",\")";
		String sinkIn = "sink(\"" + outFileName + "\")";
		
		String usedData = "cat(\"\nDATA FILE: " + dataFileName + "\n\")";
		
		String funcNc2TestME = null;
		if (design == "CRD") {
			funcNc2TestME = "result<-try(nc2TestME(\"CRD\", \"dataRead\", respvar = " + respvarVector + ", female = \"" + female + "\", male = \"" + male + 
						"\", rep = NULL, block = NULL, row = NULL, column = NULL, inbred = " + inbred + ", individual = NULL, environment = \"" + environment + "\"), silent = TRUE)";
		} 
		if (design == "RCB") {
			funcNc2TestME = "result<-try(nc2TestME(\"RCB\", \"dataRead\", respvar =" + respvarVector + ", female =\"" + female + "\", male = \"" + male + "\", rep = NULL, block = \"" 
						+ block + "\", row = NULL, column = NULL, inbred = " + inbred + ", individual = NULL, environment = \"" + environment + "\"), silent = TRUE)";
		}
		if (design == "Alpha") {
			funcNc2TestME = "result<-try(nc2TestME(\"Alpha\", \"dataRead\", respvar = " + respvarVector + ", female = \"" + female + "\", male = \"" + male + "\", rep = \"" 
						+ rep + "\", block = \"" + block + "\", row = NULL, column = NULL, inbred = " + inbred + ", individual = NULL, environment = \"" + environment + "\"), silent = TRUE)";
		}
		if (design == "RowColumn") {
			funcNc2TestME = "result<-try(nc2TestME(\"RowColumn\", \"dataRead\", respvar = " + respvarVector + ", female = \"" + female + "\", male = \"" + male + "\", rep = \"" 
						+ rep + "\", block = NULL, row = \"" + row + "\", column = \"" + column + "\", inbred = " + inbred + ", individual = NULL, environment = \"" + environment + "\"), silent = TRUE)";
		}
		
		String checkError = "if (class(result) == \"try-error\") {\n";
		checkError = checkError + "    msg <- trimStrings(strsplit(result, \":\")[[1]])\n";
		checkError = checkError + "    msg <- trimStrings(paste(strsplit(msg, \"\\n\")[[length(msg)]], collapse = \" \"))\n";
		checkError = checkError + "    msg <- gsub(\"\\\"\", \"\", msg)\n";
		checkError = checkError + "    cat(\"\n*** \nERROR in nc2TestME function:\\n  \",msg, \"\n***\", sep = \"\")\n";
		checkError = checkError + "}";	
		String sinkOut = "sink()";
		
		System.out.println(readData);
		System.out.println(sinkIn);
		System.out.println(funcNc2TestME);
		System.out.println(checkError);
		System.out.println(sinkOut);
		
		//R statements passed on to the R engine
		rEngine.eval(readData);
		rEngine.eval(sinkIn);
		rEngine.eval(usedData);
		rEngine.eval(funcNc2TestME);
		rEngine.eval(checkError);
		rEngine.eval(sinkOut);
		rEngineEnd();
	}
	
		
	@Override
	public void doNC3Test(String dataFileName, String outFileName, String design, String[] respvar, String tester, String f2lines, 
			String rep, String block, String row, String column, String inbred, String individual, String environment){

		String respvarVector= inputTransform.createRVector(respvar);
		
		//defining the R statements
		String readData = "dataRead <- read.csv(\"" + dataFileName + "\", header = TRUE, na.strings = c(\"NA\",\".\",\" \",\"\"), blank.lines.skip=TRUE, sep = \",\")";
		String sinkIn = "sink(\"" + outFileName + "\")";
		
		String usedData = "cat(\"\nDATA FILE: " + dataFileName + "\n\")";
		
		String funcNc3Test = null;
		if (design == "CRD") {
			if (environment == "NULL") {
				funcNc3Test = "result<-try(nc3Test(\"CRD\", \"dataRead\", respvar = " + respvarVector + ", tester = \"" + tester + "\", f2lines = \"" + f2lines + 
						"\", rep = NULL, block = NULL, row = NULL, column = NULL, individual = NULL, environment = " + environment + "), silent = TRUE)";
			} else {
				funcNc3Test = "result<-try(nc3Test(\"CRD\", \"dataRead\", respvar = " + respvarVector + ", tester = \"" + tester + "\", f2lines = \"" + f2lines + 
						"\", rep = NULL, block = NULL, row = NULL, column = NULL, individual = NULL, environment = \"" + environment + "\"), silent = TRUE)";
			}
		} 
		if (design == "RCB") {
			if (environment == "NULL") {
				funcNc3Test = "result<-try(nc3Test(\"RCB\", \"dataRead\", respvar = " + respvarVector + ", tester = \"" + tester + "\", f2lines = \"" + f2lines + "\", rep = NULL, block = \"" 
						+ block + "\", row = NULL, column = NULL, individual = NULL, environment = " + environment + "), silent = TRUE)";
			} else {
				funcNc3Test = "result<-try(nc3Test(\"RCB\", \"dataRead\", respvar =" + respvarVector + ", tester =\"" + tester + "\", f2lines = \"" + f2lines + "\", rep = NULL, block = \"" 
						+ block + "\", row = NULL, column = NULL, individual = NULL, environment = \"" + environment + "\"), silent = TRUE)";
			}
		}
		if (design == "Alpha") {
			if (environment == "NULL") {
				funcNc3Test = "result<-try(nc3Test(\"Alpha\", \"dataRead\", respvar = " + respvarVector + ", tester = \"" + tester + "\", f2lines = \"" + f2lines + "\", rep = \"" 
						+ rep + "\", block = \"" + block + "\", row = NULL, column = NULL, individual = NULL, environment = " + environment + "), silent = TRUE)";
			} else {
				funcNc3Test = "result<-try(nc3Test(\"Alpha\", \"dataRead\", respvar = " + respvarVector + ", tester = \"" + tester + "\", f2lines = \"" + f2lines + "\", rep = \"" 
						+ rep + "\", block = \"" + block + "\", row = NULL, column = NULL, individual = NULL, environment = \"" + environment + "\"), silent = TRUE)";
			}
		}
		if (design == "RowColumn") {
			if (environment == "NULL") {
				funcNc3Test = "result<-try(nc3Test(\"RowColumn\", \"dataRead\", respvar = " + respvarVector + ", tester = \"" + tester + "\", f2lines = \"" + f2lines + "\", rep = \"" 
						+ rep + "\", block = NULL, row = \"" + row + "\", column = \"" + column + "\", individual = NULL, environment = " + environment + "), silent = TRUE)";
			} else {
				funcNc3Test = "result<-try(nc3Test(\"RowColumn\", \"dataRead\", respvar = " + respvarVector + ", tester = \"" + tester + "\", f2lines = \"" + f2lines + "\", rep = \"" 
						+ rep + "\", block = NULL, row = \"" + row + "\", column = \"" + column + "\", individual = NULL, environment = \"" + environment + "\"), silent = TRUE)";
			}
		}
		
		String checkError = "if (class(result) == \"try-error\") {\n";
		checkError = checkError + "    msg <- trimStrings(strsplit(result, \":\")[[1]])\n";
		checkError = checkError + "    msg <- trimStrings(paste(strsplit(msg, \"\\n\")[[length(msg)]], collapse = \" \"))\n";
		checkError = checkError + "    msg <- gsub(\"\\\"\", \"\", msg)\n";
		checkError = checkError + "    cat(\"\n*** \nERROR in nc3Test function:\\n  \",msg, \"\n***\", sep = \"\")\n";
		checkError = checkError + "}";
		String sinkOut = "sink()";
		
		System.out.println(readData);
		System.out.println(sinkIn);
		System.out.println(funcNc3Test);
		System.out.println(checkError);
		System.out.println(sinkOut);
		
		//R statements passed on to the R engine
		rEngine.eval(readData);
		rEngine.eval(sinkIn);
		rEngine.eval(usedData);
		rEngine.eval(funcNc3Test);
		rEngine.eval(checkError);
		rEngine.eval(sinkOut);
		rEngineEnd();
	}
	
	@Override
	public void doNC3METest(String dataFileName, String outFileName, String design, String[] respvar, String tester, String f2lines, 
			String rep, String block, String row, String column, String individual, String environment){

		String respvarVector= inputTransform.createRVector(respvar);
		
		//defining the R statements
		String readData = "dataRead <- read.csv(\"" + dataFileName + "\", header = TRUE, na.strings = c(\"NA\",\".\",\" \",\"\"), blank.lines.skip=TRUE, sep = \",\")";
		String sinkIn = "sink(\"" + outFileName + "\")";
		
		String usedData = "cat(\"\nDATA FILE: " + dataFileName + "\n\")";
		
		String funcNc3TestME = null;
		if (design == "CRD") {
			funcNc3TestME = "result<-try(nc3TestME(\"CRD\", \"dataRead\", respvar = " + respvarVector + ", tester = \"" + tester + "\", f2lines = \"" + f2lines + 
						"\", rep = NULL, block = NULL, row = NULL, column = NULL, individual = NULL, environment = \"" + environment + "\"), silent = TRUE)";
		} 
		if (design == "RCB") {
			funcNc3TestME = "result<-try(nc3TestME(\"RCB\", \"dataRead\", respvar =" + respvarVector + ", tester =\"" + tester + "\", f2lines = \"" + f2lines + "\", rep = NULL, block = \"" 
						+ block + "\", row = NULL, column = NULL, individual = NULL, environment = \"" + environment + "\"), silent = TRUE)";
		}
		if (design == "Alpha") {
			funcNc3TestME = "result<-try(nc3TestME(\"Alpha\", \"dataRead\", respvar = " + respvarVector + ", tester = \"" + tester + "\", f2lines = \"" + f2lines + "\", rep = \"" 
						+ rep + "\", block = \"" + block + "\", row = NULL, column = NULL, individual = NULL, environment = \"" + environment + "\"), silent = TRUE)";
		}
		if (design == "RowColumn") {
			funcNc3TestME = "result<-try(nc3TestME(\"RowColumn\", \"dataRead\", respvar = " + respvarVector + ", tester = \"" + tester + "\", f2lines = \"" + f2lines + "\", rep = \"" 
						+ rep + "\", block = NULL, row = \"" + row + "\", column = \"" + column + "\", individual = NULL, environment = \"" + environment + "\"), silent = TRUE)";
		}
		
		String checkError = "if (class(result) == \"try-error\") {\n";
		checkError = checkError + "    msg <- trimStrings(strsplit(result, \":\")[[1]])\n";
		checkError = checkError + "    msg <- trimStrings(paste(strsplit(msg, \"\\n\")[[length(msg)]], collapse = \" \"))\n";
		checkError = checkError + "    msg <- gsub(\"\\\"\", \"\", msg)\n";
		checkError = checkError + "    cat(\"\n*** \nERROR in nc3TestME function:\\n  \",msg, \"\n***\", sep = \"\")\n";
		checkError = checkError + "}";	
		String sinkOut = "sink()";
		
		System.out.println(readData);
		System.out.println(sinkIn);
		System.out.println(funcNc3TestME);
		System.out.println(checkError);
		System.out.println(sinkOut);
		
		//R statements passed on to the R engine
		rEngine.eval(readData);
		rEngine.eval(sinkIn);
		rEngine.eval(usedData);
		rEngine.eval(funcNc3TestME);
		rEngine.eval(checkError);
		rEngine.eval(sinkOut);
		rEngineEnd();
	}
	
		
	@Override
	public void doTTCTest(String dataFileName, String outFileName, String design, String[] respvar, String tester, String f2lines, 
			String rep, String block, String row, String column, String individual, String environment, String codeP1, String codeP2, String codeF1, String alpha){

		String respvarVector= inputTransform.createRVector(respvar);
		
		// OLD APPROACH
//		//defining the R statements
//		String readData = "dataRead <- read.csv(\"" + dataFileName + "\", header = TRUE, na.strings = c(\"NA\",\".\",\" \",\"\"), blank.lines.skip=TRUE, sep = \",\")";
//		String sinkIn = "sink(\"" + outFileName + "\")";
//		
//		String usedData = "cat(\"\nDATA FILE: " + dataFileName + "\n\")";
//		
//		String funcTtcTest = null;
//		if (design == "CRD") {
//			if (environment == "NULL") {
//				funcTtcTest = "result<-try(ttcTest(\"CRD\", \"dataRead\", respvar = " + respvarVector + ", tester = \"" + tester + "\", f2lines = \"" + f2lines + 
//						"\", rep = NULL, block = NULL, row = NULL, column = NULL, individual = NULL, environment = " + environment + ", codeP1 = \"" + codeP1 + "\", codeP2 = \"" 
//						+ codeP2 + "\", codeF1 = \"" + codeF1 + "\", alpha = " + alpha + "), silent = TRUE)";
//			} else {
//				funcTtcTest = "result<-try(ttcTest(\"CRD\", \"dataRead\", respvar = " + respvarVector + ", tester = \"" + tester + "\", f2lines = \"" + f2lines + 
//						"\", rep = NULL, block = NULL, row = NULL, column = NULL, individual = NULL, environment = \"" + environment + "\", codeP1 = \"" + codeP1 + "\", codeP2 = \"" 
//						+ codeP2 + "\", codeF1 = \"" + codeF1 + "\", alpha = " + alpha + "), silent = TRUE)";
//			}
//		} 
//		if (design == "RCB") {
//			if (environment == "NULL") {
//				funcTtcTest = "result<-try(ttcTest(\"RCB\", \"dataRead\", respvar = " + respvarVector + ", tester = \"" + tester + "\", f2lines = \"" + f2lines + "\", rep = NULL, block = \"" 
//						+ block + "\", row = NULL, column = NULL, individual = NULL, environment = " + environment + ", codeP1 = \"" + codeP1 + "\", codeP2 = \"" 
//						+ codeP2 + "\", codeF1 = \"" + codeF1 + "\", alpha = " + alpha + "), silent = TRUE)";
//			} else {
//				funcTtcTest = "result<-try(ttcTest(\"RCB\", \"dataRead\", respvar =" + respvarVector + ", tester =\"" + tester + "\", f2lines = \"" + f2lines + "\", rep = NULL, block = \"" 
//						+ block + "\", row = NULL, column = NULL, individual = NULL, environment = \"" + environment + "\", codeP1 = \"" + codeP1 + "\", codeP2 = \"" 
//						+ codeP2 + "\", codeF1 = \"" + codeF1 + "\", alpha = " + alpha + "), silent = TRUE)";
//			}
//		}
//		if (design == "Alpha") {
//			if (environment == "NULL") {
//				funcTtcTest = "result<-try(ttcTest(\"Alpha\", \"dataRead\", respvar = " + respvarVector + ", tester = \"" + tester + "\", f2lines = \"" + f2lines + "\", rep = \"" 
//						+ rep + "\", block = \"" + block + "\", row = NULL, column = NULL, individual = NULL, environment = " + environment + ", codeP1 = \"" + codeP1 + "\", codeP2 = \"" 
//						+ codeP2 + "\", codeF1 = \"" + codeF1 + "\", alpha = " + alpha + "), silent = TRUE)";
//			} else {
//				funcTtcTest = "result<-try(ttcTest(\"Alpha\", \"dataRead\", respvar = " + respvarVector + ", tester = \"" + tester + "\", f2lines = \"" + f2lines + "\", rep = \"" 
//						+ rep + "\", block = \"" + block + "\", row = NULL, column = NULL, individual = NULL, environment = \"" + environment + "\", codeP1 = \"" + codeP1 + "\", codeP2 = \"" 
//						+ codeP2 + "\", codeF1 = \"" + codeF1 + "\", alpha = " + alpha + "), silent = TRUE)";
//			}
//		}
//		if (design == "RowColumn") {
//			if (environment == "NULL") {
//				funcTtcTest = "result<-try(ttcTest(\"RowColumn\", \"dataRead\", respvar = " + respvarVector + ", tester = \"" + tester + "\", f2lines = \"" + f2lines + "\", rep = \"" 
//						+ rep + "\", block = NULL, row = \"" + row + "\", column = \"" + column + "\", individual = NULL, environment = " + environment + ", codeP1 = \"" + codeP1 + "\", codeP2 = \"" 
//						+ codeP2 + "\", codeF1 = \"" + codeF1 + "\", alpha = " + alpha + "), silent = TRUE)";
//			} else {
//				funcTtcTest = "result<-try(ttcTest(\"RowColumn\", \"dataRead\", respvar = " + respvarVector + ", tester = \"" + tester + "\", f2lines = \"" + f2lines + "\", rep = \"" 
//						+ rep + "\", block = NULL, row = \"" + row + "\", column = \"" + column + "\", individual = NULL, environment = \"" + environment + "\", codeP1 = \"" + codeP1 + "\", codeP2 = \"" 
//						+ codeP2 + "\", codeF1 = \"" + codeF1 + "\", alpha = " + alpha + "), silent = TRUE)";
//			}
//		}
//		
//		String checkError = "if (class(result) == \"try-error\") {\n";
//		checkError = checkError + "    msg <- trimStrings(strsplit(result, \":\")[[1]])\n";
//		checkError = checkError + "    msg <- trimStrings(paste(strsplit(msg, \"\\n\")[[length(msg)]], collapse = \" \"))\n";
//		checkError = checkError + "    msg <- gsub(\"\\\"\", \"\", msg)\n";
//		checkError = checkError + "    cat(\"\n*** \nERROR in ttcTest function:\\n  \",msg, \"\n***\", sep = \"\")\n";
//		checkError = checkError + "}";
//		String sinkOut = "sink()";
//		
//		System.out.println(readData);
//		System.out.println(sinkIn);
//		System.out.println(funcTtcTest);
//		System.out.println(checkError);
//		System.out.println(sinkOut);
//		
//		//R statements passed on to the R engine
//		rEngine.eval(readData);
//		rEngine.eval(sinkIn);
//		rEngine.eval(usedData);
//		rEngine.eval(funcTtcTest);
//		rEngine.eval(checkError);
//		rEngine.eval(sinkOut);
//		rEngineEnd();
		
		//NEW APPROACH
		
		//read data
		String readData = "dataRead <- read.csv(\"" + dataFileName + "\", header = TRUE, na.strings = c(\"NA\",\".\",\" \",\"\"), blank.lines.skip=TRUE, sep = \",\")";
		System.out.println(readData);
		rEngine.eval(readData);
		String runSuccessData = rEngine.eval("data").asString();
		
		if (runSuccessData != null && runSuccessData.equals("notRun")) {	
			System.out.println("error");
			rEngine.eval("capture.output(cat(\"\n***Error reading data.***\n\"),file=\"" + outFileName + "\",append = FALSE)"); 
		} else {
			//create titles
			String designName = null;
			if (design == "CRD") {
				designName="CRD";
			}
			if (design == "RCB") { 
				designName="RCB";
			}
			if (design == "Alpha") { 
				designName="ALPHA-LATTICE";
			}
			if (design == "RowColumn") { 
				designName="ROW-COLUMN";
			}
			
			String usedData = "capture.output(cat(\"\nDATA FILE: " + dataFileName + "\n\",file=\"" + outFileName + "\"))";
			String usedDesign = "capture.output(cat(\"\nDESIGN: TRIPLE TEST CROSS (NO PARENTS) IN " + designName + "\n\n\"),file=\"" + outFileName + "\",append = TRUE)";
			String sep = "capture.output(cat(\"------------------------------\"),file=\"" + outFileName + "\",append = TRUE)";
			String sep2 = "capture.output(cat(\"==============================\n\"),file=\"" + outFileName + "\",append = TRUE)";
			String outspace = "capture.output(cat(\"\n\"),file=\"" + outFileName + "\",append = TRUE)";

			rEngine.eval(usedData);
			rEngine.eval(usedDesign);
			
			String funcTtcTest = null;
			if (design == "CRD") {
				if (environment == "NULL") {
					funcTtcTest = "result<-try(ttcTest(\"CRD\", \"dataRead\", respvar = " + respvarVector + ", tester = \"" + tester + "\", f2lines = \"" + f2lines + 
							"\", rep = NULL, block = NULL, row = NULL, column = NULL, individual = NULL, environment = " + environment + ", codeP1 = \"" + codeP1 + "\", codeP2 = \"" 
							+ codeP2 + "\", codeF1 = \"" + codeF1 + "\", alpha = " + alpha + "), silent = TRUE)";
				} else {
					funcTtcTest = "result<-try(ttcTest(\"CRD\", \"dataRead\", respvar = " + respvarVector + ", tester = \"" + tester + "\", f2lines = \"" + f2lines + 
							"\", rep = NULL, block = NULL, row = NULL, column = NULL, individual = NULL, environment = \"" + environment + "\", codeP1 = \"" + codeP1 + "\", codeP2 = \"" 
							+ codeP2 + "\", codeF1 = \"" + codeF1 + "\", alpha = " + alpha + "), silent = TRUE)";
				}
			} 
			if (design == "RCB") {
				if (environment == "NULL") {
					funcTtcTest = "result<-try(ttcTest(\"RCB\", \"dataRead\", respvar = " + respvarVector + ", tester = \"" + tester + "\", f2lines = \"" + f2lines + "\", rep = NULL, block = \"" 
							+ block + "\", row = NULL, column = NULL, individual = NULL, environment = " + environment + ", codeP1 = \"" + codeP1 + "\", codeP2 = \"" 
							+ codeP2 + "\", codeF1 = \"" + codeF1 + "\", alpha = " + alpha + "), silent = TRUE)";
				} else {
					funcTtcTest = "result<-try(ttcTest(\"RCB\", \"dataRead\", respvar =" + respvarVector + ", tester =\"" + tester + "\", f2lines = \"" + f2lines + "\", rep = NULL, block = \"" 
							+ block + "\", row = NULL, column = NULL, individual = NULL, environment = \"" + environment + "\", codeP1 = \"" + codeP1 + "\", codeP2 = \"" 
							+ codeP2 + "\", codeF1 = \"" + codeF1 + "\", alpha = " + alpha + "), silent = TRUE)";
				}
			}
			if (design == "Alpha") {
				if (environment == "NULL") {
					funcTtcTest = "result<-try(ttcTest(\"Alpha\", \"dataRead\", respvar = " + respvarVector + ", tester = \"" + tester + "\", f2lines = \"" + f2lines + "\", rep = \"" 
							+ rep + "\", block = \"" + block + "\", row = NULL, column = NULL, individual = NULL, environment = " + environment + ", codeP1 = \"" + codeP1 + "\", codeP2 = \"" 
							+ codeP2 + "\", codeF1 = \"" + codeF1 + "\", alpha = " + alpha + "), silent = TRUE)";
				} else {
					funcTtcTest = "result<-try(ttcTest(\"Alpha\", \"dataRead\", respvar = " + respvarVector + ", tester = \"" + tester + "\", f2lines = \"" + f2lines + "\", rep = \"" 
							+ rep + "\", block = \"" + block + "\", row = NULL, column = NULL, individual = NULL, environment = \"" + environment + "\", codeP1 = \"" + codeP1 + "\", codeP2 = \"" 
							+ codeP2 + "\", codeF1 = \"" + codeF1 + "\", alpha = " + alpha + "), silent = TRUE)";
				}
			}
			if (design == "RowColumn") {
				if (environment == "NULL") {
					funcTtcTest = "result<-try(ttcTest(\"RowColumn\", \"dataRead\", respvar = " + respvarVector + ", tester = \"" + tester + "\", f2lines = \"" + f2lines + "\", rep = \"" 
							+ rep + "\", block = NULL, row = \"" + row + "\", column = \"" + column + "\", individual = NULL, environment = " + environment + ", codeP1 = \"" + codeP1 + "\", codeP2 = \"" 
							+ codeP2 + "\", codeF1 = \"" + codeF1 + "\", alpha = " + alpha + "), silent = TRUE)";
				} else {
					funcTtcTest = "result<-try(ttcTest(\"RowColumn\", \"dataRead\", respvar = " + respvarVector + ", tester = \"" + tester + "\", f2lines = \"" + f2lines + "\", rep = \"" 
							+ rep + "\", block = NULL, row = \"" + row + "\", column = \"" + column + "\", individual = NULL, environment = \"" + environment + "\", codeP1 = \"" + codeP1 + "\", codeP2 = \"" 
							+ codeP2 + "\", codeF1 = \"" + codeF1 + "\", alpha = " + alpha + "), silent = TRUE)";
				}
			}
			
			rEngine.eval(funcTtcTest);
			System.out.println(funcTtcTest);

			String runSuccess = rEngine.eval("class(result)").asString();
			if (runSuccess != null && runSuccess.equals("try-error")) {	
				System.out.println("ttcTest: error");
				String checkError = "msg <- trimStrings(strsplit(result, \":\")[[1]])";
				String checkError2 = "msg <- trimStrings(paste(strsplit(msg, \"\\n\")[[length(msg)]], collapse = \" \"))";
				String checkError3 ="msg <- gsub(\"\\\"\", \"\", msg)";
				String checkError4 = "capture.output(cat(\"*** \nERROR in ttcTest function:\\n  \",msg, \"\n***\n\n\", sep = \"\"), file=\"" + outFileName + "\",append = TRUE)";
				rEngine.eval(checkError);
				rEngine.eval(checkError2);
				rEngine.eval(checkError3);
				rEngine.eval(checkError4);

			} else {
				for (int k = 0; k < respvar.length; k++) {
					int i = k + 1; // 1-relative index;
					
					String respVarHead = "capture.output(cat(\"\nRESPONSE VARIABLE: " + respvar[k] + "\n\"),file=\"" + outFileName + "\",append = TRUE)";
					rEngine.eval(sep);
					rEngine.eval(respVarHead);
					rEngine.eval(sep);
					rEngine.eval(outspace);
					
					String getEnvLevels = "length(result$output[[" + i + "]]$site)";
					int envLevelsLength = rEngine.eval(getEnvLevels).asInt();
//					System.out.println("envLevelsLength: " + envLevelsLength);
					
					for (int m = 0; m < envLevelsLength; m++) { // no of envts or sites
						int j = m + 1; // 1-relative index;
						if (environment != "NULL") {
							String envtHead = "capture.output(cat(\"\nANALYSIS FOR: "+ environment + "\", \" = \" ,result$output[[" + i	+ "]]$site[[" + j + "]]$env,\"\n\"),file=\""+ outFileName + "\",append = TRUE)";
							rEngine.eval(sep);
							rEngine.eval(envtHead);
							rEngine.eval(sep);
							rEngine.eval(outspace);
						} 
						
						String manyNA1 = "result$output[["	+ i	+ "]]$site[[" + j + "]]$tooManyNAWarning";
						String manyNA2 = rEngine.eval(manyNA1).asString();
				
//						System.out.println("exceededCheck: " + exceededCheck);
						
						if (manyNA2.equals("YES")) {
							String warningExceed = "capture.output(cat(\"\nToo many missing observations. Cannot proceed with the analysis.\n\n\n\"),file=\"" + outFileName + "\",append = TRUE)";
							rEngine.eval(warningExceed);
						} else {
							//display data summary
							String trialSumHead = "capture.output(cat(\"\nDATA SUMMARY:\n\n\"),file=\"" + outFileName + "\",append = TRUE)";
							String trialSumTable = "capture.output(result$output[[" + i + "]]$site[[" + j + "]]$funcTrialSum,file=\"" + outFileName + "\",append = TRUE)";
							String trialObsRead = "capture.output(cat(\"Number of observations read: \", result$output[["	+ i	+ "]]$site[[" + j + "]]$obsread[[1]],\"\n\"),file=\"" + outFileName + "\",append = TRUE)";
							String trialObsUsed = "capture.output(cat(\"Number of observations used: \", result$output[["	+ i	+ "]]$site[[" + j + "]]$obsused[[1]],\"\n\"),file=\""	+ outFileName + "\",append = TRUE)";

							rEngine.eval(trialSumHead);
							rEngine.eval(trialSumTable);
							rEngine.eval(outspace);
							rEngine.eval(trialObsRead);
							rEngine.eval(trialObsUsed);
//							rEngine.eval(outspace);
							
							String exceeded = "result$output[["	+ i	+ "]]$site[[" + j + "]]$exceededWarning";
							String exceededCheck = rEngine.eval(exceeded).asString();
							rEngine.eval(exceeded);
							rEngine.eval(exceededCheck);
					
//							System.out.println("exceededCheck: " + exceededCheck);
							
							if (exceededCheck.equals("YES")) {
								String warningExceed = "capture.output(cat(\"\n\n***\nERROR: The number of observations read in the data exceeds the size of a balanced data.\n       Please check if the column for block/replicate is properly labeled.\n***\n\n\"),file=\"" + outFileName + "\",append = TRUE)";
								rEngine.eval(warningExceed);
							} else {
								double responseRate =rEngine.eval("result$output[[" + i + "]]$site[[" + j + "]]$responseRate").asDouble();
								
								//display anova table if design is CRD or RCB
								if (design == "CRD" || design == "RCB") {
							        String anovaHead = "capture.output(cat(\"\n\nANOVA TABLE FOR THE EXPERIMENT:\n\n\"),file=\"" + outFileName + "\",append = TRUE)";
							        rEngine.eval(anovaHead);
							        
									if (responseRate < 0.90) {
										String warningExceed = "capture.output(cat(\" ERROR: Too many missing values. Cannot perform ANOVA for balanced data.\n\"),file=\"" + outFileName + "\",append = TRUE)";
										rEngine.eval(warningExceed);
										
									} else {
										String ttcAnova1 = "capture.output(result$output[[" + i + "]]$site[[" + j + "]]$ttc.anova,file=\"" + outFileName + "\",append = TRUE)";
										String ttcAnova2 = "capture.output(cat(\"-------\n\"),file=\"" + outFileName + "\",append = TRUE)";
										String ttcAnova3 = "capture.output(cat(\"REMARK:\", result$output[["	+ i	+ "]]$site[[" + j + "]]$anovaRemark,\"\n\"),file=\""	+ outFileName + "\",append = TRUE)";
										
										rEngine.eval(ttcAnova1);
										rEngine.eval(ttcAnova2);
										rEngine.eval(ttcAnova3);
									}
								}
								
								//test on significance of cross effect
								String crossEffect1 = "capture.output(cat(\"\n\nTESTING FOR THE SIGNIFICANCE OF CROSS EFFECT: (Crosses = " + tester + ":" + f2lines + ")\n\"),file=\"" + outFileName + "\",append = TRUE)";
								rEngine.eval(crossEffect1);
								Double pvalue=0.00;
								
								if (design == "CRD") {
									String crossEffect2 = "capture.output(cat(\"\nModel:\", result$output[["	+ i	+ "]]$site[[" + j + "]]$formula1,\"\n\n\"),file=\""	+ outFileName + "\",append = TRUE)";
									String crossEffect3 = "capture.output(result$output[[" + i + "]]$site[[" + j + "]]$testerF2.test,file=\"" + outFileName + "\",append = TRUE)";
								
									rEngine.eval(crossEffect2);
									rEngine.eval(crossEffect3);
									
									pvalue = rEngine.eval("result$output[[" + i + "]]$site[[" + j + "]]$pValue").asDouble();
									System.out.println("pvalue: " + pvalue);
									
								} else {
									String crossEffect2 = "capture.output(cat(\"\nModel:\", result$output[["	+ i	+ "]]$site[[" + j + "]]$formula1,\"\n\"),file=\""	+ outFileName + "\",append = TRUE)";
									rEngine.eval(crossEffect2);
									
									String outAnovaTable2 = "library(lmerTest)";
									String outAnovaTable3 = "model1b <- lmer(formula(result$output[[" + i + "]]$site[[" + j + "]]$formula1), data = result$output[[" + i + "]]$site[[" + j + "]]$data, REML = T)";
									String outAnovaTable4 = "a.table <- anova(model1b)";
									String outAnovaTable5 = "pvalue <- formatC(as.numeric(format(a.table[1,6], scientific=FALSE)), format=\"f\")";
									String outAnovaTable6 = "a.table<-cbind(round(a.table[,1:5], digits=4),pvalue)";
									String outAnovaTable7 = "colnames(a.table)<-c(\"Df\", \"Sum Sq\", \"Mean Sq\", \"F value\", \"Denom\", \"Pr(>F)\")";
									String outAnovaTable8 = "capture.output(cat(\"Analysis of Variance Table with Satterthwaite Denominator Df\n\"),file=\"" + outFileName + "\",append = TRUE)";
									String outAnovaTable9 = "capture.output(a.table,file=\"" + outFileName + "\",append = TRUE)";
									String outAnovaTable10 = "detach(\"package:lmerTest\")";
									
									rEngine.eval(outAnovaTable2);
									rEngine.eval(outAnovaTable3);
									rEngine.eval(outAnovaTable4);

									pvalue = rEngine.eval("a.table[1,6]").asDouble();
									System.out.println("pvalue: " + pvalue);
									
									rEngine.eval(outAnovaTable5);
									rEngine.eval(outAnovaTable6);
									rEngine.eval(outAnovaTable7);
									rEngine.eval(outspace);
									rEngine.eval(outAnovaTable8);
									rEngine.eval(outAnovaTable9);
									rEngine.eval(outAnovaTable10);
								}
								
								//if cross effect is significant,
								double alphaNew = Double.parseDouble(alpha);
								
								if (pvalue < alphaNew) {
									String epistasis1 = "capture.output(cat(\"\n\nANOVA FOR TESTING EPISTASIS:\n\n\"),file=\"" + outFileName + "\",append = TRUE)";
									rEngine.eval(epistasis1);
									
									if (responseRate < 0.90) {
										String warningExceed = "capture.output(cat(\"\n ERROR: Too many missing values. Cannot perform test for epistasis and estimation of genetic variance components.\n\n\"),file=\"" + outFileName + "\",append = TRUE)";
										rEngine.eval(warningExceed);
										rEngine.eval(outspace);
									} else {
										//testing for epistasis
										String ttcAnova1 = "capture.output(result$output[[" + i + "]]$site[[" + j + "]]$epistasis.test,file=\"" + outFileName + "\",append = TRUE)";
										String ttcAnova2 = "capture.output(cat(\"-------\n\"),file=\"" + outFileName + "\",append = TRUE)";
										String ttcAnova3 = "capture.output(cat(\"REMARK:\", result$output[["	+ i	+ "]]$site[[" + j + "]]$anovaRemark,\"\n\"),file=\""	+ outFileName + "\",append = TRUE)";
										
										rEngine.eval(ttcAnova1);
										rEngine.eval(ttcAnova2);
										rEngine.eval(ttcAnova3);
										
										//anova table
										String anovaTable1 = "capture.output(cat(\"\n\nANOVA TABLE:\n\n\"),file=\"" + outFileName + "\",append = TRUE)";
										String anovaTable2 = "capture.output(result$output[[" + i + "]]$site[[" + j + "]]$sd.anova,file=\"" + outFileName + "\",append = TRUE)";
										String anovaTable3 = "capture.output(cat(\"-------\n\"),file=\"" + outFileName + "\",append = TRUE)";
										String anovaTable4 = "capture.output(cat(\"REMARK:\", result$output[["	+ i	+ "]]$site[[" + j + "]]$anovaRemark,\"\n\"),file=\""	+ outFileName + "\",append = TRUE)";
										
										rEngine.eval(anovaTable1);
										rEngine.eval(anovaTable2);
										rEngine.eval(anovaTable3);
										rEngine.eval(anovaTable4);
										
										//genetic variances
										String genVar1 = "capture.output(cat(\"\n\nESTIMATES OF GENETIC VARIANCE COMPONENTS:\n\n\"),file=\"" + outFileName + "\",append = TRUE)";
										String genVar2 = "capture.output(result$output[[" + i + "]]$site[[" + j + "]]$genVar,file=\"" + outFileName + "\",append = TRUE)";
										
										rEngine.eval(genVar1);
										rEngine.eval(genVar2);
										rEngine.eval(outspace);
										rEngine.eval(outspace);
									} // end of else (if (responseRate < 0.90))
									
								} else {
									rEngine.eval(outspace);
									rEngine.eval(outspace);
									
								} // end of else (if (pvalue < alpha))
							} // end of else (if (exceededCheck.equals("YES")))

						}
						
					} // end of for (int m = 0; m < envLevelsLength; m++)

				} // end of for (int k = 0; k < respvar.length; k++)
				rEngine.eval(outspace);
				rEngine.eval(sep2);
			} // end of else (if (runSuccess != null && runSuccess.equals("try-error"))

		} // end of else (if (runSuccessData != null && runSuccessData.equals("notRun")))

		
		
		rEngineEnd();		
	}
	
	@Override
	public void doTTCMETest(String dataFileName, String outFileName, String design, String[] respvar, String tester, String f2lines, 
			String rep, String block, String row, String column, String individual, String environment, String codeP1, String codeP2, String codeF1, String alpha){

		String respvarVector= inputTransform.createRVector(respvar);
		
		//OLD APPROACH
		//defining the R statements
//		String readData = "dataRead <- read.csv(\"" + dataFileName + "\", header = TRUE, na.strings = c(\"NA\",\".\",\" \",\"\"), blank.lines.skip=TRUE, sep = \",\")";
//		String sinkIn = "sink(\"" + outFileName + "\")";
//		
//		String usedData = "cat(\"\nDATA FILE: " + dataFileName + "\n\")";
//		
//		String funcTtcTestME = null;
//		if (design == "CRD") {
//			funcTtcTestME = "result<-try(ttcTestME(\"CRD\", \"dataRead\", respvar = " + respvarVector + ", tester = \"" + tester + "\", f2lines = \"" + f2lines + 
//						"\", rep = NULL, block = NULL, row = NULL, column = NULL, individual = NULL, environment = \"" + environment + "\", codeP1 = \"" + codeP1 + "\", codeP2 = \"" 
//						+ codeP2 + "\", codeF1 = \"" + codeF1 + "\", alpha = " + alpha + "), silent = TRUE)";
//		} 
//		if (design == "RCB") {
//			funcTtcTestME = "result<-try(ttcTestME(\"RCB\", \"dataRead\", respvar =" + respvarVector + ", tester =\"" + tester + "\", f2lines = \"" + f2lines + "\", rep = NULL, block = \"" 
//						+ block + "\", row = NULL, column = NULL, individual = NULL, environment = \"" + environment + "\", codeP1 = \"" + codeP1 + "\", codeP2 = \"" 
//						+ codeP2 + "\", codeF1 = \"" + codeF1 + "\", alpha = " + alpha + "), silent = TRUE)";
//		}
//		if (design == "Alpha") {
//			funcTtcTestME = "result<-try(ttcTestME(\"Alpha\", \"dataRead\", respvar = " + respvarVector + ", tester = \"" + tester + "\", f2lines = \"" + f2lines + "\", rep = \"" 
//						+ rep + "\", block = \"" + block + "\", row = NULL, column = NULL, individual = NULL, environment = \"" + environment + "\", codeP1 = \"" + codeP1 + "\", codeP2 = \"" 
//						+ codeP2 + "\", codeF1 = \"" + codeF1 + "\", alpha = " + alpha + "), silent = TRUE)";
//		}
//		if (design == "RowColumn") {
//			funcTtcTestME = "result<-try(ttcTestME(\"RowColumn\", \"dataRead\", respvar = " + respvarVector + ", tester = \"" + tester + "\", f2lines = \"" + f2lines + "\", rep = \"" 
//						+ rep + "\", block = NULL, row = \"" + row + "\", column = \"" + column + "\", individual = NULL, environment = \"" + environment + "\", codeP1 = \"" + codeP1 + "\", codeP2 = \"" 
//						+ codeP2 + "\", codeF1 = \"" + codeF1 + "\", alpha = " + alpha + "), silent = TRUE)";
//		}
//		
//		String checkError = "if (class(result) == \"try-error\") {\n";
//		checkError = checkError + "    msg <- trimStrings(strsplit(result, \":\")[[1]])\n";
//		checkError = checkError + "    msg <- trimStrings(paste(strsplit(msg, \"\\n\")[[length(msg)]], collapse = \" \"))\n";
//		checkError = checkError + "    msg <- gsub(\"\\\"\", \"\", msg)\n";
//		checkError = checkError + "    cat(\"\n*** \nERROR in ttcTestME function:\\n  \",msg, \"\n***\", sep = \"\")\n";
//		checkError = checkError + "}";	
//		String sinkOut = "sink()";
//		
//		System.out.println(readData);
//		System.out.println(sinkIn);
//		System.out.println(funcTtcTestME);
//		System.out.println(checkError);
//		System.out.println(sinkOut);
//		
//		//R statements passed on to the R engine
//		rEngine.eval(readData);
//		rEngine.eval(sinkIn);
//		rEngine.eval(usedData);
//		rEngine.eval(funcTtcTestME);
//		rEngine.eval(checkError);
//		rEngine.eval(sinkOut);
//		rEngineEnd();
		
		//NEW APPROACH
		
		//read data
				String readData = "dataRead <- read.csv(\"" + dataFileName + "\", header = TRUE, na.strings = c(\"NA\",\".\",\" \",\"\"), blank.lines.skip=TRUE, sep = \",\")";
				System.out.println(readData);
				rEngine.eval(readData);
				String runSuccessData = rEngine.eval("data").asString();
				
				if (runSuccessData != null && runSuccessData.equals("notRun")) {	
					System.out.println("error");
					rEngine.eval("capture.output(cat(\"\n***Error reading data.***\n\"),file=\"" + outFileName + "\",append = FALSE)"); 
				} else {
					//create titles
					String designName = null;
					if (design == "CRD") {
						designName="CRD";
					}
					if (design == "RCB") { 
						designName="RCB";
					}
					if (design == "Alpha") { 
						designName="ALPHA-LATTICE";
					}
					if (design == "RowColumn") { 
						designName="ROW-COLUMN";
					}
					
					String usedData = "capture.output(cat(\"\nDATA FILE: " + dataFileName + "\n\",file=\"" + outFileName + "\"))";
					String usedDesign = "capture.output(cat(\"\nMULTIPLE ENVIRONMENT ANALYSIS\n\nDESIGN: TRIPLE TEST CROSS (NO PARENTS) IN " + designName + "\n\n\"),file=\"" + outFileName + "\",append = TRUE)";
					String sep = "capture.output(cat(\"------------------------------\"),file=\"" + outFileName + "\",append = TRUE)";
					String sep2 = "capture.output(cat(\"==============================\n\"),file=\"" + outFileName + "\",append = TRUE)";
					String outspace = "capture.output(cat(\"\n\"),file=\"" + outFileName + "\",append = TRUE)";

					rEngine.eval(usedData);
					rEngine.eval(usedDesign);
					
					String funcTtcTestME = null;
					if (design == "CRD") {
						funcTtcTestME = "result<-try(ttcTestME(\"CRD\", \"dataRead\", respvar = " + respvarVector + ", tester = \"" + tester + "\", f2lines = \"" + f2lines + 
									"\", rep = NULL, block = NULL, row = NULL, column = NULL, individual = NULL, environment = \"" + environment + "\", codeP1 = \"" + codeP1 + "\", codeP2 = \"" 
									+ codeP2 + "\", codeF1 = \"" + codeF1 + "\", alpha = " + alpha + "), silent = TRUE)";
					} 
					if (design == "RCB") {
						funcTtcTestME = "result<-try(ttcTestME(\"RCB\", \"dataRead\", respvar =" + respvarVector + ", tester =\"" + tester + "\", f2lines = \"" + f2lines + "\", rep = NULL, block = \"" 
									+ block + "\", row = NULL, column = NULL, individual = NULL, environment = \"" + environment + "\", codeP1 = \"" + codeP1 + "\", codeP2 = \"" 
									+ codeP2 + "\", codeF1 = \"" + codeF1 + "\", alpha = " + alpha + "), silent = TRUE)";
					}
					if (design == "Alpha") {
						funcTtcTestME = "result<-try(ttcTestME(\"Alpha\", \"dataRead\", respvar = " + respvarVector + ", tester = \"" + tester + "\", f2lines = \"" + f2lines + "\", rep = \"" 
									+ rep + "\", block = \"" + block + "\", row = NULL, column = NULL, individual = NULL, environment = \"" + environment + "\", codeP1 = \"" + codeP1 + "\", codeP2 = \"" 
									+ codeP2 + "\", codeF1 = \"" + codeF1 + "\", alpha = " + alpha + "), silent = TRUE)";
					}
					if (design == "RowColumn") {
						funcTtcTestME = "result<-try(ttcTestME(\"RowColumn\", \"dataRead\", respvar = " + respvarVector + ", tester = \"" + tester + "\", f2lines = \"" + f2lines + "\", rep = \"" 
									+ rep + "\", block = NULL, row = \"" + row + "\", column = \"" + column + "\", individual = NULL, environment = \"" + environment + "\", codeP1 = \"" + codeP1 + "\", codeP2 = \"" 
									+ codeP2 + "\", codeF1 = \"" + codeF1 + "\", alpha = " + alpha + "), silent = TRUE)";
					}
					
					rEngine.eval(funcTtcTestME);
					System.out.println(funcTtcTestME);

					String runSuccess = rEngine.eval("class(result)").asString();
					if (runSuccess != null && runSuccess.equals("try-error")) {	
						System.out.println("ttcTest: error");
						String checkError = "msg <- trimStrings(strsplit(result, \":\")[[1]])";
						String checkError2 = "msg <- trimStrings(paste(strsplit(msg, \"\\n\")[[length(msg)]], collapse = \" \"))";
						String checkError3 ="msg <- gsub(\"\\\"\", \"\", msg)";
						String checkError4 = "capture.output(cat(\"*** \nERROR in ttcTest function:\\n  \",msg, \"\n***\n\n\", sep = \"\"), file=\"" + outFileName + "\",append = TRUE)";
						rEngine.eval(checkError);
						rEngine.eval(checkError2);
						rEngine.eval(checkError3);
						rEngine.eval(checkError4);

					} else {
						for (int k = 0; k < respvar.length; k++) {
							int i = k + 1; // 1-relative index;
							
							String respVarHead = "capture.output(cat(\"\nRESPONSE VARIABLE: " + respvar[k] + "\n\"),file=\"" + outFileName + "\",append = TRUE)";
							rEngine.eval(sep);
							rEngine.eval(respVarHead);
							rEngine.eval(sep);
							rEngine.eval(outspace);
							
							String manyNA1 = "result$output[["	+ i	+ "]]$tooManyNAWarning";
							String manyNA2 = rEngine.eval(manyNA1).asString();
					
//							System.out.println("exceededCheck: " + exceededCheck);
							
							if (manyNA2.equals("YES")) {
								String warningExceed = "capture.output(cat(\"\nToo many missing observations. Cannot proceed with the analysis.\n\n\n\"),file=\"" + outFileName + "\",append = TRUE)";
								rEngine.eval(warningExceed);
							} else {
								//display data summary
								String trialSumHead = "capture.output(cat(\"\nDATA SUMMARY:\n\n\"),file=\"" + outFileName + "\",append = TRUE)";
								String trialSumTable = "capture.output(result$output[[" + i + "]]$funcTrialSum,file=\"" + outFileName + "\",append = TRUE)";
								String trialObsRead = "capture.output(cat(\"Number of observations read: \", result$output[["	+ i	+ "]]$obsread[[1]],\"\n\"),file=\"" + outFileName + "\",append = TRUE)";
								String trialObsUsed = "capture.output(cat(\"Number of observations used: \", result$output[["	+ i	+ "]]$obsused[[1]],\"\n\"),file=\""	+ outFileName + "\",append = TRUE)";

								rEngine.eval(trialSumHead);
								rEngine.eval(trialSumTable);
								rEngine.eval(outspace);
								rEngine.eval(trialObsRead);
								rEngine.eval(trialObsUsed);
								
								
								String exceeded = "result$output[["	+ i	+ "]]$exceededWarning";
								String exceededCheck = rEngine.eval(exceeded).asString();
								rEngine.eval(exceeded);
								rEngine.eval(exceededCheck);
								
								if (exceededCheck.equals("YES")) {
									String warningExceed = "capture.output(cat(\"\n\n***\nERROR: The number of observations read in the data exceeds the size of a balanced data.\n       Please check if the column for block/replicate is properly labeled.\n***\n\n\"),file=\"" + outFileName + "\",append = TRUE)";
									rEngine.eval(warningExceed);
								} else {
									double responseRate =rEngine.eval("result$output[[" + i + "]]$responseRate").asDouble();
									
									//display anova table if design is CRD or RCB
									if (design == "CRD" || design == "RCB") {
								        String anovaHead = "capture.output(cat(\"\n\nANOVA TABLE FOR THE EXPERIMENT:\n\n\"),file=\"" + outFileName + "\",append = TRUE)";
								        rEngine.eval(anovaHead);
								        
										if (responseRate < 0.90) {
											String warningExceed = "capture.output(cat(\" ERROR: Too many missing values. Cannot perform ANOVA for balanced data.\n\"),file=\"" + outFileName + "\",append = TRUE)";
											rEngine.eval(warningExceed);
											
										} else {
											String ttcAnova1 = "capture.output(result$output[[" + i + "]]$ttc.anova,file=\"" + outFileName + "\",append = TRUE)";
											String ttcAnova2 = "capture.output(cat(\"-------\n\"),file=\"" + outFileName + "\",append = TRUE)";
											String ttcAnova3 = "capture.output(cat(\"REMARK:\", result$output[["	+ i	+ "]]$anovaRemark,\"\n\"),file=\""	+ outFileName + "\",append = TRUE)";
											
											rEngine.eval(ttcAnova1);
											rEngine.eval(ttcAnova2);
											rEngine.eval(ttcAnova3);
										}
									}
									
									//test on significance of cross effect
									String crossEffect1 = "capture.output(cat(\"\n\nTESTING FOR THE SIGNIFICANCE OF CROSS EFFECT: (Crosses = " + tester + ":" + f2lines + ")\n\"),file=\"" + outFileName + "\",append = TRUE)";
									rEngine.eval(crossEffect1);
									Double pvalue=0.00;
									
									String crossEffect2 = "capture.output(cat(\"\nModel:\", result$output[["	+ i	+ "]]$formula1,\"\n\"),file=\""	+ outFileName + "\",append = TRUE)";
									rEngine.eval(crossEffect2);
									
									String outAnovaTable2 = "library(lmerTest)";
									String outAnovaTable3 = "model1b <- lmer(formula(result$output[[" + i + "]]$formula1), data = result$output[[" + i + "]]$data, REML = T)";
									String outAnovaTable4 = "a.table <- anova(model1b)";
									String outAnovaTable5 = "pvalue <- formatC(as.numeric(format(a.table[1,6], scientific=FALSE)), format=\"f\")";
									String outAnovaTable6 = "a.table<-cbind(round(a.table[,1:5], digits=4),pvalue)";
									String outAnovaTable7 = "colnames(a.table)<-c(\"Df\", \"Sum Sq\", \"Mean Sq\", \"F value\", \"Denom\", \"Pr(>F)\")";
									String outAnovaTable8 = "capture.output(cat(\"Analysis of Variance Table with Satterthwaite Denominator Df\n\"),file=\"" + outFileName + "\",append = TRUE)";
									String outAnovaTable9 = "capture.output(a.table,file=\"" + outFileName + "\",append = TRUE)";
									String outAnovaTable10 = "detach(\"package:lmerTest\")";
									
									rEngine.eval(outAnovaTable2);
									rEngine.eval(outAnovaTable3);
									rEngine.eval(outAnovaTable4);

									pvalue = rEngine.eval("a.table[1,6]").asDouble();
									System.out.println("pvalue: " + pvalue);
									
									rEngine.eval(outAnovaTable5);
									rEngine.eval(outAnovaTable6);
									rEngine.eval(outAnovaTable7);
									rEngine.eval(outspace);
									rEngine.eval(outAnovaTable8);
									rEngine.eval(outAnovaTable9);
									rEngine.eval(outAnovaTable10);
									
									//if cross effect is significant,
									double alphaNew = Double.parseDouble(alpha);
									
									if (pvalue < alphaNew) {
										String epistasis1 = "capture.output(cat(\"\n\nANOVA FOR TESTING EPISTASIS:\n\"),file=\"" + outFileName + "\",append = TRUE)";
										rEngine.eval(epistasis1);
										
										if (responseRate < 0.90) {
											String warningExceed = "capture.output(cat(\"\n ERROR: Too many missing values. Cannot perform test for epistasis and estimation of genetic variance components.\n\n\"),file=\"" + outFileName + "\",append = TRUE)";
											rEngine.eval(warningExceed);
											rEngine.eval(outspace);
										} else {
											//testing for epistasis
											String ttcAnova1 = "capture.output(result$output[[" + i + "]]$epistasis.test,file=\"" + outFileName + "\",append = TRUE)";
											String ttcAnova2 = "capture.output(cat(\"-------\n\"),file=\"" + outFileName + "\",append = TRUE)";
											String ttcAnova3 = "capture.output(cat(\"REMARK:\", result$output[["	+ i	+ "]]$anovaRemark,\"\n\"),file=\""	+ outFileName + "\",append = TRUE)";
											
											rEngine.eval(ttcAnova1);
											rEngine.eval(ttcAnova2);
											rEngine.eval(ttcAnova3);
											
											//anova table
											String anovaTable1 = "capture.output(cat(\"\n\nANOVA TABLE:\n\"),file=\"" + outFileName + "\",append = TRUE)";
											String anovaTable2 = "capture.output(result$output[[" + i + "]]$sd.anova,file=\"" + outFileName + "\",append = TRUE)";
											String anovaTable3 = "capture.output(cat(\"-------\n\"),file=\"" + outFileName + "\",append = TRUE)";
											String anovaTable4 = "capture.output(cat(\"REMARK:\", result$output[["	+ i	+ "]]$anovaRemark,\"\n\"),file=\""	+ outFileName + "\",append = TRUE)";
											
											rEngine.eval(anovaTable1);
											rEngine.eval(anovaTable2);
											rEngine.eval(anovaTable3);
											rEngine.eval(anovaTable4);
											
											//genetic variances
											String genVar1 = "capture.output(cat(\"\n\nESTIMATES OF GENETIC VARIANCE COMPONENTS:\n\"),file=\"" + outFileName + "\",append = TRUE)";
											String genVar2 = "capture.output(result$output[[" + i + "]]$genVar,file=\"" + outFileName + "\",append = TRUE)";
											
											rEngine.eval(genVar1);
											rEngine.eval(genVar2);
											rEngine.eval(outspace);
											rEngine.eval(outspace);
										} // end of else (if (responseRate < 0.90))
										
									} // end of if (pvalue < alpha)
								} // end of else (if (exceededCheck.equals("YES")))
							}

						} // end of for (int k = 0; k < respvar.length; k++)
						rEngine.eval(outspace);
						rEngine.eval(sep2);
					} // end of else (if (runSuccess != null && runSuccess.equals("try-error"))

				} // end of else (if (runSuccessData != null && runSuccessData.equals("notRun")))
				
				rEngineEnd();


			}
	
		
	@Override
	public void doDiallel1Test(String dataFileName, String outFileName, String resultFolderPath, String design, String[] respvar, String p1, String p2, 
			String rep, String block, String row, String column, String cross, String individual, String environment, String alpha){

		String respvarVector= inputTransform.createRVector(respvar);
		
		//OLD APPROACH
		//defining the R statements
//		String readData = "dataRead <- read.csv(\"" + dataFileName + "\", header = TRUE, na.strings = c(\"NA\",\".\",\" \",\"\"), blank.lines.skip=TRUE, sep = \",\")";
//		String sinkIn = "sink(\"" + outFileName + "\")";
//		
//		String usedData = "cat(\"\nDATA FILE: " + dataFileName + "\n\")";
//		
//		String funcDiallel1Test=null;
//		if (design == "CRD") {
//			if (environment == "NULL") {
//				funcDiallel1Test = "result<-try(diallel1Test(\"CRD\", \"dataRead\", " + respvarVector + ", \"" + p1 + "\", \"" + p2 + 
//						"\", rep = NULL, block = NULL, row = NULL, column = NULL, cross = " + cross + ", individual = NULL, environment = " + environment + ", alpha = " + alpha + "), silent = TRUE)";
//			} else {
//				funcDiallel1Test = "result<-try(diallel1Test(\"CRD\", \"dataRead\", " + respvarVector + ", \"" + p1 + "\", \"" + p2 + 
//						"\", rep = NULL, block = NULL, row = NULL, column = NULL, cross = " + cross + ", individual = NULL, environment = \"" + environment + "\", alpha = " + alpha + "), silent = TRUE)";
//			}
//		}
//		if (design == "RCB") {
//			if (environment == "NULL") {
//				funcDiallel1Test = "result<-try(diallel1Test(\"RCB\", \"dataRead\", " + respvarVector + ", \"" + p1 + "\", \"" + p2 + "\", rep = NULL, " +
//						"block = \"" + block + "\", row = NULL, column = NULL, cross = " + cross + ", individual = NULL, environment = " + environment + ", alpha = " + alpha + "), silent = TRUE)";
//			} else {
//				funcDiallel1Test = "result<-try(diallel1Test(\"RCB\", \"dataRead\", " + respvarVector + ", \"" + p1 + "\", \"" + p2 + "\", rep = NULL, " +
//						"block = \"" + block + "\", row = NULL, column = NULL, cross = " + cross + ", individual = NULL, environment = \"" + environment + "\", alpha = " + alpha + "), silent = TRUE)";;
//			}
//		}
//		if (design == "Alpha") {
//			if (environment == "NULL") {
//				funcDiallel1Test = "result<-try(diallel1Test(\"Alpha\", \"dataRead\", " + respvarVector + ", \"" + p1 + "\", \"" + p2 + "\", rep = \"" 
//						+ rep + "\", block = \"" + block + "\", row = NULL, column = NULL, cross = " + cross + ", individual = NULL, environment = " + environment + ", alpha = " + alpha + "), silent = TRUE)";
//			} else {
//				funcDiallel1Test = "result<-try(diallel1Test(\"Alpha\", \"dataRead\", " + respvarVector + ", \"" + p1 + "\", \"" + p2 + "\", rep = \"" 
//						+ rep + "\", block = \"" + block + "\", row = NULL, column = NULL, cross = " + cross + ", individual = NULL, environment = \"" + environment + "\", alpha = " + alpha + "), silent = TRUE)";;
//			}
//		}
//		if (design == "RowColumn") {
//			if (environment == "NULL") {
//				funcDiallel1Test = "result<-try(diallel1Test(\"RowColumn\", \"dataRead\", " + respvarVector + ", \"" + p1 + "\", \"" + p2 + "\", rep = \"" 
//						+ rep + "\", block = NULL, row = \"" + row + "\", column = \"" + column + "\", cross = " + cross + ", individual = NULL, environment = " + environment + ", alpha = " + alpha + "), silent = TRUE)";
//			} else {
//				funcDiallel1Test = "result<-try(diallel1Test(\"RowColumn\", \"dataRead\", " + respvarVector + ", \"" + p1 + "\", \"" + p2 + "\", rep = \"" 
//						+ rep + "\", block = NULL, row = \"" + row + "\", column = \"" + column + "\", cross = " + cross + ", individual = NULL, environment = \"" + environment + "\", alpha = " + alpha + "), silent = TRUE)";
//			}
//		}
//		
//		String checkError = "if (class(result) == \"try-error\") {\n";
//		checkError = checkError + "    msg <- trimStrings(strsplit(result, \":\")[[1]])\n";
//		checkError = checkError + "    msg <- trimStrings(paste(strsplit(msg, \"\\n\")[[length(msg)]], collapse = \" \"))\n";
//		checkError = checkError + "    msg <- gsub(\"\\\"\", \"\", msg)\n";
//		checkError = checkError + "    cat(\"\n*** \nERROR in diallel1Test function:\\n  \",msg, \"\n***\", sep = \"\")\n";
//		checkError = checkError + "}";	
//		String sinkOut = "sink()";
//		
//		System.out.println(readData);
//		System.out.println(sinkIn);
//		System.out.println(funcDiallel1Test);
//		System.out.println(checkError);
//		System.out.println(sinkOut);
//		
//		//R statements passed on to the R engine
//		rEngine.eval(readData);
//		rEngine.eval(sinkIn);
//		rEngine.eval(usedData);
//		rEngine.eval(funcDiallel1Test);
//		rEngine.eval(checkError);
//		rEngine.eval(sinkOut);
//		rEngineEnd();
		
		//NEW APPROACH
		//read data
		String readData = "dataRead <- read.csv(\"" + dataFileName + "\", header = TRUE, na.strings = c(\"NA\",\".\",\" \",\"\"), blank.lines.skip=TRUE, sep = \",\")";
		System.out.println(readData);
		rEngine.eval(readData);
		String runSuccessData = rEngine.eval("data").asString();
		
		if (runSuccessData != null && runSuccessData.equals("notRun")) {	
			System.out.println("error");
			rEngine.eval("capture.output(cat(\"\n***Error reading data.***\n\"),file=\"" + outFileName + "\",append = FALSE)"); 
		} else {
			//create titles
			String designName = null;
			if (design == "CRD") {
				designName="CRD";
			}
			if (design == "RCB") { 
				designName="RCB";
			}
			if (design == "Alpha") { 
				designName="ALPHA-LATTICE";
			}
			if (design == "RowColumn") { 
				designName="ROW-COLUMN";
			}
			
			String usedData = "capture.output(cat(\"\nDATA FILE: " + dataFileName + "\n\",file=\"" + outFileName + "\"))";
			String usedDesign = null;
			if (cross == "TRUE") {
				usedDesign = "capture.output(cat(\"\nDIALLEL ANALYSIS: GRIFFING METHOD I IN " + designName + " (CROSS)\n\n\"),file=\"" + outFileName + "\",append = TRUE)";
			} else {
				usedDesign = "capture.output(cat(\"\nDIALLEL ANALYSIS: GRIFFING METHOD I IN " + designName + " (SELF)\n\n\"),file=\"" + outFileName + "\",append = TRUE)";
			}
			
			String sep = "capture.output(cat(\"------------------------------\"),file=\"" + outFileName + "\",append = TRUE)";
			String sep2 = "capture.output(cat(\"==============================\n\"),file=\"" + outFileName + "\",append = TRUE)";
			String outspace = "capture.output(cat(\"\n\"),file=\"" + outFileName + "\",append = TRUE)";
	
			rEngine.eval(usedData);
			rEngine.eval(usedDesign);
			
			String funcDiallel1Test=null;
			if (design == "CRD") {
				if (environment == "NULL") {
					funcDiallel1Test = "result<-try(diallel1Test(\"CRD\", \"dataRead\", " + respvarVector + ", \"" + p1 + "\", \"" + p2 + 
							"\", rep = NULL, block = NULL, row = NULL, column = NULL, cross = " + cross + ", individual = NULL, environment = " + environment + ", alpha = " + alpha + "), silent = TRUE)";
				} else {
					funcDiallel1Test = "result<-try(diallel1Test(\"CRD\", \"dataRead\", " + respvarVector + ", \"" + p1 + "\", \"" + p2 + 
							"\", rep = NULL, block = NULL, row = NULL, column = NULL, cross = " + cross + ", individual = NULL, environment = \"" + environment + "\", alpha = " + alpha + "), silent = TRUE)";
				}
			}
			if (design == "RCB") {
				if (environment == "NULL") {
					funcDiallel1Test = "result<-try(diallel1Test(\"RCB\", \"dataRead\", " + respvarVector + ", \"" + p1 + "\", \"" + p2 + "\", rep = NULL, " +
							"block = \"" + block + "\", row = NULL, column = NULL, cross = " + cross + ", individual = NULL, environment = " + environment + ", alpha = " + alpha + "), silent = TRUE)";
				} else {
					funcDiallel1Test = "result<-try(diallel1Test(\"RCB\", \"dataRead\", " + respvarVector + ", \"" + p1 + "\", \"" + p2 + "\", rep = NULL, " +
							"block = \"" + block + "\", row = NULL, column = NULL, cross = " + cross + ", individual = NULL, environment = \"" + environment + "\", alpha = " + alpha + "), silent = TRUE)";;
				}
			}
			if (design == "Alpha") {
				if (environment == "NULL") {
					funcDiallel1Test = "result<-try(diallel1Test(\"Alpha\", \"dataRead\", " + respvarVector + ", \"" + p1 + "\", \"" + p2 + "\", rep = \"" 
							+ rep + "\", block = \"" + block + "\", row = NULL, column = NULL, cross = " + cross + ", individual = NULL, environment = " + environment + ", alpha = " + alpha + "), silent = TRUE)";
				} else {
					funcDiallel1Test = "result<-try(diallel1Test(\"Alpha\", \"dataRead\", " + respvarVector + ", \"" + p1 + "\", \"" + p2 + "\", rep = \"" 
							+ rep + "\", block = \"" + block + "\", row = NULL, column = NULL, cross = " + cross + ", individual = NULL, environment = \"" + environment + "\", alpha = " + alpha + "), silent = TRUE)";;
				}
			}
			if (design == "RowColumn") {
				if (environment == "NULL") {
					funcDiallel1Test = "result<-try(diallel1Test(\"RowColumn\", \"dataRead\", " + respvarVector + ", \"" + p1 + "\", \"" + p2 + "\", rep = \"" 
							+ rep + "\", block = NULL, row = \"" + row + "\", column = \"" + column + "\", cross = " + cross + ", individual = NULL, environment = " + environment + ", alpha = " + alpha + "), silent = TRUE)";
				} else {
					funcDiallel1Test = "result<-try(diallel1Test(\"RowColumn\", \"dataRead\", " + respvarVector + ", \"" + p1 + "\", \"" + p2 + "\", rep = \"" 
							+ rep + "\", block = NULL, row = \"" + row + "\", column = \"" + column + "\", cross = " + cross + ", individual = NULL, environment = \"" + environment + "\", alpha = " + alpha + "), silent = TRUE)";
				}
			}
			
			rEngine.eval(funcDiallel1Test);
			System.out.println(funcDiallel1Test);
	
			String runSuccess = rEngine.eval("class(result)").asString();
			if (runSuccess != null && runSuccess.equals("try-error")) {	
				System.out.println("diallel1Test: error");
				String checkError = "msg <- trimStrings(strsplit(result, \":\")[[1]])";
				String checkError2 = "msg <- trimStrings(paste(strsplit(msg, \"\\n\")[[length(msg)]], collapse = \" \"))";
				String checkError3 ="msg <- gsub(\"\\\"\", \"\", msg)";
				String checkError4 = "capture.output(cat(\"*** \nERROR in diallel1Test function:\\n  \",msg, \"\n***\n\n\", sep = \"\"), file=\"" + outFileName + "\",append = TRUE)";
				rEngine.eval(checkError);
				rEngine.eval(checkError2);
				rEngine.eval(checkError3);
				rEngine.eval(checkError4);
	
			} else {
				for (int k = 0; k < respvar.length; k++) {
					int i = k + 1; // 1-relative index;
					
					String respVarHead = "capture.output(cat(\"\nRESPONSE VARIABLE: " + respvar[k] + "\n\"),file=\"" + outFileName + "\",append = TRUE)";
					rEngine.eval(sep);
					rEngine.eval(respVarHead);
					rEngine.eval(sep);
					rEngine.eval(outspace);
					
					String getEnvLevels = "length(result$output[[" + i + "]]$site)";
					int envLevelsLength = rEngine.eval(getEnvLevels).asInt();
	//							System.out.println("envLevelsLength: " + envLevelsLength);
					
					for (int m = 0; m < envLevelsLength; m++) { // no of envts or sites
						int j = m + 1; // 1-relative index;
						if (environment != "NULL") {
							String envtHead = "capture.output(cat(\"\nANALYSIS FOR: "+ environment + "\", \" = \" ,result$output[[" + i	+ "]]$site[[" + j + "]]$env,\"\n\"),file=\""+ outFileName + "\",append = TRUE)";
							rEngine.eval(sep);
							rEngine.eval(envtHead);
							rEngine.eval(sep);
							rEngine.eval(outspace);
						} 
						
						//check if max of lengthPerCross is equal to nlevelsRep 
						int maxLengthPerCross =rEngine.eval("result$output[[" + i + "]]$site[[" + j + "]]$maxLengthPerCross").asInt();
						int nLevelsRep =rEngine.eval("result$output[[" + i + "]]$site[[" + j + "]]$nlevelsRep").asInt();
						
						if (maxLengthPerCross > nLevelsRep) {
							String errorMessage1 = "capture.output(cat(\"\nERROR: \",result$output[[" + i	+ "]]$site[[" + j + "]]$blockLabelError),file=\""+ outFileName + "\",append = TRUE)";
							String errorMessage2 = "capture.output(cat(\"\n       \",result$output[[" + i	+ "]]$site[[" + j + "]]$blockLabelError2),file=\""+ outFileName + "\",append = TRUE)";
						
							rEngine.eval(errorMessage1);
							rEngine.eval(errorMessage2);
							rEngine.eval(outspace);
							rEngine.eval(outspace);
							rEngine.eval(outspace);
							
							break;
						}
						
						
						String manyNA1 = "result$output[["	+ i	+ "]]$site[[" + j + "]]$tooManyNAWarning";
						String manyNA2 = rEngine.eval(manyNA1).asString();
				
	//								System.out.println("exceededCheck: " + exceededCheck);
						
						if (manyNA2.equals("YES")) {
							String warningExceed = "capture.output(cat(\"\nToo many missing observations. Cannot proceed with the analysis.\n\n\n\"),file=\"" + outFileName + "\",append = TRUE)";
							rEngine.eval(warningExceed);
						} else {
							//display data summary
							String trialSumHead = "capture.output(cat(\"\nDATA SUMMARY:\n\n\"),file=\"" + outFileName + "\",append = TRUE)";
							String trialSumTable = "capture.output(result$output[[" + i + "]]$site[[" + j + "]]$funcTrialSum,file=\"" + outFileName + "\",append = TRUE)";
							String trialObsRead = "capture.output(cat(\"Number of observations read: \", result$output[["	+ i	+ "]]$site[[" + j + "]]$obsread[[1]],\"\n\"),file=\"" + outFileName + "\",append = TRUE)";
							String trialObsUsed = "capture.output(cat(\"Number of observations used: \", result$output[["	+ i	+ "]]$site[[" + j + "]]$obsused[[1]],\"\n\"),file=\""	+ outFileName + "\",append = TRUE)";
	
							rEngine.eval(trialSumHead);
							rEngine.eval(trialSumTable);
							rEngine.eval(outspace);
							rEngine.eval(trialObsRead);
							rEngine.eval(trialObsUsed);
	//									rEngine.eval(outspace);
							
							String exceeded = "result$output[["	+ i	+ "]]$site[[" + j + "]]$exceededWarning";
							String exceededCheck = rEngine.eval(exceeded).asString();
							rEngine.eval(exceeded);
							rEngine.eval(exceededCheck);
					
	//									System.out.println("exceededCheck: " + exceededCheck);
							
							if (exceededCheck.equals("YES")) {
								String warningExceed = "capture.output(cat(\"\n\n***\nERROR: The number of observations read in the data exceeds the size of a balanced data.\n       Please check if the column for block/replicate is properly labeled.\n***\n\n\"),file=\"" + outFileName + "\",append = TRUE)";
								rEngine.eval(warningExceed);
							} else {
								double responseRate =rEngine.eval("result$output[[" + i + "]]$site[[" + j + "]]$responseRate").asDouble();
								
								//display anova table if design is CRD or RCB
								if (design == "CRD" || design == "RCB") {
							        String anovaHead = "capture.output(cat(\"\n\nANOVA TABLE FOR THE EXPERIMENT:\n\n\"),file=\"" + outFileName + "\",append = TRUE)";
							        rEngine.eval(anovaHead);
							        
							        if (design == "CRD") {
							        	String ttcAnova1 = "capture.output(result$output[[" + i + "]]$site[[" + j + "]]$diallel1.anova,file=\"" + outFileName + "\",append = TRUE)";
										String ttcAnova2 = "capture.output(cat(\"-------\n\"),file=\"" + outFileName + "\",append = TRUE)";
										String ttcAnova3 = "capture.output(cat(\"REMARK:\", result$output[["	+ i	+ "]]$site[[" + j + "]]$anovaRemark1,\"\n\"),file=\""	+ outFileName + "\",append = TRUE)";
										
										rEngine.eval(ttcAnova1);
										rEngine.eval(ttcAnova2);
										rEngine.eval(ttcAnova3);
							        }
							        
							        if (design == "RCB") {
							        	if (responseRate < 0.90) {
											String warningExceed = "capture.output(cat(\" ERROR: Too many missing values. Cannot perform ANOVA for balanced data.\n\"),file=\"" + outFileName + "\",append = TRUE)";
											rEngine.eval(warningExceed);
											
										} else {
											String ttcAnova1 = "capture.output(result$output[[" + i + "]]$site[[" + j + "]]$diallel1.anova,file=\"" + outFileName + "\",append = TRUE)";
											String ttcAnova2 = "capture.output(cat(\"-------\n\"),file=\"" + outFileName + "\",append = TRUE)";
											String ttcAnova3 = "capture.output(cat(\"REMARK:\", result$output[["	+ i	+ "]]$site[[" + j + "]]$anovaRemark1,\"\n\"),file=\""	+ outFileName + "\",append = TRUE)";
											
											rEngine.eval(ttcAnova1);
											rEngine.eval(ttcAnova2);
											rEngine.eval(ttcAnova3);
										}
							        }
								}
								
								//test on significance of cross effect
								String crossEffect1 = "capture.output(cat(\"\n\nTESTING FOR THE SIGNIFICANCE OF CROSS EFFECT: (Crosses = " + p1 + ":" + p2 + ")\n\"),file=\"" + outFileName + "\",append = TRUE)";
								rEngine.eval(crossEffect1);
								Double pvalue=0.00;
								
								if (design == "CRD") {
									String crossEffect2 = "capture.output(cat(\"\nModel:\", result$output[["	+ i	+ "]]$site[[" + j + "]]$formula1,\"\n\n\"),file=\""	+ outFileName + "\",append = TRUE)";
									String crossEffect3 = "capture.output(result$output[[" + i + "]]$site[[" + j + "]]$genoEffect.anova,file=\"" + outFileName + "\",append = TRUE)";
								
									rEngine.eval(crossEffect2);
									rEngine.eval(crossEffect3);
									
									pvalue = rEngine.eval("result$output[[" + i + "]]$site[[" + j + "]]$pValue").asDouble();
									System.out.println("pvalue: " + pvalue);
									
									//display matrix of means
									String matrixMeans1 = "capture.output(cat(\"\n\nMATRIX OF MEANS:\n\n\"),file=\"" + outFileName + "\",append = TRUE)";
									String matrixMeans2 = "capture.output(result$output[[" + i + "]]$site[[" + j + "]]$means.matrix,file=\"" + outFileName + "\",append = TRUE)";
									rEngine.eval(matrixMeans1);
									rEngine.eval(matrixMeans2);
									
//									String estimatedMissing1 = "result$output[["	+ i	+ "]]$site[[" + j + "]]$estimatedMissing";
//									String estimatedMissing2 = rEngine.eval(estimatedMissing1).asString();
									
//									if (estimatedMissing2.equals("TRUE")) {
										String anovaRemark1 = "capture.output(cat(\"-------\n\"),file=\"" + outFileName + "\",append = TRUE)";
										String anovaRemark2 = "capture.output(cat(\"REMARK:\", result$output[["	+ i	+ "]]$site[[" + j + "]]$anovaRemark2,\"\n\"),file=\""	+ outFileName + "\",append = TRUE)";
										
										rEngine.eval(anovaRemark1);
										rEngine.eval(anovaRemark2);
//									}
									
									//save matrix of means to a file
									String meansFileName2 = null;
									if (environment != "NULL") {
										meansFileName2 = "meansFileName2 <- paste(\"" + resultFolderPath + "\",\"matrixMeans_\", \"" + respvar[k] + "\", \"_\", result$output[[" + i + "]]$site[[" + j + "]]$env,\".csv\", sep=\"\")";
									} else {
										meansFileName2 = "meansFileName2 <- paste(\"" + resultFolderPath + "\",\"matrixMeans_\", \"" + respvar[k] + "\", \".csv\", sep=\"\")";
									}
									
									System.out.println(meansFileName2);
									
									String funcSavePredMeansCsv = "saveDataB1 <- try(write.table(result$output[[" + i + "]]$site[[" + j + "]]$means.matrix,file = meansFileName2 ,sep=\",\",col.names=NA, row.names=TRUE), silent=TRUE)";
									rEngine.eval(meansFileName2);
									rEngine.eval(funcSavePredMeansCsv);
									
									String runSuccessSavePredMeans = rEngine.eval("class(saveDataB1)").asString();
									if (runSuccessSavePredMeans != null && runSuccessSavePredMeans.equals("try-error")) {	
										System.out.println("save matrix of means: error");
										String checkError = "msg <- trimStrings(strsplit(saveDataB1, \":\")[[1]])";
										String checkError2 = "msg <- trimStrings(paste(strsplit(msg, \"\\n\")[[length(msg)]], collapse = \" \"))";
										String checkError3 ="msg <- gsub(\"\\\"\", \"\", msg)";
										String checkError4 = "capture.output(cat(\"\n***\nERROR in saving matrix of means to a file:\\n  \",msg, \"\n***\n\", sep = \"\"), file=\"" + outFileName + "\",append = TRUE)";
										rEngine.eval(checkError);
										rEngine.eval(checkError2);
										rEngine.eval(checkError3);
										rEngine.eval(checkError4);
									}
									
								} else {
									if (responseRate < 0.90) {
										String warningExceed = "capture.output(cat(\"\n ERROR: Too many missing values. Cannot perform the analysis.\n\"),file=\"" + outFileName + "\",append = TRUE)";
										rEngine.eval(warningExceed);
									} else {
										String crossEffect2 = "capture.output(cat(\"\nModel:\", result$output[["	+ i	+ "]]$site[[" + j + "]]$formula1,\"\n\"),file=\""	+ outFileName + "\",append = TRUE)";
										rEngine.eval(crossEffect2);
										
										String outAnovaTable2 = "library(lmerTest)";
										String outAnovaTable3 = "model1b <- lmer(formula(result$output[[" + i + "]]$site[[" + j + "]]$formula1), data = result$output[[" + i + "]]$site[[" + j + "]]$data, REML = T)";
										String outAnovaTable4 = "a.table <- anova(model1b)";
										String outAnovaTable5 = "pvalue <- formatC(as.numeric(format(a.table[1,6], scientific=FALSE)), format=\"f\")";
										String outAnovaTable6 = "a.table<-cbind(round(a.table[,1:5], digits=4),pvalue)";
										String outAnovaTable7 = "colnames(a.table)<-c(\"Df\", \"Sum Sq\", \"Mean Sq\", \"F value\", \"Denom\", \"Pr(>F)\")";
										String outAnovaTable8 = "capture.output(cat(\"Analysis of Variance Table with Satterthwaite Denominator Df\n\"),file=\"" + outFileName + "\",append = TRUE)";
										String outAnovaTable9 = "capture.output(a.table,file=\"" + outFileName + "\",append = TRUE)";
										String outAnovaTable10 = "detach(\"package:lmerTest\")";
										
										rEngine.eval(outAnovaTable2);
										rEngine.eval(outAnovaTable3);
										rEngine.eval(outAnovaTable4);
		
										pvalue = rEngine.eval("a.table[1,6]").asDouble();
										System.out.println("pvalue: " + pvalue);
										
										rEngine.eval(outAnovaTable5);
										rEngine.eval(outAnovaTable6);
										rEngine.eval(outAnovaTable7);
										rEngine.eval(outspace);
										rEngine.eval(outAnovaTable8);
										rEngine.eval(outAnovaTable9);
										rEngine.eval(outAnovaTable10);
										
										//display matrix of means
										String matrixMeans1 = "capture.output(cat(\"\n\nMATRIX OF MEANS:\n\n\"),file=\"" + outFileName + "\",append = TRUE)";
										String matrixMeans2 = "capture.output(result$output[[" + i + "]]$site[[" + j + "]]$means.matrix,file=\"" + outFileName + "\",append = TRUE)";
										rEngine.eval(matrixMeans1);
										rEngine.eval(matrixMeans2);
										
//										String estimatedMissing1 = "result$output[["	+ i	+ "]]$site[[" + j + "]]$estimatedMissing";
//										String estimatedMissing2 = rEngine.eval(estimatedMissing1).asString();
										
//										if (estimatedMissing2.equals("TRUE")) {
											String anovaRemark1 = "capture.output(cat(\"-------\n\"),file=\"" + outFileName + "\",append = TRUE)";
											String anovaRemark2 = "capture.output(cat(\"REMARK:\", result$output[["	+ i	+ "]]$site[[" + j + "]]$anovaRemark2,\"\n\"),file=\""	+ outFileName + "\",append = TRUE)";
											
											rEngine.eval(anovaRemark1);
											rEngine.eval(anovaRemark2);
//										}
										
										//save matrix of means to a file
										String meansFileName2 = null;
										if (environment != "NULL") {
											meansFileName2 = "meansFileName2 <- paste(\"" + resultFolderPath + "\",\"matrixMeans_\", \"" + respvar[k] + "\", \"_\", result$output[[" + i + "]]$site[[" + j + "]]$env,\".csv\", sep=\"\")";
										} else {
											meansFileName2 = "meansFileName2 <- paste(\"" + resultFolderPath + "\",\"matrixMeans_\", \"" + respvar[k] + "\", \".csv\", sep=\"\")";
										}
										
										System.out.println(meansFileName2);
										
										String funcSavePredMeansCsv = "saveDataB1 <- try(write.table(result$output[[" + i + "]]$site[[" + j + "]]$means.matrix,file = meansFileName2 ,sep=\",\",col.names=NA, row.names=TRUE), silent=TRUE)";
										rEngine.eval(meansFileName2);
										rEngine.eval(funcSavePredMeansCsv);
										
										String runSuccessSavePredMeans = rEngine.eval("class(saveDataB1)").asString();
										if (runSuccessSavePredMeans != null && runSuccessSavePredMeans.equals("try-error")) {	
											System.out.println("save matrix of means: error");
											String checkError = "msg <- trimStrings(strsplit(saveDataB1, \":\")[[1]])";
											String checkError2 = "msg <- trimStrings(paste(strsplit(msg, \"\\n\")[[length(msg)]], collapse = \" \"))";
											String checkError3 ="msg <- gsub(\"\\\"\", \"\", msg)";
											String checkError4 = "capture.output(cat(\"\n***\nERROR in saving matrix of means to a file:\\n  \",msg, \"\n***\n\", sep = \"\"), file=\"" + outFileName + "\",append = TRUE)";
											rEngine.eval(checkError);
											rEngine.eval(checkError2);
											rEngine.eval(checkError3);
											rEngine.eval(checkError4);
										}
									}
								}
								
								String meansComplete1 = "result$output[["	+ i	+ "]]$site[[" + j + "]]$meansComplete";
								String meansComplete2 = rEngine.eval(meansComplete1).asString();
								
								if (meansComplete2.equals("TRUE")) {
									//if cross effect is significant,
									double alphaNew = Double.parseDouble(alpha);
									
									if (pvalue < alphaNew) {
										String gcaAnovaHead = "capture.output(cat(\"\n\nANALYSIS OF VARIANCE:\n\n\"),file=\"" + outFileName + "\",append = TRUE)";
										rEngine.eval(gcaAnovaHead);
											
										if (responseRate >= 0.90 || design == "CRD") {
											//GCA and SCA anova table
											String gcaAnova1 = "capture.output(result$output[[" + i + "]]$site[[" + j + "]]$gcasca.anova,file=\"" + outFileName + "\",append = TRUE)";
											String gcaAnova2 = "capture.output(cat(\"-------\n\"),file=\"" + outFileName + "\",append = TRUE)";
											String gcaAnova3 = "capture.output(cat(\"NOTE:\", result$output[["	+ i	+ "]]$site[[" + j + "]]$gcasca.anovaRemark),file=\""	+ outFileName + "\",append = TRUE)";
											
											rEngine.eval(gcaAnova1);
											rEngine.eval(gcaAnova2);
											rEngine.eval(gcaAnova3);
											
											//estimates of effects
											String effects1 = "capture.output(cat(\"\n\nGENERAL COMBINING ABILITY EFFECTS (diagonal), SPECIFIC COMBINING\n\"),file=\"" + outFileName + "\",append = TRUE)";
											String effects2 = "capture.output(cat(\"ABILITY EFFECTS (above diagonal) AND RECIPROCAL EFFECTS (below diagonal):\n\n\"),file=\"" + outFileName + "\",append = TRUE)";
											String effects3 = "capture.output(result$output[[" + i + "]]$site[[" + j + "]]$gcasca.matrix,file=\"" + outFileName + "\",append = TRUE)";
											
											rEngine.eval(effects1);
											rEngine.eval(effects2);
											rEngine.eval(effects3);
											
											//save matrix of effects to a file
											String effectsFileName2 = null;
											if (environment != "NULL") {
												effectsFileName2 = "effectsFileName2 <- paste(\"" + resultFolderPath + "\",\"matrixEffects_\", \"" + respvar[k] + "\", \"_\", result$output[[" + i + "]]$site[[" + j + "]]$env,\".csv\", sep=\"\")";
											} else {
												effectsFileName2 = "effectsFileName2 <- paste(\"" + resultFolderPath + "\",\"matrixEffects_\", \"" + respvar[k] + "\", \".csv\", sep=\"\")";
											}
											
											String funcSaveEffectsCsv = "saveDataB2 <- try(write.table(result$output[[" + i + "]]$site[[" + j + "]]$gcasca.matrix,file = effectsFileName2 ,sep=\",\",col.names=NA, row.names=TRUE), silent=TRUE)";
											rEngine.eval(effectsFileName2);
											rEngine.eval(funcSaveEffectsCsv);
											
											String runSuccessSaveEffects = rEngine.eval("class(saveDataB2)").asString();
											if (runSuccessSaveEffects != null && runSuccessSaveEffects.equals("try-error")) {	
												System.out.println("save matrix of effects: error");
												String checkError = "msg <- trimStrings(strsplit(saveDataB2, \":\")[[1]])";
												String checkError2 = "msg <- trimStrings(paste(strsplit(msg, \"\\n\")[[length(msg)]], collapse = \" \"))";
												String checkError3 ="msg <- gsub(\"\\\"\", \"\", msg)";
												String checkError4 = "capture.output(cat(\"\n***\nERROR in saving matrix of effects to a file:\\n  \",msg, \"\n***\n\", sep = \"\"), file=\"" + outFileName + "\",append = TRUE)";
												rEngine.eval(checkError);
												rEngine.eval(checkError2);
												rEngine.eval(checkError3);
												rEngine.eval(checkError4);
											}
											
											//estimate of variance components
											String varComp1 = "capture.output(cat(\"\n\nESTIMATES OF VARIANCE COMPONENTS:\n\n\"),file=\"" + outFileName + "\",append = TRUE)";
											String varComp2 = "capture.output(result$output[[" + i + "]]$site[[" + j + "]]$var.components,file=\"" + outFileName + "\",append = TRUE)";
											
											rEngine.eval(varComp1);
											rEngine.eval(varComp2);
											
											//genetic variances
											String genVar1 = "capture.output(cat(\"\n\nESTIMATES OF GENETIC VARIANCE COMPONENTS:\n\"),file=\"" + outFileName + "\",append = TRUE)";
											String genVar2 = "capture.output(result$output[[" + i + "]]$site[[" + j + "]]$genvar.components,file=\"" + outFileName + "\",append = TRUE)";
											
											rEngine.eval(genVar1);
											rEngine.eval(genVar2);
											
											//standard errors
											String lsd1 = "capture.output(cat(\"\n\nTABLE OF STANDARD ERRORS AND LSDs:\n\n\"),file=\"" + outFileName + "\",append = TRUE)";
											String lsd2 = "capture.output(result$output[[" + i + "]]$site[[" + j + "]]$stderror.table,file=\"" + outFileName + "\",append = TRUE)";
											
											rEngine.eval(lsd1);
											rEngine.eval(lsd2);
											rEngine.eval(outspace);
											rEngine.eval(outspace);
											
										} else { 
											String warningExceed = "capture.output(cat(\" ERROR: Too many missing values. Cannot perform test for significance of GCA and SCA effects.\n\"),file=\"" + outFileName + "\",append = TRUE)";
											String warningExceed2 = "capture.output(cat(\"        and estimation of genetic variance components.\n\n\"),file=\"" + outFileName + "\",append = TRUE)";
											rEngine.eval(warningExceed);
											rEngine.eval(warningExceed2);
											rEngine.eval(outspace);
										}// end of else (if (responseRate < 0.90))
										
									} else {
										rEngine.eval(outspace);
										rEngine.eval(outspace);
										
									} // end of else (if (pvalue < alpha))
								} else {
									if (design == "CRD") {
										String meansNotComplete = "capture.output(cat(\"\n\n***\nERROR: At least one required P1xP2 combination is missing. Cannot perform the succeeding analyses.\n***\n\n\n\"),file=\"" + outFileName + "\",append = TRUE)";
										rEngine.eval(meansNotComplete);
									} else {
										rEngine.eval(outspace);
										rEngine.eval(outspace);
										rEngine.eval(outspace);
									}
								}
								
							} // end of else (if (exceededCheck.equals("YES")))
	
						}
						
					} // end of for (int m = 0; m < envLevelsLength; m++)
	
				} // end of for (int k = 0; k < respvar.length; k++)
				rEngine.eval(outspace);
				rEngine.eval(sep2);
			} // end of else (if (runSuccess != null && runSuccess.equals("try-error"))
	
		} // end of else (if (runSuccessData != null && runSuccessData.equals("notRun")))
		
		rEngineEnd();
	}
	
	@Override
	public void doDiallel1METest(String dataFileName, String outFileName, String resultFolderPath, String design, String[] respvar, String p1, String p2, 
			String rep, String block, String row, String column, String cross, String individual, String environment, String alpha){

		String respvarVector= inputTransform.createRVector(respvar);
		
		//defining the R statements
		String readData = "dataRead <- read.csv(\"" + dataFileName + "\", header = TRUE, na.strings = c(\"NA\",\".\",\" \",\"\"), blank.lines.skip=TRUE, sep = \",\")";
		String sinkIn = "sink(\"" + outFileName + "\")";
		
		String usedData = "cat(\"\nDATA FILE: " + dataFileName + "\n\")";
		
		String funcDiallel1TestME=null;
		if (design == "CRD") {
			funcDiallel1TestME = "result<-try(diallel1TestME(\"CRD\", \"dataRead\", " + respvarVector + ", \"" + p1 + "\", \"" + p2 + 
						"\", rep = NULL, block = NULL, row = NULL, column = NULL, cross = " + cross + ", individual = NULL, environment = \"" + environment + "\", alpha = " + alpha + "), silent = TRUE)";
		}
		if (design == "RCB") {
			funcDiallel1TestME = "result<-try(diallel1TestME(\"RCB\", \"dataRead\", " + respvarVector + ", \"" + p1 + "\", \"" + p2 + "\", rep = NULL, " +
						"block = \"" + block + "\", row = NULL, column = NULL, cross = " + cross + ", individual = NULL, environment = \"" + environment + "\", alpha = " + alpha + "), silent = TRUE)";;
		}
		if (design == "Alpha") {
			funcDiallel1TestME = "result<-try(diallel1TestME(\"Alpha\", \"dataRead\", " + respvarVector + ", \"" + p1 + "\", \"" + p2 + "\", rep = \"" 
						+ rep + "\", block = \"" + block + "\", row = NULL, column = NULL, cross = " + cross + ", individual = NULL, environment = \"" + environment + "\", alpha = " + alpha + "), silent = TRUE)";;
		}
		if (design == "RowColumn") {
			funcDiallel1TestME = "result<-try(diallel1TestME(\"RowColumn\", \"dataRead\", " + respvarVector + ", \"" + p1 + "\", \"" + p2 + "\", rep = \"" 
						+ rep + "\", block = NULL, row = \"" + row + "\", column = \"" + column + "\", cross = " + cross + ", individual = NULL, environment = \"" + environment + "\", alpha = " + alpha + "), silent = TRUE)";
		}
		
		String checkError = "if (class(result) == \"try-error\") {\n";
		checkError = checkError + "    msg <- trimStrings(strsplit(result, \":\")[[1]])\n";
		checkError = checkError + "    msg <- trimStrings(paste(strsplit(msg, \"\\n\")[[length(msg)]], collapse = \" \"))\n";
		checkError = checkError + "    msg <- gsub(\"\\\"\", \"\", msg)\n";
		checkError = checkError + "    cat(\"\n*** \nERROR in diallel1TestME function:\\n  \",msg, \"\n***\", sep = \"\")\n";
		checkError = checkError + "}";		
		String sinkOut = "sink()";
		
		System.out.println(readData);
		System.out.println(sinkIn);
		System.out.println(funcDiallel1TestME);
		System.out.println(checkError);
		System.out.println(sinkOut);
		
		//R statements passed on to the R engine
		rEngine.eval(readData);
		rEngine.eval(sinkIn);
		rEngine.eval(usedData);
		rEngine.eval(funcDiallel1TestME);
		rEngine.eval(checkError);
		rEngine.eval(sinkOut);
		
		//save matrix of means and effects to csv files
		String runSuccess = rEngine.eval("class(result)").asString();
		if (runSuccess != null && runSuccess.equals("try-error")) {	
			System.out.println("results of diallel1TestME: error");
		} else {
			for (int k = 0; k < respvar.length; k++) {
				int i = k + 1; // 1-relative index;
				
				String estimatedMissing1 = "result$output[[" + i + "]]$canEstimateMissing";
				String estimatedMissing2 = rEngine.eval(estimatedMissing1).asString();
				
				if (estimatedMissing2.equals("TRUE")) {
					//save matrix of means to a file
					String meansFileName2 = "meansFileName2 <- paste(\"" + resultFolderPath + "\",\"matrixMeans_\", \"" + respvar[k] + "\", \".csv\", sep=\"\")";
					String funcSavePredMeansCsv = "saveDataB1 <- try(write.table(result$output[[" + i + "]]$means.matrix,file = meansFileName2 ,sep=\",\",col.names=NA, row.names=TRUE), silent=TRUE)";
					rEngine.eval(meansFileName2);
					rEngine.eval(funcSavePredMeansCsv);
					
					System.out.println(meansFileName2);
					System.out.println(funcSavePredMeansCsv);
					
					String runSuccessSavePredMeans = rEngine.eval("class(saveDataB1)").asString();
					if (runSuccessSavePredMeans != null && runSuccessSavePredMeans.equals("try-error")) {	
						System.out.println("save matrix of means: error");
					}
					
					String effectsFileName2 = "effectsFileName2 <- paste(\"" + resultFolderPath + "\",\"matrixEffects_\", \"" + respvar[k] + "\", \".csv\", sep=\"\")";
					String funcSaveEffectsCsv = "saveDataB2 <- try(write.table(result$output[[" + i + "]]$gcasca.matrix,file = effectsFileName2 ,sep=\",\",col.names=NA, row.names=TRUE), silent=TRUE)";
					rEngine.eval(effectsFileName2);
					rEngine.eval(funcSaveEffectsCsv);
					
					System.out.println(effectsFileName2);
					System.out.println(funcSaveEffectsCsv);
					
					String runSuccessSaveEffects = rEngine.eval("class(saveDataB2)").asString();
					if (runSuccessSaveEffects != null && runSuccessSaveEffects.equals("try-error")) {	
						System.out.println("save matrix of effects: error");
					}
				}
			} //end of for (int k = 0; k < respvar.length; k++)
		}
		
		rEngineEnd();
	}
	
		
	@Override
	public void doDiallel2Test(String dataFileName, String outFileName, String resultFolderPath, String design, String[] respvar, String p1, String p2, 
			String rep, String block, String row, String column, String cross, String individual, String environment, String alpha){

		String respvarVector= inputTransform.createRVector(respvar);
		
		//OLD VERSION
//		//defining the R statements
//		String readData = "dataRead <- read.csv(\"" + dataFileName + "\", header = TRUE, na.strings = c(\"NA\",\".\",\" \",\"\"), blank.lines.skip=TRUE, sep = \",\")";
//		String sinkIn = "sink(\"" + outFileName + "\")";
//		
//		String usedData = "cat(\"\nDATA FILE: " + dataFileName + "\n\")";
//		
//		String funcDiallel2Test=null;
//		if (design == "CRD") {
//			if (environment == "NULL") {
//				funcDiallel2Test = "result<-try(diallel2Test(\"CRD\", \"dataRead\", " + respvarVector + ", \"" + p1 + "\", \"" + p2 + 
//						"\", rep = NULL, block = NULL, row = NULL, column = NULL, cross = " + cross + ", individual = NULL, environment = " + environment + ", alpha = " + alpha + "), silent = TRUE)";
//			} else {
//				funcDiallel2Test = "result<-try(diallel2Test(\"CRD\", \"dataRead\", " + respvarVector + ", \"" + p1 + "\", \"" + p2 + 
//						"\", rep = NULL, block = NULL, row = NULL, column = NULL, cross = " + cross + ", individual = NULL, environment = \"" + environment + "\", alpha = " + alpha + "), silent = TRUE)";
//			}
//		}
//		if (design == "RCB") {
//			if (environment == "NULL") {
//				funcDiallel2Test = "result<-try(diallel2Test(\"RCB\", \"dataRead\", " + respvarVector + ", \"" + p1 + "\", \"" + p2 + "\", rep = NULL, " +
//						"block = \"" + block + "\", row = NULL, column = NULL, cross = " + cross + ", individual = NULL, environment = " + environment + ", alpha = " + alpha + "), silent = TRUE)";
//			} else {
//				funcDiallel2Test = "result<-try(diallel2Test(\"RCB\", \"dataRead\", " + respvarVector + ", \"" + p1 + "\", \"" + p2 + "\", rep = NULL, " +
//						"block = \"" + block + "\", row = NULL, column = NULL, cross = " + cross + ", individual = NULL, environment = \"" + environment + "\", alpha = " + alpha + "), silent = TRUE)";;
//			}
//		}
//		if (design == "Alpha") {
//			if (environment == "NULL") {
//				funcDiallel2Test = "result<-try(diallel2Test(\"Alpha\", \"dataRead\", " + respvarVector + ", \"" + p1 + "\", \"" + p2 + "\", rep = \"" 
//						+ rep + "\", block = \"" + block + "\", row = NULL, column = NULL, cross = " + cross + ", individual = NULL, environment = " + environment + ", alpha = " + alpha + "), silent = TRUE)";
//			} else {
//				funcDiallel2Test = "result<-try(diallel2Test(\"Alpha\", \"dataRead\", " + respvarVector + ", \"" + p1 + "\", \"" + p2 + "\", rep = \"" 
//						+ rep + "\", block = \"" + block + "\", row = NULL, column = NULL, cross = " + cross + ", individual = NULL, environment = \"" + environment + "\", alpha = " + alpha + "), silent = TRUE)";;
//			}
//		}
//		if (design == "RowColumn") {
//			if (environment == "NULL") {
//				funcDiallel2Test = "result<-try(diallel2Test(\"RowColumn\", \"dataRead\", " + respvarVector + ", \"" + p1 + "\", \"" + p2 + "\", rep = \"" 
//						+ rep + "\", block = NULL, row = \"" + row + "\", column = \"" + column + "\", cross = " + cross + ", individual = NULL, environment = " + environment + ", alpha = " + alpha + "), silent = TRUE)";
//			} else {
//				funcDiallel2Test = "result<-try(diallel2Test(\"RowColumn\", \"dataRead\", " + respvarVector + ", \"" + p1 + "\", \"" + p2 + "\", rep = \"" 
//						+ rep + "\", block = NULL, row = \"" + row + "\", column = \"" + column + "\", cross = " + cross + ", individual = NULL, environment = \"" + environment + "\", alpha = " + alpha + "), silent = TRUE)";
//			}
//		}
//		
//		String checkError = "if (class(result) == \"try-error\") {\n";
//		checkError = checkError + "    msg <- trimStrings(strsplit(result, \":\")[[1]])\n";
//		checkError = checkError + "    msg <- trimStrings(paste(strsplit(msg, \"\\n\")[[length(msg)]], collapse = \" \"))\n";
//		checkError = checkError + "    msg <- gsub(\"\\\"\", \"\", msg)\n";
//		checkError = checkError + "    cat(\"\n*** \nERROR in diallel2Test function:\\n  \",msg, \"\n***\", sep = \"\")\n";
//		checkError = checkError + "}";	
//		String sinkOut = "sink()";
//		
//		System.out.println(readData);
//		System.out.println(sinkIn);
//		System.out.println(funcDiallel2Test);
//		System.out.println(checkError);
//		System.out.println(sinkOut);
//		
//		//R statements passed on to the R engine
//		rEngine.eval(readData);
//		rEngine.eval(sinkIn);
//		rEngine.eval(usedData);
//		rEngine.eval(funcDiallel2Test);
//		rEngine.eval(checkError);
//		rEngine.eval(sinkOut);
//		rEngineEnd();
		
		//NEW APPROACH
		//read data
		String readData = "dataRead <- read.csv(\"" + dataFileName + "\", header = TRUE, na.strings = c(\"NA\",\".\",\" \",\"\"), blank.lines.skip=TRUE, sep = \",\")";
		System.out.println(readData);
		rEngine.eval(readData);
		String runSuccessData = rEngine.eval("data").asString();
		
		if (runSuccessData != null && runSuccessData.equals("notRun")) {	
			System.out.println("error");
			rEngine.eval("capture.output(cat(\"\n***Error reading data.***\n\"),file=\"" + outFileName + "\",append = FALSE)"); 
		} else {
			//create titles
			String designName = null;
			if (design == "CRD") {
				designName="CRD";
			}
			if (design == "RCB") { 
				designName="RCB";
			}
			if (design == "Alpha") { 
				designName="ALPHA-LATTICE";
			}
			if (design == "RowColumn") { 
				designName="ROW-COLUMN";
			}
			
			String usedData = "capture.output(cat(\"\nDATA FILE: " + dataFileName + "\n\",file=\"" + outFileName + "\"))";
			String usedDesign = null;
			if (cross == "TRUE") {
				usedDesign = "capture.output(cat(\"\nDIALLEL ANALYSIS: GRIFFING METHOD II IN " + designName + " (CROSS)\n\n\"),file=\"" + outFileName + "\",append = TRUE)";
			} else {
				usedDesign = "capture.output(cat(\"\nDIALLEL ANALYSIS: GRIFFING METHOD II IN " + designName + " (SELF)\n\n\"),file=\"" + outFileName + "\",append = TRUE)";
			}
			
			String sep = "capture.output(cat(\"------------------------------\"),file=\"" + outFileName + "\",append = TRUE)";
			String sep2 = "capture.output(cat(\"==============================\n\"),file=\"" + outFileName + "\",append = TRUE)";
			String outspace = "capture.output(cat(\"\n\"),file=\"" + outFileName + "\",append = TRUE)";
	
			rEngine.eval(usedData);
			rEngine.eval(usedDesign);
			
			String funcDiallel2Test=null;
			if (design == "CRD") {
				if (environment == "NULL") {
					funcDiallel2Test = "result<-try(diallel2Test(\"CRD\", \"dataRead\", " + respvarVector + ", \"" + p1 + "\", \"" + p2 + 
							"\", rep = NULL, block = NULL, row = NULL, column = NULL, cross = " + cross + ", individual = NULL, environment = " + environment + ", alpha = " + alpha + "), silent = TRUE)";
				} else {
					funcDiallel2Test = "result<-try(diallel2Test(\"CRD\", \"dataRead\", " + respvarVector + ", \"" + p1 + "\", \"" + p2 + 
							"\", rep = NULL, block = NULL, row = NULL, column = NULL, cross = " + cross + ", individual = NULL, environment = \"" + environment + "\", alpha = " + alpha + "), silent = TRUE)";
				}
			}
			if (design == "RCB") {
				if (environment == "NULL") {
					funcDiallel2Test = "result<-try(diallel2Test(\"RCB\", \"dataRead\", " + respvarVector + ", \"" + p1 + "\", \"" + p2 + "\", rep = NULL, " +
							"block = \"" + block + "\", row = NULL, column = NULL, cross = " + cross + ", individual = NULL, environment = " + environment + ", alpha = " + alpha + "), silent = TRUE)";
				} else {
					funcDiallel2Test = "result<-try(diallel2Test(\"RCB\", \"dataRead\", " + respvarVector + ", \"" + p1 + "\", \"" + p2 + "\", rep = NULL, " +
							"block = \"" + block + "\", row = NULL, column = NULL, cross = " + cross + ", individual = NULL, environment = \"" + environment + "\", alpha = " + alpha + "), silent = TRUE)";;
				}
			}
			if (design == "Alpha") {
				if (environment == "NULL") {
					funcDiallel2Test = "result<-try(diallel2Test(\"Alpha\", \"dataRead\", " + respvarVector + ", \"" + p1 + "\", \"" + p2 + "\", rep = \"" 
							+ rep + "\", block = \"" + block + "\", row = NULL, column = NULL, cross = " + cross + ", individual = NULL, environment = " + environment + ", alpha = " + alpha + "), silent = TRUE)";
				} else {
					funcDiallel2Test = "result<-try(diallel2Test(\"Alpha\", \"dataRead\", " + respvarVector + ", \"" + p1 + "\", \"" + p2 + "\", rep = \"" 
							+ rep + "\", block = \"" + block + "\", row = NULL, column = NULL, cross = " + cross + ", individual = NULL, environment = \"" + environment + "\", alpha = " + alpha + "), silent = TRUE)";;
				}
			}
			if (design == "RowColumn") {
				if (environment == "NULL") {
					funcDiallel2Test = "result<-try(diallel2Test(\"RowColumn\", \"dataRead\", " + respvarVector + ", \"" + p1 + "\", \"" + p2 + "\", rep = \"" 
							+ rep + "\", block = NULL, row = \"" + row + "\", column = \"" + column + "\", cross = " + cross + ", individual = NULL, environment = " + environment + ", alpha = " + alpha + "), silent = TRUE)";
				} else {
					funcDiallel2Test = "result<-try(diallel2Test(\"RowColumn\", \"dataRead\", " + respvarVector + ", \"" + p1 + "\", \"" + p2 + "\", rep = \"" 
							+ rep + "\", block = NULL, row = \"" + row + "\", column = \"" + column + "\", cross = " + cross + ", individual = NULL, environment = \"" + environment + "\", alpha = " + alpha + "), silent = TRUE)";
				}
			}
			
			rEngine.eval(funcDiallel2Test);
			System.out.println(funcDiallel2Test);
	
			String runSuccess = rEngine.eval("class(result)").asString();
			if (runSuccess != null && runSuccess.equals("try-error")) {	
				System.out.println("diallel2Test: error");
				String checkError = "msg <- trimStrings(strsplit(result, \":\")[[1]])";
				String checkError2 = "msg <- trimStrings(paste(strsplit(msg, \"\\n\")[[length(msg)]], collapse = \" \"))";
				String checkError3 ="msg <- gsub(\"\\\"\", \"\", msg)";
				String checkError4 = "capture.output(cat(\"*** \nERROR in diallel2Test function:\\n  \",msg, \"\n***\n\n\", sep = \"\"), file=\"" + outFileName + "\",append = TRUE)";
				rEngine.eval(checkError);
				rEngine.eval(checkError2);
				rEngine.eval(checkError3);
				rEngine.eval(checkError4);
	
			} else {
				for (int k = 0; k < respvar.length; k++) {
					int i = k + 1; // 1-relative index;
					
					String respVarHead = "capture.output(cat(\"\nRESPONSE VARIABLE: " + respvar[k] + "\n\"),file=\"" + outFileName + "\",append = TRUE)";
					rEngine.eval(sep);
					rEngine.eval(respVarHead);
					rEngine.eval(sep);
					rEngine.eval(outspace);
					
					String getEnvLevels = "length(result$output[[" + i + "]]$site)";
					int envLevelsLength = rEngine.eval(getEnvLevels).asInt();
	//							System.out.println("envLevelsLength: " + envLevelsLength);
					
					for (int m = 0; m < envLevelsLength; m++) { // no of envts or sites
						int j = m + 1; // 1-relative index;
						if (environment != "NULL") {
							String envtHead = "capture.output(cat(\"\nANALYSIS FOR: "+ environment + "\", \" = \" ,result$output[[" + i	+ "]]$site[[" + j + "]]$env,\"\n\"),file=\""+ outFileName + "\",append = TRUE)";
							rEngine.eval(sep);
							rEngine.eval(envtHead);
							rEngine.eval(sep);
							rEngine.eval(outspace);
						} 
						
						//check if max of lengthPerCross is equal to nlevelsRep 
						int maxLengthPerCross =rEngine.eval("result$output[[" + i + "]]$site[[" + j + "]]$maxLengthPerCross").asInt();
						int nLevelsRep =rEngine.eval("result$output[[" + i + "]]$site[[" + j + "]]$nlevelsRep").asInt();
						
						if (maxLengthPerCross > nLevelsRep) {
							String errorMessage1 = "capture.output(cat(\"\nERROR: \",result$output[[" + i	+ "]]$site[[" + j + "]]$blockLabelError),file=\""+ outFileName + "\",append = TRUE)";
							String errorMessage2 = "capture.output(cat(\"\n       \",result$output[[" + i	+ "]]$site[[" + j + "]]$blockLabelError2),file=\""+ outFileName + "\",append = TRUE)";
						
							rEngine.eval(errorMessage1);
							rEngine.eval(errorMessage2);
							rEngine.eval(outspace);
							rEngine.eval(outspace);
							rEngine.eval(outspace);
							
							break;
						}
						
						String manyNA1 = "result$output[["	+ i	+ "]]$site[[" + j + "]]$tooManyNAWarning";
						String manyNA2 = rEngine.eval(manyNA1).asString();
				
	//								System.out.println("exceededCheck: " + exceededCheck);
						
						if (manyNA2.equals("YES")) {
							String warningExceed = "capture.output(cat(\"\nToo many missing observations. Cannot proceed with the analysis.\n\n\n\"),file=\"" + outFileName + "\",append = TRUE)";
							rEngine.eval(warningExceed);
						} else {
							//display data summary
							String trialSumHead = "capture.output(cat(\"\nDATA SUMMARY:\n\n\"),file=\"" + outFileName + "\",append = TRUE)";
							String trialSumTable = "capture.output(result$output[[" + i + "]]$site[[" + j + "]]$funcTrialSum,file=\"" + outFileName + "\",append = TRUE)";
							String trialObsRead = "capture.output(cat(\"Number of observations read: \", result$output[["	+ i	+ "]]$site[[" + j + "]]$obsread[[1]],\"\n\"),file=\"" + outFileName + "\",append = TRUE)";
							String trialObsUsed = "capture.output(cat(\"Number of observations used: \", result$output[["	+ i	+ "]]$site[[" + j + "]]$obsused[[1]],\"\n\"),file=\""	+ outFileName + "\",append = TRUE)";
	
							rEngine.eval(trialSumHead);
							rEngine.eval(trialSumTable);
							rEngine.eval(outspace);
							rEngine.eval(trialObsRead);
							rEngine.eval(trialObsUsed);
	//									rEngine.eval(outspace);
							
							String exceeded = "result$output[["	+ i	+ "]]$site[[" + j + "]]$exceededWarning";
							String exceededCheck = rEngine.eval(exceeded).asString();
							rEngine.eval(exceeded);
							rEngine.eval(exceededCheck);
					
	//									System.out.println("exceededCheck: " + exceededCheck);
							
							if (exceededCheck.equals("YES")) {
								String warningExceed = "capture.output(cat(\"\n\n***\nERROR: The number of observations read in the data exceeds the size of a balanced data.\n       Please check if the column for block/replicate is properly labeled.\n***\n\n\"),file=\"" + outFileName + "\",append = TRUE)";
								rEngine.eval(warningExceed);
							} else {
								double responseRate =rEngine.eval("result$output[[" + i + "]]$site[[" + j + "]]$responseRate").asDouble();
								
								//display anova table if design is RCB
								if (design == "RCB") {
									String anovaHead = "capture.output(cat(\"\n\nANOVA TABLE FOR THE EXPERIMENT:\n\n\"),file=\"" + outFileName + "\",append = TRUE)";
							        rEngine.eval(anovaHead);
							        
						        	if (responseRate < 0.90) {
										String warningExceed = "capture.output(cat(\" ERROR: Too many missing values. Cannot perform ANOVA for balanced data.\n\"),file=\"" + outFileName + "\",append = TRUE)";
										rEngine.eval(warningExceed);
										
									} else {
										String ttcAnova1 = "capture.output(result$output[[" + i + "]]$site[[" + j + "]]$diallel1.anova,file=\"" + outFileName + "\",append = TRUE)";
										String ttcAnova2 = "capture.output(cat(\"-------\n\"),file=\"" + outFileName + "\",append = TRUE)";
										String ttcAnova3 = "capture.output(cat(\"REMARK:\", result$output[["	+ i	+ "]]$site[[" + j + "]]$anovaRemark1,\"\n\"),file=\""	+ outFileName + "\",append = TRUE)";
										
										rEngine.eval(ttcAnova1);
										rEngine.eval(ttcAnova2);
										rEngine.eval(ttcAnova3);
									}
						        }
								
								//test on significance of cross effect
								String crossEffect1 = "capture.output(cat(\"\n\nTESTING FOR THE SIGNIFICANCE OF CROSS EFFECT: (Crosses = " + p1 + ":" + p2 + ")\n\"),file=\"" + outFileName + "\",append = TRUE)";
								rEngine.eval(crossEffect1);
								Double pvalue=0.00;
								
								if (design == "CRD") {
									String crossEffect2 = "capture.output(cat(\"\nModel:\", result$output[["	+ i	+ "]]$site[[" + j + "]]$formula1,\"\n\n\"),file=\""	+ outFileName + "\",append = TRUE)";
									String crossEffect3 = "capture.output(result$output[[" + i + "]]$site[[" + j + "]]$genoEffect.anova,file=\"" + outFileName + "\",append = TRUE)";
								
									rEngine.eval(crossEffect2);
									rEngine.eval(crossEffect3);
									
									pvalue = rEngine.eval("result$output[[" + i + "]]$site[[" + j + "]]$pValue").asDouble();
									System.out.println("pvalue: " + pvalue);
									
									//display matrix of means
									String matrixMeans1 = "capture.output(cat(\"\n\nMATRIX OF MEANS:\n\n\"),file=\"" + outFileName + "\",append = TRUE)";
									String matrixMeans2 = "capture.output(result$output[[" + i + "]]$site[[" + j + "]]$means.matrix,file=\"" + outFileName + "\",append = TRUE)";
									rEngine.eval(matrixMeans1);
									rEngine.eval(matrixMeans2);
									
//									String estimatedMissing1 = "result$output[["	+ i	+ "]]$site[[" + j + "]]$estimatedMissing";
//									String estimatedMissing2 = rEngine.eval(estimatedMissing1).asString();
									
//									if (estimatedMissing2.equals("TRUE")) {
										String anovaRemark1 = "capture.output(cat(\"-------\n\"),file=\"" + outFileName + "\",append = TRUE)";
										String anovaRemark2 = "capture.output(cat(\"REMARK:\", result$output[["	+ i	+ "]]$site[[" + j + "]]$anovaRemark2,\"\n\"),file=\""	+ outFileName + "\",append = TRUE)";
										
										rEngine.eval(anovaRemark1);
										rEngine.eval(anovaRemark2);
//									}
									
									//save matrix of means to a file
									String meansFileName2 = null;
									if (environment != "NULL") {
										meansFileName2 = "meansFileName2 <- paste(\"" + resultFolderPath + "\",\"matrixMeans_\", \"" + respvar[k] + "\", \"_\", result$output[[" + i + "]]$site[[" + j + "]]$env,\".csv\", sep=\"\")";
									} else {
										meansFileName2 = "meansFileName2 <- paste(\"" + resultFolderPath + "\",\"matrixMeans_\", \"" + respvar[k] + "\", \".csv\", sep=\"\")";
									}
									
									System.out.println(meansFileName2);
									
									String funcSavePredMeansCsv = "saveDataB1 <- try(write.table(result$output[[" + i + "]]$site[[" + j + "]]$means.matrix,file = meansFileName2 ,sep=\",\",col.names=NA, row.names=TRUE), silent=TRUE)";
									rEngine.eval(meansFileName2);
									rEngine.eval(funcSavePredMeansCsv);
									
									String runSuccessSavePredMeans = rEngine.eval("class(saveDataB1)").asString();
									if (runSuccessSavePredMeans != null && runSuccessSavePredMeans.equals("try-error")) {	
										System.out.println("save matrix of means: error");
										String checkError = "msg <- trimStrings(strsplit(saveDataB1, \":\")[[1]])";
										String checkError2 = "msg <- trimStrings(paste(strsplit(msg, \"\\n\")[[length(msg)]], collapse = \" \"))";
										String checkError3 ="msg <- gsub(\"\\\"\", \"\", msg)";
										String checkError4 = "capture.output(cat(\"\n***\nERROR in saving matrix of means to a file:\\n  \",msg, \"\n***\n\", sep = \"\"), file=\"" + outFileName + "\",append = TRUE)";
										rEngine.eval(checkError);
										rEngine.eval(checkError2);
										rEngine.eval(checkError3);
										rEngine.eval(checkError4);
									}
									
								} else {
									if (responseRate < 0.90) {
										String warningExceed = "capture.output(cat(\"\n ERROR: Too many missing values. Cannot perform the analysis.\n\"),file=\"" + outFileName + "\",append = TRUE)";
										rEngine.eval(warningExceed);
									} else {
										String crossEffect2 = "capture.output(cat(\"\nModel:\", result$output[["	+ i	+ "]]$site[[" + j + "]]$formula1,\"\n\"),file=\""	+ outFileName + "\",append = TRUE)";
										rEngine.eval(crossEffect2);
										
										String outAnovaTable2 = "library(lmerTest)";
										String outAnovaTable3 = "model1b <- lmer(formula(result$output[[" + i + "]]$site[[" + j + "]]$formula1), data = result$output[[" + i + "]]$site[[" + j + "]]$data, REML = T)";
										String outAnovaTable4 = "a.table <- anova(model1b)";
										String outAnovaTable5 = "pvalue <- formatC(as.numeric(format(a.table[1,6], scientific=FALSE)), format=\"f\")";
										String outAnovaTable6 = "a.table<-cbind(round(a.table[,1:5], digits=4),pvalue)";
										String outAnovaTable7 = "colnames(a.table)<-c(\"Df\", \"Sum Sq\", \"Mean Sq\", \"F value\", \"Denom\", \"Pr(>F)\")";
										String outAnovaTable8 = "capture.output(cat(\"Analysis of Variance Table with Satterthwaite Denominator Df\n\"),file=\"" + outFileName + "\",append = TRUE)";
										String outAnovaTable9 = "capture.output(a.table,file=\"" + outFileName + "\",append = TRUE)";
										String outAnovaTable10 = "detach(\"package:lmerTest\")";
										
										rEngine.eval(outAnovaTable2);
										rEngine.eval(outAnovaTable3);
										rEngine.eval(outAnovaTable4);
		
										pvalue = rEngine.eval("a.table[1,6]").asDouble();
										System.out.println("pvalue: " + pvalue);
										
										rEngine.eval(outAnovaTable5);
										rEngine.eval(outAnovaTable6);
										rEngine.eval(outAnovaTable7);
										rEngine.eval(outspace);
										rEngine.eval(outAnovaTable8);
										rEngine.eval(outAnovaTable9);
										rEngine.eval(outAnovaTable10);
										
										//display matrix of means
										String matrixMeans1 = "capture.output(cat(\"\n\nMATRIX OF MEANS:\n\n\"),file=\"" + outFileName + "\",append = TRUE)";
										String matrixMeans2 = "capture.output(result$output[[" + i + "]]$site[[" + j + "]]$means.matrix,file=\"" + outFileName + "\",append = TRUE)";
										rEngine.eval(matrixMeans1);
										rEngine.eval(matrixMeans2);
										
//										String estimatedMissing1 = "result$output[["	+ i	+ "]]$site[[" + j + "]]$estimatedMissing";
//										String estimatedMissing2 = rEngine.eval(estimatedMissing1).asString();
										
//										if (estimatedMissing2.equals("TRUE")) {
											String anovaRemark1 = "capture.output(cat(\"-------\n\"),file=\"" + outFileName + "\",append = TRUE)";
											String anovaRemark2 = "capture.output(cat(\"REMARK:\", result$output[["	+ i	+ "]]$site[[" + j + "]]$anovaRemark2,\"\n\"),file=\""	+ outFileName + "\",append = TRUE)";
											
											rEngine.eval(anovaRemark1);
											rEngine.eval(anovaRemark2);
//										}
										
										//save matrix of means to a file
										String meansFileName2 = null;
										if (environment != "NULL") {
											meansFileName2 = "meansFileName2 <- paste(\"" + resultFolderPath + "\",\"matrixMeans_\", \"" + respvar[k] + "\", \"_\", result$output[[" + i + "]]$site[[" + j + "]]$env,\".csv\", sep=\"\")";
										} else {
											meansFileName2 = "meansFileName2 <- paste(\"" + resultFolderPath + "\",\"matrixMeans_\", \"" + respvar[k] + "\", \".csv\", sep=\"\")";
										}
										
										System.out.println(meansFileName2);
										
										String funcSavePredMeansCsv = "saveDataB1 <- try(write.table(result$output[[" + i + "]]$site[[" + j + "]]$means.matrix,file = meansFileName2 ,sep=\",\",col.names=NA, row.names=TRUE), silent=TRUE)";
										rEngine.eval(meansFileName2);
										rEngine.eval(funcSavePredMeansCsv);
										
										String runSuccessSavePredMeans = rEngine.eval("class(saveDataB1)").asString();
										if (runSuccessSavePredMeans != null && runSuccessSavePredMeans.equals("try-error")) {	
											System.out.println("save matrix of means: error");
											String checkError = "msg <- trimStrings(strsplit(saveDataB1, \":\")[[1]])";
											String checkError2 = "msg <- trimStrings(paste(strsplit(msg, \"\\n\")[[length(msg)]], collapse = \" \"))";
											String checkError3 ="msg <- gsub(\"\\\"\", \"\", msg)";
											String checkError4 = "capture.output(cat(\"\n***\nERROR in saving matrix of means to a file:\\n  \",msg, \"\n***\n\", sep = \"\"), file=\"" + outFileName + "\",append = TRUE)";
											rEngine.eval(checkError);
											rEngine.eval(checkError2);
											rEngine.eval(checkError3);
											rEngine.eval(checkError4);
										}
									}
								}
								
								String meansComplete1 = "result$output[["	+ i	+ "]]$site[[" + j + "]]$meansComplete";
								String meansComplete2 = rEngine.eval(meansComplete1).asString();
								
								if (meansComplete2.equals("TRUE")) {
									//if cross effect is significant,
									double alphaNew = Double.parseDouble(alpha);
									
									if (pvalue < alphaNew) {
										String gcaAnovaHead = "capture.output(cat(\"\n\nANALYSIS OF VARIANCE:\n\n\"),file=\"" + outFileName + "\",append = TRUE)";
										rEngine.eval(gcaAnovaHead);
											
										if (responseRate >= 0.90 || design=="CRD") {
											//GCA and SCA anova table
											String gcaAnova1 = "capture.output(result$output[[" + i + "]]$site[[" + j + "]]$gcasca.anova,file=\"" + outFileName + "\",append = TRUE)";
											rEngine.eval(gcaAnova1);
											
											//estimates of effects
											String effects1 = "capture.output(cat(\"\n\nGENERAL COMBINING ABILITY EFFECTS (diagonal), SPECIFIC COMBINING\n\"),file=\"" + outFileName + "\",append = TRUE)";
											String effects2 = "capture.output(cat(\"ABILITY EFFECTS (above diagonal) AND RECIPROCAL EFFECTS (below diagonal):\n\n\"),file=\"" + outFileName + "\",append = TRUE)";
											String effects3 = "capture.output(result$output[[" + i + "]]$site[[" + j + "]]$gcasca.matrix,file=\"" + outFileName + "\",append = TRUE)";
											
											rEngine.eval(effects1);
											rEngine.eval(effects2);
											rEngine.eval(effects3);
											
											//save matrix of effects to a file
											String effectsFileName2 = null;
											if (environment != "NULL") {
												effectsFileName2 = "effectsFileName2 <- paste(\"" + resultFolderPath + "\",\"matrixEffects_\", \"" + respvar[k] + "\", \"_\", result$output[[" + i + "]]$site[[" + j + "]]$env,\".csv\", sep=\"\")";
											} else {
												effectsFileName2 = "effectsFileName2 <- paste(\"" + resultFolderPath + "\",\"matrixEffects_\", \"" + respvar[k] + "\", \".csv\", sep=\"\")";
											}
											
											String funcSaveEffectsCsv = "saveDataB2 <- try(write.table(result$output[[" + i + "]]$site[[" + j + "]]$gcasca.matrix,file = effectsFileName2 ,sep=\",\",col.names=NA, row.names=TRUE), silent=TRUE)";
											rEngine.eval(effectsFileName2);
											rEngine.eval(funcSaveEffectsCsv);
											
											String runSuccessSaveEffects = rEngine.eval("class(saveDataB2)").asString();
											if (runSuccessSaveEffects != null && runSuccessSaveEffects.equals("try-error")) {	
												System.out.println("save matrix of effects: error");
												String checkError = "msg <- trimStrings(strsplit(saveDataB2, \":\")[[1]])";
												String checkError2 = "msg <- trimStrings(paste(strsplit(msg, \"\\n\")[[length(msg)]], collapse = \" \"))";
												String checkError3 ="msg <- gsub(\"\\\"\", \"\", msg)";
												String checkError4 = "capture.output(cat(\"\n***\nERROR in saving matrix of effects to a file:\\n  \",msg, \"\n***\n\", sep = \"\"), file=\"" + outFileName + "\",append = TRUE)";
												rEngine.eval(checkError);
												rEngine.eval(checkError2);
												rEngine.eval(checkError3);
												rEngine.eval(checkError4);
											}
											
											//estimate of variance components
											String varComp1 = "capture.output(cat(\"\n\nESTIMATES OF VARIANCE COMPONENTS:\n\n\"),file=\"" + outFileName + "\",append = TRUE)";
											String varComp2 = "capture.output(result$output[[" + i + "]]$site[[" + j + "]]$var.components,file=\"" + outFileName + "\",append = TRUE)";
											
											rEngine.eval(varComp1);
											rEngine.eval(varComp2);
											
											//genetic variances
											String genVar1 = "capture.output(cat(\"\n\nESTIMATES OF GENETIC VARIANCE COMPONENTS:\n\"),file=\"" + outFileName + "\",append = TRUE)";
											String genVar2 = "capture.output(result$output[[" + i + "]]$site[[" + j + "]]$genvar.components,file=\"" + outFileName + "\",append = TRUE)";
											
											rEngine.eval(genVar1);
											rEngine.eval(genVar2);
											
											//standard errors
											String lsd1 = "capture.output(cat(\"\n\nTABLE OF STANDARD ERRORS AND LSDs:\n\n\"),file=\"" + outFileName + "\",append = TRUE)";
											String lsd2 = "capture.output(result$output[[" + i + "]]$site[[" + j + "]]$stderror.table,file=\"" + outFileName + "\",append = TRUE)";
											
											rEngine.eval(lsd1);
											rEngine.eval(lsd2);
											rEngine.eval(outspace);
											rEngine.eval(outspace);
											
										} else {
											String warningExceed = "capture.output(cat(\" ERROR: Too many missing values. Cannot perform test for significance of GCA and SCA effects.\n\"),file=\"" + outFileName + "\",append = TRUE)";
											String warningExceed2 = "capture.output(cat(\"        and estimation of genetic variance components.\n\n\"),file=\"" + outFileName + "\",append = TRUE)";
											rEngine.eval(warningExceed);
											rEngine.eval(warningExceed2);
											rEngine.eval(outspace);
										}// end of else (if (responseRate >= 0.90))
										
									} else {
										rEngine.eval(outspace);
										rEngine.eval(outspace);
										
									} // end of else (if (pvalue < alpha))
								} else {
									if (design == "CRD") {
										String meansNotComplete = "capture.output(cat(\"\n\n***\nERROR: At least one required P1xP2 combination is missing. Cannot perform the succeeding analyses.\n***\n\n\n\"),file=\"" + outFileName + "\",append = TRUE)";
										rEngine.eval(meansNotComplete);
									} else {
										rEngine.eval(outspace);
										rEngine.eval(outspace);
										rEngine.eval(outspace);
									}
								}
								
							} // end of else (if (exceededCheck.equals("YES")))
	
						}
						
					} // end of for (int m = 0; m < envLevelsLength; m++)
	
				} // end of for (int k = 0; k < respvar.length; k++)
				rEngine.eval(outspace);
				rEngine.eval(sep2);
			} // end of else (if (runSuccess != null && runSuccess.equals("try-error"))
	
		} // end of else (if (runSuccessData != null && runSuccessData.equals("notRun")))
		
		rEngineEnd();
	}
	
	@Override
	public void doDiallel2METest(String dataFileName, String outFileName, String resultFolderPath, String design, String[] respvar, String p1, String p2, 
			String rep, String block, String row, String column, String cross, String individual, String environment, String alpha){

		String respvarVector= inputTransform.createRVector(respvar);
		
		//defining the R statements
		String readData = "dataRead <- read.csv(\"" + dataFileName + "\", header = TRUE, na.strings = c(\"NA\",\".\",\" \",\"\"), blank.lines.skip=TRUE, sep = \",\")";
		String sinkIn = "sink(\"" + outFileName + "\")";
		
		String usedData = "cat(\"\nDATA FILE: " + dataFileName + "\n\")";
		
		String funcDiallel2TestME=null;
		if (design == "CRD") {
			funcDiallel2TestME = "result<-try(diallel2TestME(\"CRD\", \"dataRead\", " + respvarVector + ", \"" + p1 + "\", \"" + p2 + 
						"\", rep = NULL, block = NULL, row = NULL, column = NULL, cross = " + cross + ", individual = NULL, environment = \"" + environment + "\", alpha = " + alpha + "), silent = TRUE)";
		}
		if (design == "RCB") {
			funcDiallel2TestME = "result<-try(diallel2TestME(\"RCB\", \"dataRead\", " + respvarVector + ", \"" + p1 + "\", \"" + p2 + "\", rep = NULL, " +
						"block = \"" + block + "\", row = NULL, column = NULL, cross = " + cross + ", individual = NULL, environment = \"" + environment + "\", alpha = " + alpha + "), silent = TRUE)";;
		}
		if (design == "Alpha") {
			funcDiallel2TestME = "result<-try(diallel2TestME(\"Alpha\", \"dataRead\", " + respvarVector + ", \"" + p1 + "\", \"" + p2 + "\", rep = \"" 
						+ rep + "\", block = \"" + block + "\", row = NULL, column = NULL, cross = " + cross + ", individual = NULL, environment = \"" + environment + "\", alpha = " + alpha + "), silent = TRUE)";;
		}
		if (design == "RowColumn") {
			funcDiallel2TestME = "result<-try(diallel2TestME(\"RowColumn\", \"dataRead\", " + respvarVector + ", \"" + p1 + "\", \"" + p2 + "\", rep = \"" 
						+ rep + "\", block = NULL, row = \"" + row + "\", column = \"" + column + "\", cross = " + cross + ", individual = NULL, environment = \"" + environment + "\", alpha = " + alpha + "), silent = TRUE)";
		}
		
		String checkError = "if (class(result) == \"try-error\") {\n";
		checkError = checkError + "    msg <- trimStrings(strsplit(result, \":\")[[1]])\n";
		checkError = checkError + "    msg <- trimStrings(paste(strsplit(msg, \"\\n\")[[length(msg)]], collapse = \" \"))\n";
		checkError = checkError + "    msg <- gsub(\"\\\"\", \"\", msg)\n";
		checkError = checkError + "    cat(\"\n*** \nERROR in diallel2TestME function:\\n  \",msg, \"\n***\", sep = \"\")\n";
		checkError = checkError + "}";		
		String sinkOut = "sink()";
		
		System.out.println(readData);
		System.out.println(sinkIn);
		System.out.println(funcDiallel2TestME);
		System.out.println(checkError);
		System.out.println(sinkOut);
		
		//R statements passed on to the R engine
		rEngine.eval(readData);
		rEngine.eval(sinkIn);
		rEngine.eval(usedData);
		rEngine.eval(funcDiallel2TestME);
		rEngine.eval(checkError);
		rEngine.eval(sinkOut);
		
		//save matrix of means and effects to csv files
		String runSuccess = rEngine.eval("class(result)").asString();
		if (runSuccess != null && runSuccess.equals("try-error")) {	
			System.out.println("results of diallel2TestME: error");
		} else {
			for (int k = 0; k < respvar.length; k++) {
				int i = k + 1; // 1-relative index;
				
				String estimatedMissing1 = "result$output[[" + i + "]]$canEstimateMissing";
				String estimatedMissing2 = rEngine.eval(estimatedMissing1).asString();
				
				if (estimatedMissing2.equals("TRUE")) {
					//save matrix of means to a file
					String meansFileName2 = "meansFileName2 <- paste(\"" + resultFolderPath + "\",\"matrixMeans_\", \"" + respvar[k] + "\", \".csv\", sep=\"\")";
					String funcSavePredMeansCsv = "saveDataB1 <- try(write.table(result$output[[" + i + "]]$means.matrix,file = meansFileName2 ,sep=\",\",col.names=NA, row.names=TRUE), silent=TRUE)";
					rEngine.eval(meansFileName2);
					rEngine.eval(funcSavePredMeansCsv);
					
					System.out.println(meansFileName2);
					System.out.println(funcSavePredMeansCsv);
					
					String runSuccessSavePredMeans = rEngine.eval("class(saveDataB1)").asString();
					if (runSuccessSavePredMeans != null && runSuccessSavePredMeans.equals("try-error")) {	
						System.out.println("save matrix of means: error");
					}
					
					String effectsFileName2 = "effectsFileName2 <- paste(\"" + resultFolderPath + "\",\"matrixEffects_\", \"" + respvar[k] + "\", \".csv\", sep=\"\")";
					String funcSaveEffectsCsv = "saveDataB2 <- try(write.table(result$output[[" + i + "]]$gcasca.matrix,file = effectsFileName2 ,sep=\",\",col.names=NA, row.names=TRUE), silent=TRUE)";
					rEngine.eval(effectsFileName2);
					rEngine.eval(funcSaveEffectsCsv);
					
					System.out.println(effectsFileName2);
					System.out.println(funcSaveEffectsCsv);
					
					String runSuccessSaveEffects = rEngine.eval("class(saveDataB2)").asString();
					if (runSuccessSaveEffects != null && runSuccessSaveEffects.equals("try-error")) {	
						System.out.println("save matrix of effects: error");
					}
				}
			} //end of for (int k = 0; k < respvar.length; k++)
		}
		
		rEngineEnd();
	}
	
		
	@Override
	public void doDiallel3Test(String dataFileName, String outFileName, String resultFolderPath, String design, String[] respvar, String p1, String p2, 
			String rep, String block, String row, String column, String cross, String individual, String environment, String alpha){

		String respvarVector= inputTransform.createRVector(respvar);
		
		//OLD APPROACH
//		//defining the R statements
//		String readData = "dataRead <- read.csv(\"" + dataFileName + "\", header = TRUE, na.strings = c(\"NA\",\".\",\" \",\"\"), blank.lines.skip=TRUE, sep = \",\")";
//		String sinkIn = "sink(\"" + outFileName + "\")";
//		
//		String usedData = "cat(\"\nDATA FILE: " + dataFileName + "\n\")";
//		
//		String funcDiallel3Test=null;
//		if (design == "CRD") {
//			if (environment == "NULL") {
//				funcDiallel3Test = "result<-try(diallel3Test(\"CRD\", \"dataRead\", " + respvarVector + ", \"" + p1 + "\", \"" + p2 + 
//						"\", rep = NULL, block = NULL, row = NULL, column = NULL, cross = " + cross + ", individual = NULL, environment = " + environment + ", alpha = " + alpha + "), silent = TRUE)";
//			} else {
//				funcDiallel3Test = "result<-try(diallel3Test(\"CRD\", \"dataRead\", " + respvarVector + ", \"" + p1 + "\", \"" + p2 + 
//						"\", rep = NULL, block = NULL, row = NULL, column = NULL, cross = " + cross + ", individual = NULL, environment = \"" + environment + "\", alpha = " + alpha + "), silent = TRUE)";
//			}
//		}
//		if (design == "RCB") {
//			if (environment == "NULL") {
//				funcDiallel3Test = "result<-try(diallel3Test(\"RCB\", \"dataRead\", " + respvarVector + ", \"" + p1 + "\", \"" + p2 + "\", rep = NULL, " +
//						"block = \"" + block + "\", row = NULL, column = NULL, cross = " + cross + ", individual = NULL, environment = " + environment + ", alpha = " + alpha + "), silent = TRUE)";
//			} else {
//				funcDiallel3Test = "result<-try(diallel3Test(\"RCB\", \"dataRead\", " + respvarVector + ", \"" + p1 + "\", \"" + p2 + "\", rep = NULL, " +
//						"block = \"" + block + "\", row = NULL, column = NULL, cross = " + cross + ", individual = NULL, environment = \"" + environment + "\", alpha = " + alpha + "), silent = TRUE)";;
//			}
//		}
//		if (design == "Alpha") {
//			if (environment == "NULL") {
//				funcDiallel3Test = "result<-try(diallel3Test(\"Alpha\", \"dataRead\", " + respvarVector + ", \"" + p1 + "\", \"" + p2 + "\", rep = \"" 
//						+ rep + "\", block = \"" + block + "\", row = NULL, column = NULL, cross = " + cross + ", individual = NULL, environment = " + environment + ", alpha = " + alpha + "), silent = TRUE)";
//			} else {
//				funcDiallel3Test = "result<-try(diallel3Test(\"Alpha\", \"dataRead\", " + respvarVector + ", \"" + p1 + "\", \"" + p2 + "\", rep = \"" 
//						+ rep + "\", block = \"" + block + "\", row = NULL, column = NULL, cross = " + cross + ", individual = NULL, environment = \"" + environment + "\", alpha = " + alpha + "), silent = TRUE)";;
//			}
//		}
//		if (design == "RowColumn") {
//			if (environment == "NULL") {
//				funcDiallel3Test = "result<-try(diallel3Test(\"RowColumn\", \"dataRead\", " + respvarVector + ", \"" + p1 + "\", \"" + p2 + "\", rep = \"" 
//						+ rep + "\", block = NULL, row = \"" + row + "\", column = \"" + column + "\", cross = " + cross + ", individual = NULL, environment = " + environment + ", alpha = " + alpha + "), silent = TRUE)";
//			} else {
//				funcDiallel3Test = "result<-try(diallel3Test(\"RowColumn\", \"dataRead\", " + respvarVector + ", \"" + p1 + "\", \"" + p2 + "\", rep = \"" 
//						+ rep + "\", block = NULL, row = \"" + row + "\", column = \"" + column + "\", cross = " + cross + ", individual = NULL, environment = \"" + environment + "\", alpha = " + alpha + "), silent = TRUE)";
//			}
//		}
//		
//		String checkError = "if (class(result) == \"try-error\") {\n";
//		checkError = checkError + "    msg <- trimStrings(strsplit(result, \":\")[[1]])\n";
//		checkError = checkError + "    msg <- trimStrings(paste(strsplit(msg, \"\\n\")[[length(msg)]], collapse = \" \"))\n";
//		checkError = checkError + "    msg <- gsub(\"\\\"\", \"\", msg)\n";
//		checkError = checkError + "    cat(\"\n*** \nERROR in diallel3Test function:\\n  \",msg, \"\n***\", sep = \"\")\n";
//		checkError = checkError + "}";	
//		String sinkOut = "sink()";
//		
//		System.out.println(readData);
//		System.out.println(sinkIn);
//		System.out.println(funcDiallel3Test);
//		System.out.println(checkError);
//		System.out.println(sinkOut);
//		
//		//R statements passed on to the R engine
//		rEngine.eval(readData);
//		rEngine.eval(sinkIn);
//		rEngine.eval(usedData);
//		rEngine.eval(funcDiallel3Test);
//		rEngine.eval(checkError);
//		rEngine.eval(sinkOut);
//		rEngineEnd();
		
		//NEW APPROACH
		//read data
		String readData = "dataRead <- read.csv(\"" + dataFileName + "\", header = TRUE, na.strings = c(\"NA\",\".\",\" \",\"\"), blank.lines.skip=TRUE, sep = \",\")";
		System.out.println(readData);
		rEngine.eval(readData);
		String runSuccessData = rEngine.eval("data").asString();
		
		if (runSuccessData != null && runSuccessData.equals("notRun")) {	
			System.out.println("error");
			rEngine.eval("capture.output(cat(\"\n***Error reading data.***\n\"),file=\"" + outFileName + "\",append = FALSE)"); 
		} else {
			//create titles
			String designName = null;
			if (design == "CRD") {
				designName="CRD";
			}
			if (design == "RCB") { 
				designName="RCB";
			}
			if (design == "Alpha") { 
				designName="ALPHA-LATTICE";
			}
			if (design == "RowColumn") { 
				designName="ROW-COLUMN";
			}
			
			String usedData = "capture.output(cat(\"\nDATA FILE: " + dataFileName + "\n\",file=\"" + outFileName + "\"))";
			String usedDesign = null;
			if (cross == "TRUE") {
				usedDesign = "capture.output(cat(\"\nDIALLEL ANALYSIS: GRIFFING METHOD III IN " + designName + " (CROSS)\n\n\"),file=\"" + outFileName + "\",append = TRUE)";
			} else {
				usedDesign = "capture.output(cat(\"\nDIALLEL ANALYSIS: GRIFFING METHOD III IN " + designName + " (SELF)\n\n\"),file=\"" + outFileName + "\",append = TRUE)";
			}
			
			String sep = "capture.output(cat(\"------------------------------\"),file=\"" + outFileName + "\",append = TRUE)";
			String sep2 = "capture.output(cat(\"==============================\n\"),file=\"" + outFileName + "\",append = TRUE)";
			String outspace = "capture.output(cat(\"\n\"),file=\"" + outFileName + "\",append = TRUE)";
	
			rEngine.eval(usedData);
			rEngine.eval(usedDesign);
			
			String funcDiallel3Test=null;
			if (design == "CRD") {
				if (environment == "NULL") {
					funcDiallel3Test = "result<-try(diallel3Test(\"CRD\", \"dataRead\", " + respvarVector + ", \"" + p1 + "\", \"" + p2 + 
							"\", rep = NULL, block = NULL, row = NULL, column = NULL, cross = " + cross + ", individual = NULL, environment = " + environment + ", alpha = " + alpha + "), silent = TRUE)";
				} else {
					funcDiallel3Test = "result<-try(diallel3Test(\"CRD\", \"dataRead\", " + respvarVector + ", \"" + p1 + "\", \"" + p2 + 
							"\", rep = NULL, block = NULL, row = NULL, column = NULL, cross = " + cross + ", individual = NULL, environment = \"" + environment + "\", alpha = " + alpha + "), silent = TRUE)";
				}
			}
			if (design == "RCB") {
				if (environment == "NULL") {
					funcDiallel3Test = "result<-try(diallel3Test(\"RCB\", \"dataRead\", " + respvarVector + ", \"" + p1 + "\", \"" + p2 + "\", rep = NULL, " +
							"block = \"" + block + "\", row = NULL, column = NULL, cross = " + cross + ", individual = NULL, environment = " + environment + ", alpha = " + alpha + "), silent = TRUE)";
				} else {
					funcDiallel3Test = "result<-try(diallel3Test(\"RCB\", \"dataRead\", " + respvarVector + ", \"" + p1 + "\", \"" + p2 + "\", rep = NULL, " +
							"block = \"" + block + "\", row = NULL, column = NULL, cross = " + cross + ", individual = NULL, environment = \"" + environment + "\", alpha = " + alpha + "), silent = TRUE)";;
				}
			}
			if (design == "Alpha") {
				if (environment == "NULL") {
					funcDiallel3Test = "result<-try(diallel3Test(\"Alpha\", \"dataRead\", " + respvarVector + ", \"" + p1 + "\", \"" + p2 + "\", rep = \"" 
							+ rep + "\", block = \"" + block + "\", row = NULL, column = NULL, cross = " + cross + ", individual = NULL, environment = " + environment + ", alpha = " + alpha + "), silent = TRUE)";
				} else {
					funcDiallel3Test = "result<-try(diallel3Test(\"Alpha\", \"dataRead\", " + respvarVector + ", \"" + p1 + "\", \"" + p2 + "\", rep = \"" 
							+ rep + "\", block = \"" + block + "\", row = NULL, column = NULL, cross = " + cross + ", individual = NULL, environment = \"" + environment + "\", alpha = " + alpha + "), silent = TRUE)";;
				}
			}
			if (design == "RowColumn") {
				if (environment == "NULL") {
					funcDiallel3Test = "result<-try(diallel3Test(\"RowColumn\", \"dataRead\", " + respvarVector + ", \"" + p1 + "\", \"" + p2 + "\", rep = \"" 
							+ rep + "\", block = NULL, row = \"" + row + "\", column = \"" + column + "\", cross = " + cross + ", individual = NULL, environment = " + environment + ", alpha = " + alpha + "), silent = TRUE)";
				} else {
					funcDiallel3Test = "result<-try(diallel3Test(\"RowColumn\", \"dataRead\", " + respvarVector + ", \"" + p1 + "\", \"" + p2 + "\", rep = \"" 
							+ rep + "\", block = NULL, row = \"" + row + "\", column = \"" + column + "\", cross = " + cross + ", individual = NULL, environment = \"" + environment + "\", alpha = " + alpha + "), silent = TRUE)";
				}
			}
			
			rEngine.eval(funcDiallel3Test);
			System.out.println(funcDiallel3Test);
	
			String runSuccess = rEngine.eval("class(result)").asString();
			if (runSuccess != null && runSuccess.equals("try-error")) {	
				System.out.println("diallel3Test: error");
				String checkError = "msg <- trimStrings(strsplit(result, \":\")[[1]])";
				String checkError2 = "msg <- trimStrings(paste(strsplit(msg, \"\\n\")[[length(msg)]], collapse = \" \"))";
				String checkError3 ="msg <- gsub(\"\\\"\", \"\", msg)";
				String checkError4 = "capture.output(cat(\"*** \nERROR in diallel3Test function:\\n  \",msg, \"\n***\n\n\", sep = \"\"), file=\"" + outFileName + "\",append = TRUE)";
				rEngine.eval(checkError);
				rEngine.eval(checkError2);
				rEngine.eval(checkError3);
				rEngine.eval(checkError4);
	
			} else {
				for (int k = 0; k < respvar.length; k++) {
					int i = k + 1; // 1-relative index;
					
					String respVarHead = "capture.output(cat(\"\nRESPONSE VARIABLE: " + respvar[k] + "\n\"),file=\"" + outFileName + "\",append = TRUE)";
					rEngine.eval(sep);
					rEngine.eval(respVarHead);
					rEngine.eval(sep);
					rEngine.eval(outspace);
					
					String getEnvLevels = "length(result$output[[" + i + "]]$site)";
					int envLevelsLength = rEngine.eval(getEnvLevels).asInt();
	//							System.out.println("envLevelsLength: " + envLevelsLength);
					
					for (int m = 0; m < envLevelsLength; m++) { // no of envts or sites
						int j = m + 1; // 1-relative index;
						if (environment != "NULL") {
							String envtHead = "capture.output(cat(\"\nANALYSIS FOR: "+ environment + "\", \" = \" ,result$output[[" + i	+ "]]$site[[" + j + "]]$env,\"\n\"),file=\""+ outFileName + "\",append = TRUE)";
							rEngine.eval(sep);
							rEngine.eval(envtHead);
							rEngine.eval(sep);
							rEngine.eval(outspace);
						} 
						
						//check if max of lengthPerCross is equal to nlevelsRep 
						int maxLengthPerCross =rEngine.eval("result$output[[" + i + "]]$site[[" + j + "]]$maxLengthPerCross").asInt();
						int nLevelsRep =rEngine.eval("result$output[[" + i + "]]$site[[" + j + "]]$nlevelsRep").asInt();
						
						if (maxLengthPerCross > nLevelsRep) {
							String errorMessage1 = "capture.output(cat(\"\nERROR: \",result$output[[" + i	+ "]]$site[[" + j + "]]$blockLabelError),file=\""+ outFileName + "\",append = TRUE)";
							String errorMessage2 = "capture.output(cat(\"\n       \",result$output[[" + i	+ "]]$site[[" + j + "]]$blockLabelError2),file=\""+ outFileName + "\",append = TRUE)";
						
							rEngine.eval(errorMessage1);
							rEngine.eval(errorMessage2);
							rEngine.eval(outspace);
							rEngine.eval(outspace);
							rEngine.eval(outspace);
							
							break;
						}
						
						String manyNA1 = "result$output[["	+ i	+ "]]$site[[" + j + "]]$tooManyNAWarning";
						String manyNA2 = rEngine.eval(manyNA1).asString();
				
	//								System.out.println("exceededCheck: " + exceededCheck);
						
						if (manyNA2.equals("YES")) {
							String warningExceed = "capture.output(cat(\"\nToo many missing observations. Cannot proceed with the analysis.\n\n\n\"),file=\"" + outFileName + "\",append = TRUE)";
							rEngine.eval(warningExceed);
						} else {
							//display data summary
							String trialSumHead = "capture.output(cat(\"\nDATA SUMMARY:\n\n\"),file=\"" + outFileName + "\",append = TRUE)";
							String trialSumTable = "capture.output(result$output[[" + i + "]]$site[[" + j + "]]$funcTrialSum,file=\"" + outFileName + "\",append = TRUE)";
							String trialObsRead = "capture.output(cat(\"Number of observations read: \", result$output[["	+ i	+ "]]$site[[" + j + "]]$obsread[[1]],\"\n\"),file=\"" + outFileName + "\",append = TRUE)";
							String trialObsUsed = "capture.output(cat(\"Number of observations used: \", result$output[["	+ i	+ "]]$site[[" + j + "]]$obsused[[1]],\"\n\"),file=\""	+ outFileName + "\",append = TRUE)";
	
							rEngine.eval(trialSumHead);
							rEngine.eval(trialSumTable);
							rEngine.eval(outspace);
							rEngine.eval(trialObsRead);
							rEngine.eval(trialObsUsed);
	//									rEngine.eval(outspace);
							
							String exceeded = "result$output[["	+ i	+ "]]$site[[" + j + "]]$exceededWarning";
							String exceededCheck = rEngine.eval(exceeded).asString();
							rEngine.eval(exceeded);
							rEngine.eval(exceededCheck);
					
	//									System.out.println("exceededCheck: " + exceededCheck);
							
							if (exceededCheck.equals("YES")) {
								String warningExceed = "capture.output(cat(\"\n\n***\nERROR: The number of observations read in the data exceeds the size of a balanced data.\n       Please check if the column for block/replicate is properly labeled.\n***\n\n\"),file=\"" + outFileName + "\",append = TRUE)";
								rEngine.eval(warningExceed);
							} else {
								double responseRate =rEngine.eval("result$output[[" + i + "]]$site[[" + j + "]]$responseRate").asDouble();
								
								//display anova table if design is RCB
								if (design == "RCB") {
							        String anovaHead = "capture.output(cat(\"\n\nANOVA TABLE FOR THE EXPERIMENT:\n\n\"),file=\"" + outFileName + "\",append = TRUE)";
							        rEngine.eval(anovaHead);
							        
							        if (responseRate < 0.90) {
										String warningExceed = "capture.output(cat(\" ERROR: Too many missing values. Cannot perform ANOVA for balanced data.\n\"),file=\"" + outFileName + "\",append = TRUE)";
										rEngine.eval(warningExceed);
										
									} else {
										String ttcAnova1 = "capture.output(result$output[[" + i + "]]$site[[" + j + "]]$diallel1.anova,file=\"" + outFileName + "\",append = TRUE)";
										String ttcAnova2 = "capture.output(cat(\"-------\n\"),file=\"" + outFileName + "\",append = TRUE)";
										String ttcAnova3 = "capture.output(cat(\"REMARK:\", result$output[["	+ i	+ "]]$site[[" + j + "]]$anovaRemark1,\"\n\"),file=\""	+ outFileName + "\",append = TRUE)";
										
										rEngine.eval(ttcAnova1);
										rEngine.eval(ttcAnova2);
										rEngine.eval(ttcAnova3);
									}
								}
								
								//test on significance of cross effect
								String crossEffect1 = "capture.output(cat(\"\n\nTESTING FOR THE SIGNIFICANCE OF CROSS EFFECT: (Crosses = " + p1 + ":" + p2 + ")\n\"),file=\"" + outFileName + "\",append = TRUE)";
								rEngine.eval(crossEffect1);
								Double pvalue=0.00;
								
								if (design == "CRD") {
									String crossEffect2 = "capture.output(cat(\"\nModel:\", result$output[["	+ i	+ "]]$site[[" + j + "]]$formula1,\"\n\n\"),file=\""	+ outFileName + "\",append = TRUE)";
									String crossEffect3 = "capture.output(result$output[[" + i + "]]$site[[" + j + "]]$genoEffect.anova,file=\"" + outFileName + "\",append = TRUE)";
								
									rEngine.eval(crossEffect2);
									rEngine.eval(crossEffect3);
									
									pvalue = rEngine.eval("result$output[[" + i + "]]$site[[" + j + "]]$pValue").asDouble();
									System.out.println("pvalue: " + pvalue);
									
									//display matrix of means
									String matrixMeans1 = "capture.output(cat(\"\n\nMATRIX OF MEANS:\n\n\"),file=\"" + outFileName + "\",append = TRUE)";
									String matrixMeans2 = "capture.output(result$output[[" + i + "]]$site[[" + j + "]]$means.matrix,file=\"" + outFileName + "\",append = TRUE)";
									rEngine.eval(matrixMeans1);
									rEngine.eval(matrixMeans2);
									
//									String estimatedMissing1 = "result$output[["	+ i	+ "]]$site[[" + j + "]]$estimatedMissing";
//									String estimatedMissing2 = rEngine.eval(estimatedMissing1).asString();
									
//									if (estimatedMissing2.equals("TRUE")) {
										String anovaRemark1 = "capture.output(cat(\"-------\n\"),file=\"" + outFileName + "\",append = TRUE)";
										String anovaRemark2 = "capture.output(cat(\"REMARK:\", result$output[["	+ i	+ "]]$site[[" + j + "]]$anovaRemark2,\"\n\"),file=\""	+ outFileName + "\",append = TRUE)";
										
										rEngine.eval(anovaRemark1);
										rEngine.eval(anovaRemark2);
//									}
									
									//save matrix of means to a file
									String meansFileName2 = null;
									if (environment != "NULL") {
										meansFileName2 = "meansFileName2 <- paste(\"" + resultFolderPath + "\",\"matrixMeans_\", \"" + respvar[k] + "\", \"_\", result$output[[" + i + "]]$site[[" + j + "]]$env,\".csv\", sep=\"\")";
									} else {
										meansFileName2 = "meansFileName2 <- paste(\"" + resultFolderPath + "\",\"matrixMeans_\", \"" + respvar[k] + "\", \".csv\", sep=\"\")";
									}
									
									System.out.println(meansFileName2);
									
									String funcSavePredMeansCsv = "saveDataB1 <- try(write.table(result$output[[" + i + "]]$site[[" + j + "]]$means.matrix,file = meansFileName2 ,sep=\",\",col.names=NA, row.names=TRUE), silent=TRUE)";
									rEngine.eval(meansFileName2);
									rEngine.eval(funcSavePredMeansCsv);
									
									String runSuccessSavePredMeans = rEngine.eval("class(saveDataB1)").asString();
									if (runSuccessSavePredMeans != null && runSuccessSavePredMeans.equals("try-error")) {	
										System.out.println("save matrix of means: error");
										String checkError = "msg <- trimStrings(strsplit(saveDataB1, \":\")[[1]])";
										String checkError2 = "msg <- trimStrings(paste(strsplit(msg, \"\\n\")[[length(msg)]], collapse = \" \"))";
										String checkError3 ="msg <- gsub(\"\\\"\", \"\", msg)";
										String checkError4 = "capture.output(cat(\"\n***\nERROR in saving matrix of means to a file:\\n  \",msg, \"\n***\n\", sep = \"\"), file=\"" + outFileName + "\",append = TRUE)";
										rEngine.eval(checkError);
										rEngine.eval(checkError2);
										rEngine.eval(checkError3);
										rEngine.eval(checkError4);
									}
									
								} else {
									if (responseRate < 0.90) {
										String warningExceed = "capture.output(cat(\"\n ERROR: Too many missing values. Cannot perform the analysis.\n\"),file=\"" + outFileName + "\",append = TRUE)";
										rEngine.eval(warningExceed);
									} else {
										String crossEffect2 = "capture.output(cat(\"\nModel:\", result$output[["	+ i	+ "]]$site[[" + j + "]]$formula1,\"\n\"),file=\""	+ outFileName + "\",append = TRUE)";
										rEngine.eval(crossEffect2);
										
										String outAnovaTable2 = "library(lmerTest)";
										String outAnovaTable3 = "model1b <- lmer(formula(result$output[[" + i + "]]$site[[" + j + "]]$formula1), data = result$output[[" + i + "]]$site[[" + j + "]]$data, REML = T)";
										String outAnovaTable4 = "a.table <- anova(model1b)";
										String outAnovaTable5 = "pvalue <- formatC(as.numeric(format(a.table[1,6], scientific=FALSE)), format=\"f\")";
										String outAnovaTable6 = "a.table<-cbind(round(a.table[,1:5], digits=4),pvalue)";
										String outAnovaTable7 = "colnames(a.table)<-c(\"Df\", \"Sum Sq\", \"Mean Sq\", \"F value\", \"Denom\", \"Pr(>F)\")";
										String outAnovaTable8 = "capture.output(cat(\"Analysis of Variance Table with Satterthwaite Denominator Df\n\"),file=\"" + outFileName + "\",append = TRUE)";
										String outAnovaTable9 = "capture.output(a.table,file=\"" + outFileName + "\",append = TRUE)";
										String outAnovaTable10 = "detach(\"package:lmerTest\")";
										
										rEngine.eval(outAnovaTable2);
										rEngine.eval(outAnovaTable3);
										rEngine.eval(outAnovaTable4);
		
										pvalue = rEngine.eval("a.table[1,6]").asDouble();
										System.out.println("pvalue: " + pvalue);
										
										rEngine.eval(outAnovaTable5);
										rEngine.eval(outAnovaTable6);
										rEngine.eval(outAnovaTable7);
										rEngine.eval(outspace);
										rEngine.eval(outAnovaTable8);
										rEngine.eval(outAnovaTable9);
										rEngine.eval(outAnovaTable10);
										
										//display matrix of means
										String matrixMeans1 = "capture.output(cat(\"\n\nMATRIX OF MEANS:\n\n\"),file=\"" + outFileName + "\",append = TRUE)";
										String matrixMeans2 = "capture.output(result$output[[" + i + "]]$site[[" + j + "]]$means.matrix,file=\"" + outFileName + "\",append = TRUE)";
										rEngine.eval(matrixMeans1);
										rEngine.eval(matrixMeans2);
										
//										String estimatedMissing1 = "result$output[["	+ i	+ "]]$site[[" + j + "]]$estimatedMissing";
//										String estimatedMissing2 = rEngine.eval(estimatedMissing1).asString();
										
//										if (estimatedMissing2.equals("TRUE")) {
											String anovaRemark1 = "capture.output(cat(\"-------\n\"),file=\"" + outFileName + "\",append = TRUE)";
											String anovaRemark2 = "capture.output(cat(\"REMARK:\", result$output[["	+ i	+ "]]$site[[" + j + "]]$anovaRemark2,\"\n\"),file=\""	+ outFileName + "\",append = TRUE)";
											
											rEngine.eval(anovaRemark1);
											rEngine.eval(anovaRemark2);
//										}
										
										//save matrix of means to a file
										String meansFileName2 = null;
										if (environment != "NULL") {
											meansFileName2 = "meansFileName2 <- paste(\"" + resultFolderPath + "\",\"matrixMeans_\", \"" + respvar[k] + "\", \"_\", result$output[[" + i + "]]$site[[" + j + "]]$env,\".csv\", sep=\"\")";
										} else {
											meansFileName2 = "meansFileName2 <- paste(\"" + resultFolderPath + "\",\"matrixMeans_\", \"" + respvar[k] + "\", \".csv\", sep=\"\")";
										}
										
										System.out.println(meansFileName2);
										
										String funcSavePredMeansCsv = "saveDataB1 <- try(write.table(result$output[[" + i + "]]$site[[" + j + "]]$means.matrix,file = meansFileName2 ,sep=\",\",col.names=NA, row.names=TRUE), silent=TRUE)";
										rEngine.eval(meansFileName2);
										rEngine.eval(funcSavePredMeansCsv);
										
										String runSuccessSavePredMeans = rEngine.eval("class(saveDataB1)").asString();
										if (runSuccessSavePredMeans != null && runSuccessSavePredMeans.equals("try-error")) {	
											System.out.println("save matrix of means: error");
											String checkError = "msg <- trimStrings(strsplit(saveDataB1, \":\")[[1]])";
											String checkError2 = "msg <- trimStrings(paste(strsplit(msg, \"\\n\")[[length(msg)]], collapse = \" \"))";
											String checkError3 ="msg <- gsub(\"\\\"\", \"\", msg)";
											String checkError4 = "capture.output(cat(\"\n***\nERROR in saving matrix of means to a file:\\n  \",msg, \"\n***\n\", sep = \"\"), file=\"" + outFileName + "\",append = TRUE)";
											rEngine.eval(checkError);
											rEngine.eval(checkError2);
											rEngine.eval(checkError3);
											rEngine.eval(checkError4);
										}
									}
								}
								
								String meansComplete1 = "result$output[["	+ i	+ "]]$site[[" + j + "]]$meansComplete";
								String meansComplete2 = rEngine.eval(meansComplete1).asString();
								
								if (meansComplete2.equals("TRUE")) {
									//if cross effect is significant,
									double alphaNew = Double.parseDouble(alpha);
									
									if (pvalue < alphaNew) {
										String gcaAnovaHead = "capture.output(cat(\"\n\nANALYSIS OF VARIANCE:\n\n\"),file=\"" + outFileName + "\",append = TRUE)";
										rEngine.eval(gcaAnovaHead);
											
										if (responseRate >= 0.90 || design=="CRD") {
											//GCA and SCA anova table
											String gcaAnova1 = "capture.output(result$output[[" + i + "]]$site[[" + j + "]]$gcasca.anova,file=\"" + outFileName + "\",append = TRUE)";
											rEngine.eval(gcaAnova1);
											
											//estimates of effects
											String effects1 = "capture.output(cat(\"\n\nGENERAL COMBINING ABILITY EFFECTS (diagonal), SPECIFIC COMBINING\n\"),file=\"" + outFileName + "\",append = TRUE)";
											String effects2 = "capture.output(cat(\"ABILITY EFFECTS (above diagonal) AND RECIPROCAL EFFECTS (below diagonal):\n\n\"),file=\"" + outFileName + "\",append = TRUE)";
											String effects3 = "capture.output(result$output[[" + i + "]]$site[[" + j + "]]$gcasca.matrix,file=\"" + outFileName + "\",append = TRUE)";
											
											rEngine.eval(effects1);
											rEngine.eval(effects2);
											rEngine.eval(effects3);
											
											//save matrix of effects to a file
											String effectsFileName2 = null;
											if (environment != "NULL") {
												effectsFileName2 = "effectsFileName2 <- paste(\"" + resultFolderPath + "\",\"matrixEffects_\", \"" + respvar[k] + "\", \"_\", result$output[[" + i + "]]$site[[" + j + "]]$env,\".csv\", sep=\"\")";
											} else {
												effectsFileName2 = "effectsFileName2 <- paste(\"" + resultFolderPath + "\",\"matrixEffects_\", \"" + respvar[k] + "\", \".csv\", sep=\"\")";
											}
											
											String funcSaveEffectsCsv = "saveDataB2 <- try(write.table(result$output[[" + i + "]]$site[[" + j + "]]$gcasca.matrix,file = effectsFileName2 ,sep=\",\",col.names=NA, row.names=TRUE), silent=TRUE)";
											rEngine.eval(effectsFileName2);
											rEngine.eval(funcSaveEffectsCsv);
											
											String runSuccessSaveEffects = rEngine.eval("class(saveDataB2)").asString();
											if (runSuccessSaveEffects != null && runSuccessSaveEffects.equals("try-error")) {	
												System.out.println("save matrix of effects: error");
												String checkError = "msg <- trimStrings(strsplit(saveDataB2, \":\")[[1]])";
												String checkError2 = "msg <- trimStrings(paste(strsplit(msg, \"\\n\")[[length(msg)]], collapse = \" \"))";
												String checkError3 ="msg <- gsub(\"\\\"\", \"\", msg)";
												String checkError4 = "capture.output(cat(\"\n***\nERROR in saving matrix of effects to a file:\\n  \",msg, \"\n***\n\", sep = \"\"), file=\"" + outFileName + "\",append = TRUE)";
												rEngine.eval(checkError);
												rEngine.eval(checkError2);
												rEngine.eval(checkError3);
												rEngine.eval(checkError4);
											}
											
											//estimate of variance components
											String varComp1 = "capture.output(cat(\"\n\nESTIMATES OF VARIANCE COMPONENTS:\n\n\"),file=\"" + outFileName + "\",append = TRUE)";
											String varComp2 = "capture.output(result$output[[" + i + "]]$site[[" + j + "]]$var.components,file=\"" + outFileName + "\",append = TRUE)";
											
											rEngine.eval(varComp1);
											rEngine.eval(varComp2);
											
											//genetic variances
											String genVar1 = "capture.output(cat(\"\n\nESTIMATES OF GENETIC VARIANCE COMPONENTS:\n\"),file=\"" + outFileName + "\",append = TRUE)";
											String genVar2 = "capture.output(result$output[[" + i + "]]$site[[" + j + "]]$genvar.components,file=\"" + outFileName + "\",append = TRUE)";
											
											rEngine.eval(genVar1);
											rEngine.eval(genVar2);
											
											//standard errors
											String lsd1 = "capture.output(cat(\"\n\nTABLE OF STANDARD ERRORS AND LSDs:\n\n\"),file=\"" + outFileName + "\",append = TRUE)";
											String lsd2 = "capture.output(result$output[[" + i + "]]$site[[" + j + "]]$stderror.table,file=\"" + outFileName + "\",append = TRUE)";
											
											rEngine.eval(lsd1);
											rEngine.eval(lsd2);
											rEngine.eval(outspace);
											rEngine.eval(outspace);
											
										} else {
											String warningExceed = "capture.output(cat(\" ERROR: Too many missing values. Cannot perform test for significance of GCA and SCA effects.\n\"),file=\"" + outFileName + "\",append = TRUE)";
											String warningExceed2 = "capture.output(cat(\"        and estimation of genetic variance components.\n\n\"),file=\"" + outFileName + "\",append = TRUE)";
											rEngine.eval(warningExceed);
											rEngine.eval(warningExceed2);
											rEngine.eval(outspace);
										}// end of else (if (responseRate >= 0.90))
										
									} else {
										rEngine.eval(outspace);
										rEngine.eval(outspace);
										
									} // end of else (if (pvalue < alpha))
								} else {
									if (design == "CRD") {
										String meansNotComplete = "capture.output(cat(\"\n\n***\nERROR: At least one required P1xP2 combination is missing. Cannot perform the succeeding analyses.\n***\n\n\n\"),file=\"" + outFileName + "\",append = TRUE)";
										rEngine.eval(meansNotComplete);
									} else {
										rEngine.eval(outspace);
										rEngine.eval(outspace);
										rEngine.eval(outspace);
									}
								}
								
							} // end of else (if (exceededCheck.equals("YES")))
	
						}
						
					} // end of for (int m = 0; m < envLevelsLength; m++)
	
				} // end of for (int k = 0; k < respvar.length; k++)
				rEngine.eval(outspace);
				rEngine.eval(sep2);
			} // end of else (if (runSuccess != null && runSuccess.equals("try-error"))
	
		} // end of else (if (runSuccessData != null && runSuccessData.equals("notRun")))
		
		rEngineEnd();
	}
	
	@Override
	public void doDiallel3METest(String dataFileName, String outFileName, String resultFolderPath, String design, String[] respvar, String p1, String p2, 
			String rep, String block, String row, String column, String cross, String individual, String environment, String alpha){

		String respvarVector= inputTransform.createRVector(respvar);
		
		//defining the R statements
		String readData = "dataRead <- read.csv(\"" + dataFileName + "\", header = TRUE, na.strings = c(\"NA\",\".\",\" \",\"\"), blank.lines.skip=TRUE, sep = \",\")";
		String sinkIn = "sink(\"" + outFileName + "\")";
		
		String usedData = "cat(\"\nDATA FILE: " + dataFileName + "\n\")";
		
		String funcDiallel3TestME=null;
		if (design == "CRD") {
			funcDiallel3TestME = "result<-try(diallel3TestME(\"CRD\", \"dataRead\", " + respvarVector + ", \"" + p1 + "\", \"" + p2 + 
						"\", rep = NULL, block = NULL, row = NULL, column = NULL, cross = " + cross + ", individual = NULL, environment = \"" + environment + "\", alpha = " + alpha + "), silent = TRUE)";
		}
		if (design == "RCB") {
			funcDiallel3TestME = "result<-try(diallel3TestME(\"RCB\", \"dataRead\", " + respvarVector + ", \"" + p1 + "\", \"" + p2 + "\", rep = NULL, " +
						"block = \"" + block + "\", row = NULL, column = NULL, cross = " + cross + ", individual = NULL, environment = \"" + environment + "\", alpha = " + alpha + "), silent = TRUE)";;
		}
		if (design == "Alpha") {
			funcDiallel3TestME = "result<-try(diallel3TestME(\"Alpha\", \"dataRead\", " + respvarVector + ", \"" + p1 + "\", \"" + p2 + "\", rep = \"" 
						+ rep + "\", block = \"" + block + "\", row = NULL, column = NULL, cross = " + cross + ", individual = NULL, environment = \"" + environment + "\", alpha = " + alpha + "), silent = TRUE)";;
		}
		if (design == "RowColumn") {
			funcDiallel3TestME = "result<-try(diallel3TestME(\"RowColumn\", \"dataRead\", " + respvarVector + ", \"" + p1 + "\", \"" + p2 + "\", rep = \"" 
						+ rep + "\", block = NULL, row = \"" + row + "\", column = \"" + column + "\", cross = " + cross + ", individual = NULL, environment = \"" + environment + "\", alpha = " + alpha + "), silent = TRUE)";
		}
		
		String checkError = "if (class(result) == \"try-error\") {\n";
		checkError = checkError + "    msg <- trimStrings(strsplit(result, \":\")[[1]])\n";
		checkError = checkError + "    msg <- trimStrings(paste(strsplit(msg, \"\\n\")[[length(msg)]], collapse = \" \"))\n";
		checkError = checkError + "    msg <- gsub(\"\\\"\", \"\", msg)\n";
		checkError = checkError + "    cat(\"\n*** \nERROR in diallel3TestME function:\\n  \",msg, \"\n***\", sep = \"\")\n";
		checkError = checkError + "}";		
		String sinkOut = "sink()";
		
		System.out.println(readData);
		System.out.println(sinkIn);
		System.out.println(funcDiallel3TestME);
		System.out.println(checkError);
		System.out.println(sinkOut);
		
		//R statements passed on to the R engine
		rEngine.eval(readData);
		rEngine.eval(sinkIn);
		rEngine.eval(usedData);
		rEngine.eval(funcDiallel3TestME);
		rEngine.eval(checkError);
		rEngine.eval(sinkOut);
		
		//save matrix of means and effects to csv files
		String runSuccess = rEngine.eval("class(result)").asString();
		if (runSuccess != null && runSuccess.equals("try-error")) {	
			System.out.println("results of diallel3TestME: error");
		} else {
			for (int k = 0; k < respvar.length; k++) {
				int i = k + 1; // 1-relative index;
				
				String estimatedMissing1 = "result$output[[" + i + "]]$canEstimateMissing";
				String estimatedMissing2 = rEngine.eval(estimatedMissing1).asString();
				
				if (estimatedMissing2.equals("TRUE")) {
					//save matrix of means to a file
					String meansFileName2 = "meansFileName2 <- paste(\"" + resultFolderPath + "\",\"matrixMeans_\", \"" + respvar[k] + "\", \".csv\", sep=\"\")";
					String funcSavePredMeansCsv = "saveDataB1 <- try(write.table(result$output[[" + i + "]]$means.matrix,file = meansFileName2 ,sep=\",\",col.names=NA, row.names=TRUE), silent=TRUE)";
					rEngine.eval(meansFileName2);
					rEngine.eval(funcSavePredMeansCsv);
					
					System.out.println(meansFileName2);
					System.out.println(funcSavePredMeansCsv);
					
					String runSuccessSavePredMeans = rEngine.eval("class(saveDataB1)").asString();
					if (runSuccessSavePredMeans != null && runSuccessSavePredMeans.equals("try-error")) {	
						System.out.println("save matrix of means: error");
					}
					
					String effectsFileName2 = "effectsFileName2 <- paste(\"" + resultFolderPath + "\",\"matrixEffects_\", \"" + respvar[k] + "\", \".csv\", sep=\"\")";
					String funcSaveEffectsCsv = "saveDataB2 <- try(write.table(result$output[[" + i + "]]$gcasca.matrix,file = effectsFileName2 ,sep=\",\",col.names=NA, row.names=TRUE), silent=TRUE)";
					rEngine.eval(effectsFileName2);
					rEngine.eval(funcSaveEffectsCsv);
					
					System.out.println(effectsFileName2);
					System.out.println(funcSaveEffectsCsv);
					
					String runSuccessSaveEffects = rEngine.eval("class(saveDataB2)").asString();
					if (runSuccessSaveEffects != null && runSuccessSaveEffects.equals("try-error")) {	
						System.out.println("save matrix of effects: error");
					}
				}
			} //end of for (int k = 0; k < respvar.length; k++)
		}
		rEngineEnd();
	}
	
		
	@Override
	public void doDiallel4Test(String dataFileName, String outFileName, String resultFolderPath, String design, String[] respvar, String p1, String p2, 
			String rep, String block, String row, String column, String cross, String individual, String environment, String alpha){

		String respvarVector= inputTransform.createRVector(respvar);
		
		//OLD APPROACH
//		//defining the R statements
//		String readData = "dataRead <- read.csv(\"" + dataFileName + "\", header = TRUE, na.strings = c(\"NA\",\".\",\" \",\"\"), blank.lines.skip=TRUE, sep = \",\")";
//		String sinkIn = "sink(\"" + outFileName + "\")";
//		
//		String usedData = "cat(\"\nDATA FILE: " + dataFileName + "\n\")";
//		
//		String funcDiallel4Test=null;
//		if (design == "CRD") {
//			if (environment == "NULL") {
//				funcDiallel4Test = "result<-try(diallel4Test(\"CRD\", \"dataRead\", " + respvarVector + ", \"" + p1 + "\", \"" + p2 + 
//						"\", rep = NULL, block = NULL, row = NULL, column = NULL, cross = " + cross + ", individual = NULL, environment = " + environment + ", alpha = " + alpha + "), silent = TRUE)";
//			} else {
//				funcDiallel4Test = "result<-try(diallel4Test(\"CRD\", \"dataRead\", " + respvarVector + ", \"" + p1 + "\", \"" + p2 + 
//						"\", rep = NULL, block = NULL, row = NULL, column = NULL, cross = " + cross + ", individual = NULL, environment = \"" + environment + "\", alpha = " + alpha + "), silent = TRUE)";
//			}
//		}
//		if (design == "RCB") {
//			if (environment == "NULL") {
//				funcDiallel4Test = "result<-try(diallel4Test(\"RCB\", \"dataRead\", " + respvarVector + ", \"" + p1 + "\", \"" + p2 + "\", rep = NULL, " +
//						"block = \"" + block + "\", row = NULL, column = NULL, cross = " + cross + ", individual = NULL, environment = " + environment + ", alpha = " + alpha + "), silent = TRUE)";
//			} else {
//				funcDiallel4Test = "result<-try(diallel4Test(\"RCB\", \"dataRead\", " + respvarVector + ", \"" + p1 + "\", \"" + p2 + "\", rep = NULL, " +
//						"block = \"" + block + "\", row = NULL, column = NULL, cross = " + cross + ", individual = NULL, environment = \"" + environment + "\", alpha = " + alpha + "), silent = TRUE)";;
//			}
//		}
//		if (design == "Alpha") {
//			if (environment == "NULL") {
//				funcDiallel4Test = "result<-try(diallel4Test(\"Alpha\", \"dataRead\", " + respvarVector + ", \"" + p1 + "\", \"" + p2 + "\", rep = \"" 
//						+ rep + "\", block = \"" + block + "\", row = NULL, column = NULL, cross = " + cross + ", individual = NULL, environment = " + environment + ", alpha = " + alpha + "), silent = TRUE)";
//			} else {
//				funcDiallel4Test = "result<-try(diallel4Test(\"Alpha\", \"dataRead\", " + respvarVector + ", \"" + p1 + "\", \"" + p2 + "\", rep = \"" 
//						+ rep + "\", block = \"" + block + "\", row = NULL, column = NULL, cross = " + cross + ", individual = NULL, environment = \"" + environment + "\", alpha = " + alpha + "), silent = TRUE)";;
//			}
//		}
//		if (design == "RowColumn") {
//			if (environment == "NULL") {
//				funcDiallel4Test = "result<-try(diallel4Test(\"RowColumn\", \"dataRead\", " + respvarVector + ", \"" + p1 + "\", \"" + p2 + "\", rep = \"" 
//						+ rep + "\", block = NULL, row = \"" + row + "\", column = \"" + column + "\", cross = " + cross + ", individual = NULL, environment = " + environment + ", alpha = " + alpha + "), silent = TRUE)";
//			} else {
//				funcDiallel4Test = "result<-try(diallel4Test(\"RowColumn\", \"dataRead\", " + respvarVector + ", \"" + p1 + "\", \"" + p2 + "\", rep = \"" 
//						+ rep + "\", block = NULL, row = \"" + row + "\", column = \"" + column + "\", cross = " + cross + ", individual = NULL, environment = \"" + environment + "\", alpha = " + alpha + "), silent = TRUE)";
//			}
//		}
//		
//		String checkError = "if (class(result) == \"try-error\") {\n";
//		checkError = checkError + "    msg <- trimStrings(strsplit(result, \":\")[[1]])\n";
//		checkError = checkError + "    msg <- trimStrings(paste(strsplit(msg, \"\\n\")[[length(msg)]], collapse = \" \"))\n";
//		checkError = checkError + "    msg <- gsub(\"\\\"\", \"\", msg)\n";
//		checkError = checkError + "    cat(\"\n*** \nERROR in diallel4Test function:\\n  \",msg, \"\n***\", sep = \"\")\n";
//		checkError = checkError + "}";	
//		String sinkOut = "sink()";
//		
//		System.out.println(readData);
//		System.out.println(sinkIn);
//		System.out.println(funcDiallel4Test);
//		System.out.println(checkError);
//		System.out.println(sinkOut);
//		
//		//R statements passed on to the R engine
//		rEngine.eval(readData);
//		rEngine.eval(sinkIn);
//		rEngine.eval(usedData);
//		rEngine.eval(funcDiallel4Test);
//		rEngine.eval(checkError);
//		rEngine.eval(sinkOut);
//		rEngineEnd();
		
		//NEW APPROACH
		//read data
		String readData = "dataRead <- read.csv(\"" + dataFileName + "\", header = TRUE, na.strings = c(\"NA\",\".\",\" \",\"\"), blank.lines.skip=TRUE, sep = \",\")";
		System.out.println(readData);
		rEngine.eval(readData);
		String runSuccessData = rEngine.eval("data").asString();
		
		if (runSuccessData != null && runSuccessData.equals("notRun")) {	
			System.out.println("error");
			rEngine.eval("capture.output(cat(\"\n***Error reading data.***\n\"),file=\"" + outFileName + "\",append = FALSE)"); 
		} else {
			//create titles
			String designName = null;
			if (design == "CRD") {
				designName="CRD";
			}
			if (design == "RCB") { 
				designName="RCB";
			}
			if (design == "Alpha") { 
				designName="ALPHA-LATTICE";
			}
			if (design == "RowColumn") { 
				designName="ROW-COLUMN";
			}
			
			String usedData = "capture.output(cat(\"\nDATA FILE: " + dataFileName + "\n\",file=\"" + outFileName + "\"))";
			String usedDesign = null;
			if (cross == "TRUE") {
				usedDesign = "capture.output(cat(\"\nDIALLEL ANALYSIS: GRIFFING METHOD IV IN " + designName + " (CROSS)\n\n\"),file=\"" + outFileName + "\",append = TRUE)";
			} else {
				usedDesign = "capture.output(cat(\"\nDIALLEL ANALYSIS: GRIFFING METHOD IV IN " + designName + " (SELF)\n\n\"),file=\"" + outFileName + "\",append = TRUE)";
			}
			
			String sep = "capture.output(cat(\"------------------------------\"),file=\"" + outFileName + "\",append = TRUE)";
			String sep2 = "capture.output(cat(\"==============================\n\"),file=\"" + outFileName + "\",append = TRUE)";
			String outspace = "capture.output(cat(\"\n\"),file=\"" + outFileName + "\",append = TRUE)";
	
			rEngine.eval(usedData);
			rEngine.eval(usedDesign);
			
			String funcDiallel4Test=null;
			if (design == "CRD") {
				if (environment == "NULL") {
					funcDiallel4Test = "result<-try(diallel4Test(\"CRD\", \"dataRead\", " + respvarVector + ", \"" + p1 + "\", \"" + p2 + 
							"\", rep = NULL, block = NULL, row = NULL, column = NULL, cross = " + cross + ", individual = NULL, environment = " + environment + ", alpha = " + alpha + "), silent = TRUE)";
				} else {
					funcDiallel4Test = "result<-try(diallel4Test(\"CRD\", \"dataRead\", " + respvarVector + ", \"" + p1 + "\", \"" + p2 + 
							"\", rep = NULL, block = NULL, row = NULL, column = NULL, cross = " + cross + ", individual = NULL, environment = \"" + environment + "\", alpha = " + alpha + "), silent = TRUE)";
				}
			}
			if (design == "RCB") {
				if (environment == "NULL") {
					funcDiallel4Test = "result<-try(diallel4Test(\"RCB\", \"dataRead\", " + respvarVector + ", \"" + p1 + "\", \"" + p2 + "\", rep = NULL, " +
							"block = \"" + block + "\", row = NULL, column = NULL, cross = " + cross + ", individual = NULL, environment = " + environment + ", alpha = " + alpha + "), silent = TRUE)";
				} else {
					funcDiallel4Test = "result<-try(diallel4Test(\"RCB\", \"dataRead\", " + respvarVector + ", \"" + p1 + "\", \"" + p2 + "\", rep = NULL, " +
							"block = \"" + block + "\", row = NULL, column = NULL, cross = " + cross + ", individual = NULL, environment = \"" + environment + "\", alpha = " + alpha + "), silent = TRUE)";;
				}
			}
			if (design == "Alpha") {
				if (environment == "NULL") {
					funcDiallel4Test = "result<-try(diallel4Test(\"Alpha\", \"dataRead\", " + respvarVector + ", \"" + p1 + "\", \"" + p2 + "\", rep = \"" 
							+ rep + "\", block = \"" + block + "\", row = NULL, column = NULL, cross = " + cross + ", individual = NULL, environment = " + environment + ", alpha = " + alpha + "), silent = TRUE)";
				} else {
					funcDiallel4Test = "result<-try(diallel4Test(\"Alpha\", \"dataRead\", " + respvarVector + ", \"" + p1 + "\", \"" + p2 + "\", rep = \"" 
							+ rep + "\", block = \"" + block + "\", row = NULL, column = NULL, cross = " + cross + ", individual = NULL, environment = \"" + environment + "\", alpha = " + alpha + "), silent = TRUE)";;
				}
			}
			if (design == "RowColumn") {
				if (environment == "NULL") {
					funcDiallel4Test = "result<-try(diallel4Test(\"RowColumn\", \"dataRead\", " + respvarVector + ", \"" + p1 + "\", \"" + p2 + "\", rep = \"" 
							+ rep + "\", block = NULL, row = \"" + row + "\", column = \"" + column + "\", cross = " + cross + ", individual = NULL, environment = " + environment + ", alpha = " + alpha + "), silent = TRUE)";
				} else {
					funcDiallel4Test = "result<-try(diallel4Test(\"RowColumn\", \"dataRead\", " + respvarVector + ", \"" + p1 + "\", \"" + p2 + "\", rep = \"" 
							+ rep + "\", block = NULL, row = \"" + row + "\", column = \"" + column + "\", cross = " + cross + ", individual = NULL, environment = \"" + environment + "\", alpha = " + alpha + "), silent = TRUE)";
				}
			}
			
			rEngine.eval(funcDiallel4Test);
			System.out.println(funcDiallel4Test);
	
			String runSuccess = rEngine.eval("class(result)").asString();
			if (runSuccess != null && runSuccess.equals("try-error")) {	
				System.out.println("diallel3Test: error");
				String checkError = "msg <- trimStrings(strsplit(result, \":\")[[1]])";
				String checkError2 = "msg <- trimStrings(paste(strsplit(msg, \"\\n\")[[length(msg)]], collapse = \" \"))";
				String checkError3 ="msg <- gsub(\"\\\"\", \"\", msg)";
				String checkError4 = "capture.output(cat(\"*** \nERROR in diallel3Test function:\\n  \",msg, \"\n***\n\n\", sep = \"\"), file=\"" + outFileName + "\",append = TRUE)";
				rEngine.eval(checkError);
				rEngine.eval(checkError2);
				rEngine.eval(checkError3);
				rEngine.eval(checkError4);
	
			} else {
				for (int k = 0; k < respvar.length; k++) {
					int i = k + 1; // 1-relative index;
					
					String respVarHead = "capture.output(cat(\"\nRESPONSE VARIABLE: " + respvar[k] + "\n\"),file=\"" + outFileName + "\",append = TRUE)";
					rEngine.eval(sep);
					rEngine.eval(respVarHead);
					rEngine.eval(sep);
					rEngine.eval(outspace);
					
					String getEnvLevels = "length(result$output[[" + i + "]]$site)";
					int envLevelsLength = rEngine.eval(getEnvLevels).asInt();
	//							System.out.println("envLevelsLength: " + envLevelsLength);
					
					for (int m = 0; m < envLevelsLength; m++) { // no of envts or sites
						int j = m + 1; // 1-relative index;
						if (environment != "NULL") {
							String envtHead = "capture.output(cat(\"\nANALYSIS FOR: "+ environment + "\", \" = \" ,result$output[[" + i	+ "]]$site[[" + j + "]]$env,\"\n\"),file=\""+ outFileName + "\",append = TRUE)";
							rEngine.eval(sep);
							rEngine.eval(envtHead);
							rEngine.eval(sep);
							rEngine.eval(outspace);
						} 
						
						//check if max of lengthPerCross is equal to nlevelsRep 
						int maxLengthPerCross =rEngine.eval("result$output[[" + i + "]]$site[[" + j + "]]$maxLengthPerCross").asInt();
						int nLevelsRep =rEngine.eval("result$output[[" + i + "]]$site[[" + j + "]]$nlevelsRep").asInt();
						
						if (maxLengthPerCross > nLevelsRep) {
							String errorMessage1 = "capture.output(cat(\"\nERROR: \",result$output[[" + i	+ "]]$site[[" + j + "]]$blockLabelError),file=\""+ outFileName + "\",append = TRUE)";
							String errorMessage2 = "capture.output(cat(\"\n       \",result$output[[" + i	+ "]]$site[[" + j + "]]$blockLabelError2),file=\""+ outFileName + "\",append = TRUE)";
						
							rEngine.eval(errorMessage1);
							rEngine.eval(errorMessage2);
							rEngine.eval(outspace);
							rEngine.eval(outspace);
							rEngine.eval(outspace);
							
							break;
						}
						
						String manyNA1 = "result$output[["	+ i	+ "]]$site[[" + j + "]]$tooManyNAWarning";
						String manyNA2 = rEngine.eval(manyNA1).asString();
				
	//								System.out.println("exceededCheck: " + exceededCheck);
						
						if (manyNA2.equals("YES")) {
							String warningExceed = "capture.output(cat(\"\nToo many missing observations. Cannot proceed with the analysis.\n\n\n\"),file=\"" + outFileName + "\",append = TRUE)";
							rEngine.eval(warningExceed);
						} else {
							//display data summary
							String trialSumHead = "capture.output(cat(\"\nDATA SUMMARY:\n\n\"),file=\"" + outFileName + "\",append = TRUE)";
							String trialSumTable = "capture.output(result$output[[" + i + "]]$site[[" + j + "]]$funcTrialSum,file=\"" + outFileName + "\",append = TRUE)";
							String trialObsRead = "capture.output(cat(\"Number of observations read: \", result$output[["	+ i	+ "]]$site[[" + j + "]]$obsread[[1]],\"\n\"),file=\"" + outFileName + "\",append = TRUE)";
							String trialObsUsed = "capture.output(cat(\"Number of observations used: \", result$output[["	+ i	+ "]]$site[[" + j + "]]$obsused[[1]],\"\n\"),file=\""	+ outFileName + "\",append = TRUE)";
	
							rEngine.eval(trialSumHead);
							rEngine.eval(trialSumTable);
							rEngine.eval(outspace);
							rEngine.eval(trialObsRead);
							rEngine.eval(trialObsUsed);
	//									rEngine.eval(outspace);
							
							String exceeded = "result$output[["	+ i	+ "]]$site[[" + j + "]]$exceededWarning";
							String exceededCheck = rEngine.eval(exceeded).asString();
							rEngine.eval(exceeded);
							rEngine.eval(exceededCheck);
					
	//									System.out.println("exceededCheck: " + exceededCheck);
							
							if (exceededCheck.equals("YES")) {
								String warningExceed = "capture.output(cat(\"\n\n***\nERROR: The number of observations read in the data exceeds the size of a balanced data.\n       Please check if the column for block/replicate is properly labeled.\n***\n\n\"),file=\"" + outFileName + "\",append = TRUE)";
								rEngine.eval(warningExceed);
							} else {
								double responseRate =rEngine.eval("result$output[[" + i + "]]$site[[" + j + "]]$responseRate").asDouble();
								
								//display anova table if design is RCB
								if (design == "RCB") {
							        String anovaHead = "capture.output(cat(\"\n\nANOVA TABLE FOR THE EXPERIMENT:\n\n\"),file=\"" + outFileName + "\",append = TRUE)";
							        rEngine.eval(anovaHead);
							        
							        if (responseRate < 0.90) {
										String warningExceed = "capture.output(cat(\" ERROR: Too many missing values. Cannot perform ANOVA for balanced data.\n\"),file=\"" + outFileName + "\",append = TRUE)";
										rEngine.eval(warningExceed);
										
									} else {
										String ttcAnova1 = "capture.output(result$output[[" + i + "]]$site[[" + j + "]]$diallel1.anova,file=\"" + outFileName + "\",append = TRUE)";
										String ttcAnova2 = "capture.output(cat(\"-------\n\"),file=\"" + outFileName + "\",append = TRUE)";
										String ttcAnova3 = "capture.output(cat(\"REMARK:\", result$output[["	+ i	+ "]]$site[[" + j + "]]$anovaRemark1,\"\n\"),file=\""	+ outFileName + "\",append = TRUE)";
										
										rEngine.eval(ttcAnova1);
										rEngine.eval(ttcAnova2);
										rEngine.eval(ttcAnova3);
									}
								}
								
								//test on significance of cross effect
								String crossEffect1 = "capture.output(cat(\"\n\nTESTING FOR THE SIGNIFICANCE OF CROSS EFFECT: (Crosses = " + p1 + ":" + p2 + ")\n\"),file=\"" + outFileName + "\",append = TRUE)";
								rEngine.eval(crossEffect1);
								Double pvalue=0.00;
								
								if (design == "CRD") {
									String crossEffect2 = "capture.output(cat(\"\nModel:\", result$output[["	+ i	+ "]]$site[[" + j + "]]$formula1,\"\n\n\"),file=\""	+ outFileName + "\",append = TRUE)";
									String crossEffect3 = "capture.output(result$output[[" + i + "]]$site[[" + j + "]]$genoEffect.anova,file=\"" + outFileName + "\",append = TRUE)";
								
									rEngine.eval(crossEffect2);
									rEngine.eval(crossEffect3);
									
									pvalue = rEngine.eval("result$output[[" + i + "]]$site[[" + j + "]]$pValue").asDouble();
									System.out.println("pvalue: " + pvalue);
									
									//display matrix of means
									String matrixMeans1 = "capture.output(cat(\"\n\nMATRIX OF MEANS:\n\n\"),file=\"" + outFileName + "\",append = TRUE)";
									String matrixMeans2 = "capture.output(result$output[[" + i + "]]$site[[" + j + "]]$means.matrix,file=\"" + outFileName + "\",append = TRUE)";
									rEngine.eval(matrixMeans1);
									rEngine.eval(matrixMeans2);
									
//									String estimatedMissing1 = "result$output[["	+ i	+ "]]$site[[" + j + "]]$estimatedMissing";
//									String estimatedMissing2 = rEngine.eval(estimatedMissing1).asString();
									
//									if (estimatedMissing2.equals("TRUE")) {
										String anovaRemark1 = "capture.output(cat(\"-------\n\"),file=\"" + outFileName + "\",append = TRUE)";
										String anovaRemark2 = "capture.output(cat(\"REMARK:\", result$output[["	+ i	+ "]]$site[[" + j + "]]$anovaRemark2,\"\n\"),file=\""	+ outFileName + "\",append = TRUE)";
										
										rEngine.eval(anovaRemark1);
										rEngine.eval(anovaRemark2);
//									}
									
									//save matrix of means to a file
									String meansFileName2 = null;
									if (environment != "NULL") {
										meansFileName2 = "meansFileName2 <- paste(\"" + resultFolderPath + "\",\"matrixMeans_\", \"" + respvar[k] + "\", \"_\", result$output[[" + i + "]]$site[[" + j + "]]$env,\".csv\", sep=\"\")";
									} else {
										meansFileName2 = "meansFileName2 <- paste(\"" + resultFolderPath + "\",\"matrixMeans_\", \"" + respvar[k] + "\", \".csv\", sep=\"\")";
									}
									
									System.out.println(meansFileName2);
									
									String funcSavePredMeansCsv = "saveDataB1 <- try(write.table(result$output[[" + i + "]]$site[[" + j + "]]$means.matrix,file = meansFileName2 ,sep=\",\",col.names=NA, row.names=TRUE), silent=TRUE)";
									rEngine.eval(meansFileName2);
									rEngine.eval(funcSavePredMeansCsv);
									
									String runSuccessSavePredMeans = rEngine.eval("class(saveDataB1)").asString();
									if (runSuccessSavePredMeans != null && runSuccessSavePredMeans.equals("try-error")) {	
										System.out.println("save matrix of means: error");
										String checkError = "msg <- trimStrings(strsplit(saveDataB1, \":\")[[1]])";
										String checkError2 = "msg <- trimStrings(paste(strsplit(msg, \"\\n\")[[length(msg)]], collapse = \" \"))";
										String checkError3 ="msg <- gsub(\"\\\"\", \"\", msg)";
										String checkError4 = "capture.output(cat(\"\n***\nERROR in saving matrix of means to a file:\\n  \",msg, \"\n***\n\", sep = \"\"), file=\"" + outFileName + "\",append = TRUE)";
										rEngine.eval(checkError);
										rEngine.eval(checkError2);
										rEngine.eval(checkError3);
										rEngine.eval(checkError4);
									}
									
								} else {
									if (responseRate < 0.90) {
										String warningExceed = "capture.output(cat(\"\n ERROR: Too many missing values. Cannot perform the analysis.\n\"),file=\"" + outFileName + "\",append = TRUE)";
										rEngine.eval(warningExceed);
									} else {
										String crossEffect2 = "capture.output(cat(\"\nModel:\", result$output[["	+ i	+ "]]$site[[" + j + "]]$formula1,\"\n\"),file=\""	+ outFileName + "\",append = TRUE)";
										rEngine.eval(crossEffect2);
										
										String outAnovaTable2 = "library(lmerTest)";
										String outAnovaTable3 = "model1b <- lmer(formula(result$output[[" + i + "]]$site[[" + j + "]]$formula1), data = result$output[[" + i + "]]$site[[" + j + "]]$data, REML = T)";
										String outAnovaTable4 = "a.table <- anova(model1b)";
										String outAnovaTable5 = "pvalue <- formatC(as.numeric(format(a.table[1,6], scientific=FALSE)), format=\"f\")";
										String outAnovaTable6 = "a.table<-cbind(round(a.table[,1:5], digits=4),pvalue)";
										String outAnovaTable7 = "colnames(a.table)<-c(\"Df\", \"Sum Sq\", \"Mean Sq\", \"F value\", \"Denom\", \"Pr(>F)\")";
										String outAnovaTable8 = "capture.output(cat(\"Analysis of Variance Table with Satterthwaite Denominator Df\n\"),file=\"" + outFileName + "\",append = TRUE)";
										String outAnovaTable9 = "capture.output(a.table,file=\"" + outFileName + "\",append = TRUE)";
										String outAnovaTable10 = "detach(\"package:lmerTest\")";
										
										rEngine.eval(outAnovaTable2);
										rEngine.eval(outAnovaTable3);
										rEngine.eval(outAnovaTable4);
		
										pvalue = rEngine.eval("a.table[1,6]").asDouble();
										System.out.println("pvalue: " + pvalue);
										
										rEngine.eval(outAnovaTable5);
										rEngine.eval(outAnovaTable6);
										rEngine.eval(outAnovaTable7);
										rEngine.eval(outspace);
										rEngine.eval(outAnovaTable8);
										rEngine.eval(outAnovaTable9);
										rEngine.eval(outAnovaTable10);
										
										//display matrix of means
										String matrixMeans1 = "capture.output(cat(\"\n\nMATRIX OF MEANS:\n\n\"),file=\"" + outFileName + "\",append = TRUE)";
										String matrixMeans2 = "capture.output(result$output[[" + i + "]]$site[[" + j + "]]$means.matrix,file=\"" + outFileName + "\",append = TRUE)";
										rEngine.eval(matrixMeans1);
										rEngine.eval(matrixMeans2);
										
//										String estimatedMissing1 = "result$output[["	+ i	+ "]]$site[[" + j + "]]$estimatedMissing";
//										String estimatedMissing2 = rEngine.eval(estimatedMissing1).asString();
										
//										if (estimatedMissing2.equals("TRUE")) {
											String anovaRemark1 = "capture.output(cat(\"-------\n\"),file=\"" + outFileName + "\",append = TRUE)";
											String anovaRemark2 = "capture.output(cat(\"REMARK:\", result$output[["	+ i	+ "]]$site[[" + j + "]]$anovaRemark2,\"\n\"),file=\""	+ outFileName + "\",append = TRUE)";
											
											rEngine.eval(anovaRemark1);
											rEngine.eval(anovaRemark2);
//										}
										
										//save matrix of means to a file
										String meansFileName2 = null;
										if (environment != "NULL") {
											meansFileName2 = "meansFileName2 <- paste(\"" + resultFolderPath + "\",\"matrixMeans_\", \"" + respvar[k] + "\", \"_\", result$output[[" + i + "]]$site[[" + j + "]]$env,\".csv\", sep=\"\")";
										} else {
											meansFileName2 = "meansFileName2 <- paste(\"" + resultFolderPath + "\",\"matrixMeans_\", \"" + respvar[k] + "\", \".csv\", sep=\"\")";
										}
										
										System.out.println(meansFileName2);
										
										String funcSavePredMeansCsv = "saveDataB1 <- try(write.table(result$output[[" + i + "]]$site[[" + j + "]]$means.matrix,file = meansFileName2 ,sep=\",\",col.names=NA, row.names=TRUE), silent=TRUE)";
										rEngine.eval(meansFileName2);
										rEngine.eval(funcSavePredMeansCsv);
										
										String runSuccessSavePredMeans = rEngine.eval("class(saveDataB1)").asString();
										if (runSuccessSavePredMeans != null && runSuccessSavePredMeans.equals("try-error")) {	
											System.out.println("save matrix of means: error");
											String checkError = "msg <- trimStrings(strsplit(saveDataB1, \":\")[[1]])";
											String checkError2 = "msg <- trimStrings(paste(strsplit(msg, \"\\n\")[[length(msg)]], collapse = \" \"))";
											String checkError3 ="msg <- gsub(\"\\\"\", \"\", msg)";
											String checkError4 = "capture.output(cat(\"\n***\nERROR in saving matrix of means to a file:\\n  \",msg, \"\n***\n\", sep = \"\"), file=\"" + outFileName + "\",append = TRUE)";
											rEngine.eval(checkError);
											rEngine.eval(checkError2);
											rEngine.eval(checkError3);
											rEngine.eval(checkError4);
										}
									}
								}
								
								String meansComplete1 = "result$output[["	+ i	+ "]]$site[[" + j + "]]$meansComplete";
								String meansComplete2 = rEngine.eval(meansComplete1).asString();
								
								if (meansComplete2.equals("TRUE")) {
									//if cross effect is significant,
									double alphaNew = Double.parseDouble(alpha);
									
									if (pvalue < alphaNew) {
										String gcaAnovaHead = "capture.output(cat(\"\n\nANALYSIS OF VARIANCE:\n\n\"),file=\"" + outFileName + "\",append = TRUE)";
										rEngine.eval(gcaAnovaHead);
											
										if (responseRate >= 0.90 || design=="CRD") {
											//GCA and SCA anova table
											String gcaAnova1 = "capture.output(result$output[[" + i + "]]$site[[" + j + "]]$gcasca.anova,file=\"" + outFileName + "\",append = TRUE)";
											rEngine.eval(gcaAnova1);
											
											//estimates of effects
											String effects1 = "capture.output(cat(\"\n\nGENERAL COMBINING ABILITY EFFECTS (diagonal), SPECIFIC COMBINING\n\"),file=\"" + outFileName + "\",append = TRUE)";
											String effects2 = "capture.output(cat(\"ABILITY EFFECTS (above diagonal) AND RECIPROCAL EFFECTS (below diagonal):\n\n\"),file=\"" + outFileName + "\",append = TRUE)";
											String effects3 = "capture.output(result$output[[" + i + "]]$site[[" + j + "]]$gcasca.matrix,file=\"" + outFileName + "\",append = TRUE)";
											
											rEngine.eval(effects1);
											rEngine.eval(effects2);
											rEngine.eval(effects3);
											
											//save matrix of effects to a file
											String effectsFileName2 = null;
											if (environment != "NULL") {
												effectsFileName2 = "effectsFileName2 <- paste(\"" + resultFolderPath + "\",\"matrixEffects_\", \"" + respvar[k] + "\", \"_\", result$output[[" + i + "]]$site[[" + j + "]]$env,\".csv\", sep=\"\")";
											} else {
												effectsFileName2 = "effectsFileName2 <- paste(\"" + resultFolderPath + "\",\"matrixEffects_\", \"" + respvar[k] + "\", \".csv\", sep=\"\")";
											}
											
											String funcSaveEffectsCsv = "saveDataB2 <- try(write.table(result$output[[" + i + "]]$site[[" + j + "]]$gcasca.matrix,file = effectsFileName2 ,sep=\",\",col.names=NA, row.names=TRUE), silent=TRUE)";
											rEngine.eval(effectsFileName2);
											rEngine.eval(funcSaveEffectsCsv);
											
											String runSuccessSaveEffects = rEngine.eval("class(saveDataB2)").asString();
											if (runSuccessSaveEffects != null && runSuccessSaveEffects.equals("try-error")) {	
												System.out.println("save matrix of effects: error");
												String checkError = "msg <- trimStrings(strsplit(saveDataB2, \":\")[[1]])";
												String checkError2 = "msg <- trimStrings(paste(strsplit(msg, \"\\n\")[[length(msg)]], collapse = \" \"))";
												String checkError3 ="msg <- gsub(\"\\\"\", \"\", msg)";
												String checkError4 = "capture.output(cat(\"\n***\nERROR in saving matrix of effects to a file:\\n  \",msg, \"\n***\n\", sep = \"\"), file=\"" + outFileName + "\",append = TRUE)";
												rEngine.eval(checkError);
												rEngine.eval(checkError2);
												rEngine.eval(checkError3);
												rEngine.eval(checkError4);
											}
											
											//estimate of variance components
											String varComp1 = "capture.output(cat(\"\n\nESTIMATES OF VARIANCE COMPONENTS:\n\n\"),file=\"" + outFileName + "\",append = TRUE)";
											String varComp2 = "capture.output(result$output[[" + i + "]]$site[[" + j + "]]$var.components,file=\"" + outFileName + "\",append = TRUE)";
											
											rEngine.eval(varComp1);
											rEngine.eval(varComp2);
											
											//genetic variances
											String genVar1 = "capture.output(cat(\"\n\nESTIMATES OF GENETIC VARIANCE COMPONENTS:\n\"),file=\"" + outFileName + "\",append = TRUE)";
											String genVar2 = "capture.output(result$output[[" + i + "]]$site[[" + j + "]]$genvar.components,file=\"" + outFileName + "\",append = TRUE)";
											
											rEngine.eval(genVar1);
											rEngine.eval(genVar2);
											
											//standard errors
											String lsd1 = "capture.output(cat(\"\n\nTABLE OF STANDARD ERRORS AND LSDs:\n\n\"),file=\"" + outFileName + "\",append = TRUE)";
											String lsd2 = "capture.output(result$output[[" + i + "]]$site[[" + j + "]]$stderror.table,file=\"" + outFileName + "\",append = TRUE)";
											
											rEngine.eval(lsd1);
											rEngine.eval(lsd2);
											rEngine.eval(outspace);
											rEngine.eval(outspace);
											
										} else {
											String warningExceed = "capture.output(cat(\" ERROR: Too many missing values. Cannot perform test for significance of GCA and SCA effects.\n\"),file=\"" + outFileName + "\",append = TRUE)";
											String warningExceed2 = "capture.output(cat(\"        and estimation of genetic variance components.\n\n\"),file=\"" + outFileName + "\",append = TRUE)";
											rEngine.eval(warningExceed);
											rEngine.eval(warningExceed2);
											rEngine.eval(outspace);
										}// end of else (if (responseRate >= 0.90))
										
									} else {
										rEngine.eval(outspace);
										rEngine.eval(outspace);
										
									} // end of else (if (pvalue < alpha))
								} else {
									if (design == "CRD") {
										String meansNotComplete = "capture.output(cat(\"\n\n***\nERROR: At least one required P1xP2 combination is missing. Cannot perform the succeeding analyses.\n***\n\n\n\"),file=\"" + outFileName + "\",append = TRUE)";
										rEngine.eval(meansNotComplete);
									} else {
										rEngine.eval(outspace);
										rEngine.eval(outspace);
										rEngine.eval(outspace);
									}
								}
								
							} // end of else (if (exceededCheck.equals("YES")))
	
						}
						
					} // end of for (int m = 0; m < envLevelsLength; m++)
	
				} // end of for (int k = 0; k < respvar.length; k++)
				rEngine.eval(outspace);
				rEngine.eval(sep2);
			} // end of else (if (runSuccess != null && runSuccess.equals("try-error"))
	
		} // end of else (if (runSuccessData != null && runSuccessData.equals("notRun")))
		
		rEngineEnd();
	}
	
	@Override
	public void doDiallel4METest(String dataFileName, String outFileName, String resultFolderPath, String design, String[] respvar, String p1, String p2, 
			String rep, String block, String row, String column, String cross, String individual, String environment, String alpha){

		String respvarVector= inputTransform.createRVector(respvar);
		
		//defining the R statements
		String readData = "dataRead <- read.csv(\"" + dataFileName + "\", header = TRUE, na.strings = c(\"NA\",\".\",\" \",\"\"), blank.lines.skip=TRUE, sep = \",\")";
		String sinkIn = "sink(\"" + outFileName + "\")";
		
		String usedData = "cat(\"\nDATA FILE: " + dataFileName + "\n\")";
		
		String funcDiallel4TestME=null;
		if (design == "CRD") {
			funcDiallel4TestME = "result<-try(diallel4TestME(\"CRD\", \"dataRead\", " + respvarVector + ", \"" + p1 + "\", \"" + p2 + 
						"\", rep = NULL, block = NULL, row = NULL, column = NULL, cross = " + cross + ", individual = NULL, environment = \"" + environment + "\", alpha = " + alpha + "), silent = TRUE)";
		}
		if (design == "RCB") {
			funcDiallel4TestME = "result<-try(diallel4TestME(\"RCB\", \"dataRead\", " + respvarVector + ", \"" + p1 + "\", \"" + p2 + "\", rep = NULL, " +
						"block = \"" + block + "\", row = NULL, column = NULL, cross = " + cross + ", individual = NULL, environment = \"" + environment + "\", alpha = " + alpha + "), silent = TRUE)";;
		}
		if (design == "Alpha") {
			funcDiallel4TestME = "result<-try(diallel4TestME(\"Alpha\", \"dataRead\", " + respvarVector + ", \"" + p1 + "\", \"" + p2 + "\", rep = \"" 
						+ rep + "\", block = \"" + block + "\", row = NULL, column = NULL, cross = " + cross + ", individual = NULL, environment = \"" + environment + "\", alpha = " + alpha + "), silent = TRUE)";;
		}
		if (design == "RowColumn") {
			funcDiallel4TestME = "result<-try(diallel4TestME(\"RowColumn\", \"dataRead\", " + respvarVector + ", \"" + p1 + "\", \"" + p2 + "\", rep = \"" 
						+ rep + "\", block = NULL, row = \"" + row + "\", column = \"" + column + "\", cross = " + cross + ", individual = NULL, environment = \"" + environment + "\", alpha = " + alpha + "), silent = TRUE)";
		}
		
		String checkError = "if (class(result) == \"try-error\") {\n";
		checkError = checkError + "    msg <- trimStrings(strsplit(result, \":\")[[1]])\n";
		checkError = checkError + "    msg <- trimStrings(paste(strsplit(msg, \"\\n\")[[length(msg)]], collapse = \" \"))\n";
		checkError = checkError + "    msg <- gsub(\"\\\"\", \"\", msg)\n";
		checkError = checkError + "    cat(\"\n*** \nERROR in diallel4TestME function:\\n  \",msg, \"\n***\", sep = \"\")\n";
		checkError = checkError + "}";		
		String sinkOut = "sink()";
		
		System.out.println(readData);
		System.out.println(sinkIn);
		System.out.println(funcDiallel4TestME);
		System.out.println(checkError);
		System.out.println(sinkOut);
		
		//R statements passed on to the R engine
		rEngine.eval(readData);
		rEngine.eval(sinkIn);
		rEngine.eval(usedData);
		rEngine.eval(funcDiallel4TestME);
		rEngine.eval(checkError);
		rEngine.eval(sinkOut);
		
		//save matrix of means and effects to csv files
		String runSuccess = rEngine.eval("class(result)").asString();
		if (runSuccess != null && runSuccess.equals("try-error")) {	
			System.out.println("results of diallel4TestME: error");
		} else {
			for (int k = 0; k < respvar.length; k++) {
				int i = k + 1; // 1-relative index;
				
				String estimatedMissing1 = "result$output[[" + i + "]]$canEstimateMissing";
				String estimatedMissing2 = rEngine.eval(estimatedMissing1).asString();
				
				if (estimatedMissing2.equals("TRUE")) {
					//save matrix of means to a file
					String meansFileName2 = "meansFileName2 <- paste(\"" + resultFolderPath + "\",\"matrixMeans_\", \"" + respvar[k] + "\", \".csv\", sep=\"\")";
					String funcSavePredMeansCsv = "saveDataB1 <- try(write.table(result$output[[" + i + "]]$means.matrix,file = meansFileName2 ,sep=\",\",col.names=NA, row.names=TRUE), silent=TRUE)";
					rEngine.eval(meansFileName2);
					rEngine.eval(funcSavePredMeansCsv);
					
					System.out.println(meansFileName2);
					System.out.println(funcSavePredMeansCsv);
					
					String runSuccessSavePredMeans = rEngine.eval("class(saveDataB1)").asString();
					if (runSuccessSavePredMeans != null && runSuccessSavePredMeans.equals("try-error")) {	
						System.out.println("save matrix of means: error");
					}
					
					String effectsFileName2 = "effectsFileName2 <- paste(\"" + resultFolderPath + "\",\"matrixEffects_\", \"" + respvar[k] + "\", \".csv\", sep=\"\")";
					String funcSaveEffectsCsv = "saveDataB2 <- try(write.table(result$output[[" + i + "]]$gcasca.matrix,file = effectsFileName2 ,sep=\",\",col.names=NA, row.names=TRUE), silent=TRUE)";
					rEngine.eval(effectsFileName2);
					rEngine.eval(funcSaveEffectsCsv);
					
					System.out.println(effectsFileName2);
					System.out.println(funcSaveEffectsCsv);
					
					String runSuccessSaveEffects = rEngine.eval("class(saveDataB2)").asString();
					if (runSuccessSaveEffects != null && runSuccessSaveEffects.equals("try-error")) {	
						System.out.println("save matrix of effects: error");
					}
				}
			} //end of for (int k = 0; k < respvar.length; k++)
		}
		rEngineEnd();
	}
	
	@Override
	public void doSingleEnvironmentAnalysis(String dataFileName, String outFileName, String resultFolderPath, int designIndex, String[] respvar, String environment, String[] environmentLevels,
			String genotype, String block, String rep, String row, String column, boolean descriptiveStat, boolean varianceComponents, 
			boolean boxplotRawData, boolean histogramRawData, boolean heatmapResiduals, String heatmapRow, String heatmapColumn, boolean diagnosticPlot, 
			boolean genotypeFixed, boolean performPairwise, String pairwiseAlpha, String[] genotypeLevels, String[] controlLevels, boolean compareControl, boolean performAllPairwise,
			boolean genotypeRandom, boolean excludeControls, boolean genoPhenoCorrelation) {
		
		String respvarVector= inputTransform.createRVector(respvar);
//		String genotypeLevelsVector= inputTransform.createRVector(genotypeLevels);
		String controlLevelsVector= inputTransform.createRVector(controlLevels);
		boolean runningFixedSuccess =true;
		boolean runningRandomSuccess =true;
		boolean printAllOutputFixed =true;
		boolean printAllOutputRandom =true;
		
		try {				
			String designUsed = new String();
			String design = new String();
			switch (designIndex) {
				case 0: {
					designUsed = "Randomized Complete Block (RCB)"; 
					design = "RCB"; 
					break;
				}
				case 1: {
					designUsed = "Augmented RCB"; 
					design = "AugRCB";
					break;
				}
				case 2: {
					designUsed = "Augmented Latin Square"; 
					design = "AugLS";
					break;
				}
				case 3: {
					designUsed = "Alpha-Lattice"; 
					design = "Alpha";
					break;
				}
				case 4: {
					designUsed = "Row-Column"; 
					design = "RowCol";
					break;
				}
				case 5: {
					designUsed = "Latinized Alpha-Lattice"; 
					design = "LatinAlpha";
					break;
				}
				case 6: {
					designUsed = "Latinized Row-Column"; 
					design = "LatinRowCol";
					break;
				}
				default: {
					designUsed = "Randomized Complete Block (RCB)"; 
					design = "RCB";
					break;
				}
			}
			
			String readData = "data <- read.csv(\"" + dataFileName + "\", header = TRUE, na.strings = c(\"NA\",\".\",\" \",\"\"), blank.lines.skip=TRUE, sep = \",\")";
			System.out.println(readData);
			rEngine.eval(readData);
			String runSuccessData = rEngine.eval("data").asString();
			
			if (runSuccessData != null && runSuccessData.equals("notRun")) {	
				System.out.println("error");
				rEngine.eval("capture.output(cat(\"\n***Error reading data.***\n\"),file=\"" + outFileName + "\",append = FALSE)"); 
			}
			else {
				String setWd = "setwd(\"" + resultFolderPath + "\")";
				System.out.println(setWd);
				rEngine.eval(setWd);
			}
						
			String usedData = "capture.output(cat(\"\nDATA FILE: " + dataFileName + "\n\",file=\"" + outFileName + "\"))";
			String outFile = "capture.output(cat(\"\nSINGLE-ENVIRONMENT ANALYSIS\n\"),file=\"" + outFileName + "\",append = TRUE)";
			String usedDesign = "capture.output(cat(\"\nDESIGN: " + designUsed + "\n\n\"),file=\"" + outFileName + "\",append = TRUE)";
			String sep = "capture.output(cat(\"------------------------------\"),file=\"" + outFileName + "\",append = TRUE)";
			String sep2 = "capture.output(cat(\"==============================\n\"),file=\"" + outFileName + "\",append = TRUE)";
			String outspace = "capture.output(cat(\"\n\"),file=\"" + outFileName + "\",append = TRUE)"; 
			
			rEngine.eval(usedData);
			rEngine.eval(outFile);
			rEngine.eval(usedDesign);
			
			// OUTPUT
			// Genotype Fixed
			if (genotypeFixed) {
				String funcSsaFixed = null;
				String groupVars = null;
				if (environment == "NULL") {
					if (designIndex == 0 || designIndex == 1){
						funcSsaFixed = "ssa1 <- try(ssa.test(\"" + design + "\",data,"+ respvarVector + ",\"" + genotype + "\",\"" + block+ "\",column=NULL, rep=NULL," + environment+ ", is.random = FALSE), silent = TRUE)";
						groupVars = "c(\"" + genotype + "\", \"" + block + "\")";
					} else if (designIndex == 2) {
						funcSsaFixed = "ssa1 <- try(ssa.test(\"" + design + "\",data,"+ respvarVector + ",\"" + genotype + "\",\"" + row+ "\",\"" + column + "\", rep=NULL," + environment+ ", is.random = FALSE), silent = TRUE)";
						groupVars = "c(\"" + genotype + "\", \"" + row + "\", \"" + column + "\")";
					} else if (designIndex == 3 || designIndex == 5) {
						funcSsaFixed = "ssa1 <- try(ssa.test(\"" + design + "\",data,"+ respvarVector + ",\"" + genotype + "\",\"" + block+ "\",column=NULL,\"" + rep + "\"," + environment+ ", is.random = FALSE), silent = TRUE)";
						groupVars = "c(\"" + genotype + "\", \"" + block + "\", \"" + rep + "\")";
					} else if (designIndex == 4 || designIndex == 6) {
						funcSsaFixed = "ssa1 <- try(ssa.test(\"" + design + "\",data,"+ respvarVector + ",\"" + genotype + "\",\"" + row+ "\",\"" + column + "\",\"" + rep + "\","+ environment + ", is.random = FALSE), silent = TRUE)";
						groupVars = "c(\"" + genotype + "\", \"" + rep + "\", \"" + row + "\", \"" + column + "\")";
					}
				} else {
					if (designIndex == 0 || designIndex == 1){
						funcSsaFixed = "ssa1 <- try(ssa.test(\"" + design + "\",data,"+ respvarVector + ",\"" + genotype + "\",\"" + block+ "\",column=NULL, rep=NULL,\"" + environment+ "\", is.random = FALSE), silent = TRUE)";
						groupVars = "c(\"" + genotype + "\", \"" + block + "\")";
					} else if (designIndex == 2) {
						funcSsaFixed = "ssa1 <- try(ssa.test(\"" + design + "\",data,"+ respvarVector + ",\"" + genotype + "\",\"" + row+ "\",\"" + column + "\", rep=NULL,\"" + environment+ "\", is.random = FALSE), silent = TRUE)";
						groupVars = "c(\"" + genotype + "\", \"" + row + "\", \"" + column + "\")";
					} else if (designIndex == 3 || designIndex == 5) {
						funcSsaFixed = "ssa1 <- try(ssa.test(\"" + design + "\",data,"+ respvarVector + ",\"" + genotype + "\",\"" + block+ "\",column=NULL,\"" + rep + "\",\"" + environment+ "\", is.random = FALSE), silent = TRUE)";
						groupVars = "c(\"" + genotype + "\", \"" + block + "\", \"" + rep + "\")";
					} else if (designIndex == 4 || designIndex == 6) {
						funcSsaFixed = "ssa1 <- try(ssa.test(\"" + design + "\",data,"+ respvarVector + ",\"" + genotype + "\",\"" + row+ "\",\"" + column + "\",\"" + rep + "\",\""+ environment + "\", is.random = FALSE), silent = TRUE)";
						groupVars = "c(\"" + genotype + "\", \"" + rep + "\", \"" + row + "\", \"" + column + "\")";
					}
				}
				String fixedHead = "capture.output(cat(\"GENOTYPE AS: Fixed\n\"),file=\""+ outFileName + "\",append = TRUE)";
				rEngine.eval(funcSsaFixed);
				rEngine.eval(sep2);
				rEngine.eval(fixedHead);
				rEngine.eval(sep2);
				rEngine.eval(outspace);
				
				System.out.println(funcSsaFixed);

				String runSuccess = rEngine.eval("class(ssa1)").asString();
				if (runSuccess != null && runSuccess.equals("try-error")) {	
					System.out.println("ssa.test: error");
					String checkError = "msg <- trimStrings(strsplit(ssa1, \":\")[[1]])";
					String checkError2 = "msg <- trimStrings(paste(strsplit(msg, \"\\n\")[[length(msg)]], collapse = \" \"))";
					String checkError3 ="msg <- gsub(\"\\\"\", \"\", msg)";
					String checkError4 = "capture.output(cat(\"*** \nERROR in ssa.test function:\\n  \",msg, \"\n***\n\n\", sep = \"\"), file=\"" + outFileName + "\",append = TRUE)";
					rEngine.eval(checkError);
					rEngine.eval(checkError2);
					rEngine.eval(checkError3);
					rEngine.eval(checkError4);
					
					runningFixedSuccess=false;

				}
				else {
					for (int k = 0; k < respvar.length; k++) {
						int i = k + 1; // 1-relative index;
						String respVarHead = "capture.output(cat(\"\nRESPONSE VARIABLE: " + respvar[k] + "\n\"),file=\"" + outFileName + "\",append = TRUE)";
						rEngine.eval(sep);
						rEngine.eval(respVarHead);
						rEngine.eval(sep);
						rEngine.eval(outspace);
						
						// optional output: descriptive statistics
						if (descriptiveStat) {
							String funcDesc = null;
							if (environment=="NULL") {
								funcDesc = "outDesc <- try(DescriptiveStatistics(data, \"" + respvar[k] + "\", " + environment + "), silent=TRUE)";
							} else {
								funcDesc = "outDesc <- try(DescriptiveStatistics(data, \"" + respvar[k] + "\", \"" + environment + "\"), silent=TRUE)";
							}
							System.out.println(funcDesc);
							rEngine.eval(funcDesc);
							
							String outDescStat = "capture.output(cat(\"DESCRIPTIVE STATISTICS:\n\n\"),file=\"" + outFileName + "\",append = TRUE)"; 
							String outDescStat2 = "capture.output(outDesc,file=\"" + outFileName + "\",append = TRUE)";
							
	  						String runSuccessDescStat = rEngine.eval("class(outDesc)").asString();	
							if (runSuccessDescStat != null && runSuccessDescStat.equals("try-error")) {	
								System.out.println("desc stat: error");
								String checkError = "msg <- trimStrings(strsplit(outDesc, \":\")[[1]])";
								String checkError2 = "msg <- trimStrings(paste(strsplit(msg, \"\\n\")[[length(msg)]], collapse = \" \"))";
								String checkError3 ="msg <- gsub(\"\\\"\", \"\", msg)";
								String checkError4 = "capture.output(cat(\"*** \nERROR in DescriptiveStatistics function:\\n  \",msg, \"\n***\n\n\", sep = \"\"), file=\"" + outFileName + "\",append = TRUE)";
								rEngine.eval(checkError);
								rEngine.eval(checkError2);
								rEngine.eval(checkError3);
								rEngine.eval(checkError4);
							} 
							else {
								rEngine.eval(outspace);
								rEngine.eval(outDescStat);
								rEngine.eval(outDescStat2);
								rEngine.eval(outspace);
							}
						}
						int envLevelsLength=0;
						if (environment == "NULL") {
							envLevelsLength = 1;
						} else {
							envLevelsLength = environmentLevels.length;
						}
						
						for (int m = 0; m < envLevelsLength; m++) { // no of envts or sites
							printAllOutputFixed=true;
							int j = m + 1; // 1-relative index;
							if (environment != "NULL") {
								String envtHead = "capture.output(cat(\"\nANALYSIS FOR: "+ environment + "\", \" = \" ,ssa1$output[[" + i	+ "]]$site[[" + j + "]]$env,\"\n\"),file=\""+ outFileName + "\",append = TRUE)";
								rEngine.eval(sep);
								rEngine.eval(envtHead);
								rEngine.eval(sep);
								rEngine.eval(outspace);
							}
							
							//check if the data has too many missing observation
							double nrowData=rEngine.eval("ssa1$output[[" + i + "]]$site[[" + j + "]]$responseRate").asDouble();
							if (nrowData < 0.80) {
								String allNAWarning = rEngine.eval("ssa1$output[[" + i + "]]$site[[" + j + "]]$manyNAWarning").asString();
								String printError1 = "capture.output(cat(\"***\\n\"), file=\"" + outFileName + "\",append = TRUE)";
								String printError2 = "capture.output(cat(\"ERROR:\\n\"), file=\"" + outFileName + "\",append = TRUE)";
								String printError3 = "capture.output(cat(\"" + allNAWarning + "\\n\"), file=\"" + outFileName + "\",append = TRUE)";
								
								rEngine.eval(outspace);
								rEngine.eval(printError1);
								rEngine.eval(printError2);
								rEngine.eval(printError3);
								rEngine.eval(printError1);
								rEngine.eval(outspace);
								rEngine.eval(outspace);
								printAllOutputFixed=false;
								
							} else {
								String lmerRun=rEngine.eval("ssa1$output[[" + i + "]]$site[[" + j + "]]$lmerRun").asString();
								if (lmerRun.equals("ERROR")) {
									String lmerError = rEngine.eval("ssa1$output[[" + i + "]]$site[[" + j + "]]$lmerError").asString();
									String printError1 = "capture.output(cat(\"***\\n\"), file=\"" + outFileName + "\",append = TRUE)";
									String printError2 = "capture.output(cat(\"ERROR:\\n\"), file=\"" + outFileName + "\",append = TRUE)";
									String printError3 = "capture.output(cat(\"" + lmerError + "\\n\"), file=\"" + outFileName + "\",append = TRUE)";
									
									rEngine.eval(outspace);
									rEngine.eval(printError1);
									rEngine.eval(printError2);
									rEngine.eval(printError3);
									rEngine.eval(printError1);
									rEngine.eval(outspace);
									rEngine.eval(outspace);
									printAllOutputFixed=false;
								}
							}
							
							if (printAllOutputFixed) {
								// default output: trial summary
								String funcTrialSum = "funcTrialSum <- try(class.information(" + groupVars + ",ssa1$output[[" + i + "]]$site[[" + j + "]]$data), silent=TRUE)";
								String trialSumHead = "capture.output(cat(\"\nDATA SUMMARY:\n\n\"),file=\"" + outFileName + "\",append = TRUE)";
								String trialObsRead = "capture.output(cat(\"Number of observations read: \", ssa1$output[["	+ i	+ "]]$site[[" + j + "]]$obsread[[1]],\"\n\"),file=\"" + outFileName + "\",append = TRUE)";
								String trialObsUsed = "capture.output(cat(\"Number of observations used: \", ssa1$output[["	+ i	+ "]]$site[[" + j + "]]$obsused[[1]],\"\n\n\"),file=\""	+ outFileName + "\",append = TRUE)";
								String trialSum = "capture.output(funcTrialSum,file=\"" + outFileName + "\",append = TRUE)";

								rEngine.eval(funcTrialSum);
//								System.out.println(funcTrialSum);
		
								String runSuccessTS = rEngine.eval("class(funcTrialSum)").asString();
								if (runSuccessTS != null && runSuccessTS.equals("try-error")) {	
									System.out.println("class info: error");
									String checkError = "msg <- trimStrings(strsplit(funcTrialSum, \":\")[[1]])";
									String checkError2 = "msg <- trimStrings(paste(strsplit(msg, \"\\n\")[[length(msg)]], collapse = \" \"))";
									String checkError3 ="msg <- gsub(\"\\\"\", \"\", msg)";
									String checkError4 = "capture.output(cat(\"*** \nERROR in class.information function:\\n  \",msg, \"\n***\n\n\", sep = \"\"), file=\"" + outFileName + "\",append = TRUE)";
									rEngine.eval(checkError);
									rEngine.eval(checkError2);
									rEngine.eval(checkError3);
									rEngine.eval(checkError4);
								}
								else {
									rEngine.eval(trialSumHead);
									rEngine.eval(trialObsRead);
									rEngine.eval(trialObsUsed);
									rEngine.eval(trialSum);
									rEngine.eval(outspace);
								}	
								
								// optional output: variance components
								if (varianceComponents) {
									String outVarComp = "capture.output(cat(\"\nVARIANCE COMPONENTS TABLE:\n\n\"),file=\"" + outFileName + "\",append = TRUE)";
									String outVarComp2 = "capture.output(ssa1$output[[" + i + "]]$site[[" + j + "]]$varcomp.table,file=\"" + outFileName + "\",append = TRUE)";
		
									rEngine.eval(outVarComp);
									rEngine.eval(outVarComp2);
									rEngine.eval(outspace);
								}
		
								//default output: test for genotypic effect
								String outAnovaTable1 = "capture.output(cat(\"\nTESTING FOR THE SIGNIFICANCE OF GENOTYPIC EFFECT:\n\"),file=\"" + outFileName + "\",append = TRUE)";
								String outAnovaTable2 = "library(lmerTest)";
								String outAnovaTable3 = "model1b <- lmer(formula(ssa1$output[[" + i + "]]$site[[" + j + "]]$formula1), data = ssa1$output[[" + i + "]]$site[[" + j + "]]$data, REML = T)";
								String outAnovaTable4 = "a.table <- anova(model1b)";
								String outAnovaTable5 = "pvalue <- formatC(as.numeric(format(a.table[1,6], scientific=FALSE)), format=\"f\")";
								String outAnovaTable6 = "a.table<-cbind(round(a.table[,1:5], digits=4),pvalue)";
								String outAnovaTable7 = "colnames(a.table)<-c(\"Df\", \"Sum Sq\", \"Mean Sq\", \"F value\", \"Denom\", \"Pr(>F)\")";
								String outAnovaTable8 = "capture.output(cat(\"Analysis of Variance Table with Satterthwaite Denominator Df\n\"),file=\"" + outFileName + "\",append = TRUE)";
								String outAnovaTable9 = "capture.output(a.table,file=\"" + outFileName + "\",append = TRUE)";
								String outAnovaTable10 = "detach(\"package:lmerTest\")";
								
								rEngine.eval(outAnovaTable1);
								rEngine.eval(outAnovaTable2);
								rEngine.eval(outAnovaTable3);
								rEngine.eval(outAnovaTable4);
								rEngine.eval(outAnovaTable5);
								rEngine.eval(outAnovaTable6);
								rEngine.eval(outAnovaTable7);
								rEngine.eval(outspace);
								rEngine.eval(outAnovaTable8);
								rEngine.eval(outAnovaTable9);
								rEngine.eval(outspace);
								rEngine.eval(outAnovaTable10);
								
								// default output: test for genotypic effect
//								String outAnovaTable1b = "capture.output(cat(\"\nTESTING FOR THE SIGNIFICANCE OF GENOTYPIC EFFECT:\n\n\"),file=\"" + outFileName + "\",append = TRUE)";
//								String outAnovaTable2b = "library(pbkrtest)";
//								String outAnovaTable3b = "model1b <- lmer(formula(ssa1$output[[" + i + "]]$site[[" + j + "]]$formula1), data = ssa1$output[[" + i + "]]$site[[" + j + "]]$data, REML = T)";
//								String outAnovaTable4b = "model2b <- lmer(formula(ssa1$output[[" + i + "]]$site[[" + j + "]]$formula2), data = ssa1$output[[" + i + "]]$site[[" + j + "]]$data, REML = T)";
//								String outAnovaTable5b = "anova.table1 <- KRmodcomp(model1b, model2b)[[1]][1,]";
//								String outAnovaTable6b = "anova.table1 <- anova.table1[-c(4)]";
//								String outAnovaTable7b = "rownames(anova.table1) <- " + genotype;
//								String outAnovaTable8b = "colnames(anova.table1) <- c(\"F value\", \"Num df\", \"Denom df\", \"Pr(>F)\")";
//								String outAnovaTable9b = "anova.table1[1, \"F value\"] <- format(round(anova.table1[1, \"F value\"],2), digits=2, nsmall=2, scientific=FALSE)";
//								String outAnovaTable10b = "anova.table1[1, \"Pr(>F)\"] <- formatC(as.numeric(format(anova.table1[1, \"Pr(>F)\"], scientific=FALSE)), format=\"f\")";
//								String outAnovaTable11b = "capture.output(anova.table1,file=\"" + outFileName + "\",append = TRUE)";
//								String outAnovaTable12b = "detach(\"package:pbkrtest\")";
//								
//								rEngine.eval(outAnovaTable1b);
//								rEngine.eval(outAnovaTable2b);
//								rEngine.eval(outAnovaTable3b);
//								rEngine.eval(outAnovaTable4b);
//								rEngine.eval(outAnovaTable5b);
//								rEngine.eval(outAnovaTable6b);
//								rEngine.eval(outAnovaTable7b);
//								rEngine.eval(outAnovaTable8b);
//								rEngine.eval(outAnovaTable9b);
//								rEngine.eval(outAnovaTable10b);
//								rEngine.eval(outAnovaTable11b);
//								rEngine.eval(outAnovaTable12b);
//								rEngine.eval(outspace);
								
//								String outAnovaTable1b = "capture.output(cat(\"\nTESTING FOR THE SIGNIFICANCE OF GENOTYPIC EFFECT:\n\n\"),file=\"" + outFileName + "\",append = TRUE)";
//								String outAnovaTable2b = "capture.output(ssa1$output[[" + i + "]]$site[[" + j + "]]$model.comparison,file=\"" + outFileName + "\",append = TRUE)";
//								
//								rEngine.eval(outAnovaTable1b);
//								rEngine.eval(outAnovaTable2b);
//								rEngine.eval(outspace);
		
								//default output: LSMeans
								String outDescStat = "capture.output(cat(\"\nGENOTYPE LSMEANS AND STANDARD ERRORS:\n\n\"),file=\"" + outFileName + "\",append = TRUE)";
								String outDescStat2 = "capture.output(ssa1$output[[" + i + "]]$site[[" + j + "]]$summary.statistic,file=\"" + outFileName + "\",append = TRUE)"; 
		
								rEngine.eval(outDescStat);
								rEngine.eval(outDescStat2);
								rEngine.eval(outspace);
								
								//default output: standard error of the differences
								String outsedTable = "capture.output(cat(\"\nSTANDARD ERROR OF THE DIFFERENCE (SED):\n\n\"),file=\"" + outFileName + "\",append = TRUE)";
								String outsedTable2 = "capture.output(ssa1$output[[" + i + "]]$site[[" + j + "]]$sedTable,file=\"" + outFileName + "\",append = TRUE)";

								rEngine.eval(outsedTable);
								rEngine.eval(outsedTable2);
								rEngine.eval(outspace);
							
								
								if (performPairwise) {
									
									double pairwiseSig = Double.valueOf(pairwiseAlpha);
									
//									rEngine.rniAssign("trmt.levels",	rEngine.rniPutStringArray(genotypeLevels),0); // a string array from OptionsPage
									
										if (compareControl) {
//											rEngine.rniAssign("controlLevels",rEngine.rniPutStringArray(controlLevels),0); // a string array from OptionsPage
																		
											String funcPwC = "pwControl <- try(ssa.pairwise(ssa1$output[[" + i + "]]$site[["	+ j	+ "]]$model, type = \"Dunnett\", alpha = "	+ pairwiseSig + ", control.level = " + controlLevelsVector + "), silent=TRUE)";
											String outCompareControl = "capture.output(cat(\"\nSIGNIFICANT PAIRWISE COMPARISONS (IF ANY): \nCompared with control(s)\n\n\"),file=\"" + outFileName	+ "\",append = TRUE)";
											String outCompareControl2n = "capture.output(pwControl$result,file=\""	+ outFileName	+ "\",append = TRUE)";
											String outCompareControl3n = "capture.output(cat(\"\n\n\"),file=\"" + outFileName	+ "\",append = TRUE)";
											System.out.println(funcPwC);
											rEngine.eval(funcPwC);
											rEngine.eval(outCompareControl);
											
											
					  						String runSuccessPwC = rEngine.eval("class(pwControl)").asString();	
											if (runSuccessPwC != null && runSuccessPwC.equals("try-error")) {	
												System.out.println("compare with control: error");
												String checkError = "msg <- trimStrings(strsplit(pwControl, \":\")[[1]])";
												String checkError2 = "msg <- trimStrings(paste(strsplit(msg, \"\\n\")[[length(msg)]], collapse = \" \"))";
												String checkError3 ="msg <- gsub(\"\\\"\", \"\", msg)";
												String checkError4 = "capture.output(cat(\"*** \nERROR in ssa.pairwise function:\\n  \",msg, \"\n***\n\n\", sep = \"\"), file=\"" + outFileName + "\",append = TRUE)";
												rEngine.eval(checkError);
												rEngine.eval(checkError2);
												rEngine.eval(checkError3);
												rEngine.eval(checkError4);
											}
											else {
												
												rEngine.eval(outCompareControl2n);
												
												// display warning generated by checkTest in ssa.test
												String warningControlTest = rEngine.eval("pwControl$controlTestWarning").asString();
												
												if (!warningControlTest.equals("NONE")) {
													String warningCheckTest2 = "capture.output(cat(\"----- \nNOTE:\\n\"), file=\"" + outFileName + "\",append = TRUE)";
													String warningCheckTest3 = "capture.output(cat(\"" + warningControlTest + "\\n\"), file=\"" + outFileName + "\",append = TRUE)";
													
													rEngine.eval(warningCheckTest2);
													rEngine.eval(warningCheckTest3);
												}
												
												rEngine.eval(outCompareControl3n);
												
												System.out.println("pairwise control test:" + warningControlTest); 
												
											}
										} else if (performAllPairwise) {
											String outPerformAllPairwise = "capture.output(cat(\"\nSIGNIFICANT PAIRWISE COMPARISONS (IF ANY): \n\n\"),file=\""	+ outFileName	+ "\",append = TRUE)";
											rEngine.eval(outPerformAllPairwise);
											if (genotypeLevels.length > 0 && genotypeLevels.length < 16) {
												String funcPwAll = "pwAll <- try(ssa.pairwise(ssa1$output[[" + i + "]]$site[[" + j + "]]$model, type = \"Tukey\", alpha = "+ pairwiseSig + ", control.level = NULL), silent=TRUE)";
												String outPerformAllPairwise2 = "capture.output(pwAll$result,file=\"" + outFileName + "\",append = TRUE)";
												String outPerformAllPairwise3 = "capture.output(cat(\"\n\"),file=\""	+ outFileName	+ "\",append = TRUE)";
												rEngine.eval(funcPwAll);
//												System.out.println(funcPwAll);
		
												String runSuccessPwAll = rEngine.eval("class(pwAll)").asString();
												if (runSuccessPwAll != null && runSuccessPwAll.equals("try-error")) {	
													System.out.println("all pairwise: error");
													String checkError = "msg <- trimStrings(strsplit(pwAll, \":\")[[1]])";
													String checkError2 = "msg <- trimStrings(paste(strsplit(msg, \"\\n\")[[length(msg)]], collapse = \" \"))";
													String checkError3 ="msg <- gsub(\"\\\"\", \"\", msg)";
													String checkError4 = "capture.output(cat(\"*** \nERROR in ssa.pairwise function:\\n  \",msg, \"\n***\n\n\", sep = \"\"), file=\"" + outFileName + "\",append = TRUE)";
													rEngine.eval(checkError);
													rEngine.eval(checkError2);
													rEngine.eval(checkError3);
													rEngine.eval(checkError4);
												}
												else {
													rEngine.eval(outPerformAllPairwise2);
													rEngine.eval(outPerformAllPairwise3);
												}
											} else {
												String nLevelsLarge = "capture.output(cat(\"***\nExceeded maximum number of genotypes that can be compared. \n***\n\n\"),file=\"" + outFileName + "\",append = TRUE)";
												rEngine.eval(nLevelsLarge);
											}
										}
								} else {
									rEngine.eval(outspace);
								}
							}
							
						} // end of for loop for diff envts
					}

					//default output: save the means, standard error of the mean, variance and no. of reps in a file
					String checkMeanSSE = rEngine.eval("ssa1$meansseWarning").asString();
					String checkVarRep = rEngine.eval("ssa1$varrepWarning").asString();
					System.out.println("checkMeanSSE: " + checkMeanSSE);
					System.out.println("checkVarRep: " + checkVarRep);
					
					if (checkMeanSSE.equals("empty") | checkVarRep.equals("empty")) {
						System.out.println("Saving means not done.");
					} else {
						String meansFileName = "meansFileName <- paste(\"" + resultFolderPath + "\",\"summaryStats.csv\", sep=\"\")";
						String funcSaveSesVarRep=null;
						if (environment=="NULL") {
							funcSaveSesVarRep = "meansVar <- merge(ssa1$meansse,ssa1$varrep, by = \"EnvLevel\")";
						} else {
							funcSaveSesVarRep = "meansVar <- merge(ssa1$meansse,ssa1$varrep, by = \"" + environment + "\")";
						}
						String funcSaveSesVarRepCsv = "saveMeans <- try(write.table(meansVar,file = meansFileName ,sep=\",\",row.names=FALSE), silent=TRUE)";

						rEngine.eval(meansFileName);
						rEngine.eval(funcSaveSesVarRep);
						rEngine.eval(funcSaveSesVarRepCsv);
						
						String runSuccessSaveMeansSes = rEngine.eval("class(saveMeans)").asString();
						if (runSuccessSaveMeansSes != null && runSuccessSaveMeansSes.equals("try-error")) {	
							System.out.println("saving means file: error");
							String checkError = "msg <- trimStrings(strsplit(saveMeans, \":\")[[1]])";
							String checkError2 = "msg <- trimStrings(paste(strsplit(msg, \"\\n\")[[length(msg)]], collapse = \" \"))";
							String checkError3 ="msg <- gsub(\"\\\"\", \"\", msg)";
							String checkError4 = "capture.output(cat(\"*** \nERROR in saving means file:\\n  \",msg, \"\n***\n\n\", sep = \"\"), file=\"" + outFileName + "\",append = TRUE)";
							rEngine.eval(checkError);
							rEngine.eval(checkError2);
							rEngine.eval(checkError3);
							rEngine.eval(checkError4);
						} 
					}
					
					//diagnostic plots for genotype fixed
					if (diagnosticPlot) {
						String diagPlotsFunc=null;
						if (environment=="NULL") {
							diagPlotsFunc = "diagPlots <- try(graph.sea.diagplots(data, " + respvarVector + ", env = " + environment + ", is.random = FALSE, ssa1), silent=TRUE)";
						} else {
							diagPlotsFunc = "diagPlots <- try(graph.sea.diagplots(data, " + respvarVector + ", env = \"" + environment + "\", is.random = FALSE, ssa1), silent=TRUE)";
						}
						System.out.println(diagPlotsFunc);
						rEngine.eval(diagPlotsFunc);
						
						String runSuccessDiagPlots = rEngine.eval("class(diagPlots)").asString();
						if (runSuccessDiagPlots != null && runSuccessDiagPlots.equals("try-error")) {	
							System.out.println("diagnostic plots(genotype fixed): error");
							String checkError = "msg <- trimStrings(strsplit(diagPlots, \":\")[[1]])";
							String checkError2 = "msg <- trimStrings(paste(strsplit(msg, \"\\n\")[[length(msg)]], collapse = \" \"))";
							String checkError3 ="msg <- gsub(\"\\\"\", \"\", msg)";
							String checkError4 = "capture.output(cat(\"*** \nERROR in graph.sea.diagplots function:\\n  \",msg, \"\n***\n\n\", sep = \"\"), file=\"" + outFileName + "\",append = TRUE)";
							rEngine.eval(checkError);
							rEngine.eval(checkError2);
							rEngine.eval(checkError3);
							rEngine.eval(checkError4);
						}
					}
				}  
			} // end of if fixed
				
  
			// Genotype Random
			if (genotypeRandom == true) {
				String funcSsaRandom = null;
				String groupVars = null;

				if (excludeControls) {
					if (environment == "NULL") {
						if (designIndex == 0){
							funcSsaRandom = "ssa2 <- try(ssa.test(\"" + design + "\",data," + respvarVector + ",\"" + genotype + "\",\"" + block + "\",column=NULL, rep=NULL," + environment + ", is.random = TRUE, excludeCheck=TRUE, checkList= " + controlLevelsVector + "), silent=TRUE)";
							groupVars = "c(\"" + genotype + "\", \"" + block + "\")";
						} else if (designIndex == 1){
							funcSsaRandom = "ssa2 <- try(ssa.test(\"" + design + "\",data," + respvarVector + ",\"" + genotype + "\",\"" + block + "\",column=NULL, rep=NULL," + environment + ", is.random = TRUE, excludeCheck=TRUE, checkList= NULL), silent=TRUE)";
							groupVars = "c(\"" + genotype + "\", \"" + block + "\")";
						} else if (designIndex == 2) {
							funcSsaRandom = "ssa2 <- try(ssa.test(\"" + design + "\",data," + respvarVector + ",\"" + genotype + "\",\"" + row + "\",\"" + column + "\", rep=NULL," + environment + ", is.random = TRUE, excludeCheck=TRUE, checkList= NULL), silent=TRUE)";
							groupVars = "c(\"" + genotype + "\", \"" + row + "\", \"" + column + "\")";
						} else if (designIndex == 3 || designIndex == 5) {
							funcSsaRandom = "ssa2 <- try(ssa.test(\"" + design + "\",data," + respvarVector + ",\"" + genotype + "\",\"" + block + "\",column=NULL,\"" + rep + "\"," + environment + ", is.random = TRUE, excludeCheck=TRUE, checkList= " + controlLevelsVector + "), silent=TRUE)";
							groupVars = "c(\"" + genotype + "\", \"" + block + "\", \"" + rep + "\")";
						} else if (designIndex == 4 || designIndex == 6) {
							funcSsaRandom = "ssa2 <- try(ssa.test(\"" + design + "\",data," + respvarVector + ",\"" + genotype + "\",\"" + row + "\",\"" + column + "\",\"" + rep + "\"," + environment + ", is.random = TRUE, excludeCheck=TRUE, checkList= " + controlLevelsVector + "), silent=TRUE)";
							groupVars = "c(\"" + genotype + "\", \"" + rep + "\", \"" + row + "\", \"" + column + "\")";
						}
					} else {
						if (designIndex == 0){
							funcSsaRandom = "ssa2 <- try(ssa.test(\"" + design + "\",data," + respvarVector + ",\"" + genotype + "\",\"" + block + "\",column=NULL, rep=NULL,\"" + environment + "\", is.random = TRUE, excludeCheck=TRUE, checkList= " + controlLevelsVector + "), silent=TRUE)";
							groupVars = "c(\"" + genotype + "\", \"" + block + "\")";
						} else if (designIndex == 1) {
							funcSsaRandom = "ssa2 <- try(ssa.test(\"" + design + "\",data," + respvarVector + ",\"" + genotype + "\",\"" + block + "\",column=NULL, rep=NULL,\"" + environment + "\", is.random = TRUE, excludeCheck=TRUE, checkList= NULL), silent=TRUE)";
							groupVars = "c(\"" + genotype + "\", \"" + block + "\")";
						} else if (designIndex == 2) {
							funcSsaRandom = "ssa2 <- try(ssa.test(\"" + design + "\",data," + respvarVector + ",\"" + genotype + "\",\"" + row + "\",\"" + column + "\", rep=NULL,\"" + environment + "\", is.random = TRUE, excludeCheck=TRUE, checkList= NULL), silent=TRUE)";
							groupVars = "c(\"" + genotype + "\", \"" + row + "\", \"" + column + "\")";
						} else if (designIndex == 3 || designIndex == 5) {
							funcSsaRandom = "ssa2 <- try(ssa.test(\"" + design + "\",data," + respvarVector + ",\"" + genotype + "\",\"" + block + "\",column=NULL,\"" + rep + "\",\"" + environment + "\", is.random = TRUE, excludeCheck=TRUE, checkList= " + controlLevelsVector + "), silent=TRUE)";
							groupVars = "c(\"" + genotype + "\", \"" + block + "\", \"" + rep + "\")";
						} else if (designIndex == 4 || designIndex == 6) {
							funcSsaRandom = "ssa2 <- try(ssa.test(\"" + design + "\",data," + respvarVector + ",\"" + genotype + "\",\"" + row + "\",\"" + column + "\",\"" + rep + "\",\"" + environment + "\", is.random = TRUE, excludeCheck=TRUE, checkList= " + controlLevelsVector + "), silent=TRUE)";
							groupVars = "c(\"" + genotype + "\", \"" + rep + "\", \"" + row + "\", \"" + column + "\")";
						}
					}
				} else {
					if (environment == "NULL") {
						if (designIndex == 0 || designIndex == 1) {
							funcSsaRandom = "ssa2 <- try(ssa.test(\"" + design + "\",data," + respvarVector + ",\"" + genotype + "\",\"" + block + "\",column=NULL, rep=NULL," + environment + ", is.random = TRUE), silent=TRUE)";
							groupVars = "c(\"" + genotype + "\", \"" + block + "\")";
						} else if (designIndex == 2) {
							funcSsaRandom = "ssa2 <- try(ssa.test(\"" + design + "\",data," + respvarVector + ",\"" + genotype + "\",\"" + row + "\",\"" + column + "\", rep=NULL," + environment + ", is.random = TRUE), silent=TRUE)";
							groupVars = "c(\"" + genotype + "\", \"" + row + "\", \"" + column + "\")";
						} else if (designIndex == 3 || designIndex == 5) {
							funcSsaRandom = "ssa2 <- try(ssa.test(\"" + design + "\",data," + respvarVector + ",\"" + genotype + "\",\"" + block + "\",column=NULL,\"" + rep + "\"," + environment + ", is.random = TRUE), silent=TRUE)";
							groupVars = "c(\"" + genotype + "\", \"" + block + "\", \"" + rep + "\")";
						} else if (designIndex == 4 || designIndex == 6) {
							funcSsaRandom = "ssa2 <- try(ssa.test(\"" + design + "\",data," + respvarVector + ",\"" + genotype + "\",\"" + row + "\",\"" + column + "\",\"" + rep + "\"," + environment + ", is.random = TRUE), silent=TRUE)";
							groupVars = "c(\"" + genotype + "\", \"" + rep + "\", \"" + row + "\", \"" + column + "\")";
						}
					} else {
						if (designIndex == 0 || designIndex == 1) {
							funcSsaRandom = "ssa2 <- try(ssa.test(\"" + design + "\",data," + respvarVector + ",\"" + genotype + "\",\"" + block + "\",column=NULL, rep=NULL,\"" + environment + "\", is.random = TRUE), silent=TRUE)";
							groupVars = "c(\"" + genotype + "\", \"" + block + "\")";
						} else if (designIndex == 2) {
							funcSsaRandom = "ssa2 <- try(ssa.test(\"" + design + "\",data," + respvarVector + ",\"" + genotype + "\",\"" + row + "\",\"" + column + "\", rep=NULL,\"" + environment + "\", is.random = TRUE), silent=TRUE)";
							groupVars = "c(\"" + genotype + "\", \"" + row + "\", \"" + column + "\")";
						} else if (designIndex == 3 || designIndex == 5) {
							funcSsaRandom = "ssa2 <- try(ssa.test(\"" + design + "\",data," + respvarVector + ",\"" + genotype + "\",\"" + block + "\",column=NULL,\"" + rep + "\",\"" + environment + "\", is.random = TRUE), silent=TRUE)";
							groupVars = "c(\"" + genotype + "\", \"" + block + "\", \"" + rep + "\")";
						} else if (designIndex == 4 || designIndex == 6) {
							funcSsaRandom = "ssa2 <- try(ssa.test(\"" + design + "\",data," + respvarVector + ",\"" + genotype + "\",\"" + row + "\",\"" + column + "\",\"" + rep + "\",\"" + environment + "\", is.random = TRUE), silent=TRUE)";
							groupVars = "c(\"" + genotype + "\", \"" + rep + "\", \"" + row + "\", \"" + column + "\")";
						}
					}
				}
				String randomHead = "capture.output(cat(\"GENOTYPE AS: Random\n\"),file=\"" + outFileName + "\",append = TRUE)";
				rEngine.eval(funcSsaRandom);
				rEngine.eval(sep2);
				rEngine.eval(randomHead);
				rEngine.eval(sep2);
				rEngine.eval(outspace);
				System.out.println(funcSsaRandom);
				
				String runSuccess2 = rEngine.eval("class(ssa2)").asString();	
				if (runSuccess2 != null && runSuccess2.equals("try-error")) {	
					System.out.println("ssa2: error");
					String checkError = "msg <- trimStrings(strsplit(ssa2, \":\")[[1]])";
					String checkError2 = "msg <- trimStrings(paste(strsplit(msg, \"\\n\")[[length(msg)]], collapse = \" \"))";
					String checkError3 ="msg <- gsub(\"\\\"\", \"\", msg)";
					String checkError4 = "capture.output(cat(\"*** \nERROR in ssa.test function:\\n  \",msg, \"\n***\n\n\", sep = \"\"), file=\"" + outFileName + "\",append = TRUE)";
					rEngine.eval(checkError);
					rEngine.eval(checkError2);
					rEngine.eval(checkError3);
					rEngine.eval(checkError4);
					
					runningRandomSuccess=false;
				}
				else {

				for (int k = 0; k < respvar.length; k++) {
					int i = k + 1; // 1-relative index;
					String respVarHead = "capture.output(cat(\"\nRESPONSE VARIABLE: " + respvar[k] + "\n\"),file=\"" + outFileName + "\",append = TRUE)";
					rEngine.eval(sep);
					rEngine.eval(respVarHead);
					rEngine.eval(sep);
					rEngine.eval(outspace);
					
					// optional output: descriptive statistics
					if (descriptiveStat) {
						String funcDesc = null;
						if (environment == "NULL") {
							funcDesc = "outDesc <- try(DescriptiveStatistics(data, \"" + respvar[k] + "\", " + environment + "), silent=TRUE)";
						} else {
							funcDesc = "outDesc <- try(DescriptiveStatistics(data, \"" + respvar[k] + "\", \"" + environment + "\"), silent=TRUE)";
						}
						rEngine.eval(funcDesc);
						System.out.println(funcDesc);
						String outDescStat = "capture.output(cat(\"DESCRIPTIVE STATISTICS:\n\n\"),file=\"" + outFileName + "\",append = TRUE)";
						String outDescStat2 = "capture.output(outDesc,file=\"" + outFileName + "\",append = TRUE)"; 

  						String runSuccessDescStat = rEngine.eval("class(outDesc)").asString();
						if (runSuccessDescStat != null && runSuccessDescStat.equals("try-error")) {	
							System.out.println("desc stat: error");
							String checkError = "msg <- trimStrings(strsplit(outDesc, \":\")[[1]])";
							String checkError2 = "msg <- trimStrings(paste(strsplit(msg, \"\\n\")[[length(msg)]], collapse = \" \"))";
							String checkError3 ="msg <- gsub(\"\\\"\", \"\", msg)";
							String checkError4 = "capture.output(cat(\"*** \nERROR in DescriptiveStatistics function:\\n  \",msg, \"\n***\n\n\", sep = \"\"), file=\"" + outFileName + "\",append = TRUE)";
							rEngine.eval(checkError);
							rEngine.eval(checkError2);
							rEngine.eval(checkError3);
							rEngine.eval(checkError4);
						} 
						else {
							rEngine.eval(outspace);
							rEngine.eval(outDescStat);
							rEngine.eval(outDescStat2);
							rEngine.eval(outspace);
						}	
					}
					int envLevelsLength2 = 0;
					if (environment == "NULL") {
						envLevelsLength2 = 1;
					} else {
						envLevelsLength2 = environmentLevels.length;
					}
					for (int m = 0; m < envLevelsLength2; m++) { // no of envts or sites
						printAllOutputRandom=true;
						int j = m + 1; // 1-relative index;
						
						if (environment != "NULL") {
							String envtHead = "capture.output(cat(\"\nANALYSIS FOR: "+ environment + "\", \" = \" ,ssa2$output[[" + i	+ "]]$site[[" + j + "]]$env,\"\n\"),file=\""+ outFileName + "\",append = TRUE)";
							rEngine.eval(sep);
							rEngine.eval(envtHead);
							rEngine.eval(sep);
							rEngine.eval(outspace);
						}
						
						//check if the data has too many missing observations
						double responseRate=rEngine.eval("ssa2$output[[" + i + "]]$site[[" + j + "]]$responseRate").asDouble();
						if (responseRate < 0.8) {
							String allNAWarning2 = rEngine.eval("ssa2$output[[" + i + "]]$site[[" + j + "]]$manyNAWarning").asString();
							String printError1 = "capture.output(cat(\"***\\n\"), file=\"" + outFileName + "\",append = TRUE)";
							String printError2 = "capture.output(cat(\"ERROR:\\n\"), file=\"" + outFileName + "\",append = TRUE)";
							String printError3 = "capture.output(cat(\"" + allNAWarning2 + "\\n\"), file=\"" + outFileName + "\",append = TRUE)";
							
							rEngine.eval(outspace);
							rEngine.eval(printError1);
							rEngine.eval(printError2);
							rEngine.eval(printError3);
							rEngine.eval(printError1);
							rEngine.eval(outspace);
							rEngine.eval(outspace);
							printAllOutputRandom=false;
						} else {
							String lmerRun=rEngine.eval("ssa2$output[[" + i + "]]$site[[" + j + "]]$lmerRun").asString();
							if (lmerRun.equals("ERROR")) {
								String lmerError = rEngine.eval("ssa2$output[[" + i + "]]$site[[" + j + "]]$lmerError").asString();
								String printError1 = "capture.output(cat(\"***\\n\"), file=\"" + outFileName + "\",append = TRUE)";
								String printError2 = "capture.output(cat(\"ERROR:\\n\"), file=\"" + outFileName + "\",append = TRUE)";
								String printError3 = "capture.output(cat(\"" + lmerError + "\\n\"), file=\"" + outFileName + "\",append = TRUE)";
								
								rEngine.eval(outspace);
								rEngine.eval(printError1);
								rEngine.eval(printError2);
								rEngine.eval(printError3);
								rEngine.eval(printError1);
								rEngine.eval(outspace);
								rEngine.eval(outspace);
								printAllOutputRandom=false;
							}
						}
						
						if (printAllOutputRandom) {
							// display warning generated by checkTest in ssa.test
							String warningCheckTest = rEngine.eval("ssa2$output[[" + i	+ "]]$site[[" + j + "]]$checkTestWarning").asString();
							
							if (!warningCheckTest.equals("NONE")) {
								String warningCheckTest2 = "capture.output(cat(\"\n*** \nWARNING:\\n\"), file=\"" + outFileName + "\",append = TRUE)";
								String warningCheckTest3 = "capture.output(cat(\"" + warningCheckTest + "\"), file=\"" + outFileName + "\",append = TRUE)";
								String warningCheckTest4 = "capture.output(cat(\"\n*** \\n\"), file=\"" + outFileName + "\",append = TRUE)";
								
								rEngine.eval(warningCheckTest2);
								rEngine.eval(warningCheckTest3);
								rEngine.eval(warningCheckTest4);
							} 
							System.out.println("check test:" + warningCheckTest);
							
							// default output: trial summary
							String funcTrialSum = "funcTrialSum <- try(class.information(" + groupVars + ",ssa2$output[[" + i + "]]$site[[" + j + "]]$data), silent=TRUE)";
							String trialSumHead = "capture.output(cat(\"\nDATA SUMMARY:\n\n\"),file=\"" + outFileName + "\",append = TRUE)";
							String trialObsRead = "capture.output(cat(\"Number of observations read: \", ssa2$output[["	+ i	+ "]]$site[[" + j + "]]$obsread[[1]],\"\n\"),file=\"" + outFileName + "\",append = TRUE)";
							String trialObsUsed = "capture.output(cat(\"Number of observations used: \", ssa2$output[["	+ i	+ "]]$site[[" + j + "]]$obsused[[1]],\"\n\n\"),file=\""	+ outFileName + "\",append = TRUE)";
							String trialSum = "capture.output(funcTrialSum,file=\"" + outFileName + "\",append = TRUE)";

							rEngine.eval(funcTrialSum);

							String runSuccessTS = rEngine.eval("class(funcTrialSum)").asString();
							if (runSuccessTS != null && runSuccessTS.equals("try-error")) {	
								System.out.println("class info: error");
								String checkError = "msg <- trimStrings(strsplit(funcTrialSum, \":\")[[1]])";
								String checkError2 = "msg <- trimStrings(paste(strsplit(msg, \"\\n\")[[length(msg)]], collapse = \" \"))";
								String checkError3 ="msg <- gsub(\"\\\"\", \"\", msg)";
								String checkError4 = "capture.output(cat(\"*** \nERROR in class.information function:\\n  \",msg, \"\n***\n\n\", sep = \"\"), file=\"" + outFileName + "\",append = TRUE)";
								rEngine.eval(checkError);
								rEngine.eval(checkError2);
								rEngine.eval(checkError3);
								rEngine.eval(checkError4);
							}
							else {
								rEngine.eval(trialSumHead);
								rEngine.eval(trialObsRead);
								rEngine.eval(trialObsUsed);
								rEngine.eval(trialSum);
								rEngine.eval(outspace);
							}

							// optional output: variance components
							if (varianceComponents) {
								String outVarComp = "capture.output(cat(\"\nVARIANCE COMPONENTS TABLE:\n\n\"),file=\"" + outFileName + "\",append = TRUE)";
								String outVarComp2 = "capture.output(ssa2$output[[" + i + "]]$site[[" + j + "]]$varcomp.table,file=\"" + outFileName + "\",append = TRUE)";

								rEngine.eval(outVarComp);
								rEngine.eval(outVarComp2);
								rEngine.eval(outspace);
							}
							
							//default output: test genotypic effect
							String outTestGen1 = "capture.output(cat(\"\nTESTING FOR THE SIGNIFICANCE OF GENOTYPIC EFFECT USING -2 LOGLIKELIHOOD RATIO TEST:\n\"),file=\"" + outFileName + "\",append = TRUE)";
							String outTestGen2 = "capture.output(cat(\"\nFormula for Model1: \", ssa2$output[["	+ i	+ "]]$site[[" + j + "]]$formula1,\"\n\"),file=\"" + outFileName + "\",append = TRUE)";
							String outTestGen3 = "capture.output(cat(\"Formula for Model2: \", ssa2$output[["	+ i	+ "]]$site[[" + j + "]]$formula2,\"\n\n\"),file=\"" + outFileName + "\",append = TRUE)";
							String outTestGen4 = "capture.output(ssa2$output[[" + i + "]]$site[[" + j + "]]$models.table,file=\"" + outFileName + "\",append = TRUE)";
							rEngine.eval(outTestGen1);
							rEngine.eval(outTestGen2);
							rEngine.eval(outTestGen3);
							rEngine.eval(outTestGen4);
							rEngine.eval(outspace);
							
							//default output: test for check effect
							String newExcludeCheck = rEngine.eval("ssa2$output[[" + i + "]]$site[[" + j + "]]$newExcludeCheck").asBool().toString();
							System.out.println("newExcludeCheck: " + newExcludeCheck);
							
							if (newExcludeCheck.equals("TRUE")) {
								String outAnovaTable1 = "capture.output(cat(\"\nTESTING FOR THE SIGNIFICANCE OF CHECK EFFECT:\n\"),file=\"" + outFileName + "\",append = TRUE)";
								String outAnovaTable2 = "library(lmerTest)";
								String outAnovaTable3 = "model2b <- lmer(formula(ssa2$output[[" + i + "]]$site[[" + j + "]]$formula1), data = ssa2$output[[" + i + "]]$site[[" + j + "]]$data, REML = T)";
								String outAnovaTable4 = "a.table <- anova(model2b)";
								String outAnovaTable5 = "pvalue <- formatC(as.numeric(format(a.table[1,6], scientific=FALSE)), format=\"f\")";
								String outAnovaTable6 = "a.table<-cbind(round(a.table[,1:5], digits=4),pvalue)";
								String outAnovaTable7 = "colnames(a.table)<-c(\"Df\", \"Sum Sq\", \"Mean Sq\", \"F value\", \"Denom\", \"Pr(>F)\")";
								String outAnovaTable8 = "capture.output(cat(\"Analysis of Variance Table with Satterthwaite Denominator Df\n\"),file=\"" + outFileName + "\",append = TRUE)";
								String outAnovaTable9 = "capture.output(a.table,file=\"" + outFileName + "\",append = TRUE)";
								String outAnovaTable10 = "detach(\"package:lmerTest\")";
								
//								rEngine.eval(outspace);
								rEngine.eval(outAnovaTable1);
								rEngine.eval(outAnovaTable2);
								rEngine.eval(outAnovaTable3);
								rEngine.eval(outAnovaTable4);
								rEngine.eval(outAnovaTable5);
								rEngine.eval(outAnovaTable6);
								rEngine.eval(outAnovaTable7);
								rEngine.eval(outspace);
								rEngine.eval(outAnovaTable8);
								rEngine.eval(outAnovaTable9);
								rEngine.eval(outspace);
								rEngine.eval(outAnovaTable10);
							}
							
							//default output: predicted means
							String outPredMeans = "capture.output(cat(\"\nPREDICTED MEANS:\n\n\"),file=\"" + outFileName + "\",append = TRUE)";
							String outPredMeans2 = "capture.output(ssa2$output[[" + i + "]]$site[[" + j + "]]$summary.statistic,file=\"" + outFileName + "\",append = TRUE)"; 
							rEngine.eval(outPredMeans);
							rEngine.eval(outPredMeans2);
							rEngine.eval(outspace);
							
							//default output: lsmeans of checks
							if (excludeControls) {
								int newCheckListLength = rEngine.eval("ssa2$output[[" + i	+ "]]$site[[" + j + "]]$newCheckListLength").asInt();
								
								if (newCheckListLength > 0) {
									String outLSMeansCheck = "capture.output(cat(\"\nCHECK/CONTROL LSMEANS:\n\n\"),file=\"" + outFileName + "\",append = TRUE)";
									String outLSMeansCheck2 = "capture.output(ssa2$output[[" + i + "]]$site[[" + j + "]]$lsmeans.checks,file=\"" + outFileName + "\",append = TRUE)"; 
									rEngine.eval(outLSMeansCheck);
									rEngine.eval(outLSMeansCheck2);
									rEngine.eval(outspace);
								}
							}

							//default output: estimate heritability
							String outEstHerit = "capture.output(cat(\"\nHERITABILITY:\n\"),file=\"" + outFileName + "\",append = TRUE)";
							String outEstHerit2 = "capture.output(ssa2$output[[" + i + "]]$site[[" + j + "]]$heritability,file=\""	+ outFileName + "\",append = TRUE)";
							String outEstHerit3 = "capture.output(cat(\"\n\"),file=\"" + outFileName + "\",append = TRUE)";
							rEngine.eval(outEstHerit);
							rEngine.eval(outEstHerit2);
							rEngine.eval(outEstHerit3);
							rEngine.eval(outspace);
						}
						
					}
				}
				
				//optional output: estimate genotypic and phenotypic correlations
				if (genoPhenoCorrelation) {
					rEngine.eval(sep2);
					String funcEstCorr = null;
					if (excludeControls) {
						if (environment == "NULL") {
							if (designIndex == 0)
								funcEstCorr = "gpcorr <- try(genoNpheno.corr(\"" + design + "\",data," + respvarVector + ",\"" + genotype + "\",\"" + block + "\",column=NULL, rep=NULL," + environment + ", excludeLevels=TRUE, excludeList = " + controlLevelsVector + "), silent=TRUE)";
							else if (designIndex == 1)
								funcEstCorr = "gpcorr <- try(genoNpheno.corr(\"" + design + "\",data," + respvarVector + ",\"" + genotype + "\",\"" + block + "\",column=NULL, rep=NULL," + environment + ", excludeLevels=TRUE, excludeList = NULL), silent=TRUE)";
							else if (designIndex == 2)
								funcEstCorr = "gpcorr <- try(genoNpheno.corr(\"" + design + "\",data," + respvarVector + ",\"" + genotype + "\",\"" + row + "\",\"" + column + "\", rep=NULL," + environment + ", excludeLevels=TRUE, excludeList = NULL), silent=TRUE)";
							else if (designIndex == 3)
								funcEstCorr = "gpcorr <- try(genoNpheno.corr(\"" + design + "\",data," + respvarVector + ",\"" + genotype + "\",\"" + block + "\",column=NULL,\"" + rep + "\"," + environment + ", excludeLevels=TRUE, excludeList = " + controlLevelsVector + "), silent=TRUE)";
							else if (designIndex == 4)
								funcEstCorr = "gpcorr <- try(genoNpheno.corr(\"" + design + "\",data," + respvarVector + ",\"" + genotype + "\",\"" + row + "\",\"" + column + "\",\"" + rep + "\"," + environment + ", excludeLevels=TRUE, excludeList = " + controlLevelsVector + "), silent=TRUE)";
							else if (designIndex == 5)
								funcEstCorr = "gpcorr <- try(genoNpheno.corr(\"" + design + "\",data," + respvarVector + ",\"" + genotype + "\",\"" + block + "\",column=NULL,\"" + rep + "\"," + environment + ", excludeLevels=TRUE, excludeList = " + controlLevelsVector + "), silent=TRUE)";
							else if (designIndex == 6)
								funcEstCorr = "gpcorr <- try(genoNpheno.corr(\"" + design + "\",data," + respvarVector + ",\"" + genotype + "\",\"" + row + "\",\"" + column + "\",\"" + rep + "\"," + environment + ", excludeLevels=TRUE, excludeList = " + controlLevelsVector + "), silent=TRUE)";
						} else {
							if (designIndex == 0)
								funcEstCorr = "gpcorr <- try(genoNpheno.corr(\"" + design + "\",data," + respvarVector + ",\"" + genotype + "\",\"" + block + "\",column=NULL, rep=NULL,\"" + environment + "\", excludeLevels=TRUE, excludeList = " + controlLevelsVector + "), silent=TRUE)";
							else if (designIndex == 1)
								funcEstCorr = "gpcorr <- try(genoNpheno.corr(\"" + design + "\",data," + respvarVector + ",\"" + genotype + "\",\"" + block + "\",column=NULL, rep=NULL,\"" + environment + "\", excludeLevels=TRUE, excludeList = NULL), silent=TRUE)";
							else if (designIndex == 2)
								funcEstCorr = "gpcorr <- try(genoNpheno.corr(\"" + design + "\",data," + respvarVector + ",\"" + genotype + "\",\"" + row + "\",\"" + column + "\", rep=NULL,\"" + environment + "\", excludeLevels=TRUE, excludeList = NULL), silent=TRUE)";
							else if (designIndex == 3)
								funcEstCorr = "gpcorr <- try(genoNpheno.corr(\"" + design + "\",data," + respvarVector + ",\"" + genotype + "\",\"" + block + "\",column=NULL,\"" + rep + "\",\"" + environment + "\", excludeLevels=TRUE, excludeList = " + controlLevelsVector + "), silent=TRUE)";
							else if (designIndex == 4)
								funcEstCorr = "gpcorr <- try(genoNpheno.corr(\"" + design + "\",data," + respvarVector + ",\"" + genotype + "\",\"" + row + "\",\"" + column + "\",\"" + rep + "\",\"" + environment + "\", excludeLevels=TRUE, excludeList = " + controlLevelsVector + "), silent=TRUE)";
							else if (designIndex == 5)
								funcEstCorr = "gpcorr <- try(genoNpheno.corr(\"" + design + "\",data," + respvarVector + ",\"" + genotype + "\",\"" + block + "\",column=NULL,\"" + rep + "\",\"" + environment + "\", excludeLevels=TRUE, excludeList = " + controlLevelsVector + "), silent=TRUE)";
							else if (designIndex == 6)
								funcEstCorr = "gpcorr <- try(genoNpheno.corr(\"" + design + "\",data," + respvarVector + ",\"" + genotype + "\",\"" + row + "\",\"" + column + "\",\"" + rep + "\",\"" + environment + "\", excludeLevels=TRUE, excludeList = " + controlLevelsVector + "), silent=TRUE)";
						}
					} else {
						if (environment == "NULL") {
							if (designIndex == 0 || designIndex == 1)
								funcEstCorr = "gpcorr <- try(genoNpheno.corr(\"" + design + "\",data," + respvarVector + ",\"" + genotype + "\",\"" + block + "\",column=NULL, rep=NULL," + environment + "), silent=TRUE)";
							else if (designIndex == 2)
								funcEstCorr = "gpcorr <- try(genoNpheno.corr(\"" + design + "\",data," + respvarVector + ",\"" + genotype + "\",\"" + row + "\",\"" + column + "\", rep=NULL," + environment + "), silent=TRUE)";
							else if (designIndex == 3)
								funcEstCorr = "gpcorr <- try(genoNpheno.corr(\"" + design + "\",data," + respvarVector + ",\"" + genotype + "\",\"" + block + "\",column=NULL,\"" + rep + "\"," + environment + "), silent=TRUE)";
							else if (designIndex == 4)
								funcEstCorr = "gpcorr <- try(genoNpheno.corr(\"" + design + "\",data," + respvarVector + ",\"" + genotype + "\",\"" + row + "\",\"" + column + "\",\"" + rep + "\"," + environment + "), silent=TRUE)";
							else if (designIndex == 5)
								funcEstCorr = "gpcorr <- try(genoNpheno.corr(\"" + design + "\",data," + respvarVector + ",\"" + genotype + "\",\"" + block + "\",column=NULL,\"" + rep + "\"," + environment + "), silent=TRUE)";
							else if (designIndex == 6)
								funcEstCorr = "gpcorr <- try(genoNpheno.corr(\"" + design + "\",data," + respvarVector + ",\"" + genotype + "\",\"" + row + "\",\"" + column + "\",\"" + rep + "\"," + environment + "), silent=TRUE)";
						} else {
							if (designIndex == 0 || designIndex == 1)
								funcEstCorr = "gpcorr <- try(genoNpheno.corr(\"" + design + "\",data," + respvarVector + ",\"" + genotype + "\",\"" + block + "\",column=NULL, rep=NULL,\"" + environment + "\"), silent=TRUE)";
							else if (designIndex == 2)
								funcEstCorr = "gpcorr <- try(genoNpheno.corr(\"" + design + "\",data," + respvarVector + ",\"" + genotype + "\",\"" + row + "\",\"" + column + "\", rep=NULL,\"" + environment + "\"), silent=TRUE)";
							else if (designIndex == 3)
								funcEstCorr = "gpcorr <- try(genoNpheno.corr(\"" + design + "\",data," + respvarVector + ",\"" + genotype + "\",\"" + block + "\",column=NULL,\"" + rep + "\",\"" + environment + "\"), silent=TRUE)";
							else if (designIndex == 4)
								funcEstCorr = "gpcorr <- try(genoNpheno.corr(\"" + design + "\",data," + respvarVector + ",\"" + genotype + "\",\"" + row + "\",\"" + column + "\",\"" + rep + "\",\"" + environment + "\"), silent=TRUE)";
							else if (designIndex == 5)
								funcEstCorr = "gpcorr <- try(genoNpheno.corr(\"" + design + "\",data," + respvarVector + ",\"" + genotype + "\",\"" + block + "\",column=NULL,\"" + rep + "\",\"" + environment + "\"), silent=TRUE)";
							else if (designIndex == 6)
								funcEstCorr = "gpcorr <- try(genoNpheno.corr(\"" + design + "\",data," + respvarVector + ",\"" + genotype + "\",\"" + row + "\",\"" + column + "\",\"" + rep + "\",\"" + environment + "\"), silent=TRUE)";
						}
					}

					System.out.println(funcEstCorr);
					rEngine.eval(funcEstCorr);	
					
					String runSuccessGPCorr = rEngine.eval("class(gpcorr)").asString();
					if (runSuccessGPCorr != null && runSuccessGPCorr.equals("try-error")) {	
						System.out.println("geno pheno corr: error");
						String checkError = "msg <- trimStrings(strsplit(gpcorr, \":\")[[1]])";
						String checkError2 = "msg <- trimStrings(paste(strsplit(msg, \"\\n\")[[length(msg)]], collapse = \" \"))";
						String checkError3 ="msg <- gsub(\"\\\"\", \"\", msg)";
						String checkError4 = "capture.output(cat(\"*** \nERROR in genoNpheno.corr function:\\n  \",msg, \"\n***\n\n\", sep = \"\"), file=\"" + outFileName + "\",append = TRUE)";
						rEngine.eval(checkError);
						rEngine.eval(checkError2);
						rEngine.eval(checkError3);
						rEngine.eval(checkError4);
					}
					else {
						String outEstGenoCorr = "capture.output(cat(\"\nGENOTYPIC CORRELATIONS:\n\"),file=\"" + outFileName + "\",append = TRUE)";
						rEngine.eval(outEstGenoCorr);
						
						int envLevelsLength = 0;
						if (environment == "NULL") {
							envLevelsLength = 1;
						} else {
							envLevelsLength = environmentLevels.length;
						}
					
						for (int m = 0; m < envLevelsLength; m++) { // no of envts or sites
							int j = m + 1; // 1-relative index;
							if (environment != "NULL") {
								String outEstGenoCorr2 = "capture.output(cat(\"" + environment + " = \", gpcorr$EnvLevels[[" + j + "]]),file=\"" + outFileName + "\",append = TRUE)";
								rEngine.eval(outspace);
								rEngine.eval(outEstGenoCorr2);
								rEngine.eval(outspace);
							}
							String outEstGenoCorr2b = "capture.output(gpcorr$GenoCorr[[" + j + "]],file=\"" + outFileName + "\",append = TRUE)";
							rEngine.eval(outspace);
							rEngine.eval(outEstGenoCorr2b);
							rEngine.eval(outspace);
						}
					
						String outEstPhenoCorr = "capture.output(cat(\"\nPHENOTYPIC CORRELATIONS:\n\"),file=\"" + outFileName + "\",append = TRUE)";
						rEngine.eval(outEstPhenoCorr);
						
						for (int m = 0; m < envLevelsLength; m++) { // no of envts or sites
							int j = m + 1; // 1-relative index;
							if (environment != "NULL") {
								String outEstPhenoCorr2 = "capture.output(cat(\"" + environment + " = \", gpcorr$EnvLevels[[" + j + "]]),file=\"" + outFileName + "\",append = TRUE)";
								rEngine.eval(outspace);
								rEngine.eval(outEstPhenoCorr2);
								rEngine.eval(outspace);
							}
							String outEstPhenoCorr2b = "capture.output(gpcorr$PhenoCorr[[" + j + "]],file=\"" + outFileName + "\",append = TRUE)";
							rEngine.eval(outspace);
							rEngine.eval(outEstPhenoCorr2b);
							rEngine.eval(outspace);
						}
					} //end of else for if runSuccessGPCorr	
				}
				
				//default option: save predicted means to a file
				String checkPredMean = rEngine.eval("ssa2$meansWarning").asString();
				System.out.println("checkPredMean: " + checkPredMean);
				
				if (checkPredMean.equals("empty")) {
					System.out.println("Saving predicted means not done.");
				} else {
					String meansFileName2 = "meansFileName2 <- paste(\"" + resultFolderPath + "\",\"predictedMeans.csv\", sep=\"\")";
					String funcSavePredMeansCsv = "saveDataB1 <- try(write.table(ssa2$means,file = meansFileName2 ,sep=\",\",row.names=FALSE), silent=TRUE)";
					rEngine.eval(meansFileName2);
					rEngine.eval(funcSavePredMeansCsv);
					
					String runSuccessSavePredMeans = rEngine.eval("class(saveDataB1)").asString();
					if (runSuccessSavePredMeans != null && runSuccessSavePredMeans.equals("try-error")) {	
						System.out.println("save pred means: error");
						String checkError = "msg <- trimStrings(strsplit(saveDataB1, \":\")[[1]])";
						String checkError2 = "msg <- trimStrings(paste(strsplit(msg, \"\\n\")[[length(msg)]], collapse = \" \"))";
						String checkError3 ="msg <- gsub(\"\\\"\", \"\", msg)";
						String checkError4 = "capture.output(cat(\"*** \nERROR in saving predicted means to a file:\\n  \",msg, \"\n***\n\n\", sep = \"\"), file=\"" + outFileName + "\",append = TRUE)";
						rEngine.eval(checkError);
						rEngine.eval(checkError2);
						rEngine.eval(checkError3);
						rEngine.eval(checkError4);
					}
				}
				
				//optional output: diagnostic plots for genotype random
				if (diagnosticPlot) {
					String diagPlotsFunc = null;
					if (environment == "NULL") {
						diagPlotsFunc = "diagPlots <- try(graph.sea.diagplots(data, " + respvarVector + ", env = " + environment + ", is.random = TRUE, ssa2), silent=TRUE)";
					} else {
						diagPlotsFunc = "diagPlots <- try(graph.sea.diagplots(data, " + respvarVector + ", env = \"" + environment + "\", is.random = TRUE, ssa2), silent=TRUE)";
					}
					System.out.println(diagPlotsFunc);
					rEngine.eval(diagPlotsFunc);
					
					String runSuccessDiagPlots = rEngine.eval("class(diagPlots)").asString();
					if (runSuccessDiagPlots != null && runSuccessDiagPlots.equals("try-error")) {	
						System.out.println("diagnostic plots (genotype random): error");
						String checkError = "msg <- trimStrings(strsplit(diagPlots, \":\")[[1]])";
						String checkError2 = "msg <- trimStrings(paste(strsplit(msg, \"\\n\")[[length(msg)]], collapse = \" \"))";
						String checkError3 ="msg <- gsub(\"\\\"\", \"\", msg)";
						String checkError4 = "capture.output(cat(\"*** \nERROR in graph.sea.diagplots function:\\n  \",msg, \"\n***\n\n\", sep = \"\"), file=\"" + outFileName + "\",append = TRUE)";
						rEngine.eval(checkError);
						rEngine.eval(checkError2);
						rEngine.eval(checkError3);
						rEngine.eval(checkError4);
					}
				}

//				if (SeaOptions.sConstructInt == true) {
////					graph.sea.predint <- function(data, respvar, env, result) 
//					String predIntPlotsSeaFunc = "predIntPlotsSea <- tryCatch(graph.sea.predint(data, " + respvarVector + ", env = \"" + environment + "\", geno = \"" + genotype + "\", ssa2), error=function(err) \"notRun\")";
//					System.out.println("predIntPlotsSeaFunc: " + predIntPlotsSeaFunc);
//					rEngine.eval(predIntPlotsSeaFunc);
//					
//					String runSuccesspredIntPlotsSea = rEngine.eval("predIntPlotsSea").asString();
//					System.out.println("runSuccesspredIntPlotsSea: " + runSuccesspredIntPlotsSea);
//					//generate warning if error occurred	
//					if (runSuccesspredIntPlotsSea != null && runSuccesspredIntPlotsSea.equals("notRun")) {	
//						System.out.println("error");
//						rEngine.eval("capture.output(cat(\"\n***An error has occurred.***\n***Prediction interval plots not created.***\n\"),file=\"" + outFileName + "\",append = TRUE)"); //append to output file?
//					}
////					else {
////					}
//				}
				
				} // end of else for if (runSuccess == "notRun") 
			} // end of if random

			//default output: save residuals to a file
			if (runningFixedSuccess & runningRandomSuccess) {
				String residFileNameFixed = "residFileNameFixed <- paste(\"" + resultFolderPath + "\",\"residuals_fixed.csv\", sep=\"\")";
				String residFileNameRandom = "residFileNameRandom <- paste(\"" + resultFolderPath + "\",\"residuals_random.csv\", sep=\"\")";
				if ((genotypeFixed) & (genotypeRandom == false)) {
					String runSsaResid1 = null;
					if (environment == "NULL") {
						runSsaResid1 = "resid_f <- try(ssa.resid(data, ssa1, " + respvarVector + ", env = " + environment + ", is.genoRandom = FALSE), silent=TRUE)";
					} else {
						runSsaResid1 = "resid_f <- try(ssa.resid(data, ssa1, " + respvarVector + ", env = \"" + environment + "\", is.genoRandom = FALSE), silent=TRUE)";
					}
					System.out.println(runSsaResid1);
					rEngine.eval(runSsaResid1);
					
					String runSuccessDiagPlots = rEngine.eval("class(resid_f)").asString();
					if (runSuccessDiagPlots != null && runSuccessDiagPlots.equals("try-error")) {	
						System.out.println("ssa.resid (genotype fixed): error");
						String checkError = "msg <- trimStrings(strsplit(resid_f, \":\")[[1]])";
						String checkError2 = "msg <- trimStrings(paste(strsplit(msg, \"\\n\")[[length(msg)]], collapse = \" \"))";
						String checkError3 ="msg <- gsub(\"\\\"\", \"\", msg)";
						String checkError4 = "capture.output(cat(\"*** \nERROR in ssa.resid function:\\n  \",msg, \"\n***\n\n\", sep = \"\"), file=\"" + outFileName + "\",append = TRUE)";
						rEngine.eval(checkError);
						rEngine.eval(checkError2);
						rEngine.eval(checkError3);
						rEngine.eval(checkError4);
					} else {
						String checkResid1 = rEngine.eval("resid_f$residWarning").asString();
						System.out.println("checkResid1: " + checkResid1);
						if (checkResid1.equals("empty")) {
							System.out.println("Saving resid (fixed) not done.");
						} else {
							String func1SaveResidualsCsv = "saveResid <- try(write.table(resid_f$residuals, file = residFileNameFixed ,sep=\",\",row.names=FALSE), silent=TRUE)";
							rEngine.eval(residFileNameFixed);
							rEngine.eval(func1SaveResidualsCsv);
							
							String runSuccessSaveResid = rEngine.eval("class(saveResid)").asString();
							if (runSuccessSaveResid != null && runSuccessSaveResid.equals("try-error")) {	
								System.out.println("save residuals: error");
								String checkError = "msg <- trimStrings(strsplit(saveResid, \":\")[[1]])";
								String checkError2 = "msg <- trimStrings(paste(strsplit(msg, \"\\n\")[[length(msg)]], collapse = \" \"))";
								String checkError3 ="msg <- gsub(\"\\\"\", \"\", msg)";
								String checkError4 = "capture.output(cat(\"*** \nERROR in saving residuals to a file:\\n  \",msg, \"\n***\n\n\", sep = \"\"), file=\"" + outFileName + "\",append = TRUE)";
								rEngine.eval(checkError);
								rEngine.eval(checkError2);
								rEngine.eval(checkError3);
								rEngine.eval(checkError4);
							}
							
							//generate heatmap
							if (heatmapResiduals) {
								String funcHeat=null;
								if (environment == "NULL") {
									funcHeat = "heat1 <- try(Heatmap(resid_f$residuals, genAs=\"fixed\", \"" + heatmapRow + "\", \"" + heatmapColumn + "\", " + respvarVector + ", \"" + designUsed + "\", " + environment + "), silent=TRUE)";
								} else {
									funcHeat = "heat1 <- try(Heatmap(resid_f$residuals, genAs=\"fixed\", \"" + heatmapRow + "\", \"" + heatmapColumn + "\", " + respvarVector + ", \"" + designUsed + "\", \"" + environment + "\"), silent=TRUE)";
								}
								System.out.println(funcHeat);
								rEngine.eval(funcHeat);
								
								String runSuccessHeat = rEngine.eval("class(heat1)").asString();
								if (runSuccessHeat != null && runSuccessHeat.equals("try-error")) {	
									System.out.println("heatmap (fixed): error");
									String checkError = "msg <- trimStrings(strsplit(heat1, \":\")[[1]])";
									String checkError2 = "msg <- trimStrings(paste(strsplit(msg, \"\\n\")[[length(msg)]], collapse = \" \"))";
									String checkError3 ="msg <- gsub(\"\\\"\", \"\", msg)";
									String checkError4 = "capture.output(cat(\"*** \nERROR in Heatmap function:\\n  \",msg, \"\n***\n\n\", sep = \"\"), file=\"" + outFileName + "\",append = TRUE)";
									rEngine.eval(checkError);
									rEngine.eval(checkError2);
									rEngine.eval(checkError3);
									rEngine.eval(checkError4);
								} else {
									for (int k = 0; k < respvar.length; k++) {
										int i = k + 1; // 1-relative index;
										
										String envLevelsCommand = "length(heat1[[" + i + "]]$site)";
										int envLevels = rEngine.eval(envLevelsCommand).asInt();
										for (int m = 0; m < envLevels; m++) { 
											int j = m + 1; // 1-relative index;
											
											String warningListCommand = "heat1[[" + i + "]]$site[["+ j + "]]";
											String warningList = rEngine.eval(warningListCommand).asString();
											
											if (warningList.equals("empty")) {
												
											} else if (warningList.equals("unique")) {
												
											} else {
												String trialObsUsed = "capture.output(cat(\"\nERROR:\", heat1[[" + i + "]]$site[["+ j + "]],\"\n\"),file=\""	+ outFileName + "\",append = TRUE)";
												rEngine.eval(trialObsUsed);
											}
										}
									}
								} //end (heat1 is not error)
							}
						}
					}
				}
				else if ((genotypeFixed == false) & (genotypeRandom)) {
					String runSsaResid2 = null;
					if (environment == "NULL") {
						runSsaResid2 = "resid_r <- try(ssa.resid(data, ssa2, " + respvarVector + ", env = " + environment + ", is.genoRandom = TRUE), silent=TRUE)";
					} else {
						runSsaResid2 = "resid_r <- try(ssa.resid(data, ssa2, " + respvarVector + ", env = \"" + environment + "\", is.genoRandom = TRUE), silent=TRUE)";
					}
					System.out.println(runSsaResid2);
					rEngine.eval(runSsaResid2);
					
					String runSuccessDiagPlots = rEngine.eval("class(resid_r)").asString();
					if (runSuccessDiagPlots != null && runSuccessDiagPlots.equals("try-error")) {	
						System.out.println("ssa.resid (genotype random): error");
						String checkError = "msg <- trimStrings(strsplit(resid_r, \":\")[[1]])";
						String checkError2 = "msg <- trimStrings(paste(strsplit(msg, \"\\n\")[[length(msg)]], collapse = \" \"))";
						String checkError3 ="msg <- gsub(\"\\\"\", \"\", msg)";
						String checkError4 = "capture.output(cat(\"*** \nERROR in ssa.resid function:\\n  \",msg, \"\n***\n\n\", sep = \"\"), file=\"" + outFileName + "\",append = TRUE)";
						rEngine.eval(checkError);
						rEngine.eval(checkError2);
						rEngine.eval(checkError3);
						rEngine.eval(checkError4);
					} else {
						String checkResid1 = rEngine.eval("resid_r$residWarning").asString();
						System.out.println("checkResid2: " + checkResid1);
						if (checkResid1.equals("empty")) {
							System.out.println("Saving resid (random) not done.");
						} else {
							String func1SaveResidualsCsv = "saveResid <- try(write.table(resid_r$residuals, file = residFileNameRandom ,sep=\",\",row.names=FALSE), silent=TRUE)";
							rEngine.eval(residFileNameRandom);
							rEngine.eval(func1SaveResidualsCsv);
							
							String runSuccessSaveResid = rEngine.eval("class(saveResid)").asString();
							if (runSuccessSaveResid != null && runSuccessSaveResid.equals("try-error")) {	
								System.out.println("save residuals: error");
								String checkError = "msg <- trimStrings(strsplit(saveResid, \":\")[[1]])";
								String checkError2 = "msg <- trimStrings(paste(strsplit(msg, \"\\n\")[[length(msg)]], collapse = \" \"))";
								String checkError3 ="msg <- gsub(\"\\\"\", \"\", msg)";
								String checkError4 = "capture.output(cat(\"*** \nERROR in saving residuals to a file:\\n  \",msg, \"\n***\n\n\", sep = \"\"), file=\"" + outFileName + "\",append = TRUE)";
								rEngine.eval(checkError);
								rEngine.eval(checkError2);
								rEngine.eval(checkError3);
								rEngine.eval(checkError4);
							}
							
							//generate heatmap
							if (heatmapResiduals) {
								String funcHeat=null;
								if (environment == "NULL") {
									funcHeat = "heat2 <- try(Heatmap(resid_r$residuals, genAs=\"random\", \"" + heatmapRow + "\", \"" + heatmapColumn + "\", " + respvarVector + ", \"" + designUsed + "\", " + environment + "), silent=TRUE)";
								} else {
									funcHeat = "heat2 <- try(Heatmap(resid_r$residuals, genAs=\"random\", \"" + heatmapRow + "\", \"" + heatmapColumn + "\", " + respvarVector + ", \"" + designUsed + "\", \"" + environment + "\"), silent=TRUE)";
								}
								System.out.println(funcHeat);
								rEngine.eval(funcHeat);
								
								String runSuccessHeat = rEngine.eval("class(heat2)").asString();
								if (runSuccessHeat != null && runSuccessHeat.equals("try-error")) {	
									System.out.println("heatmap (random): error");
									String checkError = "msg <- trimStrings(strsplit(heat2, \":\")[[1]])";
									String checkError2 = "msg <- trimStrings(paste(strsplit(msg, \"\\n\")[[length(msg)]], collapse = \" \"))";
									String checkError3 ="msg <- gsub(\"\\\"\", \"\", msg)";
									String checkError4 = "capture.output(cat(\"*** \nERROR in Heatmap function:\\n  \",msg, \"\n***\n\n\", sep = \"\"), file=\"" + outFileName + "\",append = TRUE)";
									rEngine.eval(checkError);
									rEngine.eval(checkError2);
									rEngine.eval(checkError3);
									rEngine.eval(checkError4);
								} else {
									for (int k = 0; k < respvar.length; k++) {
										int i = k + 1; // 1-relative index;
										
										String envLevelsCommand = "length(heat2[[" + i + "]]$site)";
										int envLevels = rEngine.eval(envLevelsCommand).asInt();
										for (int m = 0; m < envLevels; m++) { 
											int j = m + 1; // 1-relative index;
											
											String warningListCommand = "heat2[[" + i + "]]$site[["+ j + "]]";
											String warningList = rEngine.eval(warningListCommand).asString();
											
											if (warningList.equals("empty")) {
												
											} else if (warningList.equals("unique")) {
												
											} else {
												String trialObsUsed = "capture.output(cat(\"\nERROR:\", heat2[[" + i + "]]$site[["+ j + "]],\"\n\"),file=\""	+ outFileName + "\",append = TRUE)";
												rEngine.eval(trialObsUsed);
											}
										}
									}
								}
							}
						}
					}
				}
				else if ((genotypeFixed) & (genotypeRandom)) {
					String runSsaResid1 = null;
					String runSsaResid2 = null;
					if (environment == "NULL") {
						runSsaResid1 = "resid_f <- try(ssa.resid(data, ssa1, " + respvarVector + ", env = " + environment + ", is.genoRandom = FALSE), silent=TRUE)";
						runSsaResid2 = "resid_r <- try(ssa.resid(data, ssa2, " + respvarVector + ", env = " + environment + ", is.genoRandom = TRUE), silent=TRUE)";
					} else {
						runSsaResid1 = "resid_f <- try(ssa.resid(data, ssa1, " + respvarVector + ", env = \"" + environment + "\", is.genoRandom = FALSE), silent=TRUE)";
						runSsaResid2 = "resid_r <- try(ssa.resid(data, ssa2, " + respvarVector + ", env = \"" + environment + "\", is.genoRandom = TRUE), silent=TRUE)";
					}
					System.out.println(runSsaResid1);
					System.out.println(runSsaResid2);
					rEngine.eval(runSsaResid1);
					rEngine.eval(runSsaResid2);
					
					String runSuccessResidFixed = rEngine.eval("class(resid_f)").asString();
					if (runSuccessResidFixed != null && runSuccessResidFixed.equals("try-error")) {	
						System.out.println("ssa.resid (genotype fixed): error");
						String checkError = "msg <- trimStrings(strsplit(resid_f, \":\")[[1]])";
						String checkError2 = "msg <- trimStrings(paste(strsplit(msg, \"\\n\")[[length(msg)]], collapse = \" \"))";
						String checkError3 ="msg <- gsub(\"\\\"\", \"\", msg)";
						String checkError4 = "capture.output(cat(\"*** \nERROR in ssa.resid function:\\n  \",msg, \"\n***\n\n\", sep = \"\"), file=\"" + outFileName + "\",append = TRUE)";
						rEngine.eval(checkError);
						rEngine.eval(checkError2);
						rEngine.eval(checkError3);
						rEngine.eval(checkError4);
					} else {
						String checkResid1 = rEngine.eval("resid_f$residWarning").asString();
						System.out.println("checkResid1: " + checkResid1);
						if (checkResid1.equals("empty")) {
							System.out.println("Saving resid (fixed) not done.");
						} else {
							String func1SaveResidualsCsv = "saveResid <- try(write.table(resid_f$residuals, file = residFileNameFixed ,sep=\",\",row.names=FALSE), silent=TRUE)";
							rEngine.eval(residFileNameFixed);
							rEngine.eval(func1SaveResidualsCsv);
							
							String runSuccessSaveResid = rEngine.eval("class(saveResid)").asString();
							if (runSuccessSaveResid != null && runSuccessSaveResid.equals("try-error")) {	
								System.out.println("save residuals: error");
								String checkError = "msg <- trimStrings(strsplit(saveResid, \":\")[[1]])";
								String checkError2 = "msg <- trimStrings(paste(strsplit(msg, \"\\n\")[[length(msg)]], collapse = \" \"))";
								String checkError3 ="msg <- gsub(\"\\\"\", \"\", msg)";
								String checkError4 = "capture.output(cat(\"*** \nERROR in saving residuals to a file:\\n  \",msg, \"\n***\n\n\", sep = \"\"), file=\"" + outFileName + "\",append = TRUE)";
								rEngine.eval(checkError);
								rEngine.eval(checkError2);
								rEngine.eval(checkError3);
								rEngine.eval(checkError4);
							}
							
							//generate heatmap
							if (heatmapResiduals) {
								String funcHeat=null;
								if (environment == "NULL") {
									funcHeat = "heat1 <- try(Heatmap(resid_f$residuals, genAs=\"fixed\", \"" + heatmapRow + "\", \"" + heatmapColumn + "\", " + respvarVector + ", \"" + designUsed + "\", " + environment + "), silent=TRUE)";
								} else {
									funcHeat = "heat1 <- try(Heatmap(resid_f$residuals, genAs=\"fixed\", \"" + heatmapRow + "\", \"" + heatmapColumn + "\", " + respvarVector + ", \"" + designUsed + "\", \"" + environment + "\"), silent=TRUE)";
								}
								System.out.println(funcHeat);
								rEngine.eval(funcHeat);
								
								String runSuccessHeat = rEngine.eval("class(heat1)").asString();
								if (runSuccessHeat != null && runSuccessHeat.equals("try-error")) {	
									System.out.println("heatmap (fixed): error");
									String checkError = "msg <- trimStrings(strsplit(heat1, \":\")[[1]])";
									String checkError2 = "msg <- trimStrings(paste(strsplit(msg, \"\\n\")[[length(msg)]], collapse = \" \"))";
									String checkError3 ="msg <- gsub(\"\\\"\", \"\", msg)";
									String checkError4 = "capture.output(cat(\"*** \nERROR in Heatmap function:\\n  \",msg, \"\n***\n\n\", sep = \"\"), file=\"" + outFileName + "\",append = TRUE)";
									rEngine.eval(checkError);
									rEngine.eval(checkError2);
									rEngine.eval(checkError3);
									rEngine.eval(checkError4);
								} else {
									for (int k = 0; k < respvar.length; k++) {
										int i = k + 1; // 1-relative index;
										
										String envLevelsCommand = "length(heat1[[" + i + "]]$site)";
										int envLevels = rEngine.eval(envLevelsCommand).asInt();
										for (int m = 0; m < envLevels; m++) { 
											int j = m + 1; // 1-relative index;
											
											String warningListCommand = "heat1[[" + i + "]]$site[["+ j + "]]";
											String warningList = rEngine.eval(warningListCommand).asString();
											
											if (warningList.equals("empty")) {
												
											} else if (warningList.equals("unique")) {
												
											} else {
												String trialObsUsed = "capture.output(cat(\"\nERROR:\", heat1[[" + i + "]]$site[["+ j + "]],\"\n\"),file=\""	+ outFileName + "\",append = TRUE)";
												rEngine.eval(trialObsUsed);
											}
										}
									}
								}
							}
						}
					}
					
					String runSuccessResidRandom = rEngine.eval("class(resid_r)").asString();
					if (runSuccessResidRandom != null && runSuccessResidRandom.equals("try-error")) {	
						System.out.println("ssa.resid (genotype random): error");
						String checkError = "msg <- trimStrings(strsplit(resid_r, \":\")[[1]])";
						String checkError2 = "msg <- trimStrings(paste(strsplit(msg, \"\\n\")[[length(msg)]], collapse = \" \"))";
						String checkError3 ="msg <- gsub(\"\\\"\", \"\", msg)";
						String checkError4 = "capture.output(cat(\"*** \nERROR in ssa.resid function:\\n  \",msg, \"\n***\n\n\", sep = \"\"), file=\"" + outFileName + "\",append = TRUE)";
						rEngine.eval(checkError);
						rEngine.eval(checkError2);
						rEngine.eval(checkError3);
						rEngine.eval(checkError4);
					} else {
						String checkResid1 = rEngine.eval("resid_r$residWarning").asString();
						System.out.println("checkResid2: " + checkResid1);
						if (checkResid1.equals("empty")) {
							System.out.println("Saving resid (random) not done.");
						} else {
							String func1SaveResidualsCsv = "saveResid2 <- try(write.table(resid_r$residuals, file = residFileNameRandom ,sep=\",\",row.names=FALSE), silent=TRUE)";
							rEngine.eval(residFileNameRandom);
							rEngine.eval(func1SaveResidualsCsv);
							
							String runSuccessSaveResid = rEngine.eval("class(saveResid2)").asString();
							if (runSuccessSaveResid != null && runSuccessSaveResid.equals("try-error")) {	
								System.out.println("save residuals: error");
								String checkError = "msg <- trimStrings(strsplit(saveResid2, \":\")[[1]])";
								String checkError2 = "msg <- trimStrings(paste(strsplit(msg, \"\\n\")[[length(msg)]], collapse = \" \"))";
								String checkError3 ="msg <- gsub(\"\\\"\", \"\", msg)";
								String checkError4 = "capture.output(cat(\"*** \nERROR in saving residuals to a file:\\n  \",msg, \"\n***\n\n\", sep = \"\"), file=\"" + outFileName + "\",append = TRUE)";
								rEngine.eval(checkError);
								rEngine.eval(checkError2);
								rEngine.eval(checkError3);
								rEngine.eval(checkError4);
							}
							
							if (heatmapResiduals) {
								String funcHeat=null;
								if (environment == "NULL") {
									funcHeat = "heat2 <- try(Heatmap(resid_r$residuals, genAs=\"random\", \"" + heatmapRow + "\", \"" + heatmapColumn + "\", " + respvarVector + ", \"" + designUsed + "\", " + environment + "), silent=TRUE)";
								} else {
									funcHeat = "heat2 <- try(Heatmap(resid_r$residuals, genAs=\"random\", \"" + heatmapRow + "\", \"" + heatmapColumn + "\", " + respvarVector + ", \"" + designUsed + "\", \"" + environment + "\"), silent=TRUE)";
								}
								System.out.println(funcHeat);
								rEngine.eval(funcHeat);
								
								String runSuccessHeat = rEngine.eval("class(heat2)").asString();
								if (runSuccessHeat != null && runSuccessHeat.equals("try-error")) {	
									System.out.println("heatmap (random): error");
									String checkError = "msg <- trimStrings(strsplit(heat2, \":\")[[1]])";
									String checkError2 = "msg <- trimStrings(paste(strsplit(msg, \"\\n\")[[length(msg)]], collapse = \" \"))";
									String checkError3 ="msg <- gsub(\"\\\"\", \"\", msg)";
									String checkError4 = "capture.output(cat(\"*** \nERROR in Heatmap function:\\n  \",msg, \"\n***\n\n\", sep = \"\"), file=\"" + outFileName + "\",append = TRUE)";
									rEngine.eval(checkError);
									rEngine.eval(checkError2);
									rEngine.eval(checkError3);
									rEngine.eval(checkError4);
								} else {
									for (int k = 0; k < respvar.length; k++) {
										int i = k + 1; // 1-relative index;
										
										String envLevelsCommand = "length(heat2[[" + i + "]]$site)";
										int envLevels = rEngine.eval(envLevelsCommand).asInt();
										for (int m = 0; m < envLevels; m++) { 
											int j = m + 1; // 1-relative index;
											
											String warningListCommand = "heat2[[" + i + "]]$site[["+ j + "]]";
											String warningList = rEngine.eval(warningListCommand).asString();
											
											if (warningList.equals("empty")) {
												
											} else if (warningList.equals("unique")) {
												
											} else {
												String trialObsUsed = "capture.output(cat(\"\nERROR:\", heat2[[" + i + "]]$site[["+ j + "]],\"\n\"),file=\""	+ outFileName + "\",append = TRUE)";
												rEngine.eval(trialObsUsed);
											}
										}
									}
								}
							}
						}
					}
				}
			}
			
			//optional output: boxplot and histogram
			String withBox = "FALSE";
			if (boxplotRawData) withBox = "TRUE";
			String withHist = "FALSE";
			if (histogramRawData) withHist = "TRUE";
			String ssaOut = "ssa1";
			if (genotypeFixed) ssaOut = "ssa1";
			else if (genotypeRandom) ssaOut = "ssa2";

			String boxHistFunc = null;
			if (environment =="NULL") {
				boxHistFunc = "boxHist <- try(graph.sea.boxhist(data, " + respvarVector + ", env = " + environment + ", " + ssaOut + ", box = \"" + withBox + "\", hist = \"" + withHist + "\"), silent=TRUE)";
			} else {
				boxHistFunc = "boxHist <- try(graph.sea.boxhist(data, " + respvarVector + ", env = \"" + environment + "\", " + ssaOut + ", box = \"" + withBox + "\", hist = \"" + withHist + "\"), silent=TRUE)";
			}
			System.out.println(boxHistFunc);
			rEngine.eval(boxHistFunc);
			
			String runSuccessBoxHist = rEngine.eval("class(boxHist)").asString();
			if (runSuccessBoxHist != null && runSuccessBoxHist.equals("try-error")) {	
				System.out.println("boxplot/histogram: error");
				String checkError = "msg <- trimStrings(strsplit(boxHist, \":\")[[1]])";
				String checkError2 = "msg <- trimStrings(paste(strsplit(msg, \"\\n\")[[length(msg)]], collapse = \" \"))";
				String checkError3 ="msg <- gsub(\"\\\"\", \"\", msg)";
				String checkError4 = "capture.output(cat(\"*** \nERROR in graph.sea.boxhist function:\\n  \",msg, \"\n***\n\n\", sep = \"\"), file=\"" + outFileName + "\",append = TRUE)";
				rEngine.eval(checkError);
				rEngine.eval(checkError2);
				rEngine.eval(checkError3);
				rEngine.eval(checkError4);
			}
			rEngine.eval(outspace);
			rEngine.eval(sep2);

			rEngineEnd();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void doMultiEnvironmentOneStage(String dataFileName, String outFileName, String resultFolderPath, int designIndex, String[] respvar, String environment, String[] environmentLevels,
			String genotype, String block, String rep, String row, String column, boolean descriptiveStat, boolean varianceComponents, boolean boxplotRawData, boolean histogramRawData, boolean diagnosticPlot, boolean genotypeFixed, boolean performPairwise, String pairwiseAlpha, String[] genotypeLevels, 
			String[] controlLevels, boolean compareControl, boolean performAllPairwise, boolean genotypeRandom) {
	
		String respvarVector= inputTransform.createRVector(respvar);
		String controlLevelsVector= inputTransform.createRVector(controlLevels);
		boolean runningFixedSuccess =true;
		boolean runningRandomSuccess =true;
		boolean printAllOutputFixed = true;
		boolean printAllOutputRandom = true;
		
		try {
			String designUsed = new String();
			String design = new String();
			switch (designIndex) {
				case 0: {
					designUsed = "Randomized Complete Block (RCB)"; 
					design = "RCB"; 
					break;
				}
				case 1: {
					designUsed = "Augmented RCB"; 
					design = "AugRCB";
					break;
				}
				case 2: {
					designUsed = "Augmented Latin Square"; 
					design = "AugLS";
					break;
				}
				case 3: {
					designUsed = "Alpha-Lattice"; 
					design = "Alpha";
					break;
				}
				case 4: {
					designUsed = "Row-Column"; 
					design = "RowCol";
					break;
				}
				case 5: {
					designUsed = "Latinized Alpha-Lattice"; 
					design = "LatinAlpha";
					break;
				}
				case 6: {
					designUsed = "Latinized Row-Column"; 
					design = "LatinRowCol";
					break;
				}
				default: {
					designUsed = "Randomized Complete Block (RCB)"; 
					design = "RCB";
					break;
				}
			}
			
			String readData = "dataMeaOneStage <- read.csv(\"" + dataFileName + "\", header = TRUE, na.strings = c(\"NA\",\".\",\" \",\"\"), blank.lines.skip=TRUE, sep = \",\")";
			System.out.println(readData);
			rEngine.eval(readData);
			String runSuccessData = rEngine.eval("dataMeaOneStage").asString();
			
			if (runSuccessData != null && runSuccessData.equals("notRun")) {	
				System.out.println("error");
				rEngine.eval("capture.output(cat(\"\n***Error reading data.***\n\"),file=\"" + outFileName + "\",append = FALSE)"); //append to output file?
			}
			else {
				String setWd = "setwd(\"" + resultFolderPath + "\")";
				System.out.println(setWd);
				rEngine.eval(setWd);
			}
			
			String usedData = "capture.output(cat(\"\nDATA FILE: " + dataFileName + "\n\",file=\"" + outFileName + "\"))";
			String outFile = "capture.output(cat(\"\nMULTI-ENVIRONMENT ANALYSIS (ONE-STAGE)\n\"),file=\"" + outFileName + "\",append = TRUE)";
			String usedDesign = "capture.output(cat(\"\nDESIGN: " + designUsed + "\n\n\"),file=\"" + outFileName + "\",append = TRUE)";
			String sep = "capture.output(cat(\"------------------------------\n\"),file=\"" + outFileName + "\",append = TRUE)";
			String sep2 = "capture.output(cat(\"==============================\n\"),file=\"" + outFileName + "\",append = TRUE)";
			String outSpace = "capture.output(cat(\"\n\"),file=\"" + outFileName + "\",append = TRUE)";
			
			rEngine.eval(usedData);
			rEngine.eval(outFile);
			rEngine.eval(usedDesign);
						

			// OUTPUT
			// Genotype Fixed
			if (genotypeFixed) {
				String funcMeaOneStageFixed = null;
				String groupVars = null;
				if (design == "RCB" || design == "AugRCB") {
					funcMeaOneStageFixed = "meaOne1 <- try(GEOneStage.test(\"" + design + "\",dataMeaOneStage,"+ respvarVector + ",\"" + genotype + "\",\"" + block+ "\",column = NULL, rep = NULL,\"" + environment + "\", is.genoRandom = FALSE), silent=TRUE)";
					groupVars = "c(\"" + environment + "\", \"" + genotype + "\", \"" + block + "\")";
				} else if (design == "AugLS") {
					funcMeaOneStageFixed = "meaOne1 <- try(GEOneStage.test(\"" + design + "\",dataMeaOneStage,"+ respvarVector + ",\"" + genotype + "\", row = \"" + row + "\", column = \"" + column + "\", rep = NULL,\"" + environment + "\", is.genoRandom = FALSE), silent=TRUE)";
					groupVars = "c(\"" + environment + "\", \"" + genotype + "\", \"" + row + "\", \"" + column +"\")";
				} else if (design == "Alpha" || design == "LatinAlpha") {
					funcMeaOneStageFixed = "meaOne1 <- try(GEOneStage.test(\"" + design + "\",dataMeaOneStage,"+ respvarVector + ",\"" + genotype + "\",\"" + block+ "\",column = NULL,\"" + rep + "\",\"" + environment+ "\", is.genoRandom = FALSE), silent=TRUE)";
					groupVars = "c(\"" + environment + "\", \"" + genotype + "\", \"" + block + "\", \"" + rep + "\")";
				} else if (design == "RowCol" || design == "LatinRowCol") {
					funcMeaOneStageFixed = "meaOne1 <- try(GEOneStage.test(\"" + design + "\",dataMeaOneStage,"+ respvarVector + ",\"" + genotype + "\",\"" + row + "\",\"" + column + "\",\"" + rep + "\",\""+ environment + "\", is.genoRandom = FALSE), silent=TRUE)";
					groupVars = "c(\"" + environment + "\", \"" + genotype + "\", \"" + rep + "\", \"" + row + "\", \"" + column + "\")";
				}	
				
				String fixedHead = "capture.output(cat(\"GENOTYPE AS: Fixed\n\"),file=\""+ outFileName + "\",append = TRUE)";
				rEngine.eval(funcMeaOneStageFixed);
				rEngine.eval(sep2);
				rEngine.eval(fixedHead);
				rEngine.eval(sep2);
				rEngine.eval(outSpace);
				
				System.out.println(funcMeaOneStageFixed);
				String runSuccess = rEngine.eval("class(meaOne1)").asString();
				if (runSuccess != null && runSuccess.equals("try-error")) {	
					System.out.println("GEOneStage.test: error");
					String checkError = "msg <- trimStrings(strsplit(meaOne1, \":\")[[1]])";
					String checkError2 = "msg <- trimStrings(paste(strsplit(msg, \"\\n\")[[length(msg)]], collapse = \" \"))";
					String checkError3 ="msg <- gsub(\"\\\"\", \"\", msg)";
					String checkError4 = "capture.output(cat(\"*** \nERROR in GEOneStage.test function:\\n  \",msg, \"\n***\n\n\", sep = \"\"), file=\"" + outFileName + "\",append = TRUE)";
					rEngine.eval(checkError);
					rEngine.eval(checkError2);
					rEngine.eval(checkError3);
					rEngine.eval(checkError4);
					
					runningFixedSuccess =false;

				} else {

					for (int k = 0; k < respvar.length; k++) {
						printAllOutputFixed=true;
						int i = k + 1; // 1-relative index;
						String respVarHead = "capture.output(cat(\"RESPONSE VARIABLE: " + respvar[k] + "\n\"),file=\"" + outFileName + "\",append = TRUE)";
						rEngine.eval(sep);
						rEngine.eval(respVarHead);
						rEngine.eval(sep);
						
						//check if the data has too many missing observations
						double responseRate = rEngine.eval("meaOne1$output[[" + i + "]]$responseRate").asDouble();
						if (responseRate < 0.80) {
							String allNAWarning = rEngine.eval("meaOne1$output[[" + i + "]]$manyNAWarning").asString();
							String printError1 = "capture.output(cat(\"***\\n\"), file=\"" + outFileName + "\",append = TRUE)";
							String printError2 = "capture.output(cat(\"ERROR:\\n\"), file=\"" + outFileName + "\",append = TRUE)";
							String printError3 = "capture.output(cat(\"" + allNAWarning + "\\n\"), file=\"" + outFileName + "\",append = TRUE)";
							
							rEngine.eval(outSpace);
							rEngine.eval(printError1);
							rEngine.eval(printError2);
							rEngine.eval(printError3);
							rEngine.eval(printError1);
							rEngine.eval(outSpace);
							rEngine.eval(outSpace);
							printAllOutputFixed=false;
						}
						
						if (printAllOutputFixed) {
							// default output: Trial Summary
							String funcTrialSum = "funcTrialSum <- try(class.information(" + groupVars + ",meaOne1$output[[" + i + "]]$data), silent=TRUE)";
							String trialSumHead = "capture.output(cat(\"\nDATA SUMMARY:\n\n\"),file=\"" + outFileName + "\",append = TRUE)";
							String trialObsRead = "capture.output(cat(\"Number of observations read: \", meaOne1$output[["	+ i	+ "]]$obsread[[1]],\"\n\"),file=\"" + outFileName + "\",append = TRUE)";
							String trialObsUsed = "capture.output(cat(\"Number of observations used: \", meaOne1$output[["	+ i	+ "]]$obsused[[1]],\"\n\n\"),file=\""	+ outFileName + "\",append = TRUE)";
							String trialSum = "capture.output(funcTrialSum,file=\"" + outFileName + "\",append = TRUE)";
							
							rEngine.eval(funcTrialSum);
							
							String runSuccessTS = rEngine.eval("class(funcTrialSum)").asString();
							if (runSuccessTS != null && runSuccessTS.equals("try-error")) {	
								System.out.println("class info: error");
								String checkError = "msg <- trimStrings(strsplit(funcTrialSum, \":\")[[1]])";
								String checkError2 = "msg <- trimStrings(paste(strsplit(msg, \"\\n\")[[length(msg)]], collapse = \" \"))";
								String checkError3 ="msg <- gsub(\"\\\"\", \"\", msg)";
								String checkError4 = "capture.output(cat(\"*** \nERROR in class.information function:\\n  \",msg, \"\n***\n\n\", sep = \"\"), file=\"" + outFileName + "\",append = TRUE)";
								rEngine.eval(checkError);
								rEngine.eval(checkError2);
								rEngine.eval(checkError3);
								rEngine.eval(checkError4);
							}

							else {
								rEngine.eval(trialSumHead);
								rEngine.eval(trialObsRead);
								rEngine.eval(trialObsUsed);
								rEngine.eval(trialSum);
								rEngine.eval(outSpace);
							}
							
							//optional output: descriptive statistics
							String funcDesc = "outDesc <- try(DescriptiveStatistics(dataMeaOneStage, \"" + respvar[k] + "\", grp = NULL), silent=TRUE)";
							rEngine.eval(funcDesc);
		
							if (descriptiveStat) {
								String outDescStat = "capture.output(cat(\"\nDESCRIPTIVE STATISTICS:\n\n\"),file=\"" + outFileName + "\",append = TRUE)";
								String outDescStat2 = "capture.output(outDesc,file=\"" + outFileName + "\",append = TRUE)"; 
		
								String runSuccessDescStat = rEngine.eval("class(outDesc)").asString();	
								if (runSuccessDescStat != null && runSuccessDescStat.equals("try-error")) {	
									System.out.println("desc stat: error");
									String checkError = "msg <- trimStrings(strsplit(outDesc, \":\")[[1]])";
									String checkError2 = "msg <- trimStrings(paste(strsplit(msg, \"\\n\")[[length(msg)]], collapse = \" \"))";
									String checkError3 ="msg <- gsub(\"\\\"\", \"\", msg)";
									String checkError4 = "capture.output(cat(\"*** \nERROR in DescriptiveStatistics function:\\n  \",msg, \"\n***\n\n\", sep = \"\"), file=\"" + outFileName + "\",append = TRUE)";
									rEngine.eval(checkError);
									rEngine.eval(checkError2);
									rEngine.eval(checkError3);
									rEngine.eval(checkError4);
								} 

								else {
									rEngine.eval(outDescStat);
									rEngine.eval(outDescStat2);
									rEngine.eval(outSpace);
								}
							}
		
							//optional output: Variance Components
							if (varianceComponents) {
								String outVarComp = "capture.output(cat(\"\nVARIANCE COMPONENTS TABLE:\n\n\"),file=\"" + outFileName + "\",append = TRUE)";
								String outVarComp2 = "capture.output(meaOne1$output[[" + i + "]]$varcomp.table,file=\"" + outFileName + "\",append = TRUE)";
		
								rEngine.eval(outVarComp);
								rEngine.eval(outVarComp2);
								rEngine.eval(outSpace);
							}
							
							//default output: Test Genotypic Effect
//							String outAnovaTable1 = "capture.output(meaOne1$output[[" + i + "]]$testsig.Geno,file=\"" + outFileName + "\",append = TRUE)";
							String outAnovaTable1 = "capture.output(cat(\"\nTESTING FOR THE SIGNIFICANCE OF GENOTYPIC EFFECT:\n\n\"),file=\"" + outFileName + "\",append = TRUE)";
							String outAnovaTable2 = "library(lmerTest)";
							String outAnovaTable3 = "model1b <- lmer(formula(meaOne1$output[[" + i + "]]$formula1), data = meaOne1$output[[" + i + "]]$data, REML = T)";
							String outAnovaTable4 = "a.table <- anova(model1b)";
							String outAnovaTable5 = "pvalue <- formatC(as.numeric(format(a.table[1,6], scientific=FALSE)), format=\"f\")";
							String outAnovaTable6 = "a.table<-cbind(round(a.table[,1:5], digits=4),pvalue)";
							String outAnovaTable7 = "colnames(a.table)<-c(\"Df\", \"Sum Sq\", \"Mean Sq\", \"F value\", \"Denom\", \"Pr(>F)\")";
							String outAnovaTable8 = "capture.output(cat(\"Analysis of Variance Table with Satterthwaite Denominator Df\n\"),file=\"" + outFileName + "\",append = TRUE)";
							String outAnovaTable9 = "capture.output(a.table,file=\"" + outFileName + "\",append = TRUE)";
							String outAnovaTable10 = "detach(\"package:lmerTest\")";
							
//							rEngine.eval(outspace);
							rEngine.eval(outAnovaTable1);
							rEngine.eval(outAnovaTable2);
							rEngine.eval(outAnovaTable3);
							rEngine.eval(outAnovaTable4);
							rEngine.eval(outAnovaTable5);
							rEngine.eval(outAnovaTable6);
							rEngine.eval(outAnovaTable7);
//							rEngine.eval(outSpace);
							rEngine.eval(outAnovaTable8);
							rEngine.eval(outAnovaTable9);
							rEngine.eval(outSpace);
							rEngine.eval(outAnovaTable10);
													
							//default output: Test Environment Effect
							String outTestEnv1 = "capture.output(cat(\"\nTESTING FOR THE SIGNIFICANCE OF ENVIRONMENT EFFECT USING -2 LOGLIKELIHOOD RATIO TEST:\n\"),file=\"" + outFileName + "\",append = TRUE)";
							String outTestEnv2 = "capture.output(cat(\"\nFormula for Model1: \", meaOne1$output[[" + i + "]]$formula1,\"\n\"),file=\"" + outFileName + "\",append = TRUE)";
							String outTestEnv3 = "capture.output(cat(\"Formula for Model2: \", meaOne1$output[[" + i + "]]$formula3,\"\n\n\"),file=\"" + outFileName + "\",append = TRUE)";
							String outTestEnv4 = "capture.output(meaOne1$output[[" + i + "]]$testsig.Env,file=\"" + outFileName + "\",append = TRUE)";
							rEngine.eval(outTestEnv1);
							rEngine.eval(outTestEnv2);
							rEngine.eval(outTestEnv3);
							rEngine.eval(outTestEnv4);
							rEngine.eval(outSpace);
							
							//default output: Test GXE Effect
							String outTestGenoEnv1 = "capture.output(cat(\"\nTESTING FOR THE SIGNIFICANCE OF GENOTYPE X ENVIRONMENT EFFECT USING -2 LOGLIKELIHOOD RATIO TEST:\n\"),file=\"" + outFileName + "\",append = TRUE)";
							String outTestGenoEnv2 = "capture.output(cat(\"\nFormula for Model1: \", meaOne1$output[[" + i + "]]$formula1,\"\n\"),file=\"" + outFileName + "\",append = TRUE)";
							String outTestGenoEnv3 = "capture.output(cat(\"Formula for Model2: \", meaOne1$output[[" + i + "]]$formula4,\"\n\n\"),file=\"" + outFileName + "\",append = TRUE)";
							String outTestGenoEnv4 = "capture.output(meaOne1$output[[" + i + "]]$testsig.GenoEnv,file=\"" + outFileName + "\",append = TRUE)";
							rEngine.eval(outTestGenoEnv1);
							rEngine.eval(outTestGenoEnv2);
							rEngine.eval(outTestGenoEnv3);
							rEngine.eval(outTestGenoEnv4);
							rEngine.eval(outSpace);
							
							//default output: Genotype x Environment Means
							String outGenoEnv = "capture.output(cat(\"\nGENOTYPE X ENVIRONMENT MEANS:\n\n\"),file=\"" + outFileName + "\",append = TRUE)";
							String outGenoEnv2 = "capture.output(meaOne1$output[[" + i + "]]$wide.GenoEnv,file=\"" + outFileName + "\",append = TRUE)";
							rEngine.eval(outGenoEnv);
							rEngine.eval(outGenoEnv2);
							rEngine.eval(outSpace);
													
							//default output: Genotype Means
							String outDescStat = "capture.output(cat(\"\nGENOTYPE LSMEANS AND STANDARD ERRORS:\n\n\"),file=\"" + outFileName + "\",append = TRUE)";
							String outDescStat2 = "capture.output(meaOne1$output[[" + i + "]]$means.Geno,file=\"" + outFileName + "\",append = TRUE)";
							rEngine.eval(outDescStat);
							rEngine.eval(outDescStat2);
							rEngine.eval(outSpace);
							
							//default output: statistics on SED
							String outSedStat1 = "capture.output(cat(\"\nSTANDARD ERROR OF THE DIFFERENCE (SED):\n\n\"),file=\"" + outFileName + "\",append = TRUE)";
							String outSedStat2 = "capture.output(meaOne1$output[[" + i + "]]$sedTable,file=\"" + outFileName + "\",append = TRUE)";
							rEngine.eval(outSedStat1);
							rEngine.eval(outSedStat2);
							rEngine.eval(outSpace);
												
							//optional output: PerformPairwise
							if (performPairwise) {
								double pairwiseSig = Double.valueOf(pairwiseAlpha);
								
//								rEngine.rniAssign("trmt.levels",	rEngine.rniPutStringArray(genotypeLevels),	0); // a string array from OptionsPage
								if (compareControl) {
//									rEngine.rniAssign("controlLevels",rEngine.rniPutStringArray(controlLevels),0); // a string array from OptionsPage
									
									String funcPwC = "pwControl <- try(ssa.pairwise(meaOne1$output[[" + i + "]]$model, type = \"Dunnett\", alpha = "	+ pairwiseSig + ", control.level = " + controlLevelsVector + "), silent=TRUE)";
									String outCompareControl = "capture.output(cat(\"\nSIGNIFICANT PAIRWISE COMPARISONS (IF ANY): \nCompared with control(s)\n\n\"),file=\"" + outFileName	+ "\",append = TRUE)";
									String outCompareControl2 = "capture.output(pwControl$result,file=\""	+ outFileName	+ "\",append = TRUE)";
									System.out.println(funcPwC);
									rEngine.eval(funcPwC);
									rEngine.eval(outCompareControl);
									
									String runSuccessPwC = rEngine.eval("class(pwControl)").asString();	
									if (runSuccessPwC != null && runSuccessPwC.equals("try-error")) {	
										System.out.println("compare with control: error");
										String checkError = "msg <- trimStrings(strsplit(pwControl, \":\")[[1]])";
										String checkError2 = "msg <- trimStrings(paste(strsplit(msg, \"\\n\")[[length(msg)]], collapse = \" \"))";
										String checkError3 ="msg <- gsub(\"\\\"\", \"\", msg)";
										String checkError4 = "capture.output(cat(\"*** \nERROR in ssa.pairwise function:\\n  \",msg, \"\n***\n\n\", sep = \"\"), file=\"" + outFileName + "\",append = TRUE)";
										rEngine.eval(checkError);
										rEngine.eval(checkError2);
										rEngine.eval(checkError3);
										rEngine.eval(checkError4);
										rEngine.eval(outSpace);
										rEngine.eval(outSpace);
									}
									else {
										rEngine.eval(outCompareControl2);
										
										// display warning generated by checkTest in ssa.pairwise
										String warningControlTest = rEngine.eval("pwControl$controlTestWarning").asString();
										
										if (!warningControlTest.equals("NONE")) {
											String warningCheckTest2 = "capture.output(cat(\"----- \nNOTE:\\n\"), file=\"" + outFileName + "\",append = TRUE)";
											String warningCheckTest3 = "capture.output(cat(\"" + warningControlTest + "\\n\"), file=\"" + outFileName + "\",append = TRUE)";
																					
											rEngine.eval(warningCheckTest2);
											rEngine.eval(warningCheckTest3);
										}
										rEngine.eval(outSpace);
										rEngine.eval(outSpace);
										System.out.println("pairwise control test:" + warningControlTest);

									}
								} else if (performAllPairwise) {
									String outPerformAllPairwise = "capture.output(cat(\"\nSIGNIFICANT PAIRWISE COMPARISONS (IF ANY): \n\n\"),file=\""	+ outFileName	+ "\",append = TRUE)";
									rEngine.eval(outPerformAllPairwise);
									if (genotypeLevels.length > 0	& genotypeLevels.length < 16) {
										String funcPwAll = "pwAll <- try(ssa.pairwise(meaOne1$output[[" + i + "]]$model, type = \"Tukey\", alpha = "+ pairwiseSig + ", control.level = NULL), silent=TRUE)";
										String outPerformAllPairwise2n = "capture.output(pwAll$result,file=\"" + outFileName + "\",append = TRUE)";
										rEngine.eval(funcPwAll);
										
										String runSuccessPwAll = rEngine.eval("class(pwAll)").asString();
										if (runSuccessPwAll != null && runSuccessPwAll.equals("try-error")) {	
											System.out.println("all pairwise: error");
											String checkError = "msg <- trimStrings(strsplit(pwAll, \":\")[[1]])";
											String checkError2 = "msg <- trimStrings(paste(strsplit(msg, \"\\n\")[[length(msg)]], collapse = \" \"))";
											String checkError3 ="msg <- gsub(\"\\\"\", \"\", msg)";
											String checkError4 = "capture.output(cat(\"*** \nERROR in ssa.pairwise function:\\n  \",msg, \"\n***\n\n\", sep = \"\"), file=\"" + outFileName + "\",append = TRUE)";
											rEngine.eval(checkError);
											rEngine.eval(checkError2);
											rEngine.eval(checkError3);
											rEngine.eval(checkError4);
										}
										else {
											rEngine.eval(outPerformAllPairwise2n);
											rEngine.eval(outSpace);
											rEngine.eval(outSpace);
										}	
									} else {
										String nLevelsLarge = "capture.output(cat(\"***\nExceeded maximum number of genotypes that can be compared. \n***\n\n\"),file=\"" + outFileName + "\",append = TRUE)";
										rEngine.eval(nLevelsLarge);
									}
								}
							}
							
														
							String genoEnvMeans = "genoEnvMeans <- meaOne1$output[[" + i + "]]$means.GenoEnvCode";
							rEngine.eval(genoEnvMeans);
							System.out.println(genoEnvMeans);
							
//							String ybarName = respvar[k] + "_means";
														
							//optional output if selected and if the number of environment levels is at least 5: Stability Analysis using Regression
//							if (stabilityFinlay) {
//								if (environmentLevels.length > 4) {
//									String funcStability1 = "funcStability1 <- try(stability.analysis(genoEnvMeans, \"" + ybarName + "\", \"" + genotype + "\", \"" + environment + "\", method = \"regression\"), silent=TRUE)";
//									String outTestStability1 = "capture.output(cat(\"\nSTABILITY ANALYSIS USING FINLAY-WILKINSON MODEL:\n\n\"),file=\"" + outFileName + "\",append = TRUE)";
//									String outTestStability1b = "capture.output(funcStability1[[1]][[1]]$stability,file=\"" + outFileName + "\",append = TRUE)";
//															
//									rEngine.eval(funcStability1);
//									rEngine.eval(outTestStability1);
//									System.out.println(funcStability1);
//									
//									String runSuccessStab = rEngine.eval("class(funcStability1)").asString();
//									if (runSuccessStab != null && runSuccessStab.equals("try-error")) {	
//										System.out.println("stability reg: error");
//										String checkError = "msg <- trimStrings(strsplit(funcStability1, \":\")[[1]])";
//										String checkError2 = "msg <- trimStrings(paste(strsplit(msg, \"\\n\")[[length(msg)]], collapse = \" \"))";
//										String checkError3 ="msg <- gsub(\"\\\"\", \"\", msg)";
//										String checkError4 = "capture.output(cat(\"*** \nERROR in stability.analysis function:\\n  \",msg, \"\n***\n\n\", sep = \"\"), file=\"" + outFileName + "\",append = TRUE)";
//										rEngine.eval(checkError);
//										rEngine.eval(checkError2);
//										rEngine.eval(checkError3);
//										rEngine.eval(checkError4);
//									}
//									else {
//										rEngine.eval(outTestStability1b);
//										rEngine.eval(outSpace);
//									}
//								} else {
//									String outRemark = "capture.output(cat(\"\nSTABILITY ANALYSIS USING FINLAY-WILKINSON MODEL:\n\"),file=\"" + outFileName + "\",append = TRUE)";
//									String outRemark2 = "capture.output(cat(\"***This is not done. The environment factor should have at least five levels.***\n\n\"),file=\"" + outFileName + "\",append = TRUE)";
//									
//									rEngine.eval(outRemark);
//									rEngine.eval(outRemark2);
//								}
//							}
							
							//optional output if selected and if the number of environment levels is at least 5: Stability Analysis using Shukla
//							if (stabilityShukla) {
//								if (environmentLevels.length > 4) {
//									String funcStability2 = "funcStability2 <- try(stability.analysis(genoEnvMeans, \"" + ybarName + "\", \"" + genotype + "\", \"" + environment + "\", method = \"shukla\"), silent=TRUE)";
//									String outTestStability2 = "capture.output(cat(\"\nSTABILITY ANALYSIS USING SHUKLA'S MODEL:\n\n\"),file=\"" + outFileName + "\",append = TRUE)";
//									String outTestStability2b = "capture.output(funcStability2[[1]][[1]]$stability,file=\"" + outFileName + "\",append = TRUE)";
//									
//									System.out.println(funcStability2);
//									rEngine.eval(funcStability2);
//									rEngine.eval(outTestStability2);
//									
//									String runSuccessStab = rEngine.eval("class(funcStability2)").asString();
//									if (runSuccessStab != null && runSuccessStab.equals("try-error")) {	
//										System.out.println("stability shukla: error");
//										String checkError = "msg <- trimStrings(strsplit(funcStability2, \":\")[[1]])";
//										String checkError2 = "msg <- trimStrings(paste(strsplit(msg, \"\\n\")[[length(msg)]], collapse = \" \"))";
//										String checkError3 ="msg <- gsub(\"\\\"\", \"\", msg)";
//										String checkError4 = "capture.output(cat(\"*** \nERROR in stability.analysis function:\\n  \",msg, \"\n***\n\n\", sep = \"\"), file=\"" + outFileName + "\",append = TRUE)";
//										rEngine.eval(checkError);
//										rEngine.eval(checkError2);
//										rEngine.eval(checkError3);
//										rEngine.eval(checkError4);
//									}
//									else {
//										rEngine.eval(outTestStability2b);
//										rEngine.eval(outSpace);
//									}
//								} else {
//									String outRemark = "capture.output(cat(\"\nSTABILITY ANALYSIS USING SHUKLA'S MODEL:\n\"),file=\"" + outFileName + "\",append = TRUE)";
//									String outRemark2 = "capture.output(cat(\"***This is not done. The environment factor should have at least five levels.***\n\n\"),file=\"" + outFileName + "\",append = TRUE)";
//									
//									rEngine.eval(outRemark);
//									rEngine.eval(outRemark2);
//								}
//							}
												
							//optional output if selected and if the number of environment levels is at least 3: AMMI analysis
//							if (ammi) {
//								if (environmentLevels.length > 2) {
//									//String ammiOut = "ammiOut <- try(ammi.analysis(genoEnvMeans[,match(\""+ environment +"\", names(genoEnvMeans))], genoEnvMeans[,match(\"" + genotype + "\", names(genoEnvMeans))], meaOne1$output[[" + i + "]]$harmonicMean, genoEnvMeans[,match(\"" + ybarName + "\", names(genoEnvMeans))], meaOne1$output[[" + i + "]]$MSE, number = FALSE, graph = \"biplot\", yVar = \"" + ybarName +"\"), silent=TRUE)";
//									String ammiOut = "ammiOut <- try(ammi.analysis(genoEnvMeans[,match(\"CodedEnv\", names(genoEnvMeans))], genoEnvMeans[,match(\"CodedGeno\", names(genoEnvMeans))], meaOne1$output[[" + i + "]]$harmonicMean, genoEnvMeans[,match(\"" + ybarName + "\", names(genoEnvMeans))], meaOne1$output[[" + i + "]]$MSE, number = FALSE, graph = \"biplot\", yVar = \"" + ybarName +"\"), silent=TRUE)";
//									String outAmmi1 = "capture.output(cat(\"\nAMMI ANALYSIS:\n\"),file=\"" + outFileName + "\",append = TRUE)";
//									String outAmmi2 = "capture.output(cat(\"Percentage of Total Variation Accounted for by the Principal Components: \n\n\"),file=\"" + outFileName + "\",append = TRUE)";;
//									rEngine.eval(ammiOut);
//									rEngine.eval(outAmmi1);
//									System.out.println(ammiOut);
//									
//									String runSuccessAmmi = rEngine.eval("class(ammiOut)").asString();
//									if (runSuccessAmmi != null && runSuccessAmmi.equals("try-error")) {	
//										System.out.println("ammi: error");
//										String checkError = "msg <- trimStrings(strsplit(ammiOut, \":\")[[1]])";
//										String checkError2 = "msg <- trimStrings(paste(strsplit(msg, \"\\n\")[[length(msg)]], collapse = \" \"))";
//										String checkError3 ="msg <- gsub(\"\\\"\", \"\", msg)";
//										String checkError4 = "capture.output(cat(\"*** \nERROR in ammi.analysis function:\\n  \",msg, \"\n***\n\n\", sep = \"\"), file=\"" + outFileName + "\",append = TRUE)";
//										rEngine.eval(checkError);
//										rEngine.eval(checkError2);
//										rEngine.eval(checkError3);
//										rEngine.eval(checkError4);
//									} else {
//										
//										String outAmmi3 = "capture.output(ammiOut$analysis,file=\"" + outFileName + "\",append = TRUE)";
//										rEngine.eval(outAmmi2);
//										rEngine.eval(outAmmi3);
//										rEngine.eval(outSpace);
//									}
//								} else {
//									String outRemark = "capture.output(cat(\"\nAMMI ANALYSIS:\n\"),file=\"" + outFileName + "\",append = TRUE)";
//									String outRemark2 = "capture.output(cat(\"***This is not done. The environment factor should have at least three levels.***\n\n\n\"),file=\"" + outFileName + "\",append = TRUE)";
//									
//									rEngine.eval(outRemark);
//									rEngine.eval(outRemark2);
//								}
//							}
							
							//optional output if selected and if the number of environment levels is at least 3: GGE analysis
//							if (gge) {
//								if (environmentLevels.length > 2) {
//									//f=0.5
//									String ggeOut = "ggeOut <- try(gge.analysis(genoEnvMeans[,match(\"CodedEnv\", names(genoEnvMeans))], genoEnvMeans[,match(\"CodedGeno\", names(genoEnvMeans))], meaOne1$output[[" + i + "]]$harmonicMean, genoEnvMeans[,match(\"" + ybarName + "\", names(genoEnvMeans))], meaOne1$output[[" + i + "]]$MSE, number = FALSE, graph = \"biplot\", yVar = \"" + ybarName +"\", f=0.5), silent=TRUE)"; 
//									String outAmmi1 = "capture.output(cat(\"\nGGE ANALYSIS:\n\"),file=\"" + outFileName + "\",append = TRUE)";
//									String outAmmi2 = "capture.output(cat(\"Percentage of Total Variation Accounted for by the Principal Components: \n\n\"),file=\"" + outFileName + "\",append = TRUE)";;
//									rEngine.eval(ggeOut);
//									rEngine.eval(outAmmi1);
//									System.out.println(ggeOut);
//									
//									String runSuccessAmmi = rEngine.eval("class(ggeOut)").asString();
//									if (runSuccessAmmi != null && runSuccessAmmi.equals("try-error")) {	
//										System.out.println("gge1: error");
//										String checkError = "msg <- trimStrings(strsplit(ggeOut, \":\")[[1]])";
//										String checkError2 = "msg <- trimStrings(paste(strsplit(msg, \"\\n\")[[length(msg)]], collapse = \" \"))";
//										String checkError3 ="msg <- gsub(\"\\\"\", \"\", msg)";
//										String checkError4 = "capture.output(cat(\"*** \nERROR in gge.analysis function (f=0.5):\\n  \",msg, \"\n***\n\n\", sep = \"\"), file=\"" + outFileName + "\",append = TRUE)";
//										rEngine.eval(checkError);
//										rEngine.eval(checkError2);
//										rEngine.eval(checkError3);
//										rEngine.eval(checkError4);
//									} else {
//										
//										String outAmmi3 = "capture.output(ggeOut$analysis,file=\"" + outFileName + "\",append = TRUE)";
//										rEngine.eval(outAmmi2);
//										rEngine.eval(outAmmi3);
//										rEngine.eval(outSpace);
//									}
//									
//									//f=0
//									String ggeOut2 = "ggeOut2 <- try(gge.analysis(genoEnvMeans[,match(\"CodedEnv\", names(genoEnvMeans))], genoEnvMeans[,match(\"CodedGeno\", names(genoEnvMeans))], meaOne1$output[[" + i + "]]$harmonicMean, genoEnvMeans[,match(\"" + ybarName + "\", names(genoEnvMeans))], meaOne1$output[[" + i + "]]$MSE, number = FALSE, graph = \"biplot\", yVar = \"" + ybarName +"\", f=0), silent=TRUE)"; 
//									rEngine.eval(ggeOut2);
//									System.out.println(ggeOut2);
//									
//									String runSuccessAmmi2 = rEngine.eval("class(ggeOut2)").asString();
//									if (runSuccessAmmi2 != null && runSuccessAmmi2.equals("try-error")) {	
//										System.out.println("gge2: error");
//										String checkError = "msg <- trimStrings(strsplit(ggeOut2, \":\")[[1]])";
//										String checkError2 = "msg <- trimStrings(paste(strsplit(msg, \"\\n\")[[length(msg)]], collapse = \" \"))";
//										String checkError3 ="msg <- gsub(\"\\\"\", \"\", msg)";
//										String checkError4 = "capture.output(cat(\"*** \nERROR in gge.analysis function (f=0):\\n  \",msg, \"\n***\n\n\", sep = \"\"), file=\"" + outFileName + "\",append = TRUE)";
//										rEngine.eval(checkError);
//										rEngine.eval(checkError2);
//										rEngine.eval(checkError3);
//										rEngine.eval(checkError4);
//									} 
//									
//									//f=1
//									String ggeOut3 = "ggeOut3 <- try(gge.analysis(genoEnvMeans[,match(\"CodedEnv\", names(genoEnvMeans))], genoEnvMeans[,match(\"CodedGeno\", names(genoEnvMeans))], meaOne1$output[[" + i + "]]$harmonicMean, genoEnvMeans[,match(\"" + ybarName + "\", names(genoEnvMeans))], meaOne1$output[[" + i + "]]$MSE, number = FALSE, graph = \"biplot\", yVar = \"" + ybarName +"\", f=1), silent=TRUE)"; 
//									rEngine.eval(ggeOut3);
//									System.out.println(ggeOut3);
//									
//									String runSuccessAmmi3 = rEngine.eval("class(ggeOut3)").asString();
//									if (runSuccessAmmi3 != null && runSuccessAmmi3.equals("try-error")) {	
//										System.out.println("gge2: error");
//										String checkError = "msg <- trimStrings(strsplit(ggeOut2, \":\")[[1]])";
//										String checkError2 = "msg <- trimStrings(paste(strsplit(msg, \"\\n\")[[length(msg)]], collapse = \" \"))";
//										String checkError3 ="msg <- gsub(\"\\\"\", \"\", msg)";
//										String checkError4 = "capture.output(cat(\"*** \nERROR in gge.analysis function (f=1):\\n  \",msg, \"\n***\n\n\", sep = \"\"), file=\"" + outFileName + "\",append = TRUE)";
//										rEngine.eval(checkError);
//										rEngine.eval(checkError2);
//										rEngine.eval(checkError3);
//										rEngine.eval(checkError4);
//									}
//								} else {
//									String outRemark = "capture.output(cat(\"\nGGE ANALYSIS:\n\"),file=\"" + outFileName + "\",append = TRUE)";
//									String outRemark2 = "capture.output(cat(\"***This is not done. The environment factor should have at least three levels.***\n\n\n\"),file=\"" + outFileName + "\",append = TRUE)";
//									
//									rEngine.eval(outRemark);
//									rEngine.eval(outRemark2);
//								}
//							}
							
							
//							//if levels of Geno and Env are recoded, display new code for genotype and environment levels
//							String recodedLevels = rEngine.eval("meaOne1$output[[" + i + "]]$recodedLevels").asBool().toString();
//							
//							System.out.println("recodedLevels: " + recodedLevels);
//							
//							if (recodedLevels.equals("TRUE")) {
//								String outLegends = "capture.output(cat(\"\nCODES USED IN GRAPHS:\n\n\"),file=\"" + outFileName + "\",append = TRUE)";
//								String outLegends2 = "capture.output(meaOne1$output[[" + i + "]]$newCodingGeno,file=\"" + outFileName + "\",append = TRUE)";
//								String outLegends3 = "capture.output(meaOne1$output[[" + i + "]]$newCodingEnv,file=\"" + outFileName + "\",append = TRUE)";
//								rEngine.eval(outLegends);
//								rEngine.eval(outLegends2);
//								rEngine.eval(outSpace);
//								rEngine.eval(outLegends3);
//								rEngine.eval(outSpace);
//								rEngine.eval(outSpace);
//							} else {
//								rEngine.eval(outSpace);
//							}
//							
//							//create response plots
//							String responsePlot1 = "dataCoded <- meaOne1$output[[" + i + "]]$data";
//							String responsePlot2 = "nlevelsEnv <- meaOne1$output[[" + i + "]]$nlevelsEnv";
//							String responsePlot3 = "nlevelsGeno <- meaOne1$output[[" + i + "]]$nlevelsGeno";
//							String responsePlot4 = "resPlot1 <- try(GraphLine(data=dataCoded, outputPath=\"" + resultFolderPath + "\", yVars =c(\"" + respvar[k] + "\"), xVar =c(\"CodedGeno\"), lineVars =c(\"CodedEnv\"), mTitle =\"Response Plot of " + respvar[k] + "\", yAxisLab =c(\"" + respvar[k] + "\"), xAxisLab =\"" + genotype + "\", yMinValue = c(NA), yMaxValue = c(NA), axisLabelStyle = 2, byVar = NULL, plotCol = c(1:nlevelsEnv), showLineLabels =TRUE, showLeg = FALSE, boxed = TRUE, linePtTypes=rep(\"b\", nlevelsEnv), lineTypes=rep(1, nlevelsEnv), lineWidths=rep(1, nlevelsEnv), pointChars=rep(\" \", nlevelsEnv), pointCharSizes=rep(1, nlevelsEnv), multGraphs =FALSE), silent = TRUE)";
//							String responsePlot5 = "resPlot2 <- try(GraphLine(data=dataCoded, outputPath=\"" + resultFolderPath + "\", yVars =c(\"" + respvar[k] + "\"), xVar =c(\"CodedEnv\"), lineVars =c(\"CodedGeno\"), mTitle =\"Response Plot of " + respvar[k] + "\", yAxisLab =c(\"" + respvar[k] + "\"), xAxisLab =\"" + environment + "\", yMinValue = c(NA), yMaxValue = c(NA), axisLabelStyle = 2, byVar = NULL, plotCol = c(1:nlevelsGeno), showLineLabels =TRUE, showLeg = FALSE, boxed = TRUE, linePtTypes=rep(\"b\", nlevelsGeno), lineTypes=rep(1, nlevelsGeno), lineWidths=rep(1, nlevelsGeno), pointChars=rep(\" \", nlevelsGeno), pointCharSizes=rep(1, nlevelsGeno), multGraphs =FALSE), silent = TRUE)";
//							
//							System.out.println(responsePlot1);
//							System.out.println(responsePlot2);
//							System.out.println(responsePlot3);
//							System.out.println(responsePlot4);
//							System.out.println(responsePlot5);
//							
//							rEngine.eval(responsePlot1);
//							rEngine.eval(responsePlot2);
//							rEngine.eval(responsePlot3);
//							rEngine.eval(responsePlot4);
//							rEngine.eval(responsePlot5);
//							
//							String runSuccessPlot1 = rEngine.eval("class(resPlot1)").asString();
//							if (runSuccessPlot1 != null && runSuccessPlot1.equals("try-error")) {	
//								System.out.println("response plot geno: error");
//								String checkError = "msg <- trimStrings(strsplit(resPlot1, \":\")[[1]])";
//								String checkError2 = "msg <- trimStrings(paste(strsplit(msg, \"\\n\")[[length(msg)]], collapse = \" \"))";
//								String checkError3 ="msg <- gsub(\"\\\"\", \"\", msg)";
//								String checkError4 = "capture.output(cat(\"*** \nERROR in GraphLine function (geno):\\n  \",msg, \"\n***\n\n\", sep = \"\"), file=\"" + outFileName + "\",append = TRUE)";
//								rEngine.eval(checkError);
//								rEngine.eval(checkError2);
//								rEngine.eval(checkError3);
//								rEngine.eval(checkError4);
//							}
//							
//							String runSuccessPlot2 = rEngine.eval("class(resPlot2)").asString();
//							if (runSuccessPlot2 != null && runSuccessPlot2.equals("try-error")) {	
//								System.out.println("response plot env: error");
//								String checkError = "msg <- trimStrings(strsplit(resPlot2, \":\")[[1]])";
//								String checkError2 = "msg <- trimStrings(paste(strsplit(msg, \"\\n\")[[length(msg)]], collapse = \" \"))";
//								String checkError3 ="msg <- gsub(\"\\\"\", \"\", msg)";
//								String checkError4 = "capture.output(cat(\"*** \nERROR in GraphLine function (env):\\n  \",msg, \"\n***\n\n\", sep = \"\"), file=\"" + outFileName + "\",append = TRUE)";
//								rEngine.eval(checkError);
//								rEngine.eval(checkError2);
//								rEngine.eval(checkError3);
//								rEngine.eval(checkError4);
//							}
							
							rEngine.eval(outSpace);	
						}
					} //end of for loop respvars
					
					//default output: save Genotype x Environment Means to a csv file
					String checkGenoEnvMean = rEngine.eval("meaOne1$meansGenoEnvWarning").asString();
					System.out.println("checkGenoEnvMean: " + checkGenoEnvMean);
					
					if (checkGenoEnvMean.equals("empty")) {
						System.out.println("Saving geno x env means not done.");
					} else {
						String funcSaveGEMeansCsv = "saveGEMeans <- try(write.table(meaOne1$means.GenoEnv.all,file =\"" + resultFolderPath + "GenoEnvMeans_fixed.csv\",sep=\",\",row.names=FALSE), silent=TRUE)";
						System.out.println(funcSaveGEMeansCsv);
					  	rEngine.eval(funcSaveGEMeansCsv);
						
						String runSuccessSaveGEMeans = rEngine.eval("class(saveGEMeans)").asString();
						if (runSuccessSaveGEMeans != null && runSuccessSaveGEMeans.equals("try-error")) {	
							System.out.println("save GxE means: error");
							String checkError = "msg <- trimStrings(strsplit(saveGEMeans, \":\")[[1]])";
							String checkError2 = "msg <- trimStrings(paste(strsplit(msg, \"\\n\")[[length(msg)]], collapse = \" \"))";
							String checkError3 ="msg <- gsub(\"\\\"\", \"\", msg)";
							String checkError4 = "capture.output(cat(\"*** \nERROR in saving genotype x environment means to a file:\\n  \",msg, \"\n***\n\n\", sep = \"\"), file=\"" + outFileName + "\",append = TRUE)";
							rEngine.eval(checkError);
							rEngine.eval(checkError2);
							rEngine.eval(checkError3);
							rEngine.eval(checkError4);
						}

					}

					//default output: save Genotype Means to a csv file
					String checkGenoMean = rEngine.eval("meaOne1$meansGenoWarning").asString();
					System.out.println("checkGenoMean: " + checkGenoMean);
					
					if (checkGenoMean.equals("empty")) {
						System.out.println("Saving geno means not done.");
					} else {
						String funcSaveGMeansCsv = "saveGMeans <- try(write.table(meaOne1$means.Geno.all,file =\"" + resultFolderPath + "GenoMeans_fixed.csv\",sep=\",\",row.names=FALSE), silent=TRUE)";
						System.out.println(funcSaveGMeansCsv);
					  	rEngine.eval(funcSaveGMeansCsv);
						
					  	String runSuccessSaveGMeans = rEngine.eval("class(saveGMeans)").asString();
						if (runSuccessSaveGMeans != null && runSuccessSaveGMeans.equals("try-error")) {	
							System.out.println("save G means: error");
							String checkError = "msg <- trimStrings(strsplit(saveGMeans, \":\")[[1]])";
							String checkError2 = "msg <- trimStrings(paste(strsplit(msg, \"\\n\")[[length(msg)]], collapse = \" \"))";
							String checkError3 ="msg <- gsub(\"\\\"\", \"\", msg)";
							String checkError4 = "capture.output(cat(\"*** \nERROR in saving genotype means to a file:\\n  \",msg, \"\n***\n\n\", sep = \"\"), file=\"" + outFileName + "\",append = TRUE)";
							rEngine.eval(checkError);
							rEngine.eval(checkError2);
							rEngine.eval(checkError3);
							rEngine.eval(checkError4);
						}
					}
					
					//optional output: diagnostic plots for genotype fixed
					if (diagnosticPlot) {
						String diagPlotsMea1SFunc = "diagPlotsMea1S <- try(graph.mea1s.diagplots(dataMeaOneStage, " + respvarVector + ", is.random = FALSE, meaOne1), silent=TRUE)";
						System.out.println(diagPlotsMea1SFunc);
						rEngine.eval(diagPlotsMea1SFunc);
						
						String runSuccessDiag = rEngine.eval("class(diagPlotsMea1S)").asString();
						if (runSuccessDiag != null && runSuccessDiag.equals("try-error")) {	
							System.out.println("diagnostic plot: error");
							String checkError = "msg <- trimStrings(strsplit(diagPlotsMea1S, \":\")[[1]])";
							String checkError2 = "msg <- trimStrings(paste(strsplit(msg, \"\\n\")[[length(msg)]], collapse = \" \"))";
							String checkError3 ="msg <- gsub(\"\\\"\", \"\", msg)";
							String checkError4 = "capture.output(cat(\"*** \nERROR in creating diagnostic plot (fixed genotype):\\n  \",msg, \"\n***\n\n\", sep = \"\"), file=\"" + outFileName + "\",append = TRUE)";
							rEngine.eval(checkError);
							rEngine.eval(checkError2);
							rEngine.eval(checkError3);
							rEngine.eval(checkError4);
						}
					}
				} //end of else for if runSuccess
			} //end of Fixed
			
			// Genotype Random
			if (genotypeRandom) {
				String funcMeaOneStageRandom = null;
				String groupVars = null;
				if (design == "RCB" || design == "AugRCB") {
					funcMeaOneStageRandom = "meaOne2 <- try(GEOneStage.test(\"" + design + "\",dataMeaOneStage,"+ respvarVector + ",\"" + genotype + "\",\"" + block+ "\",column = NULL, rep = NULL,\"" + environment+ "\", is.genoRandom = TRUE), silent=TRUE)";
					groupVars = "c(\"" + environment + "\", \"" + genotype + "\", \"" + block + "\")";
				} else if (design == "AugLS") {
					funcMeaOneStageRandom = "meaOne2 <- try(GEOneStage.test(\"" + design + "\",dataMeaOneStage,"+ respvarVector + ",\"" + genotype + "\", row = \"" + row + "\", column = \"" + column + "\", rep = NULL,\"" + environment + "\", is.genoRandom = TRUE), silent=TRUE)";
					groupVars = "c(\"" + environment + "\", \"" + genotype + "\", \"" + row + "\", \"" + column +"\")";
				} else if (design == "Alpha" || design == "LatinAlpha") {
				 	funcMeaOneStageRandom = "meaOne2 <- try(GEOneStage.test(\"" + design + "\",dataMeaOneStage,"+ respvarVector + ",\"" + genotype + "\",\"" + block+ "\",column = NULL,\"" + rep + "\",\"" + environment+ "\", is.genoRandom = TRUE), silent=TRUE)";
					groupVars = "c(\"" + environment + "\", \"" + genotype + "\", \"" + block + "\", \"" + rep + "\")";
				} else if (design == "RowCol" || design == "LatinRowCol") {
					funcMeaOneStageRandom = "meaOne2 <- try(GEOneStage.test(\"" + design + "\",dataMeaOneStage,"+ respvarVector + ",\"" + genotype + "\",\"" + row+ "\",\"" + column + "\",\"" + rep + "\",\""+ environment + "\", is.genoRandom = TRUE), silent=TRUE)";
					groupVars = "c(\"" + environment + "\", \"" + genotype + "\", \"" + rep + "\", \"" + row + "\", \"" + column + "\")";
				}

				String randomHead = "capture.output(cat(\"GENOTYPE AS: Random\n\"),file=\"" + outFileName + "\",append = TRUE)";
				rEngine.eval(funcMeaOneStageRandom);
				rEngine.eval(sep2);
				rEngine.eval(randomHead);
				rEngine.eval(sep2);
				rEngine.eval(outSpace);
				
				System.out.println(funcMeaOneStageRandom);
				String runSuccess2 = rEngine.eval("class(meaOne2)").asString();
				if (runSuccess2 != null && runSuccess2.equals("try-error")) {	
					System.out.println("GEOneStage.test: error");
					String checkError = "msg <- trimStrings(strsplit(meaOne2, \":\")[[1]])";
					String checkError2 = "msg <- trimStrings(paste(strsplit(msg, \"\\n\")[[length(msg)]], collapse = \" \"))";
					String checkError3 ="msg <- gsub(\"\\\"\", \"\", msg)";
					String checkError4 = "capture.output(cat(\"*** \nERROR in GEOneStage.test function:\\n  \",msg, \"\n***\n\n\", sep = \"\"), file=\"" + outFileName + "\",append = TRUE)";
					rEngine.eval(checkError);
					rEngine.eval(checkError2);
					rEngine.eval(checkError3);
					rEngine.eval(checkError4);

					runningRandomSuccess=false;
				}
				else {

				for (int k = 0; k < respvar.length; k++) {
					printAllOutputRandom=true;
					int i = k + 1; // 1-relative index;
					String respVarHead = "capture.output(cat(\"RESPONSE VARIABLE: " + respvar[k] + "\n\"),file=\"" + outFileName + "\",append = TRUE)";
					rEngine.eval(sep);
					rEngine.eval(respVarHead);
					rEngine.eval(sep);
					
					double responseRate = rEngine.eval("meaOne2$output[[" + i + "]]$responseRate").asDouble();
					if (responseRate < 0.80) {
						String allNAWarning = rEngine.eval("meaOne2$output[[" + i + "]]$manyNAWarning").asString();
						String printError1 = "capture.output(cat(\"***\\n\"), file=\"" + outFileName + "\",append = TRUE)";
						String printError2 = "capture.output(cat(\"ERROR:\\n\"), file=\"" + outFileName + "\",append = TRUE)";
						String printError3 = "capture.output(cat(\"" + allNAWarning + "\\n\"), file=\"" + outFileName + "\",append = TRUE)";
						
						rEngine.eval(outSpace);
						rEngine.eval(printError1);
						rEngine.eval(printError2);
						rEngine.eval(printError3);
						rEngine.eval(printError1);
						rEngine.eval(outSpace);
						rEngine.eval(outSpace);
						printAllOutputRandom=false;
					}
					
					if (printAllOutputRandom) {
						//default output: Trial Summary
						String funcTrialSum = "funcTrialSum <- try(class.information(" + groupVars + ",meaOne2$output[[" + i + "]]$data), silent=TRUE)";
						String trialSumHead = "capture.output(cat(\"\nDATA SUMMARY:\n\n\"),file=\"" + outFileName + "\",append = TRUE)";
						String trialObsRead = "capture.output(cat(\"Number of observations read: \", meaOne2$output[["	+ i	+ "]]$obsread[[1]],\"\n\"),file=\"" + outFileName + "\",append = TRUE)";
						String trialObsUsed = "capture.output(cat(\"Number of observations used: \", meaOne2$output[["	+ i	+ "]]$obsused[[1]],\"\n\n\"),file=\""	+ outFileName + "\",append = TRUE)";
						String trialSum = "capture.output(funcTrialSum,file=\"" + outFileName + "\",append = TRUE)";

						rEngine.eval(funcTrialSum);
						
						String runSuccessTS = rEngine.eval("class(funcTrialSum)").asString();
						if (runSuccessTS != null && runSuccessTS.equals("try-error")) {	
							System.out.println("class info: error");
							String checkError = "msg <- trimStrings(strsplit(funcTrialSum, \":\")[[1]])";
							String checkError2 = "msg <- trimStrings(paste(strsplit(msg, \"\\n\")[[length(msg)]], collapse = \" \"))";
							String checkError3 ="msg <- gsub(\"\\\"\", \"\", msg)";
							String checkError4 = "capture.output(cat(\"*** \nERROR in class.information function:\\n  \",msg, \"\n***\n\n\", sep = \"\"), file=\"" + outFileName + "\",append = TRUE)";
							rEngine.eval(checkError);
							rEngine.eval(checkError2);
							rEngine.eval(checkError3);
							rEngine.eval(checkError4);
						}

						else {
							rEngine.eval(trialSumHead);
							rEngine.eval(trialObsRead);
							rEngine.eval(trialObsUsed);
							rEngine.eval(trialSum);
							rEngine.eval(outSpace);
						}	

						//optional output: for descriptive stat
						String funcDesc = "outDesc <- DescriptiveStatistics(dataMeaOneStage, \"" + respvar[k] + "\", grp = NULL)";
						rEngine.eval(funcDesc);
							
						if (descriptiveStat) {
							String outDescStat = "capture.output(cat(\"\nDESCRIPTIVE STATISTICS:\n\n\"),file=\"" + outFileName + "\",append = TRUE)";
							String outDescStat2 = "capture.output(outDesc,file=\"" + outFileName + "\",append = TRUE)"; 

							String runSuccessDescStat = rEngine.eval("class(outDesc)").asString();	
							if (runSuccessDescStat != null && runSuccessDescStat.equals("try-error")) {	
								System.out.println("desc stat: error");
								String checkError = "msg <- trimStrings(strsplit(outDesc, \":\")[[1]])";
								String checkError2 = "msg <- trimStrings(paste(strsplit(msg, \"\\n\")[[length(msg)]], collapse = \" \"))";
								String checkError3 ="msg <- gsub(\"\\\"\", \"\", msg)";
								String checkError4 = "capture.output(cat(\"*** \nERROR in DescriptiveStatistics function:\\n  \",msg, \"\n***\n\n\", sep = \"\"), file=\"" + outFileName + "\",append = TRUE)";
								rEngine.eval(checkError);
								rEngine.eval(checkError2);
								rEngine.eval(checkError3);
								rEngine.eval(checkError4);
							} 
							else {
								rEngine.eval(outDescStat);
								rEngine.eval(outDescStat2);
								rEngine.eval(outSpace);
							}	
						}

						//optional output: Variance Components
						if (varianceComponents) {
							String outVarComp = "capture.output(cat(\"\nVARIANCE COMPONENTS TABLE:\n\n\"),file=\"" + outFileName + "\",append = TRUE)";
							String outVarComp2 = "capture.output(meaOne2$output[[" + i + "]]$varcomp.table,file=\"" + outFileName + "\",append = TRUE)";
							rEngine.eval(outVarComp);
							rEngine.eval(outVarComp2);
							rEngine.eval(outSpace);
						}
						
						//default output: Test Genotypic Effect
						String outTestGeno1 = "capture.output(cat(\"\nTESTING FOR THE SIGNIFICANCE OF GENOTYPIC EFFECT USING -2 LOGLIKELIHOOD RATIO TEST:\n\"),file=\"" + outFileName + "\",append = TRUE)";
						String outTestGeno2 = "capture.output(cat(\"\nFormula for Model1: \", meaOne2$output[[" + i + "]]$formula1,\"\n\"),file=\"" + outFileName + "\",append = TRUE)";
						String outTestGeno3 = "capture.output(cat(\"Formula for Model2: \", meaOne2$output[[" + i + "]]$formula2,\"\n\n\"),file=\"" + outFileName + "\",append = TRUE)";
						String outTestGeno4 = "capture.output(meaOne2$output[[" + i + "]]$testsig.Geno,file=\"" + outFileName + "\",append = TRUE)";
						rEngine.eval(outTestGeno1);
						rEngine.eval(outTestGeno2);
						rEngine.eval(outTestGeno3);
						rEngine.eval(outTestGeno4);
						rEngine.eval(outSpace);
						
						//default output: Test Environment Effect
						String outTestEnv1 = "capture.output(cat(\"\nTESTING FOR THE SIGNIFICANCE OF ENVIRONMENT EFFECT USING -2 LOGLIKELIHOOD RATIO TEST:\n\"),file=\"" + outFileName + "\",append = TRUE)";
						String outTestEnv2 = "capture.output(cat(\"\nFormula for Model1: \", meaOne2$output[[" + i + "]]$formula1,\"\n\"),file=\"" + outFileName + "\",append = TRUE)";
						String outTestEnv3 = "capture.output(cat(\"Formula for Model2: \", meaOne2$output[[" + i + "]]$formula3,\"\n\n\"),file=\"" + outFileName + "\",append = TRUE)";
						String outTestEnv4 = "capture.output(meaOne2$output[[" + i + "]]$testsig.Env,file=\"" + outFileName + "\",append = TRUE)";
						rEngine.eval(outTestEnv1);
						rEngine.eval(outTestEnv2);
						rEngine.eval(outTestEnv3);
						rEngine.eval(outTestEnv4);
						rEngine.eval(outSpace);
						
						//default output: Test GXE Effect
						String outTestGenoEnv1 = "capture.output(cat(\"\nTESTING FOR THE SIGNIFICANCE OF GENOTYPE X ENVIRONMENT EFFECT USING -2 LOGLIKELIHOOD RATIO TEST:\n\"),file=\"" + outFileName + "\",append = TRUE)";
						String outTestGenoEnv2 = "capture.output(cat(\"\nFormula for Model1: \", meaOne2$output[[" + i + "]]$formula1,\"\n\"),file=\"" + outFileName + "\",append = TRUE)";
						String outTestGenoEnv3 = "capture.output(cat(\"Formula for Model2: \", meaOne2$output[[" + i + "]]$formula4,\"\n\n\"),file=\"" + outFileName + "\",append = TRUE)";
						String outTestGenoEnv4 = "capture.output(meaOne2$output[[" + i + "]]$testsig.GenoEnv,file=\"" + outFileName + "\",append = TRUE)";
						rEngine.eval(outTestGenoEnv1);
						rEngine.eval(outTestGenoEnv2);
						rEngine.eval(outTestGenoEnv3);
						rEngine.eval(outTestGenoEnv4);
						rEngine.eval(outSpace);
						
						//default output: Genotype X Environment Means
						String outGenoEnv = "capture.output(cat(\"\nGENOTYPE X ENVIRONMENT MEANS:\n\n\"),file=\"" + outFileName + "\",append = TRUE)";
						String outGenoEnv2 = "capture.output(meaOne2$output[[" + i + "]]$wide.GenoEnv,file=\"" + outFileName + "\",append = TRUE)";
						rEngine.eval(outGenoEnv);
						rEngine.eval(outGenoEnv2);
						rEngine.eval(outSpace);

						//default output: Genotype Means
						String outDescStat = "capture.output(cat(\"\nPREDICTED GENOTYPE MEANS:\n\n\"),file=\"" + outFileName + "\",append = TRUE)";
						String outDescStat2 = "capture.output(meaOne2$output[[" + i + "]]$means.Geno,file=\"" + outFileName + "\",append = TRUE)";
						rEngine.eval(outDescStat);
						rEngine.eval(outDescStat2);
						rEngine.eval(outSpace);
												
						//default output: EstHerit
						String outEstHerit = "capture.output(cat(\"\nHERITABILITY:\n\"),file=\"" + outFileName + "\",append = TRUE)";
						String outEstHerit2 = "capture.output(meaOne2$output[[" + i + "]]$heritability,file=\""	+ outFileName + "\",append = TRUE)";
						rEngine.eval(outEstHerit);
						rEngine.eval(outEstHerit2);
						rEngine.eval(outSpace);
						
						
//						//if levels of Geno and Env are recoded, display new code for genotype and environment levels
//						String recodedLevels = rEngine.eval("meaOne2$output[[" + i + "]]$recodedLevels").asBool().toString();
//						
//						System.out.println("recodedLevels: " + recodedLevels);
//						
//						if (recodedLevels.equals("TRUE")) {
//							String outLegends = "capture.output(cat(\"\nCODES USED IN GRAPHS:\n\n\"),file=\"" + outFileName + "\",append = TRUE)";
//							String outLegends2 = "capture.output(meaOne2$output[[" + i + "]]$newCodingGeno,file=\"" + outFileName + "\",append = TRUE)";
//							String outLegends3 = "capture.output(meaOne2$output[[" + i + "]]$newCodingEnv,file=\"" + outFileName + "\",append = TRUE)";
//							rEngine.eval(outLegends);
//							rEngine.eval(outLegends2);
//							rEngine.eval(outSpace);
//							rEngine.eval(outLegends3);
//							rEngine.eval(outSpace);
//							rEngine.eval(outSpace);
//						} else {
//							rEngine.eval(outSpace);
//						}
//						
//						//create response plots
//						String responsePlot1 = "dataCoded <- meaOne2$output[[" + i + "]]$data";
//						String responsePlot2 = "nlevelsEnv <- meaOne2$output[[" + i + "]]$nlevelsEnv";
//						String responsePlot3 = "nlevelsGeno <- meaOne2$output[[" + i + "]]$nlevelsGeno";
//						String responsePlot4 = "resPlot1 <- try(GraphLine(data=dataCoded, outputPath=\"" + resultFolderPath + "\", yVars =c(\"" + respvar[k] + "\"), xVar =c(\"CodedGeno\"), lineVars =c(\"CodedEnv\"), mTitle =\"Response Plot of " + respvar[k] + "\", yAxisLab =c(\"" + respvar[k] + "\"), xAxisLab =\"" + genotype + "\", yMinValue = c(NA), yMaxValue = c(NA), axisLabelStyle = 2, byVar = NULL, plotCol = c(1:nlevelsEnv), showLineLabels =TRUE, showLeg = FALSE, boxed = TRUE, linePtTypes=rep(\"b\", nlevelsEnv), lineTypes=rep(1, nlevelsEnv), lineWidths=rep(1, nlevelsEnv), pointChars=rep(\" \", nlevelsEnv), pointCharSizes=rep(1, nlevelsEnv), multGraphs =FALSE), silent = TRUE)";
//						String responsePlot5 = "resPlot2 <- try(GraphLine(data=dataCoded, outputPath=\"" + resultFolderPath + "\", yVars =c(\"" + respvar[k] + "\"), xVar =c(\"CodedEnv\"), lineVars =c(\"CodedGeno\"), mTitle =\"Response Plot of " + respvar[k] + "\", yAxisLab =c(\"" + respvar[k] + "\"), xAxisLab =\"" + environment + "\", yMinValue = c(NA), yMaxValue = c(NA), axisLabelStyle = 2, byVar = NULL, plotCol = c(1:nlevelsGeno), showLineLabels =TRUE, showLeg = FALSE, boxed = TRUE, linePtTypes=rep(\"b\", nlevelsGeno), lineTypes=rep(1, nlevelsGeno), lineWidths=rep(1, nlevelsGeno), pointChars=rep(\" \", nlevelsGeno), pointCharSizes=rep(1, nlevelsGeno), multGraphs =FALSE), silent = TRUE)";
//						
//						System.out.println(responsePlot1);
//						System.out.println(responsePlot2);
//						System.out.println(responsePlot3);
//						System.out.println(responsePlot4);
//						System.out.println(responsePlot5);
//						
//						rEngine.eval(responsePlot1);
//						rEngine.eval(responsePlot2);
//						rEngine.eval(responsePlot3);
//						rEngine.eval(responsePlot4);
//						rEngine.eval(responsePlot5);
//						
//						String runSuccessPlot1 = rEngine.eval("class(resPlot1)").asString();
//						if (runSuccessPlot1 != null && runSuccessPlot1.equals("try-error")) {	
//							System.out.println("response plot geno: error");
//							String checkError = "msg <- trimStrings(strsplit(resPlot1, \":\")[[1]])";
//							String checkError2 = "msg <- trimStrings(paste(strsplit(msg, \"\\n\")[[length(msg)]], collapse = \" \"))";
//							String checkError3 ="msg <- gsub(\"\\\"\", \"\", msg)";
//							String checkError4 = "capture.output(cat(\"*** \nERROR in GraphLine function (geno):\\n  \",msg, \"\n***\n\n\", sep = \"\"), file=\"" + outFileName + "\",append = TRUE)";
//							rEngine.eval(checkError);
//							rEngine.eval(checkError2);
//							rEngine.eval(checkError3);
//							rEngine.eval(checkError4);
//						}
//						
//						String runSuccessPlot2 = rEngine.eval("class(resPlot2)").asString();
//						if (runSuccessPlot2 != null && runSuccessPlot2.equals("try-error")) {	
//							System.out.println("response plot env: error");
//							String checkError = "msg <- trimStrings(strsplit(resPlot2, \":\")[[1]])";
//							String checkError2 = "msg <- trimStrings(paste(strsplit(msg, \"\\n\")[[length(msg)]], collapse = \" \"))";
//							String checkError3 ="msg <- gsub(\"\\\"\", \"\", msg)";
//							String checkError4 = "capture.output(cat(\"*** \nERROR in GraphLine function (env):\\n  \",msg, \"\n***\n\n\", sep = \"\"), file=\"" + outFileName + "\",append = TRUE)";
//							rEngine.eval(checkError);
//							rEngine.eval(checkError2);
//							rEngine.eval(checkError3);
//							rEngine.eval(checkError4);
//						}
						
						rEngine.eval(outSpace);	
					}
				}
				
				//default output: save Genotype x Environment Means to a csv file
				String checkGenoEnvMean = rEngine.eval("meaOne2$meansGenoEnvWarning").asString();
				System.out.println("checkGenoEnvMean: " + checkGenoEnvMean);
				
				if (checkGenoEnvMean.equals("empty")) {
					System.out.println("Saving geno x env means not done.");
				} else {
					String funcSaveGEMeansCsv = "saveGEMeans <- try(write.table(meaOne2$means.GenoEnv.all,file =\"" + resultFolderPath + "GenoEnvMeans_random.csv\",sep=\",\",row.names=FALSE), silent=TRUE)";
					System.out.println(funcSaveGEMeansCsv);
				  	rEngine.eval(funcSaveGEMeansCsv);
					
					String runSuccessSaveGEMeans = rEngine.eval("class(saveGEMeans)").asString();
					if (runSuccessSaveGEMeans != null && runSuccessSaveGEMeans.equals("try-error")) {	
						System.out.println("save GxE means: error");
						String checkError = "msg <- trimStrings(strsplit(saveGEMeans, \":\")[[1]])";
						String checkError2 = "msg <- trimStrings(paste(strsplit(msg, \"\\n\")[[length(msg)]], collapse = \" \"))";
						String checkError3 ="msg <- gsub(\"\\\"\", \"\", msg)";
						String checkError4 = "capture.output(cat(\"*** \nERROR in saving genotype x environment means to a file:\\n  \",msg, \"\n***\n\n\", sep = \"\"), file=\"" + outFileName + "\",append = TRUE)";
						rEngine.eval(checkError);
						rEngine.eval(checkError2);
						rEngine.eval(checkError3);
						rEngine.eval(checkError4);
					}

				}

				//default output: save Genotype Means to a csv file
				String checkGenoMean = rEngine.eval("meaOne2$meansGenoWarning").asString();
				System.out.println("checkGenoMean: " + checkGenoMean);
				
				if (checkGenoMean.equals("empty")) {
					System.out.println("Saving geno means not done.");
				} else {
					String funcSaveGMeansCsv = "saveGMeans <- try(write.table(meaOne2$means.Geno.all,file =\"" + resultFolderPath + "GenoMeans_random.csv\",sep=\",\",row.names=FALSE), silent=TRUE)";
					System.out.println(funcSaveGMeansCsv);
				  	rEngine.eval(funcSaveGMeansCsv);
					
				  	String runSuccessSaveGMeans = rEngine.eval("class(saveGMeans)").asString();
					if (runSuccessSaveGMeans != null && runSuccessSaveGMeans.equals("try-error")) {	
						System.out.println("save G means: error");
						String checkError = "msg <- trimStrings(strsplit(saveGMeans, \":\")[[1]])";
						String checkError2 = "msg <- trimStrings(paste(strsplit(msg, \"\\n\")[[length(msg)]], collapse = \" \"))";
						String checkError3 ="msg <- gsub(\"\\\"\", \"\", msg)";
						String checkError4 = "capture.output(cat(\"*** \nERROR in saving genotype means to a file:\\n  \",msg, \"\n***\n\n\", sep = \"\"), file=\"" + outFileName + "\",append = TRUE)";
						rEngine.eval(checkError);
						rEngine.eval(checkError2);
						rEngine.eval(checkError3);
						rEngine.eval(checkError4);
					}
				}
				
				//optional output: diagnostic plots for genotype random
				if (diagnosticPlot) {
					String diagPlotsMea1SFunc = "diagPlotsMea1S <- tryCatch(graph.mea1s.diagplots(dataMeaOneStage, " + respvarVector + ", is.random = TRUE, meaOne2), error=function(err) \"notRun\")";
					System.out.println(diagPlotsMea1SFunc);
					rEngine.eval(diagPlotsMea1SFunc);
					
					String runSuccessDiag = rEngine.eval("class(diagPlotsMea1S)").asString();
					if (runSuccessDiag != null && runSuccessDiag.equals("try-error")) {	
						System.out.println("diagnostic plot: error");
						String checkError = "msg <- trimStrings(strsplit(diagPlotsMea1S, \":\")[[1]])";
						String checkError2 = "msg <- trimStrings(paste(strsplit(msg, \"\\n\")[[length(msg)]], collapse = \" \"))";
						String checkError3 ="msg <- gsub(\"\\\"\", \"\", msg)";
						String checkError4 = "capture.output(cat(\"*** \nERROR in creating diagnostic plot (fixed genotype):\\n  \",msg, \"\n***\n\n\", sep = \"\"), file=\"" + outFileName + "\",append = TRUE)";
						rEngine.eval(checkError);
						rEngine.eval(checkError2);
						rEngine.eval(checkError3);
						rEngine.eval(checkError4);
					}

				}
					
				} //end of else for if runSuccess
			} // end of if random
			
			//default output: save residuals to csv files
			if (runningFixedSuccess & runningRandomSuccess) {
				String residFileNameFixed = "residFileNameFixed <- paste(\"" + resultFolderPath + "\",\"residuals_fixed.csv\", sep=\"\")";
				String residFileNameRandom = "residFileNameRandom <- paste(\"" + resultFolderPath + "\",\"residuals_random.csv\", sep=\"\")";
				if ((genotypeFixed) & (genotypeRandom == false)) {
					String runSsaResid1 = "resid_f <- try(GEOneStage_resid(meaOne1, " + respvarVector + ", is.genoRandom = FALSE), silent=TRUE)";
					System.out.println(runSsaResid1);
					rEngine.eval(runSsaResid1);
					
					String runSuccessDiagPlots = rEngine.eval("class(resid_f)").asString();
					if (runSuccessDiagPlots != null && runSuccessDiagPlots.equals("try-error")) {	
						System.out.println("GEOneStage_resid (genotype fixed): error");
						String checkError = "msg <- trimStrings(strsplit(resid_f, \":\")[[1]])";
						String checkError2 = "msg <- trimStrings(paste(strsplit(msg, \"\\n\")[[length(msg)]], collapse = \" \"))";
						String checkError3 ="msg <- gsub(\"\\\"\", \"\", msg)";
						String checkError4 = "capture.output(cat(\"*** \nERROR in GEOneStage_resid function:\\n  \",msg, \"\n***\n\n\", sep = \"\"), file=\"" + outFileName + "\",append = TRUE)";
						rEngine.eval(checkError);
						rEngine.eval(checkError2);
						rEngine.eval(checkError3);
						rEngine.eval(checkError4);
					} else {
						String checkResid1 = rEngine.eval("resid_f$ge1residWarning").asString();
						System.out.println("checkResid1: " + checkResid1);
						if (checkResid1.equals("empty")) {
							System.out.println("Saving resid (fixed) not done.");
						} else {
							String func1SaveResidualsCsv = "saveResid <- try(write.table(resid_f$residuals, file = residFileNameFixed ,sep=\",\",row.names=FALSE), silent=TRUE)";
							rEngine.eval(residFileNameFixed);
							rEngine.eval(func1SaveResidualsCsv);
							
							String runSuccessSaveResid = rEngine.eval("class(saveResid)").asString();
							if (runSuccessSaveResid != null && runSuccessSaveResid.equals("try-error")) {	
								System.out.println("save residuals: error");
								String checkError = "msg <- trimStrings(strsplit(saveResid, \":\")[[1]])";
								String checkError2 = "msg <- trimStrings(paste(strsplit(msg, \"\\n\")[[length(msg)]], collapse = \" \"))";
								String checkError3 ="msg <- gsub(\"\\\"\", \"\", msg)";
								String checkError4 = "capture.output(cat(\"*** \nERROR in saving residuals to a file:\\n  \",msg, \"\n***\n\n\", sep = \"\"), file=\"" + outFileName + "\",append = TRUE)";
								rEngine.eval(checkError);
								rEngine.eval(checkError2);
								rEngine.eval(checkError3);
								rEngine.eval(checkError4);
							}
						}
					}
				}
				else if ((genotypeFixed == false) & (genotypeRandom)) {
					String runSsaResid2 = "resid_r <- try(GEOneStage_resid(meaOne2, " + respvarVector + ", is.genoRandom = TRUE), silent=TRUE)";
					System.out.println(runSsaResid2);
					rEngine.eval(runSsaResid2);
					
					String runSuccessDiagPlots = rEngine.eval("class(resid_r)").asString();
					if (runSuccessDiagPlots != null && runSuccessDiagPlots.equals("try-error")) {	
						System.out.println("GEOneStage_resid (genotype random): error");
						String checkError = "msg <- trimStrings(strsplit(resid_r, \":\")[[1]])";
						String checkError2 = "msg <- trimStrings(paste(strsplit(msg, \"\\n\")[[length(msg)]], collapse = \" \"))";
						String checkError3 ="msg <- gsub(\"\\\"\", \"\", msg)";
						String checkError4 = "capture.output(cat(\"*** \nERROR in GEOneStage_resid function:\\n  \",msg, \"\n***\n\n\", sep = \"\"), file=\"" + outFileName + "\",append = TRUE)";
						rEngine.eval(checkError);
						rEngine.eval(checkError2);
						rEngine.eval(checkError3);
						rEngine.eval(checkError4);
					} else {
						String checkResid1 = rEngine.eval("resid_r$ge1residWarning").asString();
						System.out.println("checkResid2: " + checkResid1);
						if (checkResid1.equals("empty")) {
							System.out.println("Saving resid (random) not done.");
						} else {
							String func1SaveResidualsCsv = "saveResid <- try(write.table(resid_r$residuals, file = residFileNameRandom ,sep=\",\",row.names=FALSE), silent=TRUE)";
							rEngine.eval(residFileNameRandom);
							rEngine.eval(func1SaveResidualsCsv);
							
							String runSuccessSaveResid = rEngine.eval("class(saveResid)").asString();
							if (runSuccessSaveResid != null && runSuccessSaveResid.equals("try-error")) {	
								System.out.println("save residuals: error");
								String checkError = "msg <- trimStrings(strsplit(saveResid, \":\")[[1]])";
								String checkError2 = "msg <- trimStrings(paste(strsplit(msg, \"\\n\")[[length(msg)]], collapse = \" \"))";
								String checkError3 ="msg <- gsub(\"\\\"\", \"\", msg)";
								String checkError4 = "capture.output(cat(\"*** \nERROR in saving residuals to a file:\\n  \",msg, \"\n***\n\n\", sep = \"\"), file=\"" + outFileName + "\",append = TRUE)";
								rEngine.eval(checkError);
								rEngine.eval(checkError2);
								rEngine.eval(checkError3);
								rEngine.eval(checkError4);
							}
						}
					}
				}
				else if ((genotypeFixed) & (genotypeRandom)) {
					String runSsaResid1 = "resid_f <- try(GEOneStage_resid(meaOne1, " + respvarVector + ", is.genoRandom = FALSE), silent=TRUE)";
					String runSsaResid2 = "resid_r <- try(GEOneStage_resid(meaOne2, " + respvarVector + ", is.genoRandom = TRUE), silent=TRUE)";
					System.out.println(runSsaResid1);
					System.out.println(runSsaResid2);
					rEngine.eval(runSsaResid1);
					rEngine.eval(runSsaResid2);
					
					String runSuccessResidFixed = rEngine.eval("class(resid_f)").asString();
					if (runSuccessResidFixed != null && runSuccessResidFixed.equals("try-error")) {	
						System.out.println("GEOneStage_resid (genotype fixed): error");
						String checkError = "msg <- trimStrings(strsplit(resid_f, \":\")[[1]])";
						String checkError2 = "msg <- trimStrings(paste(strsplit(msg, \"\\n\")[[length(msg)]], collapse = \" \"))";
						String checkError3 ="msg <- gsub(\"\\\"\", \"\", msg)";
						String checkError4 = "capture.output(cat(\"*** \nERROR in GEOneStage_resid function:\\n  \",msg, \"\n***\n\n\", sep = \"\"), file=\"" + outFileName + "\",append = TRUE)";
						rEngine.eval(checkError);
						rEngine.eval(checkError2);
						rEngine.eval(checkError3);
						rEngine.eval(checkError4);
					} else {
						String checkResid1 = rEngine.eval("resid_f$ge1residWarning").asString();
						System.out.println("checkResid1: " + checkResid1);
						if (checkResid1.equals("empty")) {
							System.out.println("Saving resid (fixed) not done.");
						} else {
							String func1SaveResidualsCsv = "saveResid <- try(write.table(resid_f$residuals, file = residFileNameFixed ,sep=\",\",row.names=FALSE), silent=TRUE)";
							rEngine.eval(residFileNameFixed);
							rEngine.eval(func1SaveResidualsCsv);
							
							String runSuccessSaveResid = rEngine.eval("class(saveResid)").asString();
							if (runSuccessSaveResid != null && runSuccessSaveResid.equals("try-error")) {	
								System.out.println("save residuals: error");
								String checkError = "msg <- trimStrings(strsplit(saveResid, \":\")[[1]])";
								String checkError2 = "msg <- trimStrings(paste(strsplit(msg, \"\\n\")[[length(msg)]], collapse = \" \"))";
								String checkError3 ="msg <- gsub(\"\\\"\", \"\", msg)";
								String checkError4 = "capture.output(cat(\"*** \nERROR in saving residuals to a file:\\n  \",msg, \"\n***\n\n\", sep = \"\"), file=\"" + outFileName + "\",append = TRUE)";
								rEngine.eval(checkError);
								rEngine.eval(checkError2);
								rEngine.eval(checkError3);
								rEngine.eval(checkError4);
							}
						}
					}
					
					String runSuccessResidRandom = rEngine.eval("class(resid_r)").asString();
					if (runSuccessResidRandom != null && runSuccessResidRandom.equals("try-error")) {	
						System.out.println("GEOneStage_resid (genotype random): error");
						String checkError = "msg <- trimStrings(strsplit(resid_r, \":\")[[1]])";
						String checkError2 = "msg <- trimStrings(paste(strsplit(msg, \"\\n\")[[length(msg)]], collapse = \" \"))";
						String checkError3 ="msg <- gsub(\"\\\"\", \"\", msg)";
						String checkError4 = "capture.output(cat(\"*** \nERROR in GEOneStage_resid function:\\n  \",msg, \"\n***\n\n\", sep = \"\"), file=\"" + outFileName + "\",append = TRUE)";
						rEngine.eval(checkError);
						rEngine.eval(checkError2);
						rEngine.eval(checkError3);
						rEngine.eval(checkError4);
					} else {
						String checkResid1 = rEngine.eval("resid_r$ge1residWarning").asString();
						System.out.println("checkResid2: " + checkResid1);
						if (checkResid1.equals("empty")) {
							System.out.println("Saving resid (random) not done.");
						} else {
							String func1SaveResidualsCsv = "saveResid2 <- try(write.table(resid_r$residuals, file = residFileNameRandom ,sep=\",\",row.names=FALSE), silent=TRUE)";
							rEngine.eval(residFileNameRandom);
							rEngine.eval(func1SaveResidualsCsv);
							
							String runSuccessSaveResid = rEngine.eval("class(saveResid2)").asString();
							if (runSuccessSaveResid != null && runSuccessSaveResid.equals("try-error")) {	
								System.out.println("save residuals: error");
								String checkError = "msg <- trimStrings(strsplit(saveResid2, \":\")[[1]])";
								String checkError2 = "msg <- trimStrings(paste(strsplit(msg, \"\\n\")[[length(msg)]], collapse = \" \"))";
								String checkError3 ="msg <- gsub(\"\\\"\", \"\", msg)";
								String checkError4 = "capture.output(cat(\"*** \nERROR in saving residuals to a file:\\n  \",msg, \"\n***\n\n\", sep = \"\"), file=\"" + outFileName + "\",append = TRUE)";
								rEngine.eval(checkError);
								rEngine.eval(checkError2);
								rEngine.eval(checkError3);
								rEngine.eval(checkError4);
							}
						}
					}
				}
			}

			//boxplot and histogram
			String withBox = "FALSE";
			if (boxplotRawData) withBox = "TRUE";
			String withHist = "FALSE";
			if (histogramRawData) withHist = "TRUE";
			String meaOut = "meaOne1";
			if (genotypeFixed) meaOut = "meaOne1";
			else if (genotypeRandom) meaOut = "meaOne2";

			String boxHistMeaFunc = "boxHistMea <- tryCatch(graph.mea1s.boxhist(dataMeaOneStage, " + respvarVector + ", " + meaOut + ", box = \"" + withBox + "\", hist = \"" + withHist + "\"), error=function(err) \"notRun\")";
			System.out.println(boxHistMeaFunc);
			rEngine.eval(boxHistMeaFunc);
				
			String runSuccessBoxHistMea = rEngine.eval("boxHistMea").asString();
			//generate warning if error occurred	
			if (runSuccessBoxHistMea != null && runSuccessBoxHistMea.equals("notRun")) {	
				System.out.println("error");
				rEngine.eval("capture.output(cat(\"\n***An error has occurred.***\n***Boxplot(s) and histogram(s) not created.***\n\"),file=\"" + outFileName + "\",append = TRUE)"); //append to output file?
			}
			
			rEngine.eval(outSpace); 
			rEngine.eval(sep2);
			
			rEngineEnd();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void doMultiEnvironmentSecondStage(String dataFileName, String outFileName, String resultFolderPath, String weightOption, String[] respvar, String[] standardErrors, String[] residualVariances, String[] numberOfReps, 
			String environment, String[] environmentLevels, String genotype, boolean descriptiveStat, boolean varianceComponents, boolean boxplotRawData, 
			boolean histogramRawData, boolean diagnosticPlot, boolean genotypeFixed, boolean performPairwise, String pairwiseAlpha, String[] genotypeLevels, 
			String[] controlLevels, boolean compareControl, boolean performAllPairwise, boolean genotypeRandom) {
		
		String respvarVector= inputTransform.createRVector(respvar);
		String standardErrorsVector= inputTransform.createRVector(standardErrors);
		String residualVariancesVector= inputTransform.createRVector(residualVariances);
		String numberOfRepsVector = inputTransform.createRVector(numberOfReps);
		
		boolean runningFixedSuccess =true;
		boolean runningRandomSuccess =true;
		boolean printAllOutputFixed=true;
		boolean printAllOutputRandom=true;
		
		
		try {
			String readData = "dataMeaTwoStage <- read.csv(\"" + dataFileName + "\", header = TRUE, na.strings = c(\"NA\",\".\",\" \",\"\"), blank.lines.skip=TRUE, sep = \",\")";
			System.out.println(readData);
			rEngine.eval(readData);
			
			String runSuccessData = rEngine.eval("dataMeaTwoStage").asString();
			
			if (runSuccessData != null && runSuccessData.equals("notRun")) {	
				System.out.println("error");
				rEngine.eval("capture.output(cat(\"\n***Error reading data.***\n\"),file=\"" + outFileName + "\",append = FALSE)"); //append to output file?
			}
			else {
				String setWd = "setwd(\"" + resultFolderPath + "\")";
				System.out.println(setWd);
				rEngine.eval(setWd);
			}
			
			String usedData = "capture.output(cat(\"\nDATA FILE: " + dataFileName + "\n\",file=\"" + outFileName + "\"))";
			String outFile = "capture.output(cat(\"\nMULTI-ENVIRONMENT ANALYSIS (TWO-STAGE)\n\"),file=\"" + outFileName + "\", append = TRUE)";
			String sep = "capture.output(cat(\"------------------------------\n\"),file=\"" + outFileName + "\",append = TRUE)";
			String sep2 = "capture.output(cat(\"==============================\n\"),file=\"" + outFileName + "\",append = TRUE)";
			String outSpace = "capture.output(cat(\"\n\"),file=\"" + outFileName + "\",append = TRUE)";
			rEngine.eval(usedData);
			rEngine.eval(outFile);
			
			if (weightOption == "none") {
				String outWeights = "capture.output(cat(\"\nWEIGHT OPTION: NONE\n\"),file=\"" + outFileName + "\", append = TRUE)";
				rEngine.eval(outWeights);
				rEngine.eval(outSpace);
			} else {
				String outWeights = "capture.output(cat(\"\nWEIGHT OPTION: 1/(sem^2)\n\"),file=\"" + outFileName + "\", append = TRUE)";
				rEngine.eval(outWeights);
				rEngine.eval(outSpace);
			}
		
			// OUTPUT
			// Genotype Fixed
			if (genotypeFixed) {
				String funcMeaFixed = null;
				if (weightOption=="none") 
					funcMeaFixed = "meaTwo1 <- try(GETwoStage.test(dataMeaTwoStage, " + respvarVector + ",stderr = NULL," + residualVariancesVector + "," + numberOfRepsVector + ",\"" + genotype + "\",\"" + environment + "\", weight = \"" + weightOption + "\", is.genoRandom = FALSE), silent=TRUE)";
				else funcMeaFixed = "meaTwo1 <- try(GETwoStage.test(dataMeaTwoStage, " + respvarVector + "," + standardErrorsVector + "," + residualVariancesVector + "," + numberOfRepsVector + ",\"" + genotype + "\",\"" + environment + "\", weight = \"" + weightOption + "\", is.genoRandom = FALSE), silent=TRUE)";

				String fixedHead = "capture.output(cat(\"GENOTYPE AS: Fixed\n\"),file=\""+ outFileName + "\",append = TRUE)";
				
				System.out.println(funcMeaFixed);
				rEngine.eval(funcMeaFixed);
				rEngine.eval(sep2);
				rEngine.eval(fixedHead);
				rEngine.eval(sep2);
				rEngine.eval(outSpace);
				
				String runSuccess = rEngine.eval("class(meaTwo1)").asString();
				if (runSuccess != null && runSuccess.equals("try-error")) {	
					System.out.println("MEATwoStage.test: error");
					String checkError = "msg <- trimStrings(strsplit(meaTwo1, \":\")[[1]])";
					String checkError2 = "msg <- trimStrings(paste(strsplit(msg, \"\\n\")[[length(msg)]], collapse = \" \"))";
					String checkError3 ="msg <- gsub(\"\\\"\", \"\", msg)";
					String checkError4 = "capture.output(cat(\"*** \nERROR in GETwoStage.test function:\\n  \",msg, \"\n***\n\n\", sep = \"\"), file=\"" + outFileName + "\",append = TRUE)";
					rEngine.eval(checkError);
					rEngine.eval(checkError2);
					rEngine.eval(checkError3);
					rEngine.eval(checkError4);
					
					runningFixedSuccess =false;
				}
				else {
					for (int k = 0; k < respvar.length; k++) {
						printAllOutputFixed=true;
						int i = k + 1; // 1-relative index;
						String respVarHead = "capture.output(cat(\"RESPONSE VARIABLE: " + respvar[k] + "\n\"),file=\"" + outFileName + "\",append = TRUE)";
						rEngine.eval(sep);
						rEngine.eval(respVarHead);
						rEngine.eval(sep);
						
						//check if the data has too many missing observations
						double responseRate = rEngine.eval("meaTwo1[[" + i + "]]$responseRate").asDouble();
						if (responseRate < 0.80) {
							String allNAWarning = rEngine.eval("meaTwo1[[" + i + "]]$manyNAWarning").asString();
							String printError1 = "capture.output(cat(\"***\\n\"), file=\"" + outFileName + "\",append = TRUE)";
							String printError2 = "capture.output(cat(\"ERROR:\\n\"), file=\"" + outFileName + "\",append = TRUE)";
							String printError3 = "capture.output(cat(\"" + allNAWarning + "\\n\"), file=\"" + outFileName + "\",append = TRUE)";
							
							rEngine.eval(outSpace);
							rEngine.eval(printError1);
							rEngine.eval(printError2);
							rEngine.eval(printError3);
							rEngine.eval(printError1);
							rEngine.eval(outSpace);
							rEngine.eval(outSpace);
							printAllOutputFixed=false;
						}
						
						if (printAllOutputFixed) {
							// default output: Trial Summary
							String funcTrialSum = "funcTrialSum <- try(class.information(c(\"" + genotype + "\",\"" + environment + "\"),meaTwo1[[" + i + "]]$data), silent=TRUE)";
							String trialSumHead = "capture.output(cat(\"\nDATA SUMMARY:\n\n\"),file=\"" + outFileName + "\",append = TRUE)";
							String trialObsRead = "capture.output(cat(\"Number of observations read: \", meaTwo1[["	+ i	+ "]]$obsread[[1]],\"\n\"),file=\"" + outFileName + "\",append = TRUE)";
							String trialObsUsed = "capture.output(cat(\"Number of observations used: \", meaTwo1 [["	+ i	+ "]]$obsused[[1]],\"\n\n\"),file=\""	+ outFileName + "\",append = TRUE)";
							String trialSum = "capture.output(funcTrialSum,file=\"" + outFileName + "\",append = TRUE)";

							rEngine.eval(funcTrialSum);
							String runSuccessTS = rEngine.eval("class(funcTrialSum)").asString();
							if (runSuccessTS != null && runSuccessTS.equals("try-error")) {	
								System.out.println("class info: error");
								String checkError = "msg <- trimStrings(strsplit(funcTrialSum, \":\")[[1]])";
								String checkError2 = "msg <- trimStrings(paste(strsplit(msg, \"\\n\")[[length(msg)]], collapse = \" \"))";
								String checkError3 ="msg <- gsub(\"\\\"\", \"\", msg)";
								String checkError4 = "capture.output(cat(\"*** \nERROR in class.information function:\\n  \",msg, \"\n***\n\n\", sep = \"\"), file=\"" + outFileName + "\",append = TRUE)";
								rEngine.eval(checkError);
								rEngine.eval(checkError2);
								rEngine.eval(checkError3);
								rEngine.eval(checkError4);
							}
							else {
								rEngine.eval(trialSumHead);
								rEngine.eval(trialObsRead);
								rEngine.eval(trialObsUsed);
								rEngine.eval(trialSum);
								rEngine.eval(outSpace);
							}	
		
							//optional output: descriptive statistics
							String funcDesc = "outDesc <- try(DescriptiveStatistics(dataMeaTwoStage, \"" + respvar[k] + "\", grp = NULL), silent=TRUE)";
							rEngine.eval(funcDesc);
		
							if (descriptiveStat) {
								String outDescStat = "capture.output(cat(\"\nDESCRIPTIVE STATISTICS:\n\n\"),file=\"" + outFileName + "\",append = TRUE)";
								String outDescStat2 = "capture.output(outDesc,file=\"" + outFileName + "\",append = TRUE)"; 
		
								String runSuccessDescStat = rEngine.eval("class(outDesc)").asString();	
								if (runSuccessDescStat != null && runSuccessDescStat.equals("try-error")) {	
									System.out.println("desc stat: error");
									String checkError = "msg <- trimStrings(strsplit(outDesc, \":\")[[1]])";
									String checkError2 = "msg <- trimStrings(paste(strsplit(msg, \"\\n\")[[length(msg)]], collapse = \" \"))";
									String checkError3 ="msg <- gsub(\"\\\"\", \"\", msg)";
									String checkError4 = "capture.output(cat(\"*** \nERROR in DescriptiveStatistics function:\\n  \",msg, \"\n***\n\n\", sep = \"\"), file=\"" + outFileName + "\",append = TRUE)";
									rEngine.eval(checkError);
									rEngine.eval(checkError2);
									rEngine.eval(checkError3);
									rEngine.eval(checkError4);
								} 
								else {
									rEngine.eval(outDescStat);
									rEngine.eval(outDescStat2);
									rEngine.eval(outSpace);
								}
							}
		
							//optional output: Variance Components
							if (varianceComponents) {
								String outVarComp = "capture.output(cat(\"\nVARIANCE COMPONENTS TABLE:\n\n\"),file=\"" + outFileName + "\",append = TRUE)";
								String outVarComp2 = "capture.output(meaTwo1[[" + i + "]]$varcomp.table,file=\"" + outFileName + "\",append = TRUE)";
		
								rEngine.eval(outVarComp);
								rEngine.eval(outVarComp2);
								rEngine.eval(outSpace);
							}
							
							//default output: TestGenEffect
							String outAnovaTable1 = "capture.output(cat(\"\nTESTING FOR THE SIGNIFICANCE OF GENOTYPIC EFFECT:\n\n\"),file=\"" + outFileName + "\",append = TRUE)";
							String outAnovaTable2 = "library(lmerTest)";
							String outAnovaTable3 = "model1b <- lmer(formula(meaTwo1[[" + i + "]]$formula1), data = meaTwo1[[" + i + "]]$data, REML = T)";
							String outAnovaTable4 = "a.table <- anova(model1b)";
							String outAnovaTable5 = "pvalue <- formatC(as.numeric(format(a.table[1,6], scientific=FALSE)), format=\"f\")";
							String outAnovaTable6 = "a.table<-cbind(round(a.table[,1:5], digits=4),pvalue)";
							String outAnovaTable7 = "colnames(a.table)<-c(\"Df\", \"Sum Sq\", \"Mean Sq\", \"F value\", \"Denom\", \"Pr(>F)\")";
							String outAnovaTable8 = "capture.output(cat(\"Analysis of Variance Table with Satterthwaite Denominator Df\n\"),file=\"" + outFileName + "\",append = TRUE)";
							String outAnovaTable9 = "capture.output(a.table,file=\"" + outFileName + "\",append = TRUE)";
							String outAnovaTable10 = "detach(\"package:lmerTest\")";
							
//							rEngine.eval(outspace);
							rEngine.eval(outAnovaTable1);
							rEngine.eval(outAnovaTable2);
							rEngine.eval(outAnovaTable3);
							rEngine.eval(outAnovaTable4);
							rEngine.eval(outAnovaTable5);
							rEngine.eval(outAnovaTable6);
							rEngine.eval(outAnovaTable7);
							rEngine.eval(outAnovaTable8);
							rEngine.eval(outAnovaTable9);
							rEngine.eval(outSpace);
							rEngine.eval(outAnovaTable10);
							
							//default output: Genotype Means
							String outDescStat = "capture.output(cat(\"\nGENOTYPE LSMEANS AND STANDARD ERRORS:\n\n\"),file=\"" + outFileName + "\",append = TRUE)";
							String outDescStat2 = "capture.output(meaTwo1[[" + i + "]]$means.Geno,file=\"" + outFileName + "\",append = TRUE)";
							rEngine.eval(outDescStat);
							rEngine.eval(outDescStat2);
							rEngine.eval(outSpace);
							
							//default output: statistics on SED
							String outSedStat1 = "capture.output(cat(\"\nSTANDARD ERROR OF THE DIFFERENCE (SED):\n\n\"),file=\"" + outFileName + "\",append = TRUE)";
							String outSedStat2 = "capture.output(meaTwo1[[" + i + "]]$sedTable,file=\"" + outFileName + "\",append = TRUE)";
							rEngine.eval(outSedStat1);
							rEngine.eval(outSedStat2);
							rEngine.eval(outSpace);
							
							//optional output: Perform Pairwise
							if (performPairwise) {
								double pairwiseSig = Double.valueOf(pairwiseAlpha);
								
								rEngine.rniAssign("trmt.levels",	rEngine.rniPutStringArray(genotypeLevels),	0); // a string array from OptionsPage
								if (compareControl == true) {
									rEngine.rniAssign("controlLevels",rEngine.rniPutStringArray(controlLevels),0); // a string array from OptionsPage
									String funcPairwise = "pwControl <- try(ssa.pairwise(meaTwo1[[" + i + "]]$model, type = \"Dunnett\", alpha = "	+ pairwiseSig + ", control.level = controlLevels), silent=TRUE)";
									String outCompareControl = "capture.output(cat(\"\nSIGNIFICANT PAIRWISE COMPARISONS (IF ANY): \nCompared with control(s)\n\n\"),file=\"" + outFileName	+ "\",append = TRUE)";
									String outCompareControl2n = "capture.output(pwControl$result,file=\""	+ outFileName	+ "\",append = TRUE)";
									System.out.println(funcPairwise);
									rEngine.eval(funcPairwise);
									rEngine.eval(outCompareControl);
									
									String runSuccessPwC = rEngine.eval("class(pwControl)").asString();	
									if (runSuccessPwC != null && runSuccessPwC.equals("try-error")) {	
										System.out.println("compare with control: error");
										String checkError = "msg <- trimStrings(strsplit(pwControl, \":\")[[1]])";
										String checkError2 = "msg <- trimStrings(paste(strsplit(msg, \"\\n\")[[length(msg)]], collapse = \" \"))";
										String checkError3 ="msg <- gsub(\"\\\"\", \"\", msg)";
										String checkError4 = "capture.output(cat(\"*** \nERROR in ssa.pairwise function:\\n  \",msg, \"\n***\n\n\", sep = \"\"), file=\"" + outFileName + "\",append = TRUE)";
										rEngine.eval(checkError);
										rEngine.eval(checkError2);
										rEngine.eval(checkError3);
										rEngine.eval(checkError4);
										rEngine.eval(outSpace);
										rEngine.eval(outSpace);
									} else {
										rEngine.eval(outCompareControl2n);
										
										// display warning generated by checkTest in ssa.pairwise
										String warningControlTest = rEngine.eval("pwControl$controlTestWarning").asString();
										
										if (!warningControlTest.equals("NONE")) {
											String warningCheckTest2 = "capture.output(cat(\"----- \nNOTE:\\n\"), file=\"" + outFileName + "\",append = TRUE)";
											String warningCheckTest3 = "capture.output(cat(\"" + warningControlTest + "\\n\"), file=\"" + outFileName + "\",append = TRUE)";
																					
											rEngine.eval(warningCheckTest2);
											rEngine.eval(warningCheckTest3);
										}
										rEngine.eval(outSpace);
										rEngine.eval(outSpace);
										System.out.println("pairwise control test:" + warningControlTest);
									}
								} else if (performAllPairwise) {
									String outPerformAllPairwise = "capture.output(cat(\"\nSIGNIFICANT PAIRWISE COMPARISONS (IF ANY): \n\n\"),file=\""	+ outFileName	+ "\",append = TRUE)";
									rEngine.eval(outPerformAllPairwise);
									if (genotypeLevels.length > 0 & genotypeLevels.length < 16) {
										String funcPairwise = "pwAll <- try(ssa.pairwise(meaTwo1[[" + i + "]]$model, type = \"Tukey\", alpha = "+ pairwiseSig + ", control.level = NULL), silent=TRUE)";
										String outPerformAllPairwise2n = "capture.output(pwAll$result,file=\"" + outFileName + "\",append = TRUE)";
										System.out.println(funcPairwise);
										rEngine.eval(funcPairwise);
		
										String runSuccessPwAll = rEngine.eval("class(pwAll)").asString();
										if (runSuccessPwAll != null && runSuccessPwAll.equals("try-error")) {	
											System.out.println("all pairwise: error");
											String checkError = "msg <- trimStrings(strsplit(pwAll, \":\")[[1]])";
											String checkError2 = "msg <- trimStrings(paste(strsplit(msg, \"\\n\")[[length(msg)]], collapse = \" \"))";
											String checkError3 ="msg <- gsub(\"\\\"\", \"\", msg)";
											String checkError4 = "capture.output(cat(\"*** \nERROR in ssa.pairwise function:\\n  \",msg, \"\n***\n\n\", sep = \"\"), file=\"" + outFileName + "\",append = TRUE)";
											rEngine.eval(checkError);
											rEngine.eval(checkError2);
											rEngine.eval(checkError3);
											rEngine.eval(checkError4);
										} else {
											rEngine.eval(outPerformAllPairwise2n);
											rEngine.eval(outSpace);
											rEngine.eval(outSpace);
										}	
									} else {
										String nLevelsLarge = "capture.output(cat(\"***\nExceeded maximum number of genotypes that can be compared. \n***\n\n\"),file=\"" + outFileName + "\",append = TRUE)";
										rEngine.eval(nLevelsLarge);
									}
								}
							} //end of performPairwise
		
							//optional output if the number of environment levels is at least 5: Stability Analysis using Regression
//							if (stabilityFinlay) {
//								if (environmentLevels.length > 4) {
//									String funcStability1 = "funcStability1 <- try(stability.analysis(dataMeaTwoStage, \"" + respvar[k] + "\", \"" + genotype + "\", \"" + environment + "\", method = \"regression\"), silent=TRUE)";
//									String outTestStability1 = "capture.output(cat(\"\nSTABILITY ANALYSIS USING FINLAY-WILKINSON MODEL:\n\n\"),file=\"" + outFileName + "\",append = TRUE)";
//									String outTestStability1b = "capture.output(funcStability1[[1]][[1]]$stability,file=\"" + outFileName + "\",append = TRUE)";
//									
//									System.out.println(funcStability1);
//									rEngine.eval(outTestStability1);
//									rEngine.eval(funcStability1);
//									
//									String runSuccessStab = rEngine.eval("class(funcStability1)").asString();
//									if (runSuccessStab != null && runSuccessStab.equals("try-error")) {	
//										System.out.println("stability reg: error");
//										String checkError = "msg <- trimStrings(strsplit(funcStability1, \":\")[[1]])";
//										String checkError2 = "msg <- trimStrings(paste(strsplit(msg, \"\\n\")[[length(msg)]], collapse = \" \"))";
//										String checkError3 ="msg <- gsub(\"\\\"\", \"\", msg)";
//										String checkError4 = "capture.output(cat(\"*** \nERROR in stability.analysis function:\\n  \",msg, \"\n***\n\n\", sep = \"\"), file=\"" + outFileName + "\",append = TRUE)";
//										rEngine.eval(checkError);
//										rEngine.eval(checkError2);
//										rEngine.eval(checkError3);
//										rEngine.eval(checkError4);
//									}
//									else {
//										rEngine.eval(outTestStability1b);
//										rEngine.eval(outSpace);
//									}
//								}	else {
//									String outRemark = "capture.output(cat(\"\nSTABILITY ANALYSIS USING FINLAY-WILKINSON MODEL:\n\"),file=\"" + outFileName + "\",append = TRUE)";
//									String outRemark2 = "capture.output(cat(\"***This is not done. The environment factor should have at least five levels.***\n\n\"),file=\"" + outFileName + "\",append = TRUE)";
//									
//									rEngine.eval(outRemark);
//									rEngine.eval(outRemark2);
//								}
//							}
		
							//default output: Stability Analysis using Shukla
//							if (stabilityShukla) {
//								if (environmentLevels.length > 4) {
//									String funcStability2 = "funcStability2 <- try(stability.analysis(dataMeaTwoStage, \"" + respvar[k] + "\", \"" + genotype + "\", \"" + environment + "\", method = \"shukla\"), silent=TRUE)";
//									String outTestStability2 = "capture.output(cat(\"\nSTABILITY ANALYSIS USING SHUKLA'S MODEL:\n\n\"),file=\"" + outFileName + "\",append = TRUE)";
//									String outTestStability2b = "capture.output(funcStability2[[1]][[1]]$stability,file=\"" + outFileName + "\",append = TRUE)";
//									
//									System.out.println(funcStability2);
//									rEngine.eval(outTestStability2);
//									rEngine.eval(funcStability2);
//									
//									String runSuccessStab = rEngine.eval("class(funcStability2)").asString();
//									if (runSuccessStab != null && runSuccessStab.equals("try-error")) {	
//										System.out.println("stability shukla: error");
//										String checkError = "msg <- trimStrings(strsplit(funcStability2, \":\")[[1]])";
//										String checkError2 = "msg <- trimStrings(paste(strsplit(msg, \"\\n\")[[length(msg)]], collapse = \" \"))";
//										String checkError3 ="msg <- gsub(\"\\\"\", \"\", msg)";
//										String checkError4 = "capture.output(cat(\"*** \nERROR in stability.analysis function:\\n  \",msg, \"\n***\n\n\", sep = \"\"), file=\"" + outFileName + "\",append = TRUE)";
//										rEngine.eval(checkError);
//										rEngine.eval(checkError2);
//										rEngine.eval(checkError3);
//										rEngine.eval(checkError4);
//									}
//									else {
//										rEngine.eval(outTestStability2b);
//										rEngine.eval(outSpace);
//									}
//								}   else {
//									String outRemark = "capture.output(cat(\"\nSTABILITY ANALYSIS USING SHUKLA'S MODEL:\n\"),file=\"" + outFileName + "\",append = TRUE)";
//									String outRemark2 = "capture.output(cat(\"***This is not done. The environment factor should have at least five levels.***\n\n\"),file=\"" + outFileName + "\",append = TRUE)";
//									
//									rEngine.eval(outRemark);
//									rEngine.eval(outRemark2);
//								}
//							}
							
							//default output: AMMI Biplot
//							if (ammi) {
//								if (environmentLevels.length > 2) {
//									String funcAmmi = "ammiOut <- try(ammi.analysis2(meaTwo1[[" + i + "]]$data[,match(\"CodedEnv\", names(meaTwo1[[" + i + "]]$data))], meaTwo1[[" + i + "]]$data[,match(\"CodedGeno\", names(meaTwo1[[" + i + "]]$data))], meaTwo1[[" + i + "]]$data[,match(\"" + numberOfReps[i-1] + "\", names(meaTwo1[[" + i + "]]$data))], meaTwo1[[" + i + "]]$data[, match(\"" + respvar[i-1] + "\", names(meaTwo1[[" + i + "]]$data))], meaTwo1[[" + i + "]]$data[,match(\"" + residualVariances[i-1] + "\", names(meaTwo1[[" + i + "]]$data))], number = FALSE, graphtype = \"biplot\", respVar = \"" + respvar[k]+"\"), silent=TRUE)";
//									String outAmmi1 = "capture.output(cat(\"\nAMMI ANALYSIS:\n\"),file=\"" + outFileName + "\",append = TRUE)";;
//									String outAmmi2 = "capture.output(cat(\"Percentage of Total Variation Accounted for by the Principal Components: \n\n\"),file=\"" + outFileName + "\",append = TRUE)";;
//									String outAmmi3 = "capture.output(ammiOut$analysis,file=\"" + outFileName + "\",append = TRUE)";
//									System.out.println(funcAmmi);
//									rEngine.eval(funcAmmi);
//									rEngine.eval(outAmmi1);
//									
//									String runSuccessAmmi = rEngine.eval("class(ammiOut)").asString();
//									if (runSuccessAmmi != null && runSuccessAmmi.equals("try-error")) {	
//										System.out.println("ammi: error");
//										String checkError = "msg <- trimStrings(strsplit(ammiOut, \":\")[[1]])";
//										String checkError2 = "msg <- trimStrings(paste(strsplit(msg, \"\\n\")[[length(msg)]], collapse = \" \"))";
//										String checkError3 ="msg <- gsub(\"\\\"\", \"\", msg)";
//										String checkError4 = "capture.output(cat(\"*** \nERROR in ammi.analysis2 function:\\n  \",msg, \"\n***\n\n\", sep = \"\"), file=\"" + outFileName + "\",append = TRUE)";
//										rEngine.eval(checkError);
//										rEngine.eval(checkError2);
//										rEngine.eval(checkError3);
//										rEngine.eval(checkError4);
//									} 
//									else {
//										rEngine.eval(outAmmi2);
//										rEngine.eval(outAmmi3);
//										rEngine.eval(outSpace);
//									}	
//								} else {
//									String outRemark = "capture.output(cat(\"\nAMMI ANALYSIS:\n\"),file=\"" + outFileName + "\",append = TRUE)";
//									String outRemark2 = "capture.output(cat(\"***This is not done. The environment factor should have at least three levels.***\n\n\n\"),file=\"" + outFileName + "\",append = TRUE)";
//									
//									rEngine.eval(outRemark);
//									rEngine.eval(outRemark2);
//								}
//							}//end of if ammi
							
//							if (gge) {
//								if (environmentLevels.length > 2) {
//									String funcAmmi = "ammiOut <- try(ammi.analysis2(meaTwo1[[" + i + "]]$data[,match(\""+ environment +"\", names(meaTwo1[[" + i + "]]$data))], meaTwo1[[" + i + "]]$data[,match(\"" + genotype + "\", names(meaTwo1[[" + i + "]]$data))], meaTwo1[[" + i + "]]$data[,match(\"" + numberOfReps[i-1] + "\", names(meaTwo1[[" + i + "]]$data))], meaTwo1[[" + i + "]]$data[, match(\"" + respvar[i-1] + "\", names(meaTwo1[[" + i + "]]$data))], meaTwo1[[" + i + "]]$data[,match(\"" + residualVariances[i-1] + "\", names(meaTwo1[[" + i + "]]$data))], number = FALSE, graphtype = \"biplot\", respVar = \"" + respvar[k]+"\", gge=TRUE), silent=TRUE)";
//									String outAmmi1 = "capture.output(cat(\"\nGGE ANALYSIS:\n\"),file=\"" + outFileName + "\",append = TRUE)";;
//									String outAmmi2 = "capture.output(cat(\"Percentage of Total Variation Accounted for by the Principal Components: \n\n\"),file=\"" + outFileName + "\",append = TRUE)";;
//									String outAmmi3 = "capture.output(ammiOut$analysis,file=\"" + outFileName + "\",append = TRUE)";
//									System.out.println(funcAmmi);
//									rEngine.eval(funcAmmi);
//									rEngine.eval(outAmmi1);
//									
//									String runSuccessAmmi = rEngine.eval("class(ammiOut)").asString();
//									if (runSuccessAmmi != null && runSuccessAmmi.equals("try-error")) {	
//										System.out.println("ammi: error");
//										String checkError = "msg <- trimStrings(strsplit(ammiOut, \":\")[[1]])";
//										String checkError2 = "msg <- trimStrings(paste(strsplit(msg, \"\\n\")[[length(msg)]], collapse = \" \"))";
//										String checkError3 ="msg <- gsub(\"\\\"\", \"\", msg)";
//										String checkError4 = "capture.output(cat(\"*** \nERROR in ammi.analysis function:\\n  \",msg, \"\n***\n\n\", sep = \"\"), file=\"" + outFileName + "\",append = TRUE)";
//										rEngine.eval(checkError);
//										rEngine.eval(checkError2);
//										rEngine.eval(checkError3);
//										rEngine.eval(checkError4);
//									} 
//									else {
//										rEngine.eval(outAmmi2);
//										rEngine.eval(outAmmi3);
//										rEngine.eval(outSpace);
//										rEngine.eval(outSpace);
//									}	
//								} else {
//									String outRemark = "capture.output(cat(\"\nGGE ANALYSIS:\n\"),file=\"" + outFileName + "\",append = TRUE)";
//									String outRemark2 = "capture.output(cat(\"***This is not done. The environment factor should have at least three levels.***\n\n\n\"),file=\"" + outFileName + "\",append = TRUE)";
//									
//									rEngine.eval(outRemark);
//									rEngine.eval(outRemark2);
//								}
//							}//end of if gge
							
//							if (gge) {
//								if (environmentLevels.length > 2) {
//									//f=0.5
//									String ggeOut = "ggeOut <- try(gge.analysis2(meaTwo1[[" + i + "]]$data[,match(\"CodedEnv\", names(meaTwo1[[" + i + "]]$data))], meaTwo1[[" + i + "]]$data[,match(\"CodedGeno\", names(meaTwo1[[" + i + "]]$data))], meaTwo1[[" + i + "]]$data[,match(\"" + numberOfReps[i-1] + "\", names(meaTwo1[[" + i + "]]$data))], meaTwo1[[" + i + "]]$data[, match(\"" + respvar[i-1] + "\", names(meaTwo1[[" + i + "]]$data))], meaTwo1[[" + i + "]]$data[,match(\"" + residualVariances[i-1] + "\", names(meaTwo1[[" + i + "]]$data))], number = FALSE, graphtype = \"biplot\", respVar = \"" + respvar[k]+"\", f=0.5), silent=TRUE)";
//									String outAmmi1 = "capture.output(cat(\"\nGGE ANALYSIS:\n\"),file=\"" + outFileName + "\",append = TRUE)";
//									String outAmmi2 = "capture.output(cat(\"Percentage of Total Variation Accounted for by the Principal Components: \n\n\"),file=\"" + outFileName + "\",append = TRUE)";;
//									rEngine.eval(ggeOut);
//									rEngine.eval(outAmmi1);
//									System.out.println(ggeOut);
//									
//									String runSuccessAmmi = rEngine.eval("class(ggeOut)").asString();
//									if (runSuccessAmmi != null && runSuccessAmmi.equals("try-error")) {	
//										System.out.println("gge1: error");
//										String checkError = "msg <- trimStrings(strsplit(ggeOut, \":\")[[1]])";
//										String checkError2 = "msg <- trimStrings(paste(strsplit(msg, \"\\n\")[[length(msg)]], collapse = \" \"))";
//										String checkError3 ="msg <- gsub(\"\\\"\", \"\", msg)";
//										String checkError4 = "capture.output(cat(\"*** \nERROR in gge.analysis2 function (f=0.5):\\n  \",msg, \"\n***\n\n\", sep = \"\"), file=\"" + outFileName + "\",append = TRUE)";
//										rEngine.eval(checkError);
//										rEngine.eval(checkError2);
//										rEngine.eval(checkError3);
//										rEngine.eval(checkError4);
//									} else {
//										
//										String outAmmi3 = "capture.output(ggeOut$analysis,file=\"" + outFileName + "\",append = TRUE)";
//										rEngine.eval(outAmmi2);
//										rEngine.eval(outAmmi3);
//										rEngine.eval(outSpace);
//									}
//									
//									//f=0
//									String ggeOut2 = "ggeOut2 <- try(gge.analysis2(meaTwo1[[" + i + "]]$data[,match(\"CodedEnv\", names(meaTwo1[[" + i + "]]$data))], meaTwo1[[" + i + "]]$data[,match(\"CodedGeno\", names(meaTwo1[[" + i + "]]$data))], meaTwo1[[" + i + "]]$data[,match(\"" + numberOfReps[i-1] + "\", names(meaTwo1[[" + i + "]]$data))], meaTwo1[[" + i + "]]$data[, match(\"" + respvar[i-1] + "\", names(meaTwo1[[" + i + "]]$data))], meaTwo1[[" + i + "]]$data[,match(\"" + residualVariances[i-1] + "\", names(meaTwo1[[" + i + "]]$data))], number = FALSE, graphtype = \"biplot\", respVar = \"" + respvar[k]+"\", f=0), silent=TRUE)"; 
//									rEngine.eval(ggeOut2);
//									System.out.println(ggeOut2);
//									
//									String runSuccessAmmi2 = rEngine.eval("class(ggeOut2)").asString();
//									if (runSuccessAmmi2 != null && runSuccessAmmi2.equals("try-error")) {	
//										System.out.println("gge2: error");
//										String checkError = "msg <- trimStrings(strsplit(ggeOut2, \":\")[[1]])";
//										String checkError2 = "msg <- trimStrings(paste(strsplit(msg, \"\\n\")[[length(msg)]], collapse = \" \"))";
//										String checkError3 ="msg <- gsub(\"\\\"\", \"\", msg)";
//										String checkError4 = "capture.output(cat(\"*** \nERROR in gge.analysis2 function (f=0):\\n  \",msg, \"\n***\n\n\", sep = \"\"), file=\"" + outFileName + "\",append = TRUE)";
//										rEngine.eval(checkError);
//										rEngine.eval(checkError2);
//										rEngine.eval(checkError3);
//										rEngine.eval(checkError4);
//									} 
//									
//									//f=1
//									String ggeOut3 = "ggeOut3 <- try(gge.analysis2(meaTwo1[[" + i + "]]$data[,match(\"CodedEnv\", names(meaTwo1[[" + i + "]]$data))], meaTwo1[[" + i + "]]$data[,match(\"CodedGeno\", names(meaTwo1[[" + i + "]]$data))], meaTwo1[[" + i + "]]$data[,match(\"" + numberOfReps[i-1] + "\", names(meaTwo1[[" + i + "]]$data))], meaTwo1[[" + i + "]]$data[, match(\"" + respvar[i-1] + "\", names(meaTwo1[[" + i + "]]$data))], meaTwo1[[" + i + "]]$data[,match(\"" + residualVariances[i-1] + "\", names(meaTwo1[[" + i + "]]$data))], number = FALSE, graphtype = \"biplot\", respVar = \"" + respvar[k]+"\", f=1), silent=TRUE)";
//									rEngine.eval(ggeOut3);
//									System.out.println(ggeOut3);
//									
//									String runSuccessAmmi3 = rEngine.eval("class(ggeOut3)").asString();
//									if (runSuccessAmmi3 != null && runSuccessAmmi3.equals("try-error")) {	
//										System.out.println("gge2: error");
//										String checkError = "msg <- trimStrings(strsplit(ggeOut2, \":\")[[1]])";
//										String checkError2 = "msg <- trimStrings(paste(strsplit(msg, \"\\n\")[[length(msg)]], collapse = \" \"))";
//										String checkError3 ="msg <- gsub(\"\\\"\", \"\", msg)";
//										String checkError4 = "capture.output(cat(\"*** \nERROR in gge.analysis2 function (f=1):\\n  \",msg, \"\n***\n\n\", sep = \"\"), file=\"" + outFileName + "\",append = TRUE)";
//										rEngine.eval(checkError);
//										rEngine.eval(checkError2);
//										rEngine.eval(checkError3);
//										rEngine.eval(checkError4);
//									}
//								} else {
//									String outRemark = "capture.output(cat(\"\nGGE ANALYSIS:\n\"),file=\"" + outFileName + "\",append = TRUE)";
//									String outRemark2 = "capture.output(cat(\"***This is not done. The environment factor should have at least three levels.***\n\n\n\"),file=\"" + outFileName + "\",append = TRUE)";
//									
//									rEngine.eval(outRemark);
//									rEngine.eval(outRemark2);
//								}
//							} //end of if (gge)
							
//							//if levels of Geno and Env are recoded, display new code for genotype and environment levels
//							String recodedLevels = rEngine.eval("meaTwo1[[" + i + "]]$recodedLevels").asBool().toString();
//							
//							System.out.println("recodedLevels: " + recodedLevels);
//							
//							if (recodedLevels.equals("TRUE")) {
//								String outLegends = "capture.output(cat(\"\nCODES USED IN GRAPHS:\n\n\"),file=\"" + outFileName + "\",append = TRUE)";
//								String outLegends2 = "capture.output(meaTwo1[[" + i + "]]$newCodingGeno,file=\"" + outFileName + "\",append = TRUE)";
//								String outLegends3 = "capture.output(meaTwo1[[" + i + "]]$newCodingEnv,file=\"" + outFileName + "\",append = TRUE)";
//								rEngine.eval(outLegends);
//								rEngine.eval(outLegends2);
//								rEngine.eval(outSpace);
//								rEngine.eval(outLegends3);
//								rEngine.eval(outSpace);
//								rEngine.eval(outSpace);
//							} else {
//								rEngine.eval(outSpace);
//							}
//							
//							//create response plots
//							String responsePlot1 = "dataCoded <- meaTwo1[[" + i + "]]$data";
//							String responsePlot2 = "nlevelsEnv <- meaTwo1[[" + i + "]]$nlevelsEnv";
//							String responsePlot3 = "nlevelsGeno <- meaTwo1[[" + i + "]]$nlevelsGeno";
//							String responsePlot4 = "resPlot1 <- try(GraphLine(data=dataCoded, outputPath=\"" + resultFolderPath + "\", yVars =c(\"" + respvar[i-1] + "\"), xVar =c(\"CodedGeno\"), lineVars =c(\"CodedEnv\"), mTitle =\"Response Plot of " + respvar[i-1] + "\", yAxisLab =c(\"" + respvar[i-1] + "\"), xAxisLab =\"" + genotype + "\", yMinValue = c(NA), yMaxValue = c(NA), axisLabelStyle = 2, byVar = NULL, plotCol = c(1:nlevelsEnv), showLineLabels =TRUE, showLeg = FALSE, boxed = TRUE, linePtTypes=rep(\"b\", nlevelsEnv), lineTypes=rep(1, nlevelsEnv), lineWidths=rep(1, nlevelsEnv), pointChars=rep(1, nlevelsEnv), pointCharSizes=rep(1, nlevelsEnv), multGraphs =FALSE), silent = TRUE)";
//							String responsePlot5 = "resPlot2 <- try(GraphLine(data=dataCoded, outputPath=\"" + resultFolderPath + "\", yVars =c(\"" + respvar[i-1] + "\"), xVar =c(\"CodedEnv\"), lineVars =c(\"CodedGeno\"), mTitle =\"Response Plot of " + respvar[i-1] + "\", yAxisLab =c(\"" + respvar[i-1] + "\"), xAxisLab =\"" + environment + "\", yMinValue = c(NA), yMaxValue = c(NA), axisLabelStyle = 2, byVar = NULL, plotCol = c(1:nlevelsGeno), showLineLabels =TRUE, showLeg = FALSE, boxed = TRUE, linePtTypes=rep(\"b\", nlevelsGeno), lineTypes=rep(1, nlevelsGeno), lineWidths=rep(1, nlevelsGeno), pointChars=rep(1, nlevelsGeno), pointCharSizes=rep(1, nlevelsGeno), multGraphs =FALSE), silent = TRUE)";
//							
//							System.out.println(responsePlot1);
//							System.out.println(responsePlot2);
//							System.out.println(responsePlot3);
//							System.out.println(responsePlot4);
//							System.out.println(responsePlot5);
//							
//							rEngine.eval(responsePlot1);
//							rEngine.eval(responsePlot2);
//							rEngine.eval(responsePlot3);
//							rEngine.eval(responsePlot4);
//							rEngine.eval(responsePlot5);
//							
//							String runSuccessPlot1 = rEngine.eval("class(resPlot1)").asString();
//							if (runSuccessPlot1 != null && runSuccessPlot1.equals("try-error")) {	
//								System.out.println("response plot geno: error");
//								String checkError = "msg <- trimStrings(strsplit(resPlot1, \":\")[[1]])";
//								String checkError2 = "msg <- trimStrings(paste(strsplit(msg, \"\\n\")[[length(msg)]], collapse = \" \"))";
//								String checkError3 ="msg <- gsub(\"\\\"\", \"\", msg)";
//								String checkError4 = "capture.output(cat(\"*** \nERROR in GraphLine function (geno):\\n  \",msg, \"\n***\n\n\", sep = \"\"), file=\"" + outFileName + "\",append = TRUE)";
//								rEngine.eval(checkError);
//								rEngine.eval(checkError2);
//								rEngine.eval(checkError3);
//								rEngine.eval(checkError4);
//							}
//							
//							String runSuccessPlot2 = rEngine.eval("class(resPlot2)").asString();
//							if (runSuccessPlot2 != null && runSuccessPlot2.equals("try-error")) {	
//								System.out.println("response plot env: error");
//								String checkError = "msg <- trimStrings(strsplit(resPlot2, \":\")[[1]])";
//								String checkError2 = "msg <- trimStrings(paste(strsplit(msg, \"\\n\")[[length(msg)]], collapse = \" \"))";
//								String checkError3 ="msg <- gsub(\"\\\"\", \"\", msg)";
//								String checkError4 = "capture.output(cat(\"*** \nERROR in GraphLine function (env):\\n  \",msg, \"\n***\n\n\", sep = \"\"), file=\"" + outFileName + "\",append = TRUE)";
//								rEngine.eval(checkError);
//								rEngine.eval(checkError2);
//								rEngine.eval(checkError3);
//								rEngine.eval(checkError4);
//							}
							
							rEngine.eval(outSpace);
						} //end of if (printAllOutputFixed)
					}//end of for loop for respvars 
				
					//optional output: Diagnostic plots for genotype fixed
					if (diagnosticPlot) {
						String diagPlotsMea2SFunc = "diagPlotsMea2S <- try(graph.mea2s.diagplots(dataMeaTwoStage, " + respvarVector + ", is.random = FALSE, meaTwo1), silent=TRUE)";
						System.out.println(diagPlotsMea2SFunc);
						rEngine.eval(diagPlotsMea2SFunc);
						
						String runSuccessDiagPlotsMea2S = rEngine.eval("diagPlotsMea2S").asString();
						if (runSuccessDiagPlotsMea2S != null && runSuccessDiagPlotsMea2S.equals("notRun")) {	
							System.out.println("error");
							rEngine.eval("capture.output(cat(\"\n***An error has occurred.***\n***Diagnostic plots for fixed genotype not created.***\n\"),file=\"" + outFileName + "\",append = TRUE)"); //append to output file?
						}
					}
				} //end of else for if runSuccess
			} //end of Fixed
			
			// Genotype Random
			if (genotypeRandom) {
				String funcMeaRandom = null;
				if (weightOption == "none") 
					funcMeaRandom = "meaTwo2 <- try(GETwoStage.test(dataMeaTwoStage, " + respvarVector + ",stderr = NULL," + residualVariancesVector + "," + numberOfRepsVector + ",\"" + genotype + "\",\"" + environment + "\", weight = \"" + weightOption + "\", is.genoRandom = TRUE), silent=TRUE)";
				else funcMeaRandom = "meaTwo2 <- try(GETwoStage.test(dataMeaTwoStage, " + respvarVector + "," + standardErrorsVector + "," + residualVariancesVector + "," + numberOfRepsVector + ",\"" + genotype + "\",\"" + environment + "\", weight = \"" + weightOption + "\", is.genoRandom = TRUE), silent=TRUE)";

				String randomHead = "capture.output(cat(\"GENOTYPE AS: Random\n\"),file=\"" + outFileName + "\",append = TRUE)";
				
				System.out.println(funcMeaRandom);
				rEngine.eval(funcMeaRandom);
				rEngine.eval(outSpace);
				rEngine.eval(sep2);
				rEngine.eval(randomHead);
				rEngine.eval(sep2);
				rEngine.eval(outSpace);

				String runSuccess = rEngine.eval("class(meaTwo2)").asString();
				if (runSuccess != null && runSuccess.equals("try-error")) {	
					System.out.println("MEATwoStage.test: error");
					String checkError = "msg <- trimStrings(strsplit(meaTwo2, \":\")[[1]])";
					String checkError2 = "msg <- trimStrings(paste(strsplit(msg, \"\\n\")[[length(msg)]], collapse = \" \"))";
					String checkError3 ="msg <- gsub(\"\\\"\", \"\", msg)";
					String checkError4 = "capture.output(cat(\"*** \nERROR in GETwoStage.test function:\\n  \",msg, \"\n***\n\n\", sep = \"\"), file=\"" + outFileName + "\",append = TRUE)";
					rEngine.eval(checkError);
					rEngine.eval(checkError2);
					rEngine.eval(checkError3);
					rEngine.eval(checkError4);
					
					runningRandomSuccess =false;
				}
				else {
					for (int k = 0; k < respvar.length; k++) {
						printAllOutputRandom=true;
						int i = k + 1; // 1-relative index;
						String respVarHead = "capture.output(cat(\"RESPONSE VARIABLE: " + respvar[k] + "\n\"),file=\"" + outFileName + "\",append = TRUE)";
						rEngine.eval(sep);
						rEngine.eval(respVarHead);
						rEngine.eval(sep);
						
						//check if the data has too many missing observations
						double responseRate = rEngine.eval("meaTwo2[[" + i + "]]$responseRate").asDouble();
						if (responseRate < 0.80) {
							String allNAWarning = rEngine.eval("meaTwo2[[" + i + "]]$manyNAWarning").asString();
							String printError1 = "capture.output(cat(\"***\\n\"), file=\"" + outFileName + "\",append = TRUE)";
							String printError2 = "capture.output(cat(\"ERROR:\\n\"), file=\"" + outFileName + "\",append = TRUE)";
							String printError3 = "capture.output(cat(\"" + allNAWarning + "\\n\"), file=\"" + outFileName + "\",append = TRUE)";
							
							rEngine.eval(outSpace);
							rEngine.eval(printError1);
							rEngine.eval(printError2);
							rEngine.eval(printError3);
							rEngine.eval(printError1);
							rEngine.eval(outSpace);
							rEngine.eval(outSpace);
							printAllOutputRandom=false;
						}
						
						if (printAllOutputRandom) {
							//default output: Trial Summary
							String funcTrialSum = "funcTrialSum <- try(class.information(c(\"" + genotype + "\",\"" + environment + "\"),meaTwo2[[" + i + "]]$data), silent=TRUE)";
							String trialSumHead = "capture.output(cat(\"\nDATA SUMMARY:\n\n\"),file=\"" + outFileName + "\",append = TRUE)";
							String trialObsRead = "capture.output(cat(\"Number of observations read: \", meaTwo2[["	+ i	+ "]]$obsread[[1]],\"\n\"),file=\"" + outFileName + "\",append = TRUE)";
							String trialObsUsed = "capture.output(cat(\"Number of observations used: \", meaTwo2[["	+ i	+ "]]$obsused[[1]],\"\n\n\"),file=\""	+ outFileName + "\",append = TRUE)";
							String trialSum = "capture.output(funcTrialSum,file=\"" + outFileName + "\",append = TRUE)";

							rEngine.eval(funcTrialSum);
							
							String runSuccessTS = rEngine.eval("class(funcTrialSum)").asString();
							if (runSuccessTS != null && runSuccessTS.equals("try-error")) {	
								System.out.println("class info: error");
								String checkError = "msg <- trimStrings(strsplit(funcTrialSum, \":\")[[1]])";
								String checkError2 = "msg <- trimStrings(paste(strsplit(msg, \"\\n\")[[length(msg)]], collapse = \" \"))";
								String checkError3 ="msg <- gsub(\"\\\"\", \"\", msg)";
								String checkError4 = "capture.output(cat(\"*** \nERROR in class.information function:\\n  \",msg, \"\n***\n\n\", sep = \"\"), file=\"" + outFileName + "\",append = TRUE)";
								rEngine.eval(checkError);
								rEngine.eval(checkError2);
								rEngine.eval(checkError3);
								rEngine.eval(checkError4);
							}
							else {
								rEngine.eval(trialSumHead);
								rEngine.eval(trialObsRead);
								rEngine.eval(trialObsUsed);
								rEngine.eval(trialSum);
								rEngine.eval(outSpace);
							}
		
							//optional output: descriptive statistics
							if (descriptiveStat) {
								String funcDesc = "outDesc <- try(DescriptiveStatistics(dataMeaTwoStage, \"" + respvar[k] + "\", grp = NULL), silent=TRUE)";
								rEngine.eval(funcDesc);
								String outDescStat = "capture.output(cat(\"\nDESCRIPTIVE STATISTICS:\n\n\"),file=\"" + outFileName + "\",append = TRUE)";
								String outDescStat2 = "capture.output(outDesc,file=\"" + outFileName + "\",append = TRUE)"; 
								rEngine.eval(outDescStat);
		
								String runSuccessDescStat = rEngine.eval("class(outDesc)").asString();	
								if (runSuccessDescStat != null && runSuccessDescStat.equals("try-error")) {	
									System.out.println("desc stat: error");
									String checkError = "msg <- trimStrings(strsplit(outDesc, \":\")[[1]])";
									String checkError2 = "msg <- trimStrings(paste(strsplit(msg, \"\\n\")[[length(msg)]], collapse = \" \"))";
									String checkError3 ="msg <- gsub(\"\\\"\", \"\", msg)";
									String checkError4 = "capture.output(cat(\"*** \nERROR in DescriptiveStatistics function:\\n  \",msg, \"\n***\n\n\", sep = \"\"), file=\"" + outFileName + "\",append = TRUE)";
									rEngine.eval(checkError);
									rEngine.eval(checkError2);
									rEngine.eval(checkError3);
									rEngine.eval(checkError4);
								}
								else {
									rEngine.eval(outDescStat2);
									rEngine.eval(outSpace);
								}
							}
		
							//optional output: Variance Components
							if (varianceComponents) {
								String outVarComp = "capture.output(cat(\"\nVARIANCE COMPONENTS TABLE:\n\n\"),file=\"" + outFileName + "\",append = TRUE)";
								String outVarComp2 = "capture.output(meaTwo2[[" + i + "]]$varcomp.table,file=\"" + outFileName + "\",append = TRUE)";
		
								rEngine.eval(outVarComp);
								rEngine.eval(outVarComp2);
								rEngine.eval(outSpace);
							}
							
							//default output: Test Genotypic Effect
							String outTestGeno1 = "capture.output(cat(\"\nTESTING FOR THE SIGNIFICANCE OF GENOTYPIC EFFECT USING -2 LOGLIKELIHOOD RATIO TEST:\n\"),file=\"" + outFileName + "\",append = TRUE)";
							String outTestGeno2 = "capture.output(cat(\"\nFormula for Model1: \", meaTwo2[[" + i + "]]$formula1,\"\n\"),file=\"" + outFileName + "\",append = TRUE)";
							String outTestGeno3 = "capture.output(cat(\"Formula for Model2: \", meaTwo2[[" + i + "]]$formula2,\"\n\n\"),file=\"" + outFileName + "\",append = TRUE)";
							String outTestGeno4 = "capture.output(meaTwo2[[" + i + "]]$testsig.Geno,file=\"" + outFileName + "\",append = TRUE)";
							rEngine.eval(outTestGeno1);
							rEngine.eval(outTestGeno2);
							rEngine.eval(outTestGeno3);
							rEngine.eval(outTestGeno4);
							rEngine.eval(outSpace);
							
							//default output: Test Environment Effect
							String outTestEnv1 = "capture.output(cat(\"\nTESTING FOR THE SIGNIFICANCE OF ENVIRONMENT EFFECT USING -2 LOGLIKELIHOOD RATIO TEST:\n\"),file=\"" + outFileName + "\",append = TRUE)";
							String outTestEnv2 = "capture.output(cat(\"\nFormula for Model1: \", meaTwo2[[" + i + "]]$formula1,\"\n\"),file=\"" + outFileName + "\",append = TRUE)";
							String outTestEnv3 = "capture.output(cat(\"Formula for Model2: \", meaTwo2[[" + i + "]]$formula3,\"\n\n\"),file=\"" + outFileName + "\",append = TRUE)";
							String outTestEnv4 = "capture.output(meaTwo2[[" + i + "]]$testsig.Env,file=\"" + outFileName + "\",append = TRUE)";
							rEngine.eval(outTestEnv1);
							rEngine.eval(outTestEnv2);
							rEngine.eval(outTestEnv3);
							rEngine.eval(outTestEnv4);
							rEngine.eval(outSpace);
							
							//default output: Genotype Means
							String outDescStat = "capture.output(cat(\"\nPREDICTED GENOTYPE MEANS:\n\n\"),file=\"" + outFileName + "\",append = TRUE)";
							String outDescStat2 = "capture.output(meaTwo2[[" + i + "]]$means.Geno,file=\"" + outFileName + "\",append = TRUE)";
							rEngine.eval(outDescStat);
							rEngine.eval(outDescStat2);
							rEngine.eval(outSpace);
							
							// default output if no weight is selected: Estimate Heritability
							if (weightOption == "none") {
								String outEstHerit = "capture.output(cat(\"\nHERITABILITY = \", meaTwo2[[" + i + "]]$heritability,\"\n\"),file=\"" + outFileName + "\",append = TRUE)";
								
								rEngine.eval(outEstHerit);
								rEngine.eval(outSpace);
							}
							
//							//if levels of Geno and Env are recoded, display new code for genotype and environment levels
//							String recodedLevels = rEngine.eval("meaTwo2[[" + i + "]]$recodedLevels").asBool().toString();
//							
//							System.out.println("recodedLevels: " + recodedLevels);
//							
//							if (recodedLevels.equals("TRUE")) {
//								String outLegends = "capture.output(cat(\"\nCODES USED IN GRAPHS:\n\n\"),file=\"" + outFileName + "\",append = TRUE)";
//								String outLegends2 = "capture.output(meaTwo2[[" + i + "]]$newCodingGeno,file=\"" + outFileName + "\",append = TRUE)";
//								String outLegends3 = "capture.output(meaTwo2[[" + i + "]]$newCodingEnv,file=\"" + outFileName + "\",append = TRUE)";
//								rEngine.eval(outLegends);
//								rEngine.eval(outLegends2);
//								rEngine.eval(outSpace);
//								rEngine.eval(outLegends3);
//								rEngine.eval(outSpace);
//								rEngine.eval(outSpace);
//							} else {
//								rEngine.eval(outSpace);
//							}
//							
//							//create response plots
//							String responsePlot1 = "dataCoded <- meaTwo2[[" + i + "]]$data";
//							String responsePlot2 = "nlevelsEnv <- meaTwo2[[" + i + "]]$nlevelsEnv";
//							String responsePlot3 = "nlevelsGeno <- meaTwo2[[" + i + "]]$nlevelsGeno";
//							String responsePlot4 = "resPlot1 <- try(GraphLine(data=dataCoded, outputPath=\"" + resultFolderPath + "\", yVars =c(\"" + respvar[i-1] + "\"), xVar =c(\"CodedGeno\"), lineVars =c(\"CodedEnv\"), mTitle =\"Response Plot of " + respvar[i-1] + "\", yAxisLab =c(\"" + respvar[i-1] + "\"), xAxisLab =\"" + genotype + "\", yMinValue = c(NA), yMaxValue = c(NA), axisLabelStyle = 2, byVar = NULL, plotCol = c(1:nlevelsEnv), showLineLabels =TRUE, showLeg = FALSE, boxed = TRUE, linePtTypes=rep(\"b\", nlevelsEnv), lineTypes=rep(1, nlevelsEnv), lineWidths=rep(1, nlevelsEnv), pointChars=rep(\"  \", nlevelsEnv), pointCharSizes=rep(1, nlevelsEnv), multGraphs =FALSE), silent = TRUE)";
//							String responsePlot5 = "resPlot2 <- try(GraphLine(data=dataCoded, outputPath=\"" + resultFolderPath + "\", yVars =c(\"" + respvar[i-1] + "\"), xVar =c(\"CodedEnv\"), lineVars =c(\"CodedGeno\"), mTitle =\"Response Plot of " + respvar[i-1] + "\", yAxisLab =c(\"" + respvar[i-1] + "\"), xAxisLab =\"" + environment + "\", yMinValue = c(NA), yMaxValue = c(NA), axisLabelStyle = 2, byVar = NULL, plotCol = c(1:nlevelsGeno), showLineLabels =TRUE, showLeg = FALSE, boxed = TRUE, linePtTypes=rep(\"b\", nlevelsGeno), lineTypes=rep(1, nlevelsGeno), lineWidths=rep(1, nlevelsGeno), pointChars=rep(\"  \", nlevelsGeno), pointCharSizes=rep(1, nlevelsGeno), multGraphs =FALSE), silent = TRUE)";
//							
//							System.out.println(responsePlot1);
//							System.out.println(responsePlot2);
//							System.out.println(responsePlot3);
//							System.out.println(responsePlot4);
//							System.out.println(responsePlot5);
//							
//							rEngine.eval(responsePlot1);
//							rEngine.eval(responsePlot2);
//							rEngine.eval(responsePlot3);
//							rEngine.eval(responsePlot4);
//							rEngine.eval(responsePlot5);
//							
//							String runSuccessPlot1 = rEngine.eval("class(resPlot1)").asString();
//							if (runSuccessPlot1 != null && runSuccessPlot1.equals("try-error")) {	
//								System.out.println("response plot geno: error");
//								String checkError = "msg <- trimStrings(strsplit(resPlot1, \":\")[[1]])";
//								String checkError2 = "msg <- trimStrings(paste(strsplit(msg, \"\\n\")[[length(msg)]], collapse = \" \"))";
//								String checkError3 ="msg <- gsub(\"\\\"\", \"\", msg)";
//								String checkError4 = "capture.output(cat(\"*** \nERROR in GraphLine function (geno):\\n  \",msg, \"\n***\n\n\", sep = \"\"), file=\"" + outFileName + "\",append = TRUE)";
//								rEngine.eval(checkError);
//								rEngine.eval(checkError2);
//								rEngine.eval(checkError3);
//								rEngine.eval(checkError4);
//							}
//							
//							String runSuccessPlot2 = rEngine.eval("class(resPlot2)").asString();
//							if (runSuccessPlot2 != null && runSuccessPlot2.equals("try-error")) {	
//								System.out.println("response plot env: error");
//								String checkError = "msg <- trimStrings(strsplit(resPlot2, \":\")[[1]])";
//								String checkError2 = "msg <- trimStrings(paste(strsplit(msg, \"\\n\")[[length(msg)]], collapse = \" \"))";
//								String checkError3 ="msg <- gsub(\"\\\"\", \"\", msg)";
//								String checkError4 = "capture.output(cat(\"*** \nERROR in GraphLine function (env):\\n  \",msg, \"\n***\n\n\", sep = \"\"), file=\"" + outFileName + "\",append = TRUE)";
//								rEngine.eval(checkError);
//								rEngine.eval(checkError2);
//								rEngine.eval(checkError3);
//								rEngine.eval(checkError4);
//							}
							
							rEngine.eval(outSpace);
						}
					}//end of for loop respvar
				
					//diagnostic plots for genotype random
					if (diagnosticPlot) {
						String diagPlotsMea2SFunc = "diagPlotsMea2S <- tryCatch(graph.mea2s.diagplots(dataMeaTwoStage, " + respvarVector + ", is.random = TRUE, meaTwo2), error=function(err) \"notRun\")";
						System.out.println(diagPlotsMea2SFunc);
						rEngine.eval(diagPlotsMea2SFunc);
						
						String runSuccessDiagPlotsMea2S = rEngine.eval("diagPlotsMea2S").asString();
						if (runSuccessDiagPlotsMea2S != null && runSuccessDiagPlotsMea2S.equals("notRun")) {	
							System.out.println("error");
							rEngine.eval("capture.output(cat(\"\n***An error has occurred.***\n***Diagnostic plots for random genotype not created.***\n\"),file=\"" + outFileName + "\",append = TRUE)"); //append to output file?
						}
					}
				} //end of else for if runSuccess
			} // end of if random

			//default output: save residuals to csv files
			if (runningFixedSuccess & runningRandomSuccess) {
				String residFileNameFixed = "residFileNameFixed <- paste(\"" + resultFolderPath + "\",\"residuals_fixed.csv\", sep=\"\")";
				String residFileNameRandom = "residFileNameRandom <- paste(\"" + resultFolderPath + "\",\"residuals_random.csv\", sep=\"\")";
				if ((genotypeFixed) & (genotypeRandom == false)) {
					String runSsaResid1 = "resid_f <- try(GETwoStage_resid(meaTwo1, " + respvarVector + ", is.genoRandom = FALSE), silent=TRUE)";
					System.out.println(runSsaResid1);
					rEngine.eval(runSsaResid1);
					
					String runSuccessDiagPlots = rEngine.eval("class(resid_f)").asString();
					if (runSuccessDiagPlots != null && runSuccessDiagPlots.equals("try-error")) {	
						System.out.println("GETwoStage_resid (genotype fixed): error");
						String checkError = "msg <- trimStrings(strsplit(resid_f, \":\")[[1]])";
						String checkError2 = "msg <- trimStrings(paste(strsplit(msg, \"\\n\")[[length(msg)]], collapse = \" \"))";
						String checkError3 ="msg <- gsub(\"\\\"\", \"\", msg)";
						String checkError4 = "capture.output(cat(\"*** \nERROR in GETwoStage_resid function:\\n  \",msg, \"\n***\n\n\", sep = \"\"), file=\"" + outFileName + "\",append = TRUE)";
						rEngine.eval(checkError);
						rEngine.eval(checkError2);
						rEngine.eval(checkError3);
						rEngine.eval(checkError4);
					} else {
						String checkResid1 = rEngine.eval("resid_f$ge2residWarning").asString();
						System.out.println("checkResid1: " + checkResid1);
						if (checkResid1.equals("empty")) {
							System.out.println("Saving resid (fixed) not done.");
						} else {
							String func1SaveResidualsCsv = "saveResid <- try(write.table(resid_f$residuals, file = residFileNameFixed ,sep=\",\",row.names=FALSE), silent=TRUE)";
							rEngine.eval(residFileNameFixed);
							rEngine.eval(func1SaveResidualsCsv);
							
							String runSuccessSaveResid = rEngine.eval("class(saveResid)").asString();
							if (runSuccessSaveResid != null && runSuccessSaveResid.equals("try-error")) {	
								System.out.println("save residuals: error");
								String checkError = "msg <- trimStrings(strsplit(saveResid, \":\")[[1]])";
								String checkError2 = "msg <- trimStrings(paste(strsplit(msg, \"\\n\")[[length(msg)]], collapse = \" \"))";
								String checkError3 ="msg <- gsub(\"\\\"\", \"\", msg)";
								String checkError4 = "capture.output(cat(\"*** \nERROR in saving residuals to a file:\\n  \",msg, \"\n***\n\n\", sep = \"\"), file=\"" + outFileName + "\",append = TRUE)";
								rEngine.eval(checkError);
								rEngine.eval(checkError2);
								rEngine.eval(checkError3);
								rEngine.eval(checkError4);
							}
						}
					}
				}
				else if ((genotypeFixed == false) & (genotypeRandom)) {
					String runSsaResid2 = "resid_r <- try(GETwoStage_resid(meaTwo2, " + respvarVector + ", is.genoRandom = TRUE), silent=TRUE)";
					System.out.println(runSsaResid2);
					rEngine.eval(runSsaResid2);
					
					String runSuccessDiagPlots = rEngine.eval("class(resid_r)").asString();
					if (runSuccessDiagPlots != null && runSuccessDiagPlots.equals("try-error")) {	
						System.out.println("GETwoStage_resid (genotype random): error");
						String checkError = "msg <- trimStrings(strsplit(resid_r, \":\")[[1]])";
						String checkError2 = "msg <- trimStrings(paste(strsplit(msg, \"\\n\")[[length(msg)]], collapse = \" \"))";
						String checkError3 ="msg <- gsub(\"\\\"\", \"\", msg)";
						String checkError4 = "capture.output(cat(\"*** \nERROR in GETwoStage_resid function:\\n  \",msg, \"\n***\n\n\", sep = \"\"), file=\"" + outFileName + "\",append = TRUE)";
						rEngine.eval(checkError);
						rEngine.eval(checkError2);
						rEngine.eval(checkError3);
						rEngine.eval(checkError4);
					} else {
						String checkResid1 = rEngine.eval("resid_r$ge2residWarning").asString();
						System.out.println("checkResid2: " + checkResid1);
						if (checkResid1.equals("empty")) {
							System.out.println("Saving resid (random) not done.");
						} else {
							String func1SaveResidualsCsv = "saveResid <- try(write.table(resid_r$residuals, file = residFileNameRandom ,sep=\",\",row.names=FALSE), silent=TRUE)";
							rEngine.eval(residFileNameRandom);
							rEngine.eval(func1SaveResidualsCsv);
							
							String runSuccessSaveResid = rEngine.eval("class(saveResid)").asString();
							if (runSuccessSaveResid != null && runSuccessSaveResid.equals("try-error")) {	
								System.out.println("save residuals: error");
								String checkError = "msg <- trimStrings(strsplit(saveResid, \":\")[[1]])";
								String checkError2 = "msg <- trimStrings(paste(strsplit(msg, \"\\n\")[[length(msg)]], collapse = \" \"))";
								String checkError3 ="msg <- gsub(\"\\\"\", \"\", msg)";
								String checkError4 = "capture.output(cat(\"*** \nERROR in saving residuals to a file:\\n  \",msg, \"\n***\n\n\", sep = \"\"), file=\"" + outFileName + "\",append = TRUE)";
								rEngine.eval(checkError);
								rEngine.eval(checkError2);
								rEngine.eval(checkError3);
								rEngine.eval(checkError4);
							}
						}
					}
				}
				else if ((genotypeFixed) & (genotypeRandom)) {
					String runSsaResid1 = "resid_f <- try(GETwoStage_resid(meaTwo1, " + respvarVector + ", is.genoRandom = FALSE), silent=TRUE)";
					String runSsaResid2 = "resid_r <- try(GETwoStage_resid(meaTwo2, " + respvarVector + ", is.genoRandom = TRUE), silent=TRUE)";
					System.out.println(runSsaResid1);
					System.out.println(runSsaResid2);
					rEngine.eval(runSsaResid1);
					rEngine.eval(runSsaResid2);
					
					String runSuccessResidFixed = rEngine.eval("class(resid_f)").asString();
					if (runSuccessResidFixed != null && runSuccessResidFixed.equals("try-error")) {	
						System.out.println("GETwoStage_resid (genotype fixed): error");
						String checkError = "msg <- trimStrings(strsplit(resid_f, \":\")[[1]])";
						String checkError2 = "msg <- trimStrings(paste(strsplit(msg, \"\\n\")[[length(msg)]], collapse = \" \"))";
						String checkError3 ="msg <- gsub(\"\\\"\", \"\", msg)";
						String checkError4 = "capture.output(cat(\"*** \nERROR in GETwoStage_resid function:\\n  \",msg, \"\n***\n\n\", sep = \"\"), file=\"" + outFileName + "\",append = TRUE)";
						rEngine.eval(checkError);
						rEngine.eval(checkError2);
						rEngine.eval(checkError3);
						rEngine.eval(checkError4);
					} else {
						String checkResid1 = rEngine.eval("resid_f$ge2residWarning").asString();
						System.out.println("checkResid1: " + checkResid1);
						if (checkResid1.equals("empty")) {
							System.out.println("Saving resid (fixed) not done.");
						} else {
							String func1SaveResidualsCsv = "saveResid <- try(write.table(resid_f$residuals, file = residFileNameFixed ,sep=\",\",row.names=FALSE), silent=TRUE)";
							rEngine.eval(residFileNameFixed);
							rEngine.eval(func1SaveResidualsCsv);
							
							String runSuccessSaveResid = rEngine.eval("class(saveResid)").asString();
							if (runSuccessSaveResid != null && runSuccessSaveResid.equals("try-error")) {	
								System.out.println("save residuals: error");
								String checkError = "msg <- trimStrings(strsplit(saveResid, \":\")[[1]])";
								String checkError2 = "msg <- trimStrings(paste(strsplit(msg, \"\\n\")[[length(msg)]], collapse = \" \"))";
								String checkError3 ="msg <- gsub(\"\\\"\", \"\", msg)";
								String checkError4 = "capture.output(cat(\"*** \nERROR in saving residuals to a file:\\n  \",msg, \"\n***\n\n\", sep = \"\"), file=\"" + outFileName + "\",append = TRUE)";
								rEngine.eval(checkError);
								rEngine.eval(checkError2);
								rEngine.eval(checkError3);
								rEngine.eval(checkError4);
							}
						}
					}
					
					String runSuccessResidRandom = rEngine.eval("class(resid_r)").asString();
					if (runSuccessResidRandom != null && runSuccessResidRandom.equals("try-error")) {	
						System.out.println("GETwoStage_resid (genotype random): error");
						String checkError = "msg <- trimStrings(strsplit(resid_r, \":\")[[1]])";
						String checkError2 = "msg <- trimStrings(paste(strsplit(msg, \"\\n\")[[length(msg)]], collapse = \" \"))";
						String checkError3 ="msg <- gsub(\"\\\"\", \"\", msg)";
						String checkError4 = "capture.output(cat(\"*** \nERROR in GETwoStage_resid function:\\n  \",msg, \"\n***\n\n\", sep = \"\"), file=\"" + outFileName + "\",append = TRUE)";
						rEngine.eval(checkError);
						rEngine.eval(checkError2);
						rEngine.eval(checkError3);
						rEngine.eval(checkError4);
					} else {
						String checkResid1 = rEngine.eval("resid_r$ge2residWarning").asString();
						System.out.println("checkResid2: " + checkResid1);
						if (checkResid1.equals("empty")) {
							System.out.println("Saving resid (random) not done.");
						} else {
							String func1SaveResidualsCsv = "saveResid2 <- try(write.table(resid_r$residuals, file = residFileNameRandom ,sep=\",\",row.names=FALSE), silent=TRUE)";
							rEngine.eval(residFileNameRandom);
							rEngine.eval(func1SaveResidualsCsv);
							
							String runSuccessSaveResid = rEngine.eval("class(saveResid2)").asString();
							if (runSuccessSaveResid != null && runSuccessSaveResid.equals("try-error")) {	
								System.out.println("save residuals: error");
								String checkError = "msg <- trimStrings(strsplit(saveResid2, \":\")[[1]])";
								String checkError2 = "msg <- trimStrings(paste(strsplit(msg, \"\\n\")[[length(msg)]], collapse = \" \"))";
								String checkError3 ="msg <- gsub(\"\\\"\", \"\", msg)";
								String checkError4 = "capture.output(cat(\"*** \nERROR in saving residuals to a file:\\n  \",msg, \"\n***\n\n\", sep = \"\"), file=\"" + outFileName + "\",append = TRUE)";
								rEngine.eval(checkError);
								rEngine.eval(checkError2);
								rEngine.eval(checkError3);
								rEngine.eval(checkError4);
							}
						}
					}
				}
			}
			 
			//optional output: boxplot and histogram
			String withBox = "FALSE";
			if (boxplotRawData) withBox = "TRUE";
			String withHist = "FALSE";
			if (histogramRawData) withHist = "TRUE";
			String meaOut = "meaTwo1";
			if (genotypeFixed) meaOut = "meaTwo1";
			else if (genotypeRandom) meaOut = "meaTwo2";

			String boxHistMea2SFunc = "boxHistMea2S <- tryCatch(graph.mea2s.boxhist(dataMeaTwoStage, " + respvarVector + ", " + meaOut + ", box = \"" + withBox + "\", hist = \"" + withHist + "\"), error=function(err) \"notRun\")";
			System.out.println(boxHistMea2SFunc);
			rEngine.eval(boxHistMea2SFunc);
				
			String runSuccessBoxHistMea2S = rEngine.eval("boxHistMea2S").asString();
			if (runSuccessBoxHistMea2S != null && runSuccessBoxHistMea2S.equals("notRun")) {	
				System.out.println("error");
				rEngine.eval("capture.output(cat(\"\n***An error has occurred.***\n***Boxplot(s) and histogram(s) not created.***\n\"),file=\"" + outFileName + "\",append = TRUE)"); //append to output file?
			}
			
			rEngine.eval(outSpace);
			rEngine.eval(sep2);
			
			rEngineEnd();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	@Override
	public void doMultiEnvironmentSecondStageVersion2(String dataFileName, String outFileName, String resultFolderPath, String[] respvar, String environment, String[] environmentLevels, String genotype, String[] mseValue, String[] repValue, boolean stabilityFinlay, boolean stabilityShukla, boolean ammi, boolean gge) {
		
		String respvarVector= inputTransform.createRVector(respvar);
		
		try {
			String readData = "dataMeaTwoStage <- read.csv(\"" + dataFileName + "\", header = TRUE, na.strings = c(\"NA\",\".\",\" \",\"\"), blank.lines.skip=TRUE, sep = \",\")";
			System.out.println(readData);
			rEngine.eval(readData);
			
			String runSuccessData = rEngine.eval("dataMeaTwoStage").asString();
			
			if (runSuccessData != null && runSuccessData.equals("notRun")) {	
				System.out.println("error");
				rEngine.eval("capture.output(cat(\"\n***Error reading data.***\n\"),file=\"" + outFileName + "\",append = FALSE)"); //append to output file?
			} else {
				
				// recode levels of genotype and environment data
				String recodeData = "dataRecoded <- GETwoStage.dataRecode(dataMeaTwoStage, " + respvarVector + ", \"" + genotype + "\", \"" + environment + "\")";
				System.out.println(recodeData);
				rEngine.eval(recodeData);
				
				String setWd = "setwd(\"" + resultFolderPath + "\")";
				System.out.println(setWd);
				rEngine.eval(setWd);
				
				String usedData = "capture.output(cat(\"\nDATA FILE: " + dataFileName + "\n\",file=\"" + outFileName + "\"))";
				String outFile = "capture.output(cat(\"\nMULTI-ENVIRONMENT ANALYSIS (TWO-STAGE)\n\"),file=\"" + outFileName + "\", append = TRUE)";
				String sep = "capture.output(cat(\"------------------------------\n\"),file=\"" + outFileName + "\",append = TRUE)";
				String sep2 = "capture.output(cat(\"==============================\n\"),file=\"" + outFileName + "\",append = TRUE)";
				String outSpace = "capture.output(cat(\"\n\"),file=\"" + outFileName + "\",append = TRUE)";
				rEngine.eval(usedData);
				rEngine.eval(outFile);
				rEngine.eval(outSpace);
				
				String fixedHead = "capture.output(cat(\"GENOTYPE AS: Fixed\n\"),file=\""+ outFileName + "\",append = TRUE)";
				rEngine.eval(sep2);
				rEngine.eval(fixedHead);
				rEngine.eval(sep2);
				rEngine.eval(outSpace);

				
				// OUTPUT
				// Genotype Fixed
				for (int k = 0; k < respvar.length; k++) {
					
					String respVarHead = "capture.output(cat(\"RESPONSE VARIABLE: " + respvar[k] + "\n\"),file=\"" + outFileName + "\",append = TRUE)";
					rEngine.eval(sep);
					rEngine.eval(respVarHead);
					rEngine.eval(sep);
					
					if (stabilityFinlay) {
						//optional output; number of environment levels should be at least 5: Stability Analysis using Regression
						if (environmentLevels.length > 4) {
							String funcStability1 = "funcStability1 <- try(stability.analysis(dataRecoded[[" + (k+1) + "]]$data, \"" + respvar[k] + "\", \"" + genotype + "\", \"" + environment + "\", method = \"regression\"), silent=TRUE)";
							String outTestStability1 = "capture.output(cat(\"\nSTABILITY ANALYSIS USING FINLAY-WILKINSON MODEL:\n\n\"),file=\"" + outFileName + "\",append = TRUE)";
							String outTestStability1b = "capture.output(funcStability1[[1]][[1]]$stability,file=\"" + outFileName + "\",append = TRUE)";
													
							rEngine.eval(funcStability1);
							rEngine.eval(outTestStability1);
							System.out.println(funcStability1);
							
							String runSuccessStab = rEngine.eval("class(funcStability1)").asString();
							if (runSuccessStab != null && runSuccessStab.equals("try-error")) {	
								System.out.println("stability reg: error");
								String checkError = "msg <- trimStrings(strsplit(funcStability1, \":\")[[1]])";
								String checkError2 = "msg <- trimStrings(paste(strsplit(msg, \"\\n\")[[length(msg)]], collapse = \" \"))";
								String checkError3 ="msg <- gsub(\"\\\"\", \"\", msg)";
								String checkError4 = "capture.output(cat(\"*** \nERROR in stability.analysis function:\\n  \",msg, \"\n***\n\n\", sep = \"\"), file=\"" + outFileName + "\",append = TRUE)";
								rEngine.eval(checkError);
								rEngine.eval(checkError2);
								rEngine.eval(checkError3);
								rEngine.eval(checkError4);
							}
							else {
								rEngine.eval(outTestStability1b);
								rEngine.eval(outSpace);
							}
						} else {
							String outRemark = "capture.output(cat(\"\nSTABILITY ANALYSIS USING FINLAY-WILKINSON MODEL:\n\"),file=\"" + outFileName + "\",append = TRUE)";
							String outRemark2 = "capture.output(cat(\"***This is not done. The environment factor should have at least five levels.***\n\n\"),file=\"" + outFileName + "\",append = TRUE)";
							
							rEngine.eval(outRemark);
							rEngine.eval(outRemark2);
						}
					}
					
					if (stabilityShukla) {
						//optional output; the number of environment levels should be at least 5: Stability Analysis using Shukla 
						if (environmentLevels.length > 4) {
							String funcStability2 = "funcStability2 <- try(stability.analysis(dataRecoded[[" + (k+1) + "]]$data, \"" + respvar[k] + "\", \"" + genotype + "\", \"" + environment + "\", method = \"shukla\"), silent=TRUE)";
							String outTestStability2 = "capture.output(cat(\"\nSTABILITY ANALYSIS USING SHUKLA'S MODEL:\n\n\"),file=\"" + outFileName + "\",append = TRUE)";
							String outTestStability2b = "capture.output(funcStability2[[1]][[1]]$stability,file=\"" + outFileName + "\",append = TRUE)";
							
							System.out.println(funcStability2);
							rEngine.eval(funcStability2);
							rEngine.eval(outTestStability2);
							
							String runSuccessStab = rEngine.eval("class(funcStability2)").asString();
							if (runSuccessStab != null && runSuccessStab.equals("try-error")) {	
								System.out.println("stability shukla: error");
								String checkError = "msg <- trimStrings(strsplit(funcStability2, \":\")[[1]])";
								String checkError2 = "msg <- trimStrings(paste(strsplit(msg, \"\\n\")[[length(msg)]], collapse = \" \"))";
								String checkError3 ="msg <- gsub(\"\\\"\", \"\", msg)";
								String checkError4 = "capture.output(cat(\"*** \nERROR in stability.analysis function:\\n  \",msg, \"\n***\n\n\", sep = \"\"), file=\"" + outFileName + "\",append = TRUE)";
								rEngine.eval(checkError);
								rEngine.eval(checkError2);
								rEngine.eval(checkError3);
								rEngine.eval(checkError4);
							}
							else {
								rEngine.eval(outTestStability2b);
								rEngine.eval(outSpace);
							}
						} else {
							String outRemark = "capture.output(cat(\"\nSTABILITY ANALYSIS USING SHUKLA'S MODEL:\n\"),file=\"" + outFileName + "\",append = TRUE)";
							String outRemark2 = "capture.output(cat(\"***This is not done. The environment factor should have at least five levels.***\n\n\"),file=\"" + outFileName + "\",append = TRUE)";
							
							rEngine.eval(outRemark);
							rEngine.eval(outRemark2);
						}
					}
					
					if (ammi) {
						//optional output; the number of environment levels should be at least 3: AMMI Biplot
						if (environmentLevels.length > 2) {
							String ammiOut = "ammiOut <- try(ammi.analysis(dataRecoded[[" + (k+1) + "]]$data[,match(\"CodedEnv\", names(dataRecoded[[" + (k+1) + "]]$data))], dataRecoded[[" + (k+1) + "]]$data[,match(\"CodedGeno\", names(dataRecoded[[" + (k+1) + "]]$data))], " + repValue[k] + ", dataRecoded[[" + (k+1) + "]]$data[,match(\"" + respvar[k] + "\", names(dataRecoded[[" + (k+1) + "]]$data))], " + mseValue[k] + ", number = FALSE, graph = \"biplot\", yVar = \"" + respvar[k] +"\"), silent=TRUE)"; 
							String outAmmi1 = "capture.output(cat(\"\nAMMI ANALYSIS:\n\"),file=\"" + outFileName + "\",append = TRUE)";
							String outAmmi2 = "capture.output(cat(\"Percentage of Total Variation Accounted for by the Principal Components: \n\n\"),file=\"" + outFileName + "\",append = TRUE)";;
							rEngine.eval(ammiOut);
							rEngine.eval(outAmmi1);
							System.out.println(ammiOut);
							
							String runSuccessAmmi = rEngine.eval("class(ammiOut)").asString();
							if (runSuccessAmmi != null && runSuccessAmmi.equals("try-error")) {	
								System.out.println("ammi: error");
								String checkError = "msg <- trimStrings(strsplit(ammiOut, \":\")[[1]])";
								String checkError2 = "msg <- trimStrings(paste(strsplit(msg, \"\\n\")[[length(msg)]], collapse = \" \"))";
								String checkError3 ="msg <- gsub(\"\\\"\", \"\", msg)";
								String checkError4 = "capture.output(cat(\"*** \nERROR in ammi.analysis function:\\n  \",msg, \"\n***\n\n\", sep = \"\"), file=\"" + outFileName + "\",append = TRUE)";
								rEngine.eval(checkError);
								rEngine.eval(checkError2);
								rEngine.eval(checkError3);
								rEngine.eval(checkError4);
							} else {
								String outAmmi3 = "capture.output(ammiOut$analysis,file=\"" + outFileName + "\",append = TRUE)";
								rEngine.eval(outAmmi2);
								rEngine.eval(outAmmi3);
								rEngine.eval(outSpace);
								
							}
						} else {
							String outRemark = "capture.output(cat(\"\nAMMI ANALYSIS:\n\"),file=\"" + outFileName + "\",append = TRUE)";
							String outRemark2 = "capture.output(cat(\"***This is not done. The environment factor should have at least three levels.***\n\n\n\"),file=\"" + outFileName + "\",append = TRUE)";
							
							rEngine.eval(outRemark);
							rEngine.eval(outRemark2);
						}
					}
					
					if (gge) {
						// gge analysis
						if (environmentLevels.length > 2) {
							//f=0.5
							String ggeOut = "ggeOut <- try(gge.analysis(dataRecoded[[" + (k+1) + "]]$data[,match(\"CodedEnv\", names(dataRecoded[[" + (k+1) + "]]$data))], dataRecoded[[" + (k+1) + "]]$data[,match(\"CodedGeno\", names(dataRecoded[[" + (k+1) + "]]$data))], " + repValue[k] + ", dataRecoded[[" + (k+1) + "]]$data[,match(\"" + respvar[k] + "\", names(dataRecoded[[" + (k+1) + "]]$data))], " + mseValue[k] + ", number = FALSE, graph = \"biplot\", yVar = \"" + respvar[k] +"\", f=0.5), silent=TRUE)";
							String outAmmi1 = "capture.output(cat(\"\nGGE ANALYSIS:\n\"),file=\"" + outFileName + "\",append = TRUE)";
							String outAmmi2 = "capture.output(cat(\"Percentage of Total Variation Accounted for by the Principal Components: \n\n\"),file=\"" + outFileName + "\",append = TRUE)";;
							rEngine.eval(ggeOut);
							rEngine.eval(outAmmi1);
							System.out.println(ggeOut);
							
							String runSuccessAmmi = rEngine.eval("class(ggeOut)").asString();
							if (runSuccessAmmi != null && runSuccessAmmi.equals("try-error")) {	
								System.out.println("gge1: error");
								String checkError = "msg <- trimStrings(strsplit(ggeOut, \":\")[[1]])";
								String checkError2 = "msg <- trimStrings(paste(strsplit(msg, \"\\n\")[[length(msg)]], collapse = \" \"))";
								String checkError3 ="msg <- gsub(\"\\\"\", \"\", msg)";
								String checkError4 = "capture.output(cat(\"*** \nERROR in gge.analysis2 function (f=0.5):\\n  \",msg, \"\n***\n\n\", sep = \"\"), file=\"" + outFileName + "\",append = TRUE)";
								rEngine.eval(checkError);
								rEngine.eval(checkError2);
								rEngine.eval(checkError3);
								rEngine.eval(checkError4);
							} else {
								
								String outAmmi3 = "capture.output(ggeOut$analysis,file=\"" + outFileName + "\",append = TRUE)";
								rEngine.eval(outAmmi2);
								rEngine.eval(outAmmi3);
								rEngine.eval(outSpace);
							}
							
							//f=0
							String ggeOut2 = "ggeOut2 <- try(gge.analysis(dataRecoded[[" + (k+1) + "]]$data[,match(\"CodedEnv\", names(dataRecoded[[" + (k+1) + "]]$data))], dataRecoded[[" + (k+1) + "]]$data[,match(\"CodedGeno\", names(dataRecoded[[" + (k+1) + "]]$data))], " + repValue[k] + ", dataRecoded[[" + (k+1) + "]]$data[,match(\"" + respvar[k] + "\", names(dataRecoded[[" + (k+1) + "]]$data))], " + mseValue[k] + ", number = FALSE, graph = \"biplot\", yVar = \"" + respvar[k] +"\", f=0), silent=TRUE)"; 
							rEngine.eval(ggeOut2);
							System.out.println(ggeOut2);
							
							String runSuccessAmmi2 = rEngine.eval("class(ggeOut2)").asString();
							if (runSuccessAmmi2 != null && runSuccessAmmi2.equals("try-error")) {	
								System.out.println("gge2: error");
								String checkError = "msg <- trimStrings(strsplit(ggeOut2, \":\")[[1]])";
								String checkError2 = "msg <- trimStrings(paste(strsplit(msg, \"\\n\")[[length(msg)]], collapse = \" \"))";
								String checkError3 ="msg <- gsub(\"\\\"\", \"\", msg)";
								String checkError4 = "capture.output(cat(\"*** \nERROR in gge.analysis2 function (f=0):\\n  \",msg, \"\n***\n\n\", sep = \"\"), file=\"" + outFileName + "\",append = TRUE)";
								rEngine.eval(checkError);
								rEngine.eval(checkError2);
								rEngine.eval(checkError3);
								rEngine.eval(checkError4);
							} 
							
							//f=1
							String ggeOut3 = "ggeOut3 <- try(gge.analysis(dataRecoded[[" + (k+1) + "]]$data[,match(\"CodedEnv\", names(dataRecoded[[" + (k+1) + "]]$data))], dataRecoded[[" + (k+1) + "]]$data[,match(\"CodedGeno\", names(dataRecoded[[" + (k+1) + "]]$data))], " + repValue[k] + ", dataRecoded[[" + (k+1) + "]]$data[,match(\"" + respvar[k] + "\", names(dataRecoded[[" + (k+1) + "]]$data))], " + mseValue[k] + ", number = FALSE, graph = \"biplot\", yVar = \"" + respvar[k] +"\", f=1), silent=TRUE)";
							rEngine.eval(ggeOut3);
							System.out.println(ggeOut3);
							
							String runSuccessAmmi3 = rEngine.eval("class(ggeOut3)").asString();
							if (runSuccessAmmi3 != null && runSuccessAmmi3.equals("try-error")) {	
								System.out.println("gge2: error");
								String checkError = "msg <- trimStrings(strsplit(ggeOut2, \":\")[[1]])";
								String checkError2 = "msg <- trimStrings(paste(strsplit(msg, \"\\n\")[[length(msg)]], collapse = \" \"))";
								String checkError3 ="msg <- gsub(\"\\\"\", \"\", msg)";
								String checkError4 = "capture.output(cat(\"*** \nERROR in gge.analysis2 function (f=1):\\n  \",msg, \"\n***\n\n\", sep = \"\"), file=\"" + outFileName + "\",append = TRUE)";
								rEngine.eval(checkError);
								rEngine.eval(checkError2);
								rEngine.eval(checkError3);
								rEngine.eval(checkError4);
							}
						} else {
							String outRemark = "capture.output(cat(\"\nGGE ANALYSIS:\n\"),file=\"" + outFileName + "\",append = TRUE)";
							String outRemark2 = "capture.output(cat(\"***This is not done. The environment factor should have at least three levels.***\n\n\n\"),file=\"" + outFileName + "\",append = TRUE)";
							
							rEngine.eval(outRemark);
							rEngine.eval(outRemark2);
						}
					}
					
					//if levels of Geno and Env are recoded, display new code for genotype and environment levels
					String recodedLevels = rEngine.eval("dataRecoded[[" + (k+1) + "]]$recodedLevels").asBool().toString();
					
					System.out.println("recodedLevels: " + recodedLevels);
					
					if (recodedLevels.equals("TRUE")) {
						//display new code for genotype and environment levels
						String outLegends = "capture.output(cat(\"\nCODES USED IN GRAPHS:\n\n\"),file=\"" + outFileName + "\",append = TRUE)";
						String outLegends2 = "capture.output(dataRecoded[[" + (k+1) + "]]$newCodingGeno,file=\"" + outFileName + "\",append = TRUE)";
						String outLegends3 = "capture.output(dataRecoded[[" + (k+1) + "]]$newCodingEnv,file=\"" + outFileName + "\",append = TRUE)";
						rEngine.eval(outLegends);
						rEngine.eval(outLegends2);
						rEngine.eval(outSpace);
						rEngine.eval(outLegends3);
						rEngine.eval(outSpace);
						rEngine.eval(outSpace);
					} else {
						rEngine.eval(outSpace);
					}
					
					//create response plots
					String responsePlot1 = "dataCoded <- dataRecoded[[" + (k+1) + "]]$data";
					String responsePlot2 = "nlevelsEnv <- dataRecoded[[" + (k+1) + "]]$nlevelsEnv";
					String responsePlot3 = "nlevelsGeno <- dataRecoded[[" + (k+1) + "]]$nlevelsGeno";
					String responsePlot4 = "resPlot1 <- try(GraphLine(data=dataCoded, outputPath=\"" + resultFolderPath + "\", yVars =c(\"" + respvar[k] + "\"), xVar =c(\"CodedGeno\"), lineVars =c(\"CodedEnv\"), mTitle =\"Response Plot of " + respvar[k] + "\", yAxisLab =c(\"" + respvar[k] + "\"), xAxisLab =\"" + genotype + "\", yMinValue = c(NA), yMaxValue = c(NA), axisLabelStyle = 2, byVar = NULL, plotCol = c(1:nlevelsEnv), showLineLabels =TRUE, showLeg = FALSE, boxed = TRUE, linePtTypes=rep(\"b\", nlevelsEnv), lineTypes=rep(1, nlevelsEnv), lineWidths=rep(1, nlevelsEnv), pointChars=rep(\"  \", nlevelsEnv), pointCharSizes=rep(1, nlevelsEnv), multGraphs =FALSE), silent = TRUE)";
					String responsePlot5 = "resPlot2 <- try(GraphLine(data=dataCoded, outputPath=\"" + resultFolderPath + "\", yVars =c(\"" + respvar[k] + "\"), xVar =c(\"CodedEnv\"), lineVars =c(\"CodedGeno\"), mTitle =\"Response Plot of " + respvar[k] + "\", yAxisLab =c(\"" + respvar[k] + "\"), xAxisLab =\"" + environment + "\", yMinValue = c(NA), yMaxValue = c(NA), axisLabelStyle = 2, byVar = NULL, plotCol = c(1:nlevelsGeno), showLineLabels =TRUE, showLeg = FALSE, boxed = TRUE, linePtTypes=rep(\"b\", nlevelsGeno), lineTypes=rep(1, nlevelsGeno), lineWidths=rep(1, nlevelsGeno), pointChars=rep(\"  \", nlevelsGeno), pointCharSizes=rep(1, nlevelsGeno), multGraphs =FALSE), silent = TRUE)";
					
					System.out.println(responsePlot1);
					System.out.println(responsePlot2);
					System.out.println(responsePlot3);
					System.out.println(responsePlot4);
					System.out.println(responsePlot5);
					
					rEngine.eval(responsePlot1);
					rEngine.eval(responsePlot2);
					rEngine.eval(responsePlot3);
					rEngine.eval(responsePlot4);
					rEngine.eval(responsePlot5);
					
					String runSuccessPlot1 = rEngine.eval("class(resPlot1)").asString();
					if (runSuccessPlot1 != null && runSuccessPlot1.equals("try-error")) {	
						System.out.println("response plot geno: error");
						String checkError = "msg <- trimStrings(strsplit(resPlot1, \":\")[[1]])";
						String checkError2 = "msg <- trimStrings(paste(strsplit(msg, \"\\n\")[[length(msg)]], collapse = \" \"))";
						String checkError3 ="msg <- gsub(\"\\\"\", \"\", msg)";
						String checkError4 = "capture.output(cat(\"*** \nERROR in GraphLine function (geno):\\n  \",msg, \"\n***\n\n\", sep = \"\"), file=\"" + outFileName + "\",append = TRUE)";
						rEngine.eval(checkError);
						rEngine.eval(checkError2);
						rEngine.eval(checkError3);
						rEngine.eval(checkError4);
					}
					
					String runSuccessPlot2 = rEngine.eval("class(resPlot2)").asString();
					if (runSuccessPlot2 != null && runSuccessPlot2.equals("try-error")) {	
						System.out.println("response plot env: error");
						String checkError = "msg <- trimStrings(strsplit(resPlot2, \":\")[[1]])";
						String checkError2 = "msg <- trimStrings(paste(strsplit(msg, \"\\n\")[[length(msg)]], collapse = \" \"))";
						String checkError3 ="msg <- gsub(\"\\\"\", \"\", msg)";
						String checkError4 = "capture.output(cat(\"*** \nERROR in GraphLine function (env):\\n  \",msg, \"\n***\n\n\", sep = \"\"), file=\"" + outFileName + "\",append = TRUE)";
						rEngine.eval(checkError);
						rEngine.eval(checkError2);
						rEngine.eval(checkError3);
						rEngine.eval(checkError4);
					}
					
				}//end of for loop for respvars 
				
				rEngine.eval(outSpace);
				rEngine.eval(sep2);
			}
			
			rEngineEnd();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	@Override
	public void doStabilityModels (String dataFileName, String outFileName, String[] respvar, String environment, String genotype, boolean stabilityFinlay, boolean stabilityShukla) {
		
		String respvarVector= inputTransform.createRVector(respvar);
		String doFinlay = new Boolean(stabilityFinlay).toString().toUpperCase();
		String doShukla = new Boolean(stabilityShukla).toString().toUpperCase();
				
		try {
			String readData = "data <- read.csv(\"" + dataFileName + "\", header = TRUE, na.strings = c(\"NA\",\".\",\" \",\"\"), blank.lines.skip=TRUE, sep = \",\")";
			System.out.println(readData);
			rEngine.eval(readData);
			
			String runSuccessData = rEngine.eval("data").asString();
			
			if (runSuccessData != null && runSuccessData.equals("notRun")) {	
				System.out.println("error");
				rEngine.eval("capture.output(cat(\"\n***Error reading data.***\n\"),file=\"" + outFileName + "\",append = FALSE)"); 
			} else {
				String callStabilityTest = "result <- try(stabilityTest(data, outFileName=\"" + outFileName + "\", respvar=" + respvarVector + ", environment=\"" + environment + "\", genotype=\"" + genotype + "\", useFinlay = " + doFinlay + ", useShukla = " + doShukla + "), silent=TRUE)";
				System.out.println(callStabilityTest);
				rEngine.eval(callStabilityTest);
			}		
			
			rEngineEnd();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void doMultiplicativeModels (String dataFileName, String outFileName, String resultFolderPath, String[] respvar, String environment, String genotype, String[] numberOfReps, String[] residualVariances, boolean responsePlot, boolean doAMMI, boolean biplotPC12, boolean biplotPC13, boolean biplotPC23, 
            boolean ammi1Biplot, boolean adaptationMap, boolean doGGE, boolean graphSymmetricView, boolean graphEnvironmentView, boolean graphGenotypicView) {
		
		String respvarVector= inputTransform.createRVector(respvar);
		String numberOfRepsVector= inputTransform.createRVector(numberOfReps);
		String residualVariancesVector= inputTransform.createRVector(residualVariances);
		String doResponsePlot = new Boolean(responsePlot).toString().toUpperCase();
		String doAMMIAnalysis = new Boolean(doAMMI).toString().toUpperCase();
		String dobiplotPC12 = new Boolean(biplotPC12).toString().toUpperCase();
		String dobiplotPC13 = new Boolean(biplotPC13).toString().toUpperCase();
		String dobiplotPC23 = new Boolean(biplotPC23).toString().toUpperCase();
		String doAmmi1Biplot = new Boolean(ammi1Biplot).toString().toUpperCase();
		String doAdaptationMap = new Boolean(adaptationMap).toString().toUpperCase();
		String doGGEAnalysis = new Boolean(doGGE).toString().toUpperCase();
		String doGraphSymmetricView = new Boolean(graphSymmetricView).toString().toUpperCase();
		String doGraphEnvironmentView = new Boolean(graphEnvironmentView).toString().toUpperCase();
		String doGraphGenotypicView = new Boolean(graphGenotypicView).toString().toUpperCase();
				
		try {
			String setWd="setwd(\"" + resultFolderPath + "\")";
			String readData = "data <- read.csv(\"" + dataFileName + "\", header = TRUE, na.strings = c(\"NA\",\".\",\" \",\"\"), blank.lines.skip=TRUE, sep = \",\")";
			System.out.println(setWd);
			System.out.println(readData);
			rEngine.eval(setWd);
			rEngine.eval(readData);
			
			String runSuccessData = rEngine.eval("data").asString();
			
			if (runSuccessData != null && runSuccessData.equals("notRun")) {	
				System.out.println("error");
				rEngine.eval("capture.output(cat(\"\n***Error reading data.***\n\"),file=\"" + outFileName + "\",append = FALSE)"); 
			} else {
				String callMultiplicativeTest = "result <- try(multiplicativeModels(data, outFileName=\"" + outFileName + "\", respvar=" + respvarVector + ", environment=\"" + environment + "\", genotype=\"" + genotype + "\", numberOfReps = " + numberOfRepsVector + ", residualVariances = " + residualVariancesVector + 
						", responsePlot = "+ doResponsePlot +",  doAMMI = "+ doAMMIAnalysis +", biplotPC12 = "+ dobiplotPC12 +", biplotPC13 = "+ dobiplotPC13 +", biplotPC23 = "+ dobiplotPC23 +", ammi1 = "+ doAmmi1Biplot +", adaptMap = "+ doAdaptationMap +", doGGE = "+ doGGEAnalysis +
						", graphSym = "+ doGraphSymmetricView +", graphEnv = "+ doGraphEnvironmentView +", graphGeno = "+ doGraphGenotypicView +"), silent=TRUE)";
				System.out.println(callMultiplicativeTest);
				rEngine.eval(callMultiplicativeTest);
			}		
			
			rEngineEnd();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void doMultiplicativeModelsVersion2 (String dataFileName, String outFileName, String resultFolderPath, String[] respvar, String environment, String genotype, String[] numberOfReps, String[] residualVariances, boolean responsePlot, boolean doAMMI, boolean biplotPC12, boolean biplotPC13, boolean biplotPC23, 
            boolean ammi1Biplot, boolean adaptationMap, boolean doGGE, boolean graphSymmetricView, boolean graphEnvironmentView, boolean graphGenotypicView) {
		
		String respvarVector= inputTransform.createRVector(respvar);
		String numberOfRepsVector= inputTransform.createRNumVector(numberOfReps);
		String residualVariancesVector= inputTransform.createRNumVector(residualVariances);
		String doResponsePlot = new Boolean(responsePlot).toString().toUpperCase();
		String doAMMIAnalysis = new Boolean(doAMMI).toString().toUpperCase();
		String dobiplotPC12 = new Boolean(biplotPC12).toString().toUpperCase();
		String dobiplotPC13 = new Boolean(biplotPC13).toString().toUpperCase();
		String dobiplotPC23 = new Boolean(biplotPC23).toString().toUpperCase();
		String doAmmi1Biplot = new Boolean(ammi1Biplot).toString().toUpperCase();
		String doAdaptationMap = new Boolean(adaptationMap).toString().toUpperCase();
		String doGGEAnalysis = new Boolean(doGGE).toString().toUpperCase();
		String doGraphSymmetricView = new Boolean(graphSymmetricView).toString().toUpperCase();
		String doGraphEnvironmentView = new Boolean(graphEnvironmentView).toString().toUpperCase();
		String doGraphGenotypicView = new Boolean(graphGenotypicView).toString().toUpperCase();
				
		try {
			String setWd="setwd(\"" + resultFolderPath + "\")";
			String readData = "data <- read.csv(\"" + dataFileName + "\", header = TRUE, na.strings = c(\"NA\",\".\",\" \",\"\"), blank.lines.skip=TRUE, sep = \",\")";
			System.out.println(setWd);
			System.out.println(readData);
			rEngine.eval(setWd);
			rEngine.eval(readData);
			
			String runSuccessData = rEngine.eval("data").asString();
			
			if (runSuccessData != null && runSuccessData.equals("notRun")) {	
				System.out.println("error");
				rEngine.eval("capture.output(cat(\"\n***Error reading data.***\n\"),file=\"" + outFileName + "\",append = FALSE)"); 
			} else {
				String callStabilityTest = "result <- try(multiplicativeModels(data, outFileName=\"" + outFileName + "\", respvar=" + respvarVector + ", environment=\"" + environment + "\", genotype=\"" + genotype + "\", numberOfReps = " + numberOfRepsVector + ", residualVariances = " + residualVariancesVector + 
						", responsePlot = "+ doResponsePlot +",  doAMMI = "+ doAMMIAnalysis +", biplotPC12 = "+ dobiplotPC12 +", biplotPC13 = "+ dobiplotPC13 +", biplotPC23 = "+ dobiplotPC23 +", ammi1 = "+ doAmmi1Biplot +", adaptMap = "+ doAdaptationMap +", doGGE = "+ doGGEAnalysis +
						", graphSym = "+ doGraphSymmetricView +", graphEnv = "+ doGraphEnvironmentView +", graphGeno = "+ doGraphGenotypicView +"), silent=TRUE)";
				System.out.println(callStabilityTest);
				rEngine.eval(callStabilityTest);
			}		
			
			rEngineEnd();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void doPlsRegression (String dataFileName, String outFileName, String resultFolderPath, String[] respvar, String environment, String genotype, String covariateData, String covariateEnvironment, String covariateGenotype) {
		
		String respvarVector= inputTransform.createRVector(respvar);
				
		try {
			String setWd="setwd(\"" + resultFolderPath + "\")";
			String readData = "data <- read.csv(\"" + dataFileName + "\", header = TRUE, na.strings = c(\"NA\",\".\",\" \",\"\"), blank.lines.skip=TRUE, sep = \",\")";
			System.out.println(setWd);
			System.out.println(readData);
			rEngine.eval(setWd);
			rEngine.eval(readData);
			
			String readData2 = "dataCov <- read.csv(\"" + covariateData + "\", header = TRUE, na.strings = c(\"NA\",\".\",\" \",\"\"), blank.lines.skip=TRUE, sep = \",\")";
			System.out.println(readData2);
			rEngine.eval(readData2);
			
			String runSuccessData = rEngine.eval("data").asString();
			String runSuccessData2 = rEngine.eval("dataCov").asString();
			
			if (runSuccessData != null && runSuccessData.equals("notRun")) {	
				System.out.println("error");
				rEngine.eval("capture.output(cat(\"\n***Error reading data.***\n\"),file=\"" + outFileName + "\",append = FALSE)"); 
			} else {
				if (runSuccessData2 != null && runSuccessData2.equals("notRun")) {
					System.out.println("error");
					rEngine.eval("capture.output(cat(\"\n***Error reading covariate data.***\n\"),file=\"" + outFileName + "\",append = FALSE)"); 
				} else {
					String callPlsRegression=null;
					if (covariateEnvironment.equals("NULL")) {
						callPlsRegression = "result <- try(plsRegression(data, outFileName=\"" + outFileName + "\", respvar=" + respvarVector + ", genotype=\"" + genotype + "\", environment=\"" + environment + "\", covariateData = dataCov, covariateEnvironment = " + covariateEnvironment + ", covariateGenotype = \"" + covariateGenotype +"\"), silent=TRUE)";
					}
					if (covariateGenotype.equals("NULL")) {
						callPlsRegression = "result <- try(plsRegression(data, outFileName=\"" + outFileName + "\", respvar=" + respvarVector + ", genotype=\"" + genotype + "\", environment=\"" + environment + "\", covariateData = dataCov, covariateEnvironment = \"" + covariateEnvironment + "\", covariateGenotype = " + covariateGenotype +"), silent=TRUE)";
					}
					System.out.println(callPlsRegression);
					rEngine.eval(callPlsRegression);
				}
				
			}		
			
			rEngineEnd();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void doFactorialRegression (String dataFileName, String outFileName, String resultFolderPath, String[] respvar, String environment, String genotype, String covariateData, String covariateEnvironment, String covariateGenotype) {
		
		String respvarVector= inputTransform.createRVector(respvar);
				
		try {
			String setWd="setwd(\"" + resultFolderPath + "\")";
			String readData = "data <- read.csv(\"" + dataFileName + "\", header = TRUE, na.strings = c(\"NA\",\".\",\" \",\"\"), blank.lines.skip=TRUE, sep = \",\")";
			System.out.println(setWd);
			System.out.println(readData);
			rEngine.eval(setWd);
			rEngine.eval(readData);
			
			String readData2 = "dataCov <- read.csv(\"" + covariateData + "\", header = TRUE, na.strings = c(\"NA\",\".\",\" \",\"\"), blank.lines.skip=TRUE, sep = \",\")";
			System.out.println(readData2);
			rEngine.eval(readData2);
			
			String runSuccessData = rEngine.eval("data").asString();
			String runSuccessData2 = rEngine.eval("dataCov").asString();
			
			if (runSuccessData != null && runSuccessData.equals("notRun")) {	
				System.out.println("error");
				rEngine.eval("capture.output(cat(\"\n***Error reading data.***\n\"),file=\"" + outFileName + "\",append = FALSE)"); 
			} else {
				if (runSuccessData2 != null && runSuccessData2.equals("notRun")) {
					System.out.println("error");
					rEngine.eval("capture.output(cat(\"\n***Error reading covariate data.***\n\"),file=\"" + outFileName + "\",append = FALSE)"); 
				} else {
					String callFactorialRegression=null;
					if (covariateEnvironment.equals("NULL")) {
						callFactorialRegression = "result <- try(factorialRegression(data, outFileName=\"" + outFileName + "\", respvar=" + respvarVector + ", genotype=\"" + genotype + "\", environment=\"" + environment + "\", covariateData = dataCov, covariateEnvironment = " + covariateEnvironment + ", covariateGenotype = \"" + covariateGenotype +"\"), silent=TRUE)";
					}
					if (covariateGenotype.equals("NULL")) {
						callFactorialRegression = "result <- try(factorialRegression(data, outFileName=\"" + outFileName + "\", respvar=" + respvarVector + ", genotype=\"" + genotype + "\", environment=\"" + environment + "\", covariateData = dataCov, covariateEnvironment = \"" + covariateEnvironment + "\", covariateGenotype = " + covariateGenotype +"), silent=TRUE)";
					}
					System.out.println(callFactorialRegression);
					rEngine.eval(callFactorialRegression);
				}
				
			}		
			
			rEngineEnd();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void doSelectionIndex(String dataFileName, String outFileName, String resultFolderPath, int selectionIndex, int designIndex, boolean basisCorrelation, String weightsFileName, String markersFileName,
			String qtlFileName, int percentSelected) {
		try {

			String selectionIndexUsed = new String();
			switch (selectionIndex) {
				case 0: selectionIndexUsed = "Smith Selection Index"; break;
				case 1: selectionIndexUsed = "Eigen Selection Index Method (ESIM)"; break;
				case 2: selectionIndexUsed = "Restrictive Kempthorne and Nordskog Selection Index"; break;
				case 3: selectionIndexUsed = "Restrictive Eigen Selection Index Method"; break;
				case 4: selectionIndexUsed = "Lande and Thompson Selection Index"; break;
				case 5: selectionIndexUsed = "Molecular Eigen Selection Index Method"; break;
				default: selectionIndexUsed = "Smith Selection Index"; break;
			}
			
			String designUsed = new String();
			String design = new String();
			switch (designIndex) {
				case 0: {
					design = "lattice";
					designUsed = "Lattice"; 
					break;
				}
				case 1: {
					design = "rcb";
					designUsed = "Randomized Complete Block (RCB)"; 
					break;
				}
			}
			
			String basisSI = null;
			if (basisCorrelation) basisSI = "TRUE";
			else basisSI = "FALSE";
			
			String setWd = "setwd(\"" + resultFolderPath + "\")";
			String usedData = "capture.output(cat(\"\nDATA FILE: " + dataFileName + "\n\",file=\"" + outFileName + "\"))";

			String usedSelInd = "capture.output(cat(\"\n" + selectionIndexUsed + "\n\"),file=\"" + outFileName + "\",append = TRUE)";
			String usedDesign = "capture.output(cat(\"\nDESIGN: " + designUsed + "\n\n\"),file=\"" + outFileName + "\",append = TRUE)";
			String sep = "capture.output(cat(\"------------------------------\n\"),file=\"" + outFileName + "\",append = TRUE)";

			rEngine.eval(setWd);
			rEngine.eval(usedData);
			rEngine.eval(usedSelInd);
			rEngine.eval(usedDesign);
			
			System.out.println(setWd);

			// OUTPUT
			String funcSelInd = null;
			switch (selectionIndex) {
			case 0: {
				funcSelInd = "index <- tryCatch(SmithIndex_2(\"" + dataFileName + "\", \"" + weightsFileName + "\", " + percentSelected + ", \"" + design + "\", corr = " + basisSI + ", rawdata = TRUE), error=function(err) \"notRun\")";
				break;
			}
			case 1: {
				funcSelInd = "index <- tryCatch(ESIMIndex_2(\"" + dataFileName + "\", \"" + weightsFileName + "\", " + percentSelected + ", \"" + design + "\", corr = " + basisSI + ", rawdata = TRUE), error=function(err) \"notRun\")";
				break;
			}
			case 2: {
				funcSelInd = "index <- tryCatch(KNIndex_2(\"" + dataFileName + "\", \"" + weightsFileName + "\", " + percentSelected + ", \"" + design + "\", corr = " + basisSI + ", rawdata = TRUE), error=function(err) \"notRun\")";
				break;
				
			}
			case 3: {
				funcSelInd = "index <- tryCatch(RESIMIndex_2(\"" + dataFileName + "\", \"" + weightsFileName + "\", " + percentSelected + ", \"" + design + "\", corr = " + basisSI + ", rawdata = TRUE), error=function(err) \"notRun\")";
				break;
			}	
			case 4: {
				funcSelInd = "index <- tryCatch(LTIndex_2(\"" + dataFileName + "\", \"" + weightsFileName + "\", " + percentSelected + ", \"" + design + "\", corr = " + basisSI + ", rawdata = TRUE, \"" + markersFileName + "\", \"" + qtlFileName + "\"), error=function(err) \"notRun\")";			
				break;
			}
			case 5: {
				funcSelInd = "index <- tryCatch(MESIMIndex_2(\"" + dataFileName + "\", \"" + weightsFileName + "\", " + percentSelected + ", \"" + design + "\", corr = " + basisSI + ", rawdata = TRUE, \"" + markersFileName + "\", \"" + qtlFileName + "\"), error=function(err) \"notRun\")";
				break;
			}
			}

			rEngine.eval(funcSelInd);
			System.out.println(funcSelInd);

			String runSuccessSelInd = rEngine.eval("index").asString();
			if (runSuccessSelInd != null && runSuccessSelInd.equals("notRun")) {	
				System.out.println("error");
				rEngine.eval("capture.output(cat(\"\n***An error has occurred.***\n***Selection index not computed.***\n\"),file=\"" + outFileName + "\",append = TRUE)"); //append to output file?
			}
			else {
			
				//all output
	
				String matrixName = "COVARIANCE";
				if (basisCorrelation) matrixName = "CORRELATION";
	
				//geno cov/corr matrix
				String genoMatrix = "capture.output(cat(\"\nGENETIC " + matrixName + " MATRIX\n\n\"),file=\"" + outFileName + "\",append = TRUE)";
				String genoMatrix2 = "capture.output(index$MVGeno,file=\"" + outFileName + "\",append = TRUE)";
				
				rEngine.eval(genoMatrix);
				rEngine.eval(genoMatrix2);
				
				//pheno cov/corr matrix
				String phenoMatrix = "capture.output(cat(\"\n\nPHENOTYPIC " + matrixName + " MATRIX\n\n\"),file=\"" + outFileName + "\",append = TRUE)";
				String phenoMatrix2 = "capture.output(index$MVPheno,file=\"" + outFileName + "\",append = TRUE)";
				
				rEngine.eval(phenoMatrix);
				rEngine.eval(phenoMatrix2);
				
				//molecular cov matrix
				if (selectionIndex == 4 | selectionIndex == 5) {
					String moleCovMatrix = "capture.output(cat(\"\n\nMOLECULAR COVARIANCE MATRIX\n\n\"),file=\"" + outFileName + "\",append = TRUE)";
					String moleCovMatrix2 = "capture.output(index$MVMole,file=\"" + outFileName + "\",append = TRUE)";
					
					rEngine.eval(moleCovMatrix);
					rEngine.eval(moleCovMatrix2);
				}
				
				//CovIndBv
				String covIndBv = "capture.output(cat(\"\n\nCOVARIANCE BETWEEN SELECTION INDEX AND BREEDING VALUE: \",index$CovIndBV[[1]]),file=\"" + outFileName + "\",append = TRUE)";
				rEngine.eval(covIndBv);
	
	
				//VarInd
				String varInd = "capture.output(cat(\"\n\nVARIANCE OF THE SELECTION INDEX: \", index$VarInd[[1]]),file=\"" + outFileName + "\",append = TRUE)";
				rEngine.eval(varInd);
	
				//VarBv
				String varBv = "capture.output(cat(\"\n\nVARIANCE OF THE BREEDING VALUE: \", index$VarBV[[1]]),file=\"" + outFileName + "\",append = TRUE)";
				rEngine.eval(varBv);
				
				//CorrIndBv
				String corrIndBv = "capture.output(cat(\"\n\nCORRELATION BETWEEN SELECTION INDEX AND BREEDING VALUE: \", index$CorrIndBV[[1]]),file=\"" + outFileName + "\",append = TRUE)";
				rEngine.eval(corrIndBv);
	
				//selGen
				String selGenMatrix = "capture.output(cat(\"\n\nVALUES OF THE TRAITS, SELECTION INDEX, MEANS, GAINS FOR THE " + percentSelected + "% SELECTED INDIVIDUALS\n\n\"),file=\"" + outFileName + "\",append = TRUE)";
				String selGenMatrix2 = "capture.output(index$SelGen,file=\"" + outFileName + "\",append = TRUE)";
				
				rEngine.eval(selGenMatrix);
				rEngine.eval(selGenMatrix2);
	
				//allGen
				String allGenMatrix = "capture.output(cat(\"\n\nVALUES OF THE TRAITS AND THE SELECTION INDEX FOR ALL INDIVIDUALS\n\n\"),file=\"" + outFileName + "\",append = TRUE)";
				String allGenMatrix2 = "capture.output(index$AllGen,file=\"" + outFileName + "\",append = TRUE)";
				
				rEngine.eval(allGenMatrix);
				rEngine.eval(allGenMatrix2);
				rEngine.eval(sep);
				
				String funcSaveToCsv = "savePctSel <- tryCatch(write.csv(index$SelGen,file =\"" + resultFolderPath + "selectedGenotypes.csv\",row.names=T), error=function(err) \"notRun\")";
				rEngine.eval(funcSaveToCsv);
				
				String runSuccessSavePctSel = rEngine.eval("savePctSel").asString();
				if (runSuccessSavePctSel != null && runSuccessSavePctSel.equals("notRun")) {	
					System.out.println("error");
					rEngine.eval("capture.output(cat(\"\n***An error has occurred.***\n***Percentage selected not saved.***\n\"),file=\"" + outFileName + "\",append = TRUE)"); //append to output file?
				}

			}
			rEngineEnd();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void doQtl(String dataFileName, boolean isInputRawData, String outFileName, String resultFolderPath, String genotypicDataFileName, String mapFileName, int designIndex, String[] respvar, 
			String block, String rep, String row, String column, String genotype, String environment, String[] environmentLevels, String selectedEnvironmentLevel, 
			boolean heterozygousPresent, String crossType, String step, String windowSize, String minDistance, String qtlMethod, boolean thresholdLiJi, String thresholdNumericValue, 
			boolean estimatePlotMarkerMap, double allelicDiffThreshold, double cutOffMissingData, double significanceLevelChiSquare) {
		
		String respvarVector= inputTransform.createRVector(respvar);
		String [] respVarMean = new String[respvar.length];
		
		try {

			String dire3 = null;
			String environment_In = null;
			if (!environment.equals("NULL")) {
				environment_In = "\"" + environment + "\"";
			} else environment_In = "NULL";
			
			String[] envts = null;
			if (environmentLevels != null) {
				if (environmentLevels.length > 0) {
					envts = environmentLevels;
				} else { 
					envts = new String[1];
					envts[0] = "1"; 
				}
			}
			
			String specEnv = "NULL";
			int numEnvts = 1;
			if (!environment.equals("NULL")) {
				if (selectedEnvironmentLevel.equals("[ALL]")) numEnvts = envts.length;
			} else {
				numEnvts = 1;
			}
			
			//if input is raw data
			String designUsed = new String();
			String design = new String();
			boolean readingDataMeansSuccess=true;
			
			if (isInputRawData) {
				boolean printAllOutputFixed=true;
				
				String readData = "data <- tryCatch(read.csv(\"" + dataFileName + "\", header = TRUE, na.strings = c(\"NA\",\".\",\" \",\"\"), blank.lines.skip=TRUE, sep = \",\"), error=function(err) \"notRun\")";
				System.out.println(readData);
				rEngine.eval(readData);
				
				if (!selectedEnvironmentLevel.equals("[ALL]")) {
					String dire2 = "data<-tryCatch(data[which(data[match(\"" + environment + "\", names(data))] == \"" + selectedEnvironmentLevel + "\"),], error=function(err) \"notRun\")";
					System.out.println(dire2);
					rEngine.eval(dire2);
				}
				
				switch (designIndex) {
					case 0: {
						designUsed = "Randomized Complete Block (RCB)";
						design = "RCB";
						break;
					}
					case 1: {
						designUsed = "Augmented RCB"; 
						design = "AugRCB";
						break;
					}
					case 2: {
						designUsed = "Augmented Latin Square"; 
						design = "AugLS";
						break;
					}
					case 3: {
						designUsed = "Alpha-Lattice"; 
						design = "Alpha";
						break;
					}
					case 4: {
						designUsed = "Row-Column"; 
						design = "RowCol";
						break;
					}
					default: {
						designUsed = "Randomized Complete Block (RCB)"; 
						design = "RCB";
						break;
					}
				}
				
				String usedData = "capture.output(cat(\"\nDATA FILE: " + dataFileName + "\n\",file=\"" + outFileName + "\"))";
				String outFile = "capture.output(cat(\"\nSINGLE-ENVIRONMENT ANALYSIS\n\"),file=\"" + outFileName + "\",append = TRUE)";
				String usedDesign = "capture.output(cat(\"\nDESIGN: " + designUsed + "\n\n\"),file=\"" + outFileName + "\",append = TRUE)";
				
				rEngine.eval(usedData);
				rEngine.eval(outFile);
				rEngine.eval(usedDesign);
				
				String runSuccessData = rEngine.eval("data").asString();
				if (runSuccessData != null && runSuccessData.equals("notRun")) {	
					System.out.println("error");
					rEngine.eval("capture.output(cat(\"\n***Error reading data.***\n\"),file=\"" + outFileName + "\",append = FALSE)"); //append to output file?
				}
				
				String funcSeaFixed = null;
				String groupVars = null;
				if (design == "RCB" || design == "AugRCB"){
					funcSeaFixed = "ssa1 <- try(ssa.test(\"" + design + "\",data,"+ respvarVector + ",\"" + genotype + "\",\"" + block + "\",column=NULL, rep=NULL, env = " + environment_In+ ", is.random = FALSE), silent = TRUE)";
					groupVars = "c(\"" + genotype + "\", \"" + block + "\")";
				} else if (design == "AugLS") {
					funcSeaFixed = "ssa1 <- try(ssa.test(\"" + design + "\",data,"+ respvarVector + ",\"" + genotype + "\",\"" + row + "\",\"" + column + "\", rep=NULL, env = " + environment_In+ ", is.random = FALSE), silent = TRUE)";
					groupVars = "c(\"" + genotype + "\", \"" + row + "\", \"" + column + "\")";
				} else if (design == "Alpha") {
					funcSeaFixed = "ssa1 <- try(ssa.test(\"" + design + "\",data,"+ respvarVector + ",\"" + genotype + "\",\"" + block + "\",column=NULL,\"" + rep + "\", env = " + environment_In+ ", is.random = FALSE), silent = TRUE)";
					groupVars = "c(\"" + genotype + "\", \"" + block + "\", \"" + rep + "\")";
				} else if (design == "RowCol") {
					funcSeaFixed = "ssa1 <- try(ssa.test(\"" + design + "\",data,"+ respvarVector + ",\"" + genotype + "\",\"" + row + "\",\"" + column + "\",\"" + rep + "\", env = " + environment_In+ ", is.random = FALSE), silent = TRUE)";
					groupVars = "c(\"" + genotype + "\", \"" + rep + "\", \"" + row + "\", \"" + column + "\")";
				}

				System.out.println(funcSeaFixed);
				rEngine.eval(funcSeaFixed);
				
				String runSuccess = rEngine.eval("class(ssa1)").asString();
				if (runSuccess != null && runSuccess.equals("try-error")) {	
					System.out.println("ssa.test: error");
					String checkError = "msg <- trimStrings(strsplit(ssa1, \":\")[[1]])";
					String checkError2 = "msg <- trimStrings(paste(strsplit(msg, \"\\n\")[[length(msg)]], collapse = \" \"))";
					String checkError3 ="msg <- gsub(\"\\\"\", \"\", msg)";
					String checkError4 = "capture.output(cat(\"*** \nERROR in ssa.test function:\\n  \",msg, \"\n***\n\n\", sep = \"\"), file=\"" + outFileName + "\",append = TRUE)";
					rEngine.eval(checkError);
					rEngine.eval(checkError2);
					rEngine.eval(checkError3);
					rEngine.eval(checkError4);
				} else {
					//capture dataMeans
					dire3 = "dataMeans<-tryCatch(ssa1$meansse, error=function(err) \"notRun\")";
					rEngine.eval(dire3);
					System.out.println(dire3);
					
					String runSuccessDire3 = rEngine.eval("dataMeans").asString(); 
					if (runSuccessDire3 != null && runSuccessDire3.equals("notRun")) {
						System.out.println("error with capturing dataMeans");
						readingDataMeansSuccess=false;
					}
					
					for (int i = 0; i < respvar.length; i++) {
						respVarMean[i] = respvar[i]+ "_Mean";
					}
					
					//display results of SSA when input data is raw data
					
					
					String sep = "capture.output(cat(\"------------------------------\"),file=\"" + outFileName + "\",append = TRUE)";
					String sep2 = "capture.output(cat(\"==============================\n\"),file=\"" + outFileName + "\",append = TRUE)";
					String outspace = "capture.output(cat(\"\n\"),file=\"" + outFileName + "\",append = TRUE)"; 
					
					
					for (int k = 0; k < respvar.length; k++) {
						int i = k + 1; // 1-relative index;
						String respVarHead = "capture.output(cat(\"\nRESPONSE VARIABLE: " + respvar[k] + "\n\"),file=\"" + outFileName + "\",append = TRUE)";
						rEngine.eval(sep);
						rEngine.eval(respVarHead);
						rEngine.eval(sep);
						rEngine.eval(outspace);
						
						//default output: descriptive statistics			
						String funcDesc = "outDesc <- try(DescriptiveStatistics(data, \"" + respvar[k] + "\", \"" + environment + "\"), silent=TRUE)";
						System.out.println(funcDesc);
						rEngine.eval(funcDesc);
						
						String outDescStat = "capture.output(cat(\"DESCRIPTIVE STATISTICS:\n\n\"),file=\"" + outFileName + "\",append = TRUE)"; 
						String outDescStat2 = "capture.output(outDesc,file=\"" + outFileName + "\",append = TRUE)";
						
  						String runSuccessDescStat = rEngine.eval("class(outDesc)").asString();	
						if (runSuccessDescStat != null && runSuccessDescStat.equals("try-error")) {	
							System.out.println("desc stat: error");
							String checkError = "msg <- trimStrings(strsplit(outDesc, \":\")[[1]])";
							String checkError2 = "msg <- trimStrings(paste(strsplit(msg, \"\\n\")[[length(msg)]], collapse = \" \"))";
							String checkError3 ="msg <- gsub(\"\\\"\", \"\", msg)";
							String checkError4 = "capture.output(cat(\"*** \nERROR in DescriptiveStatistics function:\\n  \",msg, \"\n***\n\n\", sep = \"\"), file=\"" + outFileName + "\",append = TRUE)";
							rEngine.eval(checkError);
							rEngine.eval(checkError2);
							rEngine.eval(checkError3);
							rEngine.eval(checkError4);
						} 
						else {
							rEngine.eval(outspace);
							rEngine.eval(outDescStat);
							rEngine.eval(outDescStat2);
							rEngine.eval(outspace);
						}

						for (int m = 0; m < numEnvts; m++) { // no of envts or sites
							printAllOutputFixed=true;
							int j = m + 1; // 1-relative index;
							String envtHead = "capture.output(cat(\"\nANALYSIS FOR: "+ environment + "\", \" = \" ,ssa1$output[[" + i	+ "]]$site[[" + j + "]]$env,\"\n\"),file=\""+ outFileName + "\",append = TRUE)";
							rEngine.eval(sep);
							rEngine.eval(envtHead);
							rEngine.eval(sep);
							rEngine.eval(outspace);
							
							//check if the data has too many missing observation
							double nrowData=rEngine.eval("ssa1$output[[" + i + "]]$site[[" + j + "]]$responseRate").asDouble();
							if (nrowData < 0.80) {
								String allNAWarning = rEngine.eval("ssa1$output[[" + i + "]]$site[[" + j + "]]$manyNAWarning").asString();
								String printError1 = "capture.output(cat(\"***\\n\"), file=\"" + outFileName + "\",append = TRUE)";
								String printError2 = "capture.output(cat(\"ERROR:\\n\"), file=\"" + outFileName + "\",append = TRUE)";
								String printError3 = "capture.output(cat(\"" + allNAWarning + "\\n\"), file=\"" + outFileName + "\",append = TRUE)";
								
								rEngine.eval(outspace);
								rEngine.eval(printError1);
								rEngine.eval(printError2);
								rEngine.eval(printError3);
								rEngine.eval(printError1);
								rEngine.eval(outspace);
								rEngine.eval(outspace);
								printAllOutputFixed=false;
							} // end of if (nrowData < 0.80)
							
							if (printAllOutputFixed) {
								// default output: trial summary
								String funcTrialSum = "funcTrialSum <- try(class.information(" + groupVars + ",ssa1$output[[" + i + "]]$site[[" + j + "]]$data), silent=TRUE)";
								String trialSumHead = "capture.output(cat(\"\nDATA SUMMARY:\n\n\"),file=\"" + outFileName + "\",append = TRUE)";
								String trialObsRead = "capture.output(cat(\"Number of observations read: \", ssa1$output[["	+ i	+ "]]$site[[" + j + "]]$obsread[[1]],\"\n\"),file=\"" + outFileName + "\",append = TRUE)";
								String trialObsUsed = "capture.output(cat(\"Number of observations used: \", ssa1$output[["	+ i	+ "]]$site[[" + j + "]]$obsused[[1]],\"\n\n\"),file=\""	+ outFileName + "\",append = TRUE)";
								String trialSum = "capture.output(funcTrialSum,file=\"" + outFileName + "\",append = TRUE)";

								rEngine.eval(funcTrialSum);
		
								String runSuccessTS = rEngine.eval("class(funcTrialSum)").asString();
								if (runSuccessTS != null && runSuccessTS.equals("try-error")) {	
									System.out.println("class info: error");
									String checkError = "msg <- trimStrings(strsplit(funcTrialSum, \":\")[[1]])";
									String checkError2 = "msg <- trimStrings(paste(strsplit(msg, \"\\n\")[[length(msg)]], collapse = \" \"))";
									String checkError3 ="msg <- gsub(\"\\\"\", \"\", msg)";
									String checkError4 = "capture.output(cat(\"*** \nERROR in class.information function:\\n  \",msg, \"\n***\n\n\", sep = \"\"), file=\"" + outFileName + "\",append = TRUE)";
									rEngine.eval(checkError);
									rEngine.eval(checkError2);
									rEngine.eval(checkError3);
									rEngine.eval(checkError4);
								}
								else {
									rEngine.eval(trialSumHead);
									rEngine.eval(trialObsRead);
									rEngine.eval(trialObsUsed);
									rEngine.eval(trialSum);
									rEngine.eval(outspace);
								}
								
								
								// default output: variance components
								String outVarComp = "capture.output(cat(\"\nVARIANCE COMPONENTS TABLE:\n\n\"),file=\"" + outFileName + "\",append = TRUE)";
								String outVarComp2 = "capture.output(ssa1$output[[" + i + "]]$site[[" + j + "]]$varcomp.table,file=\"" + outFileName + "\",append = TRUE)";
	
								rEngine.eval(outVarComp);
								rEngine.eval(outVarComp2);
								rEngine.eval(outspace);

								
								// default output: test for genotypic effect
//								String outAnovaTable1 = "capture.output(ssa1$output[[" + i + "]]$site[[" + j + "]]$model.comparison,file=\"" + outFileName + "\",append = TRUE)";
								String outAnovaTable1 = "capture.output(cat(\"\nTESTING FOR THE SIGNIFICANCE OF GENOTYPIC EFFECT:\n\"),file=\"" + outFileName + "\",append = TRUE)";
								String outAnovaTable2 = "library(lmerTest)";
								String outAnovaTable3 = "model1b <- lmer(formula(ssa1$output[[" + i + "]]$site[[" + j + "]]$formula1), data = ssa1$output[[" + i + "]]$site[[" + j + "]]$data, REML = T)";
								String outAnovaTable4 = "a.table <- anova(model1b)";
								String outAnovaTable5 = "pvalue <- formatC(as.numeric(format(a.table[1,6], scientific=FALSE)), format=\"f\")";
								String outAnovaTable6 = "a.table<-cbind(round(a.table[,1:5], digits=4),pvalue)";
								String outAnovaTable7 = "colnames(a.table)<-c(\"Df\", \"Sum Sq\", \"Mean Sq\", \"F value\", \"Denom\", \"Pr(>F)\")";
								String outAnovaTable8 = "capture.output(cat(\"Analysis of Variance Table with Satterthwaite Denominator Df\n\"),file=\"" + outFileName + "\",append = TRUE)";
								String outAnovaTable9 = "capture.output(a.table,file=\"" + outFileName + "\",append = TRUE)";
								String outAnovaTable10 = "detach(\"package:lmerTest\")";
								
								rEngine.eval(outAnovaTable1);
								rEngine.eval(outAnovaTable2);
								rEngine.eval(outAnovaTable3);
								rEngine.eval(outAnovaTable4);
								rEngine.eval(outAnovaTable5);
								rEngine.eval(outAnovaTable6);
								rEngine.eval(outAnovaTable7);
								rEngine.eval(outspace);
								rEngine.eval(outAnovaTable8);
								rEngine.eval(outAnovaTable9);
								rEngine.eval(outspace);
								rEngine.eval(outAnovaTable10);
		
								// default output: test for genotypic effect
//								String outAnovaTable1b = "capture.output(cat(\"\nTESTING FOR THE SIGNIFICANCE OF GENOTYPIC EFFECT:\n\n\"),file=\"" + outFileName + "\",append = TRUE)";
//								String outAnovaTable2b = "library(pbkrtest)";
//								String outAnovaTable3b = "model1b <- lmer(formula(ssa1$output[[" + i + "]]$site[[" + j + "]]$formula1), data = ssa1$output[[" + i + "]]$site[[" + j + "]]$data, REML = T)";
//								String outAnovaTable4b = "model2b <- lmer(formula(ssa1$output[[" + i + "]]$site[[" + j + "]]$formula2), data = ssa1$output[[" + i + "]]$site[[" + j + "]]$data, REML = T)";
//								String outAnovaTable5b = "anova.table1 <- KRmodcomp(model1b, model2b)[[1]][1,]";
//								String outAnovaTable6b = "anova.table1 <- anova.table1[-c(4)]";
//								String outAnovaTable7b = "rownames(anova.table1) <- " + genotype;
//								String outAnovaTable8b = "colnames(anova.table1) <- c(\"F value\", \"Num df\", \"Denom df\", \"Pr(>F)\")";
//								String outAnovaTable9b = "anova.table1[1, \"F value\"] <- format(round(anova.table1[1, \"F value\"],2), digits=2, nsmall=2, scientific=FALSE)";
//								String outAnovaTable10b = "anova.table1[1, \"Pr(>F)\"] <- formatC(as.numeric(format(anova.table1[1, \"Pr(>F)\"], scientific=FALSE)), format=\"f\")";
//								String outAnovaTable11b = "capture.output(anova.table1,file=\"" + outFileName + "\",append = TRUE)";
//								String outAnovaTable12b = "detach(\"package:pbkrtest\")";
//								
//								rEngine.eval(outAnovaTable1b);
//								rEngine.eval(outAnovaTable2b);
//								rEngine.eval(outAnovaTable3b);
//								rEngine.eval(outAnovaTable4b);
//								rEngine.eval(outAnovaTable5b);
//								rEngine.eval(outAnovaTable6b);
//								rEngine.eval(outAnovaTable7b);
//								rEngine.eval(outAnovaTable8b);
//								rEngine.eval(outAnovaTable9b);
//								rEngine.eval(outAnovaTable10b);
//								rEngine.eval(outAnovaTable11b);
//								rEngine.eval(outAnovaTable12b);
//								rEngine.eval(outspace);
								
								
								//default output: LSMeans
								String outLSMeans1 = "capture.output(cat(\"\nGENOTYPE LSMEANS AND STANDARD ERRORS:\n\n\"),file=\"" + outFileName + "\",append = TRUE)";
								String outLSMeans2 = "capture.output(ssa1$output[[" + i + "]]$site[[" + j + "]]$summary.statistic,file=\"" + outFileName + "\",append = TRUE)"; 
		
								rEngine.eval(outLSMeans1);
								rEngine.eval(outLSMeans2);
								rEngine.eval(outspace);
								
								
								//default output: standard error of the differences
								String outsedTable = "capture.output(cat(\"\nSTANDARD ERROR OF THE DIFFERENCE (SED):\n\n\"),file=\"" + outFileName + "\",append = TRUE)";
								String outsedTable2 = "capture.output(ssa1$output[[" + i + "]]$site[[" + j + "]]$sedTable,file=\"" + outFileName + "\",append = TRUE)";

								rEngine.eval(outsedTable);
								rEngine.eval(outsedTable2);
								rEngine.eval(outspace);

							} //end of if (printAllOutputFixed)
								
						} // end of for loop for diff envts
						
					} //end of for (int k = 0; k < respvar.length; k++)
					
					rEngine.eval(outspace);
					rEngine.eval(sep2);
					
				} //end of else of if runSuccess
				
			}// end of if (isInputRawData)

			
			//using the predicted means data
			if (isInputRawData == false) {
				dire3 = "dataMeans<- tryCatch(read.csv(\"" + dataFileName + "\", header = TRUE, na.strings = c(\"NA\",\".\",\" \",\"\"), blank.lines.skip=TRUE, sep = \",\"), error=function(err) \"notRun\")";				
				rEngine.eval(dire3);
				System.out.println(dire3);
				
				String runSuccessDire3 = rEngine.eval("dataMeans").asString(); 
				if (runSuccessDire3 != null && runSuccessDire3.equals("notRun")) {
					System.out.println("error with capturing dataMeans");
					readingDataMeansSuccess=false;
				}
				
				for (int i = 0; i < respvar.length; i++) {
					respVarMean[i] = respvar[i];
				}
			}
			
			if (readingDataMeansSuccess) {
				//data compatibility check
				String setWd = "setwd(\"" + resultFolderPath + "\")";
				String dataPrepFunction = "dataPrepResult<-QTL.dataprep(dataMeans, \"" + genotypicDataFileName + "\", \"" + mapFileName + "\", \"" + genotype + "\")";
				
				rEngine.eval(setWd);
				rEngine.eval(dataPrepFunction);
				
				System.out.println(setWd);
				System.out.println(dataPrepFunction);
				
				String isNewPhenoCreated = rEngine.eval("dataPrepResult$isNewPhenoCreated").asBool().toString();
				String isNewMapCreated = rEngine.eval("dataPrepResult$isNewMapCreated").asBool().toString();
				String isNewGenoCreated = rEngine.eval("dataPrepResult$isNewGenoCreated").asBool().toString();
				
				System.out.println("is new phenotypic file created:" + isNewPhenoCreated);
				System.out.println("is new map file created:" + isNewMapCreated);
				System.out.println("is new genotypic file created:" + isNewGenoCreated);
				
				if (isNewPhenoCreated == "TRUE") {
					String newPheno = "dataMeans<- tryCatch(read.csv(\"" + resultFolderPath + "newPhenoData.csv\", header = TRUE, na.strings = c(\"NA\",\".\",\" \",\"\"), blank.lines.skip=TRUE, sep = \",\"), error=function(err) \"notRun\")";
					rEngine.eval(newPheno);
					System.out.println(newPheno);
				}
				
				String newMapFilePath=null;
				if (isNewMapCreated == "TRUE") {
					newMapFilePath = resultFolderPath + "newMapData.txt";
				} else {
					newMapFilePath = mapFileName;
				}
				
				String newGenoFilePath=null;
				if (isNewGenoCreated == "TRUE") {
					newGenoFilePath = resultFolderPath + "newGenoData.txt";
				} else {
					newGenoFilePath = genotypicDataFileName;
				}
				
				//start of QTL analysis
				if (isInputRawData == false) {
					String usedData = "capture.output(cat(\"\nDATA FILE: " + dataFileName + "\n\",file=\"" + outFileName + "\"))";
					rEngine.eval(usedData);
				}
				
				String heading2 = "capture.output(cat(\"\nQTL ANALYSIS\n\"),file=\"" + outFileName + "\",append = TRUE)";
				String heading3 = "capture.output(cat(\"\nMETHOD: " +  qtlMethod + "\n\n\"),file=\"" + outFileName + "\",append = TRUE)";
				String sep = "capture.output(cat(\"------------------------------\"),file=\"" + outFileName + "\",append = TRUE)";
				String sep2 = "capture.output(cat(\"==============================\n\"),file=\"" + outFileName + "\",append = TRUE)";
				String outspace = "capture.output(cat(\"\n\"),file=\"" + outFileName + "\",append = TRUE)";
				
				rEngine.eval(heading2);
				rEngine.eval(heading3);

				////////////////////////////////
//				rEngine.eval("source(\"E:/StarPbtools/QTL/QTL_irri2.R\")");
				
				////////////////////////////////
				String bHet = "FALSE";
				if (heterozygousPresent) bHet = "TRUE"; else bHet = "FALSE"; 
				String bMarker = "FALSE";
				if (estimatePlotMarkerMap) bMarker = "TRUE"; else bMarker = "FALSE";
				String sThresh;
				if (thresholdLiJi) sThresh = "Li&Ji"; else sThresh = thresholdNumericValue;

				double dThreshMQ = allelicDiffThreshold;
				double dChi2Sig = significanceLevelChiSquare;
				double dCutoff = cutOffMissingData;
									
				for (int i = 0; i < respvar.length; i++) {
					String qtlResultAll1 = "capture.output(cat(\"\nRESPONSE VARIABLE: " + respvar[i] + "\n\"),file=\"" + outFileName + "\",append = TRUE)";
					rEngine.eval(sep);
					rEngine.eval(qtlResultAll1);
					rEngine.eval(sep);
					rEngine.eval(outspace);
					
					for (int j = 0; j < numEnvts; j++) {
						if (!selectedEnvironmentLevel.equals("[ALL]")) specEnv = selectedEnvironmentLevel;
						else specEnv = envts[j];

						if (environment != "NULL") {
							String qtlResultAll1b = "capture.output(cat(\"\nANALYSIS FOR: " + environment + " = " + specEnv + "\n\"),file=\"" + outFileName + "\",append = TRUE)";
							rEngine.eval(sep);
							rEngine.eval(qtlResultAll1b);
							rEngine.eval(sep);
							rEngine.eval(outspace);
						}

						String funcDmMq1 = "dmMq1 <- tryCatch(cross.data<-load.cross.data(dataMeans, \"" + newGenoFilePath +
						"\", \"" + newMapFilePath + "\", cross = \"" + crossType +
						"\", heterozygotes = " + bHet + ", genotype = \"" + genotype + "\", env.label = " + environment_In + ", env = \"" + specEnv + "\", ST = NULL), error=function(err) \"notRun\")";

						String funcDmMq2 = "dmMq2 <- tryCatch(crossobj <- cross.data, error=function(err) \"notRun\")";

						rEngine.eval(funcDmMq1);
						System.out.println(funcDmMq1);
						
						String runSuccessDmMq1 = rEngine.eval("dmMq1").asString();
						if (runSuccessDmMq1 != null && runSuccessDmMq1.equals("notRun")) {	
							System.out.println("error");
							rEngine.eval("capture.output(cat(\"\n***Problem encountered during data reading/compilation.***\n\n\"),file=\"" + outFileName + "\",append = TRUE)"); //append to output file?
						}

						rEngine.eval(funcDmMq2);
						System.out.println(funcDmMq2);
						
						String runSuccessDmMq2 = rEngine.eval("dmMq2").asString();
						if (runSuccessDmMq2 != null && runSuccessDmMq2.equals("notRun")) {	
							System.out.println("error");
							rEngine.eval("capture.output(cat(\"\n***Problem encountered during data reading/compilation.***\n\n\"),file=\"" + outFileName + "\",append = TRUE)"); //append to output file?
						}
							
						if (i == 0 && j == 0) {
							//done once for all envts and traits
							String funcDmMq3 = "dmMq3 <- tryCatch(MQ.marker.diag(crossobj, \"" + resultFolderPath + "\", estmarker = " + bMarker + ", thresholdMQ = " + dThreshMQ + ", p.val = " + dChi2Sig + ", na.cutoff = " + 
												dCutoff + "), error=function(err) \"notRun\")";

							rEngine.eval(funcDmMq3);
							System.out.println(funcDmMq3);
							
							String runSuccessDmMq3 = rEngine.eval("dmMq3").asString();
							if (runSuccessDmMq3 != null && runSuccessDmMq3.equals("notRun")) {	
								System.out.println("error");
								rEngine.eval("capture.output(cat(\"\n***Problem encountered during marker quality check.***\n\n\"),file=\"" + outFileName + "\",append = TRUE)"); //append to output file?
							}
						}
						
						String funcQtlSIM = null;
						if (thresholdLiJi) {
							funcQtlSIM = "qtlSIM <- tryCatch(QTL.result<-QTL.analysis(crossobj, \"" + resultFolderPath + "\", env.label = " + environment_In + ", env = \"" + specEnv + "\", trait = \"" + respVarMean[i] + "\", step = " + step + 
									", method = \"SIM\", threshold = \"" + sThresh +
									"\", distance = " + minDistance + ", window.size = " + windowSize + 
						            "), error=function(err) \"notRun\")";
						} else {
							funcQtlSIM = "qtlSIM <- tryCatch(QTL.result<-QTL.analysis(crossobj, \"" + resultFolderPath + "\", env.label = " + environment_In + ", env = \"" + specEnv + "\", trait = \"" + respVarMean[i] + "\", step = " + step + 
									", method = \"SIM\", threshold = " + sThresh +
									", distance = " + minDistance + ", window.size = " + windowSize + 
						            "), error=function(err) \"notRun\")";
						}
						
						System.out.println(funcQtlSIM);
						rEngine.eval(funcQtlSIM);
						
//						String runSuccessQtlSim = rEngine.eval("qtlSIM").asString();
//						System.out.println("runSuccessQtlSim: " + runSuccessQtlSim);
//						//generate warning if error occurred	
//						if (runSuccessQtlSim != null && runSuccessQtlSim.equals("notRun")) {	
//							System.out.println("error");
//							rEngine.eval("capture.output(cat(\"\n***An error has occurred.***\n***QTL analysis not completed.***\n\"),file=\"" + outFileName + "\",append = TRUE)"); //append to output file?
//						}
						
						String runSuccessQtl = null;
						
						if (qtlMethod == "SIM") {
							runSuccessQtl = rEngine.eval("qtlSIM").asString();
						}
						
						if (qtlMethod == "CIM") {
							String funcQtlCIM1 = "qtlCIM1 <- tryCatch(cofactors<-as.vector(QTL.result$selected$marker), error=function(err) \"notRun\")";
							
							String funcQtlCIM2=null;
							if (thresholdLiJi) {
								funcQtlCIM2 = "qtlCIM2 <- tryCatch(QTL.result<-QTL.analysis(crossobj, \"" + resultFolderPath + "\", env.label = " + environment_In + ", env = \"" + specEnv + "\", trait = \"" + respVarMean[i] + "\", step = " + step + 
										", method = \"CIM\", threshold = \"" + sThresh +
										"\", distance = " + minDistance + ", cofactors, window.size = " + windowSize + 
							            "), error=function(err) \"notRun\")";
							} else {
								funcQtlCIM2 = "qtlCIM2 <- tryCatch(QTL.result<-QTL.analysis(crossobj, \"" + resultFolderPath + "\", env.label = " + environment_In + ", env = \"" + specEnv + "\", trait = \"" + respVarMean[i] + "\", step = " + step + 
										", method = \"CIM\", threshold = " + sThresh +
										", distance = " + minDistance + ", cofactors, window.size = " + windowSize + 
							            "), error=function(err) \"notRun\")";
							}
							
							System.out.println(funcQtlCIM1);
							rEngine.eval(funcQtlCIM1);

							String runSuccessQtlCIM1 = rEngine.eval("qtlCIM1").asString();
							if (runSuccessQtlCIM1 != null && runSuccessQtlCIM1.equals("notRun")) {	
								System.out.println("error");
								rEngine.eval("capture.output(cat(\"\n***Cannot proceed with CIM (error on the cofactors).***\n***(Shown output for SIM).***\n\"),file=\"" + outFileName + "\",append = TRUE)"); //append to output file?
							} else { //if cofactors were generated
								System.out.println(funcQtlCIM2);
								rEngine.eval(funcQtlCIM2);
								
								runSuccessQtl = rEngine.eval("qtlCIM2").asString();
							}
						}
						
						if (runSuccessQtl != null && runSuccessQtl.equals("notRun")) {	
							System.out.println("error");
							rEngine.eval("capture.output(cat(\"\n***QTL analysis not done.***\n\"),file=\"" + outFileName + "\",append = TRUE)"); //append to output file?
						}
						else {
						
							String qtlResultAll1a = "capture.output(cat(\"\nQTL RESULT (ALL):\n\"),file=\"" + outFileName + "\",append = TRUE)";
							rEngine.eval(qtlResultAll1a);	
		
							String qtlResultAll2 = "capture.output(QTL.result$all,file=\"" + outFileName + "\",append = TRUE)";
							rEngine.eval(qtlResultAll2);
							
							//QTL result selected
							String qtlResultSel1 = "capture.output(cat(\"\nQTL RESULT (SELECTED):\n\"),file=\"" + outFileName + "\",append = TRUE)";
							String qtlResultSel2 = "capture.output(QTL.result$selected,file=\"" + outFileName + "\",append = TRUE)";
							
							rEngine.eval(qtlResultSel1);
							rEngine.eval(qtlResultSel2);
							rEngine.eval(outspace);
							rEngine.eval(outspace);
						
						}// end of else for r
					}
				} //end of for (int i = 0; i < respvar.length; i++)
				
				rEngine.eval(sep2);
				
			} //end of if (readingDataMeansSuccess)
			
			rEngineEnd();
			
			System.out.println("reached end.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void doGenerationMeanRawData(String dataFileName, String outFileName, String[] usersNotation, String[] generalNotation, String alpha) {

		String usersNotationVector= inputTransform.createRVector(usersNotation);
		String generalNotationVector=inputTransform.createRVector(generalNotation);

		try {
			String readData = "dataRead <- read.csv(\"" + dataFileName + "\", header = TRUE, na.strings = c(\"NA\",\".\",\" \",\"\"), blank.lines.skip=TRUE, sep = \",\")";
			String sinkIn = "sink(\"" + outFileName + "\")";
			String func = "result <- try(generationMean.test(dataRead, " + usersNotationVector + "," + generalNotationVector + ",\"" + alpha + "\"), silent=TRUE)";
			String checkError = "if (class(result) == \"try-error\") {\n";
			checkError = checkError + "    msg <- trimStrings(strsplit(result, \":\")[[1]])\n";
			checkError = checkError + "    msg <- trimStrings(paste(strsplit(msg, \"\\n\")[[length(msg)]], collapse = \" \"))\n";
			checkError = checkError + "    msg <- gsub(\"\\\"\", \"\", msg)\n";
			checkError = checkError + "    cat(\"\n*** \nERROR in generationMean.test function:\\n  \",msg, \"\n***\", sep = \"\")\n";
			checkError = checkError + "}";
			String sinkOut = "sink()";

			rEngine.eval(readData);
			rEngine.eval(sinkIn);
			rEngine.eval(func);
			rEngine.eval(checkError);
			rEngine.eval(sinkOut);
			
			System.out.println(readData);
			System.out.println(sinkIn);
			System.out.println(func);
			System.out.println(checkError);
			System.out.println(sinkOut);
				
			rEngineEnd();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void doGenerationMeanSummaryStats(String dataFileName, String outFileName, String generationMean, String weights, String generationStandardDeviation, 
			String numberObservations, String generation, String[] usersNotation, String[] generalNotation, String alpha) {
		
		String usersNotationVector= inputTransform.createRVector(usersNotation);
		String generalNotationVector=inputTransform.createRVector(generalNotation);
		
		try {
			String readData = "dataRead <- read.csv(\"" + dataFileName + "\", header = TRUE, na.strings = c(\"NA\",\".\",\" \",\"\"), blank.lines.skip=TRUE, sep = \",\")";
			String sinkIn = "sink(\"" + outFileName + "\")";

			String func=null;
			if (weights != "NULL") {
				func = "result <- try(generationMeanSummaryStats.test(dataRead, \"" + generationMean + "\", \"" + weights + "\", stdDevVar=NULL, numObsVar=NULL,\"" + generation + "\"," + usersNotationVector + "," + generalNotationVector + ",\"" + alpha + "\"), silent=TRUE)";
			} else if (numberObservations != "NULL") {
					func = "result <- try(generationMeanSummaryStats.test(dataRead, \"" + generationMean + "\", weightsVar=NULL,\"" + generationStandardDeviation + "\",\"" + numberObservations + "\",\"" + generation + "\"," + usersNotationVector + "," + generalNotationVector + ",\"" + alpha + "\"), silent=TRUE)";
				} else {
					func = "result <- try(generationMeanSummaryStats.test(dataRead, \"" + generationMean + "\", weightsVar=NULL,\"" + generationStandardDeviation + "\", numObsVar=NULL,\"" + generation + "\"," + usersNotationVector + "," + generalNotationVector + ",\"" + alpha + "\"), silent=TRUE)";
				}
			String checkError = "if (class(result) == \"try-error\") {\n";
			checkError = checkError + "    msg <- trimStrings(strsplit(result, \":\")[[1]])\n";
			checkError = checkError + "    msg <- trimStrings(paste(strsplit(msg, \"\\n\")[[length(msg)]], collapse = \" \"))\n";
			checkError = checkError + "    msg <- gsub(\"\\\"\", \"\", msg)\n";
			checkError = checkError + "    cat(\"\n*** \nERROR in generationMeanSummaryStats.test function:\\n  \",msg, \"\n***\", sep = \"\")\n";
			checkError = checkError + "}";
			String sinkOut = "sink()";

			rEngine.eval(readData);
			rEngine.eval(sinkIn);
			rEngine.eval(func);
			rEngine.eval(checkError);
			rEngine.eval(sinkOut);
			
			System.out.println(readData);
			System.out.println(sinkIn);
			System.out.println(func);
			System.out.println(checkError);
			System.out.println(sinkOut);
				
			rEngineEnd();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
		
	@Override
	public void doHWETest(String rDataFilename, String outFilename, String display) {
		try {
						
			String rm = "rm(list=ls(all=TRUE))";
			String callLibrary = "library(adegenet)";
			String load = "load(\"" + rDataFilename + "\")";
			
			String hweTest1 = "capture.output(cat(\"\nDATA FILE: " + rDataFilename + "\n\n\",file=\"" + outFilename + "\"))";
			String hweTest2 = "capture.output(cat(\"HARDY-WEINBERG EQUILIBRIUM TEST\n\n\"),file=\"" + outFilename + "\",append = TRUE)";
			
			String hweTest3=null;
			String hweTest4=null;
			if (display.equals("full")) {
				hweTest3 = "capture.output(cat(\"SUMMARY OF TESTS:\n\n\"),file=\"" + outFilename + "\",append = TRUE)";
				hweTest4 = "result<-HWETest(data, display=\"full\")";
			} 
			
			if (display.equals("matrix")) {
				hweTest3 = "capture.output(cat(\"MATRIX OF P-VALUES:\n\n\"),file=\"" + outFilename + "\",append = TRUE)";
				hweTest4 = "result<-HWETest(data, display=\"matrix\")";
			}
			
			String hweTest5 = "capture.output(result,file=\"" + outFilename + "\",append = TRUE)";
			String detachLibrary = "detach(\"package:adegenet\")";
			
			rEngine.eval(rm);
			rEngine.eval(callLibrary);
			rEngine.eval(load);
			System.out.println(rm);
			System.out.println(callLibrary);
			System.out.println(load);
			rEngine.eval(hweTest1);
			rEngine.eval(hweTest2);
			rEngine.eval(hweTest3);
			rEngine.eval(hweTest4);
			System.out.println(hweTest4);
			rEngine.eval(hweTest5);
			rEngine.eval(detachLibrary);
			
			rEngineEnd();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void dogenDistance(String rDataFilename, String outFilename, String resultFolderPath, int method, Boolean displayDiag, Boolean displayUpper) {
		try {
			
			String methodUsed = new String();
			switch (method) {
				case 1: {
					methodUsed = "Nei’s Distance"; 
					break;
				}
				case 2: {
					methodUsed = "Angular Distance or Edwards’ Distance";
					break;
				}
				case 3: {
					methodUsed = "Coancestrality Coefficient or Reynolds’ Distance";
					break;
				}
				case 4: {
					methodUsed = "Classical Euclidean Distance or Rogers’ Distance";
					break;
				}
				case 5: {
					methodUsed = "Absolute Genetics Distance or Provesti’s Distance";
					break;
				}
				default: {
					methodUsed = "Nei’s Distance";
					break;
				}
			}
			
			String diagString = displayDiag.toString().toUpperCase();
			String upperString = displayUpper.toString().toUpperCase();
			
			String rm = "rm(list=ls(all=TRUE))";
			String callLibrary = "library(adegenet)";
			String load = "load(\"" + rDataFilename + "\")";
			
			String hweTest1 = "capture.output(cat(\"\nDATA FILE: " + rDataFilename + "\n\n\",file=\"" + outFilename + "\"))";
			String hweTest2 = "capture.output(cat(\"GENETIC DISTANCES BETWEEN POPULATIONS\n\n\"),file=\"" + outFilename + "\",append = TRUE)";
			String hweTest3 = "capture.output(cat(\"METHOD: " + methodUsed +"\n\n\"),file=\"" + outFilename + "\",append = TRUE)";
			String hweTest4 = "capture.output(cat(\"DISTANCE MATRIX:\n\n\"),file=\"" + outFilename + "\",append = TRUE)";
			String hweTest5 = "result<-genDistance(data, method=" + method +", displayDiag=" + diagString + ", displayUpper=" + upperString +")";
			String hweTest6 = "capture.output(result,file=\"" + outFilename + "\",append = TRUE)";
			
			String writeToFile = "write.table(as.matrix(result),file = \"" + resultFolderPath + "genDistanceMatrix.csv\",sep=\",\",col.names=NA, row.names=TRUE)";
			
			String detachLibrary = "detach(\"package:adegenet\")";
			
			rEngine.eval(rm);
			rEngine.eval(callLibrary);
			rEngine.eval(load);
			System.out.println(rm);
			System.out.println(callLibrary);
			System.out.println(load);
			rEngine.eval(hweTest1);
			rEngine.eval(hweTest2);
			rEngine.eval(hweTest3);
			rEngine.eval(hweTest4);
			rEngine.eval(hweTest5);
			System.out.println(hweTest5);
			rEngine.eval(hweTest6);
			rEngine.eval(writeToFile);
			System.out.println(writeToFile);
			rEngine.eval(detachLibrary);
			
			rEngineEnd();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void dopopStructure(String rDataFilename, String outFilename, Boolean pairwiseFst) {
		try {
			
			String pairwiseString = pairwiseFst.toString().toUpperCase();
			
			String rm = "rm(list=ls(all=TRUE))";
			String callLibrary = "library(adegenet)";
			String load = "load(\"" + rDataFilename + "\")";
			
			String Test1 = "capture.output(cat(\"\nDATA FILE: " + rDataFilename + "\n\n\",file=\"" + outFilename + "\"))";
			String Test2 = "capture.output(cat(\"TESTING POPULATION STRUCTURE\n\n\"),file=\"" + outFilename + "\",append = TRUE)";
			String Test3 = "result<-popStructure(data, pairwiseFst=" + pairwiseString + ")";
			String Test4 = "capture.output(cat(\"Fst = \", result$fst,\"\n\"),file=\"" + outFilename + "\",append = TRUE)";
			String Test5 = "capture.output(cat(\"Fit = \", result$fit,\"\n\"),file=\"" + outFilename + "\",append = TRUE)";
			String Test6 = "capture.output(cat(\"Fis = \", result$fis,\"\n\"),file=\"" + outFilename + "\",append = TRUE)";
			
			rEngine.eval(rm);
			rEngine.eval(callLibrary);
			rEngine.eval(load);
			System.out.println(rm);
			System.out.println(callLibrary);
			System.out.println(load);
			
			rEngine.eval(Test1);
			rEngine.eval(Test2);
			rEngine.eval(Test3);
			System.out.println(Test3);
			rEngine.eval(Test4);
			rEngine.eval(Test5);
			rEngine.eval(Test6);
			
			if (pairwiseFst) {
				String Test7 = "capture.output(cat(\"\n\nPAIRWISE Fst:\n\n\"),file=\"" + outFilename + "\",append = TRUE)";
				String Test8 = "capture.output(result$fstMatrix,file=\"" + outFilename + "\",append = TRUE)";
				
				rEngine.eval(Test7);
				rEngine.eval(Test8);
			}
			
			String detachLibrary = "detach(\"package:adegenet\")";
						
			rEngine.eval(detachLibrary);
			
			
			rEngineEnd();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}

