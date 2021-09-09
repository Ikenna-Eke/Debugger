/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interpreter.bytecodes;

import interpreter.VirtualMachine;
import java.util.Vector;

/**
 *
 * @author ieke2_yd0he2l
 */
public class BopCode extends ByteCode {
    private String operator;

    @Override
    public void init(Vector<String> args) {
        operator = args.get(0);
    }

    @Override
    public void execute(VirtualMachine vm) {
        int operand2 =  vm.popRunStack();
        int operand1 =  vm.popRunStack();
        
        if("+".equals(operator)){
            vm.pushRunStack(operand1+operand2);
        }
        if("-".equals(operator)){
            vm.pushRunStack(operand1-operand2);
        }
        if("*".equals(operator)){
            vm.pushRunStack(operand1 * operand2);
        }
        if("==".equals(operator)){
            if(operand1 == operand2){
                vm.pushRunStack(1);
            }
            else{
                vm.pushRunStack(0);
            }
        }
        if("/".equals(operator)){
            vm.pushRunStack(operand1/operand2);
        }
        if("!=".equals(operator)){
            if(operand1 != operand2){
                vm.pushRunStack(1);
            }
            else{
                vm.pushRunStack(0);
            }
        }
        if("<".equals(operator)){
            if(operand1 < operand2){
                vm.pushRunStack(1);
            }
            else{
                vm.pushRunStack(0);
            }
        }
        if("<=".equals(operator)){
            if(operand1 <= operand2){
                vm.pushRunStack(1);
            }
            else{
                vm.pushRunStack(0);
            }
        }
        if(">".equals(operator)){
            if(operand1 > operand2){
                vm.pushRunStack(1);
            }
            else{
                vm.pushRunStack(0);
            }
        }
        if(">=".equals(operator)){
            if(operand1 >= operand2){
                vm.pushRunStack(1);
            }
            else{
                vm.pushRunStack(0);
            }
        }
        if("&".equals(operator)){
            if(operand1 ==1 && operand1==2){
                vm.pushRunStack(1);
            }
            else{
                vm.pushRunStack(0);
            }
        }
        if("|".equals(operator)){
            if(operand1 == 0 && operand2==0){
                vm.pushRunStack(0);
            }
            else{
                vm.pushRunStack(1);
            }
        }
    }

    @Override
    public String toString() {
        String output = "BOP "+ operator;
        return output;
    }
    
}
