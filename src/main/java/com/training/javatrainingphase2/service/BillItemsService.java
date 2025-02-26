package com.training.javatrainingphase2.service;

import com.training.javatrainingphase2.exception.BillItemNotFoundException;
import com.training.javatrainingphase2.exception.BillNotFoundException;
import com.training.javatrainingphase2.model.Bill;
import com.training.javatrainingphase2.model.BillItems;
import com.training.javatrainingphase2.repository.BillItemsRepository;
import com.training.javatrainingphase2.repository.BillRepository;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BillItemsService {
    private final BillItemsRepository billItemsRepository;
    private final BillRepository billRepository;

    public BillItemsService(BillItemsRepository billItemsRepository, BillRepository billRepository) {
        this.billItemsRepository = billItemsRepository;
        this.billRepository = billRepository;
    }

    @Transactional
    public ResponseEntity<String> updateItemQuantity(Long billId, Long billItemId, int quantity){
        Bill bill = billRepository.findById(billId).orElseThrow(() -> new BillNotFoundException("Bill not found with id " + billId));
        billItemsRepository.modifyQuantity(billItemId, quantity);
        updateBillTotal(bill);

        return new ResponseEntity<>("Item quantity updated!", HttpStatus.OK);
    }

    @Transactional
    public ResponseEntity<String> addItemIntoBill(Long billId, BillItems billItems){
        Bill bill = billRepository.findById(billId).orElseThrow(() -> new BillNotFoundException("Bill not found with id " + billId));

        // Cumulate quantity when add the same item
        boolean isCumulated = false;
        for (BillItems item : bill.getBillItems()){
            if (item.getItem().getId().equals(billItems.getItem().getId())){
                item.setQuantity(item.getQuantity() + billItems.getQuantity());
                billItemsRepository.save(item);
                isCumulated = true;
            }
        }

        if (!isCumulated){
            billItems.setBill(bill);
            bill.getBillItems().add(billItems);
            billRepository.save(bill);
        }

        updateBillTotal(bill);

        return new ResponseEntity<>("Item added into bill!", HttpStatus.CREATED);
    }

    @Transactional
    public ResponseEntity<String> deleteItemFromBill(Long billItemId){
        BillItems item = billItemsRepository.findById(billItemId).orElseThrow(() -> new BillItemNotFoundException("Bill item not found with id " + billItemId));
        item.getBill().getBillItems().remove(item);
        billItemsRepository.delete(item);
        updateBillTotal(item.getBill());
        return new ResponseEntity<>("Item deleted from bill!", HttpStatus.OK);
    }

    @Transactional
    private void updateBillTotal(Bill bill){
        double total = 0;
        for (BillItems item : bill.getBillItems()){
            total += item.getQuantity() * item.getItem().getPrice();
        }
        bill.setTotal(total);
        billRepository.save(bill);
    }
}
