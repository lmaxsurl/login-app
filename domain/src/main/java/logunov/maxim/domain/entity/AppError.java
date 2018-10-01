package logunov.maxim.domain.entity;

public class AppError extends Exception {

    private ErrorType type;

    public AppError(String message, ErrorType type) {
        super(message);
        this.type = type;
    }

    public ErrorType getType() {
        return type;
    }
}
