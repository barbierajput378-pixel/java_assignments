//node clsss
import java.util.*;
class Node{
    String keyword, meaning;
    Node left, right;
    Node(String keyword, String meaning){
        this.keyword = keyword;
        this.meaning = meaning;
        this.right =  null;
        this.left = null;
    }
}
//main
public class practice {
    static Scanner sc = new Scanner(System.in);
    static Node root = null;

    //insert
static Node insert (Node root, String keyword, String meaning){
    if(root == null)
        return new Node(keyword, meaning);
    int cmp = keyword.compareToIgnoreCase(root.keyword);
    if(cmp<0)
        root.left = insert (root.left,keyword,meaning);
    else if(cmp>0)
        root.right = insert (root.right,keyword,meaning);
    else
        System.out.println("Keyword already exist!!..");
    return root;
}

    //search
static Node search(Node root, String keyword){
    if( root== null || root.keyword.equalsIgnoreCase(keyword))
        return root;
    int cmp = keyword.compareToIgnoreCase(root.keyword);
    if(cmp<0)
        return search(root.left,keyword);
    else if(cmp>0)
        return search(root.right,keyword);
    else
        System.out.println("Keyword is found!!..");
    return root;
} 

    //ascending
static void ascending(Node root){
    if(root != null){
        ascending(root.left);
        System.out.println("->"+root.keyword+":"+root.meaning);
        ascending(root.right);
    }
}

    //descending
static void descending(Node root){
    if(root != null){
        descending(root.right);
        System.out.println("->"+root.keyword+":"+root.meaning);
        descending(root.left);
    }
}

    //delete
static Node delete(Node root, String keyword){
    if(root == null)
        return null;
    int cmp = keyword.compareToIgnoreCase(root.keyword);
    if(cmp<0)
        root.left = delete(root.left,keyword);
    else if(cmp>0)
        root.right = delete(root.right,keyword);
    else{
        if(root.left==null)
            return root.right;
        else if(root.right == null)
            return root.left;

        Node t = root.right;
        while(t.left != null)
            t = root.left;

        root.keyword = t.keyword;
        root.meaning = t.meaning;

        root.right = delete(root.right, t.keyword);
}
return root;
}
    //main
    public static void main(String[] args){
        int ch = 0;
do{
    try{
        System.out.println("--dictionary of books--");
System.out.println("1.Add\n2.search\n3.Update\n4.Ascending\n5.Descending\n6.Delete\n7.Exit");
System.out.println("Enter your choice :");
ch = sc.nextInt();
sc.nextLine();
switch(ch){
    case 1:
        System.out.println("Enter the keyword : ");
        String keyword = sc.nextLine().trim();
        System.out.println("Enter the meaning : ");
        String meaning = sc.nextLine().trim();
if(keyword.isEmpty()||meaning.isEmpty()){
    throw new IllegalArgumentException("Field can't be empty!!..");
}
if(search(root,keyword)!= null)
    System.out.println("Keyword already exist...");
else{
    root = insert(root,keyword,meaning);
    System.out.println("->"+keyword+":"+meaning);
    System.out.println("Added successfullyy....!!");
}
break;
        case 2:
            System.out.println("Enter the keyword :");
            keyword = sc.nextLine().trim();
Node t = search(root,keyword);
if(t==null)
    System.out.println("Not found...");
else
    System.out.println("->"+t.keyword+":"+t.meaning);
            break;
        case 3:
            System.out.println("Enter the keyword:");
            keyword = sc.nextLine().trim();
            t = search(root,keyword);
            if(t == null)
                System.out.println("Not found!!...");
            else{
                System.out.println("Enter new keyword :");
                t.keyword = sc.nextLine().trim();
                System.out.println("Updated successfullu");
            }
            
            break;
        case 4:
            ascending(root);
            break;
        case 5:
            descending(root);
            break;
        case 6:
            System.out.println("Enter the keyword :");
            keyword = sc.nextLine().trim();
            if(search(root,keyword)==null)
                System.out.println("Not found");
            else{
                delete(root,keyword);
                System.out.println("Deleted!!..");
            }
            break;
        case 7:
            System.out.println("Existing the program...");
            break;
            default :
            System.out.println("Invalid choice!!..");
}
    }catch(Exception e){
        System.out.println("Error...Invalid choice");
        sc.nextLine();
    }
}while(ch != 7);
sc.close();
    }
}
