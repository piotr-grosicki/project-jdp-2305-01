package com.kodilla.ecommercee.domain;

import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

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

    public Group(String groupName) {
        this.groupName = groupName;
    }

    public Group(Long groupId, String groupName) {
        this.groupId = groupId;
        this.groupName = groupName;
    }

    @Id
    @GeneratedValue
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
            cascade = {CascadeType.MERGE, CascadeType.PERSIST},
            mappedBy = "group",
            fetch = FetchType.LAZY)
    public List<Product> getProductList() {
        return productList;
    }
}