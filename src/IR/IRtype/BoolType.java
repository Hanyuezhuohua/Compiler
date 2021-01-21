package IR.IRtype;

public class BoolType implements Type{ // same to i1
    @Override
    public int getSize() {
        return 8;
    }

    @Override
    public String getType() {
        return "bool";
    }
}
