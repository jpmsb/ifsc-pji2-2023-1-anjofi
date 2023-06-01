package anjofi.backend.exceptions;

public class UsuarioNaoEncontradoException extends RuntimeException {
    public UsuarioNaoEncontradoException(String id) {
    super("Não foi possível encontrar pessoa com o id: " + id);
    }
}