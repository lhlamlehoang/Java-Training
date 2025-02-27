package com.training.javatrainingphase2.service;


import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.training.javatrainingphase2.model.Bill;
import com.training.javatrainingphase2.model.BillItems;
import com.training.javatrainingphase2.model.Food;
import com.training.javatrainingphase2.model.MenuItems;
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
public class BillServiceTest {
    @InjectMocks
    BillService billService;

    @Mock
    BillRepository billRepository;

    @Test
    void getBillById_ReturnBill(){
        Long id = 1L;
        Bill bill = new Bill(id, null, 100000, null, 1);

        when(billRepository.findById(id)).thenReturn(Optional.of(bill));

        ResponseEntity<Object> actualResult = billService.getBillById(id);

        // Assert
        Assertions.assertEquals(HttpStatus.OK, actualResult.getStatusCode());
        Assertions.assertEquals(bill, actualResult.getBody());
        verify(billRepository, times(1)).findById(id);
    }

    @Test
    void addBill_BillAdded(){
        Bill bill = new Bill();
        bill.setId(1L);
        MenuItems item = new Food(1L, "Pizza", "Pizza Company", 100000, "", null, 1, 2);
        List<BillItems> lstBillItems = new ArrayList<>();
        BillItems billItem = new BillItems(2L, item, 1, bill);
        lstBillItems.add(billItem);
        bill.setBillItems(lstBillItems);
        bill.setTotal(100000);
        when(billRepository.save(any(Bill.class))).thenReturn(bill);
        ResponseEntity<String> actualResult = billService.addBill();

        // Assert
        Assertions.assertEquals(HttpStatus.CREATED, actualResult.getStatusCode());
        verify(billRepository, times(1)).save(any(Bill.class));
    }

    @Test
    void deleteBill_BillDeleted(){
        Bill bill = new Bill(1L, null, 100000, null, 1);

        when(billRepository.findById(bill.getId())).thenReturn(Optional.of(bill));
        doNothing().when(billRepository).softDeleteBillById(bill.getId());
        ResponseEntity<String> actualResult = billService.deleteBillById(bill.getId());

        // Assert
        Assertions.assertEquals(HttpStatus.OK, actualResult.getStatusCode());
        verify(billRepository, times(1)).softDeleteBillById(bill.getId());
    }
}
