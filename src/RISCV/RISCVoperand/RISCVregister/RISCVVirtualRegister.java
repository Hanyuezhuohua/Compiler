package RISCV.RISCVoperand.RISCVregister;

public class RISCVVirtualRegister extends RISCVRegister {
    static int cnt = 0;
    private int width;
    private boolean Const;
    private int ConstValue;
    private int num;
    public RISCVVirtualRegister(int width) {
        this.width = width;
        this.num = cnt++;
    }

    public void SetConstValue(int value){
        ConstValue = value;
        Const = true;
    }

    public int getWidth() {return width;}

    public boolean isConst() {
        return Const;
    }

    public int getConstValue() {
        return ConstValue;
    }

    @Override
    public String toString() {
        if (color == null) return "%" + num;
        else return color.toString();
    }
}
