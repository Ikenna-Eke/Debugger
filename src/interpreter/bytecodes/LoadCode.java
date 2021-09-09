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
public class LoadCode extends ByteCode {
    private int offset;
    private String variable;

    @Override
    public void init(Vector<String> args) {
        offset = Integer.parseInt(args.get(0));
        variable = args.get(1);
    }

    @Override
    public void execute(VirtualMachine vm) {
        vm.runStackLoad(offset);
    }    

    @Override
    public String toString() {
        String output = "LOAD " + offset + " " + variable + "    <load " + variable + ">";
        return output;
    }
}
