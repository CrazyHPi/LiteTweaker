package xyz.crazyh.litetweaker.util;

import net.java.games.input.Controller;
import net.java.games.input.DirectAndRawInputEnvironmentPlugin;
import net.java.games.input.Mouse;
import net.minecraft.client.Minecraft;
import xyz.crazyh.litetweaker.LiteModLiteTweaker;

import java.util.ArrayList;
import java.util.List;

public class RawInput {
    public static Minecraft minecraft = Minecraft.getMinecraft();
    public static Controller[] controllers;
    public static List<Controller> mouses = new ArrayList<>();

    public static Mouse mouse;
    public static int pollingInterval = 1;
    public static int dx = 0;
    public static int dy = 0;


    public static void init() {
        startTickMouse();
    }

    public static void startTickMouse() {
        Thread mouseTickThread = new Thread(() -> {
            while (true) {
                if (mouse != null && minecraft.currentScreen == null) {
                    mouse.poll();
                    dx += (int) mouse.getX().getPollData();
                    dy += (int) mouse.getY().getPollData();
                } else if (mouse != null) {
                    mouse.poll();
                }

                try {
                    Thread.sleep(pollingInterval);
                } catch (InterruptedException e) {
                    LiteModLiteTweaker.LOGGER.error(e.getStackTrace());
                }
            }
        });

        mouseTickThread.setName("mouseTickThread");
        mouseTickThread.start();
    }

    public static void getMouse() {
        Thread getMouseThread = new Thread(() -> {
            DirectAndRawInputEnvironmentPlugin directEnv = new DirectAndRawInputEnvironmentPlugin();
            controllers = directEnv.getControllers();
            mouse = null;
            mouses.clear();

            for (Controller c : controllers) {
                if (c.getType() == Controller.Type.MOUSE) {
                    mouses.add(c);
                }
            }

            while (mouse == null) {
                if (!mouses.isEmpty()) {
                    for (Controller i : mouses) {
                        i.poll();
                        float mouseX = ((Mouse) i).getX().getPollData();

                        if (mouseX > 0.1f || mouseX < -0.1f) {
                            mouse = ((Mouse) i);
                        }
                    }
                }
            }
        });
        getMouseThread.setName("getMouseThread");
        getMouseThread.start();
    }

    // todo: add Action to get mouse when mouse re-connected
}
