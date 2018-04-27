/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.gov.inpi.services;

import java.util.List;

import br.gov.inpi.marcas.beans.MrcFiguraProcess;
import br.gov.inpi.marcas.beans.MrcProcesso;


public interface ServiceIPOfficeInterface {

    List<MrcProcesso> findRange(int[] range);
    
    List<MrcFiguraProcess> findProcess(String val01, String val02, String val03);
    
    byte[] searchFile (String val01, String val02, String val03);
  
}
