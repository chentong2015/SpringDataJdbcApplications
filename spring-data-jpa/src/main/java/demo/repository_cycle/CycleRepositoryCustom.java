package demo.repository_cycle;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

// TODO: 该上层接口已经有两个实现, 不应该再标记@Repository注解
// @Repository
public interface CycleRepositoryCustom {

    Page<CycleClass> findAll(Pageable pageable);
}