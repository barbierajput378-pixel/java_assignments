import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean running = true;
        
        System.out.println("=== Stack Utilities Application ===");
        
        while (running) {
            System.out.println("\n-----------------------------");
            System.out.println("Enter your choice :");
            System.out.println("1. Reverse a string");
            System.out.println("2. Postfix Evaluator loop");
            System.out.println("3. Exit Application");
            System.out.print("Choice: ");
            
            // Validate that user entered an integer selection
            if (!sc.hasNextInt()) {
                System.out.println("Error: Please enter a valid number (1, 2, or 3).");
                sc.nextLine(); // Clear the junk input string
                continue;
            }
            
            int choice = sc.nextInt();
            sc.nextLine(); // Consumes the leftover newline character from the buffer!

            switch(choice) {
                case 1 : 
                    System.out.print("\nEnter string to reverse: ");
                    String str = sc.nextLine();
                    CharStack charStack = new CharStack(str.length());
                    String reversedStr = charStack.reverseString(str);
                    System.out.println("Reversed String: " + reversedStr);
                    break;

                case 2 :
                    System.out.println("\n=== Postfix Evaluator Mode ===");
                    System.out.println("Enter your expression with spaces (e.g., '10 5 + 3 *')");
                    System.out.println("Type 'back' to return to the main menu.");
                    
                    while (true) {
                        System.out.print("\nEnter expression: ");
                        String input = sc.nextLine().trim();
                        
                        if (input.equalsIgnoreCase("back")) {
                            System.out.println("Returning to main menu...");
                            break;
                        }
                        
                        if (input.isEmpty()) {
                            continue;
                        }
                        
                        String[] expression = input.split("\\s+");
                        System.out.println("Evaluating tokens: " + Arrays.toString(expression));
                        
                        ArrayStack stackExecutor = new ArrayStack(expression.length + 2);
                        int result = stackExecutor.evalPostfix(expression);
                        System.out.println("Final Result: " + result);
                    }
                    break;
                    
                case 3:
                    System.out.println("Exiting Application. Goodbye!");
                    running = false;
                    break;
                    
                default:
                    System.out.println("Invalid option. Please choose 1, 2, or 3.");
                    break;
            }
        }
        sc.close();
    }
}

class ArrayStack {
    int Stack[];
    int top;
    int Maxsize;

    public ArrayStack(int capacity) {
        this.Maxsize = capacity;
        this.Stack = new int[capacity];
        this.top = -1; 
    }

    boolean isFull() {
        return top == Maxsize - 1;
    }

    boolean isEmpty() {
        return top == -1;
    }

    void push(int element) {
        if (isFull()) {
            System.out.println("Stack overflow! Cannot push " + element);
        } else {
            top++;               
            Stack[top] = element; 
        }
    }

    int pop() {
        if (isEmpty()) {
            return Integer.MIN_VALUE; 
        } else {
            int element = Stack[top]; 
            top--;                    
            return element; 
        }
    }

    int evalPostfix(String[] tokens) {
        ArrayStack s = new ArrayStack(tokens.length + 2);
        
        for(String t: tokens) {
            if (t == null || t.trim().isEmpty()) {
                continue;
            }
            
            if(t.matches("-?\\d+")) {
                s.push(Integer.parseInt(t));
            } else {
                int b = s.pop();
                int a = s.pop();
                
                if (a == Integer.MIN_VALUE || b == Integer.MIN_VALUE) {
                    System.out.println("Error: Missing operands. Invalid expression syntax.");
                    return 0;
                }
                
                switch(t) {
                    case "+": s.push(a + b); break;
                    case "-": s.push(a - b); break;
                    case "*": s.push(a * b); break;
                    case "/": 
                        if (b == 0) {
                            System.out.println("Error: Division by zero");
                            return 0;
                        }
                        s.push(a / b);
                        break;
                    default:
                        System.out.println("Error: Unknown token '" + t + "'");
                        return 0;
                }
            }
        }
        
        int finalResult = s.pop();
        if (!s.isEmpty()) {
            System.out.println("Error: Too many operands left over in expression.");
            return 0;
        }
        
        return finalResult;
    }
}

class CharStack {
    char[] Stack;
    int top;
    int MaxSize;

    public CharStack(int capacity) {
        this.MaxSize = capacity;
        this.Stack = new char[capacity];
        this.top = -1;
    }
    
    boolean isEmpty() {
        return top == -1;
    }
    
    void push(char element) {
        if (top != MaxSize - 1) {
            Stack[++top] = element;
        }
    }
    
    char pop() {
        if (isEmpty()) {
            return '\0';
        }
        return Stack[top--];
    }
    
    String reverseString(String input) {
        for (int i = 0; i < input.length(); i++) {
            push(input.charAt(i));
        }

        StringBuilder reversed = new StringBuilder();
        while (!isEmpty()) {
            reversed.append(pop());
        }
        return reversed.toString();
    }
}
