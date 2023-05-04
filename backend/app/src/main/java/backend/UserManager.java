package backend;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;

public class UserManager {

    private HashMap<String, User> listaUsuarios;

    
    public UserManager() {
        this.listaUsuarios = new HashMap<String, User>();

    }

    public void adicionar(String nome, String senha){
        User usuario = new User(nome, senha);
        
        if(this.listaUsuarios.containsKey(nome)){
            System.out.println("usuario ja está adicionado");
        }else{
            this.listaUsuarios.put(nome,usuario);
        }
    }

    public void getListaUsuarios() {
        //return this.listaUsuarios.values().toString();
        Collection<User> a = listaUsuarios.values();
        for(int i=1; i<listaUsuarios.size(); i++){
                a = listaUsuarios.values();
            }
        System.out.println(a.toString());

        
    }



    @Override
    protected Object clone() throws CloneNotSupportedException {
        // TODO Auto-generated method stub
        return super.clone();
    }

    @Override
    public boolean equals(Object obj) {
        // TODO Auto-generated method stub
        return super.equals(obj);
    }

    @Override
    protected void finalize() throws Throwable {
        // TODO Auto-generated method stub
        super.finalize();
    }

    @Override
    public int hashCode() {
        // TODO Auto-generated method stub
        return super.hashCode();
    }

    

}
