package io.oliverj.chat.server;

import io.oliverj.chat.server.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class Application {
    public static void main(String[] args) {
        var sf = HibernateUtil.getSessionFactory();
        Session session = sf.openSession();
        Transaction tx = session.beginTransaction();
    }
}
