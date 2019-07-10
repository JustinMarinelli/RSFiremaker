package scripts;
import org.powerbot.script.Tile;

import org.powerbot.script.Condition;
import org.powerbot.script.Script;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.*;

import java.util.concurrent.Callable;

@Script.Manifest(name="NPC Killer", description="Kill NPCs from exp", properties="author=Justin; topic=999; client=4;")

public abstract class Task extends ClientAccessor {

    public static int LOGS_ID[] = {1511, 1521, 1519, 1517};
    public static final Tile fireSpot1 = new Tile(3201, 3430, 0);
    public static final Tile fireSpot2 = new Tile(3201, 3429, 0);
    public Task(ClientContext ctx) {
        super(ctx);
    }

    public abstract boolean activate();
    public abstract void execute();
    int inventoriesComplete = 0;
    int logsBurned = 0;


    public void checkRun(){
        if (ctx.movement.energyLevel() > 40 && !ctx.movement.running()) {
            ctx.movement.running(true);
        }
    }

    public int[] getLogsId() {
        return LOGS_ID;
    }

    public Tile getFireSpot() {
        if (inventoriesComplete % 2 == 0) {
            return fireSpot1;
        }
        else {
            return fireSpot2;
        }

    }

    public void incrementInventories() {
        inventoriesComplete += 1;
    }

    public int getInventoriesComplete() {
        return inventoriesComplete;
    }

    public void burnLogs() {
        logsBurned += 1;
    }

    public int getLogsBurned() {
        return logsBurned;
    }
}