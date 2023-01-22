package model;

import Factory.ImageFactory;
import javafx.scene.control.TreeItem;
import java.util.ArrayList;
import java.util.List;


public class TreeItemVisitor implements Visitor {

    @Override
    public TreeItem<Captor> visit(CaptorArea captor) {
        TreeItem<Captor> treeItem = new TreeItem<>(captor, new ImageFactory().imagePath("/images/captorArea.png"));
        try {
            for (Captor child : captor.getChildren()) {
                treeItem.getChildren().add(child.accept(this));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return treeItem;
    }

    @Override
    public TreeItem<Captor> visit(CaptorBasic captor) {
        TreeItem<Captor> treeItem = new TreeItem<>(captor, new ImageFactory().imagePath("/images/captor.png"));
        return treeItem;
    }
}