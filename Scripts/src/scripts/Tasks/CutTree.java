package scripts.Tasks;

import org.powerbot.script.Condition;
import org.powerbot.script.Filter;
import org.powerbot.script.Script;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.*;
import scripts.Task;
import org.powerbot.script.Tile;

import java.util.concurrent.Callable;

@Script.Manifest(name="Cut Tree", description="Cut a tree", properties="author=Justin; topic=999; client=4;")


public class CutTree extends Task {
    final static int TREE_IDS[] = {10819, 10829, 10831, 10833};
    final static int LOGS_ID = 1519;



    public CutTree(ClientContext ctx) {
        super(ctx);

    }

    @Override
    public boolean activate() {
        if (ctx.players.local().animation() == -1 && ctx.inventory.select().count() < 28) {
            System.out.println("TRUE!");
        }
        else {
            System.out.println("FALSE!");

        }
        return ctx.players.local().animation() == -1 && ctx.inventory.select().count() < 28;
    }

    @Override
    public void execute() {
        GameObject treeToCut = ctx.objects.select().id(TREE_IDS).nearest().poll();
        treeToCut.interact("Chop down");
        Condition.wait(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                return ctx.players.local().animation() != -1;
            }
        }, 100, 10);

    }




}
