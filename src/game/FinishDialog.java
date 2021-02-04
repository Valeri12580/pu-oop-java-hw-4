package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class FinishDialog extends JDialog {


    public FinishDialog(Frame owner, String title, boolean modal, ActionListener onRestart,ActionListener onClose) {
        super(owner, title, modal);
        super.setLayout(new FlowLayout());

        setLabel();
        setRestartB(onRestart);
        setCloseB(onClose);

        setSize(400, 400);
        setVisible(true);

    }

    private void setLabel() {
        JLabel label = new JLabel("Ти победи!");
        super.add(label);
    }

    private void setRestartB(ActionListener actionListener) {
        JButton restartB = new JButton("Рестартирай играта");
        restartB.addActionListener(actionListener);
        super.add(restartB);
    }

    private void setCloseB(ActionListener actionListener) {
        JButton closeB = new JButton("Затвори играта");
        closeB.addActionListener(actionListener);

        super.add(closeB);
    }


}
