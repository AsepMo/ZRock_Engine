package zrock.application.engine.content.resolver;

class ErrnoException extends Exception {
    private final String functionName;
    public final int errno;


    public ErrnoException(String functionName, int errno) {
        this.functionName = functionName;
        this.errno = errno;
    }

    @Override
    public String getMessage() {
        return functionName + " failed: " + errno;
    }
}
