package interpreter.debugger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ieke2_yd0he2l
 */
public class FunctionEnvironmentRecord {
    private int startLn;
    private int currentLn;
    private int endLn;
    private String name;
    private Table symbolTable;
    
    
    public FunctionEnvironmentRecord(){
        symbolTable = new Table();
        startLn=currentLn=endLn=0 ;
    }
    
     public void beginScope() {
        symbolTable.beginScope();
    }
     
    //Set the Name, Starting Line,and Ending Line of the FunctionEnvironmentRecord 
    public void setFunctionInfo(String name, int startLn, int endLn) {
        this.name =name;
        this.startLn = startLn;
        this.endLn = endLn;
        this.currentLn = startLn;
    }
    
    //Sets the Current Line
    public void setCurrentLineNumber(int currentLn) {
        this.currentLn = currentLn;
    }
    
    //Adds Variable to the SymbolTable with id and value
    public void setVarVal(String a, int i){ 
        beginScope();
        symbolTable.put(a, i);
    }
    
    //Pops the symbol the number of time indicated by user
    public void doPop(int num) {
        symbolTable.doPop(num);
    }
    
    public String getName(){
        return name;
    }
    
    public int getCurrentLn(){
        return currentLn;
    }
    
    public int getStartLn(){
        return startLn;
    }
    public int getEndLn(){
        return endLn;
    }
    
    public Table getTable(){
        return symbolTable;
    }
            
}
