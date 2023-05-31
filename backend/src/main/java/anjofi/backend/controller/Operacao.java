package anjofi.backend.controller;

import java.io.*;
import java.util.*;

import anjofi.backend.entities.Usuario;

public class Operacao {

    static String nomeArquivo = "arquivo.txt";
    static HashMap<Integer, Usuario> listaUsuarios = new HashMap<>();
    

    public static void adicionarUsuario(Usuario usuario){

        carregarUsuariosDoArquivo();

        if (listaUsuarios.containsKey((int)usuario.getId())) {
            System.out.println("Já existe um usuário com o ID " + usuario.getId());
            return;
        }else{
            System.out.println("usuário adicionado com o ID " + usuario.getId());
            listaUsuarios.put((int) usuario.getId(), usuario);
        }
        salvarUsuariosNoArquivo();
    }

    private static void carregarUsuariosDoArquivo() {
        try (BufferedReader reader = new BufferedReader(new FileReader(nomeArquivo))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] dadosCriptografados = linha.split(";");
                long id = Integer.parseInt(dadosCriptografados[0]);
                String nome = dadosCriptografados[1];
                String email = dadosCriptografados[2];
                String senha = dadosCriptografados[3];
                int a = (int)id;

                Usuario usuario = new Usuario(id, nome, email, senha);
                listaUsuarios.put(a, usuario);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    
    }


    // Função para exibir os usuários da lista
    public static HashMap<Integer, Usuario> exibirUsuarios() {
        carregarUsuariosDoArquivo();
        return listaUsuarios;
    }



    // Função para salvar os usuários no arquivo
    private static void salvarUsuariosNoArquivo() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nomeArquivo))) {
            for (Map.Entry<Integer, Usuario> entry : listaUsuarios.entrySet()) {
                Integer id = entry.getKey();
                Usuario usuario = entry.getValue();

                String linha = id.toString() + ";" + 
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


    public static boolean validarSenha(long id){
        
        if(listaUsuarios.containsKey(id)){

            return true;
        }else{
            return false;
        }
        
    }
   
}
