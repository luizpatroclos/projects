package org.prosur.catalog.ws;

import java.rmi.RemoteException;
import java.util.StringTokenizer;

import org.apache.axis.AxisFault;

public class TestClient {
	
	
	/**
	 * @param args
	 * @throws RemoteException 
	 */
	@SuppressWarnings("unused")
	public static void main(String[] args) throws RemoteException {
		
		System.setProperty("javax.net.ssl.keyStoreType", "JKS"); 
    	System.setProperty("javax.net.ssl.keyStore", "D:\\Prosur_jboss_config\\certificado_03\\clientBR.jks"); 
    	System.setProperty("javax.net.ssl.keyStorePassword", "drew081");
    	
    	//String str = "PI9805037,PI9804042,PI9708776,PI9604923,PI9006505,PI8703081,PI8702959,PI0006335,MU8300493,MU8300456,MU8300349,MU7502031,DI6801050,DI6702503,DI6500005,DI6404803,DI6404800,DI6403350,DI6401623,DI6401316,DI6401205,DI6304838,DI6304830,DI6303355,DI6300687,DI6203531,DI6203446,DI6002327,DI5701797,DI5500698,DI5401035,DI5400881,DI5400850,DI5400692,DI2400003,102012028214";
    	
    	//String teste = "339962,339961,339960,339959,339958,339957,339956,339853,339851,339850,339799,339797,339796,339794,339793,339792,339788,339787,339783,339782";
    	
    	
    	String teste = "340263,340260,340261,340262,340259";
    	
    	
		try {
			
			StringTokenizer processo = new StringTokenizer(teste, ",");
			
			ProsurIPRecordWS valor1 = new ProsurIPRecordWSProxy();
			
			while (processo.hasMoreTokens()) {
				
				IpRecord iprecord = new IpRecord();
				
				String aux1 = processo.nextToken();
				
				long proc = Long.parseLong(aux1);
				
				iprecord.setIpRecordId(proc);
				
				valor1.removeRecord(iprecord, "lasilva");
				
			     System.out.println(iprecord.getIpRecordId()+ "= Removido com Sucesso !!!");
			     
			     
			}

			
			/*
			 * 
			 * 
			 * System.out.println("Valor"+ System.getProperty("javax.net.ssl.keyStore"));
			   System.out.println("Valor"+ System.getProperty("javax.net.ssl.keyStorePassword"));
			 * ProsurIPRecordWS valor1 = new ProsurIPRecordWSProxy();
			
			IpRecord iprecord = new IpRecord();
		
			iprecord.setIpRecordId(339450l);
			
			DistinctiveSign iprecordRetorno = (DistinctiveSign)valor1.getRecord(iprecord, "lasilva");
			
			System.out.println("valores do beasn"+iprecordRetorno.getDistinctiveSignType());*/
			
			
			
		} catch (AxisFault e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// TODO Auto-generated method stub

	}

}
