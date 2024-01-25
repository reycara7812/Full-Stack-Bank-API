package JpMorganApi.JpMorganBankApi.handler.exceptions;

public class LoanNotValidException extends RuntimeException {
    public LoanNotValidException(String message) {
        super(message);
    }
}
