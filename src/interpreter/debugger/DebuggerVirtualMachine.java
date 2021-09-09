/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interpreter.debugger;

import interpreter.*;
import interpreter.bytecodes.*;
import interpreter.bytecodes.debuggerByteCodes.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.Set;
import java.util.Stack;
import java.util.Vector;
import java.util.Collections;
import java.util.ListIterator;

/**
 *
 * @author ieke2_yd0he2l
 */
public class DebuggerVirtualMachine extends VirtualMachine{
    private Stack<FunctionEnvironmentRecord> environmentStack; //Enviroment Stack
    private Vector<String> sourceCode; //Vector that holds all the Strings of my SourceFile
    private Vector<Integer> validBreakPoints; // Vector that holds all the line numbers that a BreakPoint can be placed
    private Vector<Integer> breakPoints; //Vector that holds where i have a breakpoint
    private String sourceFile;
    private boolean finished = false;
    private boolean steppingIn = false; //Checks if we stepping In 
    private boolean steppingOut = false;//Checks if we are stepping out
    private boolean steppingOver = false; //Checks if we are stepping over
    private int envSize; // Keeps track of the size of our environmentStack;
    private boolean isTraceOn = false; //Keeps track if Function Tracing is on
    private String traceOutput="";
    
    public DebuggerVirtualMachine(Program program, String sourceFile) throws FileNotFoundException, IOException {
        super(program);
        pc = 0;
        runStack = new RunTimeStack();
        returnAddrs = new Stack();
        environmentStack = new Stack();
        validBreakPoints = program.getValidBreakPoints();
        breakPoints = new Vector<Integer>();
        sourceCode = new Vector<String>();
        
        //Adds the Strings of the SourceFile into the SourceCode Vector
        this.sourceFile= sourceFile;
        BufferedReader br = new BufferedReader(new FileReader(sourceFile));
        String currentLn = br.readLine();
        while(currentLn!=null){
            sourceCode.add(currentLn);
            currentLn = br.readLine();
        }
    }
    
    //Executes the the ByteCodes in the Program
    @Override 
    public void executeProgram(){
        isRunning = true;
        while (isRunning) {
            ByteCode code = program.getCode(pc);
            if(code instanceof HaltCode) {
                isRunning = false;
                finished = true;
                }
            code.execute(this);
            pc++;
        }
    }
    
    //Sets whats the current line in the SourceCode and checks if is a there is a breakpoint on the line
    public void setCurrentLn(int lineNum){
        if (lineNum > 0) {
            peekEnvStack().setCurrentLineNumber(lineNum);
            //Once we hit a breakpoint it turns isRunning, steppingOut, steppingIn, steppingOver to false
            if(breakPoints.contains(lineNum)){
                isRunning = false;
                steppingOut = false;
                steppingIn = false;
                steppingOver = false;
                //Once it hits the breakpoint, it checks prints out traceOuput if isTraceOn is true
                if(isTraceOn==true){
                    System.out.print(traceOutput);
                }
            }
            //Checks if its stepping over and if the size of environmentStack hasn't change
            if(steppingOver==true &&(envSize==environmentStack.size())){
                isRunning = false;
                steppingOver = false;
            }
            //Checks if it stepping over and if the size of environmentStack hasn't change
            if(steppingIn==true &&(envSize==environmentStack.size())){
                isRunning = false;
                steppingIn = false;
            }
        }
    }
    
    //Returns the FunctionEnvironmentRecord on the top of EnvironmentStack
    public FunctionEnvironmentRecord peekEnvStack(){
        if(environmentStack.empty()){
            environmentStack.push(new FunctionEnvironmentRecord());
        }
        return (FunctionEnvironmentRecord) environmentStack.peek();
    }
    
    
    //Enters a variable and value into the SymbolTable in the FunctionEnvironment Record on the top of the EnvironmentStack
    public void setVarValFER(String variable, int value){
       peekEnvStack().setVarVal(variable, value);
    }
    
