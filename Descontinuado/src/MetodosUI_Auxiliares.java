/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dinnamus.ui.InteracaoUsuario;
import br.com.log.LogDinnamus;
import br.com.ui.ItemLista;
import br.manipulararquivos.ManipulacaoArquivoFiltro;

import dinnamus.ui.infraestrutura.JOptionPanelComEnter;
import dinnamus.ui.metodosauxiliares.SwingUtilidade;
import java.awt.Component;
import java.awt.Dialog.ModalExclusionType;
import java.awt.Dimension;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;

/**
 *
 * @author desenvolvedor
 */
public class MetodosUI_Auxiliares {
    
    private static Dimension _Dimension;
    
    static public JFrame  CentrarFrame(JFrame _JFrame,Toolkit _Toolkit )
    {

        _JFrame.setLocationRelativeTo(null);
       //Dimension=_Toolkit.getScreenSize();
       //_JFrame.setSize( _Dimension.width/2, _Dimension.height/2);
       //_JFrame.setLocation(_Dimension.width/4, _Dimension.height/4);
       
       
     
       return _JFrame;
    }
    static public JFrame  MaximizarFrame(JFrame _JFrame )
    {
       
       _JFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
              
       return _JFrame;
    }
    static public JFrame JFrameModal(JFrame _JFrame)
    {

           _JFrame.setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
           return _JFrame;
    }
    static public void MaximizarJDialog(JDialog jd, Toolkit tk)
    {

        Dimension screenSize = tk.getScreenSize();   
        jd.setSize(screenSize.width, screenSize.height);

        jd.setVisible(true);
    }
 
    static public JDialog  CentrarJDialog(JDialog _JDialog ,Toolkit _Toolkit )
    {
       _Dimension=_Toolkit.getScreenSize();

       return CentrarJDialog(_JDialog,_Toolkit,_Dimension.width/4, _Dimension.height/4);
       
       
    }
    static public JDialog  CentrarJDialog(JDialog _JDialog ,Toolkit _Toolkit, int nWidth, int nHeight)
    {
//       _Dimension=_Toolkit.getScreenSize();
       
       _JDialog.setLocation(nWidth, nHeight);

       
       return _JDialog;
    }
    static private int TipoMensagem(String TipoDeMsg)
    {
        int ntipoMsg=0;
        if(TipoDeMsg.equals("ERROR"))
             ntipoMsg=JOptionPane.ERROR_MESSAGE ;
        else if(TipoDeMsg.equals("AVISO"))
            ntipoMsg=JOptionPane.WARNING_MESSAGE;
        else if(TipoDeMsg.equals("PERGUNTA"))
            ntipoMsg=JOptionPane.QUESTION_MESSAGE;
        else if(TipoDeMsg.equals("INFO"))
            ntipoMsg=JOptionPane.INFORMATION_MESSAGE;

        return ntipoMsg;
    }
    static public void MensagemAoUsuarioSimples(JFrame j, String Msg, String Titulo, String TipoDeMsg )
    {
        int ntipoMsg=TipoMensagem(TipoDeMsg);
        //ERROR_MESSAGE, INFORMATION_MESSAGE, WARNING_MESSAGE, QUESTION_MESSAGE,
        //JOptionPanelComEnter
        
        JOptionPanelComEnter.showMessageDialog(j, Msg, Titulo, ntipoMsg);
        
    }
    static public int MensagemAoUsuarioOpcoes(JFrame j, String Msg, String Titulo, int TipoDeOpcoes,int TipodeMensagem )
    {
        return JOptionPanelComEnter.showConfirmDialog(j, Msg, Titulo, TipoDeOpcoes,TipodeMensagem);
    }
    static public String InputBox(Component c, String cMsg, String cTitulo, String cTipoMSG)
    {
        return JOptionPanelComEnter.showInputDialog(c, cMsg, cTitulo, TipoMensagem(cTipoMSG) );
    }
    static public String InputBox(Component c, String cMsg, String cTitulo, String cTipoMSG, Object[] Opcoes, Object OpcaoPadrao)
    {
        return (String) JOptionPanelComEnter.showInputDialog(c, cMsg, cTitulo, TipoMensagem(cTipoMSG),null, Opcoes, OpcaoPadrao);
    }
    
    
    
