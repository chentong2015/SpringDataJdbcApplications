package demo.repository.cycle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

// 自定义实现Custom接口, 并依赖注入Data JPA生成另一个接口实现
@Repository
public class CycleRepositoryImpl implements CycleRepositoryCustom {

    @Autowired
    private CycleRepositoryJpa cycleRepositoryJpa;

    @Override
    public Page<CycleClass> findAll(Pageable pageable) {
        return cycleRepositoryJpa.findAll(pageable);
    }
}
