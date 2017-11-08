package ua.sergii.hibernate;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

    private static final SessionFactory SESSION_FACTORY = buildSessionFactory();

    private HibernateUtil() {

    }

    private static SessionFactory buildSessionFactory() {
	try {
	    return new Configuration().configure().buildSessionFactory();
	} catch (Throwable e) {
	    System.err.println("Initial SessionFactory creation failed." + e);
	    throw new ExceptionInInitializerError(e);
	}
    }

    public static SessionFactory getSessionFactory() {
	return SESSION_FACTORY;
    }
}
