package com.kodilla.ecommercee.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
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

    public Group(Long groupId, String groupName) {
        this.groupId = groupId;
        this.groupName = groupName;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull
    @Column(name = "GROUP_ID", unique = true)
    public Long getGroupId() {
        return groupId;
    }

    @NotNull
    @Column(name = "GROUP_NAME")
    public String getGroupName() {
        return groupName;
    }

    @OneToMany(
            targetEntity = Product.class,
            mappedBy = "group",
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER
    )
    public List<Product> getProductList() {
        return productList;
    }

}
