package views.tm;

import model.Supplier;

public class SupplierTm extends Supplier {
    private String supId;
    private String supName;
    private String address;
    private String contact;

    public SupplierTm() {
    }

    public SupplierTm(String supId, String supName, String address, String contact) {
        this.setSupId(supId);
        this.setSupName(supName);
        this.setAddress(address);
        this.setContact(contact);
    }

    public String getSupId() {
        return supId;
    }

    public void setSupId(String supId) {
        this.supId = supId;
    }

    public String getSupName() {
        return supName;
    }

    public void setSupName(String supName) {
        this.supName = supName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    @Override
    public String toString() {
        return "SupplierTm{" +
                "supId='" + supId + '\'' +
                ", supName='" + supName + '\'' +
                ", address='" + address + '\'' +
                ", contact='" + contact + '\'' +
                '}';
    }
}
