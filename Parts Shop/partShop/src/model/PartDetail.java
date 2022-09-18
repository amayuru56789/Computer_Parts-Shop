package model;

public class PartDetail {
    private String partCode;
    private double unitPrice;
    private int qtyForSell;
    private double discount;
    private double tot;

    public PartDetail() {
    }

    public PartDetail(String partCode, double unitPrice, int qtyForSell, double discount, double tot) {
        this.setPartCode(partCode);
        this.setUnitPrice(unitPrice);
        this.setQtyForSell(qtyForSell);
        this.setDiscount(discount);
        this.setTot(tot);
    }

    public String getPartCode() {
        return partCode;
    }

    public void setPartCode(String partCode) {
        this.partCode = partCode;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getQtyForSell() {
        return qtyForSell;
    }

    public void setQtyForSell(int qtyForSell) {
        this.qtyForSell = qtyForSell;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public double getTot() {
        return tot;
    }

    public void setTot(double tot) {
        this.tot = tot;
    }

    @Override
    public String toString() {
        return "PartDetail{" +
                "partCode='" + partCode + '\'' +
                ", unitPrice=" + unitPrice +
                ", qtyForSell=" + qtyForSell +
                ", discount=" + discount +
                ", tot=" + tot +
                '}';
    }
}
