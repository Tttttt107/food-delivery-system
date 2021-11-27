import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Scanner;

public class function2{

    public static void main(String[] args) throws IOException {
        //read the test file and store the data into ArrayList
        File file = new File("/Users/caitong/Documents/2020S2/COMP3600/assignment/final project/final-u6619441/Program-u6619441/test/test-f2-1.txt");
        Scanner scanner = new Scanner(file);
        List<String> AL = new ArrayList<String>();
        while (scanner.hasNextLine()) {
            if (scanner.hasNextLine()) {
                AL.add(scanner.nextLine());
            } else {
                scanner.nextLine();
            }
        }
        System.out.println("Input Size: " + AL.size());

        long starTime = System.currentTimeMillis();
        HashTable ht = new HashTable(AL.size());
        ht.insert(new Item(23,"KM"));
        ht.insert(new Item(12,"CD"));
        ht.insert(new Item(99,"CS"));
        ht.insert(new Item(102,"BJ"));
        ht.insert(new Item(39,"SH"));
        ht.insert(new Item(3090,"SZ"));
        ht.insert(new Item(21,"GZ"));

        System.out.println(ht.find(23).getValue());
        System.out.println(AL);

        long endTime = System.currentTimeMillis();
        System.out.println("Total Time："+(endTime - starTime)+"ms");
    }
}


    //define each data item
    class Item {
        public int deadline;
        public String address;
        public Item next;

        public Item(int deadline, String address) {
            this.deadline = deadline;
            this.address = address;
        }

        public int getKey() {
            return deadline;
        }

        public String getValue() {
            return address;
        }
    }


    class LinkList {
        //define the head pointer
        private Item head;

        public void LinkList() {
            //the head pointer is an assistant node, so it is null
            head = null;
        }

        //insert data item
        public void insert(Item item) {
            int key = item.getKey();
            //previous item
            Item previous = null;
            Item current = head;

            //current item is not empty, and need to add the element with greater key
            while (current != null && key > current.getKey()) {
                previous = current;
                current = current.next;
            }
            //if the previous node is empty, then the list is empty, head will points to the current item
            if (previous == null)
                head = item;
            else
                previous.next = item;
                item.next = current;
        }

        public Item find(int deadline) {
            Item current = head;

            while (current != null && current.getKey() <= deadline) {
                //find the target item
                if (current.getKey() == deadline)
                    return current;
                //continue to find next item
                current = current.next;
            }
            //fail finding
            return null;
        }
    }

    class HashTable {
        private LinkList[] HashArray;
        private int ArraySize;

        //initialize hashtable
        public HashTable(int size) {
            ArraySize = size;
            HashArray = new LinkList[ArraySize];
            for (int i = 0; i < ArraySize; i++)
                HashArray[i] = new LinkList();
        }

        public HashTable() {
            HashArray = new LinkList[ArraySize];
        }

        //compute the hash function
        public int hashFunction(int deadline) {
            return deadline % ArraySize;
        }

        //insert the item, first find the key, and then compute hashfunction and insert it to the corresponding position
        public void insert(Item item) {
            int deadline = item.getKey();
            int HashValue = hashFunction(deadline);
            HashArray[HashValue].insert(item);
        }

        //compute the hashfunction of target item and then find it
        public Item find(int deadline) {
            int HashValue = hashFunction(deadline);
            Item item = HashArray[HashValue].find(deadline);
            return item;
        }
    }