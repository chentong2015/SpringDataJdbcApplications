package demo.repository.cycle;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity(name = "demo.repository_cycle.CycleClass")
public class CycleClass {

    @Id
    private Long id;

    @Column(name = "name")
    private String name;

    public CycleClass() {
    }

    public CycleClass(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
