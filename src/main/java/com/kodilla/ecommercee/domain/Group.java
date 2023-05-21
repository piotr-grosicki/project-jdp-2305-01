package com.kodilla.ecommercee.domain;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Setter
@NoArgsConstructor
@Entity
@Table(name = "GROUP_OF_PRODUCTS")
public class Group {
    private Long groupId;
    private String groupName;
    private List<Product> productList = new ArrayList<>();


    public Group(final String groupName) {
        this.groupName = groupName;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NonNull
    @Column(name = "GROUP_ID", unique = true)
    public Long getGroupId() {
        return groupId;
    }

    @NonNull
    @Column(name = "GROUP_NAME")
    public String getGroupName() {
        return groupName;
    }

    @OneToMany(
            targetEntity = Product.class,
            cascade = CascadeType.PERSIST,
            mappedBy = "group",
            fetch = FetchType.LAZY)
    public List<Product> getProductList() {
        return productList;
    }
}