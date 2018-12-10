package com.youxu.client.swing;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.youxu.client.Client;
import com.youxu.client.swing.constant.ButtonCommand;
import com.youxu.common.ModuleId;
import com.youxu.common.chat.ChatCmd;
import com.youxu.common.chat.request.PrivateChatRequest;
import com.youxu.common.chat.request.PublicChatRequest;
import com.youxu.common.model.Request;
import com.youxu.common.player.PlayerCmd;
import com.youxu.common.player.request.LoginRequest;
import com.youxu.common.player.request.RegisterRequest;
import com.youxu.common.player.response.PlayerResponse;

@Component
public class Swingclient extends JFrame implements ActionListener {
	
	@Autowired
	private Client client;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2572235358190956651L;
	
	/**
	 * �����Ϣ
	 */
	private PlayerResponse playerResponse;
	
	/**
	 * �û���
	 */
	private JTextField playerName;
	
	/**
	 * ����
	 */
	private JTextField passward;
	
	/**
	 * ��¼��ť
	 */
	private JButton loginButton;
	
	
	/**
	 * ע�ᰴť
	 */
	private JButton register;

	/**
	 * ��������
	 */
	private JTextArea chatContext;
	
	/**
	 * ��������
	 */
	private JTextField message;
	
	/**
	 * Ŀ���û�
	 */
	private JTextField targetPlayer;
	
	/**
	 * ���Ͱ�ť
	 */
	private JButton sendButton;

	/**
	 * ������ʾ
	 */
	private JLabel tips;

	

