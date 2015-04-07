package View;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Toolkit;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class HelpView {
    
    public static void addItem(JPanel p, JComponent c, int x, int y, int width, int height, int align) {
        GridBagConstraints gc = new GridBagConstraints();
        gc.gridx = x;
        gc.gridy = y;
        gc.gridwidth = width;
        gc.gridheight = height;
        gc.insets = new Insets(5, 5, 5, 5);
        gc.anchor = align;
        gc.fill = GridBagConstraints.BOTH;
        p.add(c, gc);
    }
    
    public static void addItem(JPanel p, JComponent c, int x, int y, int width, int height, double weightx,double weighty, int align) {
        GridBagConstraints gc = new GridBagConstraints();
        gc.gridx = x;
        gc.gridy = y;
        gc.gridwidth = width;
        gc.gridheight = height;
        gc.weightx = weightx;
        gc.weighty = weighty;
        gc.insets = new Insets(5, 5, 5, 5);
        gc.anchor = align;
        gc.fill = GridBagConstraints.BOTH;
        p.add(c, gc);
    }
    
    public static Dimension getDimensoes() {

        Toolkit toolkit = Toolkit.getDefaultToolkit();
        return toolkit.getScreenSize();

    }
    
    public static void configurarFrame(JFrame janela) {
        janela.setSize(1000,500);
        janela.setLocation(
            (getDimensoes().width / 2) - (janela.getWidth() / 2),
            (getDimensoes().height / 2) - (janela.getHeight() / 2)
        );
        
        janela.setLocationByPlatform(true);
        janela.setVisible(true);
    }
    
    public static Dimension getResolucao(String resolucao, float porcentagemLargura) {
        
        int largura = (int)Math.round(getDimensoes().width * (porcentagemLargura / 100.0f));
        int altura;
        
        if (largura < 600) {
            largura = 600;
        }
        
        String[] strProporcoes = resolucao.split(":", 2);

        int proporcaoLargura = Integer.parseInt(strProporcoes[0]);
        int proporcaoAltura = Integer.parseInt(strProporcoes[1]);
        
        altura = (int)Math.round((largura/proporcaoLargura) * proporcaoAltura);
        
        return new Dimension(largura, altura);
    }
}
