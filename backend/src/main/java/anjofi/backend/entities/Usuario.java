package anjofi.backend.entities;
import java.io.UnsupportedEncodingException;
import java.security.*;

public class Usuario {
    private long id;
    private String nome;
    private String email;
    private String senha;

    public Usuario() {

    }

    public Usuario(long id, String nome, String email, String senha) {
        this.id = id;
        this.nome = nome;
        this.email = criptografar(email);
        this.senha = criptografar(senha);
    }

    public long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }

    public static String criptografar(String texto) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = md.digest(texto.getBytes("UTF-8"));

            StringBuilder sb = new StringBuilder();
            for (byte b : hashBytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }
    // Função para descriptografar uma string
    public static String descriptografar(String textoCriptografado) {
        return textoCriptografado;
    }




}

