package is.mediator;

import is.Funzioni.Administrator;
import is.organigramma.Azienda;

import javax.swing.*;

public class Mediator implements MediatorIF{
    private JMenuItem searchA,createA,editA,remA,searchR,createR,editR,remR,searchU,createU,editU,remU,openA,showA;
    private JTextField idField,pswField, nameField;
    private JButton confButton, saveB, saveV;
    private JScrollPane descrScroll;
    private JComboBox dadComboBox;
    private Azienda azienda;

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
    public void setIdField(JTextField idField){
        this.idField = idField;
    }
    public void setPswField(JTextField pswField){
        this.pswField = pswField;
    }
    public void setNameField(JTextField nameField){
        this.nameField=nameField;
    }
    public void setDescrScroll(JScrollPane descrScroll){
        this.descrScroll=descrScroll;
    }
    public void setSaveB(JButton saveB){
        this.saveB=saveB;
    }
    public void setSaveV(JButton saveV){
        this.saveV=saveV;
    }
    public void setDadComboBox(JComboBox<String> dadComboBox){
        this.dadComboBox=dadComboBox;
    }
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
            Administrator admin = new Administrator("Luca","Barbieri","myemail@gmail.com",null);

            if (id.equals("1") && psw.equals("ciao")){
                JOptionPane.showMessageDialog(SwingUtilities.getAncestorOfClass(JFrame.class, widget)
                        , "Accesso effettuato da " + admin.getName() + " " + admin.getSurname());
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
