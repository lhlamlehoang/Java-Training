package model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Bill implements Serializable {
    private int id;
    private List<BillItems> lstBillItems = new ArrayList<BillItems>();
    private LocalDateTime billDate;

    public Bill(int id, List<BillItems> lstBillItems, LocalDateTime billDate){
        this.id = id;
        this.lstBillItems = lstBillItems;
        this.billDate = billDate;
    }

    // Getters and Setters
    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public List<BillItems> getLstBillItems(){
        return lstBillItems;
    }

    public void setLstBillItems(List<BillItems> lstBillItems){
        this.lstBillItems = lstBillItems;
    }

    public LocalDateTime getBillDate(){
        return billDate;
    }

    public void setBillDate(LocalDateTime billDate){
        this.billDate = billDate;
    }


    @Override
    public String toString(){
        StringBuilder bill = new StringBuilder();
        double total = 0;
        for (BillItems item : lstBillItems){
            bill.append("\nItem: ").append(item.getMenuItems().getName())
                    .append("\nQuantity: ").append(item.getQuantity())
                    .append("\nPrice: ").append(item.getMenuItems().getPrice())
                    .append("\nTotal amount: ").append(item.getQuantity() * item.getMenuItems().getPrice())
                    .append("\n");
            total += item.getQuantity() * item.getMenuItems().getPrice();
        }
        bill.append("\nTotal: " + total);
        return bill.toString();
    }
}
