package backend;


public class User {
    
    private String nome;
    private String senha;

    public void adicionar (String nome, String senha) {
        this.nome = nome;
        this.senha = senha;
        System.out.println("Usuário: " + this.nome + "\n");
        System.out.println("Senha: " + this.senha + "\n");


    }


    public void atualizar() {
        System.out.println("atualizado\n");

    }

    public void remover() {
        System.out.println("remover");

    }
    
}
