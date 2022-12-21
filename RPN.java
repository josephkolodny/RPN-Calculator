//****************************************************************
//Author: Joseph Kolodny
//RPN Caclulator
//12/6/2022
//****************************************************************

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Stack;

public class RPN {
  // Perform operation based on given operands and operations, if invalid
  // operation then throw exception
  public static int performOperation(int op1, int op2, String operation) throws Exception {
    switch (operation) {
      case "+":
        return op1 + op2;
      case "-":
        return op1 - op2;
      case "*":
        return op1 * op2;
      case "/":
        return op1 / op2;
      case "^":
        return (int) Math.pow(op1, op2);
      default:
        throw new Exception("Invalid operand");
    }
  }

  public static int solveRPN(String[] expression) throws Exception {
    Stack<Integer> stack = new Stack<>();
    for (String element : expression) {
      try {
        // If element is operand then push on satck
        int num = Integer.parseInt(element);
        stack.push(num);
      } catch (Exception e) {
        // If not operand then pop two operands form stack, perform operation and push
        // result on stack
        int op2 = stack.pop();
        int op1 = stack.pop();
        stack.push(performOperation(op1, op2, element));
      }
    }
    return stack.pop(); // return final result
  }

  public static void main(String[] args) {
    // Filename input
    Scanner keyboard = new Scanner(System.in);
    System.out.print("Enter filename: ");
    String filename = keyboard.nextLine();
    keyboard.close();

    try {
      File inputFile = new File(filename);
      Scanner scanner = new Scanner(inputFile);
      // Read file line by line, read every expression and solve it and show result
      while (scanner.hasNextLine()) {
        String line = scanner.nextLine();
        String[] expression = line.split(" ");
        System.out.print("Soulution of [" + line + "]: ");
        try {
          System.out.println(solveRPN(expression));
        } catch (Exception e) {
          System.out.println("Malformed expression");
        }
      }
      scanner.close();
    } catch (FileNotFoundException e) {
      System.out.println(e.getMessage());
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }
}
