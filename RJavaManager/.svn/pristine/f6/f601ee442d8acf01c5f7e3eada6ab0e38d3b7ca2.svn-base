package org.irri.breedingtool.rjava.staranalysis;

import org.irri.breedingtool.rjava.manager.RJavaManager;
import org.rosuda.JRI.Rengine;

public class TestErrorHandling {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
    RJavaManager rjava=new RJavaManager();
    rjava.initPBtool();
    Rengine rEngine=rjava.getrEngine();
    String command = "sum(5+a)";
    //String s=String.valueOf(rEngine.eval("try(sum(5,a))").asDouble());
    //String a= rEngine.eval("class(try(sum(5,a)))[1]").asString();
    
    String a = rEngine.eval("class(try("+ command +"))[1]").asString();
    if (a == "try-error") {
    	System.out.println(rEngine.eval("try("+ command +")").asString());
    } else {
    	System.out.println(rEngine.eval("+ command +").asDouble());
    }
   
    
	}

}
