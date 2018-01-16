package Instructions;
import java.util.*;
import ARMGen.fun;

public class Integer_Add implements Inst_Interface {
	
	public List<Object> operandlist = new ArrayList<Object>();
	
//Constructor 	
	public Integer_Add(fun fun, Operands op1, Operands op2)
	{
		this.operandlist.add(op1);
		this.operandlist.add(op2);

	}
//Constructor	
	public Integer_Add(fun fun, Object obj1, Object obj2)
	{
       this.operandlist.add(obj1);
       this.operandlist.add(obj2);
	}
//Get the instruction type		
	@Override
	public inst_type Get_Inst_type() {
		// TODO Auto-generated method stub
		return inst_type.Integer_Add;
	}
//Get the operands list
	@Override
	public List<Object> get_operands() {
		// TODO Auto-generated method stub
		return operandlist;
	}
//Print the instruction
	@Override
	public void print() {
		// TODO Auto-generated method stub
		System.out.println("ADD "+ operandlist.get(0)+" "+operandlist.get(1));
	}

}
