package designPattern.command.example;

public class BakeButtonCommand extends Command {
    public BakeButtonCommand(Barbecuer receiver) {
        super(receiver);
    }
    @Override
    public void excuteCommand() {
        receiver.bakeMutton();
    }
}
