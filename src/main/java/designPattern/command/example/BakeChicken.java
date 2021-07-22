package designPattern.command.example;

public class BakeChicken extends Command {
    public BakeChicken(Barbecuer receiver) {
        super(receiver);
    }

    @Override
    public void excuteCommand() {
        receiver.bakeChicken();
    }
}
