package com.training.javatrainingphase2.controller;

import com.training.javatrainingphase2.model.BillItems;
import com.training.javatrainingphase2.service.BillItemsService;
import com.training.javatrainingphase2.util.BillItemRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/billItems")
public class BillItemController {
    private final BillItemsService billItemsService;

    public BillItemController(BillItemsService billItemsService) {
        this.billItemsService = billItemsService;
    }

    @PutMapping("/updateQuantity")
    public ResponseEntity<String> updateQuantity(@RequestBody BillItemRequest billItemRequest){
        return billItemsService.updateItemQuantity(billItemRequest.getBillId(), billItemRequest.getBillItemId(), billItemRequest.getQuantity());
    }

    @PostMapping("/addToBill")
    public ResponseEntity<String> addToBill(@RequestParam("billId") Long billId, @RequestBody BillItems billItems){
        return billItemsService.addItemIntoBill(billId, billItems);
    }

    @DeleteMapping("/deleteFromBill")
    public ResponseEntity<String> deleteFromBill(@RequestParam("id") Long billItemId){
        return billItemsService.deleteItemFromBill(billItemId);
    }
}
