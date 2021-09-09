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
public class FunctionCode extends ByteCode {
    private String name;
    private int startLn;
    private int endLn;

    @Override
    public void init(Vector<String> args) {
        name = args.get(0);
        startLn = Integer.parseInt(args.get(1));
        endLn = Integer.parseInt(args.get(2));
    }

    @Override
    public void execute(VirtualMachine vm) {
        DebuggerVirtualMachine dmv = (DebuggerVirtualMachine) vm;
        dmv.setFunctionERinfo(name, startLn, endLn);
    }

    @Override
    public String toString() {
        String output = "FUNCTION "+ name+" "+startLn+" "+endLn;
        return output;
    }
    
}
