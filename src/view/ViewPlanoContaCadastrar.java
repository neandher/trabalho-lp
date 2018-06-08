package view;

import java.awt.EventQueue;

import javax.swing.DefaultListModel;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Point;

public class ViewPlanoContaCadastrar extends JInternalFrame {
	private JTextField descricao;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewPlanoContaCadastrar frame = new ViewPlanoContaCadastrar();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ViewPlanoContaCadastrar() {
		setClosable(true);
		setTitle("Plano de Contas - Cadastrar");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
		
		JLabel lblDescrio = new JLabel("Descri\u00E7\u00E3o");
		lblDescrio.setBounds(91, 39, 70, 16);
		getContentPane().add(lblDescrio);
		
		JLabel lblNewLabel = new JLabel("Tipo Plano Conta");
		lblNewLabel.setBounds(91, 108, 104, 16);
		getContentPane().add(lblNewLabel);
		
		descricao = new JTextField();
		descricao.setBounds(91, 68, 268, 28);
		getContentPane().add(descricao);
		descricao.setColumns(10);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Item 1", "Item 2"}));
		comboBox.setBounds(91, 136, 268, 26);
		getContentPane().add(comboBox);
		
		JButton btnNewButton = new JButton("Cadastrar");
		btnNewButton.setBounds(125, 194, 90, 28);
		getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Cancelar");
		btnNewButton_1.setBounds(227, 194, 90, 28);
		getContentPane().add(btnNewButton_1);
		
			
		
	}
}
