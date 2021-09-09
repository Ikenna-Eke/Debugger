/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interpreter;


import java.util.HashMap;

/**
 *
 * @author ieke2_yd0he2l
 */
public class CodeTable {
    private static HashMap<String, String> table = new HashMap<String, String>();
    private CodeTable(){};
    
    
    //Initialize CodeTable for the Interpreter
    public static void init(){
        table.put("ARGS","ArgsCode");
        table.put("BOP", "BopCode");
        table.put("CALL","CallCode");
        table.put("DUMP", "DumpCode");
        table.put("FALSEBRANCH","FalseBranchCode");
        table.put("GOTO","GoToCode");
        table.put("HALT", "HaltCode");
        table.put("LABEL", "LabelCode");
        table.put("LIT", "LitCode");
        table.put("LOAD", "LoadCode");
        table.put("POP", "PopCode");
        table.put("READ","ReadCode");
        table.put("RETURN","ReturnCode");
        table.put("WRITE", "WriteCode");
        table.put("STORE", "StoreCode");
    }
    
    //Initilize CodeTable for the Debugger
    public static void initDebugger(){
        table.put("CALL","debuggerByteCodes.DebugCallCode");
        table.put("LIT", "debuggerByteCodes.DebugLitCode");
        table.put("POP", "debuggerByteCodes.DebugPopCode");
        table.put("RETURN","debuggerByteCodes.DebugReturnCode");
        table.put("LINE","debuggerByteCodes.LineCode");
        table.put("FUNCTION", "debuggerByteCodes.FunctionCode");
        table.put("FORMAL", "debuggerByteCodes.FormalCode");
        
    }
    
    //Return the name of the ByteCode that is assign to that key
    public static String get(String code){
        return table.get(code);
    }
}
