package com.training.javatrainingphase2.controller;

import com.training.javatrainingphase2.model.Bill;
import com.training.javatrainingphase2.service.BillService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/bill")
public class BillController {
    private final BillService billService;

    public BillController(BillService billService){
        this.billService = billService;
    }

    @GetMapping("/getAll")
    public Map<String, Object> getAllBills(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "10") Integer size){
        Page<Bill> p = billService.getAllBills(PageRequest.of(page, size));
        return Map.of(
                "page", p.getNumber(),
                "items", p.getContent(),
                "totalElements", p.getTotalElements(),
                "currentElements", p.getNumberOfElements(),
                "totalPages", p.getTotalPages()
        );
    }

    @GetMapping("/getById")
    public ResponseEntity<Object> getBillById(@RequestParam("id") Long id){
        return billService.getBillById(id);
    }

    @PostMapping("/addBill")
    public ResponseEntity<String> addBill(){
        return billService.addBill();
    }

    @DeleteMapping("/deleteById")
    public ResponseEntity<String> deleteBillById(@RequestParam("id") Long id){
        return billService.deleteBillById(id);
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateBill(@RequestBody Bill bill){
        return billService.updateBill(bill);
    }

    @PostMapping("/print")
    public ResponseEntity<String> printBill(@RequestParam("id") Long billId){
        return billService.printBill(billId);
    }
}
