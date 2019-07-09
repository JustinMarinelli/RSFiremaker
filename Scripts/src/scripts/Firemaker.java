package scripts;

import org.powerbot.script.PollingScript;
import org.powerbot.script.Script;
import org.powerbot.script.rt4.ClientContext;
import scripts.Task;
import scripts.Tasks.*;


import java.util.ArrayList;
import java.util.List;

@Script.Manifest(name="Firemaker", description="Train firemaking", properties="author=Justin; topic=999; client=4;")

public class Firemaker extends PollingScript<ClientContext> {


    List<Task> taskList = new ArrayList<Task>();

    @Override
    public void start() {
        taskList.add(new BurnLogs(ctx));
        taskList.add(new GoToBank(ctx));
        taskList.add(new WithdrawLogs(ctx));
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