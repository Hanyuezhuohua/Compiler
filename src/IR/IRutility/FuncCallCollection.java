package IR.IRutility;

import IR.IRfunction.IRFunction;
import IR.IRinstruction.Call;
import IR.IRinstruction.IRInstruction;
import IR.IRmodule.IRModule;

import java.util.HashSet;

public class FuncCallCollection {
    private IRModule module;
    private HashSet<String> visited;
    public FuncCallCollection(IRModule module){
        this.module = module;
        visited = new HashSet<>();
    }

    private void init(){
        visited.clear();
        module.getExternalFunctionMap().forEach((id, func)->{
            func.ClearCallee();
            func.ClearCaller();
        });
    }

    private void DFS(IRFunction func){
        visited.add(func.getIdentifier());
        func.getBlockContain().forEach(block -> {
            for(IRInstruction inst = block.getHead(); inst != null; inst = inst.getNext()){
                if(inst instanceof Call){
                    IRFunction callFunc = ((Call) inst).getFnptrval();
                    if(module.internal(func)) continue;
                    func.addCallee(callFunc);
                    callFunc.addCaller(func);
                    if(!visited.contains(callFunc.getIdentifier())) DFS(callFunc);
                }
            }
        });
    }

    private void remove(){
        HashSet<String> useless = new HashSet<>();
        module.getExternalFunctionMap().keySet().forEach(id -> useless.add(id));
        useless.removeAll(visited);
        useless.forEach(id -> module.removeExternalFunction(id));
    }

    public void run(){
        init();
        DFS(module.getFunction("main"));
        remove();
    }
}
