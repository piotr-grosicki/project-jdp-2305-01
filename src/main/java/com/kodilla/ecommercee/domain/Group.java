package com.kodilla.ecommercee.domain;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Setter
@NoArgsConstructor
@Entity
@Table(name = "GROUPS")
public class Group {
    private Long groupId;
    private String groupName;
    private List<Product> productList = new ArrayList<>();

    public Group(final String groupName) {
        this.groupName = groupName;
    }

    @Id
    @GeneratedValue
    @Column(name = "GROUP_ID", unique = true)
    public Long getGroupId() {
        return groupId;
    }

    @Column(name = "GROUP_NAME", unique = true)
    public String getGroupName() {
        return groupName;
    }

    @OneToMany(
            targetEntity = Product.class,
            cascade = CascadeType.ALL,
            mappedBy = "group",
            fetch = FetchType.LAZY)
    public List<Product> getProductList() {
        return productList;
    }
}
