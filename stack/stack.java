import java.util.*;

class MyStack {
    int a[];
    int top = -1;

    MyStack(int size) {
        a = new int[size];
    }

    void push(int x) {
        if (top == a.length - 1)
            throw new RuntimeException("Stack Overflow");
        a[++top] = x;
    }

    int pop() {
        if (top == -1)
            throw new RuntimeException("Stack Underflow");
        return a[top--];
    }

    boolean isEmpty() {
        return top == -1;
    }

    int size() {
        return top + 1;
    }
}

public class stack {

    static int evaluate(String expr) {
        String t[] = expr.trim().split("\\s+");
        MyStack s = new MyStack(t.length);

        for (String x : t) {
            if (x.matches("-?\\d+")) {
                s.push(Integer.parseInt(x));
            } else {
                int b = s.pop();
                int a = s.pop();

                switch (x) {
                    case "+": s.push(a + b); break;
                    case "-": s.push(a - b); break;
                    case "*": s.push(a * b); break;
                    case "/":
                        if (b == 0)
                            throw new ArithmeticException("Division by zero");
                        s.push(a / b);
                        break;
                    default:
                        throw new IllegalArgumentException("Invalid operator");
                }
            }
        }

        if (s.size() != 1)
            throw new IllegalArgumentException("Invalid postfix expression");

        return s.pop();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice = 0;

        do {
            try {
                System.out.println("\n1. Reverse String");
                System.out.println("2. Evaluate Postfix");
                System.out.println("3. Exit");
                System.out.print("Enter choice: ");

                choice = sc.nextInt();
                sc.nextLine();

                switch (choice) {
                    case 1:
                        System.out.print("Enter string: ");
                        String str = sc.nextLine();

                        MyStack s = new MyStack(str.length());
                        for (int i = 0; i < str.length(); i++)
                            s.push(str.charAt(i));

                        System.out.print("Reversed string: ");
                        while (!s.isEmpty())
                            System.out.print((char) s.pop());

                        System.out.println();
                        break;

                    case 2:
                        System.out.print("Enter postfix expression: ");
                        String expr = sc.nextLine();
                        System.out.println("Result: " + evaluate(expr));
                        break;

                    case 3:
                        System.out.println("Exiting...");
                        break;

                    default:
                        System.out.println("Invalid choice");
                }

            } catch (InputMismatchException e) {
                System.out.println("Error: Enter a valid integer choice.");
                sc.nextLine();
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }

        } while (choice != 3);

        sc.close();
    }
}