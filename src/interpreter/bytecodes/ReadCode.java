/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interpreter.bytecodes;

import interpreter.VirtualMachine;
import java.util.Vector;
import java.util.Scanner;

/**
 *
 * @author ieke2_yd0he2l
 */
public class ReadCode extends ByteCode {
    Scanner scan = new Scanner(System.in);

    @Override
    public void init(Vector<String> args) {
        
    }

    @Override
    public void execute(VirtualMachine vm) {
        System.out.println("Enter an Integer: ");
        int input = scan.nextInt();
        vm.pushRunStack(input);
    }

    @Override
    public String toString() {
        return "READ";
    }
    
}
