package is.mediator;

import is.state.Pagination;
import is.state.PaginationIF;
import is.azienda.Employee;
import is.azienda.Role;
import is.azienda.Azienda;
import is.azienda.Organigramma;
import is.azienda.Area;
import javax.swing.*;
import java.util.*;

/**
 * La classe Mediator implementa MediatorIF.
 * Il Mediator interagisce con colleghi che estendono la classe JComponent.
 * I colleghi sono indipendenti tra di loro.
 * @author
 */
public class Mediator implements MediatorIF{
    private JMenuItem createA,listA,createR,listR,createE,listE,detA;
    private JTextField nameLog,pswLog, nameCreateArea, nameCreateRole, nameModRole,nameCreateEmployee,surnameCreateEmployee,
            emailCreateEmployee, nameCreateAzienda, codCreateAzienda, headquarterCreateAzienda, typeCreateAzienda,
            nameListArea, idListEmployee, nameListRole, areaListRole, pswCreateAzienda, nameModArea, nameArea, nameRole,areaRole, idEmployee;
    private JButton newRoleEmployee,newAziendaAccess,confLog,newAziendaLog, saveBCreateArea, saveVCreateArea, saveCreateRole, saveModRole,
           saveCreateEmployee,editArea, removeArea, removeEmployee, editRole, removeRole, saveCreateAzienda,searchListArea, searchListEmployee,
    searchListRole, saveVModArea,saveBModArea,remRoleEmployee;
    private JComboBox<String> dadComboCreateArea,dadComboModArea, areaComboCreateRole,roleComboCreateEmployee,rolesComboEmployee, rolesRemComboEmployee;

    private JTextArea descrCreateArea,descrModArea, descrCreateRole, descrModRole;

    //Frame dell'applicazione
    private JFrame frame;

    //Area da modificare
    private Organigramma oldArea=null;

    //Ruolo da modificare
    private Role oldRole=null;

    //Consente di rimuovere e aggiungere un pannello al frame
    private PaginationIF pag;

    private Azienda azienda=null;

    //GETTERS
    public Azienda getAzienda(){return this.azienda;}

    //SETTERS

    //JMenuItem
    public void setItem(JMenuItem createA,JMenuItem listA,JMenuItem createR,JMenuItem listR,
                        JMenuItem createE,JMenuItem listE,JMenuItem detA){
        this.createA=createA; this.listA=listA; this.createR=createR; this.listR=listR;
        this.createE=createE; this.listE=listE; this.detA=detA;
    }

    //About AccessPanel
    public void setNewAziendaAccess(JButton newAziendaAccess){this.newAziendaAccess = newAziendaAccess;}

    //About LogPanel
    public void setConfLog(JButton confLog){this.confLog = confLog;}
    public void setNewAziendaLog(JButton newAziendaLog){this.newAziendaLog=newAziendaLog;}
    public void setNameLog(JTextField nameLog){this.nameLog = nameLog;}
    public void setPswLog(JTextField pswLog){this.pswLog = pswLog;}

    //About CreateAreaPanel
    public void setNameCreateArea(JTextField nameCreateArea){this.nameCreateArea=nameCreateArea;}
    public void setDadComboCreateArea(JComboBox<String> dadComboCreateArea){this.dadComboCreateArea=dadComboCreateArea;}
    public void setDescrCreateArea(JTextArea descrCreateArea){this.descrCreateArea=descrCreateArea;}
    public void setSaveBCreateArea(JButton saveBCreateArea){this.saveBCreateArea=saveBCreateArea;}
    public void setSaveVCreateArea(JButton saveVCreateArea){this.saveVCreateArea=saveVCreateArea;}

    //About ModAreaPanel
    public void setOldArea(Organigramma oldArea){this.oldArea=oldArea;}
    public void setNameModArea(JTextField nameModArea){this.nameModArea=nameModArea;}
    public void setDescrModArea(JTextArea descrModArea){this.descrModArea=descrModArea;}
    public void setDadComboModArea(JComboBox<String> dadComboModArea){this.dadComboModArea=dadComboModArea;}
    public void setSaveBModArea(JButton saveBModArea){this.saveBModArea=saveBModArea;}
    public void setSaveVModArea(JButton saveVModArea){this.saveVModArea=saveVModArea;}

