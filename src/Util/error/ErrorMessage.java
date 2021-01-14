package Util.error;

import javax.swing.text.Position;

public class ErrorMessage extends RuntimeException{
    public String additional;
    public ErrorMessage(){
        additional = null;
    }
    public ErrorMessage(String message){
        super(message);
        additional = null;
    }
    public ErrorMessage(String error, Position pos){
        super(error);
        additional = pos.toString();
    }

    @Override
    public String getMessage() {
        if(additional == null) return super.getMessage();
        else return super.getMessage() +additional;
    }
}
