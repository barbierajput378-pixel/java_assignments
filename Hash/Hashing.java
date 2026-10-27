pakage hash;
import java.util.*;
//class contact
class Contact{
    String phone;
    String name;
    Contact(String name , String phone){
        this.name = name;
        this.phone = phone;
    }
    @Override
    public String toString(){
        return name+ "->"+phone;
    }
}

//calss hash
class Hashe{
    private int size;
    private Contact[] table;
Hashe(int size){
        if(size<0)
            throw new IllegalArgumentException("Can't be negative.");
        this.size = size;
        table = new Contact[size];
    }

    //hash
    private int hash(String key){
        int sum = 0;
        for(int i = 0; i<key.length();i++){
            sum += key.charAt(i);
        }
        return sum % size;
    }

//ckeck name
    private boolean isValidName(String name){
        return(name != null && name.matches("[a-zA-Z]+") && !name.trim().isEmpty());
    }

//check phone
    private boolean isValidPhone(String phone){
        return(phone != null && phone.matches("\\d{7,15}"));
    }

//add
    public void insert(String name, String phone,Scanner sc){
if(!isValidName(name)){
    System.out.println("Invalid name");
return;
}
int hashVal = hash(name.toLowerCase());
int start = hashVal;
while(table[hashVal]!=null){
    if(table[hashVal].name.equalsIgnoreCase(name)){
        System.out.println("Already exist..");
    }
    hashVal = (hashVal + 1) % size;
    if(hashVal == start)
        System.out.println("Hash table is full..");
}

if(!isValidPhone(phone)){
    System.out.println("Invalid phone.. try again.");
    phone = sc.nextLine().trim();
}

table[hashVal]= new Contact(name, phone);
System.out.println("Added sucessfully..");
    }

//search
public void search(String name){
    if(!isValidName(name)){
        System.out.println("Invalid name");
        return;
    }
    int hashVal = hash(name.toLowerCase());
    int start = hashVal;
    while(table[hashVal]!=null){
        if(table[hashVal].name.equalsIgnoreCase(name)){
            System.out.println("Found ->"+hashVal+":"+table[hashVal]);
            return;
        }
        hashVal = (hashVal+1)%size;
        if(hashVal==start)
            break;
        }
    }

//delete
public void delete(String name){
    if(!isValidName(name)){
    System.out.println("Invalid name");
    return;
    }
    int hashVal = hash(name.toLowerCase());
    int start = hashVal;
    while(table[hashVal]!=null){
        if(table[hashVal].name.equalsIgnoreCase(name)){
            table[hashVal]=null;
            System.out.println("deleted successfully..");
        }
        hashVal =(hashVal+1)%size;
        if(hashVal == start)
            break;
    }
}

//display
public void display(){
    System.out.println("---Contact List---");
    boolean isEmpty = true;
    for(int i = 0; i<size ; i++){
if(table[i]!=null){
    System.out.println("Slot:->"+i+":"+table[i]);
    isEmpty = false;
}else{
    System.out.println("Slot:->"+i+": [Empty]");
}
    }
    if(isEmpty){
        System.out.println("The table is empty!!..");
    }
}

}
//main class
public class Hashing {
    public static void main(String[] args) { // Fixed: String[] instead of Strings[]
        Scanner sc = new Scanner(System.in);
        int size = 0;

        // Fixed: Loop while size is <= 0 to get valid input first
        while (size <= 0) {
            System.out.print("Enter hash table size: ");
            String input = sc.nextLine().trim();
            if (input.matches("\\d+")) {
                size = Integer.parseInt(input);
                if (size <= 0) {
                    System.out.println("Size must be positive.");
                }
            } else {
                System.out.println("Invalid input. Enter a valid number.");
            }
        }

        // Now create the object safely after getting the valid size
    Hashe obj = new Hashe
    (size);
        int choice = 0;

        do {
            try {
                System.out.println("\n--- Hash Table ---");
                System.out.println("1.Add\n2. Search\n3. Delete\n4. Display\n5. Exit");
                System.out.print("Enter your choice: "); // Fixed comma typo
                choice = Integer.parseInt(sc.nextLine().trim());

                switch (choice) {
                    case 1:
                        System.out.print("Enter the name: ");
                        String name = sc.nextLine().trim();
                        System.out.print("Enter the phone: ");
                        String phone = sc.nextLine().trim();
                        obj.insert(name, phone, sc);
                        break;
                    case 2:
                        System.out.print("Enter name to search: ");
                        name = sc.nextLine().trim();
                        obj.search(name);
                        break;
                    case 3:
                        System.out.print("Enter name to delete: ");
                        name = sc.nextLine().trim();
                        obj.delete(name);
                        break;
                    case 4:
                        obj.display();
                        break;
                    case 5:
                        System.out.println("Exiting the program..."); // Fixed prinltn typo
                        break;
                    default:
                        System.out.println("Invalid choice..");
                }
            } catch (Exception e) {
                System.out.println("Invalid input..."); // Fixed missing semicolon
            }
        } while (choice != 5);

        sc.close();
    }
}