package anjofi.backend.exceptions;

public class UsuarioNaoEncontradoException extends RuntimeException {
    public UsuarioNaoEncontradoException(long id) {
    super("Não foi possível encontrar pessoa com o id: " + id);
    }
}