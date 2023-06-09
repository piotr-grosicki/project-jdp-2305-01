package com.kodilla.ecommercee.repository;

import com.kodilla.ecommercee.domain.Group;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface GroupRepository extends CrudRepository<Group,Long> {
  
    List<Group> findAll();
    Group save(final Group group);
    Group findByGroupId(final long groupId);
}
