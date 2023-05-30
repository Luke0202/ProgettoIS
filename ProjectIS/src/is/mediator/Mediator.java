package is.mediator;

import is.organigramma.Azienda;

import javax.swing.*;

public class Mediator implements MediatorIF{
    private JMenuItem searchA,createA,editA,remA,searchR,createR,editR,remR,searchU,createU,editU,remU,openA,showA;
    private JTextField idField,pswField, nameField, nameRoleField, nameRoleModRolePanel,nameCreateEmployee,surnameCreateEmployee,
            emailCreateEmployee,pswCreateEmployee;
    private JButton confButton,newAziendaButton, saveBPanel, saveVPanel, saveBRole, saveVRole, saveBModRolePanel,saveVModRolePanel,
           saveCreateEmployee, removeButtonArea, removeButtonEmployee, removeButtonRole;
    private JComboBox dadComboBox, areaComboBox,roleCreateEmployee;
    private Azienda azienda;
    private JFrame frame;
    public Mediator(Azienda azienda){
        this.azienda = azienda;
    }

    public void setItem(JMenuItem searchA,JMenuItem searchR, JMenuItem searchU, JMenuItem createA, JMenuItem createR,JMenuItem createU, JMenuItem editA, JMenuItem editR,
                        JMenuItem editU, JMenuItem remA,JMenuItem remR, JMenuItem remU, JMenuItem openA,JMenuItem showA){
        this.searchA = searchA; this.searchR=searchR; this.searchU=searchU;
        this.createA = createA; this.createR=createR; this.createU=createU;
        this.editA = editA; this.editR=editR; this.editU=editU;
        this.remA=remA; this.remR=remR; this.remU=remU;
        this.openA = openA; this.showA = showA;
    }
    public void setConfButton(JButton button){
        confButton = button;
    }
    public void setNewAziendaButton(JButton button){ newAziendaButton = button;}
    public void setIdField(JTextField idField){
        this.idField = idField;
    }
    public void setPswField(JTextField pswField){
        this.pswField = pswField;
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
    public void setPswCreateEmployee(JTextField pswCreateEmployee){this.pswCreateEmployee=pswCreateEmployee;}
    public void setRoleCreateEmployee(JComboBox<String> roleCreateEmployee){this.roleCreateEmployee=roleCreateEmployee;}
    public void setSaveCreateEmployee(JButton saveCreateEmployee){this.saveCreateEmployee=saveCreateEmployee;}
    public void setRemoveButtonArea(JButton removeButtonArea){this.removeButtonArea=removeButtonArea;}
    public void setRemoveButtonEmployee(JButton removeButtonEmployee){this.removeButtonEmployee=removeButtonEmployee;}
    public void setRemoveButtonRole(JButton removeButtonRole){this.removeButtonRole=removeButtonRole;}

    @Override
    public void menuChanged(JMenuItem widget) {

    }


    @Override
    public void textChanged(JTextField widget) {
        if (widget == idField || widget == pswField) {
            confButton.setEnabled(isValid());
        }

    }

    @Override
    public void buttonChanged(JButton widget) {
        if(widget==confButton){
            String id = idField.getText().trim();
            String psw = pswField.getText().trim();


            if (id.equals("1") && psw.equals("ciao")){
                JOptionPane.showMessageDialog(SwingUtilities.getAncestorOfClass(JFrame.class, widget)
                        , "Accesso effettuato da " /*admin.getName() + " " + admin.getSurname()*/);
            }else{
                JOptionPane.showMessageDialog(SwingUtilities.getAncestorOfClass(JFrame.class, widget)
                        , "Accesso negato !");
            }
        }

    }

    @Override
    public void boxComboChanged(JComboBox widget) {

    }

    private boolean isValid() {
        String id = idField.getText().trim();
        String psw = pswField.getText().trim();
        return !id.isEmpty() && !psw.isEmpty();
    }
}
