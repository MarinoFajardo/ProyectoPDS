/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package paintbasico2d;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

/**
 *
 * @author Marino
 */
public class ColorCellRender implements ListCellRenderer<Color>{
@Override
    public Component getListCellRendererComponent(JList<? extends Color> list,Color value,int index,boolean isSelected,boolean cellHasFocus){
        JButton b = new JButton();
        b.setBackground(value);
        b.setPreferredSize(new Dimension(25,25));
        return b;
    }
}