    //Pops a FunctionEnvironmentFunction from the EnvironmentStack
    public void popEnviromentStk(int value){
        if(isTraceOn==true& !environmentStack.empty()){
            for(int i = 0;i<environmentStack.size();i++){
                traceOutput+= " ";
            }
            traceOutput+= "exit "+peekEnvStack().getName()+": "+value+"\n";
        }
        environmentStack.pop();
        if(steppingOut==true &&(envSize>environmentStack.size())){
            isRunning = false;
            steppingOut = false;
        }
        if(steppingOver=true &&(envSize>= environmentStack.size())){
            isRunning= false;
            steppingOver = false;
        }
    }
    
    //Pops the "n" number of variables from the FunctionEnvironemntRecord on the top of EnvironmentStacks
    public void popVariables(int n){
        peekEnvStack().doPop(n);
    } 
    
    //Sets the name, starting line, and end line information for the FunctionEnvironmentRecord at the top of EnvironmentStack
    public void setFunctionERinfo(String name, int startLn, int endLn){
        peekEnvStack().setFunctionInfo(name, startLn, endLn);
        //If isTraceOn is true and environmentStack isn't empty it starts adding Function name to traceoutput String 
        if(isTraceOn==true&&!environmentStack.empty()){
            for(int i = 0;i<environmentStack.size();i++){
                traceOutput+=" ";
            }
            traceOutput+= name+"(";
            //Checks if the next byteCode is FormalCode and excutes it so that that we place value of it into the traceOutput string
            int temp = pc+1;
            ByteCode code = program.getCode(temp);
            while(code instanceof FormalCode){
                int offset = ((FormalCode)(code)).getOffset();
                traceOutput+= getValueFromRunStack(offset);
                temp++;
                code = program.getCode(temp);
                if(code instanceof FormalCode){
                    traceOutput+=", ";
                }
            }
            traceOutput+=")\n";
        }
        //Checks if we are stepping In and excutes any formal code that are next before stopping 
        if(steppingIn==true){
            ByteCode code = program.getCode(pc + 1);
            while (code instanceof FormalCode) {
                code.execute(this);
                pc++;
                code = program.getCode(pc + 1);
            }
            if(envSize<environmentStack.size()){
                isRunning = false;
                steppingIn = false;
            }
        }
    }
    
    //Creates a new Function EnvironmentRecord and adds it to the EnvironmentStack
    public void newFunctionER(){
        environmentStack.push(new FunctionEnvironmentRecord());
    }
    
    //Returns the value from RunStack thats located at this offset
    public int getValueFromRunStack(int offset){
        return this.runStack.getValueAtOffset(offset);
    }
    
    //Print out the Local Variables that in the FunctionEnvironmentRecord that on the top of the EnvironmentStack
    public void displayVars(){
        String output = "Local Variables: ";
        Table symTable = peekEnvStack().getTable();
        Set variables = symTable.keys();
        Iterator iter =  variables.iterator();
        if(iter.hasNext()){
            while(iter.hasNext()){
                String id = (String)iter.next();
                if(!"".equals(id)){
                    int offset = (Integer) symTable.get(id); 
                    int value = getValueFromRunStack(offset);
                    output = output+= id+": "+value+" ";
                    }
                }
            System.out.println(output);
        }
        else{
            System.out.println(output);
        }
    }
    
    public void setBreakPoint(Vector<String>args){
        int lineNumber;
        String addedBkPts="";
        String notAddedBkPts="";
        for(int i = 0;i<args.size();i++){
            try{
                lineNumber = Integer.parseInt(args.get(i));
                if(validBreakPoints.contains(lineNumber)){
                    if(!breakPoints.contains(lineNumber)){
                        breakPoints.add(lineNumber);
                        addedBkPts +=args.get(i)+" ";
                    }
                }
                else{
                    notAddedBkPts +=args.get(i)+" ";
                }
            }catch(NumberFormatException e){
                System.out.println("Error this isn't a number");
            }
        }
            if(!("").equals(addedBkPts)){
               System.out.println("A BreakPoint set at: "+addedBkPts); 
            }
            if(!("").equals(notAddedBkPts)){
                System.out.println("A Breakpoint can't be set at: "+notAddedBkPts);
            }
    }
    
