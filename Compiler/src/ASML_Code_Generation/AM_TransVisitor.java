package ASML_Code_Generation;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import Expression.Add;
import Expression.App;
import Expression.Array;
import Expression.Bool;
import Expression.Eq;
import Expression.Exp;
import Expression.FAdd;
import Expression.FDiv;
import Expression.FMul;
import Expression.FNeg;
import Expression.FSub;
import Expression.Float;
import Expression.Get;
import Expression.If;
import Expression.Int;
import Expression.LE;
import Expression.Let;
import Expression.LetRec;
import Expression.LetTuple;
import Expression.Neg;
import Expression.Not;
import Expression.Put;
import Expression.Sub;
import Expression.Tuple;
import Expression.Unit;
import Expression.Var;
import Tool.Id;
import Visiteur.ObjVisitor;
import Visiteur.PrintVisitor;

public class AM_TransVisitor implements ObjVisitor<AM_Exp>{

	@Override
	public AM_Exp visit(Unit e) {
		// TODO Auto-generated method stub
		AM_Exp a_unit=new A_Unit();
		return a_unit;
	}

	@Override
	public AM_Exp visit(Bool e) {
		// TODO Auto-generated method stub
//		if(e.b){
//			A_Ident a_i1=new A_Ident("1");
//			A_Ident a_i2=new A_Ident("1");
//			return new A_Eq(a_i1, a_i2);
//			
//		}
		if(e.b==true){
			return new A_Int(1);
		}
		return new A_Int(0);
	}

	@Override
	public AM_Exp visit(Int e) {
		// TODO Auto-generated method stub
		return new A_Int(e.i);
	}

	@Override
	public AM_Exp visit(Float e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AM_Exp visit(Not e) {
		// TODO Auto-generated method stub
//		A_Nop a_nop=(A_Nop) e.e.accept(this);
//		return a_nop;
		A_Neg a_neg=(A_Neg)e.e.accept(this);
		return a_neg;
	}

	@Override
	public AM_Exp visit(Neg e) {
		// TODO Auto-generated method stub
		A_Neg a_neg=(A_Neg)e.e.accept(this);
		return a_neg;
	}

	@Override
	public AM_Exp visit(Add e) {
		// TODO Auto-generated method stub
		AM_Exp a_e1=(AM_Exp) e.e1.accept(this);
		AM_Exp a_e2=(AM_Exp)e.e2.accept(this);
		return new A_Add(a_e1,a_e2);
		
		//return null;
	}

	@Override
	public AM_Exp visit(Sub e) {
		// TODO Auto-generated method stub
		A_Ident a_i1=(A_Ident) e.e1.accept(this);
		A_Ident a_i2=(A_Ident) e.e2.accept(this);
		return new A_Sub(a_i1,a_i2);
		
	}

	@Override
	public AM_Exp visit(FNeg e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AM_Exp visit(FAdd e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AM_Exp visit(FSub e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AM_Exp visit(FMul e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AM_Exp visit(FDiv e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AM_Exp visit(Eq e) {
		// TODO Auto-generated method stub
		A_Eq a_eq1=(A_Eq) e.e1.accept(this);
		A_Eq a_eq2=(A_Eq) e.e2.accept(this);
		return new A_Eq(a_eq1,a_eq2);
	}

	@Override
	public AM_Exp visit(LE e) {
		// TODO Auto-generated method stub
		AM_Exp am_e1=e.e1.accept(this); 
		AM_Exp am_e2=e.e2.accept(this);
		return new A_LE(am_e1, am_e2);
	}

	@Override
	public AM_Exp visit(If e) {
		// TODO Auto-generated method stub
		AM_Exp a_e1=(AM_Exp)e.e1.accept(this);
		AM_Exp a_e2=(AM_Exp)e.e1.accept(this);
		AM_Exp a_e3=(AM_Exp)e.e1.accept(this);
		return new A_If(a_e1, a_e2, a_e3);
	}

	@Override
	public AM_Exp visit(Let e) {
		// TODO Auto-generated method stub
		return new A_Let(e.id,e.t,e.e1.accept(this),e.e2.accept(this));
	}

	@Override
	public AM_Exp visit(Var e) {
		// TODO Auto-generated method stub
		return new A_Var(e.id);
	}

	
	@Override
	public AM_Exp visit(LetRec e) {
		// TODO Auto-generated method stub
 		Asml_Fundef asml_fun=new Asml_Fundef(e.fd.id, e.fd.type, e.fd.args, e.e.accept(this));
		A_LetRec a_letRec=new A_LetRec(asml_fun, e.e.accept(this));
	    System.out.println(e.fd.id);
	    System.out.println(e.fd.e);
	    System.out.println(e.fd.args.get(0));
	    System.out.println(e.e.accept(this));
	    
	    System.out.println(a_letRec.a_df.id);
	    System.out.println(a_letRec.a_df.e);
	    System.out.println(a_letRec.a_df.args.get(0));
	    System.out.println(a_letRec.a_e);
		return a_letRec;
		//return null;
	}

	@Override
	public AM_Exp visit(App e) {
		// TODO Auto-generated method stub
		AM_Exp a_e=e.e.accept(this);
		List<AM_Exp> a_es=new ArrayList<>();
		for(int i=0;i<e.es.size();i++){
		a_es.add(e.es.get(i).accept(this));
		}
		return new A_App(a_e,a_es);
	}

	@Override
	public AM_Exp visit(Tuple e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AM_Exp visit(LetTuple e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AM_Exp visit(Array e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AM_Exp visit(Get e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AM_Exp visit(Put e) {
		// TODO Auto-generated method stub
		return null;
	}

}
