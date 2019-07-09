package scripts;

import org.powerbot.script.PollingScript;
import org.powerbot.script.Script;
import org.powerbot.script.rt4.ClientContext;
import scripts.Tasks.*;


import java.util.ArrayList;
import java.util.List;

@Script.Manifest(name="Woodcutter", description="Woodcut", properties="author=Justin; topic=999; client=4;")

public class Woodcutter extends PollingScript<ClientContext> {

    final static int FOOD_ID = 351;
    List<Task> taskList = new ArrayList<Task>();

    @Override
    public void start() {
        //taskList.add(new DropLogs(ctx));
        taskList.add(new WalkToDraynorBank(ctx));
        taskList.add(new BankLogsDraynor(ctx));
        taskList.add(new CutTree(ctx));
    }

    @Override
    public void poll() {
        System.out.println("polling");
        for (Task task : taskList) {
            if (task.activate()) {
                task.execute();
                break;
            }
        }
    }


}