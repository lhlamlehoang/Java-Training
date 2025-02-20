package service;

import model.Bill;
import repository.BillRepository;

import java.io.*;
import java.util.List;

public class BillService {
    private final BillRepository billRepository = new BillRepository();

    public List<Bill> getListBill(){
        return billRepository.getBills();
    }

    public void serialize(Object o, String fileName) throws IOException {
        FileOutputStream fo = new FileOutputStream(fileName);
        ObjectOutputStream oos = new ObjectOutputStream(fo);
        oos.writeObject(o);
        fo.close();
    }

    public Object deserialize (String fileName) throws IOException, ClassNotFoundException {
        FileInputStream fi = new FileInputStream(fileName);
        ObjectInputStream ois = new ObjectInputStream(fi);
        Object o = ois.readObject();
        ois.close();
        return o;
    }
}
