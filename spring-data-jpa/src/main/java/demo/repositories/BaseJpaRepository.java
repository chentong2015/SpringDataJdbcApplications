package demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

// 泛型JpaRepository<T>不需要生成Bean注入到Spring IoC中
// 不添加@Repository则不会作为bean被注入到IoC中
@NoRepositoryBean
public interface BaseJpaRepository<T> extends JpaRepository<T, Long> {
}
