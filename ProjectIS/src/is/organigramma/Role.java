package is.organigramma;

import java.util.Objects;

public class Role {
    private String name,description,area;
    public Role(String name, String area, String description){
        this.name = name;
        this.area = area;
        this.description=description;
    }
    //GETTERS
    public String getName() {
        return name;
    }
    public String getArea() {
        return area;
    }
    public String getDescription(){ return description;}
    //SETTERS
    public void setName(String name) {
        this.name = name;
    }
    public void setArea(String area) {
        this.area = area;
    }
    public void setDescription(String description) {
        this.description = description;
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

    @Override
    public String toString() {
        return "Role{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", area='" + area + '\'' +
                '}';
    }
}
