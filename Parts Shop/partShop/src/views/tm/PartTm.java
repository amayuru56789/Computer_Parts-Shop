package views.tm;

import model.Part;

public class PartTm extends Part {
    private String partCode;
    private String partBrand;
    private String description;
    private double unitPrice;
    private int qtyOnHand;
    private String catergory;

    public PartTm() {
    }

    public PartTm(String partCode, String partBrand, String description, double unitPrice, int qtyOnHand, String catergory) {
        this.setPartCode(partCode);
        this.setPartBrand(partBrand);
        this.setDescription(description);
        this.setUnitPrice(unitPrice);
        this.setQtyOnHand(qtyOnHand);
        this.setCatergory(catergory);
    }

    public String getPartCode() {
        return partCode;
    }

    public void setPartCode(String partCode) {
        this.partCode = partCode;
    }

    public String getPartBrand() {
        return partBrand;
    }

    public void setPartBrand(String partBrand) {
        this.partBrand = partBrand;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getQtyOnHand() {
        return qtyOnHand;
    }

    public void setQtyOnHand(int qtyOnHand) {
        this.qtyOnHand = qtyOnHand;
    }

    public String getCatergory() {
        return catergory;
    }

    public void setCatergory(String catergory) {
        this.catergory = catergory;
    }
}
