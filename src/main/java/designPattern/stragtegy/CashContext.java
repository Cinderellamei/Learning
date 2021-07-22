package designPattern.stragtegy;

public class CashContext {
    private CashSuper cs;

    /*public CashContext(CashSuper csuper) {
        this.cs = csuper;
    }*/

    public CashContext(String type) {
        switch(type) {
            case "正常收费":
                cs = new CashNormal();
            case "满300减100":
                cs = new CashReturn("300","100");
            case "打8折":
                cs = new CashRebate("0.8");
        }
    }

    public double getResult(double money) {
        return cs.acceptCash(money);
    }
}
