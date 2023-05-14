package com.kodilla.ecommercee;

import com.kodilla.ecommercee.domain.Group;
import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.repository.GroupRepository;
import com.kodilla.ecommercee.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import java.math.BigDecimal;
import java.util.Arrays;
import static org.junit.Assert.*;

@SpringBootTest()
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class ProductTestSuite {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private GroupRepository groupRepository;

    @Test
    public void shouldReturnAllProducts() {
        // given
        Group group = new Group("Group");

        Product product1 = new Product(
                "Product1",
                "Description1",
                10,
                new BigDecimal(10),
                group);

        Product product2 = new Product(
                "Product2",
                "Description2",
                20,
                new BigDecimal(20),
                group);

        group.getProductList().addAll(Arrays.asList(product1,product2));

        // when
        groupRepository.save(group);

        // then
        assertEquals(2,productRepository.findAll().size());

    }

    @Test
    public void shouldReturnOneProduct() {
        // given
        Group group = new Group("Group");

        Product product1 = new Product(
                "Product1",
                "Description1",
                10,
                new BigDecimal(10),
                group);

        Product product2 = new Product(
                "Product2",
                "Description2",
                20,
                new BigDecimal(20),
                group);

        group.getProductList().addAll(Arrays.asList(product1,product2));

        // when
        groupRepository.save(group);

        //then
        assertTrue(productRepository.existsById(product2.getProductId()));
    }

    @Test
    public void shouldCreateNewProduct() {
        // given
        Group group = new Group("Group");

        Product product = new Product(
                "Product1",
                "Description1",
                10,
                new BigDecimal(10),
                group);

        group.getProductList().add(product);

        // when
        groupRepository.save(group);

        // then
        assertEquals(1, productRepository.count());
    }

    @Test
    public void shouldUpdateProduct() {
        // given
        Group group = new Group("Group");

        Product product1 = new Product(
                "Product1",
                "Description1",
                10,
                new BigDecimal(10),
                group);

        Product product2 = new Product(
                "Product2",
                "Description2",
                20,
                new BigDecimal(20),
                group);

        group.getProductList().addAll(Arrays.asList(product1,product2));

        // when
        groupRepository.save(group);

        product1.setProductName("Update name");
        product2.setProductPrice(BigDecimal.valueOf(333));

        groupRepository.save(group);

        Product expectedProduct1 = productRepository.findByProductId(product1.getProductId());
        Product expectedProduct2 = productRepository.findByProductId(product2.getProductId());

        // then
        assertEquals("Update name", expectedProduct1.getProductName());
        assertEquals(BigDecimal.valueOf(333).setScale(2), expectedProduct2.getProductPrice().setScale(2));

    }

    @Test
    public void shouldDeleteOneProduct() {
        // given
        Group group = new Group("Group");

        Product product1 = new Product(
                "Product1",
                "Description1",
                10,
                new BigDecimal(10),
                group);

        Product product2 = new Product(
                "Product2",
                "Description2",
                20,
                new BigDecimal(20),
                group);

        group.getProductList().addAll(Arrays.asList(product1,product2));

        // when
        groupRepository.save(group);
        productRepository.deleteById(product1.getProductId());

        // then
        assertEquals(1,productRepository.count());
    }

    @Test
    public void shouldRemoveAllProductsButGroupsShouldStay() {
        // given
        Group group = new Group("Group");

        Product product1 = new Product(
                "Product1",
                "Description1",
                10,
                new BigDecimal(10),
                group);

        Product product2 = new Product(
                "Product2",
                "Description2",
                20,
                new BigDecimal(20),
                group);

        group.getProductList().addAll(Arrays.asList(product1,product2));

        // when
        groupRepository.save(group);
        productRepository.deleteAll();

        // then
        assertEquals(0,productRepository.findAll().size());
        assertEquals(1,groupRepository.findAll().size());
    }

    @Test
    public void shouldRemoveGroupAndAllProducts() {
        // given
        Group group = new Group("Group");

        Product product1 = new Product(
                "Product1",
                "Description1",
                10,
                new BigDecimal(10),
                group);

        Product product2 = new Product(
                "Product2",
                "Description2",
                20,
                new BigDecimal(20),
                group);

        group.getProductList().addAll(Arrays.asList(product1,product2));

        // when
        groupRepository.save(group);
        groupRepository.deleteAll();

        // then
        assertEquals(0,groupRepository.findAll().size());
        assertEquals(0,productRepository.findAll().size());
    }
}