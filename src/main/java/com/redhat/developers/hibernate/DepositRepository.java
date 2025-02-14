package com.redhat.developers.hibernate;

import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;
import org.hibernate.reactive.mutiny.Mutiny;

@ApplicationScoped
public class DepositRepository {

    @Inject
    Mutiny.SessionFactory sessionFactory;

    //@Inject
    //JDBCPool client;

    /*public Uni<Deposit> addDeposit(Deposit deposit) {
        return sessionFactory.withTransaction((session, tx) ->
                session.persist(deposit).replaceWith(deposit));
    }

    public Multi<Deposit> streamAll() {
        *//*return client.query("SELECT depositCode, currency, amount FROM Deposit ")
                .execute()
                .onItem()
                .transformToMulti(set -> Multi.createFrom().iterable(set))
                .onItem()
                .transform(Deposit::from);*//*
        return null;
    }*/

}
