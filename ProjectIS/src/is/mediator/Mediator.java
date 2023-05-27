package is.mediator;

import javax.swing.*;

public class Mediator implements MediatorIF{
    private JMenuItem searchA,createA,editA,remA,searchR,createR,editR,remR,searchU,createU,editU,remU;

    public void setItem(JMenuItem searchA,JMenuItem searchR, JMenuItem searchU, JMenuItem createA, JMenuItem createR,JMenuItem createU, JMenuItem editA, JMenuItem editR,
                        JMenuItem editU, JMenuItem remA,JMenuItem remR, JMenuItem remU){
        this.searchA = searchA; this.searchR=searchR; this.searchU=searchU;
        this.createA = createA; this.createR=createR; this.createU=createU;
        this.editA = editA; this.editR=editR; this.editU=editU;
        this.remA=remA; this.remR=remR; this.remU=remU;
    }
    public void widgetCambiato(JMenuItem widget){
        //if (widget == searchA)
    }
}
