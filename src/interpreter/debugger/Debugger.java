/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interpreter.debugger;

import interpreter.*;
import interpreter.debugger.ui.UI;
import java.io.IOException;
import java.util.Vector;

/**
 *
 * @author ieke2_yd0he2l
 */
public class Debugger {
    private Interpreter interpreter;
    private String sourceFile;
    private String byteCodeFile;
    private DebuggerVirtualMachine dvm;
    private Program program;
    private UI userInterface;
    
    public Debugger(String fileName) throws IOException{
        sourceFile = fileName+".x";
        byteCodeFile = fileName+".x.cod";
        interpreter = new Interpreter(byteCodeFile,true);
        Program program = interpreter.getProgram();
        dvm = new DebuggerVirtualMachine(program, sourceFile);
        userInterface = new UI(dvm);
    }
    
    //Runs the UI
    public void run() throws IOException{
        userInterface.run();
    }
}
