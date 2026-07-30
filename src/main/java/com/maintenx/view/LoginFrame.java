package com.maintenx.view;

import com.maintenx.controller.LoginController;
import com.maintenx.util.ErrorHandler;
import com.maintenx.view.components.Ui;
import com.formdev.flatlaf.FlatClientProperties;
import javax.swing.*;
import java.awt.*;

public class LoginFrame extends JFrame {
    public LoginFrame(LoginController controller) {
        super("MaintenX");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(800, 500);
        setLocationRelativeTo(null);
        setResizable(false);
        setBackground(Ui.WHITE);

        var root = new JPanel(new BorderLayout());

        var left = new JPanel(new GridBagLayout());
        left.setBackground(Ui.DARK);
        var gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.anchor = GridBagConstraints.CENTER;

        var brand = new JLabel("MaintenX");
        brand.setFont(Ui.FONT_TITLE.deriveFont(Font.BOLD, 36f));
        brand.setForeground(Ui.WHITE);
        left.add(brand, gbc);

        root.add(left, BorderLayout.CENTER);

        var right = new JPanel(new GridBagLayout());
        right.setBackground(Ui.WHITE);

        var card = new JPanel();
        card.setLayout(new GridBagLayout());
        card.setBackground(Ui.WHITE);
        var cgbc = new GridBagConstraints();
        cgbc.gridwidth = GridBagConstraints.REMAINDER;
        cgbc.fill = GridBagConstraints.HORIZONTAL;
        cgbc.insets = new Insets(0, 40, 0, 40);

        var loginTitle = new JLabel("Connexion");
        loginTitle.setFont(Ui.FONT_TITLE.deriveFont(Font.BOLD, 22f));
        loginTitle.setForeground(Ui.DARK);
        cgbc.insets = new Insets(0, 40, 20, 40);
        card.add(loginTitle, cgbc);

        var username = Ui.field(15);
        username.setText("admin");
        username.putClientProperty(FlatClientProperties.TEXT_FIELD_LEADING_ICON, "people");
        cgbc.insets = new Insets(0, 40, 12, 40);
        card.add(username, cgbc);

        var password = Ui.passwordField(15);
        password.setText("Admin123!");
        password.putClientProperty(FlatClientProperties.TEXT_FIELD_LEADING_ICON, "lock");
        cgbc.insets = new Insets(0, 40, 20, 40);
        card.add(password, cgbc);

        var login = Ui.button("Se connecter");
        login.setPreferredSize(new Dimension(260, 40));
        getRootPane().setDefaultButton(login);
        cgbc.insets = new Insets(0, 40, 0, 40);
        card.add(login, cgbc);

        right.add(card);
        root.add(right, BorderLayout.EAST);

        add(root);

        login.addActionListener(e -> {
            try {
                controller.login(this, username.getText(), new String(password.getPassword()));
            } catch (Exception ex) {
                ErrorHandler.show(this, ex.getMessage(), ex);
            }
        });
    }
}
