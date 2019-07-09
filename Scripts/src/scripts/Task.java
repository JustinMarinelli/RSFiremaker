package scripts;

import org.powerbot.script.Condition;
import org.powerbot.script.Script;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.*;

import java.util.concurrent.Callable;

@Script.Manifest(name="NPC Killer", description="Kill NPCs from exp", properties="author=Justin; topic=999; client=4;")

public abstract class Task extends ClientAccessor {

    final static int FOOD_ID = 361;
    final static int AMOUNT_HEALED = 10;
    final static int BRASS_KEY_ID = 983;
    final static int ARROW_ID = 884;



    public Task(ClientContext ctx) {
        super(ctx);
    }

    public abstract boolean activate();
    public abstract void execute();

    public boolean hasFood() {
        return ctx.inventory.select().id(FOOD_ID).count() > 0;
    }

    public int getBrassKeyId() {
        return BRASS_KEY_ID;
    }

    public int getFoodID() {
        return FOOD_ID;
    }
    public int getArrowId() {
        return ARROW_ID;
    }


    public int getFoodHealingAmount() {
        return AMOUNT_HEALED;
    }


    public void heal (){
        Item foodToEat = ctx.inventory.select().id(FOOD_ID).poll();

        foodToEat.interact("Eat");

        final int startingHealth = ctx.combat.health();

        Condition.wait(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                final int currentHealth = ctx.combat.health();
                return currentHealth != startingHealth;
            }
        },150, 20);

    }

    public void checkRun(){
        if (ctx.movement.energyLevel() > 40 && !ctx.movement.running()) {
            ctx.movement.running(true);
        }
    }

    public boolean needsHeal() {
        return ctx.combat.health() < 19;
    }

}