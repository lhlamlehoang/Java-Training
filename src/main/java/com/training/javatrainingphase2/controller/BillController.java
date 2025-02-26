package com.training.javatrainingphase2.controller;

import com.training.javatrainingphase2.model.Bill;
import com.training.javatrainingphase2.service.BillService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/bill")
@Tag(name = "Bill Controller", description = "Manage bill")
public class BillController {
    private final BillService billService;

    public BillController(BillService billService){
        this.billService = billService;
    }

    @Operation(description = "Get all bills by pagination")
    @GetMapping
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

    @Operation(description = "Get bill by id")
    @GetMapping("/{id}")
    public ResponseEntity<Object> getBillById(@PathVariable Long id){
        return billService.getBillById(id);
    }

    @Operation(description = "Add new bill")
    @PostMapping
    public ResponseEntity<String> addBill(){
        return billService.addBill();
    }

    @Operation(description = "Delete bill by id")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBillById(@PathVariable Long id){
        return billService.deleteBillById(id);
    }

//    @PutMapping
//    public ResponseEntity<String> updateBill(@RequestBody Bill bill){
//        return billService.updateBill(bill);
//    }

    @Operation(description = "Print bill to file.txt")
    @GetMapping("/print/{id}")
    public ResponseEntity<String> printBill(@PathVariable Long id){
        return billService.printBill(id);
    }
}
