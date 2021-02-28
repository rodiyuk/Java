import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class View {
    public static JFrame frame = new JFrame("Search");
    private static JButton open = new JButton("Open");
    private static JButton save = new JButton("Save");
    private static JTextField source = new JTextField(10);
    private static JTextField dest = new JTextField(10);
    public static JTextArea textArea = new JTextArea(20, 30);
    public static List<JTextField> list = new ArrayList<JTextField>();

    public static void initView() throws IOException {
        frame.setSize(400, 600);
        JButton search = new JButton("Поиск");
        JButton reset = new JButton("Сброс");

        JPanel panel = new JPanel(new GridLayout(2, 3, 10, 10));
        panel.add(new JLabel("Где искать"));
        panel.add(source);
        panel.add(open);
        panel.add(new JLabel("Куда сохранить"));
        panel.add(dest);
        panel.add(save);

        frame.getContentPane().add(BorderLayout.NORTH, panel);

        JPanel panelK = new JPanel(new GridLayout(9, 3, 10, 10));
        JTextField fieldMin;
        JTextField fieldMax;
        panelK.add(new JLabel());
        panelK.add(new JLabel());
        panelK.add(new JLabel());
        panelK.add(new JLabel("Коэффициенты"));
        panelK.add(new JLabel("Минимум"));
        panelK.add(new JLabel("Максимум"));
        for (int i = 1; i < 8; i++) {
            fieldMin = new JTextField(10);
            fieldMax = new JTextField(10);
            panelK.add(new JLabel("K" + i));
            if (i == 1) {
                fieldMin.setText("0.93");
                fieldMax.setText("1.07");
            } else if (i == 2){
                fieldMin.setText("0.94");
                fieldMax.setText("1.06");
            } else {
                fieldMin.setText("0.945");
                fieldMax.setText("1.055");
            }
            panelK.add(fieldMin);
            panelK.add(fieldMax);
            list.add(fieldMin);
            list.add(fieldMax);
        }
        JPanel flow = new JPanel(new FlowLayout(FlowLayout.LEFT));
        flow.add(panelK);
        frame.getContentPane().add(flow, BorderLayout.CENTER);

        frame.add(new JScrollPane(textArea), BorderLayout.EAST);

        JPanel panel2 = new JPanel();
        panel2.add(search);
        panel2.add(reset);
        frame.getContentPane().add(BorderLayout.SOUTH, panel2);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.pack();

        search.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Path path = Path.of(source.getText());
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
                JFileChooser fileOpen = new JFileChooser();
                fileOpen.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                fileOpen.setCurrentDirectory(new File("D:\\"));
                int ret = fileOpen.showOpenDialog(null);
                if (ret == JFileChooser.APPROVE_OPTION) {
                    file = fileOpen.getSelectedFile();
                    source.setText(file.getAbsolutePath());
                }
            }
        });

        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                File file = null;
                JFileChooser fileSave = new JFileChooser();
                fileSave.setCurrentDirectory(new File("D:\\"));
                int ret = fileSave.showSaveDialog(null);
                if (ret == JFileChooser.APPROVE_OPTION) {
                    file = fileSave.getSelectedFile();
                    dest.setText(file.getAbsolutePath());
                }
            }
        });
        reset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                reset();
            }
        });
    }

    private static void reset() {
        textArea.setText("");
        source.setText("");
        dest.setText("");
        for (JTextField field : list) {
            field.setText("");
        }
    }

    public static void main(String[] args) throws IOException {
        View.initView();
    }
}
