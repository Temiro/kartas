package java2;

import java2.jpa.helpers.EntityManagerHelper;

import javax.persistence.EntityManager;

public class Mainas {
    public static void main(String[] args) {
        EntityManager em = EntityManagerHelper.getEntityManager();
    }
}
