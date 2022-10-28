/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.reflexao;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 *
 * @author desenvolvedor
 */
public class Reflexao {
    
    
    private static Method mtMetodoGet=null;
    public static String getNomeMetodo(Object obj, String cNomeAtributo)
    {
        
        try {
        
            Field[] flCampo=obj.getClass().getDeclaredFields();

            for (int i = 0; i < flCampo.length; i++) {
                    if (flCampo[i].getName().toString().equalsIgnoreCase(cNomeAtributo))
                    {
                        cNomeAtributo=flCampo[i].getName().toString().replace("_", "");
                        break;
                    }
                }
        } catch (Exception exception) {
                exception.printStackTrace();
        }
        
        cNomeAtributo = cNomeAtributo.substring(0,1).toUpperCase() + (cNomeAtributo.length()>1  ?  cNomeAtributo.substring(1, cNomeAtributo.length()) : "");

        return cNomeAtributo;
    }
    public static String getNomeMetodo2(Object obj, String cNomeAtributo, String cTipoMetodo )
    {
        String cNomeMetodo="";
        try {

            Method[] flCampo=obj.getClass().getDeclaredMethods();

            for (int i = 0; i < flCampo.length; i++) {
                    System.out.print(flCampo[i].getName().toString().toUpperCase());
                    System.out.print("\n");

                    if (flCampo[i].getName().toString().toUpperCase().equals(cTipoMetodo.toUpperCase() + cNomeAtributo.toUpperCase()))
                    {
                        cNomeMetodo=flCampo[i].getName().toString().replace("_", "");

                        break;
                    }
                }
        } catch (Exception exception) {
                exception.printStackTrace();
        }

       //cNomeAtributo = cNomeAtributo.substring(0,1).toUpperCase() + (cNomeAtributo.length()>1  ?  cNomeAtributo.substring(1, cNomeAtributo.length()) : "");

        return cNomeMetodo;
    }
    public static Object getAtributo(String cNomeAtributo, Object obj) 
    {
        Object obRetorno=new Object(); 
        
        
        try {        
            cNomeAtributo=getNomeMetodo(obj, cNomeAtributo);
            
            mtMetodoGet = obj.getClass().getDeclaredMethod("get" + cNomeAtributo);
            
            obRetorno = mtMetodoGet.invoke(obj);
            
        } catch (Exception exception) {
            exception.printStackTrace();
            obRetorno=null;
        }

        return obRetorno;
        
    }
    public static Object setAtributo(String cNomeAtributo, Object obj, Object Valor)
    {
        Object obRetorno=new Object();
        

        try {
            String cNomeMetodo="";
            cNomeMetodo=getNomeMetodo2(obj, cNomeAtributo,"set");
            if(cNomeMetodo.length()>0)
            {
               mtMetodoGet = obj.getClass().getDeclaredMethod(cNomeMetodo ,  Valor.getClass() );

               obRetorno = mtMetodoGet.invoke(obj,Valor);
            }

        } catch (Exception exception) {
            exception.printStackTrace();
            obRetorno=null;
        }

        return obj;

    }
}
