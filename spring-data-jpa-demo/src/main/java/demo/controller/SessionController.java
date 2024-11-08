package demo.controller;

import demo.entity.Session;
import demo.repositories.SessionJpaRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/sessions")
public class SessionController {

    // TODO. 这里应该使用Service层的注入
    private SessionJpaRepository repository;

    @Autowired
    public SessionController(SessionJpaRepository repository) {
        this.repository = repository;
    }

    // TODO: JPA Repository查询数据，返回List<Session>
    //  Spring MVC 将数据传递给Jackson(序列类库)，将数据转换成JSON返回
    @GetMapping
    public List<Session> list() {
        return repository.findAll();
    }

    @GetMapping
    @RequestMapping("/{id}")
    public Session get(@PathVariable Long id) {
        return repository.getById(id);
    }

    // TODO: Spring MVC 将Body中的Json数据中的attributes自动生成指定类型的对象
    //   提供的是JSON的数据格式，传入并发送请求到指定的URL
    @PostMapping
    // @ResponseStatus(HttpStatus.CREATED) // 自定义要返回的HTTP状态
    public Session create(@RequestBody final Session session) {
        return repository.saveAndFlush(session);
    }

    // 这里需要注明请求的方法RequestMethod
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Long id) {
        // 删除之前，需要考虑关联的数据表，执行Transaction事务操作
        repository.deleteById(id);
    }

    // TODO: 数据在通过body发送到EndPoint时，Jackson将json数据解析成object时
    //  必须和原始发送数据所对于的object类型字段一致
    // http://localhost:8080/api/v1/sessions/2
    // Row body, Type: JSON
    // {
    //    "session_name": "update seesion name",
    //    "session_description": "My new description",
    //    "session_length": 67
    // }
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Session update(@PathVariable Long id, @RequestBody Session session) {
        Session existingSession = repository.getById(id);
        // 忽略不需要更新的属性
        BeanUtils.copyProperties(session, existingSession, "session_id");
        return repository.saveAndFlush(existingSession);
    }
}
