package org.apache.poi.xls;

import java.math.BigDecimal;

public class PayAmount {

    private String payerName;

    private String payerAcct;

    private String payeeName;

    private String payeeAcct;

    private BigDecimal amount;

    public PayAmount() {
    }

    public PayAmount(String payerName, String payerAcct, String payeeName, String payeeAcct, BigDecimal amount) {
        this.payerName = payerName;
        this.payerAcct = payerAcct;
        this.payeeName = payeeName;
        this.payeeAcct = payeeAcct;
        this.amount = amount;
    }

    public String getPayerName() {
        return payerName;
    }

    public void setPayerName(String payerName) {
        this.payerName = payerName;
    }

    public String getPayerAcct() {
        return payerAcct;
    }

    public void setPayerAcct(String payerAcct) {
        this.payerAcct = payerAcct;
    }

    public String getPayeeName() {
        return payeeName;
    }

    public void setPayeeName(String payeeName) {
        this.payeeName = payeeName;
    }

    public String getPayeeAcct() {
        return payeeAcct;
    }

    public void setPayeeAcct(String payeeAcct) {
        this.payeeAcct = payeeAcct;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "PayAmount [payerName=" + payerName + ", payerAcct=" + payerAcct + ", payeeName=" + payeeName + ", payeeAcct="
                + payeeAcct + ", amount=" + amount + "]";
    }

}
