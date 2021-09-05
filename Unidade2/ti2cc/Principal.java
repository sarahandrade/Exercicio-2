package com.ti2cc;

import com.ti2cc.DAO;
import com.ti2cc.Cliente;

public class Principal {
	
	public static void main(String[] args) {
		
		DAO dao = new DAO();
		
		dao.conectar();

		
		//Inserir um elemento na tabela
		Usuario cliente = new Cliente(11, "pablo", "M",'3125');
		if(dao.inserirCliente(cliente) == true) {
			System.out.println("Inserção com sucesso -> " + usuario.toString());
		}
		
		//Mostrar clientes do sexo masculino		
		System.out.println("==== Mostrar clientes do sexo masculino === ");
		Cliente [] cliente = dao.getClienteMasculinos();
		for(int i = 0; i < cliente.length; i++) {
			System.out.println(cliente[i].toString());
		}

		//Atualizar 
		usuario.setcep("novo cep");
		dao.atualizarCliente(cliente);

		//Mostrar cliente do sexo masculino
		System.out.println("==== Mostrar clientes === ");
		cliente = dao.getCliente();
		for(int i = 0; i < cliente.length; i++) {
			System.out.println(cliente[i].toString());
		}
		
		//Excluir 
		dao.excluirCliente(cliente.getCodigo());
		
		//Mostrar 
		cliente = dao.getCliente();
		System.out.println("==== Mostrar cliente === ");		
		for(int i = 0; i < cliente.length; i++) {
			System.out.println(cliente[i].toString());
		}
		
		dao.close();
	}
}