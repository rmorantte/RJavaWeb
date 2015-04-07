package org.irri.breedingtool.rjava.manager;

import org.rosuda.JRI.Rengine;

public class RJavaManager {
	
	private Rengine rEngine;
	private RJavaDataManipulationManager RJavaDataManipulationManager;
	private RJavaGraphManager RJavaGraphManager;
	private PBToolAnalysisManager pbToolAnalysisManager;
	private PBToolsRandomizationManager pbToolRandomizationManager;
	private STARAnalysisManager starAnalysisManager;
	private STARDesignManager starDesignManager;

	public RJavaManager(){

	}

	public RJavaDataManipulationManager getRJavaDataManipulationManager() {
		return this.RJavaDataManipulationManager;
	}
	

	public RJavaGraphManager getRJavaGraphManager() {
		return this.RJavaGraphManager;
	}

	
	public Rengine getrEngine() {
		return this.rEngine;
	}

	
	
	public PBToolAnalysisManager getPbToolAnalysisManager() {
		return pbToolAnalysisManager;
	}
	
	public PBToolsRandomizationManager getPbToolRandomizationManager() {
		return pbToolRandomizationManager;
	}
	
	public STARAnalysisManager getSTARAnalysisManager() {
		return starAnalysisManager;
	}
	
	public STARDesignManager getSTARDesignManager() {
		return starDesignManager;
	}

	public void initPBtool(){
		this.rEngine = new Rengine(new String[] { " " }, false, null);
		this.rEngine.eval("library(PBTools)");
		this.RJavaDataManipulationManager=new RJavaDataManipulationManager(this.rEngine);
		this.RJavaGraphManager= new RJavaGraphManager(this.rEngine);
		this.pbToolAnalysisManager= new PBToolAnalysisManager(this.rEngine);
		this.pbToolRandomizationManager= new PBToolsRandomizationManager(this.rEngine);
	}
	
	public void initStar(){
		this.rEngine = new Rengine(new String[] { " " }, false, null);
		this.rEngine.eval("library(STAR)");
		this.RJavaDataManipulationManager=new RJavaDataManipulationManager(this.rEngine);
		this.RJavaGraphManager= new RJavaGraphManager(this.rEngine);
		this.starAnalysisManager= new STARAnalysisManager(this.rEngine);
		this.starDesignManager= new STARDesignManager(this.rEngine);
	}
	

}
