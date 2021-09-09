/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interpreter.bytecodes.debuggerByteCodes;

import interpreter.VirtualMachine;
import interpreter.bytecodes.ByteCode;
import interpreter.debugger.*;
import java.util.Vector;

/**
 *
 * @author ieke2_yd0he2l
 */
public class FormalCode extends ByteCode {
    private String xyz;
    private int offset;

    @Override
    public void init(Vector<String> args) {
        xyz  = args.get(0);
        offset = Integer.parseInt(args.get(1));
    }

    @Override
    public void execute(VirtualMachine vm) {
        DebuggerVirtualMachine dvm = (DebuggerVirtualMachine) vm;
        dvm.setVarValFER(xyz, offset);
    }

    @Override
    public String toString() {
        String output="FORMAL "+xyz+ " "+ offset;
        return output;
    }
    
    public int getOffset(){
        return offset;
    }
}
