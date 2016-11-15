/*
 * 
 * 
 *    NO TERMINADO
 *    TO DO
 *    AÑADIR: GUARDAR, ABRIR, COLORES DE SINTAXIS PARA HTML,CSS,PHP,ETC..., BUSCADOR DE PALABRAS....
 * 
 * 
 * 
 */

package com.github.night.notepad;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import javax.swing.BorderFactory;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

public class Main extends JFrame implements ActionListener {
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	int panel_width = (int) screenSize.getWidth();
	int panel_height = (int) screenSize.getHeight();
	private JTextArea JTA_frame;
	private JMenuBar menubar;
	private JMenu save;
	private JMenu load;

	public Main() {

		setTitle("Nightmode Notepad");
		setLocationRelativeTo(null);
		setLayout(new BorderLayout());
		setJMenuBar(menubar);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(500, 500);

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int panel_width = (int) screenSize.getWidth();
		int panel_height = (int) screenSize.getHeight();

		components();
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException ex) {
		} catch (InstantiationException ex) {
		} catch (IllegalAccessException ex) {
		} catch (UnsupportedLookAndFeelException ex) {
		}

		UIManager.put("MenuBar.background", Color.RED);
		UIManager.put("Menu.background", Color.GREEN);
		UIManager.put("MenuItem.background", Color.MAGENTA);
	}

	private void components() {

		UIManager.put("MenuBar.background", Color.decode("#353535"));
		UIManager.put("MenuBar.opaque", true);
		UIManager.put("Menu.foreground", Color.WHITE);
		// UIManager.put("Menu.border",
		// BorderFactory.createLineBorder(Color.black, 1));
		UIManager.put("MenuBar.border", BorderFactory.createLineBorder(Color.black, 1 / 2));
		// UIManager.put("MenuItem.border",
		// BorderFactory.createLineBorder(Color.black, 1));

		JTA_frame = new JTextArea("");
		JScrollPane scrollPane = new JScrollPane(JTA_frame);
		JTA_frame.setLineWrap(true);
		JTA_frame.setWrapStyleWord(true);
		scrollPane.setPreferredSize(new Dimension(500, 500));
		JTA_frame.setBounds(0, 0, panel_width, panel_height);

		JTA_frame.setBackground(Color.decode("#282828"));
		JTA_frame.setForeground(Color.decode("#ffffff"));
		JTA_frame.setMargin(new Insets(10, 30, 20, 30));
		JTA_frame.setCaretColor(Color.WHITE);
		JTA_frame.setTabSize(2);
		add(JTA_frame);

		menubar = new JMenuBar();
		setJMenuBar(menubar);
		save = new JMenu("Guardar");
		menubar.add(save);

		load = new JMenu("Abrir");
		menubar.add(load);
		save.addActionListener(this);

		save.addMenuListener(new MenuListener() {

			@Override
			public void menuSelected(MenuEvent e) {
				String ruta = null;

				try {
					FileInputStream fstream = new FileInputStream(ruta);
				} catch (FileNotFoundException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				JFrame parentFrame = new JFrame();

				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setDialogTitle("Especifica lugar de guardado");

				int userSelection = fileChooser.showSaveDialog(parentFrame);

				if (userSelection == JFileChooser.APPROVE_OPTION) {
					File fileToSave = fileChooser.getSelectedFile();
					System.out.println("Guardar como: " + fileToSave.getAbsolutePath());
					ruta = fileToSave.getAbsolutePath();
				}

				String texttosave = JTA_frame.getText();
				try (PrintWriter out = new PrintWriter(ruta)) {
					out.println(JTA_frame.getText());
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}

			@Override
			public void menuDeselected(MenuEvent e) {
			}

			@Override
			public void menuCanceled(MenuEvent e) {
			}
		});
	}

	public static void main(String[] main) {
		Main ventana = new Main();
		ventana.setVisible(true);

	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == save) {
			System.out.println("pasa");
		}
	}

}
