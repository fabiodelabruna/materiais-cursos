package br.unisul.transportadora.bean;

/**
 * Classe Java que representa um frete e seus atributos.
 * @author Fabio Dela Bruna
 */
public class FreteBean {

	// Atributos
	
	/**
	 * C�digo do Frete.
	 */
	private Integer codigo;
	
	/**
	 * Quilometragem a ser percorrida pelo caminh�o no frete.
	 */
	private Double quilometragem;
	
	/**
	 * Valor do Frete.
	 * Para este exemplo, ser� calculado da seguinte forma:
	 * Valor = 2 x quilometragem.
	 */
	private Double valor;
	
	/**
	 * C�digo da transportadora que administrar� o Frete.
	 */
	private Integer codigoTransportadora;
	
	/**
	 * C�digo do caminh�o que far� o Frete.
	 */
	private Integer codigoCaminhao;
	
	/**
	 * C�digo do cliente que solicitou o frete.
	 */
	private Integer codigoCliente;
	
	
	// Construtores.
	
	/**
	 * Construtor default.
	 */
	public FreteBean() {
		super();
	}

	
	// Getters e Setters

	/**
	 * @return the codigo
	 */
	public Integer getCodigo() {
		return codigo;
	}

	/**
	 * @param codigo the codigo to set
	 */
	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	/**
	 * @return the quilometragem
	 */
	public Double getQuilometragem() {
		return quilometragem;
	}

	/**
	 * @param quilometragem the quilometragem to set
	 */
	public void setQuilometragem(Double quilometragem) {
		this.quilometragem = quilometragem;
	}

	/**
	 * @return the valor
	 */
	public Double getValor() {
		return valor;
	}

	/**
	 * @param valor the valor to set
	 */
	public void setValor(Double valor) {
		this.valor = valor;
	}

	/**
	 * @return the codigoTransportadora
	 */
	public Integer getCodigoTransportadora() {
		return codigoTransportadora;
	}

	/**
	 * @param codigoTransportadora the codigoTransportadora to set
	 */
	public void setCodigoTransportadora(Integer codigoTransportadora) {
		this.codigoTransportadora = codigoTransportadora;
	}

	/**
	 * @return the codigoCaminhao
	 */
	public Integer getCodigoCaminhao() {
		return codigoCaminhao;
	}

	/**
	 * @param codigoCaminhao the codigoCaminhao to set
	 */
	public void setCodigoCaminhao(Integer codigoCaminhao) {
		this.codigoCaminhao = codigoCaminhao;
	}

	/**
	 * @return the codigoCliente
	 */
	public Integer getCodigoCliente() {
		return codigoCliente;
	}

	/**
	 * @param codigoCliente the codigoCliente to set
	 */
	public void setCodigoCliente(Integer codigoCliente) {
		this.codigoCliente = codigoCliente;
	}
	
	
}
