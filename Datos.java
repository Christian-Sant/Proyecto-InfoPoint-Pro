package ProyectoInfoPointPro;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
//CHRISTIAN JAY LAGO
public class Datos {
	private ArrayList<Libro> lista = new ArrayList<>(); 
	private static final String NOMBREFICHERO = "C:\\Users\\Tarde\\Downloads\\Bibliteca.dat";
	public void cargarLibros() {
		try {
			BufferedReader leerLibros = new BufferedReader(new FileReader(NOMBREFICHERO));
			String linea;
	        lista.clear();
			while((linea = leerLibros.readLine()) != null) {
				String [] partes = linea.split(";");
				if (partes.length == 4) {
                    int id = Integer.parseInt(partes[0]);
                    String titulo = partes[1];
                    String autor = partes[2];
                    double precio = Double.parseDouble(partes[3]);
                    lista.add(new Libro(id, titulo, autor, precio));
                }
			}
			leerLibros.close();
		}
		catch(IOException e) {
			System.out.println(e);
		}
		
	}
	public void guardarLibros(Scanner sc) {
		try {
			BufferedWriter escribirLibros = new BufferedWriter(new FileWriter(NOMBREFICHERO,true));
			int id;
			String titulo;
			String autor;
			double precio;
			while(true) {
				System.out.print("ID: ");
				try {
					id = sc.nextInt();
					
					boolean estar = false;
					try (BufferedReader br = new BufferedReader(new FileReader(NOMBREFICHERO))) {
					    String linea;
					    while ((linea = br.readLine()) != null) {
					        String[] partes = linea.split(";");
					        if (partes.length > 0) {
					            for (Libro libro : lista) {
					                if (libro.getId() == id) {
					                	System.out.println("ID ya existente, use otro.");
					                	estar = true;
					                	break;
					                }
					            }
					            break;
					        }
					    }
					} catch (IOException e) {
					    e.printStackTrace();
					}
					if(!estar) {
						break;
					}
					else {
						sc.nextLine();
					}
				}
				catch(InputMismatchException e) {
        			System.out.println("El formato del ID debe ser Valido.");
        			sc.nextLine();
        		}
			}
			sc.nextLine();
			while(true) {
				System.out.print("Titulo: ");
				try {
					titulo = sc.nextLine();
					break;
				}
				catch(InputMismatchException e) {
        			System.out.println("El formato del Titulo debe ser Valido.");
        			sc.nextLine();
        		}
			}
			while(true) {
				System.out.print("Autor: ");
				try {
					autor = sc.nextLine();
					break;
				}
				catch(InputMismatchException e) {
        			System.out.println("El formato del Autor debe ser Valido.");
        			sc.nextLine();
        		}
			}
			while(true) {
				System.out.print("Precio: ");
				try {
					precio = sc.nextDouble();
					System.out.println("");
					break;
				}
				catch(InputMismatchException e) {
        			System.out.println("El formato del Precio debe ser Valido.");
        			sc.nextLine();
        		}
			}
			Libro libro = new Libro(id,titulo,autor,precio);
			lista.add(libro);
			escribirLibros.write(id + ";" + titulo + ";" + autor + ";" + precio);
			escribirLibros.newLine();
			escribirLibros.close();
		}
		catch(IOException e) {
			System.out.println(e);
		}
	}
	public void constructor() {
		System.out.println("Libros cargados:\n");
        for (Libro libro : lista) {
            System.out.println(libro);
        }
	}
	public void consultarLibro(Scanner sc) {
		int idLibro;
		while(true) {
			System.out.print("Cual es el ID del Libro?: ");
			try {
				idLibro = sc.nextInt();
				break;
			}
			catch(InputMismatchException e) {
    			System.out.println("El formato del ID debe ser Valido.");
    			sc.nextLine();
    		}
		}
		try (BufferedReader br = new BufferedReader(new FileReader(NOMBREFICHERO))) {
		    String linea;
		    boolean estar = false;
		    while ((linea = br.readLine()) != null) {
		        String[] partes = linea.split(";");
		        if (partes.length > 0) {
		            for (Libro libro : lista) {
		                if (libro.getId() == idLibro) {
		                	System.out.println(libro);
		                	estar = true;
		                	break;
		                }
		            }
		            if(!estar) {
	                	System.out.println("Libro no encontrado\n");
		            }
		            break;
		        }
		    }
		} catch (IOException e) {
		    e.printStackTrace();
		}
	}
	public void modificarPrecio(Scanner sc) {
		int idLibro;
		while(true) {
			System.out.print("Cual es el ID del Libro?: ");
			try {
				idLibro = sc.nextInt();
				break;
			}
			catch(InputMismatchException e) {
    			System.out.println("El formato del ID debe ser Valido.");
    			sc.nextLine();
    		}
		}
		try (BufferedReader br = new BufferedReader(new FileReader(NOMBREFICHERO))) {
		    String linea;
		    boolean estar = false;
		    while ((linea = br.readLine()) != null) {
		        String[] partes = linea.split(";");
		        if (partes.length > 0) {
		            for (Libro libro : lista) {
		                if (libro.getId() == idLibro) {
		                	System.out.println("Precio original del Libro: " + libro.getPrecio());		                	
		                	double precioLibro;
		            		while(true) {
		            			System.out.print("Cual sera su precio ahora?: ");
		            			try {
		            				precioLibro = sc.nextDouble();
		            				libro.setPrecio(precioLibro);
		            				BufferedWriter escribirLibros = new BufferedWriter(new FileWriter(NOMBREFICHERO));
		            				for(Libro lineaLibro : lista) {
		            					escribirLibros.write(lineaLibro.getId() + ";" + lineaLibro.getTitulo() + ";" + lineaLibro.getAutor() + ";" + lineaLibro.getPrecio());
		            					escribirLibros.newLine();
		            				}
		            				System.out.println("");
		            				escribirLibros.close();
		            				break;
		            			}
		            			catch(InputMismatchException e) {
		                			System.out.println("El formato del precio debe ser Valido.");
		                			sc.nextLine();
		                		}
		            		}
		                	estar = true;
		                	break;
		                }  
		            }
		            break;
		        }
		    }
		    if(!estar) {
            	System.out.println("Libro no encontrado\n");
            }
		} catch (IOException e) {
		    e.printStackTrace();
		}
	}
	public void eliminarLibro(Scanner sc) {
		int idLibro;
		while(true) {
			System.out.print("Cual es el ID del Libro?: ");
			try {
				idLibro = sc.nextInt();
				break;
			}
			catch(InputMismatchException e) {
    			System.out.println("El formato del ID debe ser Valido.");
    			sc.nextLine();
    		}
		}
		try (BufferedReader br = new BufferedReader(new FileReader(NOMBREFICHERO))) {
		    String linea;
		    boolean estar = false;
		    while ((linea = br.readLine()) != null) {
		        String[] partes = linea.split(";");
		        if (partes.length > 0) {
		            for (Libro libro : lista) {
		                if (libro.getId() == idLibro) {
			                lista.remove(libro);
			                BufferedWriter escribirLibros = new BufferedWriter(new FileWriter(NOMBREFICHERO));
			            	for(Libro lineaLibro : lista) {
			            		escribirLibros.write(lineaLibro.getId() + ";" + lineaLibro.getTitulo() + ";" + lineaLibro.getAutor() + ";" + lineaLibro.getPrecio());
			            		escribirLibros.newLine();
			            	}	
			                estar = true;
			                escribirLibros.close();
			                break;
		                }
		            }
		            
		            if(estar) {
	                	System.out.println("Libro eliminado\n");
		            }
		            break;
		        }
		    }
		    if(!estar) {
            	System.out.println("No se puede eliminar el libro\n");
            }
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void listarLibro() {
		System.out.println("Libros no eliminados:\n");
        for (Libro libro : lista) {
        	if(!libro.isEliminado()) {
                System.out.println(libro);
        	}
        }
	}
}
