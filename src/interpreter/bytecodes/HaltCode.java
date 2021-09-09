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
public class HaltCode extends ByteCode {

    @Override
    public void init(Vector<String> args){
        
    }

    @Override
    public void execute(VirtualMachine vm) {
        vm.setRunning(false);
    }

    @Override
    public String toString() {
        return "HALT";
    }
    
}
