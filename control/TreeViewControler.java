package control;

;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.scene.Node;
import javafx.scene.control.*;

import javafx.scene.image.ImageView;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TreeView;
import javafx.scene.control.TreeItem;
import javafx.scene.image.Image;



public class TreeViewControler implements Initializable {

    @FXML
    private TreeView<String> tree;

    @Override
    public void initialize(URL location, ResourceBundle resource) {

        setTreeItem();

        // TODO Auto-generated method stub
    }

    public void setTreeItem() {
        // Node folderIcon = new ImageView(new Image(getClass().getResourceAsStream("/images/foulder.png")));
        TreeItem<String> root = new TreeItem<String>("folder");
        Node folderIcon = new ImageView(new Image(getClass().getResourceAsStream("../images/folder.png")));
        Node folderIcon2 = new ImageView(new Image(getClass().getResourceAsStream("../images/folder.png")));
        Node folderIcon3 = new ImageView(new Image(getClass().getResourceAsStream("../images/folder.png")));
        

        root.setExpanded(true);
        root.getChildren().addAll(new TreeItem<>("Subfolder1", folderIcon), new TreeItem<>("Subfolder2", folderIcon2), new TreeItem<>("Subfolder", folderIcon3));
        tree.setRoot(root);
    }

    /**
     * public void loadTreeItems() { Node folderIcon = new ImageView(new
     * Image(getClass().getResourceAsStream("../icon/folder-icon.png"))); Node
     * folderIconA = new ImageView(new
     * Image(getClass().getResourceAsStream("../icon/folder-icon.png"))); Node
     * folderIconB= new ImageView(new
     * Image(getClass().getResourceAsStream("../icon/folder-icon.png"))); Node
     * folderIconC= new ImageView(new
     * Image(getClass().getResourceAsStream("../icon/folder-icon.png"))); Node
     * folderIconD= new ImageView(new
     * Image(getClass().getResourceAsStream("../icon/folder-icon.png"))); Node
     * folderIconE= new ImageView(new
     * Image(getClass().getResourceAsStream("../icon/folder-icon.png")));
     *
     * TreeItem<String> NavigationItem = new TreeItem<>("Folder", folderIcon);
     * NavigationItem.setExpanded(true);
     *
     * TreeItem<String> SubfolderItem = new TreeItem<>("Subfolder",
     * folderIconA); NavigationItem.getChildren().add(SubfolderItem);
     *
     * SubfolderItem = new TreeItem<>("Subfolder", folderIconB);
     * NavigationItem.getChildren().add(SubfolderItem);
     *
     * SubfolderItem = new TreeItem<>("Subfolder", folderIconC);
     * NavigationItem.getChildren().add(SubfolderItem);
     *
     * SubfolderItem = new TreeItem<>("Subfolder", folderIconD);
     * NavigationItem.getChildren().add(SubfolderItem);
     *
     * SubfolderItem = new TreeItem<>("Subfolder", folderIconE);
     * NavigationItem.getChildren().add(SubfolderItem);
     *
     * tree.setRoot(NavigationItem); } * }
     */
}
