package pl.project.domain;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Tom on 18.01.2017.
 */


@Entity
@Table(name = "system", schema = "public")
public class System {

    @Id
    @GeneratedValue
    @Column(name="system_id",nullable = false)
    private Integer id;

    @Column(name = "description")
    private String description;

    @Column(name = "name",unique = true)
    private String name;

    @Column(name = "support_group",unique = true)
    private String supportGroup;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "system")
    private List<SystemContract> systemContracts;

    public Integer getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSupportGroup() {
        return supportGroup;
    }

    public void setSupportGroup(String supportGroup) {
        this.supportGroup = supportGroup;
    }

    public List<SystemContract> getSystemContracts() {
        return systemContracts;
    }

    public void setSystemContracts(List<SystemContract> systemContracts) {
        this.systemContracts = systemContracts;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        System system = (System) o;
        if (!id.equals(system.id)) return false;
        if (!description.equals(system.description)) return false;
        if (!name.equals(system.name)) return false;
        if (!supportGroup.equals(system.supportGroup)) return false;
        return systemContracts.equals(system.systemContracts);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + description.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + supportGroup.hashCode();
        result = 31 * result + systemContracts.hashCode();
        return result;
    }
}
