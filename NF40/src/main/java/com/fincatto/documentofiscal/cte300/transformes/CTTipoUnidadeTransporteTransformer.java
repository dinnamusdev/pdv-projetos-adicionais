package com.fincatto.documentofiscal.cte300.transformes;

import org.simpleframework.xml.transform.Transform;

import com.fincatto.documentofiscal.cte300.classes.CTTipoUnidadeTransporte;

public class CTTipoUnidadeTransporteTransformer implements Transform<CTTipoUnidadeTransporte> {

	@Override
	public CTTipoUnidadeTransporte read(String arg0) throws Exception {
		// TODO Auto-generated method stub
		return CTTipoUnidadeTransporte.valueOfCodigo(arg0);
	}

	@Override
	public String write(CTTipoUnidadeTransporte arg0) throws Exception {
		// TODO Auto-generated method stub
		return arg0.getCodigo();
	}

}
