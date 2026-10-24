/*
Name: Barbie Rajput
Rollno. : UCE2025404

*/
import java.util.Scanner;
import java.util.EmptyStackException;

interface StackADT<T> {
    void push(T item);
    T pop();
    T peek();
    boolean isEmpty();
    boolean isFull();
    int size();
}

class ArrayStack<T> implements StackADT<T> {
    private Object[] stack;
    private int top;
    private final int maxSize;
    private static final int DEFAULT_SIZE = 50;

    public ArrayStack() {
        this(DEFAULT_SIZE);
    }

    public ArrayStack(int maxStackSize) {
        this.maxSize = maxStackSize;
        this.stack = new Object[maxSize];
        this.top = -1;
    }

    @Override
    public boolean isFull() {
        return top == maxSize - 1;
    }

    @Override
    public boolean isEmpty() {
        return top == -1;
    }

    @Override
    public void push(T item) {
        if (isFull()) {
            System.out.println("Stack Overflow: cannot push, stack is full.");
            return;
        }
        stack[++top] = item;
    }

    @SuppressWarnings("unchecked")
    @Override
    public T pop() {
        if (isEmpty()) {
            System.out.println("Stack Underflow: cannot pop, stack is empty.");
            throw new EmptyStackException();
        }
        return (T) stack[top--];
    }

    @SuppressWarnings("unchecked")
    @Override
    public T peek() {
        if (isEmpty()) {
            System.out.println("Stack is empty, nothing to peek.");
            throw new EmptyStackException();
        }
        return (T) stack[top];
    }

    @Override
    public int size() {
        return top + 1;
    }

    public void display() {
        if (isEmpty()) {
            System.out.println("The stack is empty.");
            return;
        }
        System.out.print("The stack (top -> bottom) is: ");
        for (int i = top; i >= 0; i--) {
            System.out.print(stack[i] + " ");
        }
        System.out.println();
    }
}

class LinkedStack<T> implements StackADT<T> {

    private static class Node<T> {
        T data;
        Node<T> next;
        Node(T data, Node<T> next) {
            this.data = data;
            this.next = next;
        }
    }

    private Node<T> top;
    private int count;

    public LinkedStack() {
        top = null;
        count = 0;
    }

    @Override
    public boolean isFull() {
        return false;
    }

    @Override
    public boolean isEmpty() {
        return top == null;
    }

    @Override
    public void push(T item) {
        top = new Node<>(item, top);
        count++;
        System.out.println("New item is pushed onto the stack.");
    }

    @Override
    public T pop() {
        if (isEmpty()) {
            System.out.println("The stack is empty!!!");
            throw new EmptyStackException();
        }
        Node<T> temp = top;
        top = top.next;
        count--;
        return temp.data;
    }

    @Override
    public T peek() {
        if (isEmpty()) {
            System.out.println("The stack is empty!!!");
            throw new EmptyStackException();
        }
        return top.data;
    }

    @Override
    public int size() {
        return count;
    }

    public void display() {
        if (isEmpty()) {
            System.out.println("The stack is empty.");
            return;
        }
        System.out.print("The stack (top -> bottom) is: ");
        Node<T> ptr1 = top;
        while (ptr1 != null) {
            System.out.print(ptr1.data + " ");
            ptr1 = ptr1.next;
        }
        System.out.println();
    }
}

class PostfixEvaluator {

    public static int evaluate(String postfix) {
        StackADT<Integer> stack = new ArrayStack<>();
        String[] tokens = postfix.trim().split("\\s+");

        for (String token : tokens) {
            if (token.isEmpty()) continue;

            if (isOperator(token)) {
                if (stack.size() < 2) {
                    throw new IllegalArgumentException(
                        "Malformed postfix expression: not enough operands for operator '" + token + "'");
                }
                int operand2 = stack.pop();
                int operand1 = stack.pop();
                int result = applyOperator(operand1, operand2, token.charAt(0));
                stack.push(result);
            } else {
                stack.push(Integer.parseInt(token));
            }
        }

        if (stack.size() != 1) {
            throw new IllegalArgumentException("Malformed postfix expression: leftover operands on stack.");
        }
        return stack.pop();
    }

    private static boolean isOperator(String token) {
        return token.length() == 1 && "+-*/".contains(token);
    }

