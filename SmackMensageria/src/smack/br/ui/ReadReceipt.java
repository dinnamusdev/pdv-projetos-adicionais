/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smack.br.ui;

import java.awt.List;
import java.util.Map;
import org.jivesoftware.smack.packet.ExtensionElement;
import org.jivesoftware.smack.packet.PacketExtension;
import org.jivesoftware.smack.provider.EmbeddedExtensionProvider;

/**
 *
 * @author DSWM
 */
public class ReadReceipt implements ExtensionElement  {

    public static final String NAMESPACE = "urn:xmpp:read";
    public static final String ELEMENT = "read";

    private String id; /// original ID of the delivered message

    public ReadReceipt(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    @Override
    public String getElementName() {
        return ELEMENT;
    }

    @Override
    public String getNamespace() {
        return NAMESPACE;
    }

    @Override
    public String toXML() {
        return "<read xmlns='" + NAMESPACE + "' id='" + id + "'/>";
    }

    public static class Provider extends EmbeddedExtensionProvider {

        @Override
        protected ExtensionElement createReturnExtension(String arg0, String arg1, Map arg2, java.util.List arg3) {
           //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            return new ReadReceipt(arg2.get("id").toString());
        }
    }
    
}
