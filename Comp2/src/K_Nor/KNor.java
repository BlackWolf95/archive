package K_Nor;

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
import Visiteur.ObjVisitor;

public class KNor implements ObjVisitor<Exp> {

	@Override
	public Exp visit(Unit e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Exp visit(Bool e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Exp visit(Int e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Exp visit(Float e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Exp visit(Not e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Exp visit(Neg e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Exp visit(Add e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Exp visit(Sub e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Exp visit(FNeg e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Exp visit(FAdd e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Exp visit(FSub e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Exp visit(FMul e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Exp visit(FDiv e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Exp visit(Eq e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Exp visit(LE e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Exp visit(If e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Exp visit(Let e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Exp visit(Var e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Exp visit(LetRec e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Exp visit(App e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Exp visit(Tuple e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Exp visit(LetTuple e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Exp visit(Array e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Exp visit(Get e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Exp visit(Put e) {
		// TODO Auto-generated method stub
		return null;
	}

}