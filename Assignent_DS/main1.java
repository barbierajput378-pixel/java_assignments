import java.util.*;

class Books{
    String name, author;
    int isbn;
    Books(String n, String a,int isbn){
        this.name = n;
        this.author = a;
        thisd.isbn = isbn;
    }
}
public class main1{
    static Books b[] = new Books[50];
    static int n = 0;
    static Scanner sc = new Scanner (System.in);
    static void add(){
        if(n==50)
            throw new illegalexception("Library is full");
        System.out.println("Enter name :");
        String name = sc.nextLine();
        System.out.println("Enter author :");
        String author = sc.nextLine();
        System.out.println("Enter isbn :");
        int isbn = sc.nextInt();
        for(int i =0; i<n; i++){
            if(b[i].isbn=isbn)
                throw new illegalexception("Cant add book with same isbn number");
            b[n++]=new book(name,author,isbn);
        }
        System.out.println("Book added successfully");
    }

    static void display(){
        for(int i =0; i<n ; i++){
            System.out.println(i+"|"+b[i].name+"|"+b[i].isbn+"|"+b[i].author);
        }
    }
    static void sort(){
        for(int i =0; i<n; i++){
            for(int j =0; j<n ;j++){
                if(b[j] < b[j+1]){
                    Book temp = b[j];
                    b[j] = b[j+1];
                    b[j+1]= temp;
                }
            }
        }display();
    }

    public static void main(String[] args) {
        static int choice ;
        do{
        try { System.out.println("lirary");
            System.out.println("/n1.insert /n2.display /n3.sort /n4.search isbn /n5.searcg author/n6.exist.");
            switch(choice){
                case 1 :
                    add(); break;
                    case 2: 
                    display();break;
                    case 3: 
                    searchisbn(); break;
                    case 4: 
                    searchauthor(); break;
                    case 5: 
                    sort(); break;
                    case 6:
                        System.out.println("Exist");
                        default:
                            System.out.println("Invalid choice");
            }
        } catch (Exception e){
            System.out.println(e.getmessage());
        }
    }while(choice != 6);
    sc.close();
    }
}
//give the correct code 