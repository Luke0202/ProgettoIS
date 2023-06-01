package is.mediator;

import is.Pagination;
import is.PaginationIF;
import is.dipendenti.Administrator;
import is.organigramma.Azienda;
import is.organigramma.Organigramma;
import is.organigramma.OrganigrammaIF;
import is.parser.AziendaParse;

import javax.swing.*;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.Locale;

public class Mediator implements MediatorIF{
    private JMenuItem createA,listA,createR,listR,createE,listE,detA;
    private JTextField idLog,pswLog, nameCreateArea, nameCreateRole, nameRoleModRolePanel,nameCreateEmployee,surnameCreateEmployee,
            emailCreateEmployee,pswCreateEmployee, nameCreateAzienda, codCreateAzienda, headquarterCreateAzienda, typeCreateAzienda,
            nameListArea, idListEmployee, nameListRole, areaListRole, pswCreateAzienda, nameModArea, nameArea;
    private JButton confLog,newAziendaLog, saveBCreateArea, saveVCreateArea, saveBCreateRole, saveVCreateRole, saveBModRolePanel,saveVModRolePanel,
           saveCreateEmployee,modArea, removeArea, removeButtonEmployee, removeButtonRole, saveCreateAzienda,searchListArea, searchListEmployee,
    searchListRole, saveVModArea,saveBModArea;
    private JComboBox<String> dadCreateArea, areaCreateRole,roleCreateEmployee, dadModArea;
    private JFrame frame;
    private JTextArea descrCreateArea,descrModArea;
    private Organigramma oldArea=null;
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
    public void setIdLog(JTextField idLog){
        this.idLog = idLog;
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
    public void setDadModArea(JComboBox<String> dadModArea){this.dadModArea=dadModArea;}
    public void setDescrModArea(JTextArea descrModArea){this.descrModArea=descrModArea;}
    public void setSaveBModArea(JButton saveBModArea){this.saveBModArea=saveBModArea;}
    public void setSaveVModArea(JButton saveVModArea){this.saveVModArea=saveVModArea;}

    //CreateRolePanel
    public void setNameCreateRole(JTextField nameCreateRole){this.nameCreateRole=nameCreateRole;}
    public void setAreaCreateRole(JComboBox<String> areaCreateRole){this.areaCreateRole=areaCreateRole;}
    public void setSaveBCreateRole(JButton saveBCreateRole){this.saveBCreateRole = saveBCreateRole;}
    public void setSaveVCreateRole(JButton saveVCreateRole){this.saveVCreateRole = saveVCreateRole;}

    //AreaPanel
    public void setNameArea(JTextField nameArea){this.nameArea=nameArea;}
    public void setModArea(JButton modArea){this.modArea=modArea;}
    public void setRemoveArea(JButton removeArea){this.removeArea=removeArea;}



    public void setNameRoleModRole(JTextField nameRoleModRolePanel){this.nameRoleModRolePanel = nameRoleModRolePanel ;}
    public void setSaveBModRole(JButton saveBModRolePanel){this.saveBModRolePanel = saveBModRolePanel;}
    public void setSaveVModRole(JButton saveVModRolePanel){this.saveVModRolePanel = saveVModRolePanel;}
    public void setNameCreateEmployee(JTextField nameCreateEmployee){this.nameCreateEmployee=nameCreateEmployee;}
    public void setSurnameCreateEmployee(JTextField surnameCreateEmployee){this.surnameCreateEmployee=surnameCreateEmployee;}
    public void setEmailCreateEmployee(JTextField emailCreateEmployee){this.emailCreateEmployee=emailCreateEmployee;}
    public void setRoleCreateEmployee(JComboBox<String> roleCreateEmployee){this.roleCreateEmployee=roleCreateEmployee;}
    public void setSaveCreateEmployee(JButton saveCreateEmployee){this.saveCreateEmployee=saveCreateEmployee;}

    public void setRemoveButtonEmployee(JButton removeButtonEmployee){this.removeButtonEmployee=removeButtonEmployee;}
    public void setRemoveButtonRole(JButton removeButtonRole){this.removeButtonRole=removeButtonRole;}
    public void setNameCreateAzienda(JTextField nameCreateAzienda){this.nameCreateAzienda=nameCreateAzienda;}
    public void setCodCreateAzienda(JTextField codCreateAzienda){this.codCreateAzienda=codCreateAzienda;}
    public void setHeadquarterCreateAzienda(JTextField headquarterCreateAzienda){this.headquarterCreateAzienda=headquarterCreateAzienda;}
    public void setTypeCreateAzienda(JTextField typeCreateAzienda){this.typeCreateAzienda=typeCreateAzienda;}
    public void setSaveCreateAzienda(JButton saveCreateAzienda){this.saveCreateAzienda=saveCreateAzienda;}
    public void setNameListArea(JTextField nameListArea){this.nameListArea=nameListArea;}
    public void setIdListEmployee(JTextField idListEmployee){this.idListEmployee=idListEmployee;}
    public void setNameListRole(JTextField nameListRole){this.nameListRole=nameListRole;}
    public void setAreaListRole(JTextField areaListRole){this.areaListRole=areaListRole;}
    public void setSearchListArea(JButton searchListArea){this.searchListArea=searchListArea;}
    public void setSearchListEmployee(JButton searchListEmployee){this.searchListEmployee = searchListEmployee;}
    public void setSearchListRole(JButton searchListRole){this.searchListRole=searchListRole;}
    public void setPswCreateAzienda(JTextField pswCreateAzienda){this.pswCreateAzienda = pswCreateAzienda;}
    public void setAzienda(Azienda azienda){this.azienda=azienda;}
    public void setPag(PaginationIF pag){this.pag=pag;}

    public Azienda getAzienda(){return this.azienda;}
    public void setFrame(JFrame frame){ this.frame=frame;}

    @Override
    public void menuChanged(JMenuItem widget) {
        //Redirecting
        if(widget==detA) pag.goAhead(3,null);

        if (widget==createA)pag.goAhead(4,null);

        if (widget==listA) pag.goAhead(5,null);

        if (widget==createE) pag.goAhead(6,null);

        if (widget==listE) pag.goAhead(7,null);

        if (widget==createR) pag.goAhead(8,null);

        if (widget==listR) pag.goAhead(9,null);
    }

    @Override
    public void textChanged(JTextField widget) {
        if (widget == idLog || widget==pswLog){
            if (!idLog.getText().isEmpty() && !pswLog.getText().isEmpty()) confLog.setEnabled(true);
        }
    }
    private boolean checkCredential(String id, String psw){
        if (!id.trim().matches("\\d+")) return false;
        return azienda.getCod()==Integer.parseInt(id.trim()) && azienda.getPsw().equals(psw.trim());
    }
    @Override
    public void buttonChanged(JButton widget) {
        if(widget==confLog){
            String id = idLog.getText().trim();
            String psw = pswLog.getText().trim();

            if (checkCredential(id,psw)){
                JOptionPane.showMessageDialog(SwingUtilities.getAncestorOfClass(Pagination.class, widget)
                        , "Accesso effettuato !");
                pag.goAhead(2,null);
            }else{
                JOptionPane.showMessageDialog(SwingUtilities.getAncestorOfClass(Pagination.class, widget)
                        , "Accesso negato.");
            }
        }
        if (widget==saveCreateAzienda){
            //Creazione di un'azienda nel file data.txt
            String name = nameCreateAzienda.getText().trim();
            String cod = codCreateAzienda.getText().trim();
            String hq = headquarterCreateAzienda.getText().trim();
            String type = typeCreateAzienda.getText().trim();
            String psw = pswCreateAzienda.getText().trim();
            if (name.equals("Digita nome azienda") || name.isEmpty()
                || cod.equals("Digita codice ATECO") || cod.isEmpty()
                || hq.equals("Digita il luogo della sede") || hq.isEmpty()
                || type.equals("Digita il settore") || type.isEmpty()
                    || psw.equals("Digita la password di accesso al sistema") || psw.isEmpty() || !cod.matches("\\d+")){
                JOptionPane.showMessageDialog(frame,"Digita correttamente i dati !");
                return;
            }
            //DATI VALIDI
            //Richiesta conferma
            int i = JOptionPane.showConfirmDialog(frame, "Vuoi confermare i dati ?");
            if (i != 0) return; //No oppure Cancel

            //Capitalizing first letter
            name = name.substring(0,1).toUpperCase() + name.substring(1);
            hq = hq.substring(0,1).toUpperCase() + hq.substring(1);
            type = type.substring(0,1).toUpperCase() + type.substring(1);


            //Scrittura dati sul file di testo
            String description = "Codice ATECO: "+cod+", Sede centrale: "+hq+", Settore: "+type+".";
            Administrator ad = new Administrator(new Organigramma(name,description));
            Azienda azienda = new Azienda(Integer.parseInt(cod),name,hq,type,psw,ad);
            String path = Mediator.class.getResource("data.txt").getPath();
            try{
                PrintWriter pw = new PrintWriter(new FileWriter(path));
                AziendaParse az = new AziendaParse(azienda,pw); az.doParse();
                setAzienda(azienda);
            }catch(IOException e){
                e.printStackTrace();
            }
            pag.goAhead(2,null);

        }
        if (widget==newAziendaLog){
            pag.goAhead(1,null);
        }
        if(widget==saveBCreateArea){
            if (!nameCreateArea.getText().trim().equals("Digita nome area") && !nameCreateArea.getText().trim().isEmpty()){
                String name = nameCreateArea.getText().trim();
                //Check existing name
                for (OrganigrammaIF o:azienda.getAdmin().getOrganigramma()){
                    if (name.toLowerCase().equals(((Organigramma)o).getName().toLowerCase())){
                        JOptionPane.showMessageDialog(frame,"Impossibile procedere con il salvataggio: nome area già esistente.");
                        return;
                    }
                }

                int i = JOptionPane.showConfirmDialog(frame, "Vuoi confermare i dati ?");
                if (i != 0) return; //No oppure Cancel

                //Capitalizing first letter
                name = name.substring(0,1).toUpperCase() + name.substring(1);

                String descr = descrCreateArea.getText().trim();

                if(descr.trim().equals("Digita descrizione")) descr=" ";

                int j = dadCreateArea.getSelectedIndex();
                String area = dadCreateArea.getItemAt(j);


                Organigramma org = new Organigramma(name,descr);
                if (area.equals("Nessuna area di riferimento")){
                    azienda.getAdmin().setOrganigramma(org);
                }else{
                    Organigramma organigramma=azienda.getAdmin().getOrganigramma();

                    Iterator<OrganigrammaIF> it = organigramma.iterator();
                    while(it.hasNext()){
                        Organigramma cur = (Organigramma) it.next();
                        if (cur.getName().equals(area)) cur.addChild(org);
                    }
                }
                JOptionPane.showMessageDialog(frame,"Caricamento avvenuto con successo.");
                pag.goAhead(2,null);
            }
            else{
                JOptionPane.showMessageDialog(frame,"Digita correttamente i dati !");
            }
        }
        if (widget==saveVCreateArea){
            if (!nameCreateArea.getText().trim().equals("Digita nome area") && !nameCreateArea.getText().trim().isEmpty()){
                String name = nameCreateArea.getText().trim();
                //Check existing name
                for (OrganigrammaIF o:azienda.getAdmin().getOrganigramma()){
                    if (name.toLowerCase().equals(((Organigramma)o).getName().toLowerCase())){
                        JOptionPane.showMessageDialog(frame,"Impossibile procedere con il salvataggio: nome area già esistente.");
                        return;
                    }
                }
                int i = JOptionPane.showConfirmDialog(frame, "Vuoi confermare i dati ?");
                if (i != 0) return; //No oppure Cancel

                //Capitalizing first letter
                name = name.substring(0,1).toUpperCase() + name.substring(1);

                String descr = descrCreateArea.getText();

                if(descr.trim().equals("Digita descrizione")) descr=" ";

                int j = dadCreateArea.getSelectedIndex();
                String area = dadCreateArea.getItemAt(j);

                Organigramma org = new Organigramma(name,descr);
                //Cambiamento stato
                org.setStateArea(true);
                if (area.equals("Nessuna area di riferimento")){
                    azienda.getAdmin().setOrganigramma(org);
                }else{
                    Organigramma organigramma=azienda.getAdmin().getOrganigramma();

                    Iterator<OrganigrammaIF> it = organigramma.iterator();
                    while(it.hasNext()){
                        Organigramma cur = (Organigramma) it.next();
                        if (cur.getName().equals(area)) cur.addChild(org);
                    }
                }
                JOptionPane.showMessageDialog(frame,"Caricamento avvenuto con successo.");
                pag.goAhead(2,null);
            }else{
                JOptionPane.showMessageDialog(frame,"Digita correttamente i dati !");
            }
        }
        if(widget==searchListArea){
            //Check nameListArea
            String name = nameListArea.getText().trim();
            for (OrganigrammaIF o:azienda.getAdmin().getOrganigramma()){
                if (name.toLowerCase().equals(((Organigramma)o).getName().toLowerCase())){
                    pag.goAhead(10,o);
                    return;
                }
            }
            JOptionPane.showMessageDialog(frame,"Area non presente nel sistema.");
        }
        if (widget==removeArea){
            //Deleting area
            int i = JOptionPane.showConfirmDialog(frame, "Eliminando l'area, verranno rimossi i ruoli definiti internamente e le sotto-aree associate.\n" +
                    "Procedere dunque con la rimozione ?");
            if (i == 0) {
                //Option selected: Yes
                String name = nameArea.getText();
                Organigramma org = azienda.getAdmin().getArea(name);
                azienda.getAdmin().removeArea(org);
                JOptionPane.showMessageDialog(frame, "Rimozione avvenuta con successo.");
                pag.goAhead(2, null);
            }
        }
        if (widget==modArea){
            String name = nameArea.getText();
            Organigramma org = azienda.getAdmin().getArea(name);
            pag.goAhead(11,org);
        }
        if (widget==saveBModArea){
            String name = nameModArea.getText().trim();
            //Check existing name
            for (OrganigrammaIF o:azienda.getAdmin().getOrganigramma()){
                if (name.toLowerCase().equals(((Organigramma)o).getName().toLowerCase())){
                    JOptionPane.showMessageDialog(frame,"Impossibile procedere con la modifica: nome area già esistente.");
                    return;
                }
            }

            String descr = descrModArea.getText();

            if(descr.trim().equals("Digita descrizione")) descr=" ";

            int j = dadModArea.getSelectedIndex();
            String area = dadModArea.getItemAt(j);

            int i = JOptionPane.showConfirmDialog(frame, "Vuoi confermare i dati ?");
            if (i != 0) return; //No oppure Cancel

            oldArea.setName(name); oldArea.setDescription(descr);

            JOptionPane.showMessageDialog(frame,"Modifica effettuata.");
            pag.goAhead(2,null);
        }
        if (widget==saveVModArea){
            String name = nameModArea.getText().trim();
            //Check existing name
            for (OrganigrammaIF o:azienda.getAdmin().getOrganigramma()){
                if (name.toLowerCase().equals(((Organigramma)o).getName().toLowerCase())){
                    JOptionPane.showMessageDialog(frame,"Impossibile procedere con la modifica: nome area già esistente.");
                    return;
                }
            }

            String descr = descrModArea.getText();

            if(descr.trim().equals("Digita descrizione")) descr=" ";

            int j = dadModArea.getSelectedIndex();
            String area = dadModArea.getItemAt(j);

            int i = JOptionPane.showConfirmDialog(frame, "Vuoi confermare i dati ?");
            if (i != 0) return; //No oppure Cancel

            oldArea.setName(name); oldArea.setDescription(descr); oldArea.setStateArea(true);

            JOptionPane.showMessageDialog(frame,"Modifica effettuata.");
            pag.goAhead(2,null);
        }
    }

    @Override
    public void boxComboChanged(JComboBox widget) {

    }
}
