package com.tpe.repository;

import com.tpe.domain.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository // Component i da iceriyor. Spring Framework e bu classimin repo katmani oldugunu soyluyorum.
public class StudentRepositoryImpl implements StudentRepository{

    @Autowired // db katmaninda oldugum icin sf bagimliligimi enjekte ettim.
    private SessionFactory sessionFactory;

    @Override
    public List<Student> getAll() {
        Session session= sessionFactory.openSession();
        Transaction tx=session.beginTransaction();

        List<Student> listOfStudents=session.createQuery("from Student", Student.class).getResultList();

        tx.commit();
        session.close();
        return listOfStudents;
    }

    @Override
    public Optional<Student> findById(Long id) {
        Session session= sessionFactory.openSession();
        Transaction tx=session.beginTransaction();

        Student student= session.get(Student.class,id);
        Optional<Student> optionalStudent=Optional.ofNullable(student); // eger donen deger null ise
                                                    // ici bos bir student objesi olusturup dondurur.

        tx.commit();
        session.close();
        return optionalStudent;
    }

    @Override
    public void save(Student student) {
        Session session= sessionFactory.openSession();
        Transaction tx=session.beginTransaction();

        session.saveOrUpdate(student); // db de bu obje varsa update et, yoksa create et.

        tx.commit();
        session.close();
    }

    @Override
    public void update(Student student) {
        Session session= sessionFactory.openSession();
        Transaction tx=session.beginTransaction();

        session.update(student);

        tx.commit();
        session.close();

    }

    @Override
    public void delete(Long id) {
        Session session= sessionFactory.openSession();
        Transaction tx=session.beginTransaction();

        Student student=session.load(Student.class,id);
        session.delete(student);

        tx.commit();
        session.close();

    }

}
