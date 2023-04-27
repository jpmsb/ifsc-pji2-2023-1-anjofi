package backend;

import java.util.ArrayList;
import java.util.HashMap;

public class User {

    private String nome;
    private String senha;
    private ArrayList <dispositivos>listaDeDispositivos;

    public User(String nome, String senha) {
        this.nome = nome;
        this.senha= senha;
        this.listaDeDispositivos = new ArrayList<dispositivos>();

    }
   



}
