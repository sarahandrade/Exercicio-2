package com.ti2cc;

import java.sql.*;

public class DAO {
	private Connection conexao;
	
	public DAO() {
		conexao = null;
	}
	
	public boolean conectar() {
		String driverName = "org.postgresql.Driver";                    
		String serverName = "localhost";
		String mydatabase = "teste";
		int porta = 5432;
		String url = "jdbc:postgresql://" + serverName + ":" + porta +"/" + mydatabase;
		String username = "ti2cc";
		String password = "ti@cc";
		boolean status = false;

		try {
			Class.forName(driverName);
			conexao = DriverManager.getConnection(url, username, password);
			status = (conexao == null);
			System.out.println("Conexão efetuada com o postgres!");
		} catch (ClassNotFoundException e) { 
			System.err.println("Conexão NÃO efetuada com o postgres -- Driver não encontrado -- " + e.getMessage());
		} catch (SQLException e) {
			System.err.println("Conexão NÃO efetuada com o postgres -- " + e.getMessage());
		}

		return status;
	}
	
	public boolean close() {
		boolean status = false;
		
		try {
			conexao.close();
			status = true;
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		return status;
	}
	
	public boolean inserirCliente(Cliente cliente) {
		boolean status = false;
		try {  
			Statement st = conexao.createStatement();
			st.executeUpdate("INSERT INTO cliente (cpf, nome, sexo, cep) "
					       + "VALUES ("+cliente.getCpf()+ ", '" + cliente.getnome() + "', '"  
					       + cliente.getsexo() + "', '" + cliente.getcep() + "');");
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}
	
	public boolean atualizarCliente(Cliente cliente) {
		boolean status = false;
		try {  
			Statement st = conexao.createStatement();
			String sql = "UPDATE cliente SET cpf = '" + cliente.getcpf() + "', cep = '"  
				       + cliente.getcep() + "', sexo = '" + usuario.getSexo() + "'"
					   + " WHERE cpf = " + usuario.getcpf();
			st.executeUpdate(sql);
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}
	
	public boolean excluirCliente(int cpf) {
		boolean status = false;
		try {  
			Statement st = conexao.createStatement();
			st.executeUpdate("DELETE FROM cliente WHERE cpf = " + cpf);
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}
	
	
	public Cliente[] getCliente() {
		Cliente[] cliente = null;
		
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = st.executeQuery("SELECT * FROM cliente");		
	         if(rs.next()){
	             rs.last();
	             cliente = new Cliente[rs.getRow()];
	             rs.beforeFirst();

	             for(int i = 0; rs.next(); i++) {
	                cliente[i] = new Cliente(rs.getInt("cpf"), rs.getVarchar("nome").varcharAt, 
	                		                  rs.getString("cep").charAt, rs.getString("sexo").charAt(0));
	             }
	          }
	          st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return cliente;
	}

	
	public Cliente[] getClienteMasculinos() {
		Cliente[] cliente = null;
		
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = st.executeQuery("SELECT * FROM cliente WHERE cliente.sexo LIKE 'M'");		
	         if(rs.next()){
	             rs.last();
	             cliente = new Cliente[rs.getRow()];
	             rs.beforeFirst();

	             for(int i = 0; rs.next(); i++) {
		                cliente[i] = new Cliente(rs.getInt("cpf"), rs.getString("nome").varcharAt, 
                         		                  rs.getString("cep").charAt, rs.getString("sexo").charAt(0));
	             }
	          }
	          st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return cliente;
	}
}