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


public class Walk extends Task {
    public static final Tile[] pathToGiants = {new Tile(3184, 3438, 0), new Tile(3183, 3435, 0), new Tile(3182, 3432, 0), new Tile(3179, 3432, 0), new Tile(3176, 3432, 0), new Tile(3173, 3432, 0), new Tile(3170, 3429, 0), new Tile(3167, 3428, 0), new Tile(3164, 3426, 0), new Tile(3161, 3426, 0), new Tile(3158, 3426, 0), new Tile(3155, 3426, 0), new Tile(3152, 3426, 0), new Tile(3149, 3426, 0), new Tile(3146, 3428, 0), new Tile(3143, 3429, 0), new Tile(3140, 3429, 0), new Tile(3137, 3431, 0), new Tile(3134, 3431, 0), new Tile(3131, 3431, 0), new Tile(3128, 3432, 0), new Tile(3125, 3433, 0), new Tile(3122, 3435, 0), new Tile(3120, 3438, 0), new Tile(3118, 3441, 0), new Tile(3116, 3444, 0), new Tile(3114, 3447, 0), new Tile(3115, 3450, 0), new Tile(3116, 9851, 0), new Tile(3113, 9848, 0), new Tile(3112, 9845, 0), new Tile(3109, 9842, 0), new Tile(3106, 9839, 0), new Tile(3103, 9838, 0), new Tile(3100, 9837, 0)};
    private final Walker walker = new Walker(ctx);
    public Tile safeSpot = new Tile(3098, 9837, 0);
    final static int BIG_BONES_ID = 532;


    public Walk(ClientContext ctx) {
        super(ctx);
    }

    public boolean correctLocation(){
        if (ctx.players.local().tile().x() == safeSpot.x() &&
                ctx.players.local().tile().y() == safeSpot.y()) {
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public boolean activate() {
        return (ctx.inventory.select().count() > 27 ||
                (ctx.inventory.select().id(BIG_BONES_ID).count() <= 0)
                && ctx.players.local().tile().distanceTo(safeSpot) > 5);
    }

    @Override
    public void execute() {
        super.checkRun();
        if (!ctx.players.local().inMotion() || ctx.movement.destination().equals(Tile.NIL) || ctx.movement.destination().distanceTo(ctx.players.local()) < 5) {
            if (ctx.inventory.isFull()) {
                walker.walkPathReverse(pathToGiants);
            }
            else {
                walker.walkPath(pathToGiants);
            }
        }
    }
}



