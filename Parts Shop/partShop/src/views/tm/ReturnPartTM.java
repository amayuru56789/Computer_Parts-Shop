package views.tm;

import model.ReturnPart;

public class ReturnPartTM extends ReturnPart {
    private String returnId;
    private String returnDate;
    private String partCode;
    private String warrentyCard;

    public ReturnPartTM() {
    }

    public ReturnPartTM(String returnId, String returnDate, String partCode, String warrentyCard) {
        this.setReturnId(returnId);
        this.setReturnDate(returnDate);
        this.setPartCode(partCode);
        this.setWarrentyCard(warrentyCard);
    }

    public String getReturnId() {
        return returnId;
    }

    public void setReturnId(String returnId) {
        this.returnId = returnId;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }

    public String getPartCode() {
        return partCode;
    }

    public void setPartCode(String partCode) {
        this.partCode = partCode;
    }

    public String getWarrentyCard() {
        return warrentyCard;
    }

    public void setWarrentyCard(String warrentyCard) {
        this.warrentyCard = warrentyCard;
    }

    @Override
    public String toString() {
        return "ReturnPartTM{" +
                "returnId='" + returnId + '\'' +
                ", returnDate='" + returnDate + '\'' +
                ", partCode='" + partCode + '\'' +
                ", warrentyCard='" + warrentyCard + '\'' +
                '}';
    }
}
