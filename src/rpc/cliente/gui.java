package rpc.cliente;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.client.XmlRpcClientException;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.net.ConnectException;
import java.util.Map;
import java.awt.event.ActionEvent;

public class gui extends JFrame {

	private JPanel contentPane;
	private JTextField tfCPF;
	private ClienteRPC cliente;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					gui frame = new gui();
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
	public gui() {
		setTitle("CLIENTE RPC");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 335, 141);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),},
			new RowSpec[] {
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,}));
		
		JLabel lblNewLabel = new JLabel("CPF");
		contentPane.add(lblNewLabel, "2, 2, right, default");
		
		tfCPF = new JTextField();
		contentPane.add(tfCPF, "4, 2, fill, default");
		tfCPF.setColumns(10);
		
		JLabel lblStatus = new JLabel("");
		contentPane.add(lblStatus, "1, 4, 4, 1, center, center");
		
		JButton btnOK = new JButton("VALIDAR");
		
		
			cliente = new ClienteRPC();


		
		btnOK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					lblStatus.setText("");
					Map<String, Object> resultado = cliente.validarCpf(tfCPF.getText());
					
					String status = "O CPF " + (String) resultado.get("Cpf") + ((boolean) resultado.get("Valid") ? " é valido" : " é invalido") + "!";
					
					lblStatus.setForeground((boolean) resultado.get("Valid") ? Color.BLACK: Color.RED);
					lblStatus.setText(status);
				
				} catch(XmlRpcException e1) {
					lblStatus.setForeground(Color.RED);
					lblStatus.setText("Erro ao conectar com servidor. Tente novamente!");							
				} catch (Exception e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				
			}
		});
		contentPane.add(btnOK, "1, 6, 4, 1");
	}

}
