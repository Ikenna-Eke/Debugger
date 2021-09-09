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
public class CallCode extends JumpCode {
    private String funcName;
    private int targetAddrs;
    private int args;

    @Override
    public void init(Vector<String> args) {
        funcName = args.get(0);
    }

    @Override
    public void execute(VirtualMachine vm) {
        vm.pushReturnAddrs(vm.getPc());
        vm.setPc(targetAddrs);
        args = vm.peekRunStack();
    } 
    
    @Override
    public int getTargetAddrs(){
        return targetAddrs;
    }
    
    @Override
    public void setTargetAddrs(int targetAddrs){
        this.targetAddrs = targetAddrs;
    }
    @Override
    public String getLabel(){
        return funcName;
    }

    @Override
    public String toString() {
        String output;
        if(!funcName.contains("<")){
            output = "CALL "+funcName+ "    "+funcName+"("+args+")";
            return output;
        }
        else{
            int index = funcName.indexOf("<");
            output= "CALL "+funcName+ "    "+funcName.substring(0, index)+"("+args+")";
            return output;
        }    
    }
}