    private static int applyOperator(int operand1, int operand2, char operator) {
        switch (operator) {
            case '+': return operand1 + operand2;
            case '-': return operand1 - operand2;
            case '*': return operand1 * operand2;
            case '/':
                if (operand2 == 0) throw new ArithmeticException("Division by zero in postfix expression.");
                return operand1 / operand2;
            default:
                throw new IllegalArgumentException("Unknown operator: " + operator);
        }
    }
}

class StringReversal {

    public static String reverse(String input) {
        StackADT<Character> stack = new LinkedStack<>();

        for (char c : input.toCharArray()) {
            stack.push(c);
        }

        StringBuilder result = new StringBuilder();
        while (!stack.isEmpty()) {
            result.append(stack.pop());
        }
        return result.toString();
    }
}

public class StackLabAssignment {

    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        StackADT<Integer> arrayStack = new ArrayStack<>(10);
        StackADT<Integer> linkedStack = new LinkedStack<>();

        int choice;
        do {
            printMenu();
            choice = readInt("Enter your choice: ");

            switch (choice) {
                case 1: arrayStackMenu(arrayStack); break;
                case 2: linkedStackMenu(linkedStack); break;
                case 3: postfixMenu(); break;
                case 4: reversalMenu(); break;
                case 0: System.out.println("Exiting. Goodbye!"); break;
                default: System.out.println("Invalid choice, try again.");
            }
        } while (choice != 0);

        sc.close();
    }

    private static void printMenu() {
        System.out.println("\n==================== STACK LAB MENU ====================");
        System.out.println("1. Stack operations (Array implementation)");
        System.out.println("2. Stack operations (Linked-list implementation)");
        System.out.println("3. Evaluate a postfix expression");
        System.out.println("4. Reverse a string");
        System.out.println("0. Exit");
        System.out.println("==========================================================");
    }

    private static void arrayStackMenu(StackADT<Integer> stack) {
        System.out.println("\n-- Array-based Stack --");
        System.out.println("1.Push  2.Pop  3.Peek  4.Display  5.isEmpty  6.isFull  0.Back");
        int c = readInt("Choice: ");
        switch (c) {
            case 1:
                int val = readInt("Enter integer to push: ");
                stack.push(val);
                break;
            case 2:
                try {
                    System.out.println("Popped: " + stack.pop());
                } catch (EmptyStackException e) { }
                break;
            case 3:
                try {
                    System.out.println("Top element: " + stack.peek());
                } catch (EmptyStackException e) { }
                break;
            case 4:
                ((ArrayStack<Integer>) stack).display();
                break;
            case 5:
                System.out.println("isEmpty: " + stack.isEmpty());
                break;
            case 6:
                System.out.println("isFull: " + stack.isFull());
                break;
            default:
        }
    }

    private static void linkedStackMenu(StackADT<Integer> stack) {
        System.out.println("\n-- Linked-list-based Stack --");
        System.out.println("1.Push  2.Pop  3.Peek  4.Display  5.isEmpty  0.Back");
        int c = readInt("Choice: ");
        switch (c) {
            case 1:
                int val = readInt("Enter integer to push: ");
                stack.push(val);
                break;
            case 2:
                try {
                    System.out.println("Popped: " + stack.pop());
                } catch (EmptyStackException e) { }
                break;
            case 3:
                try {
                    System.out.println("Top element: " + stack.peek());
                } catch (EmptyStackException e) { }
                break;
            case 4:
                ((LinkedStack<Integer>) stack).display();
                break;
            case 5:
                System.out.println("isEmpty: " + stack.isEmpty());
                break;
            default:
        }
    }

    private static void postfixMenu() {
        System.out.print("Enter a postfix expression (single-digit operands, space-separated, e.g. \"6 5 2 3 + 8 * + 3 + *\"): ");
        sc.nextLine();
        String expr = sc.nextLine();
        try {
            int result = PostfixEvaluator.evaluate(expr);
            System.out.println("Result = " + result);
        } catch (IllegalArgumentException | ArithmeticException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void reversalMenu() {
        System.out.print("Enter a string to reverse: ");
        sc.nextLine();
        String s = sc.nextLine();
        System.out.println("Reversed string: " + StringReversal.reverse(s));
    }

    private static int readInt(String prompt) {
        System.out.print(prompt);
        while (!sc.hasNextInt()) {
            System.out.print("Please enter a valid integer: ");
            sc.next();
        }
        return sc.nextInt();
    }
}