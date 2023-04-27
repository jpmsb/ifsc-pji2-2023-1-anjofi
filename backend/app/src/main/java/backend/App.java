package backend;
import backend.UserManager;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class App {

    public static void main(String[] args) {

    
    UserManager cliente = new UserManager();
    cliente.adicionar();
    
    System.out.println(cliente.toString());
    }

    /*
    public static void main(String[] args) {

        try (Scanner teclado = new Scanner(System.in)) {
            
            int num;

            while(true){
                try{

                    System.out.println("1- adicionar");
                    System.out.println("2- atualizar");
                    System.out.println("3- remover");
                    num = teclado.nextInt();
                    opc(num);
                }catch(InputMismatchException | IOException e){
                    System.out.println("Entre com um número inteiro");
                    teclado.nextLine();
                }
            }
        }
    }

    private static void opc(int num) throws IOException {
        switch(num){
            case 1:
                Scanner teclado = new Scanner(System.in);
                System.out.println("Entre com o nome");
                String nome = teclado.nextLine();
                System.out.println("Entre com a senha");
                String senha = teclado.nextLine();
                //cliente.adicionar(nome,senha);
                break;
            case 2:
                //cliente.atualizar();
                break;
            case 3:
               // cliente.remover();
                break;
            default:
                break;
        }
            

    }
}
*/}