package scripts;

import org.powerbot.script.PollingScript;
import org.powerbot.script.Script;
import org.powerbot.script.rt4.ClientContext;
import scripts.Task;
import scripts.Tasks.*;


import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

@Script.Manifest(name="Firemaker", description="Train firemaking", properties="author=Justin; topic=999; client=4;")

public class Firemaker extends PollingScript<ClientContext> {

    List<Task> taskList = new ArrayList<Task>();


    @Override
    public void start() {
        String userOptions[] = {"Normal logs", "Oak logs", "Willow logs", "Maple logs"};
        String userChoice = ""+(String) JOptionPane.showInputDialog(null, "Select log type: ", "Choose logs", JOptionPane.PLAIN_MESSAGE, null, userOptions, userOptions[0]);

        int logIndex = 0;
        if (userChoice.equals("Oak logs")) {
            logIndex = 1;
        }
        else if (userChoice.equals("Willow logs")) {
            logIndex = 2;
        }
        else if (userChoice.equals("Maple logs")) {
            logIndex = 3;
        }

        taskList.add(new BurnLogs(ctx, logIndex));
        taskList.add(new GoToBank(ctx, logIndex));
        taskList.add(new WithdrawLogs(ctx, logIndex));
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