package is.Funzioni;

import is.organigramma.Organigramma;

import java.util.Objects;

public class Role {
    private String name,description;
    private Organigramma area;

    public Role(String name, String description, Organigramma area){
        this.name = name;
        this.area = area;
    }
    public String getName() {
        return name;
    }
    public String getDescription(){ return description;}
    public Organigramma getArea() {
        return area;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setArea(Organigramma area) {
        this.area = area;
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
