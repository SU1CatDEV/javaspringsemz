package su1cat.sem5.observers;

public class NoteObserver implements Observer {

    @Override
    public void update(String message) {
        System.out.println("Note observer: " + message);
    }
}
