package IR.IRbasicblock;

import AST.NullliteralNode;
import IR.IRfunction.IRFunction;
import IR.IRinstruction.*;
import IR.IRoperand.IRConstVoid;
import IR.IRoperand.IRLocalRegister;
import IR.IRtype.IRPointerType;
import IR.IRutility.IRVisitor;

import java.util.ArrayList;
import java.util.HashSet;

public class IRBasicBlock {
    private IRFunction blockIn;
    private String identifier;
    private IRInstruction head, tail;
    private ArrayList<IRBasicBlock> prev, next;
    private IRBasicBlock idom = null;
    private HashSet<IRBasicBlock> domFrontiers = new HashSet<>();

    public IRBasicBlock(IRFunction blockIn, String identifier){
        this.blockIn = blockIn;
        this.identifier = identifier;
        head = tail = null;
        prev = new ArrayList<>();
        next = new ArrayList<>();
    }

    public void addInst(IRInstruction inst){
        if(inst instanceof Phi){
            if(head == null){
                head = tail = inst;
            }
            else{
                if(head instanceof Phi){
                    IRInstruction prev = head;
                    if(prev != tail){
                        while(prev.getNext() instanceof Phi){
                            prev = prev.getNext();
                        }
                    }
                    if(prev == tail){
                        tail.setNext(inst);
                        inst.setPrev(tail);
                        tail = inst;
                    }
                    else{
                        IRInstruction next = prev.getNext();
                        prev.setNext(inst);
                        next.setPrev(inst);
                        inst.setPrev(prev);
                        inst.setNext(next);
                    }
                }
                else{
                    inst.setNext(head);
                    head.setPrev(inst);
                    head = inst;
                }
            }
        }
        else{
            if(head == null){
                head = tail = inst;
                if(inst instanceof Br){
                    link(((Br) inst).getIfTrue());
                    link(((Br) inst).getIfFalse());
                }
            }
            else if(!tail.Terminal()){
                tail.setNext(inst);
                inst.setPrev(tail);
                tail = inst;
                if(inst instanceof Br){
                    link(((Br) inst).getIfTrue());
                    link(((Br) inst).getIfFalse());
                }
            }
        }
    }

    public void addInstBefore(IRInstruction inst){
        if(head == null){
            head = tail = inst;
        }
        else{
            if(head instanceof Alloca || head instanceof Phi){
                IRInstruction prev = head;
                if(prev != tail){
                    while(prev.getNext() instanceof Alloca || prev.getNext() instanceof Phi){
                        prev = prev.getNext();
                    }
                }
                if(prev == tail){
                    tail.setNext(inst);
                    inst.setPrev(tail);
                    tail = inst;
                }
                else{
                    IRInstruction next = prev.getNext();
                    prev.setNext(inst);
                    next.setPrev(inst);
                    inst.setPrev(prev);
                    inst.setNext(next);
                }
            }
            else{
                inst.setNext(head);
                head.setPrev(inst);
                head = inst;
            }
        }
    }

    public void addInstBeforeTail(IRInstruction inst){
        if(head == tail){
            head = inst;
            inst.setNext(tail);
            tail.setPrev(inst);
        }
        else{
            IRInstruction prev = tail.getPrev();
            inst.setPrev(prev);
            inst.setNext(tail);
            prev.setNext(inst);
            tail.setPrev(inst);
        }
    }

    public IRFunction getBlockIn() {
        return blockIn;
    }

    public void addAlloc(IRLocalRegister var){
        IRInstruction store = new Store(this, new IRConstVoid(), var);
//        IRInstruction store = new Store(this, ((IRPointerType) var.getOperandType()).getPointTo().initValue(), var);
        if(head == null){
            head = tail = new Alloca(this, var);
            head.setNext(store);
            tail = store;
            tail.setPrev(head);
        }
        else{
            IRInstruction inst = new Alloca(this, var);
            if(head instanceof Alloca || head instanceof Phi){
                IRInstruction prev = head;
                if(prev != tail){
                    while(prev.getNext() instanceof Alloca || prev.getNext() instanceof Phi){
                        prev = prev.getNext();
                    }
                }
                if(prev == tail){
                    tail.setNext(inst);
                    inst.setPrev(tail);
                    inst.setNext(store);
                    store.setPrev(inst);
                    tail = store;
                }
                else{
                    IRInstruction next = prev.getNext();
                    prev.setNext(inst);
                    next.setPrev(store);
                    inst.setPrev(prev);
                    inst.setNext(store);
                    store.setPrev(inst);
                    store.setNext(next);
                }
            }
            else{
                inst.setNext(store);
                store.setPrev(inst);
                store.setNext(head);
                head.setPrev(store);
                head = inst;
            }
        }
    }

