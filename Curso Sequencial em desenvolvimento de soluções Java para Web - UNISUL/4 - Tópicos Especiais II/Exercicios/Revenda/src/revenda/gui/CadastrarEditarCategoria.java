package revenda.gui;

import revenda.domain.Categoria;
import revenda.service.CategoriaService;
import framework.gui.CadastrarEditarBase;
import framework.gui.components.TextFieldBase;
import framework.service.ServiceBase;

public class CadastrarEditarCategoria extends CadastrarEditarBase {

	private static final long serialVersionUID = 1L;
	
	
	/**
	 * Construtor default.
	 * @param title T�tulo do jDialog.
	 */
	public CadastrarEditarCategoria(String title) {
		super(title);
	}
	
	/**
	 * Construtor com par�metros.
	 * @param c Objeto do tipo Categoria.
	 * @param title T�tulo do jDialog.
	 */
	public CadastrarEditarCategoria(Categoria c, String title) {
		super(c, title);
	}
	
	
	@Override
	protected ServiceBase createServiceBase() throws Exception {
		return new CategoriaService();
	}

	@Override
	protected Object getAtributosTextFields(TextFieldBase[] textFieldVector, String[] labels) {
		Categoria c = new Categoria();
		for (int i = 0; i < textFieldVector.length; i++) {
			if (labels[i].equalsIgnoreCase("C�digo"))
				c.setCdCategoria(Integer.parseInt(textFieldVector[i].getText()));
			if (labels[i].equalsIgnoreCase("Descri��o"))
				c.setDsCategoria(textFieldVector[i].getText());
		}
		return c;
	}

	@Override
	protected String getLabels() {
		return "C�digo,Descri��o";
	}

	@Override
	protected String setaAtributosTextFields(Object obj, String label) {
		Categoria c = (Categoria) obj;
		
		if (label.equalsIgnoreCase("C�digo"))
			return String.valueOf(c.getCdCategoria());
		if (label.equalsIgnoreCase("Descri��o"))
			return c.getDsCategoria();
		
		return "";
	}

}
