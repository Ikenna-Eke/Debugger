/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interpreter.bytecodes;

import interpreter.VirtualMachine;
import java.util.Vector;

/**
 *
 * @author ieke2_yd0he2l
 */
public class ReturnCode extends ByteCode {
    private String funcName;
    private int value;

    @Override
    public void init(Vector<String> args) {
        if(args.size()>0){
            funcName = args.get(0);
        }
        else{
            funcName ="";
        }
    }

    @Override
    public void execute(VirtualMachine vm) {
        vm.popFrame();
        vm.setPc(vm.popReturnAddrs());
        value= vm.peekRunStack();
    }

    @Override
    public String toString() {
        String output;
        if(!funcName.contains("<")){
            output = "Return "+funcName+ " exit "+ funcName+": "+value;
            return output;
        }
        else{
            int index = funcName.indexOf("<");
            output = "Return "+funcName+ " exit"+funcName.substring(0, index)+": "+value;
            return output;
        }    
    }
    
    public String getFuncName(){
        return funcName;
    }
    
    public int getValue(){
        return value;
    }
    
}
