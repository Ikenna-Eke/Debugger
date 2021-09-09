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
public class LabelCode extends ByteCode {
    private String label;

    @Override
    public void init(Vector<String> args) {
        label = args.get(0);
    }

    @Override
    public void execute(VirtualMachine vm) {
        
    }

    @Override
    public String toString() {
        String output = "LABEL "+ label;
        return output;
    }
    
    public String getLabel(){
        return label;
    }
    
}
