package remoteMethodCalling;

public class MethodRequestImpl implements MethodRequest {


    private final String request;

    public MethodRequestImpl(String request){
        this.request = request;

    }

    @Override
    public String getRequest() {
        return this.request;
    }
}
