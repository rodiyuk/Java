import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

public class View {
    public static JFrame frame = new JFrame("Search");
    private static JButton open = new JButton("Open");
    private static JTextField source = new JTextField(20);
    private static JTextField dest = new JTextField(20);
    public static JTextArea textArea = new JTextArea(30,30);

    public static void initView() throws IOException {
        frame.setSize(850,600);
        JButton search = new JButton("Поиск");

        JPanel panel = new JPanel();
        panel.add(new JLabel("Где искать"));
        panel.add(source);
        panel.add(open);
        panel.add(new JLabel("Куда сохранить результат"));
        panel.add(dest);
        frame.getContentPane().add(BorderLayout.NORTH, panel);

        JPanel panel2 = new JPanel();
        panel2.add(textArea);

        frame.getContentPane().add(new JScrollPane(textArea),BorderLayout.CENTER);
        frame.getContentPane().add(BorderLayout.AFTER_LAST_LINE, search);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.pack();

        search.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Path path = Path.of(open.getText());
                try {
                    Main.isDirectory(path, dest.getText());
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });

        open.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                File file = null;
                JFileChooser fileopen = new JFileChooser();
                fileopen.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                int ret = fileopen.showDialog(null, "Открыть...");
                if (ret == JFileChooser.APPROVE_OPTION) {
                    file = fileopen.getSelectedFile();
                    source.setText(file.getAbsolutePath());
                }
            }
        });
    }
    public static void main(String[] args) throws IOException {
        View.initView();
    }
}
