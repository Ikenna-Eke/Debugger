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
public class StoreCode extends ByteCode {
    private int n;
    private String id;
    private int value;

    @Override
    public void init(Vector<String> args) {
        n = Integer.parseInt(args.get(0));
        id = args.get(1);
    }

    @Override
    public void execute(VirtualMachine vm) {
        value = vm.peekRunStack();
        vm.runStackStore(n);
    }

    @Override
    public String toString() {
        String output="STORE " + n + " " + id + "    " + id + " = " + value;
        return output;
        
    }
    
}
