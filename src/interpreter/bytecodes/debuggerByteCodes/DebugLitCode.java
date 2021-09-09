/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interpreter.bytecodes.debuggerByteCodes;

import interpreter.VirtualMachine;
import interpreter.bytecodes.LitCode;
import interpreter.debugger.DebuggerVirtualMachine;
import java.util.Vector;

/**
 *
 * @author ieke2_yd0he2l
 */
public class DebugLitCode extends LitCode{
    
    @Override
    public void init(Vector<String> args) {
        super.init(args);
    }
    
    @Override
    public void execute(VirtualMachine vm){
        super.execute(vm);
        DebuggerVirtualMachine dvm = (DebuggerVirtualMachine) vm;
        String var = this.getVariable();
        if(!var.equals("")){
            int offset = dvm.getRunStack().getOffset();
            dvm.setVarValFER(var,offset);
        }  
    }
}
