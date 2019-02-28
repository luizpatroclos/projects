package tests;

 public class Documento {
	
	
		//Estado
		private String foto; //Nome do arquivo de imagem
		
		String nome; //Nome da pessoa
		Integer codigo; //Codigo deste documento
		String dataNascimento; //Data de nascimento
		
		public String getFoto() {
			return foto;
		}
		public void setFoto(String foto) {
			this.foto = foto;
		}
		
		public Documento() {
			
		}
		
		public Documento(String nome, Integer codigo) {
			
			this.nome = nome;
			this.codigo = codigo;
			
		}

}
