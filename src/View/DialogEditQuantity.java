package View;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class DialogEditQuantity implements ActionListener{
	
	private JDialog edit;
	 JButton submit;
	 JButton cancel;
	JTextField txtsoLuong;
	public DialogEditQuantity()
	{
	    edit = new JDialog();
		edit.setTitle("Chỉnh sửa số lượng");
		edit.setSize(500, 200);
		edit.setLocationRelativeTo(null);
		edit.setVisible(true);
		edit.setLayout(null);
		JLabel title = new JLabel("Số lượng:");
		title.setBounds(50, 38, 70, 40);
		 txtsoLuong = new JTextField();
		txtsoLuong.setBounds(150, 38, 280, 40);
		edit.add(title);
		edit.add(txtsoLuong);
		 cancel = new JButton("Hủy");
		cancel.setBounds(120, 100, 70, 40);
		edit.add(cancel);
		cancel.setBackground(Color.RED);
		 submit = new JButton("Lưu");
		submit.setBounds(320, 100, 70, 40);
		submit.setBackground(Color.CYAN);
		edit.add(submit);
		
		
		submit.addActionListener(this);
		cancel.addActionListener(this);
		
		edit.setLocationRelativeTo(null);
		edit.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton button = (JButton) e.getSource();
		if (button.equals(submit))
		{
			submit();
		}else {
			cancel();
		}
	}
	public void submit()
	{
		
	}
	public void cancel()
	{
		edit.dispose();
	}

}
