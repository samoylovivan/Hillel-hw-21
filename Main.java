package ivan.samoylov;

import java.io.IOException;

public class Main {
    final static String FILE = "mydb.txt";

    public static void main(String[] args) throws IOException {

        FileStorage fs = new FileStorage(FILE);
        fs.addUser(new User("Ben", 27));
        fs.addUser(new User("Lora", 28));
        fs.addUser(new User("Robert", 47));
        fs.addUser(new User("Rick", 47));
        System.out.println(fs.getAllUsers());
        fs.removeAll();
        System.out.println(fs.getAllUsers());
        fs.addUser(new User("Patrick", 27));
        fs.addUser(new User("Bob", 28));
        fs.addUser(new User("Elizabeth", 47));
        fs.addUser(new User("Nick", 47));
        System.out.println(fs.getAllUsers());
        fs.removeUser(3);
        System.out.println(fs.getAllUsers());
        System.out.println(fs.getUser(4));
    }
}
