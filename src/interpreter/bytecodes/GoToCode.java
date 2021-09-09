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
public class GoToCode extends JumpCode {
    private String label;
    private int targetAddrs;

    @Override
    public void init(Vector<String> args) {
        label = args.get(0);
    }

    @Override
    public void execute(VirtualMachine vm) {
        vm.setPc(targetAddrs);
    }

    @Override
    public String toString() {
        String output = "GOTO "+ targetAddrs;
        return output;
    }

    @Override
    public int getTargetAddrs() {
       return targetAddrs;
    }

    @Override
    public void setTargetAddrs(int addrs) {
        targetAddrs = addrs;
    }

    @Override
    public String getLabel() {
        return label;
    }
    
}
