package AccesoADatos.Clase.Tema3.hibernate_1;
// package hibernate;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class Main {
	public static void insertarDatos(Session session, Transaction transaction) {
		Persona persona = new Persona("Beatriz", 21);
		session.save(persona);
		transaction.commit();
	}
	
	public static void lecturaDatos(int clavePrimaria, Session session, Transaction transaction) {
		Persona personaResultante = session.get(Persona.class, clavePrimaria);
		
		if(personaResultante != null) {
			System.out.println(personaResultante.toString());
		}
		
		transaction.commit();
	}
	
	public static void modificarDatos(int clavePrimaria, Session session, Transaction transaction) {
		Persona personaModificada = session.get(Persona.class, clavePrimaria);
		
		if(personaModificada != null) {
			personaModificada.setNombre("Mateo");
		}
		
		transaction.commit();
	}
	
	public static void borrarDatos(int clavePrimaria, Session session, Transaction transaction) {
		Persona personaBorrada = session.get(Persona.class, clavePrimaria);
		
		if(personaBorrada != null) {
			session.remove(personaBorrada);
		}
		
		transaction.commit();
	}


	public static void main(String[] args) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		
		lecturaDatos(1, session, transaction);

		transaction.commit();
		session.close();
		HibernateUtil.shutdown();
	}
}
