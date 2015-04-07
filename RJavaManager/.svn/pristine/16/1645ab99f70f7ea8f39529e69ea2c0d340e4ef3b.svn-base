package org.irri.breedingtool.rjava.manager;

import org.irri.breedingtool.rjava.utilities.RFunctionName;
import org.rosuda.JRI.Rengine;

/**
 * Used for defining and calling R functions/libraries
 */
public class RScriptLibraryInit {
	
	private Rengine rEngine;


	public RScriptLibraryInit(Rengine rEngine) {
		this.rEngine = rEngine;
	}
	


	/**
	 * evaluates/calls R functions/libraries
	 */
	public void initLibrary() {
		
		//for functions contained in a library, say testPackage
		this.rEngine.eval("library(testPackage)");
		
		String funcGraphHist = "source(\"" + RFunctionName.CREATEHIST + "\")";

		String funcGetVarInfo = "source(\"" + RFunctionName.GETVARINFO + "\")";
		
		String source1 = "source(\"" + RFunctionName.CLASS_INFORMATION2 + "\")";
		String source2 = "source(\"" + RFunctionName.TRIM_STRINGS + "\")";
		String source3 = "source(\"" + RFunctionName.ESTIMATEMISSINGDATA + "\")";
		String source4 = "source(\"" + RFunctionName.FACGEN + "\")";
		String source5 = "source(\"" + RFunctionName.GENERATEBALANCEDATA + "\")";
		String source6 = "source(\"" + RFunctionName.NC1TEST + "\")";

		System.out.println(funcGraphHist);
		System.out.println(funcGetVarInfo);
		System.out.println(source1);
		System.out.println(source2);
		System.out.println(source3);
		System.out.println(source4);
		System.out.println(source5);
		System.out.println(source6);
		
		this.rEngine.eval(funcGraphHist);
		this.rEngine.eval(funcGetVarInfo);
		
//		this.rEngine.eval(source1);
//		this.rEngine.eval(source2);
//		this.rEngine.eval(source3);
//		this.rEngine.eval(source4);
//		this.rEngine.eval(source5);
//		this.rEngine.eval(source6);
		
	}

	
}
