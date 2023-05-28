package is;

import is.mediator.Mediator;
import is.organigramma.Azienda;
import is.panels.CreateAreaPanel;
import is.panels.HomePanel;
import is.shapes.ImageZoom;

import javax.swing.*;
import java.awt.*;

public class Applicazione {
    public static void main(String[] args){
        JFrame frame = new JFrame("Applicazione");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JMenuBar menubar = new JMenuBar();
        JMenu area = new JMenu("Area"); menubar.add(area);
        JMenu role = new JMenu("Role"); menubar.add(role);
        JMenu association = new JMenu("Association"); menubar.add(association);
        frame.setJMenuBar(menubar);

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


        //Azienda azienda = new Azienda();
        Mediator mediator = new Mediator(null);
        mediator.setItem(searchA,searchR,searchU,createA,createR,createU,editA,editR,editU,remA,remR,remU,openA,showA);
        searchA.addActionListener(e -> mediator.menuChanged(searchA));
        searchR.addActionListener(e -> mediator.menuChanged(searchR));
        searchU.addActionListener(e -> mediator.menuChanged(searchU));
        createA.addActionListener(e -> mediator.menuChanged(createA));
        createR.addActionListener(e -> mediator.menuChanged(createR));
        createU.addActionListener(e -> mediator.menuChanged(createU));
        editA.addActionListener(e -> mediator.menuChanged(editA));
        editR.addActionListener(e -> mediator.menuChanged(editR));
        editU.addActionListener(e -> mediator.menuChanged(editU));
        remA.addActionListener(e -> mediator.menuChanged(remA));
        remR.addActionListener(e -> mediator.menuChanged(remR));
        remU.addActionListener(e -> mediator.menuChanged(remU));
        openA.addActionListener(e -> mediator.menuChanged(openA));
        showA.addActionListener(e -> mediator.menuChanged(showA));


        //Panel1
        //HomePanel home = new HomePanel(mediator); frame.add(home);
        CreateAreaPanel createArea = new CreateAreaPanel(mediator,null); frame.add(createArea);
        //Frame settings
        frame.setLocation(350,150);
        frame.setSize(1000,650);
        frame.setResizable(false);
        frame.setVisible(true);
    }
}
