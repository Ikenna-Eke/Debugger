/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interpreter;

import java.util.Stack;
import interpreter.bytecodes.ByteCode;
import interpreter.bytecodes.DumpCode;
/**
 *
 * @author ieke2_yd0he2l
 */
public class VirtualMachine {
    protected Program program;
    protected int pc; //program counter
    protected boolean isRunning;
    protected RunTimeStack runStack;
    protected Stack<Integer> returnAddrs;
    protected String dumpFlag = "OFF";
    
    public VirtualMachine(Program program){
        this.program = program;
    }
    
    public void executeProgram() {
        pc = 0;
        runStack = new RunTimeStack();
        returnAddrs = new Stack();
        isRunning = true;
        while (isRunning){
            ByteCode code = program.getCode(pc);
            code.execute(this);
            //Prints out ByteCodes toString() if dumpFlag is 'ON' and the Bytecode is not a DumpCode
           if("ON".equals(dumpFlag) && !(code instanceof DumpCode)){
                System.out.println(code.toString());
                runStack.dump();
            }
            pc++;
        }
    }
    
    public int getPc(){
        return pc;
    }
    
    public void setPc(int pc){
        this.pc = pc;
    }
    
    public void setRunning(boolean isRunning){
        this.isRunning = isRunning;
    }
    
    public boolean getRunning(){
        return isRunning;
    }
    
    public String getDumpFlag(){
        return dumpFlag;
    }
    
    public void setDumpFlag(String dumpFlag){
        this.dumpFlag = dumpFlag;
    }
    
   //Call RunTimeStack's Pop 
   public int popRunStack(){
       return runStack.pop();
   }
   
   //Returns RunTimeeStack's peel
   public int peekRunStack(){
       return runStack.peek();
   }
   
   public int runStackLoad(int offset){
       return runStack.load(offset);
   }
   
   public int runStackStore(int offset){
       return runStack.Store(offset);
   }
   
   public void pushRunStack(int i){
       runStack.push(i);
   }
   
   
   public void pushRunStack(Integer i){
       runStack.push(i);
   }
   
   
   public void newFrame(int offset){
       runStack.newFrameAt(offset);
   }
   
   public void popFrame(){
       runStack.popFrame();
   }
   
   //Returns whats at the top of ReturnAddrs Stack and removes its from stack
   public int popReturnAddrs(){
       return returnAddrs.pop();
   }
   
   //Pushes an int into the ReturnAddrs Stack 
   public void pushReturnAddrs(int i){
       returnAddrs.push(i);
   }
   
   //Pushes an Integer into the ReturnAddrs Stack
   public void pushReturnAddrs(Integer i){
       returnAddrs.push(i);
   }
   
   //Returns RunTimeStack
   public RunTimeStack getRunStack(){
       return runStack;
   }    
}
