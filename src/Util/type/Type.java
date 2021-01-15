package Util.type;

import Util.position;

public interface Type {
    String getType();
    int getDim();
    void checkAssignment(Type t, position pos);
    void checkEquality(Type t, position pos);
}
