import java.util.*;

// ----------- Stack implemented using Array -----------
class ArrayStack {
    private int maxSize; // Maximum capacity of stack
    private int[] stackArr; // Array to store stack elements
    private int top; // Index of top element (-1 if empty)

    // Constructor to initialize stack with given size
    public ArrayStack(int size) {
        if (size <= 0) {
            System.out.println("Invalid stack size. Setting default size = 10");
            size = 10; // Default size if invalid input
        }
        this.maxSize = size;
        this.stackArr = new int[maxSize];
        this.top = -1; // Stack is empty initially
    }

    // Check if stack is full
    public boolean isFull() {
        return (top == maxSize - 1);
    }

    // Check if stack is empty
    public boolean isEmpty() {
        return (top == -1);
    }

    // Push an element into the stack
    public void push(int x) {
        if (isFull()) {
            System.out.println("Stack Overflow! Cannot push " + x);
        } else {
            stackArr[++top] = x; // Increment top, then insert element
        }
    }

    // Pop an element from the stack
    public int pop() {
        if (isEmpty()) {
            System.out.println("Stack Underflow! Cannot pop.");
            return -1; // Returning -1 to indicate failure
        } else {
            return stackArr[top--]; // Return top element and decrement top
        }
    }

    // Get current number of elements in stack
    public int size() {
        return top + 1;
    }
}

// ----------- Main class implementing Stack Applications -----------
public class Main {
    // ----------- Evaluate postfix expression using custom ArrayStack -----------
    static int evaluatePostfix(String exp) {
        // Split input into tokens (operands/operators separated by spaces)
        String[] tokens = exp.trim().split("\\s+");
        ArrayStack s = new ArrayStack(100); // Stack of size 100 for evaluation

        for (String token : tokens) {
            // If token is an integer (operand)
            if (token.matches("-?\\d+")) {
                s.push(Integer.parseInt(token));
            }
            // If token is an operator (+, -, *, /)
            else {
                // Must have at least 2 operands to perform an operation
                if (s.size() < 2) {
                    System.out.println("Invalid Postfix Expression: Insufficient operands");
                    return Integer.MIN_VALUE;
                }

                int val2 = s.pop(); // Second operand
                int val1 = s.pop(); // First operand
                switch (token) {
                    case "+":
                        s.push(val1 + val2);
                        break;

                    case "-":
                        s.push(val1 - val2);
                        break;

                    case "*":
                        s.push(val1 * val2);
                        break;

                    case "/":
                        if (val2 == 0) {
                            System.out.println("Division by zero error!");
                            return Integer.MIN_VALUE;
                        }
                        s.push(val1 / val2);
                        break;

                    default:
                        System.out.println("Invalid Operator: " + token);
                        return Integer.MIN_VALUE;
                }
            }
        }

        // Final validation: There must be exactly one element left in stack
        if (s.size() != 1) {
            System.out.println("Invalid Postfix Expression: Extra operands or operators");
            return Integer.MIN_VALUE;
        }
        return s.pop(); // Final result
    }

    // ----------- Reverse a string using built-in Stack -----------
    static String reverseString(String str) {
        Stack<Character> st = new Stack<>();

        // Push all characters into stack
        for (char c : str.toCharArray()) {
            st.push(c);
        }

        // Pop all characters to build reversed string
        StringBuilder rev = new StringBuilder();
        while (!st.isEmpty()) {
            rev.append(st.pop());
        }
        return rev.toString();
    }

    // ----------- Main Menu for testing stack applications -----------
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n------ MENU ------");
            System.out.println("1. Evaluate Postfix Expression");
            System.out.println("2. Reverse a String");
            System.out.println("3. Exit");
            System.out.print("Enter your Choice (1-3): ");

            // Validate menu choice input
            if (!sc.hasNextInt()) {
                System.out.println("Invalid input! Please enter a number (1-3).");
                sc.next();
                continue;
            }

            int choice = sc.nextInt();
            sc.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.println("Enter Postfix Expression (Use Spaces, e.g., '7 5 + 2 *'):");
                    String exp = sc.nextLine().trim();
                    if (exp.isEmpty()) {
                        System.out.println("Expression cannot be empty!");
                        break;
                    }
                    int result = evaluatePostfix(exp);
                    if (result != Integer.MIN_VALUE) {
                        System.out.println("Result: " + result);
                    }
                    break;

                case 2:
                    System.out.println("Enter a string to reverse:");
                    String str = sc.nextLine();
                    if (str.isEmpty()) {
                        System.out.println("String cannot be empty!");
                        break;
                    }
                    String rev = reverseString(str);
                    System.out.println("Reversed String: " + rev);
                    break;

                case 3:
                    System.out.println("Exiting program...");
                    sc.close();
                    return; // Exit the program

                default:
                    System.out.println("Invalid Choice! Please select between 1 and 3.");
                    break;
            }
        }
    }
}