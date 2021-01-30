package IR.IRtype;

import java.util.ArrayList;

public class IRClassType implements IRType{
    private String identifier;
    private int size;
    private ArrayList<IRType> members;

    public IRClassType(String identifier){
        this.identifier = identifier;
        this.size = 0;
        members = new ArrayList<IRType>();
    }

    public String getIdentifier() {
        return identifier;
    }

    public void addMember(IRType member){
        members.add(member);
        size += member.getSize();
    }

    public void setSize(int size) {
        this.size = size;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public String getType() {
        return "%" + identifier;
    }

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
}
