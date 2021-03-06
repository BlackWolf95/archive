package ARMGen;
import java.util.*;
import registers.Registers;
import Instructions.*;
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
import Expression.FunDef;
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
import Types.TFloat;
import Types.TFun;
import Types.TInt;
import Types.Type;

public class DataStrucConversion {
	
	public Object visit(Exp e, fun fun) {
//If expression is of type Add then return an instruction of type Add		
        if (e instanceof Add) {
                return (Integer_Add)visit((Add)e, fun);
        }
//If expression is of type Sub then return an instruction of type Sub
        else if (e instanceof Sub) {
                return (Integer_Sub) visit((Sub)e, fun);
        }
//If expression is of type Let       
        else if (e instanceof Let) {
                visit((Let)e, fun);
        }
//If expression is of type LetRec        
        else if (e instanceof LetRec) {
                visit((LetRec)e, fun);
        }
//If expression is of type Int return the integer value       
        else if (e instanceof Int) {
                return (Integer) visit((Int)e, fun);
        }
//If expression is of type Bool return Boolean value        
        else if (e instanceof Bool) {
                return (boolean) visit((Bool)e, fun);
        }
//If expression is of type Not return boolean value        
        else if (e instanceof Not) {
                return (boolean) visit((Bool)e, fun);
        }
//If expression is of type VAr return a variable        
        else if (e instanceof Var) {
                return (Variable) visit((Var)e, fun);
        }
//If expression is of type App        
        else if (e instanceof App) {
                visit((App)e, fun);
        }
//If expression is of type Neg return Integer        
        else if (e instanceof Neg) {
                return (Integer) visit((Neg)e, fun);
        }
//If expression is of type If return If instruction        
        else if (e instanceof If) {
                return (If_Inst) visit((If)e, fun);
        }
        return null;
}
	
