package org.example.transaction_error;

import org.example.entity.UserEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Stream;

@Repository
public interface LongStreamTransactionRepo extends CrudRepository<UserEntity, Long> {

    @Query(value = "select t.id FROM UserEntity t where t.name in (:names)")
    Stream<Long> findUserIdByEntityNames(@Param("names") List<String> names);
}
