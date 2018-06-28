package revenda.gui;

import revenda.domain.Veiculo;
import revenda.service.VeiculoService;
import framework.gui.CadastrarEditarBase;
import framework.gui.components.TextFieldBase;
import framework.service.ServiceBase;

public class CadastrarEditarVeiculo extends CadastrarEditarBase {

	private static final long serialVersionUID = 1L;

	
	public CadastrarEditarVeiculo(Veiculo v, String title) {
		super(v, title);
	}
	
	
	public CadastrarEditarVeiculo(String title) {
		super(title);
	}



	@Override
	protected ServiceBase createServiceBase() throws Exception {
		return new VeiculoService();
	}

	@Override
	protected Object getAtributosTextFields(TextFieldBase[] textFieldVector, String[] labels) {
		Veiculo v = new Veiculo();
		for (int i = 0; i < textFieldVector.length; i++) {
			if (labels[i].equalsIgnoreCase("C�digo"))
				v.setCdVeiculo(Integer.parseInt(textFieldVector[i].getText()));
			if (labels[i].equalsIgnoreCase("Montadora")) 
				v.setCdMontadora(Integer.parseInt(textFieldVector[i].getText()));
			if (labels[i].equalsIgnoreCase("Categoria"))
				v.setCdCategoria(Integer.parseInt(textFieldVector[i].getText()));
			if (labels[i].equalsIgnoreCase("Descri��o"))
				v.setDsVeiculo(textFieldVector[i].getText());
			if (labels[i].equalsIgnoreCase("Ano Fabrica��o"))
				v.setAnoFabricacao(Integer.parseInt(textFieldVector[i].getText()));
			if (labels[i].equalsIgnoreCase("Ano Modelo"))
				v.setAnoModelo(Integer.parseInt(textFieldVector[i].getText()));
			if (labels[i].equalsIgnoreCase("Valor"))
				v.setValorVeiculo(Double.parseDouble(textFieldVector[i].getText()));
			if (labels[i].equalsIgnoreCase("Cor"))
				v.setCorVeiculo(textFieldVector[i].getText());
		}
		return v;
	}

	@Override
	protected String getLabels() {
		return "C�digo,Montadora,Categoria,Descri��o,Ano Fabrica��o,Ano Modelo,Valor,Cor";
	}

	@Override
	protected String setaAtributosTextFields(Object obj, String label) {
		Veiculo v = (Veiculo) obj;
		
		if (label.equalsIgnoreCase("C�digo"))
			return String.valueOf(v.getCdVeiculo());
		if (label.equalsIgnoreCase("Montadora"))
			return String.valueOf(v.getCdMontadora());
		if (label.equalsIgnoreCase("Categoria"))
			return String.valueOf(v.getCdCategoria());
		if (label.equalsIgnoreCase("Descri��o"))
			return v.getDsVeiculo();			
		if (label.equalsIgnoreCase("Ano Fabrica��o"))
			return String.valueOf(v.getAnoFabricacao());
		if (label.equalsIgnoreCase("Ano Modelo"))
			return String.valueOf(v.getAnoModelo());
		if (label.equalsIgnoreCase("Valor"))
			return String.valueOf(v.getValorVeiculo());
		if (label.equalsIgnoreCase("Cor"))
			return v.getCorVeiculo();
		
		return "";
	}
}
