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
public class LineCode extends ByteCode  {
    private int lineNumber;
    
    @Override
    public void init(Vector<String> args) {
        lineNumber = Integer.parseInt(args.get(0));
    }

    @Override
    public void execute(VirtualMachine vm) {
        DebuggerVirtualMachine dvm = (DebuggerVirtualMachine) vm;
        dvm.setCurrentLn(lineNumber);
    }

    @Override
    public String toString() {
        String output = "LINE "+ lineNumber;
        return output;
    }
    
    public int getLineNum(){
        return lineNumber;
    }
    
}
