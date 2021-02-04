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

    private void setLabel(String title) {
        JLabel label = new JLabel(title);
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

    public void visualize(){
        setSize(400, 400);
        setVisible(true);
    }


}
