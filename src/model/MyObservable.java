package model;

import java.util.Observable;
import java.util.Observer;



public class MyObservable extends Observable {
    private int data = 0;

    public void setData(int data) {
        this.data = data;
        setChanged();
        notifyObservers();
    }
    public int getData() {
        return data;
    }
}
/* Dans ma fenetre
public static class MyObserver implements Observer {

    @Override
    public void update(Observable observable, Object arg) {
        // Récupère les données mises à jour
        MyObservable myObservable = (MyObservable) observable;
        int data = myObservable.getData();
        // Traite les données mises à jour
        System.out.println("Data updated: " + data);
    }
}
*/



