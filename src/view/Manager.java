package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import service.ManagerService;

/**
 * Major UI class that creates a window with all managers operations and menu
 * options
 * 
 * @author kshittiz
 *
 */
public class Manager extends JFrame implements ActionListener, ListSelectionListener {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    static JButton submit;
    JList<String> addList, fetchList, upList, rmList;;
    static JLabel opLabel;
    String action = null;
    Vector<Vector<Object>> contactData;

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
                "Add new service type", "Add new service", "Add new discount",
                "Add contact details" };
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
                "See all customers in your hotel", "See discounts offered",
                "See occupancy statistics", "See total revenue generated by your hotel",
                "See contact details" };
        fetchList = new JList<String>(fetchOps);
        fetchList.setVisibleRowCount(5);
        fetchList.setBorder(BorderFactory.createTitledBorder("Available operations"));
        fetchList.addListSelectionListener(this);
        fetch.add(new JScrollPane(fetchList), BorderLayout.CENTER);

        // building UI for UPDATE operation
        String[] upOps = { "Update staff member details", "Update hotel details",
                "Update room details", "Update contact details" };
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

        // ADD operations
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

        case "Add contact details":
            new ContactDetails(this, "Add contact details");
            break;
        // FETCH operations
        case "See your hotel details":
            new FetchOperations(this, "See your hotel details", "Your hotel details");
            break;
        case "See all hotels in chain":
            new FetchOperations(this, "See all hotels in chain", "All hotels in your hotel chain");
            break;
        case "See your staff details":
            new FetchOperations(this, "See your staff details", "Details of all staff members");
            break;
        case "See all customers in your hotel":
            new FetchOperations(this, "See all customers in your hotel",
                    "Details of all customers in your hotel");
            break;
        case "See all room categories in your hotel":
            new FetchOperations(this, "See all room categories in your hotel",
                    "Room categories available");
            break;
        case "See all rooms in your hotel":
            new FetchOperations(this, "See all rooms in your hotel",
                    "All rooms available in hotel");
            break;
        case "See all types of services in hotel chain":
            new FetchOperations(this, "See all types of services in hotel chain",
                    "All types of services available in hotel chain");
            break;
        case "See all services offered by your hotel":
            new FetchOperations(this, "See all services offered by your hotel",
                    "All services offered");
            break;
        case "See discounts offered":
            new FetchOperations(this, "See discounts offered",
                    "All types of discounts offered in hotel chain");
            break;
        case "See occupancy statistics":
            new FetchOperations(this, "See occupancy statistics", "Occupancy statistics");
            break;
        case "See total revenue generated by your hotel":
            new DateQuery(this);
            break;
        case "See contact details":
            new ContactDetails(this, "See contact details");
            break;

        // UPDATE operations
        case "Update staff member details":
            new UpdateOperations(this, "Update staff member details",
                    "Click on each cell and change values (one row at a time), submit your changes using update button to update data");
            break;
        case "Update hotel details":
            new UpdateOperations(this, "Update hotel details",
                    "Click on each cell and change values (one row at a time), submit your changes using update button to update data");
            break;
        case "Update room details":
            new UpdateOperations(this, "Update room details",
                    "Click on each cell and change values (one row at a time), submit your changes using update button to update data");
            break;
        case "Update contact details":
            new ContactDetails(this, "Update contact details");
            break;

        // REMOVE operations
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
                    "All rooms available in your hotel, select room number and click delete!");
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

        case "Remove contact details":
            new ContactDetails(this, "Remove contact details");
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

@SuppressWarnings("serial")
class ContactDetails extends JDialog implements ActionListener {
    JTextField id = new JTextField();
    JPanel contact = new JPanel(new GridLayout(3, 2, 0, 3));
    JLabel name = new JLabel(" Phone Number");
    JLabel email = new JLabel(" Email");

    JTextField nameT = new JTextField();
    JTextField emailT = new JTextField();

    JLabel label = new JLabel(" Enter Contact ID");
    JComboBox<String> type = new JComboBox<String>(new String[] { "people", "hotel" });
    JButton submit = new JButton("Delete record");
    Manager manager;
    String title;