	public static Integer var_count = 0;
	public String get_tvar() { 
        var_count = var_count + 1;
        return "temp_var" + var_count.toString();  
}
	public static Integer exp_count = 0;
	public String get_tboolexp() { 
        exp_count = exp_count + 1;
        return "temp_Boolexp" + exp_count.toString();  
}
	public static Integer label_count = 0;
	public String get_new_label() {
        label_count = label_count + 1;
        return "label" + label_count.toString();
} 
	public Integer_Add visit(Add e, fun fun) {
        ArrayList<Variable> varlist = new ArrayList<Variable>();
        for (Variable v : fun.get_local()){         //get the variables of the function
                if (((Var)e.e1).id.toString() == v.get_name()) {
                        varlist.add(v);            //add them to the varlist if they match
                }
        }
        if (varlist.size() == 0) {                 //if varlist is empty then get integer variables
                Integer_Op temp1 = new Integer_Op(get_tvar(), (Integer) visit(e.e1, fun), fun);        	
                varlist.add(temp1);      //add them to the list
        }

        for (Variable v : fun.get_local()) {
                if (((Var)e.e2).id.toString() == v.get_name()) {         //add to the list if they are same
                        varlist.add(v);
                }
        }
        if (varlist.size() == 0) {
                Integer_Op temp2 = new Integer_Op(get_tvar(), (Integer)visit(e.e2, fun), fun);
                varlist.add(temp2);
        }


        try {
                Integer_Add instr = new Integer_Add(fun, varlist.get(0), varlist.get(1));
                fun.add_instructionlist(instr);    //add the created Add instruction to the list
                return instr;                      
        } catch (IndexOutOfBoundsException exception) {
                Integer_Op temp1 = new Integer_Op(get_tvar(), (Integer)visit(e.e1, fun), fun);
                Integer_Op temp2 = new Integer_Op(get_tvar(), (Integer)visit(e.e2, fun), fun);
                fun.get_local().add(temp1);
                fun.get_local().add(temp2);
                Integer_Add instr = new Integer_Add(fun, new Integer_Op(get_tvar(), (Integer)visit(e.e1, fun), fun), new Integer_Op(get_tvar(), (Integer)visit(e.e2, fun), fun));
                fun.add_instructionlist(instr);
                return instr;
        }
}
public Integer_Sub visit(Sub e, fun fun){
    ArrayList<Variable> varlist = new ArrayList<Variable>();

    for (Variable v : fun.get_local()){
            if (((Var)e.e1).id.toString() == v.get_name()) {
                    varlist.add(v);
            }
    }
    if (varlist.size() == 0) {
            Integer_Op temp1 = new Integer_Op(get_tvar(), (Integer)visit(e.e1, fun), fun);
            varlist.add(temp1);
    }

    for (Variable v : fun.get_local()) {
            if (((Var)e.e2).id.toString() == v.get_name()) {
                    varlist.add(v);
            }
    }
    if (varlist.size() == 0) {
            Integer_Op temp2 = new Integer_Op(get_tvar(), (Integer)visit(e.e2, fun), fun);
            varlist.add(temp2);
    }


    try {
            Integer_Sub instr = new Integer_Sub(fun, varlist.get(0), varlist.get(1));
            fun.add_instructionlist(instr);
            return instr;
    } catch (IndexOutOfBoundsException exception) {
            Integer_Op temp1 = new Integer_Op(get_tvar(), (Integer)visit(e.e1, fun), fun);
            Integer_Op temp2 = new Integer_Op(get_tvar(), (Integer)visit(e.e2, fun), fun);
            fun.get_local().add(temp1);
            fun.get_local().add(temp2);
            Integer_Sub instr = new Integer_Sub(fun, temp1, temp2);
            fun.add_instructionlist(instr);
            return instr;
    }
}

public void visit(Let e, fun fun) {
    if (e.e1 instanceof Int) {                           
            Integer_Op var = new Integer_Op(e.id.toString(), (Integer) visit(e.e1, fun), fun);
            Assign instr = new Assign(fun, var, (Integer) visit(e.e1, fun));
            fun.get_local().add(var);
            fun.add_instructionlist(instr);
    }
    else if (e.e1 instanceof Neg) {
            Integer_Op var = new Integer_Op(e.id.toString(), (Integer) visit(e.e1, fun), fun);
            Assign instr = new Assign(fun, var, (Integer) visit(e.e1, fun));
            fun.get_local().add(var);
            fun.add_instructionlist(instr);
    }
    else if (e.e1 instanceof App) {
            visit(e.e1, fun);
    }
    else if (e.e1 instanceof Add) {
            Integer_Op var = new Integer_Op(e.id.toString(), 0, fun);
            Assign instr = new Assign(fun, var, (Integer_Add) visit(e.e1, fun));
            fun.get_local().add(var);
            fun.add_instructionlist(instr);
    }
    else if (e.e1 instanceof Sub) {
            Integer_Op var = new Integer_Op(e.id.toString(), 0, fun);
            Assign instr = new Assign(fun, var, (Integer_Sub) visit(e.e1, fun));
            fun.get_local().add(var);
            fun.add_instructionlist(instr);
    }
    else if (e.e1 instanceof Var) {
            Variable var = new Variable(e.id.toString(), fun);
            Assign instr = new Assign(fun, var, (Variable)visit(e.e1, fun));
            fun.get_local().add(var);
            fun.add_instructionlist(instr);
    }
    else if (e.e1 instanceof If) {
            Integer_Op var = new Integer_Op(e.id.toString(), 0, fun);
            Assign instr = new Assign(fun, var, (If_Inst) visit(e.e1, fun));
            fun.get_local().add(var);
            fun.add_instructionlist(instr);
    }
    else {
            visit(e.e1, fun);
    }
    
    visit(e.e2, fun);
}

public Variable visit(Var e, fun fun){

    for (Variable var : fun.get_local()) {
            if (((Var)e).id.toString() == var.get_name()) {
                    return var;
            }
    }
    return null;
}

public Integer visit(Int e, fun fun){
    Integer_Op i = new Integer_Op(get_tvar(), e.i, fun); 
    Noop instr = new Noop(i);
    fun.add_instructionlist(instr);
    return e.i;
}

public Integer visit(Neg e, fun fun){
    Integer i = (Integer) visit(e.e, fun);
    return -i;
}

public void visit(App e, fun fun){
    ArrayList<Object> varlist = new ArrayList<Object>();
    for (Exp ex : e.es) {
            Object var = (Object) visit(ex, fun);
            if (var instanceof Integer) {
                    var = new Integer_Op(get_tvar(), (Integer)var, fun);
                    ((Variable)var).assign_register();
                    ((Variable)var).remove();
                    Assign instr = new Assign(fun, var, ((Integer_Op)var).get_val());
                    fun.add_instructionlist(instr);
            }
            else if (var instanceof Boolean) {
                    var = new Bool_Op(get_tvar(), (boolean)var, fun);
                    ((Variable)var).assign_register();
                    ((Variable)var).remove();
                    Assign instr = new Assign(fun, var, ((Bool_Op)var).get_expression());
                    fun.add_instructionlist(instr);
            }
            varlist.add(var);
    }

    for (Object obj : varlist) {
            if (obj instanceof Variable) {
                    ((Variable)obj).assign_argregister();
                    ((Variable)obj).remove_arg();
            }
    }
    Call instr = new Call(varlist, ((Var)e.e).id.toString());
    fun.add_instructionlist(instr);
}

public boolean visit(Expression.Bool e, fun fun){
    boolean b = e.b;
    return b;
}

public boolean visit(Not e, fun fun){
    boolean b = (boolean) visit(e.e, fun);
    return !b;
}

public BoolEq visit(Eq e, fun fun){
    ArrayList<Variable> varlist = new ArrayList<Variable>();

    for (Variable var : fun.get_local()) {
            if (((Var)e.e1).id.toString() == var.get_name()) {
                    varlist.add(var);
            }
    }
    if (varlist.size() == 0) {
            Integer_Op temp1 = new Integer_Op(get_tvar(), (Integer)visit(e.e1, fun), fun);
            varlist.add(temp1);
    }

    for (Variable var : fun.get_local()) {
            if (((Var)e.e2).id.toString() == var.get_name()) {
                    varlist.add(var);
            }
    }
    if (varlist.size() == 0) {
            Integer_Op temp2 = new Integer_Op(get_tvar(), (Integer)visit(e.e2, fun), fun);
            varlist.add(temp2);
    }

    BoolEq exp = new BoolEq(get_tboolexp(), fun, varlist.get(0), varlist.get(1));
    return exp;
}

public BoolLe visit(LE e, fun fun){
    ArrayList<Variable> varlist = new ArrayList<Variable>();

    for (Variable var : fun.get_local()) {
            if (((Var)e.e1).id.toString() == var.get_name()) {
                    varlist.add(var);
            }
    }
    if (varlist.size() == 0) {
            Integer_Op temp1 = new Integer_Op(get_tvar(), (Integer)visit(e.e1, fun), fun);
            varlist.add(temp1);
    }

    for (Variable var : fun.get_local()) {
            if (((Var)e.e2).id.toString() == var.get_name()) {
                    varlist.add(var);
            }
    }
    if (varlist.size() == 0) {
            Integer_Op temp2 = new Integer_Op(get_tvar(), (Integer)visit(e.e2, fun), fun);
            varlist.add(temp2);
    }
    BoolLe exp = new BoolLe(get_tboolexp(), fun, varlist.get(0), varlist.get(1));
    return exp;
}

public If_Inst visit(If e, fun fun) {
    Bool_Op cond = new Bool_Op(get_tvar(), (BoolExp)visit(e.e1, fun), fun);
    fun branchthen = new fun(get_new_label(), new ArrayList(), new ArrayList<Inst_Interface>(), fun.loc_reg, fun.arg_reg);
    visit(e.e2, branchthen);
    fun branchelse = new fun(get_new_label(), new ArrayList(), new ArrayList<Inst_Interface>(), fun.loc_reg, fun.arg_reg);
    visit(e.e3, branchelse);
    If_Inst instr = new If_Inst(cond, branchthen, branchelse);
    fun.add_instructionlist(instr);
    return instr;
}

public void visit(LetRec e, fun fun){
    ArrayList<Variable> arglist = new ArrayList<Variable>();
    ArrayList<Registers> reglist= new ArrayList<Registers>(9);
    ArrayList<Registers> arg_reglist = new ArrayList<Registers>(2);
    Registers.reg_initialization(reglist, arg_reglist);
//Initializing th function with registers and arguments
    fun func = new fun(e.fd.id.toString(), arglist, new ArrayList<Inst_Interface>(), reglist, arg_reglist);
    for (Id id : e.fd.args) {
            Variable arg = new Variable(id.toString(), func);
            arglist.add(arg);
    }
    visit(e.fd.e, func);
    visit(e.e, fun);
}

}
