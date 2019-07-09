package scripts.Tasks;

import org.powerbot.script.Condition;
import org.powerbot.script.Filter;
import org.powerbot.script.Script;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.*;
import scripts.Task;
import org.powerbot.script.Tile;

import java.util.concurrent.Callable;

@Script.Manifest(name="Withdraw Logs", description="Withdraw logs from bank", properties="author=Justin; topic=999; client=4;")

public class WithdrawLogs extends Task {



    public WithdrawLogs(ClientContext ctx) {
        super(ctx);

    }

    @Override
    public boolean activate() {
        System.out.println(ctx.bank.nearest().tile().distanceTo(ctx.players.local()));
        return ctx.inventory.select().id(super.getLogsId()).count() == 0 &&
                ctx.bank.nearest().tile().distanceTo(ctx.players.local()) < 4;
    }

    @Override
    public void execute() {
        if (ctx.bank.opened()) {
            ctx.bank.withdraw(super.getLogsId()[super.getLogsId().length - 1], 28);
            super.incrementFires();
            System.out.println("INCREMENTS!");
            Condition.wait(new Callable<Boolean>() {
                @Override
                public Boolean call() throws Exception {
                    return ctx.inventory.isFull();
                }
            }, 150, 5);
        }
        else {
            ctx.bank.open();
        }
    }
}
