package Util.type;

import javax.swing.text.Position;

public interface Type {
    String getType();
    int getDim();
    void checkAssignment(Type t, Position pos);
    void checkEquality(Type t, Position pos);
}
