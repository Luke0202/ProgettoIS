package is.mediator;

import is.Pagination;
import is.PaginationIF;
import is.dipendenti.Administrator;
import is.organigramma.Azienda;
import is.organigramma.Organigramma;
import is.parser.AziendaParse;

import javax.swing.*;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Mediator implements MediatorIF{
    private JMenuItem createA,listA,createR,listR,createE,listE,detA;
    private JTextField idLog,pswLog, nameField, nameRoleField, nameRoleModRolePanel,nameCreateEmployee,surnameCreateEmployee,
            emailCreateEmployee,pswCreateEmployee, nameCreateAzienda, codCreateAzienda, headquarterCreateAzienda, typeCreateAzienda,
            nameListArea, idListEmployee, nameListRole, areaListRole, pswCreateAzienda;
    private JButton confLog,newAziendaLog, saveBPanel, saveVPanel, saveBRole, saveVRole, saveBModRolePanel,saveVModRolePanel,
           saveCreateEmployee, removeButtonArea, removeButtonEmployee, removeButtonRole, saveCreateAzienda,searchListArea, searchListEmployee,
    searchListRole;
    private JComboBox dadComboBox, areaComboBox,roleCreateEmployee;
    private JFrame frame;
    private PaginationIF pag;
    private Azienda azienda=null;

    public void setItem(JMenuItem createA,JMenuItem listA,JMenuItem createR,JMenuItem listR,
                        JMenuItem createE,JMenuItem listE,JMenuItem detA){
        this.createA=createA; this.listA=listA; this.createR=createR; this.listR=listR;
        this.createE=createE; this.listE=listE; this.detA=detA;
    }
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
    public void setNameField(JTextField nameField){
        this.nameField=nameField;
    }
    public void setSaveBPanel(JButton saveB){
        this.saveBPanel=saveB;
    }
    public void setSaveVPanel(JButton saveV){
        this.saveVPanel=saveV;
    }
    public void setFrame(JFrame frame){ this.frame=frame;}
    public void setDadComboBox(JComboBox<String> dadComboBox){
        this.dadComboBox=dadComboBox;
    }
    public void setNameRoleField(JTextField nameRoleField){this.nameRoleField=nameRoleField;}
    public void setAreaComboBox(JComboBox<String> areaComboBox){this.areaComboBox=areaComboBox;}
    public void setSaveBRole(JButton saveB){this.saveBRole = saveB;}
    public void setSaveVRole(JButton saveV){this.saveVRole = saveV;}
    public void setNameRoleModRole(JTextField nameRoleModRolePanel){this.nameRoleModRolePanel = nameRoleModRolePanel ;}
    public void setSaveBModRole(JButton saveBModRolePanel){this.saveBModRolePanel = saveBModRolePanel;}
    public void setSaveVModRole(JButton saveVModRolePanel){this.saveVModRolePanel = saveVModRolePanel;}
    public void setNameCreateEmployee(JTextField nameCreateEmployee){this.nameCreateEmployee=nameCreateEmployee;}
    public void setSurnameCreateEmployee(JTextField surnameCreateEmployee){this.surnameCreateEmployee=surnameCreateEmployee;}
    public void setEmailCreateEmployee(JTextField emailCreateEmployee){this.emailCreateEmployee=emailCreateEmployee;}
    public void setRoleCreateEmployee(JComboBox<String> roleCreateEmployee){this.roleCreateEmployee=roleCreateEmployee;}
    public void setSaveCreateEmployee(JButton saveCreateEmployee){this.saveCreateEmployee=saveCreateEmployee;}
    public void setRemoveButtonArea(JButton removeButtonArea){this.removeButtonArea=removeButtonArea;}
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

    @Override
    public void menuChanged(JMenuItem widget) {
        //Redirecting
        if(widget==detA) pag.goAhead(3);

        if (widget==createA)pag.goAhead(4);

        if (widget==listA) pag.goAhead(5);

        if (widget==createE) pag.goAhead(6);

        if (widget==listE) pag.goAhead(7);

        if (widget==createR) pag.goAhead(8);

        if (widget==listR) pag.goAhead(9);
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
                        , "Accesso effettuato!");
                pag.goAhead(2);
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
                    || psw.equals("Digita la password di accesso al sistema") || psw.isEmpty() || !cod.matches("\\d+"))
                JOptionPane.showMessageDialog(frame,"Digita correttamente i dati!");
            else{
                //DATI VALIDI
                //Richiesta conferma
                int i = JOptionPane.showConfirmDialog(frame, "Vuoi confermare i dati?");
                if (i != 0) return; //No oppure Cancel
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
                pag.goAhead(2);
            }
        }
        if (widget==newAziendaLog){
            pag.goAhead(1);
        }
    }

    @Override
    public void boxComboChanged(JComboBox widget) {

    }
}
