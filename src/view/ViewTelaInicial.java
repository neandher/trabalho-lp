package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import javax.swing.JDesktopPane;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Component;

public class ViewTelaInicial {

	private JFrame frmSistemaFinanceiroPessoal;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
				
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            System.err.println(ex);
        }
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewTelaInicial window = new ViewTelaInicial();
					window.frmSistemaFinanceiroPessoal.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ViewTelaInicial() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmSistemaFinanceiroPessoal = new JFrame();
		frmSistemaFinanceiroPessoal.setTitle("Sistema Financeiro Pessoal");
		frmSistemaFinanceiroPessoal.setBounds(100, 100, 450, 300);
		frmSistemaFinanceiroPessoal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmSistemaFinanceiroPessoal.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frmSistemaFinanceiroPessoal.setLocationRelativeTo(null);
		
		JDesktopPane desktopPane = new JDesktopPane();		
		frmSistemaFinanceiroPessoal.getContentPane().add(desktopPane, BorderLayout.CENTER);
		frmSistemaFinanceiroPessoal.setVisible(true);		
		
		JMenuBar menuBar = new JMenuBar();
		frmSistemaFinanceiroPessoal.setJMenuBar(menuBar);
		
		JMenu menuConta = new JMenu("Contas");
		menuBar.add(menuConta);
		
		JMenuItem menuContaCadastrar = new JMenuItem("Cadastrar Conta");
		menuConta.add(menuContaCadastrar);
		
		JMenu menuPlanoConta = new JMenu("Plano de Contas");
		menuBar.add(menuPlanoConta);
		
		JMenuItem menuPlanoContaCadastrar = new JMenuItem("Cadastrar Plano de Conta");
		menuPlanoContaCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ViewPlanoContaCadastrar vpc = new ViewPlanoContaCadastrar();
				desktopPane.add(vpc);
				vpc.setVisible(true);				
			}
		});
		menuPlanoConta.add(menuPlanoContaCadastrar);
		
		JMenu menuMetodoPagamento = new JMenu("M\u00E9todos de Pagamento");
		menuBar.add(menuMetodoPagamento);
		
		JMenuItem menuMetodoPagamentoCadastrar = new JMenuItem("Cadastrar Novo M\u00E9todo");
		menuMetodoPagamento.add(menuMetodoPagamentoCadastrar);
		
		
	}

}