    public void addPrev(IRBasicBlock prev){
        this.prev.add(prev);
    }

    public void addNext(IRBasicBlock next){
        this.next.add(next);
    }

    public void link(IRBasicBlock next){
        if(next == null) return;
        this.addNext(next);
        next.addPrev(this);
    }

    public boolean Terminal(){
        if(tail == null) return false;
        else return tail.Terminal();
    }

    public IRInstruction getHead() {
        return head;
    }

    public IRInstruction getTail() {
        return tail;
    }

    public String getIdentifier() {
        return identifier;
    }

    public ArrayList<IRBasicBlock> getNext() {
        return next;
    }

    public ArrayList<IRBasicBlock> getPrev() {
        return prev;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String PrintBasicBlock(){
        return  "%" + identifier;
    }

    public void setIdom(IRBasicBlock idom) {
        this.idom = idom;
    }

    public IRBasicBlock getIdom() {
        return idom;
    }

    public void addDomFrontier(IRBasicBlock domFrontier){
        domFrontiers.add(domFrontier);
    }

    public HashSet<IRBasicBlock> getDomFrontiers() {
        return domFrontiers;
    }

    public void setHead(IRInstruction head) {
        this.head = head;
    }

    public void setTail(IRInstruction tail) {
        this.tail = tail;
    }

    public void setPrev(ArrayList<IRBasicBlock> prev) {
        this.prev = prev;
    }

    public void setNext(ArrayList<IRBasicBlock> next) {
        this.next = next;
    }

    public void updateBlock(IRBasicBlock Old, IRBasicBlock New){
        ((Br) tail).updateBlock(Old, New);
        next.remove(Old);
        link(New);
    }

    public void setDomFrontiers(HashSet<IRBasicBlock> domFrontiers) {
        this.domFrontiers = domFrontiers;
    }

    public void accept(IRVisitor visitor){
        visitor.visit(this);
    }

    public void split(IRBasicBlock newSplit, IRInstruction splitPoint){
        next.forEach(nextBlock -> {
            for(IRInstruction inst = nextBlock.getHead(); inst != null && inst instanceof Phi; inst = inst.getNext()){
                ((Phi) inst).updateLabel(this, newSplit);
            }
            nextBlock.getPrev().remove(this);
            nextBlock.getPrev().add(newSplit);
        });
        newSplit.setNext(next);
        next = new ArrayList<>();
        newSplit.setHead(splitPoint.getNext());
        splitPoint.getNext().setPrev(null);
        newSplit.setTail(tail);
        if(splitPoint.getPrev() == null){
            head = tail = null;
        }
        else{
            tail = splitPoint.getPrev();
            tail.setNext(null);
        }
        for (IRInstruction inst = newSplit.getHead(); inst != null; inst =inst.getNext()){
            inst.setInstIn(newSplit);
        }
    }

    public void merge(IRBasicBlock nextBlock){
        setNext(nextBlock.getNext());
        next.forEach(block -> {
            block.getPrev().remove(nextBlock);
            block.getPrev().add(this);
            for(IRInstruction inst = block.getHead(); inst != null && inst instanceof Phi; inst = inst.getNext()){
                ((Phi) inst).updateLabel(nextBlock, this);
            }
        });
        for(IRInstruction inst = nextBlock.getHead(); inst != null; inst = inst.getNext()){
            inst.setInstIn(this);
        }
        if(head == null) head = nextBlock.getHead();
        else tail.setNext(nextBlock.getHead());
        if(nextBlock.getHead() != null) nextBlock.getHead().setPrev(tail);
        if(nextBlock.getTail() != null) tail = nextBlock.getTail();
    }

    public boolean DomBy(IRBasicBlock block){
        for(IRBasicBlock iDom = this.idom; iDom != null; iDom = iDom.getIdom()){
            if(iDom.equals(block)) return true;
        }
        return false;
    }
}
