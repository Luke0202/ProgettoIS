package is.mediator;

import is.state.Pagination;
import is.state.PaginationIF;
import is.organigramma.Employee;
import is.organigramma.Role;
import is.organigramma.Azienda;
import is.organigramma.Organigramma;
import is.organigramma.Area;
import is.parser.TextPlainParser;
import javax.swing.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class Mediator implements MediatorIF{
    private JMenuItem createA,listA,createR,listR,createE,listE,detA;
    private JTextField nameLog,pswLog, nameCreateArea, nameCreateRole, nameModRole,nameCreateEmployee,surnameCreateEmployee,
            emailCreateEmployee, nameCreateAzienda, codCreateAzienda, headquarterCreateAzienda, typeCreateAzienda,
            nameListArea, idListEmployee, nameListRole, areaListRole, pswCreateAzienda, nameModArea, nameArea, nameRole,areaRole, idEmployee;
    private JButton confLog,newAziendaLog, saveBCreateArea, saveVCreateArea, saveCreateRole, saveModRole,
           saveCreateEmployee,editArea, removeArea, removeEmployee, editRole, removeRole, saveCreateAzienda,searchListArea, searchListEmployee,
    searchListRole, saveVModArea,saveBModArea;
    private JComboBox<String> dadCreateArea, areaCreateRole,roleCreateEmployee;
    private JFrame frame;
    private JTextArea descrCreateArea,descrModArea, descrCreateRole, descrModRole;
    private Organigramma oldArea=null;
    private Role oldRole=null;
    private PaginationIF pag;
    private Azienda azienda=null;

    public void setItem(JMenuItem createA,JMenuItem listA,JMenuItem createR,JMenuItem listR,
                        JMenuItem createE,JMenuItem listE,JMenuItem detA){
        this.createA=createA; this.listA=listA; this.createR=createR; this.listR=listR;
        this.createE=createE; this.listE=listE; this.detA=detA;
    }
    //LogPanel
    public void setConfLog(JButton confLog){
        this.confLog = confLog;
    }
    public void setNewAziendaLog(JButton newAziendaLog){ this.newAziendaLog=newAziendaLog;}
    public void setNameLog(JTextField nameLog){
        this.nameLog = nameLog;
    }
    public void setPswLog(JTextField pswLog){
        this.pswLog = pswLog;
    }

    //CreateAreaPanel
    public void setNameCreateArea(JTextField nameCreateArea){
        this.nameCreateArea=nameCreateArea;
    }
    public void setDadCreateArea(JComboBox<String> dadCreateArea){
        this.dadCreateArea=dadCreateArea;
    }
    public void setDescrCreateArea(JTextArea descrCreateArea){
        this.descrCreateArea=descrCreateArea;
    }
    public void setSaveBCreateArea(JButton saveBCreateArea){
        this.saveBCreateArea=saveBCreateArea;
    }
    public void setSaveVCreateArea(JButton saveVCreateArea){
        this.saveVCreateArea=saveVCreateArea;
    }

    //ModAreaPanel
    public void setOldArea(Organigramma oldArea){this.oldArea=oldArea;}
    public void setNameModArea(JTextField nameModArea){this.nameModArea=nameModArea;}
    public void setDescrModArea(JTextArea descrModArea){this.descrModArea=descrModArea;}
    public void setSaveBModArea(JButton saveBModArea){this.saveBModArea=saveBModArea;}
    public void setSaveVModArea(JButton saveVModArea){this.saveVModArea=saveVModArea;}

    //CreateRolePanel
    public void setNameCreateRole(JTextField nameCreateRole){this.nameCreateRole=nameCreateRole;}
    public void setAreaCreateRole(JComboBox<String> areaCreateRole){this.areaCreateRole=areaCreateRole;}
    public void setDescrCreateRole(JTextArea descrCreateRole){this.descrCreateRole=descrCreateRole;}
    public void setSaveCreateRole(JButton saveCreateRole){this.saveCreateRole = saveCreateRole;}

    //AreaPanel
    public void setNameArea(JTextField nameArea){this.nameArea=nameArea;}
    public void setEditArea(JButton editArea){this.editArea=editArea;}
    public void setRemoveArea(JButton removeArea){this.removeArea=removeArea;}


    //ModRolePanel
    public void setOldRole(Role oldRole){this.oldRole=oldRole;}
    public void setNameModRole(JTextField nameModRole){this.nameModRole = nameModRole ;}
    public void setDescrModRole(JTextArea descrModRole){this.descrModRole=descrModRole;}
    public void setSaveModRole(JButton saveModRole){this.saveModRole = saveModRole;}


    //EmployeePanel
    public void setIdEmployee(JTextField idEmployee){this.idEmployee=idEmployee;}
    public void setRemoveEmployee(JButton removeEmployee){this.removeEmployee=removeEmployee;}

    //CreateEmployeePanel
    public void setNameCreateEmployee(JTextField nameCreateEmployee){this.nameCreateEmployee=nameCreateEmployee;}
    public void setSurnameCreateEmployee(JTextField surnameCreateEmployee){this.surnameCreateEmployee=surnameCreateEmployee;}
    public void setEmailCreateEmployee(JTextField emailCreateEmployee){this.emailCreateEmployee=emailCreateEmployee;}
    public void setRoleCreateEmployee(JComboBox<String> roleCreateEmployee){this.roleCreateEmployee=roleCreateEmployee;}
    public void setSaveCreateEmployee(JButton saveCreateEmployee){this.saveCreateEmployee=saveCreateEmployee;}

    //RolePanel
    public void setNameRole(JTextField nameRole){this.nameRole = nameRole;}
    public void setAreaRole(JTextField areaRole){this.areaRole = areaRole;}
    public void setEditRole(JButton editRole){this.editRole = editRole;}
    public void setRemoveRole(JButton removeRole){this.removeRole=removeRole;}

    //CreateAziendaPanel
    public void setNameCreateAzienda(JTextField nameCreateAzienda){this.nameCreateAzienda=nameCreateAzienda;}
    public void setCodCreateAzienda(JTextField codCreateAzienda){this.codCreateAzienda=codCreateAzienda;}
    public void setHeadquarterCreateAzienda(JTextField headquarterCreateAzienda){this.headquarterCreateAzienda=headquarterCreateAzienda;}
    public void setTypeCreateAzienda(JTextField typeCreateAzienda){this.typeCreateAzienda=typeCreateAzienda;}
    public void setPswCreateAzienda(JTextField pswCreateAzienda){this.pswCreateAzienda = pswCreateAzienda;}
    public void setSaveCreateAzienda(JButton saveCreateAzienda){this.saveCreateAzienda=saveCreateAzienda;}

    //ListAreaPanel
    public void setNameListArea(JTextField nameListArea){this.nameListArea=nameListArea;}
    public void setSearchListArea(JButton searchListArea){this.searchListArea=searchListArea;}

    //ListEmployeePanel
    public void setIdListEmployee(JTextField idListEmployee){this.idListEmployee=idListEmployee;}
    public void setSearchListEmployee(JButton searchListEmployee){this.searchListEmployee = searchListEmployee;}

    //ListRolePanel
    public void setNameListRole(JTextField nameListRole){this.nameListRole=nameListRole;}
    public void setAreaListRole(JTextField areaListRole){this.areaListRole=areaListRole;}
    public void setSearchListRole(JButton searchListRole){this.searchListRole=searchListRole;}


    public void setAzienda(Azienda azienda){this.azienda=azienda;}
    public void setPagination(PaginationIF pag){this.pag=pag;}
    public void setFrame(JFrame frame){ this.frame=frame;}
    public Azienda getAzienda(){return this.azienda;}


    private boolean checkCredential(String name, String psw){
        return azienda.getName().toLowerCase().equals(name.toLowerCase()) && azienda.getPsw().equals(psw);
    }
    @Override
    public void widgetChanged(JComponent widget) {
        //Gestione JMenuItem
        if(widget==detA) pag.avanza(3,null);

        if (widget==createA)pag.avanza(4,null);

        if (widget==listA) pag.avanza(5,null);

        if (widget==createE){
            if (azienda.getRoles().isEmpty()){
                JOptionPane.showMessageDialog(frame, "Nessun ruolo presente. Creare prima un ruolo.");
                pag.avanza(8,null);
            }else pag.avanza(6,null);
        }

        if (widget==listE) pag.avanza(7,null);

        if (widget==createR) pag.avanza(8,null);

        if (widget==listR) pag.avanza(9,null);

        //Gestione JTextField
        if (widget == nameLog || widget==pswLog){
            if (nameLog.getText().equals("") || pswLog.getText().equals("")) confLog.setEnabled(false);
            else confLog.setEnabled(true);
        }

        //Gestione JButton
        if (widget == confLog) {
            String name = nameLog.getText().trim();
            String psw = pswLog.getText();

            if (checkCredential(name, psw)) {
                JOptionPane.showMessageDialog(SwingUtilities.getAncestorOfClass(Pagination.class, widget)
                        , "Accesso effettuato !");
                pag.avanza(2, null);
            } else {
                JOptionPane.showMessageDialog(SwingUtilities.getAncestorOfClass(Pagination.class, widget)
                        , "Accesso negato.");
            }
        }
        if (widget == saveCreateAzienda) {
            //Creazione di un'azienda nel file data.txt
            String name = nameCreateAzienda.getText().trim();
            String cod = codCreateAzienda.getText().trim();
            String hq = headquarterCreateAzienda.getText().trim();
            String type = typeCreateAzienda.getText().trim();
            String psw = pswCreateAzienda.getText().trim();
            if (name.equals("Digita nome azienda") || name.isEmpty()
                    || cod.equals("Digita codice ATECO") || cod.isEmpty() || !cod.matches("[\\d\\.]+")
                    || hq.equals("Digita il luogo della sede") || hq.isEmpty()
                    || type.equals("Digita il settore") || type.isEmpty()
                    || psw.equals("Digita la password di accesso al sistema") || psw.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Digita correttamente i dati !");
                return;
            }
            //DATI VALIDI
            //Richiesta conferma
            int i = JOptionPane.showConfirmDialog(frame, "Vuoi confermare i dati ?");
            if (i != 0) return; //No oppure Cancel

            //Capitalizing first letter
            name = name.substring(0, 1).toUpperCase() + name.substring(1);
            hq = hq.substring(0, 1).toUpperCase() + hq.substring(1);
            type = type.substring(0, 1).toUpperCase() + type.substring(1);


            //Scrittura dati sul file di testo
            String description = "Codice ATECO: " + cod + ", Sede centrale: " + hq + ", Settore: " + type + ".";

            Azienda azienda = new Azienda(cod, name, hq, type, psw, new Organigramma(name, description));

            File f = new File("data.txt");
            try {
                PrintWriter pw = new PrintWriter(new FileWriter(f.getPath()));
                TextPlainParser az = new TextPlainParser(azienda, pw);
                az.doParse();
                setAzienda(azienda);
            } catch (IOException e) {
                e.printStackTrace();
            }
            pag.avanza(2, null);

        }
        if (widget == newAziendaLog) {
            pag.avanza(1, null);
        }
        if (widget == saveBCreateArea) {
            if (!nameCreateArea.getText().trim().equals("Digita nome area") && !nameCreateArea.getText().trim().isEmpty()) {
                String name = nameCreateArea.getText().trim();
                //Check existing name
                for (Area o : azienda.getOrganigramma()) {
                    if (name.toLowerCase().equals(((Organigramma) o).getName().toLowerCase())) {
                        JOptionPane.showMessageDialog(frame, "Impossibile procedere con il salvataggio: nome area già esistente.");
                        return;
                    }
                }

                int i = JOptionPane.showConfirmDialog(frame, "Vuoi confermare i dati ?");
                if (i != 0) return; //No oppure Cancel

                //Capitalizing first letter
                name = name.substring(0, 1).toUpperCase() + name.substring(1);

                String descr = descrCreateArea.getText().trim();

                if (descr.trim().equals("Digita descrizione") || descr.isEmpty()) descr = "  ";

                descr = descr.substring(0, 1).toUpperCase() + descr.substring(1);

                int j = dadCreateArea.getSelectedIndex();
                String area = dadCreateArea.getItemAt(j);


                Organigramma org = new Organigramma(name, descr);
                if (area.equals("Nessuna area di riferimento")) {
                    azienda.setOrganigramma(org);
                } else {
                    Organigramma organigramma = azienda.getOrganigramma();

                    Iterator<Area> it = organigramma.iterator();
                    while (it.hasNext()) {
                        Organigramma cur = (Organigramma) it.next();
                        if (cur.getName().equals(area)) cur.addChild(org);
                    }
                }
                JOptionPane.showMessageDialog(frame, "Caricamento avvenuto con successo.");
                pag.avanza(5, null);
            } else {
                JOptionPane.showMessageDialog(frame, "Digita correttamente i dati !");
            }
        }
        if (widget == saveVCreateArea) {
            if (!nameCreateArea.getText().trim().equals("Digita nome area") && !nameCreateArea.getText().trim().isEmpty()) {
                String name = nameCreateArea.getText().trim();
                //Check existing name
                for (Area o : azienda.getOrganigramma()) {
                    if (name.toLowerCase().equals(((Organigramma) o).getName().toLowerCase())) {
                        JOptionPane.showMessageDialog(frame, "Impossibile procedere con il salvataggio: nome area già esistente.");
                        return;
                    }
                }
                int i = JOptionPane.showConfirmDialog(frame, "Una volta validata l'area non sarà più possibile modificarla.\n" +
                        "Vuoi confermare i dati ?");
                if (i != 0) return; //No oppure Cancel

                //Capitalizing first letter
                name = name.substring(0, 1).toUpperCase() + name.substring(1);

                String descr = descrCreateArea.getText();
                if (descr.trim().equals("Digita descrizione") || descr.isEmpty()) descr = "  ";

                descr = descr.substring(0, 1).toUpperCase() + descr.substring(1);

                int j = dadCreateArea.getSelectedIndex();
                String area = dadCreateArea.getItemAt(j);

                Organigramma org = new Organigramma(name, descr);
                //Cambiamento stato
                org.setStateArea(true);
                if (area.equals("Nessuna area di riferimento")) {
                    azienda.setOrganigramma(org);
                } else {
                    Organigramma organigramma = azienda.getOrganigramma();

                    Iterator<Area> it = organigramma.iterator();
                    while (it.hasNext()) {
                        Organigramma cur = (Organigramma) it.next();
                        if (cur.getName().equals(area)) cur.addChild(org);
                    }
                }
                JOptionPane.showMessageDialog(frame, "Caricamento avvenuto con successo.");
                pag.avanza(5, null);
            } else {
                JOptionPane.showMessageDialog(frame, "Digita correttamente i dati !");
            }
        }
        if (widget == searchListArea) {
            //Check nameListArea
            String name = nameListArea.getText().trim();
            for (Area o : azienda.getOrganigramma()) {
                if (name.toLowerCase().equals(((Organigramma) o).getName().toLowerCase())) {
                    pag.avanza(10, o);
                    return;
                }
            }
            JOptionPane.showMessageDialog(frame, "Area non presente nel sistema.");
        }
        if (widget == removeArea) {
            //Deleting area
            int i = JOptionPane.showConfirmDialog(frame, "Eliminando l'area, verranno rimossi i ruoli definiti internamente e le sotto-aree associate.\n" +
                    "Procedere dunque con la rimozione ?");
            if (i == 0) {
                //Option selected: Yes
                String name = nameArea.getText();
                Organigramma org = azienda.getArea(name);
                azienda.removeArea(org);
                JOptionPane.showMessageDialog(frame, "Rimozione avvenuta con successo.");
                pag.avanza(5, null);
            }
        }
        if (widget == editArea) {
            String name = nameArea.getText();
            Organigramma org = azienda.getArea(name);
            pag.avanza(11, org);
        }
        if (widget == saveBModArea) {
            String name = nameModArea.getText().trim();
            String descr = descrModArea.getText().trim();

            //Caso area uguale alla precedente
            if (oldArea.getName().toLowerCase().equals(name.toLowerCase())){
                if (!oldArea.getDescription().toLowerCase().equals(descr.toLowerCase())){
                    if (descr.trim().equals("Digita descrizione") || descr.isEmpty()) descr = "  ";
                    descr = descr.substring(0, 1).toUpperCase() + descr.substring(1);
                    oldArea.setDescription(descr);

                    JOptionPane.showMessageDialog(frame, "Modifica effettuata.");
                }
                pag.avanza(5, null);
            }

            //Check existing name
            for (Area o : azienda.getOrganigramma()) {
                if (name.toLowerCase().equals(((Organigramma) o).getName().toLowerCase())) {
                    JOptionPane.showMessageDialog(frame, "Impossibile procedere con la modifica: nome area già esistente.");
                    return;
                }
            }

            int i = JOptionPane.showConfirmDialog(frame, "Vuoi confermare i dati ?");
            if (i != 0) return; //No oppure Cancel

            //Capitalizing first letter
            name = name.substring(0, 1).toUpperCase() + name.substring(1);

            if (descr.trim().equals("Digita descrizione") || descr.isEmpty()) descr = " ";

            descr = descr.substring(0, 1).toUpperCase() + descr.substring(1);


            oldArea.setName(name); oldArea.setDescription(descr);

            JOptionPane.showMessageDialog(frame, "Modifica effettuata.");
            pag.avanza(5, null);
        }
        if (widget == saveVModArea) {
            String name = nameModArea.getText().trim();
            String descr = descrModArea.getText().trim();

            //Caso area uguale alla precedente
            if (oldArea.getName().toLowerCase().equals(name.toLowerCase())){
                if (!oldArea.getDescription().toLowerCase().equals(descr.toLowerCase())){
                    if (descr.trim().equals("Digita descrizione") || descr.isEmpty()) descr = "  ";
                    descr = descr.substring(0, 1).toUpperCase() + descr.substring(1);
                    oldArea.setDescription(descr);
                }
                oldArea.setStateArea(true);
                JOptionPane.showMessageDialog(frame, "Modifica effettuata.");
                pag.avanza(5, null);
            }

            //Check existing name
            for (Area o : azienda.getOrganigramma()) {
                if (name.toLowerCase().equals(((Organigramma) o).getName().toLowerCase())) {
                    JOptionPane.showMessageDialog(frame, "Impossibile procedere con la modifica: nome area già esistente.");
                    return;
                }
            }

            int i = JOptionPane.showConfirmDialog(frame, "Una volta validata l'area non sarà più possibile modificarla.\n" +
                    "Vuoi confermare i dati ?");
            if (i != 0) return; //No oppure Cancel

            //Capitalizing first letter
            name = name.substring(0, 1).toUpperCase() + name.substring(1);

            if (descr.trim().equals("Digita descrizione") || descr.isEmpty()) descr = "  ";

            descr = descr.substring(0, 1).toUpperCase() + descr.substring(1);

            oldArea.setName(name); oldArea.setDescription(descr); oldArea.setStateArea(true);

            JOptionPane.showMessageDialog(frame, "Modifica effettuata.");
            pag.avanza(5, null);
        }
        if (widget == saveCreateRole) {
            if (!nameCreateRole.getText().trim().equals("Digita nome ruolo") && !nameCreateRole.getText().trim().isEmpty()) {
                String name = nameCreateRole.getText().trim();

                int j = areaCreateRole.getSelectedIndex();
                String area = areaCreateRole.getItemAt(j);


                for (Role role: azienda.getRoles()){
                    if (role.getName().toLowerCase().equals(name.toLowerCase()) && role.getArea().toLowerCase().equals(area.toLowerCase())) {
                        JOptionPane.showMessageDialog(frame, "Impossibile procedere con il salvataggio: ruolo già esistente.");
                        return;
                    }
                }

                int i = JOptionPane.showConfirmDialog(frame, "Vuoi confermare i dati ?");
                if (i != 0) return; //No oppure Cancel

                //Capitalizing first letter
                name = name.substring(0, 1).toUpperCase() + name.substring(1);

                String descr = descrCreateRole.getText().trim();

                if (descr.trim().equals("Digita descrizione") || descr.isEmpty()) descr = "  ";

                descr = descr.substring(0, 1).toUpperCase() + descr.substring(1);

                Role role = new Role(name, area, descr);

                azienda.addRole(role);

                JOptionPane.showMessageDialog(frame, "Caricamento avvenuto con successo.");
                pag.avanza(9, null);
            } else {
                JOptionPane.showMessageDialog(frame, "Digita correttamente i dati !");
            }
        }
        if (widget == searchListRole) {
            //Check nameListArea
            String name = nameListRole.getText().trim();
            String area = areaListRole.getText().trim();

            for (Role role : azienda.getRoles()) {
                if (role.getName().toLowerCase().equals(name.toLowerCase()) && role.getArea().toLowerCase().equals(area.toLowerCase())) {
                    pag.avanza(12, role);
                    return;
                }
            }
            JOptionPane.showMessageDialog(frame, "Ruolo non presente nel sistema.");
        }
        if (widget == removeRole){
            //Deleting area
            int i = JOptionPane.showConfirmDialog(frame,"Procedere con la rimozione ?");
            if (i == 0) {
                //Option selected: Yes
                String name = nameRole.getText();
                String area = areaRole.getText();

                HashSet<Role> roles=azienda.getRoles();
                Iterator<Role> it = roles.iterator();
                while(it.hasNext()){
                    Role r = it.next();
                    if (r.getName().equals(name)&&r.getArea().equals(area)){
                        it.remove();
                        break;
                    }
                }

                JOptionPane.showMessageDialog(frame, "Rimozione avvenuta con successo.");
                pag.avanza(9, null);
            }
        }
        if (widget == editRole) {
            String name = nameRole.getText();
            String area = areaRole.getText();
            HashSet<Role> roles=azienda.getRoles();
            for(Role r:roles){
                if (r.getName().equals(name) && r.getArea().equals(area)){
                    pag.avanza(13, r);
                    break;
                }
            }
        }
        if (widget == saveModRole){
            String name = nameModRole.getText().trim();
            String area = oldRole.getArea();//Area rimane invariata
            String descr = descrModRole.getText().trim();

            //Caso ruolo uguale al precedente
            if (oldRole.getName().toLowerCase().equals(name.toLowerCase())){
                if (!oldRole.getDescription().toLowerCase().equals(descr.toLowerCase())){
                    if (descr.trim().equals("Digita descrizione") || descr.isEmpty()) descr = "  ";
                    descr = descr.substring(0, 1).toUpperCase() + descr.substring(1);
                    oldRole.setDescription(descr);

                    JOptionPane.showMessageDialog(frame, "Modifica effettuata.");
                }
                pag.avanza(9, null);
            }
            //Check existing name
            for (Role role: azienda.getRoles()){
                if (role.getName().toLowerCase().equals(name.toLowerCase()) && role.getArea().equals(area)){
                    JOptionPane.showMessageDialog(frame, "Impossibile procedere con la modifica: ruolo già esistente.");
                    return;
                }
            }
            int i = JOptionPane.showConfirmDialog(frame, "Vuoi confermare i dati ?");
            if (i != 0) return; //No oppure Cancel

            //Capitalizing first letter
            name = name.substring(0, 1).toUpperCase() + name.substring(1);

            if (descr.trim().equals("Digita descrizione") || descr.isEmpty()) descr = "  ";
            descr = descr.substring(0, 1).toUpperCase() + descr.substring(1);

            oldRole.setName(name); oldRole.setDescription(descr);

            JOptionPane.showMessageDialog(frame, "Modifica effettuata.");
            pag.avanza(9, null);
        }
        if (widget==saveCreateEmployee){
            String name = nameCreateEmployee.getText().trim();
            String surname = surnameCreateEmployee.getText().trim();
            String email = emailCreateEmployee.getText().trim();

            if (name.isEmpty() || name.equals("Digita nome") || surname.isEmpty() || surname.equals("Digita cognome") ||
            email.isEmpty() || email.equals("Digita email")){
                JOptionPane.showMessageDialog(frame, "Digita correttamente i dati !");
                return;
            }

            int i = JOptionPane.showConfirmDialog(frame, "Vuoi confermare i dati ?");
            if (i != 0) return; //No oppure Cancel

            int j = roleCreateEmployee.getSelectedIndex();
            String role = roleCreateEmployee.getItemAt(j);
            StringTokenizer st = new StringTokenizer(role," - ");
            String nameRole = st.nextToken();
            String areaRole = st.nextToken();
            //Capitalizing first letter
            name = name.substring(0, 1).toUpperCase() + name.substring(1);
            surname = surname.substring(0, 1).toUpperCase() + surname.substring(1);
            email = email.substring(0, 1).toUpperCase() + email.substring(1);


            Employee emp = new Employee(name,surname,email,azienda.giveID());
            for (Role r:azienda.getRoles()){
                if (r.getName().toLowerCase().equals(nameRole.toLowerCase()) &&
                        r.getArea().toLowerCase().equals(areaRole.toLowerCase())){
                    azienda.addEmployee(r,emp);
                }
            }

            JOptionPane.showMessageDialog(frame, "Caricamento avvenuto con successo.");
            pag.avanza(7, null);
        }
        if (widget==searchListEmployee){
            //Check nameListArea
            String ID = idListEmployee.getText().trim();

            if (!ID.matches("\\d+")){
                JOptionPane.showMessageDialog(frame, "Digita correttamente i dati !");
                return;
            }
            int id = Integer.parseInt(ID);


            for(Employee emp:azienda.getEmployees()){
                if (emp.getID()==id){
                    pag.avanza(14,emp);
                    return;
                }
            }

            JOptionPane.showMessageDialog(frame, "Dipendente non presente nel sistema.");
        }
        if(widget==removeEmployee){
            //Deleting area
            int i = JOptionPane.showConfirmDialog(frame,"Procedere con la rimozione ?");
            if (i == 0) {
                //Option selected: Yes
                int id = Integer.parseInt(idEmployee.getText());

                HashSet<Employee> employees=azienda.getEmployees();

                Iterator<Employee> it = employees.iterator();
                while(it.hasNext()){
                    Employee emp = it.next();
                    if (emp.getID()==id){
                        it.remove();
                        break;
                    }
                }

                JOptionPane.showMessageDialog(frame, "Rimozione avvenuta con successo.");
                pag.avanza(7, null);
            }
        }
    }
}
