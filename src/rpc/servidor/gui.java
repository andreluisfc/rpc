package rpc.servidor;
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
import java.io.IOException;
import java.net.ConnectException;
import java.util.Map;
import java.awt.event.ActionEvent;
import javax.swing.JTable;

public class gui extends JFrame {

	private JPanel contentPane;
	private ServidorRPC servidor;
	private JTable tbLog;

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
		setTitle("SERVIDOR RPC");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 346);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(197dlu;default)"),
				ColumnSpec.decode("max(12dlu;default)"),
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				ColumnSpec.decode("max(33dlu;default):grow"),
				FormSpecs.RELATED_GAP_COLSPEC,},
			new RowSpec[] {
				RowSpec.decode("max(133dlu;default)"),
				RowSpec.decode("max(33dlu;default)"),
				RowSpec.decode("max(14dlu;default):grow"),
				FormSpecs.RELATED_GAP_ROWSPEC,}));
		
		servidor = new ServidorRPC();
		
		
		JLabel lblNewLabel = new JLabel("Status: ");
		contentPane.add(lblNewLabel, "5, 1");
		
		JLabel lblStatus = new JLabel("Parado");
		lblStatus.setForeground(Color.RED);
		contentPane.add(lblStatus, "6, 1");
		
		tbLog = new JTable();
		contentPane.add(tbLog, "2, 1, 1, 2, fill, fill");
		
		JButton btnIniciar = new JButton("INICIAR");
		btnIniciar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!servidor.isRuning()) {
					try {
						servidor.start();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					btnIniciar.setText("PARAR");
					lblStatus.setText("Rodando");
					lblStatus.setForeground(Color.green.darker());					
				} else {
					servidor.stop();
					btnIniciar.setText("INICIAR");
					lblStatus.setText("Parado");
					lblStatus.setForeground(Color.RED);					
				}
				
				
			}
		});
		contentPane.add(btnIniciar, "5, 2, 2, 1");
					
			
	}

}
