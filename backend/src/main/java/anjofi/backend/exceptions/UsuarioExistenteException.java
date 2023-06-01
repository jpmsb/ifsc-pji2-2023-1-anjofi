package anjofi.backend.exceptions;

import anjofi.backend.entities.Usuario;

public class UsuarioExistenteException extends RuntimeException {
    public UsuarioExistenteException(Usuario p) {
    super("Ja existe um usuario com " + p);
    }
}