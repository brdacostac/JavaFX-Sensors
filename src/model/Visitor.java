package model;

import javafx.scene.control.TreeItem;

public interface Visitor {
    TreeItem<Captor> visit(CaptorArea captor) throws Exception;
    TreeItem<Captor> visit(CaptorBasic captor);
}