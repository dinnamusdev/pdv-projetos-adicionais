/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa.infra;

import br.com.log.LogDinnamus;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Fernando
 */
public class Persistencia {
    
    private static EntityManager INSTANCE_EntityManager;
    /**
     * @return the INSTANCE_EntityManager
     */
    private static EntityManagerFactory factory;

    private EntityManagerFactory getINSTANCE_EntityManagerFactory() {
        try {
            if (factory == null) {
                //Icon ico = new javax.swing.ImageIcon(getClass().getResource("/integracao/ui/icons8-upload-para-nuvem-48.png"));
                //InteracaoDuranteProcessamento.Mensagem_Iniciar("Conectando retaguarda web", "AGUARDE...", false, ico);
                factory = Persistence.createEntityManagerFactory("dinnamuspdvPU");
                //InteracaoDuranteProcessamento.Mensagem_Terminar();
            }
        } catch (Exception e) {
            LogDinnamus.Log(e, true);
        }
        
        return factory;
    }
    
    public EntityManager getEntityManager() {
        return getINSTANCE_EntityManagerFactory().createEntityManager();
    }    
}
