package is.organigramma;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Objects;

public class Azienda extends AbstractAzienda{
    private final String psw, cod;
    private String name, headquarter, type;
    public Azienda(String cod,String name,String headquarter,String type,String psw,Organigramma organigramma){
        super(organigramma);
        if (!cod.matches("[\\d\\.]+")) throw new IllegalArgumentException("Codice non valido");
        this.cod = cod;
        this.name = name;
        this.type = type;
        this.headquarter=headquarter;
        this.psw=psw;
    }

    //GETTERS
    public String getHeadquarter() { return headquarter;}
    public String getType(){return type;}
    public String getCod() {return cod;}
    public String getPsw() {return psw;}
    public String getName() {
        return name;
    }


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
