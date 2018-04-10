package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
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
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class Manager extends JFrame implements ActionListener, ListSelectionListener {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    static JButton submit;
    JList<String> addList, fetchList, upList, rmList;;
    static JLabel opLabel;
    String action = null;

    // 421319931
    Manager(String name) {
        super("Manager View - " + name);

        // adding manager operation tabs
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.setBackground(Color.DARK_GRAY);
        tabbedPane.setForeground(Color.WHITE);
        // getContentPane().setBackground(Color.GRAY);
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
        addList.setVisibleRowCount(5);
        addList.setBorder(BorderFactory.createTitledBorder("Available operations"));
        addList.addListSelectionListener(this);
        add.add(new JScrollPane(addList), BorderLayout.CENTER);

        // building UI for FETCH operation
        String[] fetchOps = { "See your hotel details", "See all hotels in chain",
                "See all rooms in your hotel", "See all room categories in your hotel",
                "See all services offered by your hotel",
                "See all types of services in hotel chain", "See your staff details",
                "See all customers in your hotel", "See occupancy stats of all hotels",
                "See occupancy stats of your hotel", "See total revenue generated by your hotel",
                "See total revenue generated by other hotels in chain" };
        fetchList = new JList<String>(fetchOps);
        fetchList.setVisibleRowCount(5);
        fetchList.setBorder(BorderFactory.createTitledBorder("Available operations"));
        fetchList.addListSelectionListener(this);
        fetch.add(new JScrollPane(fetchList), BorderLayout.CENTER);

        // building UI for UPDATE operation
        String[] upOps = { "Update staff member details", "Update hotel details",
                "Update room category", "Update room details", "Update service type in hotel chain",
                "Update services offered", "Update discount details",
                "Update personal contact information" };
        upList = new JList<String>(upOps);
        upList.setVisibleRowCount(5);
        upList.setBorder(BorderFactory.createTitledBorder("Available operations"));
        upList.addListSelectionListener(this);
        update.add(new JScrollPane(upList), BorderLayout.CENTER);

        // building UI for REMOVE operation
        String[] rmOps = { "Remove staff member", "Remove room category", "Remove room",
                "Remove service type", "Remove Services offered", "Remove discount",
                "Remove contact details" };
        rmList = new JList<String>(rmOps);
        upList.setVisibleRowCount(5);
        rmList.setBorder(BorderFactory.createTitledBorder("Available operations"));
        rmList.addListSelectionListener(this);
        remove.add(new JScrollPane(rmList), BorderLayout.CENTER);

        // adding tabs on JFrame
        add(tabbedPane, BorderLayout.CENTER);

        JPanel end = new JPanel(new GridLayout(2, 1));
        // adding submit button on end panel
        submit = new JButton("submit");
        ImageIcon submitIcon = new ImageIcon(new ImageIcon("images/submit.png").getImage()
                .getScaledInstance(30, 22, Image.SCALE_SMOOTH));
        submit.setIcon(submitIcon);
        submit.setBackground(Color.DARK_GRAY);
        submit.setForeground(Color.GREEN);
        submit.addActionListener(this);
        // adding help label on end panel
        opLabel = new JLabel("Please select single operation from above and click submit");
        end.add(opLabel);
        end.add(submit);
        add(end, BorderLayout.SOUTH);
        getRootPane().setDefaultButton(submit);

        setSize(dim.width / 3, (dim.height - 100) / 2);
        setLocation(100, 100);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (action == null) {
            opLabel.setForeground(Color.RED);
            opLabel.setText("No operation selected!");
            return;
        }

        // switch case for each operations a manager can perform
        switch (action) {
        case "Add new staff member":
            new NewStaff(this);
            break;
        case "Add new room category":
            new NewRoomCategory(this);
            break;
        case "Add new room":
            new NewRoom(this);
            break;
        case "Add new service type":
            new NewServiceType(this);
            break;
        case "Add new service":
            new NewService(this);
            break;
        case "Add new discount":
            new NewDiscount(this);
            break;
        case "Remove staff member":
            new DeleteOperations(this, "Remove staff member",
                    "Details of all staff members, Select ID column and click delete!");
            break;
        case "Remove room category":
            new DeleteOperations(this, "Remove room category",
                    "Room categories available, select room category or occupancy or both and click delete!");
            break;
        case "Remove room":
            new DeleteOperations(this, "Remove room",
                    "All rooms available in hotel, select room number and click delete!");
            break;
        case "Remove service type":
            new DeleteOperations(this, "Remove service type",
                    "All types of services available in hotel chain, Select type and click delete!");
            break;
        case "Remove Services offered":
            new DeleteOperations(this, "Remove Services offered",
                    "All services offered, select service number and click delete!");
            break;
        case "Remove discount":
            new DeleteOperations(this, "Remove discount",
                    "All types of discounts offered in hotel chain, Select type and click delete!");
            break;
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public void valueChanged(ListSelectionEvent le) {
        opLabel.setForeground(null);
        opLabel.setText("Please select single operation from above and click submit");
        action = (String) ((JList<String>) le.getSource()).getSelectedValue();
    }

}
