package test;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class myActionLIstener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("按钮被点击了");
    }
}
