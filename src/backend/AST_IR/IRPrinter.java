package backend.AST_IR;

import IR.IRbasicblock.IRBasicBlock;
import IR.IRfunction.IRFunction;
import IR.IRinstruction.IRInstruction;
import IR.IRmodule.IRModule;
import IR.IRoperand.IRConstString;
import IR.IRoperand.IRGlobalVariable;
import IR.IRoperand.IRLocalRegister;
import IR.IRoperand.IROperand;
import IR.IRtype.IRClassType;
import IR.IRtype.IRPointerType;
import IR.IRtype.IRVoidType;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class IRPrinter {
    private int SymbolNum;
    private PrintStream out;

    public IRPrinter(PrintStream out){
        this.out = out;
        SymbolNum = 0;
    }

    private void PrintFuncDecl(IRFunction func){
        out.print("declare ");
        out.print(func.getReturnType().getType());
        out.print(" ");
        out.print(func.PrintFunction());
        ArrayList<IRLocalRegister> tmp = new ArrayList<>();
        if(func.getClassPtr() != null){
            tmp.add((IRLocalRegister) func.getClassPtr());
        }
        func.getParameters().forEach(para -> tmp.add(para));
        if(tmp.size() == 0){
            out.println("()");
        }
        else{
            out.print("(");
            for(int i = 0; i < tmp.size(); ++i){
                IROperand para = tmp.get(i);
                para.ChangeIdentifier(Integer.toString(SymbolNum++));
                out.print(para.getOperandType().getType());
                out.print(" ");
                out.print(para.PrintOperand());
                if(i == tmp.size() - 1){
                    out.println(")");
                }
                else{
                    out.print(", ");
                }
            }
        }
    }

    private void PrintFuncDef(IRFunction func){
        out.print("define ");
        out.print(func.getReturnType().getType());
        out.print(" ");
        out.print(func.PrintFunction());
        ArrayList<IRLocalRegister> tmp = new ArrayList<>();
        if(func.getClassPtr() != null){
            tmp.add((IRLocalRegister) func.getClassPtr());
        }
        func.getParameters().forEach(para -> tmp.add(para));
        if(tmp.size() == 0){
            out.println("()");
        }
        else{
            out.print("(");
            for(int i = 0; i < tmp.size(); ++i){
                IROperand para = tmp.get(i);
                para.ChangeIdentifier(Integer.toString(SymbolNum++));
                out.print(para.getOperandType().getType());
                out.print(" ");
                out.print(para.PrintOperand());
                if(i == tmp.size() - 1){
                    out.print(")");
                }
                else{
                    out.print(", ");
                }
            }
        }
    }

    private void PrintClassType(IRClassType type){
        out.print(type.getType() + " = " + "type {");
        for(int i = 0; i < type.getMembers().size(); ++i){
            out.print(type.getMembers().get(i).getType());
            if(i == type.getMembers().size() - 1) {
                out.println("}");
            }
            else{
                out.print(", ");
            }
        }
    }

    private void PrintGlobalVar(IRGlobalVariable globalVar){
        if(globalVar.isInitialization()){
            out.print(globalVar.PrintOperand() + " = global ");
            out.print(((IRPointerType) globalVar.getOperandType()).getPointTo().getType());
            out.print(" " + globalVar.getValue().PrintOperand() + ", align ");
        }
        else{
            out.print(globalVar.PrintOperand() + " = common global ");
            out.print(((IRPointerType) globalVar.getOperandType()).getPointTo().getType());
            out.print(" zeroinitializer, align ");
        }
        out.println(globalVar.getOperandType().getSize() / 8);
    }

    private void PrintConstStr(String val, IRConstString constString){
        out.print("@" + constString.getIdentifier());
        out.print(" = private unnamed_addr constant");
        out.print(" [" + val.length() + " x i8] ");
        out.print(constString.IRStringChange());
        out.println(", align 1");
    }

    private void PrintFunction(IRFunction func){
        SymbolNum = 0;
        PrintFuncDef(func);
        out.println();
        Queue<IRBasicBlock> blockQueue = new LinkedList<>();
        ArrayList<IRBasicBlock> blocks = new ArrayList<>();
        blocks.add(func.getEntry());
        blockQueue.add(func.getEntry());
        while(!blockQueue.isEmpty()){
            IRBasicBlock block = blockQueue.poll();
            block.setIdentifier(Integer.toString(SymbolNum++));
            for(IRInstruction inst = block.getHead(); inst != null; inst = inst.getNext()){
                if(inst.getResult() != null && !(inst.getResult().getOperandType() instanceof IRVoidType)){
                    inst.getResult().ChangeIdentifier(Integer.toString(SymbolNum++));
                }
            }
            for(IRBasicBlock next: block.getNext()){
                if(next != null && !blocks.contains(next)){
                    blocks.add(next);
                    blockQueue.add(next);
                }
            }
        }
        for(IRBasicBlock block : blocks){
            out.println(block.getIdentifier() + ":");
            out.print(";prev: ");
            block.getPrev().forEach(pre -> out.print(pre.getIdentifier() + " "));
            out.print("\n;next: ");
            block.getNext().forEach(suc -> out.print(suc.getIdentifier() + " "));
            if(block.getIdom() != null){
                out.print("\n;iDom: ");
                out.print(block.getIdom().getIdentifier());
            }
            if(!block.getDomFrontiers().equals(new HashSet<IRBasicBlock>())){
                out.print("\n;DomFrontiers: ");
                block.getDomFrontiers().forEach(domFrontier -> out.print(domFrontier.getIdentifier() + " "));
            }
            out.print("\n;tail: ");
            if(block.getTail() != null) out.println(block.getTail().PrintInst());
            out.print("\n");
            for(IRInstruction inst = block.getHead(); inst != null; inst = inst.getNext()){
                out.println("\t" + inst.PrintInst());
            }
        }
        out.println("}");
    }

    public void run(IRModule module){
        module.getInternalFunctionMap().forEach((id, func) ->{
            SymbolNum = 0;
            PrintFuncDecl(func);
        });
        module.getClassTypeMap().forEach((id, type) -> PrintClassType(type));
        module.getGlobalVariableList().forEach(this::PrintGlobalVar);
        module.getConstStringMap().forEach(this::PrintConstStr);
        module.getExternalFunctionMap().forEach((id, func) -> PrintFunction(func));
    }
}
