
import java.util.*;
// contact class
class Contact{
	String name;
	String phone;
	Contact(String name,String phone){
		this.name = name;
		this.phone = phone;
	}
	@Override
	public String toString() {
		return name +"->"+phone;
	}
}
// hashing class
class Hashing{
	private int size;
	private Contact[] table;
	Hashing(int sum){
		if(size<0)
			throw new IllegalArgumentException("Size cann't negetive..");
		this.size = sum;
		table = new Contact[size];
	}
	//create hash
	private int hash(String key) {
		int sum = 0;
		for(int i = 0; i<key.length();i++) {
			sum += key.charAt(i);
		}
		return sum % size;
	}
	//check valid name
	private boolean isValidName(String name) {
		return (name != null && name.matches("[a-zA-Z]+"));
	}
	//checkk valid phone
	private boolean isValidPhone(String phone) {
		return (phone != null && phone.matches("\\d{7,15}"));
	}

	// add
	public void add(String name, String phone, Scanner sc) {
		if(!isValidName(name)) {
			System.out.println("Invalid name! Try again..");
			name = sc.nextLine();
		}
		int hashVal = hash(name.toLowerCase());
		int start = hashVal;
		
		while(table[hashVal]!= null) {
			if(table[hashVal].name.equalsIgnoreCase(name)) {
				System.out.println("Already exist...");
				return;
			}
			hashVal = (hashVal + 1)%size;
			if(hashVal == start) {
				System.out.println("Hash table is full..");
				return;
			}
		}
		if(!isValidPhone(phone)) {
			System.out.println("Invalid phone try again:");
			phone = sc.nextLine();
		}
		table[hashVal] = new Contact(name,phone);
		System.out.println("Added successfullyyyy...");
	}
	// search
	public void search(String name,Scanner sc) {
		if(!isValidName(name)) {
			System.out.println("Invalid name...Try again :");
			name = sc.nextLine();
		}
		int hashVal = hash(name.toLowerCase());
		int start = hashVal;
		while(table[hashVal]!= null) {
			if(table[hashVal].name.equalsIgnoreCase(name)) {
				System.out.println("Found : "+hashVal+"->"+table[hashVal]);
				return;
			}
			hashVal = (hashVal + 1)%size;
			if(hashVal == start) {
				break;
			}
		}
		System.out.println("Not found..");
	}
	// delete
	
	public void delete(String name,Scanner sc) {
		if(!isValidName(name)) {
			System.out.println("Invalid name...Try again :");
			name = sc.nextLine();
		}
		int hashVal= hash(name.toLowerCase());
		int start = hashVal;
		while(table[hashVal]!=null) {
			if(table[hashVal].name.equalsIgnoreCase(name)) {
				table[hashVal]=null;
				System.out.println("Deleted Scuuessfully...");
				return ;
			}
			hashVal = (hashVal + 1)%size;
			if(hashVal == start) 
				break;
		}
		System.out.println("Not found ..!");
	}
	// display
	public void display() {
		System.out.println("----Contact list----");
		boolean isEmpty = true;
		for(int i =0; i<size; i++) {
			if(table[i]!=null) {
				System.out.println("Slot : -> "+i+" : "+table[i]);
			}else {
				System.out.println("Slot : -> "+i+" : [Empty]");
			}
		}
		if(isEmpty) {
			System.out.println("Hash table is empty");
		}
	}
	
}

public class hash {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		try {
System.out.println("Enter size of the table : ");
			int size= sc.nextInt();
			sc.nextLine();
			if(size < 0)
				throw new IllegalArgumentException("Size must be positive..");
			Hashing h = new Hashing(size);
			int choice;
			do {
				System.out.println("---Menu---");
				System.out.println("1.Add \n2.Search \n3.Delete \n4.Display \n5.Exit");
				System.out.println("Enter your choice:");
				choice = sc.nextInt();
				sc.nextLine();
				switch(choice) {
				case 1 :
					System.out.println("Enter the name :");
					String name = sc.nextLine();
					System.out.println("Enter the phone :");
					String phone = sc.nextLine();
					h.add(name, phone, sc);
					break;
				case 2:
					System.out.println("Enter the name :");
					name = sc.nextLine();
					h.search(name, sc);
					break;
				case 3:
					System.out.println("Enter the name :");
					name = sc.nextLine();
					h.delete(name,sc);
					break;
				case 4:
					h.display();
					break;
				case 5:
					System.out.println("Existing the program...");
					break;
					default :
						System.out.println("Inalid input...");
				}
				
			}while(choice != 5);
			
		}catch(Exception e) {
			System.out.println("Invalid input error has occured..");
		}finally {
			sc.close();
		}
	}

}
