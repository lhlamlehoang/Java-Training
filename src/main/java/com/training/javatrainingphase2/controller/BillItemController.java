package com.training.javatrainingphase2.controller;

import com.training.javatrainingphase2.model.BillItems;
import com.training.javatrainingphase2.service.BillItemsService;
import com.training.javatrainingphase2.util.BillItemRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/billItems")
@Tag(name = "BillItemController", description = "Manage bill items")
public class BillItemController {
    private final BillItemsService billItemsService;

    public BillItemController(BillItemsService billItemsService) {
        this.billItemsService = billItemsService;
    }

    @Operation(description = "Update quantity of item in specific bill")
    @PutMapping("/updateQuantity")
    public ResponseEntity<String> updateQuantity(@RequestBody BillItemRequest billItemRequest){
        return billItemsService.updateItemQuantity(billItemRequest.getBillId(), billItemRequest.getBillItemId(), billItemRequest.getQuantity());
    }

    @Operation(description = "Add item to specific bill")
    @PostMapping("/addToBill")
    public ResponseEntity<String> addToBill(@RequestParam("billId") Long billId, @RequestBody BillItems billItems){
        return billItemsService.addItemIntoBill(billId, billItems);
    }

    @Operation(description = "Delete item from specific bill")
    @DeleteMapping("/deleteFromBill")
    public ResponseEntity<String> deleteFromBill(@RequestParam("id") Long billItemId){
        return billItemsService.deleteItemFromBill(billItemId);
    }
}
