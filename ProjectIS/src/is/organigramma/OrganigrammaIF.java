package is.organigramma;

import java.util.Iterator;

public interface OrganigrammaIF extends AreaOrganizationIF,Iterable<OrganigrammaIF>{
    void addChild(OrganigrammaIF area);
    void removeChild(int i);
    void removeChild(OrganigrammaIF area);
    int getNChildren();
    boolean containsArea(OrganigrammaIF area);
    Iterator<OrganigrammaIF> iterator();
}
