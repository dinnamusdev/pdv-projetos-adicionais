/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smack.br.ui;

import br.com.log.LogDinnamus;
import java.awt.Component;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

/**
 *
 * @author DSWM
 */
public class SmackListRenderMensagem extends ItemMensagemUI implements ListCellRenderer<SmackMensagem> {

    private HashMap<String,Icon> imagens;
    public SmackListRenderMensagem(HashMap<String,Icon> imagens) {
        this.imagens=imagens;
    }

        
    @Override
    public Component getListCellRendererComponent(JList<? extends SmackMensagem> list, SmackMensagem value, int index, boolean isSelected, boolean cellHasFocus) {
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

        if (value != null) {
            Mensagem.setText("  " +value.getMsg().getBody());
            if (value.getTipo().equalsIgnoreCase("enviada")) {
                if(imagens.containsKey("enviada")){
                    lblDireita.setIcon(imagens.get("enviada"));
                    
                }else{
                     lblDireita.setIcon(imagens.get("semimagem"));
                   // lblDireita.setIcon(new javax.swing.ImageIcon(getClass().getResource("/smack/ui/icons8-usuário-30.png"))); // NOI18N
                }
                
                lblDireita.setText("");

                Date dtRecebida = value.getDatahoraEnviada();

                if(dtRecebida!=null){
                    
                }
                lblEsquerda.setText( getDataHora(dtRecebida));
                lblEsquerda.setIcon(null);
            } else {
                  if(imagens.containsKey("recebida")){
                    lblEsquerda.setIcon(imagens.get("recebida"));
                  }else{
                    lblEsquerda.setIcon(imagens.get("semimagem")); // NOI18N
                  }
                  lblEsquerda.setText("");
               
                Date dtRecebida = value.getDatahorarecebida();
                
                lblDireita.setText( getDataHora(dtRecebida));
                lblDireita.setIcon(null);
            }
        }

        setOpaque(true);
        return this;

    }

    private String getDataHora(Date d) {
        String ret = null;
        try {

            if (d != null) {
               // String hora = "";
                GregorianCalendar g = new GregorianCalendar();
                g.setTime(d);

                GregorianCalendar Agora = new GregorianCalendar();
                Agora.setTime(new Date());

                int diaAnoMensagem = g.get(GregorianCalendar.DAY_OF_YEAR);
                int diaAnogora = Agora.get(GregorianCalendar.DAY_OF_YEAR);
                int AnoMensagem = g.get(GregorianCalendar.YEAR);
                int Anogora = Agora.get(GregorianCalendar.YEAR);
                if (diaAnoMensagem == diaAnogora && Anogora == AnoMensagem) {
                    ret = new SimpleDateFormat("HH:mm").format(d);
                } else {
                    ret = new SimpleDateFormat("dd/MM/yyyy HH:mm").format(d);
                }

            }
        } catch (Exception e) {
            LogDinnamus.Log(e, true);
        }
        return ret;
    }
}
