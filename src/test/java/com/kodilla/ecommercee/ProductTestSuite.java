package com.kodilla.ecommercee;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kodilla.ecommercee.controller.ProductController;
import com.kodilla.ecommercee.domain.Group;
import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.dto.ProductDto;
import com.kodilla.ecommercee.exception.ProductNotFoundException;
import com.kodilla.ecommercee.mapper.ProductMapper;
import com.kodilla.ecommercee.repository.GroupRepository;
import com.kodilla.ecommercee.repository.ProductRepository;
import com.kodilla.ecommercee.service.ProductService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.ServletContext;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {EcommerceeApplication.class})
@WebAppConfiguration
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class ProductTestSuite {

    private MockMvc mockMvc;
    private static final String CONTENT_TYPE = "application/json;charset=UTF-8";

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Mock
    private ProductService service;

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private GroupRepository groupRepository;

    @BeforeEach
    void setUp() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(new ProductController(service, productMapper)).build();
    }

    @Test
    public void whenServletContext_thenItProvidesProductController() {
        // Given
        ServletContext servletContext = webApplicationContext.getServletContext();

        // Then
        assertNotNull(servletContext);
        assertTrue(servletContext instanceof MockServletContext);
        assertNotNull(webApplicationContext.getBean("productController"));
    }

    @Test
    public void givenHomePageURI_whenMockMVC_thenReturnsStatusOk_andGetAllProductsTest() throws ProductNotFoundException {
        // Given
        Group group = new Group("Group");

        Product product1 = new Product("Product1", "New product1",
                1, new BigDecimal(25), group);
        Product product2 = new Product("Product2", "New product2",
                2, new BigDecimal(50), group);

        List<Product> productList = new ArrayList<>();
        productList.add(product1);
        productList.add(product2);

        // When
        groupRepository.save(group);
        productRepository.save(product1);
        productRepository.save(product2);

        when(service.getAllProducts()).thenReturn(productList);
        when(service.getProduct(product1.getProductId())).thenReturn(product1);
        when(service.getProduct(product2.getProductId())).thenReturn(product2);

        // Then
        MvcResult mvcResult = null;
        try {
            mvcResult = this.mockMvc.perform(get("/v1/products").
                    contentType(MediaType.APPLICATION_JSON_UTF8)).
                    andExpect(status().isOk()).
                    andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8)).
                    andExpect(jsonPath("$[0].productId", Matchers.is(2))).
                    andExpect(jsonPath("$[1].productId", Matchers.is(3))).
                    andExpect(jsonPath("$[0].productName", Matchers.is("Product1"))).
                    andExpect(jsonPath("$[1].productName", Matchers.is("Product2"))).
                    andExpect(jsonPath("$[0].group.groupName", Matchers.is("Group"))).
                    andDo(print()).andReturn();
        } catch (Exception e) {
            e.getCause();
        }

        assertEquals(200, mvcResult.getResponse().getStatus());
        assertEquals(CONTENT_TYPE, Objects.requireNonNull(mvcResult).getResponse().getContentType());
        assertEquals(2, productRepository.findAll().size());
    }

    @Test
    public void givenHomePageURI_whenMockMVC_thenReturnsStatusOk_andGetProductByIdTest() throws ProductNotFoundException {
        // Given
        Group group = new Group("Group");

        Product product = new Product("Product", "New product1",
                1, new BigDecimal(25), group);

        // When
        groupRepository.save(group);
        productRepository.save(product);

        when(service.getProduct(product.getProductId())).thenReturn(product);

        // Then
        MvcResult mvcResult = null;
        try {
            mvcResult = this.mockMvc.perform(get("/v1/products/{productId}", product.getProductId()).
                    accept(MediaType.APPLICATION_JSON_UTF8)).
                    andExpect(status().isOk()).
                    andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8)).
                    andExpect(jsonPath("$.productId").value(2)).
                    andExpect(jsonPath("$.productName").value("Product")).
                    andExpect(jsonPath("$.group.groupName").value("Group")).
                    andDo(print()).andReturn();
        } catch (Exception e) {
            e.getCause();
        }

        assertEquals(200, Objects.requireNonNull(mvcResult).getResponse().getStatus());
        assertEquals(CONTENT_TYPE, Objects.requireNonNull(mvcResult).getResponse().getContentType());
        assertTrue(productRepository.existsById(product.getProductId()));
    }

    @Test
    public void givenHomePageURI_whenMockMVC_thenReturnsStatusOk_andCreateProductTest() {
        // Given
        ProductDto productDto = new ProductDto();
        productDto.setProductId(1L);
        productDto.setProductName("Product1");
        productDto.setProductDescription("New product1");
        productDto.setProductQuantity(1);
        productDto.setProductPrice(new BigDecimal(25));
        productDto.setGroup(groupRepository.save(new Group("Group")));

        // When
        Product product = productMapper.mapToProduct(productDto);
        productRepository.save(product);

        when(service.saveProduct(product)).thenReturn(product);

        // Then
        MvcResult mvcResult = null;
        try {
            mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.post("/v1/products").
                    content(asJsonString(productDto)).contentType(MediaType.APPLICATION_JSON).
                    accept(MediaType.APPLICATION_JSON)).
                    andExpect(status().isOk()).andDo(print()).andReturn();
        } catch (Exception e) {
            e.getCause();
        }

        assertEquals(200, Objects.requireNonNull(mvcResult).getResponse().getStatus());
        assertTrue(productRepository.existsById(product.getProductId()));
    }

    @Test
    public void givenHomePageURI_whenMockMVC_thenReturnsStatusOk_andUpdateProductTest() {
        // Given
        ProductDto productDto = new ProductDto();
        productDto.setProductId(1L);
        productDto.setProductName("Product1000");
        productDto.setProductDescription("New product1");
        productDto.setProductQuantity(1);
        productDto.setProductPrice(new BigDecimal(25));
        productDto.setGroup(groupRepository.save(new Group("Group")));

        // When
        Product product = productMapper.mapToProduct(productDto);
        productRepository.save(product);

        when(service.saveProduct(any(Product.class))).thenReturn(product);
        when(service.updateProduct(any(ProductDto.class))).thenReturn(productDto);

        // Then
        MvcResult mvcResult = null;
        try {
            mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.put("/v1/products").
                    content(asJsonString(new ProductDto(1L, "Product111", "ProductNEW", 1, new BigDecimal(25), null))).
                    contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).
                    andExpect(status().isOk()).andDo(print()).andReturn();
        } catch (Exception e) {
            e.getCause();
            e.printStackTrace();
        }

        assertEquals(200, Objects.requireNonNull(mvcResult).getResponse().getStatus());
        assertTrue(productRepository.existsById(product.getProductId()));
    }

    @Test
    public void givenHomePageURI_whenMockMVC_thenReturnsStatusOk_andDeleteProductById_andWithoutDeleteGroupTest() {
        // Given
        Group group = new Group("Group");

        Product product = new Product(
                "Product",
                "New product1",
                1,
                 new BigDecimal(25), group);

        group.getProductList().add(product);

        // When
        groupRepository.save(group);
        productRepository.save(product);
        Group groupDb = groupRepository.findByGroupId(group.getGroupId());
        productRepository.deleteById(product.getProductId());

        doNothing().when(service).deleteProduct(product);

        // Then
        MvcResult mvcResult = null;
        try {
            mvcResult = this.mockMvc.perform(delete("/v1/products/{productId}", product.getProductId()).
                    accept(MediaType.APPLICATION_JSON_UTF8)).
                    andExpect(status().isOk()).andDo(print()).andReturn();
        } catch (Exception e) {
            e.getCause();
        }

        assertEquals(200, Objects.requireNonNull(mvcResult).getResponse().getStatus());
        assertFalse(productRepository.existsById(product.getProductId()));
        assertEquals(groupDb.getGroupId(), group.getGroupId());
        assertEquals(1, groupRepository.count());
        assertTrue(groupRepository.existsById(groupDb.getGroupId()));
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}