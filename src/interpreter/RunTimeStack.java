/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interpreter;

import java.util.Stack;
import java.util.Vector;
import java.util.Iterator;

/**
 *
 * @author ieke2_yd0he2l
 */
public class RunTimeStack {
    private Vector<Integer> runStack;
    private Stack<Integer> framePointers;
    
    public RunTimeStack(){
        runStack = new Vector<Integer>();
        framePointers = new Stack<Integer>();
        framePointers.add(0);   
    }
    
    //Prints out the content of RunTimeStack and Frame
     public void dump(){
        Iterator iter = framePointers.iterator();
        int  currentFrame = (Integer) iter.next();
        int nextFrame = currentFrame;
        //print contents of runtime stack one frame at a time
        for (int i = 0; i < framePointers.size(); i++) {
            if (iter.hasNext()) {
                nextFrame = (Integer) iter.next();
            } 
            else {
                nextFrame = runStack.size();
            }

            System.out.print("[");
            //print contents of current frame
            for (int j = currentFrame; j < nextFrame; j++) {
                System.out.print(runStack.get(j));
                if (j != nextFrame - 1) {
                    System.out.print(",");
                }
            }
            System.out.print("]");
            currentFrame = nextFrame;
        }
        System.out.println();
    }
    
    //Return what is at the Top of Stack but doesn't remove it
    public int peek(){
        return runStack.get(runStack.size()-1);        
    }
    
    //Returns what is at the Top of the Stack and removes
    public int pop(){
        int topOfStack = runStack.get(runStack.size()-1);
        runStack.remove(runStack.size()-1);
        return topOfStack;
    }
    
    public int push(int i){
        runStack.add(i);
        return i;
    }
    
    //Creates A NewFrame at this number down from the top
    public void newFrameAt(int offset){
        framePointers.push(runStack.size()-offset);
        
    }
    
    //Pop a Frame , push the return value into the RunTimeStack
    public void popFrame(){
        int topOfStack = this.pop();
        int topOfFrame = framePointers.pop();
        for(int i = runStack.size()-1; i >= topOfFrame;i--){
            this.pop();
        }
        this.push(topOfStack);
        
    }
    
    //Store into a Stack
    public int Store(int offset){
        int topOfStack = this.pop();
        runStack.set(framePointers.peek()+offset,topOfStack);
        return topOfStack;
    }
    
    //load into a Stack
    public int load(int offset){
        int temp = runStack.get(framePointers.peek()+offset);
        runStack.add(temp);
        return temp;
    }
    
    //Pushes an Integer in the RunTimeStack
    public Integer push(Integer i){
        runStack.add(i);
        return i;
    }
    
    //Returns the Offset
    public int getOffset(){
        return runStack.size()-framePointers.peek()-1;
    }
    
    //Return the value located at that offset
    public int getValueAtOffset(int offset){
        return runStack.elementAt(framePointers.peek()+offset);
    }
}