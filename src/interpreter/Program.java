/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interpreter;

import interpreter.bytecodes.*;
import interpreter.bytecodes.debuggerByteCodes.LineCode;
import java.util.Vector;
import java.util.HashMap;

/**
 *
 * @author ieke2_yd0he2l
 */
public class Program {
    //Holds all ByteCode instances
    private Vector<ByteCode> program;
    
    //Holds the Line Number where a break point may  be placed;
    private static Vector<Integer> validBreakPoints = new Vector<Integer>();
    
    //Holds the labels uses to resolve the target address for ByteCods that jump address 
    private static HashMap<String,Integer> labels = new HashMap<String, Integer>();
    public Program(){
        program = new Vector <ByteCode>();
    }
    
    public ByteCode getCode(int pc){
        return program.get(pc);
    }
    
    //Adds ByteCode to Program and if the ByteCode is LabelCode it adds its label to the label HashMap
    //If the ByteCode is a Line Code it also adds the line number into the validBreakPoint Vector
    public void add(ByteCode byteCode){
        if(byteCode instanceof LabelCode){
            LabelCode code = (LabelCode)byteCode;
            labels.put(code.getLabel(), program.size());
        }
        if(byteCode instanceof LineCode){
            LineCode code = (LineCode) byteCode;
            validBreakPoints.add(code.getLineNum());
        }
        program.add(byteCode);
    }
   
    //Resolves the Target addres for ByteCodes that jump to different addresses
    public void resolveAddress(){
        int address;
        for(int i=0;i<program.size();i++){
            if(program.get(i) instanceof JumpCode){
                JumpCode bytecode = (JumpCode)program.get(i);
                bytecode.setTargetAddrs(labels.get(bytecode.getLabel()));
            }
        }
    }
    
    //Return the Vector holds all my ByteCode Instance 
    public Vector<ByteCode> getProgram(){
        return program;
    }
    
    public Vector<Integer> getValidBreakPoints(){
        return validBreakPoints;
    }
}
