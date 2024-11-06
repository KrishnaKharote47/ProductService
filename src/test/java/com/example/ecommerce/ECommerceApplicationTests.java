package com.example.ecommerce;

import com.example.ecommerce.controller.ProductController;
import com.example.ecommerce.models.Product;
import com.example.ecommerce.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProductController.class)
class ECommerceApplicationTests {

    @MockBean
    ProductService productService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void test_whenGetProductByID_thenReturnProduct() throws Exception {

        // Mocked Product
        Product product = new Product();
        product.setId(2L); // Correct long literal with uppercase 'L'
        product.setTitle("MacBook");

        // Mock the service call
        when(productService.getProductById(anyLong(), eq("LOG9867"))).thenReturn(product);

        // Perform the GET request and assert response
        MvcResult result = mockMvc.perform(get("/products/2")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON)) // Ensure content type is set correctly
                .andExpect(jsonPath("$.id").value(2L))
                .andExpect(jsonPath("$.title").value("MacBook"))
                .andReturn();
        System.out.println("Response Content-Type: " + result.getResponse().getContentType());
        System.out.println("Response Body: " + result.getResponse().getContentAsString());

    }
}
