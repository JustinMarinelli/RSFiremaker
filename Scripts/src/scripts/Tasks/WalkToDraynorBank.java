package scripts.Tasks;

import org.powerbot.script.Condition;
import org.powerbot.script.Filter;
import org.powerbot.script.Script;
import org.powerbot.script.Tile;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.*;
import scripts.Task;
import scripts.Walker;

import java.util.concurrent.Callable;

@Script.Manifest(name="Walk", description="Kill NPCs from exp", properties="author=Justin; topic=999; client=4;")


public class WalkToDraynorBank extends Task {
    public static final Tile[] pathToBank = {new Tile(3089, 3231, 0), new Tile(3090, 3234, 0), new Tile(3089, 3237, 0), new Tile(3087, 3240, 0), new Tile(3087, 3243, 0), new Tile(3087, 3246, 0), new Tile(3090, 3247, 0), new Tile(3092, 3244, 0)};
    private final Walker walker = new Walker(ctx);
    final static int LOGS_ID = 1519;
    final static int TREE_IDS[] = {10819, 10829, 10831, 10833};
    public Tile treeSpot = new Tile(3089, 3231, 0);


    public WalkToDraynorBank(ClientContext ctx) {
        super(ctx);
    }


    @Override
    public boolean activate() {
        return ((ctx.inventory.select().count() > 27 &&
                ctx.bank.nearest().tile().distanceTo(ctx.players.local()) > 2) ||
                (ctx.inventory.select().id(LOGS_ID).count() == 0) &&
                ctx.players.local().tile().distanceTo(treeSpot) > 2);
    }

    @Override
    public void execute() {
        super.checkRun();
        if (!ctx.players.local().inMotion() || ctx.movement.destination().equals(Tile.NIL) || ctx.movement.destination().distanceTo(ctx.players.local()) < 5) {
            if (ctx.inventory.isFull()) {
                walker.walkPath(pathToBank);
            }
            else {
                walker.walkPathReverse(pathToBank);
            }
        }
    }
}



