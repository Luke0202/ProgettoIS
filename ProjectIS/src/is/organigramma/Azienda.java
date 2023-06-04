package is.organigramma;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Objects;

public class Azienda extends AbstractAzienda{
    private final int cod;
    private final String psw;
    private String name, headquarter, type;
    public Azienda(int cod,String name,String headquarter,String type,String psw,Organigramma organigramma){
        super(organigramma);
        this.cod = cod;
        this.name = name;
        this.type = type;
        this.headquarter=headquarter;
        this.psw=psw;
    }

    //GETTERS
    public String getHeadquarter() { return headquarter;}
    public String getType(){return type;}
    public int getCod() {return cod;}
    public String getPsw() {return psw;}
    public String getName() {
        return name;
    }


    //SETTERS
    public void setHeadquarter(String headquarter) {this.headquarter = headquarter;}
    public void setType(String type){this.type=type;}
    public void setName(String name){this.name = name;}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Azienda azienda = (Azienda) o;
        return cod == azienda.cod;
    }

    @Override
    public int hashCode() {
        return Objects.hash(cod);
    }

    @Override
    public String toString() {
        return "Azienda{" +
                "cod=" + cod +
                ", name='" + name + '\'' +
                ", headquarter='" + headquarter + '\'' +
                ", sector='" + type + '\'' +
                '}';
    }
}
