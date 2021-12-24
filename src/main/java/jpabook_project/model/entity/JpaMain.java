package jpabook_project.model.entity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.*;
import java.sql.Date;
import java.util.Arrays;
import java.util.List;


public class JpaMain {
    static EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpabook_project");

    public static void main(String[] args) {
        Member member = createMember("장민준", "서울","관천로","111-1111", Arrays.asList("종서바보", "못함의 민준"));
        Order order = createOrder(member);
//        Category category = createCategory("카테고리",Arrays.asList("앨범","사진"));
    }
    static Member createMember(String name, String city, String street, String zipcode, List jongseo){
        EntityManager em1 = emf.createEntityManager();
        EntityTransaction tx1 = em1.getTransaction();
        tx1.begin();

        Member member = new Member();
        member.setName(name);
        member.setCity(city);
        member.setStreet(street);
        member.setJongseo(jongseo);
        member.setZipcode(zipcode);

        System.out.println("member = " + member.getName());

        em1.persist(member);
        tx1.commit();

        em1.close();
        return member;
    }
    static Order createOrder(Member member) {
        EntityManager em2 = emf.createEntityManager();
        EntityTransaction tx2 = em2.getTransaction();
        tx2.begin();

        Order order = new Order();
        order.setMember(member);
        order.setOrderDate(Date.valueOf("2021-12-02"));

        em2.persist(order);
        tx2.commit();

        em2.close();
        return order;
    }

    static Category createCategory(String name, List item){
        EntityManager em3 = emf.createEntityManager();
        EntityTransaction tx3 = em3.getTransaction();
        tx3.begin();

        Category category = new Category();
        category.setName(name);
        category.setItems(item);

        em3.persist(category);
        tx3.commit();

        em3.close();
        return category;
    }

}
