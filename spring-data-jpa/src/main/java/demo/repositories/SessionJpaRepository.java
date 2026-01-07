package demo.repositories;

import demo.entity.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;

import java.time.ZonedDateTime;
import java.util.List;

// <Session, Long> 指定数据类型和Table的主键
// JpaRepository<Session, Long> 接口中包含基本的数据库执行的操作
@Repository
public interface SessionJpaRepository extends JpaRepository<Session, Long> {

    // 自定义查询语句，基于Entity Name名称来创建
    @Query("select s from sessions s where ((s.sessionName = :name " +
            "and s.session_description like :description%) " +
            "and s.session_length >= :length)")
    List<Session> getSessionsByFilters(@Param("name") String name,
                                       @Param("description") String description,
                                       @Param("length") int length);

    // 使用Native Query原生查询语句，必须使用Table Name名称
    @Query(value = "select s from sessions s where (CAST(:startTime as date) is null)", nativeQuery = true)
    List<Session> getSessionByDates(@Nullable @Param("startTime") ZonedDateTime startTime);
}