	public Swingclient() {
		
		getContentPane().setLayout(null);
		
		//��¼����
		JLabel lblIp = new JLabel("��ɫ��");
		lblIp.setFont(new Font("����", Font.PLAIN, 12));
		lblIp.setBounds(76, 40, 54, 15);
		getContentPane().add(lblIp);
		
		playerName = new JTextField();
		playerName.setBounds(139, 37, 154, 21);
		getContentPane().add(playerName);
		playerName.setColumns(10);
		
		JLabel label = new JLabel("��  ��");
		label.setFont(new Font("����", Font.PLAIN, 12));
		label.setBounds(76, 71, 54, 15);
		getContentPane().add(label);
		
		passward = new JTextField();
		passward.setColumns(10);
		passward.setBounds(139, 68, 154, 21);
		getContentPane().add(passward);
		
		//��¼
		loginButton = new JButton("��¼");
		loginButton.setFont(new Font("����", Font.PLAIN, 12));
		loginButton.setActionCommand(ButtonCommand.LOGIN);
		loginButton.addActionListener(this);
		loginButton.setBounds(315, 37, 93, 23);
		getContentPane().add(loginButton);
		
		//ע��
		register = new JButton("ע��");
		register.setFont(new Font("����", Font.PLAIN, 12));
		register.setActionCommand(ButtonCommand.REGISTER);
		register.addActionListener(this);
		register.setBounds(315, 67, 93, 23);
		getContentPane().add(register);
		
		//�������ݿ�
		chatContext = new JTextArea();
		chatContext.setLineWrap(true);
		
		JScrollPane scrollBar = new JScrollPane(chatContext);
		scrollBar.setBounds(76, 96, 93, 403);
		scrollBar.setSize(336, 300);
		getContentPane().add(scrollBar);

		
		//���Ͳ���
		JLabel label_7 = new JLabel("��Ϣ");
		label_7.setFont(new Font("����", Font.PLAIN, 12));
		label_7.setBounds(76, 411, 54, 15);
		getContentPane().add(label_7);
		
		message = new JTextField();
		message.setBounds(139, 408, 222, 21);
		getContentPane().add(message);
		message.setColumns(10);
		
		JLabel lblid = new JLabel("��ɫ");
		lblid.setFont(new Font("����", Font.PLAIN, 12));
		lblid.setBounds(76, 436, 43, 24);
		getContentPane().add(lblid);

		targetPlayer = new JTextField();
		targetPlayer.setBounds(139, 438, 133, 21);
		getContentPane().add(targetPlayer);
		targetPlayer.setColumns(10);
		
		sendButton = new JButton("����");
		sendButton.setFont(new Font("����", Font.PLAIN, 12));
		sendButton.setBounds(382, 407, 67, 23);
		sendButton.setActionCommand(ButtonCommand.SEND);
		sendButton.addActionListener(this);
		getContentPane().add(sendButton);
		
		//������ʾ����
		tips = new JLabel();
		tips.setForeground(Color.red);
		tips.setFont(new Font("����", Font.PLAIN, 14));
		tips.setBounds(76, 488, 200, 15);
		getContentPane().add(tips);
		

		int weigh = 500;
		int heigh = 600;
		int w = (Toolkit.getDefaultToolkit().getScreenSize().width - weigh) / 2;
		int h = (Toolkit.getDefaultToolkit().getScreenSize().height - heigh) / 2;
		this.setLocation(w, h);
		this.setTitle("���칤��");
		this.setSize(weigh, heigh);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(true);
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		
		switch (event.getActionCommand()) {
		//��¼
		case ButtonCommand.LOGIN:
			try {
				LoginRequest loginRequest = new LoginRequest();
				loginRequest.setPlayerName(playerName.getText());
				loginRequest.setPassward(passward.getText());
				
				//��������
				Request request = Request.valueOf(ModuleId.PLAYER, PlayerCmd.LOGIN, loginRequest.getBytes());
				client.sendRequest(request);
			} catch (Exception e) {
				tips.setText("�޷����ӷ�����");
			}
			break;
		//ע��
		case ButtonCommand.REGISTER:
			try {
				RegisterRequest registerRequest = new RegisterRequest();
				registerRequest.setPlayerName(playerName.getText());
				registerRequest.setPassward(passward.getText());
				
				//��������
				Request request = Request.valueOf(ModuleId.PLAYER, PlayerCmd.REGISTER_AND_LOGIN, registerRequest.getBytes());
				client.sendRequest(request);
			} catch (Exception e) {
				tips.setText("�޷����ӷ�����");
			}
			break;
		//������Ϣ
		case ButtonCommand.SEND:
			try {
				//�ж��ǹ㲥����˽��
				if(StringUtils.isEmpty(targetPlayer.getText()) && !StringUtils.isEmpty(message.getText())){
					PublicChatRequest publicChatRequest = new PublicChatRequest();
					publicChatRequest.setContext(message.getText());
					
					//��������
					Request request = Request.valueOf(ModuleId.CHAT, ChatCmd.PUBLIC_CHAT, publicChatRequest.getBytes());
					client.sendRequest(request);
				}else{
					if(StringUtils.isEmpty(message.getText())){
						tips.setText("�������ݲ���Ϊ��");
						return;
					}
					
					long palyerId = 0; 
					try {
						palyerId = Long.parseLong(targetPlayer.getText());
					} catch (NumberFormatException e) {
						tips.setText("���id����Ϊ����");
						return;
					}
					PrivateChatRequest privateChatRequest = new PrivateChatRequest();
					privateChatRequest.setContext(message.getText());
					privateChatRequest.setTargetPlayerId(palyerId);
					
					//��������
					Request request = Request.valueOf(ModuleId.CHAT, ChatCmd.PRIVATE_CHAT, privateChatRequest.getBytes());
					client.sendRequest(request);
				}
			} catch (Exception e) {
				tips.setText("�޷����ӷ�����");
			}
			break;

		default:
			break;
		}
	}
	
	@Override
	protected void processWindowEvent(WindowEvent e) {
		if (e.getID() == WindowEvent.WINDOW_CLOSING){
			client.shutdown();
		}
		super.processWindowEvent(e);
	}

	public JTextArea getChatContext() {
		return chatContext;
	}

	public JLabel getTips() {
		return tips;
	}

	public void setPlayerResponse(PlayerResponse playerResponse) {
		this.playerResponse = playerResponse;
	}

	public PlayerResponse getPlayerResponse() {
		return playerResponse;
	}
}