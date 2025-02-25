package com.training.javatrainingphase2.util;

public class BillItemRequest {
    private Long billId;
    private Long billItemId;
    private int quantity;

    public BillItemRequest(Long billId, Long billItemId, int quantity) {
        this.billId = billId;
        this.billItemId = billItemId;
        this.quantity = quantity;
    }

    public BillItemRequest() {
    }

    public Long getBillId() {
        return billId;
    }

    public void setBillId(Long billId) {
        this.billId = billId;
    }

    public Long getBillItemId() {
        return billItemId;
    }

    public void setBillItemId(Long billItemId) {
        this.billItemId = billItemId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
