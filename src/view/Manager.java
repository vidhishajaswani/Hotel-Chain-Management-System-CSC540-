package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;

public class Manager extends JFrame implements ActionListener {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    JButton submit;
    JList<String> addList;
    JList<String> fetchList;
    JList<String> upList;
    JList<String> rmList;
    JTable table;

    Manager(String name) {
        super("Manager View - " + name);

        // adding manager operation tabs
        JTabbedPane tabbedPane = new JTabbedPane();
        JPanel add = new JPanel(new BorderLayout());
        ImageIcon addIcon = new ImageIcon(new ImageIcon("images/add.png").getImage()
                .getScaledInstance(30, 30, Image.SCALE_SMOOTH));
        tabbedPane.addTab("Add", addIcon, add);

        JPanel fetch = new JPanel(new BorderLayout());
        ImageIcon fetchIcon = new ImageIcon(new ImageIcon("images/fetch.png").getImage()
                .getScaledInstance(30, 30, Image.SCALE_SMOOTH));
        tabbedPane.addTab("Fetch", fetchIcon, fetch);

        JPanel update = new JPanel(new BorderLayout());
        ImageIcon updateIcon = new ImageIcon(new ImageIcon("images/update.png").getImage()
                .getScaledInstance(30, 30, Image.SCALE_SMOOTH));
        tabbedPane.addTab("Update", updateIcon, update);

        JPanel remove = new JPanel(new BorderLayout());
        ImageIcon removeIcon = new ImageIcon(new ImageIcon("images/remove.png").getImage()
                .getScaledInstance(30, 30, Image.SCALE_SMOOTH));
        tabbedPane.addTab("Remove", removeIcon, remove);

        // set this view to full screen size
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        // building UI for ADD operation
        String[] addOps = { "Add new staff member", "Add new room category", "Add new room",
                "Add new service type", "Add new service", "Add new discount" };
        addList = new JList<String>(addOps);
        addList.setPreferredSize(new Dimension(dim.width / 3 - 30, 50));
        addList.setBorder(BorderFactory.createTitledBorder("Available operations"));
        add.add(new JScrollPane(addList), BorderLayout.CENTER);
        JLabel addL = new JLabel("Please select any operation from above and click submit");
        add.add(addL, BorderLayout.SOUTH);

        // building UI for FETCH operation
        String[] fetchOps = { "Front Desk Representative", "Manager", "Chairman" };
        fetchList = new JList<String>(fetchOps);
        fetchList.setBorder(BorderFactory.createTitledBorder("Available operations"));
        fetchList.setPreferredSize(new Dimension(dim.width / 3 - 30, 50));
        fetch.add(new JScrollPane(fetchList), BorderLayout.CENTER);
        JLabel fetchL = new JLabel("Please select any operation from above and click submit");
        fetch.add(fetchL, BorderLayout.SOUTH);

        // building UI for UPDATE operation
        String[] upOps = { "Update staff member details", "Update hotel details",
                "Update room category", "Update room details", "Update service type in hotel chain",
                "Update services offered", "Update discount details",
                "Update personal contact information" };
        upList = new JList<String>(upOps);
        upList.setBorder(BorderFactory.createTitledBorder("Available operations"));
        upList.setPreferredSize(new Dimension(dim.width / 3 - 30, 50));
        update.add(new JScrollPane(upList), BorderLayout.CENTER);
        JLabel updateL = new JLabel("Please select any operation from above and click submit");
        update.add(updateL, BorderLayout.SOUTH);

        // building UI for REMOVE operation
        String[] rmOps = { "Remove staff member", "Remove room category", "Remove room",
                "Remove service type", "Remove Services offered", "Remove discount",
                "Remove contact details" };
        rmList = new JList<String>(rmOps);
        rmList.setBorder(BorderFactory.createTitledBorder("Available operations"));
        rmList.setPreferredSize(new Dimension(dim.width / 3 - 30, 50));
        remove.add(new JScrollPane(rmList), BorderLayout.CENTER);
        JLabel rmL = new JLabel("Please select any operation from above and click submit");
        remove.add(rmL, BorderLayout.SOUTH);

        // adding tabs on JFrame
        add(tabbedPane, BorderLayout.CENTER);
        // adding submit button on JFrame
        submit = new JButton("submit");
        submit.setBackground(Color.GREEN);
        add(submit, BorderLayout.SOUTH);
        setSize(dim.width / 3, dim.height / 3);
        setLocation(100, 100);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

}
