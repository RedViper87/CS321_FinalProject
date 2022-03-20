import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class User_Model {

    private String username;
    private String password;

    public void setUsername(String u){
        username = u;
    }

    public void setPassword(String p){
        password = p;
    }

    public void addNewUser() throws IOException {
        JsonObject newUser = new JsonObject();
        newUser.addProperty("Username", username);
        newUser.addProperty("Password", password);
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

            Path path = Paths.get("UserData.json");
            Charset charset = StandardCharsets.UTF_8;
            String content = new String(Files.readAllBytes(path), charset);
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

    }

}
