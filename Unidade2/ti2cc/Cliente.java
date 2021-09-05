package com.ti2cc;

public class Cliente {
	private int cpf;
	private varchar nome;
	private char sexo;
	private char cep;
	
	public Cliente() {
		this.cpf = -1;
		this.nome = "";
		this.sexo = "";
		this.cep = '*';
	}
	
	public Cliente(int cpf, varchar nome, char sexo, char cep) {
		this.cpf = cpf;
		this.nome = nome;
		this.sexo = sexo;
		this.cep = cep;
	}

	public int getCpf() {
		return cpf;
	}

	public void setCpf(int cpf) {
		this.cpf = cpf;
	}

	public Varchar getNome() {
		return nome;
	}

	public void setnome(Varchar nome) {
		this.nome = nome;
	}

	public char getsexo() {
		return sexo;
	}

	public void setSexo(char sexo) {
		this.sexo = sexo;
	}

	public char getcep() {
		return cep;
	}

	public void setCep(char cep) {
		this.cep = cep;
	}

	@Override
	public String toString() {
		return "Cliente [cpf=" + cpf + ", nome=" + nome + ", sexo=" + sexo + ", cep=" + cep + "]";
	}	
}
