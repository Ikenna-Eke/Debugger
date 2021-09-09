/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interpreter;

import interpreter.bytecodes.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Vector;
import java.util.StringTokenizer;


/**
 *
 * @author ieke2_yd0he2l
 */
public class ByteCodeLoader extends Object {
    private BufferedReader byteCodeFile;
    
    public ByteCodeLoader(String programFile) throws IOException{
        byteCodeFile = new BufferedReader(new FileReader(programFile));
        
    }
    
    //Loads ByteCodes into Program
    public Program loadCodes() throws IOException{
        Program program = new Program();
        Vector<String> argsList = new Vector<String>();
        String currentLn = byteCodeFile.readLine();
        while(currentLn !=null){
            StringTokenizer byteCodeToken = new StringTokenizer(currentLn);
            String byteCodeName = byteCodeToken.nextToken();
            if(byteCodeToken != null){
                argsList.clear(); //Clears argsList
                String codeClass = CodeTable.get(byteCodeName);
                //Checks if ByteCode has args and puts them in argsList 
                while(byteCodeToken.hasMoreTokens()){
                    argsList.add(byteCodeToken.nextToken());
                }
                try{
                    if(codeClass !=null){
                        //Create instance of ByteCode
                        ByteCode bytecode = (ByteCode)(Class.forName("interpreter.bytecodes."+codeClass).newInstance());
                        bytecode.init(argsList);
                        program.add(bytecode);
                    }
                    currentLn = byteCodeFile.readLine();
                }catch(InstantiationException|IllegalAccessException|ClassNotFoundException e){
                     System.out.println("Exception occurred " + e);
                }
            }
        }
        //Fixes the target address for ByteCodes that have jump address
        program.resolveAddress();
        return program;
    }
    
}
