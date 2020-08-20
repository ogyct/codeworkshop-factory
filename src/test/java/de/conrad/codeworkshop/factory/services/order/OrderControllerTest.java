package de.conrad.codeworkshop.factory.services.order;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.conrad.codeworkshop.factory.services.order.api.Order;
import de.conrad.codeworkshop.factory.services.order.api.Position;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class OrderControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void createOrderAllFine() throws Exception {
        Order order = new Order();
        List<Position> positions = new ArrayList<>();
        positions.add(new Position(123456, new BigDecimal("10")));
        positions.add(new Position(1234567, new BigDecimal("0.1")));
        positions.add(new Position(12345678, new BigDecimal("42.42")));
        order.setPositions(positions);
//        System.out.println(objectMapper.writeValueAsString(order));
        mockMvc.perform(MockMvcRequestBuilders.post("/order/create")
                .content(objectMapper.writeValueAsString(order))
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
        )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("ACCEPTED"))
                .andExpect(jsonPath("$.orderNumber.orderNumberPlain").exists())

        ;
    }

    @Test
    void createOrderOneInvalid() throws Exception {
        Order order = new Order();
        List<Position> positions = new ArrayList<>();
        positions.add(new Position(12345, new BigDecimal("10")));
        positions.add(new Position(1234567, new BigDecimal("0.1")));
        positions.add(new Position(12345678, new BigDecimal("42.42")));
        order.setPositions(positions);
//        System.out.println(objectMapper.writeValueAsString(order));
        mockMvc.perform(MockMvcRequestBuilders.post("/order/create")
                .content(objectMapper.writeValueAsString(order))
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
        )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("DECLINED"))
                .andExpect(jsonPath("$.orderNumber.orderNumberPlain").doesNotExist())

        ;
    }
}