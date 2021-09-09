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
public class LitCode extends ByteCode {
    private int offset; 
    private String variable;

    @Override
    public void init(Vector<String> args) {
        offset = Integer.parseInt(args.get(0));
        if(args.size()>1){
            variable = args.get(1);
        }else{
            variable = "";
        }
    }

    @Override
    public void execute(VirtualMachine vm) {
        if(variable=="") {
            vm.pushRunStack(offset);
        } 
       else {
            vm.pushRunStack(0);
        }
    }

    @Override
    public String toString() {
        if(variable ==""){
            String output = "LIT " + offset + " " + variable+"    int";
            return output;
        }
        String output = "LIT " + offset + " " + variable+"    int " + variable;
        return output;
    }
    
    public String getVariable(){
        return variable;
    }
    public int getOffset(){
        return offset;
    }
    
}
