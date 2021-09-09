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
public class ArgsCode extends ByteCode {
    private int numOfArgs;
    
    @Override
    public void init(Vector<String> args) {
        numOfArgs = Integer.parseInt(args.get(0));
    }

    @Override
    public void execute(VirtualMachine vm) {
        vm.newFrame(numOfArgs);
    } 

    @Override
    public String toString(){ 
        String output = "Args"+" "+ numOfArgs;
        return output;
    }
}
