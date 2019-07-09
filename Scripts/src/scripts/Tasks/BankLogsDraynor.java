package scripts.Tasks;

import org.powerbot.script.Condition;
import org.powerbot.script.Filter;
import org.powerbot.script.Script;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.*;
import scripts.Task;
import org.powerbot.script.Tile;

import java.util.concurrent.Callable;

@Script.Manifest(name="Bank Logs", description="Bank logs, Draynor Village", properties="author=Justin; topic=999; client=4;")


public class BankLogsDraynor extends Task {
    final static int LOGS_ID = 1519;



    public BankLogsDraynor(ClientContext ctx) {
        super(ctx);

    }

    @Override
    public boolean activate() {
        return ctx.inventory.select().count() > 27 &&
                ctx.bank.nearest().tile().distanceTo(ctx.players.local()) < 2;
    }

    @Override
    public void execute() {
        if (ctx.bank.opened()) {
            ctx.bank.deposit(LOGS_ID, 28);
            Condition.wait(new Callable<Boolean>() {
                @Override
                public Boolean call() throws Exception {
                    return ctx.inventory.select().count() == 0;
                }
            }, 150, 5);
        }
        else {
            ctx.bank.open();
        }
    }



}
