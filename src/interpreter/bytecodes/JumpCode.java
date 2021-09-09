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
public abstract class JumpCode extends ByteCode {

    @Override
    public abstract void init(Vector<String> args);

    @Override
    public abstract void execute(VirtualMachine vm);
    
    @Override
    public abstract String toString();
    
    public abstract int getTargetAddrs();
    public abstract void setTargetAddrs(int addrs);
    public abstract String getLabel();
    
    
    
}
