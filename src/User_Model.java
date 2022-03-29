import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Scanner;

public class User_Model {

    private String username;
    private String password;
    // ArrayList<Watchlist_Model> listOfWatchlists;
    public void setUsername(String u){
        username = u;
    }

    public String getUsername(){
        return username;
    }

    public String getPassword(){
        return password;
    }

    public void setPassword(String p){
        password = p;
    }

    public boolean addNewUser() throws IOException {

        /* Make sure there is not a duplicate */
        Path path = Paths.get("UserData.json");
        Charset charset = StandardCharsets.UTF_8;
        String content = new String(Files.readAllBytes(path), charset);
        Gson gson = new Gson();
        User_Model[] list;
        list = gson.fromJson(content,User_Model[].class);
        if(list == null){
            return false;
        }
        ArrayList<User_Model> arrayList = new ArrayList<>();
        Collections.addAll(arrayList,list);

        /* check if user is in list */
        for(User_Model user:arrayList){
            if(user.getUsername().equals(username)){
                if(user.getPassword().equals(password)){
                    return false;
                }
            }
        }

        JsonObject newUser = new JsonObject();
        newUser.addProperty("username", username);
        newUser.addProperty("password", password);
        /* Cannot figure out GSON right now, works but fix later*/
       File f = new File("UserData.json");
        if(f.length() == 0){
            try (FileWriter file = new FileWriter("UserData.json", true)){
                file.write("[\n"+String.valueOf(newUser));
                file.write("\n]");
                file.flush();
            }catch(IOException ex){
                ex.printStackTrace();
            }
        }else{
            content = content.replaceAll("]", "");
            Files.write(path, content.getBytes(charset));
            String content2 = new String(Files.readAllBytes(path), charset);
            int index = content2.lastIndexOf("}");
            String content3 = content2.substring(0,index)+ "},"+ content2.substring(index+1);
            Files.write(path, content3.getBytes(charset));

            try (FileWriter file = new FileWriter("UserData.json", true)){
                file.write(String.valueOf(newUser));
                file.write("\n]");
                file.flush();
            }catch(IOException ex){
                ex.printStackTrace();
            }

        }
        return true;
    }

    public boolean checkUser() throws IOException {
        Path path = Paths.get("UserData.json");
        Charset charset = StandardCharsets.UTF_8;
        String content = new String(Files.readAllBytes(path), charset);
        Gson gson = new Gson();
        User_Model[] list;
        list = gson.fromJson(content,User_Model[].class);
        if(list == null){
            return false;
        }
        ArrayList<User_Model> arrayList = new ArrayList<>();
        Collections.addAll(arrayList,list);

        /* check if user is in list */
        for(User_Model user:arrayList){
            if(user.getUsername().equals(username)){
                if(user.getPassword().equals(password)){
                    return true;
                }
            }
        }
        return false;
    }

}
