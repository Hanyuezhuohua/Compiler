package RISCV.RISCVmodule;

import RISCV.RISCVoperand.RISCVregister.RISCVGlobalRegister;
import RISCV.RISCVoperand.RISCVregister.RISCVPhysicalRegister;
import RISCV.RISCVfunction.RISCVFunction;

import java.util.*;

import static RISCV.RISCVoperand.RISCVregister.PhysicalRegisterTable.*;

public class RISCVModule {
    private HashSet<RISCVFunction> ExternalFunctionSet;
    private HashSet<RISCVFunction> InternalFunctionSet;
    private HashMap<RISCVGlobalRegister, String> constStringMap;
    private HashSet<RISCVGlobalRegister> globalVariableSet;
    private HashMap<String, RISCVPhysicalRegister> regNameMap;
    private HashMap<Integer, RISCVPhysicalRegister> regIDMap;
    public RISCVModule() {
        regNameMap = new LinkedHashMap<>();
        regIDMap = new LinkedHashMap<>();
        ExternalFunctionSet = new LinkedHashSet<>();
        InternalFunctionSet = new LinkedHashSet<>();
        constStringMap = new LinkedHashMap<>();
        globalVariableSet = new LinkedHashSet<>();
        for(int i = 0; i < 32; ++i){
            RISCVPhysicalRegister register = new RISCVPhysicalRegister(regNames[i]);
            regNameMap.put(regNames[i], register);
            regIDMap.put(i, register);
        }
    }

    public RISCVPhysicalRegister getPhysicalRegister(String name){ return regNameMap.get(name); }

    public RISCVPhysicalRegister getPhysicalRegister(int id){ return regIDMap.get(id); }

    public ArrayList<RISCVPhysicalRegister>  getPhysicalRegisters(){
        ArrayList<RISCVPhysicalRegister> res = new ArrayList<>();
        for (int i = 0; i < 32; i++) res.add(getPhysicalRegister(i));
        return res;
    }

    public ArrayList<RISCVPhysicalRegister>  getCallerSavedRegs(){
        ArrayList<RISCVPhysicalRegister> res = new ArrayList<>();
        for (int i = 1; i <= 1; i++) res.add(getPhysicalRegister(i));
        for (int i = 5; i <= 7; i++) res.add(getPhysicalRegister(i));
        for (int i = 10; i <= 17; i++) res.add(getPhysicalRegister(i));
        for (int i = 28; i <= 31; i++) res.add(getPhysicalRegister(i));
        return res;
    }

    public ArrayList<RISCVPhysicalRegister>  getCalleeSavedRegs(){
        ArrayList<RISCVPhysicalRegister> res = new ArrayList<>();
        for (int i = 8; i <= 9; i++) res.add(getPhysicalRegister(i));
        for (int i = 18; i <= 27; i++) res.add(getPhysicalRegister(i));
        return res;
    }

    public ArrayList<RISCVPhysicalRegister> getColors(){
        ArrayList<RISCVPhysicalRegister> res = new ArrayList<>();
        for (int i = 10; i <= 17; i++) res.add(getPhysicalRegister(i));
        for (int i = 5; i <= 9; i++) res.add(getPhysicalRegister(i));
        for (int i = 18; i <= 27; i++) res.add(getPhysicalRegister(i));
        for (int i = 28; i <= 31; i++) res.add(getPhysicalRegister(i));
        for (int i = 1; i <= 1; i++) res.add(getPhysicalRegister(i));
        return res;
    }

    public ArrayList<RISCVPhysicalRegister> getFuncArgs(){
        ArrayList<RISCVPhysicalRegister> res = new ArrayList<>();
        for (int i = 10; i <= 17; i++) res.add(getPhysicalRegister(i));
        return res;
    }

    public void addExternalFunctionSet(RISCVFunction externalFunction) {
        ExternalFunctionSet.add(externalFunction);
    }

    public HashSet<RISCVFunction> getExternalFunctionSet() {
        return ExternalFunctionSet;
    }

    public void addInternalFunctionSet(RISCVFunction internalFunction) {
        InternalFunctionSet.add(internalFunction);
    }

    public void addConstStringMap(RISCVGlobalRegister register, String str){
        constStringMap.put(register, str);
    }

    public HashMap<RISCVGlobalRegister, String> getConstStringMap() {
        return constStringMap;
    }

    public void addGlobalVariableSet(RISCVGlobalRegister globalVariable) {
        globalVariableSet.add(globalVariable);
    }

    public HashSet<RISCVGlobalRegister> getGlobalVariableSet() {
        return globalVariableSet;
    }
}
