package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class FinishDialog extends JDialog {


    public FinishDialog(Frame owner,  boolean modal, String labelTitle,ActionListener onRestart,ActionListener onClose) {
        super(owner, "Играта приключи", modal);
        super.setLayout(new FlowLayout());
        setLabel(labelTitle);
        setRestartB(onRestart);
        setCloseB(onClose);

    }

    /**
     * set label of the dialog
     * @param title title of the dialog
     */
    private void setLabel(String title) {
        JLabel label = new JLabel(title);
        super.add(label);
    }

    /**
     * set restart btn
     * @param actionListener action
     */
    private void setRestartB(ActionListener actionListener) {
        JButton restartB = new JButton("Рестартирай играта");
        restartB.addActionListener(actionListener);
        super.add(restartB);
    }

    /**
     * set close btn
     * @param actionListener action
     */
    private void setCloseB(ActionListener actionListener) {
        JButton closeB = new JButton("Затвори играта");
        closeB.addActionListener(actionListener);

        super.add(closeB);
    }

    //visualize the window
    public void visualize(){
        setSize(400, 400);
        setVisible(true);
    }


}
