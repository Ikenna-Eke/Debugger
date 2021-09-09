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
public abstract class ByteCode {
    
    public abstract void init(Vector<String> args);
    public abstract void execute(VirtualMachine vm);
    public abstract String toString();
    
}
