package ivan.samoylov;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class FileStorage implements Storage{
    private int id = 0;
    private static Gson gson = new Gson();
    private static HashMap<Integer, User> allUsers = new HashMap<Integer, User>();
    private static String file;

    public FileStorage(String file) {
        this.file = file;
        readFromFile();
    }

    public void writeToFile()
    {
        try {
            PrintWriter myWriter = new PrintWriter(this.file);
            File file = new File(this.file);
            if(!file.exists()) {
                file.createNewFile();
            }
            myWriter.write(gson.toJson(allUsers.values()));
            myWriter.close();
        } catch (Exception e) {
        }
    }

    public void readFromFile() {
        try {
            BufferedReader reader = new BufferedReader( new FileReader(this.file));
            User usr = gson.fromJson(reader, User.class);
            addUser(usr);
        }
        catch (Exception e) {
            }
    }

    public void removeAll() {
        id = 0;
        allUsers.clear();
        writeToFile();
    }
    public void removeUser(int id) {
        if (allUsers.containsKey(id)){
            allUsers.remove(id);
        }
        else{
            System.out.println("id " + id + " not found");
        }
        writeToFile();
    }
    public void removeUserByName(String name) {
        for (User usr: allUsers.values()) {
            if (usr.getName().equals(name)) {
                allUsers.remove(usr.getId());
            }
        }
        writeToFile();
    }
    public void addUser(User user) {
      this.id++;
      user.setId(id);
      allUsers.put(this.id, user);
      writeToFile();
    }
    public void updateUser(User user) {
       removeUser(user.getId());
       addUser(user);
       writeToFile();
    }
    public User getUser(int id) {
        if (allUsers.containsKey(id)){
            return allUsers.get(id);
        }
        return null;
    }
    public List<User> getAllUsers() {
        return new LinkedList<User>(allUsers.values());
    }
}
