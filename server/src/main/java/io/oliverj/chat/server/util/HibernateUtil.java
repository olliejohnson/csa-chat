package io.oliverj.chat.server.util;

import io.oliverj.chat.server.data.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    private static final SessionFactory SESSION_FACTORY =
            new Configuration()
                    .addAnnotatedClass(User.class)
                    .buildSessionFactory();

    private HibernateUtil() {}

    public static SessionFactory getSessionFactory() { return SESSION_FACTORY; }
}
