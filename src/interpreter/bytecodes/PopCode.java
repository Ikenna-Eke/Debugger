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
public class PopCode extends ByteCode {
    private int num;

    @Override
    public void init(Vector<String> args) {
        num = Integer.parseInt(args.get(0));
    }

    @Override
    public void execute(VirtualMachine vm) {
        for(int i=0; i<num; i++){
            vm.popRunStack();
        }
    }

    @Override
    public String toString() {
        String output = "POP "+ num;
        return output;
    }
    public int getNum(){
        return num;
    }
}
