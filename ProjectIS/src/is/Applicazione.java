package is;

import is.mediator.Mediator;
import is.mediator.MediatorIF;

import javax.swing.*;
import java.awt.*;

public class Applicazione {
    public static void main(String[] args){
        JFrame frame = new JFrame("Applicazione");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout());


        JMenuBar menubar = new JMenuBar();
        JMenu area = new JMenu("Area"); menubar.add(area);
        JMenu role = new JMenu("Role"); menubar.add(role);
        JMenu association = new JMenu("Association"); menubar.add(association);
        frame.add(menubar);

        JMenuItem searchA = new JMenuItem("Search Area"); area.add(searchA);
        JMenuItem createA = new JMenuItem("Create Area"); area.add(createA);
        JMenuItem editA = new JMenuItem("Edit Area"); area.add(editA);
        JMenuItem remA = new JMenuItem("Remove Area"); area.add(remA);
        JMenuItem openA = new JMenuItem("Open Organization Chart"); area.add(openA);
        JMenuItem showA = new JMenuItem("Show Organization Chart"); area.add(showA);

        JMenuItem searchR = new JMenuItem("Search Role"); role.add(searchR);
        JMenuItem createR = new JMenuItem("Create Role"); role.add(createR);
        JMenuItem editR = new JMenuItem("Edit Role"); role.add(editR);
        JMenuItem remR = new JMenuItem("Remove Role"); role.add(remR);

        JMenuItem searchU = new JMenuItem("Search Association"); association.add(searchU);
        JMenuItem createU = new JMenuItem("Create Association"); association.add(createU);
        JMenuItem editU = new JMenuItem("Edit Association"); association.add(editU);
        JMenuItem remU = new JMenuItem("Remove Association"); association.add(remU);

        Mediator mediator = new Mediator();
        mediator.setItem(searchA,searchR,searchU,createA,createR,createU,editA,editR,editU,remA,remR,remU,openA,showA);
        searchA.addActionListener(e -> mediator.widgetCambiato(searchA));
        searchR.addActionListener(e -> mediator.widgetCambiato(searchR));
        searchU.addActionListener(e -> mediator.widgetCambiato(searchU));
        createA.addActionListener(e -> mediator.widgetCambiato(createA));
        createR.addActionListener(e -> mediator.widgetCambiato(createR));
        createU.addActionListener(e -> mediator.widgetCambiato(createU));
        editA.addActionListener(e -> mediator.widgetCambiato(editA));
        editR.addActionListener(e -> mediator.widgetCambiato(editR));
        editU.addActionListener(e -> mediator.widgetCambiato(editU));
        remA.addActionListener(e -> mediator.widgetCambiato(remA));
        remR.addActionListener(e -> mediator.widgetCambiato(remR));
        remU.addActionListener(e -> mediator.widgetCambiato(remU));
        openA.addActionListener(e -> mediator.widgetCambiato(openA));
        showA.addActionListener(e -> mediator.widgetCambiato(showA));

        frame.setLocation(350,150);
        frame.setSize(1000,700);
        frame.setResizable(false);
        frame.setVisible(true);
    }
}
