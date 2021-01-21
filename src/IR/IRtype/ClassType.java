package IR.IRtype;

import java.util.ArrayList;

public class ClassType implements Type{
    private String identifier;
    private int size;
    private ArrayList<Type> members;

    public ClassType(String identifier){
        this.identifier = identifier;
        this.size = 0;
        members = new ArrayList<Type>();
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
            for(Type subType: members){
                res.append(subType.getType() + ", ");
            }
            res.delete(res.length() - 2, res.length());
            res.append(" ");
        }
        res.append("}>");
        return res.toString();
    }
}
