package main.java.repository;

import main.java.model.Bill;
import main.java.model.BillItems;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class BillRepository {
    private final List<BillItems> lstBillItems = new ArrayList<>();
    private final List<Bill> lstBills = new ArrayList<>();

    public List<BillItems> getBillItems(){
        return new ArrayList<>(lstBillItems);
    }

    public void addBillItems(BillItems billItems){
        lstBillItems.add(billItems);
    }

    public List<Bill> getBills(){
        return new ArrayList<>(lstBills);
    }

    public void addBill(Bill bill){
        lstBills.add(bill);
    }

    public void serialize(Object o, String fileName) throws IOException {
        FileOutputStream fo = new FileOutputStream(fileName);
        ObjectOutputStream oos = new ObjectOutputStream(fo);
        oos.writeObject(o);
        fo.close();
    }

    public Object deserialize(String fileName) throws IOException, ClassNotFoundException {
        FileInputStream fi = new FileInputStream(fileName);
        ObjectInputStream ois = new ObjectInputStream(fi);
        Object o = ois.readObject();
        ois.close();
        return o;
    }
}
