package scripts.Tasks;

import org.powerbot.script.Script;
import org.powerbot.script.Tile;
import org.powerbot.script.rt4.ClientContext;
import scripts.Task;
import scripts.Walker;

@Script.Manifest(name="Go to bank", description="Walk to bank to get more logs", properties="author=Justin; topic=999; client=4;")


public class GoToBank extends Task {
    public static final Tile[] pathToBank = {new Tile(3201, 3430, 0), new Tile(3199, 3430, 0), new Tile(3197, 3430, 0), new Tile(3195, 3430, 0), new Tile(3193, 3430, 0), new Tile(3191, 3430, 0), new Tile(3189, 3430, 0), new Tile(3187, 3430, 0), new Tile(3185, 3430, 0), new Tile(3183, 3430, 0), new Tile(3181, 3430, 0), new Tile(3182, 3432, 0), new Tile(3182, 3434, 0), new Tile(3184, 3436, 0)};
    public static final Tile fireSpot = new Tile(3201, 3430, 0);
    private final Walker walker = new Walker(ctx);
    static int LOGS_ID = 1519;


    public GoToBank(ClientContext ctx, int li) {
        super(ctx);
        LOGS_ID = li;

    }

    @Override
    public boolean activate() {
        System.out.println(LOGS_ID);
        return ((ctx.inventory.select().id(super.getLogsId()[LOGS_ID]).count() > 0 && ctx.players.local().tile().distanceTo(fireSpot) > 4)) ||
                (ctx.inventory.select().id(super.getLogsId()[LOGS_ID]).count() == 0 && ctx.bank.nearest().tile().distanceTo(ctx.players.local()) > 2);
    }


    @Override
    public void execute() {
        System.out.println("NOBANK?");
        super.checkRun();
        if (!ctx.players.local().inMotion() || ctx.movement.destination().equals(Tile.NIL) || ctx.movement.destination().distanceTo(ctx.players.local()) < 5) {
            if (ctx.inventory.select().id(super.getLogsId()[LOGS_ID]).count() > 0) {
                walker.walkPathReverse(pathToBank);
            }
            else {
                walker.walkPath(pathToBank);

            }
        }
    }
}



