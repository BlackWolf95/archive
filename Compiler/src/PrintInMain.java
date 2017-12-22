
import java.io.*;

import ASML.AM_Exp;
import ASML.AM_Print_Visitor;
import ASML.AM_TransVisitor;
import ASML_STRUCTURE.Asml;
import ASML_STRUCTURE.Asml_Str;
import Alpha_conversion.Alpha_con;
import Expression.*;

import K_Nor.KNor;
import Reduction_nested.Reduction_N;
import Visiteur.*;
//import Parser.*;
import Heights.*;

public class PrintInMain {
	
	public static void PrintAST(String path)
	{
		try {
		      Parser p = new Parser(new Lexer(new FileReader(path)));
		      Exp expression = (Exp) p.parse().value;      
		      assert (expression != null);

		      System.out.println("------ AST ------");
		      expression.accept(new PrintVisitor());
		      System.out.println();						

		      System.out.println("------ Height of the AST ----");
		      int height = Height.computeHeight(expression);
		      System.out.println("using Height.computeHeight: " + height);
		      
		     
		      System.out.println("------ K-norm ----");
		      Exp expressK = expression.accept(new KNor());
     	      expressK.accept(new PrintVisitor());
     	      System.out.println();
		      
		      System.out.println("------ Alpha ----"); 
		      Exp expressA = expressK.accept(new Alpha_con());
		      expressA.accept(new PrintVisitor());
		      System.out.println();
     	      
	     	  System.out.println("------ Reduction ----"); 
			  Exp expressR = expressA.accept(new Reduction_N() );
			  expressR.accept(new PrintVisitor());
			  System.out.println();
			  
			  System.out.println("------ ASML ----"); 
			  AM_Exp expressAM = expressR.accept(new AM_TransVisitor() );
			  expressAM.accept(new AM_Print_Visitor());
			  System.out.println();
			  		 		   
		      ObjVisitor<Integer> v = new HeightVisitor();
		      height = expression.accept(v);
		      System.out.println("using HeightVisitor: " + height);

		    } catch (Exception e) {
		      e.printStackTrace();
		    }
	}

	public static void Help ()
	{
		
	}
}
