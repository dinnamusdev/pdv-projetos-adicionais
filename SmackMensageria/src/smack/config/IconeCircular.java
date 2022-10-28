/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smack.config;

import br.com.log.LogDinnamus;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;

/**
 *
 * @author DSWM
 */
public class IconeCircular {
      public  ImageIcon ArredondarIconeByte(byte[] resource,int width, int height, int tamanhoCirculo ){
         ImageIcon imageIcon =null;
        try {
             ///source = "/smack/ui/icons8-mao-no-rosto-30.png"
             imageIcon = new ImageIcon(new ImageIcon(resource).getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT));
                      
            // imageIcon = new ImageIcon( imageIcon.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT));
             
             imageIcon = ArredondarImagem(imageIcon, tamanhoCirculo);
        } catch (Exception e) {
            LogDinnamus.Log(e, true);
            return null;
        }
        return imageIcon;
       
    }
    public  ImageIcon ArredondarIcone(String resource,int width, int height, int tamanhoCirculo ){
         ImageIcon imageIcon =null;
        try {
             ///source = "/smack/ui/icons8-mao-no-rosto-30.png"
              imageIcon = new javax.swing.ImageIcon(getClass().getResource(resource));
             imageIcon = new ImageIcon( imageIcon.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT));
             imageIcon = ArredondarImagem(imageIcon, tamanhoCirculo);
        } catch (Exception e) {
            LogDinnamus.Log(e, true);
            return null;
        }
        return imageIcon;
       
    }
    public  ImageIcon ArredondarImagem(ImageIcon imageIcon, int r){
      ImageIcon imoutput=null;
        try {
             BufferedImage image = new BufferedImage(
                imageIcon.getIconWidth(),
                imageIcon.getIconHeight(),
                BufferedImage.TYPE_INT_RGB);
        Graphics g = image.createGraphics();
 
        imageIcon.paintIcon(null, g, 0, 0);
        g.dispose();

        
        BufferedImage output = new BufferedImage(r, r, 2);
        Graphics2D g2 = output.createGraphics();
        g2.setComposite(AlphaComposite.Src);
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(Color.WHITE);
        g2.fill(new java.awt.geom.Ellipse2D.Double(0.0D, 0.0D, r, r));
        g2.setComposite(AlphaComposite.SrcAtop);
        g2.drawImage(image.getScaledInstance(r, r, 4), 0, 0, null);
        g2.dispose();
        imoutput = new ImageIcon(output);
        } catch (Exception e) {
            LogDinnamus.Log(e, true);
        }
       
        return imoutput;
    }
}