    ContactDetails(Manager manager, String title) {
        super(manager, title, true);
        this.manager = manager;
        this.title = title;
        if ("See contact details".equals(title) || "Update contact details".equals(title)) {
            add(label, BorderLayout.WEST);
            add(id, BorderLayout.CENTER);
            label.setText(" Enter ID ");
            add(type, BorderLayout.EAST);
            submit.setText("Fetch record");
        } else if ("Remove contact details".equals(title)) {
            add(label, BorderLayout.WEST);
            add(id, BorderLayout.CENTER);
            label.setText(" Enter Contact ID ");
            submit.setText("Delete record");
        } else {
            label.setText(" Enter Person/Hotel ID");
            contact.add(label);
            contact.add(id);
            contact.add(name);
            contact.add(nameT);
            contact.add(email);
            contact.add(emailT);
            add(type, BorderLayout.EAST);
            type.addActionListener(this);
            add(contact, BorderLayout.CENTER);
            submit.setText("Add record");
        }
        add(submit, BorderLayout.SOUTH);
        submit.addActionListener(this);
        submit.setBackground(Color.ORANGE);
        setLocation(manager.getLocation());
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(dim.width / 5, dim.height / 8);
        setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if ("See contact details".equals(title)) {
            manager.contactData = ManagerService.getContactDetails((String) type.getSelectedItem(),
                    id.getText());
            if (manager.contactData != null) {
                new FetchOperations(manager, "See contact details", "Contact Lookup");
            } else {
                Manager.opLabel.setText("Record can not be fetched, error in input!");
                Manager.opLabel.setForeground(Color.RED);
            }
            this.dispose();

        } else if ("Update contact details".equals(title)) {
            manager.contactData = ManagerService.getContactDetails((String) type.getSelectedItem(),
                    id.getText());
            if (manager.contactData != null) {
                new UpdateOperations(manager, "Update contact details",
                        "Click on each cell and change values (one row at a time), submit your changes using update button to update data");
            } else {
                Manager.opLabel.setText("Record can not be fetched, error in input!");
                Manager.opLabel.setForeground(Color.RED);
            }
            this.dispose();

        } else if ("Remove contact details".equals(title)) {
            if (!ManagerService.deleteContact(id.getText())) {
                Manager.opLabel.setText("Record can not be fetched, error in input!");
                Manager.opLabel.setForeground(Color.RED);
            } else {
                Manager.opLabel.setText("Contact deleted successfully!");
                Manager.opLabel.setForeground(Color.GREEN);
            }
            this.dispose();

        } else {
            if (e.getSource() == type)
                if ("hotel".equals(type.getSelectedItem())) {
                    id.setText("" + LoginHMS.hid);
                    id.setEnabled(false);
                } else {
                    id.setText("");
                    id.setEnabled(true);
                }
            if (e.getSource() == submit) {
                if (!ManagerService.addContactInfo(id.getText(), nameT.getText(), emailT.getText(),
                        (String) type.getSelectedItem())) {
                    Manager.opLabel.setText("Record can not be added, error in input!");
                    Manager.opLabel.setForeground(Color.RED);
                } else {
                    Manager.opLabel.setText("Contact added successfully!");
                    Manager.opLabel.setForeground(Color.GREEN);
                }
                this.dispose();

            }
        }

    }

}

/**
 * Class that creates dialog box for allowing manager to enter date range for
 * generating hotel revenue
 * 
 * @author kshittiz
 *
 */
@SuppressWarnings("serial")
class DateQuery extends JDialog implements ActionListener {
    JLabel entry = new JLabel("Start Date");
    JLabel exit = new JLabel("End Date");

    JTextField entryDate = new JTextField("yyyy-mm-dd HH:mm:ss");
    JTextField exitDate = new JTextField("yyyy-mm-dd HH:mm:ss");

    JButton submit = new JButton("submit");
    Manager manager;

    DateQuery(Manager manager) {
        super(manager, "Specify date range", true);
        this.manager = manager;
        JPanel panel = new JPanel(new GridLayout(2, 2, 0, 3));
        panel.add(entry);
        panel.add(entryDate);
        panel.add(exit);
        panel.add(exitDate);
        add(panel, BorderLayout.CENTER);
        add(submit, BorderLayout.SOUTH);
        submit.addActionListener(this);
        submit.setBackground(Color.ORANGE);
        setSize(350, 150);
        setLocation(manager.getLocation());
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-mm-dd HH:mm:ss");
        try {
            Date startDate = myFormat.parse(entryDate.getText());
            Date endDate = myFormat.parse(entryDate.getText());
            double revenue = ManagerService.getRevenue(new Timestamp(startDate.getTime()),
                    new Timestamp(endDate.getTime()));
            Manager.opLabel.setText("Total revenue generated by your hotel: $" + revenue);
            Manager.opLabel.setForeground(Color.BLUE);

        } catch (ParseException e1) {
            e1.printStackTrace();
            Manager.opLabel.setText("Wrong date format used");
            Manager.opLabel.setForeground(Color.RED);
        }
        this.dispose();
    }
}
