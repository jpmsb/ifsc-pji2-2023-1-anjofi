package anjofi.backend.controller;

import java.io.*;
import java.util.*;
import java.util.Map.Entry;

import anjofi.backend.entities.Usuario;

public class Operacao {


    
    static String nomeArquivo = "arquivo.txt";
    static HashMap<String, Usuario> listaUsuarios = new HashMap<>();
    static HashMap<String, Usuario> listaUsuariosNova = new HashMap<>();


    public static void iniciar(){
        carregarUsuariosDoArquivo();

    }

    public static boolean adicionarUsuario(Usuario usuario){

        carregarUsuariosDoArquivo();

        if (listaUsuarios.containsKey(usuario.getId())) {
            System.out.println("Já existe um usuário com o ID " + usuario.getId());
            return false;
        }
        System.out.println("usuário adicionado com o ID " + usuario.getId());
        listaUsuarios.put(usuario.getId(), usuario);
        salvarUsuariosNoArquivo();
        return true;
    }

    private static void carregarUsuariosDoArquivo() {
        try (BufferedReader reader = new BufferedReader(new FileReader(nomeArquivo))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] dadosCriptografados = linha.split(";");
                String id = dadosCriptografados[0];
                String nome = dadosCriptografados[1];
                String email = dadosCriptografados[2];
                String senha = dadosCriptografados[3];

                Usuario usuario = new Usuario(id, nome, email, senha);

                listaUsuarios.put(id, usuario);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    // Função para exibir os usuários da lista
    public static HashMap<String, Usuario> exibirUsuarios() {
        carregarUsuariosDoArquivo();
        return listaUsuarios;
    }



    // Função para salvar os usuários no arquivo
    private static void salvarUsuariosNoArquivo() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nomeArquivo))) {
            for (Entry<String, Usuario> entry : listaUsuarios.entrySet()) {
                Usuario usuario = entry.getValue();
                String linha = usuario.getId() + ";" + 
                        usuario.getNome() + ";" +
                        usuario.getEmail() + ";" +
                        usuario.getSenha();
                writer.write(linha);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static boolean validarSenha(String id){
        
        if(listaUsuarios.containsKey(id)){

            return true;
        }else{
            return false;
        }
        
    }

    public static Usuario exibirUsuario(String id) {
        carregarUsuariosDoArquivo();  
        return listaUsuarios.get(id);

    }

    public static String excluirUsuario (String usuario){
        System.out.println(usuario);
        listaUsuarios.remove(usuario);
        atualizarArquivo();
        return usuario;
    }
    private static void atualizarArquivo(){
        try (BufferedReader reader = new BufferedReader(new FileReader(nomeArquivo))) {
            String linha;

            while ((linha = reader.readLine()) != null) {
                String[] dadosCriptografados = linha.split(";");
                String id = dadosCriptografados[0];
                String nome = dadosCriptografados[1];
                String email = dadosCriptografados[2];
                String senha = dadosCriptografados[3];

                Usuario usuario = new Usuario(id, nome, email, senha);
               
                try {
                    listaUsuarios.containsKey(usuario.getId());
                    listaUsuariosNova.put(id, usuario);

                } catch (Exception e) {
                    listaUsuarios.remove(usuario.getId());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }salvarUsuariosNoArquivo();

    }
   
}
