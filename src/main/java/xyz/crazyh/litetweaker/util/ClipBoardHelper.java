package xyz.crazyh.litetweaker.util;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;

public class ClipBoardHelper {
    public static String getClipBoardString() {
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();

        Transferable tran = clipboard.getContents(null);

        if (tran != null) {
            if (tran.isDataFlavorSupported(DataFlavor.stringFlavor)) {
                try {
                    return (String) tran.getTransferData(DataFlavor.stringFlavor);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        return null;
    }
}
