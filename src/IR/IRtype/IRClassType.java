package IR.IRtype;

import IR.IRoperand.IRConstInt;
import IR.IRoperand.IRConstNull;
import IR.IRoperand.IROperand;

import java.util.ArrayList;

public class IRClassType implements IRType{
    private String identifier;
    private int size;
    private ArrayList<IRType> members;
    private ArrayList<IRConstInt> offsets;

    public IRClassType(String identifier){
        this.identifier = identifier;
        this.size = 0;
        members = new ArrayList<>();
        offsets = new ArrayList<>();
    }

    public String getIdentifier() {
        return identifier;
    }

    public void addMember(IRType member){
        members.add(member);
        offsets.add(new IRConstInt(size, IRIntType.IntTypeBytes.Int32));
        size += member.getSize();
    }

    public void setSize(int size) {
        this.size = size;
    }

    @Override
    public int getSize() {
        if(size == 0){
            return 8;
        }
        return size;
    }

    public int getOffset(int id){
        int res = 0;
        for(int i = 0; i < id; ++i){
            res += members.get(i).getSize();
        }
        return res;
    }

    public ArrayList<IRConstInt> getOffsets() {
        return offsets;
    }

    @Override
    public String getType() {
        return "%struct." + identifier;
    }

    public ArrayList<IRType> getMembers() {
        return members;
    }

    @Override
    public IROperand initValue() { return new IRConstNull(); }

    public String getTypeDetailed(){
        StringBuilder res = new StringBuilder(getType());
        res.append("<{ ");
        if(!members.isEmpty()){
            for(IRType subType: members){
                res.append(subType.getType() + ", ");
            }
            res.delete(res.length() - 2, res.length());
            res.append(" ");
        }
        res.append("}>");
        return res.toString();
    }

    @Override
    public Boolean resolvable() { return false; }

    @Override
    public String toString() {
        return getType();
    }

    public int getIndex(){
        return members.size() - 1;
    }

    @Override
    public Boolean CSEChecker(IRType other) {
        return other instanceof IRClassType && ((IRClassType) other).identifier.equals(identifier);
    }
}
