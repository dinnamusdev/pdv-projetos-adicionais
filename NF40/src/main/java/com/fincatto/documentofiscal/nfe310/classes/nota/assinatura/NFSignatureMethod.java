package com.fincatto.documentofiscal.nfe310.classes.nota.assinatura;

import org.simpleframework.xml.Attribute;

public class NFSignatureMethod {

	@Attribute(name = "Algorithm", required = false)
    private String algorithm;

	public String getAlgorithm() {
		return algorithm;
	}

	public void setAlgorithm(String algorithm) {
		this.algorithm = algorithm;
	}
	
}
