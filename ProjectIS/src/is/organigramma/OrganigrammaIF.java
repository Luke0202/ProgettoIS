package is.organigramma;

import java.util.HashSet;
import java.util.Iterator;

public interface OrganigrammaIF extends AreaOrganizationIF,Iterable<OrganigrammaIF>{
    void addChild(OrganigrammaIF area);
    void removeChild(int i);
    void removeChild(OrganigrammaIF area);
    int getNChildren();
    boolean isSubArea(OrganigrammaIF area);
    Iterator<OrganigrammaIF> iterator();
    boolean isChild(OrganigrammaIF o);
}
