package is.organigramma;

import java.util.Iterator;

public interface OrganigrammaIF extends AreaOrganizationIF,Iterable<AreaOrganizationIF>{
    void addChild(AreaOrganizationIF area);
    void removeChild(int i);
    int getNChildren();
    boolean containsArea(AreaOrganizationIF area);
    Iterator<AreaOrganizationIF> iterator();
}
