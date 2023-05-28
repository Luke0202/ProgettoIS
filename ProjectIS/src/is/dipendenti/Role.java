package is.dipendenti;

import java.util.Objects;

public class Role {
    private String name,description,area;

    public Role(String name, String description, String area){
        this.name = name;
        this.area = area;
    }
    //GETTERS
    public String getName() {
        return name;
    }
    public String getDescription(){ return description;}
    public String getArea() {
        return area;
    }
    //SETTERS
    public void setName(String name) {
        this.name = name;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setArea(String area) {
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

    @Override
    public String toString(){
        return "Name: "+name+", Description: "+description+", Area: "+area+".";
    }
}
