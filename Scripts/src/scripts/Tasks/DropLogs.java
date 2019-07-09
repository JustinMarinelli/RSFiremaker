package scripts.Tasks;

import org.powerbot.script.Condition;
import org.powerbot.script.Filter;
import org.powerbot.script.Script;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.*;
import scripts.Task;
import org.powerbot.script.Tile;

import java.util.concurrent.Callable;

@Script.Manifest(name="DropLogs", description="Drop logs from inventory", properties="author=Justin; topic=999; client=4;")


public class DropLogs extends Task {
    final static int LOGS_ID = 1521;



    public DropLogs(ClientContext ctx) {
        super(ctx);

    }

    @Override
    public boolean activate() {
        return ctx.inventory.select().count() > 27;
    }

    @Override
    public void execute() {
        for (Item logs : ctx.inventory.select().id(LOGS_ID)) {
            final int initialLogCount = ctx.inventory.select().id(LOGS_ID).count();
            logs.interact("Drop");
            Condition.wait(new Callable<Boolean>() {
                @Override
                public Boolean call() throws Exception {
                    return ctx.inventory.select().id(LOGS_ID).count() < initialLogCount;
                }
            }, 25, 20);
        }
    }



}