    static public String ListarArquivos(JFrame j, String cCaminhoPadrao, String cTipoArquivo, String cTitulo )
    {
        String cFile="";
        try {
            
            JFileChooser fileChooser = new JFileChooser(new File(cCaminhoPadrao));
            fileChooser.setDialogTitle(cTitulo);
            //fileChooser.setDialogType(JFileChooser.OPEN_DIALOG);
            fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
            //fileChooser.setFileFilter(new ManipulacaoArquivoFiltro("CargaPDV*.ZIP"));
            fileChooser.addChoosableFileFilter(new ManipulacaoArquivoFiltro("*.ZIP"));
            //fileChooser.update(fileChooser.getGraphics());
            int nRetorno=fileChooser.showOpenDialog(j);
            if(nRetorno== JFileChooser.APPROVE_OPTION)
            {
                cFile=fileChooser.getSelectedFile().toString();
            }
        } catch (Exception e) {
            LogDinnamus.Log(e);
        }
        return cFile;
    }

    public static void SetarOpcaoCombo(JComboBox jc, Object nValor)
    {
        try {

            ItemLista item;
            for(int ind=0; ind<jc.getItemCount();ind++)
            {
                item = (ItemLista) jc.getItemAt(ind);
                if(item.getIndice().equals(nValor))
                {
                   jc.setSelectedIndex(ind);
                   break ;
                }
            }
        } catch (Exception e) {
            LogDinnamus.Log(e);
        }
        
    }
    public static boolean PreencherCombo(JComboBox jc, ResultSet rs, String cNomeCampoDescricao, String cNomeCampoValor,boolean  LimparItens){
            return PreencherCombo( jc,  rs, cNomeCampoDescricao, cNomeCampoValor,LimparItens, "objeto" );
    }
    public static boolean PreencherCombo(JComboBox jc, ResultSet rs, String cNomeCampoDescricao, String cNomeCampoValor,boolean  LimparItens, String cTipoIndice )
    {

        try {
            if(LimparItens)
              jc.removeAllItems();

            if(rs!=null){
                while(rs.next()){
                    ItemLista i = new ItemLista();
                    if(cTipoIndice.equalsIgnoreCase("objeto")){
                        i.setIndice(rs.getObject(cNomeCampoValor));
                    }if(cTipoIndice.equalsIgnoreCase("string")){
                        i.setIndice(rs.getString(cNomeCampoValor));
                    }
                    i.setDescricao(rs.getString(cNomeCampoDescricao));
                    jc.addItem(i);
                }
            }
            return true;
        } catch (SQLException ex) {
            LogDinnamus.Log(ex);
            return false;
        }
    }
    public static String JanelaPegaSenha(String Titulo, String Msg){
        String Senha ="";
        try {
            
            JLabel label = new JLabel(Msg);
            JPasswordField jpf = new JPasswordField();

            SwingUtilidade.RequestFocus(jpf);
            
            JOptionPane.showConfirmDialog(null,
            new Object[]{label, jpf}, Titulo,
            
            JOptionPane.OK_CANCEL_OPTION);               
            
            
            Senha = jpf.getText();
        } catch (Exception e) {
            LogDinnamus.Log(e, true);
        }
        return Senha;
 
    }
    public static void BloquearDesbloquearComponentes( java.awt.Container p, boolean  bStatus)
    {

        for(int i=0; i< p.getComponentCount() ; i++)
        {

           if(p.getComponent(i) instanceof  javax.swing.JPanel)
           {
                BloquearDesbloquearComponentes((java.awt.Container) p.getComponent(i),bStatus);
           }
           else
           {
                p.getComponent(i).setEnabled(bStatus);

           }
        }
    }
    
}
