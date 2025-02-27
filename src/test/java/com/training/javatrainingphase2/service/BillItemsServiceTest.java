package com.training.javatrainingphase2.service;

import com.training.javatrainingphase2.exception.BillItemNotFoundException;
import com.training.javatrainingphase2.model.Bill;
import com.training.javatrainingphase2.model.BillItems;
import com.training.javatrainingphase2.model.Food;
import com.training.javatrainingphase2.model.MenuItems;
import com.training.javatrainingphase2.repository.BillItemsRepository;
import com.training.javatrainingphase2.repository.BillRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Optional;
import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BillItemsServiceTest {
    @InjectMocks
    BillItemsService billItemsService;

    @Mock
    BillItemsRepository billItemsRepository;

    @Mock
    BillRepository billRepository;


    @Test
    void addItemToBill_ItemAdded(){
        Bill bill = new Bill(1L, null, 100000, null, 1);
        MenuItems item = new Food(2L, "Pizza", "Pizza Company", 100000, "", null, 1, 2);
        BillItems billItem = new BillItems(3L, item, 1, bill);
        List<BillItems> lstBillItems = new ArrayList<>();
        lstBillItems.add(billItem);
        bill.setBillItems(lstBillItems);

        when(billRepository.findById(bill.getId())).thenReturn(Optional.of(bill));
        when(billRepository.save(any(Bill.class))).thenReturn(bill);

        ResponseEntity<String> actualResult = billItemsService.addItemIntoBill(bill.getId(), billItem);

        // Assert
        Assertions.assertEquals(HttpStatus.CREATED, actualResult.getStatusCode());
        verify(billRepository, times(1)).save(any(Bill.class));
    }

    @Test
    void deleteItemFromBill_ItemDeleted(){
        Bill bill = new Bill(1L, null, 100000, null, 1);
        MenuItems item = new Food(2L, "Pizza", "Pizza Company", 100000, "", null, 1, 2);
        BillItems billItem = new BillItems(3L, item, 1, bill);
        List<BillItems> lstBillItems = new ArrayList<>();
        lstBillItems.add(billItem);
        bill.setBillItems(lstBillItems);

        when(billItemsRepository.findById(billItem.getId())).thenReturn(Optional.of(billItem));

        ResponseEntity<String> actualResult = billItemsService.deleteItemFromBill(billItem.getId());

        // Assert
        Assertions.assertEquals(HttpStatus.OK, actualResult.getStatusCode());
        verify(billItemsRepository, times(1)).delete(billItem);
    }

    @Test
    void updateItemQuantity_QuantityUpdated(){
        Bill bill = new Bill(1L, null, 100000, null, 1);
        MenuItems item = new Food(2L, "Pizza", "Pizza Company", 100000, "", null, 1, 2);
        BillItems billItem = new BillItems(3L, item, 1, bill);
        List<BillItems> lstBillItems = new ArrayList<>();
        lstBillItems.add(billItem);
        bill.setBillItems(lstBillItems);

        when(billRepository.findById(bill.getId())).thenReturn(Optional.of(bill));

        ResponseEntity<String> actualResult = billItemsService.updateItemQuantity(bill.getId(), billItem.getId(), 2);

        // Assert
        Assertions.assertEquals(HttpStatus.OK, actualResult.getStatusCode());
        verify(billItemsRepository, times(1)).modifyQuantity(billItem.getId(), 2);
    }
}
