package com.kodilla.ecommercee.domain;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "GROUPS")
public class Group {
    Long groupId;
    String groupName;
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
}
