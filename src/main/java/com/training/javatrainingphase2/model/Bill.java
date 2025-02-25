package com.training.javatrainingphase2.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Entity
public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "bill")
    @JsonManagedReference
    private List<BillItems> billItems;

    private double total;
    private LocalDateTime date;
    private int status;

    public Bill(List<BillItems> billItems, double total, LocalDateTime date, int status) {
        this.billItems = billItems;
        this.total = total;
        this.date = date;
        this.status = status;
    }

    public Bill(Long id, List<BillItems> billItems, double total, LocalDateTime date, int status) {
        this.id = id;
        this.billItems = billItems;
        this.total = total;
        this.date = date;
        this.status = status;
    }

    public Bill() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<BillItems> getBillItems() {
        return billItems;
    }

    public void setBillItems(List<BillItems> billItems) {
        this.billItems = billItems;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    @Override
    public String toString(){
        StringBuilder billItemsString = new StringBuilder();
        for (BillItems items : this.billItems){
            MenuItems menuItems = items.getItem();
            billItemsString.append(
                    "****************************\n" +
                    "Name: " + menuItems.getName() + "\n" +
                    "Description: " + menuItems.getDescription() + "\n" +
                    "Quantity: " + items.getQuantity() + "\n" +
                    "Price: " + menuItems.getPrice() + "\n" +
                    "Total amount: " + items.getQuantity() * menuItems.getPrice() + "\n");
        }
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String timeOrder = dateTimeFormatter.format(this.date);
        return  billItemsString + "\n" +
                "****************************\n" +
                "Total: " + this.total + "\n" +
                "Time Order: " + timeOrder + "\n";

    }
}
