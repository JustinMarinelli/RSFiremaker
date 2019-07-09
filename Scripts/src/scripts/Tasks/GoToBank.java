package scripts.Tasks;

import org.powerbot.script.Script;
import org.powerbot.script.Tile;
import org.powerbot.script.rt4.ClientContext;
import scripts.Task;
import scripts.Walker;

@Script.Manifest(name="Go to bank", description="Walk to bank to get more logs", properties="author=Justin; topic=999; client=4;")


public class GoToBank extends Task {
    public static final Tile[] pathToBank = {new Tile(3201, 3430, 0), new Tile(3199, 3430, 0), new Tile(3197, 3430, 0), new Tile(3195, 3430, 0), new Tile(3193, 3430, 0), new Tile(3191, 3430, 0), new Tile(3189, 3430, 0), new Tile(3187, 3430, 0), new Tile(3185, 3430, 0), new Tile(3183, 3430, 0), new Tile(3181, 3430, 0), new Tile(3182, 3432, 0), new Tile(3182, 3434, 0), new Tile(3184, 3436, 0)};

    private final Walker walker = new Walker(ctx);
    final static int LOGS_ID = 1511;


    public GoToBank(ClientContext ctx) {
        super(ctx);
    }

    @Override
    public boolean activate() {
        System.out.println(ctx.inventory.select().id(LOGS_ID).count());
        return ((ctx.inventory.isFull() && ctx.players.local().tile().distanceTo(super.getFireSpot()) > 4)) ||
                (ctx.inventory.select().id(LOGS_ID).count() == 0 && ctx.bank.nearest().tile().distanceTo(ctx.players.local()) > 4) &&
                ctx.players.local().animation() == -1;
    }


    @Override
    public void execute() {
        super.checkRun();
        if (!ctx.players.local().inMotion() || ctx.movement.destination().equals(Tile.NIL) || ctx.movement.destination().distanceTo(ctx.players.local()) < 5) {
            if (ctx.inventory.isFull()) {
                walker.walkPathReverse(pathToBank);
            }
            else {
                walker.walkPath(pathToBank);
            }
        }
    }
}



