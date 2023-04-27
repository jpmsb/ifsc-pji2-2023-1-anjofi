package backend.users;
import java.io.FileNotFoundException;
import java.lang.SecurityException;
import java.util.Formatter;
import java.util.FormatterClosedException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class CriarArquivoTexto {
  private static Formatter saida; // envia texto para um arquivo

    public static void main(String[] args) {
      abrirArquivo();
      adicionarDados();
      fecharArquivo();
    }

    // Método para abrir (ou criar) o arquivo arquivo.txt
    public static void abrirArquivo() {
      try {
        saida = new Formatter("arquivo.txt"); // Abrir o arquivo
      }
      catch (SecurityException securityException) {
        System.err.println("Não é possível escrever no arquivo. Finalizando.");
        System.exit(1); // terminar o programa
      }
      catch (FileNotFoundException fileNotFoundException) {
        System.err.println("Erro ao abrir o arquivo. Finalizando.");
        System.exit(1); // Finalizar o programa
      }
   }

    // Método para adicionar registros ao arquivo
    public static void adicionarDados() {
      Scanner entrada = new Scanner(System.in);
      System.out.printf("%s%n","Entre com o código do produto e nome do item para cadastro:");
      System.out.printf("%s%n","Para salvar os dados inseridos pressione Ctrl+Z:");
      while (entrada.hasNext()) { // iterar até que seja encontrado o marcador de fim-de-arquivo
        try {
          // Gravar novo registro no arquivo; não verifica se entrada é válida.
          saida.format("%d %s %n", entrada.nextInt(),entrada.next());
        }
        catch (FormatterClosedException formatterClosedException) {
          System.err.println("Erro ao escrever no arquivo. Finalizando.");
          break;
        }
        catch (NoSuchElementException elementException) {
          System.err.println("Entrada inválida. Tente novamente.");
          entrada.nextLine(); // Descartar a entrada para que o usuário possa tentar de novo
        }
        System.out.print("Entre com o próximo código e item:\n");
      } // Fim do laço while
    } // Fim do método adicionarDados

    // Método para fechar o arquivo
    public static void fecharArquivo() {
      if (saida != null)
        saida.close();
    }
} // Fim da classe CriarArquivoTexto
