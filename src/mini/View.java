 package mini;
import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Enumeration;
import java.util.List;


public class View extends JFrame {
    private static View adminView = null;

    private static JFrame adminWindow;

    private static JPanel adminPanel;

    private static JPanel treePanel;
    private static JTree tree;

    private static JPanel addPanel;
    private static JTextField userID;
    private static JButton addUser;
    private static JTextField groupID;
    private static JButton addGroup;

    private static JPanel userViewPanel;
    private static JButton openUserViewButton;

    private static JPanel analysisPanel;
    private static JButton userTotal;
    private static JButton groupTotal;
    private static JButton messageTotal;

    private static DefaultMutableTreeNode groupNode;
    private static DefaultMutableTreeNode userNode;
    private static String groupNodeText = "";
    private static String userNodeText = "";

    private View(){
        
        adminWindow = new JFrame();

        adminPanel = new JPanel();
        adminPanel.setLayout(new GridBagLayout());

        
        treePanel = new JPanel();
        treePanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc;
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridheight = 3;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        adminPanel.add(treePanel, gbc);

        // Group User Tree
        DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode("root");
        tree = new JTree(rootNode);
        for (int i = 0; i < tree.getRowCount(); i++) {
            tree.expandRow(i);
        }
        tree.setEnabled(true);
        tree.setRootVisible(true);
        tree.setShowsRootHandles(true);
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        treePanel.add(tree, gbc);

        /*
            Add Group & User Panel
        */
        addPanel = new JPanel();
        addPanel.setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        adminPanel.add(addPanel, gbc);

        // User ID Text
        userID = new JTextField();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        addPanel.add(userID, gbc);

        // User ID Button
        addUser = new JButton();
        addUser.setText("Add User");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        addPanel.add(addUser, gbc);

        // Group ID Text
        groupID = new JTextField("");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        addPanel.add(groupID, gbc);

        // Group ID Button
        addGroup = new JButton();
        addGroup.setText("Add Group");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        addPanel.add(addGroup, gbc);

        /*
            User View.View Panel
        */
        userViewPanel = new JPanel();
        userViewPanel.setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        adminPanel.add(userViewPanel, gbc);

        // User View.View Button
        openUserViewButton = new JButton();
        openUserViewButton.setText("Open User View");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        userViewPanel.add(openUserViewButton, gbc);

        /*
            Analysis Panel
        */
        analysisPanel = new JPanel();
        analysisPanel.setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        adminPanel.add(analysisPanel, gbc);

        // User Total Button
        userTotal = new JButton();
        userTotal.setText("Show User Total");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        analysisPanel.add(userTotal, gbc);

        // Group Total Button
        groupTotal = new JButton();
        groupTotal.setText("Show Group Total");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        analysisPanel.add(groupTotal, gbc);

        // Message Total Button
        messageTotal = new JButton();
        messageTotal.setText("Show Message Total");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        analysisPanel.add(messageTotal, gbc);

        setListeners();

        adminWindow.add(adminPanel);
        adminWindow.setVisible(true);
        adminWindow.setSize(700, 500);
    }

    private void setListeners(){
        tree.addTreeSelectionListener(new TreeSelectionListener() {
           @Override
            public void valueChanged(TreeSelectionEvent e) {
                DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
                String text = node.toString();
               if (node == null) {
                   return;
               }
               else if(node.isRoot()) {
                    groupNodeText = text;
                    groupID.setText(groupNodeText);
                    groupNode = node;
               }
               else if(node.isLeaf() && !node.isRoot()){
                    userNodeText = text;
                    userID.setText(userNodeText);
                    userNode = node;
               }
               else{
                   groupNodeText = text;
                   groupID.setText(groupNodeText);
                   groupNode = node;
               }
            }
        });

        addUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String groupInput = groupID.getText();
                String userInput = userID.getText();
                Controller.addUser(groupNodeText, groupInput, userInput);
            }
        });

        addGroup.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String groupInput = groupID.getText();
                String userInput = userID.getText();
                Controller.addGroupUser(groupNodeText, groupInput, userInput);
            }
        });

        openUserViewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String userInput = userID.getText();
                Controller.requestUserView(userInput);
            }
        });

        userTotal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Controller.requestTotalUsers(true);
            }
        });
        groupTotal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Controller.requestTotalGroups(true);
            }
        });
        messageTotal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Controller.requestTotalMessages(true);
            }
        });
    }

    public static View getInstance(){
        if (adminView == null){
            return adminView = new View();
        }
        else{
            return adminView;
        }
    }

    public static void notifyModelChange(){
        DefaultTreeModel model = (DefaultTreeModel) tree.getModel();
        Group root = Group.getRoot();
        if(!groupNodeText.equals("root")) {
            root = Group.getGroup(root, groupNodeText);
        }
        updateJTree(model, groupNode, root);
        for (int i = 0; i < tree.getRowCount(); i++) {
            tree.expandRow(i);
        }
        tree.revalidate();
    }

    public static void updateJTree(DefaultTreeModel model, DefaultMutableTreeNode parent, Group group){
        List<Component> components = group.getComponents();
        for(Component component: components){
            String nodeID = component.getID();
            DefaultMutableTreeNode child = new DefaultMutableTreeNode(nodeID);
            if(component instanceof Group && !childExists(parent, nodeID)){
                model.insertNodeInto(child, parent, parent.getChildCount());
                tree.expandPath(new TreePath(model.getPathToRoot(child.getParent())));
                tree.scrollPathToVisible(new TreePath(child.getPath()));
                tree.addTreeSelectionListener(listener());
                updateJTree(model, child, (Group)component);
            }

            else if(component instanceof Group && childExists(parent, nodeID)){
                updateJTree(model, child, (Group)component);
            }
            else if(component instanceof User && !childExists(parent, nodeID)) {
                model.insertNodeInto(child, parent, parent.getChildCount());
                tree.expandPath(new TreePath(model.getPathToRoot(child.getParent())));
                tree.scrollPathToVisible(new TreePath(child.getPath()));
                tree.addTreeSelectionListener(listener());
            }
        }
    }

    public static TreeSelectionListener listener() {
        TreeSelectionListener objTreeListener = new TreeSelectionListener() {
            @Override
            public void valueChanged(TreeSelectionEvent e) {
                DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
                String text = node.toString();
                if (node == null) {
                    return;
                }
                if(node.isRoot()) {
                    groupNodeText = text;
                    groupID.setText(groupNodeText);
                    groupNode = node;
                }
                if(node.isLeaf() && !node.isRoot()){
                    userNodeText = text;
                    userID.setText(userNodeText);
                    userNode = node;
                }
            }
        };
        return objTreeListener;
    }

    
    private static boolean childExists(DefaultMutableTreeNode root, String s) {
        Enumeration<TreeNode> e = root.depthFirstEnumeration();
        while (e.hasMoreElements()) {
            DefaultMutableTreeNode node = (DefaultMutableTreeNode) e.nextElement();
            if (node.toString().equalsIgnoreCase(s)) {
                return true;
            }
        }
        return false;
    }

}