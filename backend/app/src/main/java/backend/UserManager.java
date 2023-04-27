package backend;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;

public class UserManager {

    private HashMap<User, String> listaUsuarios;

    
    public UserManager() {
        this.listaUsuarios = new HashMap<User,String>();

    }

    public void adicionar(){
        String nome = "a";
        String senha = "b";
        User usuario = new User(nome, senha);
        this.listaUsuarios.put(usuario, "aaaaaa");
        System.out.println(this.listaUsuarios.values());
    }

    

}
