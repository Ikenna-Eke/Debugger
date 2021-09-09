/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interpreter.debugger.ui;

import interpreter.debugger.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Vector;

/**
 *
 * @author ieke2_yd0he2l
 */
public class UI {
    private DebuggerVirtualMachine dvm; // Dubugger Virtual Machine
    private boolean running;//check if the debugger UI is running

    public UI(DebuggerVirtualMachine dvm) throws IOException {
        this.dvm = dvm;
    }
    
    //Runs the UI
    public void run() throws IOException{
        running = true;
        displayFunc();
        getInputs();
    }
   
    public void getInputs() throws IOException{
        while(running==true){
            System.out.println("Type ? for help");
            System.out.print(">>");
            Vector<String> userInputs = new Vector();
            //Grabs all the inputs that the User Inputs and adds it userInput <Vector>
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            try{
                String input = br.readLine();
                StringTokenizer inputTok = new StringTokenizer(input);
                while(inputTok.hasMoreTokens()){
                    userInputs.add(inputTok.nextToken());
                }
            }
            catch(IOException e){
                System.out.println(e); 
            }
            //Takes the input located at userInput.get(o) and saves in the variable call 'command and then removes it
            String command = userInputs.get(0);
            userInputs.remove(0);
            switch(command){
                case "?":
                    help();
                    getInputs();
                    break;
                    
                case"sb":
                    setBreakPoints(userInputs);
                    getInputs();
                    break;
                    
                case"cb":
                    clearBreakPoints(userInputs);
                    getInputs();
                    break;
                    
                case"lb":
                    listBreakpoints();
                    getInputs();
                    break;
                    
                case"var":
                    displayVariables();
                    getInputs();
                    break;
                    
                case"df":
                    displayFunc();
                    getInputs();
                    break;
                    
                case"ce":
                    execute();
                    displayFunc();
                    getInputs();
                    break;
                    
                case"so":
                    stepOutFunction();
                    displayFunc();
                    getInputs();
                    break;
                    
                 case"sol":
                     stepOverLine();
                     displayFunc();
                     getInputs();
                     break;
                     
                case"si":
                    stepInFunction();
                    displayFunc();
                    getInputs();
                    break;
                    
                case"t":
                    toggleTracing();
                    getInputs();
                    break;
                    
                case"cs":
                    CallStack();
                    getInputs();
                    break;
                    
                 case"q":
                    quit();
                    break;  
                    
                 default:
                    System.out.println("Error. Not a command.");
                    getInputs();
            }
        }
    }
    
    //List the available Commands for the user
    public void help(){
        System.out.println();
        System.out.println("To Set breakpoints, type: sb (line number(s))");
        System.out.println("To Clear breakpoints, type: cb (line number(s))");
        System.out.println("To Display a list of the current breakpoints, type: lb");
        System.out.println("To Display local variables, type: var");
        System.out.println("To Step out of current function, type: so");
        System.out.println("To Step into a function at the current line, type: si");
        System.out.println("To Step over line, type: sol");
        System.out.println("To Print the Call Stack, type: cs");
        System.out.println("To Turn Function Tracing on/off, type: t ");
        System.out.println("To Display current function, type: df");
        System.out.println("To Continue execution, type: ce");
        System.out.println("To Quit execution, type: q");    
    }
    
    //Displays the Function
    public void displayFunc(){
        dvm.displayFunct();
    }
    
    //Sets Breakpoints
    public void setBreakPoints(Vector<String> args){
        dvm.setBreakPoint(args);
    }
    
    //Removes the breakpoints that the User Wants
    public void clearBreakPoints(Vector<String> args){
        dvm.clearBreakPoints(args);
    }
    
    //Executes the Debug Virtual Machine
    public void execute() throws IOException{
        if(dvm.getFinished()==true){
            quit();
        }
        else{
            dvm.executeProgram();
        }
    }
    //Prints out the the Local Variables
    public void displayVariables(){
        dvm.displayVars();
    }
    
    public void stepOutFunction(){
        System.out.println("Stepping out of Function");
        dvm.stepOut();
    }
    
    public void stepOverLine(){
        System.out.println("Stepping Over Line");
        dvm.stepOver();
    }
    public void stepInFunction(){
        System.out.println("Stepping into Function");
        dvm.stepIn();
    }
    
    public void listBreakpoints(){
        dvm.listBreakPoint();
    }
    
    public void toggleTracing(){
        dvm.Tracing();
    }
    
    public void CallStack(){
        dvm.printCallStack();
    }
    
    public void quit() throws IOException{
        running = false;
        System.out.println("****Execution Halted****");
    }
}
