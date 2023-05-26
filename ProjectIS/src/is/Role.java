package is;

import java.util.Objects;

public class Role {
    private String name;
    private AreaOrganization area;

    public Role(String name, AreaOrganization area){
        this.name = name;
        this.area = area;
    }
    public String getName() {
        return name;
    }

    public AreaOrganization getArea() {
        return area;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role role = (Role) o;
        return role.name == this.name && role.area.equals(this.area);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, area);
    }
}
