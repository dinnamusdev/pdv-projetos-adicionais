package com.fincatto.documentofiscal.cte300.transformes;

import org.simpleframework.xml.transform.Transform;

import com.fincatto.documentofiscal.cte300.classes.CTTipoDocumentoTransporteAnterior;

public class CTTipoDocumentoTransporteAnteriorTransformer implements Transform<CTTipoDocumentoTransporteAnterior> {

	@Override
	public CTTipoDocumentoTransporteAnterior read(String arg0) throws Exception {
		// TODO Auto-generated method stub
		return CTTipoDocumentoTransporteAnterior.valueOfCodigo(arg0);
	}

	@Override
	public String write(CTTipoDocumentoTransporteAnterior arg0)throws Exception {
		// TODO Auto-generated method stub
		return arg0.getCodigo();
	}

}
