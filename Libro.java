package ProyectoInfoPointPro;
public class Libro {
	private int id;
	private String titulo;
	private String autor;
	private double precio;
	private boolean eliminado;
	public Libro(int id, String titulo, String autor, double precio) {
		this.id = id;
		this.titulo = titulo;
		this.autor = autor;
		this.precio = precio;
		this.eliminado = false;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getAutor() {
		return autor;
	}
	@Override
	
	public String toString() {
		return "El libro con el ID:" + getId() + ", con el titulo: " + getTitulo() + ", con el autor: " + getAutor() + ", tiene un precio de: " + getPrecio() + ", ahora mismo "
				+ eliminado() + ".\n";
	}
	public void setAutor(String autor) {
		this.autor = autor;
	}
	public double getPrecio() {
		return precio;
	}
	public void setPrecio(double precio) {
		this.precio = precio;
	}
	public boolean isEliminado() {
		return eliminado;
	}
	public void setEliminado(boolean eliminado) {
		this.eliminado = eliminado;
	}
	private String eliminado() {
		String eliminado;
		if(isEliminado()) {
			eliminado = "esta eliminado";
		}
		else {
			eliminado = "no esta eliminado";
		}
		return eliminado;
	}
}
