package designPattern.PublishSubscribe;

public interface Subject {
    void attach(Observer observer);

    void detach(Observer observer);

    void nofity();

}
