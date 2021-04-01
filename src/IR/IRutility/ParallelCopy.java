package IR.IRutility;

import IR.IRinstruction.Move;
import IR.IRoperand.IRLocalRegister;
import IR.IRoperand.IROperand;

import java.util.ArrayList;
import java.util.HashMap;

public class ParallelCopy {
    private ArrayList<Move> copies;
    private HashMap<IROperand, Integer> useCnt;

    public ParallelCopy(){
        copies = new ArrayList<>();
        useCnt = new HashMap<>();
    }

    public void add(Move move){
        copies.add(move);
        if(move.getValue() instanceof IRLocalRegister){
            useCnt.put(move.getValue(), useCnt.getOrDefault(move.getValue(), 0) + 1);
        }
    }

    public ArrayList<Move> getCopies() {
        return copies;
    }

    public HashMap<IROperand, Integer> getUseCnt() {
        return useCnt;
    }
}
