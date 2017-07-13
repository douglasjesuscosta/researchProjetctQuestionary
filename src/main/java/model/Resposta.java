package model;

import annotation.Column;
import annotation.Table;

@annotation.Table(name="respostas", useCase="ManterRespostas")
public class Resposta extends Model {
	
	@Column(name="resposta_id", pk=true, obrigatory=true, nameOnView="")
	private Integer resposta_id;
	@Column(name="resposta1", obrigatory=true, nameOnView = "")
	private String respPerg1;
	@Column(name="resposta2", obrigatory=true, nameOnView = "")
	private String respPerg2;
	@Column(name="resposta3", obrigatory=true, nameOnView = "")
	private String respPerg3;
	@Column(name="resposta4", obrigatory=true, nameOnView = "")
	private String respPerg4;
	@Column(name="resposta5", obrigatory=true, nameOnView = "")
	private String respPerg5;
	@Column(name="resposta6", obrigatory=true, nameOnView = "")
	private String respPerg6;
	
	public Integer getResposta_id() {
		return resposta_id;
	}
	public void setResposta_id(Integer id) {
		this.resposta_id = id;
	}
	public String getRespPerg5() {
		return respPerg5;
	}
	public void setRespPerg5(String respPerg5) {
		this.respPerg5 = respPerg5;
	}
	public String getRespPerg6() {
		return respPerg6;
	}
	public void setRespPerg6(String respPerg6) {
		this.respPerg6 = respPerg6;
	}
	
	public String getRespPerg1() {
		return respPerg1;
	}
	public void setRespPerg1(String respPerg1) {
		this.respPerg1 = respPerg1;
	}
	public String getRespPerg2() {
		return respPerg2;
	}
	public void setRespPerg2(String respPerg2) {
		this.respPerg2 = respPerg2;
	}
	public String getRespPerg3() {
		return respPerg3;
	}
	public void setRespPerg3(String respPerg3) {
		this.respPerg3 = respPerg3;
	}
	public String getRespPerg4() {
		return respPerg4;
	}
	public void setRespPerg4(String respPerg4) {
		this.respPerg4 = respPerg4;
	}
	@Override
	public Object getPk() {
		return resposta_id;
	}
	@Override
	public void setPk(Object pk) {
		resposta_id = (Integer) pk;
		
	}

}
