package com.abcbank.voucherservice;

import com.abcbank.voucherservice.model.dto.OrderDTO;
import com.abcbank.voucherservice.model.dto.PurchaseRequest;
import com.abcbank.voucherservice.model.entity.Order;
import com.abcbank.voucherservice.model.entity.User;
import com.abcbank.voucherservice.repository.mysql.OrderRepository;
import com.abcbank.voucherservice.repository.mysql.UserRepository;
import com.abcbank.voucherservice.service.rest.PurchaseService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.Instant;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@RunWith(SpringRunner.class)
@SpringBootTest
@ExtendWith(SpringExtension.class)
public class PurchaseServiceTest {

    @Autowired
    private PurchaseService purchaseService;

    @MockBean
    OrderRepository orderRepository;

    @MockBean
    UserRepository userRepository;

    @Before
    public void setUp() {
        Mockito.when(purchaseService.purchaseList("78786786","request_12345"))
                .thenReturn(IntStream.range(0, 10)
                        .mapToObj(i -> new OrderDTO(Long.parseLong(String.valueOf(i)), "73c8deaa66113c59adbdb2263fc10254", "VC_" + i, "COMPLETED", "10000", "2GB", "Viettel"))
                        .collect(Collectors.toList()));
    }

    @Test
    public void testpurchaseList() {
        Assert.assertEquals(10, purchaseService.purchaseList("78786786", "request_12345").size());
    }

    @Test
    public void testpurchase() {
        Mockito.when(userRepository.findByPhoneNumber("78786786"))
                .thenReturn(new User(1L, "ACTIVE","78786786", Instant.now(), Instant.now()));

        Order order = new Order();
        order.setId(1L);
        order.setPhoneNumber("73c8deaa66113c59adbdb2263fc10254");
        order.setStatus("INIT");
        order.setCreatedDate(Instant.now());
        order.setUserId(1L);
        order.setRequestId("request_12345");

        Mockito.when(orderRepository.save(Mockito.any(Order.class)))
                .thenAnswer(i -> i.getArguments()[0]);

        Mockito.doReturn(Optional.of(order)).when(orderRepository).findById(1L);

        Mockito.when(purchaseService.getVoucherCode(1L, "78786786", "Viettel", "10000", "2GB", "request_12345"))
                .thenReturn("VC_12345");
//        String phoneNumber, String telco,String price, String dataPlan
        Assert.assertFalse(purchaseService.purchaseData(new PurchaseRequest("78786786", "Viettel", "10000", "2GB"), "request_id_12345").contains("-"));
    }

}
