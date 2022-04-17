package cn.edu.zucc.echo.exception;

public class EchoServiceException extends RuntimeException{
    public EchoServiceException(){}

    public EchoServiceException(String msg){
        super(msg);
    }
}
