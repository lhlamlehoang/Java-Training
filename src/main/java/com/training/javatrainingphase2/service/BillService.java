package com.training.javatrainingphase2.service;

import com.training.javatrainingphase2.exception.BillNotFoundException;
import com.training.javatrainingphase2.model.Bill;
import com.training.javatrainingphase2.repository.BillRepository;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Optional;

@Slf4j
@Service
public class BillService {
    private static final Logger log = LoggerFactory.getLogger(BillService.class);
    private final BillRepository billRepository;
    private static final String FILEPATH = "D:\\HL\\JavaTrainingPhase2\\bill.txt";

    public BillService(BillRepository billRepository){
        this.billRepository = billRepository;
    }

    public ResponseEntity<String> addBill(){
        Bill bill = new Bill();
        bill.setTotal(0);
        bill.setDate(LocalDateTime.now());
        bill.setStatus(1);

        billRepository.save(bill);
        return new ResponseEntity<>("Bill added!", HttpStatus.CREATED);
    }

    public Page<Bill> getAllBills(Pageable pageable){
        return billRepository.getAllBills(pageable);
    }

    public ResponseEntity<Object> getBillById(Long id){
        Bill bill = billRepository.findById(id).orElseThrow(() -> new BillNotFoundException("Bill not found with id " + id));

        return new ResponseEntity<>(bill, HttpStatus.OK);
    }

    public ResponseEntity<String> deleteBillById(Long id){
        Bill bill = billRepository.findById(id).orElseThrow(() -> new BillNotFoundException("Bill not found with id " + id));

        billRepository.softDeleteBillById(id);
        return new ResponseEntity<>("Bill has been deleted!", HttpStatus.OK);
    }

//    public ResponseEntity<String> updateBill(Bill bill){
//        Optional<Bill> b = billRepository.findById(bill.getId());
//        if (b.isEmpty()){
//            return new ResponseEntity<>("Bill id not exist!", HttpStatus.BAD_REQUEST);
//        }
//        Bill newBill = new Bill(bill.getId(), bill.getBillItems(), bill.getTotal(), bill.getDate(), bill.getStatus());
//        billRepository.save(newBill);
//        return new ResponseEntity<>("Bill has been updated!", HttpStatus.OK);
//    }

    public ResponseEntity<String> printBill(Long billId){
        Bill bill = billRepository.findById(billId).orElseThrow(() -> new BillNotFoundException("Bill not found with id " + billId));

        try (FileOutputStream fos = new FileOutputStream(FILEPATH)){
            BufferedOutputStream bos = new BufferedOutputStream(fos);
            bos.write(bill.toString().getBytes());
            bos.close();
        }
        catch (IOException e){
            log.error("Error during print bill {}", e.getMessage());
            throw new RuntimeException("Error during print bill!");
        }

        return new ResponseEntity<>("Your bill saved at " + FILEPATH + "\n\n" + bill, HttpStatus.OK);
    }
}
