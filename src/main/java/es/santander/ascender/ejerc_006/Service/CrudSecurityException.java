package es.santander.ascender.ejerc_006.Service;

public class CrudSecurityException extends RuntimeException {

    private final CRUDOperation operation;
    private final Long id;

    public CrudSecurityException(CRUDOperation operation, Long id) {
        this.operation = operation;
        this.id = id;
    }

    public CrudSecurityException(String message, CRUDOperation operation, Long id) {
        super(message);
        this.operation = operation;
        this.id = id;
    }

    public CrudSecurityException(String message, Throwable cause, CRUDOperation operation, Long id) {
        super(message);
        this.operation = operation;
        this.id = id;
    }

    public CRUDOperation getOperation() {
        return operation;
    }

    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "OperacionSecurityException [operation=" + operation + ", id=" + id + ", toString()=" + super.toString()
                + "]";
    }

}
