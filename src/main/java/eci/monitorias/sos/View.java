package eci.monitorias.sos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import socket.Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@SpringBootApplication
public class ViewUser  extends JFrame {


		private JLabel tittle;
		private JPanel center;
		private JPanel up;
		private JPanel bot;
		private JTextArea description;
		private JButton send;
		private JScrollPane scroll;
		private Client client;

		public ViewUser() {
			client = new Client(this);
			setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
			setLayout(new BorderLayout());
			setTitle("Consulta");
			initComponents();
		}

		private void initComponents() {
			upComponents();
			centerComponents();
			botComponents();
			pack();
		}

		private void upComponents() {
			up = new JPanel(new FlowLayout());
			tittle = new JLabel("Describa aquí su problema");
			up.add(tittle);
			add(up, BorderLayout.NORTH);
		}

		private void centerComponents() {
			center = new JPanel(new FlowLayout());
			description = new JTextArea(10, 40);
			scroll = new JScrollPane(description);
			center.add(scroll);
			add(center, BorderLayout.CENTER);
		}

		private void botComponents() {
			bot = new JPanel(new FlowLayout());
			send = new JButton("Enviar");
			send.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					if(description.getText().length()==0){
						JOptionPane.showMessageDialog(null, "La descripción no puede estar vacia");
						return;
					}
					try {
						client.sendRequest();
						JOptionPane.showMessageDialog(null, "Su solicitud fue enviada");
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(null, "La conexion falló");
						// e1.printStackTrace();
					}
				}
			});




			bot.add(send);
			add(bot, BorderLayout.SOUTH);
		}


		public String getDescription(){
			System.out.println(description.getText().toString().replace('\n', ' '));
			return description.getText().toString().replace('\n', ' ');
		}




	public static void main(String[] args) {
		SpringApplication.run(ViewUser.class, args);
	}



}
