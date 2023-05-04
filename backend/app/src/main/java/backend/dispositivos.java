package backend;

import java.util.HashMap;

public class dispositivos {
    private String nome;
    private String local;

    HashMap<String,String> disp = new HashMap<String,String>();
    //disp.put(nome, local);

    public dispositivos(String nome, String local, HashMap<String, String> disp) {
        this.nome = nome;
        this.local = local;
        this.disp = disp;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public HashMap<String, String> getDisp() {
        return disp;
    }

    public void setDisp(HashMap<String, String> disp) {
        this.disp = disp;
    }

    @Override
    public String toString() {
        return "dispositivos [nome=" + nome + ", local=" + local + ", disp=" + disp + "]";
    }

    
}
