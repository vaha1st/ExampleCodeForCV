package com.vaha1st.dao;

import com.vaha1st.entity.TempConversion;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TemperatureDAOimpl implements TemperatureDAO {

    // need to inject session factory
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<TempConversion> getTempEntity() {

        Session currentSession = sessionFactory.getCurrentSession();

        Query<TempConversion> query = currentSession.createQuery("from TempConversion", TempConversion.class);

        List<TempConversion> conversions = query.getResultList();

        return conversions;
    }

    @Override
    public void saveConversion(TempConversion tempConversion) {

        Session currentSession = sessionFactory.getCurrentSession();

        currentSession.save("conversion", tempConversion);
    }
}