    //About CreateRolePanel
    public void setNameCreateRole(JTextField nameCreateRole){this.nameCreateRole=nameCreateRole;}
    public void setAreaComboCreateRole(JComboBox<String> areaComboCreateRole){this.areaComboCreateRole=areaComboCreateRole;}
    public void setDescrCreateRole(JTextArea descrCreateRole){this.descrCreateRole=descrCreateRole;}
    public void setSaveCreateRole(JButton saveCreateRole){this.saveCreateRole = saveCreateRole;}

    //About AreaPanel
    public void setNameArea(JTextField nameArea){this.nameArea=nameArea;}
    public void setEditArea(JButton editArea){this.editArea=editArea;}
    public void setRemoveArea(JButton removeArea){this.removeArea=removeArea;}


    //About ModRolePanel
    public void setOldRole(Role oldRole){this.oldRole=oldRole;}
    public void setNameModRole(JTextField nameModRole){this.nameModRole = nameModRole ;}
    public void setDescrModRole(JTextArea descrModRole){this.descrModRole=descrModRole;}
    public void setSaveModRole(JButton saveModRole){this.saveModRole = saveModRole;}

    //About EmployeePanel
    public void setIdEmployee(JTextField idEmployee){this.idEmployee=idEmployee;}
    public void setRemoveEmployee(JButton removeEmployee){this.removeEmployee=removeEmployee;}
    public void setNewRoleEmployee(JButton newRoleEmployee){this.newRoleEmployee=newRoleEmployee;}
    public void setRemRoleEmployee(JButton remRoleEmployee){this.remRoleEmployee=remRoleEmployee;}
    public void setRolesComboEmployee(JComboBox<String> rolesComboEmployee){this.rolesComboEmployee=rolesComboEmployee;}
    public void setRolesRemComboEmployee(JComboBox<String> rolesRemComboEmployee){this.rolesRemComboEmployee=rolesRemComboEmployee;}

    //About CreateEmployeePanel
    public void setNameCreateEmployee(JTextField nameCreateEmployee){this.nameCreateEmployee=nameCreateEmployee;}
    public void setSurnameCreateEmployee(JTextField surnameCreateEmployee){this.surnameCreateEmployee=surnameCreateEmployee;}
    public void setEmailCreateEmployee(JTextField emailCreateEmployee){this.emailCreateEmployee=emailCreateEmployee;}
    public void setRoleComboCreateEmployee(JComboBox<String> roleComboCreateEmployee){this.roleComboCreateEmployee=roleComboCreateEmployee;}
    public void setSaveCreateEmployee(JButton saveCreateEmployee){this.saveCreateEmployee=saveCreateEmployee;}

    //About RolePanel
    public void setNameRole(JTextField nameRole){this.nameRole = nameRole;}
    public void setAreaRole(JTextField areaRole){this.areaRole = areaRole;}
    public void setEditRole(JButton editRole){this.editRole = editRole;}
    public void setRemoveRole(JButton removeRole){this.removeRole=removeRole;}

    //About CreateAziendaPanel
    public void setNameCreateAzienda(JTextField nameCreateAzienda){this.nameCreateAzienda=nameCreateAzienda;}
    public void setCodCreateAzienda(JTextField codCreateAzienda){this.codCreateAzienda=codCreateAzienda;}
    public void setHeadquarterCreateAzienda(JTextField headquarterCreateAzienda){this.headquarterCreateAzienda=headquarterCreateAzienda;}
    public void setTypeCreateAzienda(JTextField typeCreateAzienda){this.typeCreateAzienda=typeCreateAzienda;}
    public void setPswCreateAzienda(JTextField pswCreateAzienda){this.pswCreateAzienda = pswCreateAzienda;}
    public void setSaveCreateAzienda(JButton saveCreateAzienda){this.saveCreateAzienda=saveCreateAzienda;}

    //About ListAreaPanel
    public void setNameListArea(JTextField nameListArea){this.nameListArea=nameListArea;}
    public void setSearchListArea(JButton searchListArea){this.searchListArea=searchListArea;}

    //About ListEmployeePanel
    public void setIdListEmployee(JTextField idListEmployee){this.idListEmployee=idListEmployee;}
    public void setSearchListEmployee(JButton searchListEmployee){this.searchListEmployee = searchListEmployee;}

    //About ListRolePanel
    public void setNameListRole(JTextField nameListRole){this.nameListRole=nameListRole;}
    public void setAreaListRole(JTextField areaListRole){this.areaListRole=areaListRole;}
    public void setSearchListRole(JButton searchListRole){this.searchListRole=searchListRole;}

