package JpMorganApi.JpMorganBankApi.handler.exceptions;

public class DepositNotFoundException extends RuntimeException {
    public DepositNotFoundException(String message) {
        super(message);
    }
}