    public void clearBreakPoints(Vector<String> args){
        int lineNumber;
        String success=""; //This string will hold all the line Numbers that we have successfully removed a BreakPoint; 
        String failed=""; //This String will hold all the lines Number that couldn't have a breakpoint removed because it wasn't there
        for(int i = 0;i<args.size();i++){
            try{
                lineNumber = Integer.parseInt(args.get(i));
                if(breakPoints.contains(lineNumber)){
                    breakPoints.remove(breakPoints.indexOf(lineNumber));
                    success+=args.get(i)+" ";
                }
                else{
                    failed+=args.get(i)+" ";
                }
            }
            catch(NumberFormatException e){
                System.out.println("Error!! This isn't a number");
            }
        }
        if(!("").equals(success)){
            System.out.println("Breakpoint cleared at: "+success); 
        }
        if(!("").equals(failed)){
            System.out.println("A Breakpoint wasn't at: "+failed);
        }
    }
    
    //Displays all the breakpoints 
    public void listBreakPoint(){
        String output;
        if(breakPoints.size()==0){
            output = "No breaksPoints are set";
        }
        else{
            output = "Breakpoint at lines: [";
            Collections.sort(breakPoints);
            for(int i = 0;i<breakPoints.size();i++){
                output += breakPoints.get(i);
                if(i+1<breakPoints.size()){
                    output+=", ";
                }
            }
            output+="]";
        }
        System.out.println(output);
    }
    
    //Prints out the SourceCode
    public void displayFunct(){
        System.out.println();
        int startLn, endLn, currentLn;
        startLn = peekEnvStack().getStartLn();
        endLn = peekEnvStack().getEndLn();
        currentLn = peekEnvStack().getCurrentLn();
        
        
        
        if(startLn<0){
            System.out.println("****"+peekEnvStack().getName()+"****");
        }
        else{
            if(startLn==0){
                System.out.println("*****Debugging "+sourceFile+"******");
                startLn = 1;        
                endLn = sourceCode.size();
            }

            //Checks if any breakpoints are added to the breakpoint Vectors and prints a "*" if the line has a breakpoint
            for(int i = startLn-1; i<endLn;i++){
                String output = i+1+". "+sourceCode.get(i);
                if(breakPoints.contains(i+1)){
                    output = "*"+output;
                }
                //Prints an arrow to show where the current line is
                if((i+1) == currentLn){
                    output = output+"    <--------";
                }
                System.out.println(output);
            }
        }
        System.out.println();
    }
    
    public boolean getFinished(){
        return finished;
    }
    
    //Lets the program know that its in StepOut Mode
    public void stepOut(){
        steppingOut = true;
        envSize = environmentStack.size();
        executeProgram();
    }
    
    //Lets the program know that its in StepIn Mode
    public void stepIn(){
        steppingIn = true;
        envSize = environmentStack.size();
        executeProgram();
    }
    
    //Lets the program know that its in StepOver Mode
    public void stepOver(){
        steppingOver = true;
        envSize = environmentStack.size();
        executeProgram();
    }
    
    //Set isTraceON to true or false
    public void Tracing(){
        if(isTraceOn==false){
            isTraceOn= true;
            traceOutput="";
            System.out.println("Tracing On");
        }
        else{
            isTraceOn = false;
            traceOutput="";
            System.out.println("Tracing Off");
        }
    }
    
    public void printCallStack(){ 
        String callStack = "";
        ListIterator iter = environmentStack.listIterator(environmentStack.size());
        while(iter.hasPrevious()){
            FunctionEnvironmentRecord temp = ((FunctionEnvironmentRecord)iter.previous());
            String name = temp.getName();
            int line = temp.getCurrentLn();
            if(name==null){
                name = "main";
            }
            callStack+= name+ " at line "+line+"\n";
            if(iter.hasPrevious()){
                callStack+="Called from ";
            }
        }
        System.out.println(callStack);
    }
}