    //Other setters
    public void setAzienda(Azienda azienda){this.azienda=azienda;}
    public void setPagination(PaginationIF pag){this.pag=pag;}
    public void setFrame(JFrame frame){ this.frame=frame;}


    /**
     * Confronta le credenziali aziendali con quelle fornite
     * in ingresso.
     * Restituisce true se esse coincidono, altrimenti false.
     * @param name nome da verificare
     * @param psw password da verificare
     * @return boolean
     */
    private boolean checkCredential(String name, String psw){
        return azienda.getName().toLowerCase().equals(name.toLowerCase()) && azienda.getPsw().equals(psw);
    }

    /**
     * Riceve la richiesta da parte di un collega di svolgere determinate operazioni.
     * @param widget collega
     */
    @Override
    public void widgetChanged(JComponent widget) {
        //About JMenuItem
        if(widget==detA)
            //Richiesta dettaglio area
            //L'utente viene rimandato al pannello contenente i dettagli aziendali
            pag.avanza(Pagination.DETT_AZIENDA,null);

        if (widget==createA){
            //Richiesta creazione area

            //L'utente viene rimandato al pannello di creazione area
            pag.avanza(Pagination.CREATE_AREA,null);
        }

        if (widget==listA)
            //Richiesta di accesso alla lista delle aree
            //L'utente viene rimandato al pannello contenente la lista delle aree
            pag.avanza(Pagination.LIST_AREA,null);

        if (widget==createE){
            //Richiesta di creazione di un dipendente

            //Verifica presenza di almeno un ruolo
            if (azienda.getRoles().isEmpty()){
                JOptionPane.showMessageDialog(frame, "Nessun ruolo da assegnare al dipendente.\nCreare prima un ruolo.");
                //L'utente viene rimandato al pannello di creazione ruolo
                pag.avanza(Pagination.CREATE_ROLE,null);
            }else
                //L'utente viene rimandato al pannello di creazione di un dipendente
                pag.avanza(Pagination.CREATE_EMPLOYEE,null);
        }

        if (widget==listE)
            //Richiesta di accesso alla lista dei dipendenti
            //L'utente viene rimandato al pannello contenente la lista dei dipendenti
            pag.avanza(Pagination.LIST_EMPLOYEE,null);

        if (widget==createR)
            //Richiesta di creazione ruolo
            //L'utente viene rimandato al pannello di creazione ruolo
            pag.avanza(Pagination.CREATE_ROLE,null);

        if (widget==listR)
            //Richiesta di accesso alla lista dei ruoli
            //L'utente viene rimandato al pannello contenente la lista dei ruoli
            pag.avanza(Pagination.LIST_ROLE,null);

        //About JTextField in LogPanel
        if (widget == nameLog || widget==pswLog){
            //Button di accesso disabilitato in caso di presenza di campi vuoti
            if (nameLog.getText().equals("") || pswLog.getText().equals(""))
                confLog.setEnabled(false);
            else
                confLog.setEnabled(true);
        }

        //About JButton

        if (widget == confLog) {
            //Button di conferma credenziali, usato per accedere ai dati dell'azienda

            //Verifica credenziali
            String name = nameLog.getText().trim();
            String psw = pswLog.getText();

            if (checkCredential(name, psw)) {
                JOptionPane.showMessageDialog(SwingUtilities.getAncestorOfClass(Pagination.class, widget)
                        , "Accesso effettuato!");
                //L'utente viene rimandato alla homepage
                pag.avanza(Pagination.HOME, null);
            } else {
                JOptionPane.showMessageDialog(SwingUtilities.getAncestorOfClass(Pagination.class, widget)
                        , "Accesso negato.");
            }
        }
        if (widget == newAziendaAccess){
            //Richiesta creazione azienda

            //L'utente viene rimandato al pannello di creazione azienda
            pag.avanza(Pagination.CREATE_AZIENDA,null);
        }
        if (widget == newAziendaLog){
            //Richiesta creazione azienda

            //Richiesta conferma
            int i = JOptionPane.showConfirmDialog(frame, "Vuoi cancellare l'azienda presente nel sistema per memorizzarne una nuova?");
            if (i != 0) return; //L'utente non ha confermato

            //L'utente viene rimandato al pannello di creazione azienda
            pag.avanza(Pagination.CREATE_AZIENDA,null);
        }
        if (widget == saveCreateAzienda) {
            //Button di creazione di un azienda

            //Dati azienda
            String name = nameCreateAzienda.getText().trim();
            String cod = codCreateAzienda.getText().trim();
            String hq = headquarterCreateAzienda.getText().trim();
            String type = typeCreateAzienda.getText().trim();
            String psw = pswCreateAzienda.getText().trim();

            //Controllo consistenza dati
            if (name.isEmpty() || cod.isEmpty() || hq.isEmpty() || type.isEmpty() || psw.isEmpty()){
                JOptionPane.showMessageDialog(frame, "È presente almeno un campo vuoto. Digita correttamente i dati!");
                return;
            }

            if (!cod.matches("[\\d\\.]+")){
                JOptionPane.showMessageDialog(frame, "Codice ateco non valido. Digita correttamente i dati!");
                return;
            }

            //Richiesta conferma
            int i = JOptionPane.showConfirmDialog(frame, "Vuoi confermare i dati ?");
            if (i != 0) return; //L'utente non ha confermato i dati

            //Capitalizing first letter
            name = name.substring(0, 1).toUpperCase() + name.substring(1);
            hq = hq.substring(0, 1).toUpperCase() + hq.substring(1);
            type = type.substring(0, 1).toUpperCase() + type.substring(1);

            //Creazione azienda
            String description = "Azienda: "+name+", Codice ATECO: " + cod + ", Sede centrale: " + hq + ", Settore: " + type + ".";
            Azienda azienda = new Azienda(cod, name, hq, type, psw, new Organigramma("Area dirigenziale", description));
            setAzienda(azienda);

            JOptionPane.showMessageDialog(frame, "Caricamento avvenuto con successo.");
            //L'utente viene rimandato alla homepage
            pag.avanza(Pagination.HOME, null);
        }
        if (widget == saveBCreateArea) {
            //Richiesta salvataggio in BOZZA di un'area

            //Verifica consistenza dati
            if (nameCreateArea.getText().trim().isEmpty()){
                JOptionPane.showMessageDialog(frame, "Nome inserito non valido. Digita correttamente i dati!");
                return;
            }

            String name = nameCreateArea.getText().trim();

            //Check existing area
            for (Area o : azienda.getOrganigramma()) {
                if (name.toLowerCase().equals(((Organigramma) o).getName().toLowerCase())) {
                    JOptionPane.showMessageDialog(frame, "Impossibile procedere con il salvataggio: area già esistente.");
                    return;
                }
            }

            //Richiesta conferma
            int i = JOptionPane.showConfirmDialog(frame, "Vuoi confermare i dati ?");
            if (i != 0) return; //L'utente non ha confermato i dati

            String descr = descrCreateArea.getText().trim();
            if (descr.isEmpty()) descr = "  ";

            //Capitalizing first letter
            name = name.substring(0, 1).toUpperCase() + name.substring(1);
            descr = descr.substring(0, 1).toUpperCase() + descr.substring(1);

            int j = dadComboCreateArea.getSelectedIndex();
            String nameArea = dadComboCreateArea.getItemAt(j);

            //Area da aggiungere
            Organigramma org = new Organigramma(name, descr);
            if (nameArea.equals("Nessuna area di riferimento")) {
                //Non è presente alcuna area di riferimento
                //Tale area rappresenta l'area radice
                azienda.setOrganigramma(org);
            } else {
                //Ricerca area padre
                Organigramma areaPadre = azienda.getArea(nameArea);
                //Aggiunta area
                areaPadre.addChild(org);
            }

            //Area caricata
            JOptionPane.showMessageDialog(frame, "Caricamento avvenuto con successo.");
            //L'utente viene rimandato all'elenco delle aree
            pag.avanza(Pagination.LIST_AREA, null);
        }
        if (widget == saveVCreateArea) {
            //Richiesta salvataggio e validazione di un'area

            //Verifica consistenza dati
            if (nameCreateArea.getText().trim().isEmpty()){
                JOptionPane.showMessageDialog(frame, "Nome inserito non valido. Digita correttamente i dati!");
                return;
            }

            String name = nameCreateArea.getText().trim();

            //Check existing name
            for (Area o : azienda.getOrganigramma()) {
                if (name.toLowerCase().equals(((Organigramma) o).getName().toLowerCase())) {
                    JOptionPane.showMessageDialog(frame, "Impossibile procedere con il salvataggio: nome area già esistente.");
                    return;
                }
            }

            //Richiesta conferma
            int i = JOptionPane.showConfirmDialog(frame, "Una volta validata l'area non sarà più possibile modificarla.\n" +
                    "Vuoi confermare i dati ?");
            if (i != 0) return; //L'utente non ha confermato i dati

            String descr = descrCreateArea.getText();
            if (descr.isEmpty()) descr = "  ";

            //Capitalizing first letter
            name = name.substring(0, 1).toUpperCase() + name.substring(1);
            descr = descr.substring(0, 1).toUpperCase() + descr.substring(1);

            int j = dadComboCreateArea.getSelectedIndex();
            String nameArea = dadComboCreateArea.getItemAt(j);

            //Area da aggiungere
            Organigramma org = new Organigramma(name, descr);
            //Cambiamento stato
            org.setStateArea(true);

            if (nameArea.equals("Nessuna area di riferimento")) {
                //Non è presente alcuna area di riferimento
                //Tale area rappresenta l'area radice
                azienda.setOrganigramma(org);
            } else {
                //Ricerca area padre
                Organigramma areaPadre = azienda.getArea(nameArea);
                //Aggiunta area
                areaPadre.addChild(org);
            }

            //Area caricata
            JOptionPane.showMessageDialog(frame, "Caricamento avvenuto con successo.");
            //L'utente viene rimandato all'elenco delle aree
            pag.avanza(Pagination.LIST_AREA, null);
        }
        if (widget == searchListArea) {
            //Ricerca di un'area

            //Verifica presenza area
            String nameArea = nameListArea.getText().trim();
            for (Area a : azienda.getOrganigramma()) {
                if (nameArea.toLowerCase().equals(((Organigramma) a).getName().toLowerCase())) {
                    //L'utente viene rimandato al pannello di gestione di un'area
                    pag.avanza(Pagination.AREA, a);
                    return;
                }
            }

            //Area non presente
            JOptionPane.showMessageDialog(frame, "Area non presente nel sistema.");
        }
        if (widget == removeArea) {
            //Richiesta cancellazione area

            //Richiesta conferma
            int i = JOptionPane.showConfirmDialog(frame, "Eliminando l'area, verranno rimossi i ruoli definiti internamente e le sotto-aree associate.\n" +
                    "Procedere dunque con la rimozione ?");
            if (i != 0) return; //L'utente non ha confermato i dati

            //Option selected: Yes
            String name = nameArea.getText();
            Organigramma areaToRem = azienda.getArea(name);
            //Rimozione area
            azienda.removeArea(areaToRem);

            JOptionPane.showMessageDialog(frame, "Rimozione avvenuta con successo.");
            pag.avanza(Pagination.LIST_AREA, null);
        }
        if (widget == editArea) {
            //Richiesta modifica area
            String name = nameArea.getText();
            Organigramma area = azienda.getArea(name);

            //L'utente viene rimandato al pannello di modifica dell'area
            pag.avanza(Pagination.EDIT_AREA,area);
        }
        if (widget == saveBModArea) {
            //Richiesta salvataggio in BOZZA di un'area

            String name = nameModArea.getText().trim();
            String descr = descrModArea.getText().trim();

            if (descr.isEmpty()) descr = " ";

            //Getting nome area di riferimento
            int j = dadComboModArea.getSelectedIndex();
            String area = dadComboModArea.getItemAt(j);

            //Caso nuova area identica alla precedente
            if (name.toLowerCase().equals(oldArea.getName().toLowerCase()) && descr.toLowerCase().equals(oldArea.getDescription().toLowerCase())){

                //Verifica coincidenza area padre
                Organigramma org = azienda.getParent(oldArea);
                if (org == null && area.equals("Nessuna area di riferimento") || org.getName().equals(area)){
                    //L'utente viene rimandato alla lista delle aree
                    pag.avanza(Pagination.LIST_AREA, null);
                    return;
                }
            }

            //Caso nome area scelto già presente nel sistema
            for (Area a : azienda.getOrganigramma()) {
                if (name.toLowerCase().equals(((Organigramma) a).getName().toLowerCase())) {
                    //Area già esistente
                    JOptionPane.showMessageDialog(frame, "Impossibile procedere con la modifica: nome area già esistente.");
                    return;
                }
            }

            //Richiedi conferma
            int i = JOptionPane.showConfirmDialog(frame, "Vuoi confermare i dati ?");
            if (i != 0) return; //L'utente non ha confermato i dati

            //Capitalizing first letter
            name = name.substring(0, 1).toUpperCase() + name.substring(1);
            descr = descr.substring(0, 1).toUpperCase() + descr.substring(1);

            //Modifica area
            oldArea.setName(name); oldArea.setDescription(descr);

            //Modifica area di riferimento
            if (!area.equals("Nessuna area di riferimento")){
                //Area di riferimento modificata

                //Getting area di riferimento
                Organigramma dad = azienda.getParent(oldArea);

                //Rimozione figlio
                dad.removeChild(oldArea);

                //Nuova area di riferimento
                Organigramma newDad = azienda.getArea(area);

                newDad.addChild(oldArea);
            }

            JOptionPane.showMessageDialog(frame, "Modifica effettuata.");
            //L'utente viene rimandato alla lista delle aree
            pag.avanza(Pagination.LIST_AREA, null);
        }
        if (widget == saveVModArea) {
            //Richiesta salvataggio e validazione di un'area

            String name = nameModArea.getText().trim();
            String descr = descrModArea.getText().trim();
            if (descr.isEmpty()) descr = " ";

            //Getting nome area di riferimento
            int j = dadComboModArea.getSelectedIndex();
            String area = dadComboModArea.getItemAt(j);

            //Caso nome area scelto già presente nel sistema
            for (Area a : azienda.getOrganigramma()) {
                if (name.toLowerCase().equals(((Organigramma) a).getName().toLowerCase())) {
                    JOptionPane.showMessageDialog(frame, "Impossibile procedere con la modifica: nome area già esistente.");
                    return;
                }
            }

            //Richiedi conferma
            int i = JOptionPane.showConfirmDialog(frame, "Una volta validata l'area non sarà più possibile modificarla.\n" +
                    "Vuoi confermare i dati ?");
            if (i != 0) return; //L'utente non ha confermato

            //Capitalizing first letter
            name = name.substring(0, 1).toUpperCase() + name.substring(1);
            descr = descr.substring(0, 1).toUpperCase() + descr.substring(1);

            //Modifica area
            oldArea.setName(name); oldArea.setDescription(descr); oldArea.setStateArea(true);

            //Modifica area di riferimento

            if (!area.equals("Nessuna area di riferimento")){
                //Area di riferimento modificata

                //Getting area di riferimento
                Organigramma dad = azienda.getParent(oldArea);

                //Rimozione figlio
                dad.removeChild(oldArea);

                //Nuova area di riferimento
                Organigramma newDad = azienda.getArea(area);

                newDad.addChild(oldArea);
            }

            JOptionPane.showMessageDialog(frame, "Modifica effettuata.");
            //L'utente viene rimandato alla lista delle aree
            pag.avanza(Pagination.LIST_AREA, null);
        }
        if (widget == saveCreateRole) {
            //Richiesta creazione ruolo

            //Verifica validità ruolo
            if (nameCreateRole.getText().trim().isEmpty()){
                JOptionPane.showMessageDialog(frame, "Ruolo inserito non valido. Digita correttamente i dati!");
                return;
            }

            String name = nameCreateRole.getText().trim();

            //Getting name area
            int j = areaComboCreateRole.getSelectedIndex();
            String area = areaComboCreateRole.getItemAt(j);

            //Verifica ruolo già esistente
            for (Role role: azienda.getRoles()){
                if (role.getName().toLowerCase().equals(name.toLowerCase()) && role.getArea().toLowerCase().equals(area.toLowerCase())) {
                    JOptionPane.showMessageDialog(frame, "Impossibile procedere con il salvataggio: ruolo già esistente.");
                    return;
                }
            }

            //Richiesta conferma
            int i = JOptionPane.showConfirmDialog(frame, "Vuoi confermare i dati ?");
            if (i != 0) return; //L'utente non ha confermato i dati

            String descr = descrCreateRole.getText().trim();
            if (descr.isEmpty()) descr = "  ";

            //Capitalizing first letter
            name = name.substring(0, 1).toUpperCase() + name.substring(1);
            descr = descr.substring(0, 1).toUpperCase() + descr.substring(1);

            //Creazione ruolo
            Role role = new Role(name, area, descr);
            //Aggiunta ruolo
            azienda.addRole(role);

            JOptionPane.showMessageDialog(frame, "Caricamento avvenuto con successo.");
            //L'utente viene rimandato alla lista dei ruoli
            pag.avanza(Pagination.LIST_ROLE, null);
        }
        if (widget == searchListRole) {
            //Ricerca di un ruolo

            String name = nameListRole.getText().trim();
            String area = areaListRole.getText().trim();

            //Verifica presenza ruolo
            for (Role role : azienda.getRoles()) {
                if (role.getName().toLowerCase().equals(name.toLowerCase()) && role.getArea().toLowerCase().equals(area.toLowerCase())) {
                    //L'utente viene rimandato al pannello di visione di un ruolo
                    pag.avanza(Pagination.ROLE, role);
                    return;
                }
            }
            //Ruolo non presente nell'azienda
            JOptionPane.showMessageDialog(frame, "Ruolo non presente nel sistema.");
        }
        if (widget == removeRole){
            //Richiesta di cancellazione ruolo

            //Richiesta conferma
            int i = JOptionPane.showConfirmDialog(frame,"Procedere con la rimozione ?");
            if (i != 0) return; //L'utente non ha confermato i dati

            //Option selected: Yes
            String name = nameRole.getText();
            String area = areaRole.getText();

            //Rimozione ruolo
            azienda.removeRole(new Role(name,area,""));

            JOptionPane.showMessageDialog(frame, "Rimozione avvenuta con successo.");
            //L'utente viene rimandato alla lista dei ruoli
            pag.avanza(Pagination.LIST_ROLE, null);
        }
        if (widget == editRole) {
            //Richiesta modifica ruolo

            String name = nameRole.getText();
            String area = areaRole.getText();

            //Ricerca ruolo
            HashSet<Role> roles=azienda.getRoles();
            for(Role r:roles){
                if (r.getName().equals(name) && r.getArea().equals(area)){
                    //L'utente viene rimandato al pannello di modifica del ruolo
                    pag.avanza(Pagination.EDIT_ROLE, r);
                    break;
                }
            }
        }
        if (widget == saveModRole){
            //Richiesta salvataggio del ruolo

            String name = nameModRole.getText().trim();
            String area = oldRole.getArea();//Nome area uguale al precedente, in quanto l'area non è modificabile
            String descr = descrModRole.getText().trim();
            if (descr.isEmpty()) descr = "  ";

            //Caso ruolo uguale al precedente
            if (name.toLowerCase().equals(oldRole.getName().toLowerCase()) && descr.toLowerCase().equals(oldRole.getDescription().toLowerCase())){

                //L'utente viene rimandato alla lista dei ruoli
                pag.avanza(Pagination.LIST_ROLE, null);
                return;
            }

            //Verifica ruolo già esistente
            for (Role role: azienda.getRoles()){
                if (role.getName().toLowerCase().equals(name.toLowerCase()) && role.getArea().equals(area)){
                    JOptionPane.showMessageDialog(frame, "Impossibile procedere con la modifica: ruolo già esistente.");
                    return;
                }
            }

            //Richiedi conferma
            int i = JOptionPane.showConfirmDialog(frame, "Vuoi confermare i dati ?");
            if (i != 0) return; //L'utente non ha confermato i dati



            //Capitalizing first letter
            name = name.substring(0, 1).toUpperCase() + name.substring(1);
            descr = descr.substring(0, 1).toUpperCase() + descr.substring(1);

            //Modifica ruolo
            oldRole.setName(name); oldRole.setDescription(descr);

            JOptionPane.showMessageDialog(frame, "Modifica effettuata.");
            //L'utente viene rimandato alla lista dei ruoli
            pag.avanza(Pagination.LIST_ROLE, null);
        }
        if (widget==saveCreateEmployee){
            //Richiesta creazione dipendente

            String name = nameCreateEmployee.getText().trim();
            String surname = surnameCreateEmployee.getText().trim();
            String email = emailCreateEmployee.getText().trim();

            //Verifica validità dipendente
            if (name.isEmpty() || surname.isEmpty() || email.isEmpty()){
                JOptionPane.showMessageDialog(frame, "È presente almeno un campo vuoto. Digita correttamente i dati !");
                return;
            }

            //Richiesta conferma
            int i = JOptionPane.showConfirmDialog(frame, "Vuoi confermare i dati ?");
            if (i != 0) return; //L'utente non ha confermato i dati

            //Getting role
            int j = roleComboCreateEmployee.getSelectedIndex();
            String role = roleComboCreateEmployee.getItemAt(j);
            String[] parts = role.split(" - ");
            String nameRole = parts[0];
            String nameArea = parts[1];

            //Capitalizing first letter
            name = name.substring(0, 1).toUpperCase() + name.substring(1);
            surname = surname.substring(0, 1).toUpperCase() + surname.substring(1);
            email = email.substring(0, 1).toUpperCase() + email.substring(1);

            //Creazione dipendente
            Employee emp = new Employee(name,surname,email,azienda.giveID());
            //Aggiunta dipendente
            for (Role r:azienda.getRoles()){
                if (r.getName().equals(nameRole) && r.getArea().equals(nameArea)){
                    azienda.addEmployee(r,emp);
                    break;
                }
            }

            JOptionPane.showMessageDialog(frame, "Caricamento avvenuto con successo.");
            //L'utente viene rimandato alla lista dei dipendenti
            pag.avanza(Pagination.LIST_EMPLOYEE, null);
        }
        if (widget==searchListEmployee){
            //Ricerca di un dipendente

            String ID = idListEmployee.getText().trim();

            //Verifica validità id
            if (!ID.matches("\\d+")){
                JOptionPane.showMessageDialog(frame, "Id non valido!");
                return;
            }
            int id = Integer.parseInt(ID);

            //Ricerca dipendente
            for(Employee emp:azienda.getEmployees()){
                if (emp.getID()==id){
                    //L'utente viene rimandato al pannello di gestione del dipendente
                    pag.avanza(Pagination.EMPLOYEE,emp);
                    return;
                }
            }
            //Dipendente non trovato
            JOptionPane.showMessageDialog(frame, "Dipendente non presente nel sistema.");
        }
        if (widget==newRoleEmployee){
            //Richiesta di assegnazione di un ruolo ad un dipendente

            //Richiesta conferma
            int i = JOptionPane.showConfirmDialog(frame,"Assegnare il nuovo ruolo ?");
            if (i != 0) return; //L'utente non ha confermato

            //Getting role
            int j = rolesComboEmployee.getSelectedIndex();
            String role = rolesComboEmployee.getItemAt(j);
            String[] parts = role.split(" - ");
            String nameRole = parts[0];
            String nameArea = parts[1];

            //Id dipendente
            int id = Integer.parseInt(idEmployee.getText());

            //Assegnazione ruolo al dipendente
            for (Role r:azienda.getRoles()){
                if (r.getName().equals(nameRole) && r.getArea().equals(nameArea)){
                    azienda.addRoleToEmployee(r,id);
                    break;
                }
            }

            //Ricavo il dipendente
            Employee emp=null;
            for (Employee e:azienda.getEmployees()){
                if (e.getID()==id){
                    emp = e; break;
                }
            }
            JOptionPane.showMessageDialog(frame, "Ruolo assegnato con successo.");
            //L'utente viene rimandato al pannello di gestione del dipendente
            pag.avanza(Pagination.EMPLOYEE,emp);
        }
        if (widget == remRoleEmployee){
            //Richiesta di rimozione ruolo

            //Richiesta conferma
            int i = JOptionPane.showConfirmDialog(frame,"Rimuovere il ruolo ?");
            if (i != 0) return; //L'utente non ha confermato

            //Getting role
            int j = rolesRemComboEmployee.getSelectedIndex();
            String role = rolesRemComboEmployee.getItemAt(j);
            String[] parts = role.split(" - ");
            String nameRole = parts[0];
            String nameArea = parts[1];

            //Id dipendente
            int id = Integer.parseInt(idEmployee.getText());

            //Ricavo il dipendente
            Employee emp=null;
            for (Employee e:azienda.getEmployees()){
                if (e.getID()==id){
                    emp = e; break;
                }
            }

            //Rimozione ruolo
            for (Role r:azienda.getRoles()){
                if (r.getName().equals(nameRole) && r.getArea().equals(nameArea)){
                    azienda.removeRoleFromEmployee(r,emp);
                    break;
                }
            }

            JOptionPane.showMessageDialog(frame, "Ruolo rimosso con successo.");
            //L'utente viene rimandato al pannello di gestione del dipendente
            pag.avanza(Pagination.EMPLOYEE,emp);
        }
        if(widget==removeEmployee){
            //Richiesta di rimozione di un dipendente

            //Richiesta conferma
            int i = JOptionPane.showConfirmDialog(frame,"Procedere con la rimozione ?");
            if (i != 0) return; //L'utente non ha confermato

            //Option selected: Yes
            int id = Integer.parseInt(idEmployee.getText());
            //Rimozione dipendente
            azienda.removeEmployee(id);
            JOptionPane.showMessageDialog(frame, "Rimozione avvenuta con successo.");
            //L'utente viene rimandato alla lista dei dipendenti
            pag.avanza(Pagination.LIST_EMPLOYEE, null);
        }
    }
}//Mediator
