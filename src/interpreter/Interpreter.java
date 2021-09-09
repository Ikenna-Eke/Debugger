/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interpreter;

import interpreter.debugger.Debugger;
import java.io.*;
/**
* <pre>
*
*
*
* Interpreter class runs the interpreter:
* 1. Perform all initializations
* 2. Load the bytecodes from file
* 3. Run the virtual machine
*
*
*
* </pre>
*/
public class Interpreter {
    ByteCodeLoader bcl;
    public Interpreter(String codeFile, Boolean debuggerMode) {
        try {
                CodeTable.init();
                if(debuggerMode ==true){
                    CodeTable.initDebugger();
                }
                bcl = new ByteCodeLoader(codeFile);
        } catch (IOException e) {
            System.out.println("**** " + e);
        }
    }
    
    void run() throws  IOException {
        Program program = bcl.loadCodes();
        VirtualMachine vm = new VirtualMachine(program);
        vm.executeProgram();
    }
    
    public Program getProgram() throws IOException{
        Program program = bcl.loadCodes();
        return program;
    }
    
    public static void main(String args[]) throws  IOException {
        if (args.length == 0) {
            System.out.println("***Incorrect usage, try: java interpreter.Interpreter <file>");
            System.exit(1);
        }
        /**
         * Checks if the first argument is "-d", if it is true
         * then it runs the Debugger, if not it runs Interpreter
         */ 
        if("-d".equals(args[0])){
            (new Debugger(args[1])).run();
        }
        else{
            (new Interpreter(args[0],false)).run();
        }
    }
}
