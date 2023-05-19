package com.kodilla.ecommercee;

import com.kodilla.ecommercee.domain.Group;
import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.repository.GroupRepository;
import com.kodilla.ecommercee.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

@DataJpaTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class ProductEntityTestSuite {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private GroupRepository groupRepository;

    @Test
    public void returnAllProducts() {
        // given
        Group group = new Group("Group");

        Product product1 = new Product(
                "Product1",
                "Description1",
                10,
                new BigDecimal(10));

        Product product2 = new Product(
                "Product2",
                "Description2",
                20,
                new BigDecimal(20));

        product1.setGroup(group);
        product2.setGroup(group);

        group.getProductList().addAll(Arrays.asList(product1,product2));

        // when
        groupRepository.save(group);

        // then
        assertEquals(2,productRepository.count());

    }

    @Test
    public void returnOneProduct() {
        // given
        Group group = new Group("Group");

        Product product1 = new Product(
                "Product1",
                "Description1",
                10,
                new BigDecimal(10));

        Product product2 = new Product(
                "Product2",
                "Description2",
                20,
                new BigDecimal(20));

        product1.setGroup(group);
        product2.setGroup(group);

        group.getProductList().addAll(Arrays.asList(product1,product2));

        // when
        groupRepository.save(group);

        //then
        assertTrue(productRepository.existsById(product2.getProductId()));
    }

    @Test
    public void createNewProduct() {
        // given
        Product product = new Product(
                "Product1",
                "Description1",
                10,
                new BigDecimal(10));

        product.setGroup(null);

        // when
        productRepository.save(product);
        Product expectedProduct = productRepository.findByProductId(product.getProductId());

        // then
        assertEquals("Product1",expectedProduct.getProductName());
        assertEquals("Description1",expectedProduct.getProductDescription());
        assertEquals(10, expectedProduct.getProductQuantity());
        assertEquals(BigDecimal.valueOf(10).setScale(2), expectedProduct.getProductPrice().setScale(2));
    }

    @Test
    public void updateProduct() {
        // given
        Group group = new Group("Group");

        Product product1 = new Product(
                "Product1",
                "Description1",
                10,
                new BigDecimal(10));

        Product product2 = new Product(
                "Product2",
                "Description2",
                20,
                new BigDecimal(20));

        product1.setGroup(group);
        product2.setGroup(group);

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
    public void deleteOneProduct_ShouldKeepAllGroups() {
        // given
        Group group = new Group("Group");

        Product product1 = new Product(
                "Product1",
                "Description1",
                10,
                new BigDecimal(10));

        Product product2 = new Product(
                "Product2",
                "Description2",
                20,
                new BigDecimal(20));

        product1.setGroup(group);
        product2.setGroup(group);

        group.getProductList().addAll(Arrays.asList(product1,product2));
        groupRepository.save(group);

        // when
        Product productReadFromDb = productRepository.findByProductId(product1.getProductId());
        Group groupReadFromProduct = productReadFromDb.getGroup();

        groupReadFromProduct.getProductList().remove(productReadFromDb);
        groupRepository.save(groupReadFromProduct);

        productRepository.deleteById(product1.getProductId());

        // then
        assertEquals(1,productRepository.count());
        assertEquals(1,groupRepository.count());
    }

    @Test
    public void deleteAllProducts_shouldKeepAllGroups() {
        // given
        Group group = new Group("Group");

        Product product1 = new Product(
                "Product1",
                "Description1",
                10,
                new BigDecimal(10));

        Product product2 = new Product(
                "Product2",
                "Description2",
                20,
                new BigDecimal(20));

        product1.setGroup(group);
        product2.setGroup(group);

        group.getProductList().add(product1);
        group.getProductList().add(product2);

        groupRepository.save(group);

        // when
        List<Group> groupsReadFromDb = groupRepository.findAll();

        groupsReadFromDb.stream()
                .peek(g -> group.getProductList().clear())
                .forEach(groupRepository::save);

        productRepository.deleteAll();

        // then
        assertEquals(0,productRepository.count());
        assertEquals(1,groupRepository.count());
    }
}